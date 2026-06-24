package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.List;

public class FieldSpec {

	public enum FieldType {
		TEXT, INTEGER, FLOAT, BOOLEAN, DROPDOWN,
		COLOUR, ANGLE, IMAGE, LABEL
	}

	private String name;
	private FieldType fieldType = FieldType.TEXT;
	private String defaultValue;
	private List<DropdownOption> options = new ArrayList<>();
	private String imageUrl;
	private int imageWidth;
	private int imageHeight;
	private String imageAlt;
	private String min;
	private String max;
	private boolean required;
	private int lowerBound;
	private int upperBound = 1; // 1 means single-valued; 0 means unbounded in the intermediate spec.
	private boolean unique = true;
	private boolean ordered = true;
	private boolean id;
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

	public FieldType getFieldType() { return fieldType; }
	public void setFieldType(FieldType fieldType) { this.fieldType = fieldType; }

	public String getDefaultValue() { return defaultValue; }
	public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

	public List<DropdownOption> getOptions() { return options; }
	public void setOptions(List<DropdownOption> options) { this.options = options; }

	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

	public int getImageWidth() { return imageWidth; }
	public void setImageWidth(int imageWidth) { this.imageWidth = imageWidth; }

	public int getImageHeight() { return imageHeight; }
	public void setImageHeight(int imageHeight) { this.imageHeight = imageHeight; }

	public String getImageAlt() { return imageAlt; }
	public void setImageAlt(String imageAlt) { this.imageAlt = imageAlt; }

	public String getMin() { return min; }
	public void setMin(String min) { this.min = min; }

	public String getMax() { return max; }
	public void setMax(String max) { this.max = max; }

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

	public boolean isId() { return id; }
	public void setId(boolean id) { this.id = id; }

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
