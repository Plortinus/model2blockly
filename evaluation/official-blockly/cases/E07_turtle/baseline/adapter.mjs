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
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    turtle_turn: ['extension:turtle_turn_arrows'],
    turtle_turn_internal: ['extension:turtle_turn_arrows'],
  },
  generatorMetadata: {
    turtle_move: {
      kind: 'statement',
      template: "{{DIR}}({{value:VALUE}}, 'block_id_{{id}}');",
      sideEffects: ['missing-value-default-zero'],
    },
    turtle_move_internal: {
      kind: 'statement',
      template: "{{DIR}}(Number({{VALUE}}), 'block_id_{{id}}');",
    },
    turtle_turn: {
      kind: 'statement',
      template: "{{DIR}}({{value:VALUE}}, 'block_id_{{id}}');",
      sideEffects: ['missing-value-default-zero'],
    },
    turtle_turn_internal: {
      kind: 'statement',
      template: "{{DIR}}(Number({{VALUE}}), 'block_id_{{id}}');",
    },
    turtle_width: {
      kind: 'statement',
      template: "penWidth({{value:WIDTH}}, 'block_id_{{id}}');",
      sideEffects: ['missing-width-default-one'],
    },
    turtle_pen: {
      kind: 'statement',
      template: "{{PEN}}('block_id_{{id}}');",
    },
    turtle_colour: {
      kind: 'statement',
      template: "penColour({{value:COLOUR}}, 'block_id_{{id}}');",
      sideEffects: ['missing-colour-default-black'],
    },
    turtle_colour_internal: {
      kind: 'statement',
      template: "penColour(quote({{COLOUR}}), 'block_id_{{id}}');",
    },
    turtle_visibility: {
      kind: 'statement',
      template: "{{VISIBILITY}}('block_id_{{id}}');",
    },
    turtle_print: {
      kind: 'statement',
      template: "print({{value:TEXT}}, 'block_id_{{id}}');",
      sideEffects: ['missing-text-default-empty-string'],
    },
    turtle_font: {
      kind: 'statement',
      template: "font(quote({{FONT}}), Number({{FONTSIZE}}), quote({{FONTSTYLE}}), 'block_id_{{id}}');",
    },
    turtle_repeat_internal: {
      kind: 'statement',
      template: '{{builtin:controls_repeat}}',
      sideEffects: ['loop-variable-allocation', 'infinite-loop-trap'],
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const [
      blockSource,
      generatorSource,
      toolboxSource,
      initialWorkspaceSource,
      gameNameSource,
      categoryMessagesSource,
      turtleMessagesSource,
    ] = await Promise.all([
      load('./upstream/block-definitions.js'),
      load('./upstream/generators.js'),
      load('./upstream/level-10-toolbox.js'),
      load('./upstream/level-10-initial-workspace.js'),
      load('./upstream/game-name-message.jsonfrag'),
      load('./upstream/category-messages.jsonfrag'),
      load('./upstream/turtle-editor-messages.jsonfrag'),
    ]);

    const messages = Function(`return ({${gameNameSource}${categoryMessagesSource}${turtleMessagesSource}});`)();
    const BlocklyGames = {
      LEVEL: 10,
      MAX_LEVEL: 10,
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Turtle = { Blocks: {}, html: {} };
    window.BlocklyMsg = Blockly.Msg;
    Blockly.JavaScript = javascriptGenerator;

    Function('Blockly', 'BlocklyGames', 'Turtle', blockSource)(Blockly, BlocklyGames, Turtle);
    Turtle.Blocks.init();
    Blockly.JavaScript.controls_repeat = javascriptGenerator.forBlock.controls_repeat_ext;
    Function('Blockly', generatorSource)(Blockly);
    for (const type of blockTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }

    Function('BlocklyGames', 'Turtle', toolboxSource)(BlocklyGames, Turtle);
    const toolboxXml = Blockly.utils.xml.textToDom(Turtle.html.toolbox_(10));
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
    Function('BlocklyGames', 'BlocklyInterface', 'transform10', initialWorkspaceSource)(
      BlocklyGames,
      BlocklyInterface,
      (xml) => xml,
    );
  },
};

export default adapter;

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
