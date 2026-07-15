Bird.Blocks.init = function() {
  /**
   * Common HSV hue for all variable blocks.
   */
  const VARIABLES_HUE = 330;

  /**
   * HSV hue for movement block.
   */
  const MOVEMENT_HUE = 290;

  Blockly.defineBlocksWithJsonArray([
    // Block for no worm condition.
    {
      "type": "bird_noWorm",
      "message0": BlocklyGames.getMsg('Bird.noWorm', false),
      "output": "Boolean",
      "colour": VARIABLES_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.noWormTooltip', false),
    },

    // Block for moving bird in a direction.
    {
      "type": "bird_heading",
      "message0": BlocklyGames.getMsg('Bird.heading', false) + "%1",
      "args0": [
        {
          "type": "field_angle",
          "name": "ANGLE",
          "angle": 90,
        }
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": MOVEMENT_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.headingTooltip', false),
    },

    // Block for getting bird's x or y position.
    {
      "type": "bird_position",
      "message0": "%1",
      "args0": [
        {
          "type": "field_dropdown",
          "name": "XY",
          "options": [["x", "X"], ["y", "Y"]],
        }
      ],
      "output": "Number",
      "colour": VARIABLES_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.positionTooltip', false),
    },

    // Block for comparing bird's x or y position with a number.
    {
      "type": "bird_compare",
      "message0": `%1%2%3`,
      "args0": [
        {
          "type": "input_value",
          "name": "A",
          "check": "Number",
        },
        {
          "type": "field_dropdown",
          "name": "OP",
          "options": [['\u200F<', 'LT'], ['\u200F>', 'GT']],
        },
        {
          "type": "input_value",
          "name": "B",
          "check": "Number",
        },
      ],
      "inputsInline": true,
      "output": "Boolean",
      "colour": "%{BKY_LOGIC_HUE}",
      "helpUrl": "%{BKY_LOGIC_COMPARE_HELPURL}",
      "extensions": ["bird_compare_tooltip"],
    },

    // Block for logical operator 'and'.
    {
      "type": "bird_and",
      "message0": "%1%{BKY_LOGIC_OPERATION_AND}%2",
      "args0": [
        {
          "type": "input_value",
          "name": "A",
          "check": "Boolean",
        },
        {
          "type": "input_value",
          "name": "B",
          "check": "Boolean",
        },
      ],
      "inputsInline": true,
      "output": "Boolean",
      "colour": "%{BKY_LOGIC_HUE}",
      "tooltip": "%{BKY_LOGIC_OPERATION_TOOLTIP_AND}",
      "helpUrl": "%{BKY_LOGIC_OPERATION_HELPURL}",
    },

    // Block for 'if/else'.
    {
      "type": "bird_ifElse",
      "message0": "%{BKY_CONTROLS_IF_MSG_IF}%1%{BKY_CONTROLS_IF_MSG_THEN}%2%{BKY_CONTROLS_IF_MSG_ELSE}%3",
      "args0": [
        {
          "type": "input_value",
          "name": "CONDITION",
          "check": "Boolean",
        },
        {
          "type": "input_statement",
          "name": "DO",
        },
        {
          "type": "input_statement",
          "name": "ELSE",
        },
      ],
      "colour": "%{BKY_LOGIC_HUE}",
      "tooltip": "%{BKY_CONTROLS_IF_TOOLTIP_2}",
      "helpUrl": "%{BKY_CONTROLS_IF_HELPURL}",
    },

    // Block for numeric value.
    {
      "type": "math_number",
      "message0": "%1",
      "args0": [{
        "type": "field_number",
        "name": "NUM",
        "value": 0,
      }],
      "output": "Number",
      "helpUrl": "%{BKY_MATH_NUMBER_HELPURL}",
      "colour": "%{BKY_MATH_HUE}",
      "tooltip": "%{BKY_MATH_NUMBER_TOOLTIP}",
      "extensions": ["parent_tooltip_when_inline"],
    },
  ]);

  Blockly.Extensions.register('bird_compare_tooltip',
      Blockly.Extensions.buildTooltipForDropdown('OP',
          {
            'LT': '%{BKY_LOGIC_COMPARE_TOOLTIP_LT}',
            'GT': '%{BKY_LOGIC_COMPARE_TOOLTIP_GT}',
          }));
};
