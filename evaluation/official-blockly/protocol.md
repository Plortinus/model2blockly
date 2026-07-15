# Protocolo de evaluación con editores oficiales de Blockly

## 1. Objetivo

El objetivo de esta evaluación es determinar hasta qué punto Model2Blockly
permite especificar y regenerar subsistemas de edición Blockly existentes. No
se pretende reproducir las aplicaciones completas que integran esos editores.

El corpus reúne las ocho aplicaciones enumeradas por el catálogo oficial de
[Blockly Games](https://blockly.games/about?lang=en) —Puzzle, Maze, Bird,
Turtle, Movie, Music, Pond Tutor y Pond— y dos demostraciones del repositorio
oficial [blockly-samples](https://github.com/google/blockly-samples/tree/main/examples):
Graph Demo y JS-Interpreter Wait. Los commits y ficheros exactos se registran en
[`manifest.json`](manifest.json).

## 2. Preguntas de evaluación

- **RQ1 — Reproducción funcional:** ¿qué proporción de las propiedades
  observables de los diez editores puede reproducirse mediante Model2Blockly?
- **RQ2 — Esfuerzo de especificación:** ¿cómo cambia el tamaño de la fuente que
  debe mantener el desarrollador frente a una implementación directa con las
  APIs JSON o JavaScript de Blockly?
- **RQ3 — Límites:** ¿qué características de los editores oficiales no pueden
  expresarse, o solo pueden expresarse parcialmente, con el metamodelo y el DSL
  actuales?
- **RQ4 — Rutas de entrada:** para una muestra simple, media y compleja, ¿las
  rutas Ecore y `.m2b` producen el mismo descriptor canónico de editor?

La comparación de tamaño de RQ2 se apoya en que Blockly permite definir bloques
mediante JSON o mediante JavaScript, y recomienda JSON cuando sus capacidades
son suficientes. Véanse la
[documentación de creación de bloques](https://developers.google.com/blockly/guides/create-custom-blocks/define-blocks)
y la
[estructura de las definiciones JSON](https://developers.google.com/blockly/guides/create-custom-blocks/define/structure-json).

## 3. Unidad de análisis

La unidad de análisis no es la aplicación completa que integra Blockly, sino su
**subsistema de edición basado en bloques**. Cada caso corresponde a una
configuración concreta y reproducible de dicho subsistema. Cuando una paleta
depende del nivel, `manifest.json` fija un nivel determinado.

La evaluación distingue tres capas:

1. **Editor Blockly:** definiciones de bloques, campos, entradas, conexiones,
   paleta y comportamiento interactivo del propio bloque. Es la capa principal
   de RQ1.
2. **Generación de código:** generadores asociados a los bloques. Se informa por
   separado para no confundir una forma de bloque correcta con la ejecución de
   su código.
3. **Aplicación de dominio:** simulación, interpretación, audio, animaciones,
   lienzos y demás lógica que consume el programa creado en el editor. Queda
   fuera del alcance.

El término *renderer de Blockly* designa aquí el componente que dibuja los
bloques —por ejemplo, Geras o Zelos—. Este se fijará como variable de control en
el contenedor común. No debe confundirse con la visualización del dominio, como
el mapa de Maze o el lienzo de Turtle.

## 4. Alcance funcional

### 4.1. Propiedades incluidas

Se inspeccionarán, cuando existan en el caso de referencia:

- identificador y tipo del bloque;
- orden de los elementos visibles del bloque;
- campos: nombre, clase, valor inicial, opciones declarativas y, para números,
  mínimo, máximo y precisión;
- entradas: nombre, clase, comprobaciones de tipo y alineación;
- conexiones anterior, siguiente y de salida, incluidas sus comprobaciones;
- disposición en línea o externa;
- color, tooltip y URL de ayuda;
- categorías y orden de la paleta;
- bloques, valores, campos y sombras preconfigurados en la paleta;
- estado habilitado y otras propiedades declarativas de los elementos de la
  paleta;
- extensiones, validadores, mutators, estado extra y cambios dinámicos de forma
  que modifiquen el comportamiento observable del editor;
- presencia y estructura comparable de un generador de código, medida fuera de
  la puntuación principal del editor.

Los campos personalizados, mutators y validadores pertenecen al alcance aunque
requieran JavaScript. Su posible ausencia en Model2Blockly debe registrarse como
limitación, no eliminarse silenciosamente del denominador.

### 4.2. Elementos excluidos

No forman parte de la puntuación ni del cálculo de tamaño:

- HTML y CSS de la aplicación contenedora;
- mapas, lienzos, gráficos, audio y animaciones del dominio;
- motores de ejecución, simuladores e intérpretes;
- lógica de niveles, puntuación, navegación y diálogos ajenos al editor;
- imágenes, sonidos, traducciones y demás recursos estáticos;
- comunicación con servidores o ejecución de batallas;
- código de Blockly y de otras bibliotecas de terceros;
- salidas generadas por Model2Blockly.

Las exclusiones específicas de cada caso se enumeran en `excludedDomainLayer`
del manifiesto.

## 5. Diseño experimental

### 5.1. Tratamientos comparados

Para cada caso se construirán dos tratamientos:

- **BASELINE:** definición oficial del subsistema de edición, extraída del
  commit fijado en el manifiesto.
- **M2B:** modelo `.m2b` escrito para ese caso y el editor generado por
  Model2Blockly.

En Graph, Maze y Turtle se añadirá un tercer tratamiento, **ECORE**, para
comprobar la equivalencia entre rutas de entrada. Ecore no se empleará en la
comparación de concisión textual porque XML y el DSL no ofrecen unidades de
edición equivalentes.

### 5.2. Contenedor común

BASELINE y M2B se cargarán en un mismo contenedor de prueba con las siguientes
variables controladas:

- versión de Blockly;
- renderer de Blockly;
- tema y locale;
- dimensiones del área de trabajo;
- escala, posición inicial y conjunto de plugins auxiliares.

El experimento fija Blockly 13.1.1, el renderer Geras, el tema Classic, el
locale inglés, una ventana de 1280 × 800 píxeles y un área de trabajo de
1024 × 640 píxeles. La misma configuración se usa para los dos tratamientos de
un caso. La dependencia se instala localmente y su versión exacta se declara en
`package.json`, por lo que el contenedor no depende de una versión variable
obtenida desde un CDN.

El código oficial antiguo puede requerir una capa mínima de compatibilidad para
cargarse con la versión común de Blockly. Esa capa deberá:

1. conservar la semántica de la definición original;
2. estar separada de los ficheros BASELINE;
3. documentar cada adaptación;
4. quedar excluida del cómputo de líneas del autor original.

### 5.3. Selección de configuraciones dependientes del nivel

Se elige la configuración que expone el conjunto más completo de características
Blockly sin cambiar a otro tipo de editor. Por esta razón Pond Tutor usa el
nivel 9: sus niveles impares muestran Blockly y el nivel 9 es el último de ese
tipo. Las elecciones restantes están declaradas en `configuration` dentro del
manifiesto.

## 6. Extracción de la referencia

Los ficheros enumerados en `baselineFiles` son fuentes candidatas, no unidades
que deban contarse completas. Muchos mezclan bloques con lógica de aplicación.
Para evitar inflar artificialmente el tamaño de BASELINE, se aplicarán estas
reglas:

1. extraer únicamente definiciones de bloques, extensiones del editor,
   generadores y construcción de la paleta;
2. registrar el fichero, las líneas o nodos sintácticos de origen y un hash del
   fragmento;
3. conservar comentarios solo si pertenecen al fragmento mantenido, pero
   ofrecer también el resultado sin comentarios;
4. no contar importaciones, licencias, internacionalización, lienzos ni lógica
   de ejecución;
5. si dos configuraciones oficiales comparten un fichero, mantener la
   procedencia compartida y no fingir que son implementaciones independientes;
6. medir por separado la definición del editor y los generadores de código.

Puzzle no posee una paleta convencional: su configuración inicial del espacio
de trabajo se tratará como parte observable del editor. Pond Tutor y Pond
comparten definiciones de dominio, pero emplean configuraciones de paleta
distintas; los resultados señalarán expresamente esta dependencia.

## 7. Descriptor canónico

Cada tratamiento se transforma en un JSON canónico independiente de la sintaxis
de origen y validado mediante
[`editor-descriptor.schema.json`](schema/editor-descriptor.schema.json). El
descriptor tiene, de forma resumida, esta estructura lógica:

```json
{
  "blocks": [
    {
      "type": "example_block",
      "elements": [],
      "inputs": [],
      "connections": {
        "previous": null,
        "next": null,
        "output": null
      },
      "inputsInline": false,
      "colour": null,
      "tooltip": null,
      "helpUrl": null,
      "dynamicBehaviour": []
    }
  ],
  "toolbox": [],
  "generators": []
}
```

El comparador no depende del orden de las propiedades JSON, pero sí del orden
visible de campos, entradas, categorías y elementos de la paleta. La procedencia
de cada fragmento oficial se valida por separado con
[`baseline-extraction.schema.json`](schema/baseline-extraction.schema.json), que
registra fichero, intervalo de líneas, clases de contenido y hash SHA-256.

Los campos se emparejan primero por clase, nombre y contenido observable. Su
posición se compara después como una propiedad independiente. De esta forma, un
campo que cambia de entrada o de posición produce una diferencia de disposición,
pero no genera además falsos cambios de texto, valor y opciones al compararse
accidentalmente con otro campo.

## 8. Métricas

### 8.1. Reproducción del editor

Para cada propiedad atómica del alcance se asignará uno de estos estados:

- `match`: valor y comportamiento observables equivalentes;
- `partial`: representación aproximada con una diferencia documentada;
- `mismatch`: Model2Blockly genera un valor diferente;
- `unsupported`: no existe una representación en Model2Blockly;
- `error`: el bloque o editor no puede cargarse;
- `excluded`: la diferencia se observa y documenta, pero pertenece a la capa de
  aplicación o ejecución excluida y no entra en el denominador;
- `not-applicable`: propiedad ausente en la referencia; no entra en el
  denominador.

`partial` se reserva para una aproximación observable de la misma capacidad;
`mismatch`, para una capacidad disponible cuyo valor generado no coincide; y
`unsupported`, para una propiedad que no puede declararse con el DSL, el modelo
intermedio o el generador actuales. Esta distinción evita denominar
«limitación» a una simple omisión del modelo del caso.

Cada caso contiene un `assessment.json`, validado mediante
[`case-assessment.schema.json`](schema/case-assessment.schema.json). Toda
propiedad inicialmente diferente debe coincidir con una única regla que indique
estado, capacidad afectada, límite y justificación. Una diferencia sin regla,
una regla ambigua o una regla obsoleta hacen fallar la evaluación. El resultado
se valida a su vez con
[`comparison.schema.json`](schema/comparison.schema.json).

La métrica principal es:

```text
propiedades_aplicables = match + partial + mismatch + unsupported + error
paridad_editor = propiedades_match / propiedades_aplicables × 100
```

`partial`, `mismatch`, `unsupported` y `error` permanecen en el denominador. Se
publicarán también sus recuentos para que el porcentaje no oculte la naturaleza
de las diferencias. `excluded` se publica por separado y nunca se convierte
silenciosamente en una coincidencia.

Clasificación resumida por caso:

- **completa:** 100 % de paridad y ningún error de carga;
- **parcial:** el editor se carga, pero existe al menos una propiedad no
  coincidente;
- **no reproducible:** el editor generado o sus bloques principales no pueden
  cargarse.

### 8.2. Generadores

Los generadores se clasificarán por bloque como:

- equivalente;
- parcialmente equivalente;
- ausente en Model2Blockly;
- no aplicable.

La comparación verificará la presencia, precedencia y plantilla o estructura
del código cuando exista una correspondencia directa. Instrumentación como los
identificadores usados para animar Maze o la protección contra bucles del motor
se conserva con estado `excluded`: no aumenta ni reduce la paridad del núcleo
del generador. La ejecución dentro del motor de dominio no forma parte de esta
métrica. Blockly describe por separado
esta responsabilidad en su
[documentación de generadores de bloques](https://developers.google.com/blockly/guides/create-custom-blocks/code-generation/block-code).

### 8.3. Tamaño de la fuente mantenida

Se recopilarán para BASELINE y `.m2b`:

- bytes UTF-8;
- líneas totales;
- líneas no vacías;
- líneas no vacías y sin comentarios;
- bloques declarados;
- líneas no vacías y sin comentarios por bloque.

La reducción se calculará como:

```text
reduccion_LOC = 1 - LOC_m2b / LOC_baseline
```

Se publicarán por separado:

1. definición de bloques y paleta;
2. generadores de código;
3. total mantenido dentro del alcance.

No se utilizará el tiempo de modelado como métrica principal: los casos se
construyen con asistencia durante el desarrollo de la investigación y ese
tiempo no representaría un experimento humano controlado. LOC y bytes son
medidas repetibles y responden directamente a la comparación de tamaño indicada
en el objetivo.

## 9. Procedimiento por caso

1. Obtener el repositorio y verificar el commit del manifiesto.
2. Identificar y registrar los fragmentos BASELINE dentro del alcance.
3. Extraer el descriptor canónico esperado.
4. Escribir el modelo `.m2b` sin modificar Model2Blockly específicamente para
   ocultar diferencias del caso.
5. Generar el editor y cargar BASELINE y M2B en el contenedor común.
6. Instanciar programáticamente cada bloque y recorrer la paleta.
7. Comparar los descriptores y guardar cada diferencia.
8. Ejecutar pruebas de navegador para detectar errores de carga e interacción.
9. Tomar capturas representativas como evidencia auxiliar, nunca como criterio
   de equivalencia píxel a píxel.
10. Recopilar métricas de tamaño mediante el mismo script para todos los casos.

Antes de ampliar el corpus se ejecutarán dos pilotos: Graph Demo, por su tamaño
reducido, y Maze, por combinar varios tipos de bloque con una paleta dependiente
del nivel.

Tras estos pilotos quedan fijados el descriptor, la granularidad de propiedades,
el emparejamiento semántico de campos y las reglas de estado. Cualquier cambio
posterior exigirá incrementar la versión del esquema y volver a ejecutar todos
los casos ya evaluados.

## 10. Resultados y agregación

Se conservarán los resultados detallados de cada caso y un resumen con:

- paridad y clasificación funcional;
- recuentos de propiedades por estado;
- estado de los generadores;
- LOC y bytes de ambos tratamientos;
- reducción por caso;
- lista de características no soportadas.

Para diez casos se presentarán valores individuales, media y mediana. No se
afirmará significación estadística ni generalización a todos los editores
Blockly a partir de esta muestra intencional.

## 11. Amenazas a la validez previstas

- **Selección:** el corpus procede de Google y cubre variedad funcional, pero no
  representa todos los plugins y editores Blockly existentes.
- **Antigüedad de Blockly Games:** las fuentes pueden usar APIs anteriores. Las
  capas de compatibilidad se documentarán y no se contarán como código original.
- **Dependencia entre casos:** Pond Tutor y Pond comparten bloques. Se mostrarán
  tanto resultados por configuración como resultados sin duplicar la fuente
  compartida al resumir tamaño total.
- **Granularidad de propiedades:** un número excesivo de propiedades triviales
  podría dominar el porcentaje. El esquema canónico se fijará antes de ejecutar
  todos los casos y no se cambiará para favorecer resultados posteriores.
- **Subjetividad de `partial`:** toda asignación parcial deberá acompañarse de
  la diferencia concreta y conservarse en el resultado JSON.
- **Equivalencia visual:** capturas parecidas no prueban equivalencia funcional;
  se usarán comprobaciones estructurales y de navegador.
- **Autoría asistida:** el tiempo empleado no se interpretará como productividad
  humana; la comparación de esfuerzo se limitará a artefactos medibles.

## 12. Criterio para cerrar la evaluación

La evaluación estará completa cuando los diez casos tengan:

- fuente oficial y fragmentos de referencia trazables;
- modelo `.m2b` válido;
- resultado generado o fallo reproducible;
- descriptor comparado automáticamente;
- prueba de carga en navegador;
- métricas de tamaño;
- limitaciones documentadas.

AppMaker y los ejemplos internos de Model2Blockly se presentarán después como
evidencia complementaria de integración y verificación, no como sustitutos de
esta evaluación externa.
