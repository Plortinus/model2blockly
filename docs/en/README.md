---
layout: home
hero:
  name: Model2Blockly
  text: Generate Blockly editors from annotated Ecore
  tagline: An Eclipse plugin and standalone generation pipeline that turns an EMF Ecore metamodel into a browser-ready Blockly editor through an inspectable EditorSpec XMI model.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly converts annotated Ecore into an intermediate EditorSpec model and then into Blockly HTML output
  actions:
    - theme: brand
      text: Start with Ecore
      link: /en/user-guide
    - theme: alt
      text: AppMaker Case
      link: /en/running-example
    - theme: alt
      text: Architecture
      link: /en/architecture
features:
  - title: Ecore is the main input
    details: Select an Ecore file in Eclipse or run the Ecore standalone entry point. Optional annotations refine block labels, categories, widgets, validation and code templates.
    link: /en/ecore-reference
    linkText: Ecore annotation reference
  - title: EditorSpec is the generation contract
    details: The source EPackage is transformed into a generated EMF EditorSpec model, serialized as XMI, reloaded and validated before HTML generation.
    link: /en/architecture
    linkText: Read the architecture
  - title: Blockly output is static HTML
    details: Generation writes block definitions, toolbox files, code generators, a standalone editor, a validation workspace, a sample model and a generation report.
    link: /en/running-example
    linkText: Inspect AppMaker
  - title: Xtext is auxiliary
    details: m2b and model2blockly files are still supported by the Eclipse action and standalone path, but the documented project route is Ecore first.
    link: /en/architecture
    linkText: See the boundaries
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
is a model-driven pipeline:

```text
annotated .ecore
  -> EMF EPackage
  -> EcoreAdapter
  -> EditorSpec EMF model
  -> intermediate/*_blocklyspec.xmi
  -> BlocklyCodeGenerator
  -> static HTML/JavaScript editor
```

The AppMaker case in this repository is the reference path for that pipeline.
The generated output lives under
`io.github.plortinus.model2blockly/examples/generated/app_maker_ecore`.

The old custom landing page is no longer the public documentation homepage.
GitHub Pages now serves this VitePress site directly from the repository root.
