/*
 * Code generator: EditorSpec → Blockly (JSON + JS + HTML).
 *
 * This generator is independent of the input source (DSL or Ecore).
 * It takes an EditorSpec intermediate model and produces editor files:
 *   _blocks.js, _toolbox.js, _generators.js, _validations.js,
 *   _editor.html, _standalone.html, validation_workspace.html,
 *   validation_blocks.json, validation_runtime.js, sample_model.json
 */
package io.github.plortinus.model2blockly.generator

import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpec
import io.github.plortinus.model2blockly.intermediate.BlocklySpecModelMapper
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec
import static extension io.github.plortinus.model2blockly.generator.BlocklySpecModelQueries.*
import java.util.List
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map

class BlocklyCodeGenerator {

	def Map<String, String> generate(BlocklyEditorSpec spec) {
		return generate(BlocklySpecModelMapper.toEmfSpec(spec))
	}

	def Map<String, String> generate(EditorSpec spec) {
		val files = new LinkedHashMap<String, String>()
		val base = spec.domainName ?: "domain"
		files.put(base + "_blocks.js", generateBlocksJs(spec))
		files.put(base + "_toolbox.js", generateToolboxJs(spec))
		files.put(base + "_generators.js", generateGeneratorsJs(spec))
		files.put(base + "_validations.js", generateValidationsJs(spec))
		files.put(base + "_editor.html", generateEditorHtml(spec))
		files.put(base + "_standalone.html", generateStandaloneHtml(spec))
		files.put("validation_workspace.html", ValidationWorkspaceHtmlGenerator.generate(spec))
		files.put("validation_blocks.json", ValidationBlockModelGenerator.generate(spec))
		files.put("validation_runtime.js", ValidationRuntimeGenerator.generateClassicJs(spec))
		files.put("sample_model.json", SampleModelGenerator.generate(spec))
		return files
	}

	// ═══════════════════════════════════════════════════════════════
	// 1. BLOCK DEFINITIONS
	// ═══════════════════════════════════════════════════════════════

	def String generateBlocksJs(EditorSpec spec) {
		'''
			// Block definitions for domain "«spec.domainName»".
			// Auto-generated from metamodel.
			«generateReferenceFieldSupportScript(spec)»
			window.BLOCKLY_BLOCKS = «generateBlocksArray(spec)»;
		'''
	}

	def String generateBlocksArray(EditorSpec spec) {
		val blocks = spec.concreteBlockTypes.map[blockTypeToBlockJson(it)]
		"[\n" + blocks.join(",\n") + "\n]"
	}

	def String blockTypeToBlockJson(BlockTypeSpec bt) {
		val argsList = new ArrayList<String>()
		val msgParts = new ArrayList<String>()
		msgParts.add(bt.label ?: bt.typeName)

		// Build lookup maps for each input type
		val fieldMap = newLinkedHashMap()
		for (f : bt.fields) fieldMap.put(f.name, f)
		val refMap = newLinkedHashMap()
		for (r : bt.referenceFields) refMap.put(r.name, r)
		val viMap = newLinkedHashMap()
		for (v : bt.valueInputs) viMap.put(v.name, v)
		val siMap = newLinkedHashMap()
		for (s : bt.statementInputs) siMap.put(s.name, s)

		var argIdx = 1
		// Use orderedInputNames if available (Ecore path), otherwise fall back to old order
		val ordered = if (!bt.orderedInputNames.empty) bt.orderedInputNames
		              else {
		                val fallback = new ArrayList<String>()
		                for (f : bt.fields) fallback.add(f.name)
		                for (r : bt.referenceFields) fallback.add(r.name)
		                for (v : bt.valueInputs) fallback.add(v.name)
		                for (s : bt.statementInputs) fallback.add(s.name)
		                fallback
		              }
		for (inputName : ordered) {
			if (fieldMap.containsKey(inputName)) {
				msgParts.add("%" + argIdx)
				argsList.add(fieldToArg(fieldMap.get(inputName)))
				argIdx++
			} else if (refMap.containsKey(inputName)) {
				msgParts.add("%" + argIdx)
				argsList.add(referenceToArg(refMap.get(inputName)))
				argIdx++
			} else if (viMap.containsKey(inputName)) {
				msgParts.add("%" + argIdx)
				argsList.add(valueInputToArg(viMap.get(inputName)))
				argIdx++
			} else if (siMap.containsKey(inputName)) {
				msgParts.add("%" + argIdx)
				argsList.add(statementInputToArg(siMap.get(inputName)))
				argIdx++
			}
		}

		val generatedMessage0 = msgParts.join(" ")
		val message0 = bt.message0 ?: generatedMessage0
		val args0 = argsList.join(", ")

		val connectionJson = switch bt.connectionType {
			case NONE: ""
			case TYPED: '''"previousStatement": "«bt.connectionTypeName»", "nextStatement": "«bt.connectionTypeName»",'''
			case FREE: '"previousStatement": null, "nextStatement": null,'
			case OUTPUT: '"output": null,'
			case OUTPUT_TYPED: '''"output": "«bt.outputType»",'''
		}

		val inlineJson = if (bt.inputsInline !== null) '''"inputsInline": «bt.inputsInline»,''' else ""
		val tooltipText = bt.tooltip ?: bt.label ?: bt.typeName
		val helpUrlText = bt.helpUrl ?: ""

		'''
		{
			"type": "«bt.typeName»",
			"message0": «toJsonString(message0)»,
			"args0": [«args0»],
			«connectionJson»
			«inlineJson»
			"colour": «bt.colour»,
			"tooltip": «toJsonString(tooltipText)»,
			"helpUrl": «toJsonString(helpUrlText)»
		}'''
	}

	def String fieldToArg(FieldSpec field) {
		if (field.many) {
			return '''{"type": "field_multivalue", "name": "«field.name»", "text": «toJsonString(field.defaultValue ?: "")»}'''
		}
		switch field.fieldType {
			case TEXT:
				'''{"type": "field_input", "name": "«field.name»", "text": «toJsonString(field.defaultValue ?: "")»}'''
			case INTEGER: {
				val minPart = if (field.min !== null) ''', "min": «field.min»''' else ""
				val maxPart = if (field.max !== null) ''', "max": «field.max»''' else ""
				'''{"type": "field_number", "name": "«field.name»", "value": «parseNumberOrDefault(field.defaultValue, 0)», "precision": 1«minPart»«maxPart»}'''
			}
			case FLOAT: {
				val minPart = if (field.min !== null) ''', "min": «field.min»''' else ""
				val maxPart = if (field.max !== null) ''', "max": «field.max»''' else ""
				'''{"type": "field_number", "name": "«field.name»", "value": «parseNumberOrDefault(field.defaultValue, 0)», "precision": 0.1«minPart»«maxPart»}'''
			}
			case BOOLEAN:
				'''{"type": "field_checkbox", "name": "«field.name»", "checked": «if (field.defaultValue == "true") "true" else "false"»}'''
			case DROPDOWN: {
				val opts = field.options.map['''[«toJsonString(it.label)», "«it.value»"]'''].join(", ")
				'''{"type": "field_dropdown", "name": "«field.name»", "options": [«opts»]}'''
			}
			case COLOUR:
				'''{"type": "field_colour", "name": "«field.name»", "colour": «toJsonString(field.defaultValue ?: "#ff0000")»}'''
			case ANGLE:
				'''{"type": "field_angle", "name": "«field.name»", "angle": «parseNumberOrDefault(field.defaultValue, 90)»}'''
			case IMAGE: {
				val url = field.imageUrl ?: field.defaultValue ?: ""
				val w = if (field.imageWidth > 0) field.imageWidth else 40
				val h = if (field.imageHeight > 0) field.imageHeight else 40
				'''{"type": "field_image", "src": «toJsonString(url)», "width": «w», "height": «h», "alt": «toJsonString(field.imageAlt ?: field.name)»}'''
			}
			case LABEL:
				'''{"type": "field_label", "name": "«field.name»", "text": «toJsonString(field.defaultValue ?: field.name)»}'''
		}
	}

	def String referenceToArg(ReferenceFieldSpec ref) {
		if (ref.many) {
			val labelField = if (ref.referenceLabelField !== null) ''', "labelField": «toJsonString(ref.referenceLabelField)»''' else ""
			return '''{"type": "field_reference_multiselect", "name": "«ref.name»", "text": "", "targetType": «toJsonString(ref.targetTypeName ?: "")»«labelField»}'''
		}
		'''{"type": "field_dropdown", "name": "«ref.name»", "options": [["(none)", ""]]}'''
	}

	def String valueInputToArg(ValueInputSpec vi) {
		val check = if (vi.checkType !== null) '''"«vi.checkType»"''' else "null"
		'''{"type": "input_value", "name": "«vi.name»", "check": «check»}'''
	}

	def String statementInputToArg(StatementInputSpec si) {
		val check = if (si.checkType !== null) '''"«si.checkType»"''' else "null"
		'''{"type": "input_statement", "name": "«si.name»", "check": «check»}'''
	}

	def String generateReferenceFieldSupportScript(EditorSpec spec) {
		val targetMap = referenceTargetMap(spec)
		'''
			window.BLOCKLY_REFERENCE_TARGETS = {
			«FOR entry : targetMap.entrySet SEPARATOR ","»
			  «toJsonString(entry.key)»: [«entry.value.map[toJsonString(it)].join(", ")»]
			«ENDFOR»
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
		'''
	}

	def Map<String, List<String>> referenceTargetMap(EditorSpec spec) {
		val result = new LinkedHashMap<String, List<String>>()
		if (spec === null) return result
		val allRefs = new ArrayList<ReferenceFieldSpec>()
		for (bt : spec.concreteBlockTypes) allRefs.addAll(bt.referenceFields)
		for (ref : allRefs) {
			if (ref.targetTypeName !== null && !result.containsKey(ref.targetTypeName)) {
				val matching = new ArrayList<String>()
				for (bt : spec.concreteBlockTypes) {
					if (bt.typeName == ref.targetTypeName
						|| bt.connectionTypeName == ref.targetTypeName
						|| bt.outputType == ref.targetTypeName) {
						matching.add(bt.typeName)
					}
				}
				if (matching.empty) matching.add(ref.targetTypeName)
				result.put(ref.targetTypeName, matching)
			}
		}
		result
	}

	// ═══════════════════════════════════════════════════════════════
	// 2. TOOLBOX
	// ═══════════════════════════════════════════════════════════════

	def String generateToolboxJs(EditorSpec spec) {
		'''
			// Toolbox for domain "«spec.domainName»".
			// Auto-generated from metamodel.
			window.BLOCKLY_TOOLBOX = «generateToolboxObject(spec)»;
		'''
	}

	def String generateToolboxObject(EditorSpec spec) {
		val toolboxType = spec.effectiveToolboxType
		if ("flyout" == toolboxType) {
			return generateFlyoutToolbox(spec)
		}
		return generateCategoryToolbox(spec)
	}

	def String generateFlyoutToolbox(EditorSpec spec) {
		val concrete = spec.concreteBlockTypes
		'''
		{
			"kind": "flyoutToolbox",
			"contents": [
				«concrete.map[blockToToolboxEntry(it, spec)].join(",\n\t\t\t\t")»
			]
		}'''
	}

	def String generateCategoryToolbox(EditorSpec spec) {
		val concrete = spec.concreteBlockTypes

		val categorized = spec.categories.map [ cat |
			val catBlocks = concrete.filter[c | c.categoryName !== null && c.categoryName == cat.name].toList
			categoryToToolbox(cat, catBlocks, spec)
		]

		val uncategorized = concrete.filter[c | c.categoryName === null].toList
		val uncatJson = if (!uncategorized.isEmpty)
			'''«IF !spec.categories.isEmpty»,«ENDIF»
			{
				"kind": "category",
				"name": "Other",
				"colour": "0",
				"contents": [
					«uncategorized.map[blockToToolboxEntry(it, spec)].join(",\n\t\t\t\t\t")»
				]
			}'''
		else ""

		val hasDomainContent = !categorized.isEmpty || !uncategorized.isEmpty
		val builtinSep = if (hasDomainContent) ''',
				{"kind": "sep", "gap": "32"},''' else ""

		'''
		{
			"kind": "categoryToolbox",
			"contents": [
				«categorized.join(",\n\t\t\t")»«uncatJson»«builtinSep»
				«generateBuiltinToolboxCategories()»
			]
		}'''
	}

	def String categoryToToolbox(CategorySpec cat, List<BlockTypeSpec> blocks, EditorSpec spec) {
		val colour = String.valueOf(cat.colour)
		val label = cat.label ?: cat.name
		val concrete = spec.concreteBlockTypes

		val contentParts = new ArrayList<String>()

		// Add blocks that belong directly to this category
		for (b : blocks) {
			contentParts.add(blockToToolboxEntry(b, spec))
		}

		// Recursively add subcategories
		for (child : cat.children) {
			val childBlocks = concrete.filter[c | c.categoryName !== null && c.categoryName == child.name].toList
			contentParts.add(categoryToToolbox(child, childBlocks, spec))
		}

		'''
		{
			"kind": "category",
			"name": «toJsonString(label)»,
			"colour": "«colour»",
			"contents": [
				«contentParts.join(",\n\t\t\t\t")»
			]
		}'''
	}

	def String blockToToolboxEntry(BlockTypeSpec bt, EditorSpec spec) {
		if (bt.valueInputs.isEmpty || bt.valueInputs.filter[it.shadowBlockType !== null].isEmpty) {
			return '''{"kind": "block", "type": "«bt.typeName»"}'''
		}
		val shadowInputs = bt.valueInputs.filter[it.shadowBlockType !== null].toList
		if (shadowInputs.isEmpty) {
			return '''{"kind": "block", "type": "«bt.typeName»"}'''
		}
		val inputsJson = shadowInputs.map['''
					"«it.name»": {
						"shadow": { "type": "«it.shadowBlockType»" }
					}'''].join(",")
		'''{"kind": "block", "type": "«bt.typeName»", "inputs": {«inputsJson»
				}}'''
	}

	// ───────────────────────────────────────────────────────────────
	// Blockly built-in toolbox categories (Logic, Loops, Math, Variables, Procedures)
	// ───────────────────────────────────────────────────────────────

	def String generateBuiltinToolboxCategories() {
		'''
		{
			"kind": "category",
			"name": "Logic",
			"colour": "210",
			"contents": [
				{"kind": "block", "type": "controls_if"},
				{"kind": "block", "type": "controls_if", "extraState": {"hasElse": true}},
				{"kind": "block", "type": "logic_compare"},
				{"kind": "block", "type": "logic_operation"},
				{"kind": "block", "type": "logic_negate"},
				{"kind": "block", "type": "logic_boolean"},
				{"kind": "block", "type": "logic_null"},
				{"kind": "block", "type": "logic_ternary"}
			]
		},
		{
			"kind": "category",
			"name": "Loops",
			"colour": "120",
			"contents": [
				{"kind": "block", "type": "controls_repeat_ext", "inputs": {"TIMES": {"shadow": {"type": "math_number", "fields": {"NUM": 10}}}}},
				{"kind": "block", "type": "controls_whileUntil"},
				{"kind": "block", "type": "controls_for", "inputs": {
					"FROM": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"TO":   {"shadow": {"type": "math_number", "fields": {"NUM": 10}}},
					"BY":   {"shadow": {"type": "math_number", "fields": {"NUM": 1}}}
				}},
				{"kind": "block", "type": "controls_forEach"},
				{"kind": "block", "type": "controls_flow_statements"}
			]
		},
		{
			"kind": "category",
			"name": "Math",
			"colour": "230",
			"contents": [
				{"kind": "block", "type": "math_number", "fields": {"NUM": 0}},
				{"kind": "block", "type": "math_arithmetic", "inputs": {
					"A": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"B": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}}
				}},
				{"kind": "block", "type": "math_single", "inputs": {
					"NUM": {"shadow": {"type": "math_number", "fields": {"NUM": 9}}}
				}},
				{"kind": "block", "type": "math_round", "inputs": {
					"NUM": {"shadow": {"type": "math_number", "fields": {"NUM": 3.1}}}
				}},
				{"kind": "block", "type": "math_modulo", "inputs": {
					"DIVIDEND": {"shadow": {"type": "math_number", "fields": {"NUM": 64}}},
					"DIVISOR":  {"shadow": {"type": "math_number", "fields": {"NUM": 10}}}
				}},
				{"kind": "block", "type": "math_constrain", "inputs": {
					"VALUE": {"shadow": {"type": "math_number", "fields": {"NUM": 50}}},
					"LOW":   {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"HIGH":  {"shadow": {"type": "math_number", "fields": {"NUM": 100}}}
				}},
				{"kind": "block", "type": "math_random_int", "inputs": {
					"FROM": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"TO":   {"shadow": {"type": "math_number", "fields": {"NUM": 100}}}
				}},
				{"kind": "block", "type": "math_random_float"}
			]
		},
		{
			"kind": "category",
			"name": "Variables",
			"colour": "330",
			"custom": "VARIABLE"
		},
		{
			"kind": "category",
			"name": "Functions",
			"colour": "290",
			"custom": "PROCEDURE"
		}'''
	}

	// ═══════════════════════════════════════════════════════════════
	// 3. CODE GENERATORS
	// ═══════════════════════════════════════════════════════════════

	def String generateGeneratorsJs(EditorSpec spec) {
		'''
			// Code generators for domain "«spec.domainName»".
			// Auto-generated from metamodel.
			«generateGeneratorsBody(spec)»
		'''
	}

	def String generateGeneratorsBody(EditorSpec spec) {
		'''
			function parseBlocklyListField(value) {
			  if (Array.isArray(value)) return value;
			  if (value === null || value === undefined) return [];
			  return String(value).split(/[,\n]/).map(function(part) { return part.trim(); }).filter(function(part) { return part.length > 0; });
			}
			window.parseBlocklyListField = parseBlocklyListField;

			«FOR bt : spec.concreteBlockTypes»
			«blockTypeToGeneratorJs(bt)»
			«ENDFOR»
			«generateBuiltinBlockGenerators()»
			«generateDomainCodegenBody(spec)»
		'''
	}

	def String generateDomainCodegenBody(EditorSpec spec) {
		'''
		/* ── Domain code generation (template based) ── */
		window.BLOCKLY_DOMAIN_CODEGEN = {
		  language: '«escapeJson(spec.codeLanguage ?: "text")»',
		  fileExtension: '«escapeJson(spec.codeFileExtension ?: "txt")»',
		  blocks: {
		«FOR bt : spec.concreteBlockTypes SEPARATOR ","»
		    '«escapeJson(bt.typeName)»': {
		      label: '«escapeJson(bt.label ?: bt.typeName)»',
		      template: «IF bt.codeTemplate !== null»'«escapeJson(bt.codeTemplate)»'«ELSE»null«ENDIF»,
		      fields: [«bt.fields.map["'" + escapeJson(name) + "'"].join(", ")»],
		      fieldTypes: {«bt.fields.map["'" + escapeJson(name) + "': '" + (if (fieldType !== null) fieldType.toString else "TEXT") + "'"].join(", ")»},
		      references: [«bt.referenceFields.map["'" + escapeJson(name) + "'"].join(", ")»],
		      values: [«bt.valueInputs.map["'" + escapeJson(name) + "'"].join(", ")»],
		      statements: [«bt.statementInputs.map["'" + escapeJson(name) + "'"].join(", ")»]
		    }
		«ENDFOR»
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
		'''
	}

	/**
	 * Override Blockly's default JavaScript generators for built-in blocks
	 * so they produce JSON AST output compatible with the domain generators.
	 */
	def String generateBuiltinBlockGenerators() {
		'''
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
		'''
	}

	def String blockTypeToGeneratorJs(BlockTypeSpec bt) {
		val fieldReads = bt.fields.map[
			if (it.many)
				'''  var «it.name» = parseBlocklyListField(block.getFieldValue('«it.name»'));'''
			else
				'''  var «it.name» = block.getFieldValue('«it.name»');'''
		].join("\n")

		val refReads = bt.referenceFields.map[
			if (it.many)
				'''  var «it.name» = parseBlocklyListField(block.getFieldValue('«it.name»'));'''
			else
				'''  var «it.name» = block.getFieldValue('«it.name»');'''
		].join("\n")

		val valueReads = bt.valueInputs.map[
			'''  var «it.name»_code = javascript.javascriptGenerator.valueToCode(block, '«it.name»', 0) || 'null';'''
		].join("\n")

		val stmtReads = bt.statementInputs.map[
			'''  var «it.name»_code = javascript.javascriptGenerator.statementToCode(block, '«it.name»');
  var «it.name»_stmt = («it.name»_code || '').trim().replace(/,\\s*$/, '');'''
		].join("\n")

		val jsonParts = new ArrayList<String>()
		jsonParts.add('''  '"_type": "«bt.typeName»", "_blockId": ' + JSON.stringify(block.id)''')
		if (bt.idFieldName !== null) {
			jsonParts.add(''' ', "_modelId": ' + JSON.stringify(«bt.idFieldName» || block.id)''')
		}
		for (f : bt.fields) {
			jsonParts.add(''' ', "«f.name»": ' + JSON.stringify(«f.name»)''')
		}
		for (r : bt.referenceFields) {
			jsonParts.add(''' ', "«r.name»": ' + JSON.stringify(«r.name»)''')
		}
		for (v : bt.valueInputs) {
			jsonParts.add(''' ', "«v.name»": ' + «v.name»_code''')
		}
		for (s : bt.statementInputs) {
			val sn = s.name
			jsonParts.add("  ', \"" + sn + "\": ' + (" + sn + "_stmt ? '[' + " + sn + "_stmt + ']' : '[]')")
		}
		val jsonConcat = jsonParts.join(" + ")

		val isOutputBlock = bt.connectionType == ConnectionType.OUTPUT
			|| bt.connectionType == ConnectionType.OUTPUT_TYPED

		if (isOutputBlock) {
			'''
				javascript.javascriptGenerator.forBlock['«bt.typeName»'] = function(block) {
				«fieldReads»
				«refReads»
				«valueReads»
				«stmtReads»
				  var code = '{' + «jsonConcat» + ' }';
				  return [code, 0];
				};
			'''
		} else {
			'''
				javascript.javascriptGenerator.forBlock['«bt.typeName»'] = function(block) {
				«fieldReads»
				«refReads»
				«valueReads»
				«stmtReads»
				  return '{' + «jsonConcat» + ' },\n';
				};
			'''
		}
	}

	// ═══════════════════════════════════════════════════════════════
	// 4. VALIDATIONS
	// ═══════════════════════════════════════════════════════════════

	def String generateValidationsJs(EditorSpec spec) {
		'''
			// Validations for domain "«spec.domainName»".
			// Auto-generated from metamodel.
			«generateValidationsBody(spec)»
		'''
	}

	def String generateValidationsBody(EditorSpec spec) {
		val mustFollows = spec.validations.filter[it.type == ValidationType.MUST_FOLLOW].toList
		val cardinalities = spec.validations.filter[it.type == ValidationType.CARDINALITY].toList
		val requireds = spec.validations.filter[it.type == ValidationType.REQUIRED].toList
		val fieldCardinalities = spec.validations.filter[it.type == ValidationType.FIELD_CARDINALITY].toList
		val uniques = spec.validations.filter[it.type == ValidationType.UNIQUE].toList
		val expressions = spec.validations.filter[it.type == ValidationType.EXPRESSION].toList

		'''
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
				«IF mustFollows.isEmpty && cardinalities.isEmpty && requireds.isEmpty && fieldCardinalities.isEmpty && uniques.isEmpty && expressions.isEmpty»
				return warnings;
				«ELSE»
				«FOR v : mustFollows»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.targetType»') {
						var prev = block.getPreviousBlock();
						if (!prev || prev.type !== '«v.predecessorType»')
							queueWarn(block, '"«v.targetType»" must be preceded by "«v.predecessorType»".');
					}
				});
				«ENDFOR»
				«FOR v : cardinalities»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.ownerType»') {
						var count = 0;
						var child = block.getInputTargetBlock('«v.containmentName»');
						while (child) { count++; child = child.getNextBlock(); }
						«IF v.lowerBound !== 0»
						if (count < «v.lowerBound»)
							queueWarn(block, '"«v.ownerType».«v.containmentName»" needs at least «v.lowerBound» element(s). Has: ' + count);
						«ENDIF»
						«IF v.upperBound !== 0»
						if (count > «v.upperBound»)
							queueWarn(block, '"«v.ownerType».«v.containmentName»" allows at most «v.upperBound» element(s). Has: ' + count);
						«ENDIF»
					}
				});
				«ENDFOR»
				«FOR v : requireds»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.ownerType»') {
						var val = block.getFieldValue('«v.fieldName»');
						if (val === null || val === undefined || val === '')
							queueWarn(block, '"«v.ownerType».«v.fieldName»" is required (cannot be empty).');
					}
				});
				«ENDFOR»
				«FOR v : fieldCardinalities»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.ownerType»') {
						var count = fieldMultiplicityCount(block.getFieldValue('«v.fieldName»'));
						«IF v.lowerBound > 1»
						if (count < «v.lowerBound»)
							queueWarn(block, '"«v.ownerType».«v.fieldName»" needs at least «v.lowerBound» value(s). Has: ' + count);
						«ENDIF»
						«IF v.upperBound !== 0»
						if (count > «v.upperBound»)
							queueWarn(block, '"«v.ownerType».«v.fieldName»" allows at most «v.upperBound» value(s). Has: ' + count);
						«ENDIF»
					}
				});
				«ENDFOR»
				«FOR v : uniques»
				«IF v.fieldKind == "id"»
				(function() {
					var seen = {};
					workspace.getAllBlocks(false).forEach(function(block) {
						if (block.type === '«v.ownerType»') {
							var val = block.getFieldValue('«v.fieldName»');
							val = val === null || val === undefined ? '' : String(val).trim();
							if (!val) return;
							if (seen[val]) {
								queueWarn(block, '"«v.ownerType».«v.fieldName»" must be unique. Duplicate ID: ' + val);
								queueWarn(seen[val], '"«v.ownerType».«v.fieldName»" must be unique. Duplicate ID: ' + val);
							} else {
								seen[val] = block;
							}
						}
					});
				})();
				«ELSE»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.ownerType»') {
						var duplicates = duplicateValues(fieldValues(block.getFieldValue('«v.fieldName»')));
						if (duplicates.length)
							queueWarn(block, '"«v.ownerType».«v.fieldName»" values must be unique. Duplicate: ' + duplicates.join(', '));
					}
				});
				«ENDIF»
				«ENDFOR»
				«FOR v : expressions»
				workspace.getAllBlocks(false).forEach(function(block) {
					if (block.type === '«v.ownerType»') {
						var result = evaluateValidationExpression(block, «toJsonString(v.expression)»);
						if (!result.ok) {
							var message = «toJsonString(v.message ?: "Semantic constraint is violated.")»;
							if (result.error) message += ' Expression error: ' + result.error;
							queueWarn(block, message);
						}
					}
				});
				«ENDFOR»
				return warnings;
				«ENDIF»
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
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// 5. REFERENCE UPDATE SCRIPT
	// ═══════════════════════════════════════════════════════════════

	def String generateReferenceUpdateScript(EditorSpec spec) {
		val allRefs = new ArrayList<ReferenceFieldSpec>()
		val refOwners = new LinkedHashMap<String, List<ReferenceFieldSpec>>()
		for (bt : spec.concreteBlockTypes) {
			if (!bt.referenceFields.isEmpty) {
				refOwners.put(bt.typeName, bt.referenceFields)
				allRefs.addAll(bt.referenceFields)
			}
		}
		if (allRefs.isEmpty) return ""

		val concreteSubtypes = new LinkedHashMap<String, List<String>>()
		for (ref : allRefs) {
			if (!concreteSubtypes.containsKey(ref.targetTypeName)) {
				val matching = new ArrayList<String>()
				for (bt : spec.concreteBlockTypes) {
					if (bt.typeName == ref.targetTypeName
						|| bt.connectionTypeName == ref.targetTypeName
						|| bt.outputType == ref.targetTypeName) {
						matching.add(bt.typeName)
					}
				}
				if (matching.isEmpty) matching.add(ref.targetTypeName)
				concreteSubtypes.put(ref.targetTypeName, matching)
			}
		}

		val oppositeRules = new ArrayList<String>()
		for (entry : refOwners.entrySet) {
			for (ref : entry.value) {
				if (ref.oppositeName !== null) {
					val targetTypes = concreteSubtypes.get(ref.targetTypeName)
					val targetTypeJson = if (targetTypes !== null && !targetTypes.empty)
						targetTypes.map[toJsonString(it)].join(", ")
					else
						toJsonString(ref.targetTypeName ?: "")
					oppositeRules.add('''{ ownerType: «toJsonString(entry.key)», name: «toJsonString(ref.name)», targetTypes: [«targetTypeJson»], oppositeName: «toJsonString(ref.oppositeName)», many: «ref.many» }''')
				}
			}
		}

		'''
			/* ── Reference dropdowns (dynamic) ── */
			window.BLOCKLY_OPPOSITE_REFERENCES = [
			«oppositeRules.join(",\n")»
			];

			function referenceFieldValues(block, name) {
				if (!block || !block.getField || !block.getField(name)) return [];
				var value = block.getFieldValue(name);
				if (value === null || value === undefined || value === '') return [];
				if (typeof parseBlocklyListField === 'function') return parseBlocklyListField(value);
				return [String(value)];
			}

			function setReferenceFieldValues(block, name, ids) {
				if (!block || !block.getField || !block.getField(name)) return;
				ids = (ids || []).filter(function(id, index, all) {
					return id && all.indexOf(id) === index;
				});
				var field = block.getField(name);
				var isDropdown = !!field.menuGenerator_;
				var nextIds = isDropdown ? (ids.length ? [ids[0]] : []) : ids;
				var currentIds = referenceFieldValues(block, name);
				if (currentIds.join('|') === nextIds.join('|')) return;
				try {
					block.setFieldValue(isDropdown ? (nextIds[0] || '') : nextIds.join(', '), name);
				} catch(e) {
					try { block.setFieldValue('', name); } catch(ignored) {}
				}
			}

			function synchronizeOppositeReferences(workspace, event) {
				var rules = window.BLOCKLY_OPPOSITE_REFERENCES || [];
				if (!workspace || rules.length === 0 || window.__syncingOppositeReferences) return;
				var changedBlock = event && event.blockId ? workspace.getBlockById(event.blockId) : null;
				var changedField = event && event.name ? event.name : null;
				var activeRules = rules;
				if (changedBlock && changedField) {
					activeRules = rules.filter(function(rule) {
						return rule.ownerType === changedBlock.type && rule.name === changedField;
					});
					if (activeRules.length === 0) return;
				}
				window.__syncingOppositeReferences = true;
				var eventsEnabled = Blockly.Events && Blockly.Events.isEnabled ? Blockly.Events.isEnabled() : true;
				if (Blockly.Events && Blockly.Events.disable) Blockly.Events.disable();
				try {
					var blocks = workspace.getAllBlocks(false);
					var byId = {};
					blocks.forEach(function(block) { byId[block.id] = block; });
					activeRules.forEach(function(rule) {
						var desiredByTarget = {};
						blocks.forEach(function(source) {
							if (source.type !== rule.ownerType) return;
							referenceFieldValues(source, rule.name).forEach(function(targetId) {
								var target = byId[targetId];
								if (!target || rule.targetTypes.indexOf(target.type) === -1) return;
								if (!desiredByTarget[target.id]) desiredByTarget[target.id] = [];
								if (desiredByTarget[target.id].indexOf(source.id) === -1) desiredByTarget[target.id].push(source.id);
							});
						});
						blocks.forEach(function(target) {
							if (rule.targetTypes.indexOf(target.type) === -1) return;
							setReferenceFieldValues(target, rule.oppositeName, desiredByTarget[target.id] || []);
						});
					});
				} finally {
					if (eventsEnabled && Blockly.Events && Blockly.Events.enable) Blockly.Events.enable();
					window.__syncingOppositeReferences = false;
				}
			}
			window.synchronizeOppositeReferences = synchronizeOppositeReferences;

			function updateReferenceDropdowns(workspace) {
				var instancesByType = {};
				workspace.getAllBlocks(false).forEach(function(b) {
					if (!instancesByType[b.type]) instancesByType[b.type] = [];
					instancesByType[b.type].push(b);
				});
				function referenceOption(block, labelField) {
					var label = labelField ? block.getFieldValue(labelField) : null;
					label = label || block.getFieldValue('name') || block.type + '_' + block.id.substring(0,6);
					return [label, block.id];
				}
				«FOR entry : refOwners.entrySet»
				«FOR ref : entry.value»
				workspace.getAllBlocks(false).forEach(function(b) {
					if (b.type === '«entry.key»') {
						var field = b.getField('«ref.name»');
							if (field && field.menuGenerator_) {
								var opts = [['(none)', '']];
								var labelField = «IF ref.referenceLabelField !== null»'«escapeJson(ref.referenceLabelField)»'«ELSE»null«ENDIF»;
								«val targetTypes = concreteSubtypes.get(ref.targetTypeName)»
								«IF targetTypes !== null»
								['«targetTypes.join("','")»'].forEach(function(t) {
									(instancesByType[t] || []).forEach(function(targetBlock) { opts.push(referenceOption(targetBlock, labelField)); });
								});
								«ELSE»
							var targets = instancesByType['«ref.targetTypeName»'] || [];
							for (var i = 0; i < targets.length; i++) opts.push(referenceOption(targets[i], labelField));
							«ENDIF»
							var cur = field.getValue();
							field.menuGenerator_ = opts;
							var valid = opts.some(function(o) { return o[1] === cur; });
							if (!valid) field.setValue('');
						} else if (field && field.refreshDisplay) {
							field.refreshDisplay();
						}
					}
				});
				«ENDFOR»
				«ENDFOR»
			}
			workspace.addChangeListener(function(event) {
				if (event.type === Blockly.Events.BLOCK_MOVE ||
					event.type === Blockly.Events.BLOCK_CREATE ||
					event.type === Blockly.Events.BLOCK_DELETE ||
					event.type === Blockly.Events.BLOCK_CHANGE) {
					updateReferenceDropdowns(workspace);
					synchronizeOppositeReferences(workspace, event);
					if (typeof applyValidationWarnings === 'function') applyValidationWarnings(workspace);
					if (typeof updateOutput === 'function') updateOutput();
				}
			});
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// 6. EDITOR HTML (modular, <script src>)
	// ═══════════════════════════════════════════════════════════════

	def String generateEditorHtml(EditorSpec spec) {
		val base = spec.domainName ?: "domain"
		val title = spec.domainName ?: "Blockly Editor"
		'''
			<!DOCTYPE html>
			<html lang="en">
			<head>
				<meta charset="UTF-8">
				<title>«esc(title)» — Blockly Editor</title>
				<script src="https://unpkg.com/blockly/blockly.min.js"></script>
				<script src="https://unpkg.com/blockly/javascript_compressed.js"></script>
				«editorCss»
			</head>
			<body>
				«editorHeaderHtml(title)»
				«editorMainHtml(spec)»
				<script src="«base»_blocks.js"></script>
				<script src="«base»_toolbox.js"></script>
				<script src="«base»_generators.js"></script>
				<script src="validation_runtime.js"></script>
				<script src="«base»_validations.js"></script>
				«editorBootstrapScript(spec)»
			</body>
			</html>
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// 7. STANDALONE HTML (self-contained)
	// ═══════════════════════════════════════════════════════════════

	def String generateStandaloneHtml(EditorSpec spec) {
		val title = spec.domainName ?: "Blockly Editor"
		'''
			<!DOCTYPE html>
			<html lang="en">
			<head>
				<meta charset="UTF-8">
				<title>«esc(title)» — Blockly Editor</title>
				<script src="https://unpkg.com/blockly/blockly.min.js"></script>
				<script src="https://unpkg.com/blockly/javascript_compressed.js"></script>
				«editorCss»
			</head>
			<body>
				«editorHeaderHtml(title)»
				«editorMainHtml(spec)»
				<script>
				/* ═══ 1. BLOCKS ═══ */
				«generateReferenceFieldSupportScript(spec)»
				var BLOCKLY_BLOCKS = «generateBlocksArray(spec)»;
				Blockly.defineBlocksWithJsonArray(BLOCKLY_BLOCKS);

				/* ═══ 2. TOOLBOX ═══ */
				var BLOCKLY_TOOLBOX = «generateToolboxObject(spec)»;

				/* ═══ 3. WORKSPACE ═══ */
				var workspace = Blockly.inject('blocklyDiv', {
					toolbox: BLOCKLY_TOOLBOX,
					«workspaceOptions(spec)»
				});
				Blockly.svgResize(workspace);
				window.addEventListener('resize', function() {
					Blockly.svgResize(workspace);
				});

				/* ═══ 4. GENERATORS ═══ */
				«generateGeneratorsBody(spec)»

				/* ═══ 5. VALIDATION BLOCK RUNTIME ═══ */
				«ValidationRuntimeGenerator.generateClassicJs(spec)»

				/* ═══ 6. VALIDATIONS ═══ */
				«generateValidationsBody(spec)»
				initValidations(workspace);

				/* ═══ 7. REFERENCES ═══ */
				«generateReferenceUpdateScript(spec)»

			/* ═══ 8. MODEL VIEWER ═══ */
			«AppMakerHtmlRuntimeGenerator.script(spec)»
			«modelViewerScript(spec)»

			/* ═══ 9. AST RUNTIME ═══ */
			«generateRuntimeScript(spec)»
			</script>
		</body>
		</html>
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// Workspace options
	// ═══════════════════════════════════════════════════════════════

	def String workspaceOptions(EditorSpec spec) {
		val merged = newLinkedHashMap()
		merged.putAll(defaultWorkspaceOptions())
		merged.putAll(BlocklySpecModelQueries.workspaceOptions(spec))
		val lines = merged.entrySet
			.filter[e | e.value !== null]
			.map[e | '''«e.key»: «renderJsValue(e.value)»''']
		lines.join(",\n")
	}

	def Map<String, Object> defaultWorkspaceOptions() {
		val defaults = newLinkedHashMap()
		defaults.put("trashcan", true)

		val zoom = newLinkedHashMap()
		zoom.put("controls", true)
		zoom.put("wheel", true)
		zoom.put("startScale", 1.0)
		zoom.put("maxScale", 3)
		zoom.put("minScale", 0.3)
		defaults.put("zoom", zoom)

		val move = newLinkedHashMap()
		move.put("scrollbars", true)
		move.put("drag", true)
		move.put("wheel", true)
		defaults.put("move", move)

		// Keep grid disabled by default (no "grid" in generated JS).
		defaults.put("grid", null)
		defaults
	}

	def String renderJsValue(Object value) {
		if (value === null) return "null"
		if (value instanceof String) return toJsonString(value as String)
		if (value instanceof Boolean || value instanceof Number) return String.valueOf(value)
		if (value instanceof Map<?, ?>) return renderJsMap(value)
		if (value instanceof List<?>) return renderJsList(value)
		toJsonString(String.valueOf(value))
	}

	def String renderJsMap(Object mapObj) {
		val map = mapObj as Map<String, Object>
		if (map === null || map.empty) return "{}"
		val pairs = new ArrayList<String>()
		val keys = new ArrayList<String>(map.keySet)
		for (key : keys) {
			val v = map.get(key)
			if (key !== null && v !== null) {
				pairs.add(key + ": " + renderJsValue(v))
			}
		}
		"{ " + pairs.join(", ") + " }"
	}

	def String renderJsList(Object listObj) {
		val list = listObj as List<Object>
		if (list === null || list.empty) return "[]"
		val parts = new ArrayList<String>()
		for (var i = 0; i < list.size; i++) {
			parts.add(renderJsValue(list.get(i)))
		}
		"[" + parts.join(", ") + "]"
	}

	// ═══════════════════════════════════════════════════════════════
	// Shared HTML fragments
	// ═══════════════════════════════════════════════════════════════

	def String editorCss() {
		'''
			<style>
				:root{color-scheme:light;--bg:#f4f6f8;--surface:#fff;--panel:#fbfcfe;--text:#17202a;--muted:#5d6875;--line:#d9dee7;--accent:#1f6feb;--accent-strong:#174ea6;--success:#15803d;--warning:#8a5a00;--danger:#b42318}
				*{box-sizing:border-box}
				body{margin:0;background:var(--bg);color:var(--text);font-family:-apple-system,BlinkMacSystemFont,"Segoe UI",Arial,sans-serif;display:flex;flex-direction:column;height:100vh;min-height:0}
				button,summary{font:inherit}
				button{border:1px solid var(--line);background:#fff;color:var(--text);padding:7px 11px;border-radius:6px;cursor:pointer;font-weight:650;white-space:nowrap}
				button:hover,summary:hover{border-color:#b8c2cf;background:#f8fafc}
				button:focus-visible,summary:focus-visible{outline:2px solid var(--accent);outline-offset:2px}
				#header.appbar{background:var(--surface);border-bottom:1px solid var(--line);padding:10px 14px;display:grid;grid-template-columns:minmax(220px,auto) 1fr;gap:12px;align-items:center;box-shadow:0 1px 2px rgba(15,23,42,.04);z-index:2}
				.header-title{min-width:0}
				.header-kicker{margin:0 0 2px;color:var(--muted);font-size:.72rem;font-weight:700;text-transform:uppercase;letter-spacing:0}
				#header h1{margin:0;font-size:1.15rem;line-height:1.2;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}
				.editor-mode-hint{margin-top:2px;color:var(--muted);font-size:.76rem}
				.header-buttons{display:flex;gap:8px;flex-wrap:wrap;align-items:center;justify-content:flex-end}
				.toolbar-group{display:flex;gap:6px;align-items:center;flex-wrap:wrap;padding-right:8px;border-right:1px solid var(--line)}
				.toolbar-group:last-child{padding-right:0;border-right:0}
				.command-button{min-height:34px}
				.button-primary{background:var(--accent);border-color:var(--accent);color:#fff}
				.button-primary:hover{background:var(--accent-strong);border-color:var(--accent-strong)}
				.button-success{background:#ecfdf3;border-color:#bbf7d0;color:#166534}
				.button-danger{background:#fef2f2;border-color:#fecaca;color:var(--danger)}
				.mode-toggle{display:flex;gap:0;padding:2px;border:1px solid var(--line);border-radius:8px;background:#f8fafc}
				.mode-toggle button{border:0;background:transparent;color:var(--muted);padding:6px 9px;border-radius:6px}
				.mode-toggle button.active{background:var(--surface);color:var(--accent);box-shadow:0 1px 3px rgba(15,23,42,.12)}
				.toolbar-menu{position:relative}
				.toolbar-menu summary{list-style:none;border:1px solid var(--line);border-radius:6px;background:#fff;color:var(--text);padding:7px 11px;cursor:pointer;font-weight:bold;white-space:nowrap}
				.toolbar-menu summary::-webkit-details-marker{display:none}
				.toolbar-menu-panel{position:absolute;right:0;top:calc(100% + 8px);min-width:230px;padding:8px;border:1px solid var(--line);border-radius:8px;background:#fff;box-shadow:0 16px 34px rgba(15,23,42,.18);display:flex;flex-direction:column;gap:6px;z-index:20}
				.toolbar-menu-panel button{width:100%;text-align:left}
				.speed-control{display:flex;align-items:center;gap:6px;color:var(--muted);font-size:.82rem;padding:4px 2px}
				.speed-control input{width:90px}
				.editor-toast{position:fixed;right:16px;bottom:16px;max-width:min(420px,calc(100vw - 32px));padding:10px 12px;border:1px solid var(--line);border-radius:8px;background:#fff;color:var(--text);box-shadow:0 14px 36px rgba(15,23,42,.18);z-index:30;display:none}
				.editor-toast.visible{display:block}
				.editor-toast.error{border-color:#fecaca;background:#fef2f2;color:#991b1b}
				.editor-toast.warning{border-color:#fde68a;background:#fffbeb;color:#92400e}
				#main{display:grid;grid-template-columns:minmax(520px,1fr) minmax(360px,min(40vw,500px));gap:12px;flex:1;min-height:0;overflow:hidden;padding:12px}
				.workspace-shell,.inspector-panel{min-height:0;border:1px solid var(--line);border-radius:8px;background:var(--surface);overflow:hidden;box-shadow:0 1px 2px rgba(15,23,42,.04)}
				.workspace-shell{display:flex;flex-direction:column}
				.workspace-panel-header{display:flex;align-items:center;justify-content:space-between;gap:10px;padding:9px 12px;border-bottom:1px solid var(--line);background:var(--panel)}
				.workspace-title{font-weight:700}
				.workspace-hint{color:var(--muted);font-size:.78rem}
				#blocklyDiv{flex:1 1 auto;min-height:0;height:100%}
				#outputPanel{display:flex;flex-direction:column;min-width:0}
				.panel-header{display:flex;justify-content:space-between;align-items:flex-start;gap:10px;padding:10px 12px 6px}
				#outputPanel h2{margin:0;font-size:1rem;color:var(--text);line-height:1.2}
				.panel-subtitle{margin-top:2px;color:var(--muted);font-size:.76rem}
				#tabs{display:flex;gap:4px;border-bottom:1px solid var(--line);margin:0 10px;overflow-x:auto;scrollbar-width:thin}
				#tabs button{flex:0 0 auto;padding:8px 10px;border:0;background:#f1f4f8;color:#526071;border-radius:6px 6px 0 0}
				#tabs button.active{background:#fff;color:var(--accent);box-shadow:inset 0 -2px 0 var(--accent)}
				body:not(.developer-mode) [data-developer-only="true"]{display:none!important}
				.tab-content{flex:1;display:none;margin:0 10px 10px;padding:10px;overflow:auto;font-size:.85em;border:1px solid var(--line);border-top:0;border-radius:0 0 6px 6px;min-height:0}
				.tab-content.active{display:block}
				#modelView{background:#fff;font-family:Arial,sans-serif}
				#jsonView{background:#f5f5f5;font-family:monospace;white-space:pre-wrap}
				#runtimeView{background:#172033;color:#d4d4d4;font-family:ui-monospace,SFMono-Regular,Menlo,Consolas,monospace;font-size:.82em;white-space:pre-wrap}
				.model-node{margin:4px 0;padding:8px 12px;border-radius:6px;border-left:4px solid #4285f4}
				.model-node .node-type{font-weight:bold;font-size:.9em;color:#333}
				.model-node .node-attr{font-size:.8em;color:#666;margin-top:2px}
				.model-node .node-attr span{background:#e8f0fe;padding:1px 6px;border-radius:3px;margin-right:4px}
				.model-children{margin-left:16px;padding-left:8px;border-left:2px dashed #ccc}
				.validation-summary{display:flex;align-items:center;justify-content:space-between;margin-bottom:8px;font-size:.85em;color:#333}
				.validation-empty{color:#5f6368;text-align:center;margin-top:40px}
				.validation-list{display:flex;flex-direction:column;gap:8px}
				.validation-item{border:1px solid #f4b400;border-left:4px solid #f4b400;background:#fff8e1;border-radius:4px;padding:8px 10px;text-align:left;cursor:pointer;color:#3c4043}
				.validation-item:hover{background:#fff3c4}
				.validation-item-type{font-size:.75em;font-weight:bold;color:#8a5a00;margin-bottom:3px}
				.validation-item-message{font-size:.85em;line-height:1.35}
				#validationBlocksView{padding:0;background:#f5f7fb;overflow:hidden}
				#validationBlocksView iframe{width:100%;height:100%;min-height:520px;border:0;background:#fff}
				.reference-dialog-backdrop{position:fixed;inset:0;background:rgba(0,0,0,.28);display:flex;align-items:center;justify-content:center;z-index:9999}
				.reference-dialog-panel{background:#fff;border-radius:6px;box-shadow:0 10px 30px rgba(0,0,0,.25);width:min(420px,calc(100vw - 32px));max-height:min(520px,calc(100vh - 32px));display:flex;flex-direction:column}
				.reference-dialog-title{font-weight:bold;color:#202124;padding:14px 16px;border-bottom:1px solid #e0e0e0}
				.reference-dialog-list{padding:8px 16px;overflow:auto}
				.reference-dialog-row{display:flex;align-items:center;gap:8px;padding:8px 0;font-size:.9em;color:#333}
				.reference-dialog-row input{margin:0}
				.reference-dialog-empty{padding:18px 0;color:#777;text-align:center}
				.reference-dialog-textarea{margin:12px 16px 0;min-height:160px;resize:vertical;border:1px solid #dadce0;border-radius:4px;padding:8px;font:13px/1.4 monospace}
				.reference-dialog-actions{display:flex;gap:8px;justify-content:flex-end;padding:12px 16px;border-top:1px solid #e0e0e0}
				.reference-dialog-actions button{border:1px solid #dadce0;background:#fff;border-radius:4px;padding:7px 12px;cursor:pointer;font-weight:bold}
				.reference-dialog-actions .reference-dialog-primary{background:#1a73e8;border-color:#1a73e8;color:#fff}
				.rt-step{padding:2px 6px;border-bottom:1px solid #333}.rt-step:hover{background:#2a2a2a}
				.rt-done{padding:4px 6px;color:#4CAF50;font-weight:bold;border-top:1px solid #444}
				.rt-info{padding:2px 6px;color:#888;font-style:italic}
				.rt-err{padding:2px 6px;color:#f44336}
				@media (max-width: 1040px){
					#header.appbar{grid-template-columns:1fr}
					.header-buttons{justify-content:flex-start}
					#main{grid-template-columns:1fr;overflow:auto}
					.workspace-shell{min-height:420px}
					.inspector-panel{min-height:420px;max-height:620px}
				}
				@media (max-width: 560px){
					#header.appbar{padding:10px}
					.toolbar-group{width:100%;border-right:0;padding-right:0}
					.toolbar-group.primary-actions button{flex:1 1 auto}
					.mode-toggle{width:100%}
					.mode-toggle button{flex:1}
					.toolbar-menu{width:100%}
					.toolbar-menu summary{width:100%;text-align:center}
					.toolbar-menu-panel{position:static;margin-top:6px;box-shadow:none}
					#main{padding:8px}
					.workspace-shell{min-height:360px}
					.inspector-panel{min-height:420px;max-height:520px}
				}
				«AppMakerHtmlRuntimeGenerator.css»
			</style>
		'''
	}

	def String editorHeaderHtml(String title) {
		'''
			<div id="header" class="appbar" data-generated-ui="2026-product">
				<div class="header-title">
					<div class="header-kicker">Blockly editor</div>
					<h1>«esc(title)»</h1>
					<div id="editorModeHint" class="editor-mode-hint">Build mode</div>
				</div>
					<div class="header-buttons" role="toolbar" aria-label="Editor actions">
						<div class="toolbar-group primary-actions">
							<button class="button-primary command-button" onclick="loadSampleModel()">Load Sample</button>
							<button class="command-button" onclick="saveWorkspace()">Save</button>
							<button class="command-button" onclick="loadWorkspace()">Load</button>
							<button class="command-button" onclick="exportJSON()">Export JSON</button>
					</div>
					<div class="toolbar-group">
						<details class="toolbar-menu">
							<summary>More</summary>
							<div class="toolbar-menu-panel">
								<button onclick="loadModelJSON()">Import Model JSON</button>
								<button onclick="exportXMI()">Export XMI</button>
								<button onclick="exportCode()">Export Code</button>
								<button class="button-success" onclick="runModel()">Run Runtime</button>
								<button onclick="pauseModel()">Pause Runtime</button>
								<button onclick="stepModel()">Step Runtime</button>
								<button class="button-danger" onclick="stopModel()">Stop Runtime</button>
								<label class="speed-control">Speed
									<input type="range" min="50" max="2000" value="500" step="50" oninput="_astRunner.speed=parseInt(this.value)">
								</label>
							</div>
						</details>
					</div>
					<div class="toolbar-group">
						<div class="mode-toggle" aria-label="Editor mode">
							<button id="buildModeButton" type="button" class="active" onclick="setEditorMode('build')">Build</button>
							<button id="developerModeButton" type="button" onclick="setEditorMode('developer')">Developer</button>
						</div>
					</div>
				</div>
			</div>
			<div id="editorToast" class="editor-toast" role="status" aria-live="polite"></div>
		'''
	}

	def String editorMainHtml(EditorSpec spec) {
		'''
				<div id="main">
					<section class="workspace-shell" aria-label="Blockly workspace">
						<div class="workspace-panel-header">
							<div class="workspace-title">Workspace</div>
							<div class="workspace-hint">Drag blocks here to build the model.</div>
						</div>
						<div id="blocklyDiv"></div>
					</section>
					<aside id="outputPanel" class="inspector-panel" aria-label="Generated model inspector">
						<div class="panel-header">
							<div>
								<h2>Inspector</h2>
								<div id="panelSubtitle" class="panel-subtitle">Model, preview and validation issues</div>
							</div>
					</div>
					<div id="tabs">
						<button class="active" data-tab="model" onclick="switchTab('model')">Model</button>
						«AppMakerHtmlRuntimeGenerator.tabButton(spec)»
						<button data-tab="issues" onclick="switchTab('issues')">Issues <span id="issuesBadge"></span></button>
						<button data-tab="json" data-developer-only="true" onclick="switchTab('json')">JSON</button>
						<button data-tab="validationBlocks" data-developer-only="true" onclick="switchTab('validationBlocks')">Validation Blocks</button>
						<button data-tab="runtime" data-developer-only="true" onclick="switchTab('runtime')">Runtime</button>
					</div>
					<div id="modelView" class="tab-content active"></div>
					«AppMakerHtmlRuntimeGenerator.tabContent(spec)»
						<div id="jsonView" class="tab-content"></div>
						<div id="issuesView" class="tab-content"></div>
						<div id="validationBlocksView" class="tab-content"><iframe title="Visual validation Blockly workspace" src="validation_workspace.html"></iframe></div>
						<div id="runtimeView" class="tab-content"></div>
					</aside>
				</div>
			'''
		}

	def String editorBootstrapScript(EditorSpec spec) {
		'''
			<script>
				Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
				var workspace = Blockly.inject('blocklyDiv', {
					toolbox: window.BLOCKLY_TOOLBOX,
					«workspaceOptions(spec)»
				});
				Blockly.svgResize(workspace);
				window.addEventListener('resize', function() {
					Blockly.svgResize(workspace);
				});
				initValidations(workspace);
				«generateReferenceUpdateScript(spec)»
				«AppMakerHtmlRuntimeGenerator.script(spec)»
				«modelViewerScript(spec)»
				«generateRuntimeScript(spec)»
			</script>
		'''
	}

	def String modelViewerScript(EditorSpec spec) {
		val nsURI = spec.nsURI ?: "http://www.example.org/" + (spec.domainName ?: "domain")
		val nsPrefix = spec.nsPrefix ?: (spec.domainName ?: "domain").toLowerCase
		'''
			/* ── Tab switching ── */
			var DEVELOPER_TABS = { json: true, validationBlocks: true, runtime: true };
			var currentEditorTab = 'model';

			function showEditorMessage(message, type) {
				var toast = document.getElementById('editorToast');
				if (!toast) { if (type === 'error') console.error(message); return; }
				toast.textContent = message;
				toast.className = 'editor-toast visible' + (type ? ' ' + type : '');
				window.clearTimeout(window.__editorToastTimer);
				window.__editorToastTimer = window.setTimeout(function() {
					toast.className = 'editor-toast';
				}, 4200);
			}

			function setEditorMode(mode, keepTab) {
				var developer = mode === 'developer';
				document.body.classList.toggle('developer-mode', developer);
				document.body.classList.toggle('build-mode', !developer);
				var buildButton = document.getElementById('buildModeButton');
				var developerButton = document.getElementById('developerModeButton');
				if (buildButton) {
					buildButton.classList.toggle('active', !developer);
					buildButton.setAttribute('aria-pressed', String(!developer));
				}
				if (developerButton) {
					developerButton.classList.toggle('active', developer);
					developerButton.setAttribute('aria-pressed', String(developer));
				}
				var hint = document.getElementById('editorModeHint');
				if (hint) hint.textContent = developer ? 'Developer mode' : 'Build mode';
				var subtitle = document.getElementById('panelSubtitle');
				if (subtitle) {
					subtitle.textContent = developer
						? 'Model, preview, JSON, runtime and validation internals'
						: 'Model, preview and validation issues';
				}
				if (!developer && !keepTab && DEVELOPER_TABS[currentEditorTab]) {
					switchTab(document.querySelector('[data-tab="preview"]') ? 'preview' : 'model');
				}
			}

			function switchTab(tab) {
				if (DEVELOPER_TABS[tab]) setEditorMode('developer', true);
				currentEditorTab = tab;
				document.querySelectorAll('.tab-content').forEach(function(el) { el.classList.remove('active'); });
				document.querySelectorAll('#tabs button').forEach(function(el) { el.classList.remove('active'); });
				var view = document.getElementById(tab + 'View');
				if (view) view.classList.add('active');
				var activeButton = document.querySelector('#tabs button[data-tab="' + tab + '"]');
				if (activeButton) activeButton.classList.add('active');
				if (tab === 'validationBlocks' && typeof syncValidationBlocksFromFrame === 'function') {
					window.setTimeout(syncValidationBlocksFromFrame, 0);
				}
			}

			window.BLOCKLY_SAMPLE_MODEL = «SampleModelGenerator.generate(spec)»;

			/* ── Extract model JSON from workspace ── */
			function getModelJSON() {
				var code = javascript.javascriptGenerator.workspaceToCode(workspace);
				if (!code || !code.trim()) return null;
				try {
					var wrapped = '[' + code.replace(/,\s*$/, '') + ']';
					wrapped = wrapped.replace(/,\s*\]/g, ']');
					return JSON.parse(wrapped);
				} catch(e) {
					return null;
				}
			}

			/* ── Render model tree as visual cards ── */
			function renderModelNode(obj) {
				if (!obj || typeof obj !== 'object') return '';
				var type = obj._type || 'Unknown';
				var html = '<div class="model-node">';
				html += '<div class="node-type">' + type + '</div>';
				html += '<div class="node-attr">';
				Object.keys(obj).forEach(function(key) {
					if (key === '_type') return;
					var val = obj[key];
					if (Array.isArray(val)) return;
					if (val !== null && typeof val === 'object') return;
					html += '<span>' + key + ': ' + val + '</span> ';
				});
				html += '</div>';
				Object.keys(obj).forEach(function(key) {
					var val = obj[key];
					if (Array.isArray(val)) {
						if (val.length === 0) return;
						html += '<div class="model-children">';
						html += '<div style="font-size:.75em;color:#999;margin:4px 0">' + key + ':</div>';
						val.forEach(function(child) { html += renderModelNode(child); });
						html += '</div>';
					} else if (val !== null && typeof val === 'object' && val._type) {
						html += '<div class="model-children">';
						html += '<div style="font-size:.75em;color:#999;margin:4px 0">' + key + ':</div>';
						html += renderModelNode(val);
						html += '</div>';
					}
				});
				html += '</div>';
				return html;
			}

			/* ── Update both tabs ── */
			function updateOutput() {
				var model = getModelJSON();
				var modelView = document.getElementById('modelView');
				var jsonView = document.getElementById('jsonView');
				if (!model || model.length === 0) {
					modelView.innerHTML = '<p style="color:#999;text-align:center;margin-top:40px">Drag blocks to build your model</p>';
					jsonView.textContent = '// Empty workspace';
					if (typeof renderAppMakerPreview === 'function') renderAppMakerPreview([]);
					return;
				}
				var html = '';
				model.forEach(function(node) { html += renderModelNode(node); });
				modelView.innerHTML = html;
				jsonView.textContent = JSON.stringify(model, null, 2);
				if (typeof renderAppMakerPreview === 'function') renderAppMakerPreview(model);
			}

			/* ── Save workspace state ── */
			function saveWorkspace() {
				var state = Blockly.serialization.workspaces.save(workspace);
				var json = JSON.stringify(state, null, 2);
				var blob = new Blob([json], {type: 'application/json'});
				var a = document.createElement('a');
				a.href = URL.createObjectURL(blob);
				a.download = '«esc(spec.domainName ?: "model")»_workspace.json';
				a.click();
			}

			/* ── Load workspace state ── */
			function loadWorkspace() {
				var input = document.createElement('input');
				input.type = 'file';
				input.accept = '.json';
				input.onchange = function(e) {
					var file = e.target.files[0];
					if (!file) return;
					var reader = new FileReader();
					reader.onload = function(ev) {
						try {
							var state = JSON.parse(ev.target.result);
							Blockly.serialization.workspaces.load(state, workspace);
							if (typeof updateReferenceDropdowns === 'function') updateReferenceDropdowns(workspace);
							if (typeof applyValidationWarnings === 'function') applyValidationWarnings(workspace);
							if (typeof updateOutput === 'function') updateOutput();
							Blockly.svgResize(workspace);
				} catch(err) {
					showEditorMessage('Error loading workspace: ' + err.message, 'error');
				}
					};
					reader.readAsText(file);
					};
					input.click();
				}

				/* ── Import domain model JSON back into Blockly blocks ── */
				function loadModelJSON() {
					var input = document.createElement('input');
					input.type = 'file';
					input.accept = '.json';
					input.onchange = function(e) {
						var file = e.target.files[0];
						if (!file) return;
						var reader = new FileReader();
						reader.onload = function(ev) {
							try {
								var model = JSON.parse(ev.target.result);
								importModelJSON(model);
					} catch(err) {
						showEditorMessage('Error importing model JSON: ' + err.message, 'error');
					}
						};
						reader.readAsText(file);
					};
					input.click();
				}

		function loadSampleModel() {
			if (!window.BLOCKLY_SAMPLE_MODEL || window.BLOCKLY_SAMPLE_MODEL.length === 0) {
				showEditorMessage('No sample model was generated for this domain.', 'warning');
				return;
			}
					importModelJSON(JSON.parse(JSON.stringify(window.BLOCKLY_SAMPLE_MODEL)));
				}

				function importModelJSON(model) {
					var config = (window.BLOCKLY_DOMAIN_CODEGEN && window.BLOCKLY_DOMAIN_CODEGEN.blocks) || {};
					var roots = Array.isArray(model) ? model : [model];
					var supported = roots.filter(function(node) {
						return node && node._type && config[node._type];
					});
					if (supported.length === 0) {
						throw new Error('No known domain nodes were found.');
					}

					var idMap = {};
					var pendingReferences = [];
					var eventsEnabled = Blockly.Events.isEnabled ? Blockly.Events.isEnabled() : true;
					if (Blockly.Events.disable) Blockly.Events.disable();
					try {
						workspace.clear();
						var x = 40;
						var y = 40;
						roots.forEach(function(node) {
							if (!node || !node._type || !config[node._type]) return;
							createImportedModelNode(node, x, y, idMap, pendingReferences);
							x += 300;
							if (x > 640) { x = 40; y += 170; }
						});
					} finally {
						if (eventsEnabled && Blockly.Events.enable) Blockly.Events.enable();
					}

					applyImportedReferences(pendingReferences, idMap);
					if (typeof applyValidationWarnings === 'function') applyValidationWarnings(workspace);
					if (typeof updateOutput === 'function') updateOutput();
					Blockly.svgResize(workspace);
				}

				function createImportedModelNode(node, x, y, idMap, pendingReferences) {
					var config = (window.BLOCKLY_DOMAIN_CODEGEN && window.BLOCKLY_DOMAIN_CODEGEN.blocks) || {};
					var blockConfig = node && node._type ? config[node._type] : null;
					if (!blockConfig) return null;

					var block = createImportedBlock(node._type, node._blockId, x, y, idMap);
					(blockConfig.fields || []).forEach(function(name) {
						if (Object.prototype.hasOwnProperty.call(node, name)) {
							setImportedField(block, name, node[name]);
						}
					});
					(blockConfig.references || []).forEach(function(name) {
						pendingReferences.push({ block: block, field: name, oldId: node[name] || '' });
					});
					(blockConfig.values || []).forEach(function(name) {
						var childNode = node[name];
						if (!childNode || !childNode._type) return;
						var child = createImportedModelNode(childNode, 0, 0, idMap, pendingReferences);
						var input = block.getInput(name);
						if (child && input && input.connection && child.outputConnection) {
							input.connection.connect(child.outputConnection);
						}
					});
					(blockConfig.statements || []).forEach(function(name) {
						var children = Array.isArray(node[name]) ? node[name] : [];
						connectImportedStatementBlocks(block, name, children, idMap, pendingReferences);
					});
					return block;
				}

				function connectImportedStatementBlocks(parent, inputName, children, idMap, pendingReferences) {
					var input = parent.getInput(inputName);
					var previous = null;
					children.forEach(function(childNode) {
						if (!childNode || !childNode._type) return;
						var child = createImportedModelNode(childNode, 0, 0, idMap, pendingReferences);
						if (!child) return;
						if (!previous && input && input.connection && child.previousConnection) {
							input.connection.connect(child.previousConnection);
						} else if (previous && previous.nextConnection && child.previousConnection) {
							previous.nextConnection.connect(child.previousConnection);
						}
						previous = child;
					});
				}

				function createImportedBlock(type, requestedId, x, y, idMap) {
					var block = null;
					var blockId = requestedId && !workspace.getBlockById(requestedId) ? requestedId : importedModelNewId();
					try {
						block = workspace.newBlock(type, blockId);
					} catch(e) {
						blockId = importedModelNewId();
						block = workspace.newBlock(type, blockId);
					}
					if (requestedId) idMap[requestedId] = block.id;
					block.initSvg();
					block.render();
					if (typeof x === 'number' && typeof y === 'number') block.moveBy(x, y);
					return block;
				}

				function applyImportedReferences(pendingReferences, idMap) {
					if (typeof updateReferenceDropdowns === 'function') updateReferenceDropdowns(workspace);
					pendingReferences.forEach(function(ref) {
						if (!ref.oldId || !ref.block || !ref.block.getField(ref.field)) return;
						if (Array.isArray(ref.oldId)) {
							var mappedIds = ref.oldId.map(function(oldId) { return idMap[oldId] || oldId; }).filter(function(id) { return !!workspace.getBlockById(id); });
							try {
								ref.block.setFieldValue(mappedIds.join(', '), ref.field);
							} catch(e) {}
							return;
						}
						var mappedId = idMap[ref.oldId] || ref.oldId;
						if (!workspace.getBlockById(mappedId)) return;
						try {
							ref.block.setFieldValue(mappedId, ref.field);
						} catch(e) {
							ref.block.setFieldValue('', ref.field);
						}
					});
					if (typeof updateReferenceDropdowns === 'function') updateReferenceDropdowns(workspace);
				}

				function setImportedField(block, name, value) {
					if (!block || !block.getField(name) || value === null || value === undefined) return;
					var field = block.getField(name);
					var current = field.getValue ? field.getValue() : null;
					if (Array.isArray(value)) {
						value = value.map(function(item) {
							return item === null || item === undefined ? '' : String(item).trim();
						}).filter(function(item) {
							return item.length > 0;
						}).join(', ');
					}
					if (typeof value === 'boolean' && (current === 'TRUE' || current === 'FALSE')) {
						value = value ? 'TRUE' : 'FALSE';
					}
					try {
						block.setFieldValue(String(value), name);
					} catch(e) {}
				}

				function importedModelNewId() {
					if (Blockly.utils && Blockly.utils.idGenerator && Blockly.utils.idGenerator.genUid) {
						return Blockly.utils.idGenerator.genUid();
					}
					return 'imported_' + Math.random().toString(36).slice(2);
				}

					/* ── Export JSON ── */
					function exportJSON() {
						if (!confirmExportIfInvalid(workspace)) return;
					var model = getModelJSON();
					if (!model) { showEditorMessage('Workspace is empty.', 'warning'); return; }
				var json = JSON.stringify(model, null, 2);
				var blob = new Blob([json], {type: 'application/json'});
				var a = document.createElement('a');
				a.href = URL.createObjectURL(blob);
				a.download = '«esc(spec.domainName ?: "model")»_model.json';
				a.click();
			}

				/* ── Export domain instance XMI (EMF/XMI style) ── */
				function exportXMI() {
					if (!confirmExportIfInvalid(workspace)) return;
					var model = getModelJSON();
					if (!model) { showEditorMessage('Workspace is empty.', 'warning'); return; }
				var xmi = modelToDomainXMI(model);
				var blob = new Blob([xmi], {type: 'application/xml'});
				var a = document.createElement('a');
				a.href = URL.createObjectURL(blob);
				a.download = '«esc(spec.domainName ?: "model")»_model.xmi';
				a.click();
			}

				/* ── Export generated domain code ── */
				function exportCode() {
					if (!confirmExportIfInvalid(workspace)) return;
					var code = generateDomainCode(workspace);
					if (!code || !code.trim()) { showEditorMessage('Workspace is empty.', 'warning'); return; }
					var config = window.BLOCKLY_DOMAIN_CODEGEN || {};
					var ext = config.fileExtension || 'txt';
					var blob = new Blob([code], {type: 'text/plain'});
					var a = document.createElement('a');
					a.href = URL.createObjectURL(blob);
					a.download = '«esc(spec.domainName ?: "model")»_code.' + ext.replace(/^\./, '');
					a.click();
				}

			function modelToDomainXMI(model) {
				var nsURI = '«escapeJson(nsURI)»';
				var nsPrefix = '«escapeJson(nsPrefix)»';
				var roots = Array.isArray(model) ? model : [model];
				var xmi = '<?xml version="1.0" encoding="UTF-8"?>\n';
				xmi += '<xmi:XMI xmi:version="2.0"';
				xmi += ' xmlns:xmi="http://www.omg.org/XMI"';
				xmi += ' xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"';
				xmi += ' xmlns:' + nsPrefix + '="' + xmlAttribute(nsURI) + '">\n';
				roots.forEach(function(node) {
					xmi += domainNodeToXMI(node, 1, nsPrefix, nsPrefix + ':' + xmlName(node && node._type ? node._type : 'Element'), false);
				});
				xmi += '</xmi:XMI>';
				return xmi;
			}
			window.modelToDomainXMI = modelToDomainXMI;

			function domainNodeToXMI(obj, indent, nsPrefix, elementName, includeType) {
				if (!obj || typeof obj !== 'object') return '';
				var pad = '  '.repeat(indent);
				var type = obj._type || 'Element';
				var config = domainBlockConfig(type);
				var tag = elementName || (nsPrefix + ':' + xmlName(type));
				var attrs = '';
				var children = '';

				if (includeType) attrs += ' xsi:type="' + nsPrefix + ':' + xmlName(type) + '"';
				if (obj._blockId) attrs += ' xmi:id="' + xmlAttribute(obj._blockId) + '"';

				(config.fields || []).forEach(function(key) {
					if (!Object.prototype.hasOwnProperty.call(obj, key)) return;
					var val = obj[key];
					if (Array.isArray(val)) {
						val.forEach(function(item) {
							children += pad + '  <' + xmlName(key) + '>' + xmlText(domainScalarValue(item, key, config)) + '</' + xmlName(key) + '>\n';
						});
					} else if (val !== null && val !== undefined && typeof val !== 'object') {
						attrs += ' ' + xmlName(key) + '="' + xmlAttribute(domainScalarValue(val, key, config)) + '"';
					}
				});

				(config.references || []).forEach(function(key) {
					if (!Object.prototype.hasOwnProperty.call(obj, key)) return;
					var ids = referenceIds(obj[key]);
					if (ids.length) attrs += ' ' + xmlName(key) + '="' + xmlAttribute(ids.join(' ')) + '"';
				});

				(config.values || []).forEach(function(key) {
					var child = obj[key];
					if (child && child._type) {
						children += domainNodeToXMI(child, indent + 1, nsPrefix, xmlName(key), true);
					}
				});

				(config.statements || []).forEach(function(key) {
					var list = Array.isArray(obj[key]) ? obj[key] : [];
					list.forEach(function(child) {
						if (child && child._type) {
							children += domainNodeToXMI(child, indent + 1, nsPrefix, xmlName(key), true);
						}
					});
				});

				if (children) {
					return pad + '<' + tag + attrs + '>\n' + children + pad + '</' + tag + '>\n';
				} else {
					return pad + '<' + tag + attrs + '/>\n';
				}
			}

			function domainBlockConfig(type) {
				var blocks = (window.BLOCKLY_DOMAIN_CODEGEN && window.BLOCKLY_DOMAIN_CODEGEN.blocks) || {};
				return blocks[type] || { fields: [], fieldTypes: {}, references: [], values: [], statements: [] };
			}

			function domainScalarValue(value, fieldName, config) {
				var type = config && config.fieldTypes ? config.fieldTypes[fieldName] : null;
				if (type === 'BOOLEAN') {
					if (value === true || value === 'TRUE' || value === 'true' || value === '1') return 'true';
					if (value === false || value === 'FALSE' || value === 'false' || value === '0') return 'false';
				}
				return value === null || value === undefined ? '' : String(value);
			}

			function referenceIds(value) {
				if (Array.isArray(value)) {
					return value.map(function(item) { return String(item || '').trim(); }).filter(Boolean);
				}
				if (value === null || value === undefined || value === '') return [];
				return String(value).split(/[,\n ]+/).map(function(item) { return item.trim(); }).filter(Boolean);
			}

			function xmlName(value) {
				var text = String(value || 'Element').replace(/[^A-Za-z0-9_.-]/g, '_');
				return /^[A-Za-z_]/.test(text) ? text : '_' + text;
			}

			function xmlText(value) {
				return String(value === null || value === undefined ? '' : value)
					.replace(/&/g, '&amp;')
					.replace(/</g, '&lt;')
					.replace(/>/g, '&gt;');
			}

			function xmlAttribute(value) {
				return xmlText(value).replace(/"/g, '&quot;');
			}

			/* ── Auto-update ── */
			workspace.addChangeListener(function(e) {
				if (e.type !== Blockly.Events.UI) updateOutput();
			});
			setEditorMode('build', true);
			updateOutput();
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// Generic AST Runtime (stepper / walker)
	// ═══════════════════════════════════════════════════════════════

	def String generateRuntimeScript(EditorSpec spec) {
		'''
			/* ═══ GENERIC AST RUNTIME ═══ */
			var _astRunner = {
				running: false, paused: false, stepping: false,
				stepResolve: null, speed: 500, nodeCount: 0,

				run: function() {
					var code;
					try {
						code = javascript.javascriptGenerator.workspaceToCode(workspace);
					} catch(ex) { this.log('Generator error: ' + ex.message, 'err'); return; }
					if (!code || code.trim().length === 0) { showEditorMessage('Workspace is empty.', 'warning'); return; }
					var model;
					try {
						var wrapped = '[' + code.replace(/,\s*$/, '') + ']';
						wrapped = wrapped.replace(/,\s*\]/g, ']');
						model = JSON.parse(wrapped);
					} catch(ex) { this.log('JSON parse error: ' + ex.message, 'err'); return; }
					this.nodeCount = 0;
					this.running = true; this.paused = false; this.stepping = false;
					document.getElementById('runtimeView').innerHTML = '';
					this.log('▶ Execution started (' + model.length + ' root node' + (model.length > 1 ? 's' : '') + ')', 'info');
					var self = this;
					(async function() {
						for (var i = 0; i < model.length; i++) {
							if (!self.running) break;
							await self.walkNode(model[i], 0);
						}
						self.running = false;
						workspace.highlightBlock(null);
						self.log('✓ Finished — visited ' + self.nodeCount + ' nodes', 'done');
					})();
				},

				walkNode: async function(node, depth) {
					if (!node || typeof node !== 'object' || !node._type || !this.running) return;
					this.nodeCount++;
					if (node._blockId) workspace.highlightBlock(node._blockId);
					var attrs = [];
					var childKeys = [];
					for (var key in node) {
						if (key === '_type' || key === '_blockId') continue;
						var v = node[key];
						if (Array.isArray(v)) { childKeys.push(key); continue; }
						if (v !== null && typeof v === 'object' && v._type) { childKeys.push(key); continue; }
						attrs.push(key + '=' + JSON.stringify(v));
					}
					var indent = '';
					for (var d = 0; d < depth; d++) indent += '  ';
					this.log(indent + '► ' + node._type + (attrs.length ? '  (' + attrs.join(', ') + ')' : ''), 'step');
					await this.waitStep();
					for (var ci = 0; ci < childKeys.length; ci++) {
						var ck = childKeys[ci];
						var cv = node[ck];
						if (Array.isArray(cv)) {
							for (var j = 0; j < cv.length; j++) {
								if (!this.running) return;
								await this.walkNode(cv[j], depth + 1);
							}
						} else if (cv && typeof cv === 'object' && cv._type) {
							if (!this.running) return;
							await this.walkNode(cv, depth + 1);
						}
					}
				},

				waitStep: function() {
					var self = this;
					if (this.stepping || this.paused) {
						return new Promise(function(r) { self.stepResolve = r; });
					}
					return new Promise(function(r) { setTimeout(r, self.speed); });
				},

				pause: function() { this.paused = true; },
				resume: function() {
					this.paused = false; this.stepping = false;
					if (this.stepResolve) { this.stepResolve(); this.stepResolve = null; }
				},
				step: function() {
					this.stepping = true;
					if (this.stepResolve) { this.stepResolve(); this.stepResolve = null; }
				},
				stop: function() {
					this.running = false;
					if (this.stepResolve) { this.stepResolve(); this.stepResolve = null; }
					workspace.highlightBlock(null);
					this.log('⏹ Stopped', 'info');
				},

				log: function(msg, cls) {
					var el = document.getElementById('runtimeView');
					if (!el) return;
					var d = document.createElement('div');
					d.className = 'rt-' + (cls || 'info');
					d.textContent = msg;
					el.appendChild(d);
					el.scrollTop = el.scrollHeight;
				}
			};

			function runModel() {
				if (_astRunner.running && _astRunner.paused) { _astRunner.resume(); return; }
				if (_astRunner.running) return;
				switchTab('runtime');
				_astRunner.run();
			}
			function pauseModel() { _astRunner.pause(); }
			function stepModel() {
				if (!_astRunner.running) { _astRunner.stepping = true; switchTab('runtime'); _astRunner.run(); }
				else { _astRunner.step(); }
			}
			function stopModel() { _astRunner.stop(); }
		'''
	}

	// ═══════════════════════════════════════════════════════════════
	// Utility methods
	// ═══════════════════════════════════════════════════════════════

	def String esc(String s) {
		if (s === null) return ""
		s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
	}

	def String escapeJson(String s) {
		if (s === null) return ""
		s.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t")
	}

	def String toJsonString(String s) {
		if (s === null) return "\"\""
		'"' + escapeJson(s) + '"'
	}

	def int parseNumberOrDefault(String s, int defaultVal) {
		if (s === null || s.isEmpty) return defaultVal
		try { Integer.parseInt(s) } catch (NumberFormatException e) { defaultVal }
	}
}
