---
layout: home
hero:
  name: Model2Blockly
  text: Eclipse plugin for Blockly editors
  tagline: Install Model2Blockly in Eclipse, select an annotated `.ecore` metamodel, and generate a browser-ready Blockly DSL editor.
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: The Model2Blockly Eclipse plugin converts Ecore metamodels into Blockly editors
  actions:
    - theme: brand
      text: Get Started
      link: /en/user-guide
    - theme: alt
      text: AppMaker Example
      link: /en/running-example
    - theme: alt
      text: Install Plugin
      link: https://plortinus.github.io/model2blockly/update-site/
features:
  - title: Generate from Ecore
    details: Reuse an existing `.ecore` metamodel and add annotations for Blockly labels, colors, categories, fields and validations.
    link: /en/ecore-reference
    linkText: Ecore support
  - title: Use EMF intermediate models
    details: Transform the source EPackage into a generated EditorSpec model, persist it as XMI, reload it, and generate from that model.
    link: /en/architecture
    linkText: Architecture
  - title: Produce runnable Blockly pages
    details: Generate toolbox, workspace, block definitions, code generators and standalone HTML pages that open directly in a browser.
    link: /en/running-example
    linkText: AppMaker example
  - title: Visualize validation rules
    details: Create a validation workspace where required fields, cardinality, order, references and simple expressions appear as Blockly blocks.
    link: /en/user-guide
    linkText: User workflow
  - title: Export JSON, XMI and code
    details: Save and load workspaces, then export user-created models as JSON, EMF-checkable domain XMI for the Ecore route, or template-generated code.
    link: /en/user-guide
    linkText: Export workflow
  - title: Inspect reports and XMI
    details: Every generation writes a report and a BlocklyEditorSpec XMI file so the input-to-editor mapping remains inspectable.
    link: /en/architecture
    linkText: Generation flow
---
