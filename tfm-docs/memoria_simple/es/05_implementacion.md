# Implementación de BlocklyDSL

Este capítulo describe cómo se ha materializado la arquitectura propuesta en una implementación ejecutable. La exposición se centra en las decisiones que permiten mantener separadas las rutas de entrada, la representación intermedia y los objetivos de generación. De este modo, la implementación no se presenta como una lista de ficheros, sino como la concreción de una cadena generativa para convertir descripciones de dominio en editores Blockly operativos.

## Organización del proyecto

La implementación se organiza como un proyecto Eclipse/Xtext compuesto por tres módulos principales: el lenguaje y su lógica de generación, el soporte de IDE y la integración con la interfaz de Eclipse. Esta separación es habitual en proyectos Xtext, donde la definición del lenguaje, la capa IDE y la integración UI se mantienen como proyectos relacionados [@xtextProject].

Dentro del módulo principal, la implementación se divide en componentes con responsabilidades explícitas:

| Componente | Responsabilidad |
|---|---|
| Gramática textual | Definición de la sintaxis del DSL. |
| Modelo intermedio | Representación Java independiente de la entrada. |
| Adaptadores | Conversión desde Ecore y desde el modelo producido por Xtext hacia la especificación común del editor. |
| Generadores | Generación de editores HTML/JavaScript, salida HTML y scripts asociados. |
| Validación estática | Reglas estáticas del DSL textual. |
| Ejecución independiente | Entrada de línea de comandos para generar desde metamodelos Ecore. |
| Pruebas | Pruebas JUnit del modelo intermedio, adaptadores y generadores. |

Esta estructura sigue la arquitectura descrita en el capítulo anterior. La gramática y los adaptadores forman la entrada, el modelo intermedio actúa como punto común y los generadores producen los editores finales. Esta separación evita que la lógica de un formato concreto se extienda al resto del sistema.

## DSL textual con Xtext

La gramática Xtext define el lenguaje textual de entrada. A partir de esta gramática, Xtext genera un metamodelo EMF, un parser y soporte de edición [@xtextProject; @bettini2013xtext; @bettini2015xsemantics]. Así, cada fichero DSL puede tratarse como un modelo.

La raíz del modelo agrupa el nombre del dominio, las opciones de generación, la configuración del workspace, las categorías, las clases y las restricciones. Las clases del dominio se declaran con una construcción específica del DSL. Cada clase puede ser abstracta, generar un bloque de salida, heredar de otra clase, pertenecer a una categoría, definir color, etiqueta, tooltip, disposición en línea y plantilla de código.

Dentro de una clase se declaran cuatro tipos de elementos: atributos, contenciones, referencias y entradas de valor. Esta distinción se corresponde con las formas principales en las que Blockly representa información: campos, entradas de sentencias, desplegables de referencia y conectores de valor.

El DSL también incluye opciones de interfaz. Con estas opciones se pueden indicar widgets, etiquetas, textos de ayuda, grupos, orden de presentación, campos de solo lectura, campos ocultos y campos usados para mostrar referencias. Esta información no es necesaria para un editor Blockly básico, pero permite enriquecer la salida HTML sin escribir JavaScript a mano.

La implementación incluye además un validador Xtext. Este validador realiza comprobaciones estáticas antes de la generación: nombres de clase duplicados, cardinalidades inválidas, dominios compuestos únicamente por clases abstractas, usos problemáticos de contenciones o entradas de valor, restricciones de orden incoherentes y errores comunes en las anotaciones de interfaz. Esta capa desplaza parte de la detección de errores al momento de definición del dominio.

## Modelo intermedio en Java

El modelo intermedio se implementa como un conjunto de objetos Java sencillos, sin dependencia directa de Xtext ni de la API de Blockly. Su elemento principal contiene el nombre del dominio, espacio de nombres, categorías, tipos de bloque, validaciones, opciones de workspace y metadatos de generación de código.

Cada tipo de bloque guarda su forma de conexión: bloque raíz, bloque apilable, bloque apilable tipado, bloque de valor o bloque de valor tipado. Esta clasificación permite generar las conexiones visuales adecuadas sin mezclar reglas específicas de Blockly con la lectura del dominio. La implementación mantiene además listas separadas para campos, entradas de sentencias, referencias y entradas de valor, lo que facilita generar cada tipo de elemento con la construcción Blockly adecuada.

El modelo intermedio también conserva información de orden para las entradas de un bloque. Esta decisión es relevante para la ruta Ecore: cuando las características de una clase se leen desde el metamodelo, el orden original de atributos y referencias puede influir en la legibilidad del bloque generado.

La ventaja de esta implementación es el desacoplamiento. Los adaptadores de entrada producen el mismo tipo de objeto intermedio, y los generadores lo consumen sin depender de cómo fue creado. Así, las entradas y las salidas quedan separadas en el código.

## Adaptador desde Ecore

El adaptador Ecore implementa la ruta basada en metamodelos. Recibe un paquete Ecore y produce la especificación intermedia del editor. La implementación recorre los clasificadores del paquete, identifica las clases, las transforma en tipos de bloque y analiza sus características estructurales. Este proceso usa los conceptos propios de Ecore, como clases, atributos, referencias, herencia y cardinalidades [@emfOverview].

El mapeo estructural se implementa de forma automática. Un atributo Ecore se transforma en un campo visual; su tipo determina si el campo Blockly será textual, numérico, booleano, desplegable, color, ángulo o imagen. Una referencia de contención se transforma en una entrada estructural, porque representa una relación jerárquica entre elementos del modelo. Una referencia no contenida se transforma en una referencia dinámica, porque apunta a otro elemento ya existente. Las cardinalidades se convierten en reglas de validación para que el editor generado pueda avisar al usuario si faltan elementos obligatorios, si se supera un límite superior o si un campo requerido queda sin completar.

La inferencia de conexiones también se realiza en el adaptador. Las clases contenedoras que no están contenidas por otras pueden convertirse en bloques raíz; las clases que heredan de una superclase pueden generar conexiones tipadas; y las clases marcadas como bloques de salida generan conectores de valor. Esta inferencia evita que el usuario tenga que escribir a mano reglas de conexión Blockly para cada clase.

El adaptador permite enriquecer la generación mediante anotaciones Ecore. Las anotaciones con fuente `blockly` afectan a la sintaxis visual; las anotaciones con fuente `ui` se incorporan al modelo intermedio para la salida HTML; y las anotaciones con fuente `code` permiten definir información de generación textual. Así, un metamodelo sin anotaciones puede generar un editor básico, mientras que un metamodelo anotado puede producir una experiencia más adaptada al dominio.

## Adaptador desde el DSL textual

El adaptador del DSL textual recibe el modelo EMF producido por Xtext al analizar el fichero de entrada. A partir de ese modelo convierte categorías, clases, restricciones, opciones de workspace y metadatos de código a la misma especificación intermedia usada por la ruta Ecore.

El mapeo es más directo que en Ecore porque la gramática ya distingue los conceptos específicos que necesita BlocklyDSL. Los atributos se convierten en campos, las contenciones en entradas estructurales, las referencias en desplegables dinámicos y las entradas de valor en conexiones horizontales. Las restricciones de orden se convierten en validaciones, y los campos marcados como obligatorios generan comprobaciones de obligatoriedad.

La implementación usa también información de herencia para determinar la forma de conexión. Una clase marcada como bloque de salida se convierte en bloque de valor; una clase con superclase genera conexiones tipadas; una clase con contenciones y sin superclase puede funcionar como bloque raíz; y una clase ordinaria se genera como bloque apilable.

## Generador HTML/JavaScript

El generador de la salida HTML/JavaScript transforma el modelo intermedio en los artefactos necesarios para abrir el editor en un navegador: bloques, paleta, exportación, validaciones y páginas HTML. Esta salida usa Blockly para definir bloques, inyectar el workspace, gestionar la paleta, serializar y generar código [@blocklyDocs].

La generación de bloques transforma cada tipo concreto del modelo intermedio en una definición visual de Blockly. El generador omite las clases abstractas, porque no deben aparecer como bloques instanciables. Para cada bloque, construye la etiqueta visible, los argumentos, el color, el tooltip, las conexiones y la disposición en línea. Los atributos se convierten en campos Blockly adecuados según su tipo: texto, número, booleano, desplegable, color, ángulo o imagen.

La generación de la paleta produce el conjunto de bloques disponible para el usuario. Si no hay categorías, el generador puede emitir una paleta simple; si existen categorías, genera una paleta categorizada. También se pueden incluir categorías integradas de Blockly, como lógica, bucles, matemáticas, variables y funciones.

La exportación tiene dos objetivos. Por un lado, serializa el workspace como una estructura JSON con el tipo de bloque, identificador, campos, referencias, valores y sentencias. Por otro lado, incorpora una ruta de generación de código basada en plantillas. Si un bloque define una plantilla, el generador sustituye marcadores por valores de campos, entradas de valor o secuencias de sentencias. Si no existe plantilla, se usa una representación textual genérica.

## Validaciones y referencias en tiempo de ejecución

La implementación distingue entre validación estática del DSL y validación en tiempo de ejecución del editor generado. La primera se realiza en Eclipse/Xtext antes de generar. La segunda se ejecuta en el navegador sobre el workspace Blockly.

El código de validación se genera a partir de las reglas del modelo intermedio. Durante la ejecución, recorre los bloques del workspace y produce advertencias. Las reglas de orden comprueban que un bloque esté precedido inmediatamente por el tipo esperado. Las reglas de cardinalidad cuentan los hijos conectados a una entrada de sentencias. Las reglas de obligatoriedad verifican que un campo o una referencia no estén vacíos. Después, los mensajes se muestran como advertencias Blockly y, si todavía existen problemas, se pide confirmación antes de exportar.

El comportamiento generado es ligero. Las validaciones no son un motor OCL completo ni un verificador semántico general. Son comprobaciones JavaScript sobre el workspace que muestran advertencias en los bloques. Aun así, la implementación cubre más que cardinalidades: también incluye obligatoriedad, unicidad, expresiones de validación declaradas en anotaciones y un subconjunto simple de invariantes OCL traducibles.

Las referencias dinámicas se implementan mediante un script generado que actualiza los desplegables de referencia. El generador agrupa las instancias del workspace por tipo de bloque y, para cada campo de referencia, calcula los candidatos compatibles. Esta implementación mantiene la diferencia entre contención y referencia: una contención crea estructura jerárquica, mientras que una referencia selecciona un elemento que ya existe.

## Entrada standalone para Ecore

La implementación proporciona una entrada independiente para la ruta Ecore. Su función es cargar un metamodelo, convertirlo mediante el adaptador Ecore y escribir los artefactos HTML generados en un directorio de salida. De este modo, la ruta Ecore no depende de escribir un fichero del DSL textual ni de ejecutar el generador desde el editor textual.

Esta entrada refuerza la ruta metamodelo-céntrica, porque permite partir directamente de un metamodelo. En el ejemplo AppMaker, esta ruta genera un editor Blockly desde `model/app_maker.ecore` sin pasar por la sintaxis textual del DSL.

## Pruebas de implementación

La implementación incluye una batería de pruebas JUnit organizada por componente. El conjunto consta de 142 pruebas distribuidas por capas: modelo intermedio, adaptador Ecore, adaptador del DSL textual y generador HTML/JavaScript. Estas pruebas no sustituyen a la evaluación con el ejemplo conductor, pero reducen el riesgo de que el generador funcione solo por una coincidencia accidental del caso AppMaker.

Las pruebas del modelo intermedio verifican valores por defecto, filtrado de bloques abstractos, tipos de campo, conexiones y metadatos de código. Las pruebas del adaptador Ecore comprueban el mapeo de clases, atributos, referencias, cardinalidades, anotaciones, conexiones y validaciones. Las pruebas del adaptador DSL verifican atributos y referencias obligatorias, etiquetas de referencia y metadatos de generación de código. Las pruebas del generador HTML/JavaScript revisan que los ficheros generados contengan las construcciones esperadas de Blockly, las validaciones, las referencias y la exportación de código.

## Resumen de implementación

La implementación de BlocklyDSL permite definir un dominio mediante Ecore o mediante un DSL textual. Cada ruta se adapta a una representación común. A partir de esa representación se generan editores Blockly HTML/JavaScript, validaciones, referencias dinámicas, exportadores y plantillas de código. La existencia de pruebas por capa refuerza que el proyecto no es una colección de ficheros generados manualmente, sino una infraestructura generativa organizada alrededor de un modelo intermedio.
