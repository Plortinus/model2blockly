const birdTypes = [
  'bird_noWorm',
  'bird_heading',
  'bird_position',
  'bird_compare',
  'bird_and',
  'bird_ifElse',
  'math_number',
];

const blockTypes = [...birdTypes, 'controls_if'];

const adapter = {
  caseId: 'E04_bird',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    bird_compare: ['dropdown-dependent-tooltip'],
    math_number: ['parent-tooltip-when-inline'],
  },
  generatorMetadata: {
    bird_noWorm: {
      kind: 'value',
      template: 'noWorm()',
    },
    bird_heading: {
      kind: 'statement',
      template: "heading({{ANGLE}}, 'block_id_{{id}}');",
    },
    bird_position: {
      kind: 'value',
      template: 'get{{XY}}()',
    },
    bird_compare: {
      kind: 'value',
      template: '{{value:A}} {{mapped:OP:LT=<,GT=>}} {{value:B}}',
      sideEffects: ['missing-values-default-zero'],
    },
    bird_and: {
      kind: 'value',
      template: '{{value:A}} && {{value:B}}',
      sideEffects: ['missing-operands-boolean-identity'],
    },
    bird_ifElse: {
      kind: 'statement',
      template: 'if ({{value:CONDITION}}) {\n{{statements:DO}}\n} else {\n{{statements:ELSE}}\n}',
      sideEffects: ['missing-condition-default-false'],
    },
    math_number: {
      kind: 'value',
      template: '{{NUM}}',
    },
    controls_if: null,
  },
  async register({ Blockly, javascriptGenerator }) {
    const [
      messagesSource,
      blocksSource,
      birdGeneratorsSource,
      controlsIfSource,
      mathGeneratorSource,
      toolboxSource,
      initialWorkspaceSource,
    ] = await Promise.all([
      load('./upstream/english-editor-messages.jsonfrag'),
      load('./upstream/block-definitions.js'),
      load('./upstream/bird-generators.js'),
      load('./upstream/controls-if-singleton.js'),
      load('./upstream/math-number-generator.js'),
      load('./upstream/level-10-toolbox.js'),
      load('./upstream/level-10-initial-workspace.js'),
    ]);

    const messages = Function(`return ({${messagesSource}});`)();
    const BlocklyGames = {
      LEVEL: 10,
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Bird = { Blocks: {}, html: {} };
    window.BlocklyMsg = Blockly.Msg;
    Blockly.JavaScript = javascriptGenerator;

    Function('Blockly', 'BlocklyGames', 'Bird', 'window', blocksSource)(Blockly, BlocklyGames, Bird, window);
    Bird.Blocks.init();
    Function('Blockly', controlsIfSource)(Blockly);
    Function('Blockly', birdGeneratorsSource)(Blockly);
    Function('Blockly', mathGeneratorSource)(Blockly);
    for (const type of birdTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }

    Function('Bird', toolboxSource)(Bird);
    const toolboxXml = Blockly.utils.xml.textToDom(Bird.html.toolbox_(10));
    adapter.toolbox = Blockly.utils.toolbox.convertToolboxDefToJson(toolboxXml);

    const BlocklyInterface = {
      loadBlocks(xmlText) {
        const workspace = new Blockly.Workspace();
        try {
          const xml = Blockly.utils.xml.textToDom(xmlText);
          Blockly.Xml.domToWorkspace(xml, workspace);
          adapter.initialWorkspace = Blockly.serialization.workspaces.save(workspace);
        } finally {
          workspace.dispose();
        }
      },
    };
    Function('BlocklyGames', 'BlocklyInterface', initialWorkspaceSource)(BlocklyGames, BlocklyInterface);
  },
};

export default adapter;

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
