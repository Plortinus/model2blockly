/*
 * Custom validation rules for the Model2Blockly language (domain metamodel).
 */
package io.github.plortinus.model2blockly.validation;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.model2Blockly.Attribute;
import io.github.plortinus.model2blockly.model2Blockly.Cardinality;
import io.github.plortinus.model2blockly.model2Blockly.CategoryDef;
import io.github.plortinus.model2blockly.model2Blockly.ClassDef;
import io.github.plortinus.model2blockly.model2Blockly.Model2BlocklyPackage;
import io.github.plortinus.model2blockly.model2Blockly.ConstraintDef;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.model2Blockly.Containment;
import io.github.plortinus.model2blockly.model2Blockly.EnumType;
import io.github.plortinus.model2blockly.model2Blockly.Feature;
import io.github.plortinus.model2blockly.model2Blockly.Reference;
import io.github.plortinus.model2blockly.model2Blockly.SimpleType;
import io.github.plortinus.model2blockly.model2Blockly.SimpleTypeName;
import io.github.plortinus.model2blockly.model2Blockly.ValidationDef;
import io.github.plortinus.model2blockly.model2Blockly.ValueInput;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator.Issue;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator.Result;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.Diagnostic;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper;
import io.github.plortinus.model2blockly.blocklyspec.ValidationExpressionSyntax;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Custom validation rules for the domain metamodel DSL.
 */
public class Model2BlocklyValidator extends AbstractModel2BlocklyValidator {

	public static final String DUPLICATE_CLASS_NAME = "duplicateClassName";
	public static final String INVALID_CARDINALITY = "invalidCardinality";
	public static final String ABSTRACT_NOT_INSTANTIABLE = "abstractNotInstantiable";
	public static final String OUTPUT_ABSTRACT = "outputAbstract";
	public static final String REQUIRED_BOOLEAN = "requiredBoolean";
	public static final String MUST_FOLLOW_OUTPUT = "mustFollowOutput";
	public static final String OPPOSITE_NOT_FOUND = "oppositeNotFound";
	public static final String UI_WIDGET_INCOMPATIBLE = "uiWidgetIncompatible";
	public static final String UI_WIDGET_INCOMPLETE = "uiWidgetIncomplete";
	public static final String REQUIRED_NOT_EDITABLE = "requiredNotEditable";
	public static final String REFERENCE_LABEL_FIELD_MISSING = "referenceLabelFieldMissing";
	public static final String CONFLICTING_CARDINALITY_FLAGS = "conflictingCardinalityFlags";
	public static final String ID_MUST_BE_SINGLE = "idMustBeSingle";
	public static final String IMAGE_FIELD_INCOMPLETE = "imageFieldIncomplete";
	public static final String VALIDATION_EXPRESSION_EMPTY = "validationExpressionEmpty";
	public static final String INTERMEDIATE_MODEL_INVALID = "intermediateModelInvalid";

	/**
	 * Bridge generator-facing intermediate-model invariants back into the DSL
	 * editor. This keeps the source editor aligned with the actual generation
	 * contract enforced by BlocklyEditorSpecValidator.
	 */
	@Check(CheckType.NORMAL)
	public void checkIntermediateBlocklySpec(DomainModel domain) {
		EditorSpec spec;
		try {
			spec = DomainModelAdapter.toEditorSpec(domain);
		} catch (RuntimeException ignored) {
			// During incomplete edits, syntax/linking checks should report the
			// immediate problem; the intermediate bridge can wait until the model
			// can be converted.
			return;
		}

		Result result = BlocklyEditorSpecValidator.validate(spec);
		if (result.getIssues().isEmpty()) return;

		for (Diagnostic diagnostic : BlocklySpecDiagnostics.diagnostics(
				result.getIssues(), BlocklySpecSourceMapper.forDomainModel(domain, null))) {
			Issue issue = diagnostic.getIssue();
			EObject source = BlocklySpecSourceMapper.sourceObjectForDomainIssue(domain, issue);
			if (source == null) source = domain;
			EStructuralFeature feature = diagnosticFeature(source);
			String message = intermediateMessage(diagnostic);
			if (issue.getSeverity() == BlocklyEditorSpecValidator.Severity.ERROR) {
				error(message, source, feature, INTERMEDIATE_MODEL_INVALID, firstNonBlank(issue.getLocation(), ""));
			} else {
				warning(message, source, feature, INTERMEDIATE_MODEL_INVALID, firstNonBlank(issue.getLocation(), ""));
			}
		}
	}

	/**
	 * Class names must be unique within the domain.
	 */
	@Check
	public void checkUniqueClassName(DomainModel domain) {
		Set<String> seen = new HashSet<>();
		for (int i = 0; i < domain.getClasses().size(); i++) {
			ClassDef cls = domain.getClasses().get(i);
			if (cls.getName() != null && !seen.add(cls.getName())) {
				error("Duplicate class name: '" + cls.getName() + "'",
						Model2BlocklyPackage.Literals.DOMAIN_MODEL__CLASSES, i,
						DUPLICATE_CLASS_NAME);
			}
		}
	}

	/**
	 * Containment lower bound must be <= upper bound.
	 */
	@Check
	public void checkContainmentCardinality(Containment cont) {
		if (cont.getLower() != 0 && cont.getUpper() != 0 && cont.getLower() > cont.getUpper()) {
			error("Lower bound (" + cont.getLower() + ") must be <= upper bound (" + cont.getUpper() + ").",
					Model2BlocklyPackage.Literals.CONTAINMENT__LOWER,
					INVALID_CARDINALITY);
		}
	}

	@Check
	public void checkAttributeCardinality(Attribute attr) {
		Cardinality cardinality = attr.getCardinality();
		if (cardinality != null) {
			checkCardinality(cardinality);
		}
		if (attr.isUnique() && attr.isNonUnique()) {
			error("Use either 'unique' or 'nonUnique', not both.",
					Model2BlocklyPackage.Literals.ATTRIBUTE__UNIQUE,
					CONFLICTING_CARDINALITY_FLAGS);
		}
		if (attr.isOrdered() && attr.isUnordered()) {
			error("Use either 'ordered' or 'unordered', not both.",
					Model2BlocklyPackage.Literals.ATTRIBUTE__ORDERED,
					CONFLICTING_CARDINALITY_FLAGS);
		}
		if (attr.isId() && cardinality != null && (cardinality.getUpper() == 0 || cardinality.getUpper() > 1)) {
			error("ID attributes must be single-valued.",
					Model2BlocklyPackage.Literals.ATTRIBUTE__ID,
					ID_MUST_BE_SINGLE);
		}
		if (isSimpleType(attr, SimpleTypeName.IMAGE)) {
			String imageUrl = firstNonBlank(attr.getImageUrl(), attr.getDefaultValue());
			if (imageUrl == null || attr.getImageWidth() == 0 || attr.getImageHeight() == 0) {
				warning("Image fields should define src, width and height for Blockly field_image.",
						Model2BlocklyPackage.Literals.ATTRIBUTE__TYPE,
						IMAGE_FIELD_INCOMPLETE);
			}
		}
	}

	@Check
	public void checkReferenceCardinality(Reference ref) {
		Cardinality cardinality = ref.getCardinality();
		if (cardinality != null) {
			checkCardinality(cardinality);
		}
		if (ref.isUnique() && ref.isNonUnique()) {
			error("Use either 'unique' or 'nonUnique', not both.",
					Model2BlocklyPackage.Literals.REFERENCE__UNIQUE,
					CONFLICTING_CARDINALITY_FLAGS);
		}
		if (ref.isOrdered() && ref.isUnordered()) {
			error("Use either 'ordered' or 'unordered', not both.",
					Model2BlocklyPackage.Literals.REFERENCE__ORDERED,
					CONFLICTING_CARDINALITY_FLAGS);
		}
	}

	private void checkCardinality(Cardinality cardinality) {
		if (cardinality.getUpper() != 0 && cardinality.getLower() > cardinality.getUpper()) {
			error("Lower bound (" + cardinality.getLower() + ") must be <= upper bound (" + cardinality.getUpper() + ").",
					Model2BlocklyPackage.Literals.CARDINALITY__LOWER,
					INVALID_CARDINALITY);
		}
	}

	/**
	 * Abstract classes should not be the sole option — warn if all classes are abstract.
	 */
	@Check
	public void checkNotAllAbstract(DomainModel domain) {
		if (domain.getClasses().isEmpty()) return;
		boolean hasConcreteClass = false;
		for (ClassDef cls : domain.getClasses()) {
			if (!cls.isAbstract()) {
				hasConcreteClass = true;
				break;
			}
		}
		if (!hasConcreteClass) {
			warning("All classes are abstract. No Blockly blocks will be generated.",
					Model2BlocklyPackage.Literals.DOMAIN_MODEL__NAME);
		}
	}

	/**
	 * Value inputs should reference classes marked as 'output'.
	 */
	@Check
	public void checkValueInputTargetIsOutput(ValueInput vi) {
		if (vi.getType() != null && !vi.getType().isAbstract() && !vi.getType().isOutput()) {
			boolean hasOutputSubtype = false;
			for (ClassDef cls : ((DomainModel) vi.eContainer().eContainer()).getClasses()) {
				if (cls.isOutput() && hasSuperType(cls, vi.getType())) {
					hasOutputSubtype = true;
					break;
				}
			}
			if (!hasOutputSubtype) {
				warning("Value input target '" + vi.getType().getName()
						+ "' is not an output class. Consider adding 'output' to its definition.",
						Model2BlocklyPackage.Literals.VALUE_INPUT__TYPE);
			}
		}
	}

	/**
	 * A boolean Blockly checkbox always has a true/false value, so "required"
	 * does not add useful runtime semantics for that field kind.
	 */
	@Check
	public void checkRequiredBoolean(Attribute attr) {
		if (!featureAsBoolean(attr, "required")) return;
		if (attr.getType() instanceof SimpleType
				&& ((SimpleType) attr.getType()).getTypeName() == SimpleTypeName.BOOLEAN) {
			warning("Boolean fields always have a value. The 'required' marker has no effect here.",
					Model2BlocklyPackage.Literals.ATTRIBUTE__TYPE,
					REQUIRED_BOOLEAN);
		}
	}

	/**
	 * Must-follow is defined for vertically connected statement blocks. Output
	 * blocks are value blocks and normally do not have previous/next links.
	 */
	@Check
	public void checkMustFollowStatementBlocks(ConstraintDef constraint) {
		if (constraint.getTarget() != null && constraint.getTarget().isOutput()) {
			warning("Must-follow validation targets an output block. It only applies to statement blocks.",
					Model2BlocklyPackage.Literals.CONSTRAINT_DEF__TARGET,
					MUST_FOLLOW_OUTPUT);
		}
		if (constraint.getPredecessor() != null && constraint.getPredecessor().isOutput()) {
			warning("Must-follow predecessor is an output block. It only applies to statement blocks.",
					Model2BlocklyPackage.Literals.CONSTRAINT_DEF__PREDECESSOR,
					MUST_FOLLOW_OUTPUT);
		}
	}

	/**
	 * Opposite references are synchronized by the generated runtime only when the
	 * named reverse reference exists as an editable reference on the target class.
	 */
	@Check
	public void checkOppositeRuntimeSupport(Reference ref) {
		if (ref.getOppositeName() == null || ref.getOppositeName().isEmpty()) return;
		if (ref.getType() == null) return;
		for (Feature feature : ref.getType().getFeatures()) {
			if (feature instanceof Reference && ref.getOppositeName().equals(((Reference) feature).getName())) {
				return;
			}
		}
		warning("Opposite reference '" + ref.getOppositeName()
				+ "' does not exist as a reference on target class '" + ref.getType().getName() + "'.",
				Model2BlocklyPackage.Literals.REFERENCE__OPPOSITE_NAME,
				OPPOSITE_NOT_FOUND);
	}

	/**
	 * UI widgets are generic metadata, but some widgets only make sense for a
	 * specific kind of field. Warn early instead of producing a confusing
	 * generated editor.
	 */
	@Check
	public void checkAttributeUiWidgetCompatibility(Attribute attr) {
		String widget = uiWidget(attr);
		if (widget == null) return;

		boolean isString = isSimpleType(attr, SimpleTypeName.STRING);
		boolean isNumber = isSimpleType(attr, SimpleTypeName.INT) || isSimpleType(attr, SimpleTypeName.FLOAT);
		boolean isBoolean = isSimpleType(attr, SimpleTypeName.BOOLEAN);
		boolean isEnum = attr.getType() instanceof EnumType;

		if ("textarea".equals(widget) && !isString) {
			warning("UI widget 'textarea' is intended for string attributes.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}
		if ("slider".equals(widget)) {
			if (!isNumber) {
				warning("UI widget 'slider' is intended for int or float attributes.",
						Model2BlocklyPackage.Literals.FEATURE__UI,
						UI_WIDGET_INCOMPATIBLE);
			}
			if (attr.getMin() == null || attr.getMax() == null) {
				warning("UI widget 'slider' should define both min and max for predictable rendering.",
						Model2BlocklyPackage.Literals.FEATURE__UI,
						UI_WIDGET_INCOMPLETE);
			}
		}
		if (("switch".equals(widget) || "checkbox".equals(widget)) && !isBoolean) {
			warning("UI widget '" + widget + "' is intended for boolean attributes.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}
		if (("select".equals(widget) || "radio".equals(widget)) && !isEnum) {
			warning("UI widget '" + widget + "' is intended for enum attributes.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}
		if ("reference-select".equals(widget)) {
			warning("UI widget 'reference-select' is intended for reference features, not attributes.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}
		if ("image".equals(widget) && !isSimpleType(attr, SimpleTypeName.IMAGE)) {
			warning("UI widget 'image' is intended for image attributes.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}
		checkRequiredEditable(attr, featureAsBoolean(attr, "required"), attr.getDefaultValue());
	}

	@Check
	public void checkReferenceUiOptions(Reference ref) {
		String widget = uiWidget(ref);
		if (widget != null && !"reference-select".equals(widget) && !"select".equals(widget)) {
			warning("Reference features should use widget 'reference-select' or leave the widget unspecified.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					UI_WIDGET_INCOMPATIBLE);
		}

		String labelField = uiFeatureAsString(ref, "referenceLabelField");
		if (labelField != null && ref.getType() != null && !hasNamedFeature(ref.getType(), labelField)) {
			warning("Reference label field '" + labelField + "' does not exist on target type '" + ref.getType().getName() + "'.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					REFERENCE_LABEL_FIELD_MISSING);
		}

		checkRequiredEditable(ref, featureAsBoolean(ref, "required"), null);
	}

	@Check
	public void checkValidationExpression(ValidationDef validation) {
		if (validation.getExpression() == null || validation.getExpression().isBlank()) {
			error("Validation expression must not be empty.",
					Model2BlocklyPackage.Literals.VALIDATION_DEF__EXPRESSION,
					VALIDATION_EXPRESSION_EMPTY);
			return;
		}
		if (validation.getKind() == io.github.plortinus.model2blockly.model2Blockly.ValidationKind.OCL) {
			String translated = DomainModelAdapter.translateBasicOclExpression(
				validation.getTarget(), validation.getExpression());
			if (translated == null || translated.isBlank()) {
				error("Unsupported OCL validation expression. Supported subset: self.<feature>, size(), "
						+ "notEmpty(), isEmpty(), oclIsUndefined(), comparisons, and/or/not.",
						Model2BlocklyPackage.Literals.VALIDATION_DEF__EXPRESSION,
						VALIDATION_EXPRESSION_EMPTY);
			}
			return;
		}
		String syntaxError = ValidationExpressionSyntax.firstSyntaxError(validation.getExpression());
		if (syntaxError != null) {
			error("Validation expression has invalid syntax: " + syntaxError,
					Model2BlocklyPackage.Literals.VALIDATION_DEF__EXPRESSION,
					VALIDATION_EXPRESSION_EMPTY);
		}
	}

	private String intermediateMessage(Diagnostic diagnostic) {
		StringBuilder message = new StringBuilder(diagnostic.getIssue().getMessage());
		if (diagnostic.getIssue().getLocation() != null && !diagnostic.getIssue().getLocation().isBlank()) {
			message.append(" [").append(diagnostic.getIssue().getLocation()).append("]");
		}
		if (diagnostic.getHint() != null && !diagnostic.getHint().isBlank()) {
			message.append(" Hint: ").append(diagnostic.getHint());
		}
		return message.toString();
	}

	private EStructuralFeature diagnosticFeature(EObject source) {
		if (source instanceof DomainModel) return Model2BlocklyPackage.Literals.DOMAIN_MODEL__NAME;
		if (source instanceof CategoryDef) return Model2BlocklyPackage.Literals.CATEGORY_DEF__NAME;
		if (source instanceof ClassDef) return Model2BlocklyPackage.Literals.CLASS_DEF__NAME;
		if (source instanceof Feature) return Model2BlocklyPackage.Literals.FEATURE__NAME;
		if (source instanceof ConstraintDef) return Model2BlocklyPackage.Literals.CONSTRAINT_DEF__NAME;
		if (source instanceof ValidationDef) return Model2BlocklyPackage.Literals.VALIDATION_DEF__NAME;
		if (!source.eClass().getEAllStructuralFeatures().isEmpty()) {
			return source.eClass().getEAllStructuralFeatures().get(0);
		}
		return null;
	}

	private boolean hasSuperType(ClassDef cls, ClassDef target) {
		ClassDef current = cls;
		while (current != null) {
			if (current == target) return true;
			current = current.getSuperClass();
		}
		return false;
	}

	private boolean featureAsBoolean(org.eclipse.emf.ecore.EObject obj, String featureName) {
		if (obj == null) return false;
		EStructuralFeature feature = obj.eClass().getEStructuralFeature(featureName);
		if (feature == null) return false;
		Object value = obj.eGet(feature);
		return value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(String.valueOf(value));
	}

	private boolean isSimpleType(Attribute attr, SimpleTypeName typeName) {
		return attr.getType() instanceof SimpleType
				&& ((SimpleType) attr.getType()).getTypeName() == typeName;
	}

	private String uiWidget(Feature feature) {
		String widget = uiFeatureAsString(feature, "widget");
		return widget != null ? widget.toLowerCase().replace('_', '-') : null;
	}

	private String uiFeatureAsString(Feature feature, String featureName) {
		org.eclipse.emf.ecore.EObject ui = uiOptions(feature);
		if (ui == null) return null;
		EStructuralFeature eFeature = ui.eClass().getEStructuralFeature(featureName);
		if (eFeature == null) return null;
		Object value = ui.eGet(eFeature);
		if (value == null) return null;
		String text = String.valueOf(value);
		return text.isBlank() ? null : text;
	}

	private org.eclipse.emf.ecore.EObject uiOptions(Feature feature) {
		if (feature == null) return null;
		EStructuralFeature uiFeature = feature.eClass().getEStructuralFeature("ui");
		if (uiFeature == null) return null;
		Object ui = feature.eGet(uiFeature);
		return ui instanceof org.eclipse.emf.ecore.EObject ? (org.eclipse.emf.ecore.EObject) ui : null;
	}

	private boolean hasNamedFeature(ClassDef cls, String featureName) {
		for (Feature feature : cls.getFeatures()) {
			if (featureName.equals(feature.getName())) return true;
		}
		return false;
	}

	private String firstNonBlank(String... values) {
		if (values == null) return null;
		for (String value : values) {
			if (value != null && !value.isBlank()) return value;
		}
		return null;
	}

	private void checkRequiredEditable(Feature feature, boolean required, String defaultValue) {
		if (!required) return;
		if (featureAsBoolean(uiOptions(feature), "uiHidden", "hidden")) {
			warning("Required UI feature is hidden, so users cannot fill or inspect it in the generated UI.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					REQUIRED_NOT_EDITABLE);
		}
		if (featureAsBoolean(uiOptions(feature), "uiReadonly", "readonly")
				&& (defaultValue == null || defaultValue.isBlank())) {
			warning("Required UI feature is readonly and has no default value.",
					Model2BlocklyPackage.Literals.FEATURE__UI,
					REQUIRED_NOT_EDITABLE);
		}
	}

	private boolean featureAsBoolean(org.eclipse.emf.ecore.EObject obj, String primaryFeatureName, String fallbackFeatureName) {
		if (obj == null) return false;
		EStructuralFeature feature = obj.eClass().getEStructuralFeature(primaryFeatureName);
		if (feature == null) feature = obj.eClass().getEStructuralFeature(fallbackFeatureName);
		if (feature == null) return false;
		Object value = obj.eGet(feature);
		return value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(String.valueOf(value));
	}
}
