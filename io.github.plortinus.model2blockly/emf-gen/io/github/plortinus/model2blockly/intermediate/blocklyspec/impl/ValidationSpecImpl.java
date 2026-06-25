/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validation Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getType <em>Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getTargetType <em>Target Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getPredecessorType <em>Predecessor Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getOwnerType <em>Owner Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getContainmentName <em>Containment Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getFieldKind <em>Field Kind</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getVisualBlock <em>Visual Block</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl#getVisualExpression <em>Visual Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidationSpecImpl extends MinimalEObjectImpl.Container implements ValidationSpec {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ValidationType TYPE_EDEFAULT = ValidationType.MUST_FOLLOW;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ValidationType type = TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getTargetType() <em>Target Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetType()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetType() <em>Target Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetType()
	 * @generated
	 * @ordered
	 */
	protected String targetType = TARGET_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredecessorType() <em>Predecessor Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessorType()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDECESSOR_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredecessorType() <em>Predecessor Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessorType()
	 * @generated
	 * @ordered
	 */
	protected String predecessorType = PREDECESSOR_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOwnerType() <em>Owner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnerType()
	 * @generated
	 * @ordered
	 */
	protected static final String OWNER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOwnerType() <em>Owner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnerType()
	 * @generated
	 * @ordered
	 */
	protected String ownerType = OWNER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getContainmentName() <em>Containment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainmentName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainmentName() <em>Containment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainmentName()
	 * @generated
	 * @ordered
	 */
	protected String containmentName = CONTAINMENT_NAME_EDEFAULT;

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
	protected static final int UPPER_BOUND_EDEFAULT = 0;

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
	 * The default value of the '{@link #getFieldKind() <em>Field Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldKind()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldKind() <em>Field Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldKind()
	 * @generated
	 * @ordered
	 */
	protected String fieldKind = FIELD_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected String message = MESSAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVisualBlock() <em>Visual Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualBlock()
	 * @generated
	 * @ordered
	 */
	protected ValidationBlockSpec visualBlock;

	/**
	 * The cached value of the '{@link #getVisualExpression() <em>Visual Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualExpression()
	 * @generated
	 * @ordered
	 */
	protected ValidationExpressionSpec visualExpression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidationSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.VALIDATION_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(ValidationType newType) {
		ValidationType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetType() {
		return targetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetType(String newTargetType) {
		String oldTargetType = targetType;
		targetType = newTargetType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__TARGET_TYPE, oldTargetType, targetType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPredecessorType() {
		return predecessorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPredecessorType(String newPredecessorType) {
		String oldPredecessorType = predecessorType;
		predecessorType = newPredecessorType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__PREDECESSOR_TYPE, oldPredecessorType, predecessorType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOwnerType() {
		return ownerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnerType(String newOwnerType) {
		String oldOwnerType = ownerType;
		ownerType = newOwnerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__OWNER_TYPE, oldOwnerType, ownerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContainmentName() {
		return containmentName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainmentName(String newContainmentName) {
		String oldContainmentName = containmentName;
		containmentName = newContainmentName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__CONTAINMENT_NAME, oldContainmentName, containmentName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__LOWER_BOUND, oldLowerBound, lowerBound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__UPPER_BOUND, oldUpperBound, upperBound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__FIELD_NAME, oldFieldName, fieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFieldKind() {
		return fieldKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFieldKind(String newFieldKind) {
		String oldFieldKind = fieldKind;
		fieldKind = newFieldKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__FIELD_KIND, oldFieldKind, fieldKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessage(String newMessage) {
		String oldMessage = message;
		message = newMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__MESSAGE, oldMessage, message));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationBlockSpec getVisualBlock() {
		return visualBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisualBlock(ValidationBlockSpec newVisualBlock, NotificationChain msgs) {
		ValidationBlockSpec oldVisualBlock = visualBlock;
		visualBlock = newVisualBlock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK, oldVisualBlock, newVisualBlock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisualBlock(ValidationBlockSpec newVisualBlock) {
		if (newVisualBlock != visualBlock) {
			NotificationChain msgs = null;
			if (visualBlock != null)
				msgs = ((InternalEObject)visualBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK, null, msgs);
			if (newVisualBlock != null)
				msgs = ((InternalEObject)newVisualBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK, null, msgs);
			msgs = basicSetVisualBlock(newVisualBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK, newVisualBlock, newVisualBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValidationExpressionSpec getVisualExpression() {
		return visualExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisualExpression(ValidationExpressionSpec newVisualExpression, NotificationChain msgs) {
		ValidationExpressionSpec oldVisualExpression = visualExpression;
		visualExpression = newVisualExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION, oldVisualExpression, newVisualExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisualExpression(ValidationExpressionSpec newVisualExpression) {
		if (newVisualExpression != visualExpression) {
			NotificationChain msgs = null;
			if (visualExpression != null)
				msgs = ((InternalEObject)visualExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION, null, msgs);
			if (newVisualExpression != null)
				msgs = ((InternalEObject)newVisualExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION, null, msgs);
			msgs = basicSetVisualExpression(newVisualExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION, newVisualExpression, newVisualExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK:
				return basicSetVisualBlock(null, msgs);
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION:
				return basicSetVisualExpression(null, msgs);
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
			case BlocklySpecPackage.VALIDATION_SPEC__TYPE:
				return getType();
			case BlocklySpecPackage.VALIDATION_SPEC__NAME:
				return getName();
			case BlocklySpecPackage.VALIDATION_SPEC__TARGET_TYPE:
				return getTargetType();
			case BlocklySpecPackage.VALIDATION_SPEC__PREDECESSOR_TYPE:
				return getPredecessorType();
			case BlocklySpecPackage.VALIDATION_SPEC__OWNER_TYPE:
				return getOwnerType();
			case BlocklySpecPackage.VALIDATION_SPEC__CONTAINMENT_NAME:
				return getContainmentName();
			case BlocklySpecPackage.VALIDATION_SPEC__LOWER_BOUND:
				return getLowerBound();
			case BlocklySpecPackage.VALIDATION_SPEC__UPPER_BOUND:
				return getUpperBound();
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_NAME:
				return getFieldName();
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_KIND:
				return getFieldKind();
			case BlocklySpecPackage.VALIDATION_SPEC__EXPRESSION:
				return getExpression();
			case BlocklySpecPackage.VALIDATION_SPEC__MESSAGE:
				return getMessage();
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK:
				return getVisualBlock();
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION:
				return getVisualExpression();
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
			case BlocklySpecPackage.VALIDATION_SPEC__TYPE:
				setType((ValidationType)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__NAME:
				setName((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__TARGET_TYPE:
				setTargetType((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__PREDECESSOR_TYPE:
				setPredecessorType((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__OWNER_TYPE:
				setOwnerType((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__CONTAINMENT_NAME:
				setContainmentName((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_NAME:
				setFieldName((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_KIND:
				setFieldKind((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__EXPRESSION:
				setExpression((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__MESSAGE:
				setMessage((String)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK:
				setVisualBlock((ValidationBlockSpec)newValue);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION:
				setVisualExpression((ValidationExpressionSpec)newValue);
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
			case BlocklySpecPackage.VALIDATION_SPEC__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__TARGET_TYPE:
				setTargetType(TARGET_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__PREDECESSOR_TYPE:
				setPredecessorType(PREDECESSOR_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__OWNER_TYPE:
				setOwnerType(OWNER_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__CONTAINMENT_NAME:
				setContainmentName(CONTAINMENT_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_NAME:
				setFieldName(FIELD_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_KIND:
				setFieldKind(FIELD_KIND_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__MESSAGE:
				setMessage(MESSAGE_EDEFAULT);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK:
				setVisualBlock((ValidationBlockSpec)null);
				return;
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION:
				setVisualExpression((ValidationExpressionSpec)null);
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
			case BlocklySpecPackage.VALIDATION_SPEC__TYPE:
				return type != TYPE_EDEFAULT;
			case BlocklySpecPackage.VALIDATION_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BlocklySpecPackage.VALIDATION_SPEC__TARGET_TYPE:
				return TARGET_TYPE_EDEFAULT == null ? targetType != null : !TARGET_TYPE_EDEFAULT.equals(targetType);
			case BlocklySpecPackage.VALIDATION_SPEC__PREDECESSOR_TYPE:
				return PREDECESSOR_TYPE_EDEFAULT == null ? predecessorType != null : !PREDECESSOR_TYPE_EDEFAULT.equals(predecessorType);
			case BlocklySpecPackage.VALIDATION_SPEC__OWNER_TYPE:
				return OWNER_TYPE_EDEFAULT == null ? ownerType != null : !OWNER_TYPE_EDEFAULT.equals(ownerType);
			case BlocklySpecPackage.VALIDATION_SPEC__CONTAINMENT_NAME:
				return CONTAINMENT_NAME_EDEFAULT == null ? containmentName != null : !CONTAINMENT_NAME_EDEFAULT.equals(containmentName);
			case BlocklySpecPackage.VALIDATION_SPEC__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case BlocklySpecPackage.VALIDATION_SPEC__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_NAME:
				return FIELD_NAME_EDEFAULT == null ? fieldName != null : !FIELD_NAME_EDEFAULT.equals(fieldName);
			case BlocklySpecPackage.VALIDATION_SPEC__FIELD_KIND:
				return FIELD_KIND_EDEFAULT == null ? fieldKind != null : !FIELD_KIND_EDEFAULT.equals(fieldKind);
			case BlocklySpecPackage.VALIDATION_SPEC__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case BlocklySpecPackage.VALIDATION_SPEC__MESSAGE:
				return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_BLOCK:
				return visualBlock != null;
			case BlocklySpecPackage.VALIDATION_SPEC__VISUAL_EXPRESSION:
				return visualExpression != null;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", name: ");
		result.append(name);
		result.append(", targetType: ");
		result.append(targetType);
		result.append(", predecessorType: ");
		result.append(predecessorType);
		result.append(", ownerType: ");
		result.append(ownerType);
		result.append(", containmentName: ");
		result.append(containmentName);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(", fieldName: ");
		result.append(fieldName);
		result.append(", fieldKind: ");
		result.append(fieldKind);
		result.append(", expression: ");
		result.append(expression);
		result.append(", message: ");
		result.append(message);
		result.append(')');
		return result.toString();
	}

} //ValidationSpecImpl
