/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Field Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getTargetTypeName <em>Target Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getOppositeName <em>Opposite Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiWidget <em>Ui Widget</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiLabel <em>Ui Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiHelp <em>Ui Help</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiPlaceholder <em>Ui Placeholder</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiGroup <em>Ui Group</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiOrder <em>Ui Order</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#isUiReadonly <em>Ui Readonly</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#isUiHidden <em>Ui Hidden</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getUiVariant <em>Ui Variant</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl#getReferenceLabelField <em>Reference Label Field</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceFieldSpecImpl extends MinimalEObjectImpl.Container implements ReferenceFieldSpec {
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
	 * The default value of the '{@link #getTargetTypeName() <em>Target Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetTypeName() <em>Target Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetTypeName()
	 * @generated
	 * @ordered
	 */
	protected String targetTypeName = TARGET_TYPE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getOppositeName() <em>Opposite Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOppositeName()
	 * @generated
	 * @ordered
	 */
	protected static final String OPPOSITE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOppositeName() <em>Opposite Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOppositeName()
	 * @generated
	 * @ordered
	 */
	protected String oppositeName = OPPOSITE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getReferenceLabelField() <em>Reference Label Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceLabelField()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_LABEL_FIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferenceLabelField() <em>Reference Label Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceLabelField()
	 * @generated
	 * @ordered
	 */
	protected String referenceLabelField = REFERENCE_LABEL_FIELD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceFieldSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.REFERENCE_FIELD_SPEC;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetTypeName() {
		return targetTypeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetTypeName(String newTargetTypeName) {
		String oldTargetTypeName = targetTypeName;
		targetTypeName = newTargetTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME, oldTargetTypeName, targetTypeName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__REQUIRED, oldRequired, required));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__LOWER_BOUND, oldLowerBound, lowerBound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UPPER_BOUND, oldUpperBound, upperBound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UNIQUE, oldUnique, unique));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOppositeName() {
		return oppositeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOppositeName(String newOppositeName) {
		String oldOppositeName = oppositeName;
		oppositeName = newOppositeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__OPPOSITE_NAME, oldOppositeName, oppositeName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_WIDGET, oldUiWidget, uiWidget));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_LABEL, oldUiLabel, uiLabel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HELP, oldUiHelp, uiHelp));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_PLACEHOLDER, oldUiPlaceholder, uiPlaceholder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_GROUP, oldUiGroup, uiGroup));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_ORDER, oldUiOrder, uiOrder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_READONLY, oldUiReadonly, uiReadonly));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HIDDEN, oldUiHidden, uiHidden));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_VARIANT, oldUiVariant, uiVariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getReferenceLabelField() {
		return referenceLabelField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferenceLabelField(String newReferenceLabelField) {
		String oldReferenceLabelField = referenceLabelField;
		referenceLabelField = newReferenceLabelField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD, oldReferenceLabelField, referenceLabelField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__NAME:
				return getName();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME:
				return getTargetTypeName();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REQUIRED:
				return isRequired();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__LOWER_BOUND:
				return getLowerBound();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UPPER_BOUND:
				return getUpperBound();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UNIQUE:
				return isUnique();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__ORDERED:
				return isOrdered();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__OPPOSITE_NAME:
				return getOppositeName();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_WIDGET:
				return getUiWidget();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_LABEL:
				return getUiLabel();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HELP:
				return getUiHelp();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_PLACEHOLDER:
				return getUiPlaceholder();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_GROUP:
				return getUiGroup();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_ORDER:
				return getUiOrder();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_READONLY:
				return isUiReadonly();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HIDDEN:
				return isUiHidden();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_VARIANT:
				return getUiVariant();
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD:
				return getReferenceLabelField();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__NAME:
				setName((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME:
				setTargetTypeName((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REQUIRED:
				setRequired((Boolean)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__ORDERED:
				setOrdered((Boolean)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__OPPOSITE_NAME:
				setOppositeName((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_WIDGET:
				setUiWidget((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_LABEL:
				setUiLabel((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HELP:
				setUiHelp((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_GROUP:
				setUiGroup((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_ORDER:
				setUiOrder((Integer)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_READONLY:
				setUiReadonly((Boolean)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HIDDEN:
				setUiHidden((Boolean)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_VARIANT:
				setUiVariant((String)newValue);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD:
				setReferenceLabelField((String)newValue);
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
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME:
				setTargetTypeName(TARGET_TYPE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REQUIRED:
				setRequired(REQUIRED_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__OPPOSITE_NAME:
				setOppositeName(OPPOSITE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_WIDGET:
				setUiWidget(UI_WIDGET_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_LABEL:
				setUiLabel(UI_LABEL_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HELP:
				setUiHelp(UI_HELP_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder(UI_PLACEHOLDER_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_GROUP:
				setUiGroup(UI_GROUP_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_ORDER:
				setUiOrder(UI_ORDER_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_READONLY:
				setUiReadonly(UI_READONLY_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HIDDEN:
				setUiHidden(UI_HIDDEN_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_VARIANT:
				setUiVariant(UI_VARIANT_EDEFAULT);
				return;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD:
				setReferenceLabelField(REFERENCE_LABEL_FIELD_EDEFAULT);
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
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME:
				return TARGET_TYPE_NAME_EDEFAULT == null ? targetTypeName != null : !TARGET_TYPE_NAME_EDEFAULT.equals(targetTypeName);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REQUIRED:
				return required != REQUIRED_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__OPPOSITE_NAME:
				return OPPOSITE_NAME_EDEFAULT == null ? oppositeName != null : !OPPOSITE_NAME_EDEFAULT.equals(oppositeName);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_WIDGET:
				return UI_WIDGET_EDEFAULT == null ? uiWidget != null : !UI_WIDGET_EDEFAULT.equals(uiWidget);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_LABEL:
				return UI_LABEL_EDEFAULT == null ? uiLabel != null : !UI_LABEL_EDEFAULT.equals(uiLabel);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HELP:
				return UI_HELP_EDEFAULT == null ? uiHelp != null : !UI_HELP_EDEFAULT.equals(uiHelp);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_PLACEHOLDER:
				return UI_PLACEHOLDER_EDEFAULT == null ? uiPlaceholder != null : !UI_PLACEHOLDER_EDEFAULT.equals(uiPlaceholder);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_GROUP:
				return UI_GROUP_EDEFAULT == null ? uiGroup != null : !UI_GROUP_EDEFAULT.equals(uiGroup);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_ORDER:
				return UI_ORDER_EDEFAULT == null ? uiOrder != null : !UI_ORDER_EDEFAULT.equals(uiOrder);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_READONLY:
				return uiReadonly != UI_READONLY_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_HIDDEN:
				return uiHidden != UI_HIDDEN_EDEFAULT;
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__UI_VARIANT:
				return UI_VARIANT_EDEFAULT == null ? uiVariant != null : !UI_VARIANT_EDEFAULT.equals(uiVariant);
			case BlocklySpecPackage.REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD:
				return REFERENCE_LABEL_FIELD_EDEFAULT == null ? referenceLabelField != null : !REFERENCE_LABEL_FIELD_EDEFAULT.equals(referenceLabelField);
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
		result.append(", targetTypeName: ");
		result.append(targetTypeName);
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
		result.append(", oppositeName: ");
		result.append(oppositeName);
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
		result.append(", referenceLabelField: ");
		result.append(referenceLabelField);
		result.append(')');
		return result.toString();
	}

} //ReferenceFieldSpecImpl
