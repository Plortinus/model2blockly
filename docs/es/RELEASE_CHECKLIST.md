# Checklist de publicación

Idioma: [English](../../RELEASE_CHECKLIST.md) | **Español** | [中文](../zh/RELEASE_CHECKLIST.md)

Use esta guía cuando publique una nueva versión del plugin Eclipse y del update
site. La checklist inglesa completa está en
[`../../RELEASE_CHECKLIST.md`](../../RELEASE_CHECKLIST.md).

## Version

Mantenga la misma versión en estos lugares:

| Archivo | Campo |
| --- | --- |
| `io.github.plortinus.model2blockly/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ide/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.feature/feature.xml` | `feature/@version` |
| `io.github.plortinus.model2blockly.updatesite/category.xml` | `feature/@version` |
| `scripts/verify-eclipse-plugin.mjs` | `pluginVersion` |

Si la versión no cambia, Eclipse puede ocultar la actualización cuando
`Hide items that are already installed` está activado.

## Reconstruir el update site local

1. Compile los bundles core, IDE y UI con Java 21.
2. Cree los jars versionados bajo:

```text
io.github.plortinus.model2blockly.updatesite/repository/plugins/
io.github.plortinus.model2blockly.updatesite/repository/features/
```

3. Regenerar metadata p2:

```bash
npm run rebuild:update-site
```

Si Eclipse no está en la ruta macOS por defecto:

```bash
ECLIPSE=/path/to/eclipse npm run rebuild:update-site
```

## Verificacion obligatoria

```bash
npm run verify:plugin
npm run verify:docs
npm run verify:dsl-validation
npm run verify:patch
npm run smoke
```

Si Eclipse no está en la ruta macOS por defecto, configure los plugins y Java
antes de las verificaciones:

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
```

No publique si algun comando falla.

## Publicacion GitHub Pages

El workflow `.github/workflows/pages.yml` publica el sitio al hacer push a
`main`. También renderiza los documentos Markdown como páginas HTML bajo
`/docs/`.

El update site publico es:

```text
https://plortinus.github.io/model2blockly/update-site/
```

Despues del deploy, compruebe:

```text
https://plortinus.github.io/model2blockly/
https://plortinus.github.io/model2blockly/update-site/
https://plortinus.github.io/model2blockly/update-site/content.jar
https://plortinus.github.io/model2blockly/update-site/artifacts.jar
```

## Instrucciones para usuarios

1. Abrir `Help -> Install New Software...`.
2. Usar:

```text
https://plortinus.github.io/model2blockly/update-site/
```

3. Desmarcar `Hide items that are already installed`.
4. Si no aparecen elementos, desmarcar `Group items by category`.
5. Eliminar entradas antiguas desde `Manage...`, agregar el sitio otra vez y
   recargar.
6. Mantener activado `Contact all update sites during install to find required
   software`.
7. Instalar Model2Blockly y reiniciar Eclipse.

## Smoke check posterior

1. Prepare un archivo `.model2blockly` en un workspace limpio de Eclipse.
2. Ejecute `Generate Blockly Editor`.
3. Confirme que se abre `generation_report.html`.
4. Confirme que la salida contiene `intermediate/*_blocklyspec.xmi`.
5. Confirme que modelos DSL invalidos producen marcadores `Problems`, no stack
   traces Java.
6. Si prueba Ecore, genere desde `app_maker.ecore` y confirme que el editor se
   abre.
