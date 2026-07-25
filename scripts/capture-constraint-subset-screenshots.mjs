#!/usr/bin/env node

// Captures thesis screenshots for examples/feature_pairs/04_constraint_subset.
// Usage: node scripts/capture-constraint-subset-screenshots.mjs

import { createServer } from 'node:http';
import { readFileSync, mkdirSync, existsSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { chromium } from 'playwright';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const htmlRoot = path.join(
  repoRoot, 'examples', 'feature_pairs', '04_constraint_subset', 'generated', 'ecore', 'html');
const outDir = path.join(
  repoRoot, 'examples', 'feature_pairs', '04_constraint_subset', 'screenshots');
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
const baseUrl = `http://127.0.0.1:${server.address().port}/ValidatedApp_standalone.html`;

const browser = await chromium.launch();
const page = await browser.newPage({
  viewport: { width: 1760, height: 990 },
  deviceScaleFactor: 2,
});

async function openEditor() {
  await page.goto(baseUrl, { waitUntil: 'load', timeout: 30000 });
  await page.waitForFunction(() => window.workspace && window.Blockly, null, { timeout: 15000 });
  await page.waitForTimeout(400);
}

async function ensureIssuesTab() {
  const issuesTab = page.locator('[data-tab="issues"]');
  if (!(await issuesTab.isVisible())) {
    await page.locator('#developerModeButton').click();
    await page.waitForTimeout(200);
  }
  await issuesTab.click();
  await page.waitForTimeout(300);
}

// ── 1. Violations: published without tags + enabled button too narrow ──
await openEditor();
await page.getByText('Load Sample', { exact: true }).click();
await page.waitForTimeout(600);
await page.evaluate(() => {
  const ws = window.workspace;
  const app = ws.getAllBlocks(false).find((b) => b.type === 'App');
  const button = ws.getAllBlocks(false).find((b) => b.type === 'Button');
  app.setFieldValue('TRUE', 'published');
  const tagsField = app.getField('tags');
  if (tagsField && typeof tagsField.setValue === 'function') tagsField.setValue('[]');
  else app.setFieldValue('[]', 'tags');
  button.setFieldValue(80, 'width');
  if (typeof updateOutput === 'function') updateOutput();
  if (typeof applyValidationWarnings === 'function') applyValidationWarnings(ws);
});
await page.waitForTimeout(500);
await ensureIssuesTab();
await page.waitForFunction(() => {
  const text = document.getElementById('issuesView').textContent || '';
  return text.includes('tag') && text.includes('100');
}, null, { timeout: 10000 });
await page.screenshot({ path: path.join(outDir, 'issues_violations.png') });
console.log('saved issues_violations.png');

// ── 2. Fixed: restore sample-valid state ──
await page.getByText('Load Sample', { exact: true }).click();
await page.waitForTimeout(600);
await page.evaluate(() => {
  if (typeof updateOutput === 'function') updateOutput();
  if (typeof applyValidationWarnings === 'function') applyValidationWarnings(window.workspace);
});
await page.waitForTimeout(400);
await ensureIssuesTab();
await page.waitForFunction(() => {
  const text = (document.getElementById('issuesView').textContent || '').trim();
  return text === 'No validation issues' || text.includes('No validation');
}, null, { timeout: 10000 });
await page.screenshot({ path: path.join(outDir, 'issues_cleared.png') });
console.log('saved issues_cleared.png');

await browser.close();
server.close();
console.log(`Screenshots written to ${outDir}`);
