#!/usr/bin/env node

import { cpSync, existsSync, mkdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const outputDir = path.resolve(repoRoot, optionValue('--out') || '_site/docs');
const repoUrl = 'https://github.com/Plortinus/model2blockly';

const pages = [
  { source: 'docs/README.md', output: 'index.html', language: 'en', nav: 'Docs' },
  { source: 'docs/en/README.md', output: 'en/index.html', language: 'en', nav: 'English' },
  { source: 'README.md', output: 'en/project.html', language: 'en', nav: 'Project' },
  { source: 'DOCS.md', output: 'en/docs.html', language: 'en', nav: 'Map' },
  { source: 'GETTING_STARTED.md', output: 'en/getting-started.html', language: 'en', nav: 'Getting started' },
  { source: 'TROUBLESHOOTING.md', output: 'en/troubleshooting.html', language: 'en', nav: 'Troubleshooting' },
  { source: 'DSL_REFERENCE.md', output: 'en/dsl-reference.html', language: 'en', nav: 'DSL' },
  { source: 'ECORE_REFERENCE.md', output: 'en/ecore-reference.html', language: 'en', nav: 'Ecore' },
  { source: 'RELEASE_CHECKLIST.md', output: 'en/release-checklist.html', language: 'en', nav: 'Release' },
  { source: 'docs/es/README.md', output: 'es/index.html', language: 'es', nav: 'Inicio' },
  { source: 'docs/es/GETTING_STARTED.md', output: 'es/getting-started.html', language: 'es', nav: 'Primeros pasos' },
  { source: 'docs/es/TROUBLESHOOTING.md', output: 'es/troubleshooting.html', language: 'es', nav: 'Problemas' },
  { source: 'docs/es/DSL_REFERENCE.md', output: 'es/dsl-reference.html', language: 'es', nav: 'DSL' },
  { source: 'docs/es/ECORE_REFERENCE.md', output: 'es/ecore-reference.html', language: 'es', nav: 'Ecore' },
  { source: 'docs/es/RELEASE_CHECKLIST.md', output: 'es/release-checklist.html', language: 'es', nav: 'Release' },
  { source: 'docs/zh/README.md', output: 'zh/index.html', language: 'zh', nav: '首页' },
  { source: 'docs/zh/GETTING_STARTED.md', output: 'zh/getting-started.html', language: 'zh', nav: '入门' },
  { source: 'docs/zh/TROUBLESHOOTING.md', output: 'zh/troubleshooting.html', language: 'zh', nav: '排错' },
  { source: 'docs/zh/DSL_REFERENCE.md', output: 'zh/dsl-reference.html', language: 'zh', nav: 'DSL' },
  { source: 'docs/zh/ECORE_REFERENCE.md', output: 'zh/ecore-reference.html', language: 'zh', nav: 'Ecore' },
  { source: 'docs/zh/RELEASE_CHECKLIST.md', output: 'zh/release-checklist.html', language: 'zh', nav: '发布' },
];

const pagesBySource = new Map(pages.map((page) => [normalize(page.source), page]));

rmSync(outputDir, { recursive: true, force: true });
mkdirSync(outputDir, { recursive: true });

const docsAssetsDir = path.resolve(repoRoot, 'docs/assets');
if (existsSync(docsAssetsDir)) {
  cpSync(docsAssetsDir, path.join(outputDir, 'assets'), { recursive: true });
}

for (const page of pages) {
  const markdown = readFileSync(path.resolve(repoRoot, page.source), 'utf8');
  const title = firstHeading(markdown) || 'Model2Blockly Docs';
  const html = renderPage(page, title, renderMarkdown(markdown, page));
  const target = path.join(outputDir, page.output);
  mkdirSync(path.dirname(target), { recursive: true });
  writeFileSync(target, html);
}

console.log(`[docs] Built ${pages.length} documentation pages in ${path.relative(repoRoot, outputDir)}`);

function optionValue(name) {
  const index = process.argv.indexOf(name);
  return index >= 0 ? process.argv[index + 1] : null;
}

function renderPage(page, title, content) {
  return `<!doctype html>
<html lang="${page.language}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${escapeHtml(title)} - Model2Blockly</title>
  <style>
    :root {
      color-scheme: light;
      --bg: #f6f7f9;
      --panel: #ffffff;
      --text: #17202a;
      --muted: #5d6875;
      --line: #d9dee7;
      --accent: #1f6feb;
      --accent-strong: #174ea6;
      --code-bg: #f1f4f8;
    }
    * { box-sizing: border-box; }
    body {
      margin: 0;
      background: var(--bg);
      color: var(--text);
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
      line-height: 1.62;
    }
    .layout {
      width: min(1180px, calc(100% - 32px));
      margin: 0 auto;
      padding: 28px 0 56px;
      display: grid;
      grid-template-columns: 240px minmax(0, 1fr);
      gap: 28px;
    }
    aside {
      position: sticky;
      top: 20px;
      align-self: start;
      padding: 16px;
      border: 1px solid var(--line);
      border-radius: 8px;
      background: var(--panel);
    }
    main {
      min-width: 0;
      padding: 28px;
      border: 1px solid var(--line);
      border-radius: 8px;
      background: var(--panel);
    }
    .brand {
      display: inline-flex;
      margin-bottom: 14px;
      color: var(--text);
      font-weight: 760;
      font-size: 1.25rem;
      text-decoration: none;
    }
    .nav-group {
      margin: 14px 0 0;
      padding-top: 14px;
      border-top: 1px solid var(--line);
    }
    .nav-title {
      margin: 0 0 8px;
      color: var(--muted);
      font-size: 0.82rem;
      font-weight: 720;
      text-transform: uppercase;
      letter-spacing: 0;
    }
    .nav-link {
      display: block;
      padding: 5px 0;
      color: var(--accent);
      text-decoration: none;
      overflow-wrap: anywhere;
    }
    .nav-link:hover { color: var(--accent-strong); text-decoration: underline; }
    h1, h2, h3, h4, h5, h6 {
      line-height: 1.25;
      letter-spacing: 0;
    }
    h1 {
      margin: 0 0 18px;
      font-size: clamp(2rem, 5vw, 3.1rem);
    }
    h2 {
      margin-top: 34px;
      padding-top: 18px;
      border-top: 1px solid var(--line);
      font-size: 1.55rem;
    }
    h3 { margin-top: 26px; font-size: 1.2rem; }
    main, p, li, a {
      overflow-wrap: anywhere;
      word-break: break-word;
    }
    p, li { color: var(--muted); }
    a { color: var(--accent); text-decoration: none; }
    a:hover { color: var(--accent-strong); text-decoration: underline; }
    code {
      padding: 0.1em 0.25em;
      border-radius: 4px;
      background: var(--code-bg);
      overflow-wrap: anywhere;
      word-break: break-word;
      font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
      font-size: 0.92em;
    }
    pre {
      max-width: 100%;
      overflow-x: hidden;
      padding: 14px;
      border-radius: 8px;
      background: #0f172a;
      color: #e5e7eb;
      white-space: pre-wrap;
      overflow-wrap: anywhere;
      word-break: break-word;
    }
    pre code {
      padding: 0;
      background: transparent;
      color: inherit;
    }
    img {
      display: block;
      max-width: 100%;
      height: auto;
      margin: 14px 0 22px;
      border: 1px solid var(--line);
      border-radius: 8px;
      background: #fff;
      box-shadow: 0 10px 24px rgba(15, 23, 42, 0.06);
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin: 16px 0;
      overflow-wrap: anywhere;
    }
    th, td {
      padding: 9px 10px;
      border: 1px solid var(--line);
      text-align: left;
      vertical-align: top;
    }
    th {
      background: #f7f9fc;
      color: var(--text);
    }
    @media (max-width: 860px) {
      .layout {
        grid-template-columns: 1fr;
        width: min(100% - 24px, 1180px);
      }
      aside {
        position: static;
      }
      main {
        padding: 20px;
      }
    }
  </style>
</head>
<body>
  <div class="layout">
    <aside>
      <a class="brand" href="${relativeHref(page.output, 'index.html')}">Model2Blockly</a>
      ${renderNav(page)}
    </aside>
    <main>
${content}
    </main>
  </div>
</body>
</html>
`;
}

function renderNav(currentPage) {
  const languageLinks = [
    ['English', 'en/index.html'],
    ['Español', 'es/index.html'],
    ['中文', 'zh/index.html'],
  ].map(([label, output]) => `<a class="nav-link" href="${relativeHref(currentPage.output, output)}">${escapeHtml(label)}</a>`).join('\n');

  const sectionPages = pages
    .filter((page) => page.language === currentPage.language && page.output !== 'index.html')
    .map((page) => `<a class="nav-link" href="${relativeHref(currentPage.output, page.output)}">${escapeHtml(page.nav)}</a>`)
    .join('\n');

  return `<div class="nav-group">
    <p class="nav-title">Languages</p>
    ${languageLinks}
  </div>
  <div class="nav-group">
    <p class="nav-title">Pages</p>
    ${sectionPages}
  </div>
  <div class="nav-group">
    <a class="nav-link" href="${relativeHref(currentPage.output, '../index.html')}">Project site</a>
    <a class="nav-link" href="${relativeHref(currentPage.output, '../update-site/index.html')}">Update site</a>
  </div>`;
}

function renderMarkdown(markdown, page) {
  const lines = markdown.replace(/\r\n/g, '\n').split('\n');
  const output = [];
  let paragraph = [];
  let listType = null;
  let inCode = false;
  let codeLanguage = '';
  let codeLines = [];

  const flushParagraph = () => {
    if (!paragraph.length) return;
    output.push(`<p>${renderInline(paragraph.join(' '), page)}</p>`);
    paragraph = [];
  };
  const closeList = () => {
    if (!listType) return;
    output.push(`</${listType}>`);
    listType = null;
  };
  const openList = (type) => {
    flushParagraph();
    if (listType === type) return;
    closeList();
    output.push(`<${type}>`);
    listType = type;
  };

  for (let index = 0; index < lines.length; index++) {
    const line = lines[index];
    const trimmed = line.trim();

    if (inCode) {
      if (trimmed.startsWith('```')) {
        output.push(`<pre><code${codeLanguage ? ` class="language-${escapeAttr(codeLanguage)}"` : ''}>${escapeHtml(codeLines.join('\n'))}</code></pre>`);
        inCode = false;
        codeLanguage = '';
        codeLines = [];
      } else {
        codeLines.push(line);
      }
      continue;
    }

    if (trimmed.startsWith('```')) {
      flushParagraph();
      closeList();
      inCode = true;
      codeLanguage = trimmed.slice(3).trim();
      continue;
    }

    if (!trimmed) {
      flushParagraph();
      closeList();
      continue;
    }

    const heading = /^(#{1,6})\s+(.+)$/.exec(trimmed);
    if (heading) {
      flushParagraph();
      closeList();
      const level = heading[1].length;
      const text = heading[2].trim();
      output.push(`<h${level} id="${escapeAttr(slugify(text))}">${renderInline(text, page)}</h${level}>`);
      continue;
    }

    if (isTableStart(lines, index)) {
      flushParagraph();
      closeList();
      const tableLines = [line];
      index += 2;
      while (index < lines.length && lines[index].includes('|') && lines[index].trim()) {
        tableLines.push(lines[index]);
        index++;
      }
      index--;
      output.push(renderTable(tableLines, page));
      continue;
    }

    const unordered = /^\s*[-*]\s+(.+)$/.exec(line);
    if (unordered) {
      openList('ul');
      output.push(`<li>${renderInline(unordered[1], page)}</li>`);
      continue;
    }

    const ordered = /^\s*\d+\.\s+(.+)$/.exec(line);
    if (ordered) {
      openList('ol');
      output.push(`<li>${renderInline(ordered[1], page)}</li>`);
      continue;
    }

    if (listType && output.length && output[output.length - 1].endsWith('</li>')) {
      output[output.length - 1] = output[output.length - 1].replace(
        '</li>',
        ` ${renderInline(trimmed, page)}</li>`,
      );
      continue;
    }

    paragraph.push(trimmed);
  }

  flushParagraph();
  closeList();
  if (inCode) {
    output.push(`<pre><code${codeLanguage ? ` class="language-${escapeAttr(codeLanguage)}"` : ''}>${escapeHtml(codeLines.join('\n'))}</code></pre>`);
  }
  return output.map((line) => `      ${line}`).join('\n');
}

function isTableStart(lines, index) {
  return lines[index]?.includes('|')
    && /^\s*\|?\s*:?-{3,}:?\s*(\|\s*:?-{3,}:?\s*)+\|?\s*$/.test(lines[index + 1] || '');
}

function renderTable(tableLines, page) {
  const rows = tableLines.map((line) => splitTableRow(line));
  const head = rows[0];
  const body = rows.slice(1);
  return `<table>
<thead><tr>${head.map((cell) => `<th>${renderInline(cell, page)}</th>`).join('')}</tr></thead>
<tbody>
${body.map((row) => `<tr>${row.map((cell) => `<td>${renderInline(cell, page)}</td>`).join('')}</tr>`).join('\n')}
</tbody>
</table>`;
}

function splitTableRow(line) {
  return line.trim().replace(/^\|/, '').replace(/\|$/, '').split('|').map((cell) => cell.trim());
}

function renderInline(text, page) {
  const tokens = [];
  const token = (html) => {
    const id = tokens.length;
    tokens.push(html);
    return `\u0000${id}\u0000`;
  };
  let value = text.replace(/`([^`]+)`/g, (_, code) => token(`<code>${escapeHtml(code)}</code>`));
  value = value.replace(/<((?:https?:\/\/|mailto:)[^>\s]+)>/g, (_, target) => {
    return token(`<a href="${escapeAttr(target)}">${escapeHtml(target)}</a>`);
  });
  value = value.replace(/\*\*([^*]+)\*\*/g, (_, strong) => token(`<strong>${escapeHtml(strong)}</strong>`));
  value = value.replace(/!\[([^\]]*)\]\(([^)]+)\)/g, (_, alt, target) => {
    const src = resolveLink(target.trim(), page);
    return token(`<img src="${escapeAttr(src)}" alt="${escapeAttr(alt)}" loading="lazy">`);
  });
  value = value.replace(/\[([^\]]+)\]\(([^)]+)\)/g, (_, label, target) => {
    const href = resolveLink(target, page);
    return token(`<a href="${escapeAttr(href)}">${escapeHtml(label)}</a>`);
  });
  value = escapeHtml(value);
  return restoreTokens(value, tokens);
}

function restoreTokens(value, tokens) {
  let rendered = value;
  for (let pass = 0; pass < tokens.length + 2; pass++) {
    const next = rendered.replace(/\u0000(\d+)\u0000/g, (_, id) => tokens[Number(id)] || '');
    if (next === rendered) return next;
    rendered = next;
  }
  return rendered;
}

function resolveLink(target, page) {
  if (/^(https?:|mailto:)/.test(target) || target.startsWith('#')) return target;
  const cleaned = target.replace(/^<|>$/g, '');
  const hashIndex = cleaned.indexOf('#');
  const pathPart = hashIndex >= 0 ? cleaned.slice(0, hashIndex) : cleaned;
  const hash = hashIndex >= 0 ? cleaned.slice(hashIndex) : '';
  if (!pathPart) return hash;

  const absolute = path.resolve(repoRoot, path.dirname(page.source), pathPart);
  const relative = normalize(path.relative(repoRoot, absolute));
  if (relative.startsWith('docs/assets/')) {
    return relativeHref(page.output, relative.replace(/^docs\//, '')) + hash;
  }
  const targetPage = pagesBySource.get(relative);
  if (targetPage) return relativeHref(page.output, targetPage.output) + hash;
  return `${repoUrl}/blob/main/${relative}${hash}`;
}

function relativeHref(fromOutput, toOutput) {
  const href = normalize(path.relative(path.dirname(fromOutput), toOutput));
  return href || path.basename(toOutput);
}

function firstHeading(markdown) {
  const match = /^#\s+(.+)$/m.exec(markdown);
  return match ? match[1].trim() : null;
}

function slugify(text) {
  return text
    .toLowerCase()
    .replace(/`([^`]+)`/g, '$1')
    .replace(/[^\p{L}\p{N}\s-]/gu, '')
    .trim()
    .replace(/\s+/g, '-');
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

function escapeAttr(value) {
  return escapeHtml(value).replace(/"/g, '&quot;');
}
