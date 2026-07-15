// Block definitions for domain "Pond".
// Auto-generated from metamodel.
window.BLOCKLY_REFERENCE_TARGETS = {
};

(function() {
  if (typeof Blockly === 'undefined' || !Blockly.FieldTextInput || !Blockly.fieldRegistry) return;
  if (Blockly.fieldRegistry.getClass && Blockly.fieldRegistry.getClass('field_reference_multiselect')) return;

function parseReferenceMultiValue(value) {
    if (Array.isArray(value)) return value;
    if (value === null || value === undefined) return [];
    return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
  }
  window.parseReferenceMultiValue = parseReferenceMultiValue;
  window.parseBlocklyListField = parseReferenceMultiValue;

  function closeMultiValueDialog() {
    var existing = document.getElementById('multiValueDialog');
    if (existing) existing.remove();
  }

  window.openMultiValueDialog = function(field) {
    closeMultiValueDialog();
    var values = parseReferenceMultiValue(field.getValue());
    var overlay = document.createElement('div');
    overlay.id = 'multiValueDialog';
    overlay.className = 'reference-dialog-backdrop';
    var panel = document.createElement('div');
    panel.className = 'reference-dialog-panel';
    var title = document.createElement('div');
    title.className = 'reference-dialog-title';
    title.textContent = 'Edit ' + (field.name || 'values');
    panel.appendChild(title);
    var input = document.createElement('textarea');
    input.className = 'reference-dialog-textarea';
    input.rows = 8;
    input.value = values.join('\\n');
    panel.appendChild(input);
    var hint = document.createElement('div');
    hint.className = 'reference-dialog-empty';
    hint.textContent = 'One value per line. Values are stored as a comma-separated list.';
    panel.appendChild(hint);
    var actions = document.createElement('div');
    actions.className = 'reference-dialog-actions';
    var clearBtn = document.createElement('button');
    clearBtn.type = 'button';
    clearBtn.textContent = 'Clear';
    clearBtn.addEventListener('click', function() { input.value = ''; });
    var cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.textContent = 'Cancel';
    cancelBtn.addEventListener('click', closeMultiValueDialog);
    var applyBtn = document.createElement('button');
    applyBtn.type = 'button';
    applyBtn.className = 'reference-dialog-primary';
    applyBtn.textContent = 'Apply';
    applyBtn.addEventListener('click', function() {
      field.setValue(parseReferenceMultiValue(input.value).join(', '));
      if (typeof applyValidationWarnings === 'function') applyValidationWarnings(referenceWorkspaceForField(field));
      if (typeof updateOutput === 'function') updateOutput();
      closeMultiValueDialog();
    });
    actions.appendChild(clearBtn);
    actions.appendChild(cancelBtn);
    actions.appendChild(applyBtn);
    panel.appendChild(actions);
    overlay.appendChild(panel);
    overlay.addEventListener('click', function(event) {
      if (event.target === overlay) closeMultiValueDialog();
    });
    document.body.appendChild(overlay);
    input.focus();
  };

  function referenceLabelForBlock(block, labelField) {
    if (!block) return '';
    var label = labelField && block.getField ? block.getFieldValue(labelField) : null;
    return label || block.getFieldValue('displayName') || block.getFieldValue('title') ||
      block.getFieldValue('name') || block.type + '_' + block.id.substring(0, 6);
  }

  function referenceWorkspaceForField(field) {
    var source = field && field.getSourceBlock ? field.getSourceBlock() : null;
    return source && source.workspace ? source.workspace : window.workspace;
  }

  function referenceTargetBlocksForField(field) {
    var workspace = referenceWorkspaceForField(field);
    if (!workspace) return [];
    var targetType = field.targetTypeName_ || '';
    var targetTypes = (window.BLOCKLY_REFERENCE_TARGETS && window.BLOCKLY_REFERENCE_TARGETS[targetType]) || [targetType];
    var source = field.getSourceBlock ? field.getSourceBlock() : null;
    return workspace.getAllBlocks(false).filter(function(block) {
      return block !== source && targetTypes.indexOf(block.type) !== -1;
    });
  }

  function referenceDisplayText(field, value) {
    var workspace = referenceWorkspaceForField(field);
    var ids = parseReferenceMultiValue(value);
    if (!ids.length) return '(select)';
    if (!workspace) return ids.join(', ');
    var labels = ids.map(function(id) {
      var block = workspace.getBlockById(id);
      return block ? referenceLabelForBlock(block, field.labelFieldName_) : id;
    });
    return labels.join(', ');
  }

  function closeReferenceMultiDialog() {
    var existing = document.getElementById('referenceMultiDialog');
    if (existing) existing.remove();
  }

  window.openReferenceMultiSelectDialog = function(field) {
    closeReferenceMultiDialog();
    var selected = new Set(parseReferenceMultiValue(field.getValue()));
    var candidates = referenceTargetBlocksForField(field);
    var overlay = document.createElement('div');
    overlay.id = 'referenceMultiDialog';
    overlay.className = 'reference-dialog-backdrop';
    var panel = document.createElement('div');
    panel.className = 'reference-dialog-panel';
    var title = document.createElement('div');
    title.className = 'reference-dialog-title';
    title.textContent = 'Select ' + (field.name || 'references');
    panel.appendChild(title);
    var list = document.createElement('div');
    list.className = 'reference-dialog-list';
    if (!candidates.length) {
      var empty = document.createElement('div');
      empty.className = 'reference-dialog-empty';
      empty.textContent = 'No compatible blocks exist yet.';
      list.appendChild(empty);
    }
    candidates.forEach(function(block) {
      var row = document.createElement('label');
      row.className = 'reference-dialog-row';
      var checkbox = document.createElement('input');
      checkbox.type = 'checkbox';
      checkbox.value = block.id;
      checkbox.checked = selected.has(block.id);
      checkbox.addEventListener('change', function() {
        if (checkbox.checked) selected.add(block.id);
        else selected.delete(block.id);
      });
      var text = document.createElement('span');
      text.textContent = referenceLabelForBlock(block, field.labelFieldName_);
      row.appendChild(checkbox);
      row.appendChild(text);
      list.appendChild(row);
    });
    panel.appendChild(list);
    var actions = document.createElement('div');
    actions.className = 'reference-dialog-actions';
    var clearBtn = document.createElement('button');
    clearBtn.type = 'button';
    clearBtn.textContent = 'Clear';
    clearBtn.addEventListener('click', function() {
      selected.clear();
      list.querySelectorAll('input[type="checkbox"]').forEach(function(input) { input.checked = false; });
    });
    var cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.textContent = 'Cancel';
    cancelBtn.addEventListener('click', closeReferenceMultiDialog);
    var applyBtn = document.createElement('button');
    applyBtn.type = 'button';
    applyBtn.className = 'reference-dialog-primary';
    applyBtn.textContent = 'Apply';
    applyBtn.addEventListener('click', function() {
      field.setValue(Array.from(selected).join(', '));
      if (typeof synchronizeOppositeReferences === 'function') {
        var sourceBlock = field.getSourceBlock ? field.getSourceBlock() : null;
        synchronizeOppositeReferences(referenceWorkspaceForField(field), { blockId: sourceBlock ? sourceBlock.id : null, name: field.name });
      }
      if (typeof applyValidationWarnings === 'function') applyValidationWarnings(referenceWorkspaceForField(field));
      if (typeof updateOutput === 'function') updateOutput();
      closeReferenceMultiDialog();
    });
    actions.appendChild(clearBtn);
    actions.appendChild(cancelBtn);
    actions.appendChild(applyBtn);
    panel.appendChild(actions);
    overlay.appendChild(panel);
    overlay.addEventListener('click', function(event) {
      if (event.target === overlay) closeReferenceMultiDialog();
    });
    document.body.appendChild(overlay);
  };

  class FieldReferenceMultiselect extends Blockly.FieldTextInput {
    constructor(value, validator, config) {
      super(value || '', validator, config);
      this.targetTypeName_ = config && config.targetType ? config.targetType : '';
      this.labelFieldName_ = config && config.labelField ? config.labelField : '';
    }
    static fromJson(options) {
      return new FieldReferenceMultiselect(options.text || '', undefined, options);
    }
    doClassValidation_(value) {
      if (value === null || value === undefined) return '';
      return String(value);
    }
    getDisplayText_() {
      return referenceDisplayText(this, this.getValue());
    }
    showEditor_() {
      window.openReferenceMultiSelectDialog(this);
    }
    refreshDisplay() {
      if (this.forceRerender) this.forceRerender();
    }
  }
  Blockly.fieldRegistry.register('field_reference_multiselect', FieldReferenceMultiselect);

  class FieldMultiValue extends Blockly.FieldTextInput {
    constructor(value, validator, config) {
      super(value || '', validator, config);
    }
    static fromJson(options) {
      return new FieldMultiValue(options.text || '', undefined, options);
    }
    doClassValidation_(value) {
      if (value === null || value === undefined) return '';
      return parseReferenceMultiValue(value).join(', ');
    }
    getDisplayText_() {
      var values = parseReferenceMultiValue(this.getValue());
      if (!values.length) return '(empty)';
      if (values.length <= 2) return values.join(', ');
      return values.length + ' values';
    }
    showEditor_() {
      window.openMultiValueDialog(this);
    }
  }
  Blockly.fieldRegistry.register('field_multivalue', FieldMultiValue);
})();
window.BLOCKLY_BLOCKS = [
{
	"type": "pond_scan",
	"message0": "%1(%2)",
	"args0": [{"type": "field_label", "name": "CALL", "text": "scan"}, {"type": "input_value", "name": "DEGREE", "check": "Number"}],
	"output": "Number",
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Scan for enemies. Specify a direction (0-360). Returns the distance to the closest enemy in that direction. Returns Infinity if no enemy found.",
	"helpUrl": ""
},
{
	"type": "pond_cannon",
	"message0": "%1(%2, %3);",
	"args0": [{"type": "field_label", "name": "CALL", "text": "cannon"}, {"type": "input_value", "name": "DEGREE", "check": "Number"}, {"type": "input_value", "name": "RANGE", "check": "Number"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Fire the cannon. Specify a direction (0-360) and a range (0-70).",
	"helpUrl": ""
},
{
	"type": "pond_swim",
	"message0": "%1(%2);",
	"args0": [{"type": "field_label", "name": "CALL", "text": "swim"}, {"type": "input_value", "name": "DEGREE", "check": "Number"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Swim forward. Specify a direction (0-360).",
	"helpUrl": ""
},
{
	"type": "pond_stop",
	"message0": "%1(%2);",
	"args0": [{"type": "field_label", "name": "CALL", "text": "stop"}, {"type": "field_label", "name": "EMPTY", "text": ""}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Stop swimming. Player will slow to a stop.",
	"helpUrl": ""
},
{
	"type": "pond_health",
	"message0": "%1(%2)",
	"args0": [{"type": "field_label", "name": "CALL", "text": "health"}, {"type": "field_label", "name": "EMPTY", "text": ""}],
	"output": "Number",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Returns the player\'s current health (0 is dead, 100 is healthy).",
	"helpUrl": ""
},
{
	"type": "pond_speed",
	"message0": "%1(%2)",
	"args0": [{"type": "field_label", "name": "CALL", "text": "speed"}, {"type": "field_label", "name": "EMPTY", "text": ""}],
	"output": "Number",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Returns the current speed of the player (0 is stopped, 100 is full speed).",
	"helpUrl": ""
},
{
	"type": "pond_getX",
	"message0": "%1(%2)",
	"args0": [{"type": "field_label", "name": "CALL", "text": "getX"}, {"type": "field_label", "name": "EMPTY", "text": ""}],
	"output": "Number",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Returns the X coordinate of the player (0 is the left edge, 100 is the right edge).",
	"helpUrl": ""
},
{
	"type": "pond_getY",
	"message0": "%1(%2)",
	"args0": [{"type": "field_label", "name": "CALL", "text": "getY"}, {"type": "field_label", "name": "EMPTY", "text": ""}],
	"output": "Number",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Returns the Y coordinate of the player (0 is the bottom edge, 100 is the top edge).",
	"helpUrl": ""
},
{
	"type": "pond_log",
	"message0": "%1(%2);",
	"args0": [{"type": "field_label", "name": "CALL", "text": "log"}, {"type": "input_value", "name": "VALUE", "check": "Number"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Prints a number to your browser\'s console.",
	"helpUrl": ""
},
{
	"type": "controls_if",
	"message0": "if (%1) {%2}",
	"args0": [{"type": "input_value", "name": "IF0", "check": "Boolean"}, {"type": "input_statement", "name": "DO0", "check": "PondCommand"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 210,
	"tooltip": "If a value is true, then do some statements.",
	"helpUrl": "https://github.com/RaspberryPiFoundation/blockly/wiki/IfElse"
},
{
	"type": "logic_compare",
	"message0": "%1 %2 %3",
	"args0": [{"type": "input_value", "name": "A", "check": "Number"}, {"type": "field_dropdown", "name": "OP", "options": [["==", "EQ"], ["!=", "NEQ"], ["‏<", "LT"], ["‏<=", "LTE"], ["‏>", "GT"], ["‏>=", "GTE"]]}, {"type": "input_value", "name": "B", "check": "Number"}],
	"output": "Boolean",
	"inputsInline": true,
	"colour": 210,
	"tooltip": "Return true if both inputs equal each other.",
	"helpUrl": "https://en.wikipedia.org/wiki/Inequality_(mathematics)"
},
{
	"type": "logic_operation",
	"message0": "%1 %2 %3",
	"args0": [{"type": "input_value", "name": "A", "check": "Boolean"}, {"type": "field_dropdown", "name": "OP", "options": [["&&", "AND"], ["||", "OR"]]}, {"type": "input_value", "name": "B", "check": "Boolean"}],
	"output": "Boolean",
	"inputsInline": true,
	"colour": 210,
	"tooltip": "Return true if both inputs are true.",
	"helpUrl": "https://github.com/RaspberryPiFoundation/blockly/wiki/Logic#logical-operations"
},
{
	"type": "logic_boolean",
	"message0": "%1",
	"args0": [{"type": "field_dropdown", "name": "BOOL", "options": [["true", "TRUE"], ["false", "FALSE"]]}],
	"output": "Boolean",
	"inputsInline": false,
	"colour": 210,
	"tooltip": "Returns either true or false.",
	"helpUrl": "https://github.com/RaspberryPiFoundation/blockly/wiki/Logic#values"
},
{
	"type": "controls_whileUntil",
	"message0": "while (%1) {%2}",
	"args0": [{"type": "input_value", "name": "BOOL", "check": "Boolean"}, {"type": "input_statement", "name": "DO", "check": "PondCommand"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "While a value is true, then do some statements.",
	"helpUrl": "https://github.com/RaspberryPiFoundation/blockly/wiki/Loops#repeat"
},
{
	"type": "pond_math_number",
	"message0": "%1",
	"args0": [{"type": "field_number", "name": "NUM", "value": 0, "precision": 0.1}],
	"output": "Number",
	"inputsInline": false,
	"colour": 230,
	"tooltip": "A number.",
	"helpUrl": "https://en.wikipedia.org/wiki/Number"
},
{
	"type": "math_arithmetic",
	"message0": "%1 %2 %3",
	"args0": [{"type": "input_value", "name": "A", "check": "Number"}, {"type": "field_dropdown", "name": "OP", "options": [["+", "ADD"], ["-", "MINUS"], ["*", "MULTIPLY"], ["/", "DIVIDE"]]}, {"type": "input_value", "name": "B", "check": "Number"}],
	"output": "Number",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Return the sum of the two numbers.",
	"helpUrl": "https://en.wikipedia.org/wiki/Arithmetic"
},
{
	"type": "pond_math_single",
	"message0": "%1 (%2)",
	"args0": [{"type": "field_dropdown", "name": "OP", "options": [["Math.sqrt", "ROOT"], ["Math.abs", "ABS"], ["Math.sin_deg", "SIN"], ["Math.cos_deg", "COS"], ["Math.tan_deg", "TAN"], ["Math.asin_deg", "ASIN"], ["Math.acos_deg", "ACOS"], ["Math.atan_deg", "ATAN"]]}, {"type": "input_value", "name": "NUM", "check": "Number"}],
	"output": "Number",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Return the square root of a number.",
	"helpUrl": "https://en.wikipedia.org/wiki/Square_root"
},
{
	"type": "math_random_float",
	"message0": "Math.random  (  )",
	"args0": [],
	"output": "Number",
	"inputsInline": false,
	"colour": 230,
	"tooltip": "Return a random fraction between 0.0 (inclusive) and 1.0 (exclusive).",
	"helpUrl": "https://en.wikipedia.org/wiki/Random_number_generation"
},
{
	"type": "math_change",
	"message0": "%1 += %2;",
	"args0": [{"type": "field_input", "name": "VAR", "text": "name"}, {"type": "input_value", "name": "DELTA", "check": "Number"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 330,
	"tooltip": "Add a number to variable \'name\'.",
	"helpUrl": "https://en.wikipedia.org/wiki/Programming_idiom#Incrementing_a_counter"
},
{
	"type": "variables_set",
	"message0": "var %1 = %2;",
	"args0": [{"type": "field_input", "name": "VAR", "text": "name"}, {"type": "input_value", "name": "VALUE", "check": "Number"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 330,
	"tooltip": "Sets this variable to be equal to the input.",
	"helpUrl": "https://github.com/RaspberryPiFoundation/blockly/wiki/Variables#set"
},
{
	"type": "procedures_defnoreturn",
	"message0": "%1 %2 %3%4%5%6%7",
	"args0": [{"type": "field_label", "name": "FUNCTION", "text": "function"}, {"type": "field_input", "name": "NAME", "text": ""}, {"type": "field_label", "name": "OPEN", "text": "("}, {"type": "field_label", "name": "PARAMS", "text": ""}, {"type": "field_label", "name": "BODY", "text": ") {"}, {"type": "input_statement", "name": "STACK", "check": "PondCommand"}, {"type": "field_label", "name": "CLOSE", "text": "}"}],
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Creates a function with no output.",
	"helpUrl": "https://en.wikipedia.org/wiki/Subroutine"
},
{
	"type": "procedures_defreturn",
	"message0": "%1 %2 %3%4%5%6return %7%8",
	"args0": [{"type": "field_label", "name": "FUNCTION", "text": "function"}, {"type": "field_input", "name": "NAME", "text": ""}, {"type": "field_label", "name": "OPEN", "text": "("}, {"type": "field_label", "name": "PARAMS", "text": ""}, {"type": "field_label", "name": "BODY", "text": ") {"}, {"type": "input_statement", "name": "STACK", "check": "PondCommand"}, {"type": "input_value", "name": "RETURN", "check": "Number"}, {"type": "field_label", "name": "CLOSE", "text": "}"}],
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Creates a function with an output.",
	"helpUrl": "https://en.wikipedia.org/wiki/Subroutine"
},
{
	"type": "procedures_callnoreturn",
	"message0": "%1%2%3",
	"args0": [{"type": "field_label", "name": "NAME", "text": ""}, {"type": "field_label", "name": "OPEN", "text": "("}, {"type": "field_label", "name": "TAIL", "text": ");"}],
	"previousStatement": "PondCommand", "nextStatement": "PondCommand",
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Run the user-defined function \'%1\'.",
	"helpUrl": "https://en.wikipedia.org/wiki/Subroutine"
},
{
	"type": "procedures_callreturn",
	"message0": "%1%2%3",
	"args0": [{"type": "field_label", "name": "NAME", "text": ""}, {"type": "field_label", "name": "OPEN", "text": "("}, {"type": "field_label", "name": "TAIL", "text": ")"}],
	"output": null,
	"inputsInline": true,
	"colour": 290,
	"tooltip": "Run the user-defined function \'%1\' and use its output.",
	"helpUrl": "https://en.wikipedia.org/wiki/Subroutine"
}
];
