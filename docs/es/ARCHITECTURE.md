# Arquitectura

Model2Blockly se organiza como una cadena MDE con dos entradas. Los
metamodelos Ecore anotados y los modelos textuales `.m2b` se transforman al
mismo modelo intermedio EMF `EditorSpec`; después, el generador Xtend produce
los artefactos Blockly estáticos.

## Flujo runtime

```text
.ecore anotado                    DSL textual .m2b
  -> EPackage                       -> DomainModel
  -> EcoreAdapter                   -> DomainModelAdapter
          \                         /
           \                       /
            -> modelo EMF EditorSpec
            -> BlocklySpecXmiSerializer
            -> intermediate/*_blocklyspec.xmi
            -> recarga XMI y diagnósticos
            -> BlocklyCodeGenerator
            -> artefactos HTML/JavaScript
```

![Flujo de generación](../assets/diagrams/generation-flow.svg)

## Módulos

| Módulo | Rol |
| --- | --- |
| `io.github.plortinus.model2blockly` | Plugin núcleo: Xtext/EMF, adaptadores, modelo intermedio, generadores y entradas standalone. |
| `io.github.plortinus.model2blockly.ui` | Integración UI de Eclipse: menús, comandos y parche de validación. |
| `io.github.plortinus.model2blockly.ide` | Servicios IDE de Xtext para la sintaxis auxiliar. |
| `io.github.plortinus.model2blockly.feature` | Empaquetado como feature de Eclipse. |
| `io.github.plortinus.model2blockly.updatesite` | Repositorio p2 publicado. |
| `docs` | Fuente de la documentación VitePress. |
| `scripts` | Verificación y construcción del sitio/update-site. |

## Código generado y escrito a mano

| Directorio | Contenido |
| --- | --- |
| `src` | Implementación Java/Xtend escrita a mano. |
| `src-gen` | Código generado por Xtext. |
| `emf-gen` | APIs Java generadas por EMF para los metamodelos fijos. |
| `xtend-gen` | Java generado desde Xtend. |

## Mapa de implementación

| Responsabilidad | Archivo principal |
| --- | --- |
| Ruta Ecore standalone | `standalone/EcoreToBlocklyMain.java` |
| Conversión `EPackage` -> `EditorSpec` | `adapter/EcoreAdapter.java` |
| Ruta DSL textual -> `EditorSpec` | `adapter/DomainModelAdapter.java` |
| Serialización XMI intermedia | `intermediate/BlocklySpecXmiSerializer.java` |
| Validación intermedia | `blocklyspec/BlocklyEditorSpecValidator.java` |
| Generación Blockly | `generator/BlocklyCodeGenerator.xtend` |
| Informe de generación | `generator/GenerationReportHtmlRenderer.java` |
| Comando Eclipse | `ui/handlers/GenerateBlocklyEditorHandler.java` |

## Modelo intermedio

`EditorSpec` es el contrato entre el análisis del modelo fuente y la salida
Blockly:

```text
Ruta Ecore:
  EPackage -> EcoreAdapter -> EditorSpec

Ruta DSL textual:
  DomainModel -> DomainModelAdapter -> EditorSpec
```

El XMI `intermediate/*_blocklyspec.xmi` se escribe, se recarga y se valida antes
de generar HTML.

![Arquitectura del sistema](../assets/diagrams/system-architecture.svg)

## Mapeo Ecore

| Fuente Ecore | Significado generado |
| --- | --- |
| `EClass` | Tipo de bloque Blockly. |
| `EClass` abstracta o interfaz | Tipo abstracto no emitido como bloque concreto. |
| `EAttribute` | Campo Blockly. |
| Atributo `EEnum` | Campo desplegable. |
| `EReference` de contención | Entrada de sentencia y validación de contención. |
| `EReference` no de contención | Campo de referencia dinámico. |
| `lowerBound >= 1` | Validación de valor obligatorio. |
| Feature múltiple única | Validación de duplicados. |
| Atributo `iD` | Identidad para referencias y exportación XMI. |

Las claves de anotación están en el [mapeo de Ecore a Blockly](./ECORE_TO_BLOCKLY_MAPPING.md).

## GitHub Pages

GitHub Pages publica VitePress en:

```text
https://plortinus.github.io/model2blockly/
```

Después del build de VitePress, el workflow copia:

| Ruta | Propósito |
| --- | --- |
| `/update-site/` | Endpoint p2 para instalar el plugin. |
| `/app_maker_ecore/` | Editor AppMaker generado desde Ecore anotado. |
| `/app_maker_dsl/` | Editor AppMaker generado desde el DSL textual `.m2b`. |
