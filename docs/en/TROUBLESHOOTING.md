# Troubleshooting

This page covers the current Ecore-first implementation.

## GitHub Pages Shows the Wrong Page

GitHub Pages must use `GitHub Actions` as its source:

```text
Settings -> Pages -> Build and deployment -> Source -> GitHub Actions
```

The workflow builds VitePress directly into `_site`, so the public root is:

```text
https://plortinus.github.io/model2blockly/
```

The old custom `site/index.html` page is not copied to Pages anymore.

## Eclipse Cannot Install the Plugin

Use this update site:

```text
https://plortinus.github.io/model2blockly/update-site/
```

If Eclipse does not show the feature:

1. Confirm the GitHub Pages deployment completed.
2. Open the URL in a browser and check that the update-site page loads.
3. In Eclipse, disable `Group items by category` if the feature list is empty.
4. Retry after clearing any stale update-site entry with the same name.

## The Generate Command Does Not Appear

The context-menu command is visible for:

- `.ecore`
- `.m2b`
- `.model2blockly`

Select exactly one file in the workspace. The command is contributed by the UI
plugin through `plugin.xml` and handled by `GenerateBlocklyEditorHandler`.

## Ecore Generation Fails

Check the source file first:

- the selected file must contain an `EPackage`;
- referenced classifiers must resolve;
- annotation values such as `colour`, `width`, `height` and `order` should be
  numeric where expected;
- unsupported options should not use arbitrary top-level annotation keys.

The generation report is only written after the source model is transformed
and the intermediate `EditorSpec` validates successfully.

## Generated HTML Looks Stale

The generator now writes browser files under `html/`. It also removes stale
root-level HTML files that match generated `html/*` names. If the browser still
shows old content:

1. Re-run generation.
2. Open the file under the generated `html/` directory.
3. Hard-refresh the browser.
4. Check `generation_report.html` and the intermediate XMI timestamp.

## Validation Workspace Is Missing

The current generator should write:

```text
html/validation_workspace.html
html/validation_blocks.json
html/validation_runtime.js
```

Run:

```bash
npm run smoke
```

The smoke test will fail if the generated AppMaker editor cannot load its
sample model or validation output correctly.

## Domain XMI Export Fails Verification

Run:

```bash
npm run verify:domain-xmi
```

That check expects the AppMaker editor to export EMF-style XMI with XMI IDs and
without runtime-only block IDs. If it fails, inspect the XMI export logic in the
generated `Appmaker_standalone.html` and the source mapping in
`EcoreAdapter.java`.

## Documentation Build Fails

Run:

```bash
npm run build:site-docs
npm run verify:docs
```

The VitePress source now lives under `docs/`. Root-level Markdown files are not
used as VitePress source pages.
