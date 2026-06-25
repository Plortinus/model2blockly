# User Guide

This guide follows the current implementation. The recommended route starts
from an annotated Ecore metamodel. The Xtext syntax remains available for
`.m2b` and `.model2blockly` files, but it is an auxiliary route.

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

## Generate from Ecore

The reference input is:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

In Eclipse:

1. Import the plugin projects into the workspace.
2. Open the `io.github.plortinus.model2blockly` project.
3. Select `model/app_maker.ecore`.
4. Right-click and choose `Generate Blockly Editor`.
5. Open the generated `generation_report.html`.

The same Ecore route is implemented by:

```text
io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/EcoreToBlocklyMain.java
```

The checked-in launch configuration `Generate AppMaker from Ecore.launch`
passes these arguments:

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

## Generated Folder

The AppMaker generated output in this repository is:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

The generator writes:

| Path | Purpose |
| --- | --- |
| `generation_report.html` | Human-readable mapping from Ecore to generated Blockly output. |
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

Open:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
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

## When to Use the Xtext Route

Use `.m2b` or `.model2blockly` only when you need the auxiliary textual syntax.
The Eclipse command and `Model2BlocklyToBlocklyMain` still support it, and both
routes normalize into the same `EditorSpec` model before generation.

For the project narrative and the AppMaker case, use Ecore as the source of
truth.
