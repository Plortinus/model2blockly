# Running Example: AppMaker

Languages: **English** | [Español](docs/es/README.md) | [中文](docs/zh/README.md)

This running example follows the same AppMaker domain through both supported
input routes and into the generated Blockly editor. It is the example to use
when explaining the proposal in the thesis because it shows the textual DSL
route, the Ecore annotation route, the shared EMF intermediate model, and the
final generated artifacts.

## Domain

AppMaker is a small low-code application builder. A model contains an `App`,
`DataSource` elements, `Page` elements, UI `Component` elements, actions, and
expressions. The generated editor lets a user assemble those elements visually
with Blockly blocks, validate the model, preview the app structure, and export
model/code artifacts.

Main source files:

```text
io.github.plortinus.model2blockly/examples/app_maker.model2blockly
io.github.plortinus.model2blockly/model/app_maker.ecore
```

Generated editors:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/html/AppMaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

## Route A: Textual DSL

The `.model2blockly` route is compact and is intended for authors who want to
describe the domain and the editor-facing metadata in one textual model.

```model2blockly
domain AppMaker codeLanguage "javascript" codeFileExtension "js" runtimeKind "appMaker"

category Pages label "Pages" colour 260
category Components label "Components" colour 160

class App category Pages colour 260 label "App" {
  attribute name : string [1..1] default "Task Tracker" required
    widget text uiLabel "App name" group "App" order 1
  contains DataSource dataSources [1..20]
    uiLabel "Data sources" group "Data" order 3
  contains Page pages [1..20]
    uiLabel "Pages" group "Pages" order 4
}
```

The full example adds code templates, widgets, validation constraints, reference
labels, value inputs, statement inputs, and toolbox categories.

## Route B: Ecore With Annotations

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

Use this route when the input already exists as EMF/Ecore, when Ecore
cardinalities and IDs should remain the source of truth, or when preserving
Ecore annotations is more important than a concise DSL syntax.

## Shared EMF Intermediate Model

Both routes are transformed to the same generated EMF metamodel:

```text
io.github.plortinus.model2blockly/model/generated/BlocklyEditorSpec.genmodel
io.github.plortinus.model2blockly/src-gen/io/github/plortinus/model2blockly/intermediate/blocklyspec/EditorSpec.java
```

The root object is `EditorSpec`. It is serialized as XMI, read back, validated,
and then passed to the Xtend/Java generators:

```text
.model2blockly / .ecore
  -> generated EMF EditorSpec
  -> intermediate/*_blocklyspec.xmi
  -> reload XMI as EditorSpec
  -> generated Blockly HTML/JavaScript
  -> user-created AppMaker instance JSON/XMI
```

Example intermediate files:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/intermediate/AppMaker_blocklyspec.xmi
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
html/validation_workspace.html
html/sample_model.json
generation_report.html
intermediate/*_blocklyspec.xmi
```

The generated Blockly editor can export the user-created model as JSON and as
EMF-style domain instance XMI. This exported `*_model.xmi` is different from
`intermediate/*_blocklyspec.xmi`: the intermediate XMI conforms to
`BlocklyEditorSpec`, while the exported model XMI uses the AppMaker domain
namespace. The editor does not currently perform a reverse transformation back
into the original `.model2blockly` or `.ecore` source file.

## Thesis Evidence

For the thesis, this example supports these figures or tables:

- the `.model2blockly` AppMaker source excerpt;
- the equivalent `.ecore` structure with `EAnnotation` metadata;
- the `BlocklyEditorSpec` / `EditorSpec` intermediate metamodel;
- the generated `intermediate/*_blocklyspec.xmi` file;
- screenshots of the generated AppMaker editor and validation workspace;
- a comparison table showing that both routes produce the same kind of Blockly
  editor through the same intermediate model.
