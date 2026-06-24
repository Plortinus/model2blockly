/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BlocklySpecFactoryImpl extends EFactoryImpl implements BlocklySpecFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BlocklySpecFactory init() {
		try {
			BlocklySpecFactory theBlocklySpecFactory = (BlocklySpecFactory)EPackage.Registry.INSTANCE.getEFactory(BlocklySpecPackage.eNS_URI);
			if (theBlocklySpecFactory != null) {
				return theBlocklySpecFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BlocklySpecFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlocklySpecFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BlocklySpecPackage.EDITOR_SPEC: return createEditorSpec();
			case BlocklySpecPackage.CATEGORY_SPEC: return createCategorySpec();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC: return createBlockTypeSpec();
			case BlocklySpecPackage.NAMED_VALUE: return createNamedValue();
			case BlocklySpecPackage.DROPDOWN_OPTION: return createDropdownOption();
			case BlocklySpecPackage.FIELD_SPEC: return createFieldSpec();
			case BlocklySpecPackage.STATEMENT_INPUT_SPEC: return createStatementInputSpec();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC: return createReferenceFieldSpec();
			case BlocklySpecPackage.VALUE_INPUT_SPEC: return createValueInputSpec();
			case BlocklySpecPackage.WORKSPACE_OPTION: return createWorkspaceOption();
			case BlocklySpecPackage.VALIDATION_SPEC: return createValidationSpec();
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC: return createValidationBlockSpec();
			case BlocklySpecPackage.VALIDATION_BLOCK_INPUT: return createValidationBlockInput();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC: return createValidationExpressionSpec();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BlocklySpecPackage.CONNECTION_TYPE:
				return createConnectionTypeFromString(eDataType, initialValue);
			case BlocklySpecPackage.FIELD_TYPE:
				return createFieldTypeFromString(eDataType, initialValue);
			case BlocklySpecPackage.VALIDATION_TYPE:
				return createValidationTypeFromString(eDataType, initialValue);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_KIND:
				return createValidationExpressionKindFromString(eDataType, initialValue);
			case BlocklySpecPackage.WORKSPACE_OPTION_TYPE:
				return createWorkspaceOptionTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BlocklySpecPackage.CONNECTION_TYPE:
				return convertConnectionTypeToString(eDataType, instanceValue);
			case BlocklySpecPackage.FIELD_TYPE:
				return convertFieldTypeToString(eDataType, instanceValue);
			case BlocklySpecPackage.VALIDATION_TYPE:
				return convertValidationTypeToString(eDataType, instanceValue);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_KIND:
				return convertValidationExpressionKindToString(eDataType, instanceValue);
			case BlocklySpecPackage.WORKSPACE_OPTION_TYPE:
				return convertWorkspaceOptionTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EditorSpec createEditorSpec() {
		EditorSpecImpl editorSpec = new EditorSpecImpl();
		return editorSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CategorySpec createCategorySpec() {
		CategorySpecImpl categorySpec = new CategorySpecImpl();
		return categorySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BlockTypeSpec createBlockTypeSpec() {
		BlockTypeSpecImpl blockTypeSpec = new BlockTypeSpecImpl();
		return blockTypeSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedValue createNamedValue() {
		NamedValueImpl namedValue = new NamedValueImpl();
		return namedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DropdownOption createDropdownOption() {
		DropdownOptionImpl dropdownOption = new DropdownOptionImpl();
		return dropdownOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FieldSpec createFieldSpec() {
		FieldSpecImpl fieldSpec = new FieldSpecImpl();
		return fieldSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StatementInputSpec createStatementInputSpec() {
		StatementInputSpecImpl statementInputSpec = new StatementInputSpecImpl();
		return statementInputSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReferenceFieldSpec createReferenceFieldSpec() {
		ReferenceFieldSpecImpl referenceFieldSpec = new ReferenceFieldSpecImpl();
		return referenceFieldSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValueInputSpec createValueInputSpec() {
		ValueInputSpecImpl valueInputSpec = new ValueInputSpecImpl();
		return valueInputSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WorkspaceOption createWorkspaceOption() {
		WorkspaceOptionImpl workspaceOption = new WorkspaceOptionImpl();
		return workspaceOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationSpec createValidationSpec() {
		ValidationSpecImpl validationSpec = new ValidationSpecImpl();
		return validationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationBlockSpec createValidationBlockSpec() {
		ValidationBlockSpecImpl validationBlockSpec = new ValidationBlockSpecImpl();
		return validationBlockSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationBlockInput createValidationBlockInput() {
		ValidationBlockInputImpl validationBlockInput = new ValidationBlockInputImpl();
		return validationBlockInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationExpressionSpec createValidationExpressionSpec() {
		ValidationExpressionSpecImpl validationExpressionSpec = new ValidationExpressionSpecImpl();
		return validationExpressionSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionType createConnectionTypeFromString(EDataType eDataType, String initialValue) {
		ConnectionType result = ConnectionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConnectionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldType createFieldTypeFromString(EDataType eDataType, String initialValue) {
		FieldType result = FieldType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFieldTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationType createValidationTypeFromString(EDataType eDataType, String initialValue) {
		ValidationType result = ValidationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValidationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidationExpressionKind createValidationExpressionKindFromString(EDataType eDataType, String initialValue) {
		ValidationExpressionKind result = ValidationExpressionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValidationExpressionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceOptionType createWorkspaceOptionTypeFromString(EDataType eDataType, String initialValue) {
		WorkspaceOptionType result = WorkspaceOptionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkspaceOptionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BlocklySpecPackage getBlocklySpecPackage() {
		return (BlocklySpecPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BlocklySpecPackage getPackage() {
		return BlocklySpecPackage.eINSTANCE;
	}

} //BlocklySpecFactoryImpl
