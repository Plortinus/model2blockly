# E10 — Pond

Este caso evalúa el editor Blockly completo de Pond Duck. A diferencia de Pond
Tutor, no selecciona un nivel didáctico: incluye todas las acciones y consultas
de Pond, las extensiones de lógica y matemáticas, y las categorías dinámicas de
variables y funciones.

La referencia procede del commit fijado
`5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2` de `google/blockly-games`:

- [`blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/src/blocks.js)
  define nueve bloques de dominio, el número adaptativo, la función matemática
  unaria y sus generadores;
- [`js-blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/src/js-blocks.js)
  adapta bloques estándar de lógica, bucles, matemáticas, variables y
  procedimientos a una sintaxis JavaScript;
- [`html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/duck/src/html.js)
  construye la paleta completa de seis categorías;
- [`default-ducks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/pond/duck/default-ducks.js)
  aporta el `pond_cannon` inicial del pato editable.

Los intervalos, hashes y grupos métricos exactos se conservan en
[`baseline/extraction-plan.json`](baseline/extraction-plan.json) y
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json). Se
comparan 24 tipos accesibles desde la paleta estática o sus flyouts dinámicos.
El motor de batalla, los avatares, la física, la simulación y la ejecución
gráfica quedan fuera del alcance.

## Compatibilidad y tratamientos

BASELINE ejecuta sin modificar los fragmentos oficiales. El adaptador aporta
fuera de la métrica compatibilidad para las APIs retiradas `Blockly.Mutator`,
`FieldAngle` y la alineación de entradas. La capa solo traduce llamadas de API;
las formas observadas siguen procediendo del código oficial.

M2B parte de [`source.m2b`](source.m2b). El modelo declara seis categorías y 24
bloques concretos: nueve bloques de Pond, cinco formas de lógica y control,
seis formas matemáticas, dos de variables y cuatro de procedimientos. Las
categorías `Variables` y `Functions` se modelan estáticamente para hacer
explícito su contenido, porque el DSL no declara callbacks de flyout.

## Reproducción

Desde la raíz del repositorio:

```bash
npm run prepare:evaluation-pond
npm run verify:evaluation-pond
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E10_pond_duck/generated
```

La verificación usa Blockly 13.1.1, Geras, Classic, locale inglés y la misma
ventana controlada que el resto del corpus. Produce descriptores canónicos y
las capturas [`baseline.png`](results/screenshots/baseline.png) y
[`m2b.png`](results/screenshots/m2b.png).

## Resultado

El descriptor compara 813 propiedades aplicables del editor:

- 681 coincidencias;
- 37 reproducciones parciales;
- 5 discrepancias directas;
- 90 propiedades no soportadas;
- 83,76 % de paridad funcional;
- clasificación: reproducción parcial;
- cero errores de carga y cero diferencias sin justificar.

Las 37 aproximaciones conservan 18 labels de llamada, siete configuraciones de
sombra, los estados iniciales de lógica, matemáticas, variables y
procedimientos, el mutador inicial de `controls_if` y el número adaptativo. Las
cinco discrepancias directas son las comprobaciones de salida perdidas al
redefinir `logic_compare`, `logic_operation`, `logic_boolean`,
`math_arithmetic` y `math_random_float`.

Las 90 propiedades no declarables se concentran en política de conexiones
(27), filas dummy explícitas o nombradas (40), campos de variable y categorías
dinámicas (8), composición y color de la paleta (9), y seis propiedades
puntuales relativas a tipos múltiples, alineación, precisión y espacio inicial.

Los generadores obtienen 68 coincidencias de 96 propiedades. Siete plantillas
son parciales: `controls_if`, las cuatro formas de procedimiento y las dos
operaciones con variables. Las 21 propiedades no soportadas corresponden a
valores alternativos de entradas, mapeos de enums y bases de nombres o
definiciones. No se excluye ninguna diferencia del generador.

La referencia oficial mantenida dentro del alcance contiene 740 líneas no
vacías y sin comentarios, frente a 352 líneas de `.m2b`, una reducción del
52,43 %. Pond y Pond Tutor reutilizan fragmentos de `blocks.js` y
`js-blocks.js`; el agregado del corpus debe deduplicar esas líneas compartidas
antes de presentar un total global de esfuerzo.
