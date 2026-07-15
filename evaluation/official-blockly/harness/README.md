# Contenedor común de evaluación

El contenedor carga un tratamiento en una página Blockly controlada y publica
su descriptor canónico en `window.__M2B_EVALUATION__`. Los tratamientos se
cargan por separado para evitar colisiones entre tipos de bloque globales.

## Ejecución

La comprobación automatizada se ejecuta desde la raíz del repositorio:

```bash
npm run verify:evaluation-harness
```

La prueba inicia un servidor local temporal, abre Chromium con Playwright,
valida los JSON mediante sus esquemas y compara los dos tratamientos del caso
sintético. El proceso termina cerrando tanto el navegador como el servidor.

## Contrato de un adaptador

Cada tratamiento exporta por defecto un objeto con esta forma:

```js
export default {
  caseId: 'E01_graph',
  treatment: 'baseline', // baseline, m2b o ecore
  blockTypes: ['graph_get_x', 'graph_set_y'],
  toolbox: {},
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  register({ Blockly, javascriptGenerator }) {
    // Registra bloques, campos, extensiones y generadores del tratamiento.
  },
};
```

El adaptador BASELINE puede incluir una capa de compatibilidad mínima, pero no
debe reescribir la semántica oficial. Las adaptaciones se documentarán en el
caso y no entrarán en las métricas de tamaño.

## Salida del navegador

Durante la carga:

```js
window.__M2B_EVALUATION__ = {
  status: 'loading',
  descriptor: null,
  error: null,
};
```

Al terminar, `status` será `ready` y `descriptor` cumplirá
[`editor-descriptor.schema.json`](../schema/editor-descriptor.schema.json). Si
ocurre un error, `status` será `error` y `error` contendrá su traza.

La página se abre mediante un parámetro del mismo origen:

```text
harness/index.html?adapter=/evaluation/official-blockly/cases/E01_graph/baseline/adapter.mjs
```

No se admiten adaptadores de otros orígenes.

## Clasificación de diferencias

`verify-case.mjs` compara primero los valores canónicos sin interpretación. Cada
diferencia debe coincidir después con exactamente una regla del
`assessment.json` del caso. La regla declara si la propiedad es parcial,
distinta, no soportada o excluida, e incluye la capacidad afectada y una
justificación. Los esquemas
[`case-assessment.schema.json`](../schema/case-assessment.schema.json) y
[`comparison.schema.json`](../schema/comparison.schema.json) impiden guardar
resultados sin clasificar o con una estructura diferente entre casos.

Los campos no se comparan únicamente por índice: se emparejan por clase, nombre,
valor, texto y opciones, y su orden visible se evalúa de forma separada. Esto
evita que una diferencia de disposición produzca varias diferencias ficticias
de contenido.

Durante la construcción de un caso puede ejecutarse
`node evaluation/official-blockly/tests/verify-case.mjs E04_bird --draft`. Este
modo imprime todas las diferencias brutas y termina con código 2, pero no escribe
resultados. La ejecución normal sigue exigiendo una clasificación completa.
