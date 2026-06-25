# Caso AppMaker

AppMaker es el caso de referencia de la ruta Ecore-first.

## Fuente y salida

| Elemento | Ruta |
| --- | --- |
| Metamodelo fuente | `io.github.plortinus.model2blockly/model/app_maker.ecore` |
| Salida generada | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore` |
| Editor standalone | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html` |
| Modelo intermedio | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi` |
| Informe | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` |

Demo pública:

```text
https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
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
