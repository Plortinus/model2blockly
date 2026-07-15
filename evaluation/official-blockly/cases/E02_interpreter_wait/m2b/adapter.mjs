const adapter = {
  caseId: 'E02_interpreter_wait',
  treatment: 'm2b',
  blockTypes: ['wait_seconds'],
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated/html/InterpreterWait_blocks.js'),
      load('../generated/html/InterpreterWait_toolbox.js'),
      load('../generated/html/InterpreterWait_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.wait_seconds || {};
    adapter.generatorMetadata = {
      wait_seconds: {
        kind: 'statement',
        template: config.template ?? null,
      },
    };

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
