/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecFactory
 * @model kind="package"
 * @generated
 */
public interface BlocklySpecPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "blocklyspec";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/model2blockly/intermediate/BlocklyEditorSpec";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "blocklyspec";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BlocklySpecPackage eINSTANCE = io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl.init();

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl <em>Editor Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getEditorSpec()
	 * @generated
	 */
	int EDITOR_SPEC = 0;

	/**
	 * The feature id for the '<em><b>Domain Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__DOMAIN_NAME = 0;

	/**
	 * The feature id for the '<em><b>Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__NS_URI = 1;

	/**
	 * The feature id for the '<em><b>Ns Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__NS_PREFIX = 2;

	/**
	 * The feature id for the '<em><b>Toolbox Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__TOOLBOX_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Code Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__CODE_LANGUAGE = 4;

	/**
	 * The feature id for the '<em><b>Code File Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__CODE_FILE_EXTENSION = 5;

	/**
	 * The feature id for the '<em><b>Runtime Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__RUNTIME_KIND = 6;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__CATEGORIES = 7;

	/**
	 * The feature id for the '<em><b>Block Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__BLOCK_TYPES = 8;

	/**
	 * The feature id for the '<em><b>Validations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__VALIDATIONS = 9;

	/**
	 * The feature id for the '<em><b>Workspace Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC__WORKSPACE_OPTIONS = 10;

	/**
	 * The number of structural features of the '<em>Editor Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_SPEC_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.CategorySpecImpl <em>Category Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.CategorySpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getCategorySpec()
	 * @generated
	 */
	int CATEGORY_SPEC = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SPEC__LABEL = 1;

	/**
	 * The feature id for the '<em><b>Colour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SPEC__COLOUR = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SPEC__CHILDREN = 3;

	/**
	 * The number of structural features of the '<em>Category Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_SPEC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl <em>Block Type Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getBlockTypeSpec()
	 * @generated
	 */
	int BLOCK_TYPE_SPEC = 2;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__TYPE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__LABEL = 1;

	/**
	 * The feature id for the '<em><b>Colour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__COLOUR = 2;

	/**
	 * The feature id for the '<em><b>Category Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__CATEGORY_NAME = 3;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__ABSTRACT = 4;

	/**
	 * The feature id for the '<em><b>Super Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__SUPER_TYPE_NAME = 5;

	/**
	 * The feature id for the '<em><b>Connection Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__CONNECTION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Connection Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME = 7;

	/**
	 * The feature id for the '<em><b>Ordered Input Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES = 8;

	/**
	 * The feature id for the '<em><b>Output Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__OUTPUT_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Message0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__MESSAGE0 = 10;

	/**
	 * The feature id for the '<em><b>Inputs Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__INPUTS_INLINE = 11;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__TOOLTIP = 12;

	/**
	 * The feature id for the '<em><b>Help Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__HELP_URL = 13;

	/**
	 * The feature id for the '<em><b>Code Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__CODE_TEMPLATE = 14;

	/**
	 * The feature id for the '<em><b>Id Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__ID_FIELD_NAME = 15;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__FIELDS = 16;

	/**
	 * The feature id for the '<em><b>Statement Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__STATEMENT_INPUTS = 17;

	/**
	 * The feature id for the '<em><b>Reference Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__REFERENCE_FIELDS = 18;

	/**
	 * The feature id for the '<em><b>Value Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC__VALUE_INPUTS = 19;

	/**
	 * The number of structural features of the '<em>Block Type Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_TYPE_SPEC_FEATURE_COUNT = 20;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.NamedValueImpl <em>Named Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.NamedValueImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getNamedValue()
	 * @generated
	 */
	int NAMED_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_VALUE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_VALUE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Named Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_VALUE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.DropdownOptionImpl <em>Dropdown Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.DropdownOptionImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getDropdownOption()
	 * @generated
	 */
	int DROPDOWN_OPTION = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROPDOWN_OPTION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROPDOWN_OPTION__LABEL = 1;

	/**
	 * The number of structural features of the '<em>Dropdown Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROPDOWN_OPTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl <em>Field Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getFieldSpec()
	 * @generated
	 */
	int FIELD_SPEC = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Field Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__FIELD_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__DEFAULT_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__OPTIONS = 3;

	/**
	 * The feature id for the '<em><b>Image Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__IMAGE_URL = 4;

	/**
	 * The feature id for the '<em><b>Image Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__IMAGE_WIDTH = 5;

	/**
	 * The feature id for the '<em><b>Image Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__IMAGE_HEIGHT = 6;

	/**
	 * The feature id for the '<em><b>Image Alt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__IMAGE_ALT = 7;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__MIN = 8;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__MAX = 9;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__REQUIRED = 10;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__LOWER_BOUND = 11;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UPPER_BOUND = 12;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UNIQUE = 13;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__ORDERED = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__ID = 15;

	/**
	 * The feature id for the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_WIDGET = 16;

	/**
	 * The feature id for the '<em><b>Ui Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_LABEL = 17;

	/**
	 * The feature id for the '<em><b>Ui Help</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_HELP = 18;

	/**
	 * The feature id for the '<em><b>Ui Placeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_PLACEHOLDER = 19;

	/**
	 * The feature id for the '<em><b>Ui Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_GROUP = 20;

	/**
	 * The feature id for the '<em><b>Ui Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_ORDER = 21;

	/**
	 * The feature id for the '<em><b>Ui Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_READONLY = 22;

	/**
	 * The feature id for the '<em><b>Ui Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_HIDDEN = 23;

	/**
	 * The feature id for the '<em><b>Ui Variant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC__UI_VARIANT = 24;

	/**
	 * The number of structural features of the '<em>Field Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_SPEC_FEATURE_COUNT = 25;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.StatementInputSpecImpl <em>Statement Input Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.StatementInputSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getStatementInputSpec()
	 * @generated
	 */
	int STATEMENT_INPUT_SPEC = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__CHECK_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__LOWER_BOUND = 2;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UPPER_BOUND = 3;

	/**
	 * The feature id for the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_WIDGET = 4;

	/**
	 * The feature id for the '<em><b>Ui Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_LABEL = 5;

	/**
	 * The feature id for the '<em><b>Ui Help</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_HELP = 6;

	/**
	 * The feature id for the '<em><b>Ui Placeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_PLACEHOLDER = 7;

	/**
	 * The feature id for the '<em><b>Ui Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_GROUP = 8;

	/**
	 * The feature id for the '<em><b>Ui Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_ORDER = 9;

	/**
	 * The feature id for the '<em><b>Ui Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_READONLY = 10;

	/**
	 * The feature id for the '<em><b>Ui Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_HIDDEN = 11;

	/**
	 * The feature id for the '<em><b>Ui Variant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC__UI_VARIANT = 12;

	/**
	 * The number of structural features of the '<em>Statement Input Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_INPUT_SPEC_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl <em>Reference Field Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getReferenceFieldSpec()
	 * @generated
	 */
	int REFERENCE_FIELD_SPEC = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Target Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__REQUIRED = 2;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__LOWER_BOUND = 3;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UPPER_BOUND = 4;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UNIQUE = 5;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__ORDERED = 6;

	/**
	 * The feature id for the '<em><b>Opposite Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__OPPOSITE_NAME = 7;

	/**
	 * The feature id for the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_WIDGET = 8;

	/**
	 * The feature id for the '<em><b>Ui Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_LABEL = 9;

	/**
	 * The feature id for the '<em><b>Ui Help</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_HELP = 10;

	/**
	 * The feature id for the '<em><b>Ui Placeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_PLACEHOLDER = 11;

	/**
	 * The feature id for the '<em><b>Ui Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_GROUP = 12;

	/**
	 * The feature id for the '<em><b>Ui Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_ORDER = 13;

	/**
	 * The feature id for the '<em><b>Ui Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_READONLY = 14;

	/**
	 * The feature id for the '<em><b>Ui Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_HIDDEN = 15;

	/**
	 * The feature id for the '<em><b>Ui Variant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__UI_VARIANT = 16;

	/**
	 * The feature id for the '<em><b>Reference Label Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD = 17;

	/**
	 * The number of structural features of the '<em>Reference Field Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FIELD_SPEC_FEATURE_COUNT = 18;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl <em>Value Input Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValueInputSpec()
	 * @generated
	 */
	int VALUE_INPUT_SPEC = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__CHECK_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Shadow Block Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Ui Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_WIDGET = 3;

	/**
	 * The feature id for the '<em><b>Ui Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_LABEL = 4;

	/**
	 * The feature id for the '<em><b>Ui Help</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_HELP = 5;

	/**
	 * The feature id for the '<em><b>Ui Placeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_PLACEHOLDER = 6;

	/**
	 * The feature id for the '<em><b>Ui Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_GROUP = 7;

	/**
	 * The feature id for the '<em><b>Ui Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_ORDER = 8;

	/**
	 * The feature id for the '<em><b>Ui Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_READONLY = 9;

	/**
	 * The feature id for the '<em><b>Ui Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_HIDDEN = 10;

	/**
	 * The feature id for the '<em><b>Ui Variant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC__UI_VARIANT = 11;

	/**
	 * The number of structural features of the '<em>Value Input Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INPUT_SPEC_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.WorkspaceOptionImpl <em>Workspace Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.WorkspaceOptionImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getWorkspaceOption()
	 * @generated
	 */
	int WORKSPACE_OPTION = 9;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPTION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPTION__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPTION__VALUE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPTION__CHILDREN = 3;

	/**
	 * The number of structural features of the '<em>Workspace Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPTION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl <em>Validation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationSpec()
	 * @generated
	 */
	int VALIDATION_SPEC = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__NAME = 1;

	/**
	 * The feature id for the '<em><b>Target Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__TARGET_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Predecessor Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__PREDECESSOR_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Owner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__OWNER_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Containment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__CONTAINMENT_NAME = 5;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__LOWER_BOUND = 6;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__UPPER_BOUND = 7;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__FIELD_NAME = 8;

	/**
	 * The feature id for the '<em><b>Field Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__FIELD_KIND = 9;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__EXPRESSION = 10;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__MESSAGE = 11;

	/**
	 * The feature id for the '<em><b>Visual Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__VISUAL_BLOCK = 12;

	/**
	 * The feature id for the '<em><b>Visual Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC__VISUAL_EXPRESSION = 13;

	/**
	 * The number of structural features of the '<em>Validation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_SPEC_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl <em>Validation Block Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationBlockSpec()
	 * @generated
	 */
	int VALIDATION_BLOCK_SPEC = 11;

	/**
	 * The feature id for the '<em><b>Blockly Block Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_SPEC__FIELDS = 1;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_SPEC__INPUTS = 2;

	/**
	 * The number of structural features of the '<em>Validation Block Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_SPEC_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockInputImpl <em>Validation Block Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockInputImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationBlockInput()
	 * @generated
	 */
	int VALIDATION_BLOCK_INPUT = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_INPUT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_INPUT__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Validation Block Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_BLOCK_INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl <em>Validation Expression Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationExpressionSpec()
	 * @generated
	 */
	int VALIDATION_EXPRESSION_SPEC = 13;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__KIND = 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__OPERATOR = 1;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__FIELD_NAME = 2;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__TYPE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__LITERAL = 4;

	/**
	 * The feature id for the '<em><b>Blockly Block Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC__CHILDREN = 6;

	/**
	 * The number of structural features of the '<em>Validation Expression Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATION_EXPRESSION_SPEC_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType <em>Connection Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getConnectionType()
	 * @generated
	 */
	int CONNECTION_TYPE = 14;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType <em>Field Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getFieldType()
	 * @generated
	 */
	int FIELD_TYPE = 15;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType <em>Validation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationType()
	 * @generated
	 */
	int VALIDATION_TYPE = 16;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind <em>Validation Expression Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationExpressionKind()
	 * @generated
	 */
	int VALIDATION_EXPRESSION_KIND = 17;

	/**
	 * The meta object id for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType <em>Workspace Option Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getWorkspaceOptionType()
	 * @generated
	 */
	int WORKSPACE_OPTION_TYPE = 18;


	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec <em>Editor Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editor Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec
	 * @generated
	 */
	EClass getEditorSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getDomainName <em>Domain Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getDomainName()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_DomainName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getNsURI <em>Ns URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ns URI</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getNsURI()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_NsURI();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getNsPrefix <em>Ns Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ns Prefix</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getNsPrefix()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_NsPrefix();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getToolboxType <em>Toolbox Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Toolbox Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getToolboxType()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_ToolboxType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCodeLanguage <em>Code Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code Language</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCodeLanguage()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_CodeLanguage();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCodeFileExtension <em>Code File Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code File Extension</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCodeFileExtension()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_CodeFileExtension();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getRuntimeKind <em>Runtime Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runtime Kind</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getRuntimeKind()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EAttribute getEditorSpec_RuntimeKind();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getCategories()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EReference getEditorSpec_Categories();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getBlockTypes <em>Block Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Block Types</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getBlockTypes()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EReference getEditorSpec_BlockTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getValidations <em>Validations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validations</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getValidations()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EReference getEditorSpec_Validations();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getWorkspaceOptions <em>Workspace Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workspace Options</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec#getWorkspaceOptions()
	 * @see #getEditorSpec()
	 * @generated
	 */
	EReference getEditorSpec_WorkspaceOptions();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec <em>Category Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec
	 * @generated
	 */
	EClass getCategorySpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getName()
	 * @see #getCategorySpec()
	 * @generated
	 */
	EAttribute getCategorySpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getLabel()
	 * @see #getCategorySpec()
	 * @generated
	 */
	EAttribute getCategorySpec_Label();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getColour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colour</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getColour()
	 * @see #getCategorySpec()
	 * @generated
	 */
	EAttribute getCategorySpec_Colour();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec#getChildren()
	 * @see #getCategorySpec()
	 * @generated
	 */
	EReference getCategorySpec_Children();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec <em>Block Type Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Type Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec
	 * @generated
	 */
	EClass getBlockTypeSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTypeName()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_TypeName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getLabel()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_Label();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getColour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colour</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getColour()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_Colour();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCategoryName <em>Category Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCategoryName()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_CategoryName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#isAbstract()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getSuperTypeName <em>Super Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Super Type Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getSuperTypeName()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_SuperTypeName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionType <em>Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionType()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_ConnectionType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionTypeName <em>Connection Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Type Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getConnectionTypeName()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_ConnectionTypeName();

	/**
	 * Returns the meta object for the attribute list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOrderedInputNames <em>Ordered Input Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Ordered Input Names</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOrderedInputNames()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_OrderedInputNames();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOutputType <em>Output Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getOutputType()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_OutputType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getMessage0 <em>Message0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message0</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getMessage0()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_Message0();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getInputsInline <em>Inputs Inline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inputs Inline</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getInputsInline()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_InputsInline();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTooltip <em>Tooltip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltip</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getTooltip()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_Tooltip();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getHelpUrl <em>Help Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Help Url</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getHelpUrl()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_HelpUrl();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCodeTemplate <em>Code Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code Template</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getCodeTemplate()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_CodeTemplate();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getIdFieldName <em>Id Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Field Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getIdFieldName()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EAttribute getBlockTypeSpec_IdFieldName();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getFields()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EReference getBlockTypeSpec_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getStatementInputs <em>Statement Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statement Inputs</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getStatementInputs()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EReference getBlockTypeSpec_StatementInputs();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getReferenceFields <em>Reference Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference Fields</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getReferenceFields()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EReference getBlockTypeSpec_ReferenceFields();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getValueInputs <em>Value Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value Inputs</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec#getValueInputs()
	 * @see #getBlockTypeSpec()
	 * @generated
	 */
	EReference getBlockTypeSpec_ValueInputs();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue <em>Named Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Value</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue
	 * @generated
	 */
	EClass getNamedValue();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue#getName()
	 * @see #getNamedValue()
	 * @generated
	 */
	EAttribute getNamedValue_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue#getValue()
	 * @see #getNamedValue()
	 * @generated
	 */
	EAttribute getNamedValue_Value();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption <em>Dropdown Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dropdown Option</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption
	 * @generated
	 */
	EClass getDropdownOption();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption#getValue()
	 * @see #getDropdownOption()
	 * @generated
	 */
	EAttribute getDropdownOption_Value();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption#getLabel()
	 * @see #getDropdownOption()
	 * @generated
	 */
	EAttribute getDropdownOption_Label();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec <em>Field Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec
	 * @generated
	 */
	EClass getFieldSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getName()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getFieldType <em>Field Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getFieldType()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_FieldType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getDefaultValue()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_DefaultValue();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getOptions()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EReference getFieldSpec_Options();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageUrl <em>Image Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Url</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageUrl()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_ImageUrl();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageWidth <em>Image Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Width</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageWidth()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_ImageWidth();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageHeight <em>Image Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Height</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageHeight()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_ImageHeight();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageAlt <em>Image Alt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Alt</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getImageAlt()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_ImageAlt();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMin()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Min();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getMax()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Max();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isRequired()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Required();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getLowerBound()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUpperBound()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUnique()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Unique();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isOrdered <em>Ordered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordered</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isOrdered()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Ordered();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isId()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_Id();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiWidget <em>Ui Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Widget</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiWidget()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiWidget();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiLabel <em>Ui Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiLabel()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiLabel();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiHelp <em>Ui Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Help</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiHelp()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiHelp();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Placeholder</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiPlaceholder()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiPlaceholder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiGroup <em>Ui Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Group</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiGroup()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiGroup();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiOrder <em>Ui Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Order</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiOrder()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiOrder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiReadonly <em>Ui Readonly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Readonly</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiReadonly()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiReadonly();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiHidden <em>Ui Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Hidden</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#isUiHidden()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiHidden();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiVariant <em>Ui Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Variant</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec#getUiVariant()
	 * @see #getFieldSpec()
	 * @generated
	 */
	EAttribute getFieldSpec_UiVariant();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec <em>Statement Input Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Input Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec
	 * @generated
	 */
	EClass getStatementInputSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getName()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getCheckType <em>Check Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getCheckType()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_CheckType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getLowerBound()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUpperBound()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiWidget <em>Ui Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Widget</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiWidget()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiWidget();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiLabel <em>Ui Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiLabel()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiLabel();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiHelp <em>Ui Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Help</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiHelp()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiHelp();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiPlaceholder <em>Ui Placeholder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Placeholder</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiPlaceholder()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiPlaceholder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiGroup <em>Ui Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Group</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiGroup()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiGroup();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiOrder <em>Ui Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Order</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiOrder()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiOrder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#isUiReadonly <em>Ui Readonly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Readonly</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#isUiReadonly()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiReadonly();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#isUiHidden <em>Ui Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Hidden</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#isUiHidden()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiHidden();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiVariant <em>Ui Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Variant</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec#getUiVariant()
	 * @see #getStatementInputSpec()
	 * @generated
	 */
	EAttribute getStatementInputSpec_UiVariant();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec <em>Reference Field Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Field Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec
	 * @generated
	 */
	EClass getReferenceFieldSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getName()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getTargetTypeName <em>Target Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Type Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getTargetTypeName()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_TargetTypeName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isRequired()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_Required();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getLowerBound()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUpperBound()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUnique()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_Unique();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isOrdered <em>Ordered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordered</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isOrdered()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_Ordered();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getOppositeName <em>Opposite Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Opposite Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getOppositeName()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_OppositeName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiWidget <em>Ui Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Widget</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiWidget()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiWidget();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiLabel <em>Ui Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiLabel()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiLabel();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiHelp <em>Ui Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Help</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiHelp()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiHelp();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiPlaceholder <em>Ui Placeholder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Placeholder</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiPlaceholder()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiPlaceholder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiGroup <em>Ui Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Group</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiGroup()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiGroup();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiOrder <em>Ui Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Order</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiOrder()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiOrder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiReadonly <em>Ui Readonly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Readonly</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiReadonly()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiReadonly();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiHidden <em>Ui Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Hidden</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#isUiHidden()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiHidden();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiVariant <em>Ui Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Variant</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getUiVariant()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_UiVariant();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getReferenceLabelField <em>Reference Label Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference Label Field</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec#getReferenceLabelField()
	 * @see #getReferenceFieldSpec()
	 * @generated
	 */
	EAttribute getReferenceFieldSpec_ReferenceLabelField();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec <em>Value Input Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Input Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec
	 * @generated
	 */
	EClass getValueInputSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getName()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getCheckType <em>Check Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getCheckType()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_CheckType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getShadowBlockType <em>Shadow Block Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shadow Block Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getShadowBlockType()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_ShadowBlockType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiWidget <em>Ui Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Widget</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiWidget()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiWidget();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiLabel <em>Ui Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Label</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiLabel()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiLabel();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiHelp <em>Ui Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Help</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiHelp()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiHelp();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiPlaceholder <em>Ui Placeholder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Placeholder</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiPlaceholder()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiPlaceholder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiGroup <em>Ui Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Group</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiGroup()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiGroup();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiOrder <em>Ui Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Order</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiOrder()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiOrder();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#isUiReadonly <em>Ui Readonly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Readonly</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#isUiReadonly()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiReadonly();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#isUiHidden <em>Ui Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Hidden</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#isUiHidden()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiHidden();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiVariant <em>Ui Variant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ui Variant</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec#getUiVariant()
	 * @see #getValueInputSpec()
	 * @generated
	 */
	EAttribute getValueInputSpec_UiVariant();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption <em>Workspace Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace Option</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption
	 * @generated
	 */
	EClass getWorkspaceOption();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getKey()
	 * @see #getWorkspaceOption()
	 * @generated
	 */
	EAttribute getWorkspaceOption_Key();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValue()
	 * @see #getWorkspaceOption()
	 * @generated
	 */
	EAttribute getWorkspaceOption_Value();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getValueType()
	 * @see #getWorkspaceOption()
	 * @generated
	 */
	EAttribute getWorkspaceOption_ValueType();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption#getChildren()
	 * @see #getWorkspaceOption()
	 * @generated
	 */
	EReference getWorkspaceOption_Children();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec <em>Validation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validation Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec
	 * @generated
	 */
	EClass getValidationSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getType()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_Type();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getName()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_Name();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getTargetType <em>Target Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getTargetType()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_TargetType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getPredecessorType <em>Predecessor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predecessor Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getPredecessorType()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_PredecessorType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getOwnerType <em>Owner Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getOwnerType()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_OwnerType();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getContainmentName <em>Containment Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containment Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getContainmentName()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_ContainmentName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getLowerBound()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getUpperBound()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldName()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_FieldName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldKind <em>Field Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Kind</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getFieldKind()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_FieldKind();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getExpression()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_Expression();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getMessage()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EAttribute getValidationSpec_Message();

	/**
	 * Returns the meta object for the containment reference '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualBlock <em>Visual Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visual Block</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualBlock()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EReference getValidationSpec_VisualBlock();

	/**
	 * Returns the meta object for the containment reference '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualExpression <em>Visual Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visual Expression</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec#getVisualExpression()
	 * @see #getValidationSpec()
	 * @generated
	 */
	EReference getValidationSpec_VisualExpression();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec <em>Validation Block Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validation Block Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec
	 * @generated
	 */
	EClass getValidationBlockSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getBlocklyBlockType <em>Blockly Block Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blockly Block Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getBlocklyBlockType()
	 * @see #getValidationBlockSpec()
	 * @generated
	 */
	EAttribute getValidationBlockSpec_BlocklyBlockType();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getFields()
	 * @see #getValidationBlockSpec()
	 * @generated
	 */
	EReference getValidationBlockSpec_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec#getInputs()
	 * @see #getValidationBlockSpec()
	 * @generated
	 */
	EReference getValidationBlockSpec_Inputs();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput <em>Validation Block Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validation Block Input</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput
	 * @generated
	 */
	EClass getValidationBlockInput();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getName()
	 * @see #getValidationBlockInput()
	 * @generated
	 */
	EAttribute getValidationBlockInput_Name();

	/**
	 * Returns the meta object for the containment reference '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput#getExpression()
	 * @see #getValidationBlockInput()
	 * @generated
	 */
	EReference getValidationBlockInput_Expression();

	/**
	 * Returns the meta object for class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec <em>Validation Expression Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validation Expression Spec</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec
	 * @generated
	 */
	EClass getValidationExpressionSpec();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getKind()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_Kind();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getOperator()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_Operator();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getFieldName()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_FieldName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Name</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getTypeName()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_TypeName();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getLiteral()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_Literal();

	/**
	 * Returns the meta object for the attribute '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getBlocklyBlockType <em>Blockly Block Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blockly Block Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getBlocklyBlockType()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EAttribute getValidationExpressionSpec_BlocklyBlockType();

	/**
	 * Returns the meta object for the containment reference list '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec#getChildren()
	 * @see #getValidationExpressionSpec()
	 * @generated
	 */
	EReference getValidationExpressionSpec_Children();

	/**
	 * Returns the meta object for enum '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType <em>Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Connection Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
	 * @generated
	 */
	EEnum getConnectionType();

	/**
	 * Returns the meta object for enum '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType <em>Field Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Field Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
	 * @generated
	 */
	EEnum getFieldType();

	/**
	 * Returns the meta object for enum '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType <em>Validation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Validation Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
	 * @generated
	 */
	EEnum getValidationType();

	/**
	 * Returns the meta object for enum '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind <em>Validation Expression Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Validation Expression Kind</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind
	 * @generated
	 */
	EEnum getValidationExpressionKind();

	/**
	 * Returns the meta object for enum '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType <em>Workspace Option Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Workspace Option Type</em>'.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType
	 * @generated
	 */
	EEnum getWorkspaceOptionType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BlocklySpecFactory getBlocklySpecFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl <em>Editor Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.EditorSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getEditorSpec()
		 * @generated
		 */
		EClass EDITOR_SPEC = eINSTANCE.getEditorSpec();

		/**
		 * The meta object literal for the '<em><b>Domain Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__DOMAIN_NAME = eINSTANCE.getEditorSpec_DomainName();

		/**
		 * The meta object literal for the '<em><b>Ns URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__NS_URI = eINSTANCE.getEditorSpec_NsURI();

		/**
		 * The meta object literal for the '<em><b>Ns Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__NS_PREFIX = eINSTANCE.getEditorSpec_NsPrefix();

		/**
		 * The meta object literal for the '<em><b>Toolbox Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__TOOLBOX_TYPE = eINSTANCE.getEditorSpec_ToolboxType();

		/**
		 * The meta object literal for the '<em><b>Code Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__CODE_LANGUAGE = eINSTANCE.getEditorSpec_CodeLanguage();

		/**
		 * The meta object literal for the '<em><b>Code File Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__CODE_FILE_EXTENSION = eINSTANCE.getEditorSpec_CodeFileExtension();

		/**
		 * The meta object literal for the '<em><b>Runtime Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_SPEC__RUNTIME_KIND = eINSTANCE.getEditorSpec_RuntimeKind();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_SPEC__CATEGORIES = eINSTANCE.getEditorSpec_Categories();

		/**
		 * The meta object literal for the '<em><b>Block Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_SPEC__BLOCK_TYPES = eINSTANCE.getEditorSpec_BlockTypes();

		/**
		 * The meta object literal for the '<em><b>Validations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_SPEC__VALIDATIONS = eINSTANCE.getEditorSpec_Validations();

		/**
		 * The meta object literal for the '<em><b>Workspace Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_SPEC__WORKSPACE_OPTIONS = eINSTANCE.getEditorSpec_WorkspaceOptions();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.CategorySpecImpl <em>Category Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.CategorySpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getCategorySpec()
		 * @generated
		 */
		EClass CATEGORY_SPEC = eINSTANCE.getCategorySpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY_SPEC__NAME = eINSTANCE.getCategorySpec_Name();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY_SPEC__LABEL = eINSTANCE.getCategorySpec_Label();

		/**
		 * The meta object literal for the '<em><b>Colour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY_SPEC__COLOUR = eINSTANCE.getCategorySpec_Colour();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY_SPEC__CHILDREN = eINSTANCE.getCategorySpec_Children();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl <em>Block Type Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlockTypeSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getBlockTypeSpec()
		 * @generated
		 */
		EClass BLOCK_TYPE_SPEC = eINSTANCE.getBlockTypeSpec();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__TYPE_NAME = eINSTANCE.getBlockTypeSpec_TypeName();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__LABEL = eINSTANCE.getBlockTypeSpec_Label();

		/**
		 * The meta object literal for the '<em><b>Colour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__COLOUR = eINSTANCE.getBlockTypeSpec_Colour();

		/**
		 * The meta object literal for the '<em><b>Category Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__CATEGORY_NAME = eINSTANCE.getBlockTypeSpec_CategoryName();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__ABSTRACT = eINSTANCE.getBlockTypeSpec_Abstract();

		/**
		 * The meta object literal for the '<em><b>Super Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__SUPER_TYPE_NAME = eINSTANCE.getBlockTypeSpec_SuperTypeName();

		/**
		 * The meta object literal for the '<em><b>Connection Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__CONNECTION_TYPE = eINSTANCE.getBlockTypeSpec_ConnectionType();

		/**
		 * The meta object literal for the '<em><b>Connection Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME = eINSTANCE.getBlockTypeSpec_ConnectionTypeName();

		/**
		 * The meta object literal for the '<em><b>Ordered Input Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES = eINSTANCE.getBlockTypeSpec_OrderedInputNames();

		/**
		 * The meta object literal for the '<em><b>Output Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__OUTPUT_TYPE = eINSTANCE.getBlockTypeSpec_OutputType();

		/**
		 * The meta object literal for the '<em><b>Message0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__MESSAGE0 = eINSTANCE.getBlockTypeSpec_Message0();

		/**
		 * The meta object literal for the '<em><b>Inputs Inline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__INPUTS_INLINE = eINSTANCE.getBlockTypeSpec_InputsInline();

		/**
		 * The meta object literal for the '<em><b>Tooltip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__TOOLTIP = eINSTANCE.getBlockTypeSpec_Tooltip();

		/**
		 * The meta object literal for the '<em><b>Help Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__HELP_URL = eINSTANCE.getBlockTypeSpec_HelpUrl();

		/**
		 * The meta object literal for the '<em><b>Code Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__CODE_TEMPLATE = eINSTANCE.getBlockTypeSpec_CodeTemplate();

		/**
		 * The meta object literal for the '<em><b>Id Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_TYPE_SPEC__ID_FIELD_NAME = eINSTANCE.getBlockTypeSpec_IdFieldName();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_TYPE_SPEC__FIELDS = eINSTANCE.getBlockTypeSpec_Fields();

		/**
		 * The meta object literal for the '<em><b>Statement Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_TYPE_SPEC__STATEMENT_INPUTS = eINSTANCE.getBlockTypeSpec_StatementInputs();

		/**
		 * The meta object literal for the '<em><b>Reference Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_TYPE_SPEC__REFERENCE_FIELDS = eINSTANCE.getBlockTypeSpec_ReferenceFields();

		/**
		 * The meta object literal for the '<em><b>Value Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK_TYPE_SPEC__VALUE_INPUTS = eINSTANCE.getBlockTypeSpec_ValueInputs();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.NamedValueImpl <em>Named Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.NamedValueImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getNamedValue()
		 * @generated
		 */
		EClass NAMED_VALUE = eINSTANCE.getNamedValue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_VALUE__NAME = eINSTANCE.getNamedValue_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_VALUE__VALUE = eINSTANCE.getNamedValue_Value();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.DropdownOptionImpl <em>Dropdown Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.DropdownOptionImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getDropdownOption()
		 * @generated
		 */
		EClass DROPDOWN_OPTION = eINSTANCE.getDropdownOption();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROPDOWN_OPTION__VALUE = eINSTANCE.getDropdownOption_Value();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DROPDOWN_OPTION__LABEL = eINSTANCE.getDropdownOption_Label();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl <em>Field Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.FieldSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getFieldSpec()
		 * @generated
		 */
		EClass FIELD_SPEC = eINSTANCE.getFieldSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__NAME = eINSTANCE.getFieldSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Field Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__FIELD_TYPE = eINSTANCE.getFieldSpec_FieldType();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__DEFAULT_VALUE = eINSTANCE.getFieldSpec_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_SPEC__OPTIONS = eINSTANCE.getFieldSpec_Options();

		/**
		 * The meta object literal for the '<em><b>Image Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__IMAGE_URL = eINSTANCE.getFieldSpec_ImageUrl();

		/**
		 * The meta object literal for the '<em><b>Image Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__IMAGE_WIDTH = eINSTANCE.getFieldSpec_ImageWidth();

		/**
		 * The meta object literal for the '<em><b>Image Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__IMAGE_HEIGHT = eINSTANCE.getFieldSpec_ImageHeight();

		/**
		 * The meta object literal for the '<em><b>Image Alt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__IMAGE_ALT = eINSTANCE.getFieldSpec_ImageAlt();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__MIN = eINSTANCE.getFieldSpec_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__MAX = eINSTANCE.getFieldSpec_Max();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__REQUIRED = eINSTANCE.getFieldSpec_Required();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__LOWER_BOUND = eINSTANCE.getFieldSpec_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UPPER_BOUND = eINSTANCE.getFieldSpec_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UNIQUE = eINSTANCE.getFieldSpec_Unique();

		/**
		 * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__ORDERED = eINSTANCE.getFieldSpec_Ordered();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__ID = eINSTANCE.getFieldSpec_Id();

		/**
		 * The meta object literal for the '<em><b>Ui Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_WIDGET = eINSTANCE.getFieldSpec_UiWidget();

		/**
		 * The meta object literal for the '<em><b>Ui Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_LABEL = eINSTANCE.getFieldSpec_UiLabel();

		/**
		 * The meta object literal for the '<em><b>Ui Help</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_HELP = eINSTANCE.getFieldSpec_UiHelp();

		/**
		 * The meta object literal for the '<em><b>Ui Placeholder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_PLACEHOLDER = eINSTANCE.getFieldSpec_UiPlaceholder();

		/**
		 * The meta object literal for the '<em><b>Ui Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_GROUP = eINSTANCE.getFieldSpec_UiGroup();

		/**
		 * The meta object literal for the '<em><b>Ui Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_ORDER = eINSTANCE.getFieldSpec_UiOrder();

		/**
		 * The meta object literal for the '<em><b>Ui Readonly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_READONLY = eINSTANCE.getFieldSpec_UiReadonly();

		/**
		 * The meta object literal for the '<em><b>Ui Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_HIDDEN = eINSTANCE.getFieldSpec_UiHidden();

		/**
		 * The meta object literal for the '<em><b>Ui Variant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_SPEC__UI_VARIANT = eINSTANCE.getFieldSpec_UiVariant();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.StatementInputSpecImpl <em>Statement Input Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.StatementInputSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getStatementInputSpec()
		 * @generated
		 */
		EClass STATEMENT_INPUT_SPEC = eINSTANCE.getStatementInputSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__NAME = eINSTANCE.getStatementInputSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Check Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__CHECK_TYPE = eINSTANCE.getStatementInputSpec_CheckType();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__LOWER_BOUND = eINSTANCE.getStatementInputSpec_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UPPER_BOUND = eINSTANCE.getStatementInputSpec_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Ui Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_WIDGET = eINSTANCE.getStatementInputSpec_UiWidget();

		/**
		 * The meta object literal for the '<em><b>Ui Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_LABEL = eINSTANCE.getStatementInputSpec_UiLabel();

		/**
		 * The meta object literal for the '<em><b>Ui Help</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_HELP = eINSTANCE.getStatementInputSpec_UiHelp();

		/**
		 * The meta object literal for the '<em><b>Ui Placeholder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_PLACEHOLDER = eINSTANCE.getStatementInputSpec_UiPlaceholder();

		/**
		 * The meta object literal for the '<em><b>Ui Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_GROUP = eINSTANCE.getStatementInputSpec_UiGroup();

		/**
		 * The meta object literal for the '<em><b>Ui Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_ORDER = eINSTANCE.getStatementInputSpec_UiOrder();

		/**
		 * The meta object literal for the '<em><b>Ui Readonly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_READONLY = eINSTANCE.getStatementInputSpec_UiReadonly();

		/**
		 * The meta object literal for the '<em><b>Ui Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_HIDDEN = eINSTANCE.getStatementInputSpec_UiHidden();

		/**
		 * The meta object literal for the '<em><b>Ui Variant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATEMENT_INPUT_SPEC__UI_VARIANT = eINSTANCE.getStatementInputSpec_UiVariant();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl <em>Reference Field Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ReferenceFieldSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getReferenceFieldSpec()
		 * @generated
		 */
		EClass REFERENCE_FIELD_SPEC = eINSTANCE.getReferenceFieldSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__NAME = eINSTANCE.getReferenceFieldSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Target Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME = eINSTANCE.getReferenceFieldSpec_TargetTypeName();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__REQUIRED = eINSTANCE.getReferenceFieldSpec_Required();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__LOWER_BOUND = eINSTANCE.getReferenceFieldSpec_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UPPER_BOUND = eINSTANCE.getReferenceFieldSpec_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UNIQUE = eINSTANCE.getReferenceFieldSpec_Unique();

		/**
		 * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__ORDERED = eINSTANCE.getReferenceFieldSpec_Ordered();

		/**
		 * The meta object literal for the '<em><b>Opposite Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__OPPOSITE_NAME = eINSTANCE.getReferenceFieldSpec_OppositeName();

		/**
		 * The meta object literal for the '<em><b>Ui Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_WIDGET = eINSTANCE.getReferenceFieldSpec_UiWidget();

		/**
		 * The meta object literal for the '<em><b>Ui Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_LABEL = eINSTANCE.getReferenceFieldSpec_UiLabel();

		/**
		 * The meta object literal for the '<em><b>Ui Help</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_HELP = eINSTANCE.getReferenceFieldSpec_UiHelp();

		/**
		 * The meta object literal for the '<em><b>Ui Placeholder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_PLACEHOLDER = eINSTANCE.getReferenceFieldSpec_UiPlaceholder();

		/**
		 * The meta object literal for the '<em><b>Ui Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_GROUP = eINSTANCE.getReferenceFieldSpec_UiGroup();

		/**
		 * The meta object literal for the '<em><b>Ui Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_ORDER = eINSTANCE.getReferenceFieldSpec_UiOrder();

		/**
		 * The meta object literal for the '<em><b>Ui Readonly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_READONLY = eINSTANCE.getReferenceFieldSpec_UiReadonly();

		/**
		 * The meta object literal for the '<em><b>Ui Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_HIDDEN = eINSTANCE.getReferenceFieldSpec_UiHidden();

		/**
		 * The meta object literal for the '<em><b>Ui Variant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__UI_VARIANT = eINSTANCE.getReferenceFieldSpec_UiVariant();

		/**
		 * The meta object literal for the '<em><b>Reference Label Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD = eINSTANCE.getReferenceFieldSpec_ReferenceLabelField();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl <em>Value Input Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValueInputSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValueInputSpec()
		 * @generated
		 */
		EClass VALUE_INPUT_SPEC = eINSTANCE.getValueInputSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__NAME = eINSTANCE.getValueInputSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Check Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__CHECK_TYPE = eINSTANCE.getValueInputSpec_CheckType();

		/**
		 * The meta object literal for the '<em><b>Shadow Block Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE = eINSTANCE.getValueInputSpec_ShadowBlockType();

		/**
		 * The meta object literal for the '<em><b>Ui Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_WIDGET = eINSTANCE.getValueInputSpec_UiWidget();

		/**
		 * The meta object literal for the '<em><b>Ui Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_LABEL = eINSTANCE.getValueInputSpec_UiLabel();

		/**
		 * The meta object literal for the '<em><b>Ui Help</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_HELP = eINSTANCE.getValueInputSpec_UiHelp();

		/**
		 * The meta object literal for the '<em><b>Ui Placeholder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_PLACEHOLDER = eINSTANCE.getValueInputSpec_UiPlaceholder();

		/**
		 * The meta object literal for the '<em><b>Ui Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_GROUP = eINSTANCE.getValueInputSpec_UiGroup();

		/**
		 * The meta object literal for the '<em><b>Ui Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_ORDER = eINSTANCE.getValueInputSpec_UiOrder();

		/**
		 * The meta object literal for the '<em><b>Ui Readonly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_READONLY = eINSTANCE.getValueInputSpec_UiReadonly();

		/**
		 * The meta object literal for the '<em><b>Ui Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_HIDDEN = eINSTANCE.getValueInputSpec_UiHidden();

		/**
		 * The meta object literal for the '<em><b>Ui Variant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INPUT_SPEC__UI_VARIANT = eINSTANCE.getValueInputSpec_UiVariant();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.WorkspaceOptionImpl <em>Workspace Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.WorkspaceOptionImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getWorkspaceOption()
		 * @generated
		 */
		EClass WORKSPACE_OPTION = eINSTANCE.getWorkspaceOption();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_OPTION__KEY = eINSTANCE.getWorkspaceOption_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_OPTION__VALUE = eINSTANCE.getWorkspaceOption_Value();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_OPTION__VALUE_TYPE = eINSTANCE.getWorkspaceOption_ValueType();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_OPTION__CHILDREN = eINSTANCE.getWorkspaceOption_Children();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl <em>Validation Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationSpec()
		 * @generated
		 */
		EClass VALIDATION_SPEC = eINSTANCE.getValidationSpec();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__TYPE = eINSTANCE.getValidationSpec_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__NAME = eINSTANCE.getValidationSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Target Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__TARGET_TYPE = eINSTANCE.getValidationSpec_TargetType();

		/**
		 * The meta object literal for the '<em><b>Predecessor Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__PREDECESSOR_TYPE = eINSTANCE.getValidationSpec_PredecessorType();

		/**
		 * The meta object literal for the '<em><b>Owner Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__OWNER_TYPE = eINSTANCE.getValidationSpec_OwnerType();

		/**
		 * The meta object literal for the '<em><b>Containment Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__CONTAINMENT_NAME = eINSTANCE.getValidationSpec_ContainmentName();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__LOWER_BOUND = eINSTANCE.getValidationSpec_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__UPPER_BOUND = eINSTANCE.getValidationSpec_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__FIELD_NAME = eINSTANCE.getValidationSpec_FieldName();

		/**
		 * The meta object literal for the '<em><b>Field Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__FIELD_KIND = eINSTANCE.getValidationSpec_FieldKind();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__EXPRESSION = eINSTANCE.getValidationSpec_Expression();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_SPEC__MESSAGE = eINSTANCE.getValidationSpec_Message();

		/**
		 * The meta object literal for the '<em><b>Visual Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_SPEC__VISUAL_BLOCK = eINSTANCE.getValidationSpec_VisualBlock();

		/**
		 * The meta object literal for the '<em><b>Visual Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_SPEC__VISUAL_EXPRESSION = eINSTANCE.getValidationSpec_VisualExpression();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl <em>Validation Block Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationBlockSpec()
		 * @generated
		 */
		EClass VALIDATION_BLOCK_SPEC = eINSTANCE.getValidationBlockSpec();

		/**
		 * The meta object literal for the '<em><b>Blockly Block Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE = eINSTANCE.getValidationBlockSpec_BlocklyBlockType();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_BLOCK_SPEC__FIELDS = eINSTANCE.getValidationBlockSpec_Fields();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_BLOCK_SPEC__INPUTS = eINSTANCE.getValidationBlockSpec_Inputs();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockInputImpl <em>Validation Block Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationBlockInputImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationBlockInput()
		 * @generated
		 */
		EClass VALIDATION_BLOCK_INPUT = eINSTANCE.getValidationBlockInput();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_BLOCK_INPUT__NAME = eINSTANCE.getValidationBlockInput_Name();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_BLOCK_INPUT__EXPRESSION = eINSTANCE.getValidationBlockInput_Expression();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl <em>Validation Expression Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.ValidationExpressionSpecImpl
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationExpressionSpec()
		 * @generated
		 */
		EClass VALIDATION_EXPRESSION_SPEC = eINSTANCE.getValidationExpressionSpec();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__KIND = eINSTANCE.getValidationExpressionSpec_Kind();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__OPERATOR = eINSTANCE.getValidationExpressionSpec_Operator();

		/**
		 * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__FIELD_NAME = eINSTANCE.getValidationExpressionSpec_FieldName();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__TYPE_NAME = eINSTANCE.getValidationExpressionSpec_TypeName();

		/**
		 * The meta object literal for the '<em><b>Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__LITERAL = eINSTANCE.getValidationExpressionSpec_Literal();

		/**
		 * The meta object literal for the '<em><b>Blockly Block Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE = eINSTANCE.getValidationExpressionSpec_BlocklyBlockType();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATION_EXPRESSION_SPEC__CHILDREN = eINSTANCE.getValidationExpressionSpec_Children();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType <em>Connection Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getConnectionType()
		 * @generated
		 */
		EEnum CONNECTION_TYPE = eINSTANCE.getConnectionType();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType <em>Field Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getFieldType()
		 * @generated
		 */
		EEnum FIELD_TYPE = eINSTANCE.getFieldType();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType <em>Validation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationType()
		 * @generated
		 */
		EEnum VALIDATION_TYPE = eINSTANCE.getValidationType();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind <em>Validation Expression Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getValidationExpressionKind()
		 * @generated
		 */
		EEnum VALIDATION_EXPRESSION_KIND = eINSTANCE.getValidationExpressionKind();

		/**
		 * The meta object literal for the '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType <em>Workspace Option Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType
		 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.impl.BlocklySpecPackageImpl#getWorkspaceOptionType()
		 * @generated
		 */
		EEnum WORKSPACE_OPTION_TYPE = eINSTANCE.getWorkspaceOptionType();

	}

} //BlocklySpecPackage
