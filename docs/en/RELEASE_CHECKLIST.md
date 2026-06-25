# Release Checklist

Use this checklist before pushing a documentation, plugin or generated-output
change to `main`.

## Documentation

```bash
npm run build:site-docs
npm run verify:docs
```

Confirm that:

- `_site/index.html` is the VitePress home page;
- `_site/en/install.html` exists;
- `_site/en/user-guide.html` exists;
- `_site/en/architecture.html` exists;
- `_site/en/running-example.html` exists;
- `_site/update-site/` is copied by the GitHub Actions workflow, not by the
  VitePress build script itself.

## Plugin and Generated Example

```bash
npm run verify:plugin
npm run smoke
npm run verify:domain-xmi
npm run verify:patch
```

These checks cover plugin metadata, generated AppMaker browser behavior,
domain-XMI export and validation-patch support.

## Update Site

When the Eclipse installable artifacts change, rebuild the update site:

```bash
npm run rebuild:update-site
```

Then verify that these paths exist before pushing:

```text
io.github.plortinus.model2blockly.updatesite/repository/content.jar
io.github.plortinus.model2blockly.updatesite/repository/artifacts.jar
io.github.plortinus.model2blockly.updatesite/category.xml
```

## GitHub Pages

The Pages workflow should:

1. install Node dependencies;
2. run `npm run build:site-docs`;
3. copy generated AppMaker HTML to `_site/app_maker_ecore`;
4. copy the Eclipse update-site repository to `_site/update-site`;
5. upload `_site` with `actions/upload-pages-artifact`.

GitHub repository settings must use:

```text
Pages source: GitHub Actions
```

## Final Push

Before pushing:

```bash
git status --short --branch
git log --oneline --decorate -3
```

Do not include local analysis notes or thesis files unless that is the
intended change.
