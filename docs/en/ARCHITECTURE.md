# Architecture and Implementation

Model2Blockly is organized as a model-driven engineering pipeline. Its primary
route starts from an annotated Ecore metamodel, transforms the loaded EMF
`EPackage` into a generated EMF `EditorSpec` model, persists that intermediate
model as XMI, and then generates the Blockly editor from the reloaded model.

## EMF/MDE workflow alignment

The project follows the standard EMF pattern described in the Eclipse
[EMF documentation](https://eclipse.dev/emf/docs.html): metamodels are defined
as Ecore models, generator-facing metamodels have `.genmodel` files and
generated Java APIs, model instances are serialized as XMI, and generation is
performed from models rather than from ad hoc text.

For Model2Blockly, the core chain is:

```text
annotated Ecore metamodel (.ecore)
  -> EMF ResourceSet / EPackage
  -> model-to-model transformation
  -> generated EMF EditorSpec model
  -> intermediate/*_blocklyspec.xmi
  -> reload and validate XMI
  -> model-to-text generation
  -> Blockly HTML/JavaScript editor
  -> user-created domain model JSON/XMI
```

The internal generation metamodels are kept as EMF artifacts:

```text
io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore
io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.genmodel
io.github.plortinus.model2blockly/model/blockly_editor_spec.ecore
io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel
io.github.plortinus.model2blockly/emf-gen/
```

The textual DSL is Xtext concrete syntax over the fixed
`Model2Blockly.ecore` abstract syntax. Xtext generation writes parser, service
and IDE code to `src-gen`; generated EMF APIs for the fixed metamodels live in
`emf-gen` so they are not cleaned when the DSL infrastructure is regenerated.

The Ecore route can also operate dynamically on a source `.ecore` file without
requiring Java code generation for that source domain. The domain `EPackage` is
loaded through EMF and used as the source model for the transformation.

## System architecture

Model2Blockly has one documented MDE route: annotated Ecore is loaded as an EMF
`EPackage`, transformed into the generated `EditorSpec` model, and then used by
the generation backend before HTML and JavaScript are written.

![Model2Blockly system architecture](../assets/diagrams/system-architecture.svg)

## Generation flow

The generator writes an intermediate XMI file and reads it back before producing
the browser artifacts. This makes the formal generated model visible,
reproducible and easier to debug.

![Model2Blockly generation flow](../assets/diagrams/generation-flow.svg)

## Output artifacts

The generated folder is intentionally split between files for users, files for
runtime execution and files that help explain or debug the generation.

![Generated output artifacts](../assets/diagrams/output-artifacts.svg)

## Core and extension boundary

The core contribution is the metamodel-to-Blockly editor pipeline:

- annotated Ecore input;
- generated EMF `EditorSpec` intermediate model;
- intermediate XMI serialization, reload and validation;
- Blockly block/toolbox/editor generation;
- one representative case study, AppMaker.

The following features are useful extensions but are not the architectural
center of the project:

- generated code export from user models;
- visual validation-rule workspace;
- Eclipse update-site packaging;
- AppMaker-specific browser preview.

## Implementation map

| Responsibility | Main implementation |
| --- | --- |
| Ecore annotations to `EditorSpec` conversion | [`EcoreAdapter.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java) |
| Intermediate XMI persistence | [`BlocklySpecXmiSerializer.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java) |
| Intermediate validation | [`BlocklyEditorSpecValidator.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/blocklyspec/BlocklyEditorSpecValidator.java) |
| Blockly HTML and JavaScript output | [`BlocklyCodeGenerator.xtend`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend) |
| End-to-end generation orchestration | [`Model2BlocklyGenerator.xtend`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/Model2BlocklyGenerator.xtend) |
| EMF load/validate check for exported domain XMI | [`verify-domain-xmi.mjs`](../../scripts/verify-domain-xmi.mjs) |

## Implementation Notes

- The generated editor is model-driven: Blockly blocks are derived from the
  source model instead of being hand-written.
- The intermediate XMI file is not just a debug dump. It is reloaded and
  validated before final output generation.
- The Ecore-based AppMaker generated editor exports a sample domain XMI that is
  checked by `npm run verify:domain-xmi`: the script loads `app_maker.ecore`,
  registers its dynamic `EPackage`, loads the exported XMI with EMF and runs
  `Diagnostician`.
- The AppMaker example shows Ecore annotations, intermediate XMI, generated
  JavaScript, screenshots, generation report and validation workspace in one
  place.
