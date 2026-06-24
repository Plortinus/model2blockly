package io.github.plortinus.model2blockly.blocklyspec;

public class DropdownOption {

	private final String value;
	private final String label;

	public DropdownOption(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() { return value; }
	public String getLabel() { return label; }
}
