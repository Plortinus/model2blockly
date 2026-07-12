# Diseño y arquitectura de la solución

La arquitectura de BlocklyDSL trata un editor Blockly como una sintaxis concreta visual de un lenguaje de dominio específico. Desde esa perspectiva, el sistema no debe depender de un único ejemplo ni de una codificación manual de bloques, sino de una cadena de transformación capaz de partir de una descripción abstracta del dominio y producir una implementación web ejecutable.

## Enfoque arquitectónico

La arquitectura de BlocklyDSL sigue un enfoque de ingeniería dirigida por modelos. En lugar de definir directamente los bloques en JavaScript, el dominio se expresa mediante un artefacto de mayor nivel: un metamodelo Ecore o una descripción textual del dominio. Esta decisión es coherente con la idea de MDE de usar modelos como artefactos principales para especificar y generar software [@jimenez2017scalable]. También se ajusta a la separación habitual en DSLs entre los conceptos internos del lenguaje y la forma visible con la que el usuario los manipula: el metamodelo define los conceptos del dominio, mientras que Blockly materializa una notación visual para manipularlos [@sal2024dsl].

El diseño se apoya en tres principios. El primero es separar la lectura del dominio de la generación del editor. La lectura de un metamodelo Ecore no debe mezclarse con la generación de JavaScript, porque eso haría difícil añadir nuevas entradas o nuevas salidas. El segundo es normalizar todas las entradas en una representación común. Si la ruta Ecore y la ruta Xtext producen el mismo modelo intermedio, el generador puede ser independiente de la fuente original. El tercer principio es generar no solo la apariencia de los bloques, sino también parte de su comportamiento: conexiones, comprobaciones, referencias, exportación y metadatos de interfaz siempre que esa información esté disponible en la entrada.

Estos principios responden al problema identificado en la introducción. Blockly proporciona la infraestructura de interacción visual, pero exige que el desarrollador describa cada bloque, campo, conexión y generador [@blocklyDocs]. BlocklyDSL desplaza esa descripción a un nivel de dominio: el desarrollador define clases, atributos, contenciones, referencias y anotaciones; la herramienta se encarga de transformar esa información en los artefactos Blockly correspondientes.

## Flujo general de generación

El flujo completo de la solución se organiza en cuatro etapas:

1.  definición del dominio mediante Ecore o mediante el DSL textual;

2.  adaptación de la entrada a un modelo intermedio común;

3.  generación de artefactos Blockly a partir del modelo intermedio;

4.  ejecución del editor generado en el navegador.

La memoria PDF incluye una figura con la cadena de generación propuesta. En
forma textual, el flujo puede resumirse así:

```text
Metamodelo Ecore ─┐
                  ├─> Adaptadores de entrada ─> Modelo intermedio común
DSL textual Xtext ┘                                  │
                                                     v
                                      Generadores web ─> Editor Blockly
                                                            │
                                                            v
                                      Modelo exportado, código e informe
```

La Tabla [1.1](#tab:flujo-arquitectura) resume la responsabilidad de cada etapa.

:::
  Etapa                    Artefacto principal                     Responsabilidad
  ------------------------ --------------------------------------- ------------------------------------------------------------------------------------------------------------------------------------------
  Definición del dominio   Ecore o DSL textual                     Describir clases, atributos, relaciones, categorías y anotaciones de presentación.
  Adaptación               Adaptadores de entrada                  Extraer la información relevante y convertirla en una especificación común de editor.
  Modelo intermedio        Especificación común del editor          Representar bloques, campos, entradas, referencias, categorías, validaciones y opciones de workspace sin depender de Blockly JavaScript.
  Generación               Generadores web                         Producir bloques, paleta, validaciones, exportadores, páginas HTML y, opcionalmente, un proyecto HTML.
  Ejecución                Editor Blockly generado                 Permitir al usuario construir modelos o programas visuales en el navegador.

  : Flujo arquitectónico de BlocklyDSL
:::

La etapa de adaptación es el punto de separación más importante de la arquitectura. A partir de ella, el generador no necesita saber si un bloque procede de una clase Ecore o de una declaración del DSL textual. Esta decisión reduce el acoplamiento y permite que la evolución de una ruta de entrada no obligue a reescribir el generador.

## Modelo intermedio

El modelo intermedio común es el núcleo de la solución. Su función es capturar todo lo necesario para generar un editor Blockly, pero sin estar ligado todavía a la forma concreta en que Blockly se codifica en JavaScript. Contiene el nombre del dominio, información de espacio de nombres, categorías, tipos de bloque, reglas de validación, opciones de workspace y metadatos para generación de código.

Cada tipo de bloque se describe con su nombre, etiqueta visible, color, categoría de la paleta, información de herencia, forma de conexión y colecciones de campos, entradas estructurales, entradas de valor y referencias. Esta separación permite expresar distintas formas de interacción visual: un atributo simple puede convertirse en un campo de texto o numérico; una referencia de contención puede convertirse en una zona donde se insertan bloques hijos; una referencia no contenida puede convertirse en un desplegable dinámico; y una expresión puede representarse mediante una entrada de valor.

La Tabla [1.2](#tab:modelo-intermedio) muestra algunos de los elementos principales del modelo intermedio y la información que aportan al editor generado.

:::
  Elemento conceptual    Papel en la generación
  ---------------------- ----------------------------------------------------------------------------------------------------------
  Especificación del editor    Contenedor principal del dominio, categorías, bloques, validaciones y opciones globales.
  Tipo de bloque               Especificación de un concepto visual, incluyendo etiqueta, color, conexión, campos y entradas.
  Campo                        Representación de atributos simples como campos de texto, número, booleano, color, ángulo o desplegable.
  Entrada estructural          Representación de contenciones como entradas verticales para bloques hijos.
  Entrada de valor             Representación de conexiones horizontales para expresiones o valores.
  Referencia dinámica          Representación de referencias no contenidas mediante desplegables dinámicos.
  Regla de validación          Comprobaciones derivadas de mínimos y máximos, campos obligatorios o restricciones de orden.

  : Elementos principales del modelo intermedio
:::

El modelo intermedio evita que la arquitectura sea una traducción directa y rígida entre Ecore y Blockly. En su lugar, funciona como una descripción interna del editor visual: recoge qué bloques existirán, qué datos mostrarán y cómo se podrán conectar. Esta decisión es especialmente relevante porque algunas propiedades del editor no pertenecen estrictamente al dominio, sino a su presentación: colores, tooltips, categorías, disposición en línea o widgets de edición. Al almacenar esa información de manera uniforme, el mismo generador puede usarla para la salida HTML/JavaScript.

## Ruta basada en Ecore

La ruta Ecore representa el enfoque metamodelo-céntrico de BlocklyDSL: generar un editor Blockly a partir de un metamodelo, posiblemente enriquecido con detalles de sintaxis basada en bloques. EMF/Ecore proporciona los conceptos necesarios para describir clases, atributos, referencias, herencia y cardinalidades [@emfOverview]. El adaptador de esta ruta transforma esa información en el modelo intermedio.

El mapeo básico es sistemático. Cada clase Ecore se convierte en un tipo de bloque; cada atributo, en un campo visual; cada referencia de contención, en una entrada estructural para bloques hijos; y cada referencia no contenida, en un campo de referencia dinámica. La herencia de Ecore se usa para inferir conexiones tipadas, de manera que un bloque hijo solo pueda insertarse en entradas compatibles. Las cardinalidades de referencias y atributos se usan para derivar comprobaciones, por ejemplo cuando una contención exige al menos un elemento, cuando hay un máximo permitido o cuando un atributo obligatorio no debe estar vacío.

Además del mapeo estructural, la ruta Ecore admite anotaciones. Las anotaciones con fuente `blockly` permiten indicar etiquetas, colores, categorías, tooltips, bloques de salida, entradas en línea, rangos numéricos o restricciones de orden. En este contexto, un bloque de salida es un bloque que devuelve un valor y puede conectarse dentro de una entrada de valor. Las anotaciones con fuente `ui` añaden metadatos de presentación usados sobre todo en la salida HTML, como widget, grupo, orden, texto de ayuda o campo utilizado para mostrar referencias. Las anotaciones con fuente `code` permiten definir plantillas de generación de código. De esta forma, el metamodelo conserva la estructura del dominio y solo incorpora información adicional cuando la generación por defecto no es suficiente.

## Ruta basada en Xtext

La segunda ruta de entrada utiliza un DSL textual definido con Xtext. Xtext es adecuado para este propósito porque genera infraestructura de análisis, modelos EMF y soporte de edición a partir de una gramática [@xtextProject; @bettini2015xsemantics]. En BlocklyDSL, la gramática del DSL textual define una sintaxis compacta para declarar dominios, categorías, clases, atributos, contenciones, referencias, entradas de valor, restricciones y opciones de workspace.

Esta ruta no reemplaza a Ecore; la complementa. En muchos casos resulta más rápido escribir un ejemplo de dominio con el DSL textual que crear o editar manualmente un metamodelo Ecore. Además, el DSL textual permite expresar en un único artefacto información estructural y de presentación, como el color del bloque, la etiqueta visible, el tooltip, los widgets de interfaz o las plantillas de código. El adaptador textual transforma el modelo EMF producido por Xtext en la misma especificación intermedia usada por la ruta Ecore.

La existencia de dos rutas de entrada tiene una función metodológica. Si los mismos generadores funcionan tanto con Ecore como con el DSL textual, la solución aporta evidencia de que no está ligada a un formato concreto. El elemento común es el dominio expresado como modelo; la sintaxis de entrada puede variar sin afectar a la salida visual.

## Generación de editores Blockly

Una vez construido el modelo intermedio, la generación produce los artefactos necesarios para ejecutar el editor en un navegador. La salida HTML/JavaScript genera las definiciones de bloques, la paleta, las reglas de validación, los mecanismos de exportación y las páginas HTML necesarias para ejecutar el editor. También se genera una versión autocontenida, útil para revisar el resultado sin preparar una infraestructura web compleja.

El generador traduce cada elemento del modelo intermedio a construcciones de Blockly. Los campos de texto, número, booleano, color, ángulo o enumeración se convierten en campos Blockly apropiados. Las entradas de sentencias se traducen en cavidades verticales para bloques hijos. Las entradas de valor se traducen en conectores horizontales. Las categorías del modelo intermedio se convierten en secciones de la toolbox. Las reglas de validación se transforman en código JavaScript que inspecciona el workspace y muestra advertencias al usuario.

La implementación se centra en una salida HTML/JavaScript autocontenida. Esta decisión reduce dependencias externas y facilita que el plugin de Eclipse genere un artefacto que puede abrirse directamente en el navegador. El modelo intermedio mantiene, no obstante, suficiente información de presentación para que futuros objetivos de generación puedan reutilizar la misma especificación.

## Validaciones, referencias y personalización

Un editor de bloques útil no puede limitarse a mostrar piezas visuales. También debe guiar al usuario para construir modelos coherentes. Por ello, BlocklyDSL incluye comprobaciones derivadas del dominio. Por ejemplo, las cardinalidades se traducen en comprobaciones sobre cuántos bloques hay conectados a una entrada: si falta un bloque obligatorio o se supera un máximo, el editor puede avisar al usuario. Los atributos o referencias obligatorios se traducen en advertencias cuando el campo correspondiente está vacío. Las restricciones de tipo `must follow` permiten expresar reglas de orden local entre bloques, como exigir que un bloque aparezca inmediatamente después de otro.

El alcance de estas comprobaciones debe interpretarse de forma precisa. OCL proporciona un lenguaje formal para expresar restricciones generales sobre modelos, incluyendo invariantes y expresiones sobre objetos, propiedades y colecciones [@omgOcl]. BlocklyDSL no incorpora un evaluador OCL completo ni traduce expresiones OCL arbitrarias. En su lugar, genera comprobaciones para un conjunto limitado de restricciones estructurales y semánticas simples. Estas restricciones pueden inferirse del metamodelo Ecore, de anotaciones o del DSL textual. La Tabla [\[tab:soporte-validaciones\]](#tab:soporte-validaciones) resume este alcance.

::: tabular
L0.32 L0.56 Regla o familia de reglas & Soporte en BlocklyDSL\
Cardinalidad de contenciones & Soportada. Los límites de una contención Ecore o de una regla `contains [m..n]` se convierten en una comprobación sobre el número de bloques conectados a la entrada correspondiente.\
Campos y referencias obligatorios & Soportados. En Ecore se infieren de `lowerBound >= 1`; en el DSL textual se expresan con `required`. El editor avisa cuando el campo o la referencia está vacío.\
Orden local entre bloques & Soportado de forma acotada mediante `must follow`. La comprobación solo exige que un bloque esté precedido inmediatamente por otro tipo de bloque.\
Rangos numéricos & Soporte parcial. Los valores `min` y `max` se trasladan a campos numéricos de Blockly, pero no forman un lenguaje general de restricciones.\
Unicidad e identificadores & Soporte parcial. Los atributos identificadores de Ecore y algunos campos multivaluados con `unique=true` se traducen en advertencias de duplicado.\
Expresiones de validación & Soporte parcial. Algunas anotaciones de validación se transforman en condiciones JavaScript ejecutadas sobre el workspace.\
Comprobaciones estáticas del DSL & Soporte parcial. Xtext permite advertir sobre nombres duplicados, cardinalidades inválidas, usos problemáticos de `required`, `must follow` sobre bloques de salida o anotaciones de interfaz incoherentes antes de generar el editor.\
Invariantes OCL & Soporte parcial. Se traducen invariantes sencillos, por ejemplo `notEmpty`, `size`, comparaciones básicas y combinaciones `and/or/not`; no se soportan cuantificadores, navegación arbitraria ni operaciones complejas sobre colecciones.\
Restricciones globales complejas & No soportadas. No se implementan reglas que dependan de propiedades globales del workspace más allá de los patrones anteriores.\
Sincronización de referencias opuestas & Soporte parcial. Las referencias no contenidas con `eOpposite` se sincronizan en el runtime HTML cuando ambos extremos son editables; los casos de contenedor y la sincronización completa en HTML quedan como extensión.\
Validación bloqueante & No soportada. Las reglas generan advertencias en los bloques y una confirmación antes de exportar, pero no impiden editar el workspace.\
:::

Las referencias no contenidas se tratan de forma distinta a las contenciones. Una contención define la estructura jerárquica del modelo y por lo tanto se representa como una entrada de sentencias. Una referencia no contenida apunta a otro elemento existente en el workspace, por lo que se representa mediante un desplegable dinámico. El generador calcula los candidatos compatibles teniendo en cuenta el tipo objetivo y sus subtipos. Esta decisión mantiene una diferencia semántica importante del metamodelo: no es lo mismo crear un hijo estructural que seleccionar otro elemento ya presente en el modelo.

La personalización se incorpora como una capa controlada de metadatos. Colores, etiquetas, categorías, tooltips, disposición en línea, widgets de interfaz y plantillas de código no cambian la estructura esencial del dominio, pero sí afectan a la experiencia del usuario final. BlocklyDSL permite definir esta información mediante anotaciones Ecore o mediante opciones del DSL textual. De este modo, se evita que el usuario tenga que editar directamente JavaScript para realizar cambios habituales de presentación.

## Trazabilidad con los objetivos

La arquitectura propuesta mantiene una relación directa con los objetivos específicos definidos para el TFM. La Tabla [1.3](#tab:trazabilidad-arquitectura) resume esa relación.

:::
  Objetivo   Respuesta arquitectónica
  ---------- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  OE1        El modelo intermedio identifica explícitamente los elementos que necesita un editor de bloques: tipos, campos, conexiones, categorías, referencias y validaciones.
  OE2        La ruta Ecore permite partir de metamodelos y derivar bloques, campos, entradas y reglas.
  OE3        La gramática Xtext proporciona una sintaxis textual para definir dominios de forma compacta.
  OE4        El modelo intermedio común desacopla las entradas de los generadores y permite compartir la misma lógica de salida.
  OE5        Los generadores producen editores HTML/JavaScript ejecutables en el navegador.
  OE6        Las validaciones, referencias dinámicas, anotaciones de interfaz y plantillas de código se incorporan como capacidades genéricas.
  OE7        La separación por adaptadores, modelo intermedio y generadores facilita probar cada parte y comparar las dos rutas de entrada sobre AppMaker.

  : Trazabilidad entre objetivos y arquitectura
:::

En conjunto, la arquitectura define una cadena generativa implementable: el usuario describe un dominio a nivel de metamodelo o DSL, el sistema normaliza esa descripción y el generador produce un editor Blockly operativo. La aportación principal no es cada bloque generado de manera individual, sino el mecanismo reutilizable que permite obtener esos bloques de forma sistemática para dominios distintos.
