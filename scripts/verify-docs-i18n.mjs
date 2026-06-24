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
  'docs/en/USER_GUIDE.md',
  'docs/en/ARCHITECTURE.md',
  'docs/es/USER_GUIDE.md',
  'docs/es/ARCHITECTURE.md',
  'docs/es/PROJECT.md',
  'docs/es/DOCS.md',
  'docs/es/RUNNING_EXAMPLE.md',
  'docs/es/README.md',
  'docs/es/GETTING_STARTED.md',
  'docs/es/TROUBLESHOOTING.md',
  'docs/es/ECORE_REFERENCE.md',
  'docs/es/RELEASE_CHECKLIST.md',
  'docs/zh/USER_GUIDE.md',
  'docs/zh/ARCHITECTURE.md',
  'docs/zh/PROJECT.md',
  'docs/zh/DOCS.md',
  'docs/zh/RUNNING_EXAMPLE.md',
  'docs/zh/README.md',
  'docs/zh/GETTING_STARTED.md',
  'docs/zh/TROUBLESHOOTING.md',
  'docs/zh/ECORE_REFERENCE.md',
  'docs/zh/RELEASE_CHECKLIST.md',
];
const requiredAssets = [
  'docs/assets/screenshots/homepage-install-en.png',
  'docs/assets/screenshots/homepage-install-es.png',
  'docs/assets/screenshots/homepage-install-zh.png',
  'docs/assets/screenshots/appmaker-editor.png',
  'docs/assets/screenshots/validation-workspace.png',
  'docs/assets/diagrams/system-architecture.svg',
  'docs/assets/diagrams/generation-flow.svg',
  'docs/assets/diagrams/output-artifacts.svg',
  'docs/assets/diagrams/model2blockly-concept.svg',
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
  'en/user-guide.html',
  'en/architecture.html',
  'en/getting-started.html',
  'en/running-example.html',
  'es/index.html',
  'es/user-guide.html',
  'es/architecture.html',
  'es/project.html',
  'es/docs.html',
  'es/running-example.html',
  'es/getting-started.html',
  'zh/index.html',
  'zh/user-guide.html',
  'zh/architecture.html',
  'zh/project.html',
  'zh/docs.html',
  'zh/running-example.html',
  'zh/getting-started.html',
  'assets/screenshots/homepage-install-en.png',
  'assets/screenshots/homepage-install-es.png',
  'assets/screenshots/homepage-install-zh.png',
  'assets/screenshots/appmaker-editor.png',
  'assets/screenshots/validation-workspace.png',
  'assets/diagrams/system-architecture.svg',
  'assets/diagrams/generation-flow.svg',
  'assets/diagrams/output-artifacts.svg',
  'assets/diagrams/model2blockly-concept.svg',
]) {
  if (!existsSync(path.join(docsOutputDir, file))) {
    fail(`Generated site docs missing ${file}`);
  }
}
const generatedZhIndex = readGeneratedDoc('zh/index.html');
const generatedEsIndex = readGeneratedDoc('es/index.html');
const generatedEnIndex = readGeneratedDoc('en/index.html');
const generatedRootIndex = readGeneratedDoc('index.html');
const generatedEnUserGuide = readGeneratedDoc('en/user-guide.html');
const generatedEnArchitecture = readGeneratedDoc('en/architecture.html');
const generatedEnGettingStarted = readGeneratedDoc('en/getting-started.html');
const generatedEnRunningExample = readGeneratedDoc('en/running-example.html');
const generatedEsGettingStarted = readGeneratedDoc('es/getting-started.html');
const generatedEsArchitecture = readGeneratedDoc('es/architecture.html');
const generatedEsRunningExample = readGeneratedDoc('es/running-example.html');
const generatedZhGettingStarted = readGeneratedDoc('zh/getting-started.html');
const generatedZhArchitecture = readGeneratedDoc('zh/architecture.html');
const generatedZhRunningExample = readGeneratedDoc('zh/running-example.html');
assertIncludes(generatedEnIndex, 'VPHero', 'generated English docs home uses the VitePress home hero');
assertIncludes(generatedEnIndex, 'VPFeatures', 'generated English docs home uses feature cards');
assertIncludes(generatedEnIndex, '/model2blockly/docs/assets/diagrams/model2blockly-concept.svg', 'generated English docs home includes the Model2Blockly concept image');
assertIncludes(generatedEnIndex, 'Eclipse plugin for Blockly editors', 'generated English docs home makes the Eclipse plugin positioning clear');
assertIncludes(generatedEnIndex, 'User Guide', 'generated English docs expose a user-guide nav item');
assertIncludes(generatedEnIndex, 'Examples', 'generated English docs expose a examples nav item');
assertIncludes(generatedEnIndex, 'Architecture', 'generated English docs expose an architecture nav item');
assertIncludes(generatedRootIndex, 'Opening the English documentation', 'generated root docs page redirects to the default docs home');
assertNotIncludes(generatedRootIndex, 'Model2Blockly Documentation Languages', 'generated root docs page no longer exposes a redundant language selector page');
assertIncludes(generatedEnGettingStarted, 'VPNav', 'generated English docs use the VitePress nav shell');
assertIncludes(generatedEnGettingStarted, 'VPNavBarSearch', 'generated English docs expose local search');
assertIncludes(generatedEnGettingStarted, 'VPSidebar', 'generated English docs expose a sidebar');
assertNotIncludes(generatedEnGettingStarted, 'i18nRouting\\":false', 'generated docs use corresponding-page language switching');
assertIncludes(generatedEnGettingStarted, '/model2blockly/docs/es/getting-started.html', 'English guide switches to the Spanish guide');
assertIncludes(generatedEnGettingStarted, '/model2blockly/docs/zh/getting-started.html', 'English guide switches to the Chinese guide');
assertIncludes(generatedEnUserGuide, '/model2blockly/docs/assets/screenshots/appmaker-editor.png', 'English user guide includes the AppMaker screenshot');
assertIncludes(generatedEnUserGuide, '/model2blockly/docs/en/architecture.html', 'English user guide links to the architecture layer');
assertIncludes(generatedEnArchitecture, '/model2blockly/docs/assets/diagrams/system-architecture.svg', 'English architecture page includes the system architecture diagram');
assertIncludes(generatedEnArchitecture, '/model2blockly/docs/assets/diagrams/generation-flow.svg', 'English architecture page includes the generation flow diagram');
assertIncludes(generatedEnArchitecture, '/model2blockly/docs/assets/diagrams/output-artifacts.svg', 'English architecture page includes the output artifacts diagram');
assertIncludes(generatedEnArchitecture, '/model2blockly/docs/es/architecture.html', 'English architecture switches to the Spanish architecture page');
assertIncludes(generatedEnArchitecture, '/model2blockly/docs/zh/architecture.html', 'English architecture switches to the Chinese architecture page');
assertIncludes(generatedZhIndex, '从 Ecore 元模型生成', 'generated Chinese docs home highlights Ecore generation');
assertIncludes(generatedZhIndex, '使用 EMF 中间模型', 'generated Chinese docs home highlights the EMF intermediate model');
assertIncludes(generatedZhIndex, '可视化验证规则', 'generated Chinese docs home highlights validation workspace support');
assertIncludes(generatedZhIndex, '导出 JSON、XMI 和代码', 'generated Chinese docs home highlights export capabilities');
assertNotIncludes(generatedZhIndex, '选择入口', 'generated Chinese docs home avoids directory-style entry sections');
assertNotIncludes(generatedZhIndex, '按任务选择', 'generated Chinese docs home avoids directory-style task sections');
assertNotIncludes(generatedZhIndex, '推荐顺序', 'generated Chinese docs home avoids prescribed reading order');
assertNotIncludes(generatedZhIndex, '深入参考', 'generated Chinese docs home avoids deep-reference link lists');
assertNotIncludes(generatedZhIndex, '线上文档', 'generated Chinese docs home avoids redundant hosted-docs metadata');
assertNotIncludes(generatedZhIndex, '<strong>中文</strong>', 'generated Chinese docs home avoids redundant manual language line');
assertNotIncludes(generatedZhIndex, '权威文档', 'generated Chinese docs avoid awkward authority wording');
assertNotIncludes(generatedZhIndex, '\u0000', 'generated Chinese docs do not leak inline-token placeholders');
assertNotIncludes(generatedEsIndex, '\u0000', 'generated Spanish docs do not leak inline-token placeholders');
assertIncludes(generatedEnGettingStarted, '<img ', 'generated English getting-started docs render screenshots');
assertIncludes(generatedEnGettingStarted, '/model2blockly/docs/assets/screenshots/homepage-install-en.png', 'generated English install screenshot uses local docs asset');
assertIncludes(generatedEsGettingStarted, '<img ', 'generated Spanish getting-started docs render screenshots');
assertIncludes(generatedEsGettingStarted, '/model2blockly/docs/assets/screenshots/homepage-install-es.png', 'generated Spanish install screenshot uses local docs asset');
assertIncludes(generatedZhGettingStarted, '<img ', 'generated Chinese getting-started docs render screenshots');
assertIncludes(generatedZhGettingStarted, '/model2blockly/docs/assets/screenshots/homepage-install-zh.png', 'generated Chinese install screenshot uses local docs asset');
assertIncludes(generatedEnRunningExample, 'Technical Traceability', 'generated AppMaker example keeps the technical traceability section');
assertIncludes(generatedEnRunningExample, 'validation_workspace.html', 'generated AppMaker example documents the validation workspace');
assertIncludes(generatedEnRunningExample, '/model2blockly/docs/es/running-example.html', 'English AppMaker case switches to the Spanish AppMaker page');
assertIncludes(generatedEnRunningExample, '/model2blockly/docs/zh/running-example.html', 'English AppMaker case switches to the Chinese AppMaker page');
assertIncludes(generatedEsArchitecture, 'Arquitectura e implementación', 'generated Spanish architecture page exists');
assertIncludes(generatedZhArchitecture, '架构与实现', 'generated Chinese architecture page exists');
assertIncludes(generatedEsRunningExample, 'Caso AppMaker', 'generated Spanish AppMaker page exists');
assertIncludes(generatedZhRunningExample, 'AppMaker 案例', 'generated Chinese AppMaker page exists');

for (const file of listHtmlFiles(docsOutputDir)) {
  checkNoParagraphDirectlyInsideList(file);
}

for (const file of [
  'README.md',
  'DOCS.md',
  'GETTING_STARTED.md',
  'RUNNING_EXAMPLE.md',
  'TROUBLESHOOTING.md',
  'ECORE_REFERENCE.md',
  'RELEASE_CHECKLIST.md',
]) {
  const text = read(file);
  assertNoManualLanguageSwitcher(text, file);
}

for (const language of ['es', 'zh']) {
  for (const file of requiredFiles.filter((name) => name.startsWith(`docs/${language}/`))) {
    assertNoManualLanguageSwitcher(read(file), file);
  }
}

for (const file of requiredFiles.filter((name) => name.startsWith('docs/en/'))) {
  assertNoManualLanguageSwitcher(read(file), file);
}

const siteIndex = read('site/index.html');
assertIncludes(siteIndex, 'class="site-nav"', 'site/index.html uses a fixed top navigation shell');
assertIncludes(siteIndex, 'data-i18n="navHome"', 'site/index.html includes a home nav item');
assertIncludes(siteIndex, 'data-i18n="navExamples"', 'site/index.html includes a examples nav item');
assertIncludes(siteIndex, 'data-i18n="navDocs"', 'site/index.html includes a docs nav item');
assertIncludes(siteIndex, 'docs/en/', 'site/index.html links to hosted English docs');
assertIncludes(siteIndex, 'docs/es/', 'site/index.html links to hosted Spanish docs');
assertIncludes(siteIndex, 'docs/zh/', 'site/index.html links to hosted Chinese docs');
assertIncludes(siteIndex, 'docs/en/user-guide.html', 'site/index.html links to hosted English user guide');
assertIncludes(siteIndex, 'docs/en/architecture.html', 'site/index.html links to hosted English architecture docs');
assertIncludes(siteIndex, 'docs/en/getting-started.html', 'site/index.html links to hosted English getting started docs');
assertIncludes(siteIndex, 'docs/en/running-example.html', 'site/index.html links to hosted AppMaker example');
assertIncludes(siteIndex, 'docs/es/user-guide.html', 'site/index.html links to hosted Spanish user guide');
assertIncludes(siteIndex, 'docs/es/architecture.html', 'site/index.html links to hosted Spanish architecture docs');
assertIncludes(siteIndex, 'docs/zh/user-guide.html', 'site/index.html links to hosted Chinese user guide');
assertIncludes(siteIndex, 'docs/zh/architecture.html', 'site/index.html links to hosted Chinese architecture docs');
assertIncludes(siteIndex, 'data-language="en"', 'site/index.html exposes an English language switch');
assertIncludes(siteIndex, 'data-language="es"', 'site/index.html exposes a Spanish language switch');
assertIncludes(siteIndex, 'data-language="zh"', 'site/index.html exposes a Chinese language switch');
assertIncludes(siteIndex, 'id="install"', 'site/index.html exposes a prominent install section');
assertIncludes(siteIndex, 'copyUpdateSiteUrl', 'site/index.html supports copying the update-site URL');
assertIncludes(siteIndex, 'intermediate/*_blocklyspec.xmi', 'site/index.html explains the standard intermediate XMI pipeline');
assertIncludes(siteIndex, 'Partir de Ecore anotado', 'site/index.html contains Spanish Ecore workflow copy');
assertIncludes(siteIndex, 'Tuberia de generacion estandar', 'site/index.html contains Spanish pipeline copy');
assertIncludes(siteIndex, '从带注解的 Ecore 开始', 'site/index.html contains Chinese Ecore workflow copy');
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
  assertNoManualLanguageSwitcher(text, file);
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

function assertNoManualLanguageSwitcher(text, file) {
  if (/^(Languages?|Idioma|语言)[：:]/m.test(text)) {
    fail(`${file} has a manual language switcher line; use the VitePress menu instead`);
  }
}

function markdownFilesToCheck() {
  return [
    'README.md',
    'DOCS.md',
    'GETTING_STARTED.md',
    'RUNNING_EXAMPLE.md',
    'TROUBLESHOOTING.md',
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
