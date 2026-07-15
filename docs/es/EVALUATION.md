# Evaluación con editores Blockly existentes

AppMaker es el caso de integración del proyecto: permite seguir las rutas Ecore
y `.m2b` hasta un editor ejecutable. La evaluación externa utiliza otra
evidencia. Model2Blockly se compara con diez configuraciones publicadas en los
repositorios oficiales de Blockly para medir cuánto del editor puede
reproducirse y cuánto cambia el tamaño de la fuente mantenida.

## Qué se evalúa

La unidad de análisis es el **subsistema de edición Blockly**:

- tipos de bloque, campos, entradas, conexiones y disposición;
- paleta, categorías, sombras y configuración inicial;
- comportamiento del editor observable en el navegador;
- generadores de código asociados a los bloques.

No se incluyen los motores de dominio que consumen el programa generado. Por
ejemplo, el mapa de Maze, el lienzo de Turtle, el audio de Music y las
simulaciones de Pond quedan fuera del alcance. El motor de renderizado Geras se
fija como variable de control porque forma parte de la presentación interna de
Blockly.

## Corpus y controles

El corpus contiene Graph Demo, JS-Interpreter Wait, Maze, Bird, Movie, Music,
Turtle, Puzzle, Pond Tutor y Pond. Las fuentes se fijan a revisiones concretas de
`google/blockly-games` y `google/blockly-samples`. BASELINE y M2B se cargan con
Blockly 13.1.1, motor de renderizado Geras, tema Classic, configuración regional
inglesa y los mismos tamaños de navegador y espacio de trabajo.

La comparación no depende de capturas. Cada tratamiento se convierte en un
descriptor canónico y se comparan propiedades atómicas con los estados
`match`, `partial`, `mismatch`, `unsupported`, `error` y `excluded`. Las
capturas se conservan como evidencia visual auxiliar.

## Resultados agregados

| Medida | Resultado |
| --- | ---: |
| Casos cargados sin errores | 10/10 |
| Paridad estructural ponderada del editor | 2315/2762 (83,82 %) |
| Media y mediana de paridad por caso | 83,41 % / 82,71 % |
| Paridad ponderada de generadores | 227/291 (78,01 %) |
| LOC oficiales / LOC `.m2b` | 4074 / 1121 |
| Reducción ponderada de LOC | 72,48 % |
| Reducción conservadora con fuentes compartidas deduplicadas | 70,00 % |
| Equivalencia Ecore–`.m2b` | 3/3 casos |

Los diez editores generados cargan, pero los diez se clasifican como
reproducciones **parciales**. Por tanto, el 83,82 % no significa equivalencia de
las aplicaciones completas. Las principales diferencias afectan a políticas
de conexión, composición programática de paletas, entradas usadas para
presentación y generadores que necesitan estado, mutadores o serialización
automática.

La reducción de LOC mide concisión de la fuente mantenida. No mide tiempo de
desarrollo, dificultad cognitiva ni productividad humana. Es necesario leerla
junto con la paridad funcional: la especificación es menor, pero todavía no
expresa todas las capacidades observadas.

## Resultados por caso

| Caso | Bloques | Paridad del editor | Paridad de generadores | LOC oficiales | LOC `.m2b` | Reducción LOC | Clasificación |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| Graph Demo | 2 | 77/91 (84,62 %) | 7/8 (87,50 %) | 329 | 28 | 91,49 % | parcial |
| JS-Interpreter Wait | 1 | 73/89 (82,02 %) | 4/4 (100,00 %) | 282 | 16 | 94,33 % | parcial |
| Maze | 5 | 111/137 (81,02 %) | 15/15 (100,00 %) | 178 | 73 | 58,99 % | parcial |
| Bird | 8 | 203/218 (93,12 %) | 27/31 (87,10 %) | 246 | 93 | 62,20 % | parcial |
| Movie | 5 | 236/283 (83,39 %) | 16/20 (80,00 %) | 449 | 75 | 83,30 % | parcial |
| Music | 6 | 186/232 (80,17 %) | 19/21 (90,48 %) | 545 | 94 | 82,75 % | parcial |
| Turtle | 12 | 349/427 (81,73 %) | 30/40 (75,00 %) | 669 | 190 | 71,60 % | parcial |
| Puzzle | 3 | 59/75 (78,67 %) | 6/12 (50,00 %) | 221 | 46 | 79,19 % | parcial |
| Pond Tutor | 11 | 340/397 (85,64 %) | 35/44 (79,55 %) | 415 | 154 | 62,89 % | parcial |
| Pond | 24 | 681/813 (83,76 %) | 68/96 (70,83 %) | 740 | 352 | 52,43 % | parcial |

## Evidencia visual

Las siguientes capturas utilizan los mismos controles de navegador para el
tratamiento oficial BASELINE y el editor M2B generado. Permiten una inspección
manual, pero no forman parte de una métrica de similitud entre píxeles.

### Maze

| BASELINE oficial | M2B generado |
| --- | --- |
| ![Editor Blockly oficial de Maze](../../evaluation/official-blockly/cases/E03_maze/results/screenshots/baseline.png) | ![Editor Maze generado por Model2Blockly](../../evaluation/official-blockly/cases/E03_maze/results/screenshots/m2b.png) |

### Pond

| BASELINE oficial | M2B generado |
| --- | --- |
| ![Editor Blockly oficial de Pond](../../evaluation/official-blockly/cases/E10_pond_duck/results/screenshots/baseline.png) | ![Editor Pond generado por Model2Blockly](../../evaluation/official-blockly/cases/E10_pond_duck/results/screenshots/m2b.png) |

## Rutas Ecore y `.m2b`

Graph, Maze y Turtle se modelaron por las dos rutas de entrada. En cada caso se
exigió igualdad exacta de controles, bloques, paleta, espacio de trabajo inicial,
generadores, errores y capacidad de carga. Los tres pares son equivalentes.

Ecore no se compara con `.m2b` mediante LOC. XML/XMI y un DSL textual son
unidades de edición distintas; para estas rutas la pregunta relevante es si
producen la misma especificación común del editor.

## Reproducir y consultar la evidencia

```bash
npm run verify:evaluation-completed
npm run aggregate:evaluation
```

La evidencia completa se conserva en:

- [Introducción y organización del experimento](../../evaluation/official-blockly/README.md)
- [Protocolo de evaluación](../../evaluation/official-blockly/protocol.md)
- [Resumen agregado generado](../../evaluation/official-blockly/results/summary.md)
- [Resultados agregados en JSON](../../evaluation/official-blockly/results/aggregate.json)
- [Límites de soporte observados](../../evaluation/official-blockly/support-boundaries.md)
- [Corpus, revisiones y controles](../../evaluation/official-blockly/manifest.json)
