package io.github.plortinus.model2blockly.generator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType;

public final class BlocklySpecModelQueries {

	private BlocklySpecModelQueries() {
	}

	public static List<BlockTypeSpec> concreteBlockTypes(EditorSpec spec) {
		List<BlockTypeSpec> result = new ArrayList<>();
		if (spec == null) return result;
		for (BlockTypeSpec block : spec.getBlockTypes()) {
			if (!block.isAbstract()) result.add(block);
		}
		return result;
	}

	public static String effectiveToolboxType(EditorSpec spec) {
		String configured = spec != null ? spec.getToolboxType() : null;
		if (configured != null && !configured.isEmpty()) return configured;
		boolean hasCategories = false;
		if (spec != null) {
			for (BlockTypeSpec block : spec.getBlockTypes()) {
				if (!block.isAbstract() && block.getCategoryName() != null) {
					hasCategories = true;
					break;
				}
			}
			if (!hasCategories && spec.getCategories().isEmpty()) return "flyout";
		}
		return "category";
	}

	public static Map<String, Object> workspaceOptions(EditorSpec spec) {
		Map<String, Object> result = new LinkedHashMap<>();
		if (spec == null) return result;
		for (WorkspaceOption option : spec.getWorkspaceOptions()) {
			if (option.getKey() != null && !option.getKey().isBlank()) {
				result.put(option.getKey(), workspaceOptionValue(option));
			}
		}
		return result;
	}

	public static boolean isMany(FieldSpec field) {
		return field != null && (field.getUpperBound() == 0 || field.getUpperBound() > 1);
	}

	public static boolean isMany(ReferenceFieldSpec reference) {
		return reference != null && (reference.getUpperBound() == 0 || reference.getUpperBound() > 1);
	}

	public static Map<String, String> fields(ValidationBlockSpec block) {
		Map<String, String> result = new LinkedHashMap<>();
		if (block == null) return result;
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue field : block.getFields()) {
			result.put(field.getName(), field.getValue());
		}
		return result;
	}

	public static Map<String, ValidationExpressionSpec> inputs(ValidationBlockSpec block) {
		Map<String, ValidationExpressionSpec> result = new LinkedHashMap<>();
		if (block == null) return result;
		for (ValidationBlockInput input : block.getInputs()) {
			result.put(input.getName(), input.getExpression());
		}
		return result;
	}

	public static ValidationExpressionSpec logic(String operator, List<ValidationExpressionSpec> children) {
		ValidationExpressionSpec expression = io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecFactory.eINSTANCE
				.createValidationExpressionSpec();
		expression.setKind(ValidationExpressionKind.LOGIC_OPERATION);
		expression.setOperator(operator);
		if (children != null) expression.getChildren().addAll(children);
		return expression;
	}

	private static Object workspaceOptionValue(WorkspaceOption option) {
		if (option.getValueType() == WorkspaceOptionType.OBJECT) {
			Map<String, Object> map = new LinkedHashMap<>();
			for (WorkspaceOption child : option.getChildren()) {
				if (child.getKey() != null && !child.getKey().isBlank()) {
					map.put(child.getKey(), workspaceOptionValue(child));
				}
			}
			return map;
		}
		if (option.getValueType() == WorkspaceOptionType.BOOLEAN) {
			return Boolean.valueOf(option.getValue());
		}
		if (option.getValueType() == WorkspaceOptionType.INTEGER) {
			try {
				return Integer.valueOf(option.getValue());
			} catch (NumberFormatException e) {
				return option.getValue();
			}
		}
		return option.getValue();
	}
}
