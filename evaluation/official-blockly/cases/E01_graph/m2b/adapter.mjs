const adapter = {
  caseId: 'E01_graph',
  treatment: 'm2b',
  blockTypes: ['graph_get_x', 'graph_set_y'],
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated/html/Graph_blocks.js'),
      load('../generated/html/Graph_toolbox.js'),
      load('../generated/html/Graph_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    adapter.generatorMetadata = Object.fromEntries(adapter.blockTypes.map((type) => {
      const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.[type] || {};
      return [type, {
        kind: type === 'graph_get_x' ? 'value' : 'statement',
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
