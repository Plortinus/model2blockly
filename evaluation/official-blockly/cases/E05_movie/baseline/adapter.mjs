const blockTypes = [
  'movie_circle',
  'movie_rect',
  'movie_line',
  'movie_time',
  'movie_colour',
];

const adapter = {
  caseId: 'E05_movie',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {
    movie_circle: {
      kind: 'statement',
      template: 'circle({{value:X}}, {{value:Y}}, {{value:RADIUS}});',
      sideEffects: ['missing-values-default-zero'],
    },
    movie_rect: {
      kind: 'statement',
      template: 'rect({{value:X}}, {{value:Y}}, {{value:WIDTH}}, {{value:HEIGHT}});',
      sideEffects: ['missing-values-default-zero'],
    },
    movie_line: {
      kind: 'statement',
      template: 'line({{value:X1}}, {{value:Y1}}, {{value:X2}}, {{value:Y2}}, {{value:WIDTH}});',
      sideEffects: ['missing-values-default-zero'],
    },
    movie_time: {
      kind: 'value',
      template: 'time()',
    },
    movie_colour: {
      kind: 'statement',
      template: 'penColour({{value:COLOUR}});',
      sideEffects: ['missing-colour-default-black'],
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const [
      blockSource,
      generatorSource,
      toolboxSource,
      gameNameSource,
      categoryMessagesSource,
      movieMessagesSource,
    ] = await Promise.all([
      load('./upstream/block-definitions.js'),
      load('./upstream/generators.js'),
      load('./upstream/level-10-toolbox.js'),
      load('./upstream/game-name-message.jsonfrag'),
      load('./upstream/category-messages.jsonfrag'),
      load('./upstream/movie-editor-messages.jsonfrag'),
    ]);

    const messages = Function(`return ({${gameNameSource}${categoryMessagesSource}${movieMessagesSource}});`)();
    const BlocklyGames = {
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Movie = { Blocks: {}, html: {} };
    window.BlocklyMsg = Blockly.Msg;
    Blockly.JavaScript = javascriptGenerator;

    Function('Blockly', 'BlocklyGames', 'Movie', blockSource)(Blockly, BlocklyGames, Movie);
    Movie.Blocks.init();
    Function('Blockly', generatorSource)(Blockly);
    for (const type of blockTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }

    Function('BlocklyGames', 'Movie', toolboxSource)(BlocklyGames, Movie);
    const toolboxXml = Blockly.utils.xml.textToDom(Movie.html.toolbox_(10));
    adapter.toolbox = Blockly.utils.toolbox.convertToolboxDefToJson(toolboxXml);
  },
};

export default adapter;

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
