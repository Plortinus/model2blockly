/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Field Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getTargetTypeName <em>Target Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isRequired <em>Required</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUnique <em>Unique</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getOppositeName <em>Opposite Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiWidget <em>Ui Widget</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiLabel <em>Ui Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiHelp <em>Ui Help</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiGroup <em>Ui Group</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiOrder <em>Ui Order</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiReadonly <em>Ui Readonly</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiHidden <em>Ui Hidden</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiVariant <em>Ui Variant</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getReferenceLabelField <em>Reference Label Field</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec()
 * @model
 * @generated
 */
public interface ReferenceFieldSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Target Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Type Name</em>' attribute.
	 * @see #setTargetTypeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_TargetTypeName()
	 * @model
	 * @generated
	 */
	String getTargetTypeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getTargetTypeName <em>Target Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Type Name</em>' attribute.
	 * @see #getTargetTypeName()
	 * @generated
	 */
	void setTargetTypeName(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_Required()
	 * @model
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_LowerBound()
	 * @model
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UpperBound()
	 * @model default="1"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_Unique()
	 * @model default="true"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

	/**
	 * Returns the value of the '<em><b>Ordered</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordered</em>' attribute.
	 * @see #setOrdered(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_Ordered()
	 * @model default="true"
	 * @generated
	 */
	boolean isOrdered();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isOrdered <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordered</em>' attribute.
	 * @see #isOrdered()
	 * @generated
	 */
	void setOrdered(boolean value);

	/**
	 * Returns the value of the '<em><b>Opposite Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opposite Name</em>' attribute.
	 * @see #setOppositeName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_OppositeName()
	 * @model
	 * @generated
	 */
	String getOppositeName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getOppositeName <em>Opposite Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite Name</em>' attribute.
	 * @see #getOppositeName()
	 * @generated
	 */
	void setOppositeName(String value);

	/**
	 * Returns the value of the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Widget</em>' attribute.
	 * @see #setUiWidget(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiWidget()
	 * @model
	 * @generated
	 */
	String getUiWidget();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiWidget <em>Ui Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Widget</em>' attribute.
	 * @see #getUiWidget()
	 * @generated
	 */
	void setUiWidget(String value);

	/**
	 * Returns the value of the '<em><b>Ui Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Label</em>' attribute.
	 * @see #setUiLabel(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiLabel()
	 * @model
	 * @generated
	 */
	String getUiLabel();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiLabel <em>Ui Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Label</em>' attribute.
	 * @see #getUiLabel()
	 * @generated
	 */
	void setUiLabel(String value);

	/**
	 * Returns the value of the '<em><b>Ui Help</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Help</em>' attribute.
	 * @see #setUiHelp(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiHelp()
	 * @model
	 * @generated
	 */
	String getUiHelp();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiHelp <em>Ui Help</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Help</em>' attribute.
	 * @see #getUiHelp()
	 * @generated
	 */
	void setUiHelp(String value);

	/**
	 * Returns the value of the '<em><b>Ui Placeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Placeholder</em>' attribute.
	 * @see #setUiPlaceholder(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiPlaceholder()
	 * @model
	 * @generated
	 */
	String getUiPlaceholder();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Placeholder</em>' attribute.
	 * @see #getUiPlaceholder()
	 * @generated
	 */
	void setUiPlaceholder(String value);

	/**
	 * Returns the value of the '<em><b>Ui Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Group</em>' attribute.
	 * @see #setUiGroup(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiGroup()
	 * @model
	 * @generated
	 */
	String getUiGroup();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiGroup <em>Ui Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Group</em>' attribute.
	 * @see #getUiGroup()
	 * @generated
	 */
	void setUiGroup(String value);

	/**
	 * Returns the value of the '<em><b>Ui Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Order</em>' attribute.
	 * @see #setUiOrder(Integer)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiOrder()
	 * @model
	 * @generated
	 */
	Integer getUiOrder();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiOrder <em>Ui Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Order</em>' attribute.
	 * @see #getUiOrder()
	 * @generated
	 */
	void setUiOrder(Integer value);

	/**
	 * Returns the value of the '<em><b>Ui Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Readonly</em>' attribute.
	 * @see #setUiReadonly(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiReadonly()
	 * @model
	 * @generated
	 */
	boolean isUiReadonly();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiReadonly <em>Ui Readonly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Readonly</em>' attribute.
	 * @see #isUiReadonly()
	 * @generated
	 */
	void setUiReadonly(boolean value);

	/**
	 * Returns the value of the '<em><b>Ui Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Hidden</em>' attribute.
	 * @see #setUiHidden(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiHidden()
	 * @model
	 * @generated
	 */
	boolean isUiHidden();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiHidden <em>Ui Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Hidden</em>' attribute.
	 * @see #isUiHidden()
	 * @generated
	 */
	void setUiHidden(boolean value);

	/**
	 * Returns the value of the '<em><b>Ui Variant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Variant</em>' attribute.
	 * @see #setUiVariant(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_UiVariant()
	 * @model
	 * @generated
	 */
	String getUiVariant();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiVariant <em>Ui Variant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Variant</em>' attribute.
	 * @see #getUiVariant()
	 * @generated
	 */
	void setUiVariant(String value);

	/**
	 * Returns the value of the '<em><b>Reference Label Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Label Field</em>' attribute.
	 * @see #setReferenceLabelField(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getReferenceFieldSpec_ReferenceLabelField()
	 * @model
	 * @generated
	 */
	String getReferenceLabelField();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getReferenceLabelField <em>Reference Label Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Label Field</em>' attribute.
	 * @see #getReferenceLabelField()
	 * @generated
	 */
	void setReferenceLabelField(String value);

} // ReferenceFieldSpec
