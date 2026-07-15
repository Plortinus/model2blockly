# Model2Blockly

Model2Blockly is an Eclipse-based, model-driven generator that produces
browser-ready Blockly DSL editors from either annotated Ecore metamodels or
`.m2b` textual DSL specifications. Both input routes normalize their source
model into the same generated EMF `EditorSpec`, persist it as XMI, reload and
validate it, and use it to generate the HTML/JavaScript editor.

`EditorSpec` is the serializable root type defined by
`model/blockly_editor_spec.ecore`. The hand-written Java class
`BlocklyEditorSpec` is an auxiliary construction view used by the adapters; it
is not the root object stored in `intermediate/*_blocklyspec.xmi`. Generated
editors can serialize user-created block models as JSON and, where applicable,
as domain-instance XMI.

This is a model-driven pipeline, not a direct source-to-HTML shortcut:

```text
annotated .ecore -> EPackage    -> EcoreAdapter -------\
                                                        -> EMF EditorSpec
.m2b             -> DomainModel -> DomainModelAdapter --/
                                                        -> intermediate/*_blocklyspec.xmi
                                                        -> reload and validate XMI
                                                        -> model-to-text generation
                                                        -> generated Blockly HTML/JavaScript
                                                        -> user-created domain instance JSON/XMI
```

There are two different XMI artifacts in the project:

- `intermediate/*_blocklyspec.xmi` is an `EditorSpec` instance used inside the
  generator pipeline.
- `*_model.xmi` exported from a generated Blockly editor is a domain instance
  model using the source domain namespace. The Ecore-based AppMaker export is
  regression-checked by loading it with EMF against `app_maker.ecore`.

## Repository Layout

```text
io.github.plortinus.model2blockly/           Core language, adapters, generators and examples
io.github.plortinus.model2blockly.ide/       Eclipse IDE support
io.github.plortinus.model2blockly.ui/        Eclipse UI plugin
io.github.plortinus.model2blockly.feature/   Eclipse feature definition
io.github.plortinus.model2blockly.updatesite/ Eclipse p2 update site
docs/                            Localized documentation source for VitePress
site/                            GitHub Pages landing page and update-site page
.github/workflows/               GitHub Pages publishing workflow
scripts/                          Verification helpers
```

## Running Example

AppMaker is the checked-in end-to-end integration example. It is available
through both input routes so their generated outputs can be inspected side by
side:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
io.github.plortinus.model2blockly/examples/app_maker.m2b
```

Generated editors:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/html/Appmaker_standalone.html
```

See [Running Example: AppMaker](RUNNING_EXAMPLE.md) for the two sources, their
generated EMF intermediate models, browser editors, reports and validation
artifacts. AppMaker demonstrates integration; the separate ten-editor corpus
described below provides the external evaluation evidence.

## Documentation

The hosted documentation is the VitePress site at
<https://plortinus.github.io/model2blockly/>. It is built from the Markdown
files under `docs/` and published directly at the GitHub Pages root.

- [VitePress home source](docs/README.md)
- English docs under [`docs/en`](docs/en/README.md)
- Spanish docs under [`docs/es`](docs/es/README.md)
- Chinese docs under [`docs/zh`](docs/zh/README.md)
- [AppMaker `.ecore` example](io.github.plortinus.model2blockly/model/app_maker.ecore)
- [AppMaker `.m2b` example](io.github.plortinus.model2blockly/examples/app_maker.m2b)
- [External evaluation results](evaluation/official-blockly/results/summary.md)

## Eclipse Usage

Import these projects into Eclipse:

```text
io.github.plortinus.model2blockly
io.github.plortinus.model2blockly.ide
io.github.plortinus.model2blockly.ui
io.github.plortinus.model2blockly.feature
io.github.plortinus.model2blockly.updatesite
```

The UI plugin contributes commands for selected `.ecore`, `.m2b` and legacy
`.model2blockly` files:

- `Generate Blockly Editor`
- `Apply Validation Blocks to Source`

The bundles require JavaSE-21.

Generation uses the shared generated EMF `EditorSpec` intermediate XMI model.
Generator-facing structural problems are reported before generation, for
example duplicate block feature names, invalid value-input shadow blocks,
unsupported validation expressions or invalid reference-label settings. The
Eclipse generate command adds problem markers to the source file when
validation fails.

The Eclipse command and the standalone CLIs use the same path: they materialize
the intermediate XMI, reload it, validate the reloaded model, and only then emit
the Blockly editor files. If generation succeeds, the output folder contains
the `intermediate/*_blocklyspec.xmi` file that was used for HTML generation.

Generated validation rules are emitted both as executable JavaScript and as
`html/validation_blocks.json` plus `html/validation_workspace.html`. The visual
workspace can export `validation_blocks.edited.json`; the Eclipse command
`Apply Validation Blocks to Source` applies supported validation-rule edits back
to supported source files.

## External Evaluation

AppMaker is not used as the sole evaluation case. A reproducible corpus compares
Model2Blockly with ten editor configurations extracted from the official
Blockly Games and Blockly samples repositories. The comparison covers the
Blockly editing subsystem: block structure, fields, inputs, connections,
toolbox configuration, initial workspace state and code generators. Host
application layers such as the Maze map, Turtle canvas, Music audio engine and
Pond simulation are deliberately excluded.

| Measure | Result |
| --- | ---: |
| Cases loaded without errors | 10/10 |
| Weighted editor structural parity | 2315/2762 (83.82%) |
| Weighted generator parity | 227/291 (78.01%) |
| Official source LOC / `.m2b` LOC | 4074 / 1121 |
| Weighted LOC reduction | 72.48% |
| Conservative deduplicated LOC reduction | 70.00% |
| Strict Ecore–`.m2b` descriptor equivalence | 3/3 cases |

All ten generated editors load, but all ten are classified as partial
reproductions. The results therefore do not claim equivalence of the complete
host applications or measure developer productivity. See the
[evaluation overview](evaluation/official-blockly/README.md),
[protocol](evaluation/official-blockly/protocol.md),
[aggregate results](evaluation/official-blockly/results/summary.md) and
[observed support boundaries](evaluation/official-blockly/support-boundaries.md).

## Update Site

The hosted p2 repository is available at:

```text
https://plortinus.github.io/model2blockly/update-site/
```

In Eclipse, use:

```text
Help -> Install New Software... -> Add...
```

and paste the URL above.

The local generated p2 repository is also available at:

```text
io.github.plortinus.model2blockly.updatesite/repository/
```

In Eclipse, use:

```text
Help -> Install New Software... -> Add... -> Local...
```

and select the repository folder above.

Requirements and troubleshooting:

- Run Eclipse with JDK 21; the bundles require JavaSE-21.
- Keep `Contact all update sites during install to find required software`
  enabled so Eclipse can resolve EMF dependencies.
- If an older copy of the update site appears empty, clear
  `Group items by category` in the install dialog and reload the site.
- The GitHub Pages workflow publishes the committed p2 repository. Rebuild and
  verify it locally before pushing. It also renders the VitePress documentation
  directly into the hosted site root.

For release publishing, use the fixed checklist in
[`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md).

## Verification

Install Node dependencies once:

```bash
npm install
```

Run the basic repository checks:

```bash
npm run verify:docs
npm run verify:plugin
npm run smoke
npm run verify:evaluation-completed
```

The EMF domain XMI check and validation patch round-trip checks require
Eclipse plugins and a Java 21 runtime. The scripts use the default Eclipse.app
installation on macOS; set these variables when your Eclipse installation
lives elsewhere:

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
npm run verify:domain-xmi
npm run verify:patch
```
