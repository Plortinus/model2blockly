#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { cpSync, existsSync, mkdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const outputDir = path.resolve(repoRoot, optionValue('--out') || '_site');
const sourceDir = path.resolve(repoRoot, '.cache/site-docs-vitepress');
const docsBase = optionValue('--base') || process.env.MODEL2BLOCKLY_DOCS_BASE || '/model2blockly/';
const repoUrl = 'https://github.com/Plortinus/model2blockly';

const pages = [
  { source: 'docs/README.md', output: 'index.md' },
  { source: 'docs/en/README.md', output: 'en/index.md' },
  { source: 'docs/en/INSTALL.md', output: 'en/install.md' },
  { source: 'docs/en/USER_GUIDE.md', output: 'en/user-guide.md' },
  { source: 'docs/en/ARCHITECTURE.md', output: 'en/architecture.md' },
  { source: 'docs/en/TEXTUAL_DSL.md', output: 'en/textual-dsl.md' },
  { source: 'docs/en/ECORE_TO_BLOCKLY_MAPPING.md', output: 'en/ecore-to-blockly-mapping.md' },
  { source: 'docs/en/RUNNING_EXAMPLE.md', output: 'en/running-example.md' },
  { source: 'docs/en/TROUBLESHOOTING.md', output: 'en/troubleshooting.md' },
  { source: 'docs/en/RELEASE_CHECKLIST.md', output: 'en/release-checklist.md' },
  { source: 'docs/es/README.md', output: 'es/index.md' },
  { source: 'docs/es/INSTALL.md', output: 'es/install.md' },
  { source: 'docs/es/USER_GUIDE.md', output: 'es/user-guide.md' },
  { source: 'docs/es/ARCHITECTURE.md', output: 'es/architecture.md' },
  { source: 'docs/es/TEXTUAL_DSL.md', output: 'es/textual-dsl.md' },
  { source: 'docs/es/ECORE_TO_BLOCKLY_MAPPING.md', output: 'es/ecore-to-blockly-mapping.md' },
  { source: 'docs/es/RUNNING_EXAMPLE.md', output: 'es/running-example.md' },
  { source: 'docs/es/TROUBLESHOOTING.md', output: 'es/troubleshooting.md' },
  { source: 'docs/es/RELEASE_CHECKLIST.md', output: 'es/release-checklist.md' },
  { source: 'docs/zh/README.md', output: 'zh/index.md' },
  { source: 'docs/zh/INSTALL.md', output: 'zh/install.md' },
  { source: 'docs/zh/USER_GUIDE.md', output: 'zh/user-guide.md' },
  { source: 'docs/zh/ARCHITECTURE.md', output: 'zh/architecture.md' },
  { source: 'docs/zh/TEXTUAL_DSL.md', output: 'zh/textual-dsl.md' },
  { source: 'docs/zh/ECORE_TO_BLOCKLY_MAPPING.md', output: 'zh/ecore-to-blockly-mapping.md' },
  { source: 'docs/zh/RUNNING_EXAMPLE.md', output: 'zh/running-example.md' },
  { source: 'docs/zh/TROUBLESHOOTING.md', output: 'zh/troubleshooting.md' },
  { source: 'docs/zh/RELEASE_CHECKLIST.md', output: 'zh/release-checklist.md' },
];

const pagesBySource = new Map(pages.map((page) => [normalize(page.source), page]));

rmSync(sourceDir, { recursive: true, force: true });
rmSync(outputDir, { recursive: true, force: true });
mkdirSync(sourceDir, { recursive: true });

const docsAssetsDir = path.resolve(repoRoot, 'docs/assets');
if (existsSync(docsAssetsDir)) {
  cpSync(docsAssetsDir, path.join(sourceDir, 'public/assets'), { recursive: true });
}

for (const page of pages) {
  const source = path.resolve(repoRoot, page.source);
  let markdown = readFileSync(source, 'utf8').replace(/\r\n/g, '\n');
  markdown = injectGeneratedFeatureGallery(markdown, page);
  markdown = rewriteCodeFenceLanguages(markdown);
  markdown = protectInlineMustacheCode(markdown);
  markdown = rewriteMarkdownLinks(markdown, page);

  const target = path.join(sourceDir, page.output);
  mkdirSync(path.dirname(target), { recursive: true });
  writeFileSync(target, markdown);
}

writeVitePressConfig();
writeVitePressTheme();

const vitepress = vitepressCommand();
execFileSync(vitepress.command, [...vitepress.args, 'build', sourceDir, '--outDir', outputDir], {
  cwd: repoRoot,
  env: {
    ...process.env,
    MODEL2BLOCKLY_DOCS_BASE: docsBase,
  },
  stdio: 'inherit',
});

console.log(`[docs] Built VitePress documentation in ${path.relative(repoRoot, outputDir)}`);

function optionValue(name) {
  const index = process.argv.indexOf(name);
  return index >= 0 ? process.argv[index + 1] : null;
}

function rewriteCodeFenceLanguages(markdown) {
  return markdown.replace(/^```model2blockly(\s*)$/gm, '```text$1');
}

function protectInlineMustacheCode(markdown) {
  return markdown.replace(/`([^`\n]*\{\{[^`\n]*\}\}[^`\n]*)`/g, (_, code) => {
    return `<code v-pre>${escapeHtml(code)}</code>`;
  });
}

function injectGeneratedFeatureGallery(markdown, page) {
  const marker = '<!-- GENERATED_ECORE_FEATURE_GALLERY -->';
  if (!markdown.includes(marker)) return markdown;

  const locale = pageLocale(page);
  const manifestPath = path.join(repoRoot, 'docs/assets/ecore-feature-gallery/manifest.json');
  const manifest = JSON.parse(readFileSync(manifestPath, 'utf8'));
  const numberedEntries = manifest.map((entry, index) => ({
    ...entry,
    number: String(index + 1).padStart(2, '0'),
  }));
  const groups = groupedFeatureEntries(numberedEntries);
  const galleryGroups = groups.map(([group, entries]) => {
    const cards = entries.map((entry) => {
      const title = localizedFeatureTitle(entry, locale);
      const displayTitle = formatFeatureDisplayText(title, locale);
      const description = localizedFeatureDescription(entry, locale);
      const report = entry.report || `${entry.slug}/generated/generation_report.html`;
      const coverage = coverageListHtml(entry.covers, locale);
      const text = galleryUiText(locale);

      return `<span id="feature-${escapeAttribute(entry.slug)}" class="feature-anchor"></span>

#### ${entry.number}. ${displayTitle}

<article class="real-feature-card">
    <header>
      <p class="feature-index">${entry.number}</p>
      <p class="feature-card-summary">${escapeHtml(description)}</p>
    </header>
    ${coverage}
    <div class="real-feature-grid">
      <div class="real-feature-pane">
        <div class="pane-title">source.ecore</div>
        <iframe class="feature-source-frame" loading="lazy" src="../assets/ecore-feature-gallery/${escapeAttribute(entry.sourcePreview)}" title="${escapeAttribute(displayTitle)} Ecore source"></iframe>
      </div>
      <div class="real-feature-pane">
        <div class="pane-title">${escapeHtml(text.generatedBlockly)}</div>
        <iframe class="feature-preview-frame" loading="lazy" src="../assets/ecore-feature-gallery/${escapeAttribute(entry.preview)}" title="${escapeAttribute(displayTitle)} generated Blockly preview"></iframe>
      </div>
    </div>
    <p class="feature-links">
      <a target="_blank" rel="noreferrer" href="../assets/ecore-feature-gallery/${escapeAttribute(entry.source)}">source.ecore</a>
      <a target="_blank" rel="noreferrer" href="../assets/ecore-feature-gallery/${escapeAttribute(entry.standalone)}">${escapeHtml(text.fullEditor)}</a>
      <a target="_blank" rel="noreferrer" href="../assets/ecore-feature-gallery/${escapeAttribute(report)}">${escapeHtml(text.report)}</a>
    </p>
  </article>`;
    }).join('\n\n');

    return `### ${localizedGroupName(group, locale)}

${cards}`;
  }).join('\n\n');

  return markdown.replace(marker, `${coverageIndexHtml(numberedEntries, locale)}\n\n${galleryGroups}`);
}

function coverageIndexHtml(manifest, locale) {
  const text = galleryUiText(locale);
  const groupSections = groupedFeatureEntries(manifest).map(([group, entries]) => {
    const rows = entries.map((entry) => {
      const title = localizedFeatureTitle(entry, locale);
      return `<li><a href="#feature-${escapeAttribute(entry.slug)}"><span class="coverage-number">${escapeHtml(entry.number)}</span><span class="coverage-title">${escapeHtml(formatFeatureDisplayText(title, locale))}</span></a></li>`;
    }).join('\n');

    return `<div class="coverage-group">
  <h3>${escapeHtml(localizedGroupName(group, locale))}</h3>
  <ul>
${rows}
  </ul>
</div>`;
  }).join('\n');

  return `<section class="feature-coverage-index" aria-label="${escapeAttribute(text.coverageIndex)}">
  <h3>${escapeHtml(text.coverageIndex)}</h3>
  <p>${text.coverageIndexDescription}</p>
  <div class="coverage-index-grid">
${groupSections}
  </div>
</section>`;
}

function groupedFeatureEntries(entries) {
  const groups = new Map();
  for (const entry of entries) {
    const group = entry.group || '其他';
    if (!groups.has(group)) groups.set(group, []);
    groups.get(group).push(entry);
  }
  return [...groups.entries()];
}

function coverageListHtml(covers, locale) {
  if (!Array.isArray(covers) || covers.length === 0) return '';
  const items = covers.map((item) => `<li>${escapeHtml(formatFeatureDisplayText(item, locale))}</li>`).join('');
  return `<div class="feature-coverage"><span>${escapeHtml(galleryUiText(locale).covers)}</span><ul>${items}</ul></div>`;
}

function pageLocale(page) {
  if (page.output.startsWith('zh/')) return 'zh';
  if (page.output.startsWith('es/')) return 'es';
  return 'en';
}

function localizedFeatureTitle(entry, locale) {
  return locale === 'zh' ? (entry.zhTitle || entry.title) : entry.title;
}

function localizedFeatureDescription(entry, locale) {
  return locale === 'zh' ? (entry.zhDescription || entry.description) : entry.description;
}

function localizedGroupName(group, locale) {
  const groups = {
    '从默认到定制': { en: 'Defaults and customization', es: 'Valores por defecto y personalización' },
    '零注解与字段': { en: 'Defaults and fields', es: 'Valores por defecto y campos' },
    '字段类型': { en: 'Field types', es: 'Tipos de campo' },
    '引用与连接': { en: 'References and connections', es: 'Referencias y conexiones' },
    '继承与连接': { en: 'Inheritance and connections', es: 'Herencia y conexiones' },
    '校验': { en: 'Validation', es: 'Validación' },
    'Blockly 注解': { en: 'Blockly annotations', es: 'Anotaciones Blockly' },
    'Toolbox 分类': { en: 'Toolbox categories', es: 'Categorías de toolbox' },
    '代码生成': { en: 'Code generation', es: 'Generación de código' },
    'Workspace 配置': { en: 'Workspace options', es: 'Opciones de workspace' },
    'UI 元数据': { en: 'UI metadata', es: 'Metadatos UI' },
    'Ecore 结构': { en: 'Ecore structure', es: 'Estructura Ecore' },
    '运行时': { en: 'Runtime', es: 'Runtime' },
  };
  return locale === 'zh' ? group : (groups[group]?.[locale] || group);
}

function galleryUiText(locale) {
  if (locale === 'zh') {
    return {
      coverageIndex: '覆盖索引',
      coverageIndexDescription: '索引用于快速定位最小 <code>.ecore</code> 示例。每个示例页内提供源码、Blockly 预览和生成报告。',
      covers: '覆盖',
      generatedBlockly: '生成的 Blockly HTML',
      fullEditor: '完整编辑器',
      report: '生成报告',
    };
  }
  if (locale === 'es') {
    return {
      coverageIndex: 'Índice de cobertura',
      coverageIndexDescription: 'El índice permite localizar rápidamente ejemplos mínimos <code>.ecore</code>. Cada ejemplo incluye el código fuente, una vista Blockly y el informe de generación.',
      covers: 'Cubre',
      generatedBlockly: 'HTML Blockly generado',
      fullEditor: 'Editor completo',
      report: 'Informe de generación',
    };
  }
  return {
    coverageIndex: 'Coverage Index',
    coverageIndexDescription: 'Use this index to locate minimal <code>.ecore</code> examples. Each example includes source, a Blockly preview and a generation report.',
    covers: 'Covers',
    generatedBlockly: 'Generated Blockly HTML',
    fullEditor: 'Full editor',
    report: 'Generation report',
  };
}

function formatFeatureDisplayText(value, locale) {
  const replacement = locale === 'zh' ? ' 转为 ' : ' to ';
  return String(value).replace(/\s*->\s*/g, replacement);
}

function rewriteMarkdownLinks(markdown, page) {
  return markdown.replace(/(!?)\[([^\]]*)\]\(([^)\n]+)\)/g, (match, imagePrefix, label, rawTarget) => {
    const resolved = resolveMarkdownTarget(rawTarget.trim(), page);
    return `${imagePrefix}[${label}](${resolved})`;
  });
}

function resolveMarkdownTarget(rawTarget, page) {
  if (/^(https?:|mailto:|tel:)/.test(rawTarget) || rawTarget.startsWith('#')) return rawTarget;

  const cleaned = rawTarget.replace(/^<|>$/g, '');
  const { pathPart, hash } = splitHash(cleaned);
  if (!pathPart) return hash;

  const absolute = path.resolve(repoRoot, path.dirname(page.source), pathPart);
  const relative = normalize(path.relative(repoRoot, absolute));
  if (relative.startsWith('..')) return rawTarget;

  if (relative.startsWith('docs/assets/')) {
    return `/assets/${relative.slice('docs/assets/'.length)}${hash}`;
  }

  const targetPage = pagesBySource.get(relative);
  if (targetPage) return relativeMarkdownHref(page.output, targetPage.output) + hash;

  return `${repoUrl}/blob/main/${relative}${hash}`;
}

function splitHash(target) {
  const hashIndex = target.indexOf('#');
  if (hashIndex < 0) return { pathPart: target, hash: '' };
  return {
    pathPart: target.slice(0, hashIndex),
    hash: target.slice(hashIndex),
  };
}

function relativeMarkdownHref(fromOutput, toOutput) {
  const relative = normalize(path.relative(path.dirname(fromOutput), toOutput));
  return relative || path.basename(toOutput);
}

function writeVitePressConfig() {
  const vitepressDir = path.join(sourceDir, '.vitepress');
  mkdirSync(vitepressDir, { recursive: true });
  writeFileSync(path.join(vitepressDir, 'config.mjs'), `import { defineConfig } from 'vitepress';

const base = process.env.MODEL2BLOCKLY_DOCS_BASE || ${JSON.stringify(docsBase)};

const repoUrl = ${JSON.stringify(repoUrl)};
const appMakerDemo = 'https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html';

const enSidebar = [
  {
    text: 'Start',
    items: [
      { text: 'Overview', link: '/en/' },
      { text: 'Install Plugin', link: '/en/install' },
      { text: 'User Guide', link: '/en/user-guide' },
      { text: 'AppMaker Case', link: '/en/running-example' },
    ],
  },
  {
    text: 'Design',
    items: [
      { text: 'Architecture', link: '/en/architecture' },
      { text: 'Textual DSL', link: '/en/textual-dsl' },
      { text: 'Ecore Mapping', link: '/en/ecore-to-blockly-mapping' },
    ],
  },
];

const esSidebar = [
  {
    text: 'Inicio',
    items: [
      { text: 'Vista general', link: '/es/' },
      { text: 'Instalar plugin', link: '/es/install' },
      { text: 'Guía de uso', link: '/es/user-guide' },
      { text: 'Caso AppMaker', link: '/es/running-example' },
    ],
  },
  {
    text: 'Diseño',
    items: [
      { text: 'Arquitectura', link: '/es/architecture' },
      { text: 'DSL textual', link: '/es/textual-dsl' },
      { text: 'Mapeo Ecore', link: '/es/ecore-to-blockly-mapping' },
    ],
  },
];

const zhSidebar = [
  {
    text: '开始',
    items: [
      { text: '概览', link: '/zh/' },
      { text: '安装插件', link: '/zh/install' },
      { text: '使用指南', link: '/zh/user-guide' },
      { text: 'AppMaker 案例', link: '/zh/running-example' },
    ],
  },
  {
    text: '设计',
    items: [
      { text: '架构', link: '/zh/architecture' },
      { text: '文本 DSL', link: '/zh/textual-dsl' },
      { text: 'Ecore 到 Blockly', link: '/zh/ecore-to-blockly-mapping' },
    ],
  },
];

export default defineConfig({
  base,
  title: 'Model2Blockly',
  description: 'Generate Blockly editors from Ecore metamodels or .m2b textual DSL models.',
  cleanUrls: false,
  lastUpdated: false,
  markdown: {
    lineNumbers: true,
  },
  locales: {
    en: {
      label: 'English',
      lang: 'en-US',
      title: 'Model2Blockly',
      description: 'Generate Blockly editors from Ecore metamodels or .m2b textual DSL models.',
      themeConfig: {
        nav: [
          { text: 'Home', link: '/en/' },
          { text: 'Install', link: '/en/install' },
          { text: 'User Guide', link: '/en/user-guide' },
          { text: 'AppMaker Case', link: '/en/running-example' },
          { text: 'Textual DSL', link: '/en/textual-dsl' },
          { text: 'Mapping', link: '/en/ecore-to-blockly-mapping' },
          { text: 'Architecture', link: '/en/architecture' },
          { text: 'Reference', items: [
            { text: 'Troubleshooting', link: '/en/troubleshooting' },
            { text: 'Release checklist', link: '/en/release-checklist' },
          ] },
          { text: 'Demo', link: appMakerDemo },
        ],
        sidebar: enSidebar,
        outline: { label: 'On this page', level: [2, 3] },
      },
    },
    es: {
      label: 'Español',
      lang: 'es-ES',
      title: 'Model2Blockly',
      description: 'Genera editores Blockly desde metamodelos Ecore o modelos textuales .m2b.',
      themeConfig: {
        nav: [
          { text: 'Inicio', link: '/es/' },
          { text: 'Instalar', link: '/es/install' },
          { text: 'Guía de uso', link: '/es/user-guide' },
          { text: 'Caso AppMaker', link: '/es/running-example' },
          { text: 'DSL textual', link: '/es/textual-dsl' },
          { text: 'Mapeo', link: '/es/ecore-to-blockly-mapping' },
          { text: 'Arquitectura', link: '/es/architecture' },
          { text: 'Referencia', items: [
            { text: 'Solución de problemas', link: '/es/troubleshooting' },
            { text: 'Checklist de publicación', link: '/es/release-checklist' },
          ] },
          { text: 'Demo', link: appMakerDemo },
        ],
        sidebar: esSidebar,
        outline: { label: 'En esta página', level: [2, 3] },
      },
    },
    zh: {
      label: '中文',
      lang: 'zh-CN',
      title: 'Model2Blockly',
      description: '从 Ecore 元模型或 .m2b 文本 DSL 生成 Blockly 编辑器。',
      themeConfig: {
        nav: [
          { text: '首页', link: '/zh/' },
          { text: '安装', link: '/zh/install' },
          { text: '使用指南', link: '/zh/user-guide' },
          { text: 'AppMaker 案例', link: '/zh/running-example' },
          { text: '文本 DSL', link: '/zh/textual-dsl' },
          { text: '映射规则', link: '/zh/ecore-to-blockly-mapping' },
          { text: '架构', link: '/zh/architecture' },
          { text: '参考', items: [
            { text: '故障排查', link: '/zh/troubleshooting' },
            { text: '发布清单', link: '/zh/release-checklist' },
          ] },
          { text: '演示', link: appMakerDemo },
        ],
        sidebar: zhSidebar,
        outline: { label: '本页目录', level: [2, 4] },
      },
    },
  },
  themeConfig: {
    siteTitle: 'Model2Blockly',
    search: {
      provider: 'local',
    },
    socialLinks: [
      { icon: 'github', link: repoUrl },
    ],
    footer: {
      message: 'Model2Blockly documentation · Ecore and .m2b to Blockly through an EMF intermediate model.',
    },
  },
});
`);
}

function writeVitePressTheme() {
  const themeDir = path.join(sourceDir, '.vitepress/theme');
  mkdirSync(themeDir, { recursive: true });
  writeFileSync(path.join(themeDir, 'index.js'), `import DefaultTheme from 'vitepress/theme';
import './custom.css';

export default DefaultTheme;
`);
  writeFileSync(path.join(themeDir, 'custom.css'), `:root {
  --vp-c-brand-1: #1f6feb;
  --vp-c-brand-2: #174ea6;
  --vp-c-brand-3: #2f81f7;
  --vp-c-brand-soft: rgba(31, 111, 235, 0.12);
}

.VPNav {
  border-bottom: 1px solid var(--vp-c-divider);
  backdrop-filter: blur(12px);
}

.VPHomeHero .container {
  max-width: 1280px;
}

.VPHomeHero .image-src {
  display: block;
  position: static;
  width: 100%;
  height: auto;
  max-width: none;
  max-height: none;
  transform: none;
}

.VPHomeHero .image-bg {
  display: none;
}

@media (min-width: 960px) {
  .VPHomeHero .main {
    max-width: 520px;
  }

  .VPHomeHero .image {
    display: flex;
    align-items: center;
    justify-content: center;
    width: min(52vw, 720px);
    height: auto;
    margin: 0 0 0 32px;
  }

  .VPHomeHero .image-container {
    width: min(52vw, 720px);
    height: auto;
    transform: none;
  }
}

@media (max-width: 959px) {
  .VPHomeHero .image {
    width: 100%;
    height: auto;
    margin: 28px 0 0;
  }

  .VPHomeHero .image-container,
  .VPHomeHero .image-src {
    width: min(calc(100vw - 48px), 680px);
    height: auto;
    transform: none;
  }
}

.VPDoc .content-container {
  max-width: 1040px !important;
}

.appmaker-case-page .VPDoc .content,
.ecore-mapping-page .VPDoc .content {
  flex: 1 1 auto;
  width: 100% !important;
  max-width: none !important;
}

.appmaker-case-page .VPDoc .content-container,
.ecore-mapping-page .VPDoc .content-container {
  max-width: 1120px !important;
}

.VPDoc img {
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  background: var(--vp-c-bg);
}

.VPDoc table {
  display: table;
  width: 100%;
}

.VPDoc td,
.VPDoc th {
  word-break: break-word;
}

.feature-coverage-index {
  margin: 22px 0 30px;
  padding: 18px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  background: var(--vp-c-bg-soft);
}

.feature-coverage-index h3 {
  margin: 0 0 8px;
  padding: 0;
  border: 0;
  font-size: 18px;
}

.feature-coverage-index p {
  margin: 0 0 14px;
  color: var(--vp-c-text-2);
}

.coverage-index-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 12px;
}

.coverage-group {
  min-width: 0;
  padding: 10px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  background: var(--vp-c-bg);
}

.coverage-group h3 {
  margin: 0 0 8px;
  color: var(--vp-c-text-1);
  font-size: 15px;
}

.coverage-group ul {
  display: grid;
  gap: 3px;
  margin: 0;
  padding: 0;
  list-style: none;
}

.coverage-group li {
  min-width: 0;
}

.coverage-group a {
  display: grid;
  grid-template-columns: 32px minmax(0, 1fr);
  gap: 8px;
  align-items: center;
  min-height: 32px;
  padding: 5px 6px;
  border-radius: 6px;
  color: var(--vp-c-text-1);
  font-size: 13px;
  font-weight: 650;
  line-height: 1.35;
  text-decoration: none;
}

.coverage-group a:hover {
  background: var(--vp-c-bg-soft);
  color: var(--vp-c-brand-1);
}

.coverage-number {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 28px;
  height: 22px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 999px;
  background: var(--vp-c-bg-soft);
  color: var(--vp-c-text-2);
  font-size: 11px;
  font-weight: 750;
}

.coverage-title {
  min-width: 0;
  overflow-wrap: anywhere;
}

.feature-anchor {
  display: block;
  position: relative;
  top: -92px;
  visibility: hidden;
}

.real-feature-gallery {
  display: grid;
  gap: 28px;
  margin: 24px 0 36px;
}

.real-feature-card {
  padding-top: 14px;
  border-top: 1px solid var(--vp-c-divider);
}

.real-feature-card:last-child {
  border-bottom: 1px solid var(--vp-c-divider);
  padding-bottom: 18px;
}

.real-feature-card header {
  display: grid;
  grid-template-columns: 42px minmax(0, 1fr);
  column-gap: 14px;
  align-items: start;
  margin: 0 0 12px;
}

.real-feature-card h3 {
  margin: 0;
  padding: 0;
  border: 0;
  color: var(--vp-c-text-1);
  font-size: 20px;
  line-height: 1.3;
}

.real-feature-card .feature-card-summary {
  grid-column: 2;
  margin: 0 !important;
  color: var(--vp-c-text-2);
  font-size: 15px;
  line-height: 1.55;
}

.feature-coverage {
  display: flex;
  gap: 10px;
  align-items: flex-start;
  margin: 0 0 14px;
  padding: 10px 12px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  background: var(--vp-c-bg-soft);
}

.feature-coverage > span {
  flex: 0 0 auto;
  padding-top: 5px;
  color: var(--vp-c-text-2);
  font-size: 12px;
  font-weight: 750;
  line-height: 1.2;
}

.feature-coverage ul {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  margin: 0 !important;
  padding: 0 !important;
  list-style: none;
}

.feature-coverage li {
  display: inline-flex;
  align-items: center;
  min-height: 26px;
  margin: 0 !important;
  padding: 3px 8px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 999px;
  background: var(--vp-c-bg);
  color: var(--vp-c-text-2);
  font-size: 12px;
  line-height: 1.3;
}

.real-feature-card .feature-index {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 36px;
  height: 28px;
  margin: 0 !important;
  padding: 0;
  border: 1px solid var(--vp-c-divider);
  border-radius: 6px;
  background: var(--vp-c-bg);
  color: var(--vp-c-text-2);
  font-size: 12px;
  font-weight: 750;
  line-height: 1.2;
}

.real-feature-grid {
  display: grid;
  grid-template-columns: minmax(420px, 1fr) minmax(420px, 1fr);
  gap: 14px;
  align-items: stretch;
}

.real-feature-pane {
  min-width: 0;
  overflow: hidden;
  border: 1px solid var(--vp-c-divider);
  border-radius: 8px;
  background: var(--vp-c-bg);
}

.pane-title {
  padding: 8px 11px;
  border-bottom: 1px solid var(--vp-c-divider);
  background: var(--vp-c-bg-soft);
  color: var(--vp-c-text-2);
  font-size: 12px;
  font-weight: 750;
}

.feature-source-frame,
.feature-preview-frame {
  display: block;
  width: 100%;
  border: 0;
  background: var(--vp-c-bg-soft);
}

.feature-source-frame {
  height: 430px;
}

.feature-preview-frame {
  height: 430px;
}

.feature-links {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin: 10px 0 0;
}

.feature-links a {
  display: inline-flex;
  align-items: center;
  min-height: 30px;
  padding: 4px 9px;
  border: 1px solid var(--vp-c-divider);
  border-radius: 6px;
  background: var(--vp-c-bg-soft);
  color: var(--vp-c-brand-1);
  font-size: 13px;
  font-weight: 650;
}

@media (max-width: 980px) {
  .appmaker-case-page .VPDoc .content-container,
  .ecore-mapping-page .VPDoc .content-container {
    max-width: 100% !important;
  }

  .coverage-index-grid {
    grid-template-columns: 1fr;
  }

  .real-feature-grid {
    grid-template-columns: 1fr;
  }

  .feature-source-frame,
  .feature-preview-frame {
    height: 390px;
  }
}

@media (max-width: 560px) {
  .real-feature-card header {
    grid-template-columns: 1fr;
  }

  .real-feature-card .feature-card-summary {
    grid-column: 1;
  }

  .real-feature-card .feature-index {
    margin-bottom: 8px !important;
  }
}
`);
}

function commandName(command) {
  return process.platform === 'win32' ? `${command}.cmd` : command;
}

function vitepressCommand() {
  const localVitepress = path.join(repoRoot, 'node_modules/vitepress/bin/vitepress.js');
  if (existsSync(localVitepress)) {
    return {
      command: process.execPath,
      args: [localVitepress],
    };
  }
  return {
    command: commandName('npx'),
    args: ['vitepress'],
  };
}

function normalize(file) {
  return file.split(path.sep).join('/');
}

function escapeHtml(value) {
  return String(value)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;');
}

function escapeAttribute(value) {
  return escapeHtml(value).replace(/"/g, '&quot;');
}
