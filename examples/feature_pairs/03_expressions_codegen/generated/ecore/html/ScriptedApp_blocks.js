// Block definitions for domain "ScriptedApp".
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
  window.parseReferenceMultiValue = parseReferenceMultiValue;
  window.parseBlocklyListField = parseReferenceMultiValue;

  function normalizedMultiValueType(type) {
    return String(type || 'TEXT').trim().toUpperCase();
  }

  function coerceMultiValueItem(value, type) {
    var itemType = normalizedMultiValueType(type);
    if (itemType === 'INTEGER' || itemType === 'ANGLE') {
      if (typeof value === 'number' && Number.isInteger(value)) return value;
      var integerText = String(value === null || value === undefined ? '' : value).trim();
      if (/^[+-]?\d+$/.test(integerText)) return Number(integerText);
      return value;
    }
    if (itemType === 'FLOAT') {
      if (typeof value === 'number' && Number.isFinite(value)) return value;
      var floatText = String(value === null || value === undefined ? '' : value).trim();
      if (floatText !== '' && Number.isFinite(Number(floatText))) return Number(floatText);
      return value;
    }
    if (itemType === 'BOOLEAN') {
      if (value === true || value === false) return value;
      var booleanText = String(value === null || value === undefined ? '' : value).trim().toLowerCase();
      if (booleanText === 'true' || booleanText === '1' || booleanText === 'yes') return true;
      if (booleanText === 'false' || booleanText === '0' || booleanText === 'no') return false;
      return value;
    }
    return value === null || value === undefined ? '' : String(value);
  }

  function parseBlocklyTypedListField(value, type) {
    return parseReferenceMultiValue(value).map(function(item) {
      return coerceMultiValueItem(item, type);
    });
  }
  window.parseBlocklyTypedListField = parseBlocklyTypedListField;

  function multiValueItemLabel(field, value) {
    if (normalizedMultiValueType(field.itemTypeName_) === 'DROPDOWN') {
      var match = (field.itemOptions_ || []).find(function(option) {
        return String(option[1]) === String(value);
      });
      if (match) return String(match[0]);
    }
    if (typeof value === 'boolean') return value ? 'true' : 'false';
    return String(value);
  }

  function multiValueValidationErrors(field, values) {
    var errors = [];
    var type = normalizedMultiValueType(field.itemTypeName_);
    var min = field.itemMin_ === '' || field.itemMin_ === null || field.itemMin_ === undefined
      ? null : Number(field.itemMin_);
    var max = field.itemMax_ === '' || field.itemMax_ === null || field.itemMax_ === undefined
      ? null : Number(field.itemMax_);
    var lower = Number(field.lowerBound_ || 0);
    var upper = Number(field.upperBound_ || 0);
    if (values.length < lower) errors.push('At least ' + lower + ' value(s) are required.');
    if (upper > 0 && values.length > upper) errors.push('At most ' + upper + ' value(s) are allowed.');
    var allowed = (field.itemOptions_ || []).map(function(option) { return String(option[1]); });
    values.forEach(function(value, index) {
      var label = 'Value ' + (index + 1);
      if (type === 'INTEGER' && !(typeof value === 'number' && Number.isInteger(value))) {
        errors.push(label + ' must be an integer.');
      } else if (type === 'FLOAT' && !(typeof value === 'number' && Number.isFinite(value))) {
        errors.push(label + ' must be a number.');
      } else if (type === 'BOOLEAN' && typeof value !== 'boolean') {
        errors.push(label + ' must be true or false.');
      } else if (type === 'DROPDOWN' && allowed.indexOf(String(value)) === -1) {
        errors.push(label + ' is not one of the permitted enumeration values.');
      } else if (type === 'COLOUR' && !/^#[0-9a-f]{6}$/i.test(String(value))) {
        errors.push(label + ' must be a six-digit colour such as #1a73e8.');
      } else if (type === 'ANGLE' && !(typeof value === 'number' && Number.isInteger(value) && value >= 0 && value <= 360)) {
        errors.push(label + ' must be an integer from 0 to 360.');
      }
      if ((type === 'INTEGER' || type === 'FLOAT' || type === 'ANGLE') && typeof value === 'number') {
        if (Number.isFinite(min) && value < min) errors.push(label + ' must be at least ' + min + '.');
        if (Number.isFinite(max) && value > max) errors.push(label + ' must be at most ' + max + '.');
      }
    });
    if (field.unique_) {
      var seen = new Set();
      values.forEach(function(value) {
        var key = typeof value + ':' + JSON.stringify(value);
        if (seen.has(key) && errors.indexOf('Values must be unique.') === -1) errors.push('Values must be unique.');
        seen.add(key);
      });
    }
    return errors;
  }

  function closeMultiValueDialog() {
    var existing = document.getElementById('multiValueDialog');
    if (existing) existing.remove();
  }

  window.openMultiValueDialog = function(field) {
    closeMultiValueDialog();
    var values = parseBlocklyTypedListField(field.getValue(), field.itemTypeName_);
    var overlay = document.createElement('div');
    overlay.id = 'multiValueDialog';
    overlay.className = 'reference-dialog-backdrop';
    var panel = document.createElement('div');
    panel.className = 'reference-dialog-panel';
    var title = document.createElement('div');
    title.className = 'reference-dialog-title';
    title.textContent = 'Edit ' + (field.label_ || field.name || 'values');
    panel.appendChild(title);
    var list = document.createElement('div');
    list.className = 'reference-dialog-list multivalue-list';
    panel.appendChild(list);
    var errorBox = document.createElement('div');
    errorBox.className = 'multivalue-errors';
    panel.appendChild(errorBox);
    var hint = document.createElement('div');
    hint.className = 'multivalue-hint';
    var normalizedType = normalizedMultiValueType(field.itemTypeName_);
    var typeLabel = normalizedType === 'DROPDOWN' ? 'enum' : normalizedType.toLowerCase();
    var bounds = (field.lowerBound_ || 0) + '..' + (field.upperBound_ > 0 ? field.upperBound_ : '*');
    var range = field.itemMin_ !== '' || field.itemMax_ !== ''
      ? ' Value range: ' + (field.itemMin_ !== '' ? field.itemMin_ : '-∞')
        + '..' + (field.itemMax_ !== '' ? field.itemMax_ : '∞') + '.'
      : '';
    hint.textContent = 'Element type: ' + typeLabel + '. Cardinality: ' + bounds + '.' + range;
    panel.appendChild(hint);

    function createValueControl(value) {
      var type = normalizedMultiValueType(field.itemTypeName_);
      var control;
      if (type === 'BOOLEAN' || type === 'DROPDOWN') {
        control = document.createElement('select');
        var options = type === 'BOOLEAN'
          ? [['true', 'true'], ['false', 'false']]
          : (field.itemOptions_ || []);
        options.forEach(function(option) {
          var item = document.createElement('option');
          item.textContent = String(option[0]);
          item.value = String(option[1]);
          control.appendChild(item);
        });
        control.value = typeof value === 'boolean' ? String(value) : String(value === undefined ? '' : value);
      } else {
        control = document.createElement('input');
        control.type = type === 'COLOUR' ? 'color'
          : (type === 'INTEGER' || type === 'FLOAT' || type === 'ANGLE' ? 'number' : 'text');
        if (type === 'INTEGER') control.step = '1';
        if (type === 'FLOAT') control.step = 'any';
        if (type === 'ANGLE') {
          control.step = '1';
          control.min = '0';
          control.max = '360';
        }
        if ((type === 'INTEGER' || type === 'FLOAT') && field.itemMin_ !== '') control.min = field.itemMin_;
        if ((type === 'INTEGER' || type === 'FLOAT') && field.itemMax_ !== '') control.max = field.itemMax_;
        var nextValue = value === null || value === undefined ? '' : String(value);
        if (type === 'COLOUR' && !/^#[0-9a-f]{6}$/i.test(nextValue)) nextValue = '#000000';
        control.value = nextValue;
      }
      control.className = 'multivalue-control';
      return control;
    }

    function addRow(value) {
      var row = document.createElement('div');
      row.className = 'reference-dialog-row multivalue-row';
      var control = createValueControl(value);
      row.__multiValueControl = control;
      row.appendChild(control);
      if (field.ordered_) {
        var upBtn = document.createElement('button');
        upBtn.type = 'button';
        upBtn.className = 'multivalue-icon-button';
        upBtn.title = 'Move up';
        upBtn.textContent = '↑';
        upBtn.addEventListener('click', function() {
          if (row.previousElementSibling) list.insertBefore(row, row.previousElementSibling);
        });
        row.appendChild(upBtn);
        var downBtn = document.createElement('button');
        downBtn.type = 'button';
        downBtn.className = 'multivalue-icon-button';
        downBtn.title = 'Move down';
        downBtn.textContent = '↓';
        downBtn.addEventListener('click', function() {
          if (row.nextElementSibling) list.insertBefore(row.nextElementSibling, row);
        });
        row.appendChild(downBtn);
      }
      var removeBtn = document.createElement('button');
      removeBtn.type = 'button';
      removeBtn.className = 'multivalue-icon-button';
      removeBtn.title = 'Remove';
      removeBtn.textContent = '×';
      removeBtn.addEventListener('click', function() { row.remove(); });
      row.appendChild(removeBtn);
      list.appendChild(row);
      return control;
    }

    values.forEach(addRow);
    var actions = document.createElement('div');
    actions.className = 'reference-dialog-actions';
    var clearBtn = document.createElement('button');
    clearBtn.type = 'button';
    clearBtn.textContent = 'Clear';
    clearBtn.addEventListener('click', function() {
      list.innerHTML = '';
      errorBox.textContent = '';
    });
    var addBtn = document.createElement('button');
    addBtn.type = 'button';
    addBtn.textContent = 'Add value';
    addBtn.addEventListener('click', function() {
      var control = addRow(normalizedMultiValueType(field.itemTypeName_) === 'BOOLEAN' ? false : '');
      control.focus();
    });
    var cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.textContent = 'Cancel';
    cancelBtn.addEventListener('click', closeMultiValueDialog);
    var applyBtn = document.createElement('button');
    applyBtn.type = 'button';
    applyBtn.className = 'reference-dialog-primary';
    applyBtn.textContent = 'Apply';
    applyBtn.addEventListener('click', function() {
      var nextValues = Array.from(list.children).map(function(row) {
        var control = row.__multiValueControl;
        return coerceMultiValueItem(control ? control.value : '', field.itemTypeName_);
      }).filter(function(value) {
        return !(typeof value === 'string' && value.trim() === '');
      });
      var errors = multiValueValidationErrors(field, nextValues);
      if (errors.length) {
        errorBox.textContent = errors.join(' ');
        return;
      }
      field.setValue(JSON.stringify(nextValues));
      if (typeof applyValidationWarnings === 'function') applyValidationWarnings(referenceWorkspaceForField(field));
      if (typeof updateOutput === 'function') updateOutput();
      closeMultiValueDialog();
    });
    actions.appendChild(clearBtn);
    actions.appendChild(addBtn);
    actions.appendChild(cancelBtn);
    actions.appendChild(applyBtn);
    panel.appendChild(actions);
    overlay.appendChild(panel);
    overlay.addEventListener('click', function(event) {
      if (event.target === overlay) closeMultiValueDialog();
    });
    document.body.appendChild(overlay);
    var firstControl = list.querySelector('.multivalue-control');
    (firstControl || addBtn).focus();
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
      super('', validator, config);
      config = config || {};
		      this.itemTypeName_ = config.itemType || 'TEXT';
		      this.label_ = config.label || '';
      this.itemOptions_ = Array.isArray(config.options) ? config.options : [];
      this.itemMin_ = config.min === undefined || config.min === null ? '' : config.min;
      this.itemMax_ = config.max === undefined || config.max === null ? '' : config.max;
      this.lowerBound_ = Number(config.lowerBound || 0);
      this.upperBound_ = Number(config.upperBound || 0);
      this.unique_ = config.unique !== false;
      this.ordered_ = config.ordered !== false;
      this.setValue(value || '');
    }
    static fromJson(options) {
      return new FieldMultiValue(options.text || '', undefined, options);
    }
    doClassValidation_(value) {
      if (value === null || value === undefined || value === '') return '[]';
      return JSON.stringify(parseBlocklyTypedListField(value, this.itemTypeName_));
    }
    getDisplayText_() {
      var values = parseBlocklyTypedListField(this.getValue(), this.itemTypeName_);
      if (!values.length) return '(empty)';
      if (values.length <= 2) return values.map(function(value) {
        return multiValueItemLabel(this, value);
      }, this).join(', ');
      return values.length + ' values';
    }
    showEditor_() {
      window.openMultiValueDialog(this);
    }
    getValidationErrors() {
      return multiValueValidationErrors(
        this,
        parseBlocklyTypedListField(this.getValue(), this.itemTypeName_)
      );
    }
  }
  Blockly.fieldRegistry.register('field_multivalue', FieldMultiValue);
})();
window.BLOCKLY_BLOCKS = [
{
	"type": "App",
	"message0": "Application",
					"args0": [],
	"message1": "name %1",
						"args1": [{"type": "field_input", "name": "name", "text": "Welcome App"}],
	"message2": "theme %1",
						"args2": [{"type": "field_dropdown", "name": "theme", "options": [["light", "light"], ["dark", "dark"]]}],
	"message3": "published %1",
						"args3": [{"type": "field_checkbox", "name": "published", "checked": false}],
	"message4": "tags %1",
						"args4": [{"type": "field_multivalue", "name": "tags", "label": "tags", "text": "demo,welcome", "itemType": "TEXT", "options": [], "min": "", "max": "", "lowerBound": 0, "upperBound": 3, "unique": true, "ordered": true}],
	"message5": "previewWidths %1",
						"args5": [{"type": "field_multivalue", "name": "previewWidths", "label": "previewWidths", "text": "360,768,1440", "itemType": "INTEGER", "options": [], "min": "320", "max": "1920", "lowerBound": 1, "upperBound": 4, "unique": true, "ordered": true}],
	"message6": "targetPlatforms %1",
						"args6": [{"type": "field_multivalue", "name": "targetPlatforms", "label": "targetPlatforms", "text": "web,android", "itemType": "DROPDOWN", "options": [["web", "web"], ["android", "android"], ["ios", "ios"]], "min": "", "max": "", "lowerBound": 1, "upperBound": 3, "unique": true, "ordered": true}],
	"message7": "pages %1",
						"args7": [{"type": "input_statement", "name": "pages", "check": "Page"}],
	"inputsInline": false,
	"colour": 210,
	"tooltip": "Configure the application and add its pages.",
	"helpUrl": ""
},
{
	"type": "Page",
	"message0": "Page",
					"args0": [],
	"message1": "title %1",
						"args1": [{"type": "field_input", "name": "title", "text": "Home"}],
	"message2": "description %1",
						"args2": [{"type": "field_input", "name": "description", "text": "Main page"}],
	"message3": "backgroundColor %1",
						"args3": [{"type": "field_colour", "name": "backgroundColor", "colour": "#ffffff"}],
	"message4": "scrollable %1",
						"args4": [{"type": "field_checkbox", "name": "scrollable", "checked": true}],
	"message5": "alignment %1",
						"args5": [{"type": "field_dropdown", "name": "alignment", "options": [["left", "left"], ["center", "center"], ["right", "right"]]}],
	"message6": "components %1",
						"args6": [{"type": "input_statement", "name": "components", "check": "Component"}],
	"previousStatement": "Page", "nextStatement": "Page",
	"inputsInline": false,
	"colour": 160,
	"tooltip": "Configure one page and the components shown on it.",
	"helpUrl": ""
},
{
	"type": "Label",
	"message0": "Label",
					"args0": [],
	"message1": "text %1",
						"args1": [{"type": "field_input", "name": "text", "text": "Welcome"}],
	"message2": "fontSize %1",
						"args2": [{"type": "field_number", "name": "fontSize", "value": 24, "precision": 1, "min": 8, "max": 72}],
	"message3": "textColor %1",
						"args3": [{"type": "field_colour", "name": "textColor", "colour": "#202124"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": false,
	"colour": 290,
	"tooltip": "Display text on a page.",
	"helpUrl": ""
},
{
	"type": "Button",
	"message0": "Button",
					"args0": [],
	"message1": "text %1",
						"args1": [{"type": "field_input", "name": "text", "text": "Continue"}],
	"message2": "width %1",
						"args2": [{"type": "field_number", "name": "width", "value": 160, "precision": 1, "min": 60, "max": 320}],
	"message3": "backgroundColor %1",
						"args3": [{"type": "field_colour", "name": "backgroundColor", "colour": "#1a73e8"}],
	"message4": "enabledWhen %1",
						"args4": [{"type": "input_value", "name": "enabledWhen", "check": "Expression"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": false,
	"colour": 20,
	"tooltip": "Display a button that is enabled when its condition holds.",
	"helpUrl": ""
},
{
	"type": "Image",
	"message0": "Image",
					"args0": [],
	"message1": "url %1",
						"args1": [{"type": "field_input", "name": "url", "text": "https://example.org/welcome.png"}],
	"message2": "rotation %1",
						"args2": [{"type": "field_angle", "name": "rotation", "angle": 0}],
	"message3": "opacity %1",
						"args3": [{"type": "field_number", "name": "opacity", "value": 1, "precision": 0.1, "min": 0, "max": 1}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": false,
	"colour": 65,
	"tooltip": "Display an image from a URL.",
	"helpUrl": ""
},
{
	"type": "BoolLiteral",
	"message0": "Boolean",
					"args0": [],
	"message1": "literal %1",
						"args1": [{"type": "field_checkbox", "name": "literal", "checked": true}],
	"output": "Expression",
	"inputsInline": false,
	"colour": 230,
	"tooltip": "A fixed true or false value.",
	"helpUrl": ""
},
{
	"type": "PlatformIs",
	"message0": "Platform is",
					"args0": [],
	"message1": "platform %1",
						"args1": [{"type": "field_dropdown", "name": "platform", "options": [["web", "web"], ["android", "android"], ["ios", "ios"]]}],
	"output": "Expression",
	"inputsInline": false,
	"colour": 230,
	"tooltip": "True when the application runs on the selected platform.",
	"helpUrl": ""
},
{
	"type": "NotExpression",
	"message0": "Not",
					"args0": [],
	"message1": "operand %1",
						"args1": [{"type": "input_value", "name": "operand", "check": "Expression"}],
	"output": "Expression",
	"inputsInline": false,
	"colour": 230,
	"tooltip": "Negate the nested condition.",
	"helpUrl": ""
}
];
