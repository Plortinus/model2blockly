package io.github.plortinus.model2blockly.blocklyspec;

/**
 * A value input on a block — the horizontal puzzle-piece connector that
 * accepts a single "output" block.  Maps to Blockly's input_value.
 */
public class ValueInputSpec {

	private String name;
	private String checkType;
	private String shadowBlockType;
	private String uiWidget;
	private String uiLabel;
	private String uiHelp;
	private String uiPlaceholder;
	private String uiGroup;
	private Integer uiOrder;
	private boolean uiReadonly;
	private boolean uiHidden;
	private String uiVariant;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCheckType() { return checkType; }
	public void setCheckType(String checkType) { this.checkType = checkType; }

	public String getShadowBlockType() { return shadowBlockType; }
	public void setShadowBlockType(String shadowBlockType) { this.shadowBlockType = shadowBlockType; }

	public String getUiWidget() { return uiWidget; }
	public void setUiWidget(String uiWidget) { this.uiWidget = uiWidget; }

	public String getUiLabel() { return uiLabel; }
	public void setUiLabel(String uiLabel) { this.uiLabel = uiLabel; }

	public String getUiHelp() { return uiHelp; }
	public void setUiHelp(String uiHelp) { this.uiHelp = uiHelp; }

	public String getUiPlaceholder() { return uiPlaceholder; }
	public void setUiPlaceholder(String uiPlaceholder) { this.uiPlaceholder = uiPlaceholder; }

	public String getUiGroup() { return uiGroup; }
	public void setUiGroup(String uiGroup) { this.uiGroup = uiGroup; }

	public Integer getUiOrder() { return uiOrder; }
	public void setUiOrder(Integer uiOrder) { this.uiOrder = uiOrder; }

	public boolean isUiReadonly() { return uiReadonly; }
	public void setUiReadonly(boolean uiReadonly) { this.uiReadonly = uiReadonly; }

	public boolean isUiHidden() { return uiHidden; }
	public void setUiHidden(boolean uiHidden) { this.uiHidden = uiHidden; }

	public String getUiVariant() { return uiVariant; }
	public void setUiVariant(String uiVariant) { this.uiVariant = uiVariant; }
}
