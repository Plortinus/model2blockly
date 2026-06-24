// Validations for domain "AppMaker".
// Auto-generated from metamodel.
function computeValidationWarnings(workspace) {
	var warnings = [];
	if (!workspace) return warnings;
	function queueWarn(block, text) {
		if (!block) return;
		warnings.push({ block: block, blockId: block.id, message: text });
	}
	function fieldMultiplicityCount(value) {
		if (typeof parseBlocklyListField === 'function') return parseBlocklyListField(value).length;
		if (value === null || value === undefined || value === '') return 0;
		return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; }).length;
	}
	function fieldValues(value) {
		if (typeof parseBlocklyListField === 'function') return parseBlocklyListField(value);
		if (value === null || value === undefined || value === '') return [];
		return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
	}
	function duplicateValues(values) {
		var seen = {};
		var duplicates = [];
		values.forEach(function(value) {
			if (seen[value] && duplicates.indexOf(value) === -1) duplicates.push(value);
			seen[value] = true;
		});
		return duplicates;
	}
	function validationValue(block, name) {
		var value = block && block.getFieldValue ? block.getFieldValue(name) : '';
		if (value === null || value === undefined) return '';
		return String(value).trim();
	}
	function validationNumber(block, name) {
		var number = Number(validationValue(block, name));
		return isFinite(number) ? number : 0;
	}
	function validationSize(block, name) {
		return fieldMultiplicityCount(block && block.getFieldValue ? block.getFieldValue(name) : '');
	}
	function validationHas(block, name) {
		return validationSize(block, name) > 0 && validationValue(block, name) !== '';
	}
	function validationIncludes(block, name, item) {
		return fieldValues(block && block.getFieldValue ? block.getFieldValue(name) : '').indexOf(String(item)) !== -1;
	}
	function evaluateValidationExpression(block, expression) {
		var value = function(name) { return validationValue(block, name); };
		var number = function(name) { return validationNumber(block, name); };
		var size = function(name) { return validationSize(block, name); };
		var has = function(name) { return validationHas(block, name); };
		var includes = function(name, item) { return validationIncludes(block, name, item); };
		try {
			return { ok: !!(new Function('value', 'number', 'size', 'has', 'includes', 'block', 'return (' + expression + ');'))(value, number, size, has, includes, block) };
		} catch(e) {
			return { ok: false, error: e && e.message ? e.message : String(e) };
		}
	}
	function validationRuntimeApi() {
		if (typeof Model2BlocklyValidationRuntime !== 'undefined') return Model2BlocklyValidationRuntime;
		if (typeof window !== 'undefined' && window.Model2BlocklyValidationRuntime) return window.Model2BlocklyValidationRuntime;
		return null;
	}
	function activeValidationBlockModel() {
		if (typeof window !== 'undefined' && window.__activeValidationBlockModel) return window.__activeValidationBlockModel;
		if (typeof window !== 'undefined' && window.BLOCKLY_VALIDATION_BLOCK_MODEL) return window.BLOCKLY_VALIDATION_BLOCK_MODEL;
		return null;
	}
	function setActiveValidationBlockModel(model) {
		if (!model || typeof model !== 'object') return false;
		window.__activeValidationBlockModel = model;
		window.BLOCKLY_VALIDATION_BLOCK_MODEL = model;
		if (typeof workspace !== 'undefined' && typeof applyValidationWarnings === 'function') {
			applyValidationWarnings(workspace);
		}
		return true;
	}
	function syncValidationBlocksFromFrame() {
		var iframe = document.querySelector('#validationBlocksView iframe');
		if (!iframe || !iframe.contentWindow || typeof iframe.contentWindow.workspaceToValidationBlockModel !== 'function') return false;
		try {
			return setActiveValidationBlockModel(iframe.contentWindow.workspaceToValidationBlockModel());
		} catch (e) {
			return false;
		}
	}
	window.setActiveValidationBlockModel = setActiveValidationBlockModel;
	window.syncValidationBlocksFromFrame = syncValidationBlocksFromFrame;
	window.addEventListener('message', function(event) {
		var iframe = document.querySelector('#validationBlocksView iframe');
		if (iframe && event.source !== iframe.contentWindow) return;
		var data = event.data || {};
		if (data.type !== 'model2blockly.validationModelChanged') return;
		setActiveValidationBlockModel(data.model);
	});
	function computeRuntimeValidationWarnings(workspace, queueWarn) {
		var runtime = validationRuntimeApi();
		if (!runtime ||
			typeof runtime.generateValidationRuntimeRules !== 'function' ||
			typeof runtime.evaluateRuntimeExpression !== 'function') return false;
		var rules = runtime.generateValidationRuntimeRules(activeValidationBlockModel());
		if (!rules || !rules.length) return false;
		var runnableRules = rules.filter(function(rule) {
			return rule && rule.runtimeExpression && String(rule.runtimeExpression).trim();
		});
		if (runnableRules.length !== rules.length) return false;
		workspace.getAllBlocks(false).forEach(function(block) {
			runnableRules.forEach(function(rule) {
				if (rule.targetType && block.type !== rule.targetType) return;
				var result = runtime.evaluateRuntimeExpression(block, workspace, rule.runtimeExpression);
				if (!result.ok) {
					var message = rule.message || (rule.name ? rule.name + ' is violated.' : 'Validation rule is violated.');
					if (result.error) message += ' Expression error: ' + result.error;
					queueWarn(block, message);
				}
			});
		});
		return true;
	}
	if (computeRuntimeValidationWarnings(workspace, queueWarn)) return warnings;
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Navigate') {
			var prev = block.getPreviousBlock();
			if (!prev || prev.type !== 'Alert')
				queueWarn(block, '"Navigate" must be preceded by "Alert".');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'App') {
			var count = 0;
			var child = block.getInputTargetBlock('dataSources');
			while (child) { count++; child = child.getNextBlock(); }
			if (count < 1)
				queueWarn(block, '"App.dataSources" needs at least 1 element(s). Has: ' + count);
			if (count > 20)
				queueWarn(block, '"App.dataSources" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'App') {
			var count = 0;
			var child = block.getInputTargetBlock('pages');
			while (child) { count++; child = child.getNextBlock(); }
			if (count < 1)
				queueWarn(block, '"App.pages" needs at least 1 element(s). Has: ' + count);
			if (count > 20)
				queueWarn(block, '"App.pages" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var count = 0;
			var child = block.getInputTargetBlock('components');
			while (child) { count++; child = child.getNextBlock(); }
			if (count < 1)
				queueWarn(block, '"Page.components" needs at least 1 element(s). Has: ' + count);
			if (count > 40)
				queueWarn(block, '"Page.components" allows at most 40 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var count = 0;
			var child = block.getInputTargetBlock('onEnter');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 10)
				queueWarn(block, '"Page.onEnter" allows at most 10 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Button') {
			var count = 0;
			var child = block.getInputTargetBlock('onClick');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 10)
				queueWarn(block, '"Button.onClick" allows at most 10 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Container') {
			var count = 0;
			var child = block.getInputTargetBlock('children');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 20)
				queueWarn(block, '"Container.children" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Card') {
			var count = 0;
			var child = block.getInputTargetBlock('children');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 20)
				queueWarn(block, '"Card.children" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var count = 0;
			var child = block.getInputTargetBlock('children');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 20)
				queueWarn(block, '"Form.children" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var count = 0;
			var child = block.getInputTargetBlock('onSubmit');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 10)
				queueWarn(block, '"Form.onSubmit" allows at most 10 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TabsView') {
			var count = 0;
			var child = block.getInputTargetBlock('panels');
			while (child) { count++; child = child.getNextBlock(); }
			if (count < 2)
				queueWarn(block, '"TabsView.panels" needs at least 2 element(s). Has: ' + count);
			if (count > 8)
				queueWarn(block, '"TabsView.panels" allows at most 8 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TabPanel') {
			var count = 0;
			var child = block.getInputTargetBlock('children');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 20)
				queueWarn(block, '"TabPanel.children" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Modal') {
			var count = 0;
			var child = block.getInputTargetBlock('children');
			while (child) { count++; child = child.getNextBlock(); }
			if (count > 20)
				queueWarn(block, '"Modal.children" allows at most 20 element(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataSource') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"DataSource.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataSource') {
			var val = block.getFieldValue('url');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"DataSource.url" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'App') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"App.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var val = block.getFieldValue('title');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Page.title" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var val = block.getFieldValue('route');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Page.route" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Button') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Button.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextInput') {
			var val = block.getFieldValue('hint');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextInput.hint" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextArea') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextArea.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextArea') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextArea.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'ListView') {
			var val = block.getFieldValue('source');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"ListView.source" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'ToggleSwitch') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"ToggleSwitch.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'ToggleSwitch') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"ToggleSwitch.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SelectInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SelectInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var val = block.getFieldValue('optionsText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SelectInput.optionsText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'CheckboxInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"CheckboxInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'CheckboxInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"CheckboxInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"RadioGroup.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"RadioGroup.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var val = block.getFieldValue('optionsText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"RadioGroup.optionsText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'NumberInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"NumberInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'NumberInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"NumberInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SliderInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SliderInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SliderInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SliderInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DateInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"DateInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DateInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"DateInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TimeInput') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TimeInput.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TimeInput') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TimeInput.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'FileUpload') {
			var val = block.getFieldValue('name');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"FileUpload.name" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'FileUpload') {
			var val = block.getFieldValue('labelText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"FileUpload.labelText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'FileUpload') {
			var val = block.getFieldValue('acceptTypes');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"FileUpload.acceptTypes" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TableView') {
			var val = block.getFieldValue('columnsText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TableView.columnsText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TableView') {
			var val = block.getFieldValue('source');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TableView.source" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Card') {
			var val = block.getFieldValue('title');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Card.title" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var val = block.getFieldValue('title');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Form.title" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var val = block.getFieldValue('endpoint');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Form.endpoint" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TabPanel') {
			var val = block.getFieldValue('title');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TabPanel.title" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Modal') {
			var val = block.getFieldValue('title');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Modal.title" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Modal') {
			var val = block.getFieldValue('triggerText');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Modal.triggerText" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Navigate') {
			var val = block.getFieldValue('target');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"Navigate.target" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SubmitForm') {
			var val = block.getFieldValue('endpoint');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SubmitForm.endpoint" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SetInputValue') {
			var val = block.getFieldValue('input');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"SetInputValue.input" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextLiteral') {
			var val = block.getFieldValue('content');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"TextLiteral.content" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'InputValue') {
			var val = block.getFieldValue('input');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"InputValue.input" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataField') {
			var val = block.getFieldValue('fieldName');
			if (val === null || val === undefined || val === '')
				queueWarn(block, '"DataField.fieldName" is required (cannot be empty).');
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataSource') {
			var count = fieldMultiplicityCount(block.getFieldValue('headers'));
			if (count > 10)
				queueWarn(block, '"DataSource.headers" allows at most 10 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var count = fieldMultiplicityCount(block.getFieldValue('tags'));
			if (count > 8)
				queueWarn(block, '"Page.tags" allows at most 8 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var count = fieldMultiplicityCount(block.getFieldValue('optionsText'));
			if (count > 20)
				queueWarn(block, '"SelectInput.optionsText" allows at most 20 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var count = fieldMultiplicityCount(block.getFieldValue('optionsText'));
			if (count < 2)
				queueWarn(block, '"RadioGroup.optionsText" needs at least 2 value(s). Has: ' + count);
			if (count > 10)
				queueWarn(block, '"RadioGroup.optionsText" allows at most 10 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'FileUpload') {
			var count = fieldMultiplicityCount(block.getFieldValue('acceptTypes'));
			if (count > 10)
				queueWarn(block, '"FileUpload.acceptTypes" allows at most 10 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TableView') {
			var count = fieldMultiplicityCount(block.getFieldValue('columnsText'));
			if (count > 8)
				queueWarn(block, '"TableView.columnsText" allows at most 8 value(s). Has: ' + count);
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var count = fieldMultiplicityCount(block.getFieldValue('requiredFields'));
			if (count > 20)
				queueWarn(block, '"Form.requiredFields" allows at most 20 value(s). Has: ' + count);
		}
	});
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'DataSource') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"DataSource.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"DataSource.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'Page') {
				var val = block.getFieldValue('route');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"Page.route" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"Page.route" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'TextInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"TextInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"TextInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'TextArea') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"TextArea.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"TextArea.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'ToggleSwitch') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"ToggleSwitch.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"ToggleSwitch.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'SelectInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"SelectInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"SelectInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var duplicates = duplicateValues(fieldValues(block.getFieldValue('optionsText')));
			if (duplicates.length)
				queueWarn(block, '"SelectInput.optionsText" values must be unique. Duplicate: ' + duplicates.join(', '));
		}
	});
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'CheckboxInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"CheckboxInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"CheckboxInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'RadioGroup') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"RadioGroup.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"RadioGroup.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var duplicates = duplicateValues(fieldValues(block.getFieldValue('optionsText')));
			if (duplicates.length)
				queueWarn(block, '"RadioGroup.optionsText" values must be unique. Duplicate: ' + duplicates.join(', '));
		}
	});
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'NumberInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"NumberInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"NumberInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'SliderInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"SliderInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"SliderInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'DateInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"DateInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"DateInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'TimeInput') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"TimeInput.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"TimeInput.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	(function() {
		var seen = {};
		workspace.getAllBlocks(false).forEach(function(block) {
			if (block.type === 'FileUpload') {
				var val = block.getFieldValue('name');
				val = val === null || val === undefined ? '' : String(val).trim();
				if (!val) return;
				if (seen[val]) {
					queueWarn(block, '"FileUpload.name" must be unique. Duplicate ID: ' + val);
					queueWarn(seen[val], '"FileUpload.name" must be unique. Duplicate ID: ' + val);
				} else {
					seen[val] = block;
				}
			}
		});
	})();
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'FileUpload') {
			var duplicates = duplicateValues(fieldValues(block.getFieldValue('acceptTypes')));
			if (duplicates.length)
				queueWarn(block, '"FileUpload.acceptTypes" values must be unique. Duplicate: ' + duplicates.join(', '));
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TableView') {
			var duplicates = duplicateValues(fieldValues(block.getFieldValue('columnsText')));
			if (duplicates.length)
				queueWarn(block, '"TableView.columnsText" values must be unique. Duplicate: ' + duplicates.join(', '));
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var duplicates = duplicateValues(fieldValues(block.getFieldValue('requiredFields')));
			if (duplicates.length)
				queueWarn(block, '"Form.requiredFields" values must be unique. Duplicate: ' + duplicates.join(', '));
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataSource') {
			var result = evaluateValidationExpression(block, "value(\'url\').startsWith(\'http://\') || value(\'url\').startsWith(\'https://\')");
			if (!result.ok) {
				var message = "Data source URL must start with http:// or https://.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DataSource') {
			var result = evaluateValidationExpression(block, "number(\'retryCount\') >= 0 && number(\'retryCount\') <= 10");
			if (!result.ok) {
				var message = "Retry count must be between 0 and 10.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var result = evaluateValidationExpression(block, "value(\'route\') !== \'\' && value(\'route\').startsWith(\'/\')");
			if (!result.ok) {
				var message = "Page route must start with /.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Page') {
			var result = evaluateValidationExpression(block, "value(\'route\').indexOf(\' \') === -1");
			if (!result.ok) {
				var message = "Page route must not contain spaces.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TextArea') {
			var result = evaluateValidationExpression(block, "number(\'rows\') >= 2 && number(\'rows\') <= 12");
			if (!result.ok) {
				var message = "Text area rows must be between 2 and 12.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SelectInput') {
			var result = evaluateValidationExpression(block, "!has(\'initialValue\') || includes(\'optionsText\', value(\'initialValue\'))");
			if (!result.ok) {
				var message = "Select initial value must be one of the configured options.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'RadioGroup') {
			var result = evaluateValidationExpression(block, "!has(\'initialValue\') || includes(\'optionsText\', value(\'initialValue\'))");
			if (!result.ok) {
				var message = "Radio initial value must be one of the configured options.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'NumberInput') {
			var result = evaluateValidationExpression(block, "number(\'minValue\') <= number(\'maxValue\')");
			if (!result.ok) {
				var message = "Minimum value must be less than or equal to maximum value.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'NumberInput') {
			var result = evaluateValidationExpression(block, "number(\'initialValue\') >= number(\'minValue\') && number(\'initialValue\') <= number(\'maxValue\')");
			if (!result.ok) {
				var message = "Initial number value must be inside the min/max range.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'NumberInput') {
			var result = evaluateValidationExpression(block, "number(\'stepValue\') > 0");
			if (!result.ok) {
				var message = "Number input step must be greater than 0.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SliderInput') {
			var result = evaluateValidationExpression(block, "number(\'minValue\') <= number(\'maxValue\')");
			if (!result.ok) {
				var message = "Slider minimum must be less than or equal to maximum.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SliderInput') {
			var result = evaluateValidationExpression(block, "number(\'initialValue\') >= number(\'minValue\') && number(\'initialValue\') <= number(\'maxValue\')");
			if (!result.ok) {
				var message = "Slider initial value must be inside the min/max range.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'SliderInput') {
			var result = evaluateValidationExpression(block, "number(\'stepValue\') > 0");
			if (!result.ok) {
				var message = "Slider step must be greater than 0.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'DateInput') {
			var result = evaluateValidationExpression(block, "!has(\'initialValue\') || (value(\'initialValue\').length === 10 && value(\'initialValue\').charAt(4) === \'-\' && value(\'initialValue\').charAt(7) === \'-\')");
			if (!result.ok) {
				var message = "Date input initial value should use YYYY-MM-DD.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TimeInput') {
			var result = evaluateValidationExpression(block, "!has(\'initialValue\') || (value(\'initialValue\').length === 5 && value(\'initialValue\').charAt(2) === \':\')");
			if (!result.ok) {
				var message = "Time input initial value should use HH:MM.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'TableView') {
			var result = evaluateValidationExpression(block, "size(\'columnsText\') >= 1 && size(\'columnsText\') <= 8");
			if (!result.ok) {
				var message = "Table must define between 1 and 8 columns.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	workspace.getAllBlocks(false).forEach(function(block) {
		if (block.type === 'Form') {
			var result = evaluateValidationExpression(block, "inputCount(\'children\') >= 1");
			if (!result.ok) {
				var message = "Form should contain at least one field or component.";
				if (result.error) message += ' Expression error: ' + result.error;
				queueWarn(block, message);
			}
		}
	});
	return warnings;
}

function applyValidationWarnings(workspace) {
	if (!workspace) return [];
	workspace.getAllBlocks(false).forEach(function(b) { b.setWarningText(null); });
	var warnings = computeValidationWarnings(workspace);
	var pending = {};
	warnings.forEach(function(w) {
		if (!w.block) return;
		if (!pending[w.block.id]) pending[w.block.id] = { b: w.block, msgs: [] };
		pending[w.block.id].msgs.push(w.message);
	});
	Object.keys(pending).forEach(function(k) {
		var p = pending[k];
		p.b.setWarningText(p.msgs.join('\n'));
	});
	renderValidationIssues(warnings);
	return warnings;
}

function renderValidationIssues(warnings) {
	var view = document.getElementById('issuesView');
	var badge = document.getElementById('issuesBadge');
	if (badge) badge.textContent = warnings && warnings.length ? '(' + warnings.length + ')' : '';
	if (!view) return;
	view.innerHTML = '';
	if (!warnings || warnings.length === 0) {
		var empty = document.createElement('div');
		empty.className = 'validation-empty';
		empty.textContent = 'No validation issues';
		view.appendChild(empty);
		return;
	}
	var summary = document.createElement('div');
	summary.className = 'validation-summary';
	summary.textContent = warnings.length + ' validation issue' + (warnings.length === 1 ? '' : 's');
	view.appendChild(summary);
	var list = document.createElement('div');
	list.className = 'validation-list';
	warnings.forEach(function(w) {
		var item = document.createElement('button');
		item.type = 'button';
		item.className = 'validation-item';
		item.addEventListener('click', function() { focusValidationBlock(w.blockId); });
		var type = document.createElement('div');
		type.className = 'validation-item-type';
		type.textContent = w.block ? w.block.type : 'Model';
		var msg = document.createElement('div');
		msg.className = 'validation-item-message';
		msg.textContent = w.message;
		item.appendChild(type);
		item.appendChild(msg);
		list.appendChild(item);
	});
	view.appendChild(list);
}

function focusValidationBlock(blockId) {
	if (!blockId || !workspace) return;
	var block = workspace.getBlockById(blockId);
	if (!block) return;
	if (typeof switchTab === 'function') switchTab('issues');
	if (workspace.centerOnBlock) workspace.centerOnBlock(blockId);
	if (block.select) block.select();
	workspace.highlightBlock(blockId);
	window.setTimeout(function() { workspace.highlightBlock(null); }, 1200);
}

function confirmExportIfInvalid(workspace) {
	var warnings = applyValidationWarnings(workspace);
	if (!warnings.length) return true;
	var preview = warnings.slice(0, 5).map(function(w) { return '- ' + w.message; }).join('\n');
	if (warnings.length > 5) preview += '\n- ...and ' + (warnings.length - 5) + ' more.';
	return confirm('This workspace has validation warnings:\n\n' + preview + '\n\nExport anyway?');
}

function initValidations(workspace) {
	applyValidationWarnings(workspace);
	workspace.addChangeListener(function(event) {
		if (event.type !== Blockly.Events.BLOCK_MOVE &&
			event.type !== Blockly.Events.BLOCK_CREATE &&
			event.type !== Blockly.Events.BLOCK_DELETE &&
			event.type !== Blockly.Events.BLOCK_CHANGE) return;
		applyValidationWarnings(workspace);
	});
}
