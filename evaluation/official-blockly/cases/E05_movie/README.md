# E05 — Movie

## Alcance

El caso reproduce el subsistema Blockly del nivel 10 de Movie: los cinco
bloques `movie_circle`, `movie_rect`, `movie_line`, `movie_time` y
`movie_colour`, la paleta completa por categorías y sus generadores JavaScript.
El espacio oficial comienza vacío, por lo que se normaliza como ausencia de
contenido inicial. Se excluyen el lienzo, el renderizado por fotogramas, el
scrubber, JS-Interpreter, la comprobación de la solución y la galería.

La referencia procede de los ficheros oficiales
[`appengine/movie/src/blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/movie/src/blocks.js),
[`appengine/movie/src/html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/movie/src/html.js) y
[`json/en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json).
Los intervalos exactos, las copias sin modificar y sus hashes se conservan en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida con Model2Blockly.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): carga las definiciones,
  mensajes, generadores y paleta oficiales.
- [`m2b/adapter.mjs`](m2b/adapter.mjs): carga directamente los artefactos
  generados.
- [`generated/`](generated/): salida Xtext → EditorSpec EMF → XMI intermedio →
  Blockly.
- [`assessment.json`](assessment.json): clasificación trazable de cada
  diferencia.
- [`results/comparison.json`](results/comparison.json): comparación propiedad a
  propiedad.
- [`results/metrics.json`](results/metrics.json): métricas de tamaño.
- [`results/screenshots/`](results/screenshots/): capturas bajo los mismos
  controles experimentales.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-movie
npm run verify:evaluation-movie
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E05_movie/generated
```

La preparación extrae únicamente los intervalos declarados del commit fijado y
regenera el editor desde `.m2b`. La verificación valida hashes y esquemas, carga
los dos tratamientos en Chromium y actualiza resultados y capturas.

## Resultado

El descriptor compara 283 propiedades aplicables del editor:

- 236 coincidencias;
- 47 diferencias clasificadas como no soportadas;
- 83,39 % de paridad funcional;
- clasificación: reproducción parcial;
- cero errores de carga y cero discrepancias sin justificar.

Los cinco bloques se registran y conservan orden de entradas, comprobaciones de
tipo, conexiones de sentencia, disposición, colores, textos, tooltips y ayudas.
Las diferencias se agrupan en seis límites:

- alineación no declarable de las doce entradas numéricas;
- una salida `movie_time` tipada como `Number`, frente a la salida oficial sin
  restricción;
- siete colores de categoría que Model2Blockly materializa y Movie omite;
- veintidós propiedades de composición exacta de las categorías estándar,
  principalmente la categoría `Lists` ausente y la selección de bloques en
  `Logic`, `Loops`, `Math` y `Colour`;
- cuatro entradas de paleta con sombras o valores iniciales específicos;
- una diferencia en la configuración automática del separador.

Los cinco generadores conservan sus clases y plantillas principales. Se
obtienen 16 coincidencias de 20 propiedades; las cuatro diferencias son los
valores alternativos que el JavaScript oficial aplica cuando faltan entradas
geométricas o el color.

La referencia oficial dentro del alcance contiene 449 líneas no vacías y sin
comentarios, frente a 75 líneas de `.m2b`, una reducción del 83,30 %. La cifra
incluye la extensa paleta oficial del nivel 10 y debe leerse junto con la
reproducción parcial de su composición. El smoke test confirma además que el
editor generado carga cuatro raíces de ejemplo, produce JSON y exporta XMI de
dominio sin incidencias de validación.
