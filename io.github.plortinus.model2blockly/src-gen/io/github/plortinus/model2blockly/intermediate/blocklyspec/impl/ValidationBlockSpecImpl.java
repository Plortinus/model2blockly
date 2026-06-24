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
import io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validation Block Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl#getBlocklyBlockType <em>Blockly Block Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidationBlockSpecImpl extends MinimalEObjectImpl.Container implements ValidationBlockSpec {
	/**
	 * The default value of the '{@link #getBlocklyBlockType() <em>Blockly Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlocklyBlockType()
	 * @generated
	 * @ordered
	 */
	protected static final String BLOCKLY_BLOCK_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBlocklyBlockType() <em>Blockly Block Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlocklyBlockType()
	 * @generated
	 * @ordered
	 */
	protected String blocklyBlockType = BLOCKLY_BLOCK_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedValue> fields;

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ValidationBlockInput> inputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidationBlockSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.VALIDATION_BLOCK_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBlocklyBlockType() {
		return blocklyBlockType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBlocklyBlockType(String newBlocklyBlockType) {
		String oldBlocklyBlockType = blocklyBlockType;
		blocklyBlockType = newBlocklyBlockType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE, oldBlocklyBlockType, blocklyBlockType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<NamedValue> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentEList<NamedValue>(NamedValue.class, this, BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ValidationBlockInput> getInputs() {
		if (inputs == null) {
			inputs = new EObjectContainmentEList<ValidationBlockInput>(ValidationBlockInput.class, this, BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS:
				return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
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
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE:
				return getBlocklyBlockType();
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS:
				return getFields();
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS:
				return getInputs();
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
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE:
				setBlocklyBlockType((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS:
				getFields().clear();
				getFields().addAll((Collection<? extends NamedValue>)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends ValidationBlockInput>)newValue);
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
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE:
				setBlocklyBlockType(BLOCKLY_BLOCK_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS:
				getFields().clear();
				return;
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS:
				getInputs().clear();
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
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE:
				return BLOCKLY_BLOCK_TYPE_EDEFAULT == null ? blocklyBlockType != null : !BLOCKLY_BLOCK_TYPE_EDEFAULT.equals(blocklyBlockType);
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__FIELDS:
				return fields != null && !fields.isEmpty();
			case BlocklySpecPackage.VALIDATION_BLOCK_SPEC__INPUTS:
				return inputs != null && !inputs.isEmpty();
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
		result.append(" (blocklyBlockType: ");
		result.append(blocklyBlockType);
		result.append(')');
		return result.toString();
	}

} //ValidationBlockSpecImpl
