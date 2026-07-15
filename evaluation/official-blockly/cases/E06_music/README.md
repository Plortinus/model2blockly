# E06 — Music

## Alcance

El caso reproduce el subsistema Blockly del nivel 10 de Music: seis tipos de
bloque, el campo personalizado `FieldPitch`, los recursos gráficos de notas y
silencios, la paleta completa, el bloque inicial y los generadores JavaScript.
Se excluyen la síntesis y descarga de audio, el motor temporal de reproducción,
la partitura animada, el control de velocidad y la galería.

La referencia procede de
[`appengine/music/src/blocks.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/src/blocks.js),
[`appengine/music/src/field_pitch.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/src/field_pitch.js),
[`appengine/music/src/startcount.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/src/startcount.js),
[`appengine/music/src/html.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/src/html.js),
[`appengine/music/src/main.js`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/src/main.js),
[`appengine/music/style.css`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/appengine/music/style.css) y
[`json/en.json`](https://github.com/google/blockly-games/blob/5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2/json/en.json).
Los intervalos, copias y hashes exactos, junto con los doce recursos PNG, se
conservan en
[`baseline/baseline-extraction.json`](baseline/baseline-extraction.json).

## Artefactos

- [`source.m2b`](source.m2b): especificación mantenida.
- [`baseline/adapter.mjs`](baseline/adapter.mjs): registra `FieldPitch`, los
  bloques, la paleta, el bloque inicial y los generadores oficiales.
- [`m2b/adapter.mjs`](m2b/adapter.mjs): carga la salida generada.
- [`generated/`](generated/): resultado Xtext → EditorSpec EMF → XMI intermedio
  → Blockly.
- [`assessment.json`](assessment.json): clasificación de cada diferencia.
- [`results/comparison.json`](results/comparison.json): comparación atómica.
- [`results/metrics.json`](results/metrics.json): métricas de tamaño.
- [`results/screenshots/`](results/screenshots/): evidencia visual de ambos
  tratamientos.

## Reproducción

```bash
npm run prepare:evaluation-music
npm run verify:evaluation-music
node scripts/smoke-test-generated.mjs --generic --allow-issues evaluation/official-blockly/cases/E06_music/generated
```

La preparación obtiene únicamente los intervalos y recursos declarados del
commit fijado y regenera el editor. La verificación valida hashes y esquemas,
ejecuta ambos tratamientos en Chromium y actualiza resultados y capturas.

## Resultado

El descriptor compara 232 propiedades aplicables del editor:

- 186 coincidencias;
- 7 propiedades parcialmente reproducidas;
- 39 propiedades no soportadas;
- 80,17 % de paridad funcional;
- cero errores de carga y cero discrepancias sin clasificar.

Las siete propiedades parciales corresponden a dos sustituciones deliberadas:

- `FieldPitch` se representa mediante un campo numérico limitado a 0-12. Se
  conserva el valor usado por el generador, pero no el selector gráfico, la
  conversión entre números y nombres C3-A4 ni su interacción personalizada;
- los cinco valores de duración permanecen disponibles como un desplegable con
  etiquetas textuales `1/1` a `1/16`, pero no como imágenes con valores internos
  numéricos.

Las 39 propiedades no soportadas se agrupan en conexiones derivadas de
`MusicCommand` (9), dos filas de mensaje en `music_start` (3), composición de la
paleta estándar (16), colores de categoría omitidos por el original (6),
preconfiguración de entradas (3), separador (1) y espacio inicial (1).

En los generadores coinciden 19 de 21 propiedades aplicables. Faltan el valor
alternativo de una entrada PITCH vacía y el generador de definiciones con
contador de hilos de `music_start`. Los tres identificadores `block_id` usados
para sincronizar la visualización se muestran como diferencias, pero se
excluyen del denominador por pertenecer al runtime de reproducción.

La fuente oficial contiene 545 líneas no vacías y sin comentarios, frente a 94
en `.m2b`, una reducción del 82,75 %. En este caso la referencia incluye las 215
líneas extraídas del campo personalizado, por lo que la reducción debe
interpretarse junto con su reproducción parcial. El smoke test confirma que el
editor generado carga `music_start`, produce JSON, exporta XMI y sincroniza su
regla de validación sin incidencias.

Durante la ejecución se corrigió además un defecto independiente: el adaptador
del DSL confundía `colour 0` explícito con un color ausente. Ahora consulta el
nodo Xtext para conservar el tono cero y `music_start` coincide con el color
oficial.
