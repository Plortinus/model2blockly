/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Validation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationType()
 * @model
 * @generated
 */
public enum ValidationType implements Enumerator {
	/**
	 * The '<em><b>MUST FOLLOW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MUST_FOLLOW_VALUE
	 * @generated
	 * @ordered
	 */
	MUST_FOLLOW(0, "MUST_FOLLOW", "MUST_FOLLOW"),

	/**
	 * The '<em><b>CARDINALITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINALITY_VALUE
	 * @generated
	 * @ordered
	 */
	CARDINALITY(1, "CARDINALITY", "CARDINALITY"),

	/**
	 * The '<em><b>REQUIRED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRED_VALUE
	 * @generated
	 * @ordered
	 */
	REQUIRED(2, "REQUIRED", "REQUIRED"),

	/**
	 * The '<em><b>FIELD CARDINALITY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_CARDINALITY_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_CARDINALITY(3, "FIELD_CARDINALITY", "FIELD_CARDINALITY"),

	/**
	 * The '<em><b>UNIQUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIQUE_VALUE
	 * @generated
	 * @ordered
	 */
	UNIQUE(4, "UNIQUE", "UNIQUE"),

	/**
	 * The '<em><b>EXPRESSION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPRESSION_VALUE
	 * @generated
	 * @ordered
	 */
	EXPRESSION(5, "EXPRESSION", "EXPRESSION");

	/**
	 * The '<em><b>MUST FOLLOW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MUST_FOLLOW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MUST_FOLLOW_VALUE = 0;

	/**
	 * The '<em><b>CARDINALITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINALITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CARDINALITY_VALUE = 1;

	/**
	 * The '<em><b>REQUIRED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REQUIRED_VALUE = 2;

	/**
	 * The '<em><b>FIELD CARDINALITY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_CARDINALITY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_CARDINALITY_VALUE = 3;

	/**
	 * The '<em><b>UNIQUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIQUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNIQUE_VALUE = 4;

	/**
	 * The '<em><b>EXPRESSION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPRESSION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_VALUE = 5;

	/**
	 * An array of all the '<em><b>Validation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ValidationType[] VALUES_ARRAY =
		new ValidationType[] {
			MUST_FOLLOW,
			CARDINALITY,
			REQUIRED,
			FIELD_CARDINALITY,
			UNIQUE,
			EXPRESSION,
		};

	/**
	 * A public read-only list of all the '<em><b>Validation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ValidationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Validation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ValidationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Validation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ValidationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Validation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationType get(int value) {
		switch (value) {
			case MUST_FOLLOW_VALUE: return MUST_FOLLOW;
			case CARDINALITY_VALUE: return CARDINALITY;
			case REQUIRED_VALUE: return REQUIRED;
			case FIELD_CARDINALITY_VALUE: return FIELD_CARDINALITY;
			case UNIQUE_VALUE: return UNIQUE;
			case EXPRESSION_VALUE: return EXPRESSION;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ValidationType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ValidationType
