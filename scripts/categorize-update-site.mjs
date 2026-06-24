#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { existsSync, readdirSync } from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import { fileURLToPath, pathToFileURL } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repositoryDir = path.join(repoRoot, 'io.github.plortinus.model2blockly.updatesite', 'repository');
const categoryXml = path.join(repoRoot, 'io.github.plortinus.model2blockly.updatesite', 'category.xml');
const eclipse = process.env.ECLIPSE || defaultEclipseExecutable();

assertExists(eclipse, 'Eclipse executable');
assertExists(repositoryDir, 'p2 repository directory');
assertExists(categoryXml, 'category.xml');

runEclipseApplication('org.eclipse.equinox.p2.publisher.CategoryPublisher', [
  '-metadataRepository',
  pathToFileURL(repositoryDir).href,
  '-categoryDefinition',
  pathToFileURL(categoryXml).href,
  '-compress',
]);

console.log('[update-site] Applied Model2Blockly category metadata.');

function defaultEclipseExecutable() {
  if (process.platform === 'darwin') {
    return '/Applications/Eclipse.app/Contents/MacOS/eclipse';
  }
  return 'eclipse';
}

function runEclipseApplication(application, args) {
  try {
    execFileSync(eclipse, ['-nosplash', '-application', application, ...args], { stdio: 'inherit' });
    return;
  } catch (error) {
    const { java, launcher } = eclipseLauncherFallback();
    console.warn(`[update-site] Eclipse executable failed; retrying through Equinox launcher: ${String(error.signal || error.status || error.message)}`);
    execFileSync(java, [
      '-jar',
      launcher,
      '-nosplash',
      '-configuration',
      path.join(os.tmpdir(), 'model2blockly-eclipse-config'),
      '-data',
      path.join(os.tmpdir(), 'model2blockly-eclipse-workspace'),
      '-application',
      application,
      ...args,
    ], { stdio: 'inherit' });
  }
}

function eclipseLauncherFallback() {
  const eclipseHome = process.env.ECLIPSE_HOME || '/Applications/Eclipse.app/Contents/Eclipse';
  const pluginsDir = path.join(eclipseHome, 'plugins');
  assertExists(pluginsDir, 'Eclipse plugins directory');
  const launcher = readdirSync(pluginsDir)
    .filter((name) => name.startsWith('org.eclipse.equinox.launcher_') && name.endsWith('.jar'))
    .sort()
    .at(-1);
  if (!launcher) throw new Error(`Equinox launcher not found under ${pluginsDir}`);
  const javaHome = readdirSync(pluginsDir)
    .filter((name) => name.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.') && !name.endsWith('.jar'))
    .sort()
    .at(-1);
  if (!javaHome) throw new Error(`Eclipse JustJ Java runtime not found under ${pluginsDir}`);
  return {
    java: path.join(pluginsDir, javaHome, 'jre', 'bin', 'java'),
    launcher: path.join(pluginsDir, launcher),
  };
}

function assertExists(file, label) {
  if (!existsSync(file)) {
    throw new Error(`${label} not found: ${file}`);
  }
}
