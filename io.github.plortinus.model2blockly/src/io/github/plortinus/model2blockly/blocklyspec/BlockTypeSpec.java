package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.List;

public class BlockTypeSpec {

	public enum ConnectionType { NONE, FREE, TYPED, OUTPUT, OUTPUT_TYPED }

	private String typeName;
	private String label;
	private int colour = 200;
	private String categoryName;
	private boolean isAbstract;
	private String superTypeName;
	private ConnectionType connectionType = ConnectionType.FREE;
	private String connectionTypeName;
	private List<FieldSpec> fields = new ArrayList<>();
	private List<StatementInputSpec> statementInputs = new ArrayList<>();
	private List<ReferenceFieldSpec> referenceFields = new ArrayList<>();
	private List<ValueInputSpec> valueInputs = new ArrayList<>();
	private List<String> orderedInputNames = new ArrayList<>();
	private String outputType;
	private String message0;
	private Boolean inputsInline;
	private String tooltip;
	private String helpUrl;
	private String codeTemplate;
	private String idFieldName;

	public String getTypeName() { return typeName; }
	public void setTypeName(String typeName) { this.typeName = typeName; }

	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	public int getColour() { return colour; }
	public void setColour(int colour) { this.colour = colour; }

	public String getCategoryName() { return categoryName; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

	public boolean isAbstract() { return isAbstract; }
	public void setAbstract(boolean isAbstract) { this.isAbstract = isAbstract; }

	public String getSuperTypeName() { return superTypeName; }
	public void setSuperTypeName(String superTypeName) { this.superTypeName = superTypeName; }

	public ConnectionType getConnectionType() { return connectionType; }
	public void setConnectionType(ConnectionType connectionType) { this.connectionType = connectionType; }

	public String getConnectionTypeName() { return connectionTypeName; }
	public void setConnectionTypeName(String connectionTypeName) { this.connectionTypeName = connectionTypeName; }

	public List<FieldSpec> getFields() { return fields; }
	public void setFields(List<FieldSpec> fields) { this.fields = fields; }

	public List<StatementInputSpec> getStatementInputs() { return statementInputs; }
	public void setStatementInputs(List<StatementInputSpec> statementInputs) { this.statementInputs = statementInputs; }

	public List<ReferenceFieldSpec> getReferenceFields() { return referenceFields; }
	public void setReferenceFields(List<ReferenceFieldSpec> referenceFields) { this.referenceFields = referenceFields; }

	public List<ValueInputSpec> getValueInputs() { return valueInputs; }
	public void setValueInputs(List<ValueInputSpec> valueInputs) { this.valueInputs = valueInputs; }

	public List<String> getOrderedInputNames() { return orderedInputNames; }
	public void setOrderedInputNames(List<String> orderedInputNames) { this.orderedInputNames = orderedInputNames; }

	public String getOutputType() { return outputType; }
	public void setOutputType(String outputType) { this.outputType = outputType; }

	public String getMessage0() { return message0; }
	public void setMessage0(String message0) { this.message0 = message0; }

	public Boolean getInputsInline() { return inputsInline; }
	public void setInputsInline(Boolean inputsInline) { this.inputsInline = inputsInline; }

	public String getTooltip() { return tooltip; }
	public void setTooltip(String tooltip) { this.tooltip = tooltip; }

	public String getHelpUrl() { return helpUrl; }
	public void setHelpUrl(String helpUrl) { this.helpUrl = helpUrl; }

	public String getCodeTemplate() { return codeTemplate; }
	public void setCodeTemplate(String codeTemplate) { this.codeTemplate = codeTemplate; }

	public String getIdFieldName() { return idFieldName; }
	public void setIdFieldName(String idFieldName) { this.idFieldName = idFieldName; }
}
