// Block definitions for domain "CodegenWorkspace".
// Auto-generated from metamodel.
window.BLOCKLY_REFERENCE_TARGETS = {
};

(function() {
  if (typeof Blockly === 'undefined' || !Blockly.FieldTextInput || !Blockly.fieldRegistry) return;

  function hasRegisteredField(type) {
    return Blockly.fieldRegistry.getClass && Blockly.fieldRegistry.getClass(type);
  }

  // Blockly distributes colour and angle fields as optional plugins.  Generated
  // editors provide small compatible fallbacks so these widgets also work when
  // only the core Blockly bundle is loaded.
  if (!hasRegisteredField('field_colour')) {
    class FieldColourFallback extends Blockly.FieldTextInput {
      constructor(value, validator, config) {
        super(value || '#ff0000', validator, config);
      }
      static fromJson(options) {
        return new FieldColourFallback(options.colour || '#ff0000', undefined, options);
      }
      doClassValidation_(value) {
        var colour = String(value || '').trim().toLowerCase();
        return /^#[0-9a-f]{6}$/.test(colour) ? colour : null;
      }
      showEditor_() {
        var field = this;
        var picker = document.createElement('input');
        picker.type = 'color';
        picker.value = this.getValue();
        picker.setAttribute('aria-label', this.name || 'Colour');
        picker.style.position = 'fixed';
        picker.style.left = '-1000px';
        picker.addEventListener('input', function() { field.setValue(picker.value); });
        picker.addEventListener('change', function() {
          field.setValue(picker.value);
          picker.remove();
        });
        picker.addEventListener('blur', function() {
          window.setTimeout(function() { picker.remove(); }, 0);
        });
        document.body.appendChild(picker);
        picker.click();
      }
    }
    Blockly.fieldRegistry.register('field_colour', FieldColourFallback);
  }

  if (!hasRegisteredField('field_angle') && Blockly.FieldNumber) {
    class FieldAngleFallback extends Blockly.FieldNumber {
      constructor(value, validator, config) {
        super(value === null || value === undefined ? 90 : value, 0, 360, 1, validator, config);
      }
      static fromJson(options) {
        return new FieldAngleFallback(options.angle === undefined ? 90 : options.angle, undefined, options);
      }
    }
    Blockly.fieldRegistry.register('field_angle', FieldAngleFallback);
  }

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
	"type": "Program",
	"message0": "Program",
					"args0": [],
	"message1": "name %1",
						"args1": [{"type": "field_input", "name": "name", "text": "Demo"}],
	"message2": "steps %1",
						"args2": [{"type": "input_statement", "name": "steps", "check": "Command"}],
	"inputsInline": false,
	"colour": 260,
	"tooltip": "Program",
	"helpUrl": ""
},
{
	"type": "Log",
	"message0": "Log",
					"args0": [],
	"message1": "message %1",
						"args1": [{"type": "field_input", "name": "message", "text": "Ready"}],
	"previousStatement": "Command", "nextStatement": "Command",
	"inputsInline": false,
	"colour": 30,
	"tooltip": "Log",
	"helpUrl": ""
},
{
	"type": "Wait",
	"message0": "Wait",
					"args0": [],
	"message1": "milliseconds %1",
						"args1": [{"type": "field_number", "name": "milliseconds", "value": 250, "precision": 1}],
	"previousStatement": "Command", "nextStatement": "Command",
	"inputsInline": false,
	"colour": 30,
	"tooltip": "Wait",
	"helpUrl": ""
},
{
	"type": "BooleanLiteral",
	"message0": "BooleanLiteral",
					"args0": [],
	"message1": "literal %1",
						"args1": [{"type": "field_checkbox", "name": "literal", "checked": true}],
	"output": null,
	"inputsInline": false,
	"colour": 230,
	"tooltip": "BooleanLiteral",
	"helpUrl": ""
}
];
