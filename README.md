# Model2Blockly

Languages: **English** | [Español](docs/es/README.md) | [中文](docs/zh/README.md)

Model2Blockly is an Eclipse/Xtext-based generator for producing Blockly editors
from domain models. It supports two input routes:

- a textual `.model2blockly` language;
- annotated Ecore models.

Both routes are transformed into a generated EMF `EditorSpec` instance that
conforms to the shared `BlocklyEditorSpec` intermediate metamodel, serialized
to `intermediate/*_blocklyspec.xmi`, reloaded from that XMI, and then generated
into HTML/JavaScript Blockly editor artifacts. The generated editor can then
export user-created blocks as a domain instance model in JSON or EMF-style XMI.

This is a model-driven pipeline, not a direct source-to-HTML shortcut:

```text
.model2blockly / .ecore
  -> generated EMF EditorSpec
  -> intermediate/*_blocklyspec.xmi
  -> reload XMI
  -> generated Blockly HTML/JavaScript
  -> user-created domain instance JSON/XMI
```

There are two different XMI artifacts in the project:

- `intermediate/*_blocklyspec.xmi` is an `EditorSpec` instance used inside the
  generator pipeline.
- `*_model.xmi` exported from a generated Blockly editor is a domain instance
  model using the source domain namespace.

## Repository Layout

```text
io.github.plortinus.model2blockly/           Core language, adapters, generators and examples
io.github.plortinus.model2blockly.ide/       Xtext IDE support
io.github.plortinus.model2blockly.ui/        Eclipse UI plugin
io.github.plortinus.model2blockly.feature/   Eclipse feature definition
io.github.plortinus.model2blockly.updatesite/ Eclipse p2 update site
docs/                            Localized documentation
site/                            GitHub Pages landing page and update-site page
.github/workflows/               GitHub Pages publishing workflow
scripts/                          Verification helpers
```

## Running Example

The included AppMaker example is available through both supported routes:

```text
io.github.plortinus.model2blockly/examples/app_maker.model2blockly
io.github.plortinus.model2blockly/model/app_maker.ecore
```

Generated editors:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/html/AppMaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

See [Running Example: AppMaker](RUNNING_EXAMPLE.md) for the side-by-side DSL
and Ecore routes, the generated EMF intermediate model, and the generated
editor artifacts.

## Documentation

- [Documentation languages](docs/README.md) for English, Spanish and Chinese
- [Documentation map](DOCS.md) for choosing the right guide by task
- [Running Example: AppMaker](RUNNING_EXAMPLE.md) for the thesis-style
  end-to-end example
- [Getting started](GETTING_STARTED.md) for generating your first editor
- [Troubleshooting](TROUBLESHOOTING.md) for installation, generation and
  validation issues
- [Release checklist](RELEASE_CHECKLIST.md) for rebuilding the update site,
  verifying it, and publishing GitHub Pages
- [Model2Blockly Language Reference](DSL_REFERENCE.md) with Blockly field, toolbox,
  workspace and code-generation mappings
- [Ecore Annotation Reference](ECORE_REFERENCE.md) with supported EAnnotation
  sources, keys and Blockly mappings
- [Xtext grammar](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- [AppMaker `.model2blockly` example](io.github.plortinus.model2blockly/examples/app_maker.model2blockly)
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

The UI plugin contributes commands for selected `.model2blockly` and `.ecore`
files:

- `Generate Blockly Editor`
- `Apply Validation Blocks to Source`

The bundles require JavaSE-21.

Generation uses a shared generated EMF `EditorSpec` intermediate XMI model. For
`.model2blockly` files, generator-facing structural problems are also reported by
the Xtext editor before generation, for example duplicate block feature names,
invalid value-input shadow blocks, unsupported OCL validation expressions, or
invalid `referenceLabelField` settings. The Eclipse generate command adds
problem markers to the source file when validation fails.

The Eclipse command and the standalone CLIs use the same path: they materialize
the intermediate XMI, reload it, validate the reloaded model, and only then emit
the Blockly editor files. If generation succeeds, the output folder contains
the `intermediate/*_blocklyspec.xmi` file that was used for HTML generation.

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
  enabled so Eclipse can resolve Xtext and EMF dependencies.
- If an older copy of the update site appears empty, clear
  `Group items by category` in the install dialog and reload the site.
- The GitHub Pages workflow publishes the committed p2 repository. Rebuild and
  verify it locally before pushing. It also renders the Markdown documentation
  into hosted pages under `/docs/`.

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

The DSL validation bridge and validation patch round-trip checks require
Eclipse plugins and a Java 21 runtime. The scripts use the default Eclipse.app
installation on macOS; set these variables when your Eclipse installation lives
elsewhere:

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
npm run verify:dsl-validation
npm run verify:patch
```
