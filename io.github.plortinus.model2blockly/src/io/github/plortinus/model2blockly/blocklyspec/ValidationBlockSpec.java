package io.github.plortinus.model2blockly.blocklyspec;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * High-level Blockly block chosen to present a validation rule.
 */
public class ValidationBlockSpec {

	private String blocklyBlockType;
	private Map<String, String> fields = new LinkedHashMap<>();
	private Map<String, ValidationExpressionSpec> inputs = new LinkedHashMap<>();

	public static ValidationBlockSpec block(String blocklyBlockType) {
		ValidationBlockSpec block = new ValidationBlockSpec();
		block.setBlocklyBlockType(blocklyBlockType);
		return block;
	}

	public static ValidationBlockSpec requiredField(String ownerType, String fieldName, String fieldKind) {
		return block("validation_required_field")
			.field("TYPE", ownerType)
			.field("FIELD", fieldName)
			.field("FIELD_KIND", fieldKind);
	}

	public static ValidationBlockSpec statementCardinality(String ownerType, String inputName, int lowerBound, int upperBound) {
		return block("validation_statement_cardinality")
			.field("TYPE", ownerType)
			.field("INPUT", inputName)
			.field("MIN", Integer.toString(lowerBound))
			.field("MAX", upperBound == 0 ? "unbounded" : Integer.toString(upperBound));
	}

	public static ValidationBlockSpec fieldCardinality(String ownerType, String fieldName,
			String fieldKind, int lowerBound, int upperBound) {
		return block("validation_field_cardinality")
			.field("TYPE", ownerType)
			.field("FIELD", fieldName)
			.field("FIELD_KIND", fieldKind)
			.field("MIN", Integer.toString(lowerBound))
			.field("MAX", upperBound == 0 ? "unbounded" : Integer.toString(upperBound));
	}

	public static ValidationBlockSpec fieldUnique(String ownerType, String fieldName, String fieldKind) {
		return block("validation_field_unique")
			.field("TYPE", ownerType)
			.field("FIELD", fieldName)
			.field("FIELD_KIND", fieldKind);
	}

	public static ValidationBlockSpec typeFieldUnique(String ownerType, String fieldName) {
		return block("validation_type_field_unique")
			.field("TYPE", ownerType)
			.field("FIELD", fieldName);
	}

	public static ValidationBlockSpec previousBlockIs(String targetType, String predecessorType) {
		return block("validation_previous_block_is")
			.field("TARGET", targetType)
			.field("TYPE", predecessorType);
	}

	public static ValidationBlockSpec expressionRule(String ownerType, String expression,
			ValidationExpressionSpec expandedExpression) {
		ValidationBlockSpec block = block("validation_expression_rule")
			.field("TYPE", ownerType)
			.field("EXPRESSION", expression);
		if (expandedExpression != null) {
			block.input("CONDITION", expandedExpression);
		}
		return block;
	}

	public ValidationBlockSpec field(String name, String value) {
		fields.put(name, value != null ? value : "");
		return this;
	}

	public ValidationBlockSpec input(String name, ValidationExpressionSpec expression) {
		inputs.put(name, expression);
		return this;
	}

	public String getBlocklyBlockType() { return blocklyBlockType; }
	public void setBlocklyBlockType(String blocklyBlockType) { this.blocklyBlockType = blocklyBlockType; }

	public Map<String, String> getFields() { return fields; }
	public void setFields(Map<String, String> fields) {
		this.fields = fields != null ? fields : new LinkedHashMap<>();
	}

	public Map<String, ValidationExpressionSpec> getInputs() { return inputs; }
	public void setInputs(Map<String, ValidationExpressionSpec> inputs) {
		this.inputs = inputs != null ? inputs : new LinkedHashMap<>();
	}
}
