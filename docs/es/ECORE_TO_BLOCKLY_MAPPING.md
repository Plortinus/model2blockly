---
pageClass: ecore-mapping-page
---

# Mapeo de Ecore a Blockly

Esta página explica cómo Model2Blockly convierte un metamodelo Ecore en un
editor Blockly. Úsala al diseñar archivos `.ecore`: primero revisa las reglas
por defecto y después añade anotaciones solo cuando quieras personalizar el
resultado.

Para el caso completo, consulta el [Caso AppMaker](./RUNNING_EXAMPLE.md).

## Reglas de mapeo

| Elemento Ecore | Resultado generado |
| --- | --- |
| `EClass` | Un tipo de bloque Blockly. |
| `EClass` con `abstract="true"` | No aparece en la toolbox, pero se usa como tipo de conexión. |
| `EAttribute` | Un campo de texto, desplegable, color, checkbox o número. |
| `EReference` con `containment="true"` | `input_statement` o `input_value` para bloques hijos. |
| `EReference` sin containment | Campo de referencia para elegir objetos existentes. |
| Anotación `source="blockly"` | Categorías, colores, tooltips, tipos de campo y shadow blocks. |
| Anotación `source="ui"` | Etiquetas, widgets, grupos y orden de campos. |
| Anotación `source="code"` | Plantillas de exportación de código. |
| Anotación `source="validation"` | Reglas de validación generadas. |

## Valores por defecto y personalización

Un modelo Ecore puede generar un editor Blockly sin anotaciones. Las anotaciones
sobrescriben las reglas inferidas cuando el editor necesita etiquetas más claras,
categorías, widgets, validación o exportación de código.

### Sin anotaciones

| Entrada Ecore | Resultado por defecto |
| --- | --- |
| `EClass` concreta | Un bloque Blockly arrastrable. |
| `EClass` abstracta o interfaz | No aparece en la toolbox; se usa como padre o tipo de conexión. |
| `EAttribute:EString` | Campo de texto. |
| `EAttribute:EBoolean` | Checkbox. |
| Atributo numérico | Campo numérico. |
| Atributo `EEnum` | Desplegable. |
| `EReference` containment | Entrada statement para anidar bloques hijos. |
| `EReference` sin containment | Desplegable dinámico de referencias. |
| `lowerBound >= 1` | Validación de requerido o cardinalidad mínima. |
| Sin anotaciones `category` | Categorías de toolbox inferidas desde la herencia. |

### Cuando necesitas un resultado específico

| Resultado Blockly deseado | Dónde añadir la anotación | Claves habituales |
| --- | --- | --- |
| Colocar bloques en una categoría concreta | `EClass` con `source="blockly"` | `category`, con rutas como `UI/Inputs`. |
| Cambiar color, tooltip o enlace de ayuda | `EClass` con `source="blockly"` | `colour`, `tooltip`, `helpUrl`. |
| Cambiar texto del bloque u orden de campos | `EClass` con `source="blockly"` | `message0`, `inputsInline`. |
| Usar campos de color, ángulo, imagen o etiqueta | `EAttribute` con `source="blockly"` | `type=field_colour`, `field_angle`, `field_image`, `field_label`. |
| Limitar un campo numérico | `EAttribute` con `source="blockly"` | `type=field_number`, `min`, `max`. |
| Cambiar etiqueta, ayuda, placeholder u orden | `EAttribute` o `EReference` con `source="ui"` | `label`, `help`, `placeholder`, `order`, `readonly`, `hidden`. |
| Convertir containment en socket de expresión | `EReference` con `source="blockly"` | `type=input_value`, `check`, `shadow`. |
| Cambiar etiquetas del desplegable de referencias | `EReference` sin containment con `source="ui"` | `referenceLabelField`. |
| Añadir validación semántica | `EClass` con `source="validation"` | `mustFollow`, `expression`, `condition`, `ocl`, `message`. |
| Añadir exportación de código | `EPackage` o `EClass` con `source="code"` | `language`, `fileExtension`, `template`, `codeTemplate`. |
| Configurar el workspace Blockly | `EPackage` con `source="blockly"` | `workspace.renderer`, `workspace.grid.*`, `workspace.zoom.*`, `workspace.toolboxType`. |

```xml
<eClassifiers xsi:type="ecore:EClass" name="Task">
  <eAnnotations source="blockly">
    <details key="category" value="Work/Tasks"/>
    <details key="colour" value="210"/>
    <details key="tooltip" value="Create a task"/>
  </eAnnotations>

  <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
      eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString">
    <eAnnotations source="ui">
      <details key="label" value="Title"/>
      <details key="placeholder" value="Task title"/>
    </eAnnotations>
  </eStructuralFeatures>
</eClassifiers>
```

## Referencia rápida de claves

### `source="blockly"` en `EPackage`

| Clave | Significado |
| --- | --- |
| `workspace.toolboxType` | Tipo de toolbox. |
| `workspace.renderer` | Opción de `Blockly.inject`. |
| `workspace.zoom.controls` | Opción anidada de zoom. |
| `workspace.zoom.maxScale` | Escala máxima. |
| `workspace.grid.spacing` | Espaciado de grid. |
| `workspace.grid.snap` | Ajuste a grid. |

### `source="blockly"` en `EClass`

| Clave | Significado |
| --- | --- |
| `message0` | Mensaje Blockly explícito. |
| `colour` | Color numérico del bloque. |
| `category` | Categoría del toolbox; usa `/` para categorías anidadas. |
| `output` | Marca el bloque como output; acepta `true` o un tipo. |
| `inputsInline` | `true` o `false`. |
| `tooltip` | Tooltip. |
| `helpUrl` | URL de ayuda. |

### `source="blockly"` en `EAttribute`

| Clave | Significado |
| --- | --- |
| `type` | `field_colour`, `field_angle`, `field_image`, `field_label`, `field_input`, `field_number`, `field_checkbox`. |
| `min` | Valor mínimo. |
| `max` | Valor máximo. |
| `src` | URL para imagen. |
| `width` | Ancho de imagen. |
| `height` | Alto de imagen. |
| `alt` | Texto alternativo. |

### `source="blockly"` en `EReference`

| Clave | Significado |
| --- | --- |
| `type=input_value` | Emite una entrada de valor Blockly. |
| `check` | Tipo de comprobación de Blockly. |
| `shadow` | Sugerencia de bloque shadow. |

### `source="ui"`

En `EClass` se admite `label`.

En `EAttribute` y `EReference` se admiten `widget`, `label`, `help`,
`placeholder`, `group`, `variant`, `readonly`, `hidden`, `order` y
`referenceLabelField` para referencias.

### `source="code"`

En `EPackage` se admiten `language` y `fileExtension`.

En `EClass` se admiten `template` y `codeTemplate`.

### `source="runtime"`

En `EPackage` se admite `kind`.

### `source="validation"`

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

## Ejemplos de funciones

Cada ejemplo es un modelo `.ecore` independiente. El panel izquierdo muestra el
modelo fuente y el panel derecho incrusta el HTML Blockly generado desde el mismo
modelo. Los enlaces abren el código fuente, el editor standalone y el informe de
generación.

<!-- GENERATED_ECORE_FEATURE_GALLERY -->
