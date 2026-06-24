/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validation Expression Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getKind <em>Kind</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getOperator <em>Operator</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getLiteral <em>Literal</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getBlocklyBlockType <em>Blockly Block Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec()
 * @model
 * @generated
 */
public interface ValidationExpressionSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind
	 * @see #setKind(ValidationExpressionKind)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_Kind()
	 * @model
	 * @generated
	 */
	ValidationExpressionKind getKind();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ValidationExpressionKind value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see #setOperator(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_Operator()
	 * @model
	 * @generated
	 */
	String getOperator();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(String value);

	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_TypeName()
	 * @model
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal</em>' attribute.
	 * @see #setLiteral(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_Literal()
	 * @model
	 * @generated
	 */
	String getLiteral();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getLiteral <em>Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal</em>' attribute.
	 * @see #getLiteral()
	 * @generated
	 */
	void setLiteral(String value);

	/**
	 * Returns the value of the '<em><b>Blockly Block Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blockly Block Type</em>' attribute.
	 * @see #setBlocklyBlockType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_BlocklyBlockType()
	 * @model
	 * @generated
	 */
	String getBlocklyBlockType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getBlocklyBlockType <em>Blockly Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blockly Block Type</em>' attribute.
	 * @see #getBlocklyBlockType()
	 * @generated
	 */
	void setBlocklyBlockType(String value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionSpec_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValidationExpressionSpec> getChildren();

} // ValidationExpressionSpec
