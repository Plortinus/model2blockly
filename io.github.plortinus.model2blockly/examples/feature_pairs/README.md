# Paired Ecore and `.m2b` feature examples

This directory contains small, paired inputs used to exercise the two
Model2Blockly authoring routes. Each pair describes the same small domain once
as Ecore and once as `.m2b`. Both inputs must reach equivalent observable
`EditorSpec` content for the capabilities claimed by that pair.

The complete AppMaker inputs remain the end-to-end integration case. These
smaller pairs are implementation fixtures: they isolate mapping rules, make
diagrams readable, and provide precise regression targets.

## Directory contract

Each implemented pair uses this layout:

```text
feature_pairs/
  01_basic_structure/
    source.ecore
    source.m2b
  02_fields_ui/
    source.ecore
    source.m2b
  03_relations/
    source.ecore
    source.m2b
  04_validation_codegen/
    source.ecore
    source.m2b
```

Generated HTML, JavaScript, and XMI are reproducible test artifacts and are not
stored beside the sources. The verification command generates them in a
temporary directory.

Run every pair from the repository root with:

```bash
npm run verify:feature-pairs
```

The command compiles the current Java sources with JDK 21, executes both
standalone adapters, compares canonical `EditorSpec` content, requires identical
generated editor artifacts, and runs domain-neutral browser smoke tests. Set
`ECLIPSE_PLUGINS` when Eclipse is installed outside the default macOS path, or
set `JAVA_HOME` to select another JDK 21 installation.

## Equivalence boundary

The paired examples compare semantic output, not source syntax. The Ecore route
uses structural elements and `EAnnotation` details; the DSL route uses Xtext
keywords. These different notations are equivalent only when they produce the
same relevant `EditorSpec` values.

The canonical comparison will:

- compare categories, block types, inheritance, connections, ordered inputs,
  fields, statement inputs, value inputs, references, validations, code
  metadata, runtime metadata, toolbox type, and workspace options;
- ignore serialization order that is not part of the model contract;
- normalize `nsURI` and `nsPrefix`, because Ecore supplies them explicitly while
  the DSL adapter derives them from the domain name;
- require explicit `referenceLabelField` in paired DSL inputs instead of relying
  on the Ecore adapter's automatic `id`, `displayName`, `title`, or `name`
  fallback;
- use finite upper bounds in paired examples, avoiding a comparison between
  Ecore `-1` and the intermediate model's normalized unbounded value `0`.

## Pair 01: basic structure

This pair establishes the model skeleton and connection inference without
explicit categories. Omitting categories intentionally exercises automatic
category generation in both adapters. Both sources declare `inputsInline=false`
explicitly: the DSL metamodel currently uses the first `BoolVal` literal
(`true`) when the clause is omitted, whereas the Ecore adapter leaves the
corresponding value unset. Making the layout intent explicit keeps this pair
focused on structural equivalence.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Domain identity | `EPackage` name and namespace | `domain` | `domainName`; normalized namespace identity | `00-zero-annotation-defaults` |
| Concrete class | concrete `EClass` | `class` | concrete `BlockTypeSpec` | `00-zero-annotation-defaults` |
| Abstract class | `EClass abstract="true"` | `abstract class` | abstract block type, omitted from instantiable blocks | `07-abstract-inheritance` |
| Single inheritance | `eSuperTypes` | `extends` | `superTypeName` and typed connections | `07-abstract-inheritance` |
| Root/container inference | root `EClass` with containment | root class with `contains` | connection type `NONE` | `04-containment-statement` |
| Contained block inference | containment target | target of `contains` | typed statement connection | `04-containment-statement` |
| Free block inference | standalone concrete `EClass` | standalone concrete class | connection type `FREE` | `00-zero-annotation-defaults` |
| Automatic categories | no category annotation | no category declaration/reference | inheritance-based category tree | `15-auto-category` |

## Pair 02: fields and UI metadata

This pair concentrates field types and presentation metadata in a small style
domain. It also introduces explicit and nested categories, so it does not test
the automatic-category branch covered by Pair 01. Widget and variant values are
written explicitly in both sources because the DSL metamodel uses the first
enum literals (`text` and `default`) when those clauses are omitted.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Explicit category | `blockly.category` | `category` plus class `category` | named category and block membership | `00-annotation-customization` |
| Nested category | slash-separated category path | nested category declaration | category children | `12-nested-category` |
| Class presentation | `blockly` and class `ui` annotations | class options | label, colour, message, tooltip, help URL, inline layout | `11-custom-message`, `17-class-presentation` |
| Text field | `EString` | `string` | field type `TEXT` | `01-text-field` |
| Integer and float fields | numeric EDataTypes | `int`, `float` | `INTEGER` and `FLOAT` fields | `03-typed-fields` |
| Boolean field | `EBoolean` | `boolean` | `BOOLEAN` field | `03-typed-fields` |
| Enum dropdown | `EEnum` attribute | inline `enum` | dropdown options and labels | `02-enum-dropdown` |
| Colour, angle, image, and label fields | field-type annotation | dedicated DSL simple type | specialised field type and image metadata | `10-image-angle-label` |
| Defaults and numeric limits | default literal plus `min`/`max` annotations | `default`, `min`, `max` | default value and limits | `03-typed-fields`, `18-field-overrides` |
| Multi-valued attribute | EAttribute multiplicity | attribute cardinality | text representation plus field-cardinality metadata | `09-multivalue-field` |
| Attribute widget family | structural-feature `ui.widget` detail | `text`, `textarea`, `number`, `slider`, `switch`, `checkbox`, `select`, `radio`, `color`, `angle`, `image` | field widget metadata without compatibility warnings | `03-typed-fields`, `10-image-angle-label`, `19-ui-metadata` |
| UI text | structural-feature `ui` annotation | `uiLabel`, `help`, `placeholder` | field UI text metadata | `19-ui-metadata` |
| UI layout and visibility | structural-feature `ui` annotation | `group`, `order`, `readonly`, `hidden`, and the three `variant` values | grouping, order, visibility, and variant metadata | `19-ui-metadata` |

## Pair 03: relations and inputs

This pair is the focused relational example intended to replace large Ecore XML
fragments in explanatory material. It contains a root, contained blocks,
an expression input, a dynamic reference, an id/label field, and an opposite
reference. UI order is explicit on every relation input because the DSL stores
`UiOptions.order` as an integer whose implicit value becomes `0` once a UI
options clause exists, while an omitted Ecore `ui.order` remains unset.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Containment | containment `EReference` | `contains` | `StatementInputSpec` | `04-containment-statement` |
| Containment cardinality | reference bounds | `[lower..upper]` | input bounds and cardinality validation | `04-containment-statement` |
| Value input | containment annotated `type=input_value` | `value` | `ValueInputSpec` | `05-value-input-shadow` |
| Shadow block | reference `shadow` detail | `shadow` | `shadowBlockType` | `05-value-input-shadow` |
| Non-containment reference | ordinary `EReference` | `reference` | `ReferenceFieldSpec` and dynamic dropdown | `06-reference-dropdown` |
| Required reference | lower bound at least one | `required` or lower bound | required flag and validation | `06-reference-dropdown` |
| Reference label | `ui.referenceLabelField` | `referenceLabelField` | selected display field | `06-reference-dropdown`, `20-id-reference-label` |
| Relation/input widgets | structural-feature `ui.widget` detail | `reference-select`, `slot`, `expression-slot` | reference, statement, and value-input UI metadata | `06-reference-dropdown`, `19-ui-metadata` |
| Model id | `EAttribute iD="true"` | `modelId` | block `idFieldName` and uniqueness validation | `20-id-reference-label` |
| Opposite reference | `eOpposite` | `opposite` | reciprocal reference name | `21-multireference-opposite` |
| Reference multiplicity | EReference bounds | reference cardinality | bounds and field-cardinality validation | `21-multireference-opposite` |
| Unique and ordered relation | ETypedElement flags | `unique`/`nonUnique`, `ordered`/`unordered` | relation flags and uniqueness validation | `21-multireference-opposite` |
| Mixed declaration order | EStructuralFeature order | feature declaration order | `orderedInputNames` across all input kinds | adapter regression tests |

## Pair 04: validation, generation, and workspace

This pair exercises the metadata that affects validation and generated
artifacts. It includes `Action`, `Alert`, `Navigate`, `Page`, and one output
expression so the model stays small while covering the complete generation
path.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Required validation | lower bound at least one | `required` | required validation rule | `01-text-field` |
| Cardinality validation | structural bounds | cardinality | cardinality rule and visual expression | `04-containment-statement`, `09-multivalue-field` |
| Uniqueness validation | id/unique structural flags | `modelId`/`unique` | uniqueness rule | `20-id-reference-label`, `21-multireference-opposite` |
| Order constraint | `validation.mustFollow` | `constraint ... must follow` | `MUST_FOLLOW` validation | `08-validation-rule` |
| Expression/condition/JS validation aliases | validation annotation details | `validation` with `expression`, `condition`, and `js` kinds | expression rules, visual blocks, and messages | `22-expression-validation` |
| Basic OCL subset | Ecore OCL annotation | `validation ... ocl` | translated browser expression | `23-ocl-validation` |
| Code language and extension | package `code` annotation | domain code options | code metadata | `13-code-template` |
| Per-block code template | class `code.template` annotation | class `code` option | block code template | `13-code-template` |
| Output block | class `blockly.output` annotation | `output class` | output connection and optional type | `16-output-blocks` |
| Runtime kind | package `runtime.kind` annotation | `runtimeKind` | runtime metadata | `27-runtime-kind` |
| Workspace configuration | `blockly.workspace.*` details | nested `workspace` options | typed nested workspace map | `14-workspace-options` |
| Flyout toolbox | `workspace.toolboxType` detail | workspace `toolboxType` | toolbox type | `26-flyout-toolbox` |

## Route-specific coverage

Not every input capability has a meaningful paired spelling. These cases stay
in route-specific tests and must not be presented as cross-route equivalence.

| Capability | Route | Reason and existing coverage |
|---|---|---|
| Subpackages | Ecore only | The DSL has one flat domain declaration; recursive EPackage traversal is covered by `25-subpackages`. |
| Ignored derived/transient/volatile/non-changeable features | Ecore only | These are EMF structural flags with no DSL spelling; covered by `24-ignored-features`. |
| EClass interface flag | Ecore only | The adapter treats interfaces as abstract; the DSL exposes `abstract` but no interface declaration. |
| Broad Ecore numeric aliases | Ecore only | ELong, EShort, EBigInteger, EDouble, and EBigDecimal normalize to the smaller DSL type set. |
| Automatic reference-label fallback | Ecore only automation | Ecore can infer id/displayName/title/name; paired DSL inputs specify `referenceLabelField` explicitly. |
| Explicit namespace URI and prefix | Ecore only input | The DSL adapter synthesizes both values from the domain name, so canonical comparison normalizes them. |
| Standard Ecore/OCL annotation containers | Ecore only notation | The DSL can express the supported OCL subset, but it does not reproduce Ecore annotation containers. |

No DSL-only `EditorSpec` capability has been identified. DSL conveniences such
as `inline`, inline enum declarations, and nested category syntax map to
capabilities also reachable through Ecore annotations or structure.

## Acceptance criteria

A pair is complete only when all of the following are true:

1. `source.ecore` loads as an `EPackage` and `source.m2b` parses, links, and
   validates without errors.
2. Both adapters produce valid EMF `EditorSpec` instances.
3. The canonical comparison passes for every capability assigned to the pair.
4. Both routes serialize and reload their intermediate XMI successfully.
5. Both routes generate the expected HTML and JavaScript artifact set.
6. Generated editors pass the existing smoke test.
7. The full AppMaker Ecore and `.m2b` generation still pass as an integration
   regression.

## Verification workflow

All four source pairs are implemented. The verification command processes them
in numerical order, then runs one browser smoke test for each Ecore and `.m2b`
result. Temporary classes and generated editors are deleted after a successful
run; set `KEEP_FEATURE_PAIR_OUTPUTS=1` to retain them for inspection.
