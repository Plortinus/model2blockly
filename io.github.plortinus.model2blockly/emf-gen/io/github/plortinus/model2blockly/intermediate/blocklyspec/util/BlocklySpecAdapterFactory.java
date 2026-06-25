/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage
 * @generated
 */
public class BlocklySpecAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BlocklySpecPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlocklySpecAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BlocklySpecPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlocklySpecSwitch<Adapter> modelSwitch =
		new BlocklySpecSwitch<Adapter>() {
			@Override
			public Adapter caseEditorSpec(EditorSpec object) {
				return createEditorSpecAdapter();
			}
			@Override
			public Adapter caseCategorySpec(CategorySpec object) {
				return createCategorySpecAdapter();
			}
			@Override
			public Adapter caseBlockTypeSpec(BlockTypeSpec object) {
				return createBlockTypeSpecAdapter();
			}
			@Override
			public Adapter caseNamedValue(NamedValue object) {
				return createNamedValueAdapter();
			}
			@Override
			public Adapter caseDropdownOption(DropdownOption object) {
				return createDropdownOptionAdapter();
			}
			@Override
			public Adapter caseFieldSpec(FieldSpec object) {
				return createFieldSpecAdapter();
			}
			@Override
			public Adapter caseStatementInputSpec(StatementInputSpec object) {
				return createStatementInputSpecAdapter();
			}
			@Override
			public Adapter caseReferenceFieldSpec(ReferenceFieldSpec object) {
				return createReferenceFieldSpecAdapter();
			}
			@Override
			public Adapter caseValueInputSpec(ValueInputSpec object) {
				return createValueInputSpecAdapter();
			}
			@Override
			public Adapter caseWorkspaceOption(WorkspaceOption object) {
				return createWorkspaceOptionAdapter();
			}
			@Override
			public Adapter caseValidationSpec(ValidationSpec object) {
				return createValidationSpecAdapter();
			}
			@Override
			public Adapter caseValidationBlockSpec(ValidationBlockSpec object) {
				return createValidationBlockSpecAdapter();
			}
			@Override
			public Adapter caseValidationBlockInput(ValidationBlockInput object) {
				return createValidationBlockInputAdapter();
			}
			@Override
			public Adapter caseValidationExpressionSpec(ValidationExpressionSpec object) {
				return createValidationExpressionSpecAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec <em>Editor Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec
	 * @generated
	 */
	public Adapter createEditorSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec <em>Category Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec
	 * @generated
	 */
	public Adapter createCategorySpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec <em>Block Type Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec
	 * @generated
	 */
	public Adapter createBlockTypeSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue <em>Named Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.NamedValue
	 * @generated
	 */
	public Adapter createNamedValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption <em>Dropdown Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption
	 * @generated
	 */
	public Adapter createDropdownOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec <em>Field Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec
	 * @generated
	 */
	public Adapter createFieldSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec <em>Statement Input Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec
	 * @generated
	 */
	public Adapter createStatementInputSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec <em>Reference Field Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec
	 * @generated
	 */
	public Adapter createReferenceFieldSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec <em>Value Input Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec
	 * @generated
	 */
	public Adapter createValueInputSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption <em>Workspace Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.WorkspaceOption
	 * @generated
	 */
	public Adapter createWorkspaceOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec <em>Validation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec
	 * @generated
	 */
	public Adapter createValidationSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec <em>Validation Block Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockSpec
	 * @generated
	 */
	public Adapter createValidationBlockSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput <em>Validation Block Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationBlockInput
	 * @generated
	 */
	public Adapter createValidationBlockInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec <em>Validation Expression Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationExpressionSpec
	 * @generated
	 */
	public Adapter createValidationExpressionSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //BlocklySpecAdapterFactory
