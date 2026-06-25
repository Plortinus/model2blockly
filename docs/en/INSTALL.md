# Install the Eclipse Plugin

This page documents how to install Model2Blockly from the published Eclipse p2
update site. The update-site URL is a functional Eclipse repository endpoint,
not a separate documentation site.

## Update Site URL

Use this URL in Eclipse:

```text
https://plortinus.github.io/model2blockly/update-site/
```

The page at that URL also exposes the p2 metadata files:

- `content.jar`
- `artifacts.jar`
- `category.xml`

Eclipse reads those files directly when resolving installable features.

## Requirements

| Requirement | Why |
| --- | --- |
| Eclipse with JavaSE-21 support | The bundles require JavaSE-21. |
| Eclipse running on JDK 21 | The local launch configurations and generated plugin metadata target Java 21. |
| Dependency resolution enabled | Eclipse must be allowed to resolve Xtext and EMF dependencies from configured Eclipse update sites. |

Keep `Contact all update sites during install to find required software`
enabled in the install wizard unless you already have all dependencies in the
target Eclipse installation.

## Install Steps

1. Open Eclipse.
2. Select `Help` -> `Install New Software...`.
3. Click `Add...`.
4. Use a name such as `Model2Blockly`.
5. Paste the update-site URL:

   ```text
   https://plortinus.github.io/model2blockly/update-site/
   ```

6. Select the Model2Blockly feature.
7. Complete the installation wizard.
8. Restart Eclipse when prompted.

After installation, select an `.ecore`, `.m2b` or `.model2blockly` file in the
workspace and run `Generate Blockly Editor` from the context menu.

## If No Items Appear

If the install dialog shows no installable items:

1. Click `Reload`.
2. Clear `Group items by category`.
3. Confirm that the update-site URL ends with `/update-site/`.
4. Keep dependency resolution enabled.
5. Remove any stale Model2Blockly software-site entry from `Manage...`, add the
   site again and reload.

Browser directory listings are not required. It is normal for the endpoint to
look minimal in a browser because Eclipse reads `content.jar` and
`artifacts.jar` directly.

## Updating an Existing Installation

If Model2Blockly is already installed and a newer build is not visible:

1. In `Install New Software...`, clear `Hide items that are already installed`.
2. Open `Manage...` and remove stale Model2Blockly site entries.
3. Add the update-site URL again.
4. Click `Reload`.
5. Install the visible Model2Blockly feature or use `Help` -> `Check for Updates`.

Restart Eclipse after the update.

## Verify the Installation

Use a small Ecore source first:

1. Import the Model2Blockly projects or open a workspace with an `.ecore` file.
2. Select `io.github.plortinus.model2blockly/model/app_maker.ecore`.
3. Right-click and choose `Generate Blockly Editor`.
4. Open the generated `generation_report.html`.
5. Open `html/Appmaker_standalone.html` and click `Load Sample`.

The expected generated folder for the checked-in AppMaker case is:

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

## Related Pages

- [User guide](./USER_GUIDE.md)
- [Troubleshooting](./TROUBLESHOOTING.md)
- [Release checklist](./RELEASE_CHECKLIST.md)
