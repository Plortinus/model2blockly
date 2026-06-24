package io.github.plortinus.model2blockly.generator;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Generates a small native-HTML preview runtime for the AppMaker example.
 *
 * This is intentionally conservative: it renders the AppMaker model with
 * standard browser elements and mock data, without introducing a framework or a
 * deployable application runtime.
 */
public final class AppMakerHtmlRuntimeGenerator {

	private AppMakerHtmlRuntimeGenerator() {}

	public static String css() {
		return """
			#previewView{background:#eef2f7;padding:0}
			.app-preview-shell{min-height:100%;background:#eef2f7;color:#172033;font-family:Arial,sans-serif}
			.app-preview-phone{max-width:420px;margin:16px auto;background:#fff;border:1px solid #d8dee9;border-radius:8px;overflow:hidden;box-shadow:0 8px 24px rgba(15,23,42,.14)}
			.app-preview-header{padding:14px 16px;background:#1f6feb;color:#fff}
			.app-preview-title{font-size:1rem;font-weight:bold;margin:0}
			.app-preview-subtitle{font-size:.78rem;opacity:.85;margin-top:3px}
			.app-preview-nav{display:flex;gap:6px;padding:10px;background:#f8fafc;border-bottom:1px solid #e5e7eb;overflow:auto}
			.app-preview-nav button{border:1px solid #cfd8e3;background:#fff;border-radius:4px;padding:6px 9px;cursor:pointer;color:#1f2937;font-size:.78rem}
			.app-preview-nav button.active{background:#1f6feb;border-color:#1f6feb;color:#fff}
			.app-preview-page{padding:14px;display:flex;flex-direction:column;gap:12px;min-height:360px}
			.app-preview-page.row{flex-direction:row;align-items:flex-start}
			.app-preview-page.grid{display:grid;grid-template-columns:1fr 1fr;align-content:start}
			.app-preview-status{margin:0 14px 12px;padding:8px 10px;background:#ecfdf3;border:1px solid #bbf7d0;color:#166534;border-radius:4px;font-size:.8rem}
			.app-preview-component{box-sizing:border-box}
			.app-preview-text{margin:0;color:#111827;line-height:1.4}
			.app-preview-text.title{font-size:1.35rem;font-weight:bold}
			.app-preview-text.subtitle{font-size:1rem;font-weight:bold;color:#475569}
			.app-preview-text.caption{font-size:.75rem;color:#64748b}
			.app-preview-text.center{text-align:center}
			.app-preview-text.end{text-align:right}
			.app-preview-field{display:flex;flex-direction:column;gap:5px}
			.app-preview-field label{font-size:.78rem;font-weight:bold;color:#334155}
			.app-preview-field input,.app-preview-field textarea,.app-preview-field select{border:1px solid #cbd5e1;border-radius:4px;padding:8px;font:inherit;background:#fff}
			.app-preview-fieldset{border:1px solid #cbd5e1;border-radius:6px;padding:10px;margin:0}
			.app-preview-fieldset legend{font-size:.78rem;font-weight:bold;color:#334155;padding:0 4px}
			.app-preview-option{display:flex;align-items:center;gap:7px;margin:6px 0;color:#334155}
			.app-preview-button{border:0;border-radius:4px;padding:9px 12px;cursor:pointer;font-weight:bold}
			.app-preview-button.primary{background:#1f6feb;color:#fff}
			.app-preview-button.secondary{background:#e5e7eb;color:#111827}
			.app-preview-button.danger{background:#dc2626;color:#fff}
			.app-preview-button.ghost{background:transparent;color:#1f6feb;border:1px solid #1f6feb}
			.app-preview-list{border:1px solid #e2e8f0;border-radius:6px;overflow:hidden}
			.app-preview-list-row{padding:9px 10px;border-bottom:1px solid #e2e8f0;background:#fff}
			.app-preview-list-row:last-child{border-bottom:0}
			.app-preview-empty{padding:12px;color:#64748b;text-align:center}
			.app-preview-card{border:1px solid #e2e8f0;border-radius:6px;padding:12px;background:#fff}
			.app-preview-card-title{font-weight:bold;margin-bottom:8px}
			.app-preview-form{border:1px solid #cbd5e1;border-radius:6px;padding:12px;background:#f8fafc;display:flex;flex-direction:column;gap:10px}
			.app-preview-form-title{font-weight:bold;color:#172033}
			.app-preview-table{width:100%;border-collapse:collapse;border:1px solid #e2e8f0;background:#fff;font-size:.82rem}
			.app-preview-table th,.app-preview-table td{border-bottom:1px solid #e2e8f0;padding:7px 8px;text-align:left}
			.app-preview-table th{background:#f8fafc;color:#334155;font-weight:bold}
			.app-preview-tabs{border:1px solid #e2e8f0;border-radius:6px;overflow:hidden;background:#fff}
			.app-preview-tabbar{display:flex;gap:0;border-bottom:1px solid #e2e8f0;background:#f8fafc;overflow:auto}
			.app-preview-tabbar button{border:0;border-right:1px solid #e2e8f0;background:transparent;padding:8px 10px;cursor:pointer;color:#334155}
			.app-preview-tabbar button.active{background:#fff;color:#1f6feb;font-weight:bold}
			.app-preview-tab-panel{padding:10px;display:flex;flex-direction:column;gap:10px}
			.app-preview-dialog{border:0;border-radius:8px;padding:0;max-width:360px;width:calc(100% - 48px);box-shadow:0 14px 38px rgba(15,23,42,.28)}
			.app-preview-dialog::backdrop{background:rgba(15,23,42,.38)}
			.app-preview-dialog-header{padding:12px 14px;background:#1f6feb;color:#fff;font-weight:bold}
			.app-preview-dialog-body{padding:14px;display:flex;flex-direction:column;gap:10px}
			.app-preview-toast{padding:10px 12px;border-radius:6px;border:1px solid #cbd5e1;background:#f8fafc;color:#334155}
			.app-preview-toast.success{border-color:#bbf7d0;background:#ecfdf3;color:#166534}
			.app-preview-toast.warning{border-color:#fde68a;background:#fffbeb;color:#92400e}
			.app-preview-toast.error{border-color:#fecaca;background:#fef2f2;color:#991b1b}
			.app-preview-container{display:flex;gap:12px;padding:12px;border:1px dashed #cbd5e1;border-radius:6px;background:#f8fafc}
			.app-preview-container.column{flex-direction:column}
			.app-preview-container.row{flex-direction:row;align-items:flex-start}
			.app-preview-container.wrap{flex-direction:row;flex-wrap:wrap}
			.app-preview-image{max-width:100%;border-radius:6px;border:1px solid #e2e8f0}
			.app-preview-image-fallback{padding:20px;border:1px dashed #cbd5e1;border-radius:6px;background:#f8fafc;color:#64748b;text-align:center}
			.app-preview-switch{display:flex;align-items:center;gap:8px}
			.app-preview-unknown{padding:8px;border:1px dashed #cbd5e1;color:#64748b;border-radius:4px}
		""";
	}

	public static String tabButton(EditorSpec spec) {
		return isEnabled(spec) ? "<button data-tab=\"preview\" onclick=\"switchTab('preview')\">Preview</button>" : "";
	}

	public static String tabContent(EditorSpec spec) {
		return isEnabled(spec) ? "<div id=\"previewView\" class=\"tab-content\"></div>" : "";
	}

	public static String script(EditorSpec spec) {
		if (!isEnabled(spec)) return "";
		return """
			(function() {
			  var previewState = { activePageId: null, inputs: {}, status: '' };

			  function create(tag, className, text) {
			    var el = document.createElement(tag);
			    if (className) el.className = className;
			    if (text !== undefined && text !== null) el.textContent = String(text);
			    return el;
			  }

			  function append(parent, child) {
			    if (child) parent.appendChild(child);
			    return child;
			  }

			  function scalar(value, fallback) {
			    if (value === null || value === undefined || value === '') return fallback || '';
			    return String(value);
			  }

			  function isTrue(value) {
			    return value === true || value === 'true' || value === 'TRUE';
			  }

			  function optionsFromText(value) {
			    return scalar(value, '')
			      .split(/[\\n,;]+/)
			      .map(function(part) { return part.trim(); })
			      .filter(function(part) { return part.length > 0; });
			  }

			  function walk(value, visit) {
			    if (Array.isArray(value)) {
			      value.forEach(function(item) { walk(item, visit); });
			    } else if (value && typeof value === 'object') {
			      visit(value);
			      Object.keys(value).forEach(function(key) { walk(value[key], visit); });
			    }
			  }

			  function indexById(model) {
			    var byId = {};
			    walk(model, function(node) {
			      if (node && node._blockId) byId[node._blockId] = node;
			    });
			    return byId;
			  }

			  function firstApp(model) {
			    var roots = Array.isArray(model) ? model : [model];
			    for (var i = 0; i < roots.length; i++) {
			      if (roots[i] && roots[i]._type === 'App') return roots[i];
			    }
			    return roots[0] || null;
			  }

			  function validImageUrl(value) {
			    return /^https?:\\/\\//.test(value) || /^data:image\\//.test(value) || /^\\.\\.?\\//.test(value);
			  }

			  function expressionText(expr, item) {
			    if (!expr || typeof expr !== 'object') return '';
			    if (expr._type === 'TextLiteral') return scalar(expr.content, '');
			    if (expr._type === 'DataField') {
			      var key = scalar(expr.fieldName, 'title');
			      return item && Object.prototype.hasOwnProperty.call(item, key) ? scalar(item[key], '') : '';
			    }
			    if (expr._type === 'InputValue') return scalar(previewState.inputs[expr.input], '');
			    if (expr._type === 'JoinText') return expressionText(expr.left, item) + expressionText(expr.right, item);
			    return scalar(expr.content || expr.fieldName || expr._type, '');
			  }

			  function mockRows(dataSource) {
			    var sourceName = scalar(dataSource && dataSource.name, 'items');
			    return [
			      { title: sourceName + ' item 1', subtitle: 'Generated preview row', status: 'open' },
			      { title: sourceName + ' item 2', subtitle: 'Generated preview row', status: 'in progress' },
			      { title: sourceName + ' item 3', subtitle: 'Generated preview row', status: 'done' }
			    ];
			  }

			  function runActions(actions, byId, dataSources) {
			    (actions || []).forEach(function(action) {
			      if (!action || !action._type) return;
			      if (action._type === 'Alert') {
			        alert(expressionText(action.message));
			      } else if (action._type === 'Navigate') {
			        if (action.target) previewState.activePageId = action.target;
			        window.renderAppMakerPreview(window.__lastAppMakerModel || []);
			      } else if (action._type === 'SubmitForm') {
			        var endpoint = byId[action.endpoint];
			        previewState.status = 'Submitted form data to ' + scalar(endpoint && endpoint.name, 'data source') + '.';
			        if (action.successTarget) previewState.activePageId = action.successTarget;
			        window.renderAppMakerPreview(window.__lastAppMakerModel || []);
			      } else if (action._type === 'SetInputValue') {
			        previewState.inputs[action.input] = expressionText(action.newValue);
			        var input = document.querySelector('[data-app-input="' + action.input + '"]');
			        if (input) input.value = previewState.inputs[action.input];
			      } else if (action._type === 'OpenUrl') {
			        var url = expressionText(action.url);
			        if (url) window.open(url, '_blank', 'noopener,noreferrer');
			      }
			    });
			  }

			  function renderComponent(node, byId, dataSources, item) {
			    if (!node || !node._type) return null;
			    if (node._type === 'TextLabel') {
			      var style = scalar(node.textStyle, 'body');
			      var tag = style === 'title' ? 'h1' : style === 'subtitle' ? 'h2' : style === 'caption' ? 'small' : 'p';
			      var text = create(tag, 'app-preview-text ' + style + ' ' + scalar(node.alignment, 'start'), expressionText(node.content, item));
			      return text;
			    }
			    if (node._type === 'Button') {
			      var button = create('button', 'app-preview-button ' + scalar(node.style, 'primary'), scalar(node.labelText, 'Button'));
			      button.type = 'button';
			      button.addEventListener('click', function() { runActions(node.onClick || [], byId, dataSources); });
			      return button;
			    }
			    if (node._type === 'TextInput') {
			      var field = create('div', 'app-preview-field');
			      append(field, create('label', null, scalar(node.labelText, node.name || 'Input')));
			      var input = create('input');
			      input.type = node.inputType === 'password' ? 'password' : node.inputType === 'numeric' ? 'number' : node.inputType === 'email' ? 'email' : 'text';
			      input.placeholder = scalar(node.hint, '');
			      input.required = isTrue(node.mandatory);
			      input.dataset.appInput = node._blockId || '';
			      input.value = scalar(previewState.inputs[node._blockId], '');
			      input.addEventListener('input', function() { previewState.inputs[node._blockId] = input.value; });
			      append(field, input);
			      return field;
			    }
			    if (node._type === 'TextArea') {
			      var areaField = create('div', 'app-preview-field');
			      append(areaField, create('label', null, scalar(node.labelText, node.name || 'Text area')));
			      var area = create('textarea');
			      area.placeholder = scalar(node.hint, '');
			      area.rows = Number(node.rows || 4);
			      area.required = isTrue(node.mandatory);
			      area.dataset.appInput = node._blockId || '';
			      area.value = scalar(previewState.inputs[node._blockId], '');
			      area.addEventListener('input', function() { previewState.inputs[node._blockId] = area.value; });
			      append(areaField, area);
			      return areaField;
			    }
			    if (node._type === 'ImageView') {
			      var url = expressionText(node.url, item);
			      if (validImageUrl(url)) {
			        var img = create('img', 'app-preview-image');
			        img.src = url;
			        img.alt = scalar(node.altText, 'Image');
			        img.style.objectFit = scalar(node.fit, 'cover');
			        return img;
			      }
			      return create('div', 'app-preview-image-fallback', scalar(node.altText, 'Image') + ': ' + scalar(url, 'no URL'));
			    }
			    if (node._type === 'ListView') {
			      var ds = byId[node.source] || dataSources[0];
			      var rows = mockRows(ds);
			      var list = create('div', 'app-preview-list');
			      if (!rows.length) append(list, create('div', 'app-preview-empty', scalar(node.emptyText, 'No items')));
			      rows.forEach(function(row) {
			        append(list, create('div', 'app-preview-list-row', expressionText(node.itemTitle, row) || scalar(row.title, 'Item')));
			      });
			      return list;
			    }
			    if (node._type === 'ToggleSwitch') {
			      var switchLabel = create('label', 'app-preview-switch');
			      var checkbox = create('input');
			      checkbox.type = 'checkbox';
			      checkbox.checked = isTrue(node.defaultOn);
			      checkbox.dataset.appInput = node._blockId || '';
			      checkbox.addEventListener('change', function() { previewState.inputs[node._blockId] = checkbox.checked ? 'true' : 'false'; });
			      append(switchLabel, checkbox);
			      append(switchLabel, document.createTextNode(scalar(node.labelText, node.name || 'Switch')));
			      return switchLabel;
			    }
			    if (node._type === 'SelectInput') {
			      var selectField = create('div', 'app-preview-field');
			      append(selectField, create('label', null, scalar(node.labelText, node.name || 'Select')));
			      var select = create('select');
			      select.required = isTrue(node.mandatory);
			      select.dataset.appInput = node._blockId || '';
			      optionsFromText(node.optionsText).forEach(function(optionText) {
			        var option = create('option', null, optionText);
			        option.value = optionText;
			        option.selected = scalar(previewState.inputs[node._blockId], scalar(node.initialValue, '')) === optionText;
			        append(select, option);
			      });
			      select.addEventListener('change', function() { previewState.inputs[node._blockId] = select.value; });
			      append(selectField, select);
			      return selectField;
			    }
			    if (node._type === 'CheckboxInput') {
			      var checkboxLabel = create('label', 'app-preview-switch');
			      var checkboxInput = create('input');
			      checkboxInput.type = 'checkbox';
			      checkboxInput.checked = scalar(previewState.inputs[node._blockId], isTrue(node.defaultChecked) ? 'true' : 'false') === 'true';
			      checkboxInput.dataset.appInput = node._blockId || '';
			      checkboxInput.addEventListener('change', function() { previewState.inputs[node._blockId] = checkboxInput.checked ? 'true' : 'false'; });
			      append(checkboxLabel, checkboxInput);
			      append(checkboxLabel, document.createTextNode(scalar(node.labelText, node.name || 'Checkbox')));
			      return checkboxLabel;
			    }
			    if (node._type === 'RadioGroup') {
			      var fieldset = create('fieldset', 'app-preview-fieldset');
			      append(fieldset, create('legend', null, scalar(node.labelText, node.name || 'Radio group')));
			      var radioName = 'radio_' + scalar(node._blockId, Math.random().toString(36).slice(2));
			      var selected = scalar(previewState.inputs[node._blockId], scalar(node.initialValue, ''));
			      optionsFromText(node.optionsText).forEach(function(optionText) {
			        var optionLabel = create('label', 'app-preview-option');
			        var radio = create('input');
			        radio.type = 'radio';
			        radio.name = radioName;
			        radio.value = optionText;
			        radio.checked = selected === optionText;
			        radio.addEventListener('change', function() { if (radio.checked) previewState.inputs[node._blockId] = optionText; });
			        append(optionLabel, radio);
			        append(optionLabel, document.createTextNode(optionText));
			        append(fieldset, optionLabel);
			      });
			      return fieldset;
			    }
			    if (node._type === 'NumberInput' || node._type === 'SliderInput') {
			      var numberField = create('div', 'app-preview-field');
			      append(numberField, create('label', null, scalar(node.labelText, node.name || node._type)));
			      var numberInput = create('input');
			      numberInput.type = node._type === 'SliderInput' ? 'range' : 'number';
			      numberInput.min = scalar(node.minValue, '0');
			      numberInput.max = scalar(node.maxValue, '100');
			      numberInput.step = scalar(node.stepValue, '1');
			      numberInput.value = scalar(previewState.inputs[node._blockId], scalar(node.initialValue, '0'));
			      numberInput.dataset.appInput = node._blockId || '';
			      var numberValue = create('small', null, numberInput.value);
			      numberInput.addEventListener('input', function() {
			        previewState.inputs[node._blockId] = numberInput.value;
			        numberValue.textContent = numberInput.value;
			      });
			      append(numberField, numberInput);
			      if (node._type === 'SliderInput') append(numberField, numberValue);
			      return numberField;
			    }
			    if (node._type === 'DateInput' || node._type === 'TimeInput') {
			      var dateField = create('div', 'app-preview-field');
			      append(dateField, create('label', null, scalar(node.labelText, node.name || node._type)));
			      var dateInput = create('input');
			      dateInput.type = node._type === 'DateInput' ? 'date' : 'time';
			      dateInput.value = scalar(previewState.inputs[node._blockId], scalar(node.initialValue, ''));
			      dateInput.required = isTrue(node.mandatory);
			      dateInput.dataset.appInput = node._blockId || '';
			      dateInput.addEventListener('input', function() { previewState.inputs[node._blockId] = dateInput.value; });
			      append(dateField, dateInput);
			      return dateField;
			    }
			    if (node._type === 'FileUpload') {
			      var fileField = create('div', 'app-preview-field');
			      append(fileField, create('label', null, scalar(node.labelText, node.name || 'File')));
			      var fileInput = create('input');
			      fileInput.type = 'file';
			      fileInput.accept = scalar(node.acceptTypes, '');
			      fileInput.multiple = isTrue(node.multipleFiles);
			      append(fileField, fileInput);
			      return fileField;
			    }
			    if (node._type === 'TableView') {
			      var tableSource = byId[node.source] || dataSources[0];
			      var tableRows = mockRows(tableSource);
			      var columns = optionsFromText(node.columnsText);
			      if (!columns.length) columns = ['title', 'status'];
			      var table = create('table', 'app-preview-table');
			      var thead = create('thead');
			      var headerRow = create('tr');
			      columns.forEach(function(column) { append(headerRow, create('th', null, column)); });
			      append(thead, headerRow);
			      append(table, thead);
			      var tbody = create('tbody');
			      tableRows.forEach(function(row) {
			        var tr = create('tr');
			        columns.forEach(function(column) { append(tr, create('td', null, scalar(row[column], ''))); });
			        append(tbody, tr);
			      });
			      append(table, tbody);
			      return table;
			    }
			    if (node._type === 'Container') {
			      var container = create('div', 'app-preview-container ' + scalar(node.layout, 'column'));
			      container.style.gap = Number(node.gap || 12) + 'px';
			      container.style.padding = Number(node.padding || 16) + 'px';
			      (node.children || []).forEach(function(child) { append(container, renderComponent(child, byId, dataSources, item)); });
			      return container;
			    }
			    if (node._type === 'Card') {
			      var card = create('section', 'app-preview-card');
			      append(card, create('div', 'app-preview-card-title', scalar(node.title, 'Card')));
			      (node.children || []).forEach(function(child) { append(card, renderComponent(child, byId, dataSources, item)); });
			      return card;
			    }
			    if (node._type === 'Form') {
			      var form = create('form', 'app-preview-form');
			      append(form, create('div', 'app-preview-form-title', scalar(node.title, 'Form')));
			      (node.children || []).forEach(function(child) { append(form, renderComponent(child, byId, dataSources, item)); });
			      var submit = create('button', 'app-preview-button primary', 'Submit');
			      submit.type = 'submit';
			      append(form, submit);
			      form.addEventListener('submit', function(event) {
			        event.preventDefault();
			        runActions(node.onSubmit || [], byId, dataSources);
			        if (!node.onSubmit || node.onSubmit.length === 0) {
			          var endpoint = byId[node.endpoint];
			          previewState.status = 'Submitted form data to ' + scalar(endpoint && endpoint.name, 'preview data source') + '.';
			          window.renderAppMakerPreview(window.__lastAppMakerModel || []);
			        }
			      });
			      return form;
			    }
			    if (node._type === 'TabsView') {
			      var tabs = create('div', 'app-preview-tabs');
			      var tabbar = create('div', 'app-preview-tabbar');
			      var panelHost = create('div', 'app-preview-tab-panel');
			      var panels = node.panels || [];
			      function showPanel(panel) {
			        panelHost.innerHTML = '';
			        (panel.children || []).forEach(function(child) { append(panelHost, renderComponent(child, byId, dataSources, item)); });
			        Array.prototype.forEach.call(tabbar.querySelectorAll('button'), function(btn) {
			          btn.classList.toggle('active', btn.dataset.panelId === panel._blockId);
			        });
			      }
			      panels.forEach(function(panel, index) {
			        var tabButton = create('button', index === 0 ? 'active' : '', scalar(panel.title, 'Tab'));
			        tabButton.type = 'button';
			        tabButton.dataset.panelId = panel._blockId || String(index);
			        tabButton.addEventListener('click', function() { showPanel(panel); });
			        append(tabbar, tabButton);
			      });
			      append(tabs, tabbar);
			      append(tabs, panelHost);
			      if (panels[0]) showPanel(panels[0]);
			      return tabs;
			    }
			    if (node._type === 'Modal') {
			      var wrap = create('div');
			      var trigger = create('button', 'app-preview-button secondary', scalar(node.triggerText, 'Open modal'));
			      trigger.type = 'button';
			      var dialog = create('dialog', 'app-preview-dialog');
			      append(dialog, create('div', 'app-preview-dialog-header', scalar(node.title, 'Modal')));
			      var body = create('div', 'app-preview-dialog-body');
			      (node.children || []).forEach(function(child) { append(body, renderComponent(child, byId, dataSources, item)); });
			      var close = create('button', 'app-preview-button secondary', 'Close');
			      close.type = 'button';
			      close.addEventListener('click', function() { dialog.close(); });
			      append(body, close);
			      append(dialog, body);
			      trigger.addEventListener('click', function() { if (dialog.showModal) dialog.showModal(); else dialog.setAttribute('open', 'open'); });
			      append(wrap, trigger);
			      append(wrap, dialog);
			      if (isTrue(node.openByDefault)) dialog.setAttribute('open', 'open');
			      return wrap;
			    }
			    if (node._type === 'Toast') {
			      return create('div', 'app-preview-toast ' + scalar(node.tone, 'info'), expressionText(node.message, item));
			    }
			    if (node._type === 'Divider') {
			      var hr = create('hr');
			      hr.style.border = '0';
			      hr.style.borderTop = Number(node.thickness || 1) + 'px solid ' + scalar(node.dividerColor, '#e5e7eb');
			      hr.style.width = '100%';
			      return hr;
			    }
			    if (node._type === 'Spacer') {
			      var spacer = create('div');
			      spacer.style.height = Number(node.size || 16) + 'px';
			      return spacer;
			    }
			    return create('div', 'app-preview-unknown', node._type);
			  }

			  window.renderAppMakerPreview = function(model) {
			    window.__lastAppMakerModel = model;
			    var preview = document.getElementById('previewView');
			    if (!preview) return;
			    preview.innerHTML = '';
			    var app = firstApp(model);
			    if (!app || app._type !== 'App') {
			      append(preview, create('p', 'app-preview-empty', 'Load or build an App model to preview native HTML output.'));
			      return;
			    }
			    var byId = indexById(model);
			    var dataSources = app.dataSources || [];
			    var pages = app.pages || [];
			    var validPage = pages.some(function(page) { return page._blockId === previewState.activePageId; });
			    if (!validPage && pages.length) previewState.activePageId = pages[0]._blockId;
			    var activePage = pages.filter(function(page) { return page._blockId === previewState.activePageId; })[0] || pages[0];

			    var shell = create('div', 'app-preview-shell');
			    var phone = create('div', 'app-preview-phone');
			    append(shell, phone);
			    var header = create('header', 'app-preview-header');
			    append(header, create('div', 'app-preview-title', scalar(app.name, 'App')));
			    append(header, create('div', 'app-preview-subtitle', 'Theme: ' + scalar(app.theme, 'light')));
			    append(phone, header);

			    var nav = create('nav', 'app-preview-nav');
			    pages.forEach(function(page) {
			      var button = create('button', page === activePage ? 'active' : '', scalar(page.title, 'Page'));
			      button.type = 'button';
			      button.addEventListener('click', function() {
			        previewState.activePageId = page._blockId;
			        window.renderAppMakerPreview(window.__lastAppMakerModel || []);
			      });
			      append(nav, button);
			    });
			    append(phone, nav);

			    if (activePage) {
			      var pageEl = create('main', 'app-preview-page ' + scalar(activePage.layout, 'column'));
			      pageEl.style.background = scalar(activePage.backgroundColor, '#ffffff');
			      (activePage.components || []).forEach(function(component) {
			        append(pageEl, renderComponent(component, byId, dataSources));
			      });
			      append(phone, pageEl);
			      if (previewState.status) append(phone, create('div', 'app-preview-status', previewState.status));
			      if (activePage.onEnter && activePage.onEnter.length && !activePage.__previewEntered) {
			        activePage.__previewEntered = true;
			      }
			    } else {
			      append(phone, create('div', 'app-preview-empty', 'No pages defined.'));
			    }
			    append(preview, shell);
			  };
			})();
		""";
	}

	private static boolean isEnabled(EditorSpec spec) {
		if (spec == null) return false;
		String runtimeKind = spec.getRuntimeKind();
		if (runtimeKind != null && "appMaker".equalsIgnoreCase(runtimeKind)) return true;
		String domainName = spec.getDomainName();
		return domainName != null && "AppMaker".equalsIgnoreCase(domainName);
	}
}
