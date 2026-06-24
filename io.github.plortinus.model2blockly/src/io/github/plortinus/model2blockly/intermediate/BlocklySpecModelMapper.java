package io.github.plortinus.model2blockly.intermediate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.plortinus.model2blockly.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpec;
import io.github.plortinus.model2blockly.blocklyspec.CategorySpec;
import io.github.plortinus.model2blockly.blocklyspec.DropdownOption;
import io.github.plortinus.model2blockly.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.blocklyspec.StatementInputSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValidationBlockSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValidationSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValueInputSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecFactory;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType;

/**
 * Bridge between the legacy Java intermediate classes and the generated EMF
 * intermediate model.
 */
public final class BlocklySpecModelMapper {

	private static final BlocklySpecFactory FACTORY = BlocklySpecFactory.eINSTANCE;

	private BlocklySpecModelMapper() {
	}

	public static EditorSpec toEmfSpec(BlocklyEditorSpec source) {
		if (source == null) {
			throw new IllegalArgumentException("BlocklyEditorSpec must not be null.");
		}
		EditorSpec target = FACTORY.createEditorSpec();
		target.setDomainName(source.getDomainName());
		target.setNsURI(source.getNsURI());
		target.setNsPrefix(source.getNsPrefix());
		target.setToolboxType(source.getToolboxType());
		target.setCodeLanguage(source.getCodeLanguage());
		target.setCodeFileExtension(source.getCodeFileExtension());
		target.setRuntimeKind(source.getRuntimeKind());
		for (CategorySpec category : safeList(source.getCategories())) {
			target.getCategories().add(toEmfCategory(category));
		}
		for (BlockTypeSpec blockType : safeList(source.getBlockTypes())) {
			target.getBlockTypes().add(toEmfBlockType(blockType));
		}
		for (ValidationSpec validation : safeList(source.getValidations())) {
			target.getValidations().add(toEmfValidation(validation));
		}
		for (Map.Entry<String, Object> option : safeMap(source.getWorkspaceOptions()).entrySet()) {
			target.getWorkspaceOptions().add(toEmfWorkspaceOption(option.getKey(), option.getValue()));
		}
		return target;
	}

	public static BlocklyEditorSpec toJavaSpec(EditorSpec source) {
		if (source == null) {
			throw new IllegalArgumentException("EditorSpec must not be null.");
		}
		BlocklyEditorSpec target = new BlocklyEditorSpec();
		target.setDomainName(source.getDomainName());
		target.setNsURI(source.getNsURI());
		target.setNsPrefix(source.getNsPrefix());
		target.setToolboxType(source.getToolboxType());
		target.setCodeLanguage(source.getCodeLanguage());
		target.setCodeFileExtension(source.getCodeFileExtension());
		target.setRuntimeKind(source.getRuntimeKind());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec category : source.getCategories()) {
			target.getCategories().add(toJavaCategory(category));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec blockType : source.getBlockTypes()) {
			target.getBlockTypes().add(toJavaBlockType(blockType));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec validation : source.getValidations()) {
			target.getValidations().add(toJavaValidation(validation));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption option : source.getWorkspaceOptions()) {
			if (option.getKey() != null && !option.getKey().isBlank()) {
				target.putWorkspaceOption(option.getKey(), toJavaWorkspaceOptionValue(option));
			}
		}
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec toEmfCategory(CategorySpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec target = FACTORY.createCategorySpec();
		target.setName(source.getName());
		target.setLabel(source.getLabel());
		target.setColour(source.getColour());
		for (CategorySpec child : safeList(source.getChildren())) {
			target.getChildren().add(toEmfCategory(child));
		}
		return target;
	}

	private static CategorySpec toJavaCategory(io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec source) {
		CategorySpec target = new CategorySpec();
		target.setName(source.getName());
		target.setLabel(source.getLabel());
		target.setColour(source.getColour());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec child : source.getChildren()) {
			target.getChildren().add(toJavaCategory(child));
		}
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec toEmfBlockType(BlockTypeSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec target = FACTORY.createBlockTypeSpec();
		target.setTypeName(source.getTypeName());
		target.setLabel(source.getLabel());
		target.setColour(source.getColour());
		target.setCategoryName(source.getCategoryName());
		target.setAbstract(source.isAbstract());
		target.setSuperTypeName(source.getSuperTypeName());
		target.setConnectionType(toEmfConnectionType(source.getConnectionType()));
		target.setConnectionTypeName(source.getConnectionTypeName());
		target.getOrderedInputNames().addAll(safeList(source.getOrderedInputNames()));
		target.setOutputType(source.getOutputType());
		target.setMessage0(source.getMessage0());
		target.setInputsInline(source.getInputsInline());
		target.setTooltip(source.getTooltip());
		target.setHelpUrl(source.getHelpUrl());
		target.setCodeTemplate(source.getCodeTemplate());
		target.setIdFieldName(source.getIdFieldName());
		for (FieldSpec field : safeList(source.getFields())) {
			target.getFields().add(toEmfField(field));
		}
		for (StatementInputSpec input : safeList(source.getStatementInputs())) {
			target.getStatementInputs().add(toEmfStatementInput(input));
		}
		for (ReferenceFieldSpec reference : safeList(source.getReferenceFields())) {
			target.getReferenceFields().add(toEmfReference(reference));
		}
		for (ValueInputSpec input : safeList(source.getValueInputs())) {
			target.getValueInputs().add(toEmfValueInput(input));
		}
		return target;
	}

	private static BlockTypeSpec toJavaBlockType(io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec source) {
		BlockTypeSpec target = new BlockTypeSpec();
		target.setTypeName(source.getTypeName());
		target.setLabel(source.getLabel());
		target.setColour(source.getColour());
		target.setCategoryName(source.getCategoryName());
		target.setAbstract(source.isAbstract());
		target.setSuperTypeName(source.getSuperTypeName());
		target.setConnectionType(toJavaConnectionType(source.getConnectionType()));
		target.setConnectionTypeName(source.getConnectionTypeName());
		target.getOrderedInputNames().addAll(source.getOrderedInputNames());
		target.setOutputType(source.getOutputType());
		target.setMessage0(source.getMessage0());
		target.setInputsInline(source.getInputsInline());
		target.setTooltip(source.getTooltip());
		target.setHelpUrl(source.getHelpUrl());
		target.setCodeTemplate(source.getCodeTemplate());
		target.setIdFieldName(source.getIdFieldName());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec field : source.getFields()) {
			target.getFields().add(toJavaField(field));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec input : source.getStatementInputs()) {
			target.getStatementInputs().add(toJavaStatementInput(input));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec reference : source.getReferenceFields()) {
			target.getReferenceFields().add(toJavaReference(reference));
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec input : source.getValueInputs()) {
			target.getValueInputs().add(toJavaValueInput(input));
		}
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec toEmfField(FieldSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec target = FACTORY.createFieldSpec();
		target.setName(source.getName());
		target.setFieldType(toEmfFieldType(source.getFieldType()));
		target.setDefaultValue(source.getDefaultValue());
		for (DropdownOption option : safeList(source.getOptions())) {
			target.getOptions().add(toEmfDropdownOption(option));
		}
		target.setImageUrl(source.getImageUrl());
		target.setImageWidth(source.getImageWidth());
		target.setImageHeight(source.getImageHeight());
		target.setImageAlt(source.getImageAlt());
		target.setMin(source.getMin());
		target.setMax(source.getMax());
		target.setRequired(source.isRequired());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setUnique(source.isUnique());
		target.setOrdered(source.isOrdered());
		target.setId(source.isId());
		setEmfUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static FieldSpec toJavaField(io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec source) {
		FieldSpec target = new FieldSpec();
		target.setName(source.getName());
		target.setFieldType(toJavaFieldType(source.getFieldType()));
		target.setDefaultValue(source.getDefaultValue());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption option : source.getOptions()) {
			target.getOptions().add(new DropdownOption(option.getValue(), option.getLabel()));
		}
		target.setImageUrl(source.getImageUrl());
		target.setImageWidth(source.getImageWidth());
		target.setImageHeight(source.getImageHeight());
		target.setImageAlt(source.getImageAlt());
		target.setMin(source.getMin());
		target.setMax(source.getMax());
		target.setRequired(source.isRequired());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setUnique(source.isUnique());
		target.setOrdered(source.isOrdered());
		target.setId(source.isId());
		applyJavaUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption toEmfDropdownOption(
			DropdownOption source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption target = FACTORY.createDropdownOption();
		target.setValue(source.getValue());
		target.setLabel(source.getLabel());
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec toEmfStatementInput(
			StatementInputSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec target = FACTORY.createStatementInputSpec();
		target.setName(source.getName());
		target.setCheckType(source.getCheckType());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		setEmfUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static StatementInputSpec toJavaStatementInput(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec source) {
		StatementInputSpec target = new StatementInputSpec();
		target.setName(source.getName());
		target.setCheckType(source.getCheckType());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		applyJavaUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec toEmfReference(
			ReferenceFieldSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec target = FACTORY.createReferenceFieldSpec();
		target.setName(source.getName());
		target.setTargetTypeName(source.getTargetTypeName());
		target.setRequired(source.isRequired());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setUnique(source.isUnique());
		target.setOrdered(source.isOrdered());
		target.setOppositeName(source.getOppositeName());
		setEmfUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		target.setReferenceLabelField(source.getReferenceLabelField());
		return target;
	}

	private static ReferenceFieldSpec toJavaReference(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec source) {
		ReferenceFieldSpec target = new ReferenceFieldSpec();
		target.setName(source.getName());
		target.setTargetTypeName(source.getTargetTypeName());
		target.setRequired(source.isRequired());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setUnique(source.isUnique());
		target.setOrdered(source.isOrdered());
		target.setOppositeName(source.getOppositeName());
		target.setReferenceLabelField(source.getReferenceLabelField());
		applyJavaUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec toEmfValueInput(ValueInputSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec target = FACTORY.createValueInputSpec();
		target.setName(source.getName());
		target.setCheckType(source.getCheckType());
		target.setShadowBlockType(source.getShadowBlockType());
		setEmfUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static ValueInputSpec toJavaValueInput(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec source) {
		ValueInputSpec target = new ValueInputSpec();
		target.setName(source.getName());
		target.setCheckType(source.getCheckType());
		target.setShadowBlockType(source.getShadowBlockType());
		applyJavaUi(target, source.getUiWidget(), source.getUiLabel(), source.getUiHelp(), source.getUiPlaceholder(),
			source.getUiGroup(), source.getUiOrder(), source.isUiReadonly(), source.isUiHidden(), source.getUiVariant());
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption toEmfWorkspaceOption(
			String key, Object value) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption target = FACTORY.createWorkspaceOption();
		target.setKey(key);
		if (value instanceof Map<?, ?>) {
			target.setValueType(WorkspaceOptionType.OBJECT);
			for (Map.Entry<?, ?> child : ((Map<?, ?>) value).entrySet()) {
				target.getChildren().add(toEmfWorkspaceOption(String.valueOf(child.getKey()), child.getValue()));
			}
		} else if (value instanceof Boolean) {
			target.setValueType(WorkspaceOptionType.BOOLEAN);
			target.setValue(String.valueOf(value));
		} else if (value instanceof Number) {
			target.setValueType(WorkspaceOptionType.INTEGER);
			target.setValue(String.valueOf(value));
		} else {
			target.setValueType(WorkspaceOptionType.STRING);
			target.setValue(value != null ? String.valueOf(value) : null);
		}
		return target;
	}

	private static Object toJavaWorkspaceOptionValue(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption source) {
		if (source.getValueType() == WorkspaceOptionType.OBJECT) {
			Map<String, Object> result = new LinkedHashMap<>();
			for (io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption child : source.getChildren()) {
				if (child.getKey() != null && !child.getKey().isBlank()) {
					result.put(child.getKey(), toJavaWorkspaceOptionValue(child));
				}
			}
			return result;
		}
		if (source.getValueType() == WorkspaceOptionType.BOOLEAN) {
			return Boolean.valueOf(source.getValue());
		}
		if (source.getValueType() == WorkspaceOptionType.INTEGER) {
			try {
				return Integer.valueOf(source.getValue());
			} catch (NumberFormatException e) {
				return source.getValue();
			}
		}
		return source.getValue();
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec toEmfValidation(
			ValidationSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec target = FACTORY.createValidationSpec();
		target.setType(toEmfValidationType(source.getType()));
		target.setName(source.getName());
		target.setTargetType(source.getTargetType());
		target.setPredecessorType(source.getPredecessorType());
		target.setOwnerType(source.getOwnerType());
		target.setContainmentName(source.getContainmentName());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setFieldName(source.getFieldName());
		target.setFieldKind(source.getFieldKind());
		target.setExpression(source.getExpression());
		target.setMessage(source.getMessage());
		if (source.getVisualBlock() != null) {
			target.setVisualBlock(toEmfValidationBlock(source.getVisualBlock()));
		}
		if (source.getVisualExpression() != null) {
			target.setVisualExpression(toEmfValidationExpression(source.getVisualExpression()));
		}
		return target;
	}

	private static ValidationSpec toJavaValidation(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec source) {
		ValidationSpec target = new ValidationSpec();
		target.setType(toJavaValidationType(source.getType()));
		target.setName(source.getName());
		target.setTargetType(source.getTargetType());
		target.setPredecessorType(source.getPredecessorType());
		target.setOwnerType(source.getOwnerType());
		target.setContainmentName(source.getContainmentName());
		target.setLowerBound(source.getLowerBound());
		target.setUpperBound(source.getUpperBound());
		target.setFieldName(source.getFieldName());
		target.setFieldKind(source.getFieldKind());
		target.setExpression(source.getExpression());
		target.setMessage(source.getMessage());
		if (source.getVisualBlock() != null) {
			target.setVisualBlock(toJavaValidationBlock(source.getVisualBlock()));
		}
		if (source.getVisualExpression() != null) {
			target.setVisualExpression(toJavaValidationExpression(source.getVisualExpression()));
		}
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec toEmfValidationBlock(
			ValidationBlockSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec target = FACTORY.createValidationBlockSpec();
		target.setBlocklyBlockType(source.getBlocklyBlockType());
		for (Map.Entry<String, String> field : safeMap(source.getFields()).entrySet()) {
			io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue value = FACTORY.createNamedValue();
			value.setName(field.getKey());
			value.setValue(field.getValue());
			target.getFields().add(value);
		}
		for (Map.Entry<String, ValidationExpressionSpec> input : safeMap(source.getInputs()).entrySet()) {
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput value =
				FACTORY.createValidationBlockInput();
			value.setName(input.getKey());
			if (input.getValue() != null) {
				value.setExpression(toEmfValidationExpression(input.getValue()));
			}
			target.getInputs().add(value);
		}
		return target;
	}

	private static ValidationBlockSpec toJavaValidationBlock(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec source) {
		ValidationBlockSpec target = new ValidationBlockSpec();
		target.setBlocklyBlockType(source.getBlocklyBlockType());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue field : source.getFields()) {
			target.field(field.getName(), field.getValue());
		}
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput input : source.getInputs()) {
			if (input.getExpression() != null) {
				target.input(input.getName(), toJavaValidationExpression(input.getExpression()));
			}
		}
		return target;
	}

	private static io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec toEmfValidationExpression(
			ValidationExpressionSpec source) {
		io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec target =
			FACTORY.createValidationExpressionSpec();
		target.setKind(toEmfValidationExpressionKind(source.getKind()));
		target.setOperator(source.getOperator());
		target.setFieldName(source.getFieldName());
		target.setTypeName(source.getTypeName());
		target.setLiteral(source.getLiteral());
		target.setBlocklyBlockType(source.getBlocklyBlockType());
		for (ValidationExpressionSpec child : safeList(source.getChildren())) {
			target.getChildren().add(toEmfValidationExpression(child));
		}
		return target;
	}

	private static ValidationExpressionSpec toJavaValidationExpression(
			io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec source) {
		ValidationExpressionSpec target = new ValidationExpressionSpec();
		target.setKind(toJavaValidationExpressionKind(source.getKind()));
		target.setOperator(source.getOperator());
		target.setFieldName(source.getFieldName());
		target.setTypeName(source.getTypeName());
		target.setLiteral(source.getLiteral());
		target.setBlocklyBlockType(source.getBlocklyBlockType());
		for (io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec child : source.getChildren()) {
			target.getChildren().add(toJavaValidationExpression(child));
		}
		return target;
	}

	private static ConnectionType toEmfConnectionType(BlockTypeSpec.ConnectionType value) {
		return value != null ? ConnectionType.getByName(value.name()) : null;
	}

	private static BlockTypeSpec.ConnectionType toJavaConnectionType(ConnectionType value) {
		return value != null ? BlockTypeSpec.ConnectionType.valueOf(value.getName()) : null;
	}

	private static FieldType toEmfFieldType(FieldSpec.FieldType value) {
		return value != null ? FieldType.getByName(value.name()) : null;
	}

	private static FieldSpec.FieldType toJavaFieldType(FieldType value) {
		return value != null ? FieldSpec.FieldType.valueOf(value.getName()) : null;
	}

	private static ValidationType toEmfValidationType(ValidationSpec.ValidationType value) {
		return value != null ? ValidationType.getByName(value.name()) : null;
	}

	private static ValidationSpec.ValidationType toJavaValidationType(ValidationType value) {
		return value != null ? ValidationSpec.ValidationType.valueOf(value.getName()) : null;
	}

	private static ValidationExpressionKind toEmfValidationExpressionKind(ValidationExpressionSpec.Kind value) {
		return value != null ? ValidationExpressionKind.getByName(value.name()) : null;
	}

	private static ValidationExpressionSpec.Kind toJavaValidationExpressionKind(ValidationExpressionKind value) {
		return value != null ? ValidationExpressionSpec.Kind.valueOf(value.getName()) : null;
	}

	private static void setEmfUi(io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec target,
			String widget, String label, String help, String placeholder, String group, Integer order,
			boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void setEmfUi(io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec target,
			String widget, String label, String help, String placeholder, String group, Integer order,
			boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void setEmfUi(io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec target,
			String widget, String label, String help, String placeholder, String group, Integer order,
			boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void setEmfUi(io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec target,
			String widget, String label, String help, String placeholder, String group, Integer order,
			boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void applyJavaUi(FieldSpec target, String widget, String label, String help,
			String placeholder, String group, Integer order, boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void applyJavaUi(StatementInputSpec target, String widget, String label, String help,
			String placeholder, String group, Integer order, boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void applyJavaUi(ReferenceFieldSpec target, String widget, String label, String help,
			String placeholder, String group, Integer order, boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static void applyJavaUi(ValueInputSpec target, String widget, String label, String help,
			String placeholder, String group, Integer order, boolean readonly, boolean hidden, String variant) {
		target.setUiWidget(widget);
		target.setUiLabel(label);
		target.setUiHelp(help);
		target.setUiPlaceholder(placeholder);
		target.setUiGroup(group);
		target.setUiOrder(order);
		target.setUiReadonly(readonly);
		target.setUiHidden(hidden);
		target.setUiVariant(variant);
	}

	private static <T> List<T> safeList(List<T> list) {
		return list != null ? list : new ArrayList<>();
	}

	private static <K, V> Map<K, V> safeMap(Map<K, V> map) {
		return map != null ? map : new LinkedHashMap<>();
	}
}
