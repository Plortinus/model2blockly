/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getOptions <em>Options</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageUrl <em>Image Url</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageWidth <em>Image Width</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageHeight <em>Image Height</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageAlt <em>Image Alt</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMin <em>Min</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMax <em>Max</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isRequired <em>Required</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUnique <em>Unique</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isId <em>Id</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiWidget <em>Ui Widget</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiLabel <em>Ui Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiHelp <em>Ui Help</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiGroup <em>Ui Group</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiOrder <em>Ui Order</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiReadonly <em>Ui Readonly</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiHidden <em>Ui Hidden</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiVariant <em>Ui Variant</em>}</li>
 * </ul>
 *
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec()
 * @model
 * @generated
 */
public interface FieldSpec extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Field Type</b></em>' attribute.
	 * The default value is <code>"TEXT"</code>.
	 * The literals are from the enumeration {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
	 * @see #setFieldType(FieldType)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_FieldType()
	 * @model default="TEXT"
	 * @generated
	 */
	FieldType getFieldType();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getFieldType <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Type</em>' attribute.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
	 * @see #getFieldType()
	 * @generated
	 */
	void setFieldType(FieldType value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_DefaultValue()
	 * @model
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Options()
	 * @model containment="true"
	 * @generated
	 */
	EList<DropdownOption> getOptions();

	/**
	 * Returns the value of the '<em><b>Image Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Url</em>' attribute.
	 * @see #setImageUrl(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_ImageUrl()
	 * @model
	 * @generated
	 */
	String getImageUrl();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageUrl <em>Image Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Url</em>' attribute.
	 * @see #getImageUrl()
	 * @generated
	 */
	void setImageUrl(String value);

	/**
	 * Returns the value of the '<em><b>Image Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Width</em>' attribute.
	 * @see #setImageWidth(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_ImageWidth()
	 * @model
	 * @generated
	 */
	int getImageWidth();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageWidth <em>Image Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Width</em>' attribute.
	 * @see #getImageWidth()
	 * @generated
	 */
	void setImageWidth(int value);

	/**
	 * Returns the value of the '<em><b>Image Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Height</em>' attribute.
	 * @see #setImageHeight(int)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_ImageHeight()
	 * @model
	 * @generated
	 */
	int getImageHeight();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageHeight <em>Image Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Height</em>' attribute.
	 * @see #getImageHeight()
	 * @generated
	 */
	void setImageHeight(int value);

	/**
	 * Returns the value of the '<em><b>Image Alt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Alt</em>' attribute.
	 * @see #setImageAlt(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_ImageAlt()
	 * @model
	 * @generated
	 */
	String getImageAlt();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageAlt <em>Image Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Alt</em>' attribute.
	 * @see #getImageAlt()
	 * @generated
	 */
	void setImageAlt(String value);

	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Min()
	 * @model
	 * @generated
	 */
	String getMin();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(String value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Max()
	 * @model
	 * @generated
	 */
	String getMax();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Required()
	 * @model
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isRequired <em>Required</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_LowerBound()
	 * @model
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getLowerBound <em>Lower Bound</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UpperBound()
	 * @model default="1"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUpperBound <em>Upper Bound</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Unique()
	 * @model default="true"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUnique <em>Unique</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Ordered()
	 * @model default="true"
	 * @generated
	 */
	boolean isOrdered();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isOrdered <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordered</em>' attribute.
	 * @see #isOrdered()
	 * @generated
	 */
	void setOrdered(boolean value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(boolean)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_Id()
	 * @model
	 * @generated
	 */
	boolean isId();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #isId()
	 * @generated
	 */
	void setId(boolean value);

	/**
	 * Returns the value of the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Widget</em>' attribute.
	 * @see #setUiWidget(String)
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiWidget()
	 * @model
	 * @generated
	 */
	String getUiWidget();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiWidget <em>Ui Widget</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiLabel()
	 * @model
	 * @generated
	 */
	String getUiLabel();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiLabel <em>Ui Label</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiHelp()
	 * @model
	 * @generated
	 */
	String getUiHelp();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiHelp <em>Ui Help</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiPlaceholder()
	 * @model
	 * @generated
	 */
	String getUiPlaceholder();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiGroup()
	 * @model
	 * @generated
	 */
	String getUiGroup();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiGroup <em>Ui Group</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiOrder()
	 * @model
	 * @generated
	 */
	Integer getUiOrder();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiOrder <em>Ui Order</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiReadonly()
	 * @model
	 * @generated
	 */
	boolean isUiReadonly();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiReadonly <em>Ui Readonly</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiHidden()
	 * @model
	 * @generated
	 */
	boolean isUiHidden();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiHidden <em>Ui Hidden</em>}' attribute.
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
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getFieldSpec_UiVariant()
	 * @model
	 * @generated
	 */
	String getUiVariant();

	/**
	 * Sets the value of the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiVariant <em>Ui Variant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Variant</em>' attribute.
	 * @see #getUiVariant()
	 * @generated
	 */
	void setUiVariant(String value);

} // FieldSpec
