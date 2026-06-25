# Ecore Annotation Reference

The Ecore route works without annotations, then uses optional `EAnnotation`
details to improve the Blockly editor. The implementation is in
`EcoreAdapter.java`.

## Zero-annotation Inference

| Ecore element | Default Model2Blockly mapping |
| --- | --- |
| `EPackage` | Editor domain name, namespace URI and namespace prefix. |
| Concrete `EClass` | Concrete Blockly block type. |
| Abstract `EClass` or interface | Abstract block type, not emitted as a concrete toolbox block. |
| Supertype | Block inheritance and connection typing. |
| `EAttribute:EString` | Text field. |
| `EAttribute:EInt`, `ELong`, `EShort`, `EBigInteger` | Integer field. |
| `EAttribute:EFloat`, `EDouble`, `EBigDecimal` | Floating-point field. |
| `EAttribute:EBoolean` | Checkbox field. |
| `EEnum` attribute | Dropdown field. |
| Containment `EReference` | Statement input with containment validation. |
| Non-containment `EReference` | Dynamic reference field. |
| `lowerBound >= 1` | Required value validation. |
| Multi-valued unique feature | Duplicate-value validation. |
| `iD` attribute or `eIDAttribute` | Identity field for reference labels and XMI export. |

## `source="blockly"` on `EPackage`

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

## `source="blockly"` on `EClass`

| Key | Meaning |
| --- | --- |
| `message0` | Explicit Blockly message string. |
| `colour` | Block colour as a number. |
| `category` | Toolbox category. Slash-separated values create nested categories. |
| `output` | Marks the block as an output block. Use `true` or a typed output name. |
| `inputsInline` | `true` or `false`. |
| `tooltip` | Blockly tooltip. |
| `helpUrl` | Blockly help URL. |

Example:

```xml
<eAnnotations source="blockly">
  <details key="category" value="Screens/Navigation"/>
  <details key="colour" value="210"/>
  <details key="message0" value="Screen %1"/>
</eAnnotations>
```

## `source="blockly"` on `EAttribute`

| Key | Meaning |
| --- | --- |
| `type` | Supported values include `field_colour`, `field_angle`, `field_image`, `field_label`, `field_input`, `field_number`, `field_checkbox`. |
| `min` | Minimum value for numeric fields. |
| `max` | Maximum value for numeric fields. |
| `src` | Image URL for `field_image`. |
| `width` | Image width for `field_image`. |
| `height` | Image height for `field_image`. |
| `alt` | Alternative text for `field_image`. |

## `source="blockly"` on `EReference`

| Key | Meaning |
| --- | --- |
| `type=input_value` | Emits a Blockly value input instead of the default mapping. |
| `check` | Blockly type check for value or statement inputs. |
| `shadow` | Shadow block hint for value inputs. |

Containment references default to statement inputs. Non-containment references
default to dynamic reference dropdowns.

## `source="ui"`

On `EClass`:

| Key | Meaning |
| --- | --- |
| `label` | Human-readable class/block label. |

On `EAttribute` and `EReference`:

| Key | Meaning |
| --- | --- |
| `widget` | Inspector widget hint. |
| `label` | Inspector label. |
| `help` | Help text. |
| `placeholder` | Placeholder text. |
| `group` | Inspector grouping hint. |
| `variant` | UI variant hint. |
| `readonly` | Boolean read-only flag. |
| `hidden` | Boolean hidden flag. |
| `order` | Numeric ordering hint. |
| `referenceLabelField` | Reference label source field, for references only. |

## `source="code"`

On `EPackage`:

| Key | Meaning |
| --- | --- |
| `language` | Code generator language label. |
| `fileExtension` | File extension for generated code exports. |

On `EClass`:

| Key | Meaning |
| --- | --- |
| `template` | Code template for that block type. |
| `codeTemplate` | Alternate key accepted by the adapter. |

## `source="runtime"`

On `EPackage`:

| Key | Meaning |
| --- | --- |
| `kind` | Runtime kind metadata passed into the editor specification. |

## `source="validation"`

On `EClass`:

| Key | Meaning |
| --- | --- |
| `mustFollow` | Creates an ordering validation against the named predecessor type. |
| `expression`, `condition`, `js` | Expression validation. |
| `message` | Diagnostic message for the default expression. |
| `expression.<name>`, `condition.<name>`, `js.<name>` | Named expression validation. |
| `message.<name>` | Diagnostic message for a named expression. |
| `ocl` | OCL expression translated when it fits the supported subset. |
| `ocl.<name>` | Named OCL expression. |

The OCL subset covers simple feature navigation, `size()`, `notEmpty()`,
`isEmpty()`, `oclIsUndefined()`, comparisons and boolean operators.
Unsupported OCL is still reported in the generated validation diagnostics.

## EMF/OCL Annotations

The adapter also reads standard EMF/OCL annotations:

```text
source="http://www.eclipse.org/emf/2002/Ecore"
source="http://www.eclipse.org/emf/2002/Ecore/OCL"
source="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"
```

When `constraints` declares names on the Ecore annotation, the adapter looks up
matching OCL expressions by name. Without declared names, it reads all OCL
details on the OCL annotation.
