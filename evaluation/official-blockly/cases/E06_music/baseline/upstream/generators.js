Blockly.JavaScript['music_pitch'] = function(block) {
  return [Number(block.getFieldValue('PITCH')),
      Blockly.JavaScript.ORDER_ATOMIC];
};

Blockly.JavaScript['music_note'] = function(block) {
  const duration = Number(block.getFieldValue('DURATION'));
  const pitch = Blockly.JavaScript.valueToCode(block, 'PITCH',
      Blockly.JavaScript.ORDER_COMMA) || '7';
  return `play(${duration}, ${pitch}, 'block_id_${block.id}');\n`;
};

Blockly.JavaScript['music_rest_whole'] = function(block) {
  return `rest(1, 'block_id_${block.id}');\n`;
};

Blockly.JavaScript['music_rest'] = function(block) {
  const duration = Number(block.getFieldValue('DURATION'));
  return `rest(${duration}, 'block_id_${block.id}');\n`;
};

Blockly.JavaScript['music_instrument'] = function(block) {
  const instrument = block.getFieldValue('INSTRUMENT');
  return `setInstrument(${Blockly.JavaScript.quote_(instrument)});\n`;
};

Blockly.JavaScript['music_start'] = function(block) {
  const startCount = Music.startCount.get() + 1;
  Music.startCount.set(startCount);
  const statements_stack = Blockly.JavaScript.statementToCode(block, 'STACK');
  const code = `function start${startCount}() {\n${statements_stack}}\n`;
  // Add % so as not to collide with helper functions in definitions list.
  Blockly.JavaScript.definitions_['%start' + startCount] = code;
  return null;
};
