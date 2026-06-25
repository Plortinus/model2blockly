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
  'docs/en/INSTALL.md',
  'docs/en/USER_GUIDE.md',
  'docs/en/ARCHITECTURE.md',
  'docs/en/RUNNING_EXAMPLE.md',
  'docs/en/ECORE_REFERENCE.md',
  'docs/en/TROUBLESHOOTING.md',
  'docs/en/RELEASE_CHECKLIST.md',
  'docs/es/README.md',
  'docs/es/INSTALL.md',
  'docs/es/USER_GUIDE.md',
  'docs/es/ARCHITECTURE.md',
  'docs/es/RUNNING_EXAMPLE.md',
  'docs/es/ECORE_REFERENCE.md',
  'docs/es/TROUBLESHOOTING.md',
  'docs/es/RELEASE_CHECKLIST.md',
  'docs/zh/README.md',
  'docs/zh/INSTALL.md',
  'docs/zh/USER_GUIDE.md',
  'docs/zh/ARCHITECTURE.md',
  'docs/zh/RUNNING_EXAMPLE.md',
  'docs/zh/ECORE_REFERENCE.md',
  'docs/zh/TROUBLESHOOTING.md',
  'docs/zh/RELEASE_CHECKLIST.md',
];

const requiredAssets = [
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
  'en/install.html',
  'en/user-guide.html',
  'en/architecture.html',
  'en/running-example.html',
  'en/ecore-reference.html',
  'en/troubleshooting.html',
  'en/release-checklist.html',
  'es/index.html',
  'es/install.html',
  'es/user-guide.html',
  'es/architecture.html',
  'es/running-example.html',
  'es/ecore-reference.html',
  'es/troubleshooting.html',
  'es/release-checklist.html',
  'zh/index.html',
  'zh/install.html',
  'zh/user-guide.html',
  'zh/architecture.html',
  'zh/running-example.html',
  'zh/ecore-reference.html',
  'zh/troubleshooting.html',
  'zh/release-checklist.html',
  'assets/screenshots/appmaker-editor.png',
  'assets/screenshots/validation-workspace.png',
  'assets/diagrams/system-architecture.svg',
  'assets/diagrams/generation-flow.svg',
  'assets/diagrams/output-artifacts.svg',
  'assets/diagrams/model2blockly-concept.svg',
]) {
  if (!existsSync(path.join(docsOutputDir, file))) {
    fail(`Generated VitePress site missing ${file}`);
  }
}

const generatedRootIndex = readGeneratedDoc('index.html');
const generatedEnIndex = readGeneratedDoc('en/index.html');
const generatedInstall = readGeneratedDoc('en/install.html');
const generatedUserGuide = readGeneratedDoc('en/user-guide.html');
const generatedArchitecture = readGeneratedDoc('en/architecture.html');
const generatedCase = readGeneratedDoc('en/running-example.html');
const generatedEcoreReference = readGeneratedDoc('en/ecore-reference.html');
const generatedTroubleshooting = readGeneratedDoc('en/troubleshooting.html');
const generatedEsIndex = readGeneratedDoc('es/index.html');
const generatedEsInstall = readGeneratedDoc('es/install.html');
const generatedEsArchitecture = readGeneratedDoc('es/architecture.html');
const generatedZhIndex = readGeneratedDoc('zh/index.html');
const generatedZhInstall = readGeneratedDoc('zh/install.html');
const generatedZhArchitecture = readGeneratedDoc('zh/architecture.html');

assertIncludes(generatedRootIndex, 'Opening the English documentation', 'root page redirects to the English locale');
assertIncludes(generatedRootIndex, '/model2blockly/es/', 'root page links to the Spanish locale');
assertIncludes(generatedRootIndex, '/model2blockly/zh/', 'root page links to the Chinese locale');
assertNotIncludes(generatedRootIndex, '/model2blockly/docs/', 'root page no longer publishes under /docs/');

assertIncludes(generatedEnIndex, 'Ecore is the main input', 'English home explains the current input route');
assertIncludes(generatedEnIndex, 'Español', 'English pages expose the Spanish language option');
assertIncludes(generatedEnIndex, '中文', 'English pages expose the Chinese language option');
assertIncludes(generatedInstall, 'Install the Eclipse Plugin', 'install page is generated');
assertIncludes(generatedInstall, 'https://plortinus.github.io/model2blockly/update-site/', 'install page includes the update-site URL');
assertIncludes(generatedInstall, 'Group items by category', 'install page includes the empty-list troubleshooting step');
assertIncludes(generatedUserGuide, 'EcoreToBlocklyMain.java', 'user guide names the current Ecore standalone entry point');
assertIncludes(generatedUserGuide, 'model/app_maker.ecore', 'user guide starts from the checked-in AppMaker Ecore source');
assertIncludes(generatedUserGuide, 'Generate Blockly Editor', 'user guide documents the Eclipse command');
assertIncludes(generatedArchitecture, 'EcoreAdapter', 'architecture page names the real adapter');
assertIncludes(generatedArchitecture, 'src-gen', 'architecture page explains Xtext-generated code location');
assertIncludes(generatedArchitecture, 'emf-gen', 'architecture page explains EMF-generated code location');
assertIncludes(generatedArchitecture, 'GitHub Pages now publishes VitePress as the site root', 'architecture page documents the current Pages shape');
assertIncludes(generatedCase, 'Appmaker_blocklyspec.xmi', 'AppMaker case documents the intermediate XMI');
assertIncludes(generatedCase, 'Smoke Test', 'AppMaker case documents the browser smoke test');
assertIncludes(generatedEcoreReference, 'source=&quot;blockly&quot;', 'Ecore reference documents blockly annotations');
assertIncludes(generatedEcoreReference, 'source=&quot;validation&quot;', 'Ecore reference documents validation annotations');
assertIncludes(generatedTroubleshooting, 'GitHub Actions', 'troubleshooting explains Pages source configuration');
assertIncludes(generatedTroubleshooting, 'update-site', 'troubleshooting keeps plugin installation endpoint visible');
assertIncludes(generatedEsIndex, 'Genera editores Blockly desde Ecore anotado', 'Spanish home is generated from the current Ecore narrative');
assertIncludes(generatedEsInstall, 'Instalar el plugin de Eclipse', 'Spanish install page is generated');
assertIncludes(generatedEsArchitecture, 'Arquitectura', 'Spanish architecture page is generated');
assertIncludes(generatedZhIndex, '从 Ecore 生成可用的 Blockly 编辑器', 'Chinese home is generated from the current Ecore narrative');
assertNotIncludes(generatedZhIndex, '文档导览', 'Chinese home should not show the old documentation guide section');
assertNotIncludes(generatedZhIndex, '生成流程', 'Chinese home should not show the old generation flow section');
assertNotIncludes(generatedZhIndex, '适用场景', 'Chinese home should not show the old use-case section');
assertIncludes(generatedZhInstall, '安装 Eclipse 插件', 'Chinese install page is generated');
assertNotIncludes(generatedZhInstall, '支持 JavaSE-21', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '如果列表为空', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '更新已有安装', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '验证安装', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '相关页面', 'Chinese install page should only show installation steps');
assertIncludes(generatedZhArchitecture, '架构', 'Chinese architecture page is generated');

for (const file of listHtmlFiles(docsOutputDir)) {
  checkNoParagraphDirectlyInsideList(file);
}

for (const file of requiredFiles) {
  checkMarkdownLinks(file);
}

if (failures.length) {
  for (const failure of failures) console.error(`[FAIL] ${failure}`);
  console.error(`\n${failures.length} documentation check(s) failed.`);
  process.exit(1);
}

console.log('[PASS] VitePress documentation build, source files and links passed.');

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
        || target.startsWith('#')
        || target.startsWith('/')) {
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
