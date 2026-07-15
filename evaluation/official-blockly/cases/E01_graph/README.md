# E01 — Graph Demo

## Alcance

El caso reproduce el subsistema Blockly de Graph Demo: los bloques
`graph_get_x` y `graph_set_y`, su paleta, la configuración inicial del espacio
de trabajo y las plantillas de generación de JavaScript. Se excluyen Google
Charts, el trazado de puntos y la evaluación de la fórmula.

La referencia procede del fichero oficial
[`examples/graph-demo/index.html`](https://github.com/google/blockly-samples/blob/62ce120977078bf9179c5633681ee70446e5073b/examples/graph-demo/index.html).
Los intervalos exactos, las copias sin modificar y sus hashes se encuentran en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida con Model2Blockly.
- [`source.ecore`](source.ecore): especificación Ecore equivalente para la
  comprobación de la segunda ruta de entrada.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): carga los fragmentos oficiales
  en el contenedor común.
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
- [`results/screenshots/`](results/screenshots/): capturas auxiliares de los tres
  tratamientos bajo los mismos controles.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-graph
npm run verify:evaluation-graph
```

La primera orden vuelve a descargar únicamente los intervalos declarados del
commit oficial y regenera el editor desde `.m2b` y Ecore. La segunda valida
hashes y esquemas, carga los tres tratamientos en Chromium, compara la
referencia con `.m2b` y exige igualdad exacta entre las dos rutas de entrada.

## Resultado del piloto

Con el descriptor actual se compararon 91 propiedades del editor:

- 77 coincidencias;
- 14 diferencias;
- 84,62 % de paridad funcional;
- clasificación: reproducción parcial.

Las 14 diferencias se clasifican como `unsupported`: política de conexiones
(2), efecto interactivo del editor (1), composición de la paleta (9), valor
preconfigurado de una entrada (1) y espacio de trabajo inicial (1). No quedan
diferencias sin clasificar.

Las formas, campos, entradas, tipos, colores, tooltips y enlaces de ayuda de los
dos bloques coinciden. Las diferencias se concentran en:

- conexiones anterior y siguiente añadidas a `graph_set_y`;
- ausencia del efecto que hace no eliminable a `graph_set_y`;
- contenido y orden de las categorías de la paleta;
- ausencia de la configuración inicial `y = x²`.

Las plantillas de generación coinciden, salvo el efecto lateral que cambia la
propiedad `deletable` de `graph_set_y`: 7 de 8 propiedades.

La fuente oficial dentro del alcance contiene 329 líneas no vacías y sin
comentarios, frente a 28 en `.m2b`, una reducción del 91,49 %. Este dato debe
interpretarse junto con la paridad parcial: el menor tamaño no implica que las
14 propiedades diferentes hayan sido reproducidas.

La ruta Ecore produce el mismo descriptor canónico que `.m2b` en bloques,
campos, entradas, conexiones, paleta, espacio inicial, metadatos de generadores
y errores de carga. Ecore se excluye del cálculo de LOC. La construcción de la
ruta hizo explícito que `graph_get_x`, además de heredar de `Number`, debe
declararse como bloque de salida en la anotación Ecore.
