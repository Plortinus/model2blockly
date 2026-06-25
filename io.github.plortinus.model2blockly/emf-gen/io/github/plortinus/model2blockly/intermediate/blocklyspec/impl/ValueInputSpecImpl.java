/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Input Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getCheckType <em>Check Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getShadowBlockType <em>Shadow Block Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiWidget <em>Ui Widget</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiLabel <em>Ui Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiHelp <em>Ui Help</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiPlaceholder <em>Ui Placeholder</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiGroup <em>Ui Group</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiOrder <em>Ui Order</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#isUiReadonly <em>Ui Readonly</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#isUiHidden <em>Ui Hidden</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl#getUiVariant <em>Ui Variant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValueInputSpecImpl extends MinimalEObjectImpl.Container implements ValueInputSpec {
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
	 * The default value of the '{@link #getCheckType() <em>Check Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckType()
	 * @generated
	 * @ordered
	 */
	protected static final String CHECK_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCheckType() <em>Check Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckType()
	 * @generated
	 * @ordered
	 */
	protected String checkType = CHECK_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getShadowBlockType() <em>Shadow Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShadowBlockType()
	 * @generated
	 * @ordered
	 */
	protected static final String SHADOW_BLOCK_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShadowBlockType() <em>Shadow Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShadowBlockType()
	 * @generated
	 * @ordered
	 */
	protected String shadowBlockType = SHADOW_BLOCK_TYPE_EDEFAULT;

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
	protected ValueInputSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.VALUE_INPUT_SPEC;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCheckType() {
		return checkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCheckType(String newCheckType) {
		String oldCheckType = checkType;
		checkType = newCheckType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__CHECK_TYPE, oldCheckType, checkType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getShadowBlockType() {
		return shadowBlockType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShadowBlockType(String newShadowBlockType) {
		String oldShadowBlockType = shadowBlockType;
		shadowBlockType = newShadowBlockType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE, oldShadowBlockType, shadowBlockType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_WIDGET, oldUiWidget, uiWidget));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_LABEL, oldUiLabel, uiLabel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HELP, oldUiHelp, uiHelp));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_PLACEHOLDER, oldUiPlaceholder, uiPlaceholder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_GROUP, oldUiGroup, uiGroup));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_ORDER, oldUiOrder, uiOrder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_READONLY, oldUiReadonly, uiReadonly));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HIDDEN, oldUiHidden, uiHidden));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALUE_INPUT_SPEC__UI_VARIANT, oldUiVariant, uiVariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BlocklySpecPackage.VALUE_INPUT_SPEC__NAME:
				return getName();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__CHECK_TYPE:
				return getCheckType();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE:
				return getShadowBlockType();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_WIDGET:
				return getUiWidget();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_LABEL:
				return getUiLabel();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HELP:
				return getUiHelp();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_PLACEHOLDER:
				return getUiPlaceholder();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_GROUP:
				return getUiGroup();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_ORDER:
				return getUiOrder();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_READONLY:
				return isUiReadonly();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HIDDEN:
				return isUiHidden();
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_VARIANT:
				return getUiVariant();
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
			case BlocklySpecPackage.VALUE_INPUT_SPEC__NAME:
				setName((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__CHECK_TYPE:
				setCheckType((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE:
				setShadowBlockType((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_WIDGET:
				setUiWidget((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_LABEL:
				setUiLabel((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HELP:
				setUiHelp((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_GROUP:
				setUiGroup((String)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_ORDER:
				setUiOrder((Integer)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_READONLY:
				setUiReadonly((Boolean)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HIDDEN:
				setUiHidden((Boolean)newValue);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_VARIANT:
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
			case BlocklySpecPackage.VALUE_INPUT_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__CHECK_TYPE:
				setCheckType(CHECK_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE:
				setShadowBlockType(SHADOW_BLOCK_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_WIDGET:
				setUiWidget(UI_WIDGET_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_LABEL:
				setUiLabel(UI_LABEL_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HELP:
				setUiHelp(UI_HELP_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_PLACEHOLDER:
				setUiPlaceholder(UI_PLACEHOLDER_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_GROUP:
				setUiGroup(UI_GROUP_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_ORDER:
				setUiOrder(UI_ORDER_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_READONLY:
				setUiReadonly(UI_READONLY_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HIDDEN:
				setUiHidden(UI_HIDDEN_EDEFAULT);
				return;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_VARIANT:
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
			case BlocklySpecPackage.VALUE_INPUT_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__CHECK_TYPE:
				return CHECK_TYPE_EDEFAULT == null ? checkType != null : !CHECK_TYPE_EDEFAULT.equals(checkType);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE:
				return SHADOW_BLOCK_TYPE_EDEFAULT == null ? shadowBlockType != null : !SHADOW_BLOCK_TYPE_EDEFAULT.equals(shadowBlockType);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_WIDGET:
				return UI_WIDGET_EDEFAULT == null ? uiWidget != null : !UI_WIDGET_EDEFAULT.equals(uiWidget);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_LABEL:
				return UI_LABEL_EDEFAULT == null ? uiLabel != null : !UI_LABEL_EDEFAULT.equals(uiLabel);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HELP:
				return UI_HELP_EDEFAULT == null ? uiHelp != null : !UI_HELP_EDEFAULT.equals(uiHelp);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_PLACEHOLDER:
				return UI_PLACEHOLDER_EDEFAULT == null ? uiPlaceholder != null : !UI_PLACEHOLDER_EDEFAULT.equals(uiPlaceholder);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_GROUP:
				return UI_GROUP_EDEFAULT == null ? uiGroup != null : !UI_GROUP_EDEFAULT.equals(uiGroup);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_ORDER:
				return UI_ORDER_EDEFAULT == null ? uiOrder != null : !UI_ORDER_EDEFAULT.equals(uiOrder);
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_READONLY:
				return uiReadonly != UI_READONLY_EDEFAULT;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_HIDDEN:
				return uiHidden != UI_HIDDEN_EDEFAULT;
			case BlocklySpecPackage.VALUE_INPUT_SPEC__UI_VARIANT:
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
		result.append(", checkType: ");
		result.append(checkType);
		result.append(", shadowBlockType: ");
		result.append(shadowBlockType);
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

} //ValueInputSpecImpl
