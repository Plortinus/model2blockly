#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import {
  copyFileSync,
  existsSync,
  mkdirSync,
  mkdtempSync,
  readFileSync,
  readdirSync,
  rmSync,
  statSync,
} from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const projectDir = path.join(repoRoot, 'io.github.plortinus.model2blockly');
const pairsRoot = path.join(repoRoot, 'examples', 'feature_pairs');
const ecoreSpecificRoot = path.join(repoRoot, 'examples', 'ecore_specific', '07_ecore_specific');
const persistOutputs = process.argv.includes('--persist');
const unsupportedArgs = process.argv.slice(2).filter((arg) => arg !== '--persist');
if (unsupportedArgs.includes('--help') || unsupportedArgs.includes('-h')) {
  console.log(`Usage: node scripts/verify-feature-pairs.mjs [--persist]

Options:
  --persist  Keep each Ecore and DSL editor under its feature-pair directory.`);
  process.exit(0);
}
if (unsupportedArgs.length > 0) {
  throw new Error(`Unsupported arguments: ${unsupportedArgs.join(', ')}`);
}
const eclipsePlugins = process.env.ECLIPSE_PLUGINS
  || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const javaHome = findJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin', executable('java'));
const javac = path.join(javaHome, 'bin', executable('javac'));
const workBase = path.join(repoRoot, '.cache');
mkdirSync(workBase, { recursive: true });
const workDir = mkdtempSync(path.join(workBase, 'feature-pairs-'));
const classesDir = path.join(workDir, 'classes');
const generatedRoot = path.join(workDir, 'generated');
const keepOutput = process.env.KEEP_FEATURE_PAIR_OUTPUTS === '1';
let completed = false;

try {
  const pairs = discoverPairs();
  compileProject(classesDir);
  const classpath = [classesDir, path.join(eclipsePlugins, '*')].join(path.delimiter);
  const smokeTargets = [];

  for (const pair of pairs) {
    const pairOutput = persistOutputs
      ? path.join(pair.dir, 'generated')
      : path.join(generatedRoot, pair.name);
    if (persistOutputs) rmSync(pairOutput, { recursive: true, force: true });
    const ecoreOutput = path.join(pairOutput, 'ecore');
    const dslOutput = path.join(pairOutput, 'dsl');

    runJava(classpath,
      'io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain',
      pair.ecore, ecoreOutput);
    runJava(classpath,
      'io.github.plortinus.model2blockly.standalone.Model2BlocklyToBlocklyMain',
      pair.dsl, dslOutput);

    const ecoreXmi = findIntermediateXmi(ecoreOutput);
    const dslXmi = findIntermediateXmi(dslOutput);
    runJava(classpath,
      'io.github.plortinus.model2blockly.standalone.FeaturePairVerifierMain',
      ecoreXmi, dslXmi);

    const artifactCount = compareGeneratedHtml(ecoreOutput, dslOutput);
    console.log(`[PASS] ${pair.name}: canonical EditorSpec; ${artifactCount} identical HTML/JS/JSON artifacts.`);
    smokeTargets.push(ecoreOutput, dslOutput);
  }

  const ecoreSpecificOutput = persistOutputs
    ? path.join(ecoreSpecificRoot, 'generated', 'ecore')
    : path.join(generatedRoot, '07_ecore_specific', 'ecore');
  if (persistOutputs) rmSync(ecoreSpecificOutput, { recursive: true, force: true });
  const ecoreSpecificSource = path.join(ecoreSpecificRoot, 'ecoreSpecific.ecore');
  if (!existsSync(ecoreSpecificSource)) {
    throw new Error(`Missing Ecore-only source: ${ecoreSpecificSource}`);
  }
  runJava(classpath,
    'io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain',
    ecoreSpecificSource, ecoreSpecificOutput);
  const ecoreSpecificXmi = findIntermediateXmi(ecoreSpecificOutput);
  runJava(classpath,
    'io.github.plortinus.model2blockly.standalone.EcoreSpecificVerifierMain',
    ecoreSpecificXmi);
  console.log('[PASS] 07_ecore_specific: Ecore-only capability assertions.');
  smokeTargets.push(ecoreSpecificOutput);

  const smokeArgs = [
    path.join(repoRoot, 'scripts', 'smoke-test-generated.mjs'),
    '--generic',
  ];
  if (persistOutputs) smokeArgs.push('--persist-sample-code');
  smokeArgs.push(...smokeTargets);
  execFileSync(process.execPath, smokeArgs, { cwd: repoRoot, stdio: 'inherit' });

  console.log(`\n${pairs.length}/${pairs.length} feature pairs and 1/1 Ecore-only example passed generation, comparison, capability assertions, and browser smoke tests.`);
  if (persistOutputs) {
    console.log(`Persistent paired editors written below ${pairsRoot}.`);
    console.log(`Persistent Ecore-only editor written below ${ecoreSpecificRoot}.`);
  }
  completed = true;
} finally {
  if (completed && !keepOutput) {
    rmSync(workDir, { recursive: true, force: true });
  } else {
    console.log(`Feature-pair work directory: ${workDir}`);
  }
}

function discoverPairs() {
  const candidates = readdirSync(pairsRoot)
    .filter((name) => /^\d{2}_[a-z0-9_]+$/.test(name))
    .filter((name) => statSync(path.join(pairsRoot, name)).isDirectory())
    .sort();

  const pairs = candidates.flatMap((name) => {
    const dir = path.join(pairsRoot, name);
    const files = readdirSync(dir);
    const ecoreFiles = files.filter((file) => file.endsWith('.ecore'));
    const dslFiles = files.filter((file) => file.endsWith('.m2b'));

    if (ecoreFiles.length === 0 && dslFiles.length === 0) return [];
    if (ecoreFiles.length !== 1 || dslFiles.length !== 1) {
      throw new Error(`Pair ${name} must contain exactly one .ecore and one .m2b file.`);
    }

    const ecoreBase = path.basename(ecoreFiles[0], '.ecore');
    const dslBase = path.basename(dslFiles[0], '.m2b');
    if (ecoreBase !== dslBase) {
      throw new Error(`Pair ${name} must use the same descriptive basename for .ecore and .m2b files.`);
    }

    return [{
      name,
      dir,
      ecore: path.join(dir, ecoreFiles[0]),
      dsl: path.join(dir, dslFiles[0]),
    }];
  });

  if (pairs.length !== 5) {
    throw new Error(`Expected 5 feature-pair directories, found ${pairs.length}: ${pairs.map((pair) => pair.name).join(', ')}`);
  }
  return pairs;
}

function compileProject(outputDir) {
  mkdirSync(outputDir, { recursive: true });
  const roots = ['src', 'src-gen', 'emf-gen', 'xtend-gen']
    .map((name) => path.join(projectDir, name));
  const sources = roots.flatMap((root) => collectFiles(root, (file) => file.endsWith('.java'))).sort();
  if (sources.length === 0) throw new Error('No Java sources found for feature-pair verification.');

  console.log(`Compiling ${sources.length} Java sources with ${javac} ...`);
  execFileSync(javac, [
    '-cp', path.join(eclipsePlugins, '*'),
    '-d', outputDir,
    ...sources,
  ], { cwd: repoRoot, stdio: 'inherit' });

  const generatedResourcesRoot = path.join(projectDir, 'src-gen');
  const generatedResources = collectFiles(
    generatedResourcesRoot,
    (file) => !file.endsWith('.java'));
  for (const resource of generatedResources) {
    const target = path.join(outputDir, path.relative(generatedResourcesRoot, resource));
    mkdirSync(path.dirname(target), { recursive: true });
    copyFileSync(resource, target);
  }
}

function runJava(classpath, mainClass, ...args) {
  execFileSync(java, ['-cp', classpath, mainClass, ...args], {
    cwd: repoRoot,
    stdio: 'inherit',
  });
}

function findIntermediateXmi(outputDir) {
  const intermediate = path.join(outputDir, 'intermediate');
  const files = readdirSync(intermediate).filter((name) => name.endsWith('_blocklyspec.xmi'));
  if (files.length !== 1) {
    throw new Error(`Expected one intermediate XMI in ${intermediate}, found ${files.length}.`);
  }
  return path.join(intermediate, files[0]);
}

function compareGeneratedHtml(leftOutput, rightOutput) {
  const leftRoot = path.join(leftOutput, 'html');
  const rightRoot = path.join(rightOutput, 'html');
  const leftFiles = collectFiles(leftRoot, () => true)
    .map((file) => path.relative(leftRoot, file))
    .sort();
  const rightFiles = collectFiles(rightRoot, () => true)
    .map((file) => path.relative(rightRoot, file))
    .sort();
  if (JSON.stringify(leftFiles) !== JSON.stringify(rightFiles)) {
    throw new Error(`Generated artifact sets differ:\nleft=${leftFiles.join(', ')}\nright=${rightFiles.join(', ')}`);
  }
  for (const relative of leftFiles) {
    const left = readFileSync(path.join(leftRoot, relative));
    const right = readFileSync(path.join(rightRoot, relative));
    if (!left.equals(right)) {
      throw new Error(`Generated artifact differs: ${relative}`);
    }
  }
  return leftFiles.length;
}

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
  if (process.env.JAVA_HOME) {
    const home = process.env.JAVA_HOME;
    if (existsSync(path.join(home, 'bin', executable('java')))
      && existsSync(path.join(home, 'bin', executable('javac')))) return home;
    throw new Error(`JAVA_HOME does not contain java and javac: ${home}`);
  }
  if (!existsSync(pluginsDir)) {
    throw new Error(`Eclipse plugins directory not found: ${pluginsDir}`);
  }
  const candidates = readdirSync(pluginsDir)
    .filter((name) => name.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .reverse();
  for (const candidate of candidates) {
    const home = path.join(pluginsDir, candidate, 'jre');
    if (existsSync(path.join(home, 'bin', executable('java')))
      && existsSync(path.join(home, 'bin', executable('javac')))) return home;
  }
  throw new Error('No Java development runtime found. Set JAVA_HOME to a JDK 21 installation.');
}

function executable(name) {
  return process.platform === 'win32' ? `${name}.exe` : name;
}
