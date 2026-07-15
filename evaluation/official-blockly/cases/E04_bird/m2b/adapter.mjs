const blockTypes = [
  'bird_noWorm',
  'bird_heading',
  'bird_position',
  'bird_compare',
  'bird_and',
  'bird_ifElse',
  'math_number',
  'controls_if',
];

const valueTypes = new Set([
  'bird_noWorm',
  'bird_position',
  'bird_compare',
  'bird_and',
  'math_number',
]);

const adapter = {
  caseId: 'E04_bird',
  treatment: 'm2b',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated/html/Bird_blocks.js'),
      load('../generated/html/Bird_toolbox.js'),
      load('../generated/html/Bird_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    adapter.generatorMetadata = Object.fromEntries(blockTypes.map((type) => {
      if (type === 'controls_if') return [type, null];
      const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.[type] || {};
      return [type, {
        kind: valueTypes.has(type) ? 'value' : 'statement',
        template: config.template ?? null,
      }];
    }));

    if (javascriptGenerator !== javascript.javascriptGenerator) {
      throw new Error('Unexpected JavaScript generator instance.');
    }
  },
};

export default adapter;

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
