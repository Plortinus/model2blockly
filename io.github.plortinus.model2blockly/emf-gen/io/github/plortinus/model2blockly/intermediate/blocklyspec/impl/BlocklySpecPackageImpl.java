/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecFactory;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionKind;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOptionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BlocklySpecPackageImpl extends EPackageImpl implements BlocklySpecPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categorySpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockTypeSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dropdownOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementInputSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceFieldSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueInputSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceOptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validationSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validationBlockSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validationBlockInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validationExpressionSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum connectionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fieldTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum validationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum validationExpressionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum workspaceOptionTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BlocklySpecPackageImpl() {
		super(eNS_URI, BlocklySpecFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link BlocklySpecPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BlocklySpecPackage init() {
		if (isInited) return (BlocklySpecPackage)EPackage.Registry.INSTANCE.getEPackage(BlocklySpecPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredBlocklySpecPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		BlocklySpecPackageImpl theBlocklySpecPackage = registeredBlocklySpecPackage instanceof BlocklySpecPackageImpl ? (BlocklySpecPackageImpl)registeredBlocklySpecPackage : new BlocklySpecPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theBlocklySpecPackage.createPackageContents();

		// Initialize created meta-data
		theBlocklySpecPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBlocklySpecPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BlocklySpecPackage.eNS_URI, theBlocklySpecPackage);
		return theBlocklySpecPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEditorSpec() {
		return editorSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_DomainName() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_NsURI() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_NsPrefix() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_ToolboxType() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_CodeLanguage() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_CodeFileExtension() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditorSpec_RuntimeKind() {
		return (EAttribute)editorSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditorSpec_Categories() {
		return (EReference)editorSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditorSpec_BlockTypes() {
		return (EReference)editorSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditorSpec_Validations() {
		return (EReference)editorSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditorSpec_WorkspaceOptions() {
		return (EReference)editorSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCategorySpec() {
		return categorySpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCategorySpec_Name() {
		return (EAttribute)categorySpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCategorySpec_Label() {
		return (EAttribute)categorySpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCategorySpec_Colour() {
		return (EAttribute)categorySpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCategorySpec_Children() {
		return (EReference)categorySpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBlockTypeSpec() {
		return blockTypeSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_TypeName() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_Label() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_Colour() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_CategoryName() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_Abstract() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_SuperTypeName() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_ConnectionType() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_ConnectionTypeName() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_OrderedInputNames() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_OutputType() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_Message0() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_InputsInline() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_Tooltip() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_HelpUrl() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_CodeTemplate() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlockTypeSpec_IdFieldName() {
		return (EAttribute)blockTypeSpecEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlockTypeSpec_Fields() {
		return (EReference)blockTypeSpecEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlockTypeSpec_StatementInputs() {
		return (EReference)blockTypeSpecEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlockTypeSpec_ReferenceFields() {
		return (EReference)blockTypeSpecEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlockTypeSpec_ValueInputs() {
		return (EReference)blockTypeSpecEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedValue() {
		return namedValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedValue_Name() {
		return (EAttribute)namedValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedValue_Value() {
		return (EAttribute)namedValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDropdownOption() {
		return dropdownOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDropdownOption_Value() {
		return (EAttribute)dropdownOptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDropdownOption_Label() {
		return (EAttribute)dropdownOptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFieldSpec() {
		return fieldSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Name() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_FieldType() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_DefaultValue() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFieldSpec_Options() {
		return (EReference)fieldSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_ImageUrl() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_ImageWidth() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_ImageHeight() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_ImageAlt() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Min() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Max() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Required() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_LowerBound() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UpperBound() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Unique() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Ordered() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_Id() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiWidget() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiLabel() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiHelp() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiPlaceholder() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiGroup() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiOrder() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiReadonly() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiHidden() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFieldSpec_UiVariant() {
		return (EAttribute)fieldSpecEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStatementInputSpec() {
		return statementInputSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_Name() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_CheckType() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_LowerBound() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UpperBound() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiWidget() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiLabel() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiHelp() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiPlaceholder() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiGroup() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiOrder() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiReadonly() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiHidden() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStatementInputSpec_UiVariant() {
		return (EAttribute)statementInputSpecEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferenceFieldSpec() {
		return referenceFieldSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_Name() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_TargetTypeName() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_Required() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_LowerBound() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UpperBound() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_Unique() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_Ordered() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_OppositeName() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiWidget() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiLabel() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiHelp() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiPlaceholder() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiGroup() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiOrder() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiReadonly() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiHidden() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_UiVariant() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReferenceFieldSpec_ReferenceLabelField() {
		return (EAttribute)referenceFieldSpecEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValueInputSpec() {
		return valueInputSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_Name() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_CheckType() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_ShadowBlockType() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiWidget() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiLabel() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiHelp() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiPlaceholder() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiGroup() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiOrder() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiReadonly() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiHidden() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueInputSpec_UiVariant() {
		return (EAttribute)valueInputSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkspaceOption() {
		return workspaceOptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWorkspaceOption_Key() {
		return (EAttribute)workspaceOptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWorkspaceOption_Value() {
		return (EAttribute)workspaceOptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWorkspaceOption_ValueType() {
		return (EAttribute)workspaceOptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkspaceOption_Children() {
		return (EReference)workspaceOptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidationSpec() {
		return validationSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_Type() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_Name() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_TargetType() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_PredecessorType() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_OwnerType() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_ContainmentName() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_LowerBound() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_UpperBound() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_FieldName() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_FieldKind() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_Expression() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationSpec_Message() {
		return (EAttribute)validationSpecEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationSpec_VisualBlock() {
		return (EReference)validationSpecEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationSpec_VisualExpression() {
		return (EReference)validationSpecEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidationBlockSpec() {
		return validationBlockSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationBlockSpec_BlocklyBlockType() {
		return (EAttribute)validationBlockSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationBlockSpec_Fields() {
		return (EReference)validationBlockSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationBlockSpec_Inputs() {
		return (EReference)validationBlockSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidationBlockInput() {
		return validationBlockInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationBlockInput_Name() {
		return (EAttribute)validationBlockInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationBlockInput_Expression() {
		return (EReference)validationBlockInputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidationExpressionSpec() {
		return validationExpressionSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_Kind() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_Operator() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_FieldName() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_TypeName() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_Literal() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidationExpressionSpec_BlocklyBlockType() {
		return (EAttribute)validationExpressionSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidationExpressionSpec_Children() {
		return (EReference)validationExpressionSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getConnectionType() {
		return connectionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFieldType() {
		return fieldTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getValidationType() {
		return validationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getValidationExpressionKind() {
		return validationExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getWorkspaceOptionType() {
		return workspaceOptionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BlocklySpecFactory getBlocklySpecFactory() {
		return (BlocklySpecFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		editorSpecEClass = createEClass(EDITOR_SPEC);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__DOMAIN_NAME);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__NS_URI);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__NS_PREFIX);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__TOOLBOX_TYPE);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__CODE_LANGUAGE);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__CODE_FILE_EXTENSION);
		createEAttribute(editorSpecEClass, EDITOR_SPEC__RUNTIME_KIND);
		createEReference(editorSpecEClass, EDITOR_SPEC__CATEGORIES);
		createEReference(editorSpecEClass, EDITOR_SPEC__BLOCK_TYPES);
		createEReference(editorSpecEClass, EDITOR_SPEC__VALIDATIONS);
		createEReference(editorSpecEClass, EDITOR_SPEC__WORKSPACE_OPTIONS);

		categorySpecEClass = createEClass(CATEGORY_SPEC);
		createEAttribute(categorySpecEClass, CATEGORY_SPEC__NAME);
		createEAttribute(categorySpecEClass, CATEGORY_SPEC__LABEL);
		createEAttribute(categorySpecEClass, CATEGORY_SPEC__COLOUR);
		createEReference(categorySpecEClass, CATEGORY_SPEC__CHILDREN);

		blockTypeSpecEClass = createEClass(BLOCK_TYPE_SPEC);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__TYPE_NAME);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__LABEL);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__COLOUR);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__CATEGORY_NAME);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__ABSTRACT);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__SUPER_TYPE_NAME);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__CONNECTION_TYPE);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__CONNECTION_TYPE_NAME);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__ORDERED_INPUT_NAMES);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__OUTPUT_TYPE);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__MESSAGE0);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__INPUTS_INLINE);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__TOOLTIP);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__HELP_URL);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__CODE_TEMPLATE);
		createEAttribute(blockTypeSpecEClass, BLOCK_TYPE_SPEC__ID_FIELD_NAME);
		createEReference(blockTypeSpecEClass, BLOCK_TYPE_SPEC__FIELDS);
		createEReference(blockTypeSpecEClass, BLOCK_TYPE_SPEC__STATEMENT_INPUTS);
		createEReference(blockTypeSpecEClass, BLOCK_TYPE_SPEC__REFERENCE_FIELDS);
		createEReference(blockTypeSpecEClass, BLOCK_TYPE_SPEC__VALUE_INPUTS);

		namedValueEClass = createEClass(NAMED_VALUE);
		createEAttribute(namedValueEClass, NAMED_VALUE__NAME);
		createEAttribute(namedValueEClass, NAMED_VALUE__VALUE);

		dropdownOptionEClass = createEClass(DROPDOWN_OPTION);
		createEAttribute(dropdownOptionEClass, DROPDOWN_OPTION__VALUE);
		createEAttribute(dropdownOptionEClass, DROPDOWN_OPTION__LABEL);

		fieldSpecEClass = createEClass(FIELD_SPEC);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__NAME);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__FIELD_TYPE);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__DEFAULT_VALUE);
		createEReference(fieldSpecEClass, FIELD_SPEC__OPTIONS);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__IMAGE_URL);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__IMAGE_WIDTH);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__IMAGE_HEIGHT);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__IMAGE_ALT);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__MIN);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__MAX);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__REQUIRED);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__LOWER_BOUND);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UPPER_BOUND);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UNIQUE);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__ORDERED);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__ID);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_WIDGET);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_LABEL);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_HELP);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_PLACEHOLDER);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_GROUP);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_ORDER);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_READONLY);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_HIDDEN);
		createEAttribute(fieldSpecEClass, FIELD_SPEC__UI_VARIANT);

		statementInputSpecEClass = createEClass(STATEMENT_INPUT_SPEC);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__NAME);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__CHECK_TYPE);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__LOWER_BOUND);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UPPER_BOUND);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_WIDGET);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_LABEL);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_HELP);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_PLACEHOLDER);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_GROUP);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_ORDER);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_READONLY);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_HIDDEN);
		createEAttribute(statementInputSpecEClass, STATEMENT_INPUT_SPEC__UI_VARIANT);

		referenceFieldSpecEClass = createEClass(REFERENCE_FIELD_SPEC);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__NAME);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__TARGET_TYPE_NAME);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__REQUIRED);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__LOWER_BOUND);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UPPER_BOUND);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UNIQUE);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__ORDERED);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__OPPOSITE_NAME);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_WIDGET);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_LABEL);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_HELP);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_PLACEHOLDER);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_GROUP);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_ORDER);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_READONLY);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_HIDDEN);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__UI_VARIANT);
		createEAttribute(referenceFieldSpecEClass, REFERENCE_FIELD_SPEC__REFERENCE_LABEL_FIELD);

		valueInputSpecEClass = createEClass(VALUE_INPUT_SPEC);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__NAME);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__CHECK_TYPE);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__SHADOW_BLOCK_TYPE);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_WIDGET);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_LABEL);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_HELP);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_PLACEHOLDER);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_GROUP);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_ORDER);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_READONLY);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_HIDDEN);
		createEAttribute(valueInputSpecEClass, VALUE_INPUT_SPEC__UI_VARIANT);

		workspaceOptionEClass = createEClass(WORKSPACE_OPTION);
		createEAttribute(workspaceOptionEClass, WORKSPACE_OPTION__KEY);
		createEAttribute(workspaceOptionEClass, WORKSPACE_OPTION__VALUE);
		createEAttribute(workspaceOptionEClass, WORKSPACE_OPTION__VALUE_TYPE);
		createEReference(workspaceOptionEClass, WORKSPACE_OPTION__CHILDREN);

		validationSpecEClass = createEClass(VALIDATION_SPEC);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__TYPE);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__NAME);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__TARGET_TYPE);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__PREDECESSOR_TYPE);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__OWNER_TYPE);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__CONTAINMENT_NAME);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__LOWER_BOUND);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__UPPER_BOUND);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__FIELD_NAME);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__FIELD_KIND);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__EXPRESSION);
		createEAttribute(validationSpecEClass, VALIDATION_SPEC__MESSAGE);
		createEReference(validationSpecEClass, VALIDATION_SPEC__VISUAL_BLOCK);
		createEReference(validationSpecEClass, VALIDATION_SPEC__VISUAL_EXPRESSION);

		validationBlockSpecEClass = createEClass(VALIDATION_BLOCK_SPEC);
		createEAttribute(validationBlockSpecEClass, VALIDATION_BLOCK_SPEC__BLOCKLY_BLOCK_TYPE);
		createEReference(validationBlockSpecEClass, VALIDATION_BLOCK_SPEC__FIELDS);
		createEReference(validationBlockSpecEClass, VALIDATION_BLOCK_SPEC__INPUTS);

		validationBlockInputEClass = createEClass(VALIDATION_BLOCK_INPUT);
		createEAttribute(validationBlockInputEClass, VALIDATION_BLOCK_INPUT__NAME);
		createEReference(validationBlockInputEClass, VALIDATION_BLOCK_INPUT__EXPRESSION);

		validationExpressionSpecEClass = createEClass(VALIDATION_EXPRESSION_SPEC);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__KIND);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__OPERATOR);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__FIELD_NAME);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__TYPE_NAME);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__LITERAL);
		createEAttribute(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__BLOCKLY_BLOCK_TYPE);
		createEReference(validationExpressionSpecEClass, VALIDATION_EXPRESSION_SPEC__CHILDREN);

		// Create enums
		connectionTypeEEnum = createEEnum(CONNECTION_TYPE);
		fieldTypeEEnum = createEEnum(FIELD_TYPE);
		validationTypeEEnum = createEEnum(VALIDATION_TYPE);
		validationExpressionKindEEnum = createEEnum(VALIDATION_EXPRESSION_KIND);
		workspaceOptionTypeEEnum = createEEnum(WORKSPACE_OPTION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(editorSpecEClass, EditorSpec.class, "EditorSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEditorSpec_DomainName(), ecorePackage.getEString(), "domainName", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_NsURI(), ecorePackage.getEString(), "nsURI", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_NsPrefix(), ecorePackage.getEString(), "nsPrefix", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_ToolboxType(), ecorePackage.getEString(), "toolboxType", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_CodeLanguage(), ecorePackage.getEString(), "codeLanguage", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_CodeFileExtension(), ecorePackage.getEString(), "codeFileExtension", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEditorSpec_RuntimeKind(), ecorePackage.getEString(), "runtimeKind", null, 0, 1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEditorSpec_Categories(), this.getCategorySpec(), null, "categories", null, 0, -1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEditorSpec_BlockTypes(), this.getBlockTypeSpec(), null, "blockTypes", null, 0, -1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEditorSpec_Validations(), this.getValidationSpec(), null, "validations", null, 0, -1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEditorSpec_WorkspaceOptions(), this.getWorkspaceOption(), null, "workspaceOptions", null, 0, -1, EditorSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categorySpecEClass, CategorySpec.class, "CategorySpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCategorySpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, CategorySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCategorySpec_Label(), ecorePackage.getEString(), "label", null, 0, 1, CategorySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCategorySpec_Colour(), ecorePackage.getEInt(), "colour", "200", 0, 1, CategorySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategorySpec_Children(), this.getCategorySpec(), null, "children", null, 0, -1, CategorySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockTypeSpecEClass, BlockTypeSpec.class, "BlockTypeSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlockTypeSpec_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_Label(), ecorePackage.getEString(), "label", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_Colour(), ecorePackage.getEInt(), "colour", "200", 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_CategoryName(), ecorePackage.getEString(), "categoryName", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_SuperTypeName(), ecorePackage.getEString(), "superTypeName", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_ConnectionType(), this.getConnectionType(), "connectionType", "FREE", 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_ConnectionTypeName(), ecorePackage.getEString(), "connectionTypeName", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_OrderedInputNames(), ecorePackage.getEString(), "orderedInputNames", null, 0, -1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_OutputType(), ecorePackage.getEString(), "outputType", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_Message0(), ecorePackage.getEString(), "message0", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_InputsInline(), ecorePackage.getEBooleanObject(), "inputsInline", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_Tooltip(), ecorePackage.getEString(), "tooltip", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_HelpUrl(), ecorePackage.getEString(), "helpUrl", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_CodeTemplate(), ecorePackage.getEString(), "codeTemplate", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlockTypeSpec_IdFieldName(), ecorePackage.getEString(), "idFieldName", null, 0, 1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockTypeSpec_Fields(), this.getFieldSpec(), null, "fields", null, 0, -1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockTypeSpec_StatementInputs(), this.getStatementInputSpec(), null, "statementInputs", null, 0, -1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockTypeSpec_ReferenceFields(), this.getReferenceFieldSpec(), null, "referenceFields", null, 0, -1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlockTypeSpec_ValueInputs(), this.getValueInputSpec(), null, "valueInputs", null, 0, -1, BlockTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedValueEClass, NamedValue.class, "NamedValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, NamedValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dropdownOptionEClass, DropdownOption.class, "DropdownOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDropdownOption_Value(), ecorePackage.getEString(), "value", null, 0, 1, DropdownOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDropdownOption_Label(), ecorePackage.getEString(), "label", null, 0, 1, DropdownOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldSpecEClass, FieldSpec.class, "FieldSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFieldSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_FieldType(), this.getFieldType(), "fieldType", "TEXT", 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFieldSpec_Options(), this.getDropdownOption(), null, "options", null, 0, -1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_ImageUrl(), ecorePackage.getEString(), "imageUrl", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_ImageWidth(), ecorePackage.getEInt(), "imageWidth", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_ImageHeight(), ecorePackage.getEInt(), "imageHeight", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_ImageAlt(), ecorePackage.getEString(), "imageAlt", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Min(), ecorePackage.getEString(), "min", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Max(), ecorePackage.getEString(), "max", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UpperBound(), ecorePackage.getEInt(), "upperBound", "1", 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Unique(), ecorePackage.getEBoolean(), "unique", "true", 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Ordered(), ecorePackage.getEBoolean(), "ordered", "true", 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_Id(), ecorePackage.getEBoolean(), "id", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiWidget(), ecorePackage.getEString(), "uiWidget", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiLabel(), ecorePackage.getEString(), "uiLabel", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiHelp(), ecorePackage.getEString(), "uiHelp", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiPlaceholder(), ecorePackage.getEString(), "uiPlaceholder", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiGroup(), ecorePackage.getEString(), "uiGroup", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiOrder(), ecorePackage.getEIntegerObject(), "uiOrder", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiReadonly(), ecorePackage.getEBoolean(), "uiReadonly", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiHidden(), ecorePackage.getEBoolean(), "uiHidden", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldSpec_UiVariant(), ecorePackage.getEString(), "uiVariant", null, 0, 1, FieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statementInputSpecEClass, StatementInputSpec.class, "StatementInputSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStatementInputSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_CheckType(), ecorePackage.getEString(), "checkType", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UpperBound(), ecorePackage.getEInt(), "upperBound", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiWidget(), ecorePackage.getEString(), "uiWidget", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiLabel(), ecorePackage.getEString(), "uiLabel", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiHelp(), ecorePackage.getEString(), "uiHelp", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiPlaceholder(), ecorePackage.getEString(), "uiPlaceholder", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiGroup(), ecorePackage.getEString(), "uiGroup", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiOrder(), ecorePackage.getEIntegerObject(), "uiOrder", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiReadonly(), ecorePackage.getEBoolean(), "uiReadonly", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiHidden(), ecorePackage.getEBoolean(), "uiHidden", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatementInputSpec_UiVariant(), ecorePackage.getEString(), "uiVariant", null, 0, 1, StatementInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceFieldSpecEClass, ReferenceFieldSpec.class, "ReferenceFieldSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceFieldSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_TargetTypeName(), ecorePackage.getEString(), "targetTypeName", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UpperBound(), ecorePackage.getEInt(), "upperBound", "1", 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_Unique(), ecorePackage.getEBoolean(), "unique", "true", 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_Ordered(), ecorePackage.getEBoolean(), "ordered", "true", 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_OppositeName(), ecorePackage.getEString(), "oppositeName", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiWidget(), ecorePackage.getEString(), "uiWidget", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiLabel(), ecorePackage.getEString(), "uiLabel", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiHelp(), ecorePackage.getEString(), "uiHelp", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiPlaceholder(), ecorePackage.getEString(), "uiPlaceholder", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiGroup(), ecorePackage.getEString(), "uiGroup", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiOrder(), ecorePackage.getEIntegerObject(), "uiOrder", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiReadonly(), ecorePackage.getEBoolean(), "uiReadonly", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiHidden(), ecorePackage.getEBoolean(), "uiHidden", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_UiVariant(), ecorePackage.getEString(), "uiVariant", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceFieldSpec_ReferenceLabelField(), ecorePackage.getEString(), "referenceLabelField", null, 0, 1, ReferenceFieldSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueInputSpecEClass, ValueInputSpec.class, "ValueInputSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueInputSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_CheckType(), ecorePackage.getEString(), "checkType", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_ShadowBlockType(), ecorePackage.getEString(), "shadowBlockType", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiWidget(), ecorePackage.getEString(), "uiWidget", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiLabel(), ecorePackage.getEString(), "uiLabel", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiHelp(), ecorePackage.getEString(), "uiHelp", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiPlaceholder(), ecorePackage.getEString(), "uiPlaceholder", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiGroup(), ecorePackage.getEString(), "uiGroup", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiOrder(), ecorePackage.getEIntegerObject(), "uiOrder", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiReadonly(), ecorePackage.getEBoolean(), "uiReadonly", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiHidden(), ecorePackage.getEBoolean(), "uiHidden", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInputSpec_UiVariant(), ecorePackage.getEString(), "uiVariant", null, 0, 1, ValueInputSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workspaceOptionEClass, WorkspaceOption.class, "WorkspaceOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkspaceOption_Key(), ecorePackage.getEString(), "key", null, 0, 1, WorkspaceOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspaceOption_Value(), ecorePackage.getEString(), "value", null, 0, 1, WorkspaceOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspaceOption_ValueType(), this.getWorkspaceOptionType(), "valueType", "STRING", 0, 1, WorkspaceOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkspaceOption_Children(), this.getWorkspaceOption(), null, "children", null, 0, -1, WorkspaceOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validationSpecEClass, ValidationSpec.class, "ValidationSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidationSpec_Type(), this.getValidationType(), "type", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_TargetType(), ecorePackage.getEString(), "targetType", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_PredecessorType(), ecorePackage.getEString(), "predecessorType", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_OwnerType(), ecorePackage.getEString(), "ownerType", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_ContainmentName(), ecorePackage.getEString(), "containmentName", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_LowerBound(), ecorePackage.getEInt(), "lowerBound", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_UpperBound(), ecorePackage.getEInt(), "upperBound", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_FieldKind(), ecorePackage.getEString(), "fieldKind", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationSpec_Message(), ecorePackage.getEString(), "message", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationSpec_VisualBlock(), this.getValidationBlockSpec(), null, "visualBlock", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationSpec_VisualExpression(), this.getValidationExpressionSpec(), null, "visualExpression", null, 0, 1, ValidationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validationBlockSpecEClass, ValidationBlockSpec.class, "ValidationBlockSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidationBlockSpec_BlocklyBlockType(), ecorePackage.getEString(), "blocklyBlockType", null, 0, 1, ValidationBlockSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationBlockSpec_Fields(), this.getNamedValue(), null, "fields", null, 0, -1, ValidationBlockSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationBlockSpec_Inputs(), this.getValidationBlockInput(), null, "inputs", null, 0, -1, ValidationBlockSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validationBlockInputEClass, ValidationBlockInput.class, "ValidationBlockInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidationBlockInput_Name(), ecorePackage.getEString(), "name", null, 0, 1, ValidationBlockInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationBlockInput_Expression(), this.getValidationExpressionSpec(), null, "expression", null, 0, 1, ValidationBlockInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validationExpressionSpecEClass, ValidationExpressionSpec.class, "ValidationExpressionSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidationExpressionSpec_Kind(), this.getValidationExpressionKind(), "kind", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationExpressionSpec_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationExpressionSpec_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationExpressionSpec_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationExpressionSpec_Literal(), ecorePackage.getEString(), "literal", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidationExpressionSpec_BlocklyBlockType(), ecorePackage.getEString(), "blocklyBlockType", null, 0, 1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidationExpressionSpec_Children(), this.getValidationExpressionSpec(), null, "children", null, 0, -1, ValidationExpressionSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(connectionTypeEEnum, ConnectionType.class, "ConnectionType");
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.NONE);
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.FREE);
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.TYPED);
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.OUTPUT);
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.OUTPUT_TYPED);

		initEEnum(fieldTypeEEnum, FieldType.class, "FieldType");
		addEEnumLiteral(fieldTypeEEnum, FieldType.TEXT);
		addEEnumLiteral(fieldTypeEEnum, FieldType.INTEGER);
		addEEnumLiteral(fieldTypeEEnum, FieldType.FLOAT);
		addEEnumLiteral(fieldTypeEEnum, FieldType.BOOLEAN);
		addEEnumLiteral(fieldTypeEEnum, FieldType.DROPDOWN);
		addEEnumLiteral(fieldTypeEEnum, FieldType.COLOUR);
		addEEnumLiteral(fieldTypeEEnum, FieldType.ANGLE);
		addEEnumLiteral(fieldTypeEEnum, FieldType.IMAGE);
		addEEnumLiteral(fieldTypeEEnum, FieldType.LABEL);

		initEEnum(validationTypeEEnum, ValidationType.class, "ValidationType");
		addEEnumLiteral(validationTypeEEnum, ValidationType.MUST_FOLLOW);
		addEEnumLiteral(validationTypeEEnum, ValidationType.CARDINALITY);
		addEEnumLiteral(validationTypeEEnum, ValidationType.REQUIRED);
		addEEnumLiteral(validationTypeEEnum, ValidationType.FIELD_CARDINALITY);
		addEEnumLiteral(validationTypeEEnum, ValidationType.UNIQUE);
		addEEnumLiteral(validationTypeEEnum, ValidationType.EXPRESSION);

		initEEnum(validationExpressionKindEEnum, ValidationExpressionKind.class, "ValidationExpressionKind");
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.LOGIC_OPERATION);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.LOGIC_NEGATE);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.LOGIC_COMPARE);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.NUMBER_LITERAL);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.STRING_LITERAL);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.BOOLEAN_LITERAL);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.FIELD_VALUE);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.FIELD_NUMBER);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.FIELD_EXISTS);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.FIELD_COUNT);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.FIELD_UNIQUE);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.TYPE_FIELD_UNIQUE);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.INPUT_COUNT);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.PREVIOUS_BLOCK_IS);
		addEEnumLiteral(validationExpressionKindEEnum, ValidationExpressionKind.CUSTOM);

		initEEnum(workspaceOptionTypeEEnum, WorkspaceOptionType.class, "WorkspaceOptionType");
		addEEnumLiteral(workspaceOptionTypeEEnum, WorkspaceOptionType.STRING);
		addEEnumLiteral(workspaceOptionTypeEEnum, WorkspaceOptionType.INTEGER);
		addEEnumLiteral(workspaceOptionTypeEEnum, WorkspaceOptionType.BOOLEAN);
		addEEnumLiteral(workspaceOptionTypeEEnum, WorkspaceOptionType.OBJECT);

		// Create resource
		createResource(eNS_URI);
	}

} //BlocklySpecPackageImpl
