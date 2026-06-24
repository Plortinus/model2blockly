# Primeros pasos

Idioma: [English](../../GETTING_STARTED.md) | **Español** | [中文](../zh/GETTING_STARTED.md)

Esta guía muestra el camino más corto desde el plugin instalado hasta un editor
Blockly generado.

## Que va a hacer

1. Abrir un ejemplo ya generado para ver el resultado esperado.
2. Generar un editor desde el ejemplo `.model2blockly`.
3. Generar el editor equivalente desde el ejemplo `.ecore`.
4. Revisar el informe de generación para decidir que cambiar.

## Requisitos

- Eclipse ejecutandose con JDK 21.
- Plugin Model2Blockly instalado, o estos proyectos importados en el workspace:

```text
io.github.plortinus.model2blockly
io.github.plortinus.model2blockly.ide
io.github.plortinus.model2blockly.ui
io.github.plortinus.model2blockly.feature
io.github.plortinus.model2blockly.updatesite
```

- Un navegador para abrir los archivos HTML generados.

Para instalar desde Eclipse use:

```text
Help -> Install New Software... -> Add...
https://plortinus.github.io/model2blockly/update-site/
```

Si el diálogo de instalación aparece vacío, revise
[`TROUBLESHOOTING.md`](TROUBLESHOOTING.md).

## Guia visual

Estas capturas muestran el flujo habitual: copiar la URL del update site, abrir
el editor AppMaker generado y revisar el workspace de validaciones.

![Seccion de instalacion en la pagina del proyecto](../assets/screenshots/homepage-install-es.png)

La seccion de instalacion muestra la URL p2 update site que usa el dialogo
`Install New Software` de Eclipse.

![Editor AppMaker generado con un modelo de ejemplo cargado](../assets/screenshots/appmaker-editor.png)

Despues de generar, el HTML standalone deberia mostrar toolbox, workspace,
vista previa y boton `Load Sample`.

![Workspace de validaciones con reglas como bloques Blockly](../assets/screenshots/validation-workspace.png)

`validation_workspace.html` es una pagina generada aparte que muestra las reglas
del modelo como bloques Blockly.

## Probar primero los ejemplos generados

El repositorio incluye editores AppMaker ya generados:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/html/AppMaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

En el editor:

1. Pulse `Load Sample`.
2. Mueva algunos bloques.
3. Revise el panel de código o salida.
4. Abra `html/validation_workspace.html` si quiere ver las reglas de validación
   como bloques Blockly.
5. Use `More -> Export XMI` para exportar los bloques como modelo de instancia
   del dominio.

También puede abrir los ejemplos publicados:

```text
https://plortinus.github.io/model2blockly/
```

## Generar desde `.model2blockly`

Use esta ruta cuando quiera escribir el modelo de dominio a mano.

1. Abra o importe este repositorio en Eclipse.
2. Seleccione este archivo:

```text
io.github.plortinus.model2blockly/examples/app_maker.model2blockly
```

3. Clic derecho y elija `Generate Blockly Editor`.
4. Espere a que termine la generación.
5. Eclipse deberia abrir:

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/generation_report.html
```

6. Abra el editor generado:

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/html/AppMaker_standalone.html
```

La carpeta de salida se crea junto al archivo elegido y se llama:

```text
<nombre-del-archivo>_generated
```

Si falla, abra la vista `Problems` de Eclipse y el archivo `.model2blockly`.
Los errores de sintaxis, referencias y reglas estructurales del generador se
marcan sobre el archivo fuente. Un mensaje como `block[Task].assignee` apunta
al elemento del modelo intermedio que no se pudo generar con seguridad.

## Generar desde `.ecore`

Use esta ruta si ya tiene un metamodelo EMF o quiere trabajar directamente con
conceptos de EMF como `EPackage`, `EAnnotation`, `EReference.eOpposite` o código
EMF existente.

1. Seleccione:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

2. Clic derecho y elija `Generate Blockly Editor`.
3. Eclipse deberia abrir:

```text
io.github.plortinus.model2blockly/model/app_maker_generated/generation_report.html
```

4. Abra:

```text
io.github.plortinus.model2blockly/model/app_maker_generated/html/Appmaker_standalone.html
```

## Que contiene la carpeta generada

| Archivo o carpeta | Uso |
| --- | --- |
| `generation_report.html` | Primer archivo que debe revisar tras generar |
| `README.md` | Instrucciones dentro de la salida |
| `html/*_standalone.html` | Editor principal para abrir en navegador |
| `html/*_editor.html` | Pagina embebible del editor |
| `html/*_blocks.js` | Definiciones de bloques Blockly |
| `html/*_toolbox.js` | Toolbox generado |
| `html/*_generators.js` | Generadores de código |
| `html/*_validations.js` | Hooks de validación |
| `html/validation_workspace.html` | Reglas de validación como bloques |
| `html/sample_model.json` | Modelo de ejemplo |
| `intermediate/*_blocklyspec.xmi` | Modelo intermedio EMF/XMI recargado antes de generar HTML |

Empiece por `generation_report.html`. Resume lo que el generador entendió del
modelo y que archivos escribio.

No confunda los dos niveles de XMI: `intermediate/*_blocklyspec.xmi` es la
instancia `EditorSpec` interna del generador, mientras que el `*_model.xmi`
exportado por el editor es el modelo de instancia del dominio creado por el
usuario.

## Primer cambio pequeno

En `.model2blockly`, cambie una etiqueta o color:

```model2blockly
class Button extends Component category Components colour 160 label "Button"
```

En `.ecore`, cambie una anotacion:

```xml
<eAnnotations source="blockly">
  <details key="colour" value="160"/>
  <details key="category" value="Components"/>
  <details key="tooltip" value="Button component with click actions."/>
</eAnnotations>
```

Genere de nuevo y vuelva a abrir `generation_report.html`.

## Siguiente lectura

| Necesidad | Documento |
| --- | --- |
| Sintaxis textual | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| Anotaciones Ecore | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| Problemas comunes | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| Mapa completo en ingles | [`../../DOCS.md`](../../DOCS.md) |
