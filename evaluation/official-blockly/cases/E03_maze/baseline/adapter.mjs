const blockTypes = [
  'maze_moveForward',
  'maze_turn',
  'maze_forever',
  'maze_if',
  'maze_ifElse',
];

const adapter = {
  caseId: 'E03_maze',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {
    maze_moveForward: {
      kind: 'statement',
      template: "moveForward('block_id_{{id}}');",
    },
    maze_turn: {
      kind: 'statement',
      template: "{{DIR}}('block_id_{{id}}');",
    },
    maze_forever: {
      kind: 'statement',
      template: "while (notDone()) {\n{{statements:DO}}\n}",
      sideEffects: ['infinite-loop-trap'],
    },
    maze_if: {
      kind: 'statement',
      template: "if ({{DIR}}('block_id_{{id}}')) {\n{{statements:DO}}\n}",
    },
    maze_ifElse: {
      kind: 'statement',
      template: "if ({{DIR}}('block_id_{{id}}')) {\n{{statements:DO}}\n} else {\n{{statements:ELSE}}\n}",
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    ensureAssetBase();
    const [messagesSource, blocksSource, generatorsSource, toolboxSource] = await Promise.all([
      load('./upstream/english-editor-messages.jsonfrag'),
      load('./upstream/block-definitions.js'),
      load('./upstream/generators.js'),
      load('./upstream/level-10-toolbox.js'),
    ]);
    const messages = Function(`return ({${messagesSource}});`)();
    const BlocklyGames = {
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Maze = { Blocks: {}, html: {} };
    window.BlocklyMsg = Blockly.Msg;
    Blockly.JavaScript = javascriptGenerator;

    Function('Blockly', 'BlocklyGames', 'Maze', 'window', blocksSource)(Blockly, BlocklyGames, Maze, window);
    Maze.Blocks.init();
    Function('Blockly', generatorsSource)(Blockly);
    for (const type of blockTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }

    Function('BlocklyGames', 'Maze', toolboxSource)(BlocklyGames, Maze);
    const toolboxXml = Blockly.utils.xml.textToDom(Maze.html.toolbox_(10));
    adapter.toolbox = Blockly.utils.toolbox.convertToolboxDefToJson(toolboxXml);
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
