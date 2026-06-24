package io.github.plortinus.model2blockly.generator;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Generates a small JavaScript runtime for validation block trees.
 *
 * The runtime deliberately keeps the first conversion step as text:
 * Blockly-like validation blocks are compiled to audited runtime expressions
 * before those expressions are evaluated.
 */
public final class ValidationRuntimeGenerator {

	private ValidationRuntimeGenerator() {
	}

	public static String generateClassicJs(EditorSpec spec) {
		String modelJson = ValidationBlockModelGenerator.generate(spec);
		return """
			// Validation block runtime for domain "%s".
			// Auto-generated from EditorSpec.
			(function(root) {
			  const VALIDATION_BLOCK_MODEL = JSON.parse(%s);

			%s

			  const api = {
			    VALIDATION_BLOCK_MODEL,
			    blockTreeToRuntimeExpression,
			    compileValidationBlockModel,
			    createValidationAccessors,
			    evaluateRuntimeExpression,
			    generateValidationRuntimeRules,
			    runtimeExpressionToFunctionBody
			  };

			  root.BLOCKLY_VALIDATION_BLOCK_MODEL = VALIDATION_BLOCK_MODEL;
			  root.Model2BlocklyValidationRuntime = api;
			  if (typeof module !== "undefined" && module.exports) module.exports = api;
			})(typeof window !== "undefined" ? window : globalThis);
			""".formatted(defaultString(spec.getDomainName(), "domain"), jsString(modelJson), indent(runtimeFunctions(""), "  "));
	}

	private static String runtimeFunctions(String exportPrefix) {
		return """
			%sfunction generateValidationRuntimeRules(model = VALIDATION_BLOCK_MODEL) {
			  const rules = (model && Array.isArray(model.rules)) ? model.rules : [];
			  return rules.map((rule) => {
			    const runtimeExpression = ruleRuntimeExpression(rule);
			    return {
			      name: rule && rule.name ? rule.name : 'validation',
			      validationType: rule && rule.validationType ? rule.validationType : '',
			      targetType: rule && rule.targetType ? rule.targetType : '',
			      message: rule && rule.message ? rule.message : '',
			      runtimeCheck: runtimeCheckForRule(rule),
			      runtimeExpression,
			      runtimeFunctionBody: runtimeExpressionToFunctionBody(runtimeExpression)
			    };
			  });
			}

			%sfunction compileValidationBlockModel(model = VALIDATION_BLOCK_MODEL) {
			  return generateValidationRuntimeRules(model);
			}

			%sfunction blockTreeToRuntimeExpression(node) {
			  if (!node || !node.type) return '';
			  const fields = node.fields || {};
			  const inputs = node.inputs || {};
			  switch (node.type) {
			    case 'validation_rule':
			      return blockTreeToRuntimeExpression(inputs.CONDITION);
			    case 'validation_required_field':
			      return 'has(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_statement_cardinality':
			      return rangeExpression('inputCount(' + quoteExpressionString(fields.INPUT) + ')', fields.MIN, fields.MAX);
			    case 'validation_field_cardinality':
			      return rangeExpression('fieldCount(' + quoteExpressionString(fields.FIELD) + ')', fields.MIN, fields.MAX);
			    case 'validation_expression_rule':
			      return blockTreeToRuntimeExpression(inputs.CONDITION) || String(fields.EXPRESSION || '');
			    case 'validation_field_exists':
			      return 'has(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_field_value':
			      return 'value(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_field_number':
			      return 'number(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_field_count':
			      return 'fieldCount(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_field_unique':
			      return 'fieldUnique(' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_type_field_unique':
			      return 'typeFieldUnique(' + quoteExpressionString(fields.TYPE) + ', ' + quoteExpressionString(fields.FIELD) + ')';
			    case 'validation_input_count':
			      return 'inputCount(' + quoteExpressionString(fields.INPUT) + ')';
			    case 'validation_previous_block_is':
			      return 'previousBlockIs(' + quoteExpressionString(fields.TYPE) + ')';
			    case 'logic_compare':
			      return joinCompareExpression(blockTreeToRuntimeExpression(inputs.A), fields.OP, blockTreeToRuntimeExpression(inputs.B));
			    case 'logic_operation':
			      return joinLogicExpression(blockTreeToRuntimeExpression(inputs.A), fields.OP, blockTreeToRuntimeExpression(inputs.B));
			    case 'logic_negate':
			      return '!(' + (blockTreeToRuntimeExpression(inputs.BOOL) || 'false') + ')';
			    case 'logic_boolean':
			      return fields.BOOL === 'FALSE' ? 'false' : 'true';
			    case 'math_number':
			      return normalizedNumber(fields.NUM);
			    case 'text':
			      return quoteExpressionString(fields.TEXT);
			    default:
			      return String(fields.VALUE || fields.EXPRESSION || '');
			  }
			}

			%sfunction runtimeExpressionToFunctionBody(expression) {
			  const text = String(expression || '').trim();
			  return text ? 'return Boolean(' + text + ');' : 'return true;';
			}

			%sfunction createValidationAccessors(block, workspace) {
			  function value(name) {
			    const raw = block && block.getFieldValue ? block.getFieldValue(name) : '';
			    if (raw === null || raw === undefined) return '';
			    return String(raw).trim();
			  }
			  function number(name) {
			    const parsed = Number(value(name));
			    return Number.isFinite(parsed) ? parsed : 0;
			  }
			  function fieldCount(name) {
			    return fieldValuesFromRaw(value(name)).length;
			  }
			  function inputCount(name) {
			    let count = 0;
			    let child = block && block.getInputTargetBlock ? block.getInputTargetBlock(name) : null;
			    while (child) {
			      count += 1;
			      child = child.getNextBlock ? child.getNextBlock() : null;
			    }
			    return count;
			  }
			  function has(name) {
			    return fieldCount(name) > 0 && value(name) !== '';
			  }
			  function includes(name, item) {
			    return fieldValuesFromRaw(value(name)).includes(String(item));
			  }
			  function fieldUnique(name) {
			    return duplicateValues(fieldValuesFromRaw(value(name))).length === 0;
			  }
			  function typeFieldUnique(type, name) {
			    if (!workspace || !workspace.getAllBlocks || !block) return true;
			    const current = value(name);
			    if (!current) return true;
			    let count = 0;
			    workspace.getAllBlocks(false).forEach((candidate) => {
			      if (!candidate || candidate.type !== type || !candidate.getFieldValue) return;
			      const raw = candidate.getFieldValue(name);
			      const candidateValue = raw === null || raw === undefined ? '' : String(raw).trim();
			      if (candidateValue === current) count += 1;
			    });
			    return count <= 1;
			  }
			  function previousBlockIs(type) {
			    const previous = block && block.getPreviousBlock ? block.getPreviousBlock() : null;
			    return Boolean(previous && previous.type === type);
			  }
			  return {
			    value,
			    number,
			    has,
			    includes,
			    fieldCount,
			    inputCount,
			    fieldUnique,
			    typeFieldUnique,
			    previousBlockIs,
			    size: fieldCount
			  };
			}

			%sfunction evaluateRuntimeExpression(block, workspace, expression) {
			  const text = String(expression || '').trim();
			  if (!text) return { ok: true };
			  const accessors = createValidationAccessors(block, workspace);
			  try {
			    const fn = new Function(
			      'value',
			      'number',
			      'has',
			      'includes',
			      'fieldCount',
			      'inputCount',
			      'fieldUnique',
			      'typeFieldUnique',
			      'previousBlockIs',
			      'size',
			      'block',
			      'workspace',
			      'return Boolean(' + text + ');'
			    );
			    return {
			      ok: Boolean(fn(
			        accessors.value,
			        accessors.number,
			        accessors.has,
			        accessors.includes,
			        accessors.fieldCount,
			        accessors.inputCount,
			        accessors.fieldUnique,
			        accessors.typeFieldUnique,
			        accessors.previousBlockIs,
			        accessors.size,
			        block,
			        workspace
			      ))
			    };
			  } catch (error) {
			    return { ok: false, error: error && error.message ? error.message : String(error) };
			  }
			}

			function ruleRuntimeExpression(rule) {
			  if (!rule) return '';
			  const fromTree = blockTreeToRuntimeExpression(rule.blockTree);
			  if (fromTree) return fromTree;
			  if (rule.generator && rule.generator.runtimeExpression) return rule.generator.runtimeExpression;
			  return rule.runtimeExpression || '';
			}

			function runtimeCheckForRule(rule) {
			  if (rule && rule.generator && rule.generator.runtimeCheck) return rule.generator.runtimeCheck;
			  const root = rule && rule.blockTree ? rule.blockTree : null;
			  const condition = root && root.type === 'validation_rule' && root.inputs ? root.inputs.CONDITION : root;
			  return runtimeCheckForBlockType(condition && condition.type);
			}

			function runtimeCheckForBlockType(type) {
			  if (type === 'validation_required_field') return 'requiredField';
			  if (type === 'validation_statement_cardinality') return 'statementCardinality';
			  if (type === 'validation_field_cardinality') return 'fieldCardinality';
			  if (type === 'validation_field_unique') return 'fieldUnique';
			  if (type === 'validation_type_field_unique') return 'typeFieldUnique';
			  if (type === 'validation_previous_block_is') return 'previousBlockIs';
			  if (type === 'validation_expression_rule') return 'semanticExpression';
			  if (type) return 'expressionNode';
			  return 'custom';
			}

			function joinCompareExpression(left, operator, right) {
			  return (left || 'undefined') + ' ' + compareOperator(operator) + ' ' + (right || 'undefined');
			}

			function joinLogicExpression(left, operator, right) {
			  const op = operator === 'OR' ? '||' : '&&';
			  return '(' + (left || 'true') + ') ' + op + ' (' + (right || 'true') + ')';
			}

			function compareOperator(operator) {
			  if (operator === 'NEQ') return '!==';
			  if (operator === 'GTE') return '>=';
			  if (operator === 'LTE') return '<=';
			  if (operator === 'GT') return '>';
			  if (operator === 'LT') return '<';
			  return '===';
			}

			function rangeExpression(accessor, min, max) {
			  const lower = positiveBound(min) ? accessor + ' >= ' + Number(min) : '';
			  const upper = finiteUpperBound(max) ? accessor + ' <= ' + Number(max) : '';
			  if (lower && upper) return lower + ' && ' + upper;
			  if (lower) return lower;
			  if (upper) return upper;
			  return 'true';
			}

			function positiveBound(value) {
			  const parsed = Number(value || 0);
			  return Number.isFinite(parsed) && parsed > 0;
			}

			function finiteUpperBound(value) {
			  if (value === null || value === undefined || String(value).trim() === '') return false;
			  if (String(value).toLowerCase() === 'unbounded') return false;
			  const parsed = Number(value);
			  return Number.isFinite(parsed) && parsed > 0;
			}

			function normalizedNumber(value) {
			  const parsed = Number(value);
			  return Number.isFinite(parsed) ? String(parsed) : '0';
			}

			function quoteExpressionString(value) {
			  return JSON.stringify(value === null || value === undefined ? '' : String(value));
			}

			function fieldValuesFromRaw(raw) {
			  if (raw === null || raw === undefined || raw === '') return [];
			  return String(raw)
			    .split(/[,\\n]/)
			    .map((part) => part.trim())
			    .filter((part) => part.length > 0);
			}

			function duplicateValues(values) {
			  const seen = new Set();
			  const duplicates = new Set();
			  values.forEach((value) => {
			    if (seen.has(value)) duplicates.add(value);
			    seen.add(value);
			  });
			  return Array.from(duplicates);
			}
			""".formatted(
				exportPrefix,
				exportPrefix,
				exportPrefix,
				exportPrefix,
				exportPrefix,
				exportPrefix
			);
	}

	private static String indent(String text, String prefix) {
		return text.lines().map(line -> line.isEmpty() ? line : prefix + line).reduce("", (left, right) -> left + right + "\n");
	}

	private static String defaultString(String value, String fallback) {
		return value == null || value.isBlank() ? fallback : value;
	}

	private static String jsString(String value) {
		if (value == null) return "\"\"";
		StringBuilder out = new StringBuilder("\"");
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '\\': out.append("\\\\"); break;
				case '"': out.append("\\\""); break;
				case '\n': out.append("\\n"); break;
				case '\r': out.append("\\r"); break;
				case '\t': out.append("\\t"); break;
				case '<': out.append("\\u003c"); break;
				case '>': out.append("\\u003e"); break;
				case '&': out.append("\\u0026"); break;
				default:
					if (c < 0x20) out.append(String.format("\\u%04x", (int)c));
					else out.append(c);
			}
		}
		out.append('"');
		return out.toString();
	}
}
