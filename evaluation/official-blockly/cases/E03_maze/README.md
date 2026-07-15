# E03 — Maze

## Alcance

El caso reproduce el subsistema Blockly del nivel 10 de Maze: los cinco bloques
`maze_moveForward`, `maze_turn`, `maze_forever`, `maze_if` y `maze_ifElse`, la
paleta de ese nivel y sus plantillas de generación de JavaScript. Se excluyen el
mapa, Pegman, las animaciones, la simulación del camino y la interfaz de niveles.

La referencia procede de los ficheros oficiales
[`appengine/maze/src/blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/maze/src/blocks.js),
[`appengine/maze/src/html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/maze/src/html.js) y
[`json/en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json).
Los intervalos exactos, las copias sin modificar y sus hashes se encuentran en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json). El
marcador gráfico usado por `maze_forever` también se extrae del mismo commit y
se valida mediante SHA-256.

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida con Model2Blockly.
- [`source.ecore`](source.ecore): especificación Ecore equivalente para la
  comprobación de la segunda ruta de entrada.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): carga los fragmentos oficiales
  en el contenedor común y fija la paleta del nivel 10.
- [`m2b/adapter.mjs`](m2b/adapter.mjs) y
  [`ecore/adapter.mjs`](ecore/adapter.mjs): cargan directamente los JavaScript
  generados por cada ruta.
- [`generated/`](generated/) y [`generated-ecore/`](generated-ecore/): salidas
  reales de las cadenas Xtext/Ecore → EditorSpec EMF → XMI intermedio → Blockly.
- [`results/comparison.json`](results/comparison.json): comparación propiedad a
  propiedad.
- [`assessment.json`](assessment.json): reglas que clasifican y justifican cada
  diferencia frente al límite de soporte actual.
- [`results/metrics.json`](results/metrics.json): LOC, bytes y reducción.
- [`results/route-comparison.json`](results/route-comparison.json): igualdad
  estructural entre Ecore y `.m2b`.
- [`results/screenshots/`](results/screenshots/): capturas de los tres tratamientos
  con Blockly 13.1.1, Geras, tema Classic, locale inglés y el mismo viewport.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-maze
npm run verify:evaluation-maze
```

La primera orden obtiene únicamente los intervalos declarados del commit oficial
y regenera el editor desde `.m2b` y Ecore. La segunda valida hashes y esquemas,
carga los tres tratamientos en Chromium, actualiza descriptores, métricas y
capturas, y exige igualdad exacta entre las dos rutas de entrada.

## Resultado del piloto

Con el descriptor actual se compararon 137 propiedades aplicables del editor:

- 111 coincidencias;
- 26 diferencias;
- 81,02 % de paridad funcional;
- clasificación: reproducción parcial.

Los cinco tipos de bloque, colores, tooltips, desplegables y entradas de
sentencias están presentes. Las diferencias se concentran en cuatro límites:

- Model2Blockly deriva comprobaciones de tipo `Command` y conexiones anterior y
  siguiente a partir de la herencia y las contenciones, mientras que Maze usa
  conexiones sin tipo y `maze_forever` no tiene conexión siguiente;
- la especificación actual no separa explícitamente la cabecera en una entrada
  ficticia (`input_dummy`), por lo que el contenido es equivalente pero la
  disposición de `maze_forever`, `maze_if` y `maze_ifElse` no es idéntica;
- la paleta generada contiene una sola entrada de `maze_turn`, sin las dos
  instancias oficiales preconfiguradas para izquierda y derecha;
- las plantillas oficiales añaden identificadores de bloque para animar la
  ejecución y un detector de bucle infinito, elementos propios de la capa de
  ejecución excluida del editor generado.

Las 26 diferencias del editor se clasifican como `unsupported`: política de
conexiones (14), entrada ficticia y disposición (9), y entradas de paleta
preconfiguradas (3). La comparación de generadores obtiene 15 coincidencias de
15 propiedades aplicables; cuatro trazas `block_id` y una protección de bucle se
conservan como diferencias `excluded` del runtime.

La fuente oficial dentro del alcance contiene 178 líneas no vacías y sin
comentarios, frente a 73 en `.m2b`, una reducción del 58,99 %. Como en Graph,
esta reducción debe leerse junto con la reproducción parcial y no como una
equivalencia completa.

La ruta Ecore produce el mismo descriptor canónico que `.m2b` en bloques,
desplegables, imagen, entradas de sentencias, conexiones, paleta, metadatos de
generadores y errores de carga. Ecore se excluye del cálculo de LOC: esta
comparación comprueba coherencia funcional entre entradas, no concisión textual.
