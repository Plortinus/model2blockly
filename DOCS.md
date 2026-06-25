# Documentation map

Use this page to choose the right document for the task you are doing.

## Start here

| Goal | Read |
| --- | --- |
| Browse the hosted documentation site | <https://plortinus.github.io/model2blockly/> |
| Open the VitePress home source | [`docs/README.md`](docs/README.md) |
| Use the plugin without implementation details | [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md) |
| Explain the architecture, diagrams and implementation | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) |
| Understand what this repository does | [`README.md`](README.md) |
| Follow the complete AppMaker running example | [`docs/en/RUNNING_EXAMPLE.md`](docs/en/RUNNING_EXAMPLE.md) |
| Install the Eclipse plugin | [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md#install-the-eclipse-plugin) |
| Publish a new Eclipse plugin/update-site build | [`docs/en/RELEASE_CHECKLIST.md`](docs/en/RELEASE_CHECKLIST.md) |
| Fix installation or generation issues | [`docs/en/TROUBLESHOOTING.md`](docs/en/TROUBLESHOOTING.md) |
| Check supported Ecore annotations | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md) |
| Understand the intermediate XMI pipeline | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md#intermediate-model) |

## Common tasks

| Task | Best document | Why |
| --- | --- | --- |
| Generate an editor from `.ecore` | [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md#generate-from-ecore), then [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md) | The guide gives the Eclipse steps; the reference explains annotations and Ecore cardinalities. |
| Find what a field becomes in Blockly | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md#zero-annotation-inference) | This section maps Ecore attributes to Blockly fields and inputs. |
| Configure toolbox categories | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md#sourceblockly-on-eclass) | Categories are provided through Ecore annotations. |
| Configure workspace options | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md#sourceblockly-on-epackage) | Workspace options pass through to Blockly inject options. |
| Add code generation templates | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md#sourcecode) | Templates are stored in Ecore `code` annotations. |
| Add validation rules | [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md#sourcevalidation) | Ecore supports validation annotations and a small OCL subset. |
| Inspect or edit validation rules visually | [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md#use-the-generated-editor), then [`docs/en/RUNNING_EXAMPLE.md`](docs/en/RUNNING_EXAMPLE.md#what-to-inspect) | Generated editors include `validation_workspace.html` and `validation_blocks.json`; supported edits can be applied back with `Apply Validation Blocks to Source`. |
| Inspect the formal generated model | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md#intermediate-model) | The `intermediate/*_blocklyspec.xmi` file is the EMF/XMI editor-spec model reloaded before HTML generation. |
| Inspect the user-created domain instance model | Open a generated `*_standalone.html`, click `Load Sample`, then `More -> Export XMI` | The exported `*_model.xmi` is the domain instance model produced by the generated Blockly editor. |
| Understand the system architecture | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) | The architecture page includes the system diagram, generation flow diagram, output artifact diagram and code map. |
| Understand Ecore generation diagnostics | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow), then [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md#ecore-generation-fails) | The reference explains the mapping; troubleshooting explains what to do when generation stops. |
| Rebuild and publish the update site | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) | The release checklist lists version fields, rebuild commands, verification, GitHub Pages deployment, and user refresh steps. |
| Diagnose an empty install site or stale generated editor | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) | The troubleshooting guide is organized by symptom. |

## Tutorials and how-to guides

- [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md) is the user-facing entry
  page for installation, generation, screenshots and result inspection.
- [`docs/en/RUNNING_EXAMPLE.md`](docs/en/RUNNING_EXAMPLE.md) is the end-to-end AppMaker example
  for checking the Ecore route, intermediate model, generated code and
  screenshots together.
- [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) is the implementation
  layer with architecture diagrams, generation flow and code responsibility map.
- [`docs/en/TROUBLESHOOTING.md`](docs/en/TROUBLESHOOTING.md) is symptom-oriented. It is best
  when Eclipse install, generation, references, value inputs, or validation
  output does not match expectations.
- [`docs/en/RELEASE_CHECKLIST.md`](docs/en/RELEASE_CHECKLIST.md) is the maintainer checklist
  for publishing a new p2 update site and telling users how to refresh Eclipse.

## Reference documents

- [`docs/en/ECORE_REFERENCE.md`](docs/en/ECORE_REFERENCE.md) is a reference for annotated Ecore
  models. It is best for users who already have an Ecore metamodel or need EMF
  features such as IDs, `eOpposite`, Ecore cardinalities, or OCL-derived
  validation.

## Implementation References

When documentation and implementation differ, use the implementation as the
final reference:

- Ecore adapter:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
- Generated intermediate EMF model:
  [`io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel`](io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel)
- Blockly generator:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend)
- Intermediate model validator:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/blocklyspec/BlocklyEditorSpecValidator.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/blocklyspec/BlocklyEditorSpecValidator.java)
- Intermediate XMI serializer:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java)

## Documentation model

The documentation follows a task-first structure. The Markdown files remain the
source of truth in GitHub, and `scripts/build-site-docs.mjs` renders them into a
VitePress site with fixed navigation, sidebars, language switching and local
search for GitHub Pages.

1. Read the README for the repository overview and install URL.
2. Use `GETTING_STARTED.md` to generate the first editor.
3. Use `TROUBLESHOOTING.md` when something does not behave as expected.
4. Use the Ecore reference only when authoring or debugging a model.
5. Use `generation_report.html` after each generation run to verify what the
   generator inferred.

Future tutorial material should stay separate from the reference pages so the
field/key tables remain easy to scan.
