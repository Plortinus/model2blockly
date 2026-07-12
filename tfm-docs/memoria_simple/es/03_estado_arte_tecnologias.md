# Estado del arte y tecnologías

## Programación basada en bloques

La programación basada en bloques sustituye parte de la escritura textual por la manipulación de piezas visuales que representan instrucciones, expresiones o estructuras de control. Estas piezas se conectan siguiendo reglas definidas por el entorno, lo que evita muchas combinaciones sintácticamente inválidas y ayuda al usuario a descubrir qué construcciones están disponibles. Esta forma de interacción se usa de manera habitual para introducir la programación a usuarios noveles, y su ecosistema ha crecido hasta abarcar contextos educativos y dominios de aplicación muy diversos [@lin2021landscape].

Desde el punto de vista de la interacción, los bloques no son solo una forma gráfica de mostrar código. La modalidad de programación, es decir, la forma en que el usuario representa y manipula las construcciones del lenguaje, influye en las prácticas de programación que desarrolla [@weintrop2018modalities]. Por ello, al diseñar un lenguaje basado en bloques no basta con traducir instrucciones textuales a piezas visuales: también hay que decidir qué conceptos aparecen como bloques, qué parámetros se muestran como campos, cómo se organiza la paleta, qué conexiones son válidas y qué restricciones se comunican al usuario.

En este TFM, la programación por bloques se entiende como una sintaxis concreta visual, es decir, como la forma visible con la que el usuario manipula un lenguaje de dominio específico. El usuario final no necesita conocer la implementación JavaScript del editor ni la estructura interna del metamodelo; interactúa con un conjunto de bloques que representan conceptos del dominio. Esta idea conecta directamente con la motivación del trabajo: crear lenguajes de bloques para diferentes dominios sin programar manualmente cada editor desde cero.

## Blockly

Blockly es una biblioteca web de código abierto para construir editores basados en bloques. La documentación oficial describe Blockly como una biblioteca que permite integrar un editor de código visual en una aplicación web; el desarrollador define los bloques y la semántica asociada, mientras que la biblioteca proporciona la infraestructura de interacción visual, arrastre, encaje, toolbox y serialización [@blocklyWhatIs; @blocklyDocs].

La biblioteca permite definir bloques con campos, entradas de valor, entradas de sentencias, conexiones previas y posteriores, colores, tooltips y generadores de código. También permite organizar los bloques en categorías de paleta o *toolbox*, y serializar el estado del área de trabajo o *workspace* [@blocklyGoogleDevelopers]. Estas capacidades hacen que Blockly sea una base adecuada para construir lenguajes visuales específicos, pero no eliminan la necesidad de definir manualmente la configuración de cada dominio.

El problema que aborda este TFM aparece precisamente en ese punto. Si se desea crear un editor Blockly para un dominio de planificación, otro para un dominio de storytelling y otro para un dominio de robótica, gran parte del trabajo se repite: declarar bloques, campos, categorías, conexiones, validaciones y generadores. El enfoque propuesto consiste en generar esos elementos a partir de una descripción abstracta del dominio. Así, Blockly se utiliza como plataforma de ejecución visual, pero la especificación del lenguaje se mantiene en un nivel de metamodelo o DSL.

## Lenguajes de dominio específico e ingeniería dirigida por modelos

Un lenguaje de dominio específico proporciona conceptos y notaciones adaptados a una familia concreta de problemas. Frente a un lenguaje de propósito general, un DSL reduce la distancia entre el problema y su representación porque permite usar términos y estructuras propias del dominio. En aproximaciones basadas en metamodelado, un DSL suele organizarse alrededor de una sintaxis abstracta, definida mediante un metamodelo, y una o varias sintaxis concretas con las que interactúa el usuario [@sal2024dsl; @wasowski2023dsl].

La ingeniería dirigida por modelos proporciona el marco metodológico para esta separación. En MDE, los modelos son artefactos principales del desarrollo y se utilizan para especificar, diseñar, analizar, transformar y generar software [@jimenez2017scalable]. Los estudios sobre prácticas industriales de modelado muestran que entre las motivaciones para usar MDE aparecen la reducción del tiempo de desarrollo, la reutilización, la mejora de calidad y el ahorro de costes [@akdur2018survey]. Estas motivaciones son coherentes con el objetivo de este TFM: reutilizar un generador para producir editores Blockly en lugar de implementar manualmente cada editor.

En el ámbito del modelado, existen también estándares generales como UML y OCL. UML define una notación gráfica estandarizada para visualizar, especificar, construir y documentar sistemas software, mientras que OCL proporciona un lenguaje formal para expresar restricciones sobre modelos [@omgUml; @omgOcl]. Estos estándares son relevantes como contexto, pero el presente TFM no propone un perfil UML ni una integración completa de OCL. La decisión tomada es más concreta: usar Ecore y un DSL textual propio como fuentes de dominio, y derivar desde ellas una sintaxis concreta basada en bloques.

La experiencia acumulada en lenguajes específicos de dominio muestra además que las herramientas de modelado pueden elevar el nivel de abstracción al expresar soluciones directamente con conceptos del dominio y generar artefactos a partir de esos modelos [@luoma2004dsm]. Esta idea coincide con la motivación de BlocklyDSL: que el usuario defina los conceptos del dominio y que la infraestructura genere el editor visual correspondiente.

En este proyecto, el DSL no se limita a una sintaxis textual. La sintaxis basada en bloques se considera otra forma concreta de representar el mismo dominio, pero en formato visual. El metamodelo describe qué conceptos existen y cómo se relacionan; las anotaciones o el DSL textual aportan información adicional de presentación; el generador produce la configuración Blockly que materializa esa sintaxis visual.

## EMF y Ecore

Eclipse Modeling Framework (EMF) es una infraestructura de modelado para definir modelos estructurados y generar código a partir de ellos. Ecore es el metamodelo central de EMF y permite representar paquetes, clases, atributos, referencias, tipos de datos, herencia y cardinalidades [@emfOverview; @steinberg2008emf]. En la práctica, Ecore proporciona una forma compacta y ampliamente utilizada de describir la estructura abstracta de un dominio.

En el contexto de este TFM, Ecore desempeña tres funciones. En primer lugar, actúa como fuente de verdad para la estructura del dominio: las clases representan conceptos, los atributos representan propiedades y las referencias representan relaciones. En segundo lugar, permite derivar información útil para la sintaxis basada en bloques: una clase puede convertirse en un tipo de bloque, un atributo en un campo, una referencia de contención en una entrada donde se insertan bloques hijos y una cardinalidad en una comprobación de mínimos o máximos. En tercer lugar, Ecore permite añadir anotaciones que complementan la información puramente estructural con detalles de presentación, como etiquetas, colores, categorías o tooltips.

Esta capacidad de separar estructura e información de presentación es esencial para el trabajo. No todos los detalles de un editor Blockly existen de forma natural en un metamodelo Ecore: por ejemplo, el color de un bloque o la organización de la toolbox son decisiones de sintaxis concreta. Por ello, el proyecto permite enriquecer el metamodelo con anotaciones específicas, sin perder la posibilidad de generar un editor básico a partir de la estructura Ecore.

## Xtext

Xtext es un framework de Eclipse para el desarrollo de lenguajes textuales específicos de dominio. A partir de una gramática, Xtext genera infraestructura de análisis sintáctico, un modelo abstracto basado en EMF y soporte de edición como resaltado de sintaxis, navegación, autocompletado y marcadores de error [@xtextProject; @bettini2013xtext; @bettini2015xsemantics]. Estas características lo hacen adecuado para construir una entrada textual alternativa a la edición directa de metamodelos Ecore.

En este proyecto, Xtext se usa para definir el lenguaje textual de entrada. Este lenguaje permite declarar dominios, categorías, clases, atributos, contenciones, referencias, validaciones, opciones de interfaz y plantillas de generación de código con una sintaxis más compacta que un fichero Ecore. El modelo resultante se procesa después mediante un adaptador que lo transforma en la misma representación intermedia usada por la ruta Ecore.

La ruta Xtext no sustituye a la ruta Ecore; la complementa. Ecore es la vía más cercana al enfoque metamodelo-céntrico, mientras que Xtext ofrece una sintaxis cómoda para describir dominios de prueba, documentar casos de uso y facilitar la creación de nuevos lenguajes sin editar directamente metamodelos Ecore. Ambas rutas confluyen en el mismo modelo intermedio, lo que refuerza el carácter genérico de la arquitectura.

## Relación entre las tecnologías

La relación entre las tecnologías utilizadas puede resumirse como una cadena de transformación. EMF/Ecore y Xtext permiten definir la estructura abstracta del dominio. Blockly proporciona la infraestructura web para ejecutar la sintaxis visual basada en bloques. El generador desarrollado en este TFM conecta ambos extremos mediante un modelo intermedio independiente de la fuente de entrada.

La Tabla [1.1](#tab:tecnologias-papel) resume el papel de cada tecnología dentro del proyecto.

:::
  Tecnología        Papel en el TFM
  ----------------- -----------------------------------------------------------------------------------------------------------
  Blockly           Plataforma web para representar bloques, toolbox, workspace, conexiones y generación asociada.
  EMF/Ecore         Definición estructural del dominio mediante metamodelos, clases, atributos, referencias y cardinalidades.
  Xtext             Sintaxis textual para definir dominios y generar modelos EMF a partir de descripciones textuales.
  Java/Xtend        Implementación de adaptadores, modelo intermedio y generadores de código.
  HTML/JavaScript   Tecnología de salida principal para ejecutar el editor Blockly generado en el navegador.

  : Papel de las tecnologías principales en el proyecto
:::

Esta combinación da soporte técnico al enfoque del proyecto: definir lenguajes de dominio específico a un nivel abstracto y generar automáticamente una sintaxis concreta basada en bloques. El valor de la solución no reside en usar Blockly de forma aislada, sino en integrarlo con técnicas de modelado y generación para crear editores reutilizables en distintos dominios.
