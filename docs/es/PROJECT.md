# Vista general de Model2Blockly

Model2Blockly es un plugin de Eclipse que genera editores Blockly desde un
metamodelo `.ecore` anotado.

El metamodelo Ecore se carga con EMF como `EPackage`, se transforma en el
modelo intermedio `EditorSpec`, se guarda como XMI, se vuelve a cargar, se
valida y finalmente genera los archivos del editor Blockly.

## Flujo principal

1. Instale el plugin desde el update site.
2. Cree o abra un proyecto Eclipse.
3. Añada anotaciones Blockly, UI, validación y generación de código al `.ecore`.
4. Ejecute la acción de generación.
5. Revise `generation_report.html`, `intermediate/*_blocklyspec.xmi`, el editor generado y `validation_workspace.html`.

## Qué leer después

- [Guía rápida](GETTING_STARTED.md)
- [Caso AppMaker](RUNNING_EXAMPLE.md)
- [Mapeo de Ecore a Blockly](ECORE_TO_BLOCKLY_MAPPING.md)
- [Solución de problemas](TROUBLESHOOTING.md)

La [vista general completa en inglés](../../README.md) mantiene los detalles técnicos del repositorio.
