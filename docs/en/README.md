---
layout: home
hero:
  name: Model2Blockly
  text: Generate Blockly editors from Ecore or .m2b
  tagline: An Eclipse plugin and standalone generation pipeline that normalizes annotated Ecore metamodels and textual .m2b specifications into an inspectable EditorSpec XMI model and a browser-ready Blockly editor.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly converts Ecore or m2b into an intermediate EditorSpec model and then into Blockly HTML output
  actions:
    - theme: brand
      text: Start with a model
      link: /en/user-guide
    - theme: alt
      text: AppMaker Case
      link: /en/running-example
    - theme: alt
      text: Architecture
      link: /en/architecture
features:
  - title: Two input routes, one generator
    details: Start from an annotated Ecore metamodel or a concise .m2b textual specification. EcoreAdapter and DomainModelAdapter normalize both sources into the same EditorSpec contract.
    link: /en/user-guide
    linkText: Choose an input route
  - title: EditorSpec is the generation contract
    details: Each source model is transformed into a generated EMF EditorSpec, serialized as XMI, reloaded and validated before HTML generation.
    link: /en/architecture
    linkText: Read the architecture
  - title: Blockly output is static HTML
    details: Generation writes block definitions, toolbox files, code generators, a standalone editor, a validation workspace, a sample model and a generation report.
    link: /en/running-example
    linkText: Inspect AppMaker
  - title: Evaluated against existing editors
    details: Ten configurations from the official Blockly repositories separate the AppMaker example from external evidence and quantify structural parity, generator parity, specification size and current limits.
    link: /en/evaluation
    linkText: Read the evaluation
  - title: Ecore and .m2b are first-class inputs
    details: Use Ecore when an EMF metamodel already exists; use .m2b when a compact textual language definition is easier to maintain. The longer .model2blockly extension remains a legacy alias.
    link: /en/textual-dsl
    linkText: Read the textual DSL
  - title: Validation is generated
    details: Required fields, containment cardinality, references, uniqueness and a supported subset of expression/OCL constraints are converted into runtime checks and visual validation blocks.
    link: /en/user-guide
    linkText: Use the workflow
  - title: Plugin install remains available
    details: The GitHub Pages site is now the VitePress documentation site. The Eclipse update site is still published as a functional install endpoint.
    link: /en/install
    linkText: Install guide
---

## Current Project Shape

Model2Blockly is not a hand-written Blockly template library. The current code
implements a dual-input model-driven pipeline:

```text
annotated .ecore -> EPackage    -> EcoreAdapter -------\
                                                        -> EditorSpec EMF model
.m2b             -> DomainModel -> DomainModelAdapter --/
                                                        -> intermediate/*_blocklyspec.xmi
                                                        -> BlocklyCodeGenerator
                                                        -> static HTML/JavaScript editor
```

The AppMaker integration case provides both authoring styles. Its checked-in
outputs live under `examples/generated/app_maker_ecore` and
`examples/generated/app_maker_dsl` inside the core project.

GitHub Pages serves this VitePress documentation together with the two AppMaker
editors and the Eclipse p2 repository.
