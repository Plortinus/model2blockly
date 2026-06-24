# Documentation map

Use this page to choose the right document for the task you are doing.

## Start here

| Goal | Read |
| --- | --- |
| Browse the hosted documentation site | <https://plortinus.github.io/model2blockly/docs/en/> |
| Open localized documentation | [`docs/en/README.md`](docs/en/README.md), [`docs/es/README.md`](docs/es/README.md), or [`docs/zh/README.md`](docs/zh/README.md) |
| Use the plugin without implementation details | [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md) |
| Explain the architecture, diagrams and implementation | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) |
| Understand what this repository does | [`README.md`](README.md) |
| Follow the complete AppMaker running example | [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md) |
| Generate your first editor | [`GETTING_STARTED.md`](GETTING_STARTED.md) |
| Try the generated AppMaker editor | [`GETTING_STARTED.md`](GETTING_STARTED.md#try-the-generated-examples-first) |
| Install the Eclipse plugin | [`README.md`](README.md#update-site) |
| Publish a new Eclipse plugin/update-site build | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) |
| Fix installation or generation issues | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| Check supported Ecore annotations | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| Understand the intermediate XMI pipeline | [`README.md`](README.md), then [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow) |

## Common tasks

| Task | Best document | Why |
| --- | --- | --- |
| Generate an editor from `.ecore` | [`GETTING_STARTED.md`](GETTING_STARTED.md#generate-from-ecore), then [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow) | The tutorial gives the Eclipse steps; the reference explains annotations and Ecore cardinalities. |
| Find what a field becomes in Blockly | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#attribute-annotations) | This section maps Ecore attributes to Blockly fields and inputs. |
| Configure toolbox categories | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#class-annotations) | Categories are provided through Ecore annotations. |
| Configure workspace options | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#package-annotations) | Workspace options pass through to `Blockly.inject(...)`. |
| Add code generation templates | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#sourcecode-on-eclass) | Templates are stored in Ecore `code` annotations. |
| Add validation rules | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#sourcevalidation-on-eclass) | Ecore supports validation annotations and a small OCL subset. |
| Inspect or edit validation rules visually | [`GETTING_STARTED.md`](GETTING_STARTED.md#visual-walkthrough), then [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md#generated-result) | Generated editors include `validation_workspace.html` and `validation_blocks.json`; supported edits can be applied back with `Apply Validation Blocks to Source`. |
| Inspect the formal generated model | [`GETTING_STARTED.md`](GETTING_STARTED.md#understand-the-output-folder) | The `intermediate/*_blocklyspec.xmi` file is the EMF/XMI editor-spec model reloaded before HTML generation. |
| Inspect the user-created domain instance model | Open a generated `*_standalone.html`, click `Load Sample`, then `More -> Export XMI` | The exported `*_model.xmi` is the domain instance model produced by the generated Blockly editor. |
| Understand the system architecture | [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) | The architecture page includes the system diagram, generation flow diagram, output artifact diagram and code map. |
| Understand Ecore generation diagnostics | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow), then [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md#ecore-generation-fails) | The reference explains the mapping; troubleshooting explains what to do when generation stops. |
| Rebuild and publish the update site | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) | The release checklist lists version fields, rebuild commands, verification, GitHub Pages deployment, and user refresh steps. |
| Diagnose an empty install site or stale generated editor | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) | The troubleshooting guide is organized by symptom. |

## Tutorials and how-to guides

- [`GETTING_STARTED.md`](GETTING_STARTED.md) is the first-generation tutorial.
  It is best for new users who want to see output quickly.
- [`docs/en/USER_GUIDE.md`](docs/en/USER_GUIDE.md) is the user-facing entry
  page for installation, generation, screenshots and result inspection.
- [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md) is the end-to-end AppMaker example
  for checking the Ecore route, intermediate model, generated code and
  screenshots together.
- [`docs/en/ARCHITECTURE.md`](docs/en/ARCHITECTURE.md) is the implementation
  layer with architecture diagrams, generation flow and code responsibility map.
- [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) is symptom-oriented. It is best
  when Eclipse install, generation, references, value inputs, or validation
  output does not match expectations.
- [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) is the maintainer checklist
  for publishing a new p2 update site and telling users how to refresh Eclipse.

## Reference documents

- [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) is a reference for annotated Ecore
  models. It is best for users who already have an Ecore metamodel or need EMF
  features such as IDs, `eOpposite`, Ecore cardinalities, or OCL-derived
  validation.

## Implementation References

When documentation and implementation differ, use the implementation as the
final reference:

- Ecore adapter:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
- Generated intermediate EMF model:
  [`io.github.plortinus.model2blockly/model/generated/BlocklyEditorSpec.genmodel`](io.github.plortinus.model2blockly/model/generated/BlocklyEditorSpec.genmodel)
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
