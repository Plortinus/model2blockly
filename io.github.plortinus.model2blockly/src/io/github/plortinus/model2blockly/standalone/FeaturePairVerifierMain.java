package io.github.plortinus.model2blockly.standalone;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Compares two serialized EditorSpec models after canonicalizing the parts
 * whose order or source-specific namespace spelling is not semantic.
 */
public final class FeaturePairVerifierMain {

	private static final Set<String> IGNORED_ATTRIBUTES = Set.of("nsURI", "nsPrefix");
	private static final Set<String> UNORDERED_CONTAINMENTS = Set.of(
		"EditorSpec.validations",
		"EditorSpec.workspaceOptions",
		"WorkspaceOption.children");

	private FeaturePairVerifierMain() {
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: FeaturePairVerifierMain <left-xmi> <right-xmi>");
			System.exit(2);
		}

		EditorSpec left = load(args[0]);
		EditorSpec right = load(args[1]);
		List<String> leftLines = canonicalLines(left);
		List<String> rightLines = canonicalLines(right);

		if (!leftLines.equals(rightLines)) {
			int difference = firstDifference(leftLines, rightLines);
			System.err.println("Canonical EditorSpec mismatch at line " + (difference + 1) + ":");
			System.err.println("  left : " + lineAt(leftLines, difference));
			System.err.println("  right: " + lineAt(rightLines, difference));
			System.exit(1);
		}

		System.out.println("Canonical EditorSpec match (" + leftLines.size() + " entries).");
	}

	private static EditorSpec load(String path) throws Exception {
		String xmi = Files.readString(Path.of(path));
		return BlocklySpecXmiSerializer.fromXmiToEditorSpec(xmi);
	}

	private static List<String> canonicalLines(EObject root) {
		List<String> lines = new ArrayList<>();
		appendObject(lines, root.eClass().getName(), root);
		return lines;
	}

	private static void appendObject(List<String> lines, String path, EObject object) {
		lines.add(path + " <" + object.eClass().getName() + ">");
		for (EStructuralFeature feature : object.eClass().getEAllStructuralFeatures()) {
			if (!object.eIsSet(feature)) continue;
			if (feature instanceof EAttribute) {
				if (IGNORED_ATTRIBUTES.contains(feature.getName())) continue;
				appendAttribute(lines, path, object, feature);
			} else if (feature instanceof EReference) {
				appendReference(lines, path, object, (EReference) feature);
			}
		}
	}

	private static void appendAttribute(List<String> lines, String path,
			EObject object, EStructuralFeature feature) {
		Object value = object.eGet(feature);
		if (feature.isMany() && value instanceof List<?>) {
			List<?> values = (List<?>) value;
			for (int i = 0; i < values.size(); i++) {
				lines.add(path + "." + feature.getName() + "[" + i + "]=" + scalar(values.get(i)));
			}
		} else {
			lines.add(path + "." + feature.getName() + "=" + scalar(value));
		}
	}

	private static void appendReference(List<String> lines, String path,
			EObject object, EReference reference) {
		Object value = object.eGet(reference);
		if (reference.isMany() && value instanceof List<?>) {
			List<EObject> children = new ArrayList<>();
			for (Object child : (List<?>) value) {
				if (child instanceof EObject) children.add((EObject) child);
			}
			String featureId = object.eClass().getName() + "." + reference.getName();
			if (UNORDERED_CONTAINMENTS.contains(featureId)) {
				children.sort(Comparator.comparing(FeaturePairVerifierMain::stableObjectKey));
			}
			for (int i = 0; i < children.size(); i++) {
				EObject child = children.get(i);
				appendObject(lines, path + "." + reference.getName()
					+ "[" + stablePathPart(child, i) + "]", child);
			}
		} else if (value instanceof EObject) {
			appendObject(lines, path + "." + reference.getName(), (EObject) value);
		}
	}

	private static String stableObjectKey(EObject object) {
		for (String candidate : new String[] {"name", "key", "typeName"}) {
			EStructuralFeature feature = object.eClass().getEStructuralFeature(candidate);
			if (feature != null && object.eIsSet(feature)) {
				return candidate + "=" + scalar(object.eGet(feature));
			}
		}
		List<String> lines = new ArrayList<>();
		appendObject(lines, object.eClass().getName(), object);
		return String.join("\n", lines);
	}

	private static String stablePathPart(EObject object, int index) {
		String key = stableObjectKey(object);
		return key.contains("\n") ? Integer.toString(index) : key;
	}

	private static String scalar(Object value) {
		if (value == null) return "<null>";
		return String.valueOf(value)
			.replace("\\", "\\\\")
			.replace("\r", "\\r")
			.replace("\n", "\\n");
	}

	private static int firstDifference(List<String> left, List<String> right) {
		int common = Math.min(left.size(), right.size());
		for (int i = 0; i < common; i++) {
			if (!left.get(i).equals(right.get(i))) return i;
		}
		return common;
	}

	private static String lineAt(List<String> lines, int index) {
		return index < lines.size() ? lines.get(index) : "<end of model>";
	}
}
