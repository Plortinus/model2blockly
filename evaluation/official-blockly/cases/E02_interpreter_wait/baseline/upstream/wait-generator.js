javascript.javascriptGenerator.forBlock['wait_seconds'] = function (block) {
  const seconds = Number(block.getFieldValue('SECONDS'));
  const code = 'waitForSeconds(' + seconds + ');\n';
  return code;
};
