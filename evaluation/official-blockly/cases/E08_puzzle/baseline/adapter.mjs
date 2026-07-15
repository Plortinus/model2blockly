const blockTypes = ['animal', 'picture', 'trait'];

const adapter = {
  caseId: 'E08_puzzle',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    animal: [
      'data-driven-leg-menu',
      'data-driven-populate',
      'help-url-from-mutation',
    ],
    picture: [
      'data-driven-image',
      'data-driven-populate',
    ],
    trait: [
      'data-driven-label',
      'data-driven-populate',
    ],
  },
  generatorMetadata: {
    animal: null,
    picture: null,
    trait: null,
  },
  prepareBlock({ type, block }) {
    if (type === 'animal') block.populate(1);
    if (type === 'picture') block.populate(1);
    if (type === 'trait') block.populate(1, 1);
  },
  async register({ Blockly }) {
    ensureAssetBase();
    Blockly.ALIGN_RIGHT = Blockly.inputs.Align.RIGHT;
    const [messagesSource, dataSource, blocksSource] = await Promise.all([
      load('./upstream/puzzle-editor-messages.jsonfrag'),
      load('./upstream/data.js'),
      load('./upstream/block-runtime.js'),
    ]);
    const messages = Function(`return ({${messagesSource}});`)();
    const BlocklyGames = {
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Puzzle = { Blocks: {}, data: {} };

    Function('BlocklyGames', 'Puzzle', dataSource)(BlocklyGames, Puzzle);
    Function('Blockly', 'BlocklyGames', 'Puzzle', blocksSource)(Blockly, BlocklyGames, Puzzle);

    const data = Puzzle.data.getData();
    adapter.initialWorkspace = {
      layout: 'randomized-top-level-inventory',
      deletable: false,
      blocks: data.flatMap((animal, animalIndex) => [
        { type: 'animal', animal: animalIndex + 1 },
        { type: 'picture', animal: animalIndex + 1 },
        ...animal.traits.map((_, traitIndex) => ({
          type: 'trait',
          animal: animalIndex + 1,
          trait: traitIndex + 1,
        })),
      ]),
    };
  },
};

export default adapter;

function ensureAssetBase() {
  const base = document.createElement('base');
  base.href = new URL('../assets/', import.meta.url).href;
  document.head.prepend(base);
}

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
