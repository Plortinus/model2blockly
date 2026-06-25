# DSL textual de Model2Blockly

El DSL textual de Model2Blockly utiliza archivos `.m2b`. La extensión larga
`.model2blockly` se mantiene como alias de compatibilidad, pero la documentación
y los ejemplos usan `.m2b`.

Este DSL define la estructura de un lenguaje basado en bloques: bloques, campos,
conexiones, categorías y validaciones. Lo usan los ingenieros de lenguaje o
desarrolladores de herramientas, no el usuario final del dominio. El usuario
final interactúa con el editor Blockly generado para crear modelos.

| Objeto | Usuario | Propósito |
| --- | --- | --- |
| DSL textual `.m2b` | Ingenieros de lenguaje y desarrolladores de herramientas | Define el lenguaje Blockly y la estructura del editor. |
| Editor Blockly generado | Usuarios del dominio | Crea modelos del dominio arrastrando bloques. |

## Ruta MDE

```text
.m2b source file
  -> Xtext parser
  -> DomainModel
  -> DomainModelAdapter
  -> EditorSpec EMF intermediate model
  -> BlocklyCodeGenerator.xtend
  -> generated Blockly editor for domain users
```

La ruta DSL comparte el mismo modelo intermedio y el mismo generador que la ruta
Ecore:

```text
Ruta Ecore: .ecore -> EcoreAdapter -> EditorSpec
Ruta DSL:   .m2b   -> DomainModelAdapter -> EditorSpec
```

## Archivos relacionados

| Elemento | Ubicación |
| --- | --- |
| Gramática Xtext | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext` |
| Metamodelo del DSL | `io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore` |
| Adaptador del DSL | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java` |
| Entrada standalone | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/Model2BlocklyToBlocklyMain.java` |
| Ejemplo DSL de AppMaker | `io.github.plortinus.model2blockly/examples/app_maker.m2b` |

## Boceto de sintaxis

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

## Ecore anotado frente a `.m2b`

| Aspecto | Ecore + anotaciones | DSL textual `.m2b` |
| --- | --- | --- |
| Entrada | Metamodelo EMF `.ecore`. | Modelo textual Xtext. |
| Uso principal | Reutilizar un metamodelo EMF existente. | Escribir una definición compacta del lenguaje. |
| Legibilidad | XML/XMI más anotaciones. | Más compacto para documentación y ejemplos. |
| Transformación | `EcoreAdapter -> EditorSpec`. | `DomainModelAdapter -> EditorSpec`. |

Ambas rutas son MDE y convergen en el mismo modelo intermedio EMF `EditorSpec`.
