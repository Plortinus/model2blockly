#!/usr/bin/env node

import assert from 'node:assert/strict';
import { createHash } from 'node:crypto';
import {
  existsSync,
  mkdirSync,
  readFileSync,
  writeFileSync,
} from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const evaluationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repoRoot = path.resolve(evaluationRoot, '../..');
const outputDir = path.join(evaluationRoot, 'results');
const checkMode = process.argv.includes('--check');
const manifest = readJson(path.join(evaluationRoot, 'manifest.json'));

const cases = manifest.cases.map((manifestCase) => collectCase(manifestCase));
assert.equal(cases.length, 10, 'The completed evaluation must contain exactly ten cases.');

const editorAggregate = aggregateSection(cases, 'editor');
const generatorAggregate = aggregateSection(cases, 'generators');
const authoringAggregate = aggregateAuthoring(cases);
const routeAggregate = aggregateRoutes();
const result = {
  schemaVersion: 1,
  corpus: {
    title: manifest.title,
    caseCount: cases.length,
    repositories: manifest.repositories,
    controls: manifest.controls,
  },
  perCase: cases.map(({ rawComparison, rawMetrics, ...entry }) => entry),
  editor: editorAggregate,
  generators: generatorAggregate,
  authoring: authoringAggregate,
  inputRoutes: routeAggregate,
  capabilityDifferences: collectCapabilityDifferences(cases),
  interpretation: {
    evaluationUnit: manifest.evaluationUnit,
    domainLayerExcluded: true,
    statisticalSignificanceClaimed: false,
    generalisationToAllBlocklyEditorsClaimed: false,
  },
};

const json = `${JSON.stringify(result, null, 2)}\n`;
const markdown = renderMarkdown(result);
const jsonFile = path.join(outputDir, 'aggregate.json');
const markdownFile = path.join(outputDir, 'summary.md');

if (checkMode) {
  assert.equal(readExisting(jsonFile), json, 'aggregate.json is stale; run npm run aggregate:evaluation.');
  assert.equal(readExisting(markdownFile), markdown, 'summary.md is stale; run npm run aggregate:evaluation.');
  console.log('PASS official Blockly aggregate is current');
} else {
  mkdirSync(outputDir, { recursive: true });
  writeFileSync(jsonFile, json, 'utf8');
  writeFileSync(markdownFile, markdown, 'utf8');
  console.log(`WROTE ${path.relative(repoRoot, jsonFile)}`);
  console.log(`WROTE ${path.relative(repoRoot, markdownFile)}`);
}

function collectCase(manifestCase) {
  const caseDir = path.join(evaluationRoot, 'cases', manifestCase.id);
  const comparison = readJson(path.join(caseDir, 'results', 'comparison.json'));
  const metrics = readJson(path.join(caseDir, 'results', 'metrics.json'));
  assert.equal(comparison.caseId, manifestCase.id, `Comparison caseId mismatch for ${manifestCase.id}.`);
  assert.equal(metrics.caseId, manifestCase.id, `Metrics caseId mismatch for ${manifestCase.id}.`);
  assert.deepEqual(comparison.editor.loadErrors, [], `${manifestCase.id} contains editor load errors.`);
  return {
    id: manifestCase.id,
    name: manifestCase.name,
    complexity: manifestCase.complexity,
    configuration: manifestCase.configuration,
    blockCount: metrics.blockCount,
    editor: selectSummary(comparison.editor.summary),
    generators: selectSummary(comparison.generators.summary),
    authoring: {
      officialLoc: metrics.baseline.total.nonBlankNonComment,
      m2bLoc: metrics.m2b.nonBlankNonComment,
      locReductionPercent: metrics.comparison.locReductionPercent,
      officialBytes: metrics.baseline.total.bytes,
      m2bBytes: metrics.m2b.bytes,
      byteReductionPercent: metrics.comparison.byteReductionPercent,
    },
    rawComparison: comparison,
    rawMetrics: metrics,
  };
}

function selectSummary(summary) {
  return {
    totalObserved: summary.totalObserved,
    applicable: summary.applicable,
    matches: summary.matches,
    partial: summary.partial,
    mismatches: summary.mismatches,
    unsupported: summary.unsupported,
    errors: summary.errors,
    excluded: summary.excluded,
    parityPercent: summary.parityPercent,
    ...(summary.classification ? { classification: summary.classification } : {}),
  };
}

function aggregateSection(caseEntries, section) {
  const summaries = caseEntries.map((entry) => entry[section]);
  const sum = (property) => summaries.reduce((total, item) => total + item[property], 0);
  const parities = caseEntries.map((entry) => ({ caseId: entry.id, value: entry[section].parityPercent }));
  const applicable = sum('applicable');
  const matches = sum('matches');
  return {
    totals: {
      totalObserved: sum('totalObserved'),
      applicable,
      matches,
      partial: sum('partial'),
      mismatches: sum('mismatches'),
      unsupported: sum('unsupported'),
      errors: sum('errors'),
      excluded: sum('excluded'),
    },
    weightedParityPercent: round((matches / applicable) * 100, 2),
    macroParityPercent: distribution(parities),
    ...(section === 'editor' ? {
      classifications: countValues(summaries.map((item) => item.classification)),
      casesWithLoadErrors: caseEntries
        .filter((entry) => entry.rawComparison.editor.loadErrors.length > 0)
        .map((entry) => entry.id),
    } : {}),
  };
}

function aggregateAuthoring(caseEntries) {
  const officialLoc = sum(caseEntries.map((entry) => entry.authoring.officialLoc));
  const m2bLoc = sum(caseEntries.map((entry) => entry.authoring.m2bLoc));
  const officialBytes = sum(caseEntries.map((entry) => entry.authoring.officialBytes));
  const m2bBytes = sum(caseEntries.map((entry) => entry.authoring.m2bBytes));
  const deduplicated = deduplicateOfficialSource(caseEntries);
  return {
    macroLocReductionPercent: distribution(caseEntries.map((entry) => ({
      caseId: entry.id,
      value: entry.authoring.locReductionPercent,
    }))),
    sumOfIndependentConfigurations: {
      officialLoc,
      m2bLoc,
      locReductionPercent: reduction(officialLoc, m2bLoc),
      officialBytes,
      m2bBytes,
      byteReductionPercent: reduction(officialBytes, m2bBytes),
    },
    conservativeSharedSourceDeduplication: {
      rule: 'Deduplicate official source only when repository, commit, path and original line number are identical; keep all ten independently maintained .m2b models.',
      officialLoc: deduplicated.stats.nonBlankNonComment,
      m2bLoc,
      locReductionPercent: reduction(deduplicated.stats.nonBlankNonComment, m2bLoc),
      officialBytes: deduplicated.stats.bytes,
      m2bBytes,
      byteReductionPercent: reduction(deduplicated.stats.bytes, m2bBytes),
      officialLocRemoved: officialLoc - deduplicated.stats.nonBlankNonComment,
      officialBytesRemoved: officialBytes - deduplicated.stats.bytes,
      selectedSourceLineInstances: deduplicated.selectedSourceLineInstances,
      uniqueSourceLineCoordinates: deduplicated.uniqueSourceLineCoordinates,
      duplicateSourceLineInstancesRemoved: deduplicated.selectedSourceLineInstances
        - deduplicated.uniqueSourceLineCoordinates,
      crossCaseSharedCoordinates: deduplicated.crossCaseSharedCoordinates,
      sourceFiles: deduplicated.sourceFileCount,
    },
    generatedArtifactsExcluded: true,
    domainRuntimeExcluded: true,
  };
}

function deduplicateOfficialSource(caseEntries) {
  const files = new Map();
  let selectedSourceLineInstances = 0;
  for (const entry of caseEntries) {
    const extraction = readJson(path.join(
      evaluationRoot,
      'cases',
      entry.id,
      'baseline',
      'baseline-extraction.json',
    ));
    const selectedFragments = extraction.fragments.filter((item) => item.countForMetrics);
    const caseSnapshots = [];
    for (const fragment of selectedFragments) {
      const fileKey = [extraction.source.repository, extraction.source.commit, fragment.path].join('|');
      if (!files.has(fileKey)) {
        files.set(fileKey, {
          repository: extraction.source.repository,
          commit: extraction.source.commit,
          sourcePath: fragment.path,
          lines: new Map(),
        });
      }
      const sourceFile = files.get(fileKey);
      const snapshotFile = path.resolve(repoRoot, fragment.snapshotPath || fragment.path);
      assert.ok(snapshotFile.startsWith(`${repoRoot}${path.sep}`), `Fragment escapes repository: ${snapshotFile}`);
      const snapshot = readFileSync(snapshotFile, 'utf8');
      assert.equal(
        createHash('sha256').update(snapshot).digest('hex'),
        fragment.sha256,
        `Hash mismatch in ${entry.id}/${fragment.id}.`,
      );
      caseSnapshots.push(snapshot);
      const lines = splitSnapshotLines(snapshot);
      assert.equal(
        lines.length,
        fragment.endLine - fragment.startLine + 1,
        `Line count mismatch in ${entry.id}/${fragment.id}.`,
      );
      selectedSourceLineInstances += lines.length;
      lines.forEach((text, offset) => {
        const lineNumber = fragment.startLine + offset;
        const current = sourceFile.lines.get(lineNumber);
        if (current) {
          assert.equal(current.text, text, `Conflicting snapshots for ${fragment.path}:${lineNumber}.`);
          current.caseIds.add(entry.id);
        } else {
          sourceFile.lines.set(lineNumber, { text, caseIds: new Set([entry.id]) });
        }
      });
    }
    assert.deepEqual(
      sourceStats(caseSnapshots.join('')),
      entry.rawMetrics.baseline.total,
      `${entry.id} aggregate input does not reproduce its stored official-source metrics.`,
    );
  }

  const stats = emptyStats();
  let crossCaseSharedCoordinates = 0;
  for (const sourceFile of files.values()) {
    const sorted = [...sourceFile.lines.entries()].sort(([left], [right]) => left - right);
    crossCaseSharedCoordinates += sorted.filter(([, line]) => line.caseIds.size > 1).length;
    for (const group of contiguousGroups(sorted)) {
      addStats(stats, sourceStats(`${group.map(([, line]) => line.text).join('\n')}\n`));
    }
  }
  return {
    stats,
    selectedSourceLineInstances,
    uniqueSourceLineCoordinates: [...files.values()].reduce((total, file) => total + file.lines.size, 0),
    crossCaseSharedCoordinates,
    sourceFileCount: files.size,
  };
}

function aggregateRoutes() {
  const plannedCaseIds = manifest.routes.ecore.cases;
  const cases = plannedCaseIds.map((caseId) => {
    const file = path.join(evaluationRoot, 'cases', caseId, 'results', 'route-comparison.json');
    if (!existsSync(file)) return { caseId, verified: false, equivalent: null };
    const comparison = readJson(file);
    return { caseId, verified: true, equivalent: comparison.equivalent };
  });
  return {
    planned: plannedCaseIds.length,
    verified: cases.filter((entry) => entry.verified).length,
    equivalent: cases.filter((entry) => entry.equivalent === true).length,
    complete: cases.every((entry) => entry.verified),
    cases,
  };
}

function collectCapabilityDifferences(caseEntries) {
  const sections = {};
  for (const section of ['editor', 'generators']) {
    const groups = new Map();
    for (const entry of caseEntries) {
      for (const property of entry.rawComparison[section].properties) {
        if (property.status === 'match') continue;
        const key = [property.status, property.capability || 'unclassified', property.boundary || 'unclassified'].join('|');
        if (!groups.has(key)) {
          groups.set(key, {
            status: property.status,
            capability: property.capability || 'unclassified',
            boundary: property.boundary || 'unclassified',
            propertyCount: 0,
            caseIds: new Set(),
          });
        }
        const group = groups.get(key);
        group.propertyCount += 1;
        group.caseIds.add(entry.id);
      }
    }
    sections[section] = [...groups.values()]
      .map((group) => ({ ...group, caseIds: [...group.caseIds].sort() }))
      .sort((left, right) => right.propertyCount - left.propertyCount
        || left.status.localeCompare(right.status)
        || left.capability.localeCompare(right.capability));
  }
  return sections;
}

function renderMarkdown(aggregate) {
  const editor = aggregate.editor;
  const generators = aggregate.generators;
  const authoring = aggregate.authoring;
  const independent = authoring.sumOfIndependentConfigurations;
  const deduplicated = authoring.conservativeSharedSourceDeduplication;
  const routeRows = aggregate.inputRoutes.cases
    .map((entry) => `| ${entry.caseId} | ${entry.verified ? 'sí' : 'no'} | ${entry.equivalent === null ? '—' : entry.equivalent ? 'sí' : 'no'} |`)
    .join('\n');
  const caseRows = aggregate.perCase.map((entry) => [
    entry.id,
    entry.name,
    entry.blockCount,
    `${entry.editor.matches}/${entry.editor.applicable}`,
    formatPercent(entry.editor.parityPercent),
    entry.editor.classification,
    `${entry.generators.matches}/${entry.generators.applicable}`,
    formatPercent(entry.generators.parityPercent),
    entry.authoring.officialLoc,
    entry.authoring.m2bLoc,
    formatPercent(entry.authoring.locReductionPercent),
  ].map(escapeCell).join(' | ')).map((row) => `| ${row} |`).join('\n');
  const capabilityRows = aggregate.capabilityDifferences.editor.slice(0, 12)
    .map((entry) => `| ${escapeCell(entry.status)} | ${escapeCell(entry.capability)} | ${entry.propertyCount} | ${entry.caseIds.length} |`)
    .join('\n');

  return `# Resumen agregado de la evaluación oficial de Blockly

Este documento se genera de forma determinista con \`npm run aggregate:evaluation\` a partir de los resultados detallados de los diez casos. La unidad evaluada es el subsistema de edición Blockly; la visualización y la ejecución propias de cada aplicación quedan fuera del alcance.

## Resultados por caso

| Caso | Editor oficial | Bloques | Editor: coincidencias | Paridad | Clasificación | Generadores: coincidencias | Paridad gen. | LOC oficial | LOC .m2b | Reducción LOC |
|---|---|---:|---:|---:|---|---:|---:|---:|---:|---:|
${caseRows}

## Reproducción del editor

- Paridad ponderada: **${editor.totals.matches}/${editor.totals.applicable} (${formatPercent(editor.weightedParityPercent)})**.
- Paridad macro por caso: media **${formatPercent(editor.macroParityPercent.mean)}** y mediana **${formatPercent(editor.macroParityPercent.median)}**.
- Estados no coincidentes: ${editor.totals.partial} parciales, ${editor.totals.mismatches} diferencias con capacidad disponible, ${editor.totals.unsupported} propiedades no soportadas y ${editor.totals.errors} errores.
- Clasificación: ${editor.classifications.partial || 0} casos parciales, ${editor.classifications.complete || 0} completos y ${editor.classifications['not-reproducible'] || 0} no reproducibles. Los diez editores generados cargaron sin errores.

La paridad ponderada responde por la proporción de propiedades observadas en todo el corpus. La media y la mediana macro conceden el mismo peso a cada editor, aunque sus descriptores tengan tamaños distintos.

## Generadores de código

- Paridad ponderada: **${generators.totals.matches}/${generators.totals.applicable} (${formatPercent(generators.weightedParityPercent)})**.
- Paridad macro por caso: media **${formatPercent(generators.macroParityPercent.mean)}** y mediana **${formatPercent(generators.macroParityPercent.median)}**.
- Se excluyeron ${generators.totals.excluded} propiedades ligadas a instrumentación o al motor de ejecución; no cuentan como coincidencias ni entran en el denominador.

## Tamaño de la fuente mantenida

Al tratar las diez configuraciones como artefactos independientes, la fuente oficial suma ${independent.officialLoc} LOC y los modelos suman ${independent.m2bLoc} LOC: una reducción de **${formatPercent(independent.locReductionPercent)}**.

Pond Tutor y Pond reutilizan líneas de los mismos ficheros oficiales. Para no contarlas dos veces en el total, el cálculo conservador identifica una línea por repositorio, commit, ruta y número de línea. La referencia oficial queda en ${deduplicated.officialLoc} LOC, frente a las mismas ${deduplicated.m2bLoc} LOC de los diez modelos independientes: una reducción conservadora de **${formatPercent(deduplicated.locReductionPercent)}**. Se eliminan ${deduplicated.officialLocRemoved} LOC oficiales duplicadas; los resultados individuales no cambian.

La reducción por caso tiene una media de **${formatPercent(authoring.macroLocReductionPercent.mean)}** y una mediana de **${formatPercent(authoring.macroLocReductionPercent.median)}**. No se cuentan las salidas generadas, la aplicación contenedora ni la lógica de dominio.

## Diferencias más frecuentes del editor

| Estado | Capacidad | Propiedades | Casos |
|---|---|---:|---:|
${capabilityRows}

El detalle completo, incluidos todos los grupos de capacidades y los casos afectados, se conserva en \`aggregate.json\` y en el \`comparison.json\` de cada caso.

## Equivalencia entre Ecore y .m2b

| Caso planificado | Comparación ejecutada | Descriptor equivalente |
|---|---|---|
${routeRows}

Se han verificado ${aggregate.inputRoutes.verified} de las ${aggregate.inputRoutes.planned} rutas previstas. ${aggregate.inputRoutes.complete ? 'RQ4 dispone de toda la evidencia planificada.' : 'Por tanto, RQ4 todavía no debe declararse cerrada: faltan los artefactos Ecore y las comparaciones de los casos indicados como no ejecutados.'}

## Alcance de la conclusión

Los resultados demuestran la carga y la comparación estructural de diez configuraciones oficiales bajo controles comunes. No constituyen una prueba de equivalencia de las aplicaciones completas, no miden productividad humana y no justifican significación estadística ni generalización a todos los editores Blockly.
`;
}

function distribution(entries) {
  const sorted = [...entries].sort((left, right) => left.value - right.value || left.caseId.localeCompare(right.caseId));
  const values = sorted.map((entry) => entry.value);
  const middle = Math.floor(values.length / 2);
  return {
    mean: round(sum(values) / values.length, 2),
    median: round(values.length % 2 ? values[middle] : (values[middle - 1] + values[middle]) / 2, 2),
    minimum: sorted[0],
    maximum: sorted.at(-1),
  };
}

function countValues(values) {
  return Object.fromEntries([...new Set(values)].sort().map((value) => [
    value,
    values.filter((candidate) => candidate === value).length,
  ]));
}

function contiguousGroups(entries) {
  const groups = [];
  for (const entry of entries) {
    const current = groups.at(-1);
    if (!current || entry[0] !== current.at(-1)[0] + 1) groups.push([entry]);
    else current.push(entry);
  }
  return groups;
}

function splitSnapshotLines(text) {
  const normalized = text.replace(/\r\n/g, '\n');
  assert.ok(normalized.endsWith('\n'), 'Official source snapshots must end with a newline.');
  return normalized.slice(0, -1).split('\n');
}

function sourceStats(text) {
  const lines = text.replace(/\n$/, '').split(/\r?\n/);
  const withoutComments = stripComments(text).replace(/\n$/, '').split(/\r?\n/);
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

function emptyStats() {
  return { bytes: 0, totalLines: 0, nonBlank: 0, nonBlankNonComment: 0 };
}

function addStats(target, value) {
  for (const property of Object.keys(target)) target[property] += value[property];
}

function reduction(baseline, actual) {
  return round((1 - actual / baseline) * 100, 2);
}

function sum(values) {
  return values.reduce((total, value) => total + value, 0);
}

function round(value, places) {
  const factor = 10 ** places;
  return Math.round(value * factor) / factor;
}

function formatPercent(value) {
  return `${value.toFixed(2)} %`;
}

function escapeCell(value) {
  return String(value).replaceAll('|', '\\|');
}

function readJson(file) {
  return JSON.parse(readFileSync(file, 'utf8'));
}

function readExisting(file) {
  assert.ok(existsSync(file), `${path.relative(repoRoot, file)} does not exist; run npm run aggregate:evaluation.`);
  return readFileSync(file, 'utf8');
}
