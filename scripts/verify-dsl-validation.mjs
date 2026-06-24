#!/usr/bin/env node

import { spawnSync } from 'node:child_process';
import { existsSync, mkdtempSync, readdirSync, rmSync, writeFileSync } from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const eclipsePlugins = process.env.ECLIPSE_PLUGINS || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const javaHome = process.env.JAVA_HOME || findEclipseJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin', 'java');
const javac = path.join(javaHome, 'bin', 'javac');
const coreDir = path.join(repoRoot, 'io.github.plortinus.model2blockly');
const classpath = [
  path.join(coreDir, 'bin'),
  path.join(coreDir, 'src-gen'),
  path.join(coreDir, 'xtend-gen'),
  path.join(eclipsePlugins, '*'),
].join(path.delimiter);

checkRuntime();
compileCore();

const cases = [
  {
    name: 'value input shadow is not an output block',
    source: `
domain BrokenShadow
category Main colour 120
output class Expr category Main {
}
class Statement category Main {
  attribute name : string default "statement"
}
class Host category Main {
  value Expr expr shadow Statement
}
`,
    expected: [
      'Invalid .model2blockly model',
      "Shadow block type 'Statement' is not an output block.",
      '[block[Host].expr]',
      'Use an output class for value-input shadow blocks.',
    ],
  },
  {
    name: 'duplicate feature/input names',
    source: `
domain DuplicateFeature
category Main colour 120
class Card category Main {
  attribute title : string default "Title"
  contains Child title [0..1]
}
class Child category Main {
  attribute name : string default "child"
}
`,
    expected: [
      'Invalid .model2blockly model',
      "Duplicate feature/input name 'title'.",
      '[block[Card].title]',
      'Hint: Rename one of the duplicate elements',
    ],
  },
  {
    name: 'reference label field missing on target',
    source: `
domain BrokenReferenceLabel
category Main colour 120
class User category Main {
  attribute name : string default "Ada"
}
class Task category Main {
  reference User assignee
    widget reference-select referenceLabelField displayName
}
`,
    expected: [
      'Invalid .model2blockly model',
      "Reference label field 'displayName' does not exist on target type 'User'.",
      '[block[Task].assignee]',
      'Check the referenced class, feature, input or category name',
    ],
  },
  {
    name: 'unsupported OCL is rejected',
    source: `
domain UnsupportedOcl
category Main colour 120
class Item category Main {
  attribute name : string default "item"
}
validation badOcl on Item : ocl "self.name->exists(x | x = 'item')" errorMessage "Should not disappear."
`,
    expected: [
      'Invalid .model2blockly model',
      'Unsupported OCL validation expression.',
      'Supported subset: self.<feature>, size(), notEmpty(), isEmpty(), oclIsUndefined(), comparisons, and/or/not.',
    ],
  },
];

for (const testCase of cases) {
  verifyInvalidDsl(testCase);
}
verifyOutputBlockWithStatementInputIsAllowed();
verifyValidAppMaker();

console.log(`[PASS] DSL validation bridge regression checks passed (${cases.length} invalid cases + output statement-input valid case + app_maker valid case).`);

function verifyInvalidDsl(testCase) {
  const tempDir = mkdtempSync(path.join(os.tmpdir(), 'model2blockly-validation-'));
  try {
    const sourceFile = path.join(tempDir, 'invalid.model2blockly');
    writeFileSync(sourceFile, testCase.source.trimStart(), 'utf8');
    const result = runStandalone(sourceFile, path.join(tempDir, 'out'));
    const output = result.output;
    if (result.status !== 3) {
      fail(`${testCase.name}: expected exit code 3, got ${result.status}\n${output}`);
    }
    for (const expected of testCase.expected) {
      assertIncludes(output, expected, `${testCase.name}: diagnostic includes ${expected}`);
    }
    assertNotIncludes(output, 'Transformed to EMF EditorSpec',
      `${testCase.name}: invalid DSL should be rejected before generation starts`);
    assertNotIncludes(output, 'java.lang.',
      `${testCase.name}: diagnostic should not expose Java stack traces`);
  } finally {
    rmSync(tempDir, { recursive: true, force: true });
  }
}

function verifyValidAppMaker() {
  const tempDir = mkdtempSync(path.join(os.tmpdir(), 'model2blockly-validation-valid-'));
  try {
    const sourceFile = path.join(coreDir, 'examples', 'app_maker.model2blockly');
    const result = runStandalone(sourceFile, path.join(tempDir, 'out'));
    if (result.status !== 0) {
      fail(`app_maker.model2blockly should validate and generate successfully\n${result.output}`);
    }
    assertIncludes(result.output, 'Generation complete.', 'app_maker generation completes');
    assertNotIncludes(result.output, 'Invalid .model2blockly model', 'app_maker has no DSL validation bridge errors');
  } finally {
    rmSync(tempDir, { recursive: true, force: true });
  }
}

function verifyOutputBlockWithStatementInputIsAllowed() {
  const tempDir = mkdtempSync(path.join(os.tmpdir(), 'model2blockly-output-statement-'));
  try {
    const sourceFile = path.join(tempDir, 'output_statement.model2blockly');
    writeFileSync(sourceFile, `
domain OutputStatement
category Main colour 120
output class ValueWithBody category Main {
  attribute name : string default "value"
  contains Statement body [0..3]
}
class Statement category Main {
  attribute name : string default "statement"
}
`.trimStart(), 'utf8');
    const result = runStandalone(sourceFile, path.join(tempDir, 'out'));
    if (result.status !== 0) {
      fail(`output block with statement input should be accepted by Blockly-compatible validation\n${result.output}`);
    }
    assertIncludes(result.output, 'Generation complete.', 'output block with statement input generation completes');
    assertNotIncludes(result.output, 'Output blocks cannot have statement inputs', 'output block with statement input is not rejected');
  } finally {
    rmSync(tempDir, { recursive: true, force: true });
  }
}

function runStandalone(sourceFile, outputDir) {
  const result = spawnSync(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.Model2BlocklyToBlocklyMain',
    sourceFile,
    outputDir,
  ], {
    cwd: repoRoot,
    encoding: 'utf8',
  });
  return {
    status: result.status ?? 1,
    output: `${result.stdout || ''}${result.stderr || ''}`,
  };
}

function compileCore() {
  const sources = [
    ...javaFiles(path.join(coreDir, 'src')),
    ...javaFiles(path.join(coreDir, 'src-gen')),
    ...javaFiles(path.join(coreDir, 'xtend-gen')),
  ];
  const result = spawnSync(javac, [
    '-cp',
    classpath,
    '-d',
    path.join(coreDir, 'bin'),
    ...sources,
  ], {
    cwd: coreDir,
    encoding: 'utf8',
  });
  if (result.status !== 0) {
    fail(`Could not compile core Java sources.\n${result.stdout || ''}${result.stderr || ''}`);
  }
}

function javaFiles(dir) {
  if (!existsSync(dir)) return [];
  const entries = readdirSync(dir, { withFileTypes: true });
  const files = [];
  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) files.push(...javaFiles(fullPath));
    else if (entry.isFile() && entry.name.endsWith('.java')) files.push(fullPath);
  }
  return files;
}

function checkRuntime() {
  if (!existsSync(eclipsePlugins)) {
    fail(`Eclipse plugin directory not found: ${eclipsePlugins}`);
  }
  if (!existsSync(java) || !existsSync(javac)) {
    fail(`Java 21 runtime is incomplete: ${javaHome}`);
  }
}

function findEclipseJavaHome(pluginDir) {
  if (!existsSync(pluginDir)) {
    fail(`Eclipse plugin directory not found: ${pluginDir}`);
  }
  const justj = readdirSync(pluginDir)
    .filter((name) => name.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .at(-1);
  if (!justj) {
    fail(`Could not find Eclipse-bundled Java runtime under ${pluginDir}`);
  }
  const home = path.join(pluginDir, justj, 'jre');
  if (!existsSync(path.join(home, 'bin', 'java')) || !existsSync(path.join(home, 'bin', 'javac'))) {
    fail(`Eclipse-bundled Java runtime is incomplete: ${home}`);
  }
  return home;
}

function assertIncludes(text, expected, label) {
  if (!text.includes(expected)) fail(`${label}\nMissing: ${expected}\n\nOutput:\n${text}`);
}

function assertNotIncludes(text, forbidden, label) {
  if (text.includes(forbidden)) fail(`${label}\nForbidden: ${forbidden}\n\nOutput:\n${text}`);
}

function fail(message) {
  console.error(`[FAIL] ${message}`);
  process.exit(1);
}
