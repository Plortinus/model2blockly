package io.github.plortinus.model2blockly.adapter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import io.github.plortinus.model2blockly.model2Blockly.*;
import io.github.plortinus.model2blockly.blocklyspec.*;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecModelMapper;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Converts a DomainModel (from the .m2b DSL) into the platform-independent
 * BlocklyEditorSpec intermediate model.
 */
public class DomainModelAdapter {

	public static EObject toIntermediateModel(DomainModel domain) {
		return toEditorSpec(domain);
	}

	public static EditorSpec toEditorSpec(DomainModel domain) {
		return BlocklySpecModelMapper.toEmfSpec(fromDomainModel(domain));
	}

	public static BlocklyEditorSpec fromDomainModel(DomainModel domain) {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();
		spec.setDomainName(domain.getName() != null ? domain.getName() : "domain");
		spec.setNsURI("http://www.example.org/" + spec.getDomainName().toLowerCase());
		spec.setNsPrefix(spec.getDomainName().toLowerCase());
		spec.setCodeLanguage(featureAsString(domain, "codeLanguage"));
		spec.setCodeFileExtension(featureAsString(domain, "codeFileExtension"));
		spec.setRuntimeKind(featureAsString(domain, "runtimeKind"));

		convertCategories(domain, spec);
		convertClasses(domain, spec);
		convertConstraints(domain, spec);
		convertValidations(domain, spec);
		generateCardinalityValidations(spec);
		generateRequiredValidations(spec);
		generateFieldCardinalityValidations(spec);
		generateUniqueValidations(spec);
		convertWorkspaceOptions(domain, spec);

		// Auto-generate categories if no class has an explicit category
		boolean anyCategory = false;
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.getCategoryName() != null) { anyCategory = true; break; }
		}
		if (!anyCategory) {
			autoGenerateCategories(spec);
		}

		return spec;
	}

	/**
	 * Reads optional workspace options from DSL model.
	 *
	 * NOTE: this is intentionally reflective so this adapter can compile even
	 * before Xtext-generated Java artifacts are refreshed after grammar changes.
	 */
	private static void convertWorkspaceOptions(DomainModel domain, BlocklyEditorSpec spec) {
		for (EObject child : domain.eContents()) {
			if (!"WorkspaceConfig".equals(child.eClass().getName())) continue;

			EStructuralFeature optionsFeature = child.eClass().getEStructuralFeature("options");
			if (optionsFeature == null) return;

			Object rawOptions = child.eGet(optionsFeature);
			if (!(rawOptions instanceof List<?>)) return;

			Map<String, Object> options = new LinkedHashMap<>();
			for (Object obj : (List<?>) rawOptions) {
				if (!(obj instanceof EObject)) continue;
				Map.Entry<String, Object> entry = toWorkspaceEntry((EObject) obj);
				if (entry != null && entry.getKey() != null) {
					options.put(entry.getKey(), entry.getValue());
				}
			}
			// Extract toolboxType from workspace options if present
			Object toolboxType = options.remove("toolboxType");
			if (toolboxType instanceof String) {
				spec.setToolboxType((String) toolboxType);
			}
			spec.setWorkspaceOptions(options);
			return;
		}
	}

	private static void convertCategories(DomainModel domain, BlocklyEditorSpec spec) {
		for (CategoryDef cat : domain.getCategories()) {
			spec.getCategories().add(convertCategoryDef(cat));
		}
	}

	private static CategorySpec convertCategoryDef(CategoryDef cat) {
		CategorySpec cs = new CategorySpec();
		cs.setName(cat.getName());
		cs.setLabel(cat.getLabel() != null ? cat.getLabel() : cat.getName());
		cs.setColour(cat.getColour() != 0 ? cat.getColour() : 200);
		// Recursive: convert subcategories if present
		try {
			@SuppressWarnings("unchecked")
			List<?> subcats = (List<?>) cat.eGet(cat.eClass().getEStructuralFeature("subcategories"));
			if (subcats != null) {
				for (Object sub : subcats) {
					if (sub instanceof CategoryDef) {
						CategorySpec childSpec = convertCategoryDef((CategoryDef) sub);
						if (childSpec.getColour() == 200 && cs.getColour() != 200) {
							childSpec.setColour(cs.getColour());
						}
						cs.getChildren().add(childSpec);
					}
				}
			}
		} catch (Exception ignored) {
			// subcategories feature may not exist in older grammar versions
		}
		return cs;
	}

	private static void convertClasses(DomainModel domain, BlocklyEditorSpec spec) {
		Set<String> containedTypeNames = collectContainedTypeNames(domain);
		for (ClassDef cls : domain.getClasses()) {
			BlockTypeSpec bt = new BlockTypeSpec();
			bt.setTypeName(cls.getName());
			bt.setLabel(cls.getLabel() != null ? cls.getLabel() : cls.getName());
			bt.setAbstract(cls.isAbstract());

			if (cls.getColour() != 0) {
				bt.setColour(cls.getColour());
			} else if (cls.getCategory() != null && cls.getCategory().getColour() != 0) {
				bt.setColour(cls.getCategory().getColour());
			}

			if (cls.getCategory() != null) {
				bt.setCategoryName(cls.getCategory().getName());
			}

			if (cls.getSuperClass() != null) {
				bt.setSuperTypeName(cls.getSuperClass().getName());
			}

			if (cls.getTooltip() != null) {
				bt.setTooltip(cls.getTooltip());
			}
			if (cls.getMessage0() != null) {
				bt.setMessage0(cls.getMessage0());
			}
			if (cls.getHelpUrl() != null) {
				bt.setHelpUrl(cls.getHelpUrl());
			}

			bt.setCodeTemplate(featureAsString(cls, "codeTemplate"));

			if (cls.getInputsInline() != null) {
				bt.setInputsInline(cls.getInputsInline() == BoolVal.TRUE);
			} else if (cls.isInline()) {
				bt.setInputsInline(Boolean.TRUE);
			}

			boolean hasContainments = false;
			for (Feature f : cls.getFeatures()) {
				if (f instanceof Attribute) {
					FieldSpec field = convertAttribute((Attribute) f);
					bt.getFields().add(field);
					bt.getOrderedInputNames().add(field.getName());
					if (field.isId()) {
						bt.setIdFieldName(field.getName());
					}
				} else if (f instanceof Containment) {
					hasContainments = true;
					StatementInputSpec input = convertContainment((Containment) f);
					bt.getStatementInputs().add(input);
					bt.getOrderedInputNames().add(input.getName());
				} else if (f instanceof Reference) {
					ReferenceFieldSpec reference = convertReference((Reference) f);
					bt.getReferenceFields().add(reference);
					bt.getOrderedInputNames().add(reference.getName());
				} else if (f instanceof ValueInput) {
					ValueInputSpec input = convertValueInput((ValueInput) f);
					bt.getValueInputs().add(input);
					bt.getOrderedInputNames().add(input.getName());
				}
			}

			if (cls.isOutput()) {
				if (cls.getOutputType() != null) {
					bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
					bt.setOutputType(cls.getOutputType().getName());
				} else if (cls.getSuperClass() != null) {
					bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
					bt.setOutputType(getRootAncestorName(cls));
				} else {
					bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT);
					bt.setOutputType(cls.getName());
				}
			} else if (hasContainments && cls.getSuperClass() == null
					&& !containedTypeNames.contains(cls.getName())) {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.NONE);
			} else if (cls.getSuperClass() != null) {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.TYPED);
				bt.setConnectionTypeName(getRootAncestorName(cls));
			} else if (containedTypeNames.contains(cls.getName())) {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.TYPED);
				bt.setConnectionTypeName(cls.getName());
			} else {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.FREE);
			}

			spec.getBlockTypes().add(bt);
		}
	}

	private static Set<String> collectContainedTypeNames(DomainModel domain) {
		Set<String> names = new HashSet<>();
		for (ClassDef cls : domain.getClasses()) {
			for (Feature feature : cls.getFeatures()) {
				if (feature instanceof Containment) {
					Containment containment = (Containment) feature;
					if (containment.getType() != null) {
						names.add(containment.getType().getName());
					}
				}
			}
		}
		return names;
	}

	private static FieldSpec convertAttribute(Attribute attr) {
		FieldSpec fs = new FieldSpec();
		fs.setName(attr.getName());
		fs.setDefaultValue(attr.getDefaultValue());
		if (attr.getCardinality() != null) {
			fs.setLowerBound(attr.getCardinality().getLower());
			fs.setUpperBound(attr.getCardinality().getUpper());
			if (attr.getCardinality().getLower() >= 1) {
				fs.setRequired(true);
			}
		}

		if (attr.getMin() != null) fs.setMin(attr.getMin());
		if (attr.getMax() != null) fs.setMax(attr.getMax());
		fs.setRequired(fs.isRequired() || featureAsBoolean(attr, "required"));
		fs.setId(featureAsBoolean(attr, "id"));
		fs.setUnique(!featureAsBoolean(attr, "nonUnique"));
		fs.setOrdered(!featureAsBoolean(attr, "unordered"));
		if (attr.getImageUrl() != null) fs.setImageUrl(attr.getImageUrl());
		if (attr.getImageWidth() != 0) fs.setImageWidth(attr.getImageWidth());
		if (attr.getImageHeight() != 0) fs.setImageHeight(attr.getImageHeight());
		if (attr.getImageAlt() != null) fs.setImageAlt(attr.getImageAlt());
		applyDslUiOptions(attr, fs);

		AttributeType type = attr.getType();
		if (type instanceof SimpleType) {
			switch (((SimpleType) type).getTypeName()) {
				case STRING:  fs.setFieldType(FieldSpec.FieldType.TEXT);    break;
				case INT:     fs.setFieldType(FieldSpec.FieldType.INTEGER); break;
				case FLOAT:   fs.setFieldType(FieldSpec.FieldType.FLOAT);   break;
				case BOOLEAN: fs.setFieldType(FieldSpec.FieldType.BOOLEAN); break;
				case COLOUR:  fs.setFieldType(FieldSpec.FieldType.COLOUR);  break;
				case ANGLE:   fs.setFieldType(FieldSpec.FieldType.ANGLE);   break;
				case IMAGE:
					fs.setFieldType(FieldSpec.FieldType.IMAGE);
					if (fs.getImageUrl() == null) fs.setImageUrl(attr.getDefaultValue());
					break;
				case LABEL:   fs.setFieldType(FieldSpec.FieldType.LABEL);   break;
			}
		} else if (type instanceof EnumType) {
			fs.setFieldType(FieldSpec.FieldType.DROPDOWN);
			for (EnumLiteral lit : ((EnumType) type).getLiterals()) {
				fs.getOptions().add(new DropdownOption(
					lit.getName(),
					lit.getLabel() != null ? lit.getLabel() : lit.getName()
				));
			}
		}
		if (fs.isMany()) {
			fs.setFieldType(FieldSpec.FieldType.TEXT);
		}
		return fs;
	}

	private static StatementInputSpec convertContainment(Containment cont) {
		StatementInputSpec sis = new StatementInputSpec();
		sis.setName(cont.getName());
		sis.setCheckType(cont.getType() != null ? cont.getType().getName() : null);
		sis.setLowerBound(cont.getLower());
		sis.setUpperBound(cont.getUpper());
		applyDslUiOptions(cont, sis);
		return sis;
	}

	private static ReferenceFieldSpec convertReference(Reference ref) {
		ReferenceFieldSpec rfs = new ReferenceFieldSpec();
		rfs.setName(ref.getName());
		rfs.setTargetTypeName(ref.getType() != null ? ref.getType().getName() : null);
		if (ref.getCardinality() != null) {
			rfs.setLowerBound(ref.getCardinality().getLower());
			rfs.setUpperBound(ref.getCardinality().getUpper());
			if (ref.getCardinality().getLower() >= 1) {
				rfs.setRequired(true);
			}
		}
		rfs.setRequired(rfs.isRequired() || featureAsBoolean(ref, "required"));
		rfs.setUnique(!featureAsBoolean(ref, "nonUnique"));
		rfs.setOrdered(!featureAsBoolean(ref, "unordered"));
		rfs.setOppositeName(featureAsString(ref, "oppositeName"));
		applyDslUiOptions(ref, rfs);
		return rfs;
	}

	private static ValueInputSpec convertValueInput(ValueInput vi) {
		ValueInputSpec vis = new ValueInputSpec();
		vis.setName(vi.getName());
		vis.setCheckType(vi.getType() != null ? vi.getType().getName() : null);
		if (vi.getShadowType() != null) {
			vis.setShadowBlockType(vi.getShadowType().getName());
		}
		applyDslUiOptions(vi, vis);
		return vis;
	}

	private static void applyDslUiOptions(EObject source, FieldSpec target) {
		EObject ui = uiOptions(source);
		target.setUiWidget(normalizeWidget(featureAsString(ui, "widget")));
		target.setUiLabel(featureAsString(ui, "uiLabel"));
		target.setUiHelp(featureAsString(ui, "help"));
		target.setUiPlaceholder(featureAsString(ui, "placeholder"));
		target.setUiGroup(featureAsString(ui, "group"));
		target.setUiVariant(featureAsString(ui, "variant"));
		target.setUiReadonly(featureAsBoolean(ui, "uiReadonly", "readonly"));
		target.setUiHidden(featureAsBoolean(ui, "uiHidden", "hidden"));
		target.setUiOrder(featureAsNullableInt(ui, "order"));
	}

	private static void applyDslUiOptions(EObject source, ReferenceFieldSpec target) {
		EObject ui = uiOptions(source);
		target.setUiWidget(normalizeWidget(featureAsString(ui, "widget")));
		target.setUiLabel(featureAsString(ui, "uiLabel"));
		target.setUiHelp(featureAsString(ui, "help"));
		target.setUiPlaceholder(featureAsString(ui, "placeholder"));
		target.setUiGroup(featureAsString(ui, "group"));
		target.setUiVariant(featureAsString(ui, "variant"));
		target.setUiReadonly(featureAsBoolean(ui, "uiReadonly", "readonly"));
		target.setUiHidden(featureAsBoolean(ui, "uiHidden", "hidden"));
		target.setUiOrder(featureAsNullableInt(ui, "order"));
		target.setReferenceLabelField(featureAsString(ui, "referenceLabelField"));
	}

	private static void applyDslUiOptions(EObject source, ValueInputSpec target) {
		EObject ui = uiOptions(source);
		target.setUiWidget(normalizeWidget(featureAsString(ui, "widget")));
		target.setUiLabel(featureAsString(ui, "uiLabel"));
		target.setUiHelp(featureAsString(ui, "help"));
		target.setUiPlaceholder(featureAsString(ui, "placeholder"));
		target.setUiGroup(featureAsString(ui, "group"));
		target.setUiVariant(featureAsString(ui, "variant"));
		target.setUiReadonly(featureAsBoolean(ui, "uiReadonly", "readonly"));
		target.setUiHidden(featureAsBoolean(ui, "uiHidden", "hidden"));
		target.setUiOrder(featureAsNullableInt(ui, "order"));
	}

	private static void applyDslUiOptions(EObject source, StatementInputSpec target) {
		EObject ui = uiOptions(source);
		target.setUiWidget(normalizeWidget(featureAsString(ui, "widget")));
		target.setUiLabel(featureAsString(ui, "uiLabel"));
		target.setUiHelp(featureAsString(ui, "help"));
		target.setUiPlaceholder(featureAsString(ui, "placeholder"));
		target.setUiGroup(featureAsString(ui, "group"));
		target.setUiVariant(featureAsString(ui, "variant"));
		target.setUiReadonly(featureAsBoolean(ui, "uiReadonly", "readonly"));
		target.setUiHidden(featureAsBoolean(ui, "uiHidden", "hidden"));
		target.setUiOrder(featureAsNullableInt(ui, "order"));
	}

	private static EObject uiOptions(EObject source) {
		if (source == null) return null;
		EStructuralFeature uiFeature = source.eClass().getEStructuralFeature("ui");
		if (uiFeature == null) return source;
		Object ui = source.eGet(uiFeature);
		return ui instanceof EObject ? (EObject) ui : source;
	}

	private static String normalizeWidget(String value) {
		if (value == null) return null;
		return value.toLowerCase().replace('_', '-');
	}

	private static Map.Entry<String, Object> toWorkspaceEntry(EObject option) {
		String typeName = option.eClass().getName();
		String key = featureAsString(option, "key");
		if (key == null || key.isEmpty()) return null;

		switch (typeName) {
			case "StringOption":
				return new AbstractMap.SimpleEntry<>(key, featureAsString(option, "value"));
			case "IntOption":
				return new AbstractMap.SimpleEntry<>(key, featureAsInt(option, "value"));
			case "BoolOption":
				return new AbstractMap.SimpleEntry<>(key, featureAsBoolean(option, "value"));
			case "ObjectOption":
				return new AbstractMap.SimpleEntry<>(key, objectOptionToMap(option));
			default:
				return null;
		}
	}

	private static Map<String, Object> objectOptionToMap(EObject objectOption) {
		Map<String, Object> map = new LinkedHashMap<>();
		EStructuralFeature entriesFeature = objectOption.eClass().getEStructuralFeature("entries");
		if (entriesFeature == null) return map;
		Object rawEntries = objectOption.eGet(entriesFeature);
		if (!(rawEntries instanceof List<?>)) return map;
		for (Object entryObj : (List<?>) rawEntries) {
			if (!(entryObj instanceof EObject)) continue;
			Map.Entry<String, Object> entry = toWorkspaceEntry((EObject) entryObj);
			if (entry != null && entry.getKey() != null) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

	private static String featureAsString(EObject obj, String featureName) {
		if (obj == null) return null;
		EStructuralFeature feature = obj.eClass().getEStructuralFeature(featureName);
		if (feature == null) return null;
		Object value = obj.eGet(feature);
		return value != null ? String.valueOf(value) : null;
	}

	private static Integer featureAsInt(EObject obj, String featureName) {
		String text = featureAsString(obj, featureName);
		if (text == null) return null;
		try { return Integer.valueOf(text); }
		catch (NumberFormatException ignored) { return null; }
	}

	private static Integer featureAsNullableInt(EObject obj, String featureName) {
		return featureAsInt(obj, featureName);
	}

	private static Boolean featureAsBoolean(EObject obj, String featureName) {
		if (obj == null) return Boolean.FALSE;
		EStructuralFeature feature = obj.eClass().getEStructuralFeature(featureName);
		if (feature == null) return Boolean.FALSE;
		Object value = obj.eGet(feature);
		if (value instanceof Boolean) return (Boolean) value;
		String text = value != null ? String.valueOf(value).toLowerCase() : "";
		return text.contains("true");
	}

	private static Boolean featureAsBoolean(EObject obj, String primaryFeatureName, String fallbackFeatureName) {
		if (obj == null) return Boolean.FALSE;
		EStructuralFeature primary = obj.eClass().getEStructuralFeature(primaryFeatureName);
		if (primary != null) return featureAsBoolean(obj, primaryFeatureName);
		return featureAsBoolean(obj, fallbackFeatureName);
	}

	private static void convertConstraints(DomainModel domain, BlocklyEditorSpec spec) {
		for (ConstraintDef c : domain.getConstraints()) {
			ValidationSpec vs = new ValidationSpec();
			vs.setType(ValidationSpec.ValidationType.MUST_FOLLOW);
			vs.setName(c.getName());
			vs.setTargetType(c.getTarget() != null ? c.getTarget().getName() : "");
			vs.setPredecessorType(c.getPredecessor() != null ? c.getPredecessor().getName() : "");
			vs.setVisualBlock(ValidationBlockSpec.previousBlockIs(vs.getTargetType(), vs.getPredecessorType()));
			vs.setVisualExpression(ValidationExpressionSpec.previousBlockIs(vs.getPredecessorType()));
			spec.getValidations().add(vs);
		}
	}

	private static void convertValidations(DomainModel domain, BlocklyEditorSpec spec) {
		for (ValidationDef validation : domain.getValidations()) {
			if (validation.getTarget() == null) continue;
			String expression = validation.getExpression();
			if (validation.getKind() == ValidationKind.OCL) {
				expression = translateBasicOclExpression(validation.getTarget(), expression);
				if (expression == null || expression.isBlank()) {
					addUnsupportedOclValidation(validation.getTarget(), spec,
						validation.getName(), validation.getExpression());
					continue;
				}
			}
			addValidationExpression(validation.getTarget(), spec,
				validation.getName(), expression, validation.getMessage());
		}
	}

	private static void addUnsupportedOclValidation(ClassDef cls, BlocklyEditorSpec spec,
			String name, String expression) {
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName(cls.getName() + "_" + (name == null || name.isBlank() ? "ocl" : name));
		vs.setOwnerType(cls.getName());
		vs.setDiagnosticMessage("Unsupported OCL validation '" + (name == null || name.isBlank() ? "ocl" : name)
			+ "'. Supported subset: self.<feature>, size(), notEmpty(), isEmpty(), oclIsUndefined(), "
			+ "comparisons, and/or/not. Expression: " + firstNonBlank(expression, "<empty>"));
		spec.getValidations().add(vs);
	}

	private static void addValidationExpression(ClassDef cls, BlocklyEditorSpec spec,
			String name, String expression, String message) {
		if (expression == null || expression.isBlank()) return;
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName(cls.getName() + "_" + (name == null || name.isBlank() ? "validation" : name));
		vs.setOwnerType(cls.getName());
		String trimmed = expression.trim();
		vs.setExpression(trimmed);
		ValidationExpressionSpec visualExpression = ValidationExpressionParser
			.parse(trimmed, fieldName -> sizeAccessorExpression(cls, fieldName))
			.orElse(null);
		vs.setVisualExpression(visualExpression);
		vs.setVisualBlock(ValidationBlockSpec.expressionRule(cls.getName(), trimmed, visualExpression));
		vs.setMessage(firstNonBlank(message,
			"Constraint \"" + (name == null || name.isBlank() ? "validation" : name)
				+ "\" is violated on " + cls.getName() + "."));
		spec.getValidations().add(vs);
	}

	private static void generateCardinalityValidations(BlocklyEditorSpec spec) {
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			for (StatementInputSpec si : bt.getStatementInputs()) {
				if (si.getLowerBound() != 0 || si.getUpperBound() != 0) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.CARDINALITY);
					vs.setName(bt.getTypeName() + "_" + si.getName());
					vs.setOwnerType(bt.getTypeName());
					vs.setContainmentName(si.getName());
					vs.setLowerBound(si.getLowerBound());
					vs.setUpperBound(si.getUpperBound());
					vs.setVisualBlock(ValidationBlockSpec.statementCardinality(
						bt.getTypeName(), si.getName(), si.getLowerBound(), si.getUpperBound()));
					vs.setVisualExpression(cardinalityExpression(si.getName(), si.getLowerBound(), si.getUpperBound()));
					spec.getValidations().add(vs);
				}
			}
		}
	}

	/**
	 * Auto-generates REQUIRED validations for fields/references
	 * marked as required in the intermediate model.
	 * (In DSL path, this is driven by the 'required' keyword;
	 *  in Ecore path, by EAttribute/EReference.lowerBound ≥ 1.)
	 */
	private static void generateRequiredValidations(BlocklyEditorSpec spec) {
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.isAbstract()) continue;
			for (FieldSpec fs : bt.getFields()) {
				if (fs.isRequired()) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.REQUIRED);
					vs.setName(bt.getTypeName() + "_" + fs.getName() + "_required");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(fs.getName());
					vs.setFieldKind("attribute");
					vs.setVisualBlock(ValidationBlockSpec.requiredField(bt.getTypeName(), fs.getName(), "attribute"));
					vs.setVisualExpression(ValidationExpressionSpec.fieldExists(fs.getName()));
					spec.getValidations().add(vs);
				}
			}
			for (ReferenceFieldSpec rfs : bt.getReferenceFields()) {
				if (rfs.isRequired()) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.REQUIRED);
					vs.setName(bt.getTypeName() + "_" + rfs.getName() + "_required");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(rfs.getName());
					vs.setFieldKind("reference");
					vs.setVisualBlock(ValidationBlockSpec.requiredField(bt.getTypeName(), rfs.getName(), "reference"));
					vs.setVisualExpression(ValidationExpressionSpec.fieldExists(rfs.getName()));
					spec.getValidations().add(vs);
				}
			}
		}
	}

	private static ValidationExpressionSpec cardinalityExpression(String inputName, int lowerBound, int upperBound) {
		ValidationExpressionSpec count = ValidationExpressionSpec.inputCount(inputName);
		ValidationExpressionSpec lower = lowerBound != 0
			? ValidationExpressionSpec.compare("GTE", count, ValidationExpressionSpec.number(Integer.toString(lowerBound)))
			: null;
		ValidationExpressionSpec upper = upperBound != 0
			? ValidationExpressionSpec.compare("LTE", count, ValidationExpressionSpec.number(Integer.toString(upperBound)))
			: null;
		if (lower != null && upper != null) return ValidationExpressionSpec.logic("AND", lower, upper);
		return lower != null ? lower : upper;
	}

	private static void generateFieldCardinalityValidations(BlocklyEditorSpec spec) {
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.isAbstract()) continue;
			for (FieldSpec fs : bt.getFields()) {
				if (fs.isMany() && (fs.getLowerBound() > 1 || fs.getUpperBound() != 0)) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.FIELD_CARDINALITY);
					vs.setName(bt.getTypeName() + "_" + fs.getName() + "_cardinality");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(fs.getName());
					vs.setFieldKind("attribute");
					vs.setLowerBound(fs.getLowerBound());
					vs.setUpperBound(fs.getUpperBound());
					vs.setVisualBlock(ValidationBlockSpec.fieldCardinality(
						bt.getTypeName(), fs.getName(), "attribute", fs.getLowerBound(), fs.getUpperBound()));
					vs.setVisualExpression(fieldCardinalityExpression(fs.getName(), fs.getLowerBound(), fs.getUpperBound()));
					spec.getValidations().add(vs);
				}
			}
			for (ReferenceFieldSpec rfs : bt.getReferenceFields()) {
				if (rfs.isMany() && (rfs.getLowerBound() > 1 || rfs.getUpperBound() != 0)) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.FIELD_CARDINALITY);
					vs.setName(bt.getTypeName() + "_" + rfs.getName() + "_cardinality");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(rfs.getName());
					vs.setFieldKind("reference");
					vs.setLowerBound(rfs.getLowerBound());
					vs.setUpperBound(rfs.getUpperBound());
					vs.setVisualBlock(ValidationBlockSpec.fieldCardinality(
						bt.getTypeName(), rfs.getName(), "reference", rfs.getLowerBound(), rfs.getUpperBound()));
					vs.setVisualExpression(fieldCardinalityExpression(rfs.getName(), rfs.getLowerBound(), rfs.getUpperBound()));
					spec.getValidations().add(vs);
				}
			}
		}
	}

	private static void generateUniqueValidations(BlocklyEditorSpec spec) {
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.isAbstract()) continue;
			for (FieldSpec fs : bt.getFields()) {
				if (fs.isId() || (fs.isMany() && fs.isUnique())) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.UNIQUE);
					vs.setName(bt.getTypeName() + "_" + fs.getName() + "_unique");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(fs.getName());
					vs.setFieldKind(fs.isId() ? "id" : "attribute");
					vs.setVisualBlock(fs.isId()
						? ValidationBlockSpec.typeFieldUnique(bt.getTypeName(), fs.getName())
						: ValidationBlockSpec.fieldUnique(bt.getTypeName(), fs.getName(), "attribute"));
					vs.setVisualExpression(fs.isId()
						? ValidationExpressionSpec.typeFieldUnique(bt.getTypeName(), fs.getName())
						: ValidationExpressionSpec.fieldUnique(fs.getName()));
					spec.getValidations().add(vs);
				}
			}
			for (ReferenceFieldSpec rfs : bt.getReferenceFields()) {
				if (rfs.isMany() && rfs.isUnique()) {
					ValidationSpec vs = new ValidationSpec();
					vs.setType(ValidationSpec.ValidationType.UNIQUE);
					vs.setName(bt.getTypeName() + "_" + rfs.getName() + "_unique");
					vs.setOwnerType(bt.getTypeName());
					vs.setFieldName(rfs.getName());
					vs.setFieldKind("reference");
					vs.setVisualBlock(ValidationBlockSpec.fieldUnique(bt.getTypeName(), rfs.getName(), "reference"));
					vs.setVisualExpression(ValidationExpressionSpec.fieldUnique(rfs.getName()));
					spec.getValidations().add(vs);
				}
			}
		}
	}

	private static ValidationExpressionSpec fieldCardinalityExpression(String fieldName, int lowerBound, int upperBound) {
		ValidationExpressionSpec count = ValidationExpressionSpec.fieldCount(fieldName);
		ValidationExpressionSpec lower = lowerBound != 0
			? ValidationExpressionSpec.compare("GTE", count, ValidationExpressionSpec.number(Integer.toString(lowerBound)))
			: null;
		ValidationExpressionSpec upper = upperBound != 0
			? ValidationExpressionSpec.compare("LTE", count, ValidationExpressionSpec.number(Integer.toString(upperBound)))
			: null;
		if (lower != null && upper != null) return ValidationExpressionSpec.logic("AND", lower, upper);
		return lower != null ? lower : upper;
	}

	private static ValidationExpressionSpec sizeAccessorExpression(ClassDef cls, String fieldName) {
		Feature feature = findFeature(cls, fieldName);
		if (feature instanceof Containment) {
			return ValidationExpressionSpec.inputCount(fieldName);
		}
		return ValidationExpressionSpec.fieldCount(fieldName);
	}

	private static Feature findFeature(ClassDef cls, String fieldName) {
		ClassDef current = cls;
		for (int guard = 0; current != null && guard < 50; guard++) {
			for (Feature feature : current.getFeatures()) {
				if (fieldName.equals(feature.getName())) return feature;
			}
			current = current.getSuperClass();
		}
		return null;
	}

	public static String translateBasicOclExpression(ClassDef cls, String oclExpression) {
		if (cls == null) return null;
		if (oclExpression == null || oclExpression.isBlank()) return null;
		String expr = oclExpression.trim();
		if (Pattern.compile("\\b(forAll|exists|collect|select|reject|closure|let|implies|iterate|oclIsKindOf|oclIsTypeOf)\\b",
				Pattern.CASE_INSENSITIVE).matcher(expr).find()) {
			return null;
		}

		expr = replaceAll(expr,
			"\\bself\\.([A-Za-z_][A-Za-z0-9_]*)\\s*(?:->|\\.)\\s*size\\s*\\(\\s*\\)",
			"size(\"$1\")");
		expr = replaceAll(expr,
			"\\bself\\.([A-Za-z_][A-Za-z0-9_]*)\\s*->\\s*notEmpty\\s*\\(\\s*\\)",
			"has(\"$1\")");
		expr = replaceAll(expr,
			"\\bself\\.([A-Za-z_][A-Za-z0-9_]*)\\s*->\\s*isEmpty\\s*\\(\\s*\\)",
			"!has(\"$1\")");
		expr = replaceAll(expr,
			"\\bself\\.([A-Za-z_][A-Za-z0-9_]*)\\s*\\.\\s*oclIsUndefined\\s*\\(\\s*\\)",
			"!has(\"$1\")");

		for (Feature feature : allFeatures(cls)) {
			String accessor = isNumericFeature(feature)
				? "number(\"" + feature.getName() + "\")"
				: "value(\"" + feature.getName() + "\")";
			expr = expr.replaceAll("\\bself\\." + Pattern.quote(feature.getName()) + "\\b",
				Matcher.quoteReplacement(accessor));
		}

		expr = expr.replaceAll("(?i)\\band\\b", "&&");
		expr = expr.replaceAll("(?i)\\bor\\b", "||");
		expr = expr.replaceAll("(?i)\\bnot\\b", "!");
		expr = expr.replace("<>", "!==");
		expr = expr.replaceAll("(?<![<>!=])=(?!=)", "===");
		expr = expr.replaceAll("(?i)\\btrue\\b", "true");
		expr = expr.replaceAll("(?i)\\bfalse\\b", "false");
		expr = expr.replaceAll("(?i)\\bnull\\b", "\"\"");

		if (expr.contains("->") || expr.contains("self.")) return null;
		return expr;
	}

	private static List<Feature> allFeatures(ClassDef cls) {
		List<Feature> features = new ArrayList<>();
		ClassDef current = cls;
		for (int guard = 0; current != null && guard < 50; guard++) {
			features.addAll(current.getFeatures());
			current = current.getSuperClass();
		}
		return features;
	}

	private static boolean isNumericFeature(Feature feature) {
		if (!(feature instanceof Attribute)) return false;
		Attribute attr = (Attribute) feature;
		if (!(attr.getType() instanceof SimpleType)) return false;
		SimpleTypeName typeName = ((SimpleType) attr.getType()).getTypeName();
		return typeName == SimpleTypeName.INT
			|| typeName == SimpleTypeName.FLOAT
			|| typeName == SimpleTypeName.ANGLE;
	}

	private static String replaceAll(String value, String pattern, String replacement) {
		return Pattern.compile(pattern).matcher(value).replaceAll(replacement);
	}

	private static String firstNonBlank(String... values) {
		if (values == null) return null;
		for (String value : values) {
			if (value != null && !value.isBlank()) return value;
		}
		return null;
	}

	/**
	 * When no class has an explicit category, automatically groups blocks by
	 * their inheritance hierarchy, creating nested categories for multi-level
	 * abstract class chains.
	 */
	private static void autoGenerateCategories(BlocklyEditorSpec spec) {
		Map<String, BlockTypeSpec> typeMap = new LinkedHashMap<>();
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			typeMap.put(bt.getTypeName(), bt);
		}

		Set<String> rootNames = new LinkedHashSet<>();
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.getCategoryName() != null) continue;
			rootNames.add(findRootAncestorName(bt, spec));
		}

		int hueStep = 360 / Math.max(1, rootNames.size());
		int hueIndex = 0;

		for (String rootName : rootNames) {
			int colour = (hueIndex * hueStep) % 360;
			hueIndex++;

			BlockTypeSpec rootBt = typeMap.get(rootName);
			if (rootBt == null) continue;

			CategorySpec rootCat = buildCategoryFromInheritance(
				rootBt, typeMap, spec, colour);
			if (rootCat != null) {
				spec.getCategories().add(rootCat);
			}
		}
	}

	private static CategorySpec buildCategoryFromInheritance(
			BlockTypeSpec bt, Map<String, BlockTypeSpec> typeMap,
			BlocklyEditorSpec spec, int colour) {

		List<BlockTypeSpec> directChildren = new ArrayList<>();
		for (BlockTypeSpec candidate : spec.getBlockTypes()) {
			if (candidate.getCategoryName() != null) continue;
			if (bt.getTypeName().equals(candidate.getSuperTypeName())) {
				directChildren.add(candidate);
			}
		}

		if (directChildren.isEmpty() && !bt.isAbstract()) {
			bt.setCategoryName(bt.getTypeName());
			CategorySpec leaf = new CategorySpec();
			leaf.setName(bt.getTypeName());
			leaf.setLabel(bt.getLabel() != null ? bt.getLabel() : bt.getTypeName());
			leaf.setColour(colour);
			return leaf;
		}

		CategorySpec cat = new CategorySpec();
		cat.setName(bt.getTypeName());
		cat.setLabel(bt.getLabel() != null ? bt.getLabel() : bt.getTypeName());
		cat.setColour(colour);

		for (BlockTypeSpec child : directChildren) {
			if (child.isAbstract()) {
				CategorySpec subCat = buildCategoryFromInheritance(
					child, typeMap, spec, colour);
				if (subCat != null) {
					cat.getChildren().add(subCat);
				}
			} else {
				child.setCategoryName(cat.getName());
			}
		}

		return cat;
	}

	private static String findRootAncestorName(BlockTypeSpec bt, BlocklyEditorSpec spec) {
		String current = bt.getSuperTypeName();
		if (current == null) return bt.getTypeName();
		for (int guard = 0; guard < 50; guard++) {
			BlockTypeSpec parent = null;
			for (BlockTypeSpec b : spec.getBlockTypes()) {
				if (b.getTypeName().equals(current)) { parent = b; break; }
			}
			if (parent == null || parent.getSuperTypeName() == null) return current;
			current = parent.getSuperTypeName();
		}
		return current;
	}

	private static String getRootAncestorName(ClassDef cls) {
		ClassDef current = cls;
		while (current.getSuperClass() != null) {
			current = current.getSuperClass();
		}
		return current.getName();
	}
}
