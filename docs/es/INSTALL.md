# Instalar el plugin de Eclipse

Esta página explica cómo instalar Model2Blockly desde el update site p2
publicado. La URL del update site es un endpoint funcional para Eclipse, no una
segunda documentación.

## URL del update site

Usa esta URL en Eclipse:

```text
https://plortinus.github.io/model2blockly/update-site/
```

Ese endpoint expone los metadatos p2:

- `content.jar`
- `artifacts.jar`
- `category.xml`

Eclipse lee esos archivos directamente para resolver las features instalables.

## Requisitos

| Requisito | Motivo |
| --- | --- |
| Eclipse con soporte JavaSE-21 | Los bundles declaran JavaSE-21. |
| Eclipse ejecutándose con JDK 21 | Las configuraciones de lanzamiento y metadatos del plugin apuntan a Java 21. |
| Resolución de dependencias activa | Eclipse debe resolver dependencias Xtext y EMF desde los update sites configurados. |

Mantén activada la opción `Contact all update sites during install to find
required software` salvo que tu instalación ya tenga todas las dependencias.

## Pasos de instalación

1. Abre Eclipse.
2. Selecciona `Help` -> `Install New Software...`.
3. Pulsa `Add...`.
4. Usa un nombre como `Model2Blockly`.
5. Pega la URL del update site:

   ```text
   https://plortinus.github.io/model2blockly/update-site/
   ```

6. Selecciona la feature de Model2Blockly.
7. Completa el asistente de instalación.
8. Reinicia Eclipse cuando lo pida.

Después de instalar, selecciona un archivo `.ecore`, `.m2b` o `.model2blockly`
en el workspace y ejecuta `Generate Blockly Editor` desde el menú contextual.

## Si no aparecen elementos

Si el diálogo no muestra elementos instalables:

1. Pulsa `Reload`.
2. Desactiva `Group items by category`.
3. Comprueba que la URL termina en `/update-site/`.
4. Mantén activa la resolución de dependencias.
5. Elimina entradas antiguas de Model2Blockly en `Manage...`, añade de nuevo el
   sitio y recarga.

No hace falta que el navegador muestre un listado de directorios. Eclipse lee
`content.jar` y `artifacts.jar` directamente.

## Actualizar una instalación existente

Si Model2Blockly ya está instalado y no aparece una build nueva:

1. En `Install New Software...`, desactiva `Hide items that are already installed`.
2. Abre `Manage...` y elimina entradas antiguas del update site.
3. Añade de nuevo la URL.
4. Pulsa `Reload`.
5. Instala la feature visible o usa `Help` -> `Check for Updates`.

Reinicia Eclipse después de actualizar.

## Verificar la instalación

Usa primero el caso Ecore incluido:

1. Importa los proyectos de Model2Blockly o abre un workspace con un `.ecore`.
2. Selecciona `io.github.plortinus.model2blockly/model/app_maker.ecore`.
3. Haz clic derecho y elige `Generate Blockly Editor`.
4. Abre `generation_report.html`.
5. Abre `html/Appmaker_standalone.html` y pulsa `Load Sample`.

La carpeta esperada para AppMaker es:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

## Páginas relacionadas

- [Guía de uso](./USER_GUIDE.md)
- [Solución de problemas](./TROUBLESHOOTING.md)
- [Checklist de publicación](./RELEASE_CHECKLIST.md)
