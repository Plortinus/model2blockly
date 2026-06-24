package io.github.plortinus.model2blockly.validationpatch;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Applies edited validation Blockly models back to source metamodel files.
 *
 * The class deliberately depends only on JDK APIs so it can be reused from a
 * standalone launch, an Eclipse handler, or tests without adding another bundle.
 */
public final class ValidationSourcePatcher {

	private static final String VALIDATION_SOURCE = "validation";

	private ValidationSourcePatcher() {
	}

	public static PatchReport dryRun(Path source, Path validationModel) throws IOException {
		return patch(source, validationModel, false, null);
	}

	public static PatchReport apply(Path source, Path validationModel, Path output) throws IOException {
		return patch(source, validationModel, true, output);
	}

	private static PatchReport patch(Path source, Path validationModel, boolean apply, Path output) throws IOException {
		String sourceText = Files.readString(source, StandardCharsets.UTF_8);
		String validationText = Files.readString(validationModel, StandardCharsets.UTF_8);
		ValidationModel model = ValidationModel.parse(validationText);
		PatchReport report = new PatchReport(source, validationModel, output, apply);
		report.domain = model.domain;
		report.rulesRead = model.rules.size();

		String extension = extension(source);
		String patched;
		if ("ecore".equals(extension)) {
			patched = patchEcore(sourceText, model, report, apply);
		} else if ("m2b".equals(extension) || "model2blockly".equals(extension)) {
			patched = patchModel2Blockly(sourceText, model, report, apply);
		} else {
			throw new IOException("Unsupported source extension: " + source);
		}

		if (apply) {
			Path target = output != null ? output : source;
			if (output == null) {
				Path backup = source.resolveSibling(source.getFileName().toString() + ".bak");
				Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
				report.backup = backup;
			}
			Path parent = target.getParent();
			if (parent != null) Files.createDirectories(parent);
			Files.writeString(target, patched, StandardCharsets.UTF_8);
			report.output = target;
		}
		return report;
	}

	private static String patchEcore(String sourceText, ValidationModel model, PatchReport report, boolean mutate)
			throws IOException {
		Document document = parseXml(sourceText);
		for (ValidationRule rule : model.rules) {
			RuleKind kind = rule.kind();
			switch (kind) {
				case MUST_FOLLOW:
					patchEcoreMustFollow(document, rule, report);
					break;
				case REQUIRED:
					patchEcoreRequired(document, rule, report);
					break;
				case CARDINALITY:
					patchEcoreCardinality(document, rule, true, report);
					break;
				case FIELD_CARDINALITY:
					patchEcoreCardinality(document, rule, false, report);
					break;
				case UNIQUE:
					patchEcoreUnique(document, rule, report);
					break;
				case EXPRESSION:
					patchEcoreExpression(document, rule, report);
					break;
				default:
					report.note("Unsupported validation rule " + rule.name + " (" + rule.validationType + ")");
					break;
			}
		}
		return mutate ? serializeXml(document) : sourceText;
	}

	private static void patchEcoreMustFollow(Document document, ValidationRule rule, PatchReport report) {
		String target = rule.targetType();
		String predecessor = rule.predecessorType();
		if (isBlank(target) || isBlank(predecessor)) {
			report.warn("Skip " + rule.name + ": must-follow rule is missing target or predecessor.");
			return;
		}
		Element eClass = findEClass(document, target);
		if (eClass == null) {
			report.warn("Skip " + rule.name + ": EClass " + target + " was not found.");
			return;
		}
		if (setAnnotationDetail(eClass, VALIDATION_SOURCE, "mustFollow", predecessor)) {
			report.change("EClass " + target + ": set validation.mustFollow=" + predecessor);
		} else {
			report.note("EClass " + target + ": validation.mustFollow already equals " + predecessor);
		}
	}

	private static void patchEcoreRequired(Document document, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String field = rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": required rule is missing owner or field.");
			return;
		}
		Element feature = findFeature(document, owner, field);
		if (feature == null) {
			report.warn("Skip " + rule.name + ": feature " + owner + "." + field + " was not found.");
			return;
		}
		if (setAttribute(feature, "lowerBound", "1")) {
			report.change("Feature " + owner + "." + field + ": set lowerBound=1");
		} else {
			report.note("Feature " + owner + "." + field + ": lowerBound already equals 1");
		}
	}

	private static void patchEcoreCardinality(Document document, ValidationRule rule, boolean containment,
			PatchReport report) {
		String owner = rule.ownerType();
		String field = containment ? rule.inputName() : rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": cardinality rule is missing owner or feature.");
			return;
		}
		Element feature = findFeature(document, owner, field);
		if (feature == null) {
			report.warn("Skip " + rule.name + ": feature " + owner + "." + field + " was not found.");
			return;
		}
		if (containment && !"true".equalsIgnoreCase(feature.getAttribute("containment"))) {
			report.note("Feature " + owner + "." + field + " is not marked containment=true; bounds were still updated.");
		}
		String min = ecoreBound(rule.minBound(), "0");
		String max = ecoreUpperBound(rule.maxBound());
		boolean changed = setAttribute(feature, "lowerBound", min);
		changed |= setAttribute(feature, "upperBound", max);
		if (changed) {
			report.change("Feature " + owner + "." + field + ": set lowerBound=" + min + ", upperBound=" + max);
		} else {
			report.note("Feature " + owner + "." + field + ": bounds already equal "
					+ min + ".." + max);
		}
	}

	private static void patchEcoreUnique(Document document, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String field = rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": unique rule is missing owner or field.");
			return;
		}
		Element feature = findFeature(document, owner, field);
		if (feature == null) {
			report.warn("Skip " + rule.name + ": feature " + owner + "." + field + " was not found.");
			return;
		}
		boolean changed;
		if ("validation_type_field_unique".equals(rule.conditionType)) {
			changed = setAttribute(feature, "iD", "true");
			if (!changed) {
				report.note("Feature " + owner + "." + field + ": iD already equals true");
				return;
			}
			report.change("Feature " + owner + "." + field + ": set iD=true for type-wide uniqueness");
		} else {
			changed = setAttribute(feature, "unique", "true");
			if (!changed) {
				report.note("Feature " + owner + "." + field + ": unique already equals true");
				return;
			}
			report.change("Feature " + owner + "." + field + ": set unique=true");
		}
	}

	private static void patchEcoreExpression(Document document, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String expression = rule.runtimeExpression();
		if (isBlank(owner) || isBlank(expression)) {
			report.warn("Skip " + rule.name + ": expression rule is missing owner or expression.");
			return;
		}
		Element eClass = findEClass(document, owner);
		if (eClass == null) {
			report.warn("Skip " + rule.name + ": EClass " + owner + " was not found.");
			return;
		}
		String safeName = safeName(rule.name);
		boolean changed = setAnnotationDetail(eClass, VALIDATION_SOURCE, "expression." + safeName, expression);
		if (!isBlank(rule.message)) {
			changed |= setAnnotationDetail(eClass, VALIDATION_SOURCE, "message." + safeName, rule.message);
		}
		if (changed) {
			report.change("EClass " + owner + ": set validation.expression." + safeName);
		} else {
			report.note("EClass " + owner + ": validation.expression." + safeName + " already matches");
		}
	}

	private static String patchModel2Blockly(String sourceText, ValidationModel model, PatchReport report, boolean mutate) {
		String patched = sourceText;
		List<String> unsupportedComments = new ArrayList<>();
		for (ValidationRule rule : model.rules) {
			switch (rule.kind()) {
				case MUST_FOLLOW:
					patched = patchDslMustFollow(patched, rule, report);
					break;
				case REQUIRED:
					patched = patchDslRequired(patched, rule, report);
					break;
				case CARDINALITY:
					patched = patchDslContainmentBounds(patched, rule, report);
					break;
				case FIELD_CARDINALITY:
					patched = patchDslFeatureBounds(patched, rule, report);
					break;
				case UNIQUE:
					patched = patchDslUnique(patched, rule, report);
					break;
				case EXPRESSION:
					patched = patchDslExpression(patched, rule, report);
					break;
				default:
					report.note("Unsupported DSL validation rule " + rule.name + " (" + rule.validationType + ")");
					break;
			}
		}
		if (!unsupportedComments.isEmpty()) {
			String marker = "// Generated validation patch notes";
			if (!patched.contains(marker)) {
				StringBuilder out = new StringBuilder();
				out.append(trimTrailingNewlines(patched)).append("\n\n");
				out.append(marker).append("\n");
				for (String comment : unsupportedComments) out.append(comment).append("\n");
				patched = out.toString();
				report.change("Model2Blockly: appended validation patch notes for unsupported grammar features");
			}
		}
		return mutate ? patched : sourceText;
	}

	private static String patchDslMustFollow(String text, ValidationRule rule, PatchReport report) {
		String target = rule.targetType();
		String predecessor = rule.predecessorType();
		if (isBlank(target) || isBlank(predecessor)) {
			report.warn("Skip " + rule.name + ": must-follow rule is missing target or predecessor.");
			return text;
		}
		String line = "constraint " + safeName(rule.name) + " on " + target + " : must follow " + predecessor;
		Pattern namedConstraint = Pattern.compile("(?m)^\\s*constraint\\s+" + Pattern.quote(safeName(rule.name))
				+ "\\s+on\\s+\\w+\\s*:\\s*must\\s+follow\\s+\\w+\\s*$");
		Matcher matcher = namedConstraint.matcher(text);
		if (matcher.find()) {
			String original = matcher.group(0);
			if (original.trim().equals(line)) {
				report.note("Constraint " + safeName(rule.name) + ": already present");
				return text;
			}
			String patched = matcher.replaceFirst(Matcher.quoteReplacement(line));
			report.change("Constraint " + safeName(rule.name) + ": updated to " + target + " after " + predecessor);
			return patched;
		}
		if (Pattern.compile("(?m)^\\s*" + Pattern.quote(line) + "\\s*$").matcher(text).find()) {
			report.note("Constraint " + safeName(rule.name) + ": already present");
			return text;
		}
		report.change("Constraint " + safeName(rule.name) + ": appended");
		return trimTrailingNewlines(text) + "\n\n" + line + "\n";
	}

	private static String patchDslRequired(String text, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String field = rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": required rule is missing owner or field.");
			return text;
		}
		ClassRange range = findClassRange(text, owner);
		if (range == null) {
			report.warn("Skip " + rule.name + ": class " + owner + " was not found.");
			return text;
		}
		String body = text.substring(range.bodyStart + 1, range.bodyEnd);
		LinePatch attributePatch = appendRequiredToFeatureLine(body, "attribute", field);
		LinePatch patch = attributePatch.found ? attributePatch : appendRequiredToFeatureLine(body, "reference", field);
		if (!patch.found) {
			report.warn("Skip " + rule.name + ": attribute/reference " + owner + "." + field + " was not found.");
			return text;
		}
		if (!patch.changed) {
			report.note("Feature " + owner + "." + field + ": already required");
			return text;
		}
		report.change("Feature " + owner + "." + field + ": added required");
		return text.substring(0, range.bodyStart + 1) + patch.text + text.substring(range.bodyEnd);
	}

	private static String patchDslContainmentBounds(String text, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String input = rule.inputName();
		if (isBlank(owner) || isBlank(input)) {
			report.warn("Skip " + rule.name + ": containment cardinality rule is missing owner or input.");
			return text;
		}
		ClassRange range = findClassRange(text, owner);
		if (range == null) {
			report.warn("Skip " + rule.name + ": class " + owner + " was not found.");
			return text;
		}
		String body = text.substring(range.bodyStart + 1, range.bodyEnd);
		LinePatch patch = updateContainmentBounds(body, input, dslBound(rule.minBound(), "0"),
				dslUpperBound(rule.maxBound()));
		if (!patch.found) {
			report.warn("Skip " + rule.name + ": containment " + owner + "." + input + " was not found.");
			return text;
		}
		if (!patch.changed) {
			report.note("Containment " + owner + "." + input + ": bounds already match");
			return text;
		}
		report.change("Containment " + owner + "." + input + ": updated bounds to ["
				+ dslBound(rule.minBound(), "0") + ".." + dslUpperBound(rule.maxBound()) + "]");
		return text.substring(0, range.bodyStart + 1) + patch.text + text.substring(range.bodyEnd);
	}

	private static String patchDslFeatureBounds(String text, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String field = rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": field cardinality rule is missing owner or field.");
			return text;
		}
		ClassRange range = findClassRange(text, owner);
		if (range == null) {
			report.warn("Skip " + rule.name + ": class " + owner + " was not found.");
			return text;
		}
		String min = dslBound(rule.minBound(), "0");
		String max = dslUpperBound(rule.maxBound());
		String body = text.substring(range.bodyStart + 1, range.bodyEnd);
		LinePatch attributePatch = updateFeatureBounds(body, "attribute", field, min, max);
		LinePatch patch = attributePatch.found ? attributePatch : updateFeatureBounds(body, "reference", field, min, max);
		if (!patch.found) {
			report.warn("Skip " + rule.name + ": attribute/reference " + owner + "." + field + " was not found.");
			return text;
		}
		if (!patch.changed) {
			report.note("Feature " + owner + "." + field + ": bounds already match");
			return text;
		}
		report.change("Feature " + owner + "." + field + ": updated bounds to [" + min + ".." + max + "]");
		return text.substring(0, range.bodyStart + 1) + patch.text + text.substring(range.bodyEnd);
	}

	private static String patchDslUnique(String text, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String field = rule.fieldName();
		if (isBlank(owner) || isBlank(field)) {
			report.warn("Skip " + rule.name + ": unique rule is missing owner or field.");
			return text;
		}
		ClassRange range = findClassRange(text, owner);
		if (range == null) {
			report.warn("Skip " + rule.name + ": class " + owner + " was not found.");
			return text;
		}
		String body = text.substring(range.bodyStart + 1, range.bodyEnd);
		boolean typeWide = "validation_type_field_unique".equals(rule.conditionType);
		LinePatch attributePatch = updateFeatureUniqueness(body, "attribute", field, typeWide);
		LinePatch patch = attributePatch.found ? attributePatch : updateFeatureUniqueness(body, "reference", field, false);
		if (!patch.found) {
			report.warn("Skip " + rule.name + ": attribute/reference " + owner + "." + field + " was not found.");
			return text;
		}
		if (!patch.changed) {
			report.note("Feature " + owner + "." + field + ": uniqueness already matches");
			return text;
		}
		report.change("Feature " + owner + "." + field + ": added " + (typeWide ? "modelId unique" : "unique"));
		return text.substring(0, range.bodyStart + 1) + patch.text + text.substring(range.bodyEnd);
	}

	private static String patchDslExpression(String text, ValidationRule rule, PatchReport report) {
		String owner = rule.ownerType();
		String expression = rule.runtimeExpression();
		if (isBlank(owner) || isBlank(expression)) {
			report.warn("Skip " + rule.name + ": expression rule is missing owner or expression.");
			return text;
		}
		String name = dslValidationName(rule);
		String line = dslValidationLine(name, owner, expression, rule.message);
		Pattern namedValidation = Pattern.compile("(?m)^\\s*validation\\s+" + Pattern.quote(name)
				+ "\\s+on\\s+\\w+\\s*:\\s*(?:expression|condition|js|ocl)\\s+(?:\"(?:\\\\.|[^\"])*\"|'(?:\\\\.|[^'])*')"
				+ "(?:\\s+errorMessage\\s+(?:\"(?:\\\\.|[^\"])*\"|'(?:\\\\.|[^'])*'))?\\s*$");
		Matcher matcher = namedValidation.matcher(text);
		if (matcher.find()) {
			String original = matcher.group(0);
			if (original.trim().equals(line)) {
				report.note("Validation " + name + ": already present");
				return text;
			}
			report.change("Validation " + name + ": updated expression");
			return matcher.replaceFirst(Matcher.quoteReplacement(line));
		}
		report.change("Validation " + name + ": appended expression rule");
		return trimTrailingNewlines(text) + "\n" + line + "\n";
	}

	private static LinePatch appendRequiredToFeatureLine(String body, String kind, String field) {
		String regex;
		if ("attribute".equals(kind)) {
			regex = "(?m)^(\\s*attribute\\s+" + Pattern.quote(field) + "\\s*:[^\\r\\n]*)(\\r?\\n|$)";
		} else {
			regex = "(?m)^(\\s*reference\\s+\\w+\\s+" + Pattern.quote(field) + "\\b[^\\r\\n]*)(\\r?\\n|$)";
		}
		Matcher matcher = Pattern.compile(regex).matcher(body);
		if (!matcher.find()) return LinePatch.notFound(body);
		String line = matcher.group(1);
		if (Pattern.compile("\\brequired\\b").matcher(line).find()) return LinePatch.unchanged(body);
		String replacement = line + " required" + matcher.group(2);
		return LinePatch.changed(matcher.replaceFirst(Matcher.quoteReplacement(replacement)));
	}

	private static LinePatch updateContainmentBounds(String body, String input, String min, String max) {
		Pattern pattern = Pattern.compile("(?m)^(\\s*contains\\s+\\w+\\s+" + Pattern.quote(input)
				+ "\\b)([^\\r\\n]*)(\\r?\\n|$)");
		Matcher matcher = pattern.matcher(body);
		if (!matcher.find()) return LinePatch.notFound(body);
		String prefix = matcher.group(1);
		String tail = matcher.group(2);
		String newline = matcher.group(3);
		String bounds = " [" + min + ".." + max + "]";
		String updatedTail;
		if (Pattern.compile("\\[\\s*\\d+\\s*\\.\\.\\s*(?:\\d+|\\*|unbounded|-1)\\s*\\]").matcher(tail).find()) {
			updatedTail = tail.replaceFirst("\\s*\\[\\s*\\d+\\s*\\.\\.\\s*(?:\\d+|\\*|unbounded|-1)\\s*\\]\\s*$",
					Matcher.quoteReplacement(bounds));
		} else {
			updatedTail = rstrip(tail) + bounds;
		}
		String replacement = prefix + updatedTail + newline;
		String original = matcher.group(0);
		if (original.equals(replacement)) return LinePatch.unchanged(body);
		return LinePatch.changed(matcher.replaceFirst(Matcher.quoteReplacement(replacement)));
	}

	private static LinePatch updateFeatureBounds(String body, String kind, String field, String min, String max) {
		String regex = "attribute".equals(kind)
			? "(?m)^(\\s*attribute\\s+" + Pattern.quote(field)
				+ "\\s*:\\s*(?:\\w+|enum\\s*\\{[^}\\r\\n]*\\}))(.*?)(\\r?\\n|$)"
			: "(?m)^(\\s*reference\\s+\\w+\\s+" + Pattern.quote(field) + "\\b)(.*?)(\\r?\\n|$)";
		Matcher matcher = Pattern.compile(regex).matcher(body);
		if (!matcher.find()) return LinePatch.notFound(body);
		String prefix = matcher.group(1);
		String tail = matcher.group(2);
		String newline = matcher.group(3);
		String bounds = " [" + min + ".." + max + "]";
		String updatedTail;
		if (Pattern.compile("\\[\\s*\\d+\\s*\\.\\.\\s*(?:\\d+|\\*|unbounded|-1)\\s*\\]").matcher(tail).find()) {
			updatedTail = tail.replaceFirst("\\s*\\[\\s*\\d+\\s*\\.\\.\\s*(?:\\d+|\\*|unbounded|-1)\\s*\\]",
					Matcher.quoteReplacement(bounds));
		} else {
			updatedTail = bounds + tail;
		}
		String replacement = prefix + updatedTail + newline;
		String original = matcher.group(0);
		if (original.equals(replacement)) return LinePatch.unchanged(body);
		return LinePatch.changed(matcher.replaceFirst(Matcher.quoteReplacement(replacement)));
	}

	private static LinePatch updateFeatureUniqueness(String body, String kind, String field, boolean typeWide) {
		String regex = "attribute".equals(kind)
			? "(?m)^(\\s*attribute\\s+" + Pattern.quote(field) + "\\s*:[^\\r\\n]*)(\\r?\\n|$)"
			: "(?m)^(\\s*reference\\s+\\w+\\s+" + Pattern.quote(field) + "\\b[^\\r\\n]*)(\\r?\\n|$)";
		Matcher matcher = Pattern.compile(regex).matcher(body);
		if (!matcher.find()) return LinePatch.notFound(body);
		String line = matcher.group(1);
		String updated = line.replaceAll("\\s+nonUnique\\b", "");
		boolean hasUnique = Pattern.compile("\\bunique\\b").matcher(updated).find();
		boolean hasModelId = Pattern.compile("\\bmodelId\\b").matcher(updated).find();
		if (typeWide && !hasModelId) updated += " modelId";
		if (!hasUnique) updated += " unique";
		if (line.equals(updated)) return LinePatch.unchanged(body);
		return LinePatch.changed(matcher.replaceFirst(Matcher.quoteReplacement(updated + matcher.group(2))));
	}

	private static Document parseXml(String sourceText) throws IOException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			disableXmlExternalEntities(factory);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(sourceText)));
		} catch (Exception e) {
			throw new IOException("Could not parse Ecore XML.", e);
		}
	}

	private static void disableXmlExternalEntities(DocumentBuilderFactory factory) throws ParserConfigurationException {
		try {
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		} catch (ParserConfigurationException e) {
			throw e;
		}
		factory.setExpandEntityReferences(false);
	}

	private static String serializeXml(Document document) throws IOException {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			try {
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			} catch (IllegalArgumentException ignored) {
				// Transformer implementations may not expose this vendor property.
			}
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			return writer.toString();
		} catch (TransformerException e) {
			throw new IOException("Could not serialize patched Ecore XML.", e);
		}
	}

	private static Element findEClass(Document document, String className) {
		NodeList classifiers = document.getElementsByTagName("eClassifiers");
		for (int i = 0; i < classifiers.getLength(); i++) {
			Node node = classifiers.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				String type = element.getAttribute("xsi:type");
				if (className.equals(element.getAttribute("name")) && (type.isEmpty() || type.endsWith("EClass"))) {
					return element;
				}
			}
		}
		return null;
	}

	private static Element findFeature(Document document, String className, String featureName) {
		Element eClass = findEClass(document, className);
		if (eClass == null) return null;
		NodeList children = eClass.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				if ("eStructuralFeatures".equals(element.getNodeName()) && featureName.equals(element.getAttribute("name"))) {
					return element;
				}
			}
		}
		return null;
	}

	private static boolean setAnnotationDetail(Element owner, String source, String key, String value) {
		boolean changed = false;
		Element annotation = findDirectChild(owner, "eAnnotations", "source", source);
		if (annotation == null) {
			annotation = owner.getOwnerDocument().createElement("eAnnotations");
			annotation.setAttribute("source", source);
			Node insertBefore = firstDirectChild(owner, "eStructuralFeatures");
			if (insertBefore != null) {
				owner.insertBefore(annotation, insertBefore);
			} else {
				owner.appendChild(annotation);
			}
			changed = true;
		}
		Element detail = findDirectChild(annotation, "details", "key", key);
		if (detail == null) {
			detail = owner.getOwnerDocument().createElement("details");
			detail.setAttribute("key", key);
			annotation.appendChild(detail);
			changed = true;
		}
		if (!value.equals(detail.getAttribute("value"))) {
			detail.setAttribute("value", value);
			changed = true;
		}
		return changed;
	}

	private static boolean setAttribute(Element element, String name, String value) {
		if (value.equals(element.getAttribute(name))) return false;
		element.setAttribute(name, value);
		return true;
	}

	private static Element findDirectChild(Element parent, String nodeName, String attributeName, String attributeValue) {
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				if (nodeName.equals(element.getNodeName()) && attributeValue.equals(element.getAttribute(attributeName))) {
					return element;
				}
			}
		}
		return null;
	}

	private static Node firstDirectChild(Element parent, String nodeName) {
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node instanceof Element && nodeName.equals(((Element) node).getNodeName())) return node;
		}
		return null;
	}

	private static ClassRange findClassRange(String text, String className) {
		DslScanner scanner = new DslScanner(text);
		while (scanner.hasNext()) {
			Token token = scanner.next();
			if (!"class".equals(token.text)) continue;
			Token name = scanner.nextSignificant();
			if (name == null || !className.equals(name.text)) continue;
			Integer bodyStart = scanner.nextCharOutsideIgnored('{');
			if (bodyStart == null) return null;
			int bodyEnd = scanner.matchingBrace(bodyStart);
			if (bodyEnd < 0) return null;
			return new ClassRange(token.start, bodyStart, bodyEnd);
		}
		return null;
	}

	private static String extension(Path path) {
		String name = path.getFileName().toString();
		int dot = name.lastIndexOf('.');
		return dot < 0 ? "" : name.substring(dot + 1).toLowerCase(Locale.ROOT);
	}

	private static String ecoreBound(String value, String fallback) {
		String text = firstText(value, fallback);
		if ("unbounded".equalsIgnoreCase(text) || "*".equals(text)) return "-1";
		return text;
	}

	private static String ecoreUpperBound(String value) {
		return ecoreBound(value, "-1");
	}

	private static String dslBound(String value, String fallback) {
		String text = firstText(value, fallback);
		if ("unbounded".equalsIgnoreCase(text) || "*".equals(text)) return "-1";
		return text;
	}

	private static String dslUpperBound(String value) {
		String text = firstText(value, "0");
		if ("unbounded".equalsIgnoreCase(text) || "*".equals(text) || "-1".equals(text)) return "0";
		return text;
	}

	private static String dslValidationName(ValidationRule rule) {
		String owner = rule.ownerType();
		String name = firstText(rule.name, "validation");
		if (!isBlank(owner) && name.startsWith(owner + "_") && name.length() > owner.length() + 1) {
			name = name.substring(owner.length() + 1);
		}
		return safeName(name);
	}

	private static String dslValidationLine(String name, String owner, String expression, String message) {
		String line = "validation " + safeName(name) + " on " + owner
				+ " : expression " + dslQuote(expression);
		if (!isBlank(message) && !"EXPRESSION".equalsIgnoreCase(message)) {
			line += " errorMessage " + dslQuote(message);
		}
		return line;
	}

	private static String dslQuote(String value) {
		String escaped = firstText(value)
			.replace("\\", "\\\\")
			.replace("\"", "\\\"")
			.replace("\r", "\\r")
			.replace("\n", "\\n")
			.replace("\t", "\\t");
		return "\"" + escaped + "\"";
	}

	private static String safeName(String value) {
		String text = firstText(value, "validation").replaceAll("[^A-Za-z0-9_]", "_");
		return text.isBlank() ? "validation" : text;
	}

	private static String firstText(String... values) {
		for (String value : values) {
			if (!isBlank(value)) return value.trim();
		}
		return "";
	}

	private static String trimTrailingNewlines(String value) {
		return value.replaceFirst("[\\r\\n]+$", "");
	}

	private static String rstrip(String value) {
		return value.replaceFirst("\\s+$", "");
	}

	private static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

	private enum RuleKind {
		MUST_FOLLOW, REQUIRED, CARDINALITY, FIELD_CARDINALITY, UNIQUE, EXPRESSION, UNSUPPORTED
	}

	private static final class ValidationModel {
		private final String domain;
		private final List<ValidationRule> rules;

		private ValidationModel(String domain, List<ValidationRule> rules) {
			this.domain = domain;
			this.rules = rules;
		}

		@SuppressWarnings("unchecked")
		private static ValidationModel parse(String json) throws IOException {
			Object value = new JsonParser(json).parse();
			if (!(value instanceof Map)) throw new IOException("Validation model root is not a JSON object.");
			Map<String, Object> root = (Map<String, Object>) value;
			String domain = text(root.get("domain"));
			Object rulesValue = root.get("rules");
			if (!(rulesValue instanceof List)) throw new IOException("Validation model does not contain a rules array.");
			List<ValidationRule> rules = new ArrayList<>();
			for (Object ruleValue : (List<Object>) rulesValue) {
				if (ruleValue instanceof Map) rules.add(ValidationRule.fromJson((Map<String, Object>) ruleValue));
			}
			return new ValidationModel(domain, rules);
		}
	}

	private static final class ValidationRule {
		private final String name;
		private final String validationType;
		private final String targetType;
		private final String message;
		private final String conditionType;
		private final String runtimeExpression;
		private final Map<String, String> params;

		private ValidationRule(String name, String validationType, String targetType, String message,
				String conditionType, String runtimeExpression, Map<String, String> params) {
			this.name = firstText(name, "validation");
			this.validationType = firstText(validationType);
			this.targetType = firstText(targetType);
			this.message = firstText(message);
			this.conditionType = firstText(conditionType);
			this.runtimeExpression = firstText(runtimeExpression);
			this.params = params;
		}

		@SuppressWarnings("unchecked")
		private static ValidationRule fromJson(Map<String, Object> rule) {
			Map<String, String> params = new LinkedHashMap<>();
			Map<String, Object> generator = map(rule.get("generator"));
			putStringMap(params, map(generator.get("parameters")));

			Map<String, Object> blockTree = map(rule.get("blockTree"));
			Map<String, Object> blockTreeFields = map(blockTree.get("fields"));
			putStringMapIfAbsent(params, blockTreeFields);
			Map<String, Object> condition = ruleCondition(blockTree);
			putStringMap(params, map(condition.get("fields")));

			String name = firstText(text(rule.get("name")), text(blockTreeFields.get("NAME")));
			String conditionType = text(condition.get("type"));
			String validationType = firstText(text(rule.get("validationType")),
					text(blockTreeFields.get("VALIDATION_TYPE")), validationTypeFromCondition(conditionType));
			String targetType = firstText(text(rule.get("targetType")), params.get("TARGET"),
					text(blockTreeFields.get("TARGET")), params.get("TYPE"));
			String message = firstText(text(rule.get("message")), text(blockTreeFields.get("MESSAGE")));
			String runtimeExpression = firstText(text(rule.get("runtimeExpression")),
					text(generator.get("runtimeExpression")), runtimeExpressionFromCondition(condition));
			return new ValidationRule(name, validationType, targetType, message, conditionType, runtimeExpression, params);
		}

		private RuleKind kind() {
			String type = validationType.toUpperCase(Locale.ROOT);
			if ("MUST_FOLLOW".equals(type) || "validation_previous_block_is".equals(conditionType)) return RuleKind.MUST_FOLLOW;
			if ("REQUIRED".equals(type) || "validation_required_field".equals(conditionType)) return RuleKind.REQUIRED;
			if ("CARDINALITY".equals(type) || "validation_statement_cardinality".equals(conditionType)) return RuleKind.CARDINALITY;
			if ("FIELD_CARDINALITY".equals(type) || "validation_field_cardinality".equals(conditionType)) return RuleKind.FIELD_CARDINALITY;
			if ("UNIQUE".equals(type) || "validation_field_unique".equals(conditionType)
					|| "validation_type_field_unique".equals(conditionType)) return RuleKind.UNIQUE;
			if ("EXPRESSION".equals(type) || "validation_expression_rule".equals(conditionType)
					|| !isBlank(runtimeExpression)) return RuleKind.EXPRESSION;
			return RuleKind.UNSUPPORTED;
		}

		private String targetType() {
			return firstText(targetType, params.get("TARGET"));
		}

		private String ownerType() {
			return firstText(targetType, params.get("TYPE"), params.get("TARGET"));
		}

		private String predecessorType() {
			return firstText(params.get("TYPE"), params.get("PREDECESSOR"));
		}

		private String fieldName() {
			return firstText(params.get("FIELD"));
		}

		private String inputName() {
			return firstText(params.get("INPUT"));
		}

		private String minBound() {
			return firstText(params.get("MIN"), "0");
		}

		private String maxBound() {
			return firstText(params.get("MAX"), "unbounded");
		}

		private String runtimeExpression() {
			return runtimeExpression;
		}

		@SuppressWarnings("unchecked")
		private static Map<String, Object> ruleCondition(Map<String, Object> blockTree) {
			if (blockTree == null) return Collections.emptyMap();
			if ("validation_rule".equals(text(blockTree.get("type")))) {
				Map<String, Object> inputs = map(blockTree.get("inputs"));
				Object condition = inputs.get("CONDITION");
				if (condition instanceof Map) return (Map<String, Object>) condition;
				return Collections.emptyMap();
			}
			return blockTree;
		}

		private static String validationTypeFromCondition(String conditionType) {
			if ("validation_previous_block_is".equals(conditionType)) return "MUST_FOLLOW";
			if ("validation_required_field".equals(conditionType)) return "REQUIRED";
			if ("validation_statement_cardinality".equals(conditionType)) return "CARDINALITY";
			if ("validation_field_cardinality".equals(conditionType)) return "FIELD_CARDINALITY";
			if ("validation_field_unique".equals(conditionType) || "validation_type_field_unique".equals(conditionType)) return "UNIQUE";
			if ("validation_expression_rule".equals(conditionType)) return "EXPRESSION";
			return "";
		}

		private static String runtimeExpressionFromCondition(Map<String, Object> condition) {
			String type = text(condition.get("type"));
			Map<String, Object> fields = map(condition.get("fields"));
			if ("validation_required_field".equals(type)) return "has(\"" + text(fields.get("FIELD")) + "\")";
			if ("validation_statement_cardinality".equals(type)) {
				return rangeExpression("inputCount(\"" + text(fields.get("INPUT")) + "\")",
						text(fields.get("MIN")), text(fields.get("MAX")));
			}
			if ("validation_field_cardinality".equals(type)) {
				return rangeExpression("fieldCount(\"" + text(fields.get("FIELD")) + "\")",
						text(fields.get("MIN")), text(fields.get("MAX")));
			}
			if ("validation_field_unique".equals(type)) return "fieldUnique(\"" + text(fields.get("FIELD")) + "\")";
			if ("validation_type_field_unique".equals(type)) {
				return "typeFieldUnique(\"" + text(fields.get("TYPE")) + "\", \"" + text(fields.get("FIELD")) + "\")";
			}
			if ("validation_previous_block_is".equals(type)) return "previousBlockIs(\"" + text(fields.get("TYPE")) + "\")";
			if ("validation_expression_rule".equals(type)) return text(fields.get("EXPRESSION"));
			return "";
		}

		private static String rangeExpression(String accessor, String min, String max) {
			String lower = positiveBound(min) ? accessor + " >= " + min : "";
			String upper = finiteBound(max) ? accessor + " <= " + max : "";
			if (!lower.isEmpty() && !upper.isEmpty()) return lower + " && " + upper;
			if (!lower.isEmpty()) return lower;
			if (!upper.isEmpty()) return upper;
			return "true";
		}

		private static boolean positiveBound(String value) {
			try {
				return Integer.parseInt(firstText(value, "0")) > 0;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		private static boolean finiteBound(String value) {
			if (isBlank(value) || "unbounded".equalsIgnoreCase(value) || "*".equals(value)) return false;
			try {
				return Integer.parseInt(value) >= 0;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	private static final class ClassRange {
		private final int start;
		private final int bodyStart;
		private final int bodyEnd;

		private ClassRange(int start, int bodyStart, int bodyEnd) {
			this.start = start;
			this.bodyStart = bodyStart;
			this.bodyEnd = bodyEnd;
		}
	}

	private static final class LinePatch {
		private final String text;
		private final boolean found;
		private final boolean changed;

		private LinePatch(String text, boolean found, boolean changed) {
			this.text = text;
			this.found = found;
			this.changed = changed;
		}

		private static LinePatch notFound(String text) {
			return new LinePatch(text, false, false);
		}

		private static LinePatch unchanged(String text) {
			return new LinePatch(text, true, false);
		}

		private static LinePatch changed(String text) {
			return new LinePatch(text, true, true);
		}
	}

	private static final class Token {
		private final String text;
		private final int start;

		private Token(String text, int start) {
			this.text = text;
			this.start = start;
		}
	}

	private static final class DslScanner {
		private final String text;
		private int index;

		private DslScanner(String text) {
			this.text = text;
		}

		private boolean hasNext() {
			skipIgnored();
			return index < text.length();
		}

		private Token next() {
			skipIgnored();
			if (index >= text.length()) return null;
			char ch = text.charAt(index);
			if (isIdentifierStart(ch)) {
				int start = index++;
				while (index < text.length() && isIdentifierPart(text.charAt(index))) index++;
				return new Token(text.substring(start, index), start);
			}
			return new Token(Character.toString(text.charAt(index)), index++);
		}

		private Token nextSignificant() {
			Token token = next();
			while (token != null && !isIdentifierStart(token.text.charAt(0))) token = next();
			return token;
		}

		private Integer nextCharOutsideIgnored(char target) {
			while (index < text.length()) {
				skipIgnored();
				if (index >= text.length()) return null;
				char ch = text.charAt(index);
				if (ch == target) return index;
				index++;
			}
			return null;
		}

		private int matchingBrace(int openIndex) {
			int depth = 0;
			for (int i = openIndex; i < text.length(); i++) {
				char ch = text.charAt(i);
				if (ch == '"') {
					i = skipString(i);
					continue;
				}
				if (ch == '/' && i + 1 < text.length() && text.charAt(i + 1) == '/') {
					i = skipLineComment(i);
					continue;
				}
				if (ch == '/' && i + 1 < text.length() && text.charAt(i + 1) == '*') {
					i = skipBlockComment(i);
					continue;
				}
				if (ch == '{') depth++;
				if (ch == '}') {
					depth--;
					if (depth == 0) return i;
				}
			}
			return -1;
		}

		private void skipIgnored() {
			boolean again = true;
			while (again && index < text.length()) {
				again = false;
				while (index < text.length() && Character.isWhitespace(text.charAt(index))) index++;
				if (index + 1 < text.length() && text.charAt(index) == '/' && text.charAt(index + 1) == '/') {
					index = skipLineComment(index) + 1;
					again = true;
				} else if (index + 1 < text.length() && text.charAt(index) == '/' && text.charAt(index + 1) == '*') {
					index = skipBlockComment(index) + 1;
					again = true;
				} else if (index < text.length() && text.charAt(index) == '"') {
					index = skipString(index) + 1;
					again = true;
				}
			}
		}

		private int skipString(int start) {
			for (int i = start + 1; i < text.length(); i++) {
				char ch = text.charAt(i);
				if (ch == '\\') {
					i++;
				} else if (ch == '"') {
					return i;
				}
			}
			return text.length() - 1;
		}

		private int skipLineComment(int start) {
			int newline = text.indexOf('\n', start + 2);
			return newline < 0 ? text.length() - 1 : newline;
		}

		private int skipBlockComment(int start) {
			int end = text.indexOf("*/", start + 2);
			return end < 0 ? text.length() - 1 : end + 1;
		}

		private static boolean isIdentifierStart(char ch) {
			return Character.isLetter(ch) || ch == '_';
		}

		private static boolean isIdentifierPart(char ch) {
			return Character.isLetterOrDigit(ch) || ch == '_';
		}
	}

	private static void putStringMap(Map<String, String> target, Map<String, Object> source) {
		for (Map.Entry<String, Object> entry : source.entrySet()) target.put(entry.getKey(), text(entry.getValue()));
	}

	private static void putStringMapIfAbsent(Map<String, String> target, Map<String, Object> source) {
		for (Map.Entry<String, Object> entry : source.entrySet()) target.putIfAbsent(entry.getKey(), text(entry.getValue()));
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> map(Object value) {
		return value instanceof Map ? (Map<String, Object>) value : Collections.emptyMap();
	}

	private static String text(Object value) {
		return value == null ? "" : String.valueOf(value);
	}

	private static final class JsonParser {
		private final String text;
		private int index;

		private JsonParser(String text) {
			this.text = text;
		}

		private Object parse() throws IOException {
			Object value = parseValue();
			skipWhitespace();
			if (index != text.length()) throw error("Unexpected trailing content");
			return value;
		}

		private Object parseValue() throws IOException {
			skipWhitespace();
			if (index >= text.length()) throw error("Unexpected end of JSON");
			char ch = text.charAt(index);
			if (ch == '{') return parseObject();
			if (ch == '[') return parseArray();
			if (ch == '"') return parseString();
			if (ch == 't') return parseLiteral("true", Boolean.TRUE);
			if (ch == 'f') return parseLiteral("false", Boolean.FALSE);
			if (ch == 'n') return parseLiteral("null", null);
			if (ch == '-' || Character.isDigit(ch)) return parseNumber();
			throw error("Unexpected JSON value");
		}

		private Map<String, Object> parseObject() throws IOException {
			expect('{');
			Map<String, Object> values = new LinkedHashMap<>();
			skipWhitespace();
			if (peek('}')) {
				index++;
				return values;
			}
			while (true) {
				skipWhitespace();
				String key = parseString();
				skipWhitespace();
				expect(':');
				values.put(key, parseValue());
				skipWhitespace();
				if (peek('}')) {
					index++;
					return values;
				}
				expect(',');
			}
		}

		private List<Object> parseArray() throws IOException {
			expect('[');
			List<Object> values = new ArrayList<>();
			skipWhitespace();
			if (peek(']')) {
				index++;
				return values;
			}
			while (true) {
				values.add(parseValue());
				skipWhitespace();
				if (peek(']')) {
					index++;
					return values;
				}
				expect(',');
			}
		}

		private String parseString() throws IOException {
			expect('"');
			StringBuilder out = new StringBuilder();
			while (index < text.length()) {
				char ch = text.charAt(index++);
				if (ch == '"') return out.toString();
				if (ch == '\\') {
					if (index >= text.length()) throw error("Unterminated escape sequence");
					char escape = text.charAt(index++);
					switch (escape) {
						case '"': out.append('"'); break;
						case '\\': out.append('\\'); break;
						case '/': out.append('/'); break;
						case 'b': out.append('\b'); break;
						case 'f': out.append('\f'); break;
						case 'n': out.append('\n'); break;
						case 'r': out.append('\r'); break;
						case 't': out.append('\t'); break;
						case 'u':
							if (index + 4 > text.length()) throw error("Invalid unicode escape");
							String hex = text.substring(index, index + 4);
							out.append((char) Integer.parseInt(hex, 16));
							index += 4;
							break;
						default:
							throw error("Invalid escape sequence");
					}
				} else {
					out.append(ch);
				}
			}
			throw error("Unterminated JSON string");
		}

		private Object parseNumber() {
			int start = index;
			if (peek('-')) index++;
			while (index < text.length() && Character.isDigit(text.charAt(index))) index++;
			if (peek('.')) {
				index++;
				while (index < text.length() && Character.isDigit(text.charAt(index))) index++;
			}
			if (peek('e') || peek('E')) {
				index++;
				if (peek('+') || peek('-')) index++;
				while (index < text.length() && Character.isDigit(text.charAt(index))) index++;
			}
			return text.substring(start, index);
		}

		private Object parseLiteral(String literal, Object value) throws IOException {
			if (!text.startsWith(literal, index)) throw error("Invalid literal");
			index += literal.length();
			return value;
		}

		private void expect(char expected) throws IOException {
			if (index >= text.length() || text.charAt(index) != expected) throw error("Expected '" + expected + "'");
			index++;
		}

		private boolean peek(char expected) {
			return index < text.length() && text.charAt(index) == expected;
		}

		private void skipWhitespace() {
			while (index < text.length() && Character.isWhitespace(text.charAt(index))) index++;
		}

		private IOException error(String message) {
			return new IOException(message + " at JSON offset " + index);
		}
	}
}
