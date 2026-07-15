# E09 — Pond Tutor

Este caso evalúa la configuración Blockly del nivel 9 de Pond Tutor. Los niveles
pares abren un editor de texto y los impares usan bloques; por ello el nivel 9
es la última configuración comparable del tutor y reúne `scan`, `cannon`,
`swim`, `stop`, las coordenadas, condicionales, comparación, booleanos, bucle y
el número adaptativo de Pond.

La referencia procede del commit fijado
`5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2` de `google/blockly-games`:

- [`blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/src/blocks.js)
  define los bloques de dominio, sus generadores y `pond_math_number`;
- [`js-blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/src/js-blocks.js)
  adapta cuatro bloques estándar a una sintaxis similar a JavaScript;
- [`html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/tutor/src/html.js)
  construye la paleta dependiente del nivel;
- [`main.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/tutor/src/main.js)
  carga el `pond_cannon` inicial con sus dos sombras.

Los intervalos y hashes exactos se conservan en
[`baseline/extraction-plan.json`](baseline/extraction-plan.json) y
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json). Los
bloques `health`, `speed` y `log`, aunque se registran al cargar el fichero
compartido, no se cuentan ni comparan porque el nivel 9 no los ofrece. El motor
de batalla, avatares, proyectiles, simulación y editor textual quedan fuera del
alcance.

## Compatibilidad y tratamientos

BASELINE ejecuta sin modificar los fragmentos oficiales. Como la referencia
usa APIs retiradas, el adaptador proporciona fuera de la métrica una capa de
compatibilidad para `Blockly.Mutator` y `FieldAngle`; esta capa traduce las APIs,
no redefine la forma observada. Se instancian once tipos y se carga la paleta y
el espacio inicial oficiales del nivel 9.

M2B parte de [`source.m2b`](source.m2b). El modelo declara cuatro categorías,
seis bloques de Pond, cuatro redefiniciones estándar y el número adaptativo. Las
llamadas visibles conservan por separado nombre y puntuación mediante labels de
solo lectura. Las entradas angulares incorporan sombras `pond_math_number`,
aunque el DSL no configura su valor o mutación por cada entrada.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-pond-tutor
npm run verify:evaluation-pond-tutor
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E09_pond_tutor/generated
```

La verificación usa Blockly 13.1.1, Geras, Classic, locale inglés y los mismos
tamaños de navegador y workspace que los casos anteriores. Produce los
descriptores canónicos y las capturas
[`baseline.png`](results/screenshots/baseline.png) y
[`m2b.png`](results/screenshots/m2b.png).

## Resultado

El descriptor compara 397 propiedades aplicables del editor:

- 340 coincidencias;
- 19 reproducciones parciales;
- 2 discrepancias directas;
- 36 propiedades no soportadas;
- 85,64 % de paridad funcional;
- clasificación: reproducción parcial;
- cero errores de carga y cero diferencias sin justificar.

Las 19 aproximaciones conservan el aspecto de seis llamadas mediante labels
declarativos (12 propiedades), el estado inicial de tres comportamientos
dinámicos —mutador de `controls_if`, validador de `logic_compare` y campo
numérico/ángulo— y los cuatro tipos de sombra de la paleta. Las dos discrepancias
son las comprobaciones de salida Boolean perdidas al redefinir los nombres
reservados `logic_compare` y `logic_boolean`.

Las 36 propiedades no declarables se distribuyen entre comprobaciones de
entrada con varios tipos (3), política de conexiones (14), filas dummy y
disposición JavaScript (10), precisión numérica (1), composición exacta de la
paleta (7) y espacio inicial (1). Las categorías específicas coinciden en
contenido, pero la plantilla añade otras cinco categorías estándar que no se
pueden desactivar.

Los generadores obtienen 35 coincidencias de 44 propiedades. La plantilla base
de `controls_if` es parcial porque no recorre ramas creadas por el mutador. Las
ocho diferencias restantes corresponden a valores alternativos de entradas,
mapeos de enums y protección del bucle; ninguna se oculta como exclusión.

La referencia oficial mantenida dentro del alcance contiene 415 líneas no
vacías y sin comentarios, frente a 154 líneas de `.m2b`, una reducción del
62,89 %. Pond Tutor y Pond comparten parte de `blocks.js` y `js-blocks.js`; al
agregar el corpus esas líneas compartidas no deberán presentarse como dos
implementaciones independientes.
