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
