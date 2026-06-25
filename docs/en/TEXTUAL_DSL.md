# Model2Blockly Textual DSL

The Model2Blockly textual DSL uses `.m2b` files. The longer `.model2blockly`
extension is still accepted as a legacy alias, but documentation and examples
use `.m2b`.

This DSL is a language-definition DSL. It describes the blocks, fields,
connections, categories and validation rules of a block-based language. It is
used by language engineers or tool developers, not by the final domain user.
The generated Blockly editor is what domain users interact with to create
models.

| Object | User | Purpose |
| --- | --- | --- |
| `.m2b` textual DSL | Language engineers and tool developers | Defines the Blockly language and editor structure. |
| Generated Blockly editor | Domain users | Creates domain models by dragging blocks. |

## MDE Route

```text
.m2b source file
  -> Xtext parser
  -> DomainModel
  -> DomainModelAdapter
  -> EditorSpec EMF intermediate model
  -> BlocklyCodeGenerator.xtend
  -> generated Blockly editor for domain users
```

The DSL route shares the same intermediate model and generator as the Ecore
route:

```text
Ecore route: .ecore -> EcoreAdapter -> EditorSpec
DSL route:   .m2b   -> DomainModelAdapter -> EditorSpec
```

## Related Files

| Item | Location |
| --- | --- |
| Xtext grammar | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext` |
| DSL metamodel | `io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore` |
| DSL adapter | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java` |
| Standalone DSL entry point | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/Model2BlocklyToBlocklyMain.java` |
| AppMaker DSL example | `io.github.plortinus.model2blockly/examples/app_maker.m2b` |

## Syntax Sketch

```model2blockly
domain Appmaker

category Pages label "Pages" colour 260

class App category Pages {
  attribute name : string required
  contains Page pages [1..20]
}

class Page category Pages {
  attribute title : string required
  contains Component components [1..40]
}

abstract class Component category Components {
}
```

## Ecore Annotations vs `.m2b`

| Aspect | Ecore + annotations | `.m2b` textual DSL |
| --- | --- | --- |
| Input | Standard EMF `.ecore` metamodel. | Xtext textual model. |
| Best use | Reuse an existing EMF metamodel. | Write a compact language definition. |
| Readability | Verbose XML/XMI with annotations. | Compact and easier to show in documentation. |
| Transformation | `EcoreAdapter -> EditorSpec`. | `DomainModelAdapter -> EditorSpec`. |

Both routes are model-driven and converge on the same EMF `EditorSpec`
intermediate model.
