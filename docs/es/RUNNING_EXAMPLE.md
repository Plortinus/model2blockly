# Caso AppMaker

AppMaker es el ejemplo principal de validación de Model2Blockly. El caso
demuestra cómo generar un editor Blockly desde un metamodelo Ecore anotado.

![Editor AppMaker generado](../assets/screenshots/appmaker-editor.png)

## Qué muestra el caso

- La definición del editor en `.ecore` con anotaciones.
- El XMI intermedio `intermediate/*_blocklyspec.xmi`.
- Los archivos JavaScript generados para Blockly.
- El XMI de instancia de dominio exportado por el editor generado.
- El informe `generation_report.html`.
- El workspace `validation_workspace.html`, usado para revisar reglas visuales.

## Archivos que conviene revisar

| Artefacto | Para qué sirve |
| --- | --- |
| `io.github.plortinus.model2blockly/model/app_maker.ecore` | Entrada Ecore de AppMaker |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/` | Salida Blockly generada |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` | Informe de generación |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/validation_workspace.html` | Workspace de validaciones |

La [descripción completa en inglés](../../RUNNING_EXAMPLE.md) mantiene más detalles de código, artefactos generados y criterios de validación.
