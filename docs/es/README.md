---
layout: home
hero:
  name: Model2Blockly
  text: Genera editores Blockly desde Ecore anotado
  tagline: Un plugin de Eclipse y una tubería standalone que transforma un metamodelo EMF Ecore en un editor Blockly ejecutable en navegador mediante un modelo intermedio EditorSpec en XMI.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly convierte Ecore anotado en un modelo EditorSpec intermedio y después en salida HTML de Blockly
  actions:
    - theme: brand
      text: Empezar con Ecore
      link: /es/user-guide
    - theme: alt
      text: Instalar plugin
      link: /es/install
    - theme: alt
      text: Caso AppMaker
      link: /es/running-example
features:
  - title: Ecore es la entrada principal
    details: Selecciona un archivo Ecore en Eclipse o usa el punto de entrada standalone de Ecore. Las anotaciones opcionales refinan etiquetas, categorías, widgets, validaciones y plantillas de código.
    link: /es/ecore-to-blockly-mapping
    linkText: Reglas de mapeo
  - title: EditorSpec es el contrato de generación
    details: El EPackage fuente se transforma en un modelo EMF EditorSpec, se serializa como XMI, se recarga y se valida antes de generar HTML.
    link: /es/architecture
    linkText: Ver arquitectura
  - title: La salida Blockly es HTML estático
    details: La generación escribe definiciones de bloques, toolbox, generadores de código, editor standalone, workspace de validación, modelo de ejemplo e informe.
    link: /es/running-example
    linkText: Inspeccionar AppMaker
  - title: Xtext es auxiliar
    details: Los archivos m2b y model2blockly siguen soportados por Eclipse y por la ruta standalone, pero la ruta documentada del proyecto parte de Ecore.
    link: /es/architecture
    linkText: Ver límites
  - title: La validación se genera
    details: Campos obligatorios, cardinalidad de contención, referencias, unicidad y un subconjunto de expresiones/OCL se convierten en comprobaciones runtime y bloques visuales.
    link: /es/user-guide
    linkText: Usar el flujo
  - title: Instalación del plugin
    details: El sitio público es ahora la documentación VitePress. El update site de Eclipse sigue publicado como endpoint funcional para instalar el plugin.
    link: /es/install
    linkText: Guía de instalación
---

## Forma actual del proyecto

Model2Blockly no es una biblioteca de plantillas Blockly escritas a mano. El
código actual es una tubería MDE:

```text
.ecore anotado
  -> EMF EPackage
  -> EcoreAdapter
  -> modelo EMF EditorSpec
  -> intermediate/*_blocklyspec.xmi
  -> BlocklyCodeGenerator
  -> editor HTML/JavaScript estático
```

El caso AppMaker es la ruta de referencia en este repositorio. La salida
generada está en:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```
