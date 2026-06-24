package io.github.plortinus.model2blockly.blocklyspec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import io.github.plortinus.model2blockly.model2Blockly.CategoryDef;
import io.github.plortinus.model2blockly.model2Blockly.ClassDef;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.model2Blockly.Feature;
import io.github.plortinus.model2blockly.model2Blockly.ValidationDef;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator.Issue;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.SourceLocation;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.SourceLocator;

/**
 * Best-effort mapping from intermediate-model validation locations back to the
 * original DSL or Ecore source object.
 */
public final class BlocklySpecSourceMapper {
	private static final Pattern BLOCK_LOCATION = Pattern.compile("(?:block|blockTypes)\\[([^\\]]+)\\]");
	private static final Pattern CATEGORY_LOCATION = Pattern.compile("category\\[([^\\]]+)\\]");
	private static final Pattern VALIDATION_LOCATION = Pattern.compile("validation\\[([^\\]]+)\\]");

	private BlocklySpecSourceMapper() {}

	public static SourceLocator forDomainModel(DomainModel domain, String sourceLabel) {
		return issue -> sourceLocation(locateDomainObject(domain, issue), sourceLabel, issue.getLocation());
	}

	public static SourceLocator forEPackage(EPackage pkg, String sourceLabel) {
		return issue -> sourceLocation(locateEcoreObject(pkg, issue), sourceLabel, issue.getLocation());
	}

	public static EObject sourceObjectForDomainIssue(DomainModel domain, Issue issue) {
		return locateDomainObject(domain, issue);
	}

	public static EObject sourceObjectForEcoreIssue(EPackage pkg, Issue issue) {
		return locateEcoreObject(pkg, issue);
	}

	private static EObject locateDomainObject(DomainModel domain, Issue issue) {
		if (domain == null || issue == null) return null;
		String location = issue.getLocation();
		if (isBlank(location) || "domainName".equals(location) || "blockTypes".equals(location)
				|| "validations".equals(location)) {
			return domain;
		}

		String validationName = firstMatch(VALIDATION_LOCATION, location);
		if (!isBlank(validationName)) {
			ValidationDef validation = findValidation(domain, validationName);
			if (validation != null) return validation;
		}

		String categoryName = lastMatch(CATEGORY_LOCATION, location);
		if (!isBlank(categoryName)) {
			CategoryDef category = findCategory(domain, categoryName);
			if (category != null) return category;
		}

		String blockName = firstMatch(BLOCK_LOCATION, location);
		if (!isBlank(blockName)) {
			ClassDef cls = findClass(domain, blockName);
			if (cls == null) return domain;
			String featureName = featureNameAfterBlock(location);
			if (!isBlank(featureName)) {
				Feature feature = findFeature(cls, featureName);
				if (feature != null) return feature;
			}
			return cls;
		}

		return domain;
	}

	private static EObject locateEcoreObject(EPackage pkg, Issue issue) {
		if (pkg == null || issue == null) return null;
		String location = issue.getLocation();
		String blockName = firstMatch(BLOCK_LOCATION, location);
		if (!isBlank(blockName)) {
			EClass eClass = findEClass(pkg, blockName);
			if (eClass == null) return pkg;
			String featureName = featureNameAfterBlock(location);
			if (!isBlank(featureName)) {
				EStructuralFeature feature = eClass.getEStructuralFeature(featureName);
				if (feature != null) return feature;
			}
			return eClass;
		}
		String validationName = firstMatch(VALIDATION_LOCATION, location);
		if (!isBlank(validationName)) {
			EClass eClass = bestEffortClassFromValidationName(pkg, validationName);
			if (eClass != null) return eClass;
		}
		return pkg;
	}

	private static SourceLocation sourceLocation(EObject object, String sourceLabel, String detail) {
		if (object == null) return new SourceLocation(sourceLabel, 0, 0, null);
		ICompositeNode node = null;
		try {
			node = NodeModelUtils.findActualNodeFor(object);
		} catch (RuntimeException ignored) {
			// Non-Xtext resources, such as .ecore XMI, do not have node models.
		}
		if (node != null) {
			return new SourceLocation(sourceLabel, node.getStartLine(), startColumn(node), null);
		}
		return new SourceLocation(sourceLabel, 0, 0, ecoreFragment(object));
	}

	private static int startColumn(ICompositeNode node) {
		try {
			String text = node.getRootNode().getText();
			int offset = node.getTotalOffset();
			int lineStart = text.lastIndexOf('\n', Math.max(0, offset - 1));
			return offset - lineStart;
		} catch (RuntimeException ignored) {
			return 0;
		}
	}

	private static String ecoreFragment(EObject object) {
		if (object.eResource() == null) return null;
		try {
			return "#" + object.eResource().getURIFragment(object);
		} catch (RuntimeException ignored) {
			return null;
		}
	}

	private static String featureNameAfterBlock(String location) {
		if (location == null) return null;
		Matcher matcher = BLOCK_LOCATION.matcher(location);
		if (!matcher.find()) return null;
		int index = matcher.end();
		if (index >= location.length() || location.charAt(index) != '.') return null;
		String tail = location.substring(index + 1);
		int dot = tail.indexOf('.');
		if (dot >= 0) tail = tail.substring(0, dot);
		int bracket = tail.indexOf('[');
		if (bracket >= 0) tail = tail.substring(0, bracket);
		return tail.isBlank() ? null : tail;
	}

	private static String firstMatch(Pattern pattern, String text) {
		if (text == null) return null;
		Matcher matcher = pattern.matcher(text);
		return matcher.find() ? matcher.group(1) : null;
	}

	private static String lastMatch(Pattern pattern, String text) {
		if (text == null) return null;
		Matcher matcher = pattern.matcher(text);
		String match = null;
		while (matcher.find()) {
			match = matcher.group(1);
		}
		return match;
	}

	private static ClassDef findClass(DomainModel domain, String name) {
		for (ClassDef cls : domain.getClasses()) {
			if (name.equals(cls.getName())) return cls;
		}
		return null;
	}

	private static Feature findFeature(ClassDef cls, String name) {
		for (ClassDef current = cls; current != null; current = current.getSuperClass()) {
			for (Feature feature : current.getFeatures()) {
				if (name.equals(feature.getName())) return feature;
			}
		}
		return null;
	}

	private static CategoryDef findCategory(DomainModel domain, String name) {
		for (CategoryDef category : domain.getCategories()) {
			CategoryDef found = findCategory(category, name);
			if (found != null) return found;
		}
		return null;
	}

	private static CategoryDef findCategory(CategoryDef category, String name) {
		if (name.equals(category.getName())) return category;
		for (CategoryDef child : category.getSubcategories()) {
			CategoryDef found = findCategory(child, name);
			if (found != null) return found;
		}
		return null;
	}

	private static ValidationDef findValidation(DomainModel domain, String name) {
		for (ValidationDef validation : domain.getValidations()) {
			if (name.equals(validation.getName()) || name.endsWith("_" + validation.getName())) return validation;
		}
		return null;
	}

	private static EClass findEClass(EPackage pkg, String name) {
		for (EClassifier classifier : pkg.getEClassifiers()) {
			if (classifier instanceof EClass && name.equals(classifier.getName())) {
				return (EClass) classifier;
			}
		}
		for (EPackage child : pkg.getESubpackages()) {
			EClass found = findEClass(child, name);
			if (found != null) return found;
		}
		return null;
	}

	private static EClass bestEffortClassFromValidationName(EPackage pkg, String validationName) {
		EClass best = null;
		int bestLength = -1;
		for (EClass eClass : allClasses(pkg)) {
			String name = eClass.getName();
			if (name != null && validationName.startsWith(name) && name.length() > bestLength) {
				best = eClass;
				bestLength = name.length();
			}
		}
		return best;
	}

	private static java.util.List<EClass> allClasses(EPackage pkg) {
		java.util.List<EClass> classes = new java.util.ArrayList<>();
		collectClasses(pkg, classes);
		return classes;
	}

	private static void collectClasses(EPackage pkg, java.util.List<EClass> classes) {
		for (EClassifier classifier : pkg.getEClassifiers()) {
			if (classifier instanceof EClass) classes.add((EClass) classifier);
		}
		for (EPackage child : pkg.getESubpackages()) {
			collectClasses(child, classes);
		}
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
