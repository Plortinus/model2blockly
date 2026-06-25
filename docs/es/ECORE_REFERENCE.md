# Referencia de anotaciones Ecore

La ruta Ecore funciona sin anotaciones y usa `EAnnotation` opcionales para
mejorar el editor Blockly. La implementación está en `EcoreAdapter.java`.

## Inferencia sin anotaciones

| Elemento Ecore | Mapeo por defecto |
| --- | --- |
| `EPackage` | Nombre de dominio, nsURI y nsPrefix. |
| `EClass` concreta | Tipo de bloque Blockly. |
| `EClass` abstracta o interfaz | Tipo abstracto no emitido como bloque concreto. |
| Supertipo | Herencia y tipo de conexión. |
| `EAttribute:EString` | Campo de texto. |
| `EAttribute:EInt`, `ELong`, `EShort`, `EBigInteger` | Campo entero. |
| `EAttribute:EFloat`, `EDouble`, `EBigDecimal` | Campo decimal. |
| `EAttribute:EBoolean` | Checkbox. |
| Atributo `EEnum` | Dropdown. |
| `EReference` de contención | Entrada de sentencia. |
| `EReference` no de contención | Campo de referencia dinámico. |
| `lowerBound >= 1` | Validación de obligatorio. |
| Feature múltiple única | Validación de duplicados. |
| Atributo `iD` | Identidad para referencias y exportación XMI. |

## `source="blockly"` en `EPackage`

| Clave | Significado |
| --- | --- |
| `workspace.toolboxType` | Tipo de toolbox. |
| `workspace.renderer` | Opción de `Blockly.inject`. |
| `workspace.zoom.controls` | Opción anidada de zoom. |
| `workspace.zoom.maxScale` | Escala máxima. |
| `workspace.grid.spacing` | Espaciado de grid. |
| `workspace.grid.snap` | Ajuste a grid. |

## `source="blockly"` en `EClass`

| Clave | Significado |
| --- | --- |
| `message0` | Mensaje Blockly explícito. |
| `colour` | Color numérico del bloque. |
| `category` | Categoría del toolbox; usa `/` para categorías anidadas. |
| `output` | Marca el bloque como output; acepta `true` o un tipo. |
| `inputsInline` | `true` o `false`. |
| `tooltip` | Tooltip. |
| `helpUrl` | URL de ayuda. |

## `source="blockly"` en `EAttribute`

| Clave | Significado |
| --- | --- |
| `type` | `field_colour`, `field_angle`, `field_image`, `field_label`, `field_input`, `field_number`, `field_checkbox`. |
| `min` | Valor mínimo. |
| `max` | Valor máximo. |
| `src` | URL para imagen. |
| `width` | Ancho de imagen. |
| `height` | Alto de imagen. |
| `alt` | Texto alternativo. |

## `source="blockly"` en `EReference`

| Clave | Significado |
| --- | --- |
| `type=input_value` | Emite una entrada de valor Blockly. |
| `check` | Tipo de comprobación de Blockly. |
| `shadow` | Sugerencia de bloque shadow. |

## `source="ui"`

En `EClass`: `label`.

En `EAttribute` y `EReference`: `widget`, `label`, `help`, `placeholder`,
`group`, `variant`, `readonly`, `hidden`, `order` y `referenceLabelField` para
referencias.

## `source="code"`

En `EPackage`: `language`, `fileExtension`.

En `EClass`: `template` o `codeTemplate`.

## `source="runtime"`

En `EPackage`: `kind`.

## `source="validation"`

En `EClass`:

| Clave | Significado |
| --- | --- |
| `mustFollow` | Validación de orden respecto a un tipo predecesor. |
| `expression`, `condition`, `js` | Validación por expresión. |
| `message` | Mensaje diagnóstico. |
| `expression.<name>`, `condition.<name>`, `js.<name>` | Validación nombrada. |
| `message.<name>` | Mensaje para validación nombrada. |
| `ocl`, `ocl.<name>` | OCL traducible en el subconjunto soportado. |

El subconjunto OCL cubre navegación simple de features, `size()`, `notEmpty()`,
`isEmpty()`, `oclIsUndefined()`, comparaciones y operadores booleanos.
