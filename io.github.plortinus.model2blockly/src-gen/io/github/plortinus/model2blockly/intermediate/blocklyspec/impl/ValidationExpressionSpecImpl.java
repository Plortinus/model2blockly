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
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validation Expression Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getLiteral <em>Literal</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getBlocklyBlockType <em>Blockly Block Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidationExpressionSpecImpl extends MinimalEObjectImpl.Container implements ValidationExpressionSpec {
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final ValidationExpressionKind KIND_EDEFAULT = ValidationExpressionKind.LOGIC_OPERATION;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected ValidationExpressionKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected String operator = OPERATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected String fieldName = FIELD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected String typeName = TYPE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLiteral() <em>Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteral()
	 * @generated
	 * @ordered
	 */
	protected static final String LITERAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLiteral() <em>Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteral()
	 * @generated
	 * @ordered
	 */
	protected String literal = LITERAL_EDEFAULT;

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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ValidationExpressionSpec> children;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidationExpressionSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.VALIDATION_EXPRESSION_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationExpressionKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(ValidationExpressionKind newKind) {
		ValidationExpressionKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperator(String newOperator) {
		String oldOperator = operator;
		operator = newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFieldName(String newFieldName) {
		String oldFieldName = fieldName;
		fieldName = newFieldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__FIELD_NAME, oldFieldName, fieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTypeName(String newTypeName) {
		String oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLiteral(String newLiteral) {
		String oldLiteral = literal;
		literal = newLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__LITERAL, oldLiteral, literal));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE, oldBlocklyBlockType, blocklyBlockType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ValidationExpressionSpec> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<ValidationExpressionSpec>(ValidationExpressionSpec.class, this, BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__KIND:
				return getKind();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__OPERATOR:
				return getOperator();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__FIELD_NAME:
				return getFieldName();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__TYPE_NAME:
				return getTypeName();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__LITERAL:
				return getLiteral();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE:
				return getBlocklyBlockType();
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN:
				return getChildren();
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
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__KIND:
				setKind((ValidationExpressionKind)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__OPERATOR:
				setOperator((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__FIELD_NAME:
				setFieldName((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__TYPE_NAME:
				setTypeName((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__LITERAL:
				setLiteral((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE:
				setBlocklyBlockType((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ValidationExpressionSpec>)newValue);
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
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__FIELD_NAME:
				setFieldName(FIELD_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__TYPE_NAME:
				setTypeName(TYPE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__LITERAL:
				setLiteral(LITERAL_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE:
				setBlocklyBlockType(BLOCKLY_BLOCK_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN:
				getChildren().clear();
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
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__KIND:
				return kind != KIND_EDEFAULT;
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__OPERATOR:
				return OPERATOR_EDEFAULT == null ? operator != null : !OPERATOR_EDEFAULT.equals(operator);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__FIELD_NAME:
				return FIELD_NAME_EDEFAULT == null ? fieldName != null : !FIELD_NAME_EDEFAULT.equals(fieldName);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__TYPE_NAME:
				return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__LITERAL:
				return LITERAL_EDEFAULT == null ? literal != null : !LITERAL_EDEFAULT.equals(literal);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE:
				return BLOCKLY_BLOCK_TYPE_EDEFAULT == null ? blocklyBlockType != null : !BLOCKLY_BLOCK_TYPE_EDEFAULT.equals(blocklyBlockType);
			case BlocklySpecPackage.VALIDATION_EXPRESSION_SPEC__CHILDREN:
				return children != null && !children.isEmpty();
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", operator: ");
		result.append(operator);
		result.append(", fieldName: ");
		result.append(fieldName);
		result.append(", typeName: ");
		result.append(typeName);
		result.append(", literal: ");
		result.append(literal);
		result.append(", blocklyBlockType: ");
		result.append(blocklyBlockType);
		result.append(')');
		return result.toString();
	}

} //ValidationExpressionSpecImpl
