package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.plortinus.model2blockly.intermediate.BlocklySpecModelMapper;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Structural checks for the platform-independent BlocklyEditorSpec.
 *
 * This validator protects the model-to-text generators from malformed
 * intermediate models. It deliberately checks generator-facing invariants,
 * not source-language style rules; DSL/Ecore-specific diagnostics stay in
 * their adapters and validators.
 */
public final class BlocklyEditorSpecValidator {

	public enum Severity { ERROR, WARNING }

	public static final class Issue {
		private final Severity severity;
		private final String location;
		private final String message;

		private Issue(Severity severity, String location, String message) {
			this.severity = severity;
			this.location = location;
			this.message = message;
		}

		public Severity getSeverity() { return severity; }
		public String getLocation() { return location; }
		public String getMessage() { return message; }

		@Override
		public String toString() {
			String prefix = severity + (isBlank(location) ? "" : " " + location);
			return prefix + ": " + message;
		}
	}

	public static final class Result {
		private final List<Issue> issues;

		private Result(List<Issue> issues) {
			this.issues = Collections.unmodifiableList(new ArrayList<>(issues));
		}

		public List<Issue> getIssues() { return issues; }

		public List<Issue> getErrors() {
			return issues.stream().filter(issue -> issue.getSeverity() == Severity.ERROR).toList();
		}

		public List<Issue> getWarnings() {
			return issues.stream().filter(issue -> issue.getSeverity() == Severity.WARNING).toList();
		}

		public boolean hasErrors() {
			return !getErrors().isEmpty();
		}

		public void throwIfErrors() {
			List<Issue> errors = getErrors();
			if (errors.isEmpty()) return;
			BlocklySpecDiagnostics.throwIfErrors(this, null, null);
		}
	}

	private final BlocklyEditorSpec spec;
	private final List<Issue> issues = new ArrayList<>();
	private final Map<String, BlockTypeSpec> blockByName = new LinkedHashMap<>();
	private final Set<String> categoryNames = new LinkedHashSet<>();

	private BlocklyEditorSpecValidator(BlocklyEditorSpec spec) {
		this.spec = spec;
	}

	public static Result validate(BlocklyEditorSpec spec) {
		return new BlocklyEditorSpecValidator(spec).validateInternal();
	}

	public static Result validate(EditorSpec spec) {
		return validate(BlocklySpecModelMapper.toJavaSpec(spec));
	}

	public static void assertValid(BlocklyEditorSpec spec) {
		validate(spec).throwIfErrors();
	}

	public static void assertValid(EditorSpec spec) {
		validate(spec).throwIfErrors();
	}

	private Result validateInternal() {
		if (spec == null) {
			error("BlocklyEditorSpec", "Spec must not be null.");
			return new Result(issues);
		}

		if (isBlank(spec.getDomainName())) {
			error("domainName", "Domain name must not be blank.");
		}

		collectCategories();
		collectBlockTypes();
		validateCategories();
		validateBlockTypes();
		validateValidations();

		if (concreteBlockTypeCount() == 0) {
			error("blockTypes", "At least one concrete block type is required.");
		}

		return new Result(issues);
	}

	private void collectCategories() {
		for (CategorySpec category : safeList(spec.getCategories())) {
			collectCategory(category, "category");
		}
	}

	private void collectCategory(CategorySpec category, String location) {
		if (category == null) {
			error(location, "Category must not be null.");
			return;
		}
		String name = category.getName();
		String categoryLocation = location + "[" + firstNonBlank(name, "?") + "]";
		if (isBlank(name)) {
			error(categoryLocation, "Category name must not be blank.");
		} else if (!categoryNames.add(name)) {
			error(categoryLocation, "Duplicate category name '" + name + "'.");
		}
		for (CategorySpec child : safeList(category.getChildren())) {
			collectCategory(child, categoryLocation + ".children");
		}
	}

	private void collectBlockTypes() {
		for (BlockTypeSpec block : safeList(spec.getBlockTypes())) {
			if (block == null) {
				error("blockTypes", "Block type must not be null.");
				continue;
			}
			String typeName = block.getTypeName();
			if (isBlank(typeName)) {
				error("blockTypes", "Block type name must not be blank.");
				continue;
			}
			BlockTypeSpec previous = blockByName.putIfAbsent(typeName, block);
			if (previous != null) {
				error("blockTypes[" + typeName + "]", "Duplicate block type name '" + typeName + "'.");
			}
		}
	}

	private void validateCategories() {
		if (categoryNames.isEmpty()) return;
		for (BlockTypeSpec block : safeList(spec.getBlockTypes())) {
			if (block == null || isBlank(block.getTypeName()) || isBlank(block.getCategoryName())) continue;
			if (!categoryNames.contains(block.getCategoryName())) {
				error(blockLocation(block), "Category '" + block.getCategoryName() + "' does not exist.");
			}
		}
	}

	private void validateBlockTypes() {
		for (BlockTypeSpec block : safeList(spec.getBlockTypes())) {
			if (block == null || isBlank(block.getTypeName())) continue;
			String location = blockLocation(block);

			if (!isBlank(block.getSuperTypeName())) {
				if (block.getTypeName().equals(block.getSuperTypeName())) {
					error(location, "Block type cannot extend itself.");
				} else if (!blockByName.containsKey(block.getSuperTypeName())) {
					error(location, "Super type '" + block.getSuperTypeName() + "' does not exist.");
				}
			}

			if (block.getConnectionType() == null) {
				error(location, "Connection type must not be null.");
			} else {
				validateConnection(block, location);
			}

			validateBlockFeatureNames(block, location);
			validateFields(block, location);
			validateReferences(block, location);
			validateStatementInputs(block, location);
			validateValueInputs(block, location);
			validateOrderedInputs(block, location);
			validateIdField(block, location);
		}
	}

	private void validateConnection(BlockTypeSpec block, String location) {
		switch (block.getConnectionType()) {
			case TYPED:
				if (isBlank(block.getConnectionTypeName())) {
					error(location, "Typed statement blocks must define connectionTypeName.");
				} else if (!blockByName.containsKey(block.getConnectionTypeName())) {
					error(location, "Connection type '" + block.getConnectionTypeName() + "' does not exist.");
				}
				break;
			case OUTPUT_TYPED:
				if (isBlank(block.getOutputType())) {
					error(location, "Typed output blocks must define outputType.");
				} else if (!blockByName.containsKey(block.getOutputType())) {
					error(location, "Output type '" + block.getOutputType() + "' does not exist.");
				}
				break;
			default:
				break;
		}
	}

	private void validateBlockFeatureNames(BlockTypeSpec block, String location) {
		Set<String> names = new HashSet<>();
		for (FieldSpec field : safeList(block.getFields())) addFeatureName(names, location, field != null ? field.getName() : null, "field");
		for (ReferenceFieldSpec ref : safeList(block.getReferenceFields())) addFeatureName(names, location, ref != null ? ref.getName() : null, "reference");
		for (StatementInputSpec input : safeList(block.getStatementInputs())) addFeatureName(names, location, input != null ? input.getName() : null, "statement input");
		for (ValueInputSpec input : safeList(block.getValueInputs())) addFeatureName(names, location, input != null ? input.getName() : null, "value input");
	}

	private void addFeatureName(Set<String> names, String location, String name, String kind) {
		if (isBlank(name)) {
			error(location, "A " + kind + " name must not be blank.");
		} else if (!names.add(name)) {
			error(location + "." + name, "Duplicate feature/input name '" + name + "'.");
		}
	}

	private void validateFields(BlockTypeSpec block, String blockLocation) {
		for (FieldSpec field : safeList(block.getFields())) {
			if (field == null) {
				error(blockLocation, "Field must not be null.");
				continue;
			}
			String location = blockLocation + "." + firstNonBlank(field.getName(), "?");
			if (field.getFieldType() == null) {
				error(location, "Field type must not be null.");
			}
			validateBounds(location, field.getLowerBound(), field.getUpperBound());
			if (field.isId() && field.isMany()) {
				error(location, "ID fields must be single-valued.");
			}
			if (field.getFieldType() == FieldSpec.FieldType.DROPDOWN) {
				validateDropdownOptions(field, location);
			}
			if (field.getFieldType() == FieldSpec.FieldType.IMAGE
					&& (isBlank(field.getImageUrl()) || field.getImageWidth() <= 0 || field.getImageHeight() <= 0)) {
				warning(location, "Image fields should define src, width and height.");
			}
			validateNumericBounds(field, location);
		}
	}

	private void validateDropdownOptions(FieldSpec field, String location) {
		if (safeList(field.getOptions()).isEmpty()) {
			error(location, "Dropdown fields must define at least one option.");
			return;
		}
		Set<String> values = new HashSet<>();
		for (DropdownOption option : safeList(field.getOptions())) {
			if (option == null) {
				error(location, "Dropdown option must not be null.");
				continue;
			}
			if (isBlank(option.getValue())) {
				error(location, "Dropdown option value must not be blank.");
			} else if (!values.add(option.getValue())) {
				error(location, "Duplicate dropdown option value '" + option.getValue() + "'.");
			}
		}
	}

	private void validateNumericBounds(FieldSpec field, String location) {
		if (isBlank(field.getMin()) || isBlank(field.getMax())) return;
		try {
			double min = Double.parseDouble(field.getMin());
			double max = Double.parseDouble(field.getMax());
			if (min > max) {
				error(location, "Numeric min must be less than or equal to max.");
			}
		} catch (NumberFormatException ignored) {
			warning(location, "Numeric min/max are not parseable numbers.");
		}
	}

	private void validateReferences(BlockTypeSpec block, String blockLocation) {
		for (ReferenceFieldSpec ref : safeList(block.getReferenceFields())) {
			if (ref == null) {
				error(blockLocation, "Reference must not be null.");
				continue;
			}
			String location = blockLocation + "." + firstNonBlank(ref.getName(), "?");
			validateBounds(location, ref.getLowerBound(), ref.getUpperBound());
			BlockTypeSpec target = blockByName.get(ref.getTargetTypeName());
			if (isBlank(ref.getTargetTypeName())) {
				error(location, "Reference target type must not be blank.");
			} else if (target == null) {
				error(location, "Reference target type '" + ref.getTargetTypeName() + "' does not exist.");
			} else if (!isBlank(ref.getReferenceLabelField()) && findField(target, ref.getReferenceLabelField()) == null) {
				error(location, "Reference label field '" + ref.getReferenceLabelField()
					+ "' does not exist on target type '" + ref.getTargetTypeName() + "'.");
			}
		}
	}

	private void validateStatementInputs(BlockTypeSpec block, String blockLocation) {
		for (StatementInputSpec input : safeList(block.getStatementInputs())) {
			if (input == null) {
				error(blockLocation, "Statement input must not be null.");
				continue;
			}
			String location = blockLocation + "." + firstNonBlank(input.getName(), "?");
			validateBounds(location, input.getLowerBound(), input.getUpperBound());
			if (!isBlank(input.getCheckType()) && !blockByName.containsKey(input.getCheckType())) {
				error(location, "Statement check type '" + input.getCheckType() + "' does not exist.");
			}
		}
	}

	private void validateValueInputs(BlockTypeSpec block, String blockLocation) {
		for (ValueInputSpec input : safeList(block.getValueInputs())) {
			if (input == null) {
				error(blockLocation, "Value input must not be null.");
				continue;
			}
			String location = blockLocation + "." + firstNonBlank(input.getName(), "?");
			if (!isBlank(input.getCheckType()) && !blockByName.containsKey(input.getCheckType())) {
				error(location, "Value input check type '" + input.getCheckType() + "' does not exist.");
			}
			if (!isBlank(input.getShadowBlockType())) {
				BlockTypeSpec shadow = blockByName.get(input.getShadowBlockType());
				if (shadow == null) {
					error(location, "Shadow block type '" + input.getShadowBlockType() + "' does not exist.");
				} else if (shadow.getConnectionType() != BlockTypeSpec.ConnectionType.OUTPUT
						&& shadow.getConnectionType() != BlockTypeSpec.ConnectionType.OUTPUT_TYPED) {
					error(location, "Shadow block type '" + input.getShadowBlockType() + "' is not an output block.");
				}
			}
		}
	}

	private void validateOrderedInputs(BlockTypeSpec block, String blockLocation) {
		if (safeList(block.getOrderedInputNames()).isEmpty()) return;
		Set<String> featureNames = featureNames(block);
		for (String name : safeList(block.getOrderedInputNames())) {
			if (isBlank(name)) {
				error(blockLocation, "orderedInputNames must not contain blank names.");
			} else if (!featureNames.contains(name)) {
				error(blockLocation, "orderedInputNames references unknown feature/input '" + name + "'.");
			}
		}
	}

	private void validateIdField(BlockTypeSpec block, String blockLocation) {
		if (isBlank(block.getIdFieldName())) return;
		FieldSpec field = findOwnField(block, block.getIdFieldName());
		if (field == null) {
			error(blockLocation, "idFieldName '" + block.getIdFieldName() + "' does not match a field.");
		} else if (!field.isId()) {
			error(blockLocation + "." + block.getIdFieldName(), "idFieldName must point to a field marked as ID.");
		}
	}

	private void validateValidations() {
		for (ValidationSpec validation : safeList(spec.getValidations())) {
				if (validation == null) {
					error("validations", "Validation must not be null.");
					continue;
				}
				String location = "validation[" + firstNonBlank(validation.getName(), "?") + "]";
				if (isBlank(validation.getName())) {
					error(location, "Validation name must not be blank.");
				}
				if (!isBlank(validation.getDiagnosticMessage())) {
					error(location, validation.getDiagnosticMessage());
					continue;
				}
				if (validation.getType() == null) {
					error(location, "Validation type must not be null.");
					continue;
				}
			switch (validation.getType()) {
				case MUST_FOLLOW:
					validateTypeReference(location, validation.getTargetType(), "target type");
					validateTypeReference(location, validation.getPredecessorType(), "predecessor type");
					validateVisualExpression(validation, location, validation.getTargetType());
					break;
				case CARDINALITY:
					validateOwner(location, validation.getOwnerType());
					validateBounds(location, validation.getLowerBound(), validation.getUpperBound());
					validateStatementInputReference(location, validation.getOwnerType(), validation.getContainmentName());
					validateVisualExpression(validation, location, validation.getOwnerType());
					break;
				case REQUIRED:
					validateOwner(location, validation.getOwnerType());
					validateFieldOrReference(location, validation.getOwnerType(), validation.getFieldName());
					validateVisualExpression(validation, location, validation.getOwnerType());
					break;
				case FIELD_CARDINALITY:
					validateOwner(location, validation.getOwnerType());
					validateBounds(location, validation.getLowerBound(), validation.getUpperBound());
					validateFieldOrReference(location, validation.getOwnerType(), validation.getFieldName());
					validateVisualExpression(validation, location, validation.getOwnerType());
					break;
				case UNIQUE:
					validateOwner(location, validation.getOwnerType());
					validateFieldOrReference(location, validation.getOwnerType(), validation.getFieldName());
					validateVisualExpression(validation, location, validation.getOwnerType());
					break;
					case EXPRESSION:
						validateOwner(location, validation.getOwnerType());
						if (isBlank(validation.getExpression())) {
							error(location, "Expression validation must define an expression.");
						} else {
							String syntaxError = ValidationExpressionSyntax.firstSyntaxError(validation.getExpression());
							if (!isBlank(syntaxError)) {
								error(location, "Expression validation has invalid syntax: " + syntaxError);
							}
						}
						validateVisualExpression(validation, location, validation.getOwnerType());
						break;
				default:
					break;
			}
		}
	}

	private void validateVisualExpression(ValidationSpec validation, String location, String ownerType) {
		ValidationExpressionSpec expression = validation.getVisualExpression();
		if (expression == null) return;
		validateExpressionNode(expression, location + ".visualExpression", ownerType);
	}

	private void validateExpressionNode(ValidationExpressionSpec expression, String location, String ownerType) {
		if (expression == null) {
			error(location, "Expression node must not be null.");
			return;
		}
		if (expression.getKind() == null) {
			error(location, "Expression node kind must not be null.");
			return;
		}
		switch (expression.getKind()) {
			case LOGIC_OPERATION:
				if (!"AND".equals(expression.getOperator()) && !"OR".equals(expression.getOperator())) {
					error(location, "Logic operation must use AND or OR.");
				}
				validateExpressionChildren(expression, location, ownerType, 2, -1);
				break;
			case LOGIC_NEGATE:
				validateExpressionChildren(expression, location, ownerType, 1, 1);
				break;
			case LOGIC_COMPARE:
				if (!Set.of("EQ", "NEQ", "LT", "LTE", "GT", "GTE").contains(firstNonBlank(expression.getOperator(), ""))) {
					error(location, "Compare operation must use EQ, NEQ, LT, LTE, GT or GTE.");
				}
				validateExpressionChildren(expression, location, ownerType, 2, 2);
				break;
			case NUMBER_LITERAL:
				try {
					Double.parseDouble(firstNonBlank(expression.getLiteral(), "0"));
				} catch (NumberFormatException e) {
					error(location, "Number literal is not parseable.");
				}
				break;
			case FIELD_VALUE:
			case FIELD_NUMBER:
			case FIELD_EXISTS:
			case FIELD_COUNT:
			case FIELD_UNIQUE:
				validateFieldOrReference(location, ownerType, expression.getFieldName());
				break;
			case TYPE_FIELD_UNIQUE:
				validateOwner(location, expression.getTypeName());
				validateFieldOrReference(location, expression.getTypeName(), expression.getFieldName());
				break;
			case INPUT_COUNT:
				validateStatementInputReference(location, ownerType, expression.getFieldName());
				break;
			case PREVIOUS_BLOCK_IS:
				validateTypeReference(location, expression.getTypeName(), "previous block type");
				break;
			case CUSTOM:
			case STRING_LITERAL:
			case BOOLEAN_LITERAL:
				break;
			default:
				break;
		}
	}

	private void validateExpressionChildren(ValidationExpressionSpec expression, String location,
			String ownerType, int min, int max) {
		int size = safeList(expression.getChildren()).size();
		if (size < min || (max >= 0 && size > max)) {
			String expected = max < 0 ? "at least " + min : Integer.toString(min);
			error(location, "Expected " + expected + " child expression(s), got " + size + ".");
		}
		for (int i = 0; i < size; i++) {
			validateExpressionNode(expression.getChildren().get(i), location + ".children[" + i + "]", ownerType);
		}
	}

	private void validateOwner(String location, String ownerType) {
		validateTypeReference(location, ownerType, "owner type");
	}

	private void validateTypeReference(String location, String typeName, String label) {
		if (isBlank(typeName)) {
			error(location, label + " must not be blank.");
		} else if (!blockByName.containsKey(typeName)) {
			error(location, label + " '" + typeName + "' does not exist.");
		}
	}

	private void validateStatementInputReference(String location, String ownerType, String inputName) {
		if (isBlank(ownerType) || !blockByName.containsKey(ownerType)) return;
		if (isBlank(inputName)) {
			error(location, "Statement input name must not be blank.");
			return;
		}
		if (findStatementInput(blockByName.get(ownerType), inputName) == null) {
			error(location, "Statement input '" + inputName + "' does not exist on type '" + ownerType + "'.");
		}
	}

	private void validateFieldOrReference(String location, String ownerType, String fieldName) {
		if (isBlank(ownerType) || !blockByName.containsKey(ownerType)) return;
		if (isBlank(fieldName)) {
			error(location, "Field/reference name must not be blank.");
			return;
		}
		BlockTypeSpec owner = blockByName.get(ownerType);
		if (findField(owner, fieldName) == null && findReference(owner, fieldName) == null) {
			error(location, "Field/reference '" + fieldName + "' does not exist on type '" + ownerType + "'.");
		}
	}

	private void validateBounds(String location, int lowerBound, int upperBound) {
		if (lowerBound < 0) {
			error(location, "Lower bound must be >= 0.");
		}
		if (upperBound < 0) {
			error(location, "Upper bound must be >= 0; use 0 for unbounded.");
		}
		if (upperBound != 0 && upperBound < lowerBound) {
			error(location, "Upper bound must be >= lower bound, or 0 for unbounded.");
		}
	}

	private Set<String> featureNames(BlockTypeSpec block) {
		Set<String> names = new HashSet<>();
		for (FieldSpec field : safeList(block.getFields())) if (field != null && !isBlank(field.getName())) names.add(field.getName());
		for (ReferenceFieldSpec ref : safeList(block.getReferenceFields())) if (ref != null && !isBlank(ref.getName())) names.add(ref.getName());
		for (StatementInputSpec input : safeList(block.getStatementInputs())) if (input != null && !isBlank(input.getName())) names.add(input.getName());
		for (ValueInputSpec input : safeList(block.getValueInputs())) if (input != null && !isBlank(input.getName())) names.add(input.getName());
		return names;
	}

	private FieldSpec findOwnField(BlockTypeSpec block, String name) {
		for (FieldSpec field : safeList(block.getFields())) {
			if (field != null && name.equals(field.getName())) return field;
		}
		return null;
	}

	private FieldSpec findField(BlockTypeSpec block, String name) {
		for (BlockTypeSpec current = block; current != null; current = blockByName.get(current.getSuperTypeName())) {
			FieldSpec field = findOwnField(current, name);
			if (field != null) return field;
			if (isBlank(current.getSuperTypeName())) break;
		}
		return null;
	}

	private ReferenceFieldSpec findReference(BlockTypeSpec block, String name) {
		for (BlockTypeSpec current = block; current != null; current = blockByName.get(current.getSuperTypeName())) {
			for (ReferenceFieldSpec ref : safeList(current.getReferenceFields())) {
				if (ref != null && name.equals(ref.getName())) return ref;
			}
			if (isBlank(current.getSuperTypeName())) break;
		}
		return null;
	}

	private StatementInputSpec findStatementInput(BlockTypeSpec block, String name) {
		for (BlockTypeSpec current = block; current != null; current = blockByName.get(current.getSuperTypeName())) {
			for (StatementInputSpec input : safeList(current.getStatementInputs())) {
				if (input != null && name.equals(input.getName())) return input;
			}
			if (isBlank(current.getSuperTypeName())) break;
		}
		return null;
	}

	private String blockLocation(BlockTypeSpec block) {
		return "block[" + firstNonBlank(block.getTypeName(), "?") + "]";
	}

	private int concreteBlockTypeCount() {
		int count = 0;
		for (BlockTypeSpec block : safeList(spec.getBlockTypes())) {
			if (block != null && !block.isAbstract()) count++;
		}
		return count;
	}

	private void error(String location, String message) {
		issues.add(new Issue(Severity.ERROR, location, message));
	}

	private void warning(String location, String message) {
		issues.add(new Issue(Severity.WARNING, location, message));
	}

	private static <T> List<T> safeList(List<T> values) {
		return values != null ? values : List.of();
	}

	private static String firstNonBlank(String value, String fallback) {
		return isBlank(value) ? fallback : value;
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
