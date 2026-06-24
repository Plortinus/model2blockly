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

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Editor Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getDomainName <em>Domain Name</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getNsPrefix <em>Ns Prefix</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getToolboxType <em>Toolbox Type</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getCodeLanguage <em>Code Language</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getCodeFileExtension <em>Code File Extension</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getRuntimeKind <em>Runtime Kind</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getBlockTypes <em>Block Types</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getValidations <em>Validations</em>}</li>
 *   <li>{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl#getWorkspaceOptions <em>Workspace Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EditorSpecImpl extends MinimalEObjectImpl.Container implements EditorSpec {
	/**
	 * The default value of the '{@link #getDomainName() <em>Domain Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainName()
	 * @generated
	 * @ordered
	 */
	protected static final String DOMAIN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDomainName() <em>Domain Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainName()
	 * @generated
	 * @ordered
	 */
	protected String domainName = DOMAIN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsURI()
	 * @generated
	 * @ordered
	 */
	protected static final String NS_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsURI()
	 * @generated
	 * @ordered
	 */
	protected String nsURI = NS_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String NS_PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNsPrefix()
	 * @generated
	 * @ordered
	 */
	protected String nsPrefix = NS_PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getToolboxType() <em>Toolbox Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToolboxType()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOLBOX_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToolboxType() <em>Toolbox Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToolboxType()
	 * @generated
	 * @ordered
	 */
	protected String toolboxType = TOOLBOX_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCodeLanguage() <em>Code Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodeLanguage() <em>Code Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeLanguage()
	 * @generated
	 * @ordered
	 */
	protected String codeLanguage = CODE_LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCodeFileExtension() <em>Code File Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeFileExtension()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_FILE_EXTENSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodeFileExtension() <em>Code File Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeFileExtension()
	 * @generated
	 * @ordered
	 */
	protected String codeFileExtension = CODE_FILE_EXTENSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRuntimeKind() <em>Runtime Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeKind()
	 * @generated
	 * @ordered
	 */
	protected static final String RUNTIME_KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuntimeKind() <em>Runtime Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeKind()
	 * @generated
	 * @ordered
	 */
	protected String runtimeKind = RUNTIME_KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<CategorySpec> categories;

	/**
	 * The cached value of the '{@link #getBlockTypes() <em>Block Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<BlockTypeSpec> blockTypes;

	/**
	 * The cached value of the '{@link #getValidations() <em>Validations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidations()
	 * @generated
	 * @ordered
	 */
	protected EList<ValidationSpec> validations;

	/**
	 * The cached value of the '{@link #getWorkspaceOptions() <em>Workspace Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspaceOptions()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkspaceOption> workspaceOptions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditorSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BlocklySpecPackage.Literals.EDITOR_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDomainName() {
		return domainName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDomainName(String newDomainName) {
		String oldDomainName = domainName;
		domainName = newDomainName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__DOMAIN_NAME, oldDomainName, domainName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNsURI() {
		return nsURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNsURI(String newNsURI) {
		String oldNsURI = nsURI;
		nsURI = newNsURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__NS_URI, oldNsURI, nsURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNsPrefix() {
		return nsPrefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNsPrefix(String newNsPrefix) {
		String oldNsPrefix = nsPrefix;
		nsPrefix = newNsPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__NS_PREFIX, oldNsPrefix, nsPrefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getToolboxType() {
		return toolboxType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setToolboxType(String newToolboxType) {
		String oldToolboxType = toolboxType;
		toolboxType = newToolboxType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__TOOLBOX_TYPE, oldToolboxType, toolboxType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCodeLanguage() {
		return codeLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCodeLanguage(String newCodeLanguage) {
		String oldCodeLanguage = codeLanguage;
		codeLanguage = newCodeLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__CODE_LANGUAGE, oldCodeLanguage, codeLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCodeFileExtension() {
		return codeFileExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCodeFileExtension(String newCodeFileExtension) {
		String oldCodeFileExtension = codeFileExtension;
		codeFileExtension = newCodeFileExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__CODE_FILE_EXTENSION, oldCodeFileExtension, codeFileExtension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRuntimeKind() {
		return runtimeKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRuntimeKind(String newRuntimeKind) {
		String oldRuntimeKind = runtimeKind;
		runtimeKind = newRuntimeKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BlocklySpecPackage.EDITOR_SPEC__RUNTIME_KIND, oldRuntimeKind, runtimeKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CategorySpec> getCategories() {
		if (categories == null) {
			categories = new EObjectContainmentEList<CategorySpec>(CategorySpec.class, this, BlocklySpecPackage.EDITOR_SPEC__CATEGORIES);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BlockTypeSpec> getBlockTypes() {
		if (blockTypes == null) {
			blockTypes = new EObjectContainmentEList<BlockTypeSpec>(BlockTypeSpec.class, this, BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES);
		}
		return blockTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ValidationSpec> getValidations() {
		if (validations == null) {
			validations = new EObjectContainmentEList<ValidationSpec>(ValidationSpec.class, this, BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS);
		}
		return validations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<WorkspaceOption> getWorkspaceOptions() {
		if (workspaceOptions == null) {
			workspaceOptions = new EObjectContainmentEList<WorkspaceOption>(WorkspaceOption.class, this, BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS);
		}
		return workspaceOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BlocklySpecPackage.EDITOR_SPEC__CATEGORIES:
				return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES:
				return ((InternalEList<?>)getBlockTypes()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS:
				return ((InternalEList<?>)getValidations()).basicRemove(otherEnd, msgs);
			case BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS:
				return ((InternalEList<?>)getWorkspaceOptions()).basicRemove(otherEnd, msgs);
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
			case BlocklySpecPackage.EDITOR_SPEC__DOMAIN_NAME:
				return getDomainName();
			case BlocklySpecPackage.EDITOR_SPEC__NS_URI:
				return getNsURI();
			case BlocklySpecPackage.EDITOR_SPEC__NS_PREFIX:
				return getNsPrefix();
			case BlocklySpecPackage.EDITOR_SPEC__TOOLBOX_TYPE:
				return getToolboxType();
			case BlocklySpecPackage.EDITOR_SPEC__CODE_LANGUAGE:
				return getCodeLanguage();
			case BlocklySpecPackage.EDITOR_SPEC__CODE_FILE_EXTENSION:
				return getCodeFileExtension();
			case BlocklySpecPackage.EDITOR_SPEC__RUNTIME_KIND:
				return getRuntimeKind();
			case BlocklySpecPackage.EDITOR_SPEC__CATEGORIES:
				return getCategories();
			case BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES:
				return getBlockTypes();
			case BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS:
				return getValidations();
			case BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS:
				return getWorkspaceOptions();
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
			case BlocklySpecPackage.EDITOR_SPEC__DOMAIN_NAME:
				setDomainName((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__NS_URI:
				setNsURI((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__NS_PREFIX:
				setNsPrefix((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__TOOLBOX_TYPE:
				setToolboxType((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CODE_LANGUAGE:
				setCodeLanguage((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CODE_FILE_EXTENSION:
				setCodeFileExtension((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__RUNTIME_KIND:
				setRuntimeKind((String)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends CategorySpec>)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES:
				getBlockTypes().clear();
				getBlockTypes().addAll((Collection<? extends BlockTypeSpec>)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS:
				getValidations().clear();
				getValidations().addAll((Collection<? extends ValidationSpec>)newValue);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS:
				getWorkspaceOptions().clear();
				getWorkspaceOptions().addAll((Collection<? extends WorkspaceOption>)newValue);
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
			case BlocklySpecPackage.EDITOR_SPEC__DOMAIN_NAME:
				setDomainName(DOMAIN_NAME_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__NS_URI:
				setNsURI(NS_URI_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__NS_PREFIX:
				setNsPrefix(NS_PREFIX_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__TOOLBOX_TYPE:
				setToolboxType(TOOLBOX_TYPE_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CODE_LANGUAGE:
				setCodeLanguage(CODE_LANGUAGE_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CODE_FILE_EXTENSION:
				setCodeFileExtension(CODE_FILE_EXTENSION_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__RUNTIME_KIND:
				setRuntimeKind(RUNTIME_KIND_EDEFAULT);
				return;
			case BlocklySpecPackage.EDITOR_SPEC__CATEGORIES:
				getCategories().clear();
				return;
			case BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES:
				getBlockTypes().clear();
				return;
			case BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS:
				getValidations().clear();
				return;
			case BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS:
				getWorkspaceOptions().clear();
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
			case BlocklySpecPackage.EDITOR_SPEC__DOMAIN_NAME:
				return DOMAIN_NAME_EDEFAULT == null ? domainName != null : !DOMAIN_NAME_EDEFAULT.equals(domainName);
			case BlocklySpecPackage.EDITOR_SPEC__NS_URI:
				return NS_URI_EDEFAULT == null ? nsURI != null : !NS_URI_EDEFAULT.equals(nsURI);
			case BlocklySpecPackage.EDITOR_SPEC__NS_PREFIX:
				return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
			case BlocklySpecPackage.EDITOR_SPEC__TOOLBOX_TYPE:
				return TOOLBOX_TYPE_EDEFAULT == null ? toolboxType != null : !TOOLBOX_TYPE_EDEFAULT.equals(toolboxType);
			case BlocklySpecPackage.EDITOR_SPEC__CODE_LANGUAGE:
				return CODE_LANGUAGE_EDEFAULT == null ? codeLanguage != null : !CODE_LANGUAGE_EDEFAULT.equals(codeLanguage);
			case BlocklySpecPackage.EDITOR_SPEC__CODE_FILE_EXTENSION:
				return CODE_FILE_EXTENSION_EDEFAULT == null ? codeFileExtension != null : !CODE_FILE_EXTENSION_EDEFAULT.equals(codeFileExtension);
			case BlocklySpecPackage.EDITOR_SPEC__RUNTIME_KIND:
				return RUNTIME_KIND_EDEFAULT == null ? runtimeKind != null : !RUNTIME_KIND_EDEFAULT.equals(runtimeKind);
			case BlocklySpecPackage.EDITOR_SPEC__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case BlocklySpecPackage.EDITOR_SPEC__BLOCK_TYPES:
				return blockTypes != null && !blockTypes.isEmpty();
			case BlocklySpecPackage.EDITOR_SPEC__VALIDATIONS:
				return validations != null && !validations.isEmpty();
			case BlocklySpecPackage.EDITOR_SPEC__WORKSPACE_OPTIONS:
				return workspaceOptions != null && !workspaceOptions.isEmpty();
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
		result.append(" (domainName: ");
		result.append(domainName);
		result.append(", nsURI: ");
		result.append(nsURI);
		result.append(", nsPrefix: ");
		result.append(nsPrefix);
		result.append(", toolboxType: ");
		result.append(toolboxType);
		result.append(", codeLanguage: ");
		result.append(codeLanguage);
		result.append(", codeFileExtension: ");
		result.append(codeFileExtension);
		result.append(", runtimeKind: ");
		result.append(runtimeKind);
		result.append(')');
		return result.toString();
	}

} //EditorSpecImpl
