# Solución de problemas

Use esta página cuando la instalación o la generación no se comporten como
espera.

## El diálogo de instalación no muestra elementos

Sintoma:

```text
There are no categorized items
```

Comprobaciones:

1. Confirme que la URL es exactamente:

```text
https://plortinus.github.io/model2blockly/update-site/
```

2. En `Help -> Install New Software...`, desmarque `Group items by category`.
3. Pulse `Manage...`, elimine entradas antiguas de Model2Blockly, agregue la URL
   otra vez y recargue.
4. Mantenga activado `Contact all update sites during install to find required
   software`, para que Eclipse pueda resolver EMF.
5. Si usa un update site local, seleccione esta carpeta:

```text
io.github.plortinus.model2blockly.updatesite/repository/
```

No seleccione la carpeta padre `io.github.plortinus.model2blockly.updatesite/`.

## Eclipse no muestra una versión nueva

Causas habituales:

- El repositorio p2 local no se regeneró.
- Eclipse está usando metadata antigua en cache.
- No se subió la versión de bundle o feature.
- `Hide items that are already installed` está ocultando la versión instalada.

Comprobaciones:

1. Desmarque `Hide items that are already installed`.
2. Elimine y vuelva a agregar el update site desde `Manage...`.
3. Para el repositorio local, ejecute:

```bash
npm run rebuild:update-site
npm run verify:plugin
```

4. Compruebe que existen jars con la versión esperada en:

```text
io.github.plortinus.model2blockly.updatesite/repository/features/
io.github.plortinus.model2blockly.updatesite/repository/plugins/
```

## Falta el comando `Generate Blockly Editor`

Comprobaciones:

1. Seleccione exactamente un archivo.
2. El archivo debe terminar en `.ecore`.
3. Use Project Explorer, Package Explorer o el menu superior `Model2Blockly`.
4. Revise `Help -> About Eclipse IDE -> Installation Details`.
5. Reinicie Eclipse después de instalar o actualizar el plugin.

## La salida se generó en otro lugar

El comando de Eclipse escribe una carpeta hermana junto al archivo seleccionado:

```text
<nombre-del-archivo>_generated
```

Ejemplos:

| Archivo seleccionado | Carpeta de salida |
| --- | --- |
| `model/app_maker.ecore` | `model/app_maker_generated/` |

Si no ve la carpeta, refresque el proyecto en Eclipse.

## No se puede recargar el XMI intermedio

Sintoma:

```text
Could not load intermediate EMF model from XMI:
Expected generated EditorSpec root, got ...
```

Esto suele indicar un plugin antiguo o un bundle core mal empaquetado. La
version actual usa directamente el modelo EMF generado en
`io.github.plortinus.model2blockly.intermediate.blocklyspec`, especialmente
`BlocklySpecPackage` y `EditorSpec`; ya no carga `model/blockly_editor_spec.ecore`
en tiempo de ejecucion.

Solución:

1. Instale la versión actual desde el update site o el repositorio p2 local.
2. Reinicie Eclipse.
3. Confirme la versión instalada en `Installation Details`.
4. Si es una compilacion local, reconstruya el update site y confirme que el
   core jar contiene `io/github/plortinus/model2blockly/intermediate/blocklyspec/*`.
5. Genere de nuevo desde el archivo `.ecore` seleccionado.

## Falta el XMI intermedio o esta obsoleto

Cada generacion correcta debe escribir:

```text
intermediate/*_blocklyspec.xmi
```

Este archivo no es solo un log. El generador serializa el `EditorSpec` EMF
generado como XMI, lo recarga, lo adapta para validacion y despues genera el
HTML/JavaScript de Blockly.

Si hay HTML pero no existe ese XMI, el plugin instalado o el update site local
siguen siendo antiguos. Reconstruya e instale el update site actual y genere de
nuevo.

## Falla la generación de `.ecore`

Comprobaciones:

1. La raíz del archivo debe ser un `EPackage`.
2. Los clasificadores referenciados deben resolverse dentro del modelo o de los
   paquetes importados.
3. Features `derived`, `transient`, `volatile` o no modificables se omiten por
   diseño.
4. Para value inputs, anote el `EReference`:

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="shadow" value="TextLiteral"/>
</eAnnotations>
```

## El dropdown de referencias está vacío

Los campos de referencia listan bloques compatibles que ya existen en el
workspace.

Comprobaciones:

1. Cree al menos un bloque del tipo destino.
2. Confirme que el tipo destino coincide con el tipo del bloque o su connection
   type.
3. Agregue un campo de etiqueta:

```xml
<eAnnotations source="ui">
  <details key="referenceLabelField" value="name"/>
</eAnnotations>
```

## Los value blocks no conectan

En `.ecore`, marque la clase:

```xml
<eAnnotations source="blockly">
  <details key="output" value="true"/>
</eAnnotations>
```

y el socket:

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="check" value="TextExpression"/>
</eAnnotations>
```

## Las validaciones parecen inesperadas

Las validaciones generadas vienen de:

| Origen | Validacion |
| --- | --- |
| `lowerBound >= 1` | Campo o referencia obligatoria |
| containment con limites | Cardinalidad de statement input |
| `iD=true` | Unicidad por tipo |
| campos/referencias multivaluados con `unique=true` | Unicidad de valores |
| `mustFollow` | Regla de bloque anterior |
| anotaciones validation/OCL | Regla por expresión |

Abra `html/validation_workspace.html` para inspeccionar las reglas como bloques.

## Comandos locales utiles

```bash
npm install
npm run verify:plugin
npm run verify:domain-xmi
npm run verify:patch
npm run smoke
```
