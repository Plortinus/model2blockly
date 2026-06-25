# Architecture

Model2Blockly is organized as a dual-input MDE toolchain. Annotated Ecore
metamodels and `.m2b` textual DSL models are both transformed into the same
EMF `EditorSpec` intermediate model, then the Xtend generator produces static
Blockly output.

## Runtime Flow

```text
annotated .ecore                    .m2b textual DSL
  -> EPackage                         -> DomainModel
  -> EcoreAdapter                     -> DomainModelAdapter
          \                           /
           \                         /
            -> EditorSpec EMF model
            -> BlocklySpecXmiSerializer
            -> intermediate/*_blocklyspec.xmi
            -> XMI reload and diagnostics
            -> BlocklyCodeGenerator
            -> HTML/JavaScript artifacts
```

![Model2Blockly generation flow](../assets/diagrams/generation-flow.svg)

## Modules

| Module | Role |
| --- | --- |
| `io.github.plortinus.model2blockly` | Core Xtext/EMF plugin, adapters, intermediate model, generators and standalone entry points. |
| `io.github.plortinus.model2blockly.ui` | Eclipse UI contributions: editor integration, context menu commands and validation patch command. |
| `io.github.plortinus.model2blockly.ide` | Xtext IDE services for the auxiliary textual syntax. |
| `io.github.plortinus.model2blockly.feature` | Eclipse feature packaging. |
| `io.github.plortinus.model2blockly.updatesite` | Published Eclipse update-site repository. |
| `docs` | VitePress documentation source. |
| `scripts` | Node-based verification and site/update-site build helpers. |

## Generated and Handwritten Code

The repository deliberately separates generated code by source:

| Directory | Contents |
| --- | --- |
| `src` | Handwritten Java and Xtend implementation. |
| `src-gen` | Xtext-generated parser, grammar access, setup and validation infrastructure. |
| `emf-gen` | EMF-generated Java APIs for `Model2Blockly.ecore` and `BlocklyEditorSpec`. |
| `xtend-gen` | Java generated from Xtend sources. |

This matters because regenerating Xtext infrastructure should not delete the
EMF APIs used by the intermediate model.

## Core Implementation Map

| Responsibility | Main file |
| --- | --- |
| Load `.ecore` and run the Ecore route | `standalone/EcoreToBlocklyMain.java` |
| Convert `EPackage` to `EditorSpec` | `adapter/EcoreAdapter.java` |
| Convert auxiliary Xtext model to `EditorSpec` | `adapter/DomainModelAdapter.java` |
| Serialize/reload intermediate XMI | `intermediate/BlocklySpecXmiSerializer.java` |
| Validate intermediate model | `blocklyspec/BlocklyEditorSpecValidator.java` and `blocklyspec/BlocklySpecDiagnostics.java` |
| Generate Blockly files | `generator/BlocklyCodeGenerator.xtend` |
| Generate sample model | `generator/SampleModelGenerator.java` |
| Generate validation blocks/runtime | `generator/ValidationBlockModelGenerator.java`, `ValidationRuntimeGenerator.java`, `ValidationWorkspaceHtmlGenerator.java` |
| Render mapping report | `generator/GenerationReportHtmlRenderer.java` |
| Eclipse context-menu generation | `ui/handlers/GenerateBlocklyEditorHandler.java` |
| Apply edited validation blocks back to source | `ui/handlers/ApplyValidationPatchHandler.java` and `validationpatch/ValidationSourcePatcher.java` |

## Intermediate Model

`EditorSpec` is the contract between source-model analysis and Blockly output.
Both supported input routes produce this model:

```text
Ecore route:
  EPackage -> EcoreAdapter -> EditorSpec

Auxiliary textual route:
  DomainModel -> DomainModelAdapter -> EditorSpec
```

The Ecore route writes `intermediate/*_blocklyspec.xmi`, reloads it and
validates it before generating HTML. That XMI is not just a debug dump; it is
part of the generation contract.

![Model2Blockly system architecture](../assets/diagrams/system-architecture.svg)

## Ecore Mapping

The Ecore adapter infers useful Blockly structure without annotations:

| Ecore source | Generated meaning |
| --- | --- |
| `EClass` | Blockly block type. |
| Abstract `EClass` or interface | Abstract block type, not emitted as a concrete block. |
| `EAttribute` | Blockly field. |
| `EEnum` attribute | Dropdown field. |
| Containment `EReference` | Statement input and containment validation. |
| Non-containment `EReference` | Dynamic reference field. |
| `lowerBound >= 1` | Required-field or required-reference validation. |
| Multi-valued unique feature | Duplicate-value validation. |
| `iD` attribute / `eIDAttribute` | Default identity field for references and XMI export. |

Annotations refine that inferred result. The supported keys are documented in
the [Ecore to Blockly mapping](./ECORE_TO_BLOCKLY_MAPPING.md).

## Output Artifacts

![Generated output artifacts](../assets/diagrams/output-artifacts.svg)

The generator produces static files. There is no runtime server requirement for
the generated editor itself. The checked-in AppMaker output is used by the
Playwright smoke test and by the domain-XMI verification script.

## GitHub Pages Structure

GitHub Pages now publishes VitePress as the site root:

```text
https://plortinus.github.io/model2blockly/
```

The workflow still copies functional artifacts after the VitePress build:

| Path | Purpose |
| --- | --- |
| `/update-site/` | Eclipse update-site endpoint used by plugin installation. |
| `/app_maker_ecore/` | AppMaker editor generated from annotated Ecore. |
| `/app_maker_dsl/` | AppMaker editor generated from the `.m2b` textual DSL. |

Those paths are not separate documentation systems; they are generated/runtime
artifacts exposed next to the VitePress docs.
