Blockly.JavaScript['pond_math_single'] = function(block) {
  // Advanced math operators with single operand.
  const arg = Blockly.JavaScript.valueToCode(block, 'NUM',
      Blockly.JavaScript.ORDER_NONE) || '0';
  const func = {
    'ROOT': 'sqrt',
    'ABS':  'abs',
    'SIN':  'sin_deg',
    'COS':  'cos_deg',
    'TAN':  'tan_deg',
    'ASIN': 'asin_deg',
    'ACOS': 'acos_deg',
    'ATAN': 'atan_deg',
  }[block.getFieldValue('OP')];
  return [`Math.${func}(${arg})`, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};
