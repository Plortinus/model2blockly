---
pageClass: ecore-mapping-page
---

# Ecore to Blockly Mapping

This page explains how Model2Blockly converts an Ecore metamodel into a Blockly
editor. Use it while designing `.ecore` files: start with the default inference
rules, then add annotations only when the generated Blockly needs customization.

For the complete project-level case, see the [AppMaker Case](./RUNNING_EXAMPLE.md).

## Mapping Rules

| Ecore element | Generated result |
| --- | --- |
| `EClass` | A Blockly block type. |
| `abstract="true"` `EClass` | Hidden from the toolbox, but used as a connection type. |
| `EAttribute` | A field such as text, dropdown, colour, checkbox or number. |
| `containment="true"` `EReference` | `input_statement` or `input_value` for nested child blocks. |
| Non-containment `EReference` | A reference selector field for choosing existing blocks. |
| `source="blockly"` annotation | Categories, colours, tooltips, field types and shadow blocks. |
| `source="ui"` annotation | Field labels, widgets, groups and order. |
| `source="code"` annotation | Code export templates. |
| `source="validation"` annotation | Generated Blockly validation rules. |

## Defaults and Customization

An Ecore model can generate a Blockly editor without annotations. Annotations
override the inferred defaults when the generated editor needs clearer labels,
categories, field widgets, validation or code export.

### Without Annotations

| Ecore input | Default generated result |
| --- | --- |
| Concrete `EClass` | A draggable Blockly block. |
| Abstract `EClass` or interface | Not shown in the toolbox; used as a parent or connection type. |
| `EAttribute:EString` | Text field. |
| `EAttribute:EBoolean` | Checkbox field. |
| Numeric attribute | Number field. |
| `EEnum` attribute | Dropdown field. |
| Containment `EReference` | Statement input for nesting child blocks. |
| Non-containment `EReference` | Dynamic reference dropdown for selecting existing objects. |
| `lowerBound >= 1` | Required-field or minimum-cardinality validation. |
| No `category` annotations | Toolbox categories inferred from inheritance. |

### When You Need a Specific Blockly Result

| Desired Blockly result | Add annotation to | Common keys |
| --- | --- | --- |
| Put blocks in a specific toolbox category | `EClass` with `source="blockly"` | `category`, including nested paths such as `UI/Inputs`. |
| Change block colour, tooltip or help link | `EClass` with `source="blockly"` | `colour`, `tooltip`, `helpUrl`. |
| Change block text or feature order | `EClass` with `source="blockly"` | `message0`, `inputsInline`. |
| Use colour, angle, image or label fields | `EAttribute` with `source="blockly"` | `type=field_colour`, `field_angle`, `field_image`, `field_label`. |
| Constrain a number field | `EAttribute` with `source="blockly"` | `type=field_number`, `min`, `max`. |
| Change field label, help text, placeholder or order | `EAttribute` or `EReference` with `source="ui"` | `label`, `help`, `placeholder`, `order`, `readonly`, `hidden`. |
| Turn containment into an expression socket | `EReference` with `source="blockly"` | `type=input_value`, `check`, `shadow`. |
| Change reference dropdown labels | Non-containment `EReference` with `source="ui"` | `referenceLabelField`. |
| Add semantic validation | `EClass` with `source="validation"` | `mustFollow`, `expression`, `condition`, `ocl`, `message`. |
| Add code export | `EPackage` or `EClass` with `source="code"` | `language`, `fileExtension`, `template`, `codeTemplate`. |
| Configure the Blockly workspace | `EPackage` with `source="blockly"` | `workspace.renderer`, `workspace.grid.*`, `workspace.zoom.*`, `workspace.toolboxType`. |

```xml
<eClassifiers xsi:type="ecore:EClass" name="Task">
  <eAnnotations source="blockly">
    <details key="category" value="Work/Tasks"/>
    <details key="colour" value="210"/>
    <details key="tooltip" value="Create a task"/>
  </eAnnotations>

  <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
      eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString">
    <eAnnotations source="ui">
      <details key="label" value="Title"/>
      <details key="placeholder" value="Task title"/>
    </eAnnotations>
  </eStructuralFeatures>
</eClassifiers>
```

## Annotation Key Reference

### `source="blockly"` on `EPackage`

Workspace options use `workspace.*` keys:

| Key | Meaning |
| --- | --- |
| `workspace.toolboxType` | Dedicated toolbox style field. |
| `workspace.renderer` | Passed into Blockly inject options. |
| `workspace.zoom.controls` | Nested workspace option. |
| `workspace.zoom.maxScale` | Nested workspace option. |
| `workspace.grid.spacing` | Nested workspace option. |
| `workspace.grid.snap` | Nested workspace option. |

Any `workspace.<path>` key is converted into a nested Blockly workspace option
unless it is `workspace.toolboxType`.

### `source="blockly"` on `EClass`

| Key | Meaning |
| --- | --- |
| `message0` | Explicit Blockly message string. |
| `colour` | Block colour as a number. |
| `category` | Toolbox category. Slash-separated values create nested categories. |
| `output` | Marks the block as an output block. Use `true` or a typed output name. |
| `inputsInline` | `true` or `false`. |
| `tooltip` | Blockly tooltip. |
| `helpUrl` | Blockly help URL. |

### `source="blockly"` on `EAttribute`

| Key | Meaning |
| --- | --- |
| `type` | Supported values include `field_colour`, `field_angle`, `field_image`, `field_label`, `field_input`, `field_number`, `field_checkbox`. |
| `min` | Minimum value for numeric fields. |
| `max` | Maximum value for numeric fields. |
| `src` | Image URL for `field_image`. |
| `width` | Image width for `field_image`. |
| `height` | Image height for `field_image`. |
| `alt` | Alternative text for `field_image`. |

### `source="blockly"` on `EReference`

| Key | Meaning |
| --- | --- |
| `type=input_value` | Emits a Blockly value input instead of the default mapping. |
| `check` | Blockly type check for value or statement inputs. |
| `shadow` | Shadow block hint for value inputs. |

### `source="ui"`

On `EClass`, `label` sets the human-readable class or block label.

On `EAttribute` and `EReference`, supported keys are `widget`, `label`, `help`,
`placeholder`, `group`, `variant`, `readonly`, `hidden`, `order` and
`referenceLabelField` for references.

### `source="code"`

On `EPackage`, supported keys are `language` and `fileExtension`.

On `EClass`, supported keys are `template` and `codeTemplate`.

### `source="runtime"`

On `EPackage`, `kind` passes runtime metadata into the editor specification.

### `source="validation"`

On `EClass`:

| Key | Meaning |
| --- | --- |
| `mustFollow` | Creates an ordering validation against the named predecessor type. |
| `expression`, `condition`, `js` | Expression validation. |
| `message` | Diagnostic message for the default expression. |
| `expression.<name>`, `condition.<name>`, `js.<name>` | Named expression validation. |
| `message.<name>` | Diagnostic message for a named expression. |
| `ocl`, `ocl.<name>` | OCL expression translated when it fits the supported subset. |

The OCL subset covers simple feature navigation, `size()`, `notEmpty()`,
`isEmpty()`, `oclIsUndefined()`, comparisons and boolean operators.

## Feature Examples

Each example is an independently generated `.ecore` model. The left pane shows
the source model, and the right pane embeds the Blockly HTML generated from that
same model. Resource links open the source, standalone editor and generation
report.

<!-- GENERATED_ECORE_FEATURE_GALLERY -->
