const adapter = {
  caseId: 'E02_interpreter_wait',
  treatment: 'baseline',
  blockTypes: ['wait_seconds'],
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {
    wait_seconds: {
      kind: 'statement',
      template: 'waitForSeconds({{SECONDS}});',
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blockSource, generatorSource, toolboxSource, initialSource] = await Promise.all([
      load('./upstream/wait-block-definition.js'),
      load('./upstream/wait-generator.js'),
      load('./upstream/selected-toolbox.js'),
      load('./upstream/initial-workspace.js'),
    ]);

    Function('Blockly', blockSource)(Blockly);
    Function('Blockly', 'javascript', generatorSource)(Blockly, javascript);
    adapter.toolbox = Function('Blockly', `${toolboxSource}\nreturn toolboxJson;`)(Blockly);
    adapter.initialWorkspace = Function('Blockly', `${initialSource}\nreturn startBlocks;`)(Blockly);

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
