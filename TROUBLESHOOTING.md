# Troubleshooting

Use this page when installation or generation does not behave as expected.

## Install dialog shows no items

Symptoms:

```text
There are no categorized items
```

Checks:

1. Confirm the update site URL is exactly:

```text
https://plortinus.github.io/model2blockly/update-site/
```

2. In Eclipse `Help -> Install New Software...`, try clearing
   `Group items by category`.
3. Click `Manage...`, remove any stale Model2Blockly site entry, add the URL
   again, and reload.
4. Keep `Contact all update sites during install to find required software`
   enabled so Eclipse can resolve EMF dependencies.
5. If you use a local update site, choose the repository folder:

```text
io.github.plortinus.model2blockly.updatesite/repository/
```

Do not choose the parent project folder.

## Eclipse does not show a newer plugin version

Likely causes:

- The local p2 repository was not regenerated.
- Eclipse cached the old repository metadata.
- The bundle/feature version was not bumped.
- `Hide items that are already installed` is hiding the installed version.

Checks:

1. In the install dialog, clear `Hide items that are already installed`.
2. Remove and re-add the update site from `Manage...`.
3. For the local repository, run:

```bash
npm run rebuild:update-site
npm run verify:plugin
```

4. Confirm the update site contains the expected versioned feature and plugin
   jars under:

```text
io.github.plortinus.model2blockly.updatesite/repository/features/
io.github.plortinus.model2blockly.updatesite/repository/plugins/
```

For maintainer-side release steps, use
[`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md).

## `Generate Blockly Editor` command is missing

Checks:

1. Select exactly one file.
2. The selected file must end in `.ecore`.
3. Use Project Explorer, Package Explorer, or the top-level `Model2Blockly` menu.
4. Confirm the plugin is installed in `Help -> About Eclipse IDE ->
   Installation Details`.
5. Restart Eclipse after installing or updating the plugin.

## Generation output appears in the wrong place

The Eclipse command writes a sibling folder next to the selected file:

```text
<input-file-name-without-extension>_generated
```

Examples:

| Selected file | Output folder |
| --- | --- |
| `model/app_maker.ecore` | `model/app_maker_generated/` |

After generation, Eclipse refreshes the project and opens
`generation_report.html`. If you do not see the folder, refresh the project
manually.

## `Cannot find model/blockly_editor_spec.ecore`

Symptom:

```text
Could not transform BlocklyEditorSpec to EMF model:
Cannot find model/blockly_editor_spec.ecore ...
```

This indicates an old or incorrectly packaged plugin. The current plugin loads
the metamodel from the installed bundle classpath.

Fix:

1. Install the current Model2Blockly version from the update site or local p2
   repository.
2. Restart Eclipse.
3. Confirm the installed plugin version in `Installation Details`.
4. Regenerate from the selected `.ecore` file.

## Intermediate XMI is missing or stale

Every successful generation should write:

```text
intermediate/*_blocklyspec.xmi
```

That file is not just a debug dump. The generator serializes the generated EMF
`EditorSpec` to XMI, reloads it, validates the reloaded model, and uses that
model to generate the Blockly HTML/JavaScript files.

If the output folder has HTML files but no intermediate XMI, or if the installed
plugin does not mention the XMI pipeline, the installed plugin/update-site is
stale. Rebuild and reinstall the local update site, then regenerate the editor.

## `.ecore` generation fails

Checks:

1. Confirm the `.ecore` file root is an `EPackage`.
2. Ensure referenced classifiers resolve inside the model or imported packages.
3. Derived, transient, volatile, and non-changeable features are skipped by
   design.
4. For value inputs, annotate the `EReference`:

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="shadow" value="TextLiteral"/>
</eAnnotations>
```

Useful reference:

- [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md)

## Reference dropdown is empty

Reference fields list compatible blocks already present in the workspace.

Checks:

1. Create at least one target block in the workspace.
2. Confirm the reference target type matches the target block type or its
   connection type.
3. Add a label field so choices are readable:

```xml
<eAnnotations source="ui">
  <details key="referenceLabelField" value="name"/>
</eAnnotations>
```

## Value blocks will not connect

Checks:

```xml
<eAnnotations source="blockly">
  <details key="output" value="true"/>
</eAnnotations>
```

and on the reference used as a socket:

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="check" value="TextExpression"/>
</eAnnotations>
```

The generated Blockly `check` values must be compatible.

## Validation warnings look unexpected

Generated validations come from:

| Source | Validation |
| --- | --- |
| Ecore `lowerBound >= 1` | Required field/reference |
| Ecore containment bounds | Statement input cardinality |
| Ecore `iD=true` | Type-level uniqueness |
| Ecore multi-valued unique fields/references | Value uniqueness |
| Ecore `source="validation" mustFollow` | Previous-block rule |
| Ecore validation/OCL annotations | Expression rule |

Open `validation_workspace.html` in the generated `html/` folder to inspect
validation rules as blocks.

## Generated editor opens but looks stale

Checks:

1. Regenerate the editor.
2. Refresh the Eclipse project.
3. Hard-refresh the browser.
4. Confirm you opened the generated file under the current output folder, not a
   committed sample under `examples/generated/`.

## Local verification commands

Install Node dependencies once:

```bash
npm install
```

Useful checks:

```bash
npm run verify:plugin
npm run verify:domain-xmi
npm run verify:patch
npm run smoke
```

The EMF domain XMI and validation patch checks need Eclipse plugins and JDK 21.
The repository scripts use the default Eclipse.app installation on macOS; set
these variables when your Eclipse installation lives elsewhere:

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
npm run verify:domain-xmi
npm run verify:patch
```
