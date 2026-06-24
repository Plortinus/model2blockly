/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validation Block Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getBlocklyBlockType <em>Blockly Block Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getFields <em>Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockSpec()
 * @model
 * @generated
 */
public interface ValidationBlockSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Blockly Block Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blockly Block Type</em>' attribute.
	 * @see #setBlocklyBlockType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockSpec_BlocklyBlockType()
	 * @model
	 * @generated
	 */
	String getBlocklyBlockType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getBlocklyBlockType <em>Blockly Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blockly Block Type</em>' attribute.
	 * @see #getBlocklyBlockType()
	 * @generated
	 */
	void setBlocklyBlockType(String value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockSpec_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<NamedValue> getFields();

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockSpec_Inputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValidationBlockInput> getInputs();

} // ValidationBlockSpec
