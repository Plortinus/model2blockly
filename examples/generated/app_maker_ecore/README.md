# Generated Blockly Editor

Input Ecore metamodel: `model/app_maker.ecore`

Output: EMF EditorSpec intermediate XMI and HTML Blockly editor

## Start Here

1. Open `generation_report.html` to inspect the Ecore -> Blockly mapping.
2. Inspect `intermediate/Appmaker_blocklyspec.xmi`, the generated EMF/XMI `EditorSpec` intermediate model.
3. Open `html/Appmaker_standalone.html` in a browser to use the generated editor.
4. Click `Load Sample` or import `html/sample_model.json` to inspect a representative model.
5. Edit the model, then export JSON/XML/XMI or code from the toolbar.
6. Open `html/validation_workspace.html` to inspect generated validation rules as Blockly blocks.
7. Use `Download Source Snippets` in the validation workspace to export Ecore/DSL validation snippets from edited rules.

## Typical Iteration

- If the structure is correct but the blocks are generic, add `source="blockly"` annotations for labels, colours, `message0` or tooltips.
- If field layout or inspector metadata should improve, add `source="ui"` annotations.
- If code export should be domain-specific, add `source="code"` annotations or templates.
- Rerun the same Eclipse launch configuration and reopen `generation_report.html`.
