package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.adapter.EcoreAdapter;
import io.github.plortinus.model2blockly.model2Blockly.Model2BlocklyFactory;
import io.github.plortinus.model2blockly.model2Blockly.ClassDef;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.generator.BlocklyCodeGenerator;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecFactory;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.CategorySpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneratedBlocklySpecModelTest {

	@Test
	@DisplayName("Ecore adapter exposes generated EditorSpec as intermediate model")
	void testEcoreAdapterGeneratedIntermediateModel() {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EPackage pkg = factory.createEPackage();
		pkg.setName("sample");
		pkg.setNsURI("http://test.org/sample");
		pkg.setNsPrefix("sample");
		EClass task = factory.createEClass();
		task.setName("Task");
		pkg.getEClassifiers().add(task);

		EditorSpec spec = EcoreAdapter.toEditorSpec(pkg);
		EObject intermediate = EcoreAdapter.toIntermediateModel(pkg);

		assertInstanceOf(EditorSpec.class, intermediate);
		assertEquals("Sample", spec.getDomainName());
		assertEquals("http://test.org/sample", spec.getNsURI());
		assertEquals(1, spec.getBlockTypes().size());
		assertEquals("Task", spec.getBlockTypes().get(0).getTypeName());
	}

	@Test
	@DisplayName("DSL adapter exposes generated EditorSpec as intermediate model")
	void testDomainModelAdapterGeneratedIntermediateModel() {
		Model2BlocklyFactory factory = Model2BlocklyFactory.eINSTANCE;
		DomainModel domain = factory.createDomainModel();
		domain.setName("Story");
		ClassDef scene = factory.createClassDef();
		scene.setName("Scene");
		domain.getClasses().add(scene);

		EditorSpec spec = DomainModelAdapter.toEditorSpec(domain);
		EObject intermediate = DomainModelAdapter.toIntermediateModel(domain);

		assertInstanceOf(EditorSpec.class, intermediate);
		assertEquals("Story", spec.getDomainName());
		assertEquals("http://www.example.org/story", spec.getNsURI());
		assertEquals(1, spec.getBlockTypes().size());
		assertEquals("Scene", spec.getBlockTypes().get(0).getTypeName());
	}

	@Test
	@DisplayName("Generated BlocklyEditorSpec EMF model can be saved and loaded as XMI")
	void testGeneratedModelXmiRoundTrip() throws Exception {
		BlocklySpecFactory factory = BlocklySpecFactory.eINSTANCE;
		EditorSpec spec = factory.createEditorSpec();
		spec.setDomainName("RoundTrip");
		spec.setNsURI("http://www.example.org/roundtrip");
		spec.setNsPrefix("roundtrip");
		spec.setToolboxType("category");
		spec.setCodeLanguage("javascript");
		spec.setCodeFileExtension("js");

		CategorySpec category = factory.createCategorySpec();
		category.setName("model");
		category.setLabel("Model");
		category.setColour(210);
		spec.getCategories().add(category);

		BlockTypeSpec block = factory.createBlockTypeSpec();
		block.setTypeName("Task");
		block.setLabel("Task");
		block.setCategoryName("model");
		block.setColour(160);
		spec.getBlockTypes().add(block);

		Resource resource = newResourceSet().createResource(URI.createURI("memory:/roundtrip.blocklyspec"));
		resource.getContents().add(spec);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		resource.save(out, null);
		String xmi = out.toString(StandardCharsets.UTF_8);

		assertTrue(xmi.contains("BlocklyEditorSpec"));
		assertTrue(xmi.contains("RoundTrip"));

		Resource loadedResource = newResourceSet().createResource(URI.createURI("memory:/loaded.blocklyspec"));
		loadedResource.load(new ByteArrayInputStream(out.toByteArray()), null);

		EditorSpec reloaded = assertInstanceOf(EditorSpec.class, loadedResource.getContents().get(0));
		assertEquals("RoundTrip", reloaded.getDomainName());
		assertEquals("javascript", reloaded.getCodeLanguage());
		assertEquals(1, reloaded.getCategories().size());
		assertEquals("model", reloaded.getCategories().get(0).getName());
		assertEquals(1, reloaded.getBlockTypes().size());
		assertEquals("Task", reloaded.getBlockTypes().get(0).getTypeName());
	}

	@Test
	@DisplayName("BlocklySpecXmiSerializer reads generated EditorSpec roots")
	void testSerializerGeneratedEditorSpecRoundTrip() {
		EditorSpec spec = BlocklySpecFactory.eINSTANCE.createEditorSpec();
		spec.setDomainName("Generated");
		BlockTypeSpec block = BlocklySpecFactory.eINSTANCE.createBlockTypeSpec();
		block.setTypeName("Node");
		spec.getBlockTypes().add(block);

		String xmi = BlocklySpecXmiSerializer.toXmi(spec);
		EditorSpec reloaded = BlocklySpecXmiSerializer.fromXmiToEditorSpec(xmi);

		assertEquals("Generated", reloaded.getDomainName());
		assertEquals(1, reloaded.getBlockTypes().size());
		assertEquals("Node", reloaded.getBlockTypes().get(0).getTypeName());
	}

	@Test
	@DisplayName("BlocklyCodeGenerator accepts generated EditorSpec directly")
	void testCodeGeneratorConsumesGeneratedEditorSpec() {
		EditorSpec spec = BlocklySpecFactory.eINSTANCE.createEditorSpec();
		spec.setDomainName("Generated");
		BlockTypeSpec block = BlocklySpecFactory.eINSTANCE.createBlockTypeSpec();
		block.setTypeName("Node");
		block.setLabel("Node");
		spec.getBlockTypes().add(block);

		var files = new BlocklyCodeGenerator().generate(spec);

		assertTrue(files.containsKey("Generated_blocks.js"));
		assertTrue(files.containsKey("Generated_standalone.html"));
		assertTrue(files.get("Generated_blocks.js").contains("\"type\": \"Node\""));
	}

	private ResourceSet newResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(BlocklySpecPackage.eNS_URI, BlocklySpecPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("blocklyspec",
				new XMIResourceFactoryImpl());
		return resourceSet;
	}
}
