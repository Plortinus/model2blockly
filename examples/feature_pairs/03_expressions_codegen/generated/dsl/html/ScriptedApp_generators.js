// Code generators for domain "ScriptedApp".
// Auto-generated from metamodel.
function parseBlocklyListField(value) {
  if (Array.isArray(value)) return value;
  if (value === null || value === undefined) return [];
  var text = String(value).trim();
  if (!text) return [];
  if (text.charAt(0) === '[') {
    try {
      var parsed = JSON.parse(text);
      if (Array.isArray(parsed)) return parsed;
    } catch (ignored) {}
  }
  return text.split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
}
window.parseBlocklyListField = parseBlocklyListField;

function parseBlocklyTypedListField(value, type) {
  var itemType = String(type || 'TEXT').toUpperCase();
  return parseBlocklyListField(value).map(function(item) {
    if (itemType === 'INTEGER' || itemType === 'ANGLE') {
      if (typeof item === 'number' && Number.isInteger(item)) return item;
      var integerText = String(item).trim();
      return /^[+-]?\d+$/.test(integerText) ? Number(integerText) : item;
    }
    if (itemType === 'FLOAT') {
      if (typeof item === 'number' && Number.isFinite(item)) return item;
      var floatText = String(item).trim();
      return floatText !== '' && Number.isFinite(Number(floatText)) ? Number(floatText) : item;
    }
    if (itemType === 'BOOLEAN') {
      if (item === true || item === false) return item;
      var booleanText = String(item).trim().toLowerCase();
      if (booleanText === 'true' || booleanText === '1' || booleanText === 'yes') return true;
      if (booleanText === 'false' || booleanText === '0' || booleanText === 'no') return false;
    }
    return item === null || item === undefined ? '' : String(item);
  });
}
window.parseBlocklyTypedListField = parseBlocklyTypedListField;

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
javascript.javascriptGenerator.forBlock['App'] = function(block) {
  var name = block.getFieldValue('name');
  var theme = block.getFieldValue('theme');
  var published = block.getFieldValue('published');
  var tags = parseBlocklyTypedListField(block.getFieldValue('tags'), "TEXT");
  var previewWidths = parseBlocklyTypedListField(block.getFieldValue('previewWidths'), "INTEGER");
  var targetPlatforms = parseBlocklyTypedListField(block.getFieldValue('targetPlatforms'), "DROPDOWN");
  var pages_code = javascript.javascriptGenerator.statementToCode(block, 'pages');
  var pages_stmt = (pages_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "App", "_blockId": ' + JSON.stringify(block.id) +  ', "name": ' + JSON.stringify(name) +  ', "theme": ' + JSON.stringify(theme) +  ', "published": ' + JSON.stringify(published) +  ', "tags": ' + JSON.stringify(tags) +  ', "previewWidths": ' + JSON.stringify(previewWidths) +  ', "targetPlatforms": ' + JSON.stringify(targetPlatforms) +   ', "pages": ' + (pages_stmt ? '[' + pages_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Page'] = function(block) {
  var title = block.getFieldValue('title');
  var description = block.getFieldValue('description');
  var backgroundColor = block.getFieldValue('backgroundColor');
  var scrollable = block.getFieldValue('scrollable');
  var alignment = block.getFieldValue('alignment');
  var components_code = javascript.javascriptGenerator.statementToCode(block, 'components');
  var components_stmt = (components_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Page", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +  ', "description": ' + JSON.stringify(description) +  ', "backgroundColor": ' + JSON.stringify(backgroundColor) +  ', "scrollable": ' + JSON.stringify(scrollable) +  ', "alignment": ' + JSON.stringify(alignment) +   ', "components": ' + (components_stmt ? '[' + components_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Label'] = function(block) {
  var text = block.getFieldValue('text');
  var fontSize = block.getFieldValue('fontSize');
  var textColor = block.getFieldValue('textColor');
  return '{' +   '"_type": "Label", "_blockId": ' + JSON.stringify(block.id) +  ', "text": ' + JSON.stringify(text) +  ', "fontSize": ' + JSON.stringify(fontSize) +  ', "textColor": ' + JSON.stringify(textColor) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Button'] = function(block) {
  var text = block.getFieldValue('text');
  var width = block.getFieldValue('width');
  var backgroundColor = block.getFieldValue('backgroundColor');
  var enabledWhen_code = javascript.javascriptGenerator.valueToCode(block, 'enabledWhen', 0) || 'null';
  return '{' +   '"_type": "Button", "_blockId": ' + JSON.stringify(block.id) +  ', "text": ' + JSON.stringify(text) +  ', "width": ' + JSON.stringify(width) +  ', "backgroundColor": ' + JSON.stringify(backgroundColor) +  ', "enabledWhen": ' + enabledWhen_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['Image'] = function(block) {
  var url = block.getFieldValue('url');
  var rotation = block.getFieldValue('rotation');
  var opacity = block.getFieldValue('opacity');
  return '{' +   '"_type": "Image", "_blockId": ' + JSON.stringify(block.id) +  ', "url": ' + JSON.stringify(url) +  ', "rotation": ' + JSON.stringify(rotation) +  ', "opacity": ' + JSON.stringify(opacity) + ' },\n';
};
javascript.javascriptGenerator.forBlock['BoolLiteral'] = function(block) {
  var literal = block.getFieldValue('literal');
  var code = '{' +   '"_type": "BoolLiteral", "_blockId": ' + JSON.stringify(block.id) +  ', "literal": ' + JSON.stringify(literal) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['PlatformIs'] = function(block) {
  var platform = block.getFieldValue('platform');
  var code = '{' +   '"_type": "PlatformIs", "_blockId": ' + JSON.stringify(block.id) +  ', "platform": ' + JSON.stringify(platform) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['NotExpression'] = function(block) {
  var operand_code = javascript.javascriptGenerator.valueToCode(block, 'operand', 0) || 'null';
  var code = '{' +   '"_type": "NotExpression", "_blockId": ' + JSON.stringify(block.id) +  ', "operand": ' + operand_code + ' }';
  return [code, 0];
};
/* ── Domain code generation (template based) ── */
window.BLOCKLY_DOMAIN_CODEGEN = {
  language: 'javascript',
  fileExtension: 'js',
  blocks: {
'App': {
  label: 'Application',
  template: 'app(\'{{name}}\', () => { {{statements:pages}} });',
  fields: ['name', 'theme', 'published', 'tags', 'previewWidths', 'targetPlatforms'],
  fieldTypes: {'name': 'TEXT', 'theme': 'DROPDOWN', 'published': 'BOOLEAN', 'tags': 'TEXT', 'previewWidths': 'INTEGER', 'targetPlatforms': 'DROPDOWN'},
  references: [],
  values: [],
  statements: ['pages']
},
'Page': {
  label: 'Page',
  template: 'page(\'{{title}}\', () => { {{statements:components}} });',
  fields: ['title', 'description', 'backgroundColor', 'scrollable', 'alignment'],
  fieldTypes: {'title': 'TEXT', 'description': 'TEXT', 'backgroundColor': 'COLOUR', 'scrollable': 'BOOLEAN', 'alignment': 'DROPDOWN'},
  references: [],
  values: [],
  statements: ['components']
},
'Label': {
  label: 'Label',
  template: 'label(\'{{text}}\');',
  fields: ['text', 'fontSize', 'textColor'],
  fieldTypes: {'text': 'TEXT', 'fontSize': 'INTEGER', 'textColor': 'COLOUR'},
  references: [],
  values: [],
  statements: []
},
'Button': {
  label: 'Button',
  template: 'button(\'{{text}}\', {{value:enabledWhen}});',
  fields: ['text', 'width', 'backgroundColor'],
  fieldTypes: {'text': 'TEXT', 'width': 'INTEGER', 'backgroundColor': 'COLOUR'},
  references: [],
  values: ['enabledWhen'],
  statements: []
},
'Image': {
  label: 'Image',
  template: 'image(\'{{url}}\');',
  fields: ['url', 'rotation', 'opacity'],
  fieldTypes: {'url': 'TEXT', 'rotation': 'ANGLE', 'opacity': 'FLOAT'},
  references: [],
  values: [],
  statements: []
},
'BoolLiteral': {
  label: 'Boolean',
  template: '{{literal}}',
  fields: ['literal'],
  fieldTypes: {'literal': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'PlatformIs': {
  label: 'Platform is',
  template: 'platform === \'{{platform}}\'',
  fields: ['platform'],
  fieldTypes: {'platform': 'DROPDOWN'},
  references: [],
  values: [],
  statements: []
},
'NotExpression': {
  label: 'Not',
  template: '!({{value:operand}})',
  fields: [],
  fieldTypes: {},
  references: [],
  values: ['operand'],
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
      return domainFieldText(block, config, name);
    });
}

function domainFieldText(block, config, name) {
  var value = block.getFieldValue(name);
  if (value === null || value === undefined) return '';
  var type = config && config.fieldTypes ? config.fieldTypes[name] : null;
  if (type === 'BOOLEAN') {
    if (value === true || value === 'TRUE' || value === 'true') return 'true';
    if (value === false || value === 'FALSE' || value === 'false') return 'false';
  }
  return String(value);
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
