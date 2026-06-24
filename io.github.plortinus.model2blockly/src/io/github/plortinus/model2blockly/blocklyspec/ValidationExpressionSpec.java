package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Intermediate representation for validation logic that can later be rendered
 * as Blockly validation blocks and compiled to JavaScript.
 */
public class ValidationExpressionSpec {

	public enum Kind {
		LOGIC_OPERATION,
		LOGIC_NEGATE,
		LOGIC_COMPARE,
		NUMBER_LITERAL,
		STRING_LITERAL,
		BOOLEAN_LITERAL,
		FIELD_VALUE,
		FIELD_NUMBER,
		FIELD_EXISTS,
		FIELD_COUNT,
		FIELD_UNIQUE,
		TYPE_FIELD_UNIQUE,
		INPUT_COUNT,
		PREVIOUS_BLOCK_IS,
		CUSTOM
	}

	private Kind kind;
	private String operator;
	private String fieldName;
	private String typeName;
	private String literal;
	private String blocklyBlockType;
	private List<ValidationExpressionSpec> children = new ArrayList<>();

	public static ValidationExpressionSpec logic(String operator, ValidationExpressionSpec... children) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.LOGIC_OPERATION);
		expression.setOperator(operator);
		expression.getChildren().addAll(Arrays.asList(children));
		return expression;
	}

	public static ValidationExpressionSpec not(ValidationExpressionSpec child) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.LOGIC_NEGATE);
		expression.getChildren().add(child);
		return expression;
	}

	public static ValidationExpressionSpec compare(String operator, ValidationExpressionSpec left, ValidationExpressionSpec right) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.LOGIC_COMPARE);
		expression.setOperator(operator);
		expression.getChildren().add(left);
		expression.getChildren().add(right);
		return expression;
	}

	public static ValidationExpressionSpec number(String literal) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.NUMBER_LITERAL);
		expression.setLiteral(literal);
		return expression;
	}

	public static ValidationExpressionSpec string(String literal) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.STRING_LITERAL);
		expression.setLiteral(literal);
		return expression;
	}

	public static ValidationExpressionSpec bool(boolean literal) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.BOOLEAN_LITERAL);
		expression.setLiteral(Boolean.toString(literal));
		return expression;
	}

	public static ValidationExpressionSpec fieldValue(String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.FIELD_VALUE);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec fieldNumber(String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.FIELD_NUMBER);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec fieldExists(String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.FIELD_EXISTS);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec fieldCount(String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.FIELD_COUNT);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec fieldUnique(String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.FIELD_UNIQUE);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec typeFieldUnique(String typeName, String fieldName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.TYPE_FIELD_UNIQUE);
		expression.setTypeName(typeName);
		expression.setFieldName(fieldName);
		return expression;
	}

	public static ValidationExpressionSpec inputCount(String inputName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.INPUT_COUNT);
		expression.setFieldName(inputName);
		return expression;
	}

	public static ValidationExpressionSpec previousBlockIs(String typeName) {
		ValidationExpressionSpec expression = new ValidationExpressionSpec();
		expression.setKind(Kind.PREVIOUS_BLOCK_IS);
		expression.setTypeName(typeName);
		return expression;
	}

	public Kind getKind() { return kind; }
	public void setKind(Kind kind) { this.kind = kind; }

	public String getOperator() { return operator; }
	public void setOperator(String operator) { this.operator = operator; }

	public String getFieldName() { return fieldName; }
	public void setFieldName(String fieldName) { this.fieldName = fieldName; }

	public String getTypeName() { return typeName; }
	public void setTypeName(String typeName) { this.typeName = typeName; }

	public String getLiteral() { return literal; }
	public void setLiteral(String literal) { this.literal = literal; }

	public String getBlocklyBlockType() { return blocklyBlockType; }
	public void setBlocklyBlockType(String blocklyBlockType) { this.blocklyBlockType = blocklyBlockType; }

	public List<ValidationExpressionSpec> getChildren() { return children; }
	public void setChildren(List<ValidationExpressionSpec> children) {
		this.children = children != null ? children : new ArrayList<>();
	}
}
