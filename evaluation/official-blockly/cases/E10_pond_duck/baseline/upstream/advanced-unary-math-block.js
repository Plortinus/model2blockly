Blockly.Blocks['pond_math_single'] = {
  /**
   * Advanced math operators with single operand.
   * @this {Blockly.Block}
   */
  init: function() {
    this.jsonInit({
      "message0": "%1 (%2)",
      "args0": [
        {
          "type": "field_dropdown",
          "name": "OP",
          "options": [
            ["Math.sqrt", "ROOT"],
            ["Math.abs", "ABS"],
            ["Math.sin_deg", "SIN"],
            ["Math.cos_deg", "COS"],
            ["Math.tan_deg", "TAN"],
            ["Math.asin_deg", "ASIN"],
            ["Math.acos_deg", "ACOS"],
            ["Math.atan_deg", "ATAN"],
          ],
        },
        {
          "type": "input_value",
          "name": "NUM",
          "check": "Number",
        },
      ],
      "inputsInline": true,
      "output": "Number",
      "colour": "%{BKY_MATH_HUE}",
      "helpUrl": "%{BKY_MATH_SINGLE_HELPURL}",
      "extensions": ["math_op_tooltip"],
    });
  }
};
