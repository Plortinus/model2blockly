# Objetivos y alcance

## Objetivo general

El objetivo general de este Trabajo Fin de Máster es diseñar e implementar un entorno para la creación de lenguajes de dominio específico con sintaxis basada en bloques. La idea central es que los conceptos de un dominio puedan definirse a un nivel abstracto, mediante un metamodelo Ecore o mediante una sintaxis textual específica, y que a partir de esa definición se genere automáticamente un editor Blockly ejecutable.

Este objetivo se apoya en dos ideas complementarias. Por un lado, Blockly proporciona la infraestructura para construir editores visuales basados en bloques, pero delega en el desarrollador la definición de bloques, campos, conexiones y generación asociada [@blocklyWhatIs]. Por otro lado, la ingeniería dirigida por modelos promueve el uso de modelos como artefactos centrales para especificar, diseñar y generar software [@jimenez2017scalable]. El objetivo del TFM consiste en conectar ambas ideas: usar técnicas de modelado y generación de lenguajes para derivar editores Blockly a partir de descripciones de dominio, reduciendo el trabajo manual necesario para cada nuevo lenguaje.

## Objetivos específicos

Para alcanzar el objetivo general se han definido los siguientes objetivos específicos:

**OE1. Analizar la estructura de los lenguajes basados en bloques.** Identificar qué información necesita un editor de bloques para representar un dominio: tipos de bloques, campos, categorías, conexiones, restricciones, serialización y, cuando proceda, generación de código. Este objetivo se justifica por la diversidad de diseños existentes en el ecosistema de programación por bloques [@lin2021landscape].

**OE2. Definir una ruta de generación basada en metamodelos Ecore.** Permitir que un metamodelo de dominio actúe como fuente principal de generación. Las clases se mapean a tipos de bloque, los atributos a campos, las referencias de contención a entradas estructurales y las cardinalidades a comprobaciones sobre cuántos elementos son obligatorios o permitidos. Esta ruta representa el enfoque metamodelo-céntrico del trabajo: generar código Blockly a partir de un metamodelo, posiblemente anotado con información adicional sobre su sintaxis visual basada en bloques.

**OE3. Proporcionar una sintaxis textual específica para definir dominios.** Además de la ruta Ecore, se propone un DSL textual implementado con Xtext para describir lenguajes de dominio de forma compacta. Xtext es adecuado para esta tarea porque genera infraestructura de parsing, modelo abstracto basado en EMF y soporte de edición a partir de una gramática [@xtextProject; @bettini2015xsemantics].

**OE4. Diseñar un modelo intermedio independiente de la entrada.** Separar la adaptación del dominio de la generación concreta de Blockly. Para ello, tanto la ruta Ecore como la ruta DSL se transforman en una representación común que contenga la especificación del editor. Esta decisión evita acoplar el generador a una única fuente de entrada y facilita añadir nuevas rutas o nuevos objetivos de generación.

**OE5. Generar editores Blockly ejecutables.** Implementar un generador que produzca los ficheros necesarios para abrir un editor en el navegador: definición de bloques, paleta, generadores, validaciones y página HTML. El editor generado permite crear programas o modelos usando la sintaxis visual de bloques, así como guardar, cargar y exportar el contenido del espacio de trabajo.

**OE6. Incorporar mecanismos genéricos de validación, referencias y personalización.** Un editor generado resulta más útil si no se limita a mostrar bloques aislados. Por ello, el entorno incorpora comprobaciones para detectar modelos incompletos, referencias entre elementos del modelo, opciones de presentación y anotaciones que permiten adaptar la apariencia del editor al dominio.

**OE7. Evaluar la generalidad mediante casos de uso y pruebas.** La solución se evalúa con más de un dominio para analizar si la arquitectura es reutilizable y no una implementación específica de un único ejemplo. Además, las pruebas automáticas y las verificaciones manuales cubren el modelo intermedio, los adaptadores y los generadores.

## Alcance de la solución

El alcance de la solución se define alrededor de una cadena generativa completa: descripción del dominio, normalización en una representación intermedia y generación de un editor Blockly ejecutable. La entrada puede ser un metamodelo Ecore o una descripción textual del dominio. En ambos casos, el sistema construye una especificación intermedia del editor y deriva una implementación HTML/JavaScript que puede abrirse en un navegador para crear programas visuales dentro del dominio definido.

La Tabla [1.1](#tab:alcance-funcional) resume las dimensiones consideradas en la solución. Estas dimensiones no se plantean como una lista aislada de funciones, sino como los elementos necesarios para sostener el argumento central del trabajo: que un dominio descrito a alto nivel puede transformarse de forma sistemática en una sintaxis visual basada en bloques.

:::
  Dimensión             Papel en la solución
  --------------------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  Entrada Ecore         Uso de clases, atributos, referencias, herencia, cardinalidades y anotaciones como información de partida para construir el editor visual.
  Entrada DSL textual   Sintaxis Xtext para describir dominios, categorías, clases, atributos, referencias, contenciones, validaciones y plantillas de código sin editar directamente un metamodelo.
  Modelo intermedio     Representación común de bloques, campos, categorías, referencias, entradas de valor, validaciones y metadatos de interfaz, independiente de la ruta de entrada.
  Generación Blockly    Producción de bloques, toolbox, generadores, validaciones y páginas HTML ejecutables a partir de la representación intermedia.
  Validaciones          Derivación de comprobaciones sobre campos obligatorios, mínimos y máximos de elementos, y restricciones simples de orden.
  Referencias           Representación de relaciones de contención y no contención, incluyendo listas desplegables dinámicas para seleccionar elementos del modelo.
  Personalización       Uso de colores, etiquetas, categorías, tooltips, widgets y metadatos de interfaz para adaptar la sintaxis visual al dominio.
  Evaluación            Uso de casos de dominio y pruebas automáticas para aportar evidencia sobre adaptadores, generadores y modelo intermedio.

  : Dimensiones incluidas en el alcance de la solución
:::

## Límites del trabajo

Los límites del trabajo se derivan de la orientación generativa de la solución. El objetivo central no es construir una plataforma comercial completa ni un entorno gráfico final para un único dominio, sino demostrar una cadena reutilizable para crear lenguajes de dominio específico con sintaxis basada en bloques.

Por lo tanto, quedan fuera del alcance principal:

- la importación genérica de modelos XMI existentes a un workspace Blockly;

- la integración de lenguajes de restricciones complejos como OCL;

- la sincronización automática de referencias bidireccionales complejas;

- la creación de interfaces de usuario finales altamente especializadas para cada dominio;

- la generación de código con análisis semántico avanzado o formateadores específicos de lenguajes de propósito general.

Algunos de estos elementos se exploran parcialmente, como la exportación a JSON o XMI y la generación de código mediante plantillas, pero se consideran extensiones del objetivo principal y no forman parte de la evidencia central utilizada para valorar el enfoque.

## Criterios de evaluación

La evaluación del trabajo se apoya en los siguientes criterios, orientados a valorar si el enfoque propuesto es técnicamente viable y reutilizable:

1.  permitir definir al menos un dominio mediante un metamodelo Ecore;

2.  generar automáticamente un editor Blockly funcional a partir de ese metamodelo;

3.  permitir complementar la generación con información de presentación, como etiquetas, colores o categorías;

4.  ofrecer una ruta textual con Xtext para definir dominios sin editar directamente Ecore;

5.  generar código HTML/JavaScript que pueda ejecutarse en un navegador;

6.  demostrar la reutilización de la arquitectura comparando dos rutas de entrada sobre el mismo ejemplo conductor;

7.  disponer de pruebas o verificaciones que reduzcan el riesgo de que la solución funcione únicamente para un caso preparado manualmente.

Estos criterios se corresponden con la motivación inicial del trabajo: evitar que la creación de cada lenguaje basado en bloques requiera escribir manualmente la infraestructura Blockly de bajo nivel. Si el usuario puede describir el dominio a nivel de metamodelo o DSL y obtener un editor Blockly operativo, el trabajo aporta evidencia de que la generación de editores Blockly desde descripciones de alto nivel es una alternativa razonable a la programación manual de cada editor.
