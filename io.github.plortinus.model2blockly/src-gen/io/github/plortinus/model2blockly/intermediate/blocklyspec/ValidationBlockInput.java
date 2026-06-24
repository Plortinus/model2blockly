/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validation Block Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockInput()
 * @model
 * @generated
 */
public interface ValidationBlockInput extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockInput_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(ValidationExpressionSpec)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationBlockInput_Expression()
	 * @model containment="true"
	 * @generated
	 */
	ValidationExpressionSpec getExpression();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(ValidationExpressionSpec value);

} // ValidationBlockInput
