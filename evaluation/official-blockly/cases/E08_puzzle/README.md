# E08 — Puzzle

Este caso evalúa el subsistema de edición del juego oficial Puzzle. A diferencia
de los casos anteriores, la aplicación no ofrece una paleta: crea al iniciar
cuatro bloques `animal`, cuatro `picture` y ocho `trait`, los marca como no
eliminables y los distribuye en posiciones aleatorias. El usuario resuelve el
ejercicio conectando nombres, fotografías, números de patas y rasgos.

La referencia procede del commit fijado
`5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2` de `google/blockly-games`:

- [`blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/puzzle/src/blocks.js)
  define los tres tipos, sus mutaciones y el método `populate`;
- [`data.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/puzzle/src/data.js)
  contiene los cuatro animales y sus ocho rasgos;
- [`main.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/puzzle/src/main.js)
  construye el inventario inicial;
- [`en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json)
  aporta las etiquetas del editor.

Los intervalos exactos, hashes y cuatro imágenes JPEG copiadas se registran en
[`baseline/extraction-plan.json`](baseline/extraction-plan.json) y
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json). La
validación de la respuesta, el diálogo de finalización y la navegación son
runtime de la aplicación y quedan fuera del alcance; no se excluye ninguna
propiedad del editor por el mero hecho de que el DSL no pueda expresarla.

## Tratamientos comparados

El tratamiento BASELINE carga sin modificar la definición oficial y llama a
`populate(1)` o `populate(1, 1)` para observar un estado representativo y
repetible: pato, su fotografía y el rasgo «Feathers». Además, normaliza el
espacio inicial como un inventario semántico de 16 bloques, sin comparar las
coordenadas aleatorias.

El tratamiento M2B parte de [`source.m2b`](source.m2b), que declara los mismos
tres conceptos y ese estado representativo. De este modo se comprueban también
la fotografía, sus dimensiones y el texto alternativo; los otros tres animales
no se simulan mediante bloques adicionales. Esta decisión evita presentar como
equivalentes cuatro definiciones estáticas y un único bloque oficial cuyo estado
se modifica mediante datos y mutación.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-puzzle
npm run verify:evaluation-puzzle
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E08_puzzle/generated
```

La preparación vuelve a extraer las fuentes del commit fijado y regenera el
editor. La verificación comprueba los hashes y esquemas, carga ambos tratamientos
en Chromium bajo Blockly 13.1.1, Geras, Classic y locale inglés, produce los
descriptores canónicos y guarda las capturas
[`baseline.png`](results/screenshots/baseline.png) y
[`m2b.png`](results/screenshots/m2b.png).

## Resultado

El descriptor compara 75 propiedades aplicables del editor:

- 59 coincidencias;
- 3 reproducciones parciales;
- 13 propiedades no soportadas;
- 78,67 % de paridad funcional;
- clasificación: reproducción parcial;
- cero errores de carga y cero diferencias sin justificar.

Se registran los tres tipos y coinciden sus colores, textos, tooltips, conexiones
de salida de `picture` y la imagen representativa. Las tres propiedades parciales
son los comportamientos dinámicos de `animal`, `picture` y `trait`: el estado del
pato es utilizable, pero el modelo no puede ejecutar `populate`, cambiar entre
los cuatro animales ni persistir esa elección mediante mutaciones.

Las trece propiedades no soportadas corresponden a filas `input_dummy`
explícitas o con nombre, alineación de PIC, política de conexiones, ausencia
total de paleta y espacio inicial. La comparación conserva las dos ausencias de
fila dummy como observaciones separadas porque representan filas oficiales
distintas.

Puzzle no registra generadores, ya que no ejecuta código del usuario. El editor
generado añade serializadores JavaScript de respaldo para los tres tipos; por
ello coinciden 6 de 12 propiedades y las seis propiedades extra se clasifican
como discrepancia directa, no como mejora de paridad ni como exclusión.

La referencia oficial mantenida dentro del alcance contiene 221 líneas no
vacías y sin comentarios, frente a 46 líneas de `.m2b`, una reducción del
79,19 %. Debe leerse junto con la reproducción parcial del estado dinámico y del
espacio inicial.
