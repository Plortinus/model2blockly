# Guía de uso

Model2Blockly puede generar desde metamodelos `.ecore` anotados o desde
modelos textuales `.m2b`. La extensión larga `.model2blockly` se mantiene como
alias de compatibilidad.

## Instalar el plugin

Consulta la [guía de instalación](./INSTALL.md). La URL p2 es:

```text
https://plortinus.github.io/model2blockly/update-site/
```

El plugin aporta:

- editor Xtext para `.m2b` y `.model2blockly`;
- comando `Generate Blockly Editor`;
- comando `Apply Validation Blocks to Source`;
- soporte de menú contextual para `.ecore`, `.m2b` y `.model2blockly`.

## Generar desde Ecore

La entrada de referencia es:

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

En Eclipse:

1. Importa los proyectos del plugin.
2. Abre el proyecto `io.github.plortinus.model2blockly`.
3. Selecciona `model/app_maker.ecore`.
4. Haz clic derecho y elige `Generate Blockly Editor`.
5. Abre `generation_report.html`.

La misma ruta se implementa en:

```text
io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/EcoreToBlocklyMain.java
```

La configuración `Generate AppMaker from Ecore.launch` usa:

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

## Carpeta generada

La salida AppMaker del repositorio está en:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

| Ruta | Propósito |
| --- | --- |
| `generation_report.html` | Informe legible del mapeo Ecore -> Blockly. |
| `README.md` | Guía corta de la carpeta generada. |
| `intermediate/Appmaker_blocklyspec.xmi` | Modelo EMF `EditorSpec` serializado. |
| `html/Appmaker_standalone.html` | Editor autocontenido para navegador. |
| `html/Appmaker_editor.html` | Página del editor que carga los assets generados. |
| `html/Appmaker_blocks.js` | Definiciones Blockly. |
| `html/Appmaker_toolbox.js` | Toolbox y categorías. |
| `html/Appmaker_generators.js` | Exportación de código desde plantillas. |
| `html/Appmaker_validations.js` | Validaciones runtime. |
| `html/validation_workspace.html` | Workspace visual para reglas de validación. |
| `html/validation_blocks.json` | Modelo de bloques de validación. |
| `html/validation_runtime.js` | Runtime de validación. |
| `html/sample_model.json` | Modelo cargado por `Load Sample`. |

## Usar el editor generado

Abre:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

Después:

1. Pulsa `Load Sample`.
2. Inspecciona los bloques.
3. Edita el modelo.
4. Exporta JSON, XML, XMI de dominio o código.
5. Abre `validation_workspace.html` para ver las reglas como bloques Blockly.

![Editor AppMaker generado](../assets/screenshots/appmaker-editor.png)

![Workspace de validación](../assets/screenshots/validation-workspace.png)

## Verificar la salida

Desde la raíz del repositorio:

```bash
npm run smoke
npm run verify:domain-xmi
npm run verify:plugin
```

| Comando | Qué valida |
| --- | --- |
| `npm run smoke` | Abre el editor AppMaker con Playwright, carga el ejemplo y comprueba el navegador. |
| `npm run verify:domain-xmi` | Carga el XMI de dominio con EMF contra `app_maker.ecore`. |
| `npm run verify:plugin` | Comprueba metadatos del plugin y la salida generada. |

## Cuándo usar Xtext

Usa `.m2b` o `.model2blockly` solo cuando necesites la sintaxis textual
auxiliar. Ambas rutas normalizan a `EditorSpec` antes de generar.
