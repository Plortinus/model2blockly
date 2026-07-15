# Resumen agregado de la evaluación oficial de Blockly

Este documento se genera de forma determinista con `npm run aggregate:evaluation` a partir de los resultados detallados de los diez casos. La unidad evaluada es el subsistema de edición Blockly; la visualización y la ejecución propias de cada aplicación quedan fuera del alcance.

## Resultados por caso

| Caso | Editor oficial | Bloques | Editor: coincidencias | Paridad | Clasificación | Generadores: coincidencias | Paridad gen. | LOC oficial | LOC .m2b | Reducción LOC |
|---|---|---:|---:|---:|---|---:|---:|---:|---:|---:|
| E01_graph | Graph Demo | 2 | 77/91 | 84.62 % | partial | 7/8 | 87.50 % | 329 | 28 | 91.49 % |
| E02_interpreter_wait | JS-Interpreter Wait | 1 | 73/89 | 82.02 % | partial | 4/4 | 100.00 % | 282 | 16 | 94.33 % |
| E03_maze | Maze | 5 | 111/137 | 81.02 % | partial | 15/15 | 100.00 % | 178 | 73 | 58.99 % |
| E04_bird | Bird | 8 | 203/218 | 93.12 % | partial | 27/31 | 87.10 % | 246 | 93 | 62.20 % |
| E05_movie | Movie | 5 | 236/283 | 83.39 % | partial | 16/20 | 80.00 % | 449 | 75 | 83.30 % |
| E06_music | Music | 6 | 186/232 | 80.17 % | partial | 19/21 | 90.48 % | 545 | 94 | 82.75 % |
| E07_turtle | Turtle | 12 | 349/427 | 81.73 % | partial | 30/40 | 75.00 % | 669 | 190 | 71.60 % |
| E08_puzzle | Puzzle | 3 | 59/75 | 78.67 % | partial | 6/12 | 50.00 % | 221 | 46 | 79.19 % |
| E09_pond_tutor | Pond Tutor | 11 | 340/397 | 85.64 % | partial | 35/44 | 79.55 % | 415 | 154 | 62.89 % |
| E10_pond_duck | Pond | 24 | 681/813 | 83.76 % | partial | 68/96 | 70.83 % | 740 | 352 | 52.43 % |

## Reproducción del editor

- Paridad ponderada: **2315/2762 (83.82 %)**.
- Paridad macro por caso: media **83.41 %** y mediana **82.71 %**.
- Estados no coincidentes: 73 parciales, 8 diferencias con capacidad disponible, 366 propiedades no soportadas y 0 errores.
- Clasificación: 10 casos parciales, 0 completos y 0 no reproducibles. Los diez editores generados cargaron sin errores.

La paridad ponderada responde por la proporción de propiedades observadas en todo el corpus. La media y la mediana macro conceden el mismo peso a cada editor, aunque sus descriptores tengan tamaños distintos.

## Generadores de código

- Paridad ponderada: **227/291 (78.01 %)**.
- Paridad macro por caso: media **82.05 %** y mediana **83.55 %**.
- Se excluyeron 17 propiedades ligadas a instrumentación o al motor de ejecución; no cuentan como coincidencias ni entran en el denominador.

## Tamaño de la fuente mantenida

Al tratar las diez configuraciones como artefactos independientes, la fuente oficial suma 4074 LOC y los modelos suman 1121 LOC: una reducción de **72.48 %**.

Pond Tutor y Pond reutilizan líneas de los mismos ficheros oficiales. Para no contarlas dos veces en el total, el cálculo conservador identifica una línea por repositorio, commit, ruta y número de línea. La referencia oficial queda en 3737 LOC, frente a las mismas 1121 LOC de los diez modelos independientes: una reducción conservadora de **70.00 %**. Se eliminan 337 LOC oficiales duplicadas; los resultados individuales no cambian.

La reducción por caso tiene una media de **73.92 %** y una mediana de **75.40 %**. No se cuentan las salidas generadas, la aplicación contenedora ni la lógica de dominio.

## Diferencias más frecuentes del editor

| Estado | Capacidad | Propiedades | Casos |
|---|---|---:|---:|
| unsupported | connection-policy | 101 | 9 |
| unsupported | toolbox-category-composition | 66 | 5 |
| unsupported | explicit-dummy-inputs | 52 | 4 |
| partial | readonly-call-labels | 30 | 2 |
| unsupported | category-colour-omission | 30 | 5 |
| unsupported | toolbox-composition | 19 | 2 |
| unsupported | toolbox-entry-presets | 19 | 7 |
| unsupported | input-alignment | 14 | 3 |
| partial | toolbox-shadow-configuration | 11 | 2 |
| unsupported | named-dummy-inputs | 11 | 3 |
| unsupported | explicit-dummy-input | 9 | 1 |
| mismatch | reserved-block-redefinition | 8 | 3 |

El detalle completo, incluidos todos los grupos de capacidades y los casos afectados, se conserva en `aggregate.json` y en el `comparison.json` de cada caso.

## Equivalencia entre Ecore y .m2b

| Caso planificado | Comparación ejecutada | Descriptor equivalente |
|---|---|---|
| E01_graph | sí | sí |
| E03_maze | sí | sí |
| E07_turtle | sí | sí |

Se han verificado 3 de las 3 rutas previstas. RQ4 dispone de toda la evidencia planificada.

## Alcance de la conclusión

Los resultados demuestran la carga y la comparación estructural de diez configuraciones oficiales bajo controles comunes. No constituyen una prueba de equivalencia de las aplicaciones completas, no miden productividad humana y no justifican significación estadística ni generalización a todos los editores Blockly.
