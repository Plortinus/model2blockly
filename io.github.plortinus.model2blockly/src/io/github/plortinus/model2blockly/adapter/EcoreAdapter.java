package io.github.plortinus.model2blockly.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import io.github.plortinus.model2blockly.blocklyspec.*;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecModelMapper;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Converts an EPackage (standard Ecore metamodel) into the platform-independent
 * BlocklyEditorSpec intermediate model.
 *
 * <h3>Zero-annotation automatic inference:</h3>
 * <ul>
 *   <li>EClass → BlockTypeSpec (type name, abstract, inheritance)</li>
 *   <li>EAttribute type → FieldSpec type (EString→TEXT, EInt→INTEGER, etc.)</li>
 *   <li>EAttribute.lowerBound ≥ 1 → required field validation</li>
 *   <li>EReference(containment) → StatementInputSpec + cardinality validation</li>
 *   <li>EReference(non-containment) → ReferenceFieldSpec (dynamic dropdown)</li>
 *   <li>EAttribute.iD / EClass.eIDAttribute → default model id, reference label, uniqueness validation</li>
 *   <li>EReference(non-containment).lowerBound ≥ 1 → required reference validation</li>
 *   <li>ETypedElement.unique → duplicate-value validation for multi-valued attributes/references</li>
 *   <li>Connection type: inferred from containment, inheritance, and output</li>
 *   <li>Root detection: classes not targeted by any containment reference</li>
 *   <li>Auto-category: when no @blockly(category) annotations exist, groups by inheritance</li>
 * </ul>
 *
 * <h3>Optional EAnnotation hints (source="blockly"):</h3>
 * <ul>
 *   <li>On EClass: message0, colour, category, tooltip, helpUrl, inputsInline, output</li>
 *   <li>On EAttribute: type (field_colour/field_angle/field_image/field_label), min, max, src, width, height, alt</li>
 *   <li>On EReference: type=input_value, check, shadow</li>
 * </ul>
 *
 * <h3>Optional EAnnotation hints (source="validation"):</h3>
 * <ul>
 *   <li>On EClass: mustFollow</li>
 * </ul>
 *
 * <h3>Optional EAnnotation hints (source="ui"):</h3>
 * <ul>
 *   <li>On EClass: label for readable block/category labels</li>
 *   <li>On EStructuralFeature: widget, label, help, placeholder, group, order, readonly, hidden</li>
 * </ul>
 */
public class EcoreAdapter {

	public static final String ANNOTATION_SOURCE = "blockly";
	public static final String UI_ANNOTATION_SOURCE = "ui";
	public static final String CODE_ANNOTATION_SOURCE = "code";
	public static final String RUNTIME_ANNOTATION_SOURCE = "runtime";
	public static final String VALIDATION_ANNOTATION_SOURCE = "validation";
	public static final String ECORE_ANNOTATION_SOURCE = "http://www.eclipse.org/emf/2002/Ecore";
	public static final String OCL_ANNOTATION_SOURCE = "http://www.eclipse.org/emf/2002/Ecore/OCL";
	public static final String OCL_PIVOT_ANNOTATION_SOURCE = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";

	public static EObject toIntermediateModel(EPackage pkg) {
		return toEditorSpec(pkg);
	}

	public static EditorSpec toEditorSpec(EPackage pkg) {
		return BlocklySpecModelMapper.toEmfSpec(fromEPackage(pkg));
	}

	public static BlocklyEditorSpec fromEPackage(EPackage pkg) {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();
		spec.setDomainName(capitalize(pkg.getName()));
		spec.setNsURI(pkg.getNsURI());
		spec.setNsPrefix(pkg.getNsPrefix());
		applyCodePackageAnnotation(pkg, spec);
		applyRuntimePackageAnnotation(pkg, spec);
		convertWorkspaceOptions(pkg, spec);

		// ── Phase 0: collect all containment-target type names ──
		// Used later to detect root containers (classes never contained by others).
		Set<String> containedTypeNames = new HashSet<>();
		for (EClassifier classifier : allClassifiers(pkg)) {
			if (classifier instanceof EClass) {
				for (EReference ref : ((EClass) classifier).getEAllReferences()) {
					if (ref.isContainment()) {
						containedTypeNames.add(ref.getEReferenceType().getName());
					}
				}
			}
		}

		Map<String, CategorySpec> categoryMap = new LinkedHashMap<>();
		List<String[]> mustFollowPairs = new ArrayList<>();
		boolean anyAnnotatedCategory = false;

		// ── Phase 1: convert each EClass ──
		for (EClassifier classifier : allClassifiers(pkg)) {
			if (classifier instanceof EClass) {
				EClass eClass = (EClass) classifier;
				BlockTypeSpec bt = convertEClass(eClass, categoryMap, containedTypeNames);
				spec.getBlockTypes().add(bt);

				if (bt.getCategoryName() != null) anyAnnotatedCategory = true;

				EAnnotation ann = eClass.getEAnnotation(VALIDATION_ANNOTATION_SOURCE);
				if (ann != null) {
					String mustFollow = ann.getDetails().get("mustFollow");
					if (mustFollow != null) {
						mustFollowPairs.add(new String[]{eClass.getName(), mustFollow});
					}
				}
				collectExpressionValidations(eClass, spec);
			}
		}

		spec.getCategories().addAll(categoryMap.values());

		// ── Phase 2: auto-generate categories from inheritance if none annotated ──
		if (!anyAnnotatedCategory) {
			autoGenerateCategories(spec);
		}

		// ── Phase 3: build validations ──
		for (String[] pair : mustFollowPairs) {
			ValidationSpec vs = new ValidationSpec();
			vs.setType(ValidationSpec.ValidationType.MUST_FOLLOW);
			vs.setName(pair[0] + "_after_" + pair[1]);
			vs.setTargetType(pair[0]);
			vs.setPredecessorType(pair[1]);
			vs.setVisualBlock(ValidationBlockSpec.previousBlockIs(pair[0], pair[1]));
			vs.setVisualExpression(ValidationExpressionSpec.previousBlockIs(pair[1]));
			spec.getValidations().add(vs);
		}

		generateCardinalityValidations(spec);
		generateRequiredValidations(spec);
		generateFieldCardinalityValidations(spec);
		generateUniqueValidations(spec);

		return spec;
	}

	// ═══════════════════════════════════════════════════════════════
	// EClass → BlockTypeSpec
	// ═══════════════════════════════════════════════════════════════

	private static BlockTypeSpec convertEClass(EClass eClass,
			Map<String, CategorySpec> categoryMap,
			Set<String> containedTypeNames) {
		BlockTypeSpec bt = new BlockTypeSpec();
		bt.setTypeName(eClass.getName());
		bt.setLabel(eClass.getName());
		bt.setAbstract(eClass.isAbstract() || eClass.isInterface());
		if (eClass.getEIDAttribute() != null) {
			bt.setIdFieldName(eClass.getEIDAttribute().getName());
		}

		boolean isOutputBlock = false;
		String outputTypeValue = null;

		EAnnotation ann = eClass.getEAnnotation(ANNOTATION_SOURCE);
		if (ann != null) {
			String message0 = ann.getDetails().get("message0");
			if (message0 != null) bt.setMessage0(message0);

			String colour = ann.getDetails().get("colour");
			if (colour != null) {
				try { bt.setColour(Integer.parseInt(colour)); }
				catch (NumberFormatException ignored) {}
			}

			String category = ann.getDetails().get("category");
			if (category != null) {
				int catColour = bt.getColour();

				if (category.contains("/")) {
					// Nested category path: "Parent/Child/Grandchild"
					String[] parts = category.split("/");
					String leafName = parts[parts.length - 1];
					bt.setCategoryName(leafName);
					resolveNestedCategory(categoryMap, parts, catColour);
				} else {
					bt.setCategoryName(category);
					if (!categoryMap.containsKey(category)) {
						CategorySpec cs = new CategorySpec();
						cs.setName(category);
						cs.setLabel(category);
						cs.setColour(catColour);
						categoryMap.put(category, cs);
					}
				}
			}

			String output = ann.getDetails().get("output");
			if (output != null) {
				isOutputBlock = true;
				outputTypeValue = "true".equalsIgnoreCase(output) ? null : output;
			}

			String inputsInline = ann.getDetails().get("inputsInline");
			if ("true".equalsIgnoreCase(inputsInline)) {
				bt.setInputsInline(Boolean.TRUE);
			} else if ("false".equalsIgnoreCase(inputsInline)) {
				bt.setInputsInline(Boolean.FALSE);
			}

			String tooltip = ann.getDetails().get("tooltip");
			if (tooltip != null) bt.setTooltip(tooltip);

			String helpUrl = ann.getDetails().get("helpUrl");
			if (helpUrl != null) bt.setHelpUrl(helpUrl);
		}

		EAnnotation uiClassAnn = eClass.getEAnnotation(UI_ANNOTATION_SOURCE);
		if (uiClassAnn != null) {
			String label = uiClassAnn.getDetails().get("label");
			if (label != null && !label.isBlank()) {
				bt.setLabel(label);
			}
		}

		EAnnotation codeAnn = eClass.getEAnnotation(CODE_ANNOTATION_SOURCE);
		if (codeAnn != null) {
			String template = codeAnn.getDetails().get("template");
			if (template == null) template = codeAnn.getDetails().get("codeTemplate");
			bt.setCodeTemplate(template);
		}

		if (!eClass.getESuperTypes().isEmpty()) {
			bt.setSuperTypeName(eClass.getESuperTypes().get(0).getName());
		}

		boolean hasContainments = false;

		// ── Structural features: iterate in declaration order ──
		// This preserves the original order (e.g., left → operator → right)
		// instead of grouping all attributes before all references.
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (!isEditableFeature(feature)) {
				continue;
			}
			if (feature instanceof EAttribute) {
				EAttribute eAttr = (EAttribute) feature;
				FieldSpec fs = convertEAttribute(eAttr);
				if (fs != null) {
					bt.getFields().add(fs);
					bt.getOrderedInputNames().add(fs.getName());
				}
			} else if (feature instanceof EReference) {
				EReference eRef = (EReference) feature;
				EAnnotation refAnn = eRef.getEAnnotation(ANNOTATION_SOURCE);
				boolean inputValue = refAnn != null
					&& "input_value".equalsIgnoreCase(refAnn.getDetails().get("type"));

				if (inputValue) {
					ValueInputSpec vis = new ValueInputSpec();
					vis.setName(eRef.getName());
					String check = refAnn.getDetails().get("check");
					vis.setCheckType(check != null && !check.isBlank()
						? check
						: eRef.getEReferenceType().getName());
					String shadow = refAnn.getDetails().get("shadow");
					if (shadow != null) vis.setShadowBlockType(shadow);
					applyUiAnnotation(eRef.getEAnnotation(UI_ANNOTATION_SOURCE), vis);
					bt.getValueInputs().add(vis);
					bt.getOrderedInputNames().add(vis.getName());
				} else if (eRef.isContainment()) {
					hasContainments = true;
					StatementInputSpec sis = new StatementInputSpec();
					sis.setName(eRef.getName());
					String check = refAnn != null ? refAnn.getDetails().get("check") : null;
					sis.setCheckType(check != null && !check.isBlank()
						? check
						: eRef.getEReferenceType().getName());
					sis.setLowerBound(eRef.getLowerBound());
					sis.setUpperBound(eRef.getUpperBound() == -1 ? 0 : eRef.getUpperBound());
					applyUiAnnotation(eRef.getEAnnotation(UI_ANNOTATION_SOURCE), sis);
					bt.getStatementInputs().add(sis);
					bt.getOrderedInputNames().add(sis.getName());
				} else {
					ReferenceFieldSpec rfs = new ReferenceFieldSpec();
					rfs.setName(eRef.getName());
					rfs.setTargetTypeName(eRef.getEReferenceType().getName());
					rfs.setLowerBound(eRef.getLowerBound());
					rfs.setUpperBound(normalizeUpperBound(eRef.getUpperBound()));
					rfs.setUnique(eRef.isUnique());
					rfs.setOrdered(eRef.isOrdered());
					if (eRef.getEOpposite() != null) {
						rfs.setOppositeName(eRef.getEOpposite().getName());
					}
					if (eRef.getLowerBound() >= 1) {
						rfs.setRequired(true);
					}
					applyUiAnnotation(eRef.getEAnnotation(UI_ANNOTATION_SOURCE), rfs);
					if (rfs.getReferenceLabelField() == null) {
						rfs.setReferenceLabelField(defaultReferenceLabelField(eRef.getEReferenceType()));
					}
					bt.getReferenceFields().add(rfs);
					bt.getOrderedInputNames().add(rfs.getName());
				}
			}
		}

		// ── Connection type inference (improved root detection) ──
		if (isOutputBlock) {
			if (outputTypeValue != null) {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
				bt.setOutputType(outputTypeValue);
			} else if (!eClass.getESuperTypes().isEmpty()) {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
				bt.setOutputType(getRootAncestorName(eClass));
			} else {
				bt.setConnectionType(BlockTypeSpec.ConnectionType.OUTPUT);
				bt.setOutputType(eClass.getName());
			}
		} else if (hasContainments && eClass.getESuperTypes().isEmpty()
				   && !containedTypeNames.contains(eClass.getName())) {
			// Root container: has containments + no supertype + NOT contained by others → NONE
			bt.setConnectionType(BlockTypeSpec.ConnectionType.NONE);
		} else if (!eClass.getESuperTypes().isEmpty()) {
			bt.setConnectionType(BlockTypeSpec.ConnectionType.TYPED);
			bt.setConnectionTypeName(getRootAncestorName(eClass));
		} else if (!containedTypeNames.contains(eClass.getName()) && hasContainments == false) {
			// Not contained by anyone, no containments of its own, no supertype → FREE
			bt.setConnectionType(BlockTypeSpec.ConnectionType.FREE);
		} else if (containedTypeNames.contains(eClass.getName())) {
			// Contained by others but no supertype → TYPED with own name
			// e.g., Script is contained by Sprite.scripts → TYPED("Script")
			bt.setConnectionType(BlockTypeSpec.ConnectionType.TYPED);
			bt.setConnectionTypeName(eClass.getName());
		} else {
			bt.setConnectionType(BlockTypeSpec.ConnectionType.FREE);
		}

		return bt;
	}

	// ═══════════════════════════════════════════════════════════════
	// EAttribute → FieldSpec (with required detection)
	// ═══════════════════════════════════════════════════════════════

	private static FieldSpec convertEAttribute(EAttribute eAttr) {
		FieldSpec fs = new FieldSpec();
		fs.setName(eAttr.getName());
		fs.setLowerBound(eAttr.getLowerBound());
		fs.setUpperBound(normalizeUpperBound(eAttr.getUpperBound()));
		fs.setUnique(eAttr.isUnique());
		fs.setOrdered(eAttr.isOrdered());
		fs.setId(eAttr.isID());

		EDataType type = eAttr.getEAttributeType();

		if (type instanceof EEnum) {
			EEnum eEnum = (EEnum) type;
			fs.setFieldType(FieldSpec.FieldType.DROPDOWN);
			for (EEnumLiteral lit : eEnum.getELiterals()) {
				fs.getOptions().add(new DropdownOption(
					lit.getLiteral(),
					lit.getName()
				));
			}
		} else {
			String typeName = type.getName();
			switch (typeName) {
				case "EString": case "EChar":
					fs.setFieldType(FieldSpec.FieldType.TEXT); break;
				case "EInt": case "ELong": case "EShort": case "EBigInteger":
					fs.setFieldType(FieldSpec.FieldType.INTEGER); break;
				case "EFloat": case "EDouble": case "EBigDecimal":
					fs.setFieldType(FieldSpec.FieldType.FLOAT); break;
				case "EBoolean":
					fs.setFieldType(FieldSpec.FieldType.BOOLEAN); break;
				default:
					fs.setFieldType(FieldSpec.FieldType.TEXT); break;
			}
		}

		if (eAttr.getDefaultValueLiteral() != null) {
			fs.setDefaultValue(eAttr.getDefaultValueLiteral());
		}

		// Auto-detect required from Ecore lowerBound (zero-annotation)
		if (eAttr.getLowerBound() >= 1) {
			fs.setRequired(true);
		}

		EAnnotation ann = eAttr.getEAnnotation(ANNOTATION_SOURCE);
		if (ann != null) {
			String fieldType = ann.getDetails().get("type");
			if ("field_colour".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.COLOUR);
			} else if ("field_angle".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.ANGLE);
			} else if ("field_image".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.IMAGE);
				String url = ann.getDetails().get("src");
				if (url != null) fs.setImageUrl(url);
				String w = ann.getDetails().get("width");
				if (w != null) try { fs.setImageWidth(Integer.parseInt(w)); } catch (NumberFormatException ignored) {}
				String h = ann.getDetails().get("height");
				if (h != null) try { fs.setImageHeight(Integer.parseInt(h)); } catch (NumberFormatException ignored) {}
				String alt = ann.getDetails().get("alt");
				if (alt != null) fs.setImageAlt(alt);
			} else if ("field_label".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.LABEL);
			} else if ("field_input".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.TEXT);
			} else if ("field_number".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.FLOAT);
			} else if ("field_checkbox".equalsIgnoreCase(fieldType)) {
				fs.setFieldType(FieldSpec.FieldType.BOOLEAN);
			}
			String min = ann.getDetails().get("min");
			if (min != null) fs.setMin(min);
			String max = ann.getDetails().get("max");
			if (max != null) fs.setMax(max);
		}
		applyUiAnnotation(eAttr.getEAnnotation(UI_ANNOTATION_SOURCE), fs);

		if (fs.isMany()) {
			fs.setFieldType(FieldSpec.FieldType.TEXT);
		}

		return fs;
	}

	private static void applyUiAnnotation(EAnnotation ann, FieldSpec fs) {
		if (ann == null) return;
		EMap<String, String> d = ann.getDetails();
		fs.setUiWidget(d.get("widget"));
		fs.setUiLabel(d.get("label"));
		fs.setUiHelp(d.get("help"));
		fs.setUiPlaceholder(d.get("placeholder"));
		fs.setUiGroup(d.get("group"));
		fs.setUiVariant(d.get("variant"));
		fs.setUiReadonly(parseBoolean(d.get("readonly")));
		fs.setUiHidden(parseBoolean(d.get("hidden")));
		fs.setUiOrder(parseInteger(d.get("order")));
	}

	private static void applyUiAnnotation(EAnnotation ann, ReferenceFieldSpec fs) {
		if (ann == null) return;
		EMap<String, String> d = ann.getDetails();
		fs.setUiWidget(d.get("widget"));
		fs.setUiLabel(d.get("label"));
		fs.setUiHelp(d.get("help"));
		fs.setUiPlaceholder(d.get("placeholder"));
		fs.setUiGroup(d.get("group"));
		fs.setUiVariant(d.get("variant"));
		fs.setUiReadonly(parseBoolean(d.get("readonly")));
		fs.setUiHidden(parseBoolean(d.get("hidden")));
		fs.setUiOrder(parseInteger(d.get("order")));
		fs.setReferenceLabelField(d.get("referenceLabelField"));
	}

	private static void applyUiAnnotation(EAnnotation ann, ValueInputSpec fs) {
		if (ann == null) return;
		EMap<String, String> d = ann.getDetails();
		fs.setUiWidget(d.get("widget"));
		fs.setUiLabel(d.get("label"));
		fs.setUiHelp(d.get("help"));
		fs.setUiPlaceholder(d.get("placeholder"));
		fs.setUiGroup(d.get("group"));
		fs.setUiVariant(d.get("variant"));
		fs.setUiReadonly(parseBoolean(d.get("readonly")));
		fs.setUiHidden(parseBoolean(d.get("hidden")));
		fs.setUiOrder(parseInteger(d.get("order")));
	}

	private static void applyUiAnnotation(EAnnotation ann, StatementInputSpec fs) {
		if (ann == null) return;
		EMap<String, String> d = ann.getDetails();
		fs.setUiWidget(d.get("widget"));
		fs.setUiLabel(d.get("label"));
		fs.setUiHelp(d.get("help"));
		fs.setUiPlaceholder(d.get("placeholder"));
		fs.setUiGroup(d.get("group"));
		fs.setUiVariant(d.get("variant"));
		fs.setUiReadonly(parseBoolean(d.get("readonly")));
		fs.setUiHidden(parseBoolean(d.get("hidden")));
		fs.setUiOrder(parseInteger(d.get("order")));
	}

	private static boolean parseBoolean(String value) {
		return "true".equalsIgnoreCase(value);
	}

	private static Integer parseInteger(String value) {
		if (value == null || value.isBlank()) return null;
		try { return Integer.valueOf(value); }
		catch (NumberFormatException ignored) { return null; }
	}

	// ═══════════════════════════════════════════════════════════════
	// Auto-generate categories from inheritance hierarchy
	// ═══════════════════════════════════════════════════════════════

	/**
	 * When no EClass has a @blockly(category) annotation, automatically groups
	 * blocks by their inheritance hierarchy, creating nested categories for
	 * multi-level abstract class chains.
	 *
	 * Example:  Statement (abstract)
	 *             ├── MovementAction (abstract)
	 *             │     ├── MoveSteps (concrete)
	 *             │     └── TurnRight (concrete)
	 *             └── Say (concrete)
	 *
	 * Produces:  Category "Statement"
	 *              ├── Sub-category "MovementAction"
	 *              │     ├── block MoveSteps
	 *              │     └── block TurnRight
	 *              └── block Say
	 */
	private static void autoGenerateCategories(BlocklyEditorSpec spec) {
		// Build a lookup: typeName → BlockTypeSpec
		Map<String, BlockTypeSpec> typeMap = new LinkedHashMap<>();
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			typeMap.put(bt.getTypeName(), bt);
		}

		// Collect root abstract types (abstract classes with no supertype, or
		// whose supertype is not in the model)
		Set<String> rootNames = new LinkedHashSet<>();
		for (BlockTypeSpec bt : spec.getBlockTypes()) {
			if (bt.getCategoryName() != null) continue;
			String rootName = findRootAncestorName(bt, spec);
			rootNames.add(rootName);
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

	/**
	 * Recursively builds a CategorySpec tree from the inheritance hierarchy.
	 * - Abstract classes with concrete descendants become categories/sub-categories.
	 * - Concrete classes become blocks assigned to their direct parent category.
	 */
	private static CategorySpec buildCategoryFromInheritance(
			BlockTypeSpec bt, Map<String, BlockTypeSpec> typeMap,
			BlocklyEditorSpec spec, int colour) {

		// Find all direct children (types whose superTypeName == bt.typeName)
		List<BlockTypeSpec> directChildren = new ArrayList<>();
		for (BlockTypeSpec candidate : spec.getBlockTypes()) {
			if (candidate.getCategoryName() != null) continue;
			if (bt.getTypeName().equals(candidate.getSuperTypeName())) {
				directChildren.add(candidate);
			}
		}

		// If this type has no children at all, it's a standalone concrete block
		if (directChildren.isEmpty() && !bt.isAbstract()) {
			bt.setCategoryName(bt.getTypeName());
			CategorySpec leaf = new CategorySpec();
			leaf.setName(bt.getTypeName());
			leaf.setLabel(bt.getLabel() != null ? bt.getLabel() : bt.getTypeName());
			leaf.setColour(colour);
			return leaf;
		}

		// This type has children → it becomes a category
		CategorySpec cat = new CategorySpec();
		cat.setName(bt.getTypeName());
		cat.setLabel(bt.getLabel() != null ? bt.getLabel() : bt.getTypeName());
		cat.setColour(colour);

		boolean hasAbstractChildren = false;

		for (BlockTypeSpec child : directChildren) {
			if (child.isAbstract()) {
				// Abstract child with its own descendants → sub-category (recurse)
				hasAbstractChildren = true;
				CategorySpec subCat = buildCategoryFromInheritance(
					child, typeMap, spec, colour);
				if (subCat != null) {
					cat.getChildren().add(subCat);
				}
			} else {
				// Concrete child → assign to this category
				child.setCategoryName(cat.getName());
			}
		}

		// If this abstract class also has concrete direct children AND abstract
		// children, the concrete ones are already assigned to cat.getName()
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

	// ═══════════════════════════════════════════════════════════════
	// Validation generation
	// ═══════════════════════════════════════════════════════════════

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
	 * Auto-generates REQUIRED validations from FieldSpec.required and
	 * ReferenceFieldSpec.required (inferred from Ecore lowerBound ≥ 1).
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

	private static void collectExpressionValidations(EClass eClass, BlocklyEditorSpec spec) {
		EAnnotation validationAnn = eClass.getEAnnotation(VALIDATION_ANNOTATION_SOURCE);
		if (validationAnn != null) {
			EMap<String, String> details = validationAnn.getDetails();
			addValidationExpression(eClass, spec, "validation", firstNonBlank(
				details.get("expression"),
				details.get("condition"),
				details.get("js")), details.get("message"));

			for (Map.Entry<String, String> entry : details.entrySet()) {
				String key = entry.getKey();
				if (key == null) continue;
				if (key.startsWith("expression.") || key.startsWith("condition.") || key.startsWith("js.")) {
					String name = key.substring(key.indexOf('.') + 1);
					addValidationExpression(eClass, spec, name, entry.getValue(),
						firstNonBlank(details.get("message." + name), details.get("message")));
				} else if ("ocl".equals(key)) {
					addOclValidationExpression(eClass, spec, "ocl", entry.getValue(), details.get("message"));
				} else if (key.startsWith("ocl.")) {
					String name = key.substring("ocl.".length());
					addOclValidationExpression(eClass, spec, name, entry.getValue(),
						firstNonBlank(details.get("message." + name), details.get("message")));
				}
			}
		}

		EAnnotation ecoreAnn = eClass.getEAnnotation(ECORE_ANNOTATION_SOURCE);
		EAnnotation oclAnn = firstAnnotation(eClass, OCL_PIVOT_ANNOTATION_SOURCE, OCL_ANNOTATION_SOURCE);
		if (oclAnn == null) return;

		Set<String> declaredNames = new LinkedHashSet<>();
		if (ecoreAnn != null) {
			String constraints = ecoreAnn.getDetails().get("constraints");
			if (constraints != null) {
				for (String name : constraints.split("[,\\s]+")) {
					if (!name.isBlank()) declaredNames.add(name.trim());
				}
			}
		}

		if (declaredNames.isEmpty()) {
			for (Map.Entry<String, String> entry : oclAnn.getDetails().entrySet()) {
				addOclValidationExpression(eClass, spec, entry.getKey(), entry.getValue(), null);
			}
		} else {
			for (String name : declaredNames) {
				addOclValidationExpression(eClass, spec, name, oclAnn.getDetails().get(name), null);
			}
		}
	}

	private static void addOclValidationExpression(EClass eClass, BlocklyEditorSpec spec,
			String name, String oclExpression, String message) {
		String expression = translateBasicOclExpression(eClass, oclExpression);
		if (expression != null) {
			addValidationExpression(eClass, spec, name, expression, message);
		} else {
			addUnsupportedOclValidation(eClass, spec, name, oclExpression);
		}
	}

	private static void addUnsupportedOclValidation(EClass eClass, BlocklyEditorSpec spec,
			String name, String expression) {
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName(eClass.getName() + "_" + (name == null || name.isBlank() ? "ocl" : name));
		vs.setOwnerType(eClass.getName());
		vs.setDiagnosticMessage("Unsupported OCL validation '" + (name == null || name.isBlank() ? "ocl" : name)
			+ "'. Supported subset: self.<feature>, size(), notEmpty(), isEmpty(), oclIsUndefined(), "
			+ "comparisons, and/or/not. Expression: " + firstNonBlank(expression, "<empty>"));
		spec.getValidations().add(vs);
	}

	private static void addValidationExpression(EClass eClass, BlocklyEditorSpec spec,
			String name, String expression, String message) {
		if (expression == null || expression.isBlank()) return;
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName(eClass.getName() + "_" + (name == null || name.isBlank() ? "validation" : name));
		vs.setOwnerType(eClass.getName());
		String trimmed = expression.trim();
		vs.setExpression(trimmed);
		ValidationExpressionSpec visualExpression = ValidationExpressionParser
			.parse(trimmed, fieldName -> sizeAccessorExpression(eClass, fieldName))
			.orElse(null);
		vs.setVisualExpression(visualExpression);
		vs.setVisualBlock(ValidationBlockSpec.expressionRule(eClass.getName(), trimmed, visualExpression));
		vs.setMessage(firstNonBlank(message,
			"Constraint \"" + (name == null || name.isBlank() ? "validation" : name)
				+ "\" is violated on " + eClass.getName() + "."));
		spec.getValidations().add(vs);
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

	private static ValidationExpressionSpec sizeAccessorExpression(EClass eClass, String fieldName) {
		EStructuralFeature feature = findFeature(eClass, fieldName);
		if (feature instanceof EReference && ((EReference) feature).isContainment()) {
			return ValidationExpressionSpec.inputCount(fieldName);
		}
		return ValidationExpressionSpec.fieldCount(fieldName);
	}

	private static EStructuralFeature findFeature(EClass eClass, String fieldName) {
		if (eClass == null || fieldName == null) return null;
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (fieldName.equals(feature.getName())) return feature;
		}
		return null;
	}

	/**
	 * Small OCL subset for browser-side validation generation.
	 *
	 * Supported examples:
	 * - self.name->notEmpty()
	 * - self.tasks->size() >= 1
	 * - self.estimateHours >= 1 and self.estimateHours <= 80
	 * - self.state <> ''
	 */
	private static String translateBasicOclExpression(EClass eClass, String oclExpression) {
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

		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
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

	private static boolean isNumericFeature(EStructuralFeature feature) {
		if (!(feature instanceof EAttribute)) return false;
		EDataType type = ((EAttribute) feature).getEAttributeType();
		if (type == null || type.getName() == null) return false;
		switch (type.getName()) {
			case "EInt":
			case "ELong":
			case "EShort":
			case "EBigInteger":
			case "EFloat":
			case "EDouble":
			case "EBigDecimal":
				return true;
			default:
				return false;
		}
	}

	private static String replaceAll(String value, String pattern, String replacement) {
		return Pattern.compile(pattern).matcher(value).replaceAll(replacement);
	}

	private static EAnnotation firstAnnotation(EClass eClass, String... sources) {
		for (String source : sources) {
			EAnnotation ann = eClass.getEAnnotation(source);
			if (ann != null) return ann;
		}
		return null;
	}

	private static String firstNonBlank(String... values) {
		if (values == null) return null;
		for (String value : values) {
			if (value != null && !value.isBlank()) return value;
		}
		return null;
	}

	private static String defaultReferenceLabelField(EClass targetType) {
		if (targetType == null) return null;
		EAttribute id = targetType.getEIDAttribute();
		if (id != null) return id.getName();
		for (String candidate : new String[] {"displayName", "title", "name"}) {
			if (targetType.getEAllAttributes().stream().anyMatch(attr -> candidate.equals(attr.getName()))) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * Resolves a nested category path like ["Control", "Loop"] into the categoryMap.
	 * Ensures the top-level category exists in the map, and creates child hierarchy.
	 */
	private static void resolveNestedCategory(Map<String, CategorySpec> categoryMap,
			String[] parts, int colour) {
		// Ensure root exists
		String rootName = parts[0];
		if (!categoryMap.containsKey(rootName)) {
			CategorySpec root = new CategorySpec();
			root.setName(rootName);
			root.setLabel(rootName);
			root.setColour(colour);
			categoryMap.put(rootName, root);
		}
		// Walk down the path, creating children as needed
		CategorySpec current = categoryMap.get(rootName);
		for (int i = 1; i < parts.length; i++) {
			CategorySpec child = current.getOrCreateChild(parts[i]);
			child.setColour(colour);
			current = child;
		}
	}

	// ═══════════════════════════════════════════════════════════════
	// Utilities
	// ═══════════════════════════════════════════════════════════════

	private static String getRootAncestorName(EClass eClass) {
		EClass current = eClass;
		while (!current.getESuperTypes().isEmpty()) {
			current = current.getESuperTypes().get(0);
		}
		return current.getName();
	}

	private static int normalizeUpperBound(int upperBound) {
		return upperBound == -1 ? 0 : upperBound;
	}

	private static boolean isEditableFeature(EStructuralFeature feature) {
		return !feature.isDerived()
			&& !feature.isTransient()
			&& !feature.isVolatile()
			&& feature.isChangeable();
	}

	private static List<EClassifier> allClassifiers(EPackage pkg) {
		List<EClassifier> result = new ArrayList<>();
		collectClassifiers(pkg, result);
		return result;
	}

	private static void collectClassifiers(EPackage pkg, List<EClassifier> result) {
		if (pkg == null) return;
		result.addAll(pkg.getEClassifiers());
		for (EPackage sub : pkg.getESubpackages()) {
			collectClassifiers(sub, result);
		}
	}

	private static String capitalize(String s) {
		if (s == null || s.isEmpty()) return s;
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	private static void applyCodePackageAnnotation(EPackage pkg, BlocklyEditorSpec spec) {
		EAnnotation ann = pkg.getEAnnotation(CODE_ANNOTATION_SOURCE);
		if (ann == null) return;
		spec.setCodeLanguage(ann.getDetails().get("language"));
		spec.setCodeFileExtension(ann.getDetails().get("fileExtension"));
	}

	private static void applyRuntimePackageAnnotation(EPackage pkg, BlocklyEditorSpec spec) {
		EAnnotation ann = pkg.getEAnnotation(RUNTIME_ANNOTATION_SOURCE);
		if (ann == null) return;
		spec.setRuntimeKind(ann.getDetails().get("kind"));
	}

	/**
	 * Workspace options from EPackage annotation source="blockly".
	 *
	 * Generic keys: workspace.<keyPath>=<value>, e.g.
	 *   workspace.renderer=zelos
	 *   workspace.zoom.controls=true
	 *   workspace.zoom.maxScale=5
	 *   workspace.grid.spacing=25
	 *   workspace.grid.snap=true
	 */
	private static void convertWorkspaceOptions(EPackage pkg, BlocklyEditorSpec spec) {
		EAnnotation ann = pkg.getEAnnotation(ANNOTATION_SOURCE);
		if (ann == null) return;

		// Read toolboxType as a dedicated field (not a Blockly inject option)
		String toolboxType = ann.getDetails().get("workspace.toolboxType");
		if (toolboxType != null && !toolboxType.trim().isEmpty()) {
			spec.setToolboxType(toolboxType.trim());
		}

		Map<String, Object> options = new LinkedHashMap<>(spec.getWorkspaceOptions());
		boolean hasWorkspaceOption = false;
		for (Map.Entry<String, String> detail : ann.getDetails().entrySet()) {
			String rawKey = detail.getKey();
			if (rawKey == null || !rawKey.startsWith("workspace.")) continue;
			String keyPath = rawKey.substring("workspace.".length()).trim();
			if (keyPath.isEmpty() || "toolboxType".equals(keyPath)) continue;
			putNestedWorkspaceOption(options, keyPath, parseWorkspaceLiteral(detail.getValue()));
			hasWorkspaceOption = true;
		}
		if (hasWorkspaceOption) {
			spec.setWorkspaceOptions(options);
		}
	}

	@SuppressWarnings("unchecked")
	private static void putNestedWorkspaceOption(Map<String, Object> root, String keyPath, Object value) {
		String[] parts = keyPath.split("\\.");
		Map<String, Object> current = root;
		for (int i = 0; i < parts.length - 1; i++) {
			String part = parts[i];
			Object next = current.get(part);
			if (!(next instanceof Map<?, ?>)) {
				Map<String, Object> child = new LinkedHashMap<>();
				current.put(part, child);
				next = child;
			}
			current = (Map<String, Object>) next;
		}
		current.put(parts[parts.length - 1], value);
	}

	private static Object parseWorkspaceLiteral(String raw) {
		if (raw == null) return null;
		String text = raw.trim();
		if (text.isEmpty()) return "";

		if ("true".equalsIgnoreCase(text)) return Boolean.TRUE;
		if ("false".equalsIgnoreCase(text)) return Boolean.FALSE;

		try { return Integer.valueOf(text); }
		catch (NumberFormatException ignored) {}
		try { return Double.valueOf(text); }
		catch (NumberFormatException ignored) {}

		if ((text.startsWith("'") && text.endsWith("'")) || (text.startsWith("\"") && text.endsWith("\""))) {
			return text.substring(1, text.length() - 1);
		}
		return text;
	}
}
