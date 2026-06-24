---
layout: home
hero:
  name: Model2Blockly
  text: Plugin de Eclipse para editores Blockly
  tagline: Instale Model2Blockly en Eclipse, seleccione un metamodelo `.ecore` anotado y genere un editor DSL Blockly listo para el navegador.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: El plugin Eclipse Model2Blockly convierte metamodelos Ecore en editores Blockly
  actions:
    - theme: brand
      text: Empezar
      link: /es/user-guide
    - theme: alt
      text: Caso AppMaker
      link: /es/running-example
    - theme: alt
      text: Instalar plugin
      link: https://plortinus.github.io/model2blockly/update-site/
features:
  - title: Generar desde Ecore
    details: Reutilice un metamodelo `.ecore` existente y añada anotaciones para etiquetas, colores, categorías, campos y validaciones Blockly.
    link: /es/ecore-reference
    linkText: Soporte Ecore
  - title: Usar modelos EMF intermedios
    details: Transforme el EPackage fuente en un modelo EditorSpec generado, persístalo como XMI, recárguelo y genere desde ese modelo.
    link: /es/architecture
    linkText: Arquitectura
  - title: Crear páginas Blockly ejecutables
    details: Genere toolbox, workspace, definiciones de bloques, generadores de código y páginas HTML standalone listas para navegador.
    link: /es/running-example
    linkText: Caso AppMaker
  - title: Visualizar reglas de validación
    details: Cree un workspace de validación donde campos obligatorios, cardinalidad, orden, referencias y expresiones simples aparecen como bloques.
    link: /es/user-guide
    linkText: Flujo de uso
  - title: Exportar JSON, XMI y código
    details: Guarde y cargue workspaces, y exporte modelos de usuario como JSON, XMI de dominio comprobable con EMF en la ruta Ecore, o código generado por plantillas.
    link: /es/user-guide
    linkText: Flujo de exportación
  - title: Revisar informes y XMI
    details: Cada generación escribe un informe y un XMI BlocklyEditorSpec para mantener visible la transformación de entrada a editor.
    link: /es/architecture
    linkText: Flujo de generación
---
