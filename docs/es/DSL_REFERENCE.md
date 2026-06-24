# Referencia de Model2Blockly

Idioma: [English](../../DSL_REFERENCE.md) | **Español** | [中文](../zh/DSL_REFERENCE.md)

Esta página resume la sintaxis textual `.model2blockly` y enlaza a la referencia
inglesa completa. Use la versión inglesa para las tablas exhaustivas de campos,
widgets, opciones de workspace y detalles de generación:

- [`../../DSL_REFERENCE.md`](../../DSL_REFERENCE.md)
- Gramatica: [`../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- Ejemplo principal: [`../../io.github.plortinus.model2blockly/examples/app_maker.model2blockly`](../../io.github.plortinus.model2blockly/examples/app_maker.model2blockly)

## Idea principal

Un archivo `.model2blockly` define un modelo de dominio. El generador lo convierte
en una instancia EMF generada `EditorSpec`, conforme al metamodelo
`BlocklyEditorSpec`, la serializa como `intermediate/*_blocklyspec.xmi`, la
recarga desde XMI y despues genera bloques, toolbox, generadores de codigo,
validaciones y paginas HTML.

Estructura habitual:

```text
domain <Name>
  [codeLanguage "..."]
  [codeFileExtension "..."]
  [runtimeKind "..."]

[workspace { ... }]

category ...
class ...
constraint ...
validation ...
```

## Ejemplo minimo

```model2blockly
domain TodoApp codeLanguage "javascript" codeFileExtension "js"

category Tasks label "Tasks" colour 210

class App category Tasks colour 260 label "App" {
  attribute name : string default "My Todo App" required
    widget text uiLabel "App name" group "App" order 1

  contains Task tasks [0..50]
    uiLabel "Tasks" group "Tasks" order 2
}

class Task category Tasks colour 210 label "Task" inline {
  attribute title : string default "Buy milk" required
    widget text uiLabel "Title" group "Task" order 1
}
```

Esto genera una categoría `Tasks`, un bloque `App`, un bloque `Task`, campos de
texto y un statement input `tasks`.

## Mapeo rapido

| DSL | Resultado en Blockly |
| --- | --- |
| `domain` | Prefijo de archivos, titulo y metadata del editor |
| `workspace` | Opciones pasadas a `Blockly.inject(...)` |
| `category` | Categoria del toolbox |
| `class` | Definicion de bloque |
| `abstract class` | Tipo padre para conexiones, no aparece como bloque normal |
| `output class` | Bloque de expresión con conexión `output` |
| `attribute` | Campo Blockly |
| `contains` | `input_statement` |
| `reference` | Dropdown o selector de referencias del workspace |
| `value` | `input_value`, opcionalmente con `shadow` |
| `constraint` | Regla de validación generada |
| `validation` | Regla por expresión u OCL simple |
| `code` | Plantilla de generación de código de dominio |

## Categorias y toolbox

```model2blockly
category Pages label "Pages" colour 260
category UI label "UI" colour 180 {
  category Inputs label "Inputs" colour 190
}
```

Las clases pueden declarar `category <Category>`. Si se usan categorías, el
toolbox generado es de tipo categoría. Si no hay categorías, el generador puede
crear un flyout simple.

## Clases y conexiones

```model2blockly
abstract class Component category Components colour 160

class Button extends Component category Components label "Button" {
  attribute labelText : string default "Button" required
}

output class TextLiteral category Values label "Text" {
  attribute value : string default "hello"
}
```

Reglas prácticas:

- `class` normal es un bloque de statement.
- `output class` es un bloque de valor que puede entrar en un `value`.
- `abstract class` no se muestra como bloque normal, pero sirve como tipo comun.
- Un `shadow` de un `value` debe apuntar a un `output class`.

## Features

### `attribute`

```model2blockly
attribute title : string default "Title" required
  widget text uiLabel "Title" group "Main" order 1
```

Tipos frecuentes:

| Tipo | Campo Blockly |
| --- | --- |
| `string` | texto |
| `int`, `double`, `float` | número |
| `boolean` | checkbox |
| `enum { ... }` | dropdown |

### `contains`

```model2blockly
contains Component children [0..*]
```

Genera un `input_statement`. Los limites se convierten en reglas de
cardinalidad.

### `reference`

```model2blockly
reference DataSource source required
  widget reference-select referenceLabelField name
```

Genera un selector dinámico de bloques compatibles que ya existen en el
workspace.

### `value`

```model2blockly
value Expression condition shadow BooleanLiteral
```

Genera un `input_value`. El tipo indicado controla el `check` de Blockly.

## Validacion

Validaciones basicas se derivan de:

- `required`;
- limites `[lower..upper]`;
- `constraint ... must follow ...`;
- `validation ... : expression "..."`;
- `validation ... : ocl "..."`.

Ejemplo:

```model2blockly
validation titleRequired on Task : expression "notEmpty(title)" errorMessage "Title is required."
```

El OCL soportado es intencionalmente pequeno: `self.<feature>`, `size()`,
`notEmpty()`, `isEmpty()`, `oclIsUndefined()`, comparaciones y
`and`/`or`/`not`. Si se usa OCL avanzado como `forAll`, `exists`, `select` o
`oclIsTypeOf`, la herramienta debe reportar error antes de generar.

## Plantillas de código

```model2blockly
class Button code "button(\"{{labelText}}\");" {
  attribute labelText : string default "Button"
}
```

Placeholders comunes:

| Placeholder | Significado |
| --- | --- |
| `{{fieldName}}` | Valor de un atributo o referencia |
| `{{statements:inputName}}` | Código generado por bloques hijos de un statement input |
| `{{value:inputName}}` | Código generado por un value input |

## Documentación oficial relacionada

Model2Blockly oculta parte del JSON de Blockly, pero el editor generado sigue
siendo una aplicación Blockly. Consulte también:

- Blockly block JSON: <https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/>
- Campos Blockly: <https://docs.blockly.com/guides/create-custom-blocks/fields/overview/>
- Connection checks: <https://docs.blockly.com/guides/create-custom-blocks/inputs/connection-checks/>
- Toolboxes: <https://docs.blockly.com/guides/configure/toolboxes/category/>
- Workspace config: <https://docs.blockly.com/guides/configure/configuration_struct/>
- Code generation: <https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/>
