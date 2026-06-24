/**
 */
package io.github.plortinus.model2blockly.intermediate.blocklyspec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Connection Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage#getConnectionType()
 * @model
 * @generated
 */
public enum ConnectionType implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"),

	/**
	 * The '<em><b>FREE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FREE_VALUE
	 * @generated
	 * @ordered
	 */
	FREE(1, "FREE", "FREE"),

	/**
	 * The '<em><b>TYPED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TYPED_VALUE
	 * @generated
	 * @ordered
	 */
	TYPED(2, "TYPED", "TYPED"),

	/**
	 * The '<em><b>OUTPUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUT_VALUE
	 * @generated
	 * @ordered
	 */
	OUTPUT(3, "OUTPUT", "OUTPUT"),

	/**
	 * The '<em><b>OUTPUT TYPED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUT_TYPED_VALUE
	 * @generated
	 * @ordered
	 */
	OUTPUT_TYPED(4, "OUTPUT_TYPED", "OUTPUT_TYPED");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>FREE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FREE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FREE_VALUE = 1;

	/**
	 * The '<em><b>TYPED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TYPED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TYPED_VALUE = 2;

	/**
	 * The '<em><b>OUTPUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUT_VALUE = 3;

	/**
	 * The '<em><b>OUTPUT TYPED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUT_TYPED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUT_TYPED_VALUE = 4;

	/**
	 * An array of all the '<em><b>Connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ConnectionType[] VALUES_ARRAY =
		new ConnectionType[] {
			NONE,
			FREE,
			TYPED,
			OUTPUT,
			OUTPUT_TYPED,
		};

	/**
	 * A public read-only list of all the '<em><b>Connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ConnectionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Connection Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConnectionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConnectionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Connection Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConnectionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConnectionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Connection Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ConnectionType get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case FREE_VALUE: return FREE;
			case TYPED_VALUE: return TYPED;
			case OUTPUT_VALUE: return OUTPUT;
			case OUTPUT_TYPED_VALUE: return OUTPUT_TYPED;
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
	private ConnectionType(int value, String name, String literal) {
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
	
} //ConnectionType
