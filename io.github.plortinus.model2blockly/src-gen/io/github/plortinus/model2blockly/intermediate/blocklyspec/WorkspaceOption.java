/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workspace Option</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getKey <em>Key</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValue <em>Value</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValueType <em>Value Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getWorkspaceOption()
 * @model
 * @generated
 */
public interface WorkspaceOption extends EObject {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getWorkspaceOption_Key()
	 * @model
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getWorkspaceOption_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' attribute.
	 * The default value is <code>"STRING"</code>.
	 * The literals are from the enumeration {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType
	 * @see #setValueType(WorkspaceOptionType)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getWorkspaceOption_ValueType()
	 * @model default="STRING"
	 * @generated
	 */
	WorkspaceOptionType getValueType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(WorkspaceOptionType value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getWorkspaceOption_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<WorkspaceOption> getChildren();

} // WorkspaceOption
