#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { existsSync, readFileSync, readdirSync, rmSync, statSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const failures = [];

const requiredFiles = [
  'docs/README.md',
  'docs/en/README.md',
  'docs/es/README.md',
  'docs/es/GETTING_STARTED.md',
  'docs/es/TROUBLESHOOTING.md',
  'docs/es/DSL_REFERENCE.md',
  'docs/es/ECORE_REFERENCE.md',
  'docs/es/RELEASE_CHECKLIST.md',
  'docs/zh/README.md',
  'docs/zh/GETTING_STARTED.md',
  'docs/zh/TROUBLESHOOTING.md',
  'docs/zh/DSL_REFERENCE.md',
  'docs/zh/ECORE_REFERENCE.md',
  'docs/zh/RELEASE_CHECKLIST.md',
];
const requiredAssets = [
  'docs/assets/screenshots/homepage-install-en.png',
  'docs/assets/screenshots/homepage-install-es.png',
  'docs/assets/screenshots/homepage-install-zh.png',
  'docs/assets/screenshots/appmaker-editor.png',
  'docs/assets/screenshots/validation-workspace.png',
];

for (const file of requiredFiles) assertExists(file);
for (const file of requiredAssets) assertExists(file);

const docsOutputDir = path.resolve(repoRoot, '.cache/verify-site-docs');
rmSync(docsOutputDir, { recursive: true, force: true });
execFileSync(process.execPath, ['scripts/build-site-docs.mjs', '--out', docsOutputDir], {
  cwd: repoRoot,
  stdio: 'pipe',
});
for (const file of [
  'index.html',
  'en/index.html',
  'en/getting-started.html',
  'en/dsl-reference.html',
  'es/index.html',
  'es/getting-started.html',
  'zh/index.html',
  'zh/getting-started.html',
  'assets/screenshots/homepage-install-en.png',
  'assets/screenshots/homepage-install-es.png',
  'assets/screenshots/homepage-install-zh.png',
  'assets/screenshots/appmaker-editor.png',
  'assets/screenshots/validation-workspace.png',
]) {
  if (!existsSync(path.join(docsOutputDir, file))) {
    fail(`Generated site docs missing ${file}`);
  }
}
const generatedZhIndex = readGeneratedDoc('zh/index.html');
const generatedEsIndex = readGeneratedDoc('es/index.html');
const generatedEnGettingStarted = readGeneratedDoc('en/getting-started.html');
const generatedEsGettingStarted = readGeneratedDoc('es/getting-started.html');
const generatedZhGettingStarted = readGeneratedDoc('zh/getting-started.html');
assertIncludes(generatedZhIndex, '深入参考', 'generated Chinese docs use the refined reference heading');
assertIncludes(generatedZhIndex, '<strong>中文</strong>', 'generated Chinese docs render bold language marker');
assertNotIncludes(generatedZhIndex, '权威文档', 'generated Chinese docs avoid awkward authority wording');
assertNotIncludes(generatedZhIndex, '\u0000', 'generated Chinese docs do not leak inline-token placeholders');
assertNotIncludes(generatedEsIndex, '\u0000', 'generated Spanish docs do not leak inline-token placeholders');
assertIncludes(generatedEnGettingStarted, '<img ', 'generated English getting-started docs render screenshots');
assertIncludes(generatedEnGettingStarted, '../assets/screenshots/homepage-install-en.png', 'generated English install screenshot uses local docs asset');
assertIncludes(generatedEsGettingStarted, '<img ', 'generated Spanish getting-started docs render screenshots');
assertIncludes(generatedEsGettingStarted, '../assets/screenshots/homepage-install-es.png', 'generated Spanish install screenshot uses local docs asset');
assertIncludes(generatedZhGettingStarted, '<img ', 'generated Chinese getting-started docs render screenshots');
assertIncludes(generatedZhGettingStarted, '../assets/screenshots/homepage-install-zh.png', 'generated Chinese install screenshot uses local docs asset');

for (const file of listHtmlFiles(docsOutputDir)) {
  checkNoParagraphDirectlyInsideList(file);
}

for (const file of [
  'README.md',
  'DOCS.md',
  'GETTING_STARTED.md',
  'RUNNING_EXAMPLE.md',
  'TROUBLESHOOTING.md',
  'DSL_REFERENCE.md',
  'ECORE_REFERENCE.md',
  'RELEASE_CHECKLIST.md',
]) {
  const text = read(file);
  assertIncludes(text, 'Español', `${file} links to Spanish docs`);
  assertIncludes(text, '中文', `${file} links to Chinese docs`);
}

for (const language of ['es', 'zh']) {
  for (const file of requiredFiles.filter((name) => name.startsWith(`docs/${language}/`))) {
    const text = read(file);
    assertIncludes(text, 'English', `${file} links back to English docs`);
    assertIncludes(text, language === 'es' ? '中文' : 'Español', `${file} links to the third language`);
  }
}

const siteIndex = read('site/index.html');
assertIncludes(siteIndex, 'docs/en/', 'site/index.html links to hosted English docs');
assertIncludes(siteIndex, 'docs/es/', 'site/index.html links to hosted Spanish docs');
assertIncludes(siteIndex, 'docs/zh/', 'site/index.html links to hosted Chinese docs');
assertIncludes(siteIndex, 'docs/en/getting-started.html', 'site/index.html links to hosted English getting started docs');
assertIncludes(siteIndex, 'data-language="en"', 'site/index.html exposes an English language switch');
assertIncludes(siteIndex, 'data-language="es"', 'site/index.html exposes a Spanish language switch');
assertIncludes(siteIndex, 'data-language="zh"', 'site/index.html exposes a Chinese language switch');
assertIncludes(siteIndex, 'id="install"', 'site/index.html exposes a prominent install section');
assertIncludes(siteIndex, 'copyUpdateSiteUrl', 'site/index.html supports copying the update-site URL');
assertIncludes(siteIndex, 'intermediate/*_blocklyspec.xmi', 'site/index.html explains the standard intermediate XMI pipeline');
assertIncludes(siteIndex, 'Use .model2blockly si quiere', 'site/index.html contains Spanish route-choice copy');
assertIncludes(siteIndex, 'Tuberia de generacion estandar', 'site/index.html contains Spanish pipeline copy');
assertIncludes(siteIndex, '想要简洁文本模型时', 'site/index.html contains Chinese route-choice copy');
assertIncludes(siteIndex, '标准生成流水线', 'site/index.html contains Chinese pipeline copy');

const updateSite = read('site/update-site/index.html');
assertIncludes(updateSite, '../docs/en/getting-started.html', 'update-site page links to hosted English install guide');
assertIncludes(updateSite, '../docs/es/getting-started.html', 'update-site page links to hosted Spanish install guide');
assertIncludes(updateSite, '../docs/zh/getting-started.html', 'update-site page links to hosted Chinese install guide');

for (const file of markdownFilesToCheck()) {
  checkMarkdownLinks(file);
}

if (failures.length) {
  for (const failure of failures) console.error(`[FAIL] ${failure}`);
  console.error(`\n${failures.length} documentation i18n check(s) failed.`);
  process.exit(1);
}

console.log('[PASS] Documentation language files, switches and relative links passed.');

function checkMarkdownLinks(file) {
  const text = read(file);
  const dir = path.dirname(file);
  const links = [...text.matchAll(/\[[^\]]+\]\(([^)]+)\)/g)].map((match) => match[1]);
  for (const rawTarget of links) {
    const target = rawTarget.split('#')[0].trim();
    if (!target
        || target.startsWith('http://')
        || target.startsWith('https://')
        || target.startsWith('mailto:')
        || target.startsWith('#')) {
      continue;
    }
    const normalizedTarget = target.replace(/^<|>$/g, '');
    const resolved = path.resolve(repoRoot, dir, normalizedTarget);
    if (!resolved.startsWith(repoRoot + path.sep) && resolved !== repoRoot) {
      fail(`${file} has a link outside the repository: ${rawTarget}`);
    } else if (!existsSync(resolved)) {
      fail(`${file} has a broken relative link: ${rawTarget}`);
    }
  }
}

function markdownFilesToCheck() {
  return [
    'README.md',
    'DOCS.md',
    'GETTING_STARTED.md',
    'RUNNING_EXAMPLE.md',
    'TROUBLESHOOTING.md',
    'DSL_REFERENCE.md',
    'ECORE_REFERENCE.md',
    'RELEASE_CHECKLIST.md',
    ...listMarkdownFiles('docs'),
  ];
}

function listMarkdownFiles(relativeDir) {
  const files = [];
  const absoluteDir = path.resolve(repoRoot, relativeDir);
  for (const entry of readdirSync(absoluteDir)) {
    const absolute = path.join(absoluteDir, entry);
    const relative = path.relative(repoRoot, absolute);
    const stats = statSync(absolute);
    if (stats.isDirectory()) {
      files.push(...listMarkdownFiles(relative));
    } else if (entry.endsWith('.md')) {
      files.push(relative);
    }
  }
  return files.sort();
}

function listHtmlFiles(absoluteDir) {
  const files = [];
  for (const entry of readdirSync(absoluteDir)) {
    const absolute = path.join(absoluteDir, entry);
    const stats = statSync(absolute);
    if (stats.isDirectory()) {
      files.push(...listHtmlFiles(absolute));
    } else if (entry.endsWith('.html')) {
      files.push(absolute);
    }
  }
  return files.sort();
}

function checkNoParagraphDirectlyInsideList(file) {
  let inList = false;
  const lines = readFileSync(file, 'utf8').split('\n');
  for (let index = 0; index < lines.length; index++) {
    const trimmed = lines[index].trim();
    if (trimmed === '<ul>' || trimmed === '<ol>') {
      inList = true;
    } else if (trimmed === '</ul>' || trimmed === '</ol>') {
      inList = false;
    } else if (inList && trimmed.startsWith('<p>')) {
      fail(`Generated docs contain a paragraph directly inside a list: ${path.relative(docsOutputDir, file)}:${index + 1}`);
    }
  }
}

function assertExists(file) {
  if (!existsSync(path.resolve(repoRoot, file))) fail(`Missing required file: ${file}`);
}

function assertIncludes(text, needle, message) {
  if (!text.includes(needle)) fail(message);
}

function assertNotIncludes(text, needle, message) {
  if (text.includes(needle)) fail(message);
}

function read(file) {
  return readFileSync(path.resolve(repoRoot, file), 'utf8');
}

function readGeneratedDoc(file) {
  return readFileSync(path.join(docsOutputDir, file), 'utf8');
}

function fail(message) {
  failures.push(message);
}
