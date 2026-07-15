#!/usr/bin/env node

import assert from 'node:assert/strict';
import { createHash } from 'node:crypto';
import { createReadStream, readFileSync } from 'node:fs';
import { stat } from 'node:fs/promises';
import { createServer } from 'node:http';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import Ajv2020 from 'ajv/dist/2020.js';
import { chromium } from 'playwright';

const evaluationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repoRoot = path.resolve(evaluationRoot, '../..');
const schemaDir = path.join(evaluationRoot, 'schema');
const fixtureDir = path.join(evaluationRoot, 'fixtures', 'self-test');

const ajv = new Ajv2020({ allErrors: true, strict: true, allowUnionTypes: true });
const descriptorSchema = readJson(path.join(schemaDir, 'editor-descriptor.schema.json'));
const extractionSchema = readJson(path.join(schemaDir, 'baseline-extraction.schema.json'));
const validateDescriptor = ajv.compile(descriptorSchema);
const validateExtraction = ajv.compile(extractionSchema);

const extraction = readJson(path.join(fixtureDir, 'baseline-extraction.json'));
assertSchema(validateExtraction, extraction, 'baseline extraction fixture');
verifyFragmentIntegrity(extraction);

const server = await startServer(repoRoot);
const baseUrl = `http://127.0.0.1:${server.address().port}`;
const browser = await chromium.launch({ headless: true });

try {
  const baseline = await loadTreatment(browser, baseUrl, 'baseline.mjs');
  const generated = await loadTreatment(browser, baseUrl, 'generated.mjs');

  assertSchema(validateDescriptor, baseline, 'baseline descriptor');
  assertSchema(validateDescriptor, generated, 'generated descriptor');
  assert.equal(baseline.controls.blocklyVersion, '13.1.1');
  assert.equal(baseline.controls.renderer, 'geras');
  assert.equal(baseline.controls.theme, 'classic');
  assert.deepEqual(baseline.errors, []);
  assert.deepEqual(generated.errors, []);
  assert.deepEqual(withoutTreatment(generated), withoutTreatment(baseline));

  console.log('PASS evaluation harness');
  console.log(`  Blockly: ${baseline.controls.blocklyVersion}`);
  console.log(`  controls: ${baseline.controls.renderer}, ${baseline.controls.theme}, ${baseline.controls.locale}`);
  console.log(`  canonical blocks: ${baseline.blocks.length}`);
  console.log('  baseline and generated descriptors are identical');
  console.log('  extraction metadata and fragment hash are valid');
} finally {
  await browser.close();
  await new Promise((resolve) => server.close(resolve));
}

async function loadTreatment(browser, baseUrl, fixture) {
  const page = await browser.newPage({ viewport: { width: 1280, height: 800 } });
  const browserErrors = [];
  page.on('pageerror', (error) => browserErrors.push(error.message));
  try {
    const adapter = `/evaluation/official-blockly/fixtures/self-test/${fixture}`;
    const url = `${baseUrl}/evaluation/official-blockly/harness/index.html?adapter=${encodeURIComponent(adapter)}`;
    await page.goto(url, { waitUntil: 'load', timeout: 30000 });
    await page.waitForFunction(
      () => ['ready', 'error'].includes(window.__M2B_EVALUATION__?.status),
      null,
      { timeout: 30000 },
    );
    const result = await page.evaluate(() => window.__M2B_EVALUATION__);
    assert.equal(result.status, 'ready', result.error || 'Harness did not become ready.');
    assert.deepEqual(browserErrors, []);
    return result.descriptor;
  } finally {
    await page.close();
  }
}

function verifyFragmentIntegrity(metadata) {
  for (const fragment of metadata.fragments) {
    const localPath = fragment.snapshotPath || fragment.path;
    const absolute = path.resolve(repoRoot, localPath);
    assert.ok(absolute.startsWith(repoRoot + path.sep), `Fragment escapes repository: ${localPath}`);
    const lines = readFileSync(absolute, 'utf8').split(/\r?\n/);
    assert.ok(fragment.endLine >= fragment.startLine, `Invalid line range: ${fragment.id}`);
    const expectedLineCount = fragment.endLine - fragment.startLine + 1;
    let selected;
    if (fragment.snapshotPath) {
      assert.equal(lines.length - 1, expectedLineCount, `Snapshot line count mismatch: ${fragment.id}`);
      selected = readFileSync(absolute, 'utf8');
    } else {
      assert.ok(fragment.endLine <= lines.length, `Line range exceeds file: ${fragment.id}`);
      selected = `${lines.slice(fragment.startLine - 1, fragment.endLine).join('\n')}\n`;
    }
    const actual = sha256(selected);
    assert.equal(actual, fragment.sha256, `Fragment hash mismatch: ${fragment.id}`);
  }
}

function sha256(value) {
  return createHash('sha256').update(value).digest('hex');
}

function withoutTreatment(descriptor) {
  const clone = structuredClone(descriptor);
  delete clone.treatment;
  return clone;
}

function assertSchema(validate, value, label) {
  if (validate(value)) return;
  assert.fail(`${label} does not match its schema:\n${JSON.stringify(validate.errors, null, 2)}`);
}

function readJson(file) {
  return JSON.parse(readFileSync(file, 'utf8'));
}

async function startServer(root) {
  const server = createServer(async (request, response) => {
    try {
      const url = new URL(request.url || '/', 'http://127.0.0.1');
      const rawPath = decodeURIComponent(url.pathname.replace(/^\/+/, ''));
      const file = path.resolve(root, rawPath || 'index.html');
      if (!file.startsWith(root + path.sep) && file !== root) {
        response.writeHead(403).end('Forbidden');
        return;
      }
      const info = await stat(file);
      if (!info.isFile()) throw new Error('Not a file');
      response.writeHead(200, { 'Content-Type': contentType(file) });
      createReadStream(file).pipe(response);
    } catch {
      response.writeHead(404).end('Not found');
    }
  });
  await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
  return server;
}

function contentType(file) {
  const extension = path.extname(file);
  if (extension === '.html') return 'text/html; charset=utf-8';
  if (extension === '.css') return 'text/css; charset=utf-8';
  if (extension === '.js' || extension === '.mjs') return 'text/javascript; charset=utf-8';
  if (extension === '.json' || extension === '.map') return 'application/json; charset=utf-8';
  if (extension === '.svg') return 'image/svg+xml';
  if (extension === '.png') return 'image/png';
  if (extension === '.mp3') return 'audio/mpeg';
  return 'application/octet-stream';
}
