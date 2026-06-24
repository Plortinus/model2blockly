package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.*;

import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.model2Blockly.Attribute;
import io.github.plortinus.model2blockly.model2Blockly.Model2BlocklyFactory;
import io.github.plortinus.model2blockly.model2Blockly.ClassDef;
import io.github.plortinus.model2blockly.model2Blockly.Containment;
import io.github.plortinus.model2blockly.model2Blockly.ConstraintDef;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.model2Blockly.Reference;
import io.github.plortinus.model2blockly.model2Blockly.SimpleType;
import io.github.plortinus.model2blockly.model2Blockly.SimpleTypeName;
import io.github.plortinus.model2blockly.model2Blockly.UiOptions;
import io.github.plortinus.model2blockly.model2Blockly.ValueInput;
import io.github.plortinus.model2blockly.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValidationExpressionSpec;
import io.github.plortinus.model2blockly.blocklyspec.ValidationSpec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DomainModelAdapterTest {

	private final Model2BlocklyFactory factory = Model2BlocklyFactory.eINSTANCE;

	@Test
	@DisplayName("DSL required attribute maps to FieldSpec and REQUIRED validation")
	void testRequiredAttributeMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("Story");

		ClassDef scene = classDef("Scene");
		Attribute title = factory.createAttribute();
		title.setName("title");
		title.setType(simpleType(SimpleTypeName.STRING));
		title.setRequired(true);
		scene.getFeatures().add(title);
		domain.getClasses().add(scene);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);
		BlockTypeSpec block = spec.getBlockTypes().get(0);

		assertTrue(block.getFields().get(0).isRequired());
		ValidationSpec validation = requiredValidation(spec, "Scene", "title", "attribute");
		assertNotNull(validation);
		assertEquals("validation_required_field", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals(ValidationExpressionSpec.Kind.FIELD_EXISTS, validation.getVisualExpression().getKind());
	}

	@Test
	@DisplayName("DSL required reference maps to ReferenceFieldSpec and REQUIRED validation")
	void testRequiredReferenceMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("SampleDomain");

		ClassDef user = classDef("User");
		ClassDef task = classDef("Task");
		Reference assignee = factory.createReference();
		assignee.setName("assignee");
		assignee.setType(user);
		assignee.setRequired(true);
		task.getFeatures().add(assignee);
		domain.getClasses().add(user);
		domain.getClasses().add(task);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);
		BlockTypeSpec taskBlock = spec.getBlockTypes().stream()
				.filter(block -> "Task".equals(block.getTypeName()))
				.findFirst()
				.orElseThrow();

		assertTrue(taskBlock.getReferenceFields().get(0).isRequired());
		assertTrue(hasRequiredValidation(spec, "Task", "assignee", "reference"));
	}

	@Test
	@DisplayName("DSL must-follow constraint maps to visual previous-block expression")
	void testMustFollowVisualExpressionMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("Story");

		ClassDef alert = classDef("Alert");
		ClassDef navigate = classDef("Navigate");
		ConstraintDef constraint = factory.createConstraintDef();
		constraint.setName("navigate_after_alert");
		constraint.setTarget(navigate);
		constraint.setPredecessor(alert);
		domain.getClasses().add(alert);
		domain.getClasses().add(navigate);
		domain.getConstraints().add(constraint);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);
		ValidationSpec validation = spec.getValidations().stream()
			.filter(v -> v.getType() == ValidationSpec.ValidationType.MUST_FOLLOW)
			.findFirst()
			.orElseThrow();

		assertEquals("Navigate", validation.getTargetType());
		assertEquals("Alert", validation.getPredecessorType());
		assertEquals("validation_previous_block_is", validation.getVisualBlock().getBlocklyBlockType());
		assertEquals(ValidationExpressionSpec.Kind.PREVIOUS_BLOCK_IS, validation.getVisualExpression().getKind());
		assertEquals("Alert", validation.getVisualExpression().getTypeName());
	}

	@Test
	@DisplayName("DSL referenceLabelField maps to ReferenceFieldSpec")
	void testReferenceLabelFieldMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("SampleDomain");

		ClassDef user = classDef("User");
		Attribute displayName = factory.createAttribute();
		displayName.setName("displayName");
		displayName.setType(simpleType(SimpleTypeName.STRING));
		user.getFeatures().add(displayName);

		ClassDef task = classDef("Task");
		Reference assignee = factory.createReference();
		assignee.setName("assignee");
		assignee.setType(user);
		UiOptions ui = factory.createUiOptions();
		ui.setReferenceLabelField("displayName");
		assignee.setUi(ui);
		task.getFeatures().add(assignee);
		domain.getClasses().add(user);
		domain.getClasses().add(task);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);
		BlockTypeSpec taskBlock = spec.getBlockTypes().stream()
				.filter(block -> "Task".equals(block.getTypeName()))
				.findFirst()
				.orElseThrow();

		assertEquals("displayName", taskBlock.getReferenceFields().get(0).getReferenceLabelField());
	}

	@Test
	@DisplayName("DSL feature declaration order is preserved for generated block args")
	void testFeatureDeclarationOrderMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("SampleDomain");

		ClassDef expression = classDef("Expression");
		ClassDef dataSource = classDef("DataSource");
		ClassDef child = classDef("Child");
		ClassDef card = classDef("Card");

		Attribute title = factory.createAttribute();
		title.setName("title");
		title.setType(simpleType(SimpleTypeName.STRING));
		card.getFeatures().add(title);

		ValueInput expressionSlot = factory.createValueInput();
		expressionSlot.setName("expression");
		expressionSlot.setType(expression);
		card.getFeatures().add(expressionSlot);

		Reference source = factory.createReference();
		source.setName("source");
		source.setType(dataSource);
		card.getFeatures().add(source);

		Containment children = factory.createContainment();
		children.setName("children");
		children.setType(child);
		card.getFeatures().add(children);

		domain.getClasses().add(expression);
		domain.getClasses().add(dataSource);
		domain.getClasses().add(child);
		domain.getClasses().add(card);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);
		BlockTypeSpec cardBlock = spec.getBlockTypes().stream()
				.filter(block -> "Card".equals(block.getTypeName()))
				.findFirst()
				.orElseThrow();

		assertEquals(java.util.List.of("title", "expression", "source", "children"),
				cardBlock.getOrderedInputNames());
	}

	@Test
	@DisplayName("DSL code generation metadata maps to BlocklyEditorSpec")
	void testCodeGenerationMetadataMapping() {
		DomainModel domain = factory.createDomainModel();
		domain.setName("Robot");
		domain.setCodeLanguage("javascript");
		domain.setCodeFileExtension("js");

		ClassDef move = classDef("Move");
		move.setCodeTemplate("move({{steps}});");
		Attribute steps = factory.createAttribute();
		steps.setName("steps");
		steps.setType(simpleType(SimpleTypeName.INT));
		move.getFeatures().add(steps);
		domain.getClasses().add(move);

		BlocklyEditorSpec spec = DomainModelAdapter.fromDomainModel(domain);

		assertEquals("javascript", spec.getCodeLanguage());
		assertEquals("js", spec.getCodeFileExtension());
		assertEquals("move({{steps}});", spec.getBlockTypes().get(0).getCodeTemplate());
	}

	private ClassDef classDef(String name) {
		ClassDef cls = factory.createClassDef();
		cls.setName(name);
		return cls;
	}

	private SimpleType simpleType(SimpleTypeName name) {
		SimpleType type = factory.createSimpleType();
		type.setTypeName(name);
		return type;
	}

	private boolean hasRequiredValidation(BlocklyEditorSpec spec, String ownerType, String fieldName, String fieldKind) {
		return requiredValidation(spec, ownerType, fieldName, fieldKind) != null;
	}

	private ValidationSpec requiredValidation(BlocklyEditorSpec spec, String ownerType, String fieldName, String fieldKind) {
		return spec.getValidations().stream().filter(validation ->
			validation.getType() == ValidationSpec.ValidationType.REQUIRED
				&& ownerType.equals(validation.getOwnerType())
				&& fieldName.equals(validation.getFieldName())
				&& fieldKind.equals(validation.getFieldKind()))
			.findFirst()
			.orElse(null);
	}
}
