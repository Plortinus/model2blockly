/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validation Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getType <em>Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getTargetType <em>Target Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getPredecessorType <em>Predecessor Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getOwnerType <em>Owner Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getContainmentName <em>Containment Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldKind <em>Field Kind</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getExpression <em>Expression</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getMessage <em>Message</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualBlock <em>Visual Block</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualExpression <em>Visual Expression</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec()
 * @model
 * @generated
 */
public interface ValidationSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
	 * @see #setType(ValidationType)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_Type()
	 * @model
	 * @generated
	 */
	ValidationType getType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
	 * @see #getType()
	 * @generated
	 */
	void setType(ValidationType value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Target Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Type</em>' attribute.
	 * @see #setTargetType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_TargetType()
	 * @model
	 * @generated
	 */
	String getTargetType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getTargetType <em>Target Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Type</em>' attribute.
	 * @see #getTargetType()
	 * @generated
	 */
	void setTargetType(String value);

	/**
	 * Returns the value of the '<em><b>Predecessor Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predecessor Type</em>' attribute.
	 * @see #setPredecessorType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_PredecessorType()
	 * @model
	 * @generated
	 */
	String getPredecessorType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getPredecessorType <em>Predecessor Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predecessor Type</em>' attribute.
	 * @see #getPredecessorType()
	 * @generated
	 */
	void setPredecessorType(String value);

	/**
	 * Returns the value of the '<em><b>Owner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner Type</em>' attribute.
	 * @see #setOwnerType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_OwnerType()
	 * @model
	 * @generated
	 */
	String getOwnerType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getOwnerType <em>Owner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner Type</em>' attribute.
	 * @see #getOwnerType()
	 * @generated
	 */
	void setOwnerType(String value);

	/**
	 * Returns the value of the '<em><b>Containment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containment Name</em>' attribute.
	 * @see #setContainmentName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_ContainmentName()
	 * @model
	 * @generated
	 */
	String getContainmentName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getContainmentName <em>Containment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containment Name</em>' attribute.
	 * @see #getContainmentName()
	 * @generated
	 */
	void setContainmentName(String value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_LowerBound()
	 * @model
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_UpperBound()
	 * @model
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Field Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Kind</em>' attribute.
	 * @see #setFieldKind(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_FieldKind()
	 * @model
	 * @generated
	 */
	String getFieldKind();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldKind <em>Field Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Kind</em>' attribute.
	 * @see #getFieldKind()
	 * @generated
	 */
	void setFieldKind(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_Expression()
	 * @model
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Visual Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visual Block</em>' containment reference.
	 * @see #setVisualBlock(ValidationBlockSpec)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_VisualBlock()
	 * @model containment="true"
	 * @generated
	 */
	ValidationBlockSpec getVisualBlock();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualBlock <em>Visual Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visual Block</em>' containment reference.
	 * @see #getVisualBlock()
	 * @generated
	 */
	void setVisualBlock(ValidationBlockSpec value);

	/**
	 * Returns the value of the '<em><b>Visual Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visual Expression</em>' containment reference.
	 * @see #setVisualExpression(ValidationExpressionSpec)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationSpec_VisualExpression()
	 * @model containment="true"
	 * @generated
	 */
	ValidationExpressionSpec getVisualExpression();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualExpression <em>Visual Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visual Expression</em>' containment reference.
	 * @see #getVisualExpression()
	 * @generated
	 */
	void setVisualExpression(ValidationExpressionSpec value);

} // ValidationSpec
