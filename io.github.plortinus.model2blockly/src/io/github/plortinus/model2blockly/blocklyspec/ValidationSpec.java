package io.github.plortinus.model2blockly.blocklyspec;

public class ValidationSpec {

	public enum ValidationType { MUST_FOLLOW, CARDINALITY, REQUIRED, FIELD_CARDINALITY, UNIQUE, EXPRESSION }

	private ValidationType type;
	private String name;

	// MUST_FOLLOW fields
	private String targetType;
	private String predecessorType;

	// CARDINALITY fields
	private String ownerType;
	private String containmentName;
	private int lowerBound;
	private int upperBound;

	// REQUIRED fields — auto-inferred from EAttribute.lowerBound >= 1
	// or EReference(non-containment).lowerBound >= 1
	private String fieldName;
	private String fieldKind; // "attribute" or "reference"

	// EXPRESSION fields. The generated JavaScript runtime evaluates the
	// expression with helper functions: value(name), number(name), size(name),
	// has(name) and includes(name, item).
	private String expression;
	private String message;
	private String diagnosticMessage;
	private ValidationBlockSpec visualBlock;
	private ValidationExpressionSpec visualExpression;

	public ValidationType getType() { return type; }
	public void setType(ValidationType type) { this.type = type; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getTargetType() { return targetType; }
	public void setTargetType(String targetType) { this.targetType = targetType; }

	public String getPredecessorType() { return predecessorType; }
	public void setPredecessorType(String predecessorType) { this.predecessorType = predecessorType; }

	public String getOwnerType() { return ownerType; }
	public void setOwnerType(String ownerType) { this.ownerType = ownerType; }

	public String getContainmentName() { return containmentName; }
	public void setContainmentName(String containmentName) { this.containmentName = containmentName; }

	public int getLowerBound() { return lowerBound; }
	public void setLowerBound(int lowerBound) { this.lowerBound = lowerBound; }

	public int getUpperBound() { return upperBound; }
	public void setUpperBound(int upperBound) { this.upperBound = upperBound; }

	public String getFieldName() { return fieldName; }
	public void setFieldName(String fieldName) { this.fieldName = fieldName; }

	public String getFieldKind() { return fieldKind; }
	public void setFieldKind(String fieldKind) { this.fieldKind = fieldKind; }

	public String getExpression() { return expression; }
	public void setExpression(String expression) { this.expression = expression; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	public String getDiagnosticMessage() { return diagnosticMessage; }
	public void setDiagnosticMessage(String diagnosticMessage) {
		this.diagnosticMessage = diagnosticMessage;
	}

	public ValidationBlockSpec getVisualBlock() { return visualBlock; }
	public void setVisualBlock(ValidationBlockSpec visualBlock) {
		this.visualBlock = visualBlock;
	}

	public ValidationExpressionSpec getVisualExpression() { return visualExpression; }
	public void setVisualExpression(ValidationExpressionSpec visualExpression) {
		this.visualExpression = visualExpression;
	}
}
