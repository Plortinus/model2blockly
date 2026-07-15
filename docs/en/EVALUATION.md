# Evaluation Against Existing Blockly Editors

AppMaker is the project's end-to-end integration case: it demonstrates how the
Ecore and `.m2b` routes reach an executable editor. The external evaluation uses
separate evidence. Model2Blockly is compared with ten configurations published
in the official Blockly repositories to measure editor reproduction, generator
reproduction and maintained-source size.

## Evaluation Scope

The unit of analysis is the **Blockly editing subsystem**:

- block types, fields, inputs, connections and layout;
- toolbox categories, shadows and initial workspace configuration;
- editor behavior observable in the browser;
- code generators associated with the blocks.

Host-domain engines are excluded. The Maze map, Turtle canvas, Music audio and
Pond simulations consume the generated program but are not part of the editor
generator. Blockly's Geras renderer is fixed as a control because it determines
the internal presentation of the blocks.

## Corpus and Controls

The corpus contains Graph Demo, JS-Interpreter Wait, Maze, Bird, Movie, Music,
Turtle, Puzzle, Pond Tutor and Pond. Upstream sources are pinned to revisions
from `google/blockly-games` and `google/blockly-samples`. BASELINE and M2B use
Blockly 13.1.1, the Geras renderer, the Classic theme, the English locale and
the same browser and workspace dimensions.

Screenshots do not determine equivalence. Each treatment is converted into a
canonical descriptor and compared through atomic properties with the states
`match`, `partial`, `mismatch`, `unsupported`, `error` and `excluded`.
Screenshots are retained as auxiliary visual evidence.

## Aggregate Results

| Measure | Result |
| --- | ---: |
| Cases loaded without errors | 10/10 |
| Weighted editor structural parity | 2315/2762 (83.82%) |
| Mean / median editor parity per case | 83.41% / 82.71% |
| Weighted generator parity | 227/291 (78.01%) |
| Official source LOC / `.m2b` LOC | 4074 / 1121 |
| Weighted LOC reduction | 72.48% |
| Conservative reduction after deduplicating shared sources | 70.00% |
| Strict Ecore–`.m2b` equivalence | 3/3 cases |

All ten generated editors load, but all ten are classified as **partial**
reproductions. The 83.82% result therefore does not mean that the complete host
applications are equivalent. The main differences involve connection policies,
programmatic toolbox composition, presentation-only inputs and generators that
depend on state, mutators or automatic serialization.

LOC reduction measures the concision of maintained source. It does not measure
development time, cognitive difficulty or human productivity. It must be read
together with functional parity: the specifications are smaller, but they do
not yet express every observed capability.

## Results by Case

| Case | Blocks | Editor parity | Generator parity | Official LOC | `.m2b` LOC | LOC reduction | Classification |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| Graph Demo | 2 | 77/91 (84.62%) | 7/8 (87.50%) | 329 | 28 | 91.49% | partial |
| JS-Interpreter Wait | 1 | 73/89 (82.02%) | 4/4 (100.00%) | 282 | 16 | 94.33% | partial |
| Maze | 5 | 111/137 (81.02%) | 15/15 (100.00%) | 178 | 73 | 58.99% | partial |
| Bird | 8 | 203/218 (93.12%) | 27/31 (87.10%) | 246 | 93 | 62.20% | partial |
| Movie | 5 | 236/283 (83.39%) | 16/20 (80.00%) | 449 | 75 | 83.30% | partial |
| Music | 6 | 186/232 (80.17%) | 19/21 (90.48%) | 545 | 94 | 82.75% | partial |
| Turtle | 12 | 349/427 (81.73%) | 30/40 (75.00%) | 669 | 190 | 71.60% | partial |
| Puzzle | 3 | 59/75 (78.67%) | 6/12 (50.00%) | 221 | 46 | 79.19% | partial |
| Pond Tutor | 11 | 340/397 (85.64%) | 35/44 (79.55%) | 415 | 154 | 62.89% | partial |
| Pond | 24 | 681/813 (83.76%) | 68/96 (70.83%) | 740 | 352 | 52.43% | partial |

## Visual Evidence

The following screenshots show the same browser controls for the official
BASELINE and generated M2B treatments. They support manual inspection but are
not used as a pixel-similarity metric.

### Maze

| Official BASELINE | Generated M2B |
| --- | --- |
| ![Official Maze Blockly editor](../../evaluation/official-blockly/cases/E03_maze/results/screenshots/baseline.png) | ![Model2Blockly Maze editor](../../evaluation/official-blockly/cases/E03_maze/results/screenshots/m2b.png) |

### Pond

| Official BASELINE | Generated M2B |
| --- | --- |
| ![Official Pond Blockly editor](../../evaluation/official-blockly/cases/E10_pond_duck/results/screenshots/baseline.png) | ![Model2Blockly Pond editor](../../evaluation/official-blockly/cases/E10_pond_duck/results/screenshots/m2b.png) |

## Ecore and `.m2b` Routes

Graph, Maze and Turtle were modeled through both input routes. Each pair had to
be exactly equal in controls, blocks, toolbox, initial workspace, generators,
errors and loadability. All three pairs are equivalent.

Ecore and `.m2b` are not compared through LOC because XML/XMI and a textual DSL
are different editing units. The relevant question is whether both adapters
produce the same common editor specification.

## Reproduce and Inspect the Evidence

```bash
npm run verify:evaluation-completed
npm run aggregate:evaluation
```

The complete evidence is stored in:

- [experiment overview](../../evaluation/official-blockly/README.md);
- [evaluation protocol](../../evaluation/official-blockly/protocol.md);
- [generated aggregate summary](../../evaluation/official-blockly/results/summary.md);
- [aggregate JSON results](../../evaluation/official-blockly/results/aggregate.json);
- [observed support boundaries](../../evaluation/official-blockly/support-boundaries.md);
- [corpus revisions and controls](../../evaluation/official-blockly/manifest.json).
