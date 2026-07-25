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
    basicStructure.ecore
    basicStructure.m2b
  02_enhanced_app/
    enhancedApp.ecore
    enhancedApp.m2b
  06_codegen_workspace/
    codegenWorkspace.ecore
    codegenWorkspace.m2b
```

The default verification command generates reproducible HTML, JavaScript, and
XMI artifacts in a temporary directory. To retain both generated editors next
to each source pair for inspection or screenshots, run:

```bash
npm run generate:feature-pairs
```

The persistent form uses this additional layout in every pair directory:

```text
generated/
  ecore/
    generation_report.html
    html/
    intermediate/
  dsl/
    generation_report.html
    html/
    intermediate/
screenshots/
  ecore.png
  dsl.png
```

The `generated` directory is replaced on every persistent run. The
`screenshots` directory is independent so regenerating the editors does not
delete the visual evidence.

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
- compare the automatically inferred reference label field (`id`,
  `displayName`, `title`, or `name`) in both routes;
- use finite upper bounds in paired examples, avoiding a comparison between
  Ecore `-1` and the intermediate model's normalized unbounded value `0`.

## Pair 01: basic structure

This pair describes a compact but realistic App Maker. An `App` contains one to
five `Page` elements, and each page contains `Label`, `Button`, and `Image`
components through an abstract `Component` type. Application, page, and
component properties exercise common text, enum, boolean, numeric, colour,
angle, and multi-value fields. Ecore structure supplies the domain semantics,
while optional `blockly` annotations supply only Blockly-specific presentation
details. Both adapters remain structurally equivalent.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Domain identity | `EPackage` name and namespace | `domain` | `domainName`; normalized namespace identity | `00-zero-annotation-defaults` |
| Concrete class | concrete `EClass` | `class` | concrete `BlockTypeSpec` | `00-zero-annotation-defaults` |
| Abstract class and inheritance | abstract `Component` plus `eSuperTypes` | `abstract class` plus `extends` | abstract type omitted as a block; typed child connections | `07-abstract-inheritance` |
| Root/container inference | root `EClass` with containment | root class with `contains` | connection type `NONE` | `04-containment-statement` |
| Contained block inference | containment target | target of `contains` | typed statement connection | `04-containment-statement` |
| Text attributes and defaults | `EString` attributes and default literals | `attribute ... : string ... default` | `TEXT` fields with initial values | `01-text-field` |
| Typed application fields | enums, booleans, integers, floats, colour and angle annotations | `enum`, `boolean`, `int`, `float`, `colour`, and `angle` | dropdown, checkbox, number, colour, and angle fields | `02-enum-dropdown`, `03-typed-fields`, `10-image-angle-label` |
| Multi-valued tags | `tags` upper bound `3` | `attribute tags : string [0..3]` | `many=true` and multivalue editor | `09-multivalue-field` |
| Containment cardinality | containment bounds `1..5` and `0..10` | `contains Page pages [1..5]` and `contains Component components [0..10]` | cardinality validation for both containments | `04-containment-statement` |
| Explicit categories | class category annotations | category declarations and class references | `Application`, `Pages`, and `Components` categories | `00-annotation-customization` |

## Pair 02: interactive App Maker logic and typed connections

This pair extends the App Maker introduced by Pair 01 without repeating its
field-mapping focus. It preserves `App -> Page -> Component`, then adds a
button-click action sequence. Action blocks use typed statement connections, while
message content uses a typed value input, an output block, and a replaceable
shadow block. The pair therefore demonstrates how generated blocks compose into
interaction logic.

| Capability | Ecore notation | `.m2b` notation | Expected `EditorSpec` evidence | Existing Ecore gallery seed |
|---|---|---|---|---|
| Preserved App Maker structure | the same EClass and containment pattern as Pair 01 | the same class and `contains` pattern | the original application, page, and component blocks | `04-containment-statement`, `07-abstract-inheritance` |
| Action sequences | multi-valued containment of abstract `Action` | `contains Action` | typed statement inputs and stackable action blocks | `04-containment-statement`, `07-abstract-inheritance` |
| Typed value input | contained reference annotated as `input_value` with `check` | `value TextExpression` | a horizontal input accepting only text-producing blocks | `05-value-input-shadow` |
| Output block | `output=TextExpression` | `output as TextExpression` | `TextLiteral` with a typed output connector | `06-output-blocks` |
| Default shadow block | `shadow=TextLiteral` | `shadow TextLiteral` | a replaceable default value in a newly created message action | `05-value-input-shadow` |

## Route-specific coverage

Not every input capability has a meaningful paired spelling. These cases stay
in route-specific tests and must not be presented as cross-route equivalence.

| Capability | Route | Reason and existing coverage |
|---|---|---|
| Subpackages | Ecore only | The DSL has one flat domain declaration; recursive EPackage traversal is covered by `25-subpackages`. |
| Ignored derived/transient/volatile/non-changeable features | Ecore only | These are EMF structural flags with no DSL spelling; covered by `24-ignored-features`. |
| EClass interface flag | Ecore only | The adapter treats interfaces as abstract; the DSL exposes `abstract` but no interface declaration. |
| Broad Ecore numeric aliases | Ecore only | ELong, EShort, EBigInteger, EDouble, and EBigDecimal normalize to the smaller DSL type set. |
| Automatic reference-label fallback | shared automation | Both routes infer id/displayName/title/name when no explicit label field is supplied. |
| Explicit namespace URI and prefix | Ecore only input | The DSL adapter synthesizes both values from the domain name, so canonical comparison normalizes them. |
| Standard Ecore/OCL annotation containers | Ecore only notation | The DSL can express the supported OCL subset, but it does not reproduce Ecore annotation containers. |

No DSL-only `EditorSpec` capability has been identified. DSL conveniences such
as `inline`, inline enum declarations, and nested category syntax map to
capabilities also reachable through Ecore annotations or structure.

## Acceptance criteria

A pair is complete only when all of the following are true:

1. The descriptively named `.ecore` file loads as an `EPackage`, and its
   same-basename `.m2b` counterpart parses, links, and validates without errors.
2. Both adapters produce valid EMF `EditorSpec` instances.
3. The canonical comparison passes for every capability assigned to the pair.
4. Both routes serialize and reload their intermediate XMI successfully.
5. Both routes generate the expected HTML and JavaScript artifact set.
6. Generated editors pass the existing smoke test.
7. The full AppMaker Ecore and `.m2b` generation still pass as an integration
   regression.

## Verification workflow

All three source pairs are implemented. The verification command processes them
in numerical order, then runs one browser smoke test for each Ecore and `.m2b`
result. Temporary classes and generated editors are deleted after a successful
run; set `KEEP_FEATURE_PAIR_OUTPUTS=1` to retain them for inspection.
