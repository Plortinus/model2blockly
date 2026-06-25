# AppMaker Case

AppMaker is the checked-in reference case for the Ecore-first route.

## Source and Output

| Item | Path |
| --- | --- |
| Source metamodel | `io.github.plortinus.model2blockly/model/app_maker.ecore` |
| Generated output | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore` |
| Standalone editor | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html` |
| Intermediate model | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi` |
| Mapping report | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` |

The public demo path is:

```text
https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
```

## Rebuild the Example

In Eclipse, run the launch configuration:

```text
Generate AppMaker from Ecore.launch
```

It runs:

```text
io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain
```

with:

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

The generation sequence is:

```text
app_maker.ecore
  -> EPackage
  -> EcoreAdapter.toEditorSpec(...)
  -> Appmaker_blocklyspec.xmi
  -> XMI reload
  -> BlocklyCodeGenerator
  -> html/*
```

## What to Inspect

Open the files in this order:

1. `generation_report.html` to see how Ecore classes and features map to
   Blockly blocks, fields and inputs.
2. `intermediate/Appmaker_blocklyspec.xmi` to inspect the generated EMF
   intermediate model.
3. `html/Appmaker_standalone.html` to use the generated editor.
4. `html/validation_workspace.html` to inspect validation rules as Blockly
   blocks.
5. `html/sample_model.json` to see the model loaded by `Load Sample`.

## Generated Editor Behavior

The standalone editor supports:

- loading the checked-in sample model;
- editing a Blockly workspace;
- viewing JSON and generated code;
- exporting JSON, XML and domain XMI;
- reporting validation issues;
- using generated reference dropdowns and multivalue fields;
- switching between build mode and developer inspection views.

The generated domain XMI is checked by:

```bash
npm run verify:domain-xmi
```

That script loads `app_maker.ecore`, registers the dynamic `EPackage`, loads the
exported XMI and runs EMF diagnostics.

## Smoke Test

Run:

```bash
npm run smoke
```

The smoke test starts a local static server, opens the generated standalone
editor with Playwright, clicks `Load Sample`, checks validation output and
verifies the domain-XMI export shape.

## Why This Case Matters

AppMaker exercises the current project architecture:

- annotated Ecore input;
- intermediate EMF `EditorSpec` XMI;
- static Blockly HTML/JavaScript generation;
- runtime validation output;
- generated validation workspace;
- sample model export and EMF validation.

It is the example to keep in sync when changing the adapter, generator,
validation runtime or GitHub Pages publishing workflow.
