#!/usr/bin/env node

import { createHash } from 'node:crypto';
import { existsSync, mkdirSync, readFileSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const evaluationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repoRoot = path.resolve(evaluationRoot, '../..');
const input = process.argv[2];

if (!input || input === '--help' || input === '-h') {
  console.error('Usage: node extract-upstream.mjs <case-directory>');
  process.exit(input ? 0 : 1);
}

const caseDir = path.resolve(repoRoot, input);
assertInside(caseDir, path.join(evaluationRoot, 'cases'));
const planPath = path.join(caseDir, 'baseline', 'extraction-plan.json');
if (!existsSync(planPath)) throw new Error(`Extraction plan not found: ${planPath}`);

const plan = JSON.parse(readFileSync(planPath, 'utf8'));
const upstreamFiles = new Map();
const fragments = [];
const assets = [];

for (const fragment of plan.fragments) {
  const sourcePath = fragment.sourcePath || plan.source.path;
  if (!sourcePath) throw new Error(`Missing sourcePath for ${fragment.id}.`);
  const upstreamLines = await loadUpstreamLines(sourcePath);
  if (fragment.startLine < 1 || fragment.endLine < fragment.startLine) {
    throw new Error(`Invalid range for ${fragment.id}: ${fragment.startLine}-${fragment.endLine}`);
  }
  if (fragment.endLine > upstreamLines.length) {
    throw new Error(`Range exceeds upstream file for ${fragment.id}.`);
  }
  const text = `${upstreamLines.slice(fragment.startLine - 1, fragment.endLine).join('\n')}\n`;
  const snapshot = path.resolve(caseDir, fragment.snapshot);
  assertInside(snapshot, caseDir);
  mkdirSync(path.dirname(snapshot), { recursive: true });
  writeFileSync(snapshot, text, 'utf8');
  fragments.push({
    id: fragment.id,
    path: sourcePath,
    snapshotPath: relativeRepoPath(snapshot),
    startLine: fragment.startLine,
    endLine: fragment.endLine,
    sha256: createHash('sha256').update(text).digest('hex'),
    kinds: fragment.kinds,
    metricGroups: fragment.metricGroups,
    countForMetrics: fragment.countForMetrics,
    sharedSourceKey: fragment.sharedSourceKey ?? null,
    notes: fragment.notes ?? [],
  });
  console.log(`[extract] ${fragment.id}: ${fragment.startLine}-${fragment.endLine} -> ${relativeRepoPath(snapshot)}`);
}

for (const asset of plan.assets ?? []) {
  const response = await fetch(rawUrl(plan.source.repository, plan.source.commit, asset.sourcePath));
  if (!response.ok) throw new Error(`Unable to download ${asset.sourcePath}: HTTP ${response.status}`);
  const bytes = Buffer.from(await response.arrayBuffer());
  const snapshot = path.resolve(caseDir, asset.snapshot);
  assertInside(snapshot, caseDir);
  mkdirSync(path.dirname(snapshot), { recursive: true });
  writeFileSync(snapshot, bytes);
  assets.push({
    id: asset.id,
    path: asset.sourcePath,
    snapshotPath: relativeRepoPath(snapshot),
    sha256: createHash('sha256').update(bytes).digest('hex'),
    mediaType: asset.mediaType,
    notes: asset.notes ?? [],
  });
  console.log(`[extract] ${asset.id}: binary asset -> ${relativeRepoPath(snapshot)}`);
}

const metadata = {
  schemaVersion: 1,
  caseId: plan.caseId,
  configuration: plan.configuration,
  source: {
    repository: plan.source.repository,
    commit: plan.source.commit,
    license: plan.source.license,
  },
  fragments,
  ...(assets.length ? { assets } : {}),
};
writeFileSync(
  path.join(caseDir, 'baseline', 'baseline-extraction.json'),
  `${JSON.stringify(metadata, null, 2)}\n`,
  'utf8',
);

function rawUrl(repository, commit, file) {
  const match = repository.match(/^https:\/\/github\.com\/([^/]+)\/([^/.]+)(?:\.git)?$/);
  if (!match) throw new Error(`Unsupported repository URL: ${repository}`);
  return `https://raw.githubusercontent.com/${match[1]}/${match[2]}/${commit}/${file}`;
}

async function loadUpstreamLines(sourcePath) {
  if (upstreamFiles.has(sourcePath)) return upstreamFiles.get(sourcePath);
  const upstreamUrl = rawUrl(plan.source.repository, plan.source.commit, sourcePath);
  const response = await fetch(upstreamUrl);
  if (!response.ok) throw new Error(`Unable to download ${upstreamUrl}: HTTP ${response.status}`);
  const lines = (await response.text()).replace(/\r\n/g, '\n').split('\n');
  upstreamFiles.set(sourcePath, lines);
  return lines;
}

function assertInside(value, root) {
  if (value !== root && !value.startsWith(root + path.sep)) {
    throw new Error(`Path escapes allowed directory: ${value}`);
  }
}

function relativeRepoPath(value) {
  return path.relative(repoRoot, value).split(path.sep).join('/');
}
