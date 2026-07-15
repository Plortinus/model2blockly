---
layout: home
hero:
  name: Model2Blockly
  text: Genera editores Blockly desde Ecore o .m2b
  tagline: Un complemento de Eclipse y una cadena de generación independiente que normaliza metamodelos Ecore anotados y especificaciones textuales .m2b en un EditorSpec XMI inspeccionable y un editor Blockly ejecutable en el navegador.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly convierte Ecore o m2b en un modelo EditorSpec intermedio y después en una salida HTML de Blockly
  actions:
    - theme: brand
      text: Empezar con un modelo
      link: /es/user-guide
    - theme: alt
      text: Instalar complemento
      link: /es/install
    - theme: alt
      text: Caso AppMaker
      link: /es/running-example
features:
  - title: Dos entradas y un único generador
    details: Se puede partir de un metamodelo Ecore anotado o de una especificación textual .m2b compacta. EcoreAdapter y DomainModelAdapter normalizan ambas fuentes al mismo contrato EditorSpec.
    link: /es/user-guide
    linkText: Elegir una ruta de entrada
  - title: EditorSpec es el contrato de generación
    details: Cada modelo fuente se transforma en un EditorSpec EMF, se serializa como XMI, se recarga y se valida antes de generar HTML.
    link: /es/architecture
    linkText: Ver arquitectura
  - title: La salida Blockly es HTML estático
    details: La generación escribe definiciones de bloques, paleta, generadores de código, página autónoma del editor, espacio de trabajo de validación, modelo de ejemplo e informe.
    link: /es/running-example
    linkText: Inspeccionar AppMaker
  - title: Evaluación con editores existentes
    details: Diez configuraciones oficiales permiten separar el ejemplo AppMaker de la evidencia externa y cuantificar paridad funcional, tamaño de especificación y límites actuales.
    link: /es/evaluation
    linkText: Ver resultados
  - title: Ecore y .m2b son entradas de primer nivel
    details: Ecore resulta adecuado cuando ya existe un metamodelo EMF; .m2b permite mantener una definición textual más compacta. La extensión larga .model2blockly se conserva como alias heredado.
    link: /es/textual-dsl
    linkText: Consultar el DSL textual
  - title: La validación se genera
    details: Campos obligatorios, cardinalidad de contención, referencias, unicidad y un subconjunto de expresiones/OCL se convierten en comprobaciones en tiempo de ejecución y bloques visuales.
    link: /es/user-guide
    linkText: Usar el flujo
  - title: Instalación del complemento
    details: El sitio público contiene la documentación VitePress. El sitio de actualización de Eclipse se publica como punto de instalación del complemento.
    link: /es/install
    linkText: Guía de instalación
---

## Forma actual del proyecto

Model2Blockly no es una biblioteca de plantillas Blockly escritas a mano. El
código actual implementa una cadena MDE con dos entradas:

```text
.ecore anotado -> EPackage    -> EcoreAdapter -------\
                                                      -> modelo EMF EditorSpec
.m2b           -> DomainModel -> DomainModelAdapter --/
                                                      -> intermediate/*_blocklyspec.xmi
                                                      -> BlocklyCodeGenerator
                                                      -> editor HTML/JavaScript estático
```

El caso de integración AppMaker ofrece las dos formas de autoría. Sus salidas
versionadas están en:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
io.github.plortinus.model2blockly/examples/generated/app_maker_dsl
```
