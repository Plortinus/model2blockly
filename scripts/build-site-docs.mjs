#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { cpSync, existsSync, mkdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const outputDir = path.resolve(repoRoot, optionValue('--out') || '_site/docs');
const sourceDir = path.resolve(repoRoot, '.cache/site-docs-vitepress');
const docsBase = optionValue('--base') || process.env.MODEL2BLOCKLY_DOCS_BASE || '/model2blockly/docs/';
const repoUrl = 'https://github.com/Plortinus/model2blockly';

const pages = [
  { source: 'docs/README.md', output: 'index.md' },
  { source: 'docs/en/README.md', output: 'en/index.md' },
  { source: 'docs/en/USER_GUIDE.md', output: 'en/user-guide.md' },
  { source: 'docs/en/ARCHITECTURE.md', output: 'en/architecture.md' },
  { source: 'README.md', output: 'en/project.md' },
  { source: 'DOCS.md', output: 'en/docs.md' },
  { source: 'GETTING_STARTED.md', output: 'en/getting-started.md' },
  { source: 'RUNNING_EXAMPLE.md', output: 'en/running-example.md' },
  { source: 'TROUBLESHOOTING.md', output: 'en/troubleshooting.md' },
  { source: 'ECORE_REFERENCE.md', output: 'en/ecore-reference.md' },
  { source: 'RELEASE_CHECKLIST.md', output: 'en/release-checklist.md' },
  { source: 'docs/es/USER_GUIDE.md', output: 'es/user-guide.md' },
  { source: 'docs/es/ARCHITECTURE.md', output: 'es/architecture.md' },
  { source: 'docs/es/PROJECT.md', output: 'es/project.md' },
  { source: 'docs/es/DOCS.md', output: 'es/docs.md' },
  { source: 'docs/es/RUNNING_EXAMPLE.md', output: 'es/running-example.md' },
  { source: 'docs/es/README.md', output: 'es/index.md' },
  { source: 'docs/es/GETTING_STARTED.md', output: 'es/getting-started.md' },
  { source: 'docs/es/TROUBLESHOOTING.md', output: 'es/troubleshooting.md' },
  { source: 'docs/es/ECORE_REFERENCE.md', output: 'es/ecore-reference.md' },
  { source: 'docs/es/RELEASE_CHECKLIST.md', output: 'es/release-checklist.md' },
  { source: 'docs/zh/USER_GUIDE.md', output: 'zh/user-guide.md' },
  { source: 'docs/zh/ARCHITECTURE.md', output: 'zh/architecture.md' },
  { source: 'docs/zh/PROJECT.md', output: 'zh/project.md' },
  { source: 'docs/zh/DOCS.md', output: 'zh/docs.md' },
  { source: 'docs/zh/RUNNING_EXAMPLE.md', output: 'zh/running-example.md' },
  { source: 'docs/zh/README.md', output: 'zh/index.md' },
  { source: 'docs/zh/GETTING_STARTED.md', output: 'zh/getting-started.md' },
  { source: 'docs/zh/TROUBLESHOOTING.md', output: 'zh/troubleshooting.md' },
  { source: 'docs/zh/ECORE_REFERENCE.md', output: 'zh/ecore-reference.md' },
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
const projectSite = 'https://plortinus.github.io/model2blockly/';
const updateSite = 'https://plortinus.github.io/model2blockly/update-site/';

const enSidebar = [
  {
    text: 'User Guide',
    items: [
      { text: 'Introduction', link: '/en/' },
      { text: 'User Guide', link: '/en/user-guide' },
      { text: 'Quick Start', link: '/en/getting-started' },
      { text: 'AppMaker Example', link: '/en/running-example' },
      { text: 'Troubleshooting', link: '/en/troubleshooting' },
    ],
  },
  {
    text: 'Architecture & Implementation',
    items: [
      { text: 'Architecture Overview', link: '/en/architecture' },
      { text: 'Project Overview', link: '/en/project' },
      { text: 'Generated Output', link: '/en/getting-started#understand-the-output-folder' },
      { text: 'Validation Workspace', link: '/en/getting-started#visual-walkthrough' },
      { text: 'Ecore Reference', link: '/en/ecore-reference' },
    ],
  },
  {
    text: 'Reference',
    items: [
      { text: 'Documentation Map', link: '/en/docs' },
      { text: 'Release Checklist', link: '/en/release-checklist' },
    ],
  },
];

const esSidebar = [
  {
    text: 'Guía de uso',
    items: [
      { text: 'Introducción', link: '/es/' },
      { text: 'Guía de uso', link: '/es/user-guide' },
      { text: 'Guía rápida', link: '/es/getting-started' },
      { text: 'Caso AppMaker', link: '/es/running-example' },
      { text: 'Solución de problemas', link: '/es/troubleshooting' },
    ],
  },
  {
    text: 'Arquitectura e implementación',
    items: [
      { text: 'Arquitectura', link: '/es/architecture' },
      { text: 'Vista general', link: '/es/project' },
      { text: 'Salida generada', link: '/es/getting-started#que-contiene-la-carpeta-generada' },
      { text: 'Workspace de validación', link: '/es/getting-started#guia-visual' },
      { text: 'Anotaciones Ecore', link: '/es/ecore-reference' },
    ],
  },
  {
    text: 'Referencia',
    items: [
      { text: 'Mapa de documentación', link: '/es/docs' },
      { text: 'Release checklist', link: '/es/release-checklist' },
    ],
  },
];

const zhSidebar = [
  {
    text: '使用指南',
    items: [
      { text: '介绍', link: '/zh/' },
      { text: '使用指南', link: '/zh/user-guide' },
      { text: '快速开始', link: '/zh/getting-started' },
      { text: 'AppMaker 案例', link: '/zh/running-example' },
      { text: '排错', link: '/zh/troubleshooting' },
    ],
  },
  {
    text: '架构与实现',
    items: [
      { text: '架构概览', link: '/zh/architecture' },
      { text: '项目概览', link: '/zh/project' },
      { text: '生成输出', link: '/zh/getting-started#生成目录里有什么' },
      { text: '验证工作区', link: '/zh/getting-started#截图指引' },
      { text: 'Ecore 注解', link: '/zh/ecore-reference' },
    ],
  },
  {
    text: '参考',
    items: [
      { text: '文档地图', link: '/zh/docs' },
      { text: '发布检查清单', link: '/zh/release-checklist' },
    ],
  },
];

export default defineConfig({
  base,
  title: 'Model2Blockly',
  description: 'Generate Blockly editors from annotated Ecore metamodels.',
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
      description: 'Generate Blockly editors from annotated Ecore metamodels.',
      themeConfig: {
        nav: [
          { text: 'Home', link: '/en/' },
          { text: 'User Guide', link: '/en/user-guide' },
          { text: 'Examples', link: '/en/running-example' },
          { text: 'Architecture', link: '/en/architecture' },
          { text: 'Reference', items: [
            { text: 'Ecore Reference', link: '/en/ecore-reference' },
            { text: 'Troubleshooting', link: '/en/troubleshooting' },
            { text: 'Documentation Map', link: '/en/docs' },
          ] },
          { text: 'Install', link: updateSite },
        ],
        sidebar: enSidebar,
        outline: { label: 'On this page', level: [2, 3] },
      },
    },
    es: {
      label: 'Español',
      lang: 'es-ES',
      title: 'Model2Blockly',
      description: 'Genere editores Blockly desde metamodelos Ecore anotados.',
      themeConfig: {
        nav: [
          { text: 'Inicio', link: '/es/' },
          { text: 'Guía de uso', link: '/es/user-guide' },
          { text: 'Ejemplos', link: '/es/running-example' },
          { text: 'Arquitectura', link: '/es/architecture' },
          { text: 'Referencia', items: [
            { text: 'Ecore', link: '/es/ecore-reference' },
            { text: 'Solución de problemas', link: '/es/troubleshooting' },
            { text: 'Mapa de docs', link: '/es/docs' },
          ] },
          { text: 'Instalar', link: updateSite },
        ],
        sidebar: esSidebar,
        outline: { label: 'En esta página', level: [2, 3] },
      },
    },
    zh: {
      label: '中文',
      lang: 'zh-CN',
      title: 'Model2Blockly',
      description: '从带注解的 Ecore 元模型生成 Blockly 编辑器。',
      themeConfig: {
        nav: [
          { text: '首页', link: '/zh/' },
          { text: '使用指南', link: '/zh/user-guide' },
          { text: '示例', link: '/zh/running-example' },
          { text: '架构', link: '/zh/architecture' },
          { text: '参考', items: [
            { text: 'Ecore', link: '/zh/ecore-reference' },
            { text: '排错', link: '/zh/troubleshooting' },
            { text: '文档地图', link: '/zh/docs' },
          ] },
          { text: '安装', link: updateSite },
        ],
        sidebar: zhSidebar,
        outline: { label: '本页目录', level: [2, 3] },
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
      message: 'Model2Blockly documentation · Project site: https://plortinus.github.io/model2blockly/',
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
  max-width: 920px;
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
