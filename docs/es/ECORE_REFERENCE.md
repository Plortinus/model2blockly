# Referencia de anotaciones Ecore

Model2Blockly puede generar editores Blockly desde metamodelos Ecore anotados.
La entrada usa estructura Ecore normal y `EAnnotation` en paquetes, clases,
atributos y referencias.

La ruta Ecore usa la misma tuberia intermedia: el `EPackage` se adapta al
`EditorSpec` EMF generado, se escribe como
`intermediate/*_blocklyspec.xmi`, se recarga desde XMI y despues se genera el
HTML/JavaScript de Blockly.

La referencia inglesa contiene las tablas completas de claves:

- [`../../ECORE_REFERENCE.md`](../../ECORE_REFERENCE.md)
- Adaptador: [`../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
- Ejemplo: [`../../io.github.plortinus.model2blockly/model/app_maker.ecore`](../../io.github.plortinus.model2blockly/model/app_maker.ecore)

## Forma de una anotacion

En XMI:

```xml
<eAnnotations source="blockly">
  <details key="colour" value="260"/>
  <details key="category" value="Pages"/>
  <details key="tooltip" value="Root app model."/>
</eAnnotations>
```

En el editor de árbol de Eclipse:

```text
EAnnotation
  source = blockly
  details:
    colour -> 260
    category -> Pages
    tooltip -> Root app model.
```

Los comentarios XML normales no afectan a la generación. Use `EAnnotation`.

## Sources soportados

| Source | Donde se usa | Proposito |
| --- | --- | --- |
| `blockly` | `EPackage`, `EClass`, `EAttribute`, `EReference` | Presentacion Blockly, campos, value inputs, workspace |
| `ui` | `EClass`, `EAttribute`, `EReference` | Labels y metadatos legibles |
| `code` | `EPackage`, `EClass` | Lenguaje, extensión y plantillas de código |
| `runtime` | `EPackage` | Variante runtime opcional |
| `validation` | `EClass` | Reglas semanticas y must-follow |
| `http://www.eclipse.org/emf/2002/Ecore` | `EClass` | Declaracion EMF de constraints |
| `http://www.eclipse.org/emf/2002/Ecore/OCL` | `EClass` | Cuerpo OCL simple |
| `http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot` | `EClass` | Cuerpo OCL simple |

## Mapeo sin anotaciones

La ruta Ecore funciona incluso sin anotaciones. Las anotaciones refinan el
editor generado.

| Ecore | Resultado |
| --- | --- |
| `EPackage.name` | Nombre del dominio/editor |
| `EClass` concreto | Bloque Blockly |
| `EClass.abstract=true` o `interface=true` | Tipo abstracto, no aparece como bloque normal |
| primer `eSuperTypes` | Tipo padre para conexiones |
| `EAttribute : EString` | Campo de texto |
| `EAttribute : EInt`, `ELong`, etc. | Campo numerico |
| `EAttribute : EBoolean` | Checkbox |
| `EAttribute : EEnum` | Dropdown |
| `EAttribute.lowerBound >= 1` | Validacion de requerido |
| `EAttribute.iD=true` | Identificador y validación de unicidad |
| `EReference containment=true` | `input_statement` |
| `EReference containment=false` | Selector de referencia dinámico |
| `EReference.eOpposite` | Metadata de sincronizacion runtime |
| feature derived/transient/volatile/no changeable | Se omite |

## Anotaciones de paquete

Workspace y toolbox:

```xml
<eAnnotations source="blockly">
  <details key="workspace.renderer" value="zelos"/>
  <details key="workspace.trashcan" value="true"/>
  <details key="workspace.zoom.controls" value="true"/>
  <details key="workspace.grid.spacing" value="20"/>
  <details key="workspace.toolboxType" value="category"/>
</eAnnotations>
```

Código:

```xml
<eAnnotations source="code">
  <details key="language" value="javascript"/>
  <details key="fileExtension" value="js"/>
</eAnnotations>
```

Runtime:

```xml
<eAnnotations source="runtime">
  <details key="kind" value="appMaker"/>
</eAnnotations>
```

## Anotaciones de clase

```xml
<eAnnotations source="blockly">
  <details key="message0" value="Page %1 route %2"/>
  <details key="colour" value="260"/>
  <details key="category" value="Pages"/>
  <details key="tooltip" value="A navigable page."/>
  <details key="helpUrl" value="https://example.com/page-help"/>
  <details key="inputsInline" value="true"/>
  <details key="output" value="true"/>
</eAnnotations>
```

Claves frecuentes:

| Key | Resultado |
| --- | --- |
| `message0` | Layout textual del bloque |
| `colour` | Color/hue del bloque |
| `category` | Categoría del toolbox; `UI/Inputs` crea categorías anidadas |
| `tooltip` | Tooltip Blockly |
| `helpUrl` | URL de ayuda |
| `inputsInline` | `true` o `false` |
| `output` | `true` para bloque de valor, o nombre de tipo para output tipado |

Etiqueta humana:

```xml
<eAnnotations source="ui">
  <details key="label" value="Data source"/>
</eAnnotations>
```

Plantilla de código:

```xml
<eAnnotations source="code">
  <details key="template" value="page(&quot;{{title}}&quot;, [&#10;{{statements:components}}&#10;]);"/>
</eAnnotations>
```

## Atributos y referencias

Anotacion de atributo:

```xml
<eAnnotations source="ui">
  <details key="label" value="Title"/>
  <details key="group" value="Main"/>
  <details key="order" value="1"/>
</eAnnotations>
```

Referencia como value input:

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="check" value="Expression"/>
  <details key="shadow" value="TextLiteral"/>
</eAnnotations>
```

Referencia dinamica:

```xml
<eAnnotations source="ui">
  <details key="referenceLabelField" value="name"/>
</eAnnotations>
```

## Validacion y OCL

Las validaciones se derivan de cardinalidades, IDs, unicidad y anotaciones.
Para reglas explicitas:

```xml
<eAnnotations source="validation">
  <details key="mustFollow" value="Page"/>
  <details key="message" value="Button must be inside a page."/>
</eAnnotations>
```

Para OCL basico:

```xml
<eAnnotations source="http://www.eclipse.org/emf/2002/Ecore/OCL">
  <details key="HasName" value="not self.name.oclIsUndefined() and self.name.size() > 0"/>
</eAnnotations>
```

El subconjunto soportado es pequeno: `self.<feature>`, `size()`,
`notEmpty()`, `isEmpty()`, `oclIsUndefined()`, comparaciones y
`and`/`or`/`not`. OCL avanzado se rechaza antes de generar.

## Referencias oficiales

- EMF: <https://eclipse.dev/emf/>
- `EAnnotation` Javadoc: <https://download.eclipse.org/modeling/emf/emf/javadoc/2.11/org/eclipse/emf/ecore/EAnnotation.html>
- Blockly block JSON: <https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/>
- Blockly fields: <https://docs.blockly.com/guides/create-custom-blocks/fields/overview/>
- Blockly workspace config: <https://docs.blockly.com/guides/configure/configuration_struct/>
- Blockly code generation: <https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/>
