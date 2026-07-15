const blockTypes = [
  'turtle_move',
  'turtle_move_internal',
  'turtle_turn',
  'turtle_turn_internal',
  'turtle_width',
  'turtle_pen',
  'turtle_colour',
  'turtle_colour_internal',
  'turtle_visibility',
  'turtle_print',
  'turtle_font',
  'turtle_repeat_internal',
];

const adapter = {
  caseId: 'E07_turtle',
  treatment: 'ecore',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated-ecore/html/Turtle_blocks.js'),
      load('../generated-ecore/html/Turtle_toolbox.js'),
      load('../generated-ecore/html/Turtle_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    adapter.generatorMetadata = Object.fromEntries(blockTypes.map((type) => {
      const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.[type] || {};
      return [type, {
        kind: 'statement',
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
