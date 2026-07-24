# User Guide

This guide follows the current dual-input implementation. Model2Blockly can
generate from annotated `.ecore` metamodels or from `.m2b` textual DSL files.
The longer `.model2blockly` extension remains as a legacy alias.

## Install the Eclipse Plugin

Follow the [install guide](./INSTALL.md) for the full Eclipse update-site
workflow. The p2 repository URL is:

```text
https://plortinus.github.io/model2blockly/update-site/
```

The plugin contributes:

- an Xtext editor for `.m2b` and `.model2blockly` files;
- a workspace command named `Generate Blockly Editor`;
- a command named `Apply Validation Blocks to Source`;
- context-menu support for `.ecore`, `.m2b` and `.model2blockly` files.

## Choose an Input Route

| Input | Use it when | Adapter |
| --- | --- | --- |
| `.ecore` | An EMF metamodel already exists or EMF features such as inheritance, opposites and Ecore constraints are central. | `EcoreAdapter` |
| `.m2b` | A concise textual definition of blocks, categories, fields, references, validation and code templates is preferable. | `DomainModelAdapter` |
| `.model2blockly` | An existing project still uses the legacy extension. Its language is the same as `.m2b`. | `DomainModelAdapter` |

Both recommended routes produce the same EMF `EditorSpec` contract before
generation. Neither is a shortcut around the intermediate model.

## Generate in Eclipse

1. Import or open a project containing an `.ecore` or `.m2b` source.
2. Select the source in Project Explorer.
3. Right-click and choose `Generate Blockly Editor`.
4. Wait for the output directory to be refreshed.
5. Open `generation_report.html` and then `html/*_standalone.html`.

The equivalent Java entry points are:

```text
standalone/EcoreToBlocklyMain.java
standalone/Model2BlocklyToBlocklyMain.java
```

AppMaker provides checked-in examples of both routes:

| Route | Source | Generated output |
| --- | --- | --- |
| Ecore | `io.github.plortinus.model2blockly/model/app_maker.ecore` | `examples/generated/app_maker_ecore` |
| `.m2b` | `examples/app_maker.m2b` | `examples/generated/app_maker_dsl` |

All paths in this table are relative to the repository root.

## Generated Folder

The checked-in AppMaker outputs are:

```text
examples/generated/app_maker_ecore
examples/generated/app_maker_dsl
```

The generator writes:

| Path | Purpose |
| --- | --- |
| `generation_report.html` | Human-readable trace from the selected source to generated Blockly output. |
| `README.md` | Short guide for the generated folder. |
| `intermediate/Appmaker_blocklyspec.xmi` | Serialized EMF `EditorSpec` intermediate model. |
| `html/Appmaker_standalone.html` | Self-contained browser editor entry point. |
| `html/Appmaker_editor.html` | Editor page that loads generated Blockly assets. |
| `html/Appmaker_blocks.js` | Blockly block definitions. |
| `html/Appmaker_toolbox.js` | Toolbox and category structure. |
| `html/Appmaker_generators.js` | Code export logic generated from templates. |
| `html/Appmaker_validations.js` | Runtime validation rules for user-created models. |
| `html/validation_workspace.html` | Visual workspace for validation blocks. |
| `html/validation_blocks.json` | Validation block model data. |
| `html/validation_runtime.js` | Runtime used by the validation workspace. |
| `html/sample_model.json` | Representative model used by `Load Sample`. |

## Use the Generated Editor

Open either checked-in editor:

```text
examples/generated/app_maker_ecore/html/Appmaker_standalone.html
examples/generated/app_maker_dsl/html/Appmaker_standalone.html
```

Then:

1. Click `Load Sample`.
2. Inspect the blocks in the workspace.
3. Edit the model.
4. Export JSON, XML, domain XMI or generated code from the toolbar.
5. Open `validation_workspace.html` to inspect the generated validation rules
   as Blockly blocks.

![Generated AppMaker editor](../assets/screenshots/appmaker-editor.png)

![Validation workspace](../assets/screenshots/validation-workspace.png)

## Validate the Checked-in Output

From the repository root:

```bash
npm run smoke
npm run verify:domain-xmi
npm run verify:plugin
```

What these checks cover:

| Command | What it validates |
| --- | --- |
| `npm run smoke` | Opens the generated AppMaker editor with Playwright, loads the sample model and checks browser behavior. |
| `npm run verify:domain-xmi` | Loads the exported AppMaker domain XMI with EMF against `app_maker.ecore`. |
| `npm run verify:plugin` | Checks Eclipse plugin metadata, generated examples and HTML-only cleanup assumptions. |

## Continue Editing

If the result does not match the intended editor, modify the selected source
and generate again. In Ecore, adjust classes, attributes, references,
cardinalities or `blockly`, `ui`, `validation` and `code` annotations. In
`.m2b`, adjust `category`, `class`, `attribute`, `contains`, `reference`,
`value`, `validation` or code-template declarations.

Use the [Ecore mapping guide](./ECORE_TO_BLOCKLY_MAPPING.md) for the metamodel
route and the [textual DSL guide](./TEXTUAL_DSL.md) for `.m2b`.
