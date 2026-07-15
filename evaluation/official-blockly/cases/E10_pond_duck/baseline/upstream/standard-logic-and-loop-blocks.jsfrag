(function () {
  // Enclose mixin in an immediately executed function to hide the 'prop' var.
  for (const prop in Blockly.Constants.Logic.CONTROLS_IF_MUTATOR_MIXIN) {
    Blockly.Blocks['controls_if'][prop] =
        Blockly.Constants.Logic.CONTROLS_IF_MUTATOR_MIXIN[prop];
  }
})();

/**
 * If/elseif/else condition.
 * @this {Blockly.Block}
 */
Blockly.Blocks['controls_if'].init = function() {
  this.setHelpUrl(Blockly.Msg['CONTROLS_IF_HELPURL']);
  this.setColour(Blockly.Msg['LOGIC_HUE']);
  this.appendValueInput('IF0')
      .setCheck('Boolean')
      .appendField('if (');
  this.appendDummyInput()
      .appendField(') {');
  this.appendStatementInput('DO0');
  this.appendDummyInput('TAIL')
      .appendField('}');
  this.setInputsInline(true);
  this.setPreviousStatement(true);
  this.setNextStatement(true);
  this.setMutator(new Blockly.Mutator(['controls_if_elseif',
    'controls_if_else']));
  Blockly.Constants.Logic.CONTROLS_IF_TOOLTIP_EXTENSION.apply(this);
};

/**
 * Modify this block to have the correct number of inputs.
 * @private
 * @this {Blockly.Block}
 */
Blockly.Blocks['controls_if'].updateShape_ = function() {
  // Delete everything.
  if (this.getInput('ELSE')) {
    this.removeInput('ELSEMSG');
    this.removeInput('ELSE');
  }
  let i = 1;
  while (this.getInput('IF' + i)) {
    this.removeInput('IF' + i);
    this.removeInput('TAIL' + i);
    this.removeInput('DO' + i);
    i++;
  }
  // Rebuild block.
  for (let i = 1; i <= this.elseifCount_; i++) {
    this.appendValueInput('IF' + i)
        .setCheck('Boolean')
        .appendField('} else if (');
    this.appendDummyInput('TAIL' + i)
        .appendField(') {');
    this.appendStatementInput('DO' + i);
  }
  if (this.elseCount_) {
    this.appendDummyInput('ELSEMSG')
        .appendField('} else {');
    this.appendStatementInput('ELSE');
  }
  // Move final '}' to the end.
  this.moveInputBefore('TAIL', null);
};

/**
 * Block for comparison operator.
 * @this {Blockly.Block}
 */
Blockly.Blocks['logic_compare'].init = function() {
  this.jsonInit({
    "message0": "%1 %2 %3",
    "args0": [
      {
        "type": "input_value",
        "name": "A"
      },
      {
        "type": "field_dropdown",
        "name": "OP",
        "options": [
          ["==", "EQ"],
          ["!=", "NEQ"],
          ["\u200F<", "LT"],
          ["\u200F<=", "LTE"],
          ["\u200F>", "GT"],
          ["\u200F>=", "GTE"],
        ],
      },
      {
        "type": "input_value",
        "name": "B",
      }
    ],
    "inputsInline": true,
    "output": "Boolean",
    "colour": "%{BKY_LOGIC_HUE}",
    "helpUrl": "%{BKY_LOGIC_COMPARE_HELPURL}",
    "extensions": ["logic_compare", "logic_op_tooltip"],
  });
};

/**
 * Block for boolean data type: true and false.
 * @this {Blockly.Block}
 */
Blockly.Blocks['logic_boolean'].init = function() {
  this.jsonInit({
    "message0": "%1",
    "args0": [
      {
        "type": "field_dropdown",
        "name": "BOOL",
        "options": [
          ["true", "TRUE"],
          ["false", "FALSE"],
        ],
      },
    ],
    "output": "Boolean",
    "colour": "%{BKY_LOGIC_HUE}",
    "tooltip": "%{BKY_LOGIC_BOOLEAN_TOOLTIP}",
    "helpUrl": "%{BKY_LOGIC_BOOLEAN_HELPURL}",
  });
};

/**
 * Block for logical operations: 'and', 'or'.
 * @this {Blockly.Block}
 */
Blockly.Blocks['logic_operation'].init = function() {
  this.jsonInit({
    "message0": "%1 %2 %3",
    "args0": [
      {
        "type": "input_value",
        "name": "A",
        "check": "Boolean",
      },
      {
        "type": "field_dropdown",
        "name": "OP",
        "options": [
          ["&&", "AND"],
          ["||", "OR"],
        ],
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
    "helpUrl": "%{BKY_LOGIC_OPERATION_HELPURL}",
    "extensions": ["logic_op_tooltip"],
  });
};

/**
 * Block for 'while' loop.
 * @this {Blockly.Block}
 */
Blockly.Blocks['controls_whileUntil'].init = function() {
  this.jsonInit({
    "message0": "while ( %1 ) { %2 %3 }",
    "args0": [
      {
        "type": "input_value",
        "name": "BOOL",
        "check": "Boolean",
      },
      {
        "type": "input_dummy",
      },
      {
        "type": "input_statement",
        "name": "DO",
      }
    ],
    "inputsInline": true,
    "previousStatement": null,
    "nextStatement": null,
    "colour": "%{BKY_LOOPS_HUE}",
    "tooltip": "%{BKY_CONTROLS_WHILEUNTIL_TOOLTIP_WHILE}",
    "helpUrl": "%{BKY_CONTROLS_WHILEUNTIL_HELPURL}",
  });
};
