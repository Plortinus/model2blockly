/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage
 * @generated
 */
public interface BlocklySpecFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BlocklySpecFactory eINSTANCE = io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Editor Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Editor Spec</em>'.
	 * @generated
	 */
	EditorSpec createEditorSpec();

	/**
	 * Returns a new object of class '<em>Category Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Category Spec</em>'.
	 * @generated
	 */
	CategorySpec createCategorySpec();

	/**
	 * Returns a new object of class '<em>Block Type Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Type Spec</em>'.
	 * @generated
	 */
	BlockTypeSpec createBlockTypeSpec();

	/**
	 * Returns a new object of class '<em>Named Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Value</em>'.
	 * @generated
	 */
	NamedValue createNamedValue();

	/**
	 * Returns a new object of class '<em>Dropdown Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dropdown Option</em>'.
	 * @generated
	 */
	DropdownOption createDropdownOption();

	/**
	 * Returns a new object of class '<em>Field Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Spec</em>'.
	 * @generated
	 */
	FieldSpec createFieldSpec();

	/**
	 * Returns a new object of class '<em>Statement Input Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Statement Input Spec</em>'.
	 * @generated
	 */
	StatementInputSpec createStatementInputSpec();

	/**
	 * Returns a new object of class '<em>Reference Field Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Field Spec</em>'.
	 * @generated
	 */
	ReferenceFieldSpec createReferenceFieldSpec();

	/**
	 * Returns a new object of class '<em>Value Input Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Input Spec</em>'.
	 * @generated
	 */
	ValueInputSpec createValueInputSpec();

	/**
	 * Returns a new object of class '<em>Workspace Option</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workspace Option</em>'.
	 * @generated
	 */
	WorkspaceOption createWorkspaceOption();

	/**
	 * Returns a new object of class '<em>Validation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Validation Spec</em>'.
	 * @generated
	 */
	ValidationSpec createValidationSpec();

	/**
	 * Returns a new object of class '<em>Validation Block Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Validation Block Spec</em>'.
	 * @generated
	 */
	ValidationBlockSpec createValidationBlockSpec();

	/**
	 * Returns a new object of class '<em>Validation Block Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Validation Block Input</em>'.
	 * @generated
	 */
	ValidationBlockInput createValidationBlockInput();

	/**
	 * Returns a new object of class '<em>Validation Expression Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Validation Expression Spec</em>'.
	 * @generated
	 */
	ValidationExpressionSpec createValidationExpressionSpec();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BlocklySpecPackage getBlocklySpecPackage();

} //BlocklySpecFactory
