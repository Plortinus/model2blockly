# Procedencia de la referencia

Los fragmentos de `upstream/` se extraen sin modificaciones del commit fijado
de [`google/blockly-samples`](https://github.com/google/blockly-samples), con
licencia Apache-2.0.

La referencia evaluada incluye la definición de `wait_seconds`, su generador,
la paleta exacta de `async-execution.html` y el espacio de trabajo inicial. La
función `initInterpreterWaitForSeconds` también se conserva con hash para hacer
explícito el límite, pero se marca `countForMetrics: false`: el enlace con
JS-Interpreter, `setTimeout` y la reanudación asíncrona pertenecen al runtime de
la aplicación, no al editor Blockly.
