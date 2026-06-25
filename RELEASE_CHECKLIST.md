# Release Checklist

Use this checklist when publishing a new Model2Blockly Eclipse plugin/update-site
build.

## Release Inputs

Before rebuilding the update site, decide the release version. The current
version is stored in these files:

| File | Field |
| --- | --- |
| `io.github.plortinus.model2blockly/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ide/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.feature/feature.xml` | `feature/@version` |
| `io.github.plortinus.model2blockly.updatesite/category.xml` | `feature/@version` |
| `scripts/verify-eclipse-plugin.mjs` | `pluginVersion` |

Keep these values aligned. If the version does not change, Eclipse may hide the
update when `Hide items that are already installed` is selected.

## Local Build And Update Site

1. Compile the core, IDE, and UI bundles with Java 21.
2. Ensure the compiled `bin/` output and source files are included in the
   versioned plugin jars under:

```text
io.github.plortinus.model2blockly.updatesite/repository/plugins/
```

3. Rebuild the local p2 metadata:

```bash
npm run rebuild:update-site
```

This runs the Eclipse p2 `FeaturesAndBundlesPublisher`, then reapplies
`category.xml` metadata so Eclipse shows a visible `Model2Blockly` category.

If Eclipse is not installed at the default macOS path, pass the executable:

```bash
ECLIPSE=/path/to/eclipse npm run rebuild:update-site
```

## Required Verification

Run these before publishing:

```bash
npm run verify:plugin
npm run verify:docs
npm run verify:domain-xmi
npm run verify:patch
npm run smoke
```

If Eclipse is not installed at the default macOS path, set the plugin and Java
paths before the validation checks:

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
```

What these checks cover:

| Command | Purpose |
| --- | --- |
| `verify:plugin` | Bundle metadata, feature/update-site contents, generated examples, packaged classes, and packaged XMI-pipeline sources |
| `verify:docs` | Trilingual documentation file presence, language switches, and relative links |
| `verify:domain-xmi` | EMF load/validate check for the domain XMI exported by the Ecore-generated editor |
| `verify:patch` | Validation Blockly-to-source patch dry-run/apply/regeneration flow |
| `smoke` | Browser-level generated editor sanity checks for generated examples |

Do not publish if any command fails.

## GitHub Pages Publish

The hosted update site is published by `.github/workflows/pages.yml`.

On push to `main`, the workflow:

1. builds the VitePress documentation directly into `_site/`;
2. copies generated AppMaker editor assets to `_site/app_maker_ecore/`;
3. copies the committed `io.github.plortinus.model2blockly.updatesite/repository/` to
   `_site/update-site/`;
4. copies `category.xml` and `site/update-site/index.html`;
5. deploys the result to GitHub Pages.

The workflow does not rebuild p2 metadata because the Eclipse p2 publisher is a
local release dependency. Run `npm run rebuild:update-site` and the verification
commands before pushing.

The public Eclipse update-site URL is:

```text
https://plortinus.github.io/model2blockly/update-site/
```

After pushing, wait for the `Publish GitHub Pages` workflow to complete, then
check:

```text
https://plortinus.github.io/model2blockly/
https://plortinus.github.io/model2blockly/update-site/
https://plortinus.github.io/model2blockly/update-site/content.jar
https://plortinus.github.io/model2blockly/update-site/artifacts.jar
```

## User Update Instructions

When users already have Model2Blockly installed and need the new build:

1. Open `Help -> Install New Software...`.
2. Select or add:

```text
https://plortinus.github.io/model2blockly/update-site/
```

3. Clear `Hide items that are already installed`.
4. If no items appear, clear `Group items by category`.
5. Click `Manage...`, remove stale Model2Blockly entries, add the site again, and
   reload.
6. Keep `Contact all update sites during install to find required software`
   enabled.
7. Install the visible Model2Blockly feature and restart Eclipse.

For local testing, use:

```text
Help -> Install New Software... -> Add... -> Local...
```

and choose:

```text
io.github.plortinus.model2blockly.updatesite/repository/
```

Do not choose the parent `io.github.plortinus.model2blockly.updatesite/` project folder.

## Post-Release Smoke Check

After installing from the hosted update site in a clean Eclipse workspace:

1. Import or create a project containing an annotated `.ecore` file.
2. Run `Generate Blockly Editor`.
3. Confirm `generation_report.html` opens.
4. Confirm the output contains `intermediate/*_blocklyspec.xmi`.
5. Generate from `app_maker.ecore` and confirm the output editor opens.
