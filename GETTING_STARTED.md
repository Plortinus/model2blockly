# Getting started

Languages: **English** | [Español](docs/es/GETTING_STARTED.md) | [中文](docs/zh/GETTING_STARTED.md)

This tutorial walks through the fastest path from an installed plugin to a
generated Blockly editor.

## What you will do

1. Open a generated example to see the expected result.
2. Generate a new editor from the `.model2blockly` example.
3. Generate the equivalent editor from the `.ecore` example.
4. Inspect the generated report and decide what to edit next.

## Prerequisites

- Eclipse running on JDK 21.
- The Model2Blockly Eclipse plugin installed, or these projects imported into an
  Eclipse workspace:

```text
io.github.plortinus.model2blockly
io.github.plortinus.model2blockly.ide
io.github.plortinus.model2blockly.ui
io.github.plortinus.model2blockly.feature
io.github.plortinus.model2blockly.updatesite
```

- A browser for opening generated HTML files.

For installation details, see the [README update site section](README.md#update-site).
For install problems, see [TROUBLESHOOTING.md](TROUBLESHOOTING.md).

## Visual walkthrough

Use these screenshots as a quick check for the normal workflow: copy the
update-site URL, open a generated AppMaker editor, then inspect the validation
rules workspace.

![Install section on the Model2Blockly project site](docs/assets/screenshots/homepage-install-en.png)

The project site shows the p2 update-site URL used by Eclipse's
`Install New Software` dialog.

![Generated AppMaker editor with a sample model loaded](docs/assets/screenshots/appmaker-editor.png)

After generation, the standalone HTML editor should open with a toolbox,
workspace, preview, and `Load Sample` button.

![Validation workspace showing generated validation rules](docs/assets/screenshots/validation-workspace.png)

The validation workspace is a separate generated page that shows model rules as
Blockly blocks, so you can inspect what the generator produced.

## Try the generated examples first

The repository includes generated AppMaker editors. Open one of these files:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/html/AppMaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

In the generated editor:

1. Click `Load Sample`.
2. Move a few blocks.
3. Check the generated code/output panel.
4. Open the matching `html/validation_workspace.html` if you want to inspect
   validation rules as Blockly blocks.
5. Use `More -> Export XMI` to download the current blocks as a domain instance
   model.

The hosted examples are also linked from the project site:

```text
https://plortinus.github.io/model2blockly/
```

## Generate from `.model2blockly`

Use the textual route when you want to write a domain model by hand.

1. In Eclipse, import or open this repository.
2. In Project Explorer or Package Explorer, select:

```text
io.github.plortinus.model2blockly/examples/app_maker.model2blockly
```

3. Right-click the file and choose `Generate Blockly Editor`.
4. Wait for Eclipse to finish generation.
5. Eclipse should open:

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/generation_report.html
```

6. Open the generated standalone editor:

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/html/AppMaker_standalone.html
```

The output folder is always a sibling folder named:

```text
<input-file-name-without-extension>_generated
```

If generation fails, open the Eclipse `Problems` view and the selected
`.model2blockly` file. Syntax/linking errors and generator-facing intermediate
model errors are reported on the source file. Messages may include an internal
path such as `block[Task].assignee`; that path names the generated `EditorSpec`
element that could not be generated safely.

## Generate from `.ecore`

Use the Ecore route when you already have an EMF metamodel or want to model
directly with EMF concepts such as packages, subpackages, `EAnnotation`,
`EReference.eOpposite`, or existing generated EMF code.

1. In Project Explorer or Package Explorer, select:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

2. Right-click the file and choose `Generate Blockly Editor`.
3. Eclipse should open:

```text
io.github.plortinus.model2blockly/model/app_maker_generated/generation_report.html
```

4. Open the generated standalone editor:

```text
io.github.plortinus.model2blockly/model/app_maker_generated/html/Appmaker_standalone.html
```

## Understand the output folder

Each generated folder contains:

| File or folder | Purpose |
| --- | --- |
| `generation_report.html` | First file to inspect after each generation run |
| `README.md` | Short instructions inside the output folder |
| `html/*_standalone.html` | Main generated editor to open in a browser |
| `html/*_editor.html` | Embeddable editor page |
| `html/*_blocks.js` | Generated Blockly block definitions |
| `html/*_toolbox.js` | Generated toolbox |
| `html/*_generators.js` | Generated code generators |
| `html/*_validations.js` | Generated validation runtime hooks |
| `html/validation_workspace.html` | Visual validation rule workspace |
| `html/sample_model.json` | Sample model for the generated editor |
| `intermediate/*_blocklyspec.xmi` | Formal EMF/XMI intermediate model reloaded before HTML generation |

Start with `generation_report.html`. It tells you what the generator inferred
from the source model and which files were written.

Do not confuse the two XMI roles: `intermediate/*_blocklyspec.xmi` is the
generator's `EditorSpec` model, while `More -> Export XMI` in the generated
editor writes a user-created domain instance model.

## Make your first small edit

For `.model2blockly`, try changing a label or colour:

```model2blockly
class Button extends Component category Components colour 160 label "Button"
```

For `.ecore`, try changing class annotation metadata:

```xml
<eAnnotations source="blockly">
  <details key="colour" value="160"/>
  <details key="category" value="Components"/>
  <details key="tooltip" value="Button component with click actions."/>
</eAnnotations>
```

Regenerate the editor and reopen `generation_report.html`. If the structural
mapping is correct but the block is hard to read, adjust labels, colours,
toolbox categories, `inline`/`inputsInline`, field widgets, or code templates.

## What to read next

| Need | Read |
| --- | --- |
| Textual DSL syntax | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| Ecore annotations | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| Common installation and generation issues | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| Choosing the right document by task | [`DOCS.md`](DOCS.md) |
