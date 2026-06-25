// Code generators for domain "Custommessage".
// Auto-generated from metamodel.
function parseBlocklyListField(value) {
  if (Array.isArray(value)) return value;
  if (value === null || value === undefined) return [];
  return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
}
window.parseBlocklyListField = parseBlocklyListField;

javascript.javascriptGenerator.forBlock['Rule'] = function(block) {
  var condition = block.getFieldValue('condition');
  var result = block.getFieldValue('result');
  return '{' +   '"_type": "Rule", "_blockId": ' + JSON.stringify(block.id) +  ', "condition": ' + JSON.stringify(condition) +  ', "result": ' + JSON.stringify(result) + ' },\n';
};
/* ── Built-in block generators (JSON AST output) ── */

/* --- Logic --- */
javascript.javascriptGenerator.forBlock['controls_if'] = function(block) {
  var branches = [];
  var i = 0;
  while (block.getInput('IF' + i)) {
    var cond = javascript.javascriptGenerator.valueToCode(block, 'IF' + i, 0) || 'null';
    var body = javascript.javascriptGenerator.statementToCode(block, 'DO' + i);
    var bodyArr = (body || '').trim().replace(/,\s*$/, '');
    branches.push('{"condition": ' + cond + ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + '}');
    i++;
  }
  var elseBody = javascript.javascriptGenerator.statementToCode(block, 'ELSE');
  var elseArr = (elseBody || '').trim().replace(/,\s*$/, '');
  var elsePart = elseArr ? ', "else": [' + elseArr + ']' : '';
  return '{' + '"_type": "controls_if", "_blockId": ' + JSON.stringify(block.id) +
    ', "branches": [' + branches.join(',') + ']' + elsePart + ' },\n';
};
javascript.javascriptGenerator.forBlock['logic_compare'] = function(block) {
  var op = block.getFieldValue('OP');
  var a = javascript.javascriptGenerator.valueToCode(block, 'A', 0) || 'null';
  var b = javascript.javascriptGenerator.valueToCode(block, 'B', 0) || 'null';
  var code = '{' + '"_type": "logic_compare", "_blockId": ' + JSON.stringify(block.id) +
    ', "op": ' + JSON.stringify(op) + ', "left": ' + a + ', "right": ' + b + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['logic_operation'] = function(block) {
  var op = block.getFieldValue('OP');
  var a = javascript.javascriptGenerator.valueToCode(block, 'A', 0) || 'null';
  var b = javascript.javascriptGenerator.valueToCode(block, 'B', 0) || 'null';
  var code = '{' + '"_type": "logic_operation", "_blockId": ' + JSON.stringify(block.id) +
    ', "op": ' + JSON.stringify(op) + ', "left": ' + a + ', "right": ' + b + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['logic_negate'] = function(block) {
  var val = javascript.javascriptGenerator.valueToCode(block, 'BOOL', 0) || 'null';
  var code = '{' + '"_type": "logic_negate", "_blockId": ' + JSON.stringify(block.id) +
    ', "value": ' + val + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['logic_boolean'] = function(block) {
  var val = block.getFieldValue('BOOL');
  var code = '{' + '"_type": "logic_boolean", "_blockId": ' + JSON.stringify(block.id) +
    ', "value": ' + (val === 'TRUE' ? 'true' : 'false') + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['logic_null'] = function(block) {
  var code = '{' + '"_type": "logic_null", "_blockId": ' + JSON.stringify(block.id) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['logic_ternary'] = function(block) {
  var cond = javascript.javascriptGenerator.valueToCode(block, 'IF', 0) || 'null';
  var thenVal = javascript.javascriptGenerator.valueToCode(block, 'THEN', 0) || 'null';
  var elseVal = javascript.javascriptGenerator.valueToCode(block, 'ELSE', 0) || 'null';
  var code = '{' + '"_type": "logic_ternary", "_blockId": ' + JSON.stringify(block.id) +
    ', "condition": ' + cond + ', "then": ' + thenVal + ', "else": ' + elseVal + ' }';
  return [code, 0];
};

/* --- Loops --- */
javascript.javascriptGenerator.forBlock['controls_repeat_ext'] = function(block) {
  var times = javascript.javascriptGenerator.valueToCode(block, 'TIMES', 0) || 'null';
  var body = javascript.javascriptGenerator.statementToCode(block, 'DO');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  return '{' + '"_type": "controls_repeat", "_blockId": ' + JSON.stringify(block.id) +
    ', "times": ' + times + ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['controls_whileUntil'] = function(block) {
  var mode = block.getFieldValue('MODE');
  var cond = javascript.javascriptGenerator.valueToCode(block, 'BOOL', 0) || 'null';
  var body = javascript.javascriptGenerator.statementToCode(block, 'DO');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  return '{' + '"_type": "controls_whileUntil", "_blockId": ' + JSON.stringify(block.id) +
    ', "mode": ' + JSON.stringify(mode) + ', "condition": ' + cond +
    ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['controls_for'] = function(block) {
  var varName = block.getField('VAR').getText();
  var from = javascript.javascriptGenerator.valueToCode(block, 'FROM', 0) || 'null';
  var to = javascript.javascriptGenerator.valueToCode(block, 'TO', 0) || 'null';
  var by = javascript.javascriptGenerator.valueToCode(block, 'BY', 0) || 'null';
  var body = javascript.javascriptGenerator.statementToCode(block, 'DO');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  return '{' + '"_type": "controls_for", "_blockId": ' + JSON.stringify(block.id) +
    ', "variable": ' + JSON.stringify(varName) +
    ', "from": ' + from + ', "to": ' + to + ', "by": ' + by +
    ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['controls_forEach'] = function(block) {
  var varName = block.getField('VAR').getText();
  var list = javascript.javascriptGenerator.valueToCode(block, 'LIST', 0) || 'null';
  var body = javascript.javascriptGenerator.statementToCode(block, 'DO');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  return '{' + '"_type": "controls_forEach", "_blockId": ' + JSON.stringify(block.id) +
    ', "variable": ' + JSON.stringify(varName) + ', "list": ' + list +
    ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['controls_flow_statements'] = function(block) {
  var flow = block.getFieldValue('FLOW');
  return '{' + '"_type": "controls_flow", "_blockId": ' + JSON.stringify(block.id) +
    ', "flow": ' + JSON.stringify(flow) + ' },\n';
};

/* --- Math --- */
javascript.javascriptGenerator.forBlock['math_number'] = function(block) {
  var num = block.getFieldValue('NUM');
  var code = '{' + '"_type": "math_number", "_blockId": ' + JSON.stringify(block.id) +
    ', "value": ' + Number(num) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_arithmetic'] = function(block) {
  var op = block.getFieldValue('OP');
  var a = javascript.javascriptGenerator.valueToCode(block, 'A', 0) || 'null';
  var b = javascript.javascriptGenerator.valueToCode(block, 'B', 0) || 'null';
  var code = '{' + '"_type": "math_arithmetic", "_blockId": ' + JSON.stringify(block.id) +
    ', "op": ' + JSON.stringify(op) + ', "left": ' + a + ', "right": ' + b + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_single'] = function(block) {
  var op = block.getFieldValue('OP');
  var num = javascript.javascriptGenerator.valueToCode(block, 'NUM', 0) || 'null';
  var code = '{' + '"_type": "math_single", "_blockId": ' + JSON.stringify(block.id) +
    ', "op": ' + JSON.stringify(op) + ', "value": ' + num + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_round'] = function(block) {
  var op = block.getFieldValue('OP');
  var num = javascript.javascriptGenerator.valueToCode(block, 'NUM', 0) || 'null';
  var code = '{' + '"_type": "math_round", "_blockId": ' + JSON.stringify(block.id) +
    ', "op": ' + JSON.stringify(op) + ', "value": ' + num + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_modulo'] = function(block) {
  var a = javascript.javascriptGenerator.valueToCode(block, 'DIVIDEND', 0) || 'null';
  var b = javascript.javascriptGenerator.valueToCode(block, 'DIVISOR', 0) || 'null';
  var code = '{' + '"_type": "math_modulo", "_blockId": ' + JSON.stringify(block.id) +
    ', "dividend": ' + a + ', "divisor": ' + b + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_constrain'] = function(block) {
  var val = javascript.javascriptGenerator.valueToCode(block, 'VALUE', 0) || 'null';
  var lo = javascript.javascriptGenerator.valueToCode(block, 'LOW', 0) || 'null';
  var hi = javascript.javascriptGenerator.valueToCode(block, 'HIGH', 0) || 'null';
  var code = '{' + '"_type": "math_constrain", "_blockId": ' + JSON.stringify(block.id) +
    ', "value": ' + val + ', "low": ' + lo + ', "high": ' + hi + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_random_int'] = function(block) {
  var from = javascript.javascriptGenerator.valueToCode(block, 'FROM', 0) || 'null';
  var to = javascript.javascriptGenerator.valueToCode(block, 'TO', 0) || 'null';
  var code = '{' + '"_type": "math_random_int", "_blockId": ' + JSON.stringify(block.id) +
    ', "from": ' + from + ', "to": ' + to + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['math_random_float'] = function(block) {
  var code = '{' + '"_type": "math_random_float", "_blockId": ' + JSON.stringify(block.id) + ' }';
  return [code, 0];
};

/* --- Variables --- */
javascript.javascriptGenerator.forBlock['variables_get'] = function(block) {
  var varName = block.getField('VAR').getText();
  var code = '{' + '"_type": "variables_get", "_blockId": ' + JSON.stringify(block.id) +
    ', "variable": ' + JSON.stringify(varName) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['variables_set'] = function(block) {
  var varName = block.getField('VAR').getText();
  var val = javascript.javascriptGenerator.valueToCode(block, 'VALUE', 0) || 'null';
  return '{' + '"_type": "variables_set", "_blockId": ' + JSON.stringify(block.id) +
    ', "variable": ' + JSON.stringify(varName) + ', "value": ' + val + ' },\n';
};

/* --- Procedures (Functions) --- */
javascript.javascriptGenerator.forBlock['procedures_defnoreturn'] = function(block) {
  var name = block.getFieldValue('NAME');
  var params = block.arguments_ || [];
  var body = javascript.javascriptGenerator.statementToCode(block, 'STACK');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  return '{' + '"_type": "procedures_def", "_blockId": ' + JSON.stringify(block.id) +
    ', "name": ' + JSON.stringify(name) +
    ', "params": ' + JSON.stringify(params) +
    ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['procedures_defreturn'] = function(block) {
  var name = block.getFieldValue('NAME');
  var params = block.arguments_ || [];
  var body = javascript.javascriptGenerator.statementToCode(block, 'STACK');
  var bodyArr = (body || '').trim().replace(/,\s*$/, '');
  var ret = javascript.javascriptGenerator.valueToCode(block, 'RETURN', 0) || 'null';
  return '{' + '"_type": "procedures_def", "_blockId": ' + JSON.stringify(block.id) +
    ', "name": ' + JSON.stringify(name) +
    ', "params": ' + JSON.stringify(params) +
    ', "body": ' + (bodyArr ? '[' + bodyArr + ']' : '[]') +
    ', "return": ' + ret + ' },\n';
};
javascript.javascriptGenerator.forBlock['procedures_callnoreturn'] = function(block) {
  var name = block.getFieldValue('NAME');
  var args = {};
  for (var i = 0; i < (block.arguments_ ? block.arguments_.length : 0); i++) {
    args[block.arguments_[i]] = JSON.parse(
      javascript.javascriptGenerator.valueToCode(block, 'ARG' + i, 0) || 'null'
    );
  }
  return '{' + '"_type": "procedures_call", "_blockId": ' + JSON.stringify(block.id) +
    ', "name": ' + JSON.stringify(name) +
    ', "args": ' + JSON.stringify(args) + ' },\n';
};
javascript.javascriptGenerator.forBlock['procedures_callreturn'] = function(block) {
  var name = block.getFieldValue('NAME');
  var args = {};
  for (var i = 0; i < (block.arguments_ ? block.arguments_.length : 0); i++) {
    args[block.arguments_[i]] = JSON.parse(
      javascript.javascriptGenerator.valueToCode(block, 'ARG' + i, 0) || 'null'
    );
  }
  var code = '{' + '"_type": "procedures_call", "_blockId": ' + JSON.stringify(block.id) +
    ', "name": ' + JSON.stringify(name) +
    ', "args": ' + JSON.stringify(args) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['procedures_ifreturn'] = function(block) {
  var cond = javascript.javascriptGenerator.valueToCode(block, 'CONDITION', 0) || 'null';
  var val = javascript.javascriptGenerator.valueToCode(block, 'VALUE', 0) || 'null';
  return '{' + '"_type": "procedures_ifreturn", "_blockId": ' + JSON.stringify(block.id) +
    ', "condition": ' + cond + ', "value": ' + val + ' },\n';
};

/* ── Type compatibility: allow built-in expression blocks to connect
   to domain value inputs that check for "Expression" ── */
(function() {
  var exprTypes = [
    'math_number','math_arithmetic','math_single','math_round',
    'math_modulo','math_constrain','math_random_int','math_random_float',
    'logic_compare','logic_operation','logic_negate','logic_boolean',
    'logic_null','logic_ternary','variables_get','procedures_callreturn'
  ];
  exprTypes.forEach(function(type) {
    var def = Blockly.Blocks[type];
    if (def && def.init) {
      var origInit = def.init;
      def.init = function() {
        origInit.call(this);
        this.setOutput(true, null);
      };
    }
  });
})();
/* ── Domain code generation (template based) ── */
window.BLOCKLY_DOMAIN_CODEGEN = {
  language: 'text',
  fileExtension: 'txt',
  blocks: {
'Rule': {
  label: 'Rule',
  template: null,
  fields: ['condition', 'result'],
  fieldTypes: {'condition': 'TEXT', 'result': 'TEXT'},
  references: [],
  values: [],
  statements: []
}
  }
};

function generateDomainCode(workspace) {
  if (!workspace) return '';
  var topBlocks = workspace.getTopBlocks(true).filter(function(block) {
    return !block.outputConnection;
  });
  if (topBlocks.length === 0) topBlocks = workspace.getTopBlocks(true);
  return topBlocks.map(renderDomainBlock).filter(Boolean).join('\n');
}

function renderDomainBlock(block) {
  if (!block) return '';
  var config = window.BLOCKLY_DOMAIN_CODEGEN.blocks[block.type] || null;
  if (!config) return renderFallbackDomainBlock(block);
  var template = config.template;
  if (!template) return renderFallbackDomainBlock(block, config);
  return applyDomainTemplate(block, config, template);
}

function applyDomainTemplate(block, config, template) {
  return template
    .replace(/\{\{\s*(value|statement|statements|children):\s*([A-Za-z_][\w-]*)\s*\}\}/g, function(_, kind, name) {
      if (kind === 'value') return renderDomainValue(block, name);
      return renderDomainStatement(block, name);
    })
    .replace(/\{\{\s*type\s*\}\}/g, block.type)
    .replace(/\{\{\s*([A-Za-z_][\w-]*)\s*\}\}/g, function(_, name) {
      return block.getFieldValue(name) || '';
    });
}

function renderDomainValue(block, inputName) {
  var child = block.getInputTargetBlock(inputName);
  return child ? renderDomainBlock(child) : '';
}

function renderDomainStatement(block, inputName) {
  var child = block.getInputTargetBlock(inputName);
  var lines = [];
  while (child) {
    var rendered = renderDomainBlock(child);
    if (rendered) lines.push(rendered);
    child = child.getNextBlock();
  }
  return lines.join('\n');
}

function renderFallbackDomainBlock(block, config) {
  config = config || { fields: [], references: [], values: [], statements: [] };
  var parts = [];
  (config.fields || []).forEach(function(name) {
    var value = block.getFieldValue(name);
    if (value !== null && value !== undefined && value !== '') parts.push(name + '=' + JSON.stringify(value));
  });
  (config.references || []).forEach(function(name) {
    var value = block.getFieldValue(name);
    if (value !== null && value !== undefined && value !== '') parts.push(name + '=' + JSON.stringify(value));
  });
  (config.values || []).forEach(function(name) {
    var valueCode = renderDomainValue(block, name);
    if (valueCode) parts.push(name + '=' + valueCode);
  });
  (config.statements || []).forEach(function(name) {
    var stmtCode = renderDomainStatement(block, name);
    if (stmtCode) parts.push(name + '={\n' + indentDomainCode(stmtCode) + '\n}');
  });
  return block.type + (parts.length ? '(' + parts.join(', ') + ')' : '');
}

function indentDomainCode(text) {
  return String(text || '').split('\n').map(function(line) {
    return line ? '  ' + line : line;
  }).join('\n');
}
