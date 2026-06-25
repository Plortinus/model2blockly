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
  'docs/en/TEXTUAL_DSL.md',
  'docs/en/ECORE_TO_BLOCKLY_MAPPING.md',
  'docs/en/RUNNING_EXAMPLE.md',
  'docs/en/TROUBLESHOOTING.md',
  'docs/en/RELEASE_CHECKLIST.md',
  'docs/es/README.md',
  'docs/es/INSTALL.md',
  'docs/es/USER_GUIDE.md',
  'docs/es/ARCHITECTURE.md',
  'docs/es/TEXTUAL_DSL.md',
  'docs/es/ECORE_TO_BLOCKLY_MAPPING.md',
  'docs/es/RUNNING_EXAMPLE.md',
  'docs/es/TROUBLESHOOTING.md',
  'docs/es/RELEASE_CHECKLIST.md',
  'docs/zh/README.md',
  'docs/zh/INSTALL.md',
  'docs/zh/USER_GUIDE.md',
  'docs/zh/ARCHITECTURE.md',
  'docs/zh/TEXTUAL_DSL.md',
  'docs/zh/ECORE_TO_BLOCKLY_MAPPING.md',
  'docs/zh/RUNNING_EXAMPLE.md',
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

const featureManifestFile = 'docs/assets/ecore-feature-gallery/manifest.json';
requiredAssets.push(featureManifestFile);
const featureExamples = loadFeatureManifest(featureManifestFile);

for (const entry of featureExamples) {
  for (const key of ['source', 'sourcePreview', 'preview', 'report', 'standalone']) {
    requiredAssets.push(`docs/assets/ecore-feature-gallery/${entry[key]}`);
  }
}

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
  'en/textual-dsl.html',
  'en/ecore-to-blockly-mapping.html',
  'en/running-example.html',
  'en/troubleshooting.html',
  'en/release-checklist.html',
  'es/index.html',
  'es/install.html',
  'es/user-guide.html',
  'es/architecture.html',
  'es/textual-dsl.html',
  'es/ecore-to-blockly-mapping.html',
  'es/running-example.html',
  'es/troubleshooting.html',
  'es/release-checklist.html',
  'zh/index.html',
  'zh/install.html',
  'zh/user-guide.html',
  'zh/architecture.html',
  'zh/textual-dsl.html',
  'zh/ecore-to-blockly-mapping.html',
  'zh/running-example.html',
  'zh/troubleshooting.html',
  'zh/release-checklist.html',
  'assets/screenshots/appmaker-editor.png',
  'assets/screenshots/validation-workspace.png',
  'assets/diagrams/system-architecture.svg',
  'assets/diagrams/generation-flow.svg',
  'assets/diagrams/output-artifacts.svg',
  'assets/diagrams/model2blockly-concept.svg',
  'assets/ecore-feature-gallery/manifest.json',
]) {
  if (!existsSync(path.join(docsOutputDir, file))) {
    fail(`Generated VitePress site missing ${file}`);
  }
}

for (const entry of featureExamples) {
  for (const file of [
    `assets/ecore-feature-gallery/${entry.source}`,
    `assets/ecore-feature-gallery/${entry.sourcePreview}`,
    `assets/ecore-feature-gallery/${entry.preview}`,
    `assets/ecore-feature-gallery/${entry.report}`,
    `assets/ecore-feature-gallery/${entry.standalone}`,
  ]) {
    if (!existsSync(path.join(docsOutputDir, file))) {
      fail(`Generated VitePress site missing ${file}`);
    }
  }
}

const generatedRootIndex = readGeneratedDoc('index.html');
const generatedEnIndex = readGeneratedDoc('en/index.html');
const generatedInstall = readGeneratedDoc('en/install.html');
const generatedUserGuide = readGeneratedDoc('en/user-guide.html');
const generatedArchitecture = readGeneratedDoc('en/architecture.html');
const generatedEnTextualDsl = readGeneratedDoc('en/textual-dsl.html');
const generatedEnMapping = readGeneratedDoc('en/ecore-to-blockly-mapping.html');
const generatedCase = readGeneratedDoc('en/running-example.html');
const generatedTroubleshooting = readGeneratedDoc('en/troubleshooting.html');
const generatedEsIndex = readGeneratedDoc('es/index.html');
const generatedEsInstall = readGeneratedDoc('es/install.html');
const generatedEsArchitecture = readGeneratedDoc('es/architecture.html');
const generatedEsTextualDsl = readGeneratedDoc('es/textual-dsl.html');
const generatedEsMapping = readGeneratedDoc('es/ecore-to-blockly-mapping.html');
const generatedZhIndex = readGeneratedDoc('zh/index.html');
const generatedZhInstall = readGeneratedDoc('zh/install.html');
const generatedZhArchitecture = readGeneratedDoc('zh/architecture.html');
const generatedZhTextualDsl = readGeneratedDoc('zh/textual-dsl.html');
const generatedZhMapping = readGeneratedDoc('zh/ecore-to-blockly-mapping.html');
const generatedZhCase = readGeneratedDoc('zh/running-example.html');

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
assertIncludes(generatedEnTextualDsl, 'Model2Blockly Textual DSL', 'English textual DSL page is generated');
assertIncludes(generatedEnTextualDsl, '.m2b', 'English textual DSL page documents the recommended extension');
assertIncludes(generatedEnTextualDsl, 'DomainModelAdapter', 'English textual DSL page documents the DSL adapter');
assertIncludes(generatedEnMapping, 'Ecore to Blockly Mapping', 'English mapping page is generated');
assertIncludes(generatedEnMapping, '01. zero annotation Ecore defaults', 'English mapping page exposes generated feature examples');
assertIncludes(generatedEnMapping, 'Full editor', 'English mapping page localizes generated resource links');
assertIncludes(generatedEnMapping, 'source=&quot;blockly&quot;', 'English mapping page documents blockly annotation keys');
assertIncludes(generatedEnMapping, 'source=&quot;validation&quot;', 'English mapping page documents validation annotation keys');
assertIncludes(generatedCase, 'Appmaker_blocklyspec.xmi', 'AppMaker case documents the intermediate XMI');
assertIncludes(generatedCase, 'Smoke Test', 'AppMaker case documents the browser smoke test');
assertIncludes(generatedTroubleshooting, 'GitHub Actions', 'troubleshooting explains Pages source configuration');
assertIncludes(generatedTroubleshooting, 'update-site', 'troubleshooting keeps plugin installation endpoint visible');
assertIncludes(generatedEsIndex, 'Genera editores Blockly desde Ecore anotado', 'Spanish home is generated from the current Ecore narrative');
assertIncludes(generatedEsInstall, 'Instalar el plugin de Eclipse', 'Spanish install page is generated');
assertIncludes(generatedEsArchitecture, 'Arquitectura', 'Spanish architecture page is generated');
assertIncludes(generatedEsTextualDsl, 'DSL textual de Model2Blockly', 'Spanish textual DSL page is generated');
assertIncludes(generatedEsMapping, 'Mapeo de Ecore a Blockly', 'Spanish mapping page is generated');
assertIncludes(generatedEsMapping, 'Editor completo', 'Spanish mapping page localizes generated resource links');
assertIncludes(generatedZhIndex, '用 MDE 生成 Blockly 编辑器', 'Chinese home is generated from the MDE dual-route narrative');
assertIncludes(generatedZhIndex, '给领域用户使用的 Blockly 编辑器', 'Chinese home explains who uses the generated editor');
assertIncludes(generatedZhIndex, '文本 DSL', 'Chinese home links to the textual DSL page');
assertNotIncludes(generatedZhIndex, '文档导览', 'Chinese home should not show the old documentation guide section');
assertNotIncludes(generatedZhIndex, '生成流程', 'Chinese home should not show the old generation flow section');
assertNotIncludes(generatedZhIndex, '适用场景', 'Chinese home should not show the old use-case section');
assertIncludes(generatedZhInstall, '安装 Eclipse 插件', 'Chinese install page is generated');
assertNotIncludes(generatedZhInstall, '支持 JavaSE-21', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '如果列表为空', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '更新已有安装', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '验证安装', 'Chinese install page should only show installation steps');
assertNotIncludes(generatedZhInstall, '相关页面', 'Chinese install page should only show installation steps');
assertIncludes(generatedZhArchitecture, '双输入架构', 'Chinese architecture page documents the dual input architecture');
assertIncludes(generatedZhArchitecture, 'EditorSpec EMF intermediate model', 'Chinese architecture page documents the EMF intermediate model');
assertIncludes(generatedZhArchitecture, 'Model2BlocklyToBlocklyMain.java', 'Chinese architecture page documents the standalone DSL entry point');
assertIncludes(generatedZhTextualDsl, 'Model2Blockly 文本 DSL', 'Chinese textual DSL page is generated');
assertIncludes(generatedZhTextualDsl, 'app_maker.m2b', 'Chinese textual DSL page links to the AppMaker m2b example');
assertIncludes(generatedZhTextualDsl, 'block-based language', 'Chinese textual DSL page explains the DSL role');
assertIncludes(generatedZhTextualDsl, 'DomainModelAdapter', 'Chinese textual DSL page documents the DSL adapter');
assertIncludes(generatedZhMapping, 'Ecore 到 Blockly 映射规则', 'Chinese mapping page is generated');
assertIncludes(generatedZhMapping, 'Ecore 功能转换示例', 'Chinese mapping page shows feature-level generated examples');
assertIncludes(generatedZhMapping, '从默认生成到定制', 'Chinese mapping page explains zero-annotation defaults and customization');
assertIncludes(generatedZhMapping, '没有注解时', 'Chinese mapping page documents default generation without annotations');
assertIncludes(generatedZhMapping, '需要改变效果时', 'Chinese mapping page documents which annotations customize Blockly output');
assertIncludes(generatedZhMapping, 'real-feature-card', 'Chinese mapping page renders the real generated feature cards');
assertIncludes(generatedZhMapping, '覆盖索引', 'Chinese mapping page shows the generated feature coverage index');
assertIncludes(generatedZhMapping, '01. 无注解 Ecore 默认生成', 'Chinese mapping page exposes a zero-annotation default example');
assertIncludes(generatedZhMapping, '02. 添加注解定制 Blockly', 'Chinese mapping page exposes an annotation customization example');
assertIncludes(generatedZhMapping, '03. EAttribute 转为 文本字段', 'Chinese mapping page exposes feature examples as outline headings');
assertIncludes(generatedZhMapping, '00-zero-annotation-defaults/source.html', 'Chinese mapping page embeds the zero-annotation source preview');
assertIncludes(generatedZhMapping, '00-annotation-customization/source.html', 'Chinese mapping page embeds the annotation customization source preview');
assertIncludes(generatedZhMapping, '01-text-field/source.html', 'Chinese mapping page embeds generated Ecore source previews');
assertIncludes(generatedZhMapping, 'Validationrule_standalone.html', 'Chinese mapping page links to generated standalone editors');
assertIncludes(generatedZhMapping, '27-runtime-kind/source.html', 'Chinese mapping page includes the full generated feature gallery');
assertIncludes(generatedZhMapping, 'runtime kind=appMaker', 'Chinese mapping page documents runtime-kind coverage');
assertIncludes(generatedZhMapping, 'level\\":[2,4]', 'Chinese mapping page enables h4 entries in the right outline');
assertIncludes(generatedZhMapping, 'target="_blank"', 'Chinese mapping page opens generated resource links outside the VitePress router');
assertNotIncludes(generatedZhMapping, 'ecore-blockly-showcase', 'Chinese mapping page should not use the old hand-drawn mapping showcase');
assertNotIncludes(generatedZhCase, 'Ecore 功能转换示例', 'Chinese AppMaker case should not contain the feature gallery after the split');
assertNotIncludes(generatedZhCase, 'real-feature-card', 'Chinese AppMaker case should not render generated feature cards after the split');
assertIncludes(generatedZhCase, 'Ecore route', 'Chinese AppMaker case documents the Ecore route');
assertIncludes(generatedZhCase, 'DSL route', 'Chinese AppMaker case documents the DSL route');
assertIncludes(generatedZhCase, 'app_maker.m2b', 'Chinese AppMaker case links to the textual DSL source');
assertIncludes(generatedZhCase, 'EClass 到 Blockly 对照', 'Chinese AppMaker case shows an EClass-to-block mapping summary');

if (featureExamples.length < 29) {
  fail(`Generated feature gallery should contain at least 29 examples, found ${featureExamples.length}`);
}

for (const file of listHtmlFiles(docsOutputDir)) {
  checkNoParagraphDirectlyInsideList(file);
}

for (const file of listAllFiles(docsOutputDir)) {
  const relative = path.relative(docsOutputDir, file).split(path.sep).join('/');
  if (relative.includes('ecore-feature-gallery') && /README/i.test(relative)) {
    fail(`Generated docs should not publish feature-gallery README pages: ${relative}`);
  }
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

function listAllFiles(absoluteDir) {
  const files = [];
  for (const entry of readdirSync(absoluteDir)) {
    const absolute = path.join(absoluteDir, entry);
    const stats = statSync(absolute);
    if (stats.isDirectory()) {
      files.push(...listAllFiles(absolute));
    } else {
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

function loadFeatureManifest(file) {
  const absolute = path.resolve(repoRoot, file);
  if (!existsSync(absolute)) {
    fail(`Missing required file: ${file}`);
    return [];
  }
  const entries = JSON.parse(readFileSync(absolute, 'utf8'));
  if (!Array.isArray(entries)) {
    fail(`${file} must contain an array`);
    return [];
  }
  for (const [index, entry] of entries.entries()) {
    for (const key of ['slug', 'source', 'sourcePreview', 'preview', 'report', 'standalone', 'group']) {
      if (!entry[key]) fail(`${file} entry ${index + 1} is missing ${key}`);
    }
    if (!Array.isArray(entry.covers) || entry.covers.length === 0) {
      fail(`${file} entry ${index + 1} is missing coverage items`);
    }
  }
  return entries;
}

function fail(message) {
  failures.push(message);
}
