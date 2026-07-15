# Límites de soporte fijados durante la evaluación

## Propósito

Este documento traduce las diferencias observadas en los casos ya ejecutados a
capacidades concretas de Model2Blockly. No sustituye los resultados por caso:
cada `results/comparison.json` conserva la propiedad, el valor esperado, el
valor generado y su justificación.

Se usan cuatro niveles:

- **admitido en los pilotos:** la capacidad aparece y coincide en los dos
  tratamientos donde resulta aplicable;
- **parcial:** existe soporte, pero no todos sus parámetros son configurables;
- **no declarable:** la propiedad atómica no tiene representación actual;
- **fuera del alcance:** pertenece al runtime o a la aplicación que integra el
  editor, no al subsistema Blockly generado.

## Matriz de capacidades

| Capacidad | Nivel actual | Evidencia observada |
|---|---|---|
| Registro de tipos de bloque | Admitido en los pilotos | 2/2 tipos de Graph y 5/5 de Maze se cargan. |
| Campos declarativos, valores y opciones | Admitido en los pilotos | Entradas numéricas, desplegables, etiquetas e imagen conservan sus valores observables. |
| Color, tooltip, ayuda y disposición inline | Admitido en los pilotos | Coinciden en los siete bloques evaluados. |
| Entradas de valor y de sentencias | Admitido en los pilotos | Se generan y cargan con sus nombres y estructura de contención. |
| Política exacta de conexiones | Parcial | Model2Blockly deriva tipos y conexiones desde herencia/contención, pero no permite solicitar conexión sin tipo, desactivar solo `next` o convertir una clase concreta en bloque sin conexiones. |
| Entrada con varios tipos aceptados | No declarable | Las direcciones de Pond Tutor admiten `Number` o `Angle`; una relación value del modelo común solo tiene un tipo objetivo. |
| Alineación de entradas | No declarable | Las doce entradas numéricas de los bloques geométricos de Movie usan alineación derecha; el DSL genera la alineación predeterminada. |
| Entrada explícita `input_dummy` | No declarable | Maze necesita separar cabecera y cuerpo; el metamodelo actual adjunta los campos a la entrada de sentencias. |
| Nombre de una entrada `input_dummy` | No declarable | Puzzle conserva la imagen y sus dimensiones, pero la fila `PIC` generada es anónima. |
| Labels de llamada de solo lectura | Parcial | Pond Tutor conserva nombres y puntuación visibles, pero los nombres son campos declarativos identificados en vez de strings anónimos de Blockly. |
| Estado de bloque dirigido por datos y mutaciones | Parcial | Puzzle reproduce un animal, una imagen y un rasgo representativos, pero no puede ejecutar `populate` ni serializar cuál de los cuatro animales representan. |
| Forma de bloque dirigida por mutador | Parcial | Se reproduce la rama inicial de `controls_if`, pero no las ramas `else-if` y `else` añadidas por el mutador de Pond Tutor. |
| Variables Blockly nativas | Parcial | Pond conserva la asignación y el incremento visibles, pero un atributo string produce `FieldTextInput`, no `FieldVariable`, y no participa en la base de nombres. |
| Definiciones y llamadas de procedimientos | Parcial | Pond reproduce las formas iniciales sin parámetros, pero no el mutador de argumentos, el renombrado coordinado ni las entradas dinámicas `ARGn`. |
| Campo que alterna entre número y ángulo | Parcial | `pond_math_number` funciona en modo numérico y como sombra, pero no cambia a `FieldAngle` según la conexión ni serializa ese estado. |
| Composición completa de una paleta por categorías | Parcial | Se admiten categorías de dominio, flyout y sombras; la lista de categorías Blockly estándar y su contenido siguen una plantilla fija. |
| Omisión del color de una categoría | No declarable | Movie omite `colour` en sus categorías para delegar el estilo; Model2Blockly siempre materializa un color. |
| Entradas repetidas y preconfiguradas en la paleta | No declarable | Maze usa dos instancias de `maze_turn` con valores `DIR` distintos; el generador emite una entrada por tipo. |
| Tipo de sombra sin configuración específica | Parcial | Pond Tutor conserva `pond_math_number` como sombra, pero no sus valores 0/70 ni la mutación Angle/Number por entrada. |
| Fusión de bloques de dominio con categorías estándar | No declarable | E02 genera dos categorías `Loops`: la estándar y la que contiene `wait_seconds`. |
| Categorías dinámicas `VARIABLE` y `PROCEDURE` | No declarable | Pond requiere flyouts calculados por Blockly; las categorías del DSL solo admiten contenido estático. |
| Espacio de trabajo inicial | No declarable | Graph incluye `y = x²`, pero el DSL no posee una construcción para serializarlo como configuración inicial. |
| Ausencia total de paleta | No declarable | Puzzle crea todo el inventario al iniciar y no muestra toolbox; Model2Blockly siempre genera una paleta. |
| Precisión libre de un campo numérico | No declarable | E02 conserva mínimo 0 y máximo 600, pero el entero generado fija precisión 1 frente a precisión oficial 0. |
| Extensiones dinámicas de tooltip | No declarable | Bird cambia el tooltip de `bird_compare` según `OP` y hace que `math_number` herede el del bloque padre. |
| Campo de edición personalizado | Parcial | Music conserva PITCH como número 0-12, pero no reproduce el selector gráfico, la conversión C3-A4 ni la validación de `FieldPitch`. |
| Opciones gráficas de un desplegable | Parcial | Music conserva cinco duraciones como etiquetas textuales, pero no puede usar imágenes ni valores internos numéricos en una enumeración del DSL. |
| Valores numéricos o con espacios almacenados por un desplegable | Parcial | Turtle conserva las etiquetas visibles, pero el enum del DSL almacena identificadores válidos en vez de `20`, `90` o `Times New Roman`. |
| Varias filas `messageN` | No declarable | `music_start` separa la cabecera en `message0` y el cuerpo en `message1`; el DSL actual solo expone `message0`. |
| Extensión JavaScript registrada sobre un bloque | Parcial | Turtle conserva estáticamente las flechas de giro, pero no puede declarar `turtle_turn_arrows` como extensión ejecutable. |
| Modificación de bloques Blockly estándar | No declarable | Bird sustituye `init` de `controls_if` para eliminar sus conexiones anterior y siguiente. |
| Redefinición de nombres Blockly reservados | Parcial | `math_number`, `logic_compare` y `logic_boolean` se cargan y conservan su forma, pero pierden la comprobación de salida solicitada en el tratamiento generado. |
| Mapeo de un valor de campo a código | No declarable | El generador de Bird traduce `LT`/`GT` a `<`/`>`; la plantilla actual inserta directamente el valor almacenado. |
| Valores alternativos para entradas vacías | No declarable | Los generadores de Bird aplican `0`, `false` o la identidad booleana según qué entradas falten. |
| Generador que inserta definiciones | No declarable | `music_start` incrementa un contador e inserta funciones `startN` en `definitions_` sin emitir código local. |
| Base de nombres de variables | No declarable | Los generadores de Pond deben resolver `VAR` con la base de nombres de Blockly; las plantillas insertan el texto directamente. |
| Base de definiciones de procedimientos | No declarable | Los generadores estándar coordinan definiciones, llamadas, parámetros y nombres; una plantilla por bloque no mantiene ese estado global. |
| Ausencia explícita de tooltip | No declarable | E02 no declara tooltip; Model2Blockly introduce el label como valor predeterminado. |
| Efectos interactivos arbitrarios del editor | Parcial | Existen validaciones y comportamientos generados, pero no una declaración genérica para el `setDeletable(false)` oficial de Graph. |
| Plantilla principal del generador | Admitido en los pilotos | Graph conserva 7/8 propiedades; el núcleo de los cinco generadores Maze coincide en 15/15 propiedades aplicables. |
| Coherencia Ecore–DSL del descriptor generado | Admitido | En Turtle, ambas rutas coinciden exactamente en controles, bloques, paleta, espacio inicial, generadores, errores y carga. |
| Trazas, animación y protección del motor de dominio | Fuera del alcance | Los cinco detalles Maze basados en `block_id` e `INFINITE_LOOP_TRAP` se registran como `excluded`. |
| Renderizado o simulación del dominio | Fuera del alcance | Gráfico de Graph, mapa/Pegman de Maze, audio, lienzos y motores de juego no forman parte del editor Blockly. |

## Lectura cuantitativa de los pilotos

Graph contiene 91 propiedades aplicables: 77 coinciden y 14 se clasifican como
no soportadas. Estas últimas se agrupan en política de conexiones (2), efectos
del editor (1), composición de paleta (9), entrada preconfigurada (1) y espacio
inicial (1).

Maze contiene 137 propiedades aplicables: 111 coinciden y 26 se clasifican como
no soportadas. Se agrupan en política de conexiones (14), entrada ficticia y
disposición (9), y entradas preconfiguradas de paleta (3). Sus generadores
obtienen 15/15 coincidencias aplicables; cinco diferencias adicionales de
integración con el runtime se muestran, pero quedan excluidas del denominador.

JS-Interpreter Wait contiene 89 propiedades aplicables: 73 coinciden y 16 se
clasifican como no soportadas. El bloque y el núcleo del generador coinciden;
las diferencias se concentran en la composición de la paleta (13 propiedades),
la precisión numérica (1), el tooltip vacío (1) y el espacio inicial (1). El
enlace asíncrono con JS-Interpreter se conserva como evidencia fuera del alcance.

Bird contiene 218 propiedades aplicables del editor: 203 coinciden, 14 se
clasifican como no soportadas y una como discrepancia directa. Sus límites se
concentran en conexiones derivadas (4), extensiones de tooltip (2), el bloque
`math_number` (2), la modificación de `controls_if` (2), la paleta
preconfigurada (4) y el espacio inicial (1). En los generadores coinciden 27 de
31 propiedades aplicables; el mapeo del operador y tres reglas para entradas
vacías no son declarables. Una traza de animación se excluye del denominador.

Movie contiene 283 propiedades aplicables del editor: 236 coinciden y 47 se
clasifican como no soportadas. Se distribuyen entre alineación de entradas
(12), salida sin tipo (1), color de categoría (7), composición de categorías
estándar (22), entradas preconfiguradas (4) y separador (1). Sus cinco
plantillas principales coinciden; cuatro reglas de valores alternativos para
entradas vacías no son declarables.

Music contiene 232 propiedades aplicables del editor: 186 coinciden, 7 se
clasifican como parciales y 39 como no soportadas. Las parciales son el campo de
tono (3) y los desplegables gráficos de duración (4). El resto se concentra en
conexiones (9), filas de mensaje (3), paleta (26) y espacio inicial (1). Sus
generadores obtienen 19 de 21 coincidencias aplicables; tres trazas visuales se
excluyen.

Turtle contiene 427 propiedades aplicables del editor: 349 coinciden, 7 se
clasifican como parciales y 71 como no soportadas. Las parciales conservan la
apariencia de desplegables numéricos, familias tipográficas y flechas de giro,
pero no sus valores o mecanismo interno exactos. Las 78 diferencias se
concentran en conexiones (26), filas dummy (7), campos o extensiones (7),
paleta (37) y espacio inicial (1). Sus generadores obtienen 30 de 40
coincidencias aplicables; ocho trazas de
animación se excluyen. Las rutas Ecore y `.m2b` producen el mismo descriptor
canónico en las siete secciones comprobadas.

Puzzle contiene 75 propiedades aplicables del editor: 59 coinciden, 3 se
clasifican como parciales y 13 como no soportadas. Las parciales conservan el
estado representativo del pato, su fotografía y un rasgo, pero no el mecanismo
dinámico basado en datos y mutaciones. Las restantes se concentran en filas
dummy explícitas (5), nombre de fila dummy (1), conexiones o alineación (5),
paleta (1) y espacio inicial (1). La referencia no registra generadores; los
seis metadatos de serializadores añadidos por Model2Blockly son discrepancias
directas.

Pond Tutor contiene 397 propiedades aplicables del editor: 340 coinciden, 19 se
clasifican como parciales, 2 como discrepancias directas y 36 como no
soportadas. Las aproximaciones son labels visibles (12), tres comportamientos
dinámicos y cuatro sombras sin configuración. Las no soportadas se concentran
en entradas multitype (3), conexiones (14), filas dummy (10), precisión (1),
paleta (7) y espacio inicial (1). Sus generadores obtienen 35 de 44
coincidencias; una plantilla es parcial y ocho propiedades no son declarables.

Pond contiene 813 propiedades aplicables del editor: 681 coinciden, 37 se
clasifican como parciales, 5 como discrepancias directas y 90 como no
soportadas. Las aproximaciones conservan labels de llamada (18), siete sombras
de paleta, seis comportamientos dinámicos de lógica, matemáticas y variables,
cuatro estados iniciales de procedimientos, el mutador inicial de `if` y el
número adaptativo. Las cinco discrepancias son comprobaciones de salida que se
pierden al redefinir nombres Blockly reservados. Las propiedades no declarables
se concentran en conexiones (27), filas dummy (40), variables y categorías
dinámicas (8), paleta (9), y seis límites puntuales de tipos, alineación,
precisión y espacio inicial. Sus generadores obtienen 68 de 96 coincidencias:
siete plantillas son parciales y 21 propiedades no son declarables.

Estos recuentos son propiedades atómicas, no números de funcionalidades. Por
ejemplo, una sola ausencia de `input_dummy` afecta al orden de entrada, presencia
y orden de campos de varios bloques. Por eso la tabla de capacidades debe
acompañar siempre al porcentaje de paridad.

## Regla aplicada al corpus completo

Los diez casos usan los mismos esquemas y no eliminan del denominador una
característica solo porque Model2Blockly no la soporte. `excluded` se usa
únicamente cuando la diferencia pertenece de forma demostrable al runtime del
dominio o a la aplicación contenedora. Las capacidades incorporadas por los
casos complejos se añadieron a esta matriz sin modificar retrospectivamente el
significado de los estados.
