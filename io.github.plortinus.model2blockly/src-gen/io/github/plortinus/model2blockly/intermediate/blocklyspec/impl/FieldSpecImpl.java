/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getImageUrl <em>Image Url</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getImageWidth <em>Image Width</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getImageHeight <em>Image Height</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getImageAlt <em>Image Alt</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getMin <em>Min</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getMax <em>Max</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isId <em>Id</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiWidget <em>Ui Widget</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiLabel <em>Ui Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiHelp <em>Ui Help</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiPlaceholder <em>Ui Placeholder</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiGroup <em>Ui Group</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiOrder <em>Ui Order</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isUiReadonly <em>Ui Readonly</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#isUiHidden <em>Ui Hidden</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl#getUiVariant <em>Ui Variant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FieldSpecImpl extends MinimalEObjectImpl.Container implements FieldSpec {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldType()
	 * @generated
	 * @ordered
	 */
	protected static final FieldType FIELD_TYPE_EDEFAULT = FieldType.TEXT;

	/**
	 * The cached value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldType()
	 * @generated
	 * @ordered
	 */
	protected FieldType fieldType = FIELD_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList<DropdownOption> options;

	/**
	 * The default value of the '{@link #getImageUrl() <em>Image Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImageUrl() <em>Image Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageUrl()
	 * @generated
	 * @ordered
	 */
	protected String imageUrl = IMAGE_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getImageWidth() <em>Image Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int IMAGE_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getImageWidth() <em>Image Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageWidth()
	 * @generated
	 * @ordered
	 */
	protected int imageWidth = IMAGE_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getImageHeight() <em>Image Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int IMAGE_HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getImageHeight() <em>Image Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageHeight()
	 * @generated
	 * @ordered
	 */
	protected int imageHeight = IMAGE_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getImageAlt() <em>Image Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageAlt()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_ALT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImageAlt() <em>Image Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageAlt()
	 * @generated
	 * @ordered
	 */
	protected String imageAlt = IMAGE_ALT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMin()
	 * @generated
	 * @ordered
	 */
	protected String min = MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMax()
	 * @generated
	 * @ordered
	 */
	protected String max = MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected boolean required = REQUIRED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #isId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isId()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isId()
	 * @generated
	 * @ordered
	 */
	protected boolean id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiWidget() <em>Ui Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiWidget()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_WIDGET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiWidget() <em>Ui Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiWidget()
	 * @generated
	 * @ordered
	 */
	protected String uiWidget = UI_WIDGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiLabel() <em>Ui Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiLabel() <em>Ui Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiLabel()
	 * @generated
	 * @ordered
	 */
	protected String uiLabel = UI_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiHelp() <em>Ui Help</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiHelp()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_HELP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiHelp() <em>Ui Help</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiHelp()
	 * @generated
	 * @ordered
	 */
	protected String uiHelp = UI_HELP_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiPlaceholder() <em>Ui Placeholder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiPlaceholder()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_PLACEHOLDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiPlaceholder() <em>Ui Placeholder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiPlaceholder()
	 * @generated
	 * @ordered
	 */
	protected String uiPlaceholder = UI_PLACEHOLDER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiGroup() <em>Ui Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiGroup() <em>Ui Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiGroup()
	 * @generated
	 * @ordered
	 */
	protected String uiGroup = UI_GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiOrder() <em>Ui Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiOrder()
	 * @generated
	 * @ordered
	 */
	protected static final Integer UI_ORDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiOrder() <em>Ui Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiOrder()
	 * @generated
	 * @ordered
	 */
	protected Integer uiOrder = UI_ORDER_EDEFAULT;

	/**
	 * The default value of the '{@link #isUiReadonly() <em>Ui Readonly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUiReadonly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UI_READONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUiReadonly() <em>Ui Readonly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUiReadonly()
	 * @generated
	 * @ordered
	 */
	protected boolean uiReadonly = UI_READONLY_EDEFAULT;

	/**
	 * The default value of the '{@link #isUiHidden() <em>Ui Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUiHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UI_HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUiHidden() <em>Ui Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUiHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean uiHidden = UI_HIDDEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getUiVariant() <em>Ui Variant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiVariant()
	 * @generated
	 * @ordered
	 */
	protected static final String UI_VARIANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUiVariant() <em>Ui Variant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiVariant()
	 * @generated
	 * @ordered
	 */
	protected String uiVariant = UI_VARIANT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.FIELD_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FieldType getFieldType() {
		return fieldType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFieldType(FieldType newFieldType) {
		FieldType oldFieldType = fieldType;
		fieldType = newFieldType == null ? FIELD_TYPE_EDEFAULT : newFieldType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__FIELD_TYPE, oldFieldType, fieldType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DropdownOption> getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList<DropdownOption>(DropdownOption.class, this, BlocklySpecPackage.FIELD_SPEC__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImageUrl(String newImageUrl) {
		String oldImageUrl = imageUrl;
		imageUrl = newImageUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__IMAGE_URL, oldImageUrl, imageUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImageWidth(int newImageWidth) {
		int oldImageWidth = imageWidth;
		imageWidth = newImageWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__IMAGE_WIDTH, oldImageWidth, imageWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImageHeight(int newImageHeight) {
		int oldImageHeight = imageHeight;
		imageHeight = newImageHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__IMAGE_HEIGHT, oldImageHeight, imageHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImageAlt() {
		return imageAlt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImageAlt(String newImageAlt) {
		String oldImageAlt = imageAlt;
		imageAlt = newImageAlt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__IMAGE_ALT, oldImageAlt, imageAlt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMin() {
		return min;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMin(String newMin) {
		String oldMin = min;
		min = newMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__MIN, oldMin, min));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMax() {
		return max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMax(String newMax) {
		String oldMax = max;
		max = newMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__MAX, oldMax, max));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequired() {
		return required;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__REQUIRED, oldRequired, required));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__LOWER_BOUND, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UPPER_BOUND, oldUpperBound, upperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrdered(boolean newOrdered) {
		boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(boolean newId) {
		boolean oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiWidget() {
		return uiWidget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiWidget(String newUiWidget) {
		String oldUiWidget = uiWidget;
		uiWidget = newUiWidget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_WIDGET, oldUiWidget, uiWidget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiLabel() {
		return uiLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiLabel(String newUiLabel) {
		String oldUiLabel = uiLabel;
		uiLabel = newUiLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_LABEL, oldUiLabel, uiLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiHelp() {
		return uiHelp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiHelp(String newUiHelp) {
		String oldUiHelp = uiHelp;
		uiHelp = newUiHelp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_HELP, oldUiHelp, uiHelp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiPlaceholder() {
		return uiPlaceholder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiPlaceholder(String newUiPlaceholder) {
		String oldUiPlaceholder = uiPlaceholder;
		uiPlaceholder = newUiPlaceholder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_PLACEHOLDER, oldUiPlaceholder, uiPlaceholder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiGroup() {
		return uiGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiGroup(String newUiGroup) {
		String oldUiGroup = uiGroup;
		uiGroup = newUiGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_GROUP, oldUiGroup, uiGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getUiOrder() {
		return uiOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiOrder(Integer newUiOrder) {
		Integer oldUiOrder = uiOrder;
		uiOrder = newUiOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_ORDER, oldUiOrder, uiOrder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUiReadonly() {
		return uiReadonly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiReadonly(boolean newUiReadonly) {
		boolean oldUiReadonly = uiReadonly;
		uiReadonly = newUiReadonly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_READONLY, oldUiReadonly, uiReadonly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUiHidden() {
		return uiHidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiHidden(boolean newUiHidden) {
		boolean oldUiHidden = uiHidden;
		uiHidden = newUiHidden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_HIDDEN, oldUiHidden, uiHidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUiVariant() {
		return uiVariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUiVariant(String newUiVariant) {
		String oldUiVariant = uiVariant;
		uiVariant = newUiVariant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.FIELD_SPEC__UI_VARIANT, oldUiVariant, uiVariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.FIELD_SPEC__OPTIONS:
				return ((InternalEList<?>)getOptions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BlocklySpecPackage.FIELD_SPEC__NAME:
				return getName();
			case BlocklySpecPackage.FIELD_SPEC__FIELD_TYPE:
				return getFieldType();
			case BlocklySpecPackage.FIELD_SPEC__DEFAULT_VALUE:
				return getDefaultValue();
			case BlocklySpecPackage.FIELD_SPEC__OPTIONS:
				return getOptions();
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_URL:
				return getImageUrl();
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_WIDTH:
				return getImageWidth();
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_HEIGHT:
				return getImageHeight();
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_ALT:
				return getImageAlt();
			case BlocklySpecPackage.FIELD_SPEC__MIN:
				return getMin();
			case BlocklySpecPackage.FIELD_SPEC__MAX:
				return getMax();
			case BlocklySpecPackage.FIELD_SPEC__REQUIRED:
				return isRequired();
			case BlocklySpecPackage.FIELD_SPEC__LOWER_BOUND:
				return getLowerBound();
			case BlocklySpecPackage.FIELD_SPEC__UPPER_BOUND:
				return getUpperBound();
			case BlocklySpecPackage.FIELD_SPEC__UNIQUE:
				return isUnique();
			case BlocklySpecPackage.FIELD_SPEC__ORDERED:
				return isOrdered();
			case BlocklySpecPackage.FIELD_SPEC__ID:
				return isId();
			case BlocklySpecPackage.FIELD_SPEC__UI_WIDGET:
				return getUiWidget();
			case BlocklySpecPackage.FIELD_SPEC__UI_LABEL:
				return getUiLabel();
			case BlocklySpecPackage.FIELD_SPEC__UI_HELP:
				return getUiHelp();
			case BlocklySpecPackage.FIELD_SPEC__UI_PLACEHOLDER:
				return getUiPlaceholder();
			case BlocklySpecPackage.FIELD_SPEC__UI_GROUP:
				return getUiGroup();
			case BlocklySpecPackage.FIELD_SPEC__UI_ORDER:
				return getUiOrder();
			case BlocklySpecPackage.FIELD_SPEC__UI_READONLY:
				return isUiReadonly();
			case BlocklySpecPackage.FIELD_SPEC__UI_HIDDEN:
				return isUiHidden();
			case BlocklySpecPackage.FIELD_SPEC__UI_VARIANT:
				return getUiVariant();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BlocklySpecPackage.FIELD_SPEC__NAME:
				setName((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__FIELD_TYPE:
				setFieldType((FieldType)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection<? extends DropdownOption>)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_URL:
				setImageUrl((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_WIDTH:
				setImageWidth((Integer)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_HEIGHT:
				setImageHeight((Integer)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_ALT:
				setImageAlt((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__MIN:
				setMin((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__MAX:
				setMax((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__REQUIRED:
				setRequired((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__ORDERED:
				setOrdered((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__ID:
				setId((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_WIDGET:
				setUiWidget((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_LABEL:
				setUiLabel((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_HELP:
				setUiHelp((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_GROUP:
				setUiGroup((String)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_ORDER:
				setUiOrder((Integer)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_READONLY:
				setUiReadonly((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_HIDDEN:
				setUiHidden((Boolean)newValue);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_VARIANT:
				setUiVariant((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BlocklySpecPackage.FIELD_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__FIELD_TYPE:
				setFieldType(FIELD_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__OPTIONS:
				getOptions().clear();
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_URL:
				setImageUrl(IMAGE_URL_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_WIDTH:
				setImageWidth(IMAGE_WIDTH_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_HEIGHT:
				setImageHeight(IMAGE_HEIGHT_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_ALT:
				setImageAlt(IMAGE_ALT_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__MIN:
				setMin(MIN_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__MAX:
				setMax(MAX_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__REQUIRED:
				setRequired(REQUIRED_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__ID:
				setId(ID_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_WIDGET:
				setUiWidget(UI_WIDGET_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_LABEL:
				setUiLabel(UI_LABEL_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_HELP:
				setUiHelp(UI_HELP_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder(UI_PLACEHOLDER_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_GROUP:
				setUiGroup(UI_GROUP_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_ORDER:
				setUiOrder(UI_ORDER_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_READONLY:
				setUiReadonly(UI_READONLY_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_HIDDEN:
				setUiHidden(UI_HIDDEN_EDEFAULT);
				return;
			case BlocklySpecPackage.FIELD_SPEC__UI_VARIANT:
				setUiVariant(UI_VARIANT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BlocklySpecPackage.FIELD_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BlocklySpecPackage.FIELD_SPEC__FIELD_TYPE:
				return fieldType != FIELD_TYPE_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case BlocklySpecPackage.FIELD_SPEC__OPTIONS:
				return options != null && !options.isEmpty();
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_URL:
				return IMAGE_URL_EDEFAULT == null ? imageUrl != null : !IMAGE_URL_EDEFAULT.equals(imageUrl);
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_WIDTH:
				return imageWidth != IMAGE_WIDTH_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_HEIGHT:
				return imageHeight != IMAGE_HEIGHT_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__IMAGE_ALT:
				return IMAGE_ALT_EDEFAULT == null ? imageAlt != null : !IMAGE_ALT_EDEFAULT.equals(imageAlt);
			case BlocklySpecPackage.FIELD_SPEC__MIN:
				return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
			case BlocklySpecPackage.FIELD_SPEC__MAX:
				return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
			case BlocklySpecPackage.FIELD_SPEC__REQUIRED:
				return required != REQUIRED_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__ID:
				return id != ID_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__UI_WIDGET:
				return UI_WIDGET_EDEFAULT == null ? uiWidget != null : !UI_WIDGET_EDEFAULT.equals(uiWidget);
			case BlocklySpecPackage.FIELD_SPEC__UI_LABEL:
				return UI_LABEL_EDEFAULT == null ? uiLabel != null : !UI_LABEL_EDEFAULT.equals(uiLabel);
			case BlocklySpecPackage.FIELD_SPEC__UI_HELP:
				return UI_HELP_EDEFAULT == null ? uiHelp != null : !UI_HELP_EDEFAULT.equals(uiHelp);
			case BlocklySpecPackage.FIELD_SPEC__UI_PLACEHOLDER:
				return UI_PLACEHOLDER_EDEFAULT == null ? uiPlaceholder != null : !UI_PLACEHOLDER_EDEFAULT.equals(uiPlaceholder);
			case BlocklySpecPackage.FIELD_SPEC__UI_GROUP:
				return UI_GROUP_EDEFAULT == null ? uiGroup != null : !UI_GROUP_EDEFAULT.equals(uiGroup);
			case BlocklySpecPackage.FIELD_SPEC__UI_ORDER:
				return UI_ORDER_EDEFAULT == null ? uiOrder != null : !UI_ORDER_EDEFAULT.equals(uiOrder);
			case BlocklySpecPackage.FIELD_SPEC__UI_READONLY:
				return uiReadonly != UI_READONLY_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__UI_HIDDEN:
				return uiHidden != UI_HIDDEN_EDEFAULT;
			case BlocklySpecPackage.FIELD_SPEC__UI_VARIANT:
				return UI_VARIANT_EDEFAULT == null ? uiVariant != null : !UI_VARIANT_EDEFAULT.equals(uiVariant);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", fieldType: ");
		result.append(fieldType);
		result.append(", defaultValue: ");
		result.append(defaultValue);
		result.append(", imageUrl: ");
		result.append(imageUrl);
		result.append(", imageWidth: ");
		result.append(imageWidth);
		result.append(", imageHeight: ");
		result.append(imageHeight);
		result.append(", imageAlt: ");
		result.append(imageAlt);
		result.append(", min: ");
		result.append(min);
		result.append(", max: ");
		result.append(max);
		result.append(", required: ");
		result.append(required);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(", unique: ");
		result.append(unique);
		result.append(", ordered: ");
		result.append(ordered);
		result.append(", id: ");
		result.append(id);
		result.append(", uiWidget: ");
		result.append(uiWidget);
		result.append(", uiLabel: ");
		result.append(uiLabel);
		result.append(", uiHelp: ");
		result.append(uiHelp);
		result.append(", uiPlaceholder: ");
		result.append(uiPlaceholder);
		result.append(", uiGroup: ");
		result.append(uiGroup);
		result.append(", uiOrder: ");
		result.append(uiOrder);
		result.append(", uiReadonly: ");
		result.append(uiReadonly);
		result.append(", uiHidden: ");
		result.append(uiHidden);
		result.append(", uiVariant: ");
		result.append(uiVariant);
		result.append(')');
		return result.toString();
	}

} //FieldSpecImpl
