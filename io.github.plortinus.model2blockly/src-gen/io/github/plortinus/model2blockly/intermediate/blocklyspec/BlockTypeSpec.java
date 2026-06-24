/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Type Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getLabel <em>Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getColour <em>Colour</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCategoryName <em>Category Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getSuperTypeName <em>Super Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionType <em>Connection Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionTypeName <em>Connection Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOrderedInputNames <em>Ordered Input Names</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOutputType <em>Output Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getMessage0 <em>Message0</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getInputsInline <em>Inputs Inline</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getHelpUrl <em>Help Url</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCodeTemplate <em>Code Template</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getIdFieldName <em>Id Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getFields <em>Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getStatementInputs <em>Statement Inputs</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getReferenceFields <em>Reference Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getValueInputs <em>Value Inputs</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec()
 * @model
 * @generated
 */
public interface BlockTypeSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_TypeName()
	 * @model
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Colour</b></em>' attribute.
	 * The default value is <code>"200"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Colour</em>' attribute.
	 * @see #setColour(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Colour()
	 * @model default="200"
	 * @generated
	 */
	int getColour();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getColour <em>Colour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Colour</em>' attribute.
	 * @see #getColour()
	 * @generated
	 */
	void setColour(int value);

	/**
	 * Returns the value of the '<em><b>Category Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category Name</em>' attribute.
	 * @see #setCategoryName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_CategoryName()
	 * @model
	 * @generated
	 */
	String getCategoryName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCategoryName <em>Category Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category Name</em>' attribute.
	 * @see #getCategoryName()
	 * @generated
	 */
	void setCategoryName(String value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Super Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Type Name</em>' attribute.
	 * @see #setSuperTypeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_SuperTypeName()
	 * @model
	 * @generated
	 */
	String getSuperTypeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getSuperTypeName <em>Super Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Type Name</em>' attribute.
	 * @see #getSuperTypeName()
	 * @generated
	 */
	void setSuperTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Connection Type</b></em>' attribute.
	 * The default value is <code>"FREE"</code>.
	 * The literals are from the enumeration {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
	 * @see #setConnectionType(ConnectionType)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_ConnectionType()
	 * @model default="FREE"
	 * @generated
	 */
	ConnectionType getConnectionType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionType <em>Connection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
	 * @see #getConnectionType()
	 * @generated
	 */
	void setConnectionType(ConnectionType value);

	/**
	 * Returns the value of the '<em><b>Connection Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Type Name</em>' attribute.
	 * @see #setConnectionTypeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_ConnectionTypeName()
	 * @model
	 * @generated
	 */
	String getConnectionTypeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionTypeName <em>Connection Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Type Name</em>' attribute.
	 * @see #getConnectionTypeName()
	 * @generated
	 */
	void setConnectionTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Ordered Input Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordered Input Names</em>' attribute list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_OrderedInputNames()
	 * @model
	 * @generated
	 */
	EList<String> getOrderedInputNames();

	/**
	 * Returns the value of the '<em><b>Output Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Type</em>' attribute.
	 * @see #setOutputType(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_OutputType()
	 * @model
	 * @generated
	 */
	String getOutputType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOutputType <em>Output Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Type</em>' attribute.
	 * @see #getOutputType()
	 * @generated
	 */
	void setOutputType(String value);

	/**
	 * Returns the value of the '<em><b>Message0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message0</em>' attribute.
	 * @see #setMessage0(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Message0()
	 * @model
	 * @generated
	 */
	String getMessage0();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getMessage0 <em>Message0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message0</em>' attribute.
	 * @see #getMessage0()
	 * @generated
	 */
	void setMessage0(String value);

	/**
	 * Returns the value of the '<em><b>Inputs Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs Inline</em>' attribute.
	 * @see #setInputsInline(Boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_InputsInline()
	 * @model
	 * @generated
	 */
	Boolean getInputsInline();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getInputsInline <em>Inputs Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inputs Inline</em>' attribute.
	 * @see #getInputsInline()
	 * @generated
	 */
	void setInputsInline(Boolean value);

	/**
	 * Returns the value of the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tooltip</em>' attribute.
	 * @see #setTooltip(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Tooltip()
	 * @model
	 * @generated
	 */
	String getTooltip();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTooltip <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tooltip</em>' attribute.
	 * @see #getTooltip()
	 * @generated
	 */
	void setTooltip(String value);

	/**
	 * Returns the value of the '<em><b>Help Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Help Url</em>' attribute.
	 * @see #setHelpUrl(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_HelpUrl()
	 * @model
	 * @generated
	 */
	String getHelpUrl();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getHelpUrl <em>Help Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Help Url</em>' attribute.
	 * @see #getHelpUrl()
	 * @generated
	 */
	void setHelpUrl(String value);

	/**
	 * Returns the value of the '<em><b>Code Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code Template</em>' attribute.
	 * @see #setCodeTemplate(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_CodeTemplate()
	 * @model
	 * @generated
	 */
	String getCodeTemplate();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCodeTemplate <em>Code Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code Template</em>' attribute.
	 * @see #getCodeTemplate()
	 * @generated
	 */
	void setCodeTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Id Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Field Name</em>' attribute.
	 * @see #setIdFieldName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_IdFieldName()
	 * @model
	 * @generated
	 */
	String getIdFieldName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getIdFieldName <em>Id Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Field Name</em>' attribute.
	 * @see #getIdFieldName()
	 * @generated
	 */
	void setIdFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<FieldSpec> getFields();

	/**
	 * Returns the value of the '<em><b>Statement Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement Inputs</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_StatementInputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<StatementInputSpec> getStatementInputs();

	/**
	 * Returns the value of the '<em><b>Reference Fields</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Fields</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_ReferenceFields()
	 * @model containment="true"
	 * @generated
	 */
	EList<ReferenceFieldSpec> getReferenceFields();

	/**
	 * Returns the value of the '<em><b>Value Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Inputs</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getBlockTypeSpec_ValueInputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueInputSpec> getValueInputs();

} // BlockTypeSpec
