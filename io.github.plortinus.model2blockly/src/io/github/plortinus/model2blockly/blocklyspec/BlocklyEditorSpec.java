package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Platform-independent intermediate model that captures everything needed
 * to generate a Blockly editor. Can be constructed from either a
 * DomainModel (DSL) or an EPackage (Ecore).
 */
public class BlocklyEditorSpec {

	private String domainName;
	private String nsURI;
	private String nsPrefix;
	private List<CategorySpec> categories = new ArrayList<>();
	private List<BlockTypeSpec> blockTypes = new ArrayList<>();
	private List<ValidationSpec> validations = new ArrayList<>();
	private Map<String, Object> workspaceOptions = new LinkedHashMap<>();
	private String toolboxType; // "flyout", "category", or null (auto-detect)
	private String codeLanguage = "text";
	private String codeFileExtension = "txt";
	private String runtimeKind;

	public String getDomainName() { return domainName; }
	public void setDomainName(String domainName) { this.domainName = domainName; }

	public String getNsURI() { return nsURI; }
	public void setNsURI(String nsURI) { this.nsURI = nsURI; }

	public String getNsPrefix() { return nsPrefix; }
	public void setNsPrefix(String nsPrefix) { this.nsPrefix = nsPrefix; }

	public List<CategorySpec> getCategories() { return categories; }
	public void setCategories(List<CategorySpec> categories) { this.categories = categories; }

	public List<BlockTypeSpec> getBlockTypes() { return blockTypes; }
	public void setBlockTypes(List<BlockTypeSpec> blockTypes) { this.blockTypes = blockTypes; }

	public List<ValidationSpec> getValidations() { return validations; }
	public void setValidations(List<ValidationSpec> validations) { this.validations = validations; }

	public Map<String, Object> getWorkspaceOptions() { return workspaceOptions; }
	public void setWorkspaceOptions(Map<String, Object> workspaceOptions) {
		this.workspaceOptions = workspaceOptions != null ? workspaceOptions : new LinkedHashMap<>();
	}

	public void putWorkspaceOption(String key, Object value) {
		if (key == null || key.isEmpty()) return;
		workspaceOptions.put(key, value);
	}

	public String getToolboxType() { return toolboxType; }
	public void setToolboxType(String toolboxType) { this.toolboxType = toolboxType; }

	public String getCodeLanguage() { return codeLanguage; }
	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = (codeLanguage != null && !codeLanguage.isBlank()) ? codeLanguage : "text";
	}

	public String getCodeFileExtension() { return codeFileExtension; }
	public void setCodeFileExtension(String codeFileExtension) {
		this.codeFileExtension = (codeFileExtension != null && !codeFileExtension.isBlank()) ? codeFileExtension : "txt";
	}

	public String getRuntimeKind() { return runtimeKind; }
	public void setRuntimeKind(String runtimeKind) {
		this.runtimeKind = (runtimeKind != null && !runtimeKind.isBlank()) ? runtimeKind : null;
	}

	/**
	 * Resolves the effective toolbox type:
	 * - If explicitly set → use that value
	 * - If no categories defined → "flyout"
	 * - Otherwise → "category"
	 */
	public String getEffectiveToolboxType() {
		if (toolboxType != null && !toolboxType.isEmpty()) return toolboxType;
		boolean hasCategories = false;
		for (BlockTypeSpec bt : blockTypes) {
			if (!bt.isAbstract() && bt.getCategoryName() != null) {
				hasCategories = true;
				break;
			}
		}
		if (!hasCategories && categories.isEmpty()) return "flyout";
		return "category";
	}

	/** Returns only non-abstract block types (those that appear in toolbox). */
	public List<BlockTypeSpec> getConcreteBlockTypes() {
		List<BlockTypeSpec> result = new ArrayList<>();
		for (BlockTypeSpec bt : blockTypes) {
			if (!bt.isAbstract()) result.add(bt);
		}
		return result;
	}
}
