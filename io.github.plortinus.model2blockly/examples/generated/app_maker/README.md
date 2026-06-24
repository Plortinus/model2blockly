# Generated Blockly Editor

Input DSL model: `io.github.plortinus.model2blockly/examples/app_maker.model2blockly`

Output: EMF EditorSpec intermediate XMI and HTML Blockly editor

## Start Here

1. Open `generation_report.html` to inspect the DSL -> Blockly mapping.
2. Inspect `intermediate/AppMaker_blocklyspec.xmi`, the generated EMF/XMI `EditorSpec` intermediate model.
3. Open `html/AppMaker_standalone.html` in a browser to use the generated editor.
4. Click `Load Sample` or import `html/sample_model.json` to inspect a representative model.
5. Edit the model, then export JSON/XML/XMI or code from the toolbar.
6. Open `html/validation_workspace.html` to inspect generated validation rules as Blockly blocks.
7. Use `Download Source Snippets` in the validation workspace to export Ecore/DSL validation snippets from edited rules.

## Typical Iteration

- Edit the `.model2blockly` source if the domain structure should change.
- Use labels, colours, widgets, `referenceLabelField`, `shadow` and `code` templates to improve the generated editor.
- Rerun the same Eclipse launch configuration and reopen `generation_report.html`.
