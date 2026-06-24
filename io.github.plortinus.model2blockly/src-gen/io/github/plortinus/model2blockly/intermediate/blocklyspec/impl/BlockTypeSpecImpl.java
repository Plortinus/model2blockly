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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Type Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getColour <em>Colour</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getCategoryName <em>Category Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getSuperTypeName <em>Super Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getConnectionType <em>Connection Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getConnectionTypeName <em>Connection Type Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getOrderedInputNames <em>Ordered Input Names</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getOutputType <em>Output Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getMessage0 <em>Message0</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getInputsInline <em>Inputs Inline</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getHelpUrl <em>Help Url</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getCodeTemplate <em>Code Template</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getIdFieldName <em>Id Field Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getStatementInputs <em>Statement Inputs</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getReferenceFields <em>Reference Fields</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl#getValueInputs <em>Value Inputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlockTypeSpecImpl extends MinimalEObjectImpl.Container implements BlockTypeSpec {
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
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getColour() <em>Colour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColour()
	 * @generated
	 * @ordered
	 */
	protected static final int COLOUR_EDEFAULT = 200;

	/**
	 * The cached value of the '{@link #getColour() <em>Colour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColour()
	 * @generated
	 * @ordered
	 */
	protected int colour = COLOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCategoryName() <em>Category Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryName()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategoryName() <em>Category Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryName()
	 * @generated
	 * @ordered
	 */
	protected String categoryName = CATEGORY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuperTypeName() <em>Super Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String SUPER_TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuperTypeName() <em>Super Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTypeName()
	 * @generated
	 * @ordered
	 */
	protected String superTypeName = SUPER_TYPE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionType() <em>Connection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionType()
	 * @generated
	 * @ordered
	 */
	protected static final ConnectionType CONNECTION_TYPE_EDEFAULT = ConnectionType.FREE;

	/**
	 * The cached value of the '{@link #getConnectionType() <em>Connection Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionType()
	 * @generated
	 * @ordered
	 */
	protected ConnectionType connectionType = CONNECTION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionTypeName() <em>Connection Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionTypeName() <em>Connection Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionTypeName()
	 * @generated
	 * @ordered
	 */
	protected String connectionTypeName = CONNECTION_TYPE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOrderedInputNames() <em>Ordered Input Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderedInputNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> orderedInputNames;

	/**
	 * The default value of the '{@link #getOutputType() <em>Output Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputType()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputType() <em>Output Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputType()
	 * @generated
	 * @ordered
	 */
	protected String outputType = OUTPUT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessage0() <em>Message0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage0()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE0_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessage0() <em>Message0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage0()
	 * @generated
	 * @ordered
	 */
	protected String message0 = MESSAGE0_EDEFAULT;

	/**
	 * The default value of the '{@link #getInputsInline() <em>Inputs Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputsInline()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean INPUTS_INLINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInputsInline() <em>Inputs Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputsInline()
	 * @generated
	 * @ordered
	 */
	protected Boolean inputsInline = INPUTS_INLINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOLTIP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected String tooltip = TOOLTIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getHelpUrl() <em>Help Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHelpUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String HELP_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHelpUrl() <em>Help Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHelpUrl()
	 * @generated
	 * @ordered
	 */
	protected String helpUrl = HELP_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCodeTemplate() <em>Code Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_TEMPLATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodeTemplate() <em>Code Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeTemplate()
	 * @generated
	 * @ordered
	 */
	protected String codeTemplate = CODE_TEMPLATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIdFieldName() <em>Id Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdFieldName()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_FIELD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdFieldName() <em>Id Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdFieldName()
	 * @generated
	 * @ordered
	 */
	protected String idFieldName = ID_FIELD_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<FieldSpec> fields;

	/**
	 * The cached value of the '{@link #getStatementInputs() <em>Statement Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<StatementInputSpec> statementInputs;

	/**
	 * The cached value of the '{@link #getReferenceFields() <em>Reference Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceFields()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceFieldSpec> referenceFields;

	/**
	 * The cached value of the '{@link #getValueInputs() <em>Value Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueInputSpec> valueInputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockTypeSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.BLOCK_TYPE_SPEC;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getColour() {
		return colour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColour(int newColour) {
		int oldColour = colour;
		colour = newColour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__COLOUR, oldColour, colour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCategoryName(String newCategoryName) {
		String oldCategoryName = categoryName;
		categoryName = newCategoryName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__CATEGORY_NAME, oldCategoryName, categoryName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSuperTypeName() {
		return superTypeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuperTypeName(String newSuperTypeName) {
		String oldSuperTypeName = superTypeName;
		superTypeName = newSuperTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__SUPER_TYPE_NAME, oldSuperTypeName, superTypeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectionType getConnectionType() {
		return connectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionType(ConnectionType newConnectionType) {
		ConnectionType oldConnectionType = connectionType;
		connectionType = newConnectionType == null ? CONNECTION_TYPE_EDEFAULT : newConnectionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE, oldConnectionType, connectionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionTypeName() {
		return connectionTypeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionTypeName(String newConnectionTypeName) {
		String oldConnectionTypeName = connectionTypeName;
		connectionTypeName = newConnectionTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME, oldConnectionTypeName, connectionTypeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getOrderedInputNames() {
		if (orderedInputNames == null) {
			orderedInputNames = new EDataTypeUniqueEList<String>(String.class, this, BlocklySpecPackage.BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES);
		}
		return orderedInputNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOutputType() {
		return outputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutputType(String newOutputType) {
		String oldOutputType = outputType;
		outputType = newOutputType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__OUTPUT_TYPE, oldOutputType, outputType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessage0() {
		return message0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessage0(String newMessage0) {
		String oldMessage0 = message0;
		message0 = newMessage0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__MESSAGE0, oldMessage0, message0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getInputsInline() {
		return inputsInline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInputsInline(Boolean newInputsInline) {
		Boolean oldInputsInline = inputsInline;
		inputsInline = newInputsInline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__INPUTS_INLINE, oldInputsInline, inputsInline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltip(String newTooltip) {
		String oldTooltip = tooltip;
		tooltip = newTooltip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__TOOLTIP, oldTooltip, tooltip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHelpUrl() {
		return helpUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHelpUrl(String newHelpUrl) {
		String oldHelpUrl = helpUrl;
		helpUrl = newHelpUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__HELP_URL, oldHelpUrl, helpUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCodeTemplate() {
		return codeTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCodeTemplate(String newCodeTemplate) {
		String oldCodeTemplate = codeTemplate;
		codeTemplate = newCodeTemplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__CODE_TEMPLATE, oldCodeTemplate, codeTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIdFieldName() {
		return idFieldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdFieldName(String newIdFieldName) {
		String oldIdFieldName = idFieldName;
		idFieldName = newIdFieldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.BLOCK_TYPE_SPEC__ID_FIELD_NAME, oldIdFieldName, idFieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<FieldSpec> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentEList<FieldSpec>(FieldSpec.class, this, BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<StatementInputSpec> getStatementInputs() {
		if (statementInputs == null) {
			statementInputs = new EObjectContainmentEList<StatementInputSpec>(StatementInputSpec.class, this, BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS);
		}
		return statementInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ReferenceFieldSpec> getReferenceFields() {
		if (referenceFields == null) {
			referenceFields = new EObjectContainmentEList<ReferenceFieldSpec>(ReferenceFieldSpec.class, this, BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS);
		}
		return referenceFields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ValueInputSpec> getValueInputs() {
		if (valueInputs == null) {
			valueInputs = new EObjectContainmentEList<ValueInputSpec>(ValueInputSpec.class, this, BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS);
		}
		return valueInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS:
				return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS:
				return ((InternalEList<?>)getStatementInputs()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS:
				return ((InternalEList<?>)getReferenceFields()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS:
				return ((InternalEList<?>)getValueInputs()).basicRemove(otherEnd, msgs);
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
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TYPE_NAME:
				return getTypeName();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__LABEL:
				return getLabel();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__COLOUR:
				return getColour();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CATEGORY_NAME:
				return getCategoryName();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ABSTRACT:
				return isAbstract();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__SUPER_TYPE_NAME:
				return getSuperTypeName();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE:
				return getConnectionType();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME:
				return getConnectionTypeName();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES:
				return getOrderedInputNames();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__OUTPUT_TYPE:
				return getOutputType();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__MESSAGE0:
				return getMessage0();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__INPUTS_INLINE:
				return getInputsInline();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TOOLTIP:
				return getTooltip();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__HELP_URL:
				return getHelpUrl();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CODE_TEMPLATE:
				return getCodeTemplate();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ID_FIELD_NAME:
				return getIdFieldName();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS:
				return getFields();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS:
				return getStatementInputs();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS:
				return getReferenceFields();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS:
				return getValueInputs();
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
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TYPE_NAME:
				setTypeName((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__LABEL:
				setLabel((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__COLOUR:
				setColour((Integer)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CATEGORY_NAME:
				setCategoryName((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__SUPER_TYPE_NAME:
				setSuperTypeName((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE:
				setConnectionType((ConnectionType)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME:
				setConnectionTypeName((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES:
				getOrderedInputNames().clear();
				getOrderedInputNames().addAll((Collection<? extends String>)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__OUTPUT_TYPE:
				setOutputType((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__MESSAGE0:
				setMessage0((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__INPUTS_INLINE:
				setInputsInline((Boolean)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TOOLTIP:
				setTooltip((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__HELP_URL:
				setHelpUrl((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CODE_TEMPLATE:
				setCodeTemplate((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ID_FIELD_NAME:
				setIdFieldName((String)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS:
				getFields().clear();
				getFields().addAll((Collection<? extends FieldSpec>)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS:
				getStatementInputs().clear();
				getStatementInputs().addAll((Collection<? extends StatementInputSpec>)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS:
				getReferenceFields().clear();
				getReferenceFields().addAll((Collection<? extends ReferenceFieldSpec>)newValue);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS:
				getValueInputs().clear();
				getValueInputs().addAll((Collection<? extends ValueInputSpec>)newValue);
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
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TYPE_NAME:
				setTypeName(TYPE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__COLOUR:
				setColour(COLOUR_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CATEGORY_NAME:
				setCategoryName(CATEGORY_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__SUPER_TYPE_NAME:
				setSuperTypeName(SUPER_TYPE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE:
				setConnectionType(CONNECTION_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME:
				setConnectionTypeName(CONNECTION_TYPE_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES:
				getOrderedInputNames().clear();
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__OUTPUT_TYPE:
				setOutputType(OUTPUT_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__MESSAGE0:
				setMessage0(MESSAGE0_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__INPUTS_INLINE:
				setInputsInline(INPUTS_INLINE_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TOOLTIP:
				setTooltip(TOOLTIP_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__HELP_URL:
				setHelpUrl(HELP_URL_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CODE_TEMPLATE:
				setCodeTemplate(CODE_TEMPLATE_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ID_FIELD_NAME:
				setIdFieldName(ID_FIELD_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS:
				getFields().clear();
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS:
				getStatementInputs().clear();
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS:
				getReferenceFields().clear();
				return;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS:
				getValueInputs().clear();
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
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TYPE_NAME:
				return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__COLOUR:
				return colour != COLOUR_EDEFAULT;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CATEGORY_NAME:
				return CATEGORY_NAME_EDEFAULT == null ? categoryName != null : !CATEGORY_NAME_EDEFAULT.equals(categoryName);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__SUPER_TYPE_NAME:
				return SUPER_TYPE_NAME_EDEFAULT == null ? superTypeName != null : !SUPER_TYPE_NAME_EDEFAULT.equals(superTypeName);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE:
				return connectionType != CONNECTION_TYPE_EDEFAULT;
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME:
				return CONNECTION_TYPE_NAME_EDEFAULT == null ? connectionTypeName != null : !CONNECTION_TYPE_NAME_EDEFAULT.equals(connectionTypeName);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES:
				return orderedInputNames != null && !orderedInputNames.isEmpty();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__OUTPUT_TYPE:
				return OUTPUT_TYPE_EDEFAULT == null ? outputType != null : !OUTPUT_TYPE_EDEFAULT.equals(outputType);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__MESSAGE0:
				return MESSAGE0_EDEFAULT == null ? message0 != null : !MESSAGE0_EDEFAULT.equals(message0);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__INPUTS_INLINE:
				return INPUTS_INLINE_EDEFAULT == null ? inputsInline != null : !INPUTS_INLINE_EDEFAULT.equals(inputsInline);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__TOOLTIP:
				return TOOLTIP_EDEFAULT == null ? tooltip != null : !TOOLTIP_EDEFAULT.equals(tooltip);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__HELP_URL:
				return HELP_URL_EDEFAULT == null ? helpUrl != null : !HELP_URL_EDEFAULT.equals(helpUrl);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__CODE_TEMPLATE:
				return CODE_TEMPLATE_EDEFAULT == null ? codeTemplate != null : !CODE_TEMPLATE_EDEFAULT.equals(codeTemplate);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__ID_FIELD_NAME:
				return ID_FIELD_NAME_EDEFAULT == null ? idFieldName != null : !ID_FIELD_NAME_EDEFAULT.equals(idFieldName);
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__FIELDS:
				return fields != null && !fields.isEmpty();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__STATEMENT_INPUTS:
				return statementInputs != null && !statementInputs.isEmpty();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__REFERENCE_FIELDS:
				return referenceFields != null && !referenceFields.isEmpty();
			case BlocklySpecPackage.BLOCK_TYPE_SPEC__VALUE_INPUTS:
				return valueInputs != null && !valueInputs.isEmpty();
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
		result.append(" (typeName: ");
		result.append(typeName);
		result.append(", label: ");
		result.append(label);
		result.append(", colour: ");
		result.append(colour);
		result.append(", categoryName: ");
		result.append(categoryName);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", superTypeName: ");
		result.append(superTypeName);
		result.append(", connectionType: ");
		result.append(connectionType);
		result.append(", connectionTypeName: ");
		result.append(connectionTypeName);
		result.append(", orderedInputNames: ");
		result.append(orderedInputNames);
		result.append(", outputType: ");
		result.append(outputType);
		result.append(", message0: ");
		result.append(message0);
		result.append(", inputsInline: ");
		result.append(inputsInline);
		result.append(", tooltip: ");
		result.append(tooltip);
		result.append(", helpUrl: ");
		result.append(helpUrl);
		result.append(", codeTemplate: ");
		result.append(codeTemplate);
		result.append(", idFieldName: ");
		result.append(idFieldName);
		result.append(')');
		return result.toString();
	}

} //BlockTypeSpecImpl
