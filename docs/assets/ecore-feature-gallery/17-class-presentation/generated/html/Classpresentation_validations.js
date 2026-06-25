// Validations for domain "Classpresentation".
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
