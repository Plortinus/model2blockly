#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import { existsSync, readdirSync, readFileSync, statSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const pluginVersion = '1.0.9.qualifier';
const failures = [];

checkRequiredFiles();
checkXmlFiles();
checkManifestFiles();
checkClasspathFiles();
checkBuildProperties();
checkMetamodelFirstArchitecture();
checkNpmScripts();
checkUiContributions();
checkIntermediatePipeline();
try {
  checkFeatureAndUpdateSite();
} catch (error) {
  const detail = String(error.stderr || error.message || error).trim();
  fail(`Update-site packaged jars are stale or unreadable after the Model2Blockly rename: ${detail}. Rebuild the plugin jars with JDK 21, then run npm run rebuild:update-site.`);
}
checkGeneratedExamples();
checkNoLegacyFrontendArtifacts();

if (failures.length) {
  for (const failure of failures) console.error(`[FAIL] ${failure}`);
  console.error(`\n${failures.length} Eclipse plugin verification check(s) failed.`);
  process.exit(1);
}

console.log('[PASS] Eclipse plugin metadata, feature/update site, generated examples and HTML-only cleanup checks passed.');

function checkRequiredFiles() {
  [
    'io.github.plortinus.model2blockly/META-INF/MANIFEST.MF',
    'io.github.plortinus.model2blockly.ide/META-INF/MANIFEST.MF',
    'io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF',
    'io.github.plortinus.model2blockly.ui/plugin.xml',
    'io.github.plortinus.model2blockly.feature/feature.xml',
    'io.github.plortinus.model2blockly.feature/feature.properties',
    'io.github.plortinus.model2blockly.updatesite/category.xml',
    'io.github.plortinus.model2blockly.updatesite/repository/content.jar',
    'io.github.plortinus.model2blockly.updatesite/repository/artifacts.jar',
    'io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore',
    'io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.genmodel',
    'io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel',
    'io.github.plortinus.model2blockly/model/blockly_editor_spec.ecore',
    'io.github.plortinus.model2blockly/model/app_maker.ecore',
    'io.github.plortinus.model2blockly/examples/app_maker.m2b',
    'RELEASE_CHECKLIST.md',
    'scripts/rebuild-update-site.mjs',
    'site/update-site/index.html',
  ].forEach((file) => assertExists(file));
}

function checkXmlFiles() {
  const files = [
    'io.github.plortinus.model2blockly/plugin.xml',
    'io.github.plortinus.model2blockly.ui/plugin.xml',
    'io.github.plortinus.model2blockly.feature/feature.xml',
    'io.github.plortinus.model2blockly.updatesite/category.xml',
    'io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore',
    'io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.genmodel',
    'io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel',
    'io.github.plortinus.model2blockly/model/blockly_editor_spec.ecore',
    'io.github.plortinus.model2blockly/model/app_maker.ecore',
    'io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi',
    'io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/intermediate/Appmaker_blocklyspec.xmi',
  ];
  const script = [
    'import sys, xml.etree.ElementTree as ET',
    'for f in sys.argv[1:]:',
    '    ET.parse(f)',
  ].join('\n');
  try {
    execFileSync('python3', ['-c', script, ...files.map(abs)], { stdio: 'pipe' });
  } catch (error) {
    fail(`XML/XMI parse failed: ${String(error.stderr || error.message).trim()}`);
  }
}

function checkManifestFiles() {
  const manifests = [
    'io.github.plortinus.model2blockly/META-INF/MANIFEST.MF',
    'io.github.plortinus.model2blockly.ide/META-INF/MANIFEST.MF',
    'io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF',
  ];
  for (const file of manifests) {
    const text = read(file);
    assertIncludes(text, 'Bundle-SymbolicName:', `${file} declares Bundle-SymbolicName`);
    assertIncludes(text, `Bundle-Version: ${pluginVersion}`, `${file} uses ${pluginVersion}`);
    assertIncludes(text, 'Bundle-ClassPath: ., bin/', `${file} includes compiled bin output on the bundle classpath`);
    assertIncludes(text, 'Bundle-RequiredExecutionEnvironment: JavaSE-21', `${file} declares JavaSE-21`);
  }
  const core = read('io.github.plortinus.model2blockly/META-INF/MANIFEST.MF');
  assertNotIncludes(core, 'org.junit', 'Core bundle should not import JUnit in production metadata');
  assertIncludes(core, 'io.github.plortinus.model2blockly.validationpatch', 'Core bundle exports validation patch package');

  const ui = read('io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF');
  for (const dependency of ['org.eclipse.xtext', 'org.eclipse.emf.common', 'org.eclipse.emf.ecore', 'org.eclipse.emf.ecore.xmi']) {
    assertIncludes(ui, dependency, `UI bundle declares direct runtime dependency ${dependency}`);
  }
}

function checkClasspathFiles() {
  const coreClasspath = read('io.github.plortinus.model2blockly/.classpath');
  assertIncludes(coreClasspath, 'path="emf-gen"', 'Core classpath includes fixed EMF generated API source folder');
  assertIncludes(coreClasspath, 'path="test"', 'Core classpath includes test source folder');
  assertIncludes(coreClasspath, 'output="test-bin"', 'Core test source uses separate test-bin output');
  assertIncludes(coreClasspath, 'org.eclipse.jdt.junit.JUNIT_CONTAINER/5', 'Core classpath includes JUnit 5 container for IDE tests');
  assertIncludes(coreClasspath, 'org.eclipse.pde.core.requiredPlugins', 'Core classpath includes PDE required plug-ins');
}

function checkBuildProperties() {
  const coreBuild = read('io.github.plortinus.model2blockly/build.properties');
  assertIncludes(coreBuild, 'emf-gen/', 'Core build compiles fixed EMF generated APIs');
  assertIncludes(coreBuild, 'model/', 'Core build includes model resources');
  assertIncludes(coreBuild, 'examples/', 'Core build includes examples resources');
  assertNotIncludes(coreBuild, 'test/', 'Core build does not package test sources');

  for (const file of [
    'io.github.plortinus.model2blockly.ide/build.properties',
    'io.github.plortinus.model2blockly.ui/build.properties',
    'io.github.plortinus.model2blockly.feature/build.properties',
  ]) {
    assertIncludes(read(file), 'bin.includes', `${file} declares bin.includes`);
  }
}

function checkMetamodelFirstArchitecture() {
  const grammar = read('io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext');
  assertIncludes(grammar,
    'import "https://plortinus.github.io/model2blockly/ns/model2blockly" as m2b',
    'Xtext grammar imports the fixed Model2Blockly Ecore package');
  assertNotIncludes(grammar,
    'generate model2Blockly',
    'Xtext grammar does not infer the Model2Blockly Ecore metamodel');
  assertIncludes(grammar,
    'DomainModel returns m2b::DomainModel',
    'Xtext root rule returns the fixed Ecore DomainModel type');

  const workflow = read('io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/GenerateModel2Blockly.mwe2');
  assertIncludes(workflow,
    'referencedResource = "platform:/resource/io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.genmodel"',
    'Xtext generation workflow references the fixed Model2Blockly genmodel');

  const plugin = read('io.github.plortinus.model2blockly/plugin.xml');
  assertIncludes(plugin,
    'genModel = "model/metamodel/Model2Blockly.genmodel"',
    'Core plugin registers the fixed Model2Blockly genmodel');
  assertIncludes(plugin,
    'genModel = "model/metamodel/BlocklyEditorSpec.genmodel"',
    'Core plugin registers the fixed BlocklyEditorSpec genmodel');

  for (const staleFile of [
    'io.github.plortinus.model2blockly/model/generated/Model2Blockly.ecore',
    'io.github.plortinus.model2blockly/model/generated/Model2Blockly.genmodel',
    'io.github.plortinus.model2blockly/model/generated/BlocklyEditorSpec.genmodel',
  ]) {
    if (existsSync(abs(staleFile))) {
      fail(`Fixed metamodel file should live under model/metamodel, not ${staleFile}`);
    }
  }
}

function checkNpmScripts() {
  const pkg = JSON.parse(read('package.json'));
  if (!pkg.scripts || pkg.scripts['verify:domain-xmi'] !== 'node scripts/verify-domain-xmi.mjs') {
    fail('package.json exposes verify:domain-xmi EMF domain XMI script');
  }
  if (pkg.scripts['rebuild:update-site'] !== 'node scripts/rebuild-update-site.mjs') {
    fail('package.json exposes rebuild:update-site release script');
  }
  if (pkg.scripts['verify:docs'] !== 'node scripts/verify-docs-i18n.mjs') {
    fail('package.json exposes verify:docs documentation i18n script');
  }
  const release = read('RELEASE_CHECKLIST.md');
  assertIncludes(release, 'npm run rebuild:update-site', 'Release checklist documents local update-site rebuild');
  assertIncludes(release, 'Publish GitHub Pages', 'Release checklist documents GitHub Pages publishing');
  assertIncludes(release, 'Clear `Hide items that are already installed`', 'Release checklist documents Eclipse refresh steps');
  const updateSitePage = read('site/update-site/index.html');
  assertIncludes(updateSitePage, 'Hide items that are already installed', 'Hosted update-site page documents refresh steps');
}

function checkUiContributions() {
  const plugin = read('io.github.plortinus.model2blockly.ui/plugin.xml');
  assertIncludes(plugin, 'io.github.plortinus.model2blockly.ui.generateBlocklyEditor', 'Generate command is declared');
  assertIncludes(plugin, 'BlocklyEditorSpec XMI pipeline', 'Generate command describes the intermediate XMI pipeline');
  assertIncludes(plugin, 'intermediate XMI', 'Generate command tooltip mentions the intermediate XMI artifact');
  assertIncludes(plugin, 'GenerateBlocklyEditorHandler', 'Command handler is registered');
  assertIncludes(plugin, 'io.github.plortinus.model2blockly.ui.applyValidationPatch', 'Validation patch command is declared');
  assertIncludes(plugin, 'ApplyValidationPatchHandler', 'Validation patch command handler is registered');
  assertIncludes(plugin, 'GenerateBlocklyEditorAction', 'Legacy popup action is registered');
  assertIncludes(plugin, 'nameFilter="*.ecore"', '.ecore popup action is registered');
  assertIncludes(plugin, 'io.github.plortinus.model2blockly.Model2Blockly.GeneratableFileSelection', 'Generate command visibility is restricted to supported files');
  assertIncludes(plugin, 'org.eclipse.core.resources.extension" value="ecore"', 'Generate command is visible for .ecore files');
}

function checkIntermediatePipeline() {
  const serializer = read('io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java');
  assertIncludes(serializer, 'fromXmi(String xmi)', 'Intermediate serializer can reload BlocklyEditorSpec from XMI');
  assertIncludes(serializer, 'deserialize(String xmi)', 'Intermediate serializer loads XMI as an EMF model');
  const featureProperties = read('io.github.plortinus.model2blockly.feature/feature.properties');
  assertIncludes(featureProperties, 'BlocklyEditorSpec XMI intermediate model',
    'Feature description explains the intermediate XMI pipeline');

  for (const file of [
    'io.github.plortinus.model2blockly.ui/src/io/github/plortinus/model2blockly/ui/handlers/GenerateBlocklyEditorHandler.java',
    'io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/EcoreToBlocklyMain.java',
  ]) {
    const text = read(file);
    assertIncludes(text, 'intermediateXmi', `${file} materializes an intermediate XMI string`);
    assertIncludesAny(text, [
      'BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi)',
      'BlocklySpecXmiSerializer.fromXmi(intermediateXmi)',
    ],
      `${file} reloads the intermediate XMI before HTML generation`);
  }
}

function checkFeatureAndUpdateSite() {
  const feature = read('io.github.plortinus.model2blockly.feature/feature.xml');
  for (const id of ['io.github.plortinus.model2blockly', 'io.github.plortinus.model2blockly.ide', 'io.github.plortinus.model2blockly.ui']) {
    assertIncludes(feature, `id="${id}"`, `Feature includes ${id}`);
  }
  const category = read('io.github.plortinus.model2blockly.updatesite/category.xml');
  assertIncludes(category, 'id="io.github.plortinus.model2blockly.feature"', 'Update site references Model2Blockly feature');
  assertIncludes(category, 'category name="model2blockly"', 'Update site assigns model2blockly category');
  assertExists(`io.github.plortinus.model2blockly.updatesite/repository/features/io.github.plortinus.model2blockly.feature_${pluginVersion}.jar`);
  const pluginJars = [
    `io.github.plortinus.model2blockly.updatesite/repository/plugins/io.github.plortinus.model2blockly_${pluginVersion}.jar`,
    `io.github.plortinus.model2blockly.updatesite/repository/plugins/io.github.plortinus.model2blockly.ide_${pluginVersion}.jar`,
    `io.github.plortinus.model2blockly.updatesite/repository/plugins/io.github.plortinus.model2blockly.ui_${pluginVersion}.jar`,
  ];
  const expectedBundleIds = [
    'io.github.plortinus.model2blockly',
    'io.github.plortinus.model2blockly.ide',
    'io.github.plortinus.model2blockly.ui',
  ];
  for (const [index, file] of pluginJars.entries()) {
    assertExists(file);
    const manifest = unfoldManifest(
      execFileSync('unzip', ['-p', abs(file), 'META-INF/MANIFEST.MF'], { encoding: 'utf8' }));
    assertIncludes(manifest, `Bundle-SymbolicName: ${expectedBundleIds[index]};`,
      `${file} uses the renamed Bundle-SymbolicName`);
    assertNotIncludes(manifest, 'org.example.blocklydsl',
      `${file} does not contain the legacy bundle id`);
    assertIncludes(manifest, 'Bundle-ClassPath: ., bin/', `${file} keeps bin/ classes loadable after installation`);
    if (expectedBundleIds[index] === 'io.github.plortinus.model2blockly.ui') {
      for (const dependency of ['org.eclipse.xtext', 'org.eclipse.emf.common', 'org.eclipse.emf.ecore', 'org.eclipse.emf.ecore.xmi']) {
        assertIncludes(manifest, dependency, `${file} declares direct runtime dependency ${dependency}`);
      }
      assertIncludes(manifest,
        'Bundle-Activator: io.github.plortinus.model2blockly.ui.internal.Model2blocklyActivator',
        `${file} declares the generated UI activator class with exact case`);
    }
  }
  const coreListing = execFileSync('unzip', ['-l', abs(pluginJars[0])], { encoding: 'utf8' });
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/ValidationPatchService.class',
    'Core update-site jar contains public validation patch service API class');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/ValidationPatchResult.class',
    'Core update-site jar contains public validation patch result API class');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/validationpatch/ValidationSourcePatcher.class',
    'Core update-site jar contains validation patch core class');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/validationpatch/PatchReport.class',
    'Core update-site jar contains public validation patch report API class');
  assertNotIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/validationpatch/ValidationSourcePatcher$PatchReport.class',
    'Core update-site jar does not contain the old nested PatchReport class');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/standalone/ValidationPatchMain.class',
    'Core update-site jar contains validation patch CLI class');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/blocklyspec/BlocklyEditorSpecValidator.class',
    'Core update-site jar contains intermediate model validator');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/blocklyspec/BlocklySpecDiagnostics.class',
    'Core update-site jar contains user-facing intermediate diagnostic formatter');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/blocklyspec/BlocklySpecSourceMapper.class',
    'Core update-site jar contains source mapper for intermediate diagnostics');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.class',
    'Core update-site jar contains intermediate XMI serializer');
  assertIncludes(coreListing, 'bin/io/github/plortinus/model2blockly/blocklyspec/ValidationExpressionSyntax.class',
    'Core update-site jar contains validation expression syntax checker');
  const serializerSource = execFileSync('unzip', ['-p', abs(pluginJars[0]),
    'src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java'], { encoding: 'utf8' });
  assertIncludes(serializerSource, 'fromXmi(String xmi)',
    'Core update-site jar source can reload BlocklyEditorSpec from XMI');
  assertIncludes(serializerSource, 'deserialize(String xmi)',
    'Core update-site jar source loads intermediate XMI as EMF');
  const packagedEcoreStandalone = execFileSync('unzip', ['-p', abs(pluginJars[0]),
    'src/io/github/plortinus/model2blockly/standalone/EcoreToBlocklyMain.java'], { encoding: 'utf8' });
  assertIncludes(packagedEcoreStandalone, 'EMF EditorSpec intermediate XMI and HTML Blockly editor',
    'Core update-site jar Ecore standalone README template documents the EMF intermediate XMI');
  const packagedBlocklyGenerator = execFileSync('unzip', ['-p', abs(pluginJars[0]),
    'xtend-gen/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.java'], { encoding: 'utf8' });
  assertIncludes(packagedBlocklyGenerator, 'data-generated-ui="2026-product"',
    'Core update-site jar contains the current generated editor product UI template');
  assertIncludes(packagedBlocklyGenerator, 'workspace-shell',
    'Core update-site jar generated editor template uses the workspace shell layout');
  assertIncludes(packagedBlocklyGenerator, 'function modelToDomainXMI(model)',
    'Core update-site jar generated editor exports user models as domain instance XMI');
  assertIncludes(packagedBlocklyGenerator, 'fieldTypes',
    'Core update-site jar generated editor carries domain field metadata for XMI export');
  assertIncludes(packagedBlocklyGenerator, 'http://www.omg.org/XMI',
    'Core update-site jar generated editor emits an EMF-compatible XMI namespace');
  assertNotIncludes(packagedBlocklyGenerator, 'function jsonToXMI(',
    'Core update-site jar no longer contains the old untyped JSON-to-XML export');
  assertNotIncludes(packagedBlocklyGenerator, '#header{background:#4285f4',
    'Core update-site jar no longer contains the old blue header editor template');
  const uiListing = execFileSync('unzip', ['-l', abs(pluginJars[2])], { encoding: 'utf8' });
  assertIncludes(uiListing, 'bin/io/github/plortinus/model2blockly/ui/internal/Model2blocklyActivator.class',
    'UI update-site jar contains the exact Bundle-Activator class');
  assertIncludes(uiListing, 'bin/io/github/plortinus/model2blockly/ui/handlers/ApplyValidationPatchHandler.class',
    'UI update-site jar contains validation patch handler class');
  assertIncludes(uiListing, 'bin/io/github/plortinus/model2blockly/ui/handlers/GenerateBlocklyEditorHandler.class',
    'UI update-site jar contains generate handler class');
  const uiPluginXml = execFileSync('unzip', ['-p', abs(pluginJars[2]), 'plugin.xml'], { encoding: 'utf8' });
  assertIncludes(uiPluginXml, 'io.github.plortinus.model2blockly.ui.applyValidationPatch',
    'UI update-site jar plugin.xml declares validation patch command');
  assertIncludes(uiPluginXml, 'intermediate XMI',
    'UI update-site jar plugin.xml mentions intermediate XMI output');
  const uiPatchHandlerSource = execFileSync('unzip', ['-p', abs(pluginJars[2]),
    'src/io/github/plortinus/model2blockly/ui/handlers/ApplyValidationPatchHandler.java'], { encoding: 'utf8' });
  assertNotIncludes(uiPatchHandlerSource, 'ValidationSourcePatcher.PatchReport',
    'UI update-site jar no longer imports nested PatchReport');
  assertNotIncludes(uiPatchHandlerSource, 'io.github.plortinus.model2blockly.validationpatch',
    'UI update-site jar no longer imports implementation validationpatch types');
  assertIncludes(uiPatchHandlerSource, 'ValidationPatchService',
    'UI update-site jar uses the public validation patch facade');
  assertIncludes(uiPatchHandlerSource, 'validationCandidates',
    'UI update-site jar contains validation patch handler generated-output lookup fix');
  assertIncludes(uiPatchHandlerSource, 'generatedCaseNames',
    'UI update-site jar contains route-aware validation model selection');
  assertIncludes(uiPatchHandlerSource, 'baseName + "_ecore"',
    'UI update-site jar prefers Ecore generated validation blocks for .ecore sources');
  assertIncludes(uiPatchHandlerSource, 'No source changes are required',
    'UI update-site jar contains validation patch no-op guard');
  const uiGenerateHandlerSource = execFileSync('unzip', ['-p', abs(pluginJars[2]),
    'src/io/github/plortinus/model2blockly/ui/handlers/GenerateBlocklyEditorHandler.java'], { encoding: 'utf8' });
  assertIncludes(uiGenerateHandlerSource, 'createDiagnosticMarkers',
    'UI update-site jar generates Eclipse problem markers for intermediate diagnostics');
  assertIncludes(uiGenerateHandlerSource, 'createDslValidationMarkers',
    'UI update-site jar generates Eclipse problem markers for DSL validation diagnostics');
  assertIncludes(uiGenerateHandlerSource, 'BlocklySpecSourceMapper',
    'UI update-site jar maps intermediate diagnostics back to source files');
  assertIncludes(uiGenerateHandlerSource, 'BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi)',
    'UI update-site jar reloads intermediate XMI before HTML generation');
  assertIncludes(uiGenerateHandlerSource, 'EMF EditorSpec intermediate XMI and HTML Blockly editor',
    'UI update-site jar generated-output README documents the EMF intermediate XMI');
  assertIncludes(uiGenerateHandlerSource, 'html/sample_model.json',
    'UI update-site jar generated-output README points at the nested sample model path');
  const content = execFileSync('unzip', ['-p', abs('io.github.plortinus.model2blockly.updatesite/repository/content.jar'), 'content.xml'], { encoding: 'utf8' });
  assertIncludes(content, 'io.github.plortinus.model2blockly.feature.feature.group', 'Update site metadata contains Model2Blockly feature group');
  assertIncludes(content, 'io.github.plortinus.model2blockly.ui', 'Update site metadata contains UI bundle');
  assertIncludes(content, "name='org.eclipse.equinox.p2.type.category' value='true'",
    'Update site metadata declares a visible p2 category');
  assertIncludes(content, 'category.xml.model2blockly',
    'Update site metadata contains the Model2Blockly category generated from category.xml');
  assertIncludes(content, `name='io.github.plortinus.model2blockly.feature.feature.group' range='[${pluginVersion},${pluginVersion}]'`,
    'Update site category points at the installable Model2Blockly feature group');
}

function checkGeneratedExamples() {
  for (const dir of [
    'io.github.plortinus.model2blockly/examples/generated/app_maker_ecore',
    'io.github.plortinus.model2blockly/examples/generated/app_maker_dsl',
  ]) {
    assertExists(`${dir}/README.md`);
    assertExists(`${dir}/generation_report.html`);
    assertExists(`${dir}/html/sample_model.json`);
    assertExists(`${dir}/html/validation_blocks.json`);
    assertExists(`${dir}/html/validation_runtime.js`);
    assertExists(`${dir}/html/validation_workspace.html`);
    const readme = read(`${dir}/README.md`);
    assertIncludes(readme, 'EMF EditorSpec intermediate XMI and HTML Blockly editor',
      `${dir} README documents both the intermediate XMI and generated HTML editor output`);
    const report = read(`${dir}/generation_report.html`);
    assertIncludes(report, 'Generation Pipeline', `${dir} generation report explains the source-to-XMI-to-HTML pipeline`);
    assertIncludes(report, 'Intermediate XMI', `${dir} generation report links the intermediate XMI`);
    assertIncludes(report, 'Formal EditorSpec EMF/XMI intermediate model',
      `${dir} generation report describes the intermediate XMI as the formal EditorSpec artifact`);
    assertIncludes(report, 'Domain instance model',
      `${dir} generation report documents the generated editor domain instance XMI export`);
    const htmlFiles = readdirSync(abs(`${dir}/html`)).filter((file) => file.endsWith('_standalone.html'));
    if (htmlFiles.length !== 1) fail(`${dir}/html should contain exactly one *_standalone.html file`);
    const standaloneHtml = read(`${dir}/html/${htmlFiles[0]}`);
    assertIncludes(standaloneHtml, 'data-generated-ui="2026-product"', `${dir} generated editor uses the current product UI template`);
    assertIncludes(standaloneHtml, 'workspace-shell', `${dir} generated editor uses the workspace shell layout`);
    assertIncludes(standaloneHtml, '<h2>Inspector</h2>', `${dir} generated editor labels the output panel as Inspector`);
    assertIncludes(standaloneHtml, 'class="mode-toggle"', `${dir} generated editor exposes Build/Developer mode toggle`);
    assertIncludes(standaloneHtml, 'data-developer-only="true"', `${dir} generated editor hides developer tabs by default`);
    assertIncludes(standaloneHtml, "setEditorMode('build', true)", `${dir} generated editor starts in Build mode`);
    assertIncludes(standaloneHtml, 'Model, preview and validation issues', `${dir} generated editor uses user-facing output subtitle`);
    assertIncludes(standaloneHtml, 'function modelToDomainXMI(model)', `${dir} generated editor exposes domain instance XMI export`);
    assertIncludes(standaloneHtml, 'xmlns:xmi="http://www.omg.org/XMI"', `${dir} generated editor uses the EMF XMI namespace`);
    assertNotIncludes(standaloneHtml, 'function jsonToXMI(', `${dir} generated editor no longer uses the old untyped JSON-to-XML export`);
    const sample = JSON.parse(read(`${dir}/html/sample_model.json`));
    const types = new Set();
    walk(sample, (node) => {
      if (node && typeof node === 'object' && typeof node._type === 'string') types.add(node._type);
    });
    if (dir.endsWith('app_maker_ecore')) {
      for (const type of [
        'App',
        'Page',
        'TextLabel',
        'Button',
        'TextInput',
        'TextArea',
        'ImageView',
        'ListView',
        'ToggleSwitch',
        'SelectInput',
        'CheckboxInput',
        'RadioGroup',
        'NumberInput',
        'SliderInput',
        'DateInput',
        'TimeInput',
        'FileUpload',
        'TableView',
        'Container',
        'Card',
        'Form',
        'TabsView',
        'TabPanel',
        'Modal',
        'Toast',
        'Divider',
        'Spacer',
        'Alert',
        'Navigate',
        'SubmitForm',
        'SetInputValue',
        'OpenUrl',
      ]) {
        if (!types.has(type)) fail(`${dir}/html/sample_model.json is missing sample type ${type}`);
      }
    } else {
      for (const type of ['App', 'Page', 'DataSource', 'Button', 'ListView', 'Navigate']) {
        if (!types.has(type)) fail(`${dir}/html/sample_model.json is missing sample type ${type}`);
      }
    }
  }
}

function checkNoLegacyFrontendArtifacts() {
  for (const dir of [
    'io.github.plortinus.model2blockly/examples/generated/app_maker_ecore',
    'io.github.plortinus.model2blockly/examples/generated/app_maker_dsl',
  ]) {
    const legacyFrontendDir = 're' + 'act';
    if (existsSync(abs(`${dir}/${legacyFrontendDir}`))) {
      fail(`${dir}/${legacyFrontendDir} should not exist in HTML-only output`);
    }
  }
  const forbidden = new RegExp([
    '\\b' + 'Re' + 'act' + '\\b',
    '\\b' + 're' + 'act' + '\\b',
    'Blockly' + 'Re' + 'act' + 'CodeGenerator',
    '--target ' + 'both',
    '--target ' + 're' + 'act',
  ].join('|'));
  for (const file of listFiles(repoRoot)) {
    const relative = path.relative(repoRoot, file);
    if (shouldSkipTextScan(relative)) continue;
    let text = '';
    try {
      text = readFileSync(file, 'utf8');
    } catch {
      continue;
    }
    if (forbidden.test(text)) fail(`Forbidden legacy frontend reference found in ${relative}`);
  }
}

function walk(value, visit) {
  visit(value);
  if (Array.isArray(value)) {
    value.forEach((item) => walk(item, visit));
  } else if (value && typeof value === 'object') {
    Object.values(value).forEach((item) => walk(item, visit));
  }
}

function* listFiles(dir) {
  for (const entry of readdirSync(dir)) {
    const full = path.join(dir, entry);
    const relative = path.relative(repoRoot, full);
    if (shouldSkipPath(relative)) continue;
    const info = statSync(full);
    if (info.isDirectory()) yield* listFiles(full);
    else if (info.isFile()) yield full;
  }
}

function shouldSkipPath(relative) {
  return relative === '.git'
    || relative === 'node_modules'
    || relative.endsWith('/node_modules')
    || relative.includes('/node_modules/')
    || relative.endsWith('/bin')
    || relative.includes('/bin/')
    || relative.startsWith('.git/')
    || relative.startsWith('node_modules/')
    || relative.startsWith('.cache/');
}

function shouldSkipTextScan(relative) {
  return shouldSkipPath(relative)
    || relative === 'package-lock.json'
    || relative.endsWith('.pdf')
    || relative.endsWith('.png')
    || relative.endsWith('.docx')
    || relative.endsWith('.jar');
}

function assertExists(file) {
  if (!existsSync(abs(file))) fail(`Missing required file: ${file}`);
}

function assertIncludes(text, needle, message) {
  if (!text.includes(needle)) fail(message);
}

function assertIncludesAny(text, needles, message) {
  if (!needles.some((needle) => text.includes(needle))) fail(message);
}

function assertNotIncludes(text, needle, message) {
  if (text.includes(needle)) fail(message);
}

function read(file) {
  return readFileSync(abs(file), 'utf8');
}

function unfoldManifest(text) {
  return text.replace(/\r?\n /g, '');
}

function abs(file) {
  return path.resolve(repoRoot, file);
}

function fail(message) {
  failures.push(message);
}
