const adapter = {
  caseId: 'E01_graph',
  treatment: 'baseline',
  blockTypes: ['graph_get_x', 'graph_set_y'],
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    graph_set_y: ['generator-side-effect:set-deletable-false'],
  },
  generatorMetadata: {
    graph_get_x: { kind: 'value', template: 'x' },
    graph_set_y: {
      kind: 'statement',
      template: 'y = {{value:VALUE}};',
      sideEffects: ['set-deletable-false'],
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [toolboxSource, initialSource, getBlock, getGenerator, setBlock, setGenerator] = await Promise.all([
      load('./upstream/toolbox.js'),
      load('./upstream/initial-workspace.js'),
      load('./upstream/graph-get-x-block.js'),
      load('./upstream/graph-get-x-generator.js'),
      load('./upstream/graph-set-y-block.js'),
      load('./upstream/graph-set-y-generator.js'),
    ]);

    adapter.toolbox = Function('Blockly', `${toolboxSource}\nreturn toolbox;`)(Blockly);
    adapter.initialWorkspace = Function('Blockly', `${initialSource}\nreturn startBlocks;`)(Blockly);
    Function('Blockly', 'javascript', getBlock)(Blockly, javascript);
    Function('Blockly', 'javascript', getGenerator)(Blockly, javascript);
    Function('Blockly', 'javascript', setBlock)(Blockly, javascript);
    Function('Blockly', 'javascript', setGenerator)(Blockly, javascript);

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
