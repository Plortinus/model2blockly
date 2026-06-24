package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.*;
import io.github.plortinus.model2blockly.blocklyspec.*;
import io.github.plortinus.model2blockly.generator.BlocklyCodeGenerator;

/**
 * Comprehensive tests for BlocklyCodeGenerator.
 *
 * These tests construct BlocklyEditorSpec objects programmatically and verify
 * that the generated JS/HTML contains the expected Blockly constructs.
 *
 * Run as: Right-click → Run As → JUnit Test (in Eclipse)
 */
class BlocklyCodeGeneratorTest {

	private BlocklyCodeGenerator generator;

	@BeforeEach
	void setUp() {
		generator = new BlocklyCodeGenerator();
	}

	// ═══════════════════════════════════════════════════════════════
	// Helper methods
	// ═══════════════════════════════════════════════════════════════

	private BlocklyEditorSpec minimalSpec(String domainName) {
		BlocklyEditorSpec spec = new BlocklyEditorSpec();
		spec.setDomainName(domainName);
		spec.setNsURI("http://www.example.org/" + domainName.toLowerCase());
		spec.setNsPrefix(domainName.toLowerCase());
		return spec;
	}

	private BlockTypeSpec makeBlock(String name, String label, int colour,
			BlockTypeSpec.ConnectionType connType) {
		BlockTypeSpec bt = new BlockTypeSpec();
		bt.setTypeName(name);
		bt.setLabel(label);
		bt.setColour(colour);
		bt.setConnectionType(connType);
		return bt;
	}

	private FieldSpec makeField(String name, FieldSpec.FieldType type, String defaultValue) {
		FieldSpec fs = new FieldSpec();
		fs.setName(name);
		fs.setFieldType(type);
		fs.setDefaultValue(defaultValue);
		return fs;
	}

	private String generatorFunction(String generators, String typeName) {
		String marker = "javascriptGenerator.forBlock['" + typeName + "']";
		int start = generators.indexOf(marker);
		assertTrue(start >= 0, "Generator function not found for " + typeName);
		int next = generators.indexOf("javascriptGenerator.forBlock['", start + marker.length());
		return next >= 0 ? generators.substring(start, next) : generators.substring(start);
	}

	// ═══════════════════════════════════════════════════════════════
	// 1. REGRESSION: Basic structure (existing features)
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("generate() produces editor files plus sample model with correct naming")
	void testGenerateProducesEditorFilesPlusSampleModel() {
		BlocklyEditorSpec spec = minimalSpec("MyDomain");
		spec.getBlockTypes().add(makeBlock("A", "Block A", 200,
				BlockTypeSpec.ConnectionType.FREE));

		Map<String, String> files = generator.generate(spec);

		assertEquals(10, files.size());
		assertTrue(files.containsKey("MyDomain_blocks.js"));
		assertTrue(files.containsKey("MyDomain_toolbox.js"));
		assertTrue(files.containsKey("MyDomain_generators.js"));
		assertTrue(files.containsKey("MyDomain_validations.js"));
		assertTrue(files.containsKey("MyDomain_editor.html"));
		assertTrue(files.containsKey("MyDomain_standalone.html"));
		assertTrue(files.containsKey("validation_workspace.html"));
		assertTrue(files.containsKey("validation_blocks.json"));
		assertTrue(files.containsKey("validation_runtime.js"));
		assertTrue(files.containsKey("sample_model.json"));
	}

	@Test
	@DisplayName("Abstract blocks are excluded from generated output")
	void testAbstractBlocksExcluded() {
		BlocklyEditorSpec spec = minimalSpec("Test");
		BlockTypeSpec concrete = makeBlock("Concrete", "C", 200,
				BlockTypeSpec.ConnectionType.FREE);
		BlockTypeSpec abs = makeBlock("Abstract", "A", 200,
				BlockTypeSpec.ConnectionType.FREE);
		abs.setAbstract(true);

		spec.getBlockTypes().addAll(Arrays.asList(concrete, abs));
		Map<String, String> files = generator.generate(spec);

		String blocks = files.get("Test_blocks.js");
		assertTrue(blocks.contains("\"type\": \"Concrete\""));
		assertFalse(blocks.contains("\"type\": \"Abstract\""));
	}

	// ═══════════════════════════════════════════════════════════════
	// 2. FIELD TYPES: field_input, field_number, field_checkbox, field_dropdown
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("TEXT field generates field_input")
	void testFieldText() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("myText", FieldSpec.FieldType.TEXT, "hello"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_input\""));
		assertTrue(blocks.contains("\"name\": \"myText\""));
		assertTrue(blocks.contains("\"text\": \"hello\""));
	}

	@Test
	@DisplayName("INTEGER field generates field_number with precision 1")
	void testFieldInteger() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("count", FieldSpec.FieldType.INTEGER, "5"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_number\""));
		assertTrue(blocks.contains("\"name\": \"count\""));
		assertTrue(blocks.contains("\"value\": 5"));
		assertTrue(blocks.contains("\"precision\": 1"));
	}

	@Test
	@DisplayName("FLOAT field generates field_number with precision 0.1")
	void testFieldFloat() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("rate", FieldSpec.FieldType.FLOAT, "3"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"precision\": 0.1"));
	}

	@Test
	@DisplayName("BOOLEAN field generates field_checkbox")
	void testFieldBoolean() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("active", FieldSpec.FieldType.BOOLEAN, "true"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_checkbox\""));
		assertTrue(blocks.contains("\"checked\": true"));
	}

	@Test
	@DisplayName("DROPDOWN field generates field_dropdown with options")
	void testFieldDropdown() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		FieldSpec fs = makeField("prio", FieldSpec.FieldType.DROPDOWN, null);
		fs.getOptions().add(new DropdownOption("high", "Alta"));
		fs.getOptions().add(new DropdownOption("low", "Baja"));
		bt.getFields().add(fs);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_dropdown\""));
		assertTrue(blocks.contains("[\"Alta\", \"high\"]"));
		assertTrue(blocks.contains("[\"Baja\", \"low\"]"));
	}

	// ═══════════════════════════════════════════════════════════════
	// 3. NEW FIELD TYPES: colour, angle, image, label
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("COLOUR field generates field_colour")
	void testFieldColour() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("bg", FieldSpec.FieldType.COLOUR, "#ff0000"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_colour\""),
				"Should generate field_colour type");
		assertTrue(blocks.contains("\"name\": \"bg\""));
		assertTrue(blocks.contains("\"colour\": \"#ff0000\""));
	}

	@Test
	@DisplayName("ANGLE field generates field_angle")
	void testFieldAngle() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("dir", FieldSpec.FieldType.ANGLE, "90"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_angle\""),
				"Should generate field_angle type");
		assertTrue(blocks.contains("\"name\": \"dir\""));
		assertTrue(blocks.contains("\"angle\": 90"));
	}

	@Test
	@DisplayName("IMAGE field generates field_image with dimensions")
	void testFieldImage() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		FieldSpec fs = makeField("icon", FieldSpec.FieldType.IMAGE, null);
		fs.setImageUrl("https://example.com/icon.png");
		fs.setImageWidth(32);
		fs.setImageHeight(32);
		bt.getFields().add(fs);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_image\""),
				"Should generate field_image type");
		assertTrue(blocks.contains("\"src\": \"https://example.com/icon.png\""));
		assertTrue(blocks.contains("\"width\": 32"));
		assertTrue(blocks.contains("\"height\": 32"));
	}

	@Test
	@DisplayName("LABEL field generates field_label")
	void testFieldLabel() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("lbl", FieldSpec.FieldType.LABEL, "Hello World"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"field_label\""),
				"Should generate field_label type");
		assertTrue(blocks.contains("\"text\": \"Hello World\""));
	}

	// ═══════════════════════════════════════════════════════════════
	// 4. MIN / MAX on number fields
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("INTEGER field with min/max generates constrained field_number")
	void testFieldIntegerMinMax() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		FieldSpec fs = makeField("sec", FieldSpec.FieldType.INTEGER, "5");
		fs.setMin("0");
		fs.setMax("60");
		bt.getFields().add(fs);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"min\": 0"), "Should contain min constraint");
		assertTrue(blocks.contains("\"max\": 60"), "Should contain max constraint");
	}

	@Test
	@DisplayName("FLOAT field with min/max generates constrained field_number")
	void testFieldFloatMinMax() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		FieldSpec fs = makeField("speed", FieldSpec.FieldType.FLOAT, "1");
		fs.setMin("0");
		fs.setMax("10");
		bt.getFields().add(fs);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"min\": 0"));
		assertTrue(blocks.contains("\"max\": 10"));
		assertTrue(blocks.contains("\"precision\": 0.1"));
	}

	@Test
	@DisplayName("Number field without min/max omits those properties")
	void testFieldNumberNoMinMax() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("val", FieldSpec.FieldType.INTEGER, "0"));
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertFalse(blocks.contains("\"min\""), "Should not contain min when not set");
		assertFalse(blocks.contains("\"max\""), "Should not contain max when not set");
	}

	// ═══════════════════════════════════════════════════════════════
	// 5. CONNECTION TYPES
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("NONE connection: no previousStatement/nextStatement/output")
	void testConnectionNone() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Root", "Root", 200,
				BlockTypeSpec.ConnectionType.NONE));

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertFalse(blocks.contains("previousStatement"));
		assertFalse(blocks.contains("nextStatement"));
		assertFalse(blocks.contains("\"output\""));
	}

	@Test
	@DisplayName("FREE connection: previousStatement null, nextStatement null")
	void testConnectionFree() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Item", "Item", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"previousStatement\": null"));
		assertTrue(blocks.contains("\"nextStatement\": null"));
	}

	@Test
	@DisplayName("TYPED connection: previousStatement/nextStatement with type name")
	void testConnectionTyped() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Sub", "Sub", 200,
				BlockTypeSpec.ConnectionType.TYPED);
		bt.setConnectionTypeName("Base");
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"previousStatement\": \"Base\""));
		assertTrue(blocks.contains("\"nextStatement\": \"Base\""));
	}

	@Test
	@DisplayName("OUTPUT connection: output null (untyped)")
	void testConnectionOutput() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Expr", "Expression", 200,
				BlockTypeSpec.ConnectionType.OUTPUT);
		bt.setOutputType("Expr");
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"output\": null"),
				"Should have untyped output connection");
		assertFalse(blocks.contains("previousStatement"),
				"Output blocks should not have previousStatement");
	}

	@Test
	@DisplayName("OUTPUT_TYPED connection: output with type name")
	void testConnectionOutputTyped() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("NumExpr", "Number", 200,
				BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
		bt.setOutputType("Expression");
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"output\": \"Expression\""),
				"Should have typed output connection");
	}

	// ═══════════════════════════════════════════════════════════════
	// 6. VALUE INPUT (input_value)
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Value input generates input_value in block definition")
	void testValueInputInBlockDef() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Container", "Container", 200,
				BlockTypeSpec.ConnectionType.NONE);
		ValueInputSpec vi = new ValueInputSpec();
		vi.setName("expr");
		vi.setCheckType("Expression");
		bt.getValueInputs().add(vi);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"input_value\""),
				"Should generate input_value");
		assertTrue(blocks.contains("\"name\": \"expr\""));
		assertTrue(blocks.contains("\"check\": \"Expression\""));
	}

	@Test
	@DisplayName("Value input without check type uses null")
	void testValueInputNoCheck() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Any", "Any", 200,
				BlockTypeSpec.ConnectionType.FREE);
		ValueInputSpec vi = new ValueInputSpec();
		vi.setName("val");
		bt.getValueInputs().add(vi);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"check\": null"));
	}

	@Test
	@DisplayName("Value input generates valueToCode in generator")
	void testValueInputGenerator() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Print", "Print", 200,
				BlockTypeSpec.ConnectionType.FREE);
		ValueInputSpec vi = new ValueInputSpec();
		vi.setName("what");
		vi.setCheckType("Expr");
		bt.getValueInputs().add(vi);
		spec.getBlockTypes().add(bt);

		String generators = generator.generate(spec).get("T_generators.js");
		assertTrue(generators.contains("valueToCode(block, 'what'"),
				"Should use valueToCode for value inputs");
	}

	// ═══════════════════════════════════════════════════════════════
	// 7. OUTPUT BLOCK CODE GENERATION
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Output block generator returns [code, precedence] tuple")
	void testOutputBlockGeneratorReturnsArray() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("NumExpr", "Num", 200,
				BlockTypeSpec.ConnectionType.OUTPUT);
		bt.setOutputType("Expr");
		bt.getFields().add(makeField("val", FieldSpec.FieldType.INTEGER, "0"));
		spec.getBlockTypes().add(bt);

		String generators = generator.generate(spec).get("T_generators.js");
		String numExprGenerator = generatorFunction(generators, "NumExpr");
		assertTrue(numExprGenerator.contains("return [code, 0]"),
				"Output blocks should return [code, precedence]");
		assertFalse(numExprGenerator.contains("return '{' +"),
				"Output blocks should NOT return plain string");
	}

	@Test
	@DisplayName("Statement block generator returns plain string")
	void testStatementBlockGeneratorReturnsString() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Action", "Action", 200,
				BlockTypeSpec.ConnectionType.FREE);
		bt.getFields().add(makeField("name", FieldSpec.FieldType.TEXT, ""));
		spec.getBlockTypes().add(bt);

		String generators = generator.generate(spec).get("T_generators.js");
		String actionGenerator = generatorFunction(generators, "Action");
		assertTrue(actionGenerator.contains("return '{' +"),
				"Statement blocks should return plain string");
		assertFalse(actionGenerator.contains("return [code"),
				"Statement blocks should NOT return tuple");
	}

	// ═══════════════════════════════════════════════════════════════
	// 8. INPUTS INLINE
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("inputsInline true generates property in block JSON")
	void testInputsInlineTrue() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.setInputsInline(Boolean.TRUE);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"inputsInline\": true"),
				"Should contain inputsInline: true");
	}

	@Test
	@DisplayName("inputsInline false generates property in block JSON")
	void testInputsInlineFalse() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.setInputsInline(Boolean.FALSE);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"inputsInline\": false"));
	}

	@Test
	@DisplayName("inputsInline null (auto) omits the property")
	void testInputsInlineNull() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertFalse(blocks.contains("inputsInline"),
				"Should not contain inputsInline when null");
	}

	// ═══════════════════════════════════════════════════════════════
	// 9. TOOLTIP
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Custom tooltip overrides default")
	void testCustomTooltip() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("B", "MyLabel", 200,
				BlockTypeSpec.ConnectionType.FREE);
		bt.setTooltip("Custom help text");
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"tooltip\": \"Custom help text\""));
	}

	@Test
	@DisplayName("No custom tooltip uses label as tooltip")
	void testDefaultTooltip() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "MyLabel", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"tooltip\": \"MyLabel\""));
	}

	// ═══════════════════════════════════════════════════════════════
	// 10. TOOLBOX: categories and shadow blocks
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Toolbox organizes blocks by category")
	void testToolboxCategories() {
		BlocklyEditorSpec spec = minimalSpec("T");
		CategorySpec cat = new CategorySpec();
		cat.setName("MyCat");
		cat.setLabel("My Category");
		cat.setColour(200);
		spec.getCategories().add(cat);

		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.setCategoryName("MyCat");
		spec.getBlockTypes().add(bt);

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"name\": \"My Category\""));
		assertTrue(toolbox.contains("\"colour\": \"200\""));
		assertTrue(toolbox.contains("\"type\": \"B\""));
	}

	@Test
	@DisplayName("Nested categories generate subcategories in toolbox")
	void testNestedCategories() {
		BlocklyEditorSpec spec = minimalSpec("T");

		CategorySpec parent = new CategorySpec();
		parent.setName("Control");
		parent.setLabel("Control");
		parent.setColour(37);

		CategorySpec childLoop = new CategorySpec();
		childLoop.setName("Loop");
		childLoop.setLabel("Loops");
		childLoop.setColour(37);
		parent.getChildren().add(childLoop);

		CategorySpec childCond = new CategorySpec();
		childCond.setName("Cond");
		childCond.setLabel("Conditions");
		childCond.setColour(37);
		parent.getChildren().add(childCond);

		spec.getCategories().add(parent);

		BlockTypeSpec startBlock = makeBlock("Start", "Start", 37,
				BlockTypeSpec.ConnectionType.NONE);
		startBlock.setCategoryName("Control");
		spec.getBlockTypes().add(startBlock);

		BlockTypeSpec repeatBlock = makeBlock("Repeat", "Repeat", 37,
				BlockTypeSpec.ConnectionType.FREE);
		repeatBlock.setCategoryName("Loop");
		spec.getBlockTypes().add(repeatBlock);

		BlockTypeSpec ifBlock = makeBlock("IfThen", "If", 37,
				BlockTypeSpec.ConnectionType.FREE);
		ifBlock.setCategoryName("Cond");
		spec.getBlockTypes().add(ifBlock);

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"name\": \"Control\""),
				"Should have parent category");
		assertTrue(toolbox.contains("\"name\": \"Loops\""),
				"Should have nested Loop sub-category");
		assertTrue(toolbox.contains("\"name\": \"Conditions\""),
				"Should have nested Cond sub-category");
		assertTrue(toolbox.contains("\"type\": \"Start\""),
				"Start block should be in parent Control category");
		assertTrue(toolbox.contains("\"type\": \"Repeat\""),
				"Repeat block should be in nested Loop sub-category");
		assertTrue(toolbox.contains("\"type\": \"IfThen\""),
				"IfThen block should be in nested Cond sub-category");
	}

	@Test
	@DisplayName("Shadow block appears in toolbox entry")
	void testShadowBlockInToolbox() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Parent", "Parent", 200,
				BlockTypeSpec.ConnectionType.NONE);
		ValueInputSpec vi = new ValueInputSpec();
		vi.setName("child");
		vi.setCheckType("Expr");
		vi.setShadowBlockType("DefaultExpr");
		bt.getValueInputs().add(vi);
		spec.getBlockTypes().add(bt);

		BlockTypeSpec shadow = makeBlock("DefaultExpr", "Default", 100,
				BlockTypeSpec.ConnectionType.OUTPUT);
		spec.getBlockTypes().add(shadow);

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"shadow\""),
				"Toolbox should contain shadow block config");
		assertTrue(toolbox.contains("\"type\": \"DefaultExpr\""));
	}

	@Test
	@DisplayName("Block without shadow has simple toolbox entry")
	void testNoShadowSimpleEntry() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Simple", "Simple", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertFalse(toolbox.contains("\"shadow\""));
	}

	@Test
	@DisplayName("Flyout toolbox: no categories → auto flyout")
	void testFlyoutToolboxAutoDetect() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("A", "A", 200,
				BlockTypeSpec.ConnectionType.FREE));
		spec.getBlockTypes().add(makeBlock("B", "B", 100,
				BlockTypeSpec.ConnectionType.FREE));

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"kind\": \"flyoutToolbox\""),
				"No categories should auto-detect as flyout toolbox");
		assertTrue(toolbox.contains("\"type\": \"A\""));
		assertTrue(toolbox.contains("\"type\": \"B\""));
		assertFalse(toolbox.contains("\"kind\": \"category\""),
				"Flyout toolbox should not have category entries");
	}

	@Test
	@DisplayName("Flyout toolbox: explicit toolboxType override")
	void testFlyoutToolboxExplicit() {
		BlocklyEditorSpec spec = minimalSpec("T");
		CategorySpec cat = new CategorySpec();
		cat.setName("MyCat");
		cat.setLabel("My Category");
		cat.setColour(200);
		spec.getCategories().add(cat);

		BlockTypeSpec bt = makeBlock("B", "B", 200, BlockTypeSpec.ConnectionType.FREE);
		bt.setCategoryName("MyCat");
		spec.getBlockTypes().add(bt);

		spec.setToolboxType("flyout");

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"kind\": \"flyoutToolbox\""),
				"Explicit flyout should override category auto-detect");
	}

	@Test
	@DisplayName("Category toolbox: explicit toolboxType forces category even without categories")
	void testCategoryToolboxExplicit() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("A", "A", 200,
				BlockTypeSpec.ConnectionType.FREE));
		spec.setToolboxType("category");

		String toolbox = generator.generate(spec).get("T_toolbox.js");
		assertTrue(toolbox.contains("\"kind\": \"categoryToolbox\""),
				"Explicit category should force categoryToolbox");
	}

	// ═══════════════════════════════════════════════════════════════
	// 11. WORKSPACE: grid configuration
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Grid config via workspaceOptions generates grid in workspace")
	void testGridEnabled() {
		BlocklyEditorSpec spec = minimalSpec("T");
		Map<String, Object> grid = new LinkedHashMap<>();
		grid.put("spacing", 25);
		grid.put("length", 3);
		grid.put("colour", "#ccc");
		grid.put("snap", true);
		spec.putWorkspaceOption("grid", grid);
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("grid:"), "Should contain grid configuration");
		assertTrue(html.contains("spacing: 25"));
		assertTrue(html.contains("snap: true"));
	}

	@Test
	@DisplayName("No grid option means no grid in output")
	void testGridDisabled() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_standalone.html");
		assertFalse(html.contains("grid:"), "Should not contain grid when not configured");
	}

	// ═══════════════════════════════════════════════════════════════
	// 12. HTML: Save/Load buttons
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Standalone HTML contains save/load functions")
	void testSaveLoadInStandalone() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("function saveWorkspace()"),
				"Should contain saveWorkspace function");
		assertTrue(html.contains("function loadWorkspace()"),
				"Should contain loadWorkspace function");
		assertTrue(html.contains("Blockly.serialization.workspaces.save"),
				"Should use Blockly serialization API for save");
		assertTrue(html.contains("Blockly.serialization.workspaces.load"),
				"Should use Blockly serialization API for load");
	}

	@Test
	@DisplayName("Editor HTML contains save/load buttons")
	void testSaveLoadButtonsInEditor() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_editor.html");
		assertTrue(html.contains("saveWorkspace()"), "Should have save button");
		assertTrue(html.contains("loadWorkspace()"), "Should have load button");
		assertTrue(html.contains("<script src=\"validation_runtime.js\"></script>"),
				"Modular editor should load validation runtime before validations");
		assertTrue(html.indexOf("validation_runtime.js") < html.indexOf("T_validations.js"),
				"Validation runtime must be loaded before generated validation logic");
	}

	@Test
	@DisplayName("Save filename uses domain name")
	void testSaveFilename() {
		BlocklyEditorSpec spec = minimalSpec("SampleDomain");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("SampleDomain_standalone.html");
		assertTrue(html.contains("SampleDomain_workspace.json"),
				"Save file should use domain name");
	}

	// ═══════════════════════════════════════════════════════════════
	// 13. HTML: Export JSON / XMI
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Export functions use domain name and namespace")
	void testExportFunctions() {
		BlocklyEditorSpec spec = minimalSpec("MyDomain");
		spec.setNsURI("http://example.org/mydomain");
		spec.setNsPrefix("md");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("MyDomain_standalone.html");
		assertTrue(html.contains("function exportJSON()"));
		assertTrue(html.contains("function exportXMI()"));
		assertTrue(html.contains("function exportCode()"));
		assertTrue(html.contains("function modelToDomainXMI(model)"));
		assertTrue(html.contains("xmlns:xmi=\"http://www.omg.org/XMI\""));
		assertTrue(html.contains("xmi:id="));
		assertFalse(html.contains("function jsonToXMI("),
				"XMI export should use the EditorSpec-aware domain instance serializer");
		assertTrue(html.contains("MyDomain_model.json"));
		assertTrue(html.contains("MyDomain_model.xmi"));
		assertTrue(html.contains("MyDomain_code."));
		assertTrue(html.contains("http://example.org/mydomain"));
		assertTrue(html.contains("'md'"));
	}

	@Test
	@DisplayName("Domain code generator emits template metadata and renderer")
	void testDomainCodeGenerationHelpers() {
		BlocklyEditorSpec spec = minimalSpec("Robot");
		spec.setCodeLanguage("javascript");
		spec.setCodeFileExtension("js");
		BlockTypeSpec bt = makeBlock("Move", "Move", 200,
				BlockTypeSpec.ConnectionType.FREE);
		bt.setCodeTemplate("move({{steps}});");
		bt.getFields().add(makeField("steps", FieldSpec.FieldType.INTEGER, "10"));
		spec.getBlockTypes().add(bt);

		String generators = generator.generate(spec).get("Robot_generators.js");
		String standalone = generator.generate(spec).get("Robot_standalone.html");

		assertTrue(generators.contains("window.BLOCKLY_DOMAIN_CODEGEN"));
		assertTrue(generators.contains("language: 'javascript'"));
		assertTrue(generators.contains("fileExtension: 'js'"));
		assertTrue(generators.contains("fieldTypes: {'steps': 'INTEGER'}"));
		assertTrue(generators.contains("template: 'move({{steps}});'"));
		assertTrue(generators.contains("function generateDomainCode(workspace)"));
		assertTrue(generators.contains("function renderFallbackDomainBlock(block, config)"));
		assertTrue(standalone.contains("<button onclick=\"exportCode()\""));
		assertTrue(standalone.contains("var code = generateDomainCode(workspace);"));
		assertTrue(standalone.contains("if (!confirmExportIfInvalid(workspace)) return;"));
	}

	@Test
	@DisplayName("Domain code templates escape apostrophes in generated JavaScript")
	void testDomainCodeTemplateEscapesApostrophes() {
		BlocklyEditorSpec spec = minimalSpec("Robot");
		BlockTypeSpec bt = makeBlock("Say", "Say", 200,
				BlockTypeSpec.ConnectionType.FREE);
		bt.setCodeTemplate("console.log('{{message}}');");
		bt.getFields().add(makeField("message", FieldSpec.FieldType.TEXT, "hello"));
		spec.getBlockTypes().add(bt);

		String generators = generator.generate(spec).get("Robot_generators.js");

		assertTrue(generators.contains("template: 'console.log(\\'{{message}}\\');'"));
	}

	// ═══════════════════════════════════════════════════════════════
	// 14. XMI: value input objects serialized correctly
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("XMI export handles nested value objects")
	void testXmiValueObjects() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("typeof val === 'object' && val._type"),
				"XMI export should handle value input objects");
	}

	// ═══════════════════════════════════════════════════════════════
	// 15. VALIDATIONS
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("MUST_FOLLOW validation generates workspace listener")
	void testMustFollowValidation() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("A", "A", 200,
				BlockTypeSpec.ConnectionType.FREE));
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.MUST_FOLLOW);
		vs.setName("test");
		vs.setTargetType("B");
		vs.setPredecessorType("A");
		spec.getValidations().add(vs);

		String validations = generator.generate(spec).get("T_validations.js");
		assertTrue(validations.contains("function computeValidationWarnings(workspace)"));
		assertTrue(validations.contains("function applyValidationWarnings(workspace)"));
		assertTrue(validations.contains("function confirmExportIfInvalid(workspace)"));
		assertTrue(validations.contains("function computeRuntimeValidationWarnings(workspace, queueWarn)"));
		assertTrue(validations.contains("if (computeRuntimeValidationWarnings(workspace, queueWarn)) return warnings;"));
		assertTrue(validations.contains("block.type === 'B'"));
		assertTrue(validations.contains("prev.type !== 'A'"));
		assertTrue(validations.contains("setWarningText"));
	}

	@Test
	@DisplayName("CARDINALITY validation generates count check")
	void testCardinalityValidation() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Parent", "Parent", 200,
				BlockTypeSpec.ConnectionType.NONE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.CARDINALITY);
		vs.setName("test");
		vs.setOwnerType("Parent");
		vs.setContainmentName("items");
		vs.setLowerBound(1);
		vs.setUpperBound(10);
		spec.getValidations().add(vs);

		String validations = generator.generate(spec).get("T_validations.js");
		assertTrue(validations.contains("block.type === 'Parent'"));
		assertTrue(validations.contains("getInputTargetBlock('items')"));
		assertTrue(validations.contains("count < 1"));
		assertTrue(validations.contains("count > 10"));
	}

	// ═══════════════════════════════════════════════════════════════
	// 20. NEW: REQUIRED VALIDATION
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("REQUIRED validation generates field empty check")
	void testRequiredValidation() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.REQUIRED);
		vs.setName("Task_name_required");
		vs.setOwnerType("Task");
		vs.setFieldName("name");
		vs.setFieldKind("attribute");
		spec.getValidations().add(vs);

		String validations = generator.generate(spec).get("T_validations.js");
		assertTrue(validations.contains("block.type === 'Task'"),
				"Should check block type");
		assertTrue(validations.contains("getFieldValue('name')"),
				"Should check field value");
		assertTrue(validations.contains("is required"),
				"Should show required message");

		String model = generator.generate(spec).get("validation_blocks.json");
		assertTrue(model.contains("\"runtimeExpression\": \"has(\\\"name\\\")\""),
				"Validation block model should synthesize runtime expression from plain ValidationSpec");
	}

	@Test
	@DisplayName("Visual validation block model is generated from validation expression")
	void testValidationBlockModelGenerated() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName("Task_estimate_range");
		vs.setOwnerType("Task");
		vs.setMessage("Estimate must be at least 1.");
		vs.setVisualExpression(ValidationExpressionSpec.compare(
			"GTE",
			ValidationExpressionSpec.fieldNumber("estimateHours"),
			ValidationExpressionSpec.number("1")
		));
		spec.getValidations().add(vs);

			String model = generator.generate(spec).get("validation_blocks.json");
			assertTrue(model.contains("\"format\": \"model2blockly.validationBlocks.v1\""));
			assertTrue(model.contains("\"blockDefinitions\""));
			assertTrue(model.contains("\"message0\": \"%1.%2 is required\""));
			assertTrue(model.contains("\"args0\": [{\"type\": \"field_input\", \"name\": \"NAME\""));
			assertTrue(model.contains("\"type\": \"validation_rule\""));
			assertTrue(model.contains("\"type\": \"logic_compare\""));
			assertTrue(model.contains("\"type\": \"validation_field_number\""));
		assertTrue(model.contains("\"type\": \"math_number\""));
		assertTrue(model.contains("\"OP\": \"GTE\""));
	}

	@Test
	@DisplayName("Visual validation block model prefers high-level custom block and keeps expanded expression")
	void testValidationBlockModelPrefersHighLevelVisualBlock() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.REQUIRED);
		vs.setName("Task_name_required");
		vs.setOwnerType("Task");
		vs.setFieldName("name");
		vs.setFieldKind("attribute");
		vs.setVisualBlock(ValidationBlockSpec.requiredField("Task", "name", "attribute"));
		vs.setVisualExpression(ValidationExpressionSpec.fieldExists("name"));
		spec.getValidations().add(vs);

		String model = generator.generate(spec).get("validation_blocks.json");
		assertTrue(model.contains("\"type\": \"validation_required_field\""));
		assertTrue(model.contains("\"TYPE\": \"Task\""));
		assertTrue(model.contains("\"FIELD\": \"name\""));
		assertTrue(model.contains("\"FIELD_KIND\": \"attribute\""));
		assertTrue(model.contains("\"expandedExpression\""));
		assertTrue(model.contains("\"type\": \"validation_field_exists\""));
		assertTrue(model.contains("\"generator\""));
		assertTrue(model.contains("\"format\": \"model2blockly.validationGenerator.v1\""));
		assertTrue(model.contains("\"runtimeCheck\": \"requiredField\""));
		assertTrue(model.contains("\"runtimeExpression\": \"has(\\\"name\\\")\""));
	}

	@Test
	@DisplayName("Visual validation workspace renders validation blocks with Blockly")
	void testValidationWorkspaceHtmlGenerated() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.EXPRESSION);
		vs.setName("Task_estimate_range");
		vs.setOwnerType("Task");
		vs.setVisualExpression(ValidationExpressionSpec.compare(
			"GTE",
			ValidationExpressionSpec.fieldNumber("estimateHours"),
			ValidationExpressionSpec.number("1")
		));
		spec.getValidations().add(vs);

		String workspace = generator.generate(spec).get("validation_workspace.html");
		assertTrue(workspace.contains("const VALIDATION_BLOCK_MODEL = JSON.parse("));
		assertTrue(workspace.contains("Blockly.inject(\"validationBlockly\""));
		assertTrue(workspace.contains("readOnly: false"));
			assertTrue(workspace.contains("toolbox: VALIDATION_TOOLBOX"));
			assertTrue(workspace.contains("trashcan: true"));
			assertTrue(workspace.contains("let activeValidationBlockModel = VALIDATION_BLOCK_MODEL"));
			assertTrue(workspace.contains("defineMissingJsonBlocks(validationBlockDefinitions(VALIDATION_BLOCK_MODEL))"));
			assertTrue(workspace.contains("function validationBlockDefinitions(model)"));
			assertTrue(workspace.contains("function workspaceToValidationBlockModel(workspace)"));
			assertTrue(workspace.contains("blockDefinitions: validationBlockDefinitions(sourceModel)"));
			assertTrue(workspace.contains("workspaceState: blocklyWorkspaceState(workspace)"));
		assertTrue(workspace.contains("function blocklyWorkspaceState(workspace)"));
		assertTrue(workspace.contains("Blockly.serialization.workspaces.save"));
		assertTrue(workspace.contains("Blockly.serialization.workspaces.load"));
		assertTrue(workspace.contains("function loadValidationBlockModel(workspace, model)"));
		assertTrue(workspace.contains("function importValidationJsonFile(workspace, input)"));
		assertTrue(workspace.contains("function blockToModelNode(block)"));
		assertTrue(workspace.contains("function downloadText(filename, text, mimeType)"));
		assertTrue(workspace.contains("function validationRuntimeJs(workspace)"));
		assertTrue(workspace.contains("id=\"importJson\""));
		assertTrue(workspace.contains("id=\"downloadWorkspace\""));
		assertTrue(workspace.contains("id=\"importJsonFile\""));
		assertTrue(workspace.contains("validation_blocks.edited.json"));
		assertTrue(workspace.contains("validation_workspace.state.json"));
		assertTrue(workspace.contains("validation_runtime.edited.js"));
		assertTrue(workspace.contains("window.validationWorkspaceState = function()"));
		assertTrue(workspace.contains("window.validationRuntimeJs = function()"));
		assertTrue(workspace.contains("root.Model2BlocklyValidationRuntime = api"));
		assertTrue(workspace.contains("\"type\": \"validation_rule\""));
		assertTrue(workspace.contains("\"type\": \"logic_compare\""));
		assertTrue(workspace.contains("\"type\": \"validation_field_number\""));
	}

	@Test
	@DisplayName("Validation runtime compiles visual blocks to runtime expressions")
	void testValidationRuntimeGenerated() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec vs = new ValidationSpec();
		vs.setType(ValidationSpec.ValidationType.REQUIRED);
		vs.setName("Task_name_required");
		vs.setOwnerType("Task");
		vs.setFieldName("name");
		vs.setFieldKind("attribute");
		vs.setVisualBlock(ValidationBlockSpec.requiredField("Task", "name", "attribute"));
		spec.getValidations().add(vs);

		String runtime = generator.generate(spec).get("validation_runtime.js");
		assertTrue(runtime.contains("const VALIDATION_BLOCK_MODEL = JSON.parse("));
		assertTrue(runtime.contains("function blockTreeToRuntimeExpression(node)"));
		assertTrue(runtime.contains("function generateValidationRuntimeRules(model = VALIDATION_BLOCK_MODEL)"));
		assertTrue(runtime.contains("function evaluateRuntimeExpression(block, workspace, expression)"));
		assertTrue(runtime.contains("runtimeExpression"));
		assertTrue(runtime.contains("has(\\\\\\\"name\\\\\\\")"));
		assertTrue(runtime.contains("root.Model2BlocklyValidationRuntime = api"));
	}

	@Test
	@DisplayName("Visual validation block model serializes unique validation accessors")
	void testValidationBlockModelSerializesUniqueAccessors() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		ValidationSpec idUnique = new ValidationSpec();
		idUnique.setType(ValidationSpec.ValidationType.UNIQUE);
		idUnique.setName("Task_id_unique");
		idUnique.setOwnerType("Task");
		idUnique.setFieldName("id");
		idUnique.setFieldKind("id");
		idUnique.setVisualBlock(ValidationBlockSpec.typeFieldUnique("Task", "id"));
		idUnique.setVisualExpression(ValidationExpressionSpec.typeFieldUnique("Task", "id"));
		spec.getValidations().add(idUnique);

		ValidationSpec tagsUnique = new ValidationSpec();
		tagsUnique.setType(ValidationSpec.ValidationType.UNIQUE);
		tagsUnique.setName("Task_tags_unique");
		tagsUnique.setOwnerType("Task");
		tagsUnique.setFieldName("tags");
		tagsUnique.setFieldKind("attribute");
		tagsUnique.setVisualBlock(ValidationBlockSpec.fieldUnique("Task", "tags", "attribute"));
		tagsUnique.setVisualExpression(ValidationExpressionSpec.fieldUnique("tags"));
		spec.getValidations().add(tagsUnique);

		String model = generator.generate(spec).get("validation_blocks.json");
		assertTrue(model.contains("\"type\": \"validation_type_field_unique\""));
		assertTrue(model.contains("\"TYPE\": \"Task\""));
		assertTrue(model.contains("\"FIELD\": \"id\""));
		assertTrue(model.contains("\"type\": \"validation_field_unique\""));
		assertTrue(model.contains("\"FIELD\": \"tags\""));
		assertTrue(model.contains("\"runtimeCheck\": \"typeFieldUnique\""));
		assertTrue(model.contains("\"runtimeExpression\": \"typeFieldUnique(\\\"Task\\\", \\\"id\\\")\""));
		assertTrue(model.contains("\"runtimeCheck\": \"fieldUnique\""));
		assertTrue(model.contains("\"runtimeExpression\": \"fieldUnique(\\\"tags\\\")\""));
	}

	@Test
	@DisplayName("No validations generates helper functions returning empty warnings")
	void testNoValidationsReturnsEmptyWarnings() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String validations = generator.generate(spec).get("T_validations.js");
		assertTrue(validations.contains("function computeValidationWarnings(workspace)"));
		assertTrue(validations.contains("return warnings;"),
				"Empty validations should return an empty warnings array");
		assertTrue(validations.contains("return true;"),
				"Export confirmation helper should allow export when there are no warnings");
	}

	@Test
	@DisplayName("Standalone export checks validations before downloading")
	void testStandaloneExportConfirmsInvalidModel() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("Task", "Task", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String standalone = generator.generate(spec).get("T_standalone.html");
		assertTrue(standalone.contains("function exportJSON()"));
		assertTrue(standalone.contains("function exportXMI()"));
		assertTrue(standalone.contains("if (!confirmExportIfInvalid(workspace)) return;"));
		assertTrue(standalone.contains("root.Model2BlocklyValidationRuntime = api"));
		assertTrue(standalone.contains("/* ═══ 5. VALIDATION BLOCK RUNTIME ═══ */"));
	}

	// ═══════════════════════════════════════════════════════════════
	// 16. REFERENCE FIELDS (dynamic dropdown)
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Reference field generates dynamic dropdown update script")
	void testReferenceFieldDynamic() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Widget", "Widget", 200,
				BlockTypeSpec.ConnectionType.FREE);
		ReferenceFieldSpec rfs = new ReferenceFieldSpec();
		rfs.setName("target");
		rfs.setTargetTypeName("OtherType");
		rfs.setReferenceLabelField("displayName");
		bt.getReferenceFields().add(rfs);
		spec.getBlockTypes().add(bt);

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("updateReferenceDropdowns"),
				"Should contain reference update function");
		assertTrue(html.contains("'OtherType'"),
				"Should reference the target type name");
		assertTrue(html.contains("var labelField = 'displayName';"),
				"Should use configured reference label field");
		assertTrue(html.contains("referenceOption(targets[i], labelField)")
				|| html.contains("referenceOption(targetBlock, labelField)"),
				"Should compute dropdown labels per reference field");
	}

	// ═══════════════════════════════════════════════════════════════
	// 17. STATEMENT INPUT
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Statement input generates input_statement with check type")
	void testStatementInput() {
		BlocklyEditorSpec spec = minimalSpec("T");
		BlockTypeSpec bt = makeBlock("Container", "Container", 200,
				BlockTypeSpec.ConnectionType.NONE);
		StatementInputSpec sis = new StatementInputSpec();
		sis.setName("children");
		sis.setCheckType("Child");
		bt.getStatementInputs().add(sis);
		spec.getBlockTypes().add(bt);

		String blocks = generator.generate(spec).get("T_blocks.js");
		assertTrue(blocks.contains("\"type\": \"input_statement\""));
		assertTrue(blocks.contains("\"name\": \"children\""));
		assertTrue(blocks.contains("\"check\": \"Child\""));
	}

	// ═══════════════════════════════════════════════════════════════
	// 18. WORKSPACE OPTIONS (zoom, trashcan, move)
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Standalone HTML contains standard workspace options")
	void testWorkspaceOptions() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("zoom:"));
		assertTrue(html.contains("trashcan: true"));
		assertTrue(html.contains("move:"));
		assertTrue(html.contains("scrollbars: true"));
	}

	@Test
	@DisplayName("Custom workspace options override defaults and add new keys")
	void testWorkspaceOptionsMergeOverride() {
		BlocklyEditorSpec spec = minimalSpec("T");
		spec.getBlockTypes().add(makeBlock("B", "B", 200,
				BlockTypeSpec.ConnectionType.FREE));

		Map<String, Object> options = new LinkedHashMap<>();
		options.put("renderer", "zelos");
		options.put("trashcan", false);
		Map<String, Object> zoom = new LinkedHashMap<>();
		zoom.put("controls", false);
		zoom.put("maxScale", 5);
		options.put("zoom", zoom); // whole zoom object replaces default zoom
		spec.setWorkspaceOptions(options);

		String html = generator.generate(spec).get("T_standalone.html");
		assertTrue(html.contains("renderer: \"zelos\""));
		assertTrue(html.contains("trashcan: false"));
		assertTrue(html.contains("zoom: { controls: false, maxScale: 5 }"));
		assertTrue(html.contains("move: { scrollbars: true, drag: true, wheel: true }"),
				"Unspecified defaults should be preserved");
	}

	// ═══════════════════════════════════════════════════════════════
	// 19. COMPREHENSIVE: Full scenario with all features
	// ═══════════════════════════════════════════════════════════════

	@Test
	@DisplayName("Full scenario: domain with output blocks, value inputs, all field types")
	void testFullScenario() {
		BlocklyEditorSpec spec = minimalSpec("FullTest");

		CategorySpec cat1 = new CategorySpec();
		cat1.setName("Main");
		cat1.setLabel("Main Blocks");
		cat1.setColour(200);
		spec.getCategories().add(cat1);

		CategorySpec cat2 = new CategorySpec();
		cat2.setName("Exprs");
		cat2.setLabel("Expressions");
		cat2.setColour(120);
		spec.getCategories().add(cat2);

		// Container block (NONE connection)
		BlockTypeSpec container = makeBlock("Program", "Program", 200,
				BlockTypeSpec.ConnectionType.NONE);
		container.setCategoryName("Main");
		container.getFields().add(makeField("name", FieldSpec.FieldType.TEXT, "MyProg"));
		StatementInputSpec sis = new StatementInputSpec();
		sis.setName("steps");
		sis.setCheckType("Step");
		container.getStatementInputs().add(sis);
		spec.getBlockTypes().add(container);

		// Statement block with value input
		BlockTypeSpec action = makeBlock("PrintAction", "Print", 160,
				BlockTypeSpec.ConnectionType.TYPED);
		action.setConnectionTypeName("Step");
		action.setCategoryName("Main");
		action.setInputsInline(Boolean.TRUE);
		ValueInputSpec viAction = new ValueInputSpec();
		viAction.setName("what");
		viAction.setCheckType("Expr");
		viAction.setShadowBlockType("TextExpr");
		action.getValueInputs().add(viAction);
		spec.getBlockTypes().add(action);

		// Output block (value block)
		BlockTypeSpec textExpr = makeBlock("TextExpr", "Text", 120,
				BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
		textExpr.setOutputType("Expr");
		textExpr.setCategoryName("Exprs");
		textExpr.setInputsInline(Boolean.TRUE);
		textExpr.getFields().add(makeField("text", FieldSpec.FieldType.TEXT, "hello"));
		spec.getBlockTypes().add(textExpr);

		// Output block with colour + angle
		BlockTypeSpec styleExpr = makeBlock("StyleExpr", "Style", 60,
				BlockTypeSpec.ConnectionType.OUTPUT_TYPED);
		styleExpr.setOutputType("Expr");
		styleExpr.setCategoryName("Exprs");
		styleExpr.getFields().add(makeField("color", FieldSpec.FieldType.COLOUR, "#ff0000"));
		styleExpr.getFields().add(makeField("rotation", FieldSpec.FieldType.ANGLE, "45"));
		spec.getBlockTypes().add(styleExpr);

		// Abstract block
		BlockTypeSpec absStep = makeBlock("Step", "Step", 0,
				BlockTypeSpec.ConnectionType.FREE);
		absStep.setAbstract(true);
		spec.getBlockTypes().add(absStep);

		// Generate
		Map<String, String> files = generator.generate(spec);
		assertEquals(10, files.size());
		assertTrue(files.containsKey("sample_model.json"));
		assertTrue(files.containsKey("validation_blocks.json"));
		assertTrue(files.containsKey("validation_runtime.js"));
		assertTrue(files.containsKey("validation_workspace.html"));

		String blocks = files.get("FullTest_blocks.js");
		String toolbox = files.get("FullTest_toolbox.js");
		String generators = files.get("FullTest_generators.js");
		String standalone = files.get("FullTest_standalone.html");

		// Verify blocks
		assertTrue(blocks.contains("\"type\": \"Program\""));
		assertTrue(blocks.contains("\"type\": \"PrintAction\""));
		assertTrue(blocks.contains("\"type\": \"TextExpr\""));
		assertTrue(blocks.contains("\"type\": \"StyleExpr\""));
		assertFalse(blocks.contains("\"type\": \"Step\""),
				"Abstract Step should not appear in blocks");

		// Verify connection types
		assertTrue(blocks.contains("\"output\": \"Expr\""),
				"TextExpr should have typed output");
		assertTrue(blocks.contains("\"previousStatement\": \"Step\""));
		assertTrue(blocks.contains("input_value"));
		assertTrue(blocks.contains("input_statement"));
		assertTrue(blocks.contains("field_colour"));
		assertTrue(blocks.contains("field_angle"));

		// Verify inline
		assertTrue(blocks.contains("\"inputsInline\": true"));

		// Verify toolbox has shadow
		assertTrue(toolbox.contains("\"shadow\""));
		assertTrue(toolbox.contains("\"type\": \"TextExpr\""));

		// Verify generators
		assertTrue(generators.contains("valueToCode"),
				"Should use valueToCode for PrintAction's value input");
		assertTrue(generators.contains("return [code, 0]"),
				"Output blocks should return tuple");

		// Verify HTML
		assertTrue(standalone.contains("function saveWorkspace()"));
		assertTrue(standalone.contains("function loadWorkspace()"));
		assertTrue(standalone.contains("function exportJSON()"));
		assertTrue(standalone.contains("function exportXMI()"));
		assertFalse(standalone.contains("grid:"),
				"Grid should be disabled by default unless explicitly configured");
		assertTrue(standalone.contains("zoom:"),
				"Default zoom config should still be present");
	}
}
