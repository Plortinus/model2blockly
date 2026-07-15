#!/usr/bin/env node

import assert from 'node:assert/strict';
import { createHash } from 'node:crypto';
import {
  createReadStream,
  existsSync,
  mkdirSync,
  readFileSync,
  readdirSync,
  statSync,
  writeFileSync,
} from 'node:fs';
import { stat } from 'node:fs/promises';
import { createServer } from 'node:http';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import Ajv2020 from 'ajv/dist/2020.js';
import { chromium } from 'playwright';

const evaluationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repoRoot = path.resolve(evaluationRoot, '../..');
const caseId = process.argv[2];
const draftMode = process.argv.includes('--draft');
if (!caseId || !/^E(?:0[1-9]|10)_[a-z0-9_]+$/.test(caseId)) {
  console.error('Usage: node verify-case.mjs <E01_case_id>');
  process.exit(1);
}

const caseDir = path.join(evaluationRoot, 'cases', caseId);
const resultsDir = path.join(caseDir, 'results');
const screenshotsDir = path.join(resultsDir, 'screenshots');
mkdirSync(screenshotsDir, { recursive: true });

const ajv = new Ajv2020({ allErrors: true, strict: true, allowUnionTypes: true });
const validateDescriptor = ajv.compile(readJson(path.join(evaluationRoot, 'schema', 'editor-descriptor.schema.json')));
const validateExtraction = ajv.compile(readJson(path.join(evaluationRoot, 'schema', 'baseline-extraction.schema.json')));
const validateAssessment = ajv.compile(readJson(path.join(evaluationRoot, 'schema', 'case-assessment.schema.json')));
const validateComparison = ajv.compile(readJson(path.join(evaluationRoot, 'schema', 'comparison.schema.json')));
const validateRouteComparison = ajv.compile(readJson(path.join(evaluationRoot, 'schema', 'route-comparison.schema.json')));
const extraction = readJson(path.join(caseDir, 'baseline', 'baseline-extraction.json'));
const assessment = readJson(path.join(caseDir, 'assessment.json'));
assertSchema(validateExtraction, extraction, 'baseline extraction');
assertSchema(validateAssessment, assessment, 'case assessment');
assert.equal(assessment.caseId, caseId, 'Assessment caseId does not match the selected case.');
verifyFragmentIntegrity(extraction);

const server = await startServer(repoRoot);
const baseUrl = `http://127.0.0.1:${server.address().port}`;
const browser = await chromium.launch({ headless: true });

try {
  const baseline = await loadTreatment(browser, baseUrl, 'baseline');
  const m2b = await loadTreatment(browser, baseUrl, 'm2b');
  const hasEcoreTreatment = existsSync(path.join(caseDir, 'ecore', 'adapter.mjs'));
  const ecore = hasEcoreTreatment ? await loadTreatment(browser, baseUrl, 'ecore') : null;
  assertSchema(validateDescriptor, baseline, 'baseline descriptor');
  assertSchema(validateDescriptor, m2b, 'm2b descriptor');
  assert.deepEqual(baseline.errors, [], 'Baseline contains block loading errors.');
  assert.deepEqual(m2b.controls, baseline.controls, 'Treatments do not use identical controls.');

  let routeComparison = null;
  if (ecore) {
    assertSchema(validateDescriptor, ecore, 'ecore descriptor');
    assert.deepEqual(ecore.errors, [], 'Ecore treatment contains block loading errors.');
    assert.deepEqual(ecore.controls, baseline.controls, 'Ecore treatment does not use the controlled configuration.');
    assert.deepEqual(
      comparableRouteDescriptor(ecore),
      comparableRouteDescriptor(m2b),
      'Ecore and .m2b routes do not produce the same canonical editor descriptor.',
    );
    routeComparison = {
      schemaVersion: 1,
      caseId,
      referenceTreatment: 'm2b',
      candidateTreatment: 'ecore',
      equivalent: true,
      comparedSections: [
        'controls',
        'blocks',
        'toolbox',
        'initialWorkspace',
        'generators',
        'errors',
        'loadability',
      ],
    };
    assertSchema(validateRouteComparison, routeComparison, 'route comparison');
  }

  const comparison = compareDescriptors(baseline, m2b, assessment, !draftMode);
  if (draftMode) {
    console.log(JSON.stringify({
      caseId,
      editor: comparison.editor.properties.filter((item) => item.status !== 'match'),
      generators: comparison.generators.properties.filter((item) => item.status !== 'match'),
    }, null, 2));
    console.log('DRAFT ONLY: no result files were written.');
    process.exitCode = 2;
  } else {
    assertSchema(validateComparison, comparison, 'comparison result');
    const metrics = collectMetrics(extraction, baseline.blocks.length);
    writeJson(path.join(resultsDir, 'baseline-descriptor.json'), baseline);
    writeJson(path.join(resultsDir, 'm2b-descriptor.json'), m2b);
    writeJson(path.join(resultsDir, 'comparison.json'), comparison);
    writeJson(path.join(resultsDir, 'metrics.json'), metrics);
    if (ecore) {
      writeJson(path.join(resultsDir, 'ecore-descriptor.json'), ecore);
      writeJson(path.join(resultsDir, 'route-comparison.json'), routeComparison);
    }

    console.log(`PASS ${caseId} evaluation execution`);
    console.log(`  editor parity: ${comparison.editor.summary.matches}/${comparison.editor.summary.applicable} (${comparison.editor.summary.parityPercent}%)`);
    console.log(`  classification: ${comparison.editor.summary.classification}`);
    console.log(`  generator properties: ${comparison.generators.summary.matches}/${comparison.generators.summary.applicable}`);
    console.log(`  editor status counts: partial ${comparison.editor.summary.partial}, mismatch ${comparison.editor.summary.mismatches}, unsupported ${comparison.editor.summary.unsupported}, errors ${comparison.editor.summary.errors}`);
    console.log(`  generator exclusions: ${comparison.generators.summary.excluded}`);
    console.log(`  maintained LOC: baseline ${metrics.baseline.total.nonBlankNonComment}, .m2b ${metrics.m2b.nonBlankNonComment}`);
    console.log(`  LOC reduction: ${metrics.comparison.locReductionPercent}%`);
    console.log(`  documented differences: ${comparison.editor.properties.filter((item) => item.status !== 'match').length}`);
    if (routeComparison) console.log('  input-route equivalence: Ecore = .m2b');
  }
} finally {
  await browser.close();
  await new Promise((resolve) => server.close(resolve));
}

function comparableRouteDescriptor(descriptor) {
  return {
    controls: descriptor.controls,
    blocks: descriptor.blocks,
    toolbox: descriptor.toolbox,
    initialWorkspace: descriptor.initialWorkspace,
    generators: descriptor.generators,
    errors: descriptor.errors,
  };
}

async function loadTreatment(browser, baseUrl, treatment) {
  const page = await browser.newPage({ viewport: { width: 1280, height: 800 } });
  const browserErrors = [];
  page.on('pageerror', (error) => browserErrors.push(error.message));
  try {
    const adapter = `/evaluation/official-blockly/cases/${caseId}/${treatment}/adapter.mjs`;
    const url = `${baseUrl}/evaluation/official-blockly/harness/index.html?adapter=${encodeURIComponent(adapter)}`;
    await page.goto(url, { waitUntil: 'load', timeout: 30000 });
    await page.waitForFunction(
      () => ['ready', 'error'].includes(window.__M2B_EVALUATION__?.status),
      null,
      { timeout: 30000 },
    );
    const result = await page.evaluate(() => window.__M2B_EVALUATION__);
    assert.equal(result.status, 'ready', result.error || `${treatment} harness did not become ready.`);
    assert.deepEqual(browserErrors, [], `${treatment} emitted browser errors.`);
    await page.screenshot({
      path: path.join(screenshotsDir, `${treatment}.png`),
      fullPage: false,
    });
    return result.descriptor;
  } finally {
    await page.close();
  }
}

function compareDescriptors(baseline, actual, caseAssessment, classifyDifferences) {
  const editorProperties = [];
  const generatorProperties = [];
  const recordEditor = recorder(editorProperties);
  const recordGenerator = recorder(generatorProperties);

  const actualBlocks = new Map(actual.blocks.map((block) => [block.type, block]));
  recordEditor('blocks.noUnexpectedTypes', [], actual.blocks
    .map((block) => block.type)
    .filter((type) => !baseline.blocks.some((block) => block.type === type)));

  for (const expectedBlock of baseline.blocks) {
    const blockPath = `blocks.${expectedBlock.type}`;
    const actualBlock = actualBlocks.get(expectedBlock.type);
    if (!actualBlock && actual.errors.length) {
      editorProperties.push({
        property: `${blockPath}.registered`,
        status: 'error',
        expected: true,
        actual: false,
        rationale: actual.errors.join('\n'),
      });
    } else {
      recordEditor(`${blockPath}.registered`, true, Boolean(actualBlock));
    }
    if (!actualBlock) continue;
    recordEditor(`${blockPath}.inputOrder`, expectedBlock.inputs.map(inputIdentity), actualBlock.inputs.map(inputIdentity));
    recordEditor(`${blockPath}.connections.previous`, expectedBlock.connections.previous, actualBlock.connections.previous);
    recordEditor(`${blockPath}.connections.next`, expectedBlock.connections.next, actualBlock.connections.next);
    recordEditor(`${blockPath}.connections.output`, expectedBlock.connections.output, actualBlock.connections.output);
    recordEditor(`${blockPath}.inputsInline`, expectedBlock.inputsInline, actualBlock.inputsInline);
    recordEditor(`${blockPath}.colour`, expectedBlock.colour, actualBlock.colour);
    recordEditor(`${blockPath}.tooltip`, expectedBlock.tooltip, actualBlock.tooltip);
    recordEditor(`${blockPath}.helpUrl`, expectedBlock.helpUrl, actualBlock.helpUrl);
    recordEditor(`${blockPath}.dynamicBehaviour`, expectedBlock.dynamicBehaviour, actualBlock.dynamicBehaviour);

    const pairedInputs = pairInputs(expectedBlock.inputs, actualBlock.inputs);
    for (let inputIndex = 0; inputIndex < expectedBlock.inputs.length; inputIndex += 1) {
      const expectedInput = expectedBlock.inputs[inputIndex];
      const inputPath = `${blockPath}.inputs.${expectedInput.kind}:${expectedInput.name || '<anonymous>'}`;
      const actualInput = pairedInputs[inputIndex];
      recordEditor(`${inputPath}.present`, true, Boolean(actualInput));
      if (!actualInput) continue;
      recordEditor(`${inputPath}.check`, expectedInput.check, actualInput.check);
      recordEditor(`${inputPath}.alignment`, expectedInput.alignment, actualInput.alignment);
      recordEditor(`${inputPath}.fieldOrder`, expectedInput.fields.map(fieldIdentity), actualInput.fields.map(fieldIdentity));
      const pairedFields = pairFields(expectedInput.fields, actualInput.fields);
      for (let index = 0; index < expectedInput.fields.length; index += 1) {
        const expectedField = expectedInput.fields[index];
        const actualField = pairedFields[index];
        const fieldPath = `${inputPath}.fields.${index}`;
        recordEditor(`${fieldPath}.present`, true, Boolean(actualField));
        if (!actualField) continue;
        for (const key of ['name', 'kind', 'value', 'text', 'options']) {
          recordEditor(`${fieldPath}.${key}`, expectedField[key], actualField[key]);
        }
        if (expectedField.constraints !== null) {
          recordEditor(`${fieldPath}.constraints.present`, true, actualField.constraints !== null);
        }
        if (expectedField.constraints !== null && actualField.constraints !== null) {
          for (const key of ['min', 'max', 'precision']) {
            recordEditor(`${fieldPath}.constraints.${key}`, expectedField.constraints[key], actualField.constraints[key]);
          }
        }
      }
    }
  }

  compareToolboxes(baseline.toolbox, actual.toolbox, recordEditor);
  recordEditor('initialWorkspace.present', baseline.initialWorkspace !== null, actual.initialWorkspace !== null);
  if (baseline.initialWorkspace !== null && actual.initialWorkspace !== null) {
    recordEditor('initialWorkspace.configuration', baseline.initialWorkspace, actual.initialWorkspace);
  }

  const actualGenerators = new Map(actual.generators.map((generator) => [generator.type, generator]));
  for (const expectedGenerator of baseline.generators) {
    const generator = actualGenerators.get(expectedGenerator.type);
    const generatorPath = `generators.${expectedGenerator.type}`;
    recordGenerator(`${generatorPath}.registered`, expectedGenerator.registered, generator?.registered ?? false);
    recordGenerator(`${generatorPath}.kind`, expectedGenerator.metadata?.kind ?? null, generator?.metadata?.kind ?? null);
    recordGenerator(`${generatorPath}.template`, expectedGenerator.metadata?.template ?? null, generator?.metadata?.template ?? null);
    recordGenerator(
      `${generatorPath}.sideEffects`,
      expectedGenerator.metadata?.sideEffects ?? [],
      generator?.metadata?.sideEffects ?? [],
    );
  }

  if (classifyDifferences) {
    applyAssessment(editorProperties, caseAssessment.rules, 'editor');
    applyAssessment(generatorProperties, caseAssessment.rules, 'generators');
  }
  const editorSummary = summarize(editorProperties);
  const generatorSummary = summarize(generatorProperties);
  return {
    schemaVersion: 2,
    caseId,
    baselineTreatment: 'baseline',
    actualTreatment: 'm2b',
    editor: {
      summary: {
        ...editorSummary,
        classification: actual.errors.length
          ? 'not-reproducible'
          : (editorSummary.matches === editorSummary.applicable ? 'complete' : 'partial'),
      },
      loadErrors: actual.errors,
      properties: editorProperties,
    },
    generators: {
      scope: 'Presence and domain-code template; execution engine excluded.',
      summary: generatorSummary,
      properties: generatorProperties,
    },
  };
}

function compareToolboxes(expectedToolbox, actualToolbox, record) {
  record('toolbox.present', expectedToolbox !== null, actualToolbox !== null);
  if (!expectedToolbox || !actualToolbox) return;
  record('toolbox.kind', expectedToolbox.kind ?? null, actualToolbox.kind ?? null);
  const expectedContents = expectedToolbox.contents ?? [];
  const actualContents = actualToolbox.contents ?? [];
  const expectedCategories = expectedContents.filter((item) => item.kind === 'category');
  const actualCategories = actualContents.filter((item) => item.kind === 'category');
  if (expectedCategories.length === 0 && actualCategories.length === 0) {
    compareFlyoutEntries(expectedContents, actualContents, record);
    return;
  }
  record('toolbox.categoryOrder', expectedCategories.map((item) => item.name), actualCategories.map((item) => item.name));
  record(
    'toolbox.nonCategoryItems',
    expectedContents.filter((item) => item.kind !== 'category'),
    actualContents.filter((item) => item.kind !== 'category'),
  );

  const unused = new Set(actualCategories.map((_, index) => index));
  const selectedIndexes = new Set();
  for (const expectedCategory of expectedCategories) {
    const candidates = [...unused]
      .filter((index) => actualCategories[index].name === expectedCategory.name)
      .sort((left, right) => overlapScore(expectedCategory, actualCategories[right])
        - overlapScore(expectedCategory, actualCategories[left]));
    const index = candidates[0];
    const actualCategory = index === undefined ? null : actualCategories[index];
    if (index !== undefined) {
      unused.delete(index);
      selectedIndexes.add(index);
    }
    const categoryPath = `toolbox.categories.${expectedCategory.name}`;
    record(`${categoryPath}.present`, true, Boolean(actualCategory));
    if (!actualCategory) {
      for (const expectedBlock of (expectedCategory.contents ?? []).filter((item) => item.kind === 'block')) {
        record(`${categoryPath}.blocks.${expectedBlock.type}.present`, true, false);
      }
      continue;
    }
    record(`${categoryPath}.colour`, expectedCategory.colour ?? null, actualCategory.colour ?? null);
    record(`${categoryPath}.custom`, expectedCategory.custom ?? null, actualCategory.custom ?? null);

    const expectedBlocks = (expectedCategory.contents ?? []).filter((item) => item.kind === 'block');
    const actualBlocks = (actualCategory.contents ?? []).filter((item) => item.kind === 'block');
    const usedActualBlocks = new Set();
    for (const expectedBlock of expectedBlocks) {
      const actualIndex = actualBlocks.findIndex((item, candidateIndex) =>
        !usedActualBlocks.has(candidateIndex) && item.type === expectedBlock.type);
      const actualBlock = actualIndex >= 0 ? actualBlocks[actualIndex] : null;
      if (actualIndex >= 0) usedActualBlocks.add(actualIndex);
      const blockPath = `${categoryPath}.blocks.${expectedBlock.type}`;
      record(`${blockPath}.present`, true, Boolean(actualBlock));
      if (actualBlock) record(`${blockPath}.configuration`, toolboxEntryConfig(expectedBlock), toolboxEntryConfig(actualBlock));
    }
    record(
      `${categoryPath}.unexpectedBlocks`,
      [],
      actualBlocks.filter((_, candidateIndex) => !usedActualBlocks.has(candidateIndex)).map((item) => item.type),
    );
  }

  record(
    'toolbox.unexpectedCategories',
    [],
    actualCategories
      .filter((_, index) => !selectedIndexes.has(index))
      .map((category) => category.name),
  );
}

function compareFlyoutEntries(expectedContents, actualContents, record) {
  const expectedBlocks = expectedContents.filter((item) => item.kind === 'block');
  const actualBlocks = actualContents.filter((item) => item.kind === 'block');
  record('toolbox.blockOrder', expectedBlocks.map((item) => item.type), actualBlocks.map((item) => item.type));
  record(
    'toolbox.nonBlockItems',
    expectedContents.filter((item) => item.kind !== 'block'),
    actualContents.filter((item) => item.kind !== 'block'),
  );
  const used = new Set();
  const occurrences = new Map();
  for (const expectedBlock of expectedBlocks) {
    const occurrence = (occurrences.get(expectedBlock.type) || 0) + 1;
    occurrences.set(expectedBlock.type, occurrence);
    const actualIndex = actualBlocks.findIndex((item, index) => !used.has(index) && item.type === expectedBlock.type);
    const actualBlock = actualIndex >= 0 ? actualBlocks[actualIndex] : null;
    if (actualIndex >= 0) used.add(actualIndex);
    const blockPath = `toolbox.blocks.${expectedBlock.type}#${occurrence}`;
    record(`${blockPath}.present`, true, Boolean(actualBlock));
    if (actualBlock) record(`${blockPath}.configuration`, toolboxEntryConfig(expectedBlock), toolboxEntryConfig(actualBlock));
  }
  record(
    'toolbox.unexpectedBlocks',
    [],
    actualBlocks.filter((_, index) => !used.has(index)).map((item) => item.type),
  );
}

function overlapScore(expectedCategory, actualCategory) {
  const expectedTypes = new Set((expectedCategory.contents ?? []).map((item) => item.type).filter(Boolean));
  const actualTypes = new Set((actualCategory.contents ?? []).map((item) => item.type).filter(Boolean));
  return [...expectedTypes].filter((type) => actualTypes.has(type)).length;
}

function toolboxEntryConfig(entry) {
  const clone = structuredClone(entry);
  delete clone.kind;
  delete clone.type;
  return clone;
}

function recorder(target) {
  return (property, expected, actual) => {
    const status = deepEqual(expected, actual) ? 'match' : 'mismatch';
    target.push({ property, status, expected, actual });
  };
}

function summarize(properties) {
  const count = (status) => properties.filter((item) => item.status === status).length;
  const matches = count('match');
  const excluded = count('excluded');
  const applicable = properties.length - excluded;
  return {
    totalObserved: properties.length,
    applicable,
    matches,
    partial: count('partial'),
    mismatches: count('mismatch'),
    unsupported: count('unsupported'),
    errors: count('error'),
    excluded,
    differences: properties.length - matches,
    parityPercent: applicable ? round((matches / applicable) * 100, 2) : 0,
  };
}

function applyAssessment(properties, rules, section) {
  const sectionRules = rules.filter((rule) => rule.section === section);
  const usedRules = new Set();
  for (const item of properties) {
    if (item.status === 'match' || item.status === 'error') continue;
    const candidates = sectionRules.filter((rule) => globMatches(rule.propertyPattern, item.property));
    assert.equal(
      candidates.length,
      1,
      `${section}.${item.property} must match exactly one assessment rule; matched ${candidates.length}.`,
    );
    const rule = candidates[0];
    usedRules.add(rule);
    item.status = rule.status;
    item.capability = rule.capability;
    item.boundary = rule.boundary;
    item.rationale = rule.rationale;
  }
  for (const rule of sectionRules) {
    assert.ok(usedRules.has(rule), `Assessment rule did not match a difference: ${section}.${rule.propertyPattern}`);
  }
}

function globMatches(pattern, value) {
  const expression = pattern
    .split('*')
    .map((part) => part.replace(/[|\\{}()[\]^$+?.]/g, '\\$&'))
    .join('[^.]*');
  return new RegExp(`^${expression}$`).test(value);
}

function pairInputs(expectedInputs, actualInputs) {
  const unused = new Set(actualInputs.map((_, index) => index));
  return expectedInputs.map((expected) => {
    const candidates = [...unused]
      .map((index) => ({ index, score: inputMatchScore(expected, actualInputs[index]) }))
      .filter(({ score }) => score >= 0)
      .sort((left, right) => right.score - left.score || left.index - right.index);
    if (!candidates.length) return null;
    unused.delete(candidates[0].index);
    return actualInputs[candidates[0].index];
  });
}

function inputMatchScore(expected, actual) {
  if (!actual || expected.kind !== actual.kind) return -1;
  let score = expected.name === actual.name ? 100 : 0;
  if (deepEqual(expected.check, actual.check)) score += 20;
  if (expected.alignment === actual.alignment) score += 10;
  const actualFields = new Set(actual.fields.map(fieldIdentity));
  score += expected.fields.filter((field) => actualFields.has(fieldIdentity(field))).length * 5;
  return score;
}

function pairFields(expectedFields, actualFields) {
  const unused = new Set(actualFields.map((_, index) => index));
  return expectedFields.map((expected) => {
    const candidates = [...unused]
      .map((index) => ({ index, score: fieldMatchScore(expected, actualFields[index]) }))
      .filter(({ score }) => score >= 0)
      .sort((left, right) => right.score - left.score || left.index - right.index);
    if (!candidates.length) return null;
    unused.delete(candidates[0].index);
    return actualFields[candidates[0].index];
  });
}

function fieldMatchScore(expected, actual) {
  if (!actual || expected.kind !== actual.kind) return -1;
  let score = expected.name != null && expected.name === actual.name ? 100 : 0;
  if (expected.name == null && actual.name == null) score += 5;
  if (deepEqual(expected.value, actual.value)) score += 40;
  if (expected.text === actual.text) score += 20;
  if (deepEqual(expected.options, actual.options)) score += 10;
  return score;
}

function inputIdentity(input) {
  return `${input.kind}:${input.name}`;
}

function fieldIdentity(field) {
  return `${field.kind}:${field.name ?? ''}`;
}

function deepEqual(left, right) {
  try {
    assert.deepEqual(left, right);
    return true;
  } catch {
    return false;
  }
}

function collectMetrics(metadata, blockCount) {
  assert.ok(blockCount > 0, 'At least one baseline block is required for per-block metrics.');
  const selected = metadata.fragments.filter((fragment) => fragment.countForMetrics);
  const totalTexts = selected.map(fragmentText);
  const editorTexts = selected.filter((fragment) => fragment.metricGroups.includes('editor')).map(fragmentText);
  const generatorTexts = selected.filter((fragment) => fragment.metricGroups.includes('generator')).map(fragmentText);
  const baseline = {
    total: sourceStats(totalTexts.join(''), 'javascript'),
    editor: sourceStats(editorTexts.join(''), 'javascript'),
    generator: sourceStats(generatorTexts.join(''), 'javascript'),
    fragmentCount: selected.length,
  };
  const m2b = sourceStats(readFileSync(path.join(caseDir, 'source.m2b'), 'utf8'), 'm2b');
  const baselineLoc = baseline.total.nonBlankNonComment;
  return {
    schemaVersion: 1,
    caseId,
    authoringScope: 'Official in-scope definitions/toolbox/generators versus maintained .m2b; generated files and domain runtime excluded.',
    blockCount,
    baseline,
    m2b,
    generatedArtifacts: directoryStats(path.join(caseDir, 'generated')),
    comparison: {
      locReductionPercent: round((1 - m2b.nonBlankNonComment / baselineLoc) * 100, 2),
      byteReductionPercent: round((1 - m2b.bytes / baseline.total.bytes) * 100, 2),
      baselineLocPerBlock: round(baselineLoc / blockCount, 2),
      m2bLocPerBlock: round(m2b.nonBlankNonComment / blockCount, 2),
    },
  };
}

function fragmentText(fragment) {
  return readFileSync(path.resolve(repoRoot, fragment.snapshotPath || fragment.path), 'utf8');
}

function sourceStats(text, language) {
  const lines = text.replace(/\n$/, '').split(/\r?\n/);
  const withoutComments = stripComments(text, language).replace(/\n$/, '').split(/\r?\n/);
  return {
    bytes: Buffer.byteLength(text, 'utf8'),
    totalLines: lines.length,
    nonBlank: lines.filter((line) => line.trim()).length,
    nonBlankNonComment: withoutComments.filter((line) => line.trim()).length,
  };
}

function stripComments(text) {
  let output = '';
  let quote = null;
  let lineComment = false;
  let blockComment = false;
  let escaped = false;
  for (let index = 0; index < text.length; index += 1) {
    const char = text[index];
    const next = text[index + 1];
    if (lineComment) {
      if (char === '\n') {
        lineComment = false;
        output += '\n';
      }
      continue;
    }
    if (blockComment) {
      if (char === '*' && next === '/') {
        blockComment = false;
        index += 1;
      } else if (char === '\n') output += '\n';
      continue;
    }
    if (quote) {
      output += char;
      if (escaped) escaped = false;
      else if (char === '\\') escaped = true;
      else if (char === quote) quote = null;
      continue;
    }
    if (char === '"' || char === "'" || char === '`') {
      quote = char;
      output += char;
    } else if (char === '/' && next === '/') {
      lineComment = true;
      index += 1;
    } else if (char === '/' && next === '*') {
      blockComment = true;
      index += 1;
    } else output += char;
  }
  return output;
}

function directoryStats(root) {
  const files = collectFiles(root);
  return {
    fileCount: files.length,
    bytes: files.reduce((sum, file) => sum + statSync(file).size, 0),
    excludedFromAuthoringComparison: true,
  };
}

function collectFiles(root) {
  const result = [];
  for (const entry of readdirSync(root, { withFileTypes: true })) {
    const absolute = path.join(root, entry.name);
    if (entry.isDirectory()) result.push(...collectFiles(absolute));
    else if (entry.isFile()) result.push(absolute);
  }
  return result;
}

function verifyFragmentIntegrity(metadata) {
  for (const fragment of metadata.fragments) {
    const file = path.resolve(repoRoot, fragment.snapshotPath || fragment.path);
    assert.ok(file.startsWith(repoRoot + path.sep), `Fragment escapes repository: ${file}`);
    const text = readFileSync(file, 'utf8');
    const lineCount = text.split(/\r?\n/).length - 1;
    assert.equal(lineCount, fragment.endLine - fragment.startLine + 1, `Line count mismatch: ${fragment.id}`);
    assert.equal(createHash('sha256').update(text).digest('hex'), fragment.sha256, `Hash mismatch: ${fragment.id}`);
  }
  for (const asset of metadata.assets ?? []) {
    const file = path.resolve(repoRoot, asset.snapshotPath);
    assert.ok(file.startsWith(repoRoot + path.sep), `Asset escapes repository: ${file}`);
    const bytes = readFileSync(file);
    assert.equal(createHash('sha256').update(bytes).digest('hex'), asset.sha256, `Hash mismatch: ${asset.id}`);
  }
}

function assertSchema(validate, value, label) {
  if (validate(value)) return;
  assert.fail(`${label} does not match its schema:\n${JSON.stringify(validate.errors, null, 2)}`);
}

function readJson(file) {
  return JSON.parse(readFileSync(file, 'utf8'));
}

function writeJson(file, value) {
  writeFileSync(file, `${JSON.stringify(value, null, 2)}\n`, 'utf8');
}

function round(value, places) {
  const factor = 10 ** places;
  return Math.round(value * factor) / factor;
}

async function startServer(root) {
  const server = createServer(async (request, response) => {
    try {
      const url = new URL(request.url || '/', 'http://127.0.0.1');
      const rawPath = decodeURIComponent(url.pathname.replace(/^\/+/, ''));
      const file = path.resolve(root, rawPath || 'index.html');
      if (!file.startsWith(root + path.sep) && file !== root) {
        response.writeHead(403).end('Forbidden');
        return;
      }
      const info = await stat(file);
      if (!info.isFile()) throw new Error('Not a file');
      response.writeHead(200, { 'Content-Type': contentType(file) });
      createReadStream(file).pipe(response);
    } catch {
      response.writeHead(404).end('Not found');
    }
  });
  await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
  return server;
}

function contentType(file) {
  const extension = path.extname(file);
  if (extension === '.html') return 'text/html; charset=utf-8';
  if (extension === '.css') return 'text/css; charset=utf-8';
  if (extension === '.js' || extension === '.mjs') return 'text/javascript; charset=utf-8';
  if (extension === '.json' || extension === '.map') return 'application/json; charset=utf-8';
  if (extension === '.svg') return 'image/svg+xml';
  if (extension === '.png') return 'image/png';
  if (extension === '.mp3') return 'audio/mpeg';
  return 'application/octet-stream';
}
