package io.github.plortinus.model2blockly.generator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec;

/**
 * Serializes validation rules as a Blockly-like block tree.
 *
 * This is intentionally a model artifact, not the runtime evaluator. It makes
 * generated validations inspectable and prepares the next step: rendering and
 * editing validation rules with Blockly itself.
 */
public final class ValidationBlockModelGenerator {

	private ValidationBlockModelGenerator() {
	}

	public static String generate(EditorSpec spec) {
		StringBuilder out = new StringBuilder();
		out.append("{\n");
		property(out, 1, "format", "model2blockly.validationBlocks.v1", true);
		property(out, 1, "domain", defaultString(spec.getDomainName(), "domain"), true);
		out.append(indent(1)).append("\"customBlockTypes\": ");
		appendCustomBlockTypes(out);
		out.append(",\n");
		out.append(indent(1)).append("\"blockDefinitions\": ");
		appendIndentedJson(out, validationBlockDefinitionsJson(), 1);
		out.append(",\n");
		out.append(indent(1)).append("\"rules\": [\n");
		List<ValidationSpec> validations = spec.getValidations();
		for (int i = 0; i < validations.size(); i++) {
			appendRule(out, validations.get(i), i == validations.size() - 1);
		}
		out.append(indent(1)).append("]\n");
		out.append("}\n");
		return out.toString();
	}

	public static String validationBlockDefinitionsJson() {
		return """
			[
			  {
			    "type": "validation_rule",
			    "message0": "rule %1",
			    "args0": [{"type": "field_input", "name": "NAME", "text": "validation"}],
			    "message1": "target %1 type %2",
			    "args1": [
			      {"type": "field_input", "name": "TARGET", "text": "-"},
			      {"type": "field_input", "name": "VALIDATION_TYPE", "text": "-"}
			    ],
			    "message2": "message %1",
			    "args2": [{"type": "field_input", "name": "MESSAGE", "text": ""}],
			    "message3": "condition %1",
			    "args3": [{"type": "input_value", "name": "CONDITION", "check": "Boolean"}],
			    "colour": 20,
			    "tooltip": "Generated validation rule",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_required_field",
			    "message0": "%1.%2 is required",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "FIELD", "text": "field"}
			    ],
			    "message1": "kind %1",
			    "args1": [{"type": "field_input", "name": "FIELD_KIND", "text": "field"}],
			    "output": "Boolean",
			    "colour": 145,
			    "tooltip": "Checks that a required field or reference has a value",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_statement_cardinality",
			    "message0": "%1.%2 children count",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "INPUT", "text": "children"}
			    ],
			    "message1": "min %1 max %2",
			    "args1": [
			      {"type": "field_input", "name": "MIN", "text": "0"},
			      {"type": "field_input", "name": "MAX", "text": "unbounded"}
			    ],
			    "output": "Boolean",
			    "colour": 260,
			    "tooltip": "Checks the number of child blocks in a statement input",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_cardinality",
			    "message0": "%1.%2 value count",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "FIELD", "text": "field"}
			    ],
			    "message1": "kind %1 min %2 max %3",
			    "args1": [
			      {"type": "field_input", "name": "FIELD_KIND", "text": "field"},
			      {"type": "field_input", "name": "MIN", "text": "0"},
			      {"type": "field_input", "name": "MAX", "text": "unbounded"}
			    ],
			    "output": "Boolean",
			    "colour": 260,
			    "tooltip": "Checks the number of values in a multi-valued field or reference",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_expression_rule",
			    "message0": "%1 expression %2",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "EXPRESSION", "text": "true"}
			    ],
			    "message1": "expanded %1",
			    "args1": [{"type": "input_value", "name": "CONDITION", "check": "Boolean"}],
			    "output": "Boolean",
			    "colour": 0,
			    "tooltip": "Semantic expression validation, optionally expanded into lower-level blocks",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_exists",
			    "message0": "field %1 exists",
			    "args0": [{"type": "field_input", "name": "FIELD", "text": "field"}],
			    "output": "Boolean",
			    "colour": 145,
			    "tooltip": "Checks whether a field or reference has a value",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_value",
			    "message0": "field %1 text",
			    "args0": [{"type": "field_input", "name": "FIELD", "text": "field"}],
			    "output": "String",
			    "colour": 160,
			    "tooltip": "Reads a field value as text",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_number",
			    "message0": "field %1 number",
			    "args0": [{"type": "field_input", "name": "FIELD", "text": "field"}],
			    "output": "Number",
			    "colour": 230,
			    "tooltip": "Reads a field value as a number",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_count",
			    "message0": "field %1 count",
			    "args0": [{"type": "field_input", "name": "FIELD", "text": "field"}],
			    "output": "Number",
			    "colour": 260,
			    "tooltip": "Counts values in a multi-valued field or reference",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_field_unique",
			    "message0": "%1.%2 values unique",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "FIELD", "text": "field"}
			    ],
			    "message1": "kind %1",
			    "args1": [{"type": "field_input", "name": "FIELD_KIND", "text": "field"}],
			    "output": "Boolean",
			    "colour": 145,
			    "tooltip": "Checks that values inside a multi-valued field or reference are unique",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_type_field_unique",
			    "message0": "all %1 field %2 unique",
			    "args0": [
			      {"type": "field_input", "name": "TYPE", "text": "Type"},
			      {"type": "field_input", "name": "FIELD", "text": "field"}
			    ],
			    "output": "Boolean",
			    "colour": 145,
			    "tooltip": "Checks that field values are unique across all blocks of a type",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_input_count",
			    "message0": "input %1 count",
			    "args0": [{"type": "field_input", "name": "INPUT", "text": "children"}],
			    "output": "Number",
			    "colour": 260,
			    "tooltip": "Counts child blocks in a statement input",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_previous_block_is",
			    "message0": "%1 previous block is %2",
			    "args0": [
			      {"type": "field_input", "name": "TARGET", "text": "target"},
			      {"type": "field_input", "name": "TYPE", "text": "Type"}
			    ],
			    "output": "Boolean",
			    "colour": 35,
			    "tooltip": "Checks the previous connected block type",
			    "helpUrl": ""
			  },
			  {
			    "type": "validation_custom",
			    "message0": "custom check %1",
			    "args0": [{"type": "field_input", "name": "VALUE", "text": ""}],
			    "output": "Boolean",
			    "colour": 0,
			    "tooltip": "Custom validation check",
			    "helpUrl": ""
			  }
			]
			""".stripIndent().trim();
	}

	private static void appendCustomBlockTypes(StringBuilder out) {
		out.append("[\n");
		String[][] blocks = {
			{"validation_rule", "Validation rule wrapper", "wrapper", ""},
			{"validation_required_field", "Validation rule: required field/reference", "requiredField", "has(\"${FIELD}\")"},
			{"validation_statement_cardinality", "Validation rule: statement input cardinality", "statementCardinality", "inputCount(\"${INPUT}\") within ${MIN}..${MAX}"},
			{"validation_field_cardinality", "Validation rule: multi-valued field/reference cardinality", "fieldCardinality", "fieldCount(\"${FIELD}\") within ${MIN}..${MAX}"},
			{"validation_expression_rule", "Validation rule: custom semantic expression", "semanticExpression", "${EXPRESSION}"},
			{"validation_field_exists", "Domain accessor: field/reference has a value", "expressionNode", "has(\"${FIELD}\")"},
			{"validation_field_value", "Domain accessor: field/reference value as text", "expressionNode", "value(\"${FIELD}\")"},
			{"validation_field_number", "Domain accessor: field value as number", "expressionNode", "number(\"${FIELD}\")"},
			{"validation_field_count", "Domain accessor: multi-valued field/reference count", "expressionNode", "fieldCount(\"${FIELD}\")"},
			{"validation_field_unique", "Domain accessor: multi-valued field/reference values are unique", "fieldUnique", "fieldUnique(\"${FIELD}\")"},
			{"validation_type_field_unique", "Domain accessor: field values are unique across a block type", "typeFieldUnique", "typeFieldUnique(\"${TYPE}\", \"${FIELD}\")"},
			{"validation_input_count", "Domain accessor: statement input child count", "expressionNode", "inputCount(\"${INPUT}\")"},
			{"validation_previous_block_is", "Domain accessor: previous block type", "previousBlockIs", "previousBlockIs(\"${TYPE}\")"}
		};
		for (int i = 0; i < blocks.length; i++) {
			out.append(indent(2)).append("{ ");
			inlineProperty(out, "type", blocks[i][0], true);
			inlineProperty(out, "label", blocks[i][1], true);
			out.append("\"generator\": { ");
			inlineProperty(out, "format", "model2blockly.validationGenerator.v1", true);
			inlineProperty(out, "runtimeCheck", blocks[i][2], true);
			inlineProperty(out, "expressionTemplate", blocks[i][3], false);
			out.append(" }");
			out.append(" }");
			if (i < blocks.length - 1) out.append(",");
			out.append("\n");
		}
		out.append(indent(1)).append("]");
	}

	private static void appendRule(StringBuilder out, ValidationSpec validation, boolean last) {
		out.append(indent(2)).append("{\n");
		property(out, 3, "name", defaultString(validation.getName(), "validation"), true);
		property(out, 3, "validationType", validation.getType() != null ? validation.getType().name() : "", true);
		property(out, 3, "targetType", targetType(validation), true);
		property(out, 3, "message", defaultString(validation.getMessage(), defaultMessage(validation)), true);
		out.append(indent(3)).append("\"blockTree\": ");
		appendRuleBlock(out, validation);
		if (validation.getVisualBlock() != null && validation.getVisualExpression() != null) {
			out.append(",\n");
			out.append(indent(3)).append("\"expandedExpression\": ");
			appendExpression(out, validation.getVisualExpression(), 3);
		}
		out.append(",\n");
		out.append(indent(3)).append("\"generator\": ");
		appendRuleGenerator(out, validation, 3);
		if (validation.getExpression() != null && !validation.getExpression().isBlank()) {
			out.append(",\n");
			property(out, 3, "runtimeExpression", validation.getExpression().trim(), false);
		} else {
			out.append("\n");
		}
		out.append(indent(2)).append("}");
		if (!last) out.append(",");
		out.append("\n");
	}

	private static void appendRuleGenerator(StringBuilder out, ValidationSpec validation, int level) {
		out.append("{\n");
		property(out, level + 1, "format", "model2blockly.validationGenerator.v1", true);
		property(out, level + 1, "source", generatorSource(validation), true);
		property(out, level + 1, "blockType", generatorBlockType(validation), true);
		property(out, level + 1, "runtimeCheck", runtimeCheck(validation), true);
		property(out, level + 1, "runtimeExpression", generatedRuntimeExpression(validation), true);
		out.append(indent(level + 1)).append("\"parameters\": ");
		appendStringMap(out, generatorParameters(validation), level + 1);
		out.append("\n");
		out.append(indent(level)).append("}");
	}

	private static String generatorSource(ValidationSpec validation) {
		if (validation.getVisualBlock() != null) return "visualBlock";
		if (validation.getVisualExpression() != null) return "visualExpression";
		return "validationSpec";
	}

	private static String generatorBlockType(ValidationSpec validation) {
		if (validation.getVisualBlock() != null) return defaultString(validation.getVisualBlock().getBlocklyBlockType(), "");
		return visualExpressionBlockType(validation.getVisualExpression());
	}

	private static String runtimeCheck(ValidationSpec validation) {
		if (validation.getVisualBlock() != null) return runtimeCheckForBlock(validation.getVisualBlock().getBlocklyBlockType());
		if (validation.getType() == null) return "custom";
		switch (validation.getType()) {
			case REQUIRED: return "requiredField";
			case CARDINALITY: return "statementCardinality";
			case FIELD_CARDINALITY: return "fieldCardinality";
			case UNIQUE: return "unique";
			case MUST_FOLLOW: return "previousBlockIs";
			case EXPRESSION: return "semanticExpression";
			default: return "custom";
		}
	}

	private static String runtimeCheckForBlock(String blockType) {
		if ("validation_required_field".equals(blockType)) return "requiredField";
		if ("validation_statement_cardinality".equals(blockType)) return "statementCardinality";
		if ("validation_field_cardinality".equals(blockType)) return "fieldCardinality";
		if ("validation_field_unique".equals(blockType)) return "fieldUnique";
		if ("validation_type_field_unique".equals(blockType)) return "typeFieldUnique";
		if ("validation_previous_block_is".equals(blockType)) return "previousBlockIs";
		if ("validation_expression_rule".equals(blockType)) return "semanticExpression";
		return "custom";
	}

	private static Map<String, String> generatorParameters(ValidationSpec validation) {
		if (validation.getVisualBlock() != null) return BlocklySpecModelQueries.fields(validation.getVisualBlock());
		Map<String, String> parameters = new LinkedHashMap<>();
		if (validation.getOwnerType() != null) parameters.put("TYPE", validation.getOwnerType());
		if (validation.getTargetType() != null) parameters.put("TARGET", validation.getTargetType());
		if (validation.getPredecessorType() != null) parameters.put("PREDECESSOR", validation.getPredecessorType());
		if (validation.getFieldName() != null) parameters.put("FIELD", validation.getFieldName());
		if (validation.getFieldKind() != null) parameters.put("FIELD_KIND", validation.getFieldKind());
		if (validation.getContainmentName() != null) parameters.put("INPUT", validation.getContainmentName());
		if (validation.getLowerBound() != 0) parameters.put("MIN", Integer.toString(validation.getLowerBound()));
		if (validation.getUpperBound() != 0) parameters.put("MAX", Integer.toString(validation.getUpperBound()));
		if (validation.getExpression() != null) parameters.put("EXPRESSION", validation.getExpression());
		return parameters;
	}

	private static String generatedRuntimeExpression(ValidationSpec validation) {
		if (validation.getExpression() != null && !validation.getExpression().isBlank()) return validation.getExpression().trim();
		if (validation.getVisualBlock() != null) return expressionForVisualBlock(validation.getVisualBlock());
		if (validation.getVisualExpression() != null) return expressionForVisualExpression(validation.getVisualExpression());
		return expressionForValidationSpec(validation);
	}

	private static String expressionForValidationSpec(ValidationSpec validation) {
		if (validation.getType() == null) return "";
		switch (validation.getType()) {
			case REQUIRED:
				return "has(" + quoted(validation.getFieldName()) + ")";
			case CARDINALITY:
				return rangeExpression("inputCount(" + quoted(validation.getContainmentName()) + ")",
					Integer.toString(validation.getLowerBound()), Integer.toString(validation.getUpperBound()));
			case FIELD_CARDINALITY:
				return rangeExpression("fieldCount(" + quoted(validation.getFieldName()) + ")",
					Integer.toString(validation.getLowerBound()), Integer.toString(validation.getUpperBound()));
			case UNIQUE:
				if ("id".equals(validation.getFieldKind())) {
					return "typeFieldUnique(" + quoted(validation.getOwnerType()) + ", " + quoted(validation.getFieldName()) + ")";
				}
				return "fieldUnique(" + quoted(validation.getFieldName()) + ")";
			case MUST_FOLLOW:
				return "previousBlockIs(" + quoted(validation.getPredecessorType()) + ")";
			case EXPRESSION:
				return defaultString(validation.getExpression(), "");
			default:
				return "";
		}
	}

	private static String expressionForVisualBlock(ValidationBlockSpec block) {
		String type = block.getBlocklyBlockType();
		Map<String, String> fields = BlocklySpecModelQueries.fields(block);
		if ("validation_required_field".equals(type)) return "has(" + quoted(fields.get("FIELD")) + ")";
		if ("validation_statement_cardinality".equals(type)) {
			return rangeExpression("inputCount(" + quoted(fields.get("INPUT")) + ")", fields.get("MIN"), fields.get("MAX"));
		}
		if ("validation_field_cardinality".equals(type)) {
			return rangeExpression("fieldCount(" + quoted(fields.get("FIELD")) + ")", fields.get("MIN"), fields.get("MAX"));
		}
		if ("validation_field_unique".equals(type)) return "fieldUnique(" + quoted(fields.get("FIELD")) + ")";
		if ("validation_type_field_unique".equals(type)) {
			return "typeFieldUnique(" + quoted(fields.get("TYPE")) + ", " + quoted(fields.get("FIELD")) + ")";
		}
		if ("validation_previous_block_is".equals(type)) return "previousBlockIs(" + quoted(fields.get("TYPE")) + ")";
		if ("validation_expression_rule".equals(type)) return defaultString(fields.get("EXPRESSION"), "");
		return "";
	}

	private static String rangeExpression(String accessor, String min, String max) {
		String lower = isPositiveBound(min) ? accessor + " >= " + min : "";
		String upper = isFiniteBound(max) ? accessor + " <= " + max : "";
		if (!lower.isEmpty() && !upper.isEmpty()) return lower + " && " + upper;
		if (!lower.isEmpty()) return lower;
		if (!upper.isEmpty()) return upper;
		return "true";
	}

	private static boolean isPositiveBound(String value) {
		try {
			return Integer.parseInt(defaultString(value, "0")) > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isFiniteBound(String value) {
		if (value == null || value.isBlank() || "unbounded".equalsIgnoreCase(value)) return false;
		try {
			return Integer.parseInt(value) > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static String expressionForVisualExpression(ValidationExpressionSpec expression) {
		if (expression == null || expression.getKind() == null) return "";
		switch (expression.getKind()) {
			case LOGIC_OPERATION: {
				String operator = "OR".equalsIgnoreCase(expression.getOperator()) ? " || " : " && ";
				StringBuilder out = new StringBuilder();
				for (ValidationExpressionSpec child : expression.getChildren()) {
					if (out.length() > 0) out.append(operator);
					out.append("(").append(expressionForVisualExpression(child)).append(")");
				}
				return out.length() == 0 ? "true" : out.toString();
			}
			case LOGIC_NEGATE:
				return "!(" + expressionForVisualExpression(child(expression, 0)) + ")";
			case LOGIC_COMPARE:
				return expressionForVisualExpression(child(expression, 0)) + " "
					+ runtimeCompareOperator(expression.getOperator()) + " "
					+ expressionForVisualExpression(child(expression, 1));
			case NUMBER_LITERAL:
				return defaultString(expression.getLiteral(), "0");
			case STRING_LITERAL:
				return quoted(expression.getLiteral());
			case BOOLEAN_LITERAL:
				return Boolean.parseBoolean(expression.getLiteral()) ? "true" : "false";
			case FIELD_VALUE:
				return "value(" + quoted(expression.getFieldName()) + ")";
			case FIELD_NUMBER:
				return "number(" + quoted(expression.getFieldName()) + ")";
			case FIELD_EXISTS:
				return "has(" + quoted(expression.getFieldName()) + ")";
			case FIELD_COUNT:
				return "fieldCount(" + quoted(expression.getFieldName()) + ")";
			case FIELD_UNIQUE:
				return "fieldUnique(" + quoted(expression.getFieldName()) + ")";
			case TYPE_FIELD_UNIQUE:
				return "typeFieldUnique(" + quoted(expression.getTypeName()) + ", " + quoted(expression.getFieldName()) + ")";
			case INPUT_COUNT:
				return "inputCount(" + quoted(expression.getFieldName()) + ")";
			case PREVIOUS_BLOCK_IS:
				return "previousBlockIs(" + quoted(expression.getTypeName()) + ")";
			case CUSTOM:
			default:
				return defaultString(expression.getLiteral(), "");
		}
	}

	private static String runtimeCompareOperator(String operator) {
		if ("NEQ".equals(operator)) return "!==";
		if ("GTE".equals(operator)) return ">=";
		if ("LTE".equals(operator)) return "<=";
		if ("GT".equals(operator)) return ">";
		if ("LT".equals(operator)) return "<";
		return "===";
	}

	private static void appendRuleBlock(StringBuilder out, ValidationSpec validation) {
		out.append("{\n");
		property(out, 4, "type", "validation_rule", true);
		out.append(indent(4)).append("\"fields\": {\n");
		property(out, 5, "NAME", defaultString(validation.getName(), "validation"), true);
		property(out, 5, "TARGET", targetType(validation), true);
		property(out, 5, "VALIDATION_TYPE", validation.getType() != null ? validation.getType().name() : "", true);
		property(out, 5, "MESSAGE", defaultString(validation.getMessage(), defaultMessage(validation)), false);
		out.append(indent(4)).append("},\n");
		out.append(indent(4)).append("\"inputs\": {\n");
		out.append(indent(5)).append("\"CONDITION\": ");
		if (validation.getVisualBlock() != null) {
			appendVisualBlock(out, validation.getVisualBlock(), 5);
		} else if (validation.getVisualExpression() != null) {
			appendExpression(out, validation.getVisualExpression(), 5);
		} else {
			out.append("null");
		}
		out.append("\n");
		out.append(indent(4)).append("}\n");
		out.append(indent(3)).append("}");
	}

	private static void appendVisualBlock(StringBuilder out, ValidationBlockSpec block, int level) {
		if (block == null) {
			out.append("null");
			return;
		}
		appendBlockStart(out, defaultString(block.getBlocklyBlockType(), "validation_custom"), level);
		Map<String, String> fields = BlocklySpecModelQueries.fields(block);
		Map<String, ValidationExpressionSpec> inputs = BlocklySpecModelQueries.inputs(block);
		if (!fields.isEmpty()) {
			out.append(indent(level + 1)).append("\"fields\": {\n");
			int index = 0;
			for (Map.Entry<String, String> entry : fields.entrySet()) {
				property(out, level + 2, entry.getKey(), entry.getValue(), index < fields.size() - 1);
				index++;
			}
			out.append(indent(level + 1)).append("}");
			if (!inputs.isEmpty()) out.append(",");
			out.append("\n");
		}
		if (!inputs.isEmpty()) {
			out.append(indent(level + 1)).append("\"inputs\": {\n");
			int index = 0;
			for (Map.Entry<String, ValidationExpressionSpec> entry : inputs.entrySet()) {
				out.append(indent(level + 2)).append("\"").append(json(entry.getKey())).append("\": ");
				appendExpression(out, entry.getValue(), level + 2);
				if (index < inputs.size() - 1) out.append(",");
				out.append("\n");
				index++;
			}
			out.append(indent(level + 1)).append("}\n");
		}
		out.append(indent(level)).append("}");
	}

	private static void appendExpression(StringBuilder out, ValidationExpressionSpec expression, int level) {
		if (expression == null) {
			out.append("null");
			return;
		}
		switch (expression.getKind()) {
			case LOGIC_OPERATION:
				appendLogicOperation(out, expression, level);
				return;
			case LOGIC_NEGATE:
				appendUnaryInputBlock(out, "logic_negate", "BOOL", expression, level);
				return;
			case LOGIC_COMPARE:
				appendBinaryInputBlock(out, "logic_compare", "A", "B", expression, level);
				return;
			case NUMBER_LITERAL:
				appendFieldBlock(out, "math_number", "NUM", expression.getLiteral(), level);
				return;
			case STRING_LITERAL:
				appendFieldBlock(out, "text", "TEXT", expression.getLiteral(), level);
				return;
			case BOOLEAN_LITERAL:
				appendFieldBlock(out, "logic_boolean", "BOOL",
					Boolean.parseBoolean(expression.getLiteral()) ? "TRUE" : "FALSE", level);
				return;
			case FIELD_VALUE:
				appendFieldBlock(out, "validation_field_value", "FIELD", expression.getFieldName(), level);
				return;
			case FIELD_NUMBER:
				appendFieldBlock(out, "validation_field_number", "FIELD", expression.getFieldName(), level);
				return;
			case FIELD_EXISTS:
				appendFieldBlock(out, "validation_field_exists", "FIELD", expression.getFieldName(), level);
				return;
			case FIELD_COUNT:
				appendFieldBlock(out, "validation_field_count", "FIELD", expression.getFieldName(), level);
				return;
			case FIELD_UNIQUE:
				appendFieldBlock(out, "validation_field_unique", "FIELD", expression.getFieldName(), level);
				return;
			case TYPE_FIELD_UNIQUE:
				appendTypeFieldBlock(out, "validation_type_field_unique", expression.getTypeName(), expression.getFieldName(), level);
				return;
			case INPUT_COUNT:
				appendFieldBlock(out, "validation_input_count", "INPUT", expression.getFieldName(), level);
				return;
			case PREVIOUS_BLOCK_IS:
				appendFieldBlock(out, "validation_previous_block_is", "TYPE", expression.getTypeName(), level);
				return;
			case CUSTOM:
			default:
				appendFieldBlock(out, defaultString(expression.getBlocklyBlockType(), "validation_custom"), "VALUE",
					defaultString(expression.getLiteral(), ""), level);
		}
	}

	private static void appendLogicOperation(StringBuilder out, ValidationExpressionSpec expression, int level) {
		List<ValidationExpressionSpec> children = expression.getChildren();
		if (children.isEmpty()) {
			appendFieldBlock(out, "logic_boolean", "BOOL", "TRUE", level);
			return;
		}
		if (children.size() == 1) {
			appendExpression(out, children.get(0), level);
			return;
		}
		appendBlockStart(out, "logic_operation", level);
		out.append(indent(level + 1)).append("\"fields\": { ");
		inlineProperty(out, "OP", defaultString(expression.getOperator(), "AND"), false);
		out.append(" },\n");
		out.append(indent(level + 1)).append("\"inputs\": {\n");
		out.append(indent(level + 2)).append("\"A\": ");
		appendExpression(out, children.get(0), level + 2);
		out.append(",\n");
		out.append(indent(level + 2)).append("\"B\": ");
		if (children.size() == 2) {
			appendExpression(out, children.get(1), level + 2);
		} else {
			ValidationExpressionSpec nested = BlocklySpecModelQueries.logic(expression.getOperator(),
				children.subList(1, children.size()));
			appendExpression(out, nested, level + 2);
		}
		out.append("\n");
		out.append(indent(level + 1)).append("}\n");
		out.append(indent(level)).append("}");
	}

	private static void appendBinaryInputBlock(StringBuilder out, String type, String firstName, String secondName,
			ValidationExpressionSpec expression, int level) {
		appendBlockStart(out, type, level);
		out.append(indent(level + 1)).append("\"fields\": { ");
		inlineProperty(out, "OP", defaultString(expression.getOperator(), "EQ"), false);
		out.append(" },\n");
		out.append(indent(level + 1)).append("\"inputs\": {\n");
		out.append(indent(level + 2)).append("\"").append(firstName).append("\": ");
		appendExpression(out, child(expression, 0), level + 2);
		out.append(",\n");
		out.append(indent(level + 2)).append("\"").append(secondName).append("\": ");
		appendExpression(out, child(expression, 1), level + 2);
		out.append("\n");
		out.append(indent(level + 1)).append("}\n");
		out.append(indent(level)).append("}");
	}

	private static void appendUnaryInputBlock(StringBuilder out, String type, String inputName,
			ValidationExpressionSpec expression, int level) {
		appendBlockStart(out, type, level);
		out.append(indent(level + 1)).append("\"inputs\": {\n");
		out.append(indent(level + 2)).append("\"").append(inputName).append("\": ");
		appendExpression(out, child(expression, 0), level + 2);
		out.append("\n");
		out.append(indent(level + 1)).append("}\n");
		out.append(indent(level)).append("}");
	}

	private static void appendFieldBlock(StringBuilder out, String type, String fieldName, String value, int level) {
		appendBlockStart(out, type, level);
		out.append(indent(level + 1)).append("\"fields\": { ");
		inlineProperty(out, fieldName, defaultString(value, ""), false);
		out.append(" }\n");
		out.append(indent(level)).append("}");
	}

	private static void appendTypeFieldBlock(StringBuilder out, String type, String typeName, String fieldName, int level) {
		appendBlockStart(out, type, level);
		out.append(indent(level + 1)).append("\"fields\": { ");
		inlineProperty(out, "TYPE", defaultString(typeName, ""), true);
		inlineProperty(out, "FIELD", defaultString(fieldName, ""), false);
		out.append(" }\n");
		out.append(indent(level)).append("}");
	}

	private static void appendBlockStart(StringBuilder out, String type, int level) {
		out.append("{\n");
		property(out, level + 1, "type", type, true);
	}

	private static ValidationExpressionSpec child(ValidationExpressionSpec expression, int index) {
		return expression != null && expression.getChildren().size() > index ? expression.getChildren().get(index) : null;
	}

	private static String targetType(ValidationSpec validation) {
		return defaultString(validation.getOwnerType(), validation.getTargetType());
	}

	private static String visualExpressionBlockType(ValidationExpressionSpec expression) {
		if (expression == null || expression.getKind() == null) return "";
		switch (expression.getKind()) {
			case LOGIC_OPERATION: return "logic_operation";
			case LOGIC_NEGATE: return "logic_negate";
			case LOGIC_COMPARE: return "logic_compare";
			case NUMBER_LITERAL: return "math_number";
			case STRING_LITERAL: return "text";
			case BOOLEAN_LITERAL: return "logic_boolean";
			case FIELD_VALUE: return "validation_field_value";
			case FIELD_NUMBER: return "validation_field_number";
			case FIELD_EXISTS: return "validation_field_exists";
			case FIELD_COUNT: return "validation_field_count";
			case FIELD_UNIQUE: return "validation_field_unique";
			case TYPE_FIELD_UNIQUE: return "validation_type_field_unique";
			case INPUT_COUNT: return "validation_input_count";
			case PREVIOUS_BLOCK_IS: return "validation_previous_block_is";
			case CUSTOM:
			default:
				return defaultString(expression.getBlocklyBlockType(), "validation_custom");
		}
	}

	private static String defaultMessage(ValidationSpec validation) {
		if (validation.getType() == null) return "";
		switch (validation.getType()) {
			case REQUIRED:
				return targetType(validation) + "." + defaultString(validation.getFieldName(), "") + " is required.";
			case CARDINALITY:
				return targetType(validation) + "." + defaultString(validation.getContainmentName(), "") + " has invalid cardinality.";
			case MUST_FOLLOW:
				return defaultString(validation.getTargetType(), "") + " must follow " + defaultString(validation.getPredecessorType(), "") + ".";
			default:
				return validation.getType().name();
		}
	}

	private static void property(StringBuilder out, int level, String name, String value, boolean comma) {
		out.append(indent(level)).append("\"").append(json(name)).append("\": \"").append(json(value)).append("\"");
		if (comma) out.append(",");
		out.append("\n");
	}

	private static void inlineProperty(StringBuilder out, String name, String value, boolean comma) {
		out.append("\"").append(json(name)).append("\": \"").append(json(value)).append("\"");
		if (comma) out.append(", ");
	}

	private static void appendStringMap(StringBuilder out, Map<String, String> values, int level) {
		if (values == null || values.isEmpty()) {
			out.append("{}");
			return;
		}
		out.append("{\n");
		int index = 0;
		for (Map.Entry<String, String> entry : values.entrySet()) {
			property(out, level + 1, entry.getKey(), entry.getValue(), index < values.size() - 1);
			index++;
		}
		out.append(indent(level)).append("}");
	}

	private static void appendIndentedJson(StringBuilder out, String json, int level) {
		String prefix = indent(level);
		String[] lines = defaultString(json, "[]").split("\\R", -1);
		for (int i = 0; i < lines.length; i++) {
			if (i > 0) out.append("\n").append(prefix);
			out.append(lines[i]);
		}
	}

	private static String quoted(String value) {
		return "\"" + expressionString(value) + "\"";
	}

	private static String expressionString(String value) {
		if (value == null) return "";
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '\\': out.append("\\\\"); break;
				case '"': out.append("\\\""); break;
				case '\n': out.append("\\n"); break;
				case '\r': out.append("\\r"); break;
				case '\t': out.append("\\t"); break;
				default: out.append(c);
			}
		}
		return out.toString();
	}

	private static String indent(int level) {
		return "  ".repeat(Math.max(0, level));
	}

	private static String defaultString(String value, String fallback) {
		return value == null || value.isBlank() ? fallback : value;
	}

	private static String json(String value) {
		if (value == null) return "";
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '\\': out.append("\\\\"); break;
				case '"': out.append("\\\""); break;
				case '\n': out.append("\\n"); break;
				case '\r': out.append("\\r"); break;
				case '\t': out.append("\\t"); break;
				default:
					if (c < 0x20) out.append(String.format("\\u%04x", (int)c));
					else out.append(c);
			}
		}
		return out.toString();
	}
}
