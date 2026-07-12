# Resumen

Este Trabajo Fin de Máster aborda la creación de lenguajes de dominio específico con sintaxis basada en bloques. La motivación principal es que bibliotecas como Blockly facilitan la construcción de editores visuales, pero siguen requiriendo que el desarrollador defina manualmente bloques, campos, conexiones, paletas, validaciones y generadores en JavaScript. Esta tarea se repite cada vez que se desea crear un editor para un nuevo dominio.

Para reducir ese esfuerzo, el trabajo propone BlocklyDSL, un entorno generativo que transforma descripciones abstractas de dominio en editores Blockly ejecutables. La herramienta admite dos rutas de entrada: metamodelos Ecore, posiblemente enriquecidos con anotaciones, y descripciones textuales definidas mediante Xtext. Ambas rutas se normalizan en un modelo intermedio común, que representa bloques, campos, categorías, entradas de valor, referencias, validaciones y metadatos de interfaz. A partir de esta especificación se generan editores HTML/JavaScript. Los editores generados pueden abrirse en el navegador y ofrecen guardado, carga, exportación y avisos de validación en tiempo de ejecución.

La solución se evalúa mediante un ejemplo conductor, AppMaker, implementado por las dos rutas de entrada: DSL textual y Ecore con anotaciones. Este dominio cubre pantallas, widgets, acciones, navegación, fuentes de datos, contenciones, herencia, clases abstractas, referencias dinámicas, campos obligatorios, validaciones simples y plantillas de código. Además, el proyecto incluye 142 pruebas JUnit organizadas por capas. Los resultados muestran que BlocklyDSL permite generar editores de bloques a partir de modelos o DSLs de alto nivel, reduciendo la programación manual de bajo nivel y manteniendo una arquitectura reutilizable.

**Palabras clave:** lenguajes de dominio específico, programación basada en bloques, Blockly, EMF, Ecore, Xtext, ingeniería dirigida por modelos.

# Abstract

This Master's Thesis addresses the creation of domain-specific languages with a block-based syntax. The main motivation is that libraries such as Blockly make it easier to build visual editors, but they still require developers to define blocks, fields, connections, toolboxes, validations and generators manually in JavaScript. This work is repeated whenever an editor is needed for a new domain.

To reduce this effort, the thesis proposes BlocklyDSL, a generative environment that transforms abstract domain descriptions into executable Blockly editors. The tool supports two input routes: Ecore metamodels, possibly enriched with annotations, and textual domain descriptions defined with Xtext. Both routes are normalised into a common intermediate model, which represents blocks, fields, categories, value inputs, references, validations and interface metadata. From this specification, the tool generates HTML/JavaScript editors. The generated editors can be opened in a browser and support saving, loading, export and runtime validation warnings without requiring manually written Blockly definitions for each domain.

The solution is evaluated through a running example, AppMaker, implemented through the two input routes: textual DSL and Ecore with annotations. This domain covers screens, widgets, actions, navigation, data sources, containments, inheritance, abstract classes, dynamic references, required fields, simple validations and code templates. In addition, the project includes 142 JUnit tests organised by architectural layer. The results show that BlocklyDSL can generate block-based editors from high-level models or DSLs, reducing low-level manual programming while preserving a reusable architecture.

**Keywords:** domain-specific languages, block-based programming, Blockly, EMF, Ecore, Xtext, model-driven engineering.
