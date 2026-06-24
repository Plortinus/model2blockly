package io.github.plortinus.model2blockly.blocklyspec;

public class ReferenceFieldSpec {

	private String name;
	private String targetTypeName;
	private boolean required;
	private int lowerBound;
	private int upperBound = 1; // 1 means single-valued; 0 means unbounded in the intermediate spec.
	private boolean unique = true;
	private boolean ordered = true;
	private String oppositeName;
	private String uiWidget;
	private String uiLabel;
	private String uiHelp;
	private String uiPlaceholder;
	private String uiGroup;
	private Integer uiOrder;
	private boolean uiReadonly;
	private boolean uiHidden;
	private String uiVariant;
	private String referenceLabelField;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getTargetTypeName() { return targetTypeName; }
	public void setTargetTypeName(String targetTypeName) { this.targetTypeName = targetTypeName; }

	public boolean isRequired() { return required; }
	public void setRequired(boolean required) { this.required = required; }

	public int getLowerBound() { return lowerBound; }
	public void setLowerBound(int lowerBound) { this.lowerBound = lowerBound; }

	public int getUpperBound() { return upperBound; }
	public void setUpperBound(int upperBound) { this.upperBound = upperBound; }

	public boolean isMany() { return upperBound == 0 || upperBound > 1; }

	public boolean isUnique() { return unique; }
	public void setUnique(boolean unique) { this.unique = unique; }

	public boolean isOrdered() { return ordered; }
	public void setOrdered(boolean ordered) { this.ordered = ordered; }

	public String getOppositeName() { return oppositeName; }
	public void setOppositeName(String oppositeName) { this.oppositeName = oppositeName; }

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

	public String getReferenceLabelField() { return referenceLabelField; }
	public void setReferenceLabelField(String referenceLabelField) { this.referenceLabelField = referenceLabelField; }
}
