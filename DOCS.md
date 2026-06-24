# Documentation map

Languages: **English** | [Español](docs/es/README.md) | [中文](docs/zh/README.md)

Use this page to choose the right document for the task you are doing.

## Start here

| Goal | Read |
| --- | --- |
| Choose a documentation language | [`docs/README.md`](docs/README.md) |
| Understand what this repository does | [`README.md`](README.md) |
| Follow the complete AppMaker running example | [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md) |
| Generate your first editor | [`GETTING_STARTED.md`](GETTING_STARTED.md) |
| Try the generated AppMaker editor | [`GETTING_STARTED.md`](GETTING_STARTED.md#try-the-generated-examples-first) |
| Install the Eclipse plugin | [`README.md`](README.md#update-site) |
| Publish a new Eclipse plugin/update-site build | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) |
| Fix installation or generation issues | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| Check the textual DSL syntax | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| Check supported Ecore annotations | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| Understand the intermediate XMI pipeline | [`README.md`](README.md), then [`DSL_REFERENCE.md`](DSL_REFERENCE.md#generated-output) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow) |

## Common tasks

| Task | Best document | Why |
| --- | --- | --- |
| Generate an editor from `.model2blockly` | [`GETTING_STARTED.md`](GETTING_STARTED.md#generate-from-model2blockly), then [`DSL_REFERENCE.md`](DSL_REFERENCE.md#recommended-modeling-workflow) | The tutorial gives the Eclipse steps; the reference explains the model shape. |
| Generate an editor from `.ecore` | [`GETTING_STARTED.md`](GETTING_STARTED.md#generate-from-ecore), then [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#practical-workflow) | The tutorial gives the Eclipse steps; the reference explains annotations and Ecore cardinalities. |
| Decide between `.model2blockly` and `.ecore` | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#when-to-use-ecore-instead-of-model2blockly) | The Ecore reference lists the cases where Ecore has better coverage. |
| Find what a field becomes in Blockly | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#features) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#attribute-annotations) | These sections map project fields to Blockly fields and inputs. |
| Configure toolbox categories | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#categories-and-toolbox) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#class-annotations) | Category handling differs between the DSL and Ecore routes. |
| Configure workspace options | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#workspace-options) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#package-annotations) | Both routes pass options through to `Blockly.inject(...)`, but the syntax differs. |
| Add code generation templates | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#code-templates) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#sourcecode-on-eclass) | Templates use the same placeholders in both routes. |
| Add validation rules | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#constraints-and-validation) or [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md#sourcevalidation-on-eclass) | DSL supports a small direct syntax; Ecore supports annotations and a small OCL subset. |
| Inspect the formal generated model | [`GETTING_STARTED.md`](GETTING_STARTED.md#inspect-the-generated-output) | The `intermediate/*_blocklyspec.xmi` file is the EMF/XMI editor-spec model reloaded before HTML generation. |
| Inspect the user-created domain instance model | Open a generated `*_standalone.html`, click `Load Sample`, then `More -> Export XMI` | The exported `*_model.xmi` is the domain instance model produced by the generated Blockly editor. |
| Explain both DSL and Ecore routes in the thesis | [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md), then [`DSL_REFERENCE.md`](DSL_REFERENCE.md) and [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) | The running example keeps AppMaker consistent across the textual DSL, Ecore annotations, intermediate XMI, generated editor, and exported domain instance XMI. |
| Understand DSL validation and generation diagnostics | [`DSL_REFERENCE.md`](DSL_REFERENCE.md#diagnostics-and-generation-safety), then [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md#model2blockly-generation-fails) | The reference explains which problems are caught in the editor; troubleshooting explains what to do when generation stops. |
| Rebuild and publish the update site | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) | The release checklist lists version fields, rebuild commands, verification, GitHub Pages deployment, and user refresh steps. |
| Diagnose an empty install site or stale generated editor | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) | The troubleshooting guide is organized by symptom. |

## Tutorials and how-to guides

- [`GETTING_STARTED.md`](GETTING_STARTED.md) is the first-generation tutorial.
  It is best for new users who want to see output quickly.
- [`RUNNING_EXAMPLE.md`](RUNNING_EXAMPLE.md) is the end-to-end AppMaker example
  for explaining the proposal and collecting thesis screenshots.
- [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) is symptom-oriented. It is best
  when Eclipse install, generation, references, value inputs, or validation
  output does not match expectations.
- [`DSL_REFERENCE.md#diagnostics-and-generation-safety`](DSL_REFERENCE.md#diagnostics-and-generation-safety)
  explains how `.model2blockly` editor diagnostics, generation-time diagnostics,
  and generated runtime validation differ.
- [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) is the maintainer checklist
  for publishing a new p2 update site and telling users how to refresh Eclipse.

## Reference documents

- [`DSL_REFERENCE.md`](DSL_REFERENCE.md) is a reference for the textual
  `.model2blockly` language. It is best for users writing models by hand.
- [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) is a reference for annotated Ecore
  models. It is best for users who already have an Ecore metamodel or need EMF
  features such as IDs, `eOpposite`, Ecore cardinalities, or OCL-derived
  validation.

## Implementation References

When documentation and implementation differ, use the implementation as the
final reference:

- DSL grammar:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- DSL adapter:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java)
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
- DSL validation bridge:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/validation/Model2BlocklyValidator.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/validation/Model2BlocklyValidator.java)

## Documentation model

The documentation follows a task-first structure:

1. Read the README for the repository overview and install URL.
2. Use `GETTING_STARTED.md` to generate the first editor.
3. Use `TROUBLESHOOTING.md` when something does not behave as expected.
4. Use the route-specific reference only when authoring or debugging a model.
5. Use `generation_report.html` after each generation run to verify what the
   generator inferred.

Future tutorial material should stay separate from the reference pages so the
field/key tables remain easy to scan.
