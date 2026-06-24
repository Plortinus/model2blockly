package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.*;
import io.github.plortinus.model2blockly.adapter.EcoreAdapter;
import io.github.plortinus.model2blockly.blocklyspec.*;

/**
 * Tests for EcoreAdapter: converting programmatic EPackage models
 * to BlocklyEditorSpec. Verifies all mapping rules including new features.
 *
 * IMPORTANT: This test uses EMF Ecore API (org.eclipse.emf.ecore), which is
 * provided as an OSGi bundle. It MUST be run as a "JUnit Plug-in Test":
 *   Right-click → Run As → JUnit Plug-in Test
 *
 * Running as a plain "JUnit Test" will fail with NoClassDefFoundError
 * because EMF classes are not on the standard classpath.
 */
class EcoreAdapterTest {

	private EcoreFactory f;
	private EcorePackage p;

	@BeforeEach
	void setUp() {
		f = EcoreFactory.eINSTANCE;
		p = EcorePackage.eINSTANCE;
	}

	private EPackage makePackage(String name) {
		EPackage pkg = f.createEPackage();
		pkg.setName(name);
		pkg.setNsURI("http://test.org/" + name);
		pkg.setNsPrefix(name);
		return pkg;
	}

	private EClass makeClass(EPackage pkg, String name) {
		EClass cls = f.createEClass();
		cls.setName(name);
		pkg.getEClassifiers().add(cls);
		return cls;
	}

	private EAttribute makeAttribute(EClass cls, String name, EDataType type) {
		EAttribute attr = f.createEAttribute();
		attr.setName(name);
		attr.setEType(type);
		cls.getEStructuralFeatures().add(attr);
		return attr;
	}

	private EReference makeReference(EClass cls, String name, EClass target,
			boolean containment) {
		EReference ref = f.createEReference();
		ref.setName(name);
		ref.setEType(target);
		ref.setContainment(containment);
		cls.getEStructuralFeatures().add(ref);
		return ref;
	}

	private EAnnotation blocklyAnnotation(String... keyValues) {
		EAnnotation ann = f.createEAnnotation();
		ann.setSource("blockly");
		for (int i = 0; i < keyValues.length; i += 2) {
			ann.getDetails().put(keyValues[i], keyValues[i + 1]);
		}
		return ann;
	}

	private EAnnotation uiAnnotation(String... keyValues) {
		EAnnotation ann = f.createEAnnotation();
		ann.setSource("ui");
		for (int i = 0; i < keyValues.length; i += 2) {
			ann.getDetails().put(keyValues[i], keyValues[i + 1]);
		}
		return ann;
	}

	private EAnnotation validationAnnotation(String... keyValues) {
		EAnnotation ann = f.createEAnnotation();
		ann.setSource("validation");
		for (int i = 0; i < keyValues.length; i += 2) {
			ann.getDetails().put(keyValues[i], keyValues[i + 1]);
		}
		return ann;
	}

	private EAnnotation codeAnnotation(String... keyValues) {
		EAnnotation ann = f.createEAnnotation();
		ann.setSource("code");
		for (int i = 0; i < keyValues.length; i += 2) {
			ann.getDetails().put(keyValues[i], keyValues[i + 1]);
		}
		return ann;
	}

	// ═══════════════════════════════════════════════════════════════
	// 1. BASIC CONVERSION
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EPackage name becomes capitalized domain name")
	void testDomainName() {
		EPackage pkg = makePackage("sample");
		makeClass(pkg, "Task");

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertEquals("Sample", spec.getDomainName());
		assertEquals("http://test.org/sample", spec.getNsURI());
		assertEquals("sample", spec.getNsPrefix());
	}

	@Test
	@DisplayName("EClass becomes BlockTypeSpec")
	void testClassToBlockType() {
		EPackage pkg = makePackage("test");
		makeClass(pkg, "MyClass");

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertEquals(1, spec.getBlockTypes().size());
		assertEquals("MyClass", spec.getBlockTypes().get(0).getTypeName());
	}

	@Test
	@DisplayName("Code annotations map to code generation metadata")
	void testCodeAnnotationMapping() {
		EPackage pkg = makePackage("robot");
		pkg.getEAnnotations().add(codeAnnotation("language", "javascript", "fileExtension", "js"));
		EClass move = makeClass(pkg, "Move");
		move.getEAnnotations().add(codeAnnotation("template", "move({{steps}});"));
		makeAttribute(move, "steps", p.getEInt());

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);

		assertEquals("javascript", spec.getCodeLanguage());
		assertEquals("js", spec.getCodeFileExtension());
		assertEquals("move({{steps}});", spec.getBlockTypes().get(0).getCodeTemplate());
	}

	@Test
	@DisplayName("Abstract EClass becomes abstract BlockTypeSpec")
	void testAbstractClass() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Base");
		cls.setAbstract(true);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertTrue(spec.getBlockTypes().get(0).isAbstract());
		assertEquals(0, spec.getConcreteBlockTypes().size());
	}

	// ═══════════════════════════════════════════════════════════════
	// 2. ATTRIBUTE MAPPING
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EString attribute → TEXT field")
	void testStringAttribute() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		makeAttribute(cls, "name", p.getEString());

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		FieldSpec fs = spec.getBlockTypes().get(0).getFields().get(0);
		assertEquals("name", fs.getName());
		assertEquals(FieldSpec.FieldType.TEXT, fs.getFieldType());
	}

	@Test
	@DisplayName("EInt attribute → INTEGER field")
	void testIntAttribute() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		makeAttribute(cls, "count", p.getEInt());

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.INTEGER, fs.getFieldType());
	}

	@Test
	@DisplayName("EFloat attribute → FLOAT field")
	void testFloatAttribute() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		makeAttribute(cls, "rate", p.getEFloat());

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.FLOAT, fs.getFieldType());
	}

	@Test
	@DisplayName("EBoolean attribute → BOOLEAN field")
	void testBoolAttribute() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		makeAttribute(cls, "active", p.getEBoolean());

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.BOOLEAN, fs.getFieldType());
	}

	@Test
	@DisplayName("EEnum attribute → DROPDOWN field with options")
	void testEnumAttribute() {
		EPackage pkg = makePackage("test");
		EEnum eEnum = f.createEEnum();
		eEnum.setName("Priority");
		EEnumLiteral high = f.createEEnumLiteral();
		high.setName("HIGH");
		high.setLiteral("high");
		EEnumLiteral low = f.createEEnumLiteral();
		low.setName("LOW");
		low.setLiteral("low");
		eEnum.getELiterals().add(high);
		eEnum.getELiterals().add(low);
		pkg.getEClassifiers().add(eEnum);

		EClass cls = makeClass(pkg, "Task");
		makeAttribute(cls, "priority", eEnum);

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.DROPDOWN, fs.getFieldType());
		assertEquals(2, fs.getOptions().size());
		assertEquals("high", fs.getOptions().get(0).getValue());
		assertEquals("HIGH", fs.getOptions().get(0).getLabel());
	}

	// ═══════════════════════════════════════════════════════════════
	// 3. Blockly field type annotation override
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EAnnotation type=field_colour → COLOUR field")
	void testColourAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		EAttribute attr = makeAttribute(cls, "bg", p.getEString());
		attr.getEAnnotations().add(blocklyAnnotation("type", "field_colour"));

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.COLOUR, fs.getFieldType());
	}

	@Test
	@DisplayName("EAnnotation type=field_angle → ANGLE field")
	void testAngleAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		EAttribute attr = makeAttribute(cls, "dir", p.getEInt());
		attr.getEAnnotations().add(blocklyAnnotation("type", "field_angle"));

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.ANGLE, fs.getFieldType());
	}

	@Test
	@DisplayName("EAnnotation type=field_image → IMAGE field with URL/dimensions")
	void testImageAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		EAttribute attr = makeAttribute(cls, "icon", p.getEString());
		attr.getEAnnotations().add(blocklyAnnotation(
				"type", "field_image",
				"src", "https://example.com/icon.png",
				"width", "32",
				"height", "32"));

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals(FieldSpec.FieldType.IMAGE, fs.getFieldType());
		assertEquals("https://example.com/icon.png", fs.getImageUrl());
		assertEquals(32, fs.getImageWidth());
		assertEquals(32, fs.getImageHeight());
	}

	@Test
	@DisplayName("EAnnotation min/max on attribute")
	void testMinMaxAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		EAttribute attr = makeAttribute(cls, "speed", p.getEInt());
		attr.getEAnnotations().add(blocklyAnnotation("min", "0", "max", "100"));

		FieldSpec fs = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0).getFields().get(0);
		assertEquals("0", fs.getMin());
		assertEquals("100", fs.getMax());
	}

	// ═══════════════════════════════════════════════════════════════
	// 4. REFERENCE MAPPING
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Containment EReference → StatementInputSpec")
	void testContainmentRef() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass child = makeClass(pkg, "Child");
		EReference ref = makeReference(parent, "children", child, true);
		ref.setUpperBound(-1);

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(1, bt.getStatementInputs().size());
		assertEquals("children", bt.getStatementInputs().get(0).getName());
		assertEquals("Child", bt.getStatementInputs().get(0).getCheckType());
	}

	@Test
	@DisplayName("Non-containment EReference → ReferenceFieldSpec")
	void testNonContainmentRef() {
		EPackage pkg = makePackage("test");
		EClass a = makeClass(pkg, "A");
		EClass b = makeClass(pkg, "B");
		makeReference(a, "target", b, false);

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(1, bt.getReferenceFields().size());
		assertEquals("target", bt.getReferenceFields().get(0).getName());
		assertEquals("B", bt.getReferenceFields().get(0).getTargetTypeName());
	}

	@Test
	@DisplayName("Reference ui annotation maps referenceLabelField")
	void testReferenceLabelFieldAnnotation() {
		EPackage pkg = makePackage("test");
		EClass task = makeClass(pkg, "Task");
		EClass user = makeClass(pkg, "User");
		makeAttribute(user, "displayName", p.getEString());
		EReference ref = makeReference(task, "assignee", user, false);
		ref.getEAnnotations().add(uiAnnotation("referenceLabelField", "displayName"));

		ReferenceFieldSpec rfs = EcoreAdapter.fromEPackage(pkg)
				.getBlockTypes().get(0).getReferenceFields().get(0);
		assertEquals("displayName", rfs.getReferenceLabelField());
	}

	// ═══════════════════════════════════════════════════════════════
	// 5. Blockly input_value annotation
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EReference with type=input_value → ValueInputSpec")
	void testAsValueInput() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass expr = makeClass(pkg, "Expr");
		EReference ref = makeReference(parent, "expression", expr, false);
		ref.getEAnnotations().add(blocklyAnnotation("type", "input_value"));

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(0, bt.getReferenceFields().size(),
				"Should NOT create ReferenceFieldSpec");
		assertEquals(1, bt.getValueInputs().size(),
				"Should create ValueInputSpec");
		assertEquals("expression", bt.getValueInputs().get(0).getName());
		assertEquals("Expr", bt.getValueInputs().get(0).getCheckType());
	}

	@Test
	@DisplayName("ValueInput with shadow annotation")
	void testValueInputShadow() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass expr = makeClass(pkg, "Expr");
		EReference ref = makeReference(parent, "val", expr, false);
		ref.getEAnnotations().add(blocklyAnnotation(
				"type", "input_value", "shadow", "DefaultExpr"));

		ValueInputSpec vis = EcoreAdapter.fromEPackage(pkg)
				.getBlockTypes().get(0).getValueInputs().get(0);
		assertEquals("DefaultExpr", vis.getShadowBlockType());
	}

	// ═══════════════════════════════════════════════════════════════
	// 6. CONNECTION TYPE INFERENCE
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Class with containment and no supertype → NONE")
	void testConnectionNone() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass child = makeClass(pkg, "Child");
		makeReference(parent, "items", child, true);

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(BlockTypeSpec.ConnectionType.NONE, bt.getConnectionType());
	}

	@Test
	@DisplayName("Class with supertype → TYPED")
	void testConnectionTyped() {
		EPackage pkg = makePackage("test");
		EClass base = makeClass(pkg, "Base");
		EClass sub = makeClass(pkg, "Sub");
		sub.getESuperTypes().add(base);

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(1);
		assertEquals(BlockTypeSpec.ConnectionType.TYPED, bt.getConnectionType());
		assertEquals("Base", bt.getConnectionTypeName());
	}

	@Test
	@DisplayName("Plain class → FREE")
	void testConnectionFree() {
		EPackage pkg = makePackage("test");
		makeClass(pkg, "Solo");

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(BlockTypeSpec.ConnectionType.FREE, bt.getConnectionType());
	}

	// ═══════════════════════════════════════════════════════════════
	// 7. NEW: output annotation
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EAnnotation output=true → OUTPUT connection")
	void testOutputAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Expr");
		cls.getEAnnotations().add(blocklyAnnotation("output", "true"));

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(BlockTypeSpec.ConnectionType.OUTPUT, bt.getConnectionType());
	}

	@Test
	@DisplayName("EAnnotation output=TypeName → OUTPUT_TYPED")
	void testOutputTypedAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "NumExpr");
		cls.getEAnnotations().add(blocklyAnnotation("output", "Number"));

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(BlockTypeSpec.ConnectionType.OUTPUT_TYPED, bt.getConnectionType());
		assertEquals("Number", bt.getOutputType());
	}

	// ═══════════════════════════════════════════════════════════════
	// 8. NEW: inline and tooltip annotations
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EAnnotation inputsInline=true → inputsInline=true")
	void testInlineAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		cls.getEAnnotations().add(blocklyAnnotation("inputsInline", "true"));

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals(Boolean.TRUE, bt.getInputsInline());
	}

	@Test
	@DisplayName("EAnnotation tooltip → custom tooltip")
	void testTooltipAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "A");
		cls.getEAnnotations().add(blocklyAnnotation("tooltip", "My help"));

		BlockTypeSpec bt = EcoreAdapter.fromEPackage(pkg).getBlockTypes().get(0);
		assertEquals("My help", bt.getTooltip());
	}

	// ═══════════════════════════════════════════════════════════════
	// 9. CATEGORY EXTRACTION
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EAnnotation category/colour creates CategorySpec")
	void testCategoryFromAnnotation() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Task");
		cls.getEAnnotations().add(blocklyAnnotation(
				"category", "Tasks", "colour", "160"));

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertEquals(1, spec.getCategories().size());
		assertEquals("Tasks", spec.getCategories().get(0).getName());
		assertEquals(160, spec.getCategories().get(0).getColour());
	}

	// ═══════════════════════════════════════════════════════════════
	// 10. VALIDATION GENERATION
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("mustFollow annotation generates MUST_FOLLOW validation")
	void testMustFollowFromAnnotation() {
		EPackage pkg = makePackage("test");
		EClass a = makeClass(pkg, "SetChar");
		EClass b = makeClass(pkg, "ShowChar");
		b.getEAnnotations().add(validationAnnotation("mustFollow", "SetChar"));

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertEquals(1, spec.getValidations().size());
		ValidationSpec vs = spec.getValidations().get(0);
		assertEquals(ValidationSpec.ValidationType.MUST_FOLLOW, vs.getType());
		assertEquals("ShowChar", vs.getTargetType());
		assertEquals("SetChar", vs.getPredecessorType());
		assertEquals("validation_previous_block_is", vs.getVisualBlock().getBlocklyBlockType());
		assertEquals("ShowChar", vs.getVisualBlock().getFields().get("TARGET"));
		assertEquals(ValidationExpressionSpec.Kind.PREVIOUS_BLOCK_IS, vs.getVisualExpression().getKind());
		assertEquals("SetChar", vs.getVisualExpression().getTypeName());
	}

	@Test
	@DisplayName("Containment bounds generate CARDINALITY validation")
	void testCardinalityFromBounds() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass child = makeClass(pkg, "Child");
		EReference ref = makeReference(parent, "items", child, true);
		ref.setLowerBound(1);
		ref.setUpperBound(5);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		boolean foundCardinality = spec.getValidations().stream()
				.anyMatch(v -> v.getType() == ValidationSpec.ValidationType.CARDINALITY
						&& v.getOwnerType().equals("Parent")
						&& v.getLowerBound() == 1
						&& v.getUpperBound() == 5
						&& v.getVisualBlock() != null
						&& "validation_statement_cardinality".equals(v.getVisualBlock().getBlocklyBlockType())
						&& v.getVisualExpression() != null
						&& v.getVisualExpression().getKind() == ValidationExpressionSpec.Kind.LOGIC_OPERATION);
		assertTrue(foundCardinality, "Should generate cardinality validation");
	}

	@Test
	@DisplayName("validation expression annotation maps supported expression to visual blocks")
	void testValidationExpressionAnnotationVisualMapping() {
		EPackage pkg = makePackage("test");
		EClass task = makeClass(pkg, "Task");
		makeAttribute(task, "title", p.getEString());
		makeAttribute(task, "estimateHours", p.getEInt());
		task.getEAnnotations().add(validationAnnotation(
			"expression", "has(\"title\") && number(\"estimateHours\") >= 1",
			"message", "Task must have title and estimate."));

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.EXPRESSION)
			.findFirst()
			.orElseThrow();

		assertEquals("Task_validation", validation.getName());
		assertEquals("validation_expression_rule", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals("has(\"title\") && number(\"estimateHours\") >= 1",
			validation.getVisualBlock().getFields().get("EXPRESSION"));
		assertNotNull(validation.getVisualExpression());
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_OPERATION, validation.getVisualExpression().getKind());
		assertEquals("AND", validation.getVisualExpression().getOperator());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_EXISTS,
			validation.getVisualExpression().getChildren().get(0).getKind());
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_COMPARE,
			validation.getVisualExpression().getChildren().get(1).getKind());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_NUMBER,
			validation.getVisualExpression().getChildren().get(1).getChildren().get(0).getKind());
	}

	@Test
	@DisplayName("OCL containment size expression maps to visual input count")
	void testOclContainmentSizeVisualMapping() {
		EPackage pkg = makePackage("test");
		EClass parent = makeClass(pkg, "Parent");
		EClass child = makeClass(pkg, "Child");
		EReference items = makeReference(parent, "items", child, true);
		items.setUpperBound(-1);
		parent.getEAnnotations().add(validationAnnotation(
			"ocl", "self.items->size() >= 1",
			"message", "Parent needs at least one child."));

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.EXPRESSION)
			.findFirst()
			.orElseThrow();

		assertEquals("size(\"items\") >= 1", validation.getExpression());
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_COMPARE, validation.getVisualExpression().getKind());
		assertEquals("GTE", validation.getVisualExpression().getOperator());
		assertEquals(ValidationExpressionSpec.Kind.INPUT_COUNT,
			validation.getVisualExpression().getChildren().get(0).getKind());
		assertEquals("items", validation.getVisualExpression().getChildren().get(0).getFieldName());
	}

	@Test
	@DisplayName("Multi-valued attribute bounds map to visual field count")
	void testFieldCardinalityVisualMapping() {
		EPackage pkg = makePackage("test");
		EClass task = makeClass(pkg, "Task");
		EAttribute tags = makeAttribute(task, "tags", p.getEString());
		tags.setUpperBound(3);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.FIELD_CARDINALITY)
			.findFirst()
			.orElseThrow();

		assertEquals("tags", validation.getFieldName());
		assertEquals("validation_field_cardinality", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_COMPARE, validation.getVisualExpression().getKind());
		assertEquals("LTE", validation.getVisualExpression().getOperator());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_COUNT,
			validation.getVisualExpression().getChildren().get(0).getKind());
		assertEquals("tags", validation.getVisualExpression().getChildren().get(0).getFieldName());
	}

	@Test
	@DisplayName("ID attribute uniqueness maps to visual type field unique")
	void testIdUniqueVisualMapping() {
		EPackage pkg = makePackage("test");
		EClass task = makeClass(pkg, "Task");
		EAttribute id = makeAttribute(task, "id", p.getEString());
		id.setID(true);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.UNIQUE)
			.findFirst()
			.orElseThrow();

		assertEquals("id", validation.getFieldKind());
		assertEquals("validation_type_field_unique", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals(ValidationExpressionSpec.Kind.TYPE_FIELD_UNIQUE, validation.getVisualExpression().getKind());
		assertEquals("Task", validation.getVisualExpression().getTypeName());
		assertEquals("id", validation.getVisualExpression().getFieldName());
	}

	@Test
	@DisplayName("Multi-valued attribute uniqueness maps to visual field unique")
	void testManyAttributeUniqueVisualMapping() {
		EPackage pkg = makePackage("test");
		EClass task = makeClass(pkg, "Task");
		EAttribute tags = makeAttribute(task, "tags", p.getEString());
		tags.setUpperBound(-1);
		tags.setUnique(true);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.UNIQUE)
			.findFirst()
			.orElseThrow();

		assertEquals("attribute", validation.getFieldKind());
		assertEquals("validation_field_unique", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_UNIQUE, validation.getVisualExpression().getKind());
		assertEquals("tags", validation.getVisualExpression().getFieldName());
	}

	// ═══════════════════════════════════════════════════════════════
	// 11. NEW: REQUIRED validation from lowerBound
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("EAttribute with lowerBound=1 → required field + REQUIRED validation")
	void testRequiredAttributeFromLowerBound() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Task");
		EAttribute attr = makeAttribute(cls, "name", p.getEString());
		attr.setLowerBound(1);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		FieldSpec fs = spec.getBlockTypes().get(0).getFields().get(0);
		assertTrue(fs.isRequired(), "Field should be marked as required");

		boolean foundRequired = spec.getValidations().stream()
				.anyMatch(v -> v.getType() == ValidationSpec.ValidationType.REQUIRED
						&& v.getOwnerType().equals("Task")
						&& v.getFieldName().equals("name")
						&& "attribute".equals(v.getFieldKind())
						&& v.getVisualBlock() != null
						&& "validation_required_field".equals(v.getVisualBlock().getBlocklyBlockType())
						&& v.getVisualExpression() != null
						&& v.getVisualExpression().getKind() == ValidationExpressionSpec.Kind.FIELD_EXISTS);
		assertTrue(foundRequired, "Should generate REQUIRED validation for attribute");
	}

	@Test
	@DisplayName("EAttribute with lowerBound=0 → not required")
	void testOptionalAttribute() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Task");
		makeAttribute(cls, "note", p.getEString()); // default lowerBound=0

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		FieldSpec fs = spec.getBlockTypes().get(0).getFields().get(0);
		assertFalse(fs.isRequired(), "Field should NOT be required by default");

		boolean anyRequired = spec.getValidations().stream()
				.anyMatch(v -> v.getType() == ValidationSpec.ValidationType.REQUIRED);
		assertFalse(anyRequired, "Should NOT generate REQUIRED validation");
	}

	@Test
	@DisplayName("Non-containment EReference with lowerBound=1 → required reference")
	void testRequiredReferenceFromLowerBound() {
		EPackage pkg = makePackage("test");
		EClass a = makeClass(pkg, "Task");
		EClass b = makeClass(pkg, "User");
		EReference ref = makeReference(a, "assignee", b, false);
		ref.setLowerBound(1);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		ReferenceFieldSpec rfs = spec.getBlockTypes().get(0).getReferenceFields().get(0);
		assertTrue(rfs.isRequired(), "Reference should be marked as required");

		boolean foundRequired = spec.getValidations().stream()
				.anyMatch(v -> v.getType() == ValidationSpec.ValidationType.REQUIRED
						&& v.getOwnerType().equals("Task")
						&& v.getFieldName().equals("assignee")
						&& "reference".equals(v.getFieldKind()));
		assertTrue(foundRequired, "Should generate REQUIRED validation for reference");
	}

	// ═══════════════════════════════════════════════════════════════
	// 12. NEW: Auto-category from inheritance hierarchy
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Without category annotations, auto-groups by root supertype")
	void testAutoCategoryFromInheritance() {
		EPackage pkg = makePackage("test");
		EClass base = makeClass(pkg, "Animal");
		base.setAbstract(true);
		EClass dog = makeClass(pkg, "Dog");
		dog.getESuperTypes().add(base);
		EClass cat = makeClass(pkg, "Cat");
		cat.getESuperTypes().add(base);
		EClass plant = makeClass(pkg, "Plant");
		// Plant has no supertype → its own category

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);

		// Dog and Cat should be in category "Animal"
		BlockTypeSpec dogBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("Dog")).findFirst().orElse(null);
		BlockTypeSpec catBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("Cat")).findFirst().orElse(null);
		BlockTypeSpec plantBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("Plant")).findFirst().orElse(null);

		assertNotNull(dogBt);
		assertNotNull(catBt);
		assertNotNull(plantBt);
		assertEquals("Animal", dogBt.getCategoryName());
		assertEquals("Animal", catBt.getCategoryName());
		assertEquals("Plant", plantBt.getCategoryName());
		assertEquals(2, spec.getCategories().size(),
				"Should have 2 auto-categories: Animal and Plant");
	}

	@Test
	@DisplayName("Multi-level abstract hierarchy → nested auto-categories")
	void testAutoNestedCategoriesFromInheritance() {
		EPackage pkg = makePackage("test");

		// Statement (abstract, root)
		EClass statement = makeClass(pkg, "Statement");
		statement.setAbstract(true);

		// MovementAction (abstract, extends Statement, label="运动")
		EClass movement = makeClass(pkg, "MovementAction");
		movement.setAbstract(true);
		movement.getESuperTypes().add(statement);
		movement.getEAnnotations().add(uiAnnotation("label", "运动"));

		// ControlAction (abstract, extends Statement, label="控制")
		EClass control = makeClass(pkg, "ControlAction");
		control.setAbstract(true);
		control.getESuperTypes().add(statement);
		control.getEAnnotations().add(uiAnnotation("label", "控制"));

		// Concrete classes under MovementAction
		EClass moveSteps = makeClass(pkg, "MoveSteps");
		moveSteps.getESuperTypes().add(movement);

		EClass turnRight = makeClass(pkg, "TurnRight");
		turnRight.getESuperTypes().add(movement);

		// Concrete classes under ControlAction
		EClass wait = makeClass(pkg, "WaitSeconds");
		wait.getESuperTypes().add(control);

		EClass repeat = makeClass(pkg, "RepeatTimes");
		repeat.getESuperTypes().add(control);

		// Concrete class directly under Statement (no intermediate abstract)
		EClass say = makeClass(pkg, "Say");
		say.getESuperTypes().add(statement);

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);

		// Should have 1 top-level category: "Statement"
		assertEquals(1, spec.getCategories().size(),
				"Should have 1 root auto-category: Statement");
		CategorySpec rootCat = spec.getCategories().get(0);
		assertEquals("Statement", rootCat.getName());

		// Root should have 2 sub-categories (MovementAction, ControlAction)
		assertEquals(2, rootCat.getChildren().size(),
				"Statement should have 2 nested sub-categories");

		CategorySpec moveCat = rootCat.getChildren().stream()
				.filter(c -> c.getName().equals("MovementAction"))
				.findFirst().orElse(null);
		assertNotNull(moveCat, "Should have MovementAction sub-category");
		assertEquals("运动", moveCat.getLabel(),
				"MovementAction label should come from @blockly(label)");

		CategorySpec ctrlCat = rootCat.getChildren().stream()
				.filter(c -> c.getName().equals("ControlAction"))
				.findFirst().orElse(null);
		assertNotNull(ctrlCat, "Should have ControlAction sub-category");
		assertEquals("控制", ctrlCat.getLabel());

		// Verify block assignments
		BlockTypeSpec moveBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("MoveSteps")).findFirst().orElse(null);
		assertEquals("MovementAction", moveBt.getCategoryName(),
				"MoveSteps should belong to MovementAction category");

		BlockTypeSpec turnBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("TurnRight")).findFirst().orElse(null);
		assertEquals("MovementAction", turnBt.getCategoryName());

		BlockTypeSpec waitBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("WaitSeconds")).findFirst().orElse(null);
		assertEquals("ControlAction", waitBt.getCategoryName());

		// "Say" is concrete and directly under Statement → assigned to Statement
		BlockTypeSpec sayBt = spec.getBlockTypes().stream()
				.filter(b -> b.getTypeName().equals("Say")).findFirst().orElse(null);
		assertEquals("Statement", sayBt.getCategoryName(),
				"Say should be directly in Statement category");
	}

	@Test
	@DisplayName("With category annotations, auto-category is skipped")
	void testNoCategoryAutoWhenAnnotated() {
		EPackage pkg = makePackage("test");
		EClass cls = makeClass(pkg, "Task");
		cls.getEAnnotations().add(blocklyAnnotation("category", "MyTasks"));

		BlocklyEditorSpec spec = EcoreAdapter.fromEPackage(pkg);
		assertEquals("MyTasks", spec.getBlockTypes().get(0).getCategoryName());
		assertEquals(1, spec.getCategories().size());
		assertEquals("MyTasks", spec.getCategories().get(0).getName());
	}
}
