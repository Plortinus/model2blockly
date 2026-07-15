# Evaluación con editores oficiales de Blockly

[中文评估说明](../../docs/zh/EVALUATION.md)

Este directorio contiene la evaluación empírica de Model2Blockly frente a diez
configuraciones de editores publicadas por Google. La unidad de análisis es el
**subsistema de edición Blockly**, no la aplicación completa que lo integra.

Los artefactos principales son:

- [`manifest.json`](manifest.json): corpus versionado, configuración elegida y
  ficheros oficiales que sirven como referencia para cada caso.
- [`protocol.md`](protocol.md): preguntas de evaluación, alcance, métricas,
  reglas de comparación y procedimiento reproducible.
- [`support-boundaries.md`](support-boundaries.md): matriz de capacidades y
  límites fijada después de calibrar los dos pilotos.
- [`results/summary.md`](results/summary.md): tabla de los diez casos, valores
  agregados, LOC con fuente compartida deduplicada y estado de las rutas de
  entrada.
- [`results/aggregate.json`](results/aggregate.json): los mismos resultados en
  formato procesable y con el desglose completo de capacidades.
- [`harness/`](harness/): página Blockly controlada, normalizador y contrato de
  los adaptadores de cada tratamiento.
- [`schema/`](schema/): esquemas del descriptor canónico y de la extracción
  trazable de las fuentes oficiales.

## Estado

La evaluación está completa: la selección, el protocolo, el contenedor Blockly
común y los esquemas de datos están definidos, y los diez casos se han ejecutado.
El contenedor se comprueba con un caso sintético que representa
el mismo editor una vez con JSON Blockly y otra con JavaScript. Este caso solo
verifica la infraestructura y no forma parte de los diez resultados empíricos.

Los diez casos están ejecutados. Los pilotos
[`E01_graph`](cases/E01_graph/) y [`E03_maze`](cases/E03_maze/), junto con
[`E02_interpreter_wait`](cases/E02_interpreter_wait/),
[`E04_bird`](cases/E04_bird/), [`E05_movie`](cases/E05_movie/),
[`E06_music`](cases/E06_music/), [`E07_turtle`](cases/E07_turtle/),
[`E08_puzzle`](cases/E08_puzzle/),
[`E09_pond_tutor`](cases/E09_pond_tutor/) y
[`E10_pond_duck`](cases/E10_pond_duck/), disponen de referencia oficial
trazable, modelo `.m2b`, salida generada, comparación automática, métricas y
capturas. Graph, Maze y Turtle añaden una ruta Ecore equivalente y una
comprobación canónica entre ambas entradas. La granularidad y los límites están
consolidados para el corpus completo.

El resumen agregado se regenera con `npm run aggregate:evaluation` y se valida
con `npm run verify:evaluation-aggregate`. Las comparaciones funcionales y de
tamaño de los diez casos están completas. Las tres muestras previstas para la
comprobación Ecore–`.m2b` —Graph, Maze y Turtle— también están ejecutadas y
producen descriptores canónicos equivalentes.

## Principio de alcance

Se comparan definiciones de bloques, campos, entradas, conexiones, paletas y
comportamientos que modifican el editor. Se excluyen los motores de ejecución y
las visualizaciones específicas del dominio, como el mapa de Maze, el lienzo de
Turtle y el sistema de audio de Music.

Las fuentes están fijadas a commits concretos de los repositorios oficiales
[`google/blockly-games`](https://github.com/google/blockly-games) y
[`google/blockly-samples`](https://github.com/google/blockly-samples). Esto
permite repetir el experimento aunque sus ramas principales cambien.
