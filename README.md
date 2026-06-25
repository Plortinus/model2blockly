# Model2Blockly

Model2Blockly is an Eclipse-based model-driven generator for producing
Blockly-based DSL editors from EMF metamodel-level descriptions. The main
route follows the EMF workflow: load an Ecore metamodel, transform it into a
generated EMF intermediate model, persist that model as XMI, and generate the
browser editor from the reloaded model.

The Ecore input is transformed into a generated EMF `EditorSpec` instance that
conforms to the shared `BlocklyEditorSpec` intermediate metamodel. That model
is serialized to `intermediate/*_blocklyspec.xmi`, reloaded from XMI,
validated, and then generated into HTML/JavaScript Blockly editor artifacts.
The generated editor can export user-created blocks as a domain instance model
in JSON or XMI.

This is a model-driven pipeline, not a direct source-to-HTML shortcut:

```text
annotated .ecore
  -> EMF ResourceSet / EPackage
  -> model-to-model transformation
  -> generated EMF EditorSpec
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

The included AppMaker case study is based on an annotated Ecore metamodel:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

Generated editor:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

See [Running Example: AppMaker](RUNNING_EXAMPLE.md) for the annotated Ecore
source, the generated EMF intermediate model, and the generated editor
artifacts. It points to the AppMaker Ecore annotations, intermediate XMI,
generated JavaScript, screenshots, generation reports, exported domain XMI,
and validation workspace.

## Documentation

The hosted documentation is the VitePress site at
<https://plortinus.github.io/model2blockly/>. It is built from the Markdown
files under `docs/` and published directly at the GitHub Pages root.

- [VitePress home source](docs/README.md)
- [Install guide](docs/en/INSTALL.md)
- [User guide](docs/en/USER_GUIDE.md)
- [Architecture](docs/en/ARCHITECTURE.md)
- [AppMaker case](docs/en/RUNNING_EXAMPLE.md)
- [Ecore annotation reference](docs/en/ECORE_REFERENCE.md)
- [Troubleshooting](docs/en/TROUBLESHOOTING.md)
- [Release checklist](docs/en/RELEASE_CHECKLIST.md)
- [AppMaker `.ecore` example](io.github.plortinus.model2blockly/model/app_maker.ecore)

## Eclipse Usage

Import these projects into Eclipse:

```text
io.github.plortinus.model2blockly
io.github.plortinus.model2blockly.ide
io.github.plortinus.model2blockly.ui
io.github.plortinus.model2blockly.feature
io.github.plortinus.model2blockly.updatesite
```

The UI plugin contributes commands for selected `.ecore`
files:

- `Generate Blockly Editor`
- `Apply Validation Blocks to Source`

The bundles require JavaSE-21.

Generation uses a shared generated EMF `EditorSpec` intermediate XMI model. For
For Ecore files, generator-facing structural problems are reported before
generation, for example duplicate block feature names, invalid value-input
shadow blocks, unsupported OCL validation expressions, or invalid
`referenceLabelField` settings. The Eclipse generate command adds problem
markers to the source file when validation fails.

The Eclipse command and the standalone CLIs use the same path: they materialize
the intermediate XMI, reload it, validate the reloaded model, and only then emit
the Blockly editor files. If generation succeeds, the output folder contains
the `intermediate/*_blocklyspec.xmi` file that was used for HTML generation.

Generated validation rules are emitted both as executable JavaScript and as
`html/validation_blocks.json` plus `html/validation_workspace.html`. The visual
workspace can export `validation_blocks.edited.json`; the Eclipse command
`Apply Validation Blocks to Source` applies supported validation-rule edits back
to the selected `.ecore` source.

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
