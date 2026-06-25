# Caso AppMaker

AppMaker es el caso de referencia del flujo MDE con dos entradas. La ruta Ecore
y la ruta `.m2b` están generadas, versionadas y publicadas para poder inspeccionar
las dos formas de autoría.

## Fuente y salida

| Elemento | Ruta |
| --- | --- |
| Metamodelo fuente | `io.github.plortinus.model2blockly/model/app_maker.ecore` |
| Fuente DSL textual | `io.github.plortinus.model2blockly/examples/app_maker.m2b` |
| Salida Ecore | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore` |
| Editor Ecore | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html` |
| Modelo intermedio Ecore | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi` |
| Informe Ecore | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` |
| Salida `.m2b` | `io.github.plortinus.model2blockly/examples/generated/app_maker_dsl` |
| Editor `.m2b` | `io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/html/Appmaker_standalone.html` |
| Modelo intermedio `.m2b` | `io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/intermediate/Appmaker_blocklyspec.xmi` |
| Informe `.m2b` | `io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/generation_report.html` |

Demo pública:

```text
https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
https://plortinus.github.io/model2blockly/app_maker_dsl/Appmaker_standalone.html
```

## Regenerar el ejemplo

En Eclipse ejecuta:

```text
Generate AppMaker from Ecore.launch
```

Usa `EcoreToBlocklyMain` con:

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

Para la ruta textual ejecuta:

```text
Generate AppMaker from Model2Blockly.launch
```

con:

```text
examples/app_maker.m2b
examples/generated/app_maker_dsl
```

## Qué inspeccionar

1. `generation_report.html`
2. `intermediate/Appmaker_blocklyspec.xmi`
3. `html/Appmaker_standalone.html`
4. `html/validation_workspace.html`
5. `html/sample_model.json`

## Comportamiento del editor

El editor permite cargar el ejemplo, editar bloques, ver JSON/código, exportar
JSON/XML/XMI de dominio, reportar validaciones y usar referencias dinámicas.

## Smoke test

```bash
npm run smoke
```

El test abre el editor con Playwright, carga el ejemplo, comprueba validaciones
y verifica la forma del XMI de dominio.
