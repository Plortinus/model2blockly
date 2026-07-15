# E07 — Turtle

## Alcance

El caso reproduce el subsistema Blockly del nivel 10 de Turtle: sus doce tipos
de bloque personalizados, la paleta completa por categorías, el bloque inicial
`turtle_move` y los generadores JavaScript. Aunque el nivel 10 solo muestra
siete de los doce tipos en la paleta, las variantes internas siguen formando
parte de la definición oficial y se conservan en el descriptor.

La unidad evaluada termina en el código generado por los bloques. Se excluyen
el lienzo y la tortuga gráfica, el trazado del dibujo, JS-Interpreter, la
animación asociada a `block_id`, la comparación con la figura objetivo y la
galería. Por tanto, el caso evalúa si se puede regenerar el **editor**, no si
Model2Blockly vuelve a implementar la aplicación Turtle.

La referencia procede de los ficheros oficiales
[`appengine/turtle/src/blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/turtle/src/blocks.js),
[`appengine/turtle/src/html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/turtle/src/html.js),
[`appengine/turtle/src/main.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/turtle/src/main.js) y
[`json/en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json).
Los intervalos exactos, las copias sin modificar y sus hashes se conservan en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación principal mantenida con el DSL.
- [`source.ecore`](source.ecore): especificación Ecore equivalente usada solo
  para comprobar la coherencia entre rutas de entrada.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): carga las definiciones,
  extensión de giro, generadores, paleta y espacio inicial oficiales.
- [`m2b/adapter.mjs`](m2b/adapter.mjs) y
  [`ecore/adapter.mjs`](ecore/adapter.mjs): cargan las dos salidas generadas.
- [`generated/`](generated/) y [`generated-ecore/`](generated-ecore/): salidas
  completas de las rutas `.m2b` y Ecore.
- [`assessment.json`](assessment.json): clasificación trazable de cada
  diferencia con la referencia.
- [`results/comparison.json`](results/comparison.json): comparación oficial
  frente a `.m2b`, propiedad por propiedad.
- [`results/route-comparison.json`](results/route-comparison.json): resultado
  de equivalencia entre Ecore y `.m2b`.
- [`results/metrics.json`](results/metrics.json): métricas de tamaño; Ecore no
  se usa en la comparación de concisión.
- [`results/screenshots/`](results/screenshots/): capturas de los tres
  tratamientos bajo los mismos controles.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-turtle
npm run verify:evaluation-turtle
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E07_turtle/generated
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E07_turtle/generated-ecore
```

La preparación vuelve a extraer los intervalos del commit fijado y regenera
ambos tratamientos. La verificación valida hashes y esquemas, carga BASELINE,
M2B y ECORE en Chromium, compara la referencia con M2B y exige igualdad exacta
entre los descriptores canónicos de las dos rutas de entrada.

## Resultado

El descriptor compara 427 propiedades aplicables del editor:

- 349 coincidencias;
- 7 reproducciones parciales;
- 71 propiedades no soportadas;
- 81,73 % de paridad funcional;
- clasificación: reproducción parcial;
- cero errores de carga, cero discrepancias directas y cero diferencias sin
  justificar.

Los doce bloques se registran. Se conservan las entradas de valor tipadas, los
campos de color, las opciones visibles de movimiento y giro, colores, textos,
tooltips, ayudas y disposición. Las siete diferencias parciales corresponden a
valores internos numéricos de desplegables, valores de familias con espacios y
a las dos extensiones de flecha: su apariencia se conserva, aunque no el
mecanismo dinámico original.

Las 71 propiedades no declarables se agrupan principalmente en conexiones de
sentencia tipadas, filas `input_dummy` explícitas de `turtle_font` y
`turtle_repeat_internal`, composición exacta de la paleta estándar —incluida
`Lists`—, sombras preconfiguradas y espacio de trabajo inicial. Una sola
limitación puede afectar varias propiedades atómicas;
por ejemplo, la ausencia de `Lists` produce su presencia de categoría y las
presencias de sus doce entradas.

Los generadores obtienen 30 coincidencias de 40 propiedades aplicables. Ocho
diferencias de plantilla basadas únicamente en `block_id` se muestran pero se
excluyen por pertenecer a la animación del dominio. Las diez diferencias
aplicables restantes son valores alternativos para entradas vacías, mapeos de
enumeración y reutilización del generador incorporado `controls_repeat`.

La referencia oficial dentro del alcance contiene 669 líneas no vacías y sin
comentarios, frente a 190 líneas de `.m2b`, una reducción del 71,60 %. La cifra
incluye la extensa paleta oficial del nivel 10 y debe leerse junto con su
reproducción parcial. Ecore se excluye de esta métrica, conforme al protocolo.

Finalmente, [`route-comparison.json`](results/route-comparison.json) confirma
igualdad exacta entre Ecore y `.m2b` en controles, bloques, paleta, espacio
inicial, metadatos de generadores, errores y carga. La construcción de este caso
detectó y permitió corregir una inversión de etiqueta/valor en los enums del
adaptador Ecore; los cuatro pares de características existentes continúan
produciendo especificaciones y artefactos idénticos después de la corrección.
