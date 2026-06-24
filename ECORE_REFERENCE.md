# Ecore Annotation Reference

Model2Blockly can generate Blockly editors directly from annotated Ecore
metamodels. It uses standard Ecore structure plus `EAnnotation` entries on
packages, classes, attributes, and references.

Use this document when authoring or reviewing `.ecore` files such as
[`io.github.plortinus.model2blockly/model/app_maker.ecore`](io.github.plortinus.model2blockly/model/app_maker.ecore).

Implementation references:

- Ecore adapter: [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
- Main Ecore example: [`io.github.plortinus.model2blockly/model/app_maker.ecore`](io.github.plortinus.model2blockly/model/app_maker.ecore)
- Generator mapping: [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend)
- Generated Ecore example: [`io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/README.md`](io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/README.md)

## How to use this reference

- If you are new to the project, start with [`DOCS.md`](DOCS.md) and
  [`README.md`](README.md) before reading this page end to end.
- If you already have an `.ecore` model, start with
  [Zero-Annotation Mapping](#zero-annotation-mapping), then add only the
  annotations you need.
- If you need a specific annotation key, jump to package, class, attribute, or
  reference annotations.
## Official References

- [EMF `EAnnotation` Javadoc](https://download.eclipse.org/modeling/emf/emf/javadoc/2.11/org/eclipse/emf/ecore/EAnnotation.html) for the underlying Ecore annotation object. `EAnnotation` provides `source` and `details`, which this project uses as key/value metadata.
- [Eclipse Modeling Framework](https://eclipse.dev/emf/) for general EMF/Ecore background.
- [Blockly block JSON](https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/) for `message0`, `args0`, `colour`, `tooltip`, `helpUrl`, inputs, and connections.
- [Blockly fields](https://docs.blockly.com/guides/create-custom-blocks/fields/overview/) for field behavior.
- [Blockly workspace configuration](https://docs.blockly.com/guides/configure/configuration_struct/) for `workspace.*` options.
- [Blockly code generation](https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/) for generated block-code behavior.

## Annotation Shape

In Ecore XMI, annotations look like this:

```xml
<eAnnotations source="blockly">
  <details key="colour" value="260"/>
  <details key="category" value="Pages"/>
  <details key="tooltip" value="Root app model."/>
</eAnnotations>
```

In the Eclipse Ecore tree editor, this corresponds to:

```text
EAnnotation
  source = blockly
  details:
    colour -> 260
    category -> Pages
    tooltip -> Root app model.
```

Regular XML comments are ignored by the generator. For generation metadata,
use `EAnnotation` entries.

## Supported Annotation Sources

| Source | Where used | Purpose |
| --- | --- | --- |
| `blockly` | `EPackage`, `EClass`, `EAttribute`, `EReference` | Blockly presentation, field overrides, value inputs, workspace options |
| `ui` | `EClass`, `EAttribute`, `EReference` | Human-readable labels and UI metadata |
| `code` | `EPackage`, `EClass` | Domain code metadata and block code templates |
| `runtime` | `EPackage` | Optional generated runtime flavor |
| `validation` | `EClass` | Must-follow and semantic validation rules |
| `http://www.eclipse.org/emf/2002/Ecore` | `EClass` | Standard EMF `constraints` declaration |
| `http://www.eclipse.org/emf/2002/Ecore/OCL` | `EClass` | Basic OCL invariant body source |
| `http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot` | `EClass` | Basic OCL invariant body source |

## Zero-Annotation Mapping

The Ecore route works without custom annotations. Annotations only refine the
generated editor.

| Ecore construct | Generated result |
| --- | --- |
| `EPackage.name` | Domain/editor name, capitalized |
| `EPackage.nsURI` / `nsPrefix` | Namespace metadata in the intermediate model |
| `EPackage.eSubpackages` | Classifiers are collected recursively into one generated editor |
| concrete `EClass` | Blockly block type |
| `EClass.abstract=true` or `interface=true` | Abstract type, not emitted as a toolbox block |
| first `EClass.eSuperTypes` entry | Parent type for typed block connections |
| `EAttribute : EString/EChar` | Text input field |
| `EAttribute : EInt/ELong/EShort/EBigInteger` | Number field with integer precision |
| `EAttribute : EFloat/EDouble/EBigDecimal` | Number field with decimal precision |
| `EAttribute : EBoolean` | Checkbox field |
| `EAttribute : EEnum` | Dropdown field from enum literals |
| other `EDataType` | Text input fallback |
| `EAttribute.defaultValueLiteral` | Blockly field default |
| `EAttribute.lowerBound >= 1` | Required field validation |
| `EAttribute.iD=true` / `EClass.eIDAttribute` | Model id, default reference label, uniqueness validation |
| multi-valued `EAttribute` | Custom multi-value text field with cardinality/uniqueness validation |
| containment `EReference` | Blockly `input_statement` |
| containment `lowerBound/upperBound` | Statement cardinality validation |
| non-containment `EReference` | Dynamic reference dropdown or multiselect |
| non-containment `lowerBound >= 1` | Required reference validation |
| `EReference.eOpposite` | Runtime synchronization metadata for opposite references |
| `ETypedElement.unique` | Uniqueness validation for multi-valued fields/references |
| derived/transient/volatile/non-changeable feature | Skipped from editable blocks |

## Package Annotations

### `source="blockly"` On `EPackage`

Package-level `blockly` annotations configure the generated workspace and
toolbox mode. Keys are flattened with `workspace.` prefixes.

```xml
<eAnnotations source="blockly">
  <details key="workspace.renderer" value="zelos"/>
  <details key="workspace.trashcan" value="true"/>
  <details key="workspace.zoom.controls" value="true"/>
  <details key="workspace.zoom.wheel" value="true"/>
  <details key="workspace.zoom.startScale" value="1"/>
  <details key="workspace.zoom.maxScale" value="3"/>
  <details key="workspace.zoom.minScale" value="0.3"/>
  <details key="workspace.grid.spacing" value="20"/>
  <details key="workspace.grid.length" value="3"/>
  <details key="workspace.grid.colour" value="#cccccc"/>
  <details key="workspace.grid.snap" value="true"/>
  <details key="workspace.toolboxType" value="category"/>
</eAnnotations>
```

Supported behavior:

| Key pattern | Generated effect |
| --- | --- |
| `workspace.<key>` | Adds a top-level option passed to `Blockly.inject(...)` |
| `workspace.<object>.<key>` | Adds a nested option object, for example `zoom.controls` |
| `workspace.toolboxType` | Dedicated toolbox selection: `category` or `flyout`; not passed to `Blockly.inject(...)` |

Values are parsed as booleans, integers, doubles, quoted strings, or plain
strings. For exact Blockly option semantics, check Blockly's
[workspace configuration](https://docs.blockly.com/guides/configure/configuration_struct/),
[grid](https://docs.blockly.com/guides/configure/grid/), and
[zoom](https://docs.blockly.com/guides/configure/zoom/) docs.

### `source="code"` On `EPackage`

```xml
<eAnnotations source="code">
  <details key="language" value="javascript"/>
  <details key="fileExtension" value="js"/>
</eAnnotations>
```

| Key | Meaning |
| --- | --- |
| `language` | Domain code metadata stored in `BLOCKLY_DOMAIN_CODEGEN.language` |
| `fileExtension` | Extension used for downloaded generated domain code |

### `source="runtime"` On `EPackage`

```xml
<eAnnotations source="runtime">
  <details key="kind" value="appMaker"/>
</eAnnotations>
```

`kind` selects optional runtime-specific behavior in the generated HTML. The
current example uses `appMaker`.

## Class Annotations

### `source="blockly"` On `EClass`

```xml
<eClassifiers xsi:type="ecore:EClass" name="Page">
  <eAnnotations source="blockly">
    <details key="message0" value="Page %1 route %2"/>
    <details key="colour" value="260"/>
    <details key="category" value="Pages"/>
    <details key="tooltip" value="A navigable page."/>
    <details key="helpUrl" value="https://example.com/page-help"/>
    <details key="inputsInline" value="true"/>
  </eAnnotations>
</eClassifiers>
```

| Key | Generated effect |
| --- | --- |
| `message0` | Overrides generated Blockly block text layout |
| `colour` | Blockly block hue |
| `category` | Toolbox category name; `/` creates nested categories, for example `UI/Inputs` |
| `tooltip` | Blockly block tooltip |
| `helpUrl` | Blockly block help URL |
| `inputsInline` | `true` or `false`, mapped to Blockly `inputsInline` |
| `output` | `true` for an output block, or a type name such as `Expression` for typed output |

If no class has a `category` annotation, the adapter creates categories from
the inheritance hierarchy. If any class has a category annotation, annotated
categories drive the toolbox.

### `source="ui"` On `EClass`

```xml
<eAnnotations source="ui">
  <details key="label" value="Data source"/>
</eAnnotations>
```

| Key | Generated effect |
| --- | --- |
| `label` | Human-readable block label and category label fallback |

### `source="code"` On `EClass`

```xml
<eAnnotations source="code">
  <details key="template" value="page(&quot;{{title}}&quot;, [&#10;{{statements:components}}&#10;]);"/>
</eAnnotations>
```

| Key | Generated effect |
| --- | --- |
| `template` | Domain code template for this block |
| `codeTemplate` | Alias for `template` |

Template placeholders are used by the Blockly code generator:

| Placeholder | Meaning |
| --- | --- |
| `{{fieldName}}` | Attribute or reference field value |
| `{{value:inputName}}` | Code from a connected value block |
| `{{statement:inputName}}` | Code from connected statement blocks |
| `{{statements:inputName}}` | Alias for statement input code |
| `{{children:inputName}}` | Alias for statement input code |
| `{{type}}` | Current Blockly block type |

In XML attributes, escape quotes as `&quot;` and new lines as `&#10;`.

### `source="validation"` On `EClass`

```xml
<eAnnotations source="validation">
  <details key="mustFollow" value="Alert"/>
  <details key="expression" value="has(&quot;title&quot;) &amp;&amp; inputCount(&quot;components&quot;) &gt;= 1"/>
  <details key="message" value="Page needs a title and at least one component."/>
</eAnnotations>
```

| Key | Generated validation |
| --- | --- |
| `mustFollow` | Current block must follow the named predecessor block type |
| `expression` | Runtime expression rule |
| `condition` | Alias for `expression` |
| `js` | Alias for `expression` |
| `message` | Warning message for the expression rule |
| `expression.<name>` | Named expression validation |
| `condition.<name>` | Named expression validation |
| `js.<name>` | Named expression validation |
| `message.<name>` | Message for a named validation |
| `ocl` | Basic OCL expression to translate |
| `ocl.<name>` | Named basic OCL expression to translate |

Runtime expression helpers:

| Helper | Meaning |
| --- | --- |
| `has("field")` | Field/reference has at least one value |
| `value("field")` | Field/reference value as text |
| `number("field")` | Field value as a number |
| `fieldCount("field")` | Count values in a field/reference |
| `inputCount("input")` | Count child blocks in a statement input |
| `size("field")` | Runtime alias for `fieldCount("field")`; use `inputCount(...)` for statement inputs |
| `fieldUnique("field")` | Values inside a multi-valued field/reference are unique |
| `typeFieldUnique("Type", "field")` | Field value is unique across all blocks of a type |
| `previousBlockIs("Type")` | Previous statement block has the given type |

The visual validation workspace can parse common boolean/comparison expressions
using `&&`, `||`, `!`, `==`, `===`, `!=`, `!==`, `>`, `>=`, `<`, and `<=`.
More complex JavaScript may still run as a validation expression, but it may not
round-trip into lower-level visual validation blocks.

## Attribute Annotations

### Automatic Attribute Type Mapping

| Ecore attribute type | Generated Blockly field |
| --- | --- |
| `EString`, `EChar` | `field_input` |
| `EInt`, `ELong`, `EShort`, `EBigInteger` | `field_number`, integer precision |
| `EFloat`, `EDouble`, `EBigDecimal` | `field_number`, decimal precision |
| `EBoolean` | `field_checkbox` |
| `EEnum` | `field_dropdown` |
| other `EDataType` | `field_input` fallback |
| multi-valued attribute | custom `field_multivalue` |

### `source="blockly"` On `EAttribute`

```xml
<eStructuralFeatures xsi:type="ecore:EAttribute" name="backgroundColor"
    eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
    defaultValueLiteral="#ffffff">
  <eAnnotations source="blockly">
    <details key="type" value="field_colour"/>
  </eAnnotations>
</eStructuralFeatures>
```

Supported keys:

| Key | Generated effect |
| --- | --- |
| `type=field_colour` | Force Blockly colour field |
| `type=field_angle` | Force Blockly angle field |
| `type=field_image` | Force Blockly image field |
| `type=field_label` | Force Blockly static label field |
| `type=field_input` | Force text field |
| `type=field_number` | Force number field |
| `type=field_checkbox` | Force checkbox field |
| `min` | Minimum value for number fields |
| `max` | Maximum value for number fields |
| `src` | Image URL for `field_image` |
| `width` | Image width for `field_image`; defaults to `40` |
| `height` | Image height for `field_image`; defaults to `40` |
| `alt` | Image alt text for `field_image` |

Image example:

```xml
<eAnnotations source="blockly">
  <details key="type" value="field_image"/>
  <details key="src" value="icons/warning.svg"/>
  <details key="width" value="24"/>
  <details key="height" value="24"/>
  <details key="alt" value="Warning"/>
</eAnnotations>
```

Number range example:

```xml
<eAnnotations source="blockly">
  <details key="type" value="field_number"/>
  <details key="min" value="0"/>
  <details key="max" value="100"/>
</eAnnotations>
```

### `source="ui"` On `EAttribute`

```xml
<eAnnotations source="ui">
  <details key="widget" value="slider"/>
  <details key="label" value="Progress"/>
  <details key="help" value="Initial completion percentage."/>
  <details key="placeholder" value="0"/>
  <details key="group" value="Display"/>
  <details key="order" value="1"/>
  <details key="readonly" value="false"/>
  <details key="hidden" value="false"/>
  <details key="variant" value="compact"/>
</eAnnotations>
```

Supported UI keys:

| Key | Meaning |
| --- | --- |
| `widget` | UI intent metadata such as `text`, `textarea`, `number`, `slider`, `checkbox`, `select`, `radio`, `color`, `angle`, `image` |
| `label` | Human-readable field label |
| `help` | Help text metadata |
| `placeholder` | Placeholder metadata |
| `group` | Inspector/report grouping metadata |
| `order` | Inspector/report ordering metadata |
| `readonly` | Read-only metadata |
| `hidden` | Hidden metadata |
| `variant` | Presentation metadata, for example `compact` |

Current behavior selects the field type primarily from the Ecore type and
`source="blockly"` overrides. Many `ui` keys are retained in
the intermediate model and reports, but not all are direct Blockly block JSON
controls.

## Reference Annotations

### Containment `EReference`

Containment references become statement inputs.

```xml
<eStructuralFeatures xsi:type="ecore:EReference" name="components"
    lowerBound="1" upperBound="40" eType="#//Component" containment="true">
  <eAnnotations source="blockly">
    <details key="check" value="Component"/>
  </eAnnotations>
  <eAnnotations source="ui">
    <details key="label" value="Components"/>
    <details key="group" value="Layout"/>
    <details key="order" value="3"/>
  </eAnnotations>
</eStructuralFeatures>
```

| Key | Generated effect |
| --- | --- |
| `source="blockly" check` | Overrides the Blockly statement input `check` type |
| `lowerBound` / `upperBound` | Generates cardinality validation |
| `source="ui"` keys | Same UI metadata keys as attributes |

`upperBound="-1"` is normalized to `0` in the intermediate model, where `0`
means unbounded for generated validation.

### Non-Containment `EReference`

Non-containment references become dynamic reference fields by default.

```xml
<eStructuralFeatures xsi:type="ecore:EReference" name="source"
    lowerBound="1" eType="#//DataSource">
  <eAnnotations source="ui">
    <details key="widget" value="reference-select"/>
    <details key="label" value="Source"/>
    <details key="referenceLabelField" value="name"/>
  </eAnnotations>
</eStructuralFeatures>
```

| Ecore/reference property | Generated effect |
| --- | --- |
| `lowerBound >= 1` | Required reference validation |
| `upperBound > 1` or `upperBound = -1` | Multiselect reference field |
| `unique=true` on multi-valued reference | Uniqueness validation |
| `eOpposite` | Opposite reference synchronization metadata |
| `source="ui" referenceLabelField` | Display field for target block labels |

If `referenceLabelField` is omitted, the adapter tries the target type's ID
attribute, then `displayName`, `title`, then `name`.

### `type=input_value` On `EReference`

An `EReference` can become a Blockly value input instead of a statement input
or reference dropdown.

```xml
<eStructuralFeatures xsi:type="ecore:EReference" name="content"
    lowerBound="1" eType="#//TextExpression" containment="true">
  <eAnnotations source="blockly">
    <details key="type" value="input_value"/>
    <details key="check" value="TextExpression"/>
    <details key="shadow" value="TextLiteral"/>
  </eAnnotations>
</eStructuralFeatures>
```

| Key | Generated effect |
| --- | --- |
| `type=input_value` | Generates Blockly `input_value` |
| `check` | Blockly value input `check`; defaults to the reference target type |
| `shadow` | Default shadow block in toolbox entries |

Use this for expression sockets, especially when the referenced target class is
annotated as an output block.

## Output Blocks

Use class-level `source="blockly"` with `output` to create expression blocks.

```xml
<eClassifiers xsi:type="ecore:EClass" name="TextLiteral" eSuperTypes="#//TextExpression">
  <eAnnotations source="blockly">
    <details key="output" value="true"/>
    <details key="colour" value="230"/>
    <details key="category" value="Expressions"/>
  </eAnnotations>
</eClassifiers>
```

Output mapping:

| Annotation | Generated connection |
| --- | --- |
| `output=true` with no supertype | `output: null` |
| `output=true` with a supertype | typed output using the root ancestor |
| `output=TypeName` | typed output using `TypeName` |

## OCL And EMF Constraints

The adapter recognizes standard EMF constraint declarations and a small OCL
subset.

```xml
<eAnnotations source="http://www.eclipse.org/emf/2002/Ecore">
  <details key="constraints" value="HasTitle HasComponents"/>
</eAnnotations>
<eAnnotations source="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot">
  <details key="HasTitle" value="self.title->notEmpty()"/>
  <details key="HasComponents" value="self.components->size() &gt;= 1"/>
</eAnnotations>
```

Supported OCL patterns include:

| OCL pattern | Runtime expression |
| --- | --- |
| `self.name->notEmpty()` | `has("name")` |
| `self.name->isEmpty()` | `!has("name")` |
| `self.name.oclIsUndefined()` | `!has("name")` |
| `self.items->size()` | `size("items")` |
| `self.estimate >= 1 and self.estimate <= 80` | `number("estimate") >= 1 && number("estimate") <= 80` |
| `self.state <> ''` | `value("state") !== ""` |

Unsupported OCL constructs, such as `forAll`, `exists`, `collect`, `select`,
`reject`, `closure`, `let`, `implies`, `iterate`, `oclIsKindOf`, and
`oclIsTypeOf`, are rejected before generation instead of being silently ignored.

For full OCL or Java `EValidator` behavior, keep validation as an explicit
extension task rather than assuming the browser runtime can reproduce it.

For containment counts, prefer native Ecore `lowerBound`/`upperBound` because
the generator emits explicit `inputCount(...)` cardinality validations. If you
write custom runtime expressions by hand, use `inputCount("children")` for
statement inputs and `fieldCount("field")` for fields/references.

## Current Limits

The Ecore path deliberately covers a practical subset of EMF:

- `EOperation` is not translated into Blockly behavior.
- `EGenericType` and `ETypeParameter` are mostly erased to the referenced
  `eType`.
- `EReference.resolveProxies` and `eKeys` are not represented in the browser
  editor.
- `EStructuralFeature.unsettable` is not represented.
- Complex OCL and Java `EValidator` logic are not fully translated.
- Container/opposite semantics are simplified to editable reference
  synchronization where possible.

## Complete Mini Example

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore"
    name="todo"
    nsURI="http://example.org/todo"
    nsPrefix="todo">

  <eAnnotations source="code">
    <details key="language" value="javascript"/>
    <details key="fileExtension" value="js"/>
  </eAnnotations>

  <eClassifiers xsi:type="ecore:EClass" name="TodoApp">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Tasks"/>
      <details key="tooltip" value="Root todo app."/>
    </eAnnotations>
    <eAnnotations source="code">
      <details key="template" value="app(&quot;{{name}}&quot;, [&#10;{{statements:tasks}}&#10;]);"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="My Todo App"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="tasks"
        lowerBound="1" upperBound="-1" eType="#//Task" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Task">
    <eAnnotations source="blockly">
      <details key="colour" value="210"/>
      <details key="category" value="Tasks"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eAnnotations source="code">
      <details key="template" value="task(&quot;{{title}}&quot;);"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Buy milk"/>
  </eClassifiers>
</ecore:EPackage>
```

## Practical Workflow

1. Start with a plain Ecore metamodel.
2. Run `Generate Blockly Editor` on the `.ecore` file.
3. Open `generation_report.html` and inspect the Ecore-to-Blockly mapping.
4. Add only the annotations needed to improve block readability, toolbox
   categories, field types, code templates, and validations.
5. Rerun the generator and verify the standalone editor.

Ecore inputs also pass through the shared generated EMF `EditorSpec`
intermediate-model pipeline. The generator adapts the `EPackage`, serializes
the `EditorSpec` to `intermediate/*_blocklyspec.xmi`, reloads that XMI,
validates the reloaded model, and then emits the Blockly HTML/JavaScript files.

When possible, diagnostics include the selected file and an Ecore URI fragment,
plus the intermediate path such as `block[Task].assignee`.

## Related Files

- Ecore example:
  [`io.github.plortinus.model2blockly/model/app_maker.ecore`](io.github.plortinus.model2blockly/model/app_maker.ecore)
- Generated Ecore example:
  [`io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/README.md`](io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/README.md)
- Ecore adapter:
  [`io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
