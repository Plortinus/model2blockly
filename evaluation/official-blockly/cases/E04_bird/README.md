# E04 — Bird

## Alcance

El caso reproduce el subsistema Blockly del nivel 10 de Bird: siete tipos
definidos o redefinidos por el juego, la modificación del bloque estándar
`controls_if`, la paleta del nivel, el bloque inicial y las plantillas de
generación de JavaScript. Se excluyen el mapa, el dibujo y la animación del ave,
la simulación de la trayectoria y la lógica de superación del nivel.

La referencia procede de los ficheros oficiales
[`appengine/bird/src/blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/bird/src/blocks.js),
[`appengine/bird/src/html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/bird/src/html.js),
[`appengine/bird/src/main.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/bird/src/main.js) y
[`json/en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json).
Los intervalos exactos, las copias sin modificar y sus hashes se encuentran en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida con Model2Blockly.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): ejecuta los fragmentos
  oficiales y fija la configuración del nivel 10.
- [`m2b/adapter.mjs`](m2b/adapter.mjs): carga los JavaScript generados por
  Model2Blockly y el bloque estándar no modificado.
- [`generated/`](generated/): salida real de Xtext → EditorSpec EMF → XMI
  intermedio → Blockly.
- [`assessment.json`](assessment.json): clasificación justificada de cada
  diferencia observada.
- [`results/comparison.json`](results/comparison.json): comparación propiedad a
  propiedad.
- [`results/metrics.json`](results/metrics.json): LOC, bytes y reducción.
- [`results/screenshots/`](results/screenshots/): capturas de ambos tratamientos
  bajo los mismos controles experimentales.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-bird
npm run verify:evaluation-bird
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E04_bird/generated
```

La preparación vuelve a extraer únicamente los intervalos declarados del
commit fijado y regenera el editor desde `.m2b`. La verificación valida los
hashes y esquemas, abre los dos tratamientos en Chromium y actualiza los
descriptores, métricas y capturas.

## Resultado

El descriptor compara 218 propiedades aplicables del editor:

- 203 coincidencias;
- 15 diferencias;
- 93,12 % de paridad funcional;
- clasificación: reproducción parcial.

Los ocho tipos observados se registran y coinciden en estructura básica,
campos, valores, textos, opciones, colores, tooltips y ayudas. Las diferencias
del editor se distribuyen así:

- cuatro propiedades de conexiones, porque Model2Blockly deriva restricciones
  de tipo desde la contención;
- dos extensiones dinámicas de tooltip no declarables;
- una comprobación de salida perdida al redefinir el nombre estándar
  `math_number` y una diferencia de precisión del campo numérico;
- dos conexiones que Bird elimina del bloque estándar `controls_if` mediante
  JavaScript;
- cuatro propiedades de una paleta que repite y preconfigura `bird_compare` y
  oculta tipos auxiliares;
- el espacio inicial con un `controls_if` no eliminable y posicionado.

De las 15 diferencias, 14 corresponden a capacidades no declarables y una es
una discrepancia directa en la redefinición de `math_number`; no hay errores de
carga. En los generadores coinciden 27 de 31 propiedades aplicables. Quedan
cuatro límites: el mapeo de `LT`/`GT` a operadores JavaScript y tres valores
alternativos para entradas vacías. El identificador de bloque usado para animar
`bird_heading` se registra aparte como diferencia excluida del runtime.

La fuente oficial dentro del alcance contiene 246 líneas no vacías y sin
comentarios, frente a 93 en `.m2b`, una reducción del 62,20 %. Esta reducción se
presenta junto con la paridad parcial, no como equivalencia completa. El smoke
test adicional confirma que el editor generado abre, carga su ejemplo, produce
un modelo JSON y exporta XMI de dominio sin incidencias de validación.
