# E02 — JS-Interpreter Wait

## Alcance

El caso reproduce el subsistema Blockly de la demostración de ejecución
asíncrona: el bloque `wait_seconds`, su campo numérico, la paleta seleccionada,
el espacio de trabajo inicial y la plantilla de generación JavaScript. La
referencia procede de
[`wait_block.js`](https://github.com/google/blockly-samples/blob/62ce120977078bf9179c5633681ee70446e5073b/examples/interpreter-demo/wait_block.js)
y
[`async-execution.html`](https://github.com/google/blockly-samples/blob/62ce120977078bf9179c5633681ee70446e5073b/examples/interpreter-demo/async-execution.html).

Se excluyen JS-Interpreter, `setTimeout`, la reanudación asíncrona y los
controles de ejecución de la página. La función oficial
`initInterpreterWaitForSeconds` se extrae y valida mediante SHA-256 para dejar
constancia del límite, pero no entra en la puntuación ni en las líneas
mantenidas. Los intervalos y hashes exactos están en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida con Model2Blockly.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): carga los fragmentos oficiales
  sin ejecutar el runtime asíncrono.
- [`m2b/adapter.mjs`](m2b/adapter.mjs): carga directamente los JavaScript
  generados.
- [`assessment.json`](assessment.json): clasificación justificada de todas las
  diferencias.
- [`generated/`](generated/): salida Xtext → EditorSpec EMF → XMI → Blockly.
- [`results/comparison.json`](results/comparison.json): comparación propiedad a
  propiedad.
- [`results/metrics.json`](results/metrics.json): LOC, bytes y reducción.
- [`results/screenshots/`](results/screenshots/): capturas bajo los mismos
  controles del resto del corpus.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-interpreter-wait
npm run verify:evaluation-interpreter-wait
```

## Resultado

Se observaron 89 propiedades aplicables del editor:

- 73 coincidencias;
- 16 propiedades no soportadas;
- 82,02 % de paridad funcional;
- clasificación: reproducción parcial.

El tipo de bloque, mensaje, valor inicial, rango 0–600, conexiones, color y
estructura del campo coinciden. Las diferencias se agrupan en:

- composición fija de la paleta (10);
- imposibilidad de fusionar la categoría de dominio con `Loops` (2);
- precisión del campo numérico (1);
- valor preconfigurado de una entrada de paleta (1);
- supresión del tooltip predeterminado (1);
- espacio de trabajo inicial (1).

Las cuatro propiedades del generador coinciden, incluida la plantilla
`waitForSeconds({{SECONDS}});`. El runtime que implementa la espera no se cuenta
como generador del editor.

La referencia dentro del alcance contiene 282 líneas no vacías y sin
comentarios —277 para bloque, paleta y estado inicial, y 5 para el generador—,
frente a 16 en `.m2b`. La reducción es del 94,33 %, pero debe interpretarse
junto con la ausencia de la paleta exacta y del programa inicial.
