// Block definitions for domain "Appmaker".
// Auto-generated from metamodel.
window.BLOCKLY_REFERENCE_TARGETS = {
"DataSource": ["DataSource"],
"Page": ["Page"],
"TextInput": ["TextInput"]
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
	"type": "App",
	"message0": "App %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "Task Tracker"}, {"type": "field_dropdown", "name": "theme", "options": [["light", "light"], ["dark", "dark"], ["system", "system"]]}, {"type": "input_statement", "name": "dataSources", "check": "DataSource"}, {"type": "input_statement", "name": "pages", "check": "Page"}],
	"colour": 260,
	"tooltip": "Root low-code app model containing data sources and pages.",
	"helpUrl": ""
},
{
	"type": "DataSource",
	"message0": "Data source %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "tasks"}, {"type": "field_input", "name": "url", "text": "https://example.com/api/tasks"}, {"type": "field_dropdown", "name": "method", "options": [["get", "get"], ["post", "post"], ["put", "put"], ["delete", "delete"]]}, {"type": "field_checkbox", "name": "cacheEnabled", "checked": true}],
	"previousStatement": "DataSource", "nextStatement": "DataSource",
	"inputsInline": true,
	"colour": 210,
	"tooltip": "HTTP data source that components and actions can reference.",
	"helpUrl": ""
},
{
	"type": "Page",
	"message0": "Page %1 %2 %3 %4 %5 %6 %7 %8",
	"args0": [{"type": "field_input", "name": "title", "text": "Home"}, {"type": "field_input", "name": "route", "text": "/home"}, {"type": "field_dropdown", "name": "layout", "options": [["column", "column"], ["row", "row"], ["grid", "grid"], ["tabs", "tabs"]]}, {"type": "field_dropdown", "name": "navigationStyle", "options": [["standard", "standard"], ["none", "none"], ["bottomTabs", "bottomTabs"], ["drawer", "drawer"]]}, {"type": "field_colour", "name": "backgroundColor", "colour": "#ffffff"}, {"type": "field_checkbox", "name": "scrollable", "checked": true}, {"type": "input_statement", "name": "components", "check": "Component"}, {"type": "input_statement", "name": "onEnter", "check": "Action"}],
	"previousStatement": "Page", "nextStatement": "Page",
	"colour": 260,
	"tooltip": "A navigable page with design settings, components and optional lifecycle actions.",
	"helpUrl": ""
},
{
	"type": "TextLabel",
	"message0": "Text %1 %2 %3",
	"args0": [{"type": "input_value", "name": "content", "check": "TextExpression"}, {"type": "field_dropdown", "name": "textStyle", "options": [["title", "title"], ["subtitle", "subtitle"], ["body", "body"], ["caption", "caption"]]}, {"type": "field_dropdown", "name": "alignment", "options": [["start", "start"], ["center", "center"], ["end", "end"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Static or computed text component.",
	"helpUrl": ""
},
{
	"type": "Button",
	"message0": "Button %1 %2 %3",
	"args0": [{"type": "field_input", "name": "labelText", "text": "Save"}, {"type": "field_dropdown", "name": "style", "options": [["primary", "primary"], ["secondary", "secondary"], ["danger", "danger"], ["ghost", "ghost"]]}, {"type": "input_statement", "name": "onClick", "check": "Action"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Button component with click actions.",
	"helpUrl": ""
},
{
	"type": "TextInput",
	"message0": "Text input %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "taskTitle"}, {"type": "field_input", "name": "labelText", "text": "Task title"}, {"type": "field_input", "name": "hint", "text": "What needs to be done?"}, {"type": "field_dropdown", "name": "inputType", "options": [["plainText", "plainText"], ["email", "email"], ["password", "password"], ["numeric", "numeric"]]}, {"type": "field_checkbox", "name": "mandatory", "checked": true}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Single-line text input component that actions can reference.",
	"helpUrl": ""
},
{
	"type": "TextArea",
	"message0": "Text area %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "notes"}, {"type": "field_input", "name": "labelText", "text": "Notes"}, {"type": "field_input", "name": "hint", "text": "Add details"}, {"type": "field_number", "name": "rows", "value": 4, "precision": 1, "min": 2, "max": 12}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Multi-line text input component.",
	"helpUrl": ""
},
{
	"type": "ImageView",
	"message0": "Image %1 %2 %3",
	"args0": [{"type": "input_value", "name": "url", "check": "TextExpression"}, {"type": "field_input", "name": "altText", "text": "Preview"}, {"type": "field_dropdown", "name": "fit", "options": [["cover", "cover"], ["contain", "contain"], ["fill", "fill"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Image component with a dynamic URL expression.",
	"helpUrl": ""
},
{
	"type": "ListView",
	"message0": "List view %1 %2 %3",
	"args0": [{"type": "field_dropdown", "name": "source", "options": [["(none)", ""]]}, {"type": "input_value", "name": "itemTitle", "check": "TextExpression"}, {"type": "field_input", "name": "emptyText", "text": "No items yet"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "List component backed by a data source.",
	"helpUrl": ""
},
{
	"type": "ToggleSwitch",
	"message0": "Switch %1 %2 %3",
	"args0": [{"type": "field_input", "name": "name", "text": "notifyMe"}, {"type": "field_input", "name": "labelText", "text": "Notify me"}, {"type": "field_checkbox", "name": "defaultOn", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Boolean switch component.",
	"helpUrl": ""
},
{
	"type": "SelectInput",
	"message0": "Select %1 %2 %3 %4 %5",
	"args0": [{"type": "field_input", "name": "name", "text": "priority"}, {"type": "field_input", "name": "labelText", "text": "Priority"}, {"type": "field_input", "name": "optionsText", "text": "Low\nMedium\nHigh"}, {"type": "field_input", "name": "initialValue", "text": "Medium"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Select/dropdown input component.",
	"helpUrl": ""
},
{
	"type": "CheckboxInput",
	"message0": "Checkbox %1 %2 %3",
	"args0": [{"type": "field_input", "name": "name", "text": "acceptedTerms"}, {"type": "field_input", "name": "labelText", "text": "I agree"}, {"type": "field_checkbox", "name": "defaultChecked", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 160,
	"tooltip": "Checkbox input component.",
	"helpUrl": ""
},
{
	"type": "RadioGroup",
	"message0": "Radio group %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "status"}, {"type": "field_input", "name": "labelText", "text": "Status"}, {"type": "field_input", "name": "optionsText", "text": "Open\nIn progress\nDone"}, {"type": "field_input", "name": "initialValue", "text": "Open"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Radio group input component.",
	"helpUrl": ""
},
{
	"type": "NumberInput",
	"message0": "Number input %1 %2 %3 %4 %5 %6",
	"args0": [{"type": "field_input", "name": "name", "text": "estimate"}, {"type": "field_input", "name": "labelText", "text": "Estimate"}, {"type": "field_number", "name": "initialValue", "value": 1, "precision": 1}, {"type": "field_number", "name": "minValue", "value": 0, "precision": 1}, {"type": "field_number", "name": "maxValue", "value": 100, "precision": 1}, {"type": "field_number", "name": "stepValue", "value": 1, "precision": 1}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Numeric input component.",
	"helpUrl": ""
},
{
	"type": "SliderInput",
	"message0": "Slider %1 %2 %3 %4 %5 %6",
	"args0": [{"type": "field_input", "name": "name", "text": "progress"}, {"type": "field_input", "name": "labelText", "text": "Progress"}, {"type": "field_number", "name": "initialValue", "value": 50, "precision": 1}, {"type": "field_number", "name": "minValue", "value": 0, "precision": 1}, {"type": "field_number", "name": "maxValue", "value": 100, "precision": 1}, {"type": "field_number", "name": "stepValue", "value": 5, "precision": 1}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Range slider input component.",
	"helpUrl": ""
},
{
	"type": "DateInput",
	"message0": "Date input %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "dueDate"}, {"type": "field_input", "name": "labelText", "text": "Due date"}, {"type": "field_input", "name": "initialValue", "text": "2026-06-14"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Date input component.",
	"helpUrl": ""
},
{
	"type": "TimeInput",
	"message0": "Time input %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "reminderTime"}, {"type": "field_input", "name": "labelText", "text": "Reminder time"}, {"type": "field_input", "name": "initialValue", "text": "09:00"}, {"type": "field_checkbox", "name": "mandatory", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Time input component.",
	"helpUrl": ""
},
{
	"type": "FileUpload",
	"message0": "File upload %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "name", "text": "attachment"}, {"type": "field_input", "name": "labelText", "text": "Attachment"}, {"type": "field_input", "name": "acceptTypes", "text": ".png,.jpg,.pdf"}, {"type": "field_checkbox", "name": "multipleFiles", "checked": false}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "File upload input component.",
	"helpUrl": ""
},
{
	"type": "TableView",
	"message0": "Table %1 %2 %3",
	"args0": [{"type": "field_dropdown", "name": "source", "options": [["(none)", ""]]}, {"type": "field_input", "name": "columnsText", "text": "title\nstatus\nsubtitle"}, {"type": "field_input", "name": "emptyText", "text": "No rows"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Table component backed by a data source.",
	"helpUrl": ""
},
{
	"type": "Container",
	"message0": "Container %1 %2 %3 %4",
	"args0": [{"type": "field_dropdown", "name": "layout", "options": [["column", "column"], ["row", "row"], ["wrap", "wrap"]]}, {"type": "field_number", "name": "gap", "value": 12, "precision": 1, "min": 0, "max": 64}, {"type": "field_number", "name": "padding", "value": 16, "precision": 1, "min": 0, "max": 96}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 120,
	"tooltip": "Layout container for grouping child components.",
	"helpUrl": ""
},
{
	"type": "Card",
	"message0": "Card %1 %2",
	"args0": [{"type": "field_input", "name": "title", "text": "Details"}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 120,
	"tooltip": "Card container with a title and child components.",
	"helpUrl": ""
},
{
	"type": "Form",
	"message0": "Form %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "title", "text": "Task form"}, {"type": "field_dropdown", "name": "endpoint", "options": [["(none)", ""]]}, {"type": "input_statement", "name": "children", "check": "Component"}, {"type": "input_statement", "name": "onSubmit", "check": "Action"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 120,
	"tooltip": "Form container with fields and submit actions.",
	"helpUrl": ""
},
{
	"type": "TabsView",
	"message0": "Tabs %1",
	"args0": [{"type": "input_statement", "name": "panels", "check": "TabPanel"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 120,
	"tooltip": "Tabbed container component.",
	"helpUrl": ""
},
{
	"type": "TabPanel",
	"message0": "Tab panel %1 %2",
	"args0": [{"type": "field_input", "name": "title", "text": "Details"}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "TabPanel", "nextStatement": "TabPanel",
	"colour": 120,
	"tooltip": "Single tab panel inside a Tabs component.",
	"helpUrl": ""
},
{
	"type": "Modal",
	"message0": "Modal %1 %2 %3 %4",
	"args0": [{"type": "field_input", "name": "title", "text": "Confirm"}, {"type": "field_input", "name": "triggerText", "text": "Open modal"}, {"type": "field_checkbox", "name": "openByDefault", "checked": false}, {"type": "input_statement", "name": "children", "check": "Component"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 120,
	"tooltip": "Modal dialog component.",
	"helpUrl": ""
},
{
	"type": "Toast",
	"message0": "Toast %1 %2",
	"args0": [{"type": "input_value", "name": "message", "check": "TextExpression"}, {"type": "field_dropdown", "name": "tone", "options": [["info", "info"], ["success", "success"], ["warning", "warning"], ["error", "error"]]}],
	"previousStatement": "Component", "nextStatement": "Component",
	"colour": 160,
	"tooltip": "Toast message component.",
	"helpUrl": ""
},
{
	"type": "Divider",
	"message0": "Divider %1 %2",
	"args0": [{"type": "field_number", "name": "thickness", "value": 1, "precision": 1, "min": 1, "max": 8}, {"type": "field_colour", "name": "dividerColor", "colour": "#e5e7eb"}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Horizontal divider component.",
	"helpUrl": ""
},
{
	"type": "Spacer",
	"message0": "Spacer %1",
	"args0": [{"type": "field_number", "name": "size", "value": 16, "precision": 1, "min": 0, "max": 128}],
	"previousStatement": "Component", "nextStatement": "Component",
	"inputsInline": true,
	"colour": 120,
	"tooltip": "Vertical or horizontal spacing component.",
	"helpUrl": ""
},
{
	"type": "Navigate",
	"message0": "Navigate %1",
	"args0": [{"type": "field_dropdown", "name": "target", "options": [["(none)", ""]]}],
	"previousStatement": "Action", "nextStatement": "Action",
	"inputsInline": true,
	"colour": 30,
	"tooltip": "Navigate to another page.",
	"helpUrl": ""
},
{
	"type": "Alert",
	"message0": "Alert %1",
	"args0": [{"type": "input_value", "name": "message", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"colour": 30,
	"tooltip": "Show a message to the user.",
	"helpUrl": ""
},
{
	"type": "SubmitForm",
	"message0": "Submit form %1 %2",
	"args0": [{"type": "field_dropdown", "name": "endpoint", "options": [["(none)", ""]]}, {"type": "field_dropdown", "name": "successTarget", "options": [["(none)", ""]]}],
	"previousStatement": "Action", "nextStatement": "Action",
	"colour": 30,
	"tooltip": "Submit form data to a data source and optionally navigate.",
	"helpUrl": ""
},
{
	"type": "SetInputValue",
	"message0": "Set input value %1 %2",
	"args0": [{"type": "field_dropdown", "name": "input", "options": [["(none)", ""]]}, {"type": "input_value", "name": "newValue", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"colour": 30,
	"tooltip": "Set a text input value from an expression.",
	"helpUrl": ""
},
{
	"type": "OpenUrl",
	"message0": "Open URL %1",
	"args0": [{"type": "input_value", "name": "url", "check": "TextExpression"}],
	"previousStatement": "Action", "nextStatement": "Action",
	"colour": 30,
	"tooltip": "Open an external URL.",
	"helpUrl": ""
},
{
	"type": "TextLiteral",
	"message0": "Text %1",
	"args0": [{"type": "field_input", "name": "content", "text": "Saved!"}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Literal text value.",
	"helpUrl": ""
},
{
	"type": "InputValue",
	"message0": "Input value %1",
	"args0": [{"type": "field_dropdown", "name": "input", "options": [["(none)", ""]]}],
	"output": "TextExpression",
	"colour": 230,
	"tooltip": "Read the current value from a text input.",
	"helpUrl": ""
},
{
	"type": "DataField",
	"message0": "Data field %1",
	"args0": [{"type": "field_input", "name": "fieldName", "text": "title"}],
	"output": "TextExpression",
	"inputsInline": true,
	"colour": 230,
	"tooltip": "Read a field from the current list item.",
	"helpUrl": ""
},
{
	"type": "JoinText",
	"message0": "Join text %1 %2",
	"args0": [{"type": "input_value", "name": "left", "check": "TextExpression"}, {"type": "input_value", "name": "right", "check": "TextExpression"}],
	"output": "TextExpression",
	"colour": 230,
	"tooltip": "Concatenate two text expressions.",
	"helpUrl": ""
}
];
