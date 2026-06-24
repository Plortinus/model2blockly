package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.List;

public class CategorySpec {

	private String name;
	private String label;
	private int colour = 200;
	private List<CategorySpec> children = new ArrayList<>();

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	public int getColour() { return colour; }
	public void setColour(int colour) { this.colour = colour; }

	public List<CategorySpec> getChildren() { return children; }
	public void setChildren(List<CategorySpec> children) {
		this.children = children != null ? children : new ArrayList<>();
	}

	/**
	 * Recursively find or create a child category by name.
	 * Used by adapters to build nested hierarchies from path notation (e.g. "Control/Loop").
	 */
	public CategorySpec getOrCreateChild(String childName) {
		for (CategorySpec child : children) {
			if (childName.equals(child.getName())) return child;
		}
		CategorySpec child = new CategorySpec();
		child.setName(childName);
		child.setLabel(childName);
		child.setColour(this.colour);
		children.add(child);
		return child;
	}

	/** Collects all category names in the subtree (this + descendants). */
	public List<String> allCategoryNames() {
		List<String> names = new ArrayList<>();
		names.add(name);
		for (CategorySpec child : children) {
			names.addAll(child.allCategoryNames());
		}
		return names;
	}
}
