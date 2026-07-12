# Conclusiones y trabajo futuro

Este Trabajo Fin de Máster ha abordado la creación de lenguajes de dominio específico con sintaxis basada en bloques. La motivación inicial partía de una observación práctica: Blockly proporciona una infraestructura sólida para crear editores visuales, pero construir un editor para cada dominio sigue requiriendo definir manualmente bloques, campos, toolbox, conexiones, validaciones y generadores. El trabajo desarrollado propone y materializa una alternativa basada en ingeniería de lenguajes y modelos: describir el dominio a un nivel abstracto y generar automáticamente el editor Blockly correspondiente.

## Viabilidad del enfoque

El trabajo desarrollado aporta evidencia de que es viable diseñar e implementar un entorno para la creación de lenguajes de dominio específico con sintaxis basada en bloques. BlocklyDSL acepta dos rutas de entrada: metamodelos Ecore y descripciones textuales definidas con Xtext. En ambos casos, la entrada se transforma en un modelo intermedio común, y desde él se generan editores Blockly ejecutables.

La solución es razonable porque aprovecha información que ya existe en una descripción de dominio. Las clases se convierten en tipos de bloque, los atributos en campos, las contenciones en entradas estructurales, las referencias en desplegables dinámicos, las cardinalidades en comprobaciones de mínimos y máximos, y las anotaciones en metadatos de presentación. Este proceso reduce la necesidad de programar manualmente cada editor en JavaScript y aprovecha la idea central de la ingeniería dirigida por modelos: utilizar modelos como artefactos principales para especificar y generar software [@jimenez2017scalable].

El trabajo también confirma la utilidad de separar lo que el dominio significa de la forma en que se muestra al usuario. El dominio se describe mediante Ecore o mediante el DSL textual; Blockly actúa como la representación visual generada para manipular ese dominio. Esta separación es coherente con la forma en que los lenguajes de dominio específico organizan sus conceptos y sus notaciones [@sal2024dsl].

## Principales aportaciones

La primera aportación del trabajo es una arquitectura generativa que conecta Ecore, Xtext y Blockly. Esta arquitectura no acopla directamente una entrada a una salida, sino que introduce un modelo intermedio. Gracias a esta decisión, la ruta Ecore y la ruta textual comparten los mismos generadores y reutilizan la misma información de dominio para producir la salida HTML/JavaScript.

La segunda aportación es el propio modelo intermedio común. Este modelo recoge los elementos necesarios para construir un editor visual: bloques, campos, categorías, entradas de valor, entradas de sentencias, referencias, validaciones, opciones de workspace y metadatos de código. Su papel es más que técnico: define qué información deben producir los adaptadores y qué información pueden consumir los generadores.

La tercera aportación es la ruta Ecore. El adaptador desarrollado permite partir de un `EPackage` y derivar una especificación de editor. Esta ruta desempeña la función metamodelo-céntrica del sistema: usar un metamodelo, enriquecido cuando sea necesario con anotaciones, para generar código basado en Blockly.

La cuarta aportación es el DSL textual. Esta ruta facilita definir dominios de forma compacta, con una sintaxis cercana a los conceptos que después se convertirán en bloques. También incorpora validación estática mediante Xtext, lo que permite detectar errores antes de generar el editor.

La quinta aportación es la generación de editores completos. La salida HTML/JavaScript produce definiciones de bloques, toolbox, generadores, validaciones y páginas HTML autocontenidas que pueden abrirse directamente en el navegador.

Finalmente, el trabajo aporta una evaluación con un ejemplo conductor y pruebas automáticas. AppMaker se describe tanto con el DSL textual como con Ecore anotado, lo que permite comparar las dos rutas de entrada sobre el mismo dominio. Las 142 pruebas JUnit organizadas por capa refuerzan la confianza en el modelo intermedio, los adaptadores y los generadores.

## Conclusiones por objetivos

Los objetivos específicos definidos al inicio del trabajo se han abordado de la siguiente forma:

- **OE1.** Se analizaron los elementos necesarios para un editor de bloques y se representaron explícitamente en el modelo intermedio: tipos de bloque, campos, conexiones, categorías, referencias, validaciones y generación.

- **OE2.** Se implementó una ruta Ecore que transforma clases, atributos, referencias, herencia, cardinalidades y anotaciones en una especificación Blockly.

- **OE3.** Se definió un DSL textual con Xtext para describir dominios sin editar directamente ficheros Ecore.

- **OE4.** Se diseñó e implementó un modelo intermedio común como representación independiente de la fuente de entrada.

- **OE5.** Se generaron editores HTML/JavaScript ejecutables con bloques, toolbox, validaciones, guardado, carga y exportación.

- **OE6.** Se incorporaron comprobaciones, referencias dinámicas, personalización visual, metadatos de interfaz y plantillas de código.

- **OE7.** Se evaluó la solución con AppMaker por las dos rutas de entrada y con pruebas automáticas sobre las capas principales del sistema.

Por lo tanto, el enfoque es técnicamente razonable dentro del alcance definido. Distintos dominios pueden describirse mediante modelos o DSLs y transformarse en editores de bloques mediante una misma cadena generativa.

## Limitaciones

Aunque los resultados respaldan el enfoque principal, el trabajo tiene limitaciones. La primera es que la evaluación se ha centrado en un ejemplo diseñado dentro del proyecto. AppMaker cubre estructuras variadas, pero no equivale a una validación industrial con un conjunto amplio de metamodelos externos. Por ello, el caso estudiado apoya la viabilidad de la herramienta, pero no prueba su generalidad de forma exhaustiva.

La segunda limitación afecta a las validaciones. BlocklyDSL genera advertencias para el número mínimo y máximo de bloques contenidos, campos obligatorios, referencias obligatorias, unicidad, reglas simples de orden inmediato, expresiones declaradas en anotaciones y un subconjunto sencillo de invariantes OCL. Este soporte cubre restricciones frecuentes, pero no equivale a un lenguaje de restricciones completo como OCL [@omgOcl]. En particular, no se soportan invariantes arbitrarios, cuantificadores sobre colecciones, navegación compleja por el grafo de objetos, condiciones derivadas de varios elementos no relacionados localmente ni análisis semántico profundo sobre los programas creados con los bloques. Además, las comprobaciones generadas producen advertencias y confirmación de exportación, no una validación bloqueante.

La tercera limitación se refiere a las referencias. Las referencias no contenidas se representan mediante desplegables dinámicos, lo que permite seleccionar elementos existentes del workspace. Además, el runtime HTML sincroniza referencias opuestas no contenidas cuando ambos extremos son editables. La limitación actual está en los casos más complejos: referencias opuestas con semántica de contenedor, sincronización equivalente en HTML y visualización avanzada de relaciones entre elementos.

La cuarta limitación está relacionada con la generación de código. La solución incluye plantillas de código y una representación textual por defecto, pero no incorpora todavía formateadores específicos, análisis de tipos avanzado, gestión de imports, generación multiarchivo ni integración directa con herramientas externas de construcción o ejecución.

La quinta limitación es de evaluación de usuario. Este TFM no mide si los editores generados reducen efectivamente el esfuerzo de aprendizaje o mejoran la experiencia de usuarios no técnicos. La literatura sobre programación por bloques muestra que la modalidad de interacción influye en las prácticas de los usuarios noveles [@weintrop2018modalities], pero validar empíricamente los editores generados requeriría un estudio específico.

## Trabajo futuro

A partir de las limitaciones anteriores, se identifican varias líneas de trabajo futuro.

En primer lugar, sería útil ampliar el soporte de validación. Una extensión natural consistiría en integrar un lenguaje de restricciones más expresivo o un mecanismo de reglas declarativas que permita describir condiciones semánticas más complejas que los mínimos, máximos y el orden inmediato. Esto permitiría generar editores que no solo adviertan sobre errores estructurales simples, sino también sobre propiedades específicas del dominio.

En segundo lugar, la gestión de referencias podría evolucionar hacia un soporte más completo de referencias bidireccionales. Esto implicaría extender la sincronización de referencias opuestas al runtime HTML, cubrir casos de contenedor, detectar referencias que apuntan a elementos ya eliminados y ofrecer visualizaciones más claras de las relaciones entre elementos del workspace.

En tercer lugar, se podría profundizar en la importación y exportación de modelos. Actualmente la herramienta se centra en generar editores y exportar el contenido del workspace. Una línea futura consistiría en importar modelos XMI existentes y reconstruir automáticamente el workspace Blockly correspondiente. Esto acercaría más la herramienta a flujos completos de ingeniería de modelos.

En cuarto lugar, la generación de código podría enriquecerse. Las plantillas actuales son suficientes para demostrar una ruta genérica de exportación, pero podrían ampliarse con validación de plantillas, tipado, formateadores, generación multiarchivo y objetivos específicos para lenguajes o plataformas concretas.

En quinto lugar, podrían explorarse interfaces web más avanzadas sobre el mismo modelo intermedio. Un futuro objetivo de generación podría incorporar un inspector estructurado, temas visuales por dominio, componentes personalizados y modos de edición más adaptados a usuarios finales.

En sexto lugar, sería conveniente realizar una evaluación empírica con usuarios. Un estudio futuro podría comparar el esfuerzo necesario para construir un editor Blockly manualmente frente al uso de BlocklyDSL, o analizar cómo interactúan usuarios no técnicos con editores generados para distintos dominios. Esta evaluación permitiría complementar la evidencia técnica de este TFM con datos de uso.

## Cierre

BlocklyDSL demuestra que es posible conectar técnicas de ingeniería de lenguajes con entornos de programación basada en bloques. El resultado no es un único editor visual, sino una infraestructura que transforma descripciones de dominio en editores Blockly ejecutables. Esta aproximación conserva el conocimiento del dominio en modelos o DSLs de alto nivel y deriva de ellos una sintaxis visual interactiva.

El trabajo desarrollado muestra que la combinación de Ecore, Xtext y Blockly es viable para construir editores de lenguajes de dominio específico con sintaxis basada en bloques. Dentro del alcance definido, la herramienta aporta evidencia de que esta combinación es una alternativa razonable a la programación manual de editores Blockly y deja una base extensible para futuras mejoras en validación, importación de modelos, generación de código, interfaces HTML y evaluación con usuarios.
