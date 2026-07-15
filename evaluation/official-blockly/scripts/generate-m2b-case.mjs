#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import {
  copyFileSync,
  existsSync,
  mkdirSync,
  readdirSync,
  rmSync,
} from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const evaluationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repoRoot = path.resolve(evaluationRoot, '../..');
const projectDir = path.join(repoRoot, 'io.github.plortinus.model2blockly');
const eclipsePlugins = process.env.ECLIPSE_PLUGINS || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const sourceArg = process.argv[2];
const outputArg = process.argv[3];

if (!sourceArg || !outputArg || sourceArg === '--help' || sourceArg === '-h') {
  console.error('Usage: node generate-m2b-case.mjs <source.m2b|source.ecore> <output-directory>');
  process.exit(sourceArg ? 0 : 1);
}

const source = path.resolve(repoRoot, sourceArg);
const output = path.resolve(repoRoot, outputArg);
assertInside(source, path.join(evaluationRoot, 'cases'));
assertInside(output, path.join(evaluationRoot, 'cases'));
if (!existsSync(source)) throw new Error(`DSL source not found: ${source}`);

const javaHome = findJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin', executable('java'));
const javac = path.join(javaHome, 'bin', executable('javac'));
const classesDir = path.join(repoRoot, '.cache', 'evaluation-java', 'classes');
rmSync(classesDir, { recursive: true, force: true });
mkdirSync(classesDir, { recursive: true });

const roots = ['src', 'src-gen', 'emf-gen', 'xtend-gen'].map((name) => path.join(projectDir, name));
const sources = roots.flatMap((root) => collectFiles(root, (file) => file.endsWith('.java'))).sort();
console.log(`[generate] compiling ${sources.length} Java sources`);
execFileSync(javac, [
  '-cp', path.join(eclipsePlugins, '*'),
  '-d', classesDir,
  ...sources,
], { cwd: repoRoot, stdio: 'inherit' });

for (const resource of collectFiles(path.join(projectDir, 'src-gen'), (file) => !file.endsWith('.java'))) {
  const target = path.join(classesDir, path.relative(path.join(projectDir, 'src-gen'), resource));
  mkdirSync(path.dirname(target), { recursive: true });
  copyFileSync(resource, target);
}

rmSync(output, { recursive: true, force: true });
mkdirSync(output, { recursive: true });
const classpath = [classesDir, path.join(eclipsePlugins, '*')].join(path.delimiter);
const entryPoint = source.endsWith('.ecore')
  ? 'io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain'
  : 'io.github.plortinus.model2blockly.standalone.Model2BlocklyToBlocklyMain';
execFileSync(java, [
  '-cp', classpath,
  entryPoint,
  source,
  output,
], { cwd: repoRoot, stdio: 'inherit' });

function collectFiles(root, predicate) {
  const result = [];
  for (const entry of readdirSync(root, { withFileTypes: true })) {
    const absolute = path.join(root, entry.name);
    if (entry.isDirectory()) result.push(...collectFiles(absolute, predicate));
    else if (entry.isFile() && predicate(absolute)) result.push(absolute);
  }
  return result;
}

function findJavaHome(pluginsDir) {
  if (process.env.JAVA_HOME) return process.env.JAVA_HOME;
  const candidates = readdirSync(pluginsDir)
    .filter((name) => name.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .reverse();
  for (const candidate of candidates) {
    const home = path.join(pluginsDir, candidate, 'jre');
    if (existsSync(path.join(home, 'bin', executable('java')))
        && existsSync(path.join(home, 'bin', executable('javac')))) return home;
  }
  throw new Error('No Java development runtime found. Set JAVA_HOME to a JDK installation.');
}

function executable(name) {
  return process.platform === 'win32' ? `${name}.exe` : name;
}

function assertInside(value, root) {
  if (value !== root && !value.startsWith(root + path.sep)) {
    throw new Error(`Path escapes evaluation cases: ${value}`);
  }
}
