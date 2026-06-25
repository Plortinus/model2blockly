# Solución de problemas

## GitHub Pages muestra una página incorrecta

En GitHub debe estar seleccionado:

```text
Settings -> Pages -> Build and deployment -> Source -> GitHub Actions
```

El workflow publica VitePress en la raíz del sitio:

```text
https://plortinus.github.io/model2blockly/
```

## Eclipse no instala el plugin

Usa:

```text
https://plortinus.github.io/model2blockly/update-site/
```

Si no aparecen elementos, pulsa `Reload`, desactiva `Group items by category` y
mantén activa la resolución de dependencias.

## No aparece el comando Generate

El comando aparece para un único archivo seleccionado con extensión:

- `.ecore`
- `.m2b`
- `.model2blockly`

Lo implementa `GenerateBlocklyEditorHandler`.

## Falla la generación desde Ecore

Comprueba que el archivo contiene un `EPackage`, que los clasificadores referenciados
se resuelven y que valores como `colour`, `width`, `height` u `order` son numéricos.

## HTML generado obsoleto

La salida actual se escribe bajo `html/`. Vuelve a generar, abre el archivo de
esa carpeta y refresca el navegador.

## Verificaciones útiles

```bash
npm run verify:docs
npm run verify:plugin
npm run smoke
npm run verify:domain-xmi
```
