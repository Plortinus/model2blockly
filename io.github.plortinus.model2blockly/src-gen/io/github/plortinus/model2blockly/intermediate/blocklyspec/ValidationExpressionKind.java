/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Validation Expression Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getValidationExpressionKind()
 * @model
 * @generated
 */
public enum ValidationExpressionKind implements Enumerator {
	/**
	 * The '<em><b>LOGIC OPERATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_OPERATION_VALUE
	 * @generated
	 * @ordered
	 */
	LOGIC_OPERATION(0, "LOGIC_OPERATION", "LOGIC_OPERATION"),

	/**
	 * The '<em><b>LOGIC NEGATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_NEGATE_VALUE
	 * @generated
	 * @ordered
	 */
	LOGIC_NEGATE(1, "LOGIC_NEGATE", "LOGIC_NEGATE"),

	/**
	 * The '<em><b>LOGIC COMPARE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_COMPARE_VALUE
	 * @generated
	 * @ordered
	 */
	LOGIC_COMPARE(2, "LOGIC_COMPARE", "LOGIC_COMPARE"),

	/**
	 * The '<em><b>NUMBER LITERAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER_LITERAL_VALUE
	 * @generated
	 * @ordered
	 */
	NUMBER_LITERAL(3, "NUMBER_LITERAL", "NUMBER_LITERAL"),

	/**
	 * The '<em><b>STRING LITERAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRING_LITERAL_VALUE
	 * @generated
	 * @ordered
	 */
	STRING_LITERAL(4, "STRING_LITERAL", "STRING_LITERAL"),

	/**
	 * The '<em><b>BOOLEAN LITERAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOOLEAN_LITERAL_VALUE
	 * @generated
	 * @ordered
	 */
	BOOLEAN_LITERAL(5, "BOOLEAN_LITERAL", "BOOLEAN_LITERAL"),

	/**
	 * The '<em><b>FIELD VALUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_VALUE(6, "FIELD_VALUE", "FIELD_VALUE"),

	/**
	 * The '<em><b>FIELD NUMBER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_NUMBER_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_NUMBER(7, "FIELD_NUMBER", "FIELD_NUMBER"),

	/**
	 * The '<em><b>FIELD EXISTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_EXISTS_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_EXISTS(8, "FIELD_EXISTS", "FIELD_EXISTS"),

	/**
	 * The '<em><b>FIELD COUNT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_COUNT_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_COUNT(9, "FIELD_COUNT", "FIELD_COUNT"),

	/**
	 * The '<em><b>FIELD UNIQUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_UNIQUE_VALUE
	 * @generated
	 * @ordered
	 */
	FIELD_UNIQUE(10, "FIELD_UNIQUE", "FIELD_UNIQUE"),

	/**
	 * The '<em><b>TYPE FIELD UNIQUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TYPE_FIELD_UNIQUE_VALUE
	 * @generated
	 * @ordered
	 */
	TYPE_FIELD_UNIQUE(11, "TYPE_FIELD_UNIQUE", "TYPE_FIELD_UNIQUE"),

	/**
	 * The '<em><b>INPUT COUNT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INPUT_COUNT_VALUE
	 * @generated
	 * @ordered
	 */
	INPUT_COUNT(12, "INPUT_COUNT", "INPUT_COUNT"),

	/**
	 * The '<em><b>PREVIOUS BLOCK IS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PREVIOUS_BLOCK_IS_VALUE
	 * @generated
	 * @ordered
	 */
	PREVIOUS_BLOCK_IS(13, "PREVIOUS_BLOCK_IS", "PREVIOUS_BLOCK_IS"),

	/**
	 * The '<em><b>CUSTOM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUSTOM_VALUE
	 * @generated
	 * @ordered
	 */
	CUSTOM(14, "CUSTOM", "CUSTOM");

	/**
	 * The '<em><b>LOGIC OPERATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_OPERATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGIC_OPERATION_VALUE = 0;

	/**
	 * The '<em><b>LOGIC NEGATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_NEGATE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGIC_NEGATE_VALUE = 1;

	/**
	 * The '<em><b>LOGIC COMPARE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGIC_COMPARE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGIC_COMPARE_VALUE = 2;

	/**
	 * The '<em><b>NUMBER LITERAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NUMBER_LITERAL_VALUE = 3;

	/**
	 * The '<em><b>STRING LITERAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_VALUE = 4;

	/**
	 * The '<em><b>BOOLEAN LITERAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOOLEAN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_VALUE = 5;

	/**
	 * The '<em><b>FIELD VALUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_VALUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_VALUE_VALUE = 6;

	/**
	 * The '<em><b>FIELD NUMBER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_NUMBER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_NUMBER_VALUE = 7;

	/**
	 * The '<em><b>FIELD EXISTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_EXISTS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_EXISTS_VALUE = 8;

	/**
	 * The '<em><b>FIELD COUNT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_COUNT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_COUNT_VALUE = 9;

	/**
	 * The '<em><b>FIELD UNIQUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIELD_UNIQUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FIELD_UNIQUE_VALUE = 10;

	/**
	 * The '<em><b>TYPE FIELD UNIQUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TYPE_FIELD_UNIQUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_FIELD_UNIQUE_VALUE = 11;

	/**
	 * The '<em><b>INPUT COUNT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INPUT_COUNT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INPUT_COUNT_VALUE = 12;

	/**
	 * The '<em><b>PREVIOUS BLOCK IS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PREVIOUS_BLOCK_IS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PREVIOUS_BLOCK_IS_VALUE = 13;

	/**
	 * The '<em><b>CUSTOM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUSTOM
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_VALUE = 14;

	/**
	 * An array of all the '<em><b>Validation Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ValidationExpressionKind[] VALUES_ARRAY =
		new ValidationExpressionKind[] {
			LOGIC_OPERATION,
			LOGIC_NEGATE,
			LOGIC_COMPARE,
			NUMBER_LITERAL,
			STRING_LITERAL,
			BOOLEAN_LITERAL,
			FIELD_VALUE,
			FIELD_NUMBER,
			FIELD_EXISTS,
			FIELD_COUNT,
			FIELD_UNIQUE,
			TYPE_FIELD_UNIQUE,
			INPUT_COUNT,
			PREVIOUS_BLOCK_IS,
			CUSTOM,
		};

	/**
	 * A public read-only list of all the '<em><b>Validation Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ValidationExpressionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Validation Expression Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationExpressionKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ValidationExpressionKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Validation Expression Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationExpressionKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ValidationExpressionKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Validation Expression Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ValidationExpressionKind get(int value) {
		switch (value) {
			case LOGIC_OPERATION_VALUE: return LOGIC_OPERATION;
			case LOGIC_NEGATE_VALUE: return LOGIC_NEGATE;
			case LOGIC_COMPARE_VALUE: return LOGIC_COMPARE;
			case NUMBER_LITERAL_VALUE: return NUMBER_LITERAL;
			case STRING_LITERAL_VALUE: return STRING_LITERAL;
			case BOOLEAN_LITERAL_VALUE: return BOOLEAN_LITERAL;
			case FIELD_VALUE_VALUE: return FIELD_VALUE;
			case FIELD_NUMBER_VALUE: return FIELD_NUMBER;
			case FIELD_EXISTS_VALUE: return FIELD_EXISTS;
			case FIELD_COUNT_VALUE: return FIELD_COUNT;
			case FIELD_UNIQUE_VALUE: return FIELD_UNIQUE;
			case TYPE_FIELD_UNIQUE_VALUE: return TYPE_FIELD_UNIQUE;
			case INPUT_COUNT_VALUE: return INPUT_COUNT;
			case PREVIOUS_BLOCK_IS_VALUE: return PREVIOUS_BLOCK_IS;
			case CUSTOM_VALUE: return CUSTOM;
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
	private ValidationExpressionKind(int value, String name, String literal) {
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
	
} //ValidationExpressionKind
