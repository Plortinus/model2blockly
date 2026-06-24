package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.junit.jupiter.api.*;
import io.github.plortinus.model2blockly.blocklyspec.*;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;

/**
 * Tests for the intermediate model classes (BlocklyEditorSpec, BlockTypeSpec,
 * FieldSpec, ValueInputSpec, etc.) verifying data structure correctness.
 *
 * Run as: Right-click → Run As → JUnit Test (in Eclipse)
 */
class BlocklyEditorSpecTest {

	// ═══════════════════════════════════════════════════════════════
	// BlocklyEditorSpec
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("getConcreteBlockTypes() filters out abstract blocks")
	void testConcreteBlockTypes() {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();

		BlockTypeSpec concrete1 = new BlockTypeSpec();
		concrete1.setTypeName("A");

		BlockTypeSpec abstract1 = new BlockTypeSpec();
		abstract1.setTypeName("B");
		abstract1.setAbstract(true);

		BlockTypeSpec concrete2 = new BlockTypeSpec();
		concrete2.setTypeName("C");

		spec.setBlockTypes(Arrays.asList(concrete1, abstract1, concrete2));

		assertEquals(3, spec.getBlockTypes().size());
		assertEquals(2, spec.getConcreteBlockTypes().size());
		assertEquals("A", spec.getConcreteBlockTypes().get(0).getTypeName());
		assertEquals("C", spec.getConcreteBlockTypes().get(1).getTypeName());
	}

	@Test
	@DisplayName("workspaceOptions defaults to empty map (no grid)")
	void testWorkspaceOptionsDefaults() {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();
		assertNotNull(spec.getWorkspaceOptions());
		assertTrue(spec.getWorkspaceOptions().isEmpty());
	}

	@Test
	@DisplayName("Code generation metadata defaults to plain text")
	void testCodeGenerationDefaults() {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();
		assertEquals("text", spec.getCodeLanguage());
		assertEquals("txt", spec.getCodeFileExtension());

		spec.setCodeLanguage("");
		spec.setCodeFileExtension(null);
		assertEquals("text", spec.getCodeLanguage());
		assertEquals("txt", spec.getCodeFileExtension());
	}

	@Test
	@DisplayName("Intermediate XMI round-trip ignores global .xmi binary resource factory")
	void testIntermediateXmiRoundTripUsesExplicitXmiResource() {
		Map<String, Object> extensionMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		Object previous = extensionMap.put("xmi", new ResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new BinaryResourceImpl(uri);
			}
		});
		try {
			BlocklyEditorSpec spec = new BlocklyEditorSpec();
			spec.setDomainName("RoundTrip");
			spec.setNsURI("http://www.example.org/roundtrip");
			spec.setNsPrefix("roundtrip");
			BlockTypeSpec block = new BlockTypeSpec();
			block.setTypeName("Task");
			block.setLabel("Task");
			block.setConnectionType(BlockTypeSpec.ConnectionType.FREE);
			spec.getBlockTypes().add(block);

			String xmi = BlocklySpecXmiSerializer.toXmi(spec);
			assertTrue(xmi.startsWith("<?xml"), "Intermediate artifact should be textual XMI");
			BlocklyEditorSpec reloaded = BlocklySpecXmiSerializer.fromXmi(xmi);

			assertEquals("RoundTrip", reloaded.getDomainName());
			assertEquals(1, reloaded.getBlockTypes().size());
			assertEquals("Task", reloaded.getBlockTypes().get(0).getTypeName());
		} finally {
			if (previous == null) {
				extensionMap.remove("xmi");
			} else {
				extensionMap.put("xmi", previous);
			}
		}
	}

	// ═══════════════════════════════════════════════════════════════
	// BlockTypeSpec
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("BlockTypeSpec default connection type is FREE")
	void testDefaultConnectionType() {
		BlockTypeSpec bt = new BlockTypeSpec();
		assertEquals(BlockTypeSpec.ConnectionType.FREE, bt.getConnectionType());
	}

	@Test
	@DisplayName("BlockTypeSpec default colour is 200")
	void testDefaultColour() {
		BlockTypeSpec bt = new BlockTypeSpec();
		assertEquals(200, bt.getColour());
	}

	@Test
	@DisplayName("BlockTypeSpec inputsInline defaults to null (auto)")
	void testInputsInlineDefault() {
		BlockTypeSpec bt = new BlockTypeSpec();
		assertNull(bt.getInputsInline());
	}

	@Test
	@DisplayName("BlockTypeSpec stores optional code template")
	void testCodeTemplate() {
		BlockTypeSpec bt = new BlockTypeSpec();
		bt.setCodeTemplate("print({{text}})");
		assertEquals("print({{text}})", bt.getCodeTemplate());
	}

	@Test
	@DisplayName("BlockTypeSpec has empty lists by default")
	void testEmptyListsDefault() {
		BlockTypeSpec bt = new BlockTypeSpec();
		assertNotNull(bt.getFields());
		assertNotNull(bt.getStatementInputs());
		assertNotNull(bt.getReferenceFields());
		assertNotNull(bt.getValueInputs());
		assertTrue(bt.getFields().isEmpty());
		assertTrue(bt.getStatementInputs().isEmpty());
		assertTrue(bt.getReferenceFields().isEmpty());
		assertTrue(bt.getValueInputs().isEmpty());
	}

	@Test
	@DisplayName("ConnectionType enum has all 5 values")
	void testConnectionTypeEnum() {
		BlockTypeSpec.ConnectionType[] values = BlockTypeSpec.ConnectionType.values();
		assertEquals(5, values.length);
		assertNotNull(BlockTypeSpec.ConnectionType.NONE);
		assertNotNull(BlockTypeSpec.ConnectionType.FREE);
		assertNotNull(BlockTypeSpec.ConnectionType.TYPED);
		assertNotNull(BlockTypeSpec.ConnectionType.OUTPUT);
		assertNotNull(BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
	}

	// ═══════════════════════════════════════════════════════════════
	// FieldSpec
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("FieldType enum has all 9 values")
	void testFieldTypeEnum() {
		FieldSpec.FieldType[] values = FieldSpec.FieldType.values();
		assertEquals(9, values.length);
		assertNotNull(FieldSpec.FieldType.TEXT);
		assertNotNull(FieldSpec.FieldType.INTEGER);
		assertNotNull(FieldSpec.FieldType.FLOAT);
		assertNotNull(FieldSpec.FieldType.BOOLEAN);
		assertNotNull(FieldSpec.FieldType.DROPDOWN);
		assertNotNull(FieldSpec.FieldType.COLOUR);
		assertNotNull(FieldSpec.FieldType.ANGLE);
		assertNotNull(FieldSpec.FieldType.IMAGE);
		assertNotNull(FieldSpec.FieldType.LABEL);
	}

	@Test
	@DisplayName("FieldSpec default type is TEXT")
	void testFieldSpecDefaultType() {
		FieldSpec fs = new FieldSpec();
		assertEquals(FieldSpec.FieldType.TEXT, fs.getFieldType());
	}

	@Test
	@DisplayName("FieldSpec image properties")
	void testFieldSpecImageProps() {
		FieldSpec fs = new FieldSpec();
		fs.setFieldType(FieldSpec.FieldType.IMAGE);
		fs.setImageUrl("http://example.com/img.png");
		fs.setImageWidth(64);
		fs.setImageHeight(48);

		assertEquals("http://example.com/img.png", fs.getImageUrl());
		assertEquals(64, fs.getImageWidth());
		assertEquals(48, fs.getImageHeight());
	}

	@Test
	@DisplayName("FieldSpec min/max properties")
	void testFieldSpecMinMax() {
		FieldSpec fs = new FieldSpec();
		fs.setMin("0");
		fs.setMax("100");

		assertEquals("0", fs.getMin());
		assertEquals("100", fs.getMax());
	}

	// ═══════════════════════════════════════════════════════════════
	// ValueInputSpec
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("ValueInputSpec stores name, checkType, shadowBlockType")
	void testValueInputSpec() {
		ValueInputSpec vis = new ValueInputSpec();
		vis.setName("expr");
		vis.setCheckType("Expression");
		vis.setShadowBlockType("DefaultExpr");

		assertEquals("expr", vis.getName());
		assertEquals("Expression", vis.getCheckType());
		assertEquals("DefaultExpr", vis.getShadowBlockType());
	}

	@Test
	@DisplayName("ValueInputSpec defaults to null")
	void testValueInputSpecDefaults() {
		ValueInputSpec vis = new ValueInputSpec();
		assertNull(vis.getName());
		assertNull(vis.getCheckType());
		assertNull(vis.getShadowBlockType());
	}

	// ═══════════════════════════════════════════════════════════════
	// DropdownOption
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("DropdownOption stores value and label")
	void testDropdownOption() {
		DropdownOption opt = new DropdownOption("high", "Alta");
		assertEquals("high", opt.getValue());
		assertEquals("Alta", opt.getLabel());
	}

	// ═══════════════════════════════════════════════════════════════
	// ValidationSpec
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("ValidationSpec MUST_FOLLOW fields")
	void testValidationMustFollow() {
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.MUST_FOLLOW);
		vs.setTargetType("B");
		vs.setPredecessorType("A");

		assertEquals(ValidationSpec.ValidationType.MUST_FOLLOW, vs.getType());
		assertEquals("B", vs.getTargetType());
		assertEquals("A", vs.getPredecessorType());
	}

	@Test
	@DisplayName("ValidationSpec CARDINALITY fields")
	void testValidationCardinality() {
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.CARDINALITY);
		vs.setOwnerType("Parent");
		vs.setContainmentName("items");
		vs.setLowerBound(1);
		vs.setUpperBound(10);

		assertEquals("Parent", vs.getOwnerType());
		assertEquals("items", vs.getContainmentName());
		assertEquals(1, vs.getLowerBound());
		assertEquals(10, vs.getUpperBound());
	}

	// ═══════════════════════════════════════════════════════════════
	// NEW: ValidationSpec REQUIRED + required flags
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("ValidationSpec REQUIRED fields")
	void testValidationRequired() {
		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.REQUIRED);
		vs.setOwnerType("Task");
		vs.setFieldName("name");
		vs.setFieldKind("attribute");

		assertEquals(ValidationSpec.ValidationType.REQUIRED, vs.getType());
		assertEquals("Task", vs.getOwnerType());
		assertEquals("name", vs.getFieldName());
		assertEquals("attribute", vs.getFieldKind());
	}

	@Test
	@DisplayName("ValidationSpec stores visual validation expression")
	void testValidationVisualExpression() {
		ValidationExpressionSpec expression = ValidationExpressionSpec.compare(
			"GTE",
			ValidationExpressionSpec.fieldNumber("estimateHours"),
			ValidationExpressionSpec.number("1")
		);

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setOwnerType("Task");
		vs.setVisualExpression(expression);

		assertSame(expression, vs.getVisualExpression());
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_COMPARE, vs.getVisualExpression().getKind());
		assertEquals("GTE", vs.getVisualExpression().getOperator());
		assertEquals("estimateHours", vs.getVisualExpression().getChildren().get(0).getFieldName());
		assertEquals("1", vs.getVisualExpression().getChildren().get(1).getLiteral());
	}

	@Test
	@DisplayName("ValidationSpec stores high-level visual validation block")
	void testValidationVisualBlock() {
		ValidationBlockSpec block = ValidationBlockSpec.requiredField("Task", "name", "attribute");

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.REQUIRED);
		vs.setOwnerType("Task");
		vs.setVisualBlock(block);

		assertSame(block, vs.getVisualBlock());
		assertEquals("validation_required_field", vs.getVisualBlock().getBlocklyBlockType());
		assertEquals("Task", vs.getVisualBlock().getFields().get("TYPE"));
		assertEquals("name", vs.getVisualBlock().getFields().get("FIELD"));
		assertEquals("attribute", vs.getVisualBlock().getFields().get("FIELD_KIND"));
	}

	@Test
	@DisplayName("ValidationExpressionParser converts supported expression subset to visual blocks")
	void testValidationExpressionParser() {
		ValidationExpressionSpec expression = ValidationExpressionParser.parse(
			"has(\"title\") && number(\"estimateHours\") >= 1 && value(\"state\") !== \"done\"")
			.orElseThrow();

		assertEquals(ValidationExpressionSpec.Kind.LOGIC_OPERATION, expression.getKind());
		assertEquals("AND", expression.getOperator());
		assertEquals(3, expression.getChildren().size());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_EXISTS, expression.getChildren().get(0).getKind());

		ValidationExpressionSpec estimateCheck = expression.getChildren().get(1);
		assertEquals(ValidationExpressionSpec.Kind.LOGIC_COMPARE, estimateCheck.getKind());
		assertEquals("GTE", estimateCheck.getOperator());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_NUMBER, estimateCheck.getChildren().get(0).getKind());
		assertEquals("estimateHours", estimateCheck.getChildren().get(0).getFieldName());
		assertEquals("1", estimateCheck.getChildren().get(1).getLiteral());

		ValidationExpressionSpec stateCheck = expression.getChildren().get(2);
		assertEquals("NEQ", stateCheck.getOperator());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_VALUE, stateCheck.getChildren().get(0).getKind());
		assertEquals("done", stateCheck.getChildren().get(1).getLiteral());
	}

	@Test
	@DisplayName("FieldSpec required defaults to false")
	void testFieldSpecRequiredDefault() {
		FieldSpec fs = new FieldSpec();
		assertFalse(fs.isRequired());
	}

	@Test
	@DisplayName("FieldSpec required can be set to true")
	void testFieldSpecRequiredTrue() {
		FieldSpec fs = new FieldSpec();
		fs.setRequired(true);
		assertTrue(fs.isRequired());
	}

	@Test
	@DisplayName("ReferenceFieldSpec required defaults to false")
	void testRefFieldSpecRequiredDefault() {
		ReferenceFieldSpec rfs = new ReferenceFieldSpec();
		assertFalse(rfs.isRequired());
	}

	@Test
	@DisplayName("ReferenceFieldSpec required can be set to true")
	void testRefFieldSpecRequiredTrue() {
		ReferenceFieldSpec rfs = new ReferenceFieldSpec();
		rfs.setRequired(true);
		assertTrue(rfs.isRequired());
	}
}
