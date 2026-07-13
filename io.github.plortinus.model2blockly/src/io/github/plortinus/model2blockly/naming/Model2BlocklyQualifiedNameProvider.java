package io.github.plortinus.model2blockly.naming;

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import io.github.plortinus.model2blockly.model2Blockly.CategoryDef;
import io.github.plortinus.model2blockly.model2Blockly.ClassDef;

/**
 * Qualified name for ClassDef is its name — allows cross-references
 * (extends, contains, reference, constraint targets).
 */
public class Model2BlocklyQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	public QualifiedName qualifiedName(ClassDef cls) {
		return QualifiedName.create(cls.getName());
	}

	/**
	 * Nested categories are referenced from class declarations by their DSL ID.
	 * Give every category a simple qualified name so child categories are visible
	 * to references such as {@code category Content}.
	 */
	public QualifiedName qualifiedName(CategoryDef category) {
		return QualifiedName.create(category.getName());
	}
}
