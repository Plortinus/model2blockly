Pond.Blocks.init = function() {
  /**
   * Common HSV hue for all pond blocks.
   */
  const POND_HUE = 290;

  Blockly.defineBlocksWithJsonArray([
    // Block for scanning the pond.
    {
      "type": "pond_scan",
      "message0": "%1(%2)",
      "args0": [
        "scan",
        {
          "type": "input_value",
          "name": "DEGREE",
          "check": ["Number", "Angle"],
        }
      ],
      "inputsInline": true,
      "output": "Number",
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.scanTooltip', false),
    },

    // Block for shooting the cannon.
    {
      "type": "pond_cannon",
      "message0": "%1(%2, %3);",
      "args0": [
        "cannon",
        {
          "type": "input_value",
          "name": "DEGREE",
          "check": ["Number", "Angle"]
        },
        {
          "type": "input_value",
          "name": "RANGE",
          "check": "Number",
        }
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.cannonTooltip', false),
    },

    // Block for swimming.
    {
      "type": "pond_swim",
      "message0": "%1(%2);",
      "args0": [
          "swim",
        {
          "type": "input_value",
          "name": "DEGREE",
          "check": ["Number", "Angle"],
        }
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.swimTooltip', false),
    },

    // Block for stopping.
    {
      "type": "pond_stop",
      "message0": "%1(%2);",
      "args0": ["stop", ""],
      "previousStatement": null,
      "nextStatement": null,
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.stopTooltip', false),
    },

    // Block for avatar health.
    {
      "type": "pond_health",
      "message0": "%1(%2)",
      "args0": ["health", ""],
      "output": "Number",
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.healthTooltip', false),
    },

    // Block for avatar speed.
    {
      "type": "pond_speed",
      "message0": "%1(%2)",
      "args0": ["speed", ""],
      "output": "Number",
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.speedTooltip', false),
    },

    // Block for X coordinate.
    {
      "type": "pond_getX",
      "message0": "%1(%2)",
      "args0": ["getX", ""],
      "output": "Number",
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.locXTooltip', false),
    },

    // Block for Y coordinate.
    {
      "type": "pond_getY",
      "message0": "%1(%2)",
      "args0": ["getY", ""],
      "output": "Number",
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.locYTooltip', false),
    },

    // Block for log statement.
    {
      "type": "pond_log",
      "message0": "%1(%2);",
      "args0": [
        "log",
        {
          "type": "input_value",
          "name": "VALUE",
        }
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": POND_HUE,
      "tooltip": BlocklyGames.getMsg('Pond.logTooltip', false),
    },
  ]);
};
