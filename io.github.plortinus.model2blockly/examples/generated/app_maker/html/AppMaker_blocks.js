// Block definitions for domain "AppMaker".
// Auto-generated from metamodel.
window.BLOCKLY_REFERENCE_TARGETS = {
"DataSource": ["DataSource"],
"TextInput": ["TextInput"],
"Page": ["Page"]
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
	"type": "DataSource",
	"message0": "Data source %1 %2 %3 %4 %5 %6",
	"args0": [{"type": "field_input", "name": "name", "text": "tasks"}, {"type": "field_input", "name": "url", "text": "https://example.com/api/tasks"}, {"type": "field_dropdown", "name": "method", "options": [["GET", "get"], ["POST", "post"], ["PUT", "put"], ["DELETE", "delete"]]}, {"type": "field_checkbox", "name": "cacheEnabled", "checked": true}, {"type": "field_number", "name": "retryCount", "value": 3, "precision": 1, "min": 0, "max": 10}, {"type": "field_multivalue", "name": "headers", "text": "Authorization\nX-Trace-Id"}],
	"previousStatement": "DataSource", "nextStatement": "DataSource",
	"inputsInline": true,
	"colour": 210,
	"tooltip": "Data source",
	"helpUrl": "https://developers.google.com/blockly"
},
{
	"type": "App",
	"message0": "App %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "Task Tracker"}, {"type": "field_dropdown", "name": "theme", "options": [["Light", "light"], ["Dark", "dark"], ["System", "system"]]}, {"type": "input_statement", "name": "dataSources", "check": "DataSource"}, {"type": "input_statement", "name": "pages", "check": "Page"}],
	"inputsInline": true,
	"colour": 260,
	"tooltip": "Root low-code app model.",
	"helpUrl": ""
},
{
	"type": "Page",
	"message0": "Page %1 %2 %3 %4 %5 %6 %7 %8 %9",
	"args0": [{"type": "field_input", "name": "title", "text": "Home"}, {"type": "field_input", "name": "route", "text": "/home"}, {"type": "field_dropdown", "name": "layout", "options": [["Column", "column"], ["Row", "row"], ["Grid", "grid"], ["Tabs", "tabs"]]}, {"type": "field_dropdown", "name": "navigationStyle", "options": [["Standard", "standard"], ["No navigation", "none"], ["Bottom tabs", "bottomTabs"], ["Drawer", "drawer"]]}, {"type": "field_colour", "name": "backgroundColor", "colour": "#ffffff"}, {"type": "field_checkbox", "name": "scrollable", "checked": true}, {"type": "field_multivalue", "name": "tags", "text": "work\nmobile"}, {"type": "input_statement", "name": "components", "check": "Component"}, {"type": "input_statement", "name": "onEnter", "check": "Action"}],
	"previousStatement": "Page", "nextStatement": "Page",
	"inputsInline": true,
	"colour": 260,
	"tooltip": "A navigable page with design settings, components and lifecycle actions.",
	"helpUrl": ""
},
{
	"type": "TextLabel",
	"message0": "Text %1 %2 %3",
	"args0": [{"type": "input_value", "name": "content", "check": "TextExpression"}, {"type": "field_dropdown", "name": "textStyle", "options": [["Title", "title"], ["Subtitle", "subtitle"], ["Body", "body"], ["Caption", "caption"]]}, {"type": "field_dropdown", "name": "alignment", "options": [["Start", "start"], ["Center", "center"], ["End", "end"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Text",
	"helpUrl": ""
},
{
	"type": "Button",
	"message0": "Button %1 %2 %3",
	"args0": [{"type": "field_input", "name": "labelText", "text": "Save"}, {"type": "field_dropdown", "name": "style", "options": [["Primary", "primary"], ["Secondary", "secondary"], ["Danger", "danger"], ["Ghost", "ghost"]]}, {"type": "input_statement", "name": "onClick", "check": "Action"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Button",
	"helpUrl": ""
},
{
	"type": "TextInput",
	"message0": "Text input %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "taskTitle"}, {"type": "field_input", "name": "labelText", "text": "Task title"}, {"type": "field_input", "name": "hint", "text": "What needs to be done?"}, {"type": "field_dropdown", "name": "inputType", "options": [["Text", "plainText"], ["Email", "email"], ["Password", "password"], ["Number", "numeric"]]}, {"type": "field_checkbox", "name": "mandatory", "checked": true}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Text input",
	"helpUrl": ""
},
{
	"type": "TextArea",
	"message0": "Text area %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "notes"}, {"type": "field_input", "name": "labelText", "text": "Notes"}, {"type": "field_input", "name": "hint", "text": "Add details"}, {"type": "field_number", "name": "rows", "value": 4, "precision": 1, "min": 2, "max": 12}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Text area",
	"helpUrl": ""
},
{
	"type": "ImageView",
	"message0": "Image %1 %2 %3",
	"args0": [{"type": "input_value", "name": "url", "check": "TextExpression"}, {"type": "field_input", "name": "altText", "text": "Preview"}, {"type": "field_dropdown", "name": "fit", "options": [["Cover", "cover"], ["Contain", "contain"], ["Fill", "fill"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Image",
	"helpUrl": ""
},
{
	"type": "ListView",
	"message0": "List view %1 %2 %3",
	"args0": [{"type": "field_dropdown", "name": "source", "options": [["(none)", ""]]}, {"type": "input_value", "name": "itemTitle", "check": "TextExpression"}, {"type": "field_input", "name": "emptyText", "text": "No items yet"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "List view",
	"helpUrl": ""
},
{
	"type": "ToggleSwitch",
	"message0": "Switch %1 %2 %3",
	"args0": [{"type": "field_input", "name": "name", "text": "notifyMe"}, {"type": "field_input", "name": "labelText", "text": "Notify me"}, {"type": "field_checkbox", "name": "defaultOn", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Switch",
	"helpUrl": ""
},
{
	"type": "SelectInput",
	"message0": "Select %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "priority"}, {"type": "field_input", "name": "labelText", "text": "Priority"}, {"type": "field_multivalue", "name": "optionsText", "text": "Low\nMedium\nHigh"}, {"type": "field_input", "name": "initialValue", "text": "Medium"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Select",
	"helpUrl": ""
},
{
	"type": "CheckboxInput",
	"message0": "Checkbox %1 %2 %3",
	"args0": [{"type": "field_input", "name": "name", "text": "acceptedTerms"}, {"type": "field_input", "name": "labelText", "text": "I agree"}, {"type": "field_checkbox", "name": "defaultChecked", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Checkbox",
	"helpUrl": ""
},
{
	"type": "RadioGroup",
	"message0": "Radio group %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "status"}, {"type": "field_input", "name": "labelText", "text": "Status"}, {"type": "field_multivalue", "name": "optionsText", "text": "Open\nIn progress\nDone"}, {"type": "field_input", "name": "initialValue", "text": "Open"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Radio group",
	"helpUrl": ""
},
{
	"type": "NumberInput",
	"message0": "Number input %1 %2 %3 %4 %5 %6",
	"args0": [{"type": "field_input", "name": "name", "text": "estimate"}, {"type": "field_input", "name": "labelText", "text": "Estimate"}, {"type": "field_number", "name": "initialValue", "value": 1, "precision": 1, "min": 0, "max": 100}, {"type": "field_number", "name": "minValue", "value": 0, "precision": 1}, {"type": "field_number", "name": "maxValue", "value": 100, "precision": 1}, {"type": "field_number", "name": "stepValue", "value": 1, "precision": 1, "min": 1, "max": 20}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Number input",
	"helpUrl": ""
},
{
	"type": "SliderInput",
	"message0": "Slider %1 %2 %3 %4 %5 %6",
	"args0": [{"type": "field_input", "name": "name", "text": "progress"}, {"type": "field_input", "name": "labelText", "text": "Progress"}, {"type": "field_number", "name": "initialValue", "value": 50, "precision": 1, "min": 0, "max": 100}, {"type": "field_number", "name": "minValue", "value": 0, "precision": 1}, {"type": "field_number", "name": "maxValue", "value": 100, "precision": 1}, {"type": "field_number", "name": "stepValue", "value": 5, "precision": 1, "min": 1, "max": 20}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Slider",
	"helpUrl": ""
},
{
	"type": "DateInput",
	"message0": "Date input %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "dueDate"}, {"type": "field_input", "name": "labelText", "text": "Due date"}, {"type": "field_input", "name": "initialValue", "text": "2026-06-14"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Date input",
	"helpUrl": ""
},
{
	"type": "TimeInput",
	"message0": "Time input %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "reminderTime"}, {"type": "field_input", "name": "labelText", "text": "Reminder time"}, {"type": "field_input", "name": "initialValue", "text": "09:00"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Time input",
	"helpUrl": ""
},
{
	"type": "FileUpload",
	"message0": "File upload %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "attachment"}, {"type": "field_input", "name": "labelText", "text": "Attachment"}, {"type": "field_multivalue", "name": "acceptTypes", "text": ".png,.jpg,.pdf"}, {"type": "field_checkbox", "name": "multipleFiles", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "File upload",
	"helpUrl": ""
},
{
	"type": "TableView",
	"message0": "Table %1 %2 %3",
	"args0": [{"type": "field_dropdown", "name": "source", "options": [["(none)", ""]]}, {"type": "field_multivalue", "name": "columnsText", "text": "title\nstatus\nsubtitle"}, {"type": "field_input", "name": "emptyText", "text": "No rows"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Table",
	"helpUrl": ""
},
{
	"type": "Container",
	"message0": "Container %1 %2 %3 %4",
	"args0": [{"type": "field_dropdown", "name": "layout", "options": [["Column", "column"], ["Row", "row"], ["Wrap", "wrap"]]}, {"type": "field_number", "name": "gap", "value": 12, "precision": 1, "min": 0, "max": 64}, {"type": "field_number", "name": "padding", "value": 16, "precision": 1, "min": 0, "max": 96}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Container",
	"helpUrl": ""
},
{
	"type": "Card",
	"message0": "Card %1 %2",
	"args0": [{"type": "field_input", "name": "title", "text": "Details"}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Card",
	"helpUrl": ""
},
{
	"type": "Form",
	"message0": "Form %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "title", "text": "Task form"}, {"type": "field_dropdown", "name": "endpoint", "options": [["(none)", ""]]}, {"type": "field_reference_multiselect", "name": "requiredFields", "text": "", "targetType": "TextInput", "labelField": "name"}, {"type": "input_statement", "name": "children", "check": "Component"}, {"type": "input_statement", "name": "onSubmit", "check": "Action"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Form",
	"helpUrl": ""
},
{
	"type": "TabsView",
	"message0": "Tabs %1",
	"args0": [{"type": "input_statement", "name": "panels", "check": "TabPanel"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Tabs",
	"helpUrl": ""
},
{
	"type": "TabPanel",
	"message0": "Tab panel %1 %2",
	"args0": [{"type": "field_input", "name": "title", "text": "Details"}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "TabPanel", "nextStatement": "TabPanel",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Tab panel",
	"helpUrl": ""
},
{
	"type": "Modal",
	"message0": "Modal %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "title", "text": "Confirm"}, {"type": "field_input", "name": "triggerText", "text": "Open modal"}, {"type": "field_checkbox", "name": "openByDefault", "checked": false}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Modal",
	"helpUrl": ""
},
{
	"type": "Toast",
	"message0": "Toast %1 %2",
	"args0": [{"type": "input_value", "name": "message", "check": "TextExpression"}, {"type": "field_dropdown", "name": "tone", "options": [["Info", "info"], ["Success", "success"], ["Warning", "warning"], ["Error", "error"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Toast",
	"helpUrl": ""
},
{
	"type": "Divider",
	"message0": "Divider %1 %2",
	"args0": [{"type": "field_number", "name": "thickness", "value": 1, "precision": 1, "min": 1, "max": 8}, {"type": "field_colour", "name": "dividerColor", "colour": "#e5e7eb"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Divider",
	"helpUrl": ""
},
{
	"type": "Spacer",
	"message0": "Spacer %1",
	"args0": [{"type": "field_number", "name": "size", "value": 16, "precision": 1, "min": 0, "max": 128}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Spacer",
	"helpUrl": ""
},
{
	"type": "Navigate",
	"message0": "Navigate %1",
	"args0": [{"type": "field_dropdown", "name": "target", "options": [["(none)", ""]]}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Navigate",
	"helpUrl": ""
},
{
	"type": "Alert",
	"message0": "Alert %1",
	"args0": [{"type": "input_value", "name": "message", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Alert",
	"helpUrl": ""
},
{
	"type": "SubmitForm",
	"message0": "Submit form %1 %2",
	"args0": [{"type": "field_dropdown", "name": "endpoint", "options": [["(none)", ""]]}, {"type": "field_dropdown", "name": "successTarget", "options": [["(none)", ""]]}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Submit form",
	"helpUrl": ""
},
{
	"type": "SetInputValue",
	"message0": "Set input value %1 %2",
	"args0": [{"type": "field_dropdown", "name": "input", "options": [["(none)", ""]]}, {"type": "input_value", "name": "newValue", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Set input value",
	"helpUrl": ""
},
{
	"type": "OpenUrl",
	"message0": "Open URL %1",
	"args0": [{"type": "input_value", "name": "url", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Open URL",
	"helpUrl": ""
},
{
	"type": "TextLiteral",
	"message0": "Text %1",
	"args0": [{"type": "field_input", "name": "content", "text": "Saved!"}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Text",
	"helpUrl": ""
},
{
	"type": "InputValue",
	"message0": "Input value %1",
	"args0": [{"type": "field_dropdown", "name": "input", "options": [["(none)", ""]]}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Input value",
	"helpUrl": ""
},
{
	"type": "DataField",
	"message0": "Data field %1",
	"args0": [{"type": "field_input", "name": "fieldName", "text": "title"}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Data field",
	"helpUrl": ""
},
{
	"type": "JoinText",
	"message0": "Join text %1 %2",
	"args0": [{"type": "input_value", "name": "left", "check": "TextExpression"}, {"type": "input_value", "name": "right", "check": "TextExpression"}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Join text",
	"helpUrl": ""
}
];
