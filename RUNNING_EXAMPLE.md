# Running Example: AppMaker

This running example follows the AppMaker domain from an annotated Ecore
metamodel into a generated Blockly editor. It shows the Ecore source model, the
generated EMF intermediate model, the generated Blockly artifacts, and the
domain XMI exported by the generated editor in one consistent technical
example.

## Domain

AppMaker is a small low-code application builder. A model contains an `App`,
`DataSource` elements, `Page` elements, UI `Component` elements, actions, and
expressions. The generated editor lets a user assemble those elements visually
with Blockly blocks, validate the model, preview the app structure, and export
model/code artifacts.

Main source files:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

Generated editor:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

## Ecore With Annotations

The `.ecore` route starts from a normal EMF metamodel. Standard `EAnnotation`
entries add Blockly-specific metadata only where the structural Ecore model does
not already provide enough information.

```xml
<ecore:EPackage name="appmaker"
    nsURI="http://www.example.org/appmaker"
    nsPrefix="app">
  <eAnnotations source="blockly">
    <details key="workspace.renderer" value="zelos"/>
    <details key="workspace.trashcan" value="true"/>
  </eAnnotations>
  <eAnnotations source="code">
    <details key="language" value="javascript"/>
    <details key="fileExtension" value="js"/>
  </eAnnotations>

  <eClassifiers xsi:type="ecore:EClass" name="App">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Pages"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name"
        lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Task Tracker"/>
  </eClassifiers>
</ecore:EPackage>
```

This route keeps Ecore cardinalities, IDs, references and annotations as the
source of truth.

## Shared EMF Intermediate Model

The Ecore source is transformed to the generated EMF intermediate metamodel:

```text
io.github.plortinus.model2blockly/model/generated/BlocklyEditorSpec.genmodel
io.github.plortinus.model2blockly/src-gen/io/github/plortinus/model2blockly/intermediate/blocklyspec/EditorSpec.java
```

The root object is `EditorSpec`. It is serialized as XMI, read back, validated,
and then passed to the Xtend/Java generators:

```text
annotated .ecore
  -> generated EMF EditorSpec
  -> intermediate/*_blocklyspec.xmi
  -> reload XMI as EditorSpec
  -> generated Blockly HTML/JavaScript
  -> user-created AppMaker instance JSON/XMI
```

Example intermediate files:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi
```

The old hand-written Java representation is now only a compatibility and
diagnostic bridge. The persisted intermediate model used by generation is the
generated EMF `EditorSpec`.

## Generated Result

Each generation run produces a standalone editor, support assets, a generation
report, and the intermediate XMI used for generation:

```text
html/*_standalone.html
html/*_editor.html
html/*_blocks.js
html/*_toolbox.js
html/*_generators.js
html/*_validations.js
html/validation_workspace.html
html/validation_blocks.json
html/validation_runtime.js
html/sample_model.json
generation_report.html
intermediate/*_blocklyspec.xmi
```

The generated Blockly editor can export the user-created model as JSON and as
domain instance XMI. This exported `*_model.xmi` is different from
`intermediate/*_blocklyspec.xmi`: the intermediate XMI conforms to
`BlocklyEditorSpec`, while the exported model XMI uses the AppMaker domain
namespace. For the Ecore route, `npm run verify:domain-xmi` loads the exported
sample XMI with EMF against `model/app_maker.ecore` and validates it with
`Diagnostician`.

Validation rules have one extra workflow. `validation_workspace.html` displays
the generated rules as Blockly blocks and can export
`validation_blocks.edited.json`. The Eclipse command `Apply Validation Blocks
to Source` applies supported validation-rule edits back to the selected
`.ecore` file. This is an intentionally limited patch flow:
it covers rules with a clear source representation, not a full reverse
transformation from an arbitrary Blockly workspace back into the original
domain source.

A representative generated code fragment for AppMaker is the `ListView`
generator. It reads a dynamic reference and a value input from the block, then
serializes them into the generated textual model output:

```javascript
javascript.javascriptGenerator.forBlock['ListView'] = function(block) {
  var emptyText = block.getFieldValue('emptyText');
  var source = block.getFieldValue('source');
  var itemTitle =
    javascript.javascriptGenerator.valueToCode(block, 'itemTitle', 0) || 'null';

  return '{' +
    '"_type": "ListView", ' +
    '"_blockId": ' + JSON.stringify(block.id) + ', ' +
    '"emptyText": ' + JSON.stringify(emptyText) + ', ' +
    '"source": ' + JSON.stringify(source) + ', ' +
    '"itemTitle": ' + itemTitle +
  ' },\n';
};
```

## Technical Traceability

This example is useful when checking or documenting the generator because it
connects:

- the `.ecore` structure with `EAnnotation` metadata;
- the `BlocklyEditorSpec` / `EditorSpec` intermediate metamodel;
- the generated `intermediate/*_blocklyspec.xmi` file;
- the EMF-loadable AppMaker domain XMI exported by the Ecore-generated editor;
- the generated JavaScript block/code generator excerpt;
- screenshots of the generated AppMaker editor, generation reports and
  validation workspace.

The checked-in generated examples live under `examples/generated/...` so they
can be opened immediately from the repository. A fresh Eclipse generation from
the source file writes a sibling folder such as `model/app_maker_generated/`.
