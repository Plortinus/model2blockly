// Block definitions for domain "Turtle".
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
	"type": "turtle_move",
	"message0": "%1%2",
	"args0": [{"type": "field_dropdown", "name": "DIR", "options": [["move forward by", "moveForward"], ["move backward by", "moveBackward"]]}, {"type": "input_value", "name": "VALUE", "check": "Number"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Moves the turtle forward or backward by the specified amount.",
	"helpUrl": ""
},
{
	"type": "turtle_move_internal",
	"message0": "%1%2",
	"args0": [{"type": "field_dropdown", "name": "DIR", "options": [["move forward by", "moveForward"], ["move backward by", "moveBackward"]]}, {"type": "field_dropdown", "name": "VALUE", "options": [["20", "distance20"], ["50", "distance50"], ["100", "distance100"], ["150", "distance150"]]}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Moves the turtle forward or backward by the specified amount.",
	"helpUrl": ""
},
{
	"type": "turtle_turn",
	"message0": "turn %1 by%2",
	"args0": [{"type": "field_dropdown", "name": "DIR", "options": [["right ↻", "turnRight"], ["left ↺", "turnLeft"]]}, {"type": "input_value", "name": "VALUE", "check": "Number"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Turns the turtle left or right by the specified number of degrees.",
	"helpUrl": ""
},
{
	"type": "turtle_turn_internal",
	"message0": "turn %1 by%2",
	"args0": [{"type": "field_dropdown", "name": "DIR", "options": [["right ↻", "turnRight"], ["left ↺", "turnLeft"]]}, {"type": "field_dropdown", "name": "VALUE", "options": [["1°", "angle1"], ["45°", "angle45"], ["72°", "angle72"], ["90°", "angle90"], ["120°", "angle120"], ["144°", "angle144"]]}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Turns the turtle left or right by the specified number of degrees.",
	"helpUrl": ""
},
{
	"type": "turtle_width",
	"message0": "set width to%1",
	"args0": [{"type": "input_value", "name": "WIDTH", "check": "Number"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Changes the width of the pen.",
	"helpUrl": ""
},
{
	"type": "turtle_pen",
	"message0": "%1",
	"args0": [{"type": "field_dropdown", "name": "PEN", "options": [["pen up", "penUp"], ["pen down", "penDown"]]}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Lifts or lowers the pen, to stop or start drawing.",
	"helpUrl": ""
},
{
	"type": "turtle_colour",
	"message0": "set colour to%1",
	"args0": [{"type": "input_value", "name": "COLOUR", "check": "Colour"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 20,
	"tooltip": "Changes the colour of the pen.",
	"helpUrl": ""
},
{
	"type": "turtle_colour_internal",
	"message0": "set colour to%1",
	"args0": [{"type": "field_colour", "name": "COLOUR", "colour": "#ff0000"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 20,
	"tooltip": "Changes the colour of the pen.",
	"helpUrl": ""
},
{
	"type": "turtle_visibility",
	"message0": "%1",
	"args0": [{"type": "field_dropdown", "name": "VISIBILITY", "options": [["hide turtle", "hideTurtle"], ["show turtle", "showTurtle"]]}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Makes the turtle (circle and arrow) visible or invisible.",
	"helpUrl": ""
},
{
	"type": "turtle_print",
	"message0": "print%1",
	"args0": [{"type": "input_value", "name": "TEXT", "check": "Text"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Draws text in the turtle\'s direction at its location.",
	"helpUrl": "https://en.wikipedia.org/wiki/Printing"
},
{
	"type": "turtle_font",
	"message0": "font%1font size%2%3",
	"args0": [{"type": "field_dropdown", "name": "FONT", "options": [["Arial", "Arial"], ["Courier New", "CourierNew"], ["Georgia", "Georgia"], ["Impact", "Impact"], ["Times New Roman", "TimesNewRoman"], ["Trebuchet MS", "TrebuchetMS"], ["Verdana", "Verdana"]]}, {"type": "field_number", "name": "FONTSIZE", "value": 18, "precision": 0.1, "min": 1, "max": 1000}, {"type": "field_dropdown", "name": "FONTSTYLE", "options": [["normal", "normal"], ["italic", "italic"], ["bold", "bold"]]}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Sets the font used by the print block.",
	"helpUrl": "https://en.wikipedia.org/wiki/Font"
},
{
	"type": "turtle_repeat_internal",
	"message0": "repeat%1do%2",
	"args0": [{"type": "field_dropdown", "name": "TIMES", "options": [["3", "times3"], ["4", "times4"], ["5", "times5"], ["360", "times360"]]}, {"type": "input_statement", "name": "DO", "check": "TurtleCommand"}],
	"previousStatement": "TurtleCommand", "nextStatement": "TurtleCommand",
	"inputsInline": false,
	"colour": 120,
	"tooltip": "Do some statements several times.",
	"helpUrl": "https://en.wikipedia.org/wiki/For_loop"
}
];
