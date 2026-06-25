/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage
 * @generated
 */
public class BlocklySpecSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BlocklySpecPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlocklySpecSwitch() {
		if (modelPackage == null) {
			modelPackage = BlocklySpecPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BlocklySpecPackage.EDITOR_SPEC: {
				EditorSpec editorSpec = (EditorSpec)theEObject;
				T result = caseEditorSpec(editorSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.CATEGORY_SPEC: {
				CategorySpec categorySpec = (CategorySpec)theEObject;
				T result = caseCategorySpec(categorySpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.BLOCK_TYPE_SPEC: {
				BlockTypeSpec blockTypeSpec = (BlockTypeSpec)theEObject;
				T result = caseBlockTypeSpec(blockTypeSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.NAMED_VALUE: {
				NamedValue namedValue = (NamedValue)theEObject;
				T result = caseNamedValue(namedValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.DROPDOWN_OPTION: {
				DropdownOption dropdownOption = (DropdownOption)theEObject;
				T result = caseDropdownOption(dropdownOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.FIELD_SPEC: {
				FieldSpec fieldSpec = (FieldSpec)theEObject;
				T result = caseFieldSpec(fieldSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.STATEMENT_INPUT_SPEC: {
				StatementInputSpec statementInputSpec = (StatementInputSpec)theEObject;
				T result = caseStatementInputSpec(statementInputSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC: {
				ReferenceFieldSpec referenceFieldSpec = (ReferenceFieldSpec)theEObject;
				T result = caseReferenceFieldSpec(referenceFieldSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.VALUE_INPUT_SPEC: {
				ValueInputSpec valueInputSpec = (ValueInputSpec)theEObject;
				T result = caseValueInputSpec(valueInputSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.WORKSPACE_OPTION: {
				WorkspaceOption workspaceOption = (WorkspaceOption)theEObject;
				T result = caseWorkspaceOption(workspaceOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.VALIDATION_SPEC: {
				ValidationSpec validationSpec = (ValidationSpec)theEObject;
				T result = caseValidationSpec(validationSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC: {
				ValidationBlockSpec validationBlockSpec = (ValidationBlockSpec)theEObject;
				T result = caseValidationBlockSpec(validationBlockSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.VALIDATION_BLOCK_INPUT: {
				ValidationBlockInput validationBlockInput = (ValidationBlockInput)theEObject;
				T result = caseValidationBlockInput(validationBlockInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC: {
				ValidationExpressionSpec validationExpressionSpec = (ValidationExpressionSpec)theEObject;
				T result = caseValidationExpressionSpec(validationExpressionSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editor Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editor Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditorSpec(EditorSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategorySpec(CategorySpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block Type Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block Type Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockTypeSpec(BlockTypeSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedValue(NamedValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dropdown Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dropdown Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDropdownOption(DropdownOption object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldSpec(FieldSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Statement Input Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Statement Input Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatementInputSpec(StatementInputSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Field Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Field Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceFieldSpec(ReferenceFieldSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Input Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Input Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueInputSpec(ValueInputSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workspace Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workspace Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkspaceOption(WorkspaceOption object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validation Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidationSpec(ValidationSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validation Block Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validation Block Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidationBlockSpec(ValidationBlockSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validation Block Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validation Block Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidationBlockInput(ValidationBlockInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Validation Expression Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Validation Expression Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidationExpressionSpec(ValidationExpressionSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BlocklySpecSwitch
