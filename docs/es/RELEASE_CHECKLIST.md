# Checklist de publicación

## Documentación

```bash
npm run build:site-docs
npm run verify:docs
```

Comprueba que existen:

- `_site/en/install.html`
- `_site/es/install.html`
- `_site/zh/install.html`
- `_site/en/architecture.html`
- `_site/es/architecture.html`
- `_site/zh/architecture.html`

## Plugin y ejemplo generado

```bash
npm run verify:plugin
npm run smoke
npm run verify:domain-xmi
npm run verify:patch
```

## Update site

Cuando cambien artefactos instalables:

```bash
npm run rebuild:update-site
```

Antes de publicar deben existir:

```text
io.github.plortinus.model2blockly.updatesite/repository/content.jar
io.github.plortinus.model2blockly.updatesite/repository/artifacts.jar
io.github.plortinus.model2blockly.updatesite/category.xml
```

## GitHub Pages

El workflow debe construir VitePress en `_site/`, copiar AppMaker a
`_site/app_maker_ecore/`, copiar el repositorio p2 a `_site/update-site/` y
desplegar `_site`.
