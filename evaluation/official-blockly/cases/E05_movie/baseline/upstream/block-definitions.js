Movie.Blocks.init = function() {
  /**
   * Common HSV hue for all shape blocks.
   */
  const SHAPE_HUE = 160;

  /**
   * Create a value input, numeric, right-aligned.
   * @param {string} name Name of input.
   * @returns {!Object} JSON structure for value input.
   */
  function inputFactory(name) {
    return {
      "type": "input_value",
      "name": name,
      "check": "Number",
      "align": "RIGHT",
    };
  }

  Blockly.defineBlocksWithJsonArray([
    // Block for drawing a circle.
    {
      "type": "movie_circle",
      "message0": `${BlocklyGames.getMsg('Movie.circleDraw', false)} ${BlocklyGames.getMsg('Movie.x', false)}%1${BlocklyGames.getMsg('Movie.y', false)}%2${BlocklyGames.getMsg('Movie.radius', false)}%3`,
      "args0": [
        inputFactory('X'),
        inputFactory('Y'),
        inputFactory('RADIUS'),
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": SHAPE_HUE,
      "tooltip": BlocklyGames.getMsg('Movie.circleTooltip', false),
    },

    // Block for drawing a rectangle.
    {
      "type": "movie_rect",
      "message0": `${BlocklyGames.getMsg('Movie.rectDraw', false)} ${BlocklyGames.getMsg('Movie.x', false)}%1${BlocklyGames.getMsg('Movie.y', false)}%2${BlocklyGames.getMsg('Movie.width', false)}%3${BlocklyGames.getMsg('Movie.height', false)}%4`,
      "args0": [
        inputFactory('X'),
        inputFactory('Y'),
        inputFactory('WIDTH'),
        inputFactory('HEIGHT'),
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": SHAPE_HUE,
      "tooltip": BlocklyGames.getMsg('Movie.rectTooltip', false),
    },

    // Block for drawing a line.
    {
      "type": "movie_line",
      "message0": `${BlocklyGames.getMsg('Movie.lineDraw', false)} ${BlocklyGames.getMsg('Movie.x1', false)}%1${BlocklyGames.getMsg('Movie.y1', false)}%2${BlocklyGames.getMsg('Movie.x2', false)}%3${BlocklyGames.getMsg('Movie.y2', false)}%4${BlocklyGames.getMsg('Movie.width', false)}%5`,
      "args0": [
        inputFactory('X1'),
        inputFactory('Y1'),
        inputFactory('X2'),
        inputFactory('Y2'),
        inputFactory('WIDTH'),
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": SHAPE_HUE,
      "tooltip": BlocklyGames.getMsg('Movie.lineTooltip', false),
    },

    // Block for getting the current time value.
    {
      "type": "movie_time",
      "message0": "time (0→100)",
      "output": null,
      "colour": "%{BKY_VARIABLES_HUE}",
      "tooltip": BlocklyGames.getMsg('Movie.timeTooltip', false),
    },

    // Block for setting the colour.
    {
      "type": "movie_colour",
      "message0": BlocklyGames.getMsg('Movie.setColour', false) + "%1",
      "args0": [
        {
          "type": "input_value",
          "name": "COLOUR",
          "check": "Colour"
        }
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": "%{BKY_COLOUR_HUE}",
      "tooltip": BlocklyGames.getMsg('Movie.colourTooltip', false),
    },
  ]);
};
