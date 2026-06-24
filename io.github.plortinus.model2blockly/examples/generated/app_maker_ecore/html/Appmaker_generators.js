// Code generators for domain "Appmaker".
// Auto-generated from metamodel.
function parseBlocklyListField(value) {
  if (Array.isArray(value)) return value;
  if (value === null || value === undefined) return [];
  return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
}
window.parseBlocklyListField = parseBlocklyListField;

javascript.javascriptGenerator.forBlock['App'] = function(block) {
  var name = block.getFieldValue('name');
  var theme = block.getFieldValue('theme');
  var dataSources_code = javascript.javascriptGenerator.statementToCode(block, 'dataSources');
  var dataSources_stmt = (dataSources_code || '').trim().replace(/,\\s*$/, '');
  var pages_code = javascript.javascriptGenerator.statementToCode(block, 'pages');
  var pages_stmt = (pages_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "App", "_blockId": ' + JSON.stringify(block.id) +  ', "name": ' + JSON.stringify(name) +  ', "theme": ' + JSON.stringify(theme) +   ', "dataSources": ' + (dataSources_stmt ? '[' + dataSources_stmt + ']' : '[]') +   ', "pages": ' + (pages_stmt ? '[' + pages_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['DataSource'] = function(block) {
  var name = block.getFieldValue('name');
  var url = block.getFieldValue('url');
  var method = block.getFieldValue('method');
  var cacheEnabled = block.getFieldValue('cacheEnabled');
  return '{' +   '"_type": "DataSource", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "url": ' + JSON.stringify(url) +  ', "method": ' + JSON.stringify(method) +  ', "cacheEnabled": ' + JSON.stringify(cacheEnabled) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Page'] = function(block) {
  var title = block.getFieldValue('title');
  var route = block.getFieldValue('route');
  var layout = block.getFieldValue('layout');
  var navigationStyle = block.getFieldValue('navigationStyle');
  var backgroundColor = block.getFieldValue('backgroundColor');
  var scrollable = block.getFieldValue('scrollable');
  var components_code = javascript.javascriptGenerator.statementToCode(block, 'components');
  var components_stmt = (components_code || '').trim().replace(/,\\s*$/, '');
  var onEnter_code = javascript.javascriptGenerator.statementToCode(block, 'onEnter');
  var onEnter_stmt = (onEnter_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Page", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +  ', "route": ' + JSON.stringify(route) +  ', "layout": ' + JSON.stringify(layout) +  ', "navigationStyle": ' + JSON.stringify(navigationStyle) +  ', "backgroundColor": ' + JSON.stringify(backgroundColor) +  ', "scrollable": ' + JSON.stringify(scrollable) +   ', "components": ' + (components_stmt ? '[' + components_stmt + ']' : '[]') +   ', "onEnter": ' + (onEnter_stmt ? '[' + onEnter_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['TextLabel'] = function(block) {
  var textStyle = block.getFieldValue('textStyle');
  var alignment = block.getFieldValue('alignment');
  var content_code = javascript.javascriptGenerator.valueToCode(block, 'content', 0) || 'null';
  return '{' +   '"_type": "TextLabel", "_blockId": ' + JSON.stringify(block.id) +  ', "textStyle": ' + JSON.stringify(textStyle) +  ', "alignment": ' + JSON.stringify(alignment) +  ', "content": ' + content_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['Button'] = function(block) {
  var labelText = block.getFieldValue('labelText');
  var style = block.getFieldValue('style');
  var onClick_code = javascript.javascriptGenerator.statementToCode(block, 'onClick');
  var onClick_stmt = (onClick_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Button", "_blockId": ' + JSON.stringify(block.id) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "style": ' + JSON.stringify(style) +   ', "onClick": ' + (onClick_stmt ? '[' + onClick_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['TextInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var hint = block.getFieldValue('hint');
  var inputType = block.getFieldValue('inputType');
  var mandatory = block.getFieldValue('mandatory');
  return '{' +   '"_type": "TextInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "hint": ' + JSON.stringify(hint) +  ', "inputType": ' + JSON.stringify(inputType) +  ', "mandatory": ' + JSON.stringify(mandatory) + ' },\n';
};
javascript.javascriptGenerator.forBlock['TextArea'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var hint = block.getFieldValue('hint');
  var rows = block.getFieldValue('rows');
  var mandatory = block.getFieldValue('mandatory');
  return '{' +   '"_type": "TextArea", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "hint": ' + JSON.stringify(hint) +  ', "rows": ' + JSON.stringify(rows) +  ', "mandatory": ' + JSON.stringify(mandatory) + ' },\n';
};
javascript.javascriptGenerator.forBlock['ImageView'] = function(block) {
  var altText = block.getFieldValue('altText');
  var fit = block.getFieldValue('fit');
  var url_code = javascript.javascriptGenerator.valueToCode(block, 'url', 0) || 'null';
  return '{' +   '"_type": "ImageView", "_blockId": ' + JSON.stringify(block.id) +  ', "altText": ' + JSON.stringify(altText) +  ', "fit": ' + JSON.stringify(fit) +  ', "url": ' + url_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['ListView'] = function(block) {
  var emptyText = block.getFieldValue('emptyText');
  var source = block.getFieldValue('source');
  var itemTitle_code = javascript.javascriptGenerator.valueToCode(block, 'itemTitle', 0) || 'null';
  return '{' +   '"_type": "ListView", "_blockId": ' + JSON.stringify(block.id) +  ', "emptyText": ' + JSON.stringify(emptyText) +  ', "source": ' + JSON.stringify(source) +  ', "itemTitle": ' + itemTitle_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['ToggleSwitch'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var defaultOn = block.getFieldValue('defaultOn');
  return '{' +   '"_type": "ToggleSwitch", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "defaultOn": ' + JSON.stringify(defaultOn) + ' },\n';
};
javascript.javascriptGenerator.forBlock['SelectInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var optionsText = block.getFieldValue('optionsText');
  var initialValue = block.getFieldValue('initialValue');
  var mandatory = block.getFieldValue('mandatory');
  return '{' +   '"_type": "SelectInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "optionsText": ' + JSON.stringify(optionsText) +  ', "initialValue": ' + JSON.stringify(initialValue) +  ', "mandatory": ' + JSON.stringify(mandatory) + ' },\n';
};
javascript.javascriptGenerator.forBlock['CheckboxInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var defaultChecked = block.getFieldValue('defaultChecked');
  return '{' +   '"_type": "CheckboxInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "defaultChecked": ' + JSON.stringify(defaultChecked) + ' },\n';
};
javascript.javascriptGenerator.forBlock['RadioGroup'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var optionsText = block.getFieldValue('optionsText');
  var initialValue = block.getFieldValue('initialValue');
  return '{' +   '"_type": "RadioGroup", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "optionsText": ' + JSON.stringify(optionsText) +  ', "initialValue": ' + JSON.stringify(initialValue) + ' },\n';
};
javascript.javascriptGenerator.forBlock['NumberInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var initialValue = block.getFieldValue('initialValue');
  var minValue = block.getFieldValue('minValue');
  var maxValue = block.getFieldValue('maxValue');
  var stepValue = block.getFieldValue('stepValue');
  return '{' +   '"_type": "NumberInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "initialValue": ' + JSON.stringify(initialValue) +  ', "minValue": ' + JSON.stringify(minValue) +  ', "maxValue": ' + JSON.stringify(maxValue) +  ', "stepValue": ' + JSON.stringify(stepValue) + ' },\n';
};
javascript.javascriptGenerator.forBlock['SliderInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var initialValue = block.getFieldValue('initialValue');
  var minValue = block.getFieldValue('minValue');
  var maxValue = block.getFieldValue('maxValue');
  var stepValue = block.getFieldValue('stepValue');
  return '{' +   '"_type": "SliderInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "initialValue": ' + JSON.stringify(initialValue) +  ', "minValue": ' + JSON.stringify(minValue) +  ', "maxValue": ' + JSON.stringify(maxValue) +  ', "stepValue": ' + JSON.stringify(stepValue) + ' },\n';
};
javascript.javascriptGenerator.forBlock['DateInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var initialValue = block.getFieldValue('initialValue');
  var mandatory = block.getFieldValue('mandatory');
  return '{' +   '"_type": "DateInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "initialValue": ' + JSON.stringify(initialValue) +  ', "mandatory": ' + JSON.stringify(mandatory) + ' },\n';
};
javascript.javascriptGenerator.forBlock['TimeInput'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var initialValue = block.getFieldValue('initialValue');
  var mandatory = block.getFieldValue('mandatory');
  return '{' +   '"_type": "TimeInput", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "initialValue": ' + JSON.stringify(initialValue) +  ', "mandatory": ' + JSON.stringify(mandatory) + ' },\n';
};
javascript.javascriptGenerator.forBlock['FileUpload'] = function(block) {
  var name = block.getFieldValue('name');
  var labelText = block.getFieldValue('labelText');
  var acceptTypes = block.getFieldValue('acceptTypes');
  var multipleFiles = block.getFieldValue('multipleFiles');
  return '{' +   '"_type": "FileUpload", "_blockId": ' + JSON.stringify(block.id) +  ', "_modelId": ' + JSON.stringify(name || block.id) +  ', "name": ' + JSON.stringify(name) +  ', "labelText": ' + JSON.stringify(labelText) +  ', "acceptTypes": ' + JSON.stringify(acceptTypes) +  ', "multipleFiles": ' + JSON.stringify(multipleFiles) + ' },\n';
};
javascript.javascriptGenerator.forBlock['TableView'] = function(block) {
  var columnsText = block.getFieldValue('columnsText');
  var emptyText = block.getFieldValue('emptyText');
  var source = block.getFieldValue('source');
  return '{' +   '"_type": "TableView", "_blockId": ' + JSON.stringify(block.id) +  ', "columnsText": ' + JSON.stringify(columnsText) +  ', "emptyText": ' + JSON.stringify(emptyText) +  ', "source": ' + JSON.stringify(source) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Container'] = function(block) {
  var layout = block.getFieldValue('layout');
  var gap = block.getFieldValue('gap');
  var padding = block.getFieldValue('padding');
  var children_code = javascript.javascriptGenerator.statementToCode(block, 'children');
  var children_stmt = (children_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Container", "_blockId": ' + JSON.stringify(block.id) +  ', "layout": ' + JSON.stringify(layout) +  ', "gap": ' + JSON.stringify(gap) +  ', "padding": ' + JSON.stringify(padding) +   ', "children": ' + (children_stmt ? '[' + children_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Card'] = function(block) {
  var title = block.getFieldValue('title');
  var children_code = javascript.javascriptGenerator.statementToCode(block, 'children');
  var children_stmt = (children_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Card", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +   ', "children": ' + (children_stmt ? '[' + children_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Form'] = function(block) {
  var title = block.getFieldValue('title');
  var endpoint = block.getFieldValue('endpoint');
  var children_code = javascript.javascriptGenerator.statementToCode(block, 'children');
  var children_stmt = (children_code || '').trim().replace(/,\\s*$/, '');
  var onSubmit_code = javascript.javascriptGenerator.statementToCode(block, 'onSubmit');
  var onSubmit_stmt = (onSubmit_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Form", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +  ', "endpoint": ' + JSON.stringify(endpoint) +   ', "children": ' + (children_stmt ? '[' + children_stmt + ']' : '[]') +   ', "onSubmit": ' + (onSubmit_stmt ? '[' + onSubmit_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['TabsView'] = function(block) {
  var panels_code = javascript.javascriptGenerator.statementToCode(block, 'panels');
  var panels_stmt = (panels_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "TabsView", "_blockId": ' + JSON.stringify(block.id) +   ', "panels": ' + (panels_stmt ? '[' + panels_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['TabPanel'] = function(block) {
  var title = block.getFieldValue('title');
  var children_code = javascript.javascriptGenerator.statementToCode(block, 'children');
  var children_stmt = (children_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "TabPanel", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +   ', "children": ' + (children_stmt ? '[' + children_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Modal'] = function(block) {
  var title = block.getFieldValue('title');
  var triggerText = block.getFieldValue('triggerText');
  var openByDefault = block.getFieldValue('openByDefault');
  var children_code = javascript.javascriptGenerator.statementToCode(block, 'children');
  var children_stmt = (children_code || '').trim().replace(/,\\s*$/, '');
  return '{' +   '"_type": "Modal", "_blockId": ' + JSON.stringify(block.id) +  ', "title": ' + JSON.stringify(title) +  ', "triggerText": ' + JSON.stringify(triggerText) +  ', "openByDefault": ' + JSON.stringify(openByDefault) +   ', "children": ' + (children_stmt ? '[' + children_stmt + ']' : '[]') + ' },\n';
};
javascript.javascriptGenerator.forBlock['Toast'] = function(block) {
  var tone = block.getFieldValue('tone');
  var message_code = javascript.javascriptGenerator.valueToCode(block, 'message', 0) || 'null';
  return '{' +   '"_type": "Toast", "_blockId": ' + JSON.stringify(block.id) +  ', "tone": ' + JSON.stringify(tone) +  ', "message": ' + message_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['Divider'] = function(block) {
  var thickness = block.getFieldValue('thickness');
  var dividerColor = block.getFieldValue('dividerColor');
  return '{' +   '"_type": "Divider", "_blockId": ' + JSON.stringify(block.id) +  ', "thickness": ' + JSON.stringify(thickness) +  ', "dividerColor": ' + JSON.stringify(dividerColor) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Spacer'] = function(block) {
  var size = block.getFieldValue('size');
  return '{' +   '"_type": "Spacer", "_blockId": ' + JSON.stringify(block.id) +  ', "size": ' + JSON.stringify(size) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Navigate'] = function(block) {
  var target = block.getFieldValue('target');
  return '{' +   '"_type": "Navigate", "_blockId": ' + JSON.stringify(block.id) +  ', "target": ' + JSON.stringify(target) + ' },\n';
};
javascript.javascriptGenerator.forBlock['Alert'] = function(block) {
  var message_code = javascript.javascriptGenerator.valueToCode(block, 'message', 0) || 'null';
  return '{' +   '"_type": "Alert", "_blockId": ' + JSON.stringify(block.id) +  ', "message": ' + message_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['SubmitForm'] = function(block) {
  var endpoint = block.getFieldValue('endpoint');
  var successTarget = block.getFieldValue('successTarget');
  return '{' +   '"_type": "SubmitForm", "_blockId": ' + JSON.stringify(block.id) +  ', "endpoint": ' + JSON.stringify(endpoint) +  ', "successTarget": ' + JSON.stringify(successTarget) + ' },\n';
};
javascript.javascriptGenerator.forBlock['SetInputValue'] = function(block) {
  var input = block.getFieldValue('input');
  var newValue_code = javascript.javascriptGenerator.valueToCode(block, 'newValue', 0) || 'null';
  return '{' +   '"_type": "SetInputValue", "_blockId": ' + JSON.stringify(block.id) +  ', "input": ' + JSON.stringify(input) +  ', "newValue": ' + newValue_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['OpenUrl'] = function(block) {
  var url_code = javascript.javascriptGenerator.valueToCode(block, 'url', 0) || 'null';
  return '{' +   '"_type": "OpenUrl", "_blockId": ' + JSON.stringify(block.id) +  ', "url": ' + url_code + ' },\n';
};
javascript.javascriptGenerator.forBlock['TextLiteral'] = function(block) {
  var content = block.getFieldValue('content');
  var code = '{' +   '"_type": "TextLiteral", "_blockId": ' + JSON.stringify(block.id) +  ', "content": ' + JSON.stringify(content) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['InputValue'] = function(block) {
  var input = block.getFieldValue('input');
  var code = '{' +   '"_type": "InputValue", "_blockId": ' + JSON.stringify(block.id) +  ', "input": ' + JSON.stringify(input) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['DataField'] = function(block) {
  var fieldName = block.getFieldValue('fieldName');
  var code = '{' +   '"_type": "DataField", "_blockId": ' + JSON.stringify(block.id) +  ', "fieldName": ' + JSON.stringify(fieldName) + ' }';
  return [code, 0];
};
javascript.javascriptGenerator.forBlock['JoinText'] = function(block) {
  var left_code = javascript.javascriptGenerator.valueToCode(block, 'left', 0) || 'null';
  var right_code = javascript.javascriptGenerator.valueToCode(block, 'right', 0) || 'null';
  var code = '{' +   '"_type": "JoinText", "_blockId": ' + JSON.stringify(block.id) +  ', "left": ' + left_code +  ', "right": ' + right_code + ' }';
  return [code, 0];
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
  language: 'javascript',
  fileExtension: 'js',
  blocks: {
'App': {
  label: 'App',
  template: 'app(\"{{name}}\", {\ntheme: \"{{theme}}\",\ndataSources: [\n{{statements:dataSources}}\n],\npages: [\n{{statements:pages}}\n]\n});',
  fields: ['name', 'theme'],
  fieldTypes: {'name': 'TEXT', 'theme': 'DROPDOWN'},
  references: [],
  values: [],
  statements: ['dataSources', 'pages']
},
'DataSource': {
  label: 'Data source',
  template: 'dataSource(\"{{name}}\", {\nurl: \"{{url}}\",\nmethod: \"{{method}}\",\ncacheEnabled: \"{{cacheEnabled}}\" === \"TRUE\"\n});',
  fields: ['name', 'url', 'method', 'cacheEnabled'],
  fieldTypes: {'name': 'TEXT', 'url': 'TEXT', 'method': 'DROPDOWN', 'cacheEnabled': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'Page': {
  label: 'Page',
  template: 'page(\"{{title}}\", {\nroute: \"{{route}}\",\nlayout: \"{{layout}}\",\nnavigation: \"{{navigationStyle}}\",\nbackgroundColor: \"{{backgroundColor}}\",\nscrollable: \"{{scrollable}}\" === \"TRUE\",\nonEnter: [\n{{statements:onEnter}}\n],\ncomponents: [\n{{statements:components}}\n]\n});',
  fields: ['title', 'route', 'layout', 'navigationStyle', 'backgroundColor', 'scrollable'],
  fieldTypes: {'title': 'TEXT', 'route': 'TEXT', 'layout': 'DROPDOWN', 'navigationStyle': 'DROPDOWN', 'backgroundColor': 'COLOUR', 'scrollable': 'BOOLEAN'},
  references: [],
  values: [],
  statements: ['components', 'onEnter']
},
'TextLabel': {
  label: 'Text',
  template: 'text({{value:content}}, { variant: \"{{textStyle}}\", align: \"{{alignment}}\" });',
  fields: ['textStyle', 'alignment'],
  fieldTypes: {'textStyle': 'DROPDOWN', 'alignment': 'DROPDOWN'},
  references: [],
  values: ['content'],
  statements: []
},
'Button': {
  label: 'Button',
  template: 'button(\"{{labelText}}\", \"{{style}}\", () => {\n{{statements:onClick}}\n});',
  fields: ['labelText', 'style'],
  fieldTypes: {'labelText': 'TEXT', 'style': 'DROPDOWN'},
  references: [],
  values: [],
  statements: ['onClick']
},
'TextInput': {
  label: 'Text input',
  template: 'textInput(\"{{name}}\", \"{{labelText}}\", \"{{hint}}\", {\ntype: \"{{inputType}}\",\nmandatory: \"{{mandatory}}\" === \"TRUE\"\n});',
  fields: ['name', 'labelText', 'hint', 'inputType', 'mandatory'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'hint': 'TEXT', 'inputType': 'DROPDOWN', 'mandatory': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'TextArea': {
  label: 'Text area',
  template: 'textArea(\"{{name}}\", \"{{labelText}}\", \"{{hint}}\", { rows: {{rows}}, mandatory: \"{{mandatory}}\" === \"TRUE\" });',
  fields: ['name', 'labelText', 'hint', 'rows', 'mandatory'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'hint': 'TEXT', 'rows': 'INTEGER', 'mandatory': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'ImageView': {
  label: 'Image',
  template: 'image({{value:url}}, \"{{altText}}\", \"{{fit}}\");',
  fields: ['altText', 'fit'],
  fieldTypes: {'altText': 'TEXT', 'fit': 'DROPDOWN'},
  references: [],
  values: ['url'],
  statements: []
},
'ListView': {
  label: 'List view',
  template: 'listView(\"{{source}}\", { title: {{value:itemTitle}}, emptyText: \"{{emptyText}}\" });',
  fields: ['emptyText'],
  fieldTypes: {'emptyText': 'TEXT'},
  references: ['source'],
  values: ['itemTitle'],
  statements: []
},
'ToggleSwitch': {
  label: 'Switch',
  template: 'toggle(\"{{name}}\", \"{{labelText}}\", \"{{defaultOn}}\" === \"TRUE\");',
  fields: ['name', 'labelText', 'defaultOn'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'defaultOn': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'SelectInput': {
  label: 'Select',
  template: 'selectInput(\"{{name}}\", \"{{labelText}}\", \"{{optionsText}}\", \"{{initialValue}}\");',
  fields: ['name', 'labelText', 'optionsText', 'initialValue', 'mandatory'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'optionsText': 'TEXT', 'initialValue': 'TEXT', 'mandatory': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'CheckboxInput': {
  label: 'Checkbox',
  template: 'checkboxInput(\"{{name}}\", \"{{labelText}}\", \"{{defaultChecked}}\" === \"TRUE\");',
  fields: ['name', 'labelText', 'defaultChecked'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'defaultChecked': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'RadioGroup': {
  label: 'Radio group',
  template: 'radioGroup(\"{{name}}\", \"{{labelText}}\", \"{{optionsText}}\", \"{{initialValue}}\");',
  fields: ['name', 'labelText', 'optionsText', 'initialValue'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'optionsText': 'TEXT', 'initialValue': 'TEXT'},
  references: [],
  values: [],
  statements: []
},
'NumberInput': {
  label: 'Number input',
  template: 'numberInput(\"{{name}}\", \"{{labelText}}\", {{initialValue}}, {{minValue}}, {{maxValue}}, {{stepValue}});',
  fields: ['name', 'labelText', 'initialValue', 'minValue', 'maxValue', 'stepValue'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'initialValue': 'INTEGER', 'minValue': 'INTEGER', 'maxValue': 'INTEGER', 'stepValue': 'INTEGER'},
  references: [],
  values: [],
  statements: []
},
'SliderInput': {
  label: 'Slider',
  template: 'sliderInput(\"{{name}}\", \"{{labelText}}\", {{initialValue}}, {{minValue}}, {{maxValue}}, {{stepValue}});',
  fields: ['name', 'labelText', 'initialValue', 'minValue', 'maxValue', 'stepValue'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'initialValue': 'INTEGER', 'minValue': 'INTEGER', 'maxValue': 'INTEGER', 'stepValue': 'INTEGER'},
  references: [],
  values: [],
  statements: []
},
'DateInput': {
  label: 'Date input',
  template: 'dateInput(\"{{name}}\", \"{{labelText}}\", \"{{initialValue}}\");',
  fields: ['name', 'labelText', 'initialValue', 'mandatory'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'initialValue': 'TEXT', 'mandatory': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'TimeInput': {
  label: 'Time input',
  template: 'timeInput(\"{{name}}\", \"{{labelText}}\", \"{{initialValue}}\");',
  fields: ['name', 'labelText', 'initialValue', 'mandatory'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'initialValue': 'TEXT', 'mandatory': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'FileUpload': {
  label: 'File upload',
  template: 'fileUpload(\"{{name}}\", \"{{labelText}}\", \"{{acceptTypes}}\");',
  fields: ['name', 'labelText', 'acceptTypes', 'multipleFiles'],
  fieldTypes: {'name': 'TEXT', 'labelText': 'TEXT', 'acceptTypes': 'TEXT', 'multipleFiles': 'BOOLEAN'},
  references: [],
  values: [],
  statements: []
},
'TableView': {
  label: 'Table',
  template: 'tableView(\"{{source}}\", \"{{columnsText}}\");',
  fields: ['columnsText', 'emptyText'],
  fieldTypes: {'columnsText': 'TEXT', 'emptyText': 'TEXT'},
  references: ['source'],
  values: [],
  statements: []
},
'Container': {
  label: 'Container',
  template: 'container({ layout: \"{{layout}}\", gap: {{gap}}, padding: {{padding}} }, [\n{{statements:children}}\n]);',
  fields: ['layout', 'gap', 'padding'],
  fieldTypes: {'layout': 'DROPDOWN', 'gap': 'INTEGER', 'padding': 'INTEGER'},
  references: [],
  values: [],
  statements: ['children']
},
'Card': {
  label: 'Card',
  template: 'card(\"{{title}}\", [\n{{statements:children}}\n]);',
  fields: ['title'],
  fieldTypes: {'title': 'TEXT'},
  references: [],
  values: [],
  statements: ['children']
},
'Form': {
  label: 'Form',
  template: 'form(\"{{title}}\", \"{{endpoint}}\", [\n{{statements:children}}\n], () => {\n{{statements:onSubmit}}\n});',
  fields: ['title'],
  fieldTypes: {'title': 'TEXT'},
  references: ['endpoint'],
  values: [],
  statements: ['children', 'onSubmit']
},
'TabsView': {
  label: 'Tabs',
  template: 'tabs([\n{{statements:panels}}\n]);',
  fields: [],
  fieldTypes: {},
  references: [],
  values: [],
  statements: ['panels']
},
'TabPanel': {
  label: 'Tab panel',
  template: 'tab(\"{{title}}\", [\n{{statements:children}}\n]);',
  fields: ['title'],
  fieldTypes: {'title': 'TEXT'},
  references: [],
  values: [],
  statements: ['children']
},
'Modal': {
  label: 'Modal',
  template: 'modal(\"{{title}}\", \"{{triggerText}}\", [\n{{statements:children}}\n]);',
  fields: ['title', 'triggerText', 'openByDefault'],
  fieldTypes: {'title': 'TEXT', 'triggerText': 'TEXT', 'openByDefault': 'BOOLEAN'},
  references: [],
  values: [],
  statements: ['children']
},
'Toast': {
  label: 'Toast',
  template: 'toast({{value:message}}, \"{{tone}}\");',
  fields: ['tone'],
  fieldTypes: {'tone': 'DROPDOWN'},
  references: [],
  values: ['message'],
  statements: []
},
'Divider': {
  label: 'Divider',
  template: 'divider({ thickness: {{thickness}}, colour: \"{{dividerColor}}\" });',
  fields: ['thickness', 'dividerColor'],
  fieldTypes: {'thickness': 'INTEGER', 'dividerColor': 'COLOUR'},
  references: [],
  values: [],
  statements: []
},
'Spacer': {
  label: 'Spacer',
  template: 'spacer({{size}});',
  fields: ['size'],
  fieldTypes: {'size': 'INTEGER'},
  references: [],
  values: [],
  statements: []
},
'Navigate': {
  label: 'Navigate',
  template: 'navigate(\"{{target}}\");',
  fields: [],
  fieldTypes: {},
  references: ['target'],
  values: [],
  statements: []
},
'Alert': {
  label: 'Alert',
  template: 'alert({{value:message}});',
  fields: [],
  fieldTypes: {},
  references: [],
  values: ['message'],
  statements: []
},
'SubmitForm': {
  label: 'Submit form',
  template: 'submitForm(\"{{endpoint}}\", \"{{successTarget}}\");',
  fields: [],
  fieldTypes: {},
  references: ['endpoint', 'successTarget'],
  values: [],
  statements: []
},
'SetInputValue': {
  label: 'Set input value',
  template: 'setInputValue(\"{{input}}\", {{value:newValue}});',
  fields: [],
  fieldTypes: {},
  references: ['input'],
  values: ['newValue'],
  statements: []
},
'OpenUrl': {
  label: 'Open URL',
  template: 'openUrl({{value:url}});',
  fields: [],
  fieldTypes: {},
  references: [],
  values: ['url'],
  statements: []
},
'TextLiteral': {
  label: 'Text',
  template: '\"{{content}}\"',
  fields: ['content'],
  fieldTypes: {'content': 'TEXT'},
  references: [],
  values: [],
  statements: []
},
'InputValue': {
  label: 'Input value',
  template: 'inputValue(\"{{input}}\")',
  fields: [],
  fieldTypes: {},
  references: ['input'],
  values: [],
  statements: []
},
'DataField': {
  label: 'Data field',
  template: 'item[\"{{fieldName}}\"]',
  fields: ['fieldName'],
  fieldTypes: {'fieldName': 'TEXT'},
  references: [],
  values: [],
  statements: []
},
'JoinText': {
  label: 'Join text',
  template: 'String({{value:left}}) + String({{value:right}})',
  fields: [],
  fieldTypes: {},
  references: [],
  values: ['left', 'right'],
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
