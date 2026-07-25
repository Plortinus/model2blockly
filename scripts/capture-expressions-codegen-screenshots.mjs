#!/usr/bin/env node

// Captures the thesis screenshots for examples/feature_pairs/03_expressions_codegen.
// Usage: node scripts/capture-expressions-codegen-screenshots.mjs

import { createServer } from 'node:http';
import { readFileSync, mkdirSync, existsSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { chromium } from 'playwright';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const htmlRoot = path.join(
  repoRoot, 'examples', 'feature_pairs', '03_expressions_codegen', 'generated', 'ecore', 'html');
const outDir = path.join(
  repoRoot, 'examples', 'feature_pairs', '03_expressions_codegen', 'screenshots');
mkdirSync(outDir, { recursive: true });

const mime = {
  '.html': 'text/html', '.js': 'text/javascript', '.json': 'application/json',
  '.css': 'text/css', '.png': 'image/png', '.svg': 'image/svg+xml',
};

const server = createServer((request, response) => {
  const url = new URL(request.url || '/', 'http://127.0.0.1');
  const file = path.join(htmlRoot, decodeURIComponent(url.pathname).replace(/^\/+/, ''));
  if (!existsSync(file)) {
    response.writeHead(404).end('not found');
    return;
  }
  response.writeHead(200, { 'Content-Type': mime[path.extname(file)] || 'application/octet-stream' });
  response.end(readFileSync(file));
});
await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
const baseUrl = `http://127.0.0.1:${server.address().port}/ScriptedApp_standalone.html`;

const browser = await chromium.launch();
const page = await browser.newPage({
  viewport: { width: 1760, height: 990 },
  deviceScaleFactor: 2,
});
page.on('pageerror', (error) => console.error('[pageerror]', error.message));

async function openEditor() {
  await page.goto(baseUrl, { waitUntil: 'load', timeout: 30000 });
  await page.waitForFunction(() => window.workspace && window.Blockly, null, { timeout: 15000 });
  await page.waitForTimeout(400);
}

function loadXml(xmlText) {
  return page.evaluate((xml) => {
    const ws = window.workspace;
    ws.clear();
    const parse = (window.Blockly.utils && window.Blockly.utils.xml && window.Blockly.utils.xml.textToDom)
      ? window.Blockly.utils.xml.textToDom
      : window.Blockly.Xml.textToDom;
    window.Blockly.Xml.domToWorkspace(parse(xml), ws);
  }, xmlText);
}

async function shootBlock(type, file, padding = 14) {
  const rect = await page.evaluate((blockType) => {
    const block = window.workspace.getAllBlocks(false).find((b) => b.type === blockType);
    const r = block.getSvgRoot().getBoundingClientRect();
    return { x: r.x, y: r.y, width: r.width, height: r.height };
  }, type);
  await page.screenshot({
    path: path.join(outDir, file),
    clip: {
      x: rect.x - padding,
      y: rect.y - padding,
      width: rect.width + 2 * padding,
      height: rect.height + 2 * padding,
    },
  });
  console.log(`saved ${file}`);
}

// ── 1. Button block whose value input still shows the shadow block ──
await openEditor();
await loadXml(`
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="Button" x="24" y="24">
    <value name="enabledWhen"><shadow type="BoolLiteral"/></value>
  </block>
</xml>`);
await page.waitForTimeout(300);
await shootBlock('Button', 'button_shadow.png');

// ── 2. Same input with a nested expression tree plugged in ──
await loadXml(`
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="Button" x="24" y="24">
    <value name="enabledWhen">
      <shadow type="BoolLiteral"/>
      <block type="NotExpression">
        <value name="operand">
          <shadow type="BoolLiteral"/>
          <block type="PlatformIs"/>
        </value>
      </block>
    </value>
  </block>
</xml>`);
await page.waitForTimeout(300);
await shootBlock('Button', 'button_expression.png');

// ── 2b. Two nested Not blocks: recursion from a single declaration ──
await loadXml(`
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="NotExpression" x="24" y="24">
    <value name="operand">
      <shadow type="BoolLiteral"/>
      <block type="NotExpression">
        <value name="operand">
          <shadow type="BoolLiteral"/>
          <block type="PlatformIs"/>
        </value>
      </block>
    </value>
  </block>
</xml>`);
await page.waitForTimeout(300);
{
  const rect = await page.evaluate(() => {
    const block = window.workspace.getTopBlocks(false)[0];
    const r = block.getSvgRoot().getBoundingClientRect();
    return { x: r.x, y: r.y, width: r.width, height: r.height };
  });
  const padding = 14;
  await page.screenshot({
    path: path.join(outDir, 'expression_recursion.png'),
    clip: {
      x: rect.x - padding,
      y: rect.y - padding,
      width: rect.width + 2 * padding,
      height: rect.height + 2 * padding,
    },
  });
  console.log('saved expression_recursion.png');
}

// ── 3. Expressions toolbox category with the three output blocks ──
await page.evaluate(() => {
  const ws = window.workspace;
  ws.clear();
  const toolbox = ws.getToolbox();
  const item = toolbox.getToolboxItems()
    .find((i) => i.getName && i.getName() === 'Expressions');
  toolbox.setSelectedItem(item);
});
await page.waitForTimeout(500);
const flyoutRect = await page.evaluate(() => {
  const toolboxEl = document.querySelector('.blocklyToolboxDiv')
    || document.querySelector('.blocklyToolbox')
    || document.querySelector('[class*="blocklyToolbox"]');
  const toolbox = toolboxEl.getBoundingClientRect();
  let flyout = null;
  for (const svg of document.querySelectorAll('.blocklyFlyout')) {
    const r = svg.getBoundingClientRect();
    if (r.width > 0 && (!flyout || r.width > flyout.width)) flyout = r;
  }
  let contentBottom = 0;
  for (const el of document.querySelectorAll('.blocklyFlyout .blocklyDraggable, [class*="blocklyToolbox"] .blocklyToolboxCategory')) {
    const r = el.getBoundingClientRect();
    if (r.height > 0) contentBottom = Math.max(contentBottom, r.bottom);
  }
  const x = toolbox.x;
  const y = Math.min(toolbox.y, flyout.y);
  return {
    x,
    y,
    width: flyout.x + flyout.width - x,
    height: contentBottom + 16 - y,
  };
});
await page.screenshot({
  path: path.join(outDir, 'expressions_toolbox.png'),
  clip: flyoutRect,
});
console.log('saved expressions_toolbox.png');

// ── 4. Full editor: sample model + plugged expression + code panel ──
await openEditor();
await page.getByText('Load Sample', { exact: true }).click();
await page.waitForTimeout(600);
await page.evaluate(() => {
  const ws = window.workspace;
  const button = ws.getAllBlocks(false).find((b) => b.type === 'Button');
  const not = ws.newBlock('NotExpression');
  not.initSvg();
  not.render();
  const platform = ws.newBlock('PlatformIs');
  platform.initSvg();
  platform.render();
  not.getInput('operand').connection.connect(platform.outputConnection);
  button.getInput('enabledWhen').connection.connect(not.outputConnection);
  // Remove the sample expression that was bumped out of the value input.
  ws.getTopBlocks(false)
    .filter((b) => b.outputConnection)
    .forEach((b) => b.dispose());
});
await page.waitForTimeout(400);
const codeTab = page.locator('[data-tab="code"]');
if (!(await codeTab.isVisible())) {
  await page.locator('#developerModeButton').click();
  await page.waitForTimeout(300);
}
await codeTab.click();
await page.waitForFunction(() => {
  if (typeof updateOutput === 'function') updateOutput();
  const text = document.getElementById('codeView').textContent;
  return text.includes("app('") && text.includes('platform ===');
}, null, { timeout: 10000, polling: 500 });
await page.waitForTimeout(300);
await page.screenshot({ path: path.join(outDir, 'editor_overview.png') });
console.log('saved editor_overview.png');

await browser.close();
server.close();
console.log(`Screenshots written to ${outDir}`);
