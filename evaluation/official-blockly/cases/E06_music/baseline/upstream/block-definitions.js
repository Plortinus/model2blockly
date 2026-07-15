Music.Blocks.init = function() {
  /**
   * Common HSV hue for all blocks in this category.
   */
  const HUE = 160;

  /**
   * Create a dropdown option for a note.
   * @param {number} denominator Inverse duration of note.
   * @returns {!Object} Dropdown option.
   */
  function noteFactory(denominator) {
    return [
      {
        "src": `music/note${1 / denominator}.png`,
        "width": 9,
        "height": 19,
        "alt": "1/" + denominator,
      },
      String(1 / denominator),
    ];
  }

  /**
   * Create a dropdown option for a rest.
   * @param {number} denominator Inverse duration of rest.
   * @returns {!Object} Dropdown option.
   */
  function restFactory(denominator) {
    return [
      {
        "src": `music/rest${1 / denominator}.png`,
        "width": 10,
        "height": 20,
        "alt": "1/" + denominator,
      },
      String(1 / denominator),
    ];
  }

  const notes = [];
  const rests = [];
  for (let denominator = 1; denominator <= 16; denominator *= 2) {
    notes.push(noteFactory(denominator));
    rests.push(restFactory(denominator));
  }
  // Trim off whole and sixteenth notes for levels 1-9.
  if (BlocklyGames.LEVEL < BlocklyGames.MAX_LEVEL) {
    notes.shift();
    notes.pop();
  }

  Blockly.defineBlocksWithJsonArray([
    // Block for pitch.
    {
      "type": "music_pitch",
      "message0": "%1",
      "args0": [
        {
          "type": "field_pitch",
          "name": "PITCH",
          "text": "7",
        }
      ],
      "output": "Number",
      "colour": "%{BKY_MATH_HUE}",
      "tooltip": BlocklyGames.getMsg('Music.pitchTooltip', false),
    },

    // Block for playing note.
    {
      "type": "music_note",
      "message0": BlocklyGames.getMsg('Music.playNote', false),
      "args0": [
        {
          "type": "field_dropdown",
          "name": "DURATION",
          "options": notes,
        },
        {
          "type": "input_value",
          "name": "PITCH",
          "check": "Number",
        },
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": HUE,
      "tooltip": BlocklyGames.getMsg('Music.playNoteTooltip', false),
    },

    // Block for waiting a whole note.
    {
      "type": "music_rest_whole",
      "message0": BlocklyGames.getMsg('Music.rest', false),
      "args0": [
        {
          "type": "field_image",
          "src": "music/rest1.png",
          "width": 10,
          "height": 20,
          "alt": "1/1",
        },
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": HUE,
      "tooltip": BlocklyGames.getMsg('Music.restWholeTooltip', false),
    },

    // Block for waiting.
    {
      "type": "music_rest",
      "message0": BlocklyGames.getMsg('Music.rest', false),
      "args0": [
        {
          "type": "field_dropdown",
          "name": "DURATION",
          "options": [
            restFactory(1),
            restFactory(2),
            restFactory(4),
            restFactory(8),
            restFactory(16),
          ],
        },
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": HUE,
      "tooltip": BlocklyGames.getMsg('Music.restTooltip', false),
    },

    // Block for changing instrument.
    {
      "type": "music_instrument",
      "message0": BlocklyGames.getMsg('Music.setInstrument', false),
      "args0": [
        {
          "type": "field_dropdown",
          "name": "INSTRUMENT",
          "options": [
            [BlocklyGames.getMsg('Music.piano', false), "piano"],
            [BlocklyGames.getMsg('Music.trumpet', false), "trumpet"],
            [BlocklyGames.getMsg('Music.banjo', false), "banjo"],
            [BlocklyGames.getMsg('Music.violin', false), "violin"],
            [BlocklyGames.getMsg('Music.guitar', false), "guitar"],
            [BlocklyGames.getMsg('Music.flute', false), "flute"],
            [BlocklyGames.getMsg('Music.drum', false), "drum"],
            [BlocklyGames.getMsg('Music.choir', false), "choir"],
          ],
        },
      ],
      "inputsInline": true,
      "previousStatement": null,
      "nextStatement": null,
      "colour": HUE,
      "tooltip": BlocklyGames.getMsg('Music.setInstrumentTooltip', false),
    },

    // Block for starting an execution thread.
    {
      "type": "music_start",
      "message0": BlocklyGames.getMsg('Music.start', false),
      "args0": [
        {
          "type": "field_image",
          "src": "music/play.png",
          "width": 17,
          "height": 17,
          "alt": "▶",
        },
      ],
      "message1": "%1",
      "args1": [
        {
          "type": "input_statement",
          "name": "STACK",
        },
      ],
      "colour": 0,
      "tooltip": BlocklyGames.getMsg('Music.startTooltip', false),
    }
  ]);
};
