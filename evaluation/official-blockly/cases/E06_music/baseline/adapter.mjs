const blockTypes = [
  'music_pitch',
  'music_note',
  'music_rest_whole',
  'music_rest',
  'music_instrument',
  'music_start',
];

const adapter = {
  caseId: 'E06_music',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    music_pitch: [
      'pitch-note-conversion',
      'pitch-picker',
      'pitch-range-validation',
    ],
  },
  generatorMetadata: {
    music_pitch: {
      kind: 'value',
      template: '{{PITCH}}',
    },
    music_note: {
      kind: 'statement',
      template: "play({{DURATION}}, {{value:PITCH}}, 'block_id_{{id}}');",
      sideEffects: ['missing-pitch-default-seven'],
    },
    music_rest_whole: {
      kind: 'statement',
      template: "rest(1, 'block_id_{{id}}');",
    },
    music_rest: {
      kind: 'statement',
      template: "rest({{DURATION}}, 'block_id_{{id}}');",
    },
    music_instrument: {
      kind: 'statement',
      template: "setInstrument('{{INSTRUMENT}}');",
    },
    music_start: {
      kind: 'statement',
      template: 'function start{{counter}}() {\n{{statements:STACK}}\n}',
      sideEffects: ['definition-injection-thread-counter'],
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    ensureAssetBase();
    const [
      blockSource,
      generatorSource,
      counterSource,
      fieldPitchSource,
      pitchStyleSource,
      toolboxSource,
      initialWorkspaceSource,
      gameNameSource,
      categoryMessagesSource,
      musicMessagesSource,
    ] = await Promise.all([
      load('./upstream/block-definitions.js'),
      load('./upstream/generators.js'),
      load('./upstream/start-counter.js'),
      load('./upstream/field-pitch.js'),
      load('./upstream/pitch-picker-style.css'),
      load('./upstream/level-10-toolbox.js'),
      load('./upstream/level-10-initial-workspace.js'),
      load('./upstream/game-name-message.jsonfrag'),
      load('./upstream/category-messages.jsonfrag'),
      load('./upstream/music-editor-messages.jsonfrag'),
    ]);

    const messages = Function(`return ({${gameNameSource}${categoryMessagesSource}${musicMessagesSource}});`)();
    const BlocklyGames = {
      LEVEL: 10,
      MAX_LEVEL: 10,
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Music = { Blocks: {}, html: {}, startCount: {} };
    window.BlocklyMsg = Blockly.Msg;
    Blockly.JavaScript = javascriptGenerator;

    const style = document.createElement('style');
    style.textContent = pitchStyleSource;
    document.head.appendChild(style);
    Function('Blockly', fieldPitchSource)(Blockly);
    Function('Music', counterSource)(Music);
    Function('Blockly', 'BlocklyGames', 'Music', blockSource)(Blockly, BlocklyGames, Music);
    Music.Blocks.init();
    Function('Blockly', 'Music', generatorSource)(Blockly, Music);
    for (const type of blockTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }

    Function('BlocklyGames', 'Music', toolboxSource)(BlocklyGames, Music);
    const toolboxXml = Blockly.utils.xml.textToDom(Music.html.toolbox_(10));
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
    Function(
      'BlocklyGames',
      'BlocklyInterface',
      'transform10',
      initialWorkspaceSource,
    )(BlocklyGames, BlocklyInterface, () => {});
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
