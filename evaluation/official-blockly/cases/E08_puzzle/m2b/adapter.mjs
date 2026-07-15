const blockTypes = ['animal', 'picture', 'trait'];

const adapter = {
  caseId: 'E08_puzzle',
  treatment: 'm2b',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    ensureAssetBase();
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated/html/Puzzle_blocks.js'),
      load('../generated/html/Puzzle_toolbox.js'),
      load('../generated/html/Puzzle_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    adapter.generatorMetadata = Object.fromEntries(blockTypes.map((type) => {
      const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.[type] || {};
      return [type, {
        kind: type === 'picture' ? 'value' : 'statement',
        template: config.template ?? null,
      }];
    }));

    if (javascriptGenerator !== javascript.javascriptGenerator) {
      throw new Error('Unexpected JavaScript generator instance.');
    }
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
