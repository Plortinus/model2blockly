# Model2Blockly Language Reference

Languages: **English** | [Español](docs/es/DSL_REFERENCE.md) | [中文](docs/zh/DSL_REFERENCE.md)

Model2Blockly is a textual language for defining domain models that generate
Blockly editors. The DSL intentionally hides most raw Blockly JSON, but the
generated editor is still a Blockly application. Use this document for
Model2Blockly syntax and project-specific mappings, and use the official Blockly
docs when you need the exact behavior of Blockly fields, inputs, connections,
toolboxes, workspace options, or code generators.

Implementation references:

- Grammar: [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- Main example: [`io.github.plortinus.model2blockly/examples/app_maker.model2blockly`](io.github.plortinus.model2blockly/examples/app_maker.model2blockly)
- Generator mapping: [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend)

## How to use this reference

- If you are new to the project, start with [`DOCS.md`](DOCS.md) and
  [`README.md`](README.md) before reading this page end to end.
- If you are writing a `.model2blockly` model, start with
  [Minimal Example](#minimal-example), then use [Features](#features) as a
  field/input lookup.
- If you need exact Blockly behavior, follow the official Blockly links in the
  relevant section.
- If you are annotating `.ecore`, use [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md)
  instead.

## Official Blockly Docs

These Blockly docs are the useful companion references for fields that map
directly to Blockly concepts:

- [Block structure in JSON](https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/) for `message0`, `args0`, fields, inputs, and block connections.
- [Fields overview](https://docs.blockly.com/guides/create-custom-blocks/fields/overview/) and [built-in fields](https://docs.blockly.com/guides/create-custom-blocks/fields/built-in-fields/overview/) for text, number, checkbox, dropdown, colour, angle, label, and image fields.
- [Connection checks](https://docs.blockly.com/guides/create-custom-blocks/inputs/connection-checks/) for `check`, `previousStatement`, `nextStatement`, and `output` compatibility.
- [Category toolboxes](https://docs.blockly.com/guides/configure/toolboxes/category/) and [nested categories](https://docs.blockly.com/guides/configure/toolboxes/nested/) for generated toolbox JSON.
- [Create a workspace](https://docs.blockly.com/guides/configure/configuration_struct/), [grid](https://docs.blockly.com/guides/configure/grid/), and [zoom](https://docs.blockly.com/guides/configure/zoom/) for `workspace { ... }` options.
- [Code generation overview](https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/) and [generate/run JavaScript](https://docs.blockly.com/guides/app-integration/running-javascript/) for `workspaceToCode` and block-code generator behavior.

## Generated Output

Running `Generate Blockly Editor` on a `.model2blockly` file produces a directory
containing Blockly JavaScript, HTML, validation assets, an intermediate model,
and a generation report.

Typical files:

```text
html/<Domain>_blocks.js
html/<Domain>_toolbox.js
html/<Domain>_generators.js
html/<Domain>_validations.js
html/<Domain>_editor.html
html/<Domain>_standalone.html
html/validation_workspace.html
html/validation_blocks.json
html/validation_runtime.js
html/sample_model.json
generation_report.html
intermediate/<Domain>_blocklyspec.xmi
```

The `.model2blockly` file is transformed into a generated EMF `EditorSpec`
instance that conforms to the `BlocklyEditorSpec` intermediate metamodel,
serialized to `intermediate/<Domain>_blocklyspec.xmi`, reloaded from that XMI,
validated again, and then used to emit Blockly JSON/JavaScript. Treat the XMI as
the formal intermediate artifact for comparing source models with generated
editor output.

## Minimal Example

```model2blockly
domain TodoApp codeLanguage "javascript" codeFileExtension "js"

category Tasks label "Tasks" colour 210

class App category Tasks colour 260 label "App" code "app(\"{{name}}\", [
{{statements:tasks}}
]);" {
  attribute name : string default "My Todo App" required
    widget text uiLabel "App name" group "App" order 1

  contains Task tasks [0..50]
    uiLabel "Tasks" group "Tasks" order 2
}

class Task category Tasks colour 210 label "Task" inline code "task(\"{{title}}\", \"{{priority}}\");" {
  attribute title : string default "Buy milk" required
    widget text uiLabel "Title" group "Task" order 1

  attribute priority : enum { low = "Low", medium = "Medium", high = "High" } default "medium"
    widget select uiLabel "Priority" group "Task" order 2
}
```

This produces two block types, a category toolbox named `Tasks`, a statement
input named `tasks` on `App`, fields for `name`, `title`, and `priority`, plus
domain code generation templates.

## File Structure

A `.model2blockly` file starts with one `domain`, then optional workspace options,
categories, classes, and constraints.

```text
domain <Name>
  [codeLanguage "..."]
  [codeFileExtension "..."]
  [runtimeKind "..."]

[workspace { ... }]

category ...
category ...

class ...
class ...

constraint ...
validation ...
```

Top-level declarations are order-sensitive only where they reference names
that must exist in the parsed model.

## Mapping Overview

| DSL concept | Intermediate model | Generated Blockly/runtime artifact |
| --- | --- | --- |
| `domain` | `EditorSpec.domainName` | output file prefix, editor title, domain code metadata |
| `codeLanguage` | `EditorSpec.codeLanguage` | `BLOCKLY_DOMAIN_CODEGEN.language` |
| `codeFileExtension` | `EditorSpec.codeFileExtension` | generated code download extension |
| `runtimeKind` | `EditorSpec.runtimeKind` | runtime preset metadata used by generated tooling |
| `workspace` | `workspaceOptions` map | options passed to `Blockly.inject(...)` |
| `category` | `CategorySpec` | toolbox `kind: "category"` entries |
| concrete `class` | `BlockTypeSpec` | JSON block definition in `html/<Domain>_blocks.js` |
| `abstract class` | abstract `BlockTypeSpec` | connection type parent; not emitted as a toolbox block |
| `output class` | output `BlockTypeSpec` | expression block with `output` connection |
| `attribute` | `FieldSpec` | Blockly field, usually inside `args0` |
| `contains` | `StatementInputSpec` | `input_statement` with optional `check` |
| `reference` | `ReferenceFieldSpec` | dynamic dropdown or custom reference multiselect field |
| `value` | `ValueInputSpec` | `input_value` with optional `check` and `shadow` |
| `constraint` | `ValidationSpec` | validation JavaScript and visual validation block |
| `validation` | `ValidationSpec` | expression/OCL-derived validation JavaScript and visual validation block |
| `code` | block code template | domain-specific code generator output |

## Domain

```model2blockly
domain AppMaker codeLanguage "javascript" codeFileExtension "js" runtimeKind "appMaker"
```

Fields:

| Field | Meaning |
| --- | --- |
| `name` | Domain/editor name and generated file prefix. |
| `codeLanguage` | Metadata for the generated domain code. The current generator still emits JavaScript glue for Blockly itself. |
| `codeFileExtension` | File extension used when downloading generated domain code. |
| `runtimeKind` | Optional runtime preset marker, equivalent to Ecore `source="runtime" kind="..."`. |

## Categories And Toolbox

Categories organize the Blockly toolbox.

```model2blockly
category Pages label "Pages" colour 260
category Components label "Components" colour 160

category UI label "UI" colour 180 {
  category Inputs label "Inputs" colour 190
}
```

Mapping:

| DSL field | Generated toolbox field |
| --- | --- |
| `category <Name>` | internal category id used by classes |
| `label "..."` | toolbox category `name`; defaults to the category id |
| `colour <int>` | toolbox category `colour` |
| nested `category` | nested toolbox category |

If no class has an explicit category and no categories are declared, the
generator creates a flyout toolbox. Otherwise it creates a category toolbox.
Generated category toolboxes also include built-in Logic, Loops, Math,
Variables, and Procedures categories.

See Blockly's [category toolbox](https://docs.blockly.com/guides/configure/toolboxes/category/)
and [nested categories](https://docs.blockly.com/guides/configure/toolboxes/nested/)
docs for the raw toolbox JSON shape.

## Classes And Blocks

Each non-abstract class becomes a Blockly block.

```model2blockly
class Button extends Component category Components colour 160 label "Button" inline
  tooltip "Clickable button"
  code "button(\"{{labelText}}\", () => {
{{statements:onClick}}
});" {
  ...
}
```

Class modifiers:

| Modifier | Meaning |
| --- | --- |
| `abstract class` | Defines a shared parent type. It does not appear as a normal toolbox block. |
| `output class` | Creates an expression-style block with an output connection. |
| `output as <Class> class` | Creates an output block with an explicit Blockly output check. |
| `abstract output class` | Defines an abstract expression parent type. |

Class options:

| DSL option | Generated effect |
| --- | --- |
| `extends <Class>` | Sets the parent type used for typed statement/output connections. |
| `category <Category>` | Places the concrete block in that toolbox category. |
| `colour <int>` | Emits block JSON `colour`; defaults to category colour or `200`. |
| `label "..."` | Used as the visible block label and default tooltip. |
| `message0 "..."` | Overrides generated Blockly block text layout. |
| `tooltip "..."` | Emits block JSON `tooltip`. |
| `helpUrl "..."` | Emits block JSON `helpUrl`. |
| `inputsInline true/false` | Explicitly emits Blockly `inputsInline`. |
| `inline` | Emits block JSON `inputsInline: true`. |
| `code "..."` | Defines the domain code template for this block. |

If `message0` is omitted, the generator builds Blockly `message0` and `args0`
from the class label plus ordered features.

## Block Connections

Model2Blockly chooses top-level block connections from class shape and
inheritance.

| DSL shape | Generated Blockly connection |
| --- | --- |
| `output class X` with no parent | `output: null` |
| `output class X extends Expr` | `output: "Expr"` |
| concrete class with `extends Component` | `previousStatement: "Component"` and `nextStatement: "Component"` |
| class used as a contained type | typed statement connection using that class name |
| root-like class with containments and not itself contained | no top-level previous/next connection |
| otherwise | free previous/next statement connections |

`contains` and `value` inputs also emit Blockly connection checks. Connection
checks restrict immediate compatible connections; they do not express all model
rules by themselves. Cardinality and `required` are handled by the generated
validation runtime.

## Features

Features inside a class define block fields and connections.

### Attribute

Attributes become Blockly fields.

```model2blockly
attribute title : string default "Home" required
  widget text uiLabel "Title" group "Page" order 1

attribute rows : int default "4" min "2" max "12"
  widget number uiLabel "Rows"

attribute method : enum { get = "GET", post = "POST" } default "get"
  widget select uiLabel "Method"
```

Supported simple types:

```text
string
int
boolean
float
colour
angle
image
label
```

Enum syntax:

```model2blockly
enum { internalName = "Display label", otherName = "Other label" }
```

Current Blockly field mapping:

| DSL attribute | Generated Blockly field |
| --- | --- |
| `string` | `field_input` with `text` from `default` |
| `int` | `field_number` with `precision: 1`, optional `min` and `max` |
| `float` | `field_number` with `precision: 0.1`, optional `min` and `max` |
| `boolean` | `field_checkbox`; `default "true"` becomes checked |
| `enum { ... }` | `field_dropdown` with `options` from enum labels and names |
| `colour` | `field_colour`; defaults to `#ff0000` if no default is set |
| `angle` | `field_angle`; defaults to `90` if no default is set |
| `image` | `field_image`; use `src`, `width`, `height`, and `alt` metadata |
| `label` | `field_label` |

Attribute cardinality and model constraints:

```model2blockly
attribute id : string [1..1] required modelId unique
attribute tags : string [0..0] unique unordered
attribute icon : image default "icon.png" width 24 height 24 alt "Icon"
```

| DSL part | Generated effect |
| --- | --- |
| `[lower..upper]` | Field cardinality; `0` upper bound means unbounded |
| `required` | Required field validation; also inferred from lower bound `>= 1` |
| `modelId` | Type-wide ID field plus uniqueness validation |
| `unique` | Uniqueness validation for multi-valued attributes |
| `nonUnique` | Allows duplicate values for multi-valued attributes |
| `ordered` / `unordered` | Preserved in the intermediate model |
| `src`, `width`, `height`, `alt` | Blockly `field_image` metadata |

Use Blockly's built-in field docs for the exact editor behavior:
[text input](https://docs.blockly.com/guides/create-custom-blocks/fields/built-in-fields/text-input/),
[number](https://docs.blockly.com/guides/create-custom-blocks/fields/built-in-fields/number/),
[checkbox](https://docs.blockly.com/guides/create-custom-blocks/fields/built-in-fields/checkbox/),
and [dropdown](https://docs.blockly.com/guides/create-custom-blocks/fields/built-in-fields/dropdown/).

### Containment

Containments become statement inputs for nested blocks.

```model2blockly
contains Component components [1..40]
  uiLabel "Components" group "Layout" order 3
```

Mapping:

| DSL part | Generated effect |
| --- | --- |
| `contains Component components` | `input_statement` named `components` |
| target type `Component` | Blockly `check: "Component"` |
| `[1..40]` | generated cardinality validation |

The statement input accepts blocks whose connection check matches the target
type. For inheritance hierarchies, concrete subclasses use the root ancestor
name as their statement connection check.

### Reference

References point at existing blocks in the same workspace.

```model2blockly
reference Page target [1..1] required
  widget reference-select uiLabel "Target page" referenceLabelField title
```

Mapping:

| DSL part | Generated effect |
| --- | --- |
| `reference Page target` | reference field named `target` |
| `[lower..upper]` | Reference cardinality; `0` upper bound means unbounded multiselect |
| `required` | generated required-field validation; also inferred from lower bound `>= 1` |
| `unique` / `nonUnique` | controls uniqueness validation for multi-valued references |
| `ordered` / `unordered` | preserved in the intermediate model |
| `opposite <name>` | generated runtime synchronization rule for opposite references |
| `referenceLabelField title` | uses the target block's `title` field as display text where supported |

Single-valued references are generated as dynamic dropdowns. Multi-valued
references are generated as a custom `field_reference_multiselect`.

### Value Input

Value inputs create expression sockets.

```model2blockly
value TextExpression content shadow TextLiteral
  uiLabel "Text" group "Text" order 1
```

Mapping:

| DSL part | Generated effect |
| --- | --- |
| `value TextExpression content` | `input_value` named `content` |
| target type `TextExpression` | Blockly `check: "TextExpression"` |
| `shadow TextLiteral` | toolbox entry preloads a `TextLiteral` shadow block for that input |

Use `output class` for blocks that should connect into `value` inputs.

## UI Metadata

UI options can be attached to attributes, containments, references, and value
inputs.

```model2blockly
widget text
uiLabel "Title"
help "Shown in generated reports and UI metadata"
placeholder "Enter title"
group "Page"
order 1
readonly
hidden
variant compact
referenceLabelField name
```

Accepted widgets:

```text
text
textarea
number
slider
switch
checkbox
select
radio
color
angle
image
reference-select
slot
expression-slot
```

Important current behavior:

- Blockly field type is selected primarily from the attribute type, not from
  `widget`.
- `textarea`, `slider`, `switch`, and `radio` are treated as UI intent metadata.
  The generated Blockly field is still `field_input`, `field_number`,
  `field_checkbox`, or `field_dropdown` depending on the attribute type.
- The validator warns when a widget is inconsistent with the feature type, for
  example `slider` on a non-number attribute or `reference-select` on an
  attribute.
- `group`, `order`, `help`, `placeholder`, `readonly`, `hidden`, and `variant`
  are retained in the intermediate model and reports, but the current Blockly
  block JSON generator does not use all of them as direct Blockly UI controls.
- `image` is both a UI widget hint and a dedicated attribute type. Use the
  `image` attribute type when you need Blockly `field_image`.

Supported variants:

```text
default
compact
prominent
```

## Code Templates

`code` templates describe how a block emits domain source snippets.

```model2blockly
class Button label "Button" code "button(\"{{labelText}}\", () => {
{{statements:onClick}}
});" {
  attribute labelText : string default "Save"
  contains Action onClick [0..10]
}
```

Template placeholders:

| Placeholder | Meaning |
| --- | --- |
| `{{fieldName}}` | Insert an attribute or reference field value from `block.getFieldValue(...)`. |
| `{{value:inputName}}` | Insert generated code for the block connected to a value input. |
| `{{statement:inputName}}` | Insert generated code for blocks connected to a statement input. |
| `{{statements:inputName}}` | Alias for statement input code. |
| `{{children:inputName}}` | Alias for statement input code. |
| `{{type}}` | Insert the current Blockly block type. |

If a class has no `code` template, the generator falls back to a simple textual
representation using the block type and available fields/inputs.

The generated editor also installs JavaScript block-code generators so Blockly
can call `javascriptGenerator.workspaceToCode(workspace)`. See Blockly's
[code generation overview](https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/)
for the underlying model.

## Workspace Options

Workspace options are a thin wrapper over Blockly configuration options.

```model2blockly
workspace {
  trashcan: true
  grid: {
    spacing: 20
    length: 3
    colour: "#cccccc"
    snap: true
  }
  zoom: {
    controls: true
    wheel: true
    startScale: 1
    maxScale: 3
    minScale: 0
  }
}
```

Option values can be strings, integers, booleans, or nested objects. The
generator merges your options over these defaults:

```javascript
{
  trashcan: true,
  zoom: {
    controls: true,
    wheel: true,
    startScale: 1.0,
    maxScale: 3,
    minScale: 0.3
  },
  move: {
    scrollbars: true,
    drag: true,
    wheel: true
  }
}
```

`grid` is disabled by default and is emitted only if you configure it. Use
Blockly's workspace docs for exact option names and semantics:
[configuration options](https://docs.blockly.com/guides/configure/configuration_struct/),
[grid](https://docs.blockly.com/guides/configure/grid/), and
[zoom](https://docs.blockly.com/guides/configure/zoom/).

## Diagnostics And Generation Safety

There are three different validation layers. They happen at different times and
they answer different questions.

| Layer | Where it appears | What it checks |
| --- | --- | --- |
| DSL syntax/linking validation | `.model2blockly` editor and command-line load step | Grammar errors and unresolved references such as unknown classes or categories |
| Intermediate model validation | `.model2blockly` editor, Eclipse problem markers, XMI reload, and command-line generation | Generator-facing invariants in the generated EMF `EditorSpec` |
| Generated runtime validation | Generated editor and `html/validation_workspace.html` | Domain rules for models users build with the generated Blockly editor |

The intermediate model validation layer catches problems that are syntactically
valid DSL but cannot produce a correct Blockly editor. Typical examples:

```text
Shadow block type 'Statement' is not an output block. [block[Host].expr]
Duplicate feature/input name 'title'. [block[Card].title]
Reference label field 'displayName' does not exist on target type 'User'. [block[Task].assignee]
```

In Eclipse, these become problem markers on the closest source element. In the
command-line generator, they are printed before generation starts. The bracketed
location, such as `block[Task].assignee`, is the intermediate model path. It is
useful when the DSL source and generated Blockly output need to be compared.

Generated runtime validation is different: it is code emitted into the generated
Blockly editor. It checks user-created block workspaces, not the `.model2blockly`
file itself.

## Constraints And Validation

The `.model2blockly` route generates validation rules from a small set of
language constructs. These validations run in the generated editor runtime and
are also visible in `html/validation_workspace.html`.

### Required Attributes

Use `required` on an attribute when the generated editor should warn if the
field is empty.

```model2blockly
class Page category Pages label "Page" {
  attribute title : string default "Home" required
    widget text uiLabel "Title"
}
```

Generated validation:

```text
Page.title is required
```

### Required References

Use `required` on a reference when the generated editor should warn if no
target block is selected.

```model2blockly
class Navigate category Actions label "Navigate" {
  reference Page target required
    widget reference-select uiLabel "Target page" referenceLabelField title
}
```

Generated validation:

```text
Navigate.target is required
```

### Containment Cardinality

Use `[lower..upper]` on `contains` to constrain the number of child statement
blocks.

```model2blockly
class App category Pages label "App" {
  contains Page pages [1..20]
    uiLabel "Pages"
}
```

Generated validation:

```text
App.pages must contain 1..20 child blocks
```

Use `0` as the upper bound when you want the generated validation model to
treat the upper bound as unbounded.

```model2blockly
contains Action onClick [0..0]
```

### Attribute And Reference Cardinality

Use `[lower..upper]` on attributes and non-containment references when the
generated editor should treat the field as multi-valued or enforce a lower
bound.

```model2blockly
class Task category Tasks label "Task" {
  attribute tags : string [0..0] unique unordered
    widget textarea uiLabel "Tags"

  reference User assignees [1..0] nonUnique unordered
    widget reference-select referenceLabelField id uiLabel "Assignees"
}
```

Generated validations:

```text
Task.assignees is required
Task.tags values must be unique
```

### Must-Follow Ordering

Use `constraint ... : must follow ...` when one statement block should only be
valid after another statement block type.

```model2blockly
constraint navigate_after_alert on Navigate : must follow Alert
```

This means a `Navigate` block must follow an `Alert` block in the generated
validation model.

This rule applies to statement blocks. It is not useful for `output class`
expression blocks because expression blocks do not have previous/next statement
connections.

### Expression Validations

Use `validation` for custom semantic rules. The expression language is the same
small browser-side subset used by the Ecore route.

```model2blockly
validation validRange on NumberInput
  : expression "number('minValue') <= number('maxValue')"
  errorMessage "Minimum value must be less than or equal to maximum value."
```

Supported helper calls:

| Helper | Meaning |
| --- | --- |
| `has("field")` | Field/reference has at least one value |
| `value("field")` | Field/reference value as text |
| `number("field")` | Field value parsed as a number |
| `size("field")` / `count("field")` | Count a field/reference, or statement children for containment |
| `fieldCount("field")` | Count values in an attribute/reference field |
| `inputCount("input")` | Count connected statement blocks |

Supported operators are `!`, `&&`, `||`, `==`, `===`, `!=`, `!==`, `<`, `<=`,
`>`, and `>=`.

You can also use a small OCL-style subset:

```model2blockly
validation titlePresent on Page
  : ocl "self.title->notEmpty()"
  errorMessage "Page title is required."
```

The generator translates simple OCL forms such as `self.name->notEmpty()`,
`self.items->size() >= 1`, comparisons, `and`, `or`, and `not`. More complex
OCL remains an Ecore-level concern.

### Validation Summary

The DSL generator creates validations from:

| DSL source | Generated validation |
| --- | --- |
| `attribute ... required` | required field validation |
| `reference ... required` | required reference validation |
| `attribute ... [lower..upper]` | field cardinality validation |
| `reference ... [lower..upper]` | reference cardinality validation |
| `modelId` | type-wide ID uniqueness validation |
| `unique` on multi-valued fields/references | duplicate value validation |
| `contains Type name [lower..upper]` | statement input cardinality validation |
| `constraint ... must follow ...` | previous-block validation |
| `validation ... : expression "..."` | custom expression validation |
| `validation ... : ocl "..."` | translated simple OCL validation |

Validation output is project runtime code, not native Blockly core behavior.
The generated `html/validation_workspace.html` lets users inspect and edit visual
validation blocks.

Do not confuse these generated validation rules with editor diagnostics. If the
DSL editor reports `INTERMEDIATE_MODEL_INVALID`, fix the `.model2blockly` source
first; no generated editor will be written. If the generated editor reports a
validation warning after generation, fix the Blockly workspace model or adjust
the validation rule that produced it.

### Current DSL Limits

The textual DSL now covers the same validation categories as the supported
Ecore annotations: required fields/references, containment cardinality,
attribute/reference cardinality, uniqueness, must-follow ordering, expression
rules, and simple OCL translation.

For full OCL, Java `EValidator` behavior, custom Blockly mutators, or raw
`args0` authoring, keep the Ecore route or extend the intermediate model and
generator.

## Recommended Modeling Workflow

1. Define the domain name and output language metadata.
2. Define top-level toolbox categories.
3. Define abstract parent classes for reusable connection types, such as
   `Component`, `Action`, or `TextExpression`.
4. Define concrete classes and decide whether each class is a statement block
   or an `output class`.
5. Add attributes for literal fields, `contains` for nested statement stacks,
   `value` for expression sockets, and `reference` for cross-links.
6. Add labels, colours, tooltips, `inline`, widget metadata, and code templates.
7. Run `Generate Blockly Editor` in Eclipse.
8. Open `generation_report.html` to check the mapping.
9. Open the standalone editor and test block shape, toolbox organization,
   generated code, and validation warnings.

## When To Extend The DSL

Consult Blockly's raw docs and consider extending the grammar/generator when
you need one of these features:

- raw `args0` layout per block;
- custom Blockly field types beyond the current generated field mapping;
- dynamic toolbox categories;
- mutators or advanced block state;
- custom connection checkers;
- direct UI rendering for `group`, `order`, `placeholder`, `readonly`, or
  `hidden`.

For those cases, the implementation path is usually:

1. Add syntax to [`Model2Blockly.xtext`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext).
2. Map it into the generated EMF `EditorSpec` intermediate model in
   [`DomainModelAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java).
3. Emit the corresponding Blockly JSON/JavaScript in
   [`BlocklyCodeGenerator.xtend`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend).
4. Add or update examples and verification scripts.

## Related Files

- Ecore annotation reference:
  [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md)
- Grammar:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- Example:
  [`io.github.plortinus.model2blockly/examples/app_maker.model2blockly`](io.github.plortinus.model2blockly/examples/app_maker.model2blockly)
- Generated example:
  [`io.github.plortinus.model2blockly/examples/generated/app_maker/README.md`](io.github.plortinus.model2blockly/examples/generated/app_maker/README.md)
- Generator:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend)
