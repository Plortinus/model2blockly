Blockly.JavaScript['pond_scan'] = function(block) {
  // Generate JavaScript for scanning the pond.
  const value_degree = Blockly.JavaScript.valueToCode(block, 'DEGREE',
      Blockly.JavaScript.ORDER_NONE) || 0;
  const code = `scan(${value_degree})`;
  return [code, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['pond_cannon'] = function(block) {
  // Generate JavaScript for shooting the cannon.
  const value_degree = Blockly.JavaScript.valueToCode(block, 'DEGREE',
      Blockly.JavaScript.ORDER_COMMA) || 0;
  const value_range = Blockly.JavaScript.valueToCode(block, 'RANGE',
      Blockly.JavaScript.ORDER_COMMA) || 0;
  return `cannon(${value_degree}, ${value_range});\n`;
};

Blockly.JavaScript['pond_swim'] = function(block) {
  // Generate JavaScript for swimming.
  const value_degree = Blockly.JavaScript.valueToCode(block, 'DEGREE',
      Blockly.JavaScript.ORDER_NONE) || 0;
  return `swim(${value_degree});\n`;
};

Blockly.JavaScript['pond_stop'] = function(block) {
  // Generate JavaScript for stopping.
  return 'stop();\n';
};

Blockly.JavaScript['pond_health'] = function(block) {
  // Generate JavaScript for avatar health.
  return ['health()', Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['pond_speed'] = function(block) {
  // Generate JavaScript for avatar speed.
  return ['speed()', Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['pond_getX'] = function(block) {
  // Generate JavaScript for X coordinate.
  return ['getX()', Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['pond_getY'] = function(block) {
  // Generate JavaScript for Y coordinate.
  return ['getY()', Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['pond_log'] = function(block) {
  // Generate JavaScript for logging.
  const value_text = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_NONE) || '\'\'';
  return `log(${value_text});\n`;
};
