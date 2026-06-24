#!/usr/bin/env node

import { createReadStream } from 'node:fs';
import { readdir, stat } from 'node:fs/promises';
import { createServer } from 'node:http';
import { createRequire } from 'node:module';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const require = createRequire(import.meta.url);
const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');

const options = {
  allowIssues: false,
  headed: false,
};

const defaultTargets = [
  'io.github.plortinus.model2blockly/examples/generated/app_maker_ecore',
];

const positional = [];
for (const arg of process.argv.slice(2)) {
  if (arg === '--allow-issues') options.allowIssues = true;
  else if (arg === '--headed') options.headed = true;
  else if (arg === '--help' || arg === '-h') {
    printUsage();
    process.exit(0);
  } else positional.push(arg);
}

let chromium;
try {
  ({ chromium } = require('playwright'));
} catch (error) {
  console.error('Playwright is required for browser smoke tests.');
  console.error('Install it from the repository root with:');
  console.error('  npm install --save-dev playwright');
  console.error('  npx playwright install chromium');
  process.exit(2);
}

const targetInputs = positional.length ? positional : defaultTargets;
const targets = [];
for (const input of targetInputs) {
  targets.push(await resolveTarget(input));
}

const server = await startServer(repoRoot);
const baseUrl = `http://127.0.0.1:${server.address().port}`;
const browser = await chromium.launch({ headless: !options.headed });
const results = [];

try {
  for (const target of targets) {
    results.push(await testTarget(browser, baseUrl, target, options));
  }
} finally {
  await browser.close();
  await new Promise((resolve) => server.close(resolve));
}

printResults(results);
process.exit(results.every((result) => result.ok) ? 0 : 1);

function printUsage() {
  console.log(`Usage: node scripts/smoke-test-generated.mjs [options] [generated-dir-or-html ...]

Options:
  --allow-issues   Do not fail when the generated editor reports validation issues.
  --headed         Show the browser while running the smoke test.
  -h, --help       Show this help.

Default targets:
${defaultTargets.map((target) => `  - ${target}`).join('\n')}`);
}

async function resolveTarget(input) {
  const absolute = path.resolve(repoRoot, input);
  const info = await stat(absolute);
  if (info.isFile()) {
    if (!absolute.endsWith('_standalone.html')) {
      throw new Error(`Expected a *_standalone.html file: ${input}`);
    }
    return {
      name: path.basename(path.dirname(absolute)),
      dir: path.dirname(absolute),
      html: absolute,
    };
  }
  if (!info.isDirectory()) throw new Error(`Target is not a file or directory: ${input}`);
  const html = await findStandaloneHtml(absolute);
  if (!html) throw new Error(`No *_standalone.html found in ${input} or ${input}/html`);
  return {
    name: path.basename(absolute),
    dir: absolute,
    html,
  };
}

async function findStandaloneHtml(dir) {
  const files = await readdir(dir);
  const html = files.find((file) => file.endsWith('_standalone.html'));
  if (html) return path.join(dir, html);

  const htmlDir = path.join(dir, 'html');
  try {
    const htmlDirInfo = await stat(htmlDir);
    if (!htmlDirInfo.isDirectory()) return null;
    const htmlFiles = await readdir(htmlDir);
    const nestedHtml = htmlFiles.find((file) => file.endsWith('_standalone.html'));
    return nestedHtml ? path.join(htmlDir, nestedHtml) : null;
  } catch (error) {
    return null;
  }
}

async function startServer(root) {
  const server = createServer(async (request, response) => {
    try {
      const url = new URL(request.url || '/', 'http://127.0.0.1');
      const rawPath = decodeURIComponent(url.pathname.replace(/^\/+/, ''));
      const filePath = path.resolve(root, rawPath || 'index.html');
      if (!filePath.startsWith(root + path.sep) && filePath !== root) {
        response.writeHead(403);
        response.end('Forbidden');
        return;
      }
      const info = await stat(filePath);
      if (!info.isFile()) {
        response.writeHead(404);
        response.end('Not found');
        return;
      }
      response.writeHead(200, { 'Content-Type': contentType(filePath) });
      createReadStream(filePath).pipe(response);
    } catch (error) {
      response.writeHead(404);
      response.end('Not found');
    }
  });
  await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
  return server;
}

async function testTarget(browser, baseUrl, target, opts) {
  const page = await browser.newPage({ viewport: { width: 1280, height: 800 } });
  const relative = path.relative(repoRoot, target.html).split(path.sep).map(encodeURIComponent).join('/');
  const url = `${baseUrl}/${relative}`;
  const result = {
    name: target.name,
    url,
    ok: false,
    roots: [],
    issues: '',
    error: '',
  };
  try {
    await page.goto(url, { waitUntil: 'load', timeout: 30000 });
    await page.waitForFunction(() => Boolean(window.Blockly) && typeof window.importModelJSON === 'function', null, { timeout: 30000 });
    const initialMode = await page.evaluate(() => ({
      developerMode: document.body.classList.contains('developer-mode'),
      visibleDeveloperTabs: [...document.querySelectorAll('#tabs [data-developer-only="true"]')]
        .filter((el) => getComputedStyle(el).display !== 'none')
        .map((el) => el.textContent.trim()),
    }));
    if (initialMode.developerMode || initialMode.visibleDeveloperTabs.length > 0) {
      throw new Error(`Generated editor should start in Build mode: ${JSON.stringify(initialMode)}`);
    }
    await page.getByText('Load Sample', { exact: true }).click();
    await page.waitForFunction(() => {
      const text = document.getElementById('jsonView')?.textContent || '';
      return text.includes('"_type"');
    }, null, { timeout: 10000 });
    const state = await page.evaluate(() => {
      const jsonText = document.getElementById('jsonView')?.textContent || '';
      const issues = (document.getElementById('issuesView')?.textContent || '').trim();
      let model = null;
      try { model = JSON.parse(jsonText); } catch (error) {}
      return {
        parsed: Array.isArray(model),
        roots: Array.isArray(model) ? model.map((node) => node && node._type).filter(Boolean) : [],
        issues,
        jsonLength: jsonText.length,
      };
    });
    result.roots = state.roots;
    result.issues = state.issues;
    if (!state.parsed || state.roots.length === 0) {
      throw new Error('Load Sample did not produce a non-empty JSON model.');
    }
    if (!opts.allowIssues && state.issues && state.issues !== 'No validation issues') {
      throw new Error(`Validation issues reported: ${state.issues}`);
    }
    const domainXmi = await page.evaluate(() => {
      const jsonText = document.getElementById('jsonView')?.textContent || '';
      const model = JSON.parse(jsonText);
      if (typeof window.modelToDomainXMI !== 'function') {
        return { available: false };
      }
      const xmi = window.modelToDomainXMI(model);
      const doc = new DOMParser().parseFromString(xmi, 'application/xml');
      return {
        available: typeof window.modelToDomainXMI === 'function',
        length: xmi.length,
        parseErrors: doc.getElementsByTagName('parsererror').length,
        usesEmfXmiNamespace: xmi.includes('xmlns:xmi="http://www.omg.org/XMI"'),
        hasXmiId: xmi.includes('xmi:id='),
        leaksRuntimeBlockId: xmi.includes('_blockId=') || xmi.includes('<_blockId>'),
        firstRoot: Array.isArray(model) && model[0] ? model[0]._type : '',
      };
    });
    if (!domainXmi.available
        || domainXmi.parseErrors !== 0
        || !domainXmi.usesEmfXmiNamespace
        || !domainXmi.hasXmiId
        || domainXmi.leaksRuntimeBlockId) {
      throw new Error(`Domain instance XMI export is not EMF-style: ${JSON.stringify(domainXmi)}`);
    }
    result.domainXmi = domainXmi;
    const previewButton = page.getByRole('button', { name: 'Preview', exact: true });
    if (await previewButton.count()) {
      await previewButton.click();
      await page.waitForFunction(() => {
        const preview = document.getElementById('previewView');
        return Boolean(preview?.querySelector('.app-preview-page input'))
          && Boolean(preview?.querySelector('.app-preview-button'))
          && Boolean(preview?.querySelector('.app-preview-list'))
          && Boolean(preview?.querySelector('select'))
          && Boolean(preview?.querySelector('table'))
          && Boolean(preview?.querySelector('dialog'));
      }, null, { timeout: 10000 });
      result.preview = await page.evaluate(() => {
        const preview = document.getElementById('previewView');
        return {
          inputs: preview?.querySelectorAll('input, textarea').length || 0,
          selects: preview?.querySelectorAll('select').length || 0,
          buttons: preview?.querySelectorAll('button').length || 0,
          lists: preview?.querySelectorAll('.app-preview-list').length || 0,
          tables: preview?.querySelectorAll('table').length || 0,
          dialogs: preview?.querySelectorAll('dialog').length || 0,
        };
      });
    }
    await page.getByRole('button', { name: 'Developer', exact: true }).click();
    await page.waitForFunction(() => document.body.classList.contains('developer-mode'), null, { timeout: 5000 });
    const validationBlocksButton = page.getByRole('button', { name: 'Validation Blocks', exact: true });
    if (await validationBlocksButton.count()) {
      await validationBlocksButton.click();
      const validationFrame = page.frameLocator('iframe[title="Visual validation Blockly workspace"]');
      await validationFrame.locator('.blocklySvg').waitFor({ state: 'visible', timeout: 10000 });
      const ruleCountText = (await validationFrame.locator('#ruleCount').textContent({ timeout: 10000 })) || '';
      const draggableCount = await validationFrame.locator('.blocklyDraggable').count();
      if (!/\d+\s+rules/.test(ruleCountText) || draggableCount === 0) {
        throw new Error(`Validation Blocks tab did not render Blockly rules. ruleCount="${ruleCountText}", blocks=${draggableCount}`);
      }
      await page.waitForFunction(() => {
        const iframe = document.querySelector('#validationBlocksView iframe');
        return Boolean(iframe?.contentWindow?.workspaceToValidationBlockModel)
          && typeof window.syncValidationBlocksFromFrame === 'function';
      }, null, { timeout: 10000 });
      const sync = await page.evaluate(() => {
        const iframe = document.querySelector('#validationBlocksView iframe');
        const frameModel = iframe?.contentWindow?.workspaceToValidationBlockModel?.();
        const ok = window.syncValidationBlocksFromFrame();
        const activeModel = window.__activeValidationBlockModel || window.BLOCKLY_VALIDATION_BLOCK_MODEL;
        const runtimeRules = window.Model2BlocklyValidationRuntime
          ? window.Model2BlocklyValidationRuntime.generateValidationRuntimeRules(activeModel)
          : [];
        return {
          ok,
          frameRules: Array.isArray(frameModel?.rules) ? frameModel.rules.length : 0,
          activeRules: Array.isArray(activeModel?.rules) ? activeModel.rules.length : 0,
          runtimeRules: Array.isArray(runtimeRules) ? runtimeRules.length : 0,
        };
      });
      if (!sync.ok || sync.frameRules === 0 || sync.activeRules !== sync.frameRules || sync.runtimeRules !== sync.frameRules) {
        throw new Error(`Validation Blocks did not sync to runtime: ${JSON.stringify(sync)}`);
      }
      const sourceSnippets = await page.evaluate(() => {
        const iframe = document.querySelector('#validationBlocksView iframe');
        return iframe?.contentWindow?.validationSourceSnippets?.() || '';
      });
      if (!sourceSnippets.includes('=== Ecore ===')
        || !sourceSnippets.includes('=== Model2Blockly Source ===')
        || !sourceSnippets.includes('must follow Alert')
        || !sourceSnippets.includes('<eAnnotations source="validation">')) {
        throw new Error('Validation source snippets were not generated as expected.');
      }
      await page.evaluate(() => {
        const iframe = document.querySelector('#validationBlocksView iframe');
        const source = iframe.contentWindow.workspaceToValidationBlockModel();
        const edited = {
          format: source.format,
          domain: source.domain,
          customBlockTypes: source.customBlockTypes || [],
          blockDefinitions: source.blockDefinitions || [],
          rules: [{
            name: 'smoke_force_app_failure',
            validationType: 'EXPRESSION',
            targetType: 'App',
            message: 'Forced sync test',
            blockTree: {
              type: 'validation_rule',
              fields: {
                NAME: 'smoke_force_app_failure',
                TARGET: 'App',
                VALIDATION_TYPE: 'EXPRESSION',
                MESSAGE: 'Forced sync test',
              },
              inputs: {
                CONDITION: {
                  type: 'logic_boolean',
                  fields: { BOOL: 'FALSE' },
                },
              },
            },
          }],
        };
        iframe.contentWindow.loadValidationBlockModel(edited);
      });
      await page.waitForFunction(() => {
        const issues = document.getElementById('issuesView')?.textContent || '';
        const activeRules = window.__activeValidationBlockModel?.rules || [];
        return issues.includes('Forced sync test') && activeRules.length === 1;
      }, null, { timeout: 10000 });
      result.validationBlocks = {
        ruleCount: ruleCountText.trim(),
        blocks: draggableCount,
        runtimeRules: sync.runtimeRules,
        syncProbe: true,
        sourceSnippets: true,
      };
    }
    result.ok = true;
  } catch (error) {
    result.error = error && error.message ? error.message : String(error);
  } finally {
    await page.close();
  }
  return result;
}

function printResults(results) {
  for (const result of results) {
    if (result.ok) {
      const preview = result.preview
        ? `; preview=${result.preview.inputs} inputs/${result.preview.selects} selects/${result.preview.buttons} buttons/${result.preview.lists} lists/${result.preview.tables} tables/${result.preview.dialogs} dialogs`
        : '';
      const validationBlocks = result.validationBlocks
        ? `; validationBlocks=${result.validationBlocks.ruleCount}/${result.validationBlocks.blocks} blocks/${result.validationBlocks.runtimeRules} runtime rules${result.validationBlocks.syncProbe ? '/sync ok' : ''}${result.validationBlocks.sourceSnippets ? '/source snippets ok' : ''}`
        : '';
      const domainXmi = result.domainXmi
        ? `; domainXmi=${result.domainXmi.length} bytes/${result.domainXmi.firstRoot || '-'}`
        : '';
      console.log(`[PASS] ${result.name}: roots=${result.roots.join(', ') || '-'}; issues=${result.issues || 'none'}${domainXmi}${preview}${validationBlocks}`);
    } else {
      console.error(`[FAIL] ${result.name}: ${result.error}`);
    }
  }
  const passed = results.filter((result) => result.ok).length;
  console.log(`\n${passed}/${results.length} generated editor smoke tests passed.`);
}

function contentType(filePath) {
  if (filePath.endsWith('.html')) return 'text/html; charset=utf-8';
  if (filePath.endsWith('.js')) return 'application/javascript; charset=utf-8';
  if (filePath.endsWith('.json')) return 'application/json; charset=utf-8';
  if (filePath.endsWith('.css')) return 'text/css; charset=utf-8';
  return 'application/octet-stream';
}
