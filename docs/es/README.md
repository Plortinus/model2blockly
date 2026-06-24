# Documentación de Model2Blockly

Idioma: [English](../en/README.md) | **Español** | [中文](../zh/README.md)

Model2Blockly genera editores Blockly desde dos tipos de modelo:

- archivos textuales `.model2blockly`;
- metamodelos Ecore anotados.

Ambas rutas se transforman en una instancia EMF generada `EditorSpec`, conforme
al metamodelo intermedio `BlocklyEditorSpec`, se serializan como
`intermediate/*_blocklyspec.xmi`, se recargan desde ese XMI y despues se
generan como archivos HTML y JavaScript de Blockly. El editor generado tambien
puede exportar los bloques creados por el usuario como modelo de instancia del
dominio en JSON/XMI.

La cadena de generacion es model-driven:

```text
.model2blockly / .ecore -> EditorSpec EMF -> XMI intermedio -> Blockly HTML/JavaScript -> XMI de instancia
```

Hay dos usos distintos de XMI: `intermediate/*_blocklyspec.xmi` es una instancia
de `EditorSpec` usada por el generador; el `*_model.xmi` exportado desde el
editor generado es el modelo de instancia creado por el usuario.

## Por donde empezar

| Objetivo | Documento |
| --- | --- |
| Generar el primer editor | [`GETTING_STARTED.md`](GETTING_STARTED.md) |
| Resolver problemas de instalación o generación | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| Entender la sintaxis `.model2blockly` | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| Entender las anotaciones Ecore | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| Entender la tuberia XMI intermedia | [Vista general](../../README.md) |
| Publicar una nueva versión del update site | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) |

## Referencias detalladas

Las guías localizadas priorizan el flujo de uso y los conceptos principales.
Las tablas completas de claves, sintaxis, opciones y mapeos de generación están
en las referencias inglesas:

- [Vista general del proyecto](../../README.md)
- [Mapa de documentación](../../DOCS.md)
- [Referencia DSL completa](../../DSL_REFERENCE.md)
- [Referencia Ecore completa](../../ECORE_REFERENCE.md)

Si una explicación no coincide con el comportamiento real, use la
implementación actual como criterio final.

## Enlaces públicos

- Sitio del proyecto: <https://plortinus.github.io/model2blockly/>
- Update site de Eclipse: <https://plortinus.github.io/model2blockly/update-site/>
- Código fuente: <https://github.com/Plortinus/model2blockly>
