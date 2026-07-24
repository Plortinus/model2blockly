# Model2Blockly capability matrix

## Scope

This matrix assigns every implemented capability that can be expressed through
both authoring routes to one primary paired example. A paired example always
contains descriptively named `.ecore` and `.m2b` files with the same basename
and semantically equivalent content.

The word *complete* in this document means complete with respect to the
capabilities currently implemented by Model2Blockly. It does not mean complete
coverage of the Blockly API. Differences with existing Blockly editors are
measured separately by the external evaluation corpus.

The symbols used in the summary matrix are:

- **P**: primary demonstration; the example is responsible for explaining and
  verifying the capability;
- **S**: supporting use; the capability is present only because the example
  needs it to demonstrate another feature;
- **--**: the capability is not needed by the example.

## Target example suite

| Example | Role | Source of the implementation |
|---|---|---|
| `01_basic_structure` | Domain structure and inferred Blockly organization | Existing pair, retained and simplified if necessary |
| `02_fields_ui` | Field types, widgets, and presentation metadata | Existing pair, retained |
| `03_composition_inputs` | Containment, statement inputs, value inputs, and shadow blocks | Extracted from the current `03_relations` pair |
| `04_references_integrity` | Model identity, dynamic references, opposites, and relation integrity | Extracted from the current `03_relations` pair |
| `05_validations` | Inferred and declared validation rules | Extracted from the current `04_validation_codegen` pair |
| `06_codegen_workspace` | Code generation, output blocks, runtime metadata, and workspace configuration | Extracted from the current `04_validation_codegen` pair |
| `07_ecore_specific` | Capabilities that have no equivalent `.m2b` spelling | Separate Ecore-only example, outside strict route equivalence |

## Summary allocation

| Capability family | 01 | 02 | 03 | 04 | 05 | 06 |
|---|:---:|:---:|:---:|:---:|:---:|:---:|
| Domain and class structure | P | S | S | S | S | S |
| Inheritance and inferred connections | P | -- | S | S | S | S |
| Automatic categories | P | -- | -- | -- | -- | -- |
| Explicit categories and class presentation | -- | P | S | S | S | S |
| Field types, defaults, limits, and widgets | S | P | S | S | S | S |
| Containment and statement inputs | S | -- | P | S | S | S |
| Value inputs and shadow blocks | -- | -- | P | -- | S | S |
| Dynamic references and model identity | -- | -- | -- | P | S | S |
| Multiplicity, opposites, uniqueness, and ordering | S | S | S | P | S | -- |
| Inferred and declared validations | S | S | S | S | P | -- |
| Code templates and output blocks | -- | -- | S | -- | -- | P |
| Runtime and workspace configuration | -- | S | -- | -- | -- | P |

## Detailed shared capability catalogue

Each row below has exactly one primary example. The evidence column states what
must be visible in `EditorSpec`, in a generated artifact, or in the running
editor.

### 01 -- Basic structure

| Capability | Primary evidence |
|---|---|
| Domain identity | `domainName` and normalized namespace identity are preserved in `EditorSpec`. |
| Concrete class | A concrete `BlockTypeSpec` and an instantiable Blockly block are generated. |
| Abstract class | The abstract type remains in the type system but is omitted from instantiable toolbox entries. |
| Single inheritance | `superTypeName` is preserved and compatible typed connections are inferred. |
| Root/container inference | The root container receives connection type `NONE`. |
| Contained-block inference | Containment targets receive statement connections compatible with the declared supertype. |
| Free-block inference | An independent concrete class receives connection type `FREE`. |
| Automatic categories | With no explicit category metadata, the generator builds an inheritance-based toolbox tree. |

### 02 -- Fields and UI metadata

| Capability | Primary evidence |
|---|---|
| Explicit category | A named category and block membership are preserved. |
| Nested category | The generated toolbox contains parent and child categories. |
| Class presentation | Label, colour, message, tooltip, help URL, and inline layout reach the block definition. |
| Text field | A string attribute produces a text field. |
| Integer and floating-point fields | Numeric attributes produce integer and floating-point field specifications. |
| Boolean field | A Boolean attribute produces a Boolean field. |
| Enumeration field | An enumeration produces a dropdown with the declared options. |
| Colour, angle, image, and label fields | Dedicated field specifications and image metadata are generated. |
| Default values and numeric limits | Initial values and minimum/maximum limits reach the generated block. |
| Multi-valued attribute | Attribute multiplicity and the multi-value editor metadata are preserved. |
| Widget family | Text, textarea, number, slider, switch, checkbox, select, and radio widgets are generated without compatibility warnings; specialised visual fields are covered by the dedicated-type row above. |
| UI text | Field label, help text, and placeholder are visible in the generated editor metadata. |
| UI layout and visibility | Group, order, readonly, hidden, and variant metadata are preserved. |

### 03 -- Composition and inputs

| Capability | Primary evidence |
|---|---|
| Containment | A containment reference produces a `StatementInputSpec`. |
| Containment cardinality | Lower and upper bounds are preserved on the input. |
| Statement-input presentation | Slot label, order, and variant metadata reach the generated editor. |
| Value input | An expression containment produces a `ValueInputSpec`. |
| Shadow block | The value input declares and instantiates its configured shadow block. |
| Mixed input order | Field, statement, and value-input names retain their declaration order in `orderedInputNames`. |

### 04 -- References and model integrity

| Capability | Primary evidence |
|---|---|
| Non-containment reference | A normal reference produces a dynamic `ReferenceFieldSpec`. |
| Required reference | The required flag and lower bound reach the reference field. |
| Reference label field | Candidate blocks are displayed with the configured domain attribute. |
| Reference-selection widget | The running editor offers the compatible existing blocks in the reference selector. |
| Model identifier | `idFieldName` is preserved and used for model identity. |
| Opposite reference | Reciprocal reference names are preserved and synchronized at runtime. |
| Reference multiplicity | Single- and multi-reference bounds reach `EditorSpec` and the runtime editor. |
| Unique and ordered relation | Uniqueness and ordering flags are preserved and enforced. |

### 05 -- Validations

| Capability | Primary evidence |
|---|---|
| Required-value validation | A lower bound or explicit `required` declaration produces a required rule. |
| Cardinality validation | Structural bounds produce a cardinality rule and a visual validation expression. |
| Uniqueness validation | Model identifiers and unique relations produce uniqueness rules. |
| Order constraint | A `must follow` declaration produces a `MUST_FOLLOW` rule. |
| Condition, expression, and JavaScript validations | The three aliases produce executable browser rules and the declared messages. |
| Basic OCL subset | Supported OCL expressions are translated into an executable browser expression. |
| Visual validation editing | Generated validation blocks can be loaded, edited, and synchronized with the runtime rules. |

### 06 -- Code generation and workspace

| Capability | Primary evidence |
|---|---|
| Code language and file extension | Domain-level code metadata reaches the generated editor and output filename. |
| Per-block code template | Each configured block produces code from its template. |
| Statement-template expansion | Contained statements are expanded in declaration order. |
| Output block | An output block receives the declared output connection and optional type. |
| Runtime kind | Runtime metadata is preserved and selects the intended generated runtime behavior. |
| Workspace configuration | Renderer, zoom, grid, trashcan, scale, and snapping options reach the generated page. |
| Toolbox form | Category and flyout toolbox forms are generated from the workspace option. |

## Cross-cutting acceptance criteria

These are not assigned to an additional example. Every paired example must pass
them so that the suite verifies the complete generation chain rather than only
adapter data structures.

| Criterion | Required evidence |
|---|---|
| Input validity | Ecore loads as an `EPackage`; `.m2b` parses, links, and validates without errors. |
| Intermediate validity | Both routes produce valid EMF `EditorSpec` instances. |
| Route equivalence | Canonical `EditorSpec` content matches for every capability assigned to the pair. |
| XMI persistence | Both intermediate models serialize and reload successfully. |
| Artifact equivalence | The Ecore and `.m2b` routes generate byte-identical HTML, JavaScript, and JSON artifacts. |
| Standalone loading | The generated standalone editor opens in a browser without JavaScript errors. |
| Sample loading | `Load Sample` creates a non-empty domain model. |
| Reactive validation | The issues view is updated from the current workspace model. |
| JSON representation | The workspace model is serialized to valid JSON. |
| Domain XMI export | The model is exported as parseable EMF-style XMI without leaking runtime block identifiers. |
| Code generation | When templates are configured, the expected textual code is generated. |
| Validation-block synchronization | Visual validation rules synchronize back to the active runtime. |

## Ecore-only capability catalogue

The following features cannot be placed in a strict pair because the textual
DSL has no equivalent source construct. They belong to `07_ecore_specific` and
must not be presented as evidence of Ecore/`.m2b` equivalence.

| Capability | Expected behavior |
|---|---|
| Subpackages | The Ecore adapter recursively visits nested `EPackage` elements. |
| Ignored structural features | Derived, transient, volatile, or non-changeable features are not exposed as editable fields. |
| EClass interface flag | An Ecore interface is treated as an abstract block type. |
| Broad Ecore numeric aliases | Long, short, big-integer, double, and big-decimal types normalize to supported field families. |
| Automatic reference-label fallback | The adapter selects `id`, `displayName`, `title`, or `name` when no explicit label field is configured. |
| Explicit namespace URI and prefix | Ecore namespace identity is preserved without DSL-style synthesis. |
| Standard Ecore/OCL annotation containers | Supported constraints are read from their standard Ecore annotation representation. |

## Completion rule

The suite is complete only when every shared catalogue row has one passing
primary pair, every Ecore-only row has explicit adapter evidence, all
cross-cutting criteria pass, persistent generated outputs exist beside the
sources, and one browser screenshot has been captured for each generated
editor that is cited in the thesis.
