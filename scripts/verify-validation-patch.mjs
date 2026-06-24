#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { copyFileSync, existsSync, mkdirSync, readdirSync, readFileSync, rmSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const projectDir = path.join(repoRoot, 'io.github.plortinus.model2blockly');
const coreUpdateSiteJar = path.join(
  repoRoot,
  'io.github.plortinus.model2blockly.updatesite',
  'repository',
  'plugins',
  'io.github.plortinus.model2blockly_1.0.7.qualifier.jar',
);
const eclipsePlugins = process.env.ECLIPSE_PLUGINS || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const javaHome = process.env.JAVA_HOME || findBundledJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin/java');
const javac = path.join(javaHome, 'bin/javac');
const classpath = `bin:${coreUpdateSiteJar}:${eclipsePlugins}/*`;
const tmpRoot = '/tmp/model2blockly-validation-patch';
const failures = [];

try {
  checkRuntime();
  compilePatchClasses();
  verifyDryRun();
  verifyApplyAndRegenerate();
} catch (error) {
  fail(String(error.stderr || error.message || error));
}

if (failures.length) {
  for (const failure of failures) console.error(`[FAIL] ${failure}`);
  console.error(`\n${failures.length} validation patch verification check(s) failed.`);
  process.exit(1);
}

console.log('[PASS] Validation patch CLI dry-run, apply, and regeneration checks passed.');

function checkRuntime() {
  assertExists(eclipsePlugins, 'Eclipse plugins directory exists');
  assertExists(coreUpdateSiteJar, 'Core update-site jar exists');
  assertExists(java, 'Java runtime exists');
  assertExists(javac, 'Javac runtime exists');
}

function findBundledJavaHome(pluginsDir) {
  if (!existsSync(pluginsDir)) return '';
  const candidates = readdirSync(pluginsDir)
    .filter((entry) => entry.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .reverse()
    .map((entry) => path.join(pluginsDir, entry, 'jre'));
  return candidates.find((candidate) => existsSync(path.join(candidate, 'bin/java'))
    && existsSync(path.join(candidate, 'bin/javac'))) || '';
}

function compilePatchClasses() {
  const sources = [
    ...listJavaFiles(path.join(projectDir, 'src')),
    ...listJavaFiles(path.join(projectDir, 'src-gen')),
    ...listJavaFiles(path.join(projectDir, 'xtend-gen')),
  ].map((file) => path.relative(projectDir, file));
  run(javac, [
    '-cp',
    classpath,
    '-d',
    'bin',
    ...sources,
  ]);
  copyRuntimeResources(path.join(projectDir, 'src'));
  copyRuntimeResources(path.join(projectDir, 'src-gen'));
  copyRuntimeResources(path.join(projectDir, 'xtend-gen'));
}

function listJavaFiles(dir) {
  if (!existsSync(dir)) return [];
  const files = [];
  for (const entry of readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) files.push(...listJavaFiles(full));
    else if (entry.isFile() && entry.name.endsWith('.java')) files.push(full);
  }
  return files;
}

function copyRuntimeResources(sourceDir, rootDir = sourceDir) {
  if (!existsSync(sourceDir)) return;
  for (const entry of readdirSync(sourceDir, { withFileTypes: true })) {
    const full = path.join(sourceDir, entry.name);
    if (entry.isDirectory()) {
      copyRuntimeResources(full, rootDir);
    } else if (entry.isFile() && !entry.name.endsWith('.java')) {
      const destination = path.join(projectDir, 'bin', path.relative(rootDir, full));
      mkdirSync(path.dirname(destination), { recursive: true });
      copyFileSync(full, destination);
    }
  }
}

function verifyDryRun() {
  const ecore = run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    'model/app_maker.ecore',
    '--validation',
    'examples/generated/app_maker_ecore/html/validation_blocks.json',
    '--dry-run',
  ]);
  assertIncludes(ecore, 'Validation patch dry-run', 'Ecore dry-run prints mode');
  assertIncludes(ecore, 'Rules read: 70', 'Ecore dry-run reads generated rules');
  assertIncludes(ecore, 'Warnings: 0', 'Ecore dry-run has no warnings');

  const dsl = run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    'examples/app_maker.model2blockly',
    '--validation',
    'examples/generated/app_maker/html/validation_blocks.json',
    '--dry-run',
  ]);
  assertIncludes(dsl, 'Validation patch dry-run', 'DSL dry-run prints mode');
  assertIncludes(dsl, 'Rules read: 102', 'DSL dry-run reads generated rules');
  assertIncludes(dsl, 'Changes: 0', 'DSL dry-run treats matching source as no-op changes');
  assertIncludes(dsl, 'Warnings: 0', 'DSL dry-run has no warnings');
  assertIncludes(dsl, 'Notes: 102', 'DSL dry-run reports matching rules as notes');
}

function verifyApplyAndRegenerate() {
  rmSync(tmpRoot, { recursive: true, force: true });
  const patchedEcore = path.join(tmpRoot, 'app_maker_patched.ecore');
  const patchedDsl = path.join(tmpRoot, 'app_maker_patched.model2blockly');
  const ecoreGen = path.join(tmpRoot, 'app_maker_patched_ecore_gen');
  const dslGen = path.join(tmpRoot, 'app_maker_patched_dsl_gen');

  run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    'model/app_maker.ecore',
    '--validation',
    'examples/generated/app_maker_ecore/html/validation_blocks.json',
    '--apply',
    '--out',
    patchedEcore,
  ]);
  run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    'examples/app_maker.model2blockly',
    '--validation',
    'examples/generated/app_maker/html/validation_blocks.json',
    '--apply',
    '--out',
    patchedDsl,
  ]);

  const ecoreText = readFileSync(patchedEcore, 'utf8');
  assertIncludes(ecoreText, 'source="validation"', 'Patched Ecore contains validation annotation');
  assertIncludes(ecoreText, 'key="mustFollow" value="Alert"', 'Patched Ecore contains mustFollow detail');
  assertIncludes(ecoreText, 'iD="true"', 'Patched Ecore contains ID uniqueness write-back');

  const dslText = readFileSync(patchedDsl, 'utf8');
  assertIncludes(
    dslText,
    'constraint navigate_after_alert on Navigate : must follow Alert',
    'Patched DSL contains must-follow constraint',
  );

  const patchedEcoreDryRun = run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    patchedEcore,
    '--validation',
    'examples/generated/app_maker_ecore/html/validation_blocks.json',
    '--dry-run',
  ]);
  assertIncludes(patchedEcoreDryRun, 'Changes: 0', 'Patched Ecore dry-run is idempotent');
  assertIncludes(patchedEcoreDryRun, 'Warnings: 0', 'Patched Ecore idempotent dry-run has no warnings');

  const patchedDslDryRun = run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.ValidationPatchMain',
    '--source',
    patchedDsl,
    '--validation',
    'examples/generated/app_maker/html/validation_blocks.json',
    '--dry-run',
  ]);
  assertIncludes(patchedDslDryRun, 'Changes: 0', 'Patched DSL dry-run is idempotent');
  assertIncludes(patchedDslDryRun, 'Warnings: 0', 'Patched DSL idempotent dry-run has no warnings');

  run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain',
    patchedEcore,
    ecoreGen,
  ]);
  run(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.Model2BlocklyToBlocklyMain',
    patchedDsl,
    dslGen,
  ]);

  assertExists(path.join(ecoreGen, 'html/validation_blocks.json'), 'Patched Ecore regenerates validation blocks');
  assertExists(path.join(dslGen, 'html/validation_blocks.json'), 'Patched DSL regenerates validation blocks');
}

function run(command, args) {
  return execFileSync(command, args, {
    cwd: projectDir,
    encoding: 'utf8',
    stdio: ['ignore', 'pipe', 'pipe'],
  });
}

function assertExists(file, message) {
  if (!existsSync(file)) fail(`${message}: ${file}`);
}

function assertIncludes(text, needle, message) {
  if (!text.includes(needle)) fail(`${message}; missing ${JSON.stringify(needle)}`);
}

function fail(message) {
  failures.push(message);
}
