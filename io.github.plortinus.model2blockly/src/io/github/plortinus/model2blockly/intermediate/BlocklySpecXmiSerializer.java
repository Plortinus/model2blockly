package io.github.plortinus.model2blockly.intermediate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlocklySpecPackage;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * XMI utilities for the generated EMF BlocklyEditorSpec intermediate model.
 */
public final class BlocklySpecXmiSerializer {

	private BlocklySpecXmiSerializer() {
	}

	public static String toXmi(BlocklyEditorSpec spec) {
		return toXmi(toEmfModel(spec));
	}

	public static EditorSpec toEmfModel(BlocklyEditorSpec spec) {
		try {
			return BlocklySpecModelMapper.toEmfSpec(spec);
		} catch (Exception e) {
			throw new IllegalStateException("Could not transform BlocklyEditorSpec to EMF model: " + e.getMessage(), e);
		}
	}

	public static String toXmi(EObject editorSpec) {
		return toXmi(asEditorSpec(editorSpec));
	}

	public static String toXmi(EditorSpec editorSpec) {
		try {
			return serialize(editorSpec);
		} catch (Exception e) {
			throw new IllegalStateException("Could not serialize intermediate EMF model as XMI: " + e.getMessage(), e);
		}
	}

	public static BlocklyEditorSpec fromXmi(String xmi) {
		return BlocklySpecModelMapper.toJavaSpec(toEmfModel(xmi));
	}

	public static EditorSpec fromXmiToEditorSpec(String xmi) {
		return toEmfModel(xmi);
	}

	public static EditorSpec toEmfModel(String xmi) {
		try {
			return deserialize(xmi);
		} catch (Exception e) {
			throw new IllegalStateException("Could not load intermediate EMF model from XMI: " + e.getMessage(), e);
		}
	}

	public static String generatedIntermediatePath(BlocklyEditorSpec spec) {
		String name = spec != null && spec.getDomainName() != null && !spec.getDomainName().isBlank()
			? spec.getDomainName()
			: "domain";
		return generatedIntermediatePath(name);
	}

	public static String generatedIntermediatePath(EditorSpec spec) {
		String name = spec != null && spec.getDomainName() != null && !spec.getDomainName().isBlank()
			? spec.getDomainName()
			: "domain";
		return generatedIntermediatePath(name);
	}

	public static BlocklyEditorSpec toJavaSpec(EObject editorSpec) {
		return BlocklySpecModelMapper.toJavaSpec(asEditorSpec(editorSpec));
	}

	private static String generatedIntermediatePath(String domainName) {
		return "intermediate/" + domainName + "_blocklyspec.xmi";
	}

	private static String serialize(EditorSpec editorSpec) throws Exception {
		ResourceSet resourceSet = newResourceSet();
		Resource resource = createXmiResource(resourceSet);
		resource.getContents().add(editorSpec);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map<String, Object> options = new LinkedHashMap<>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		resource.save(out, options);
		return out.toString(StandardCharsets.UTF_8);
	}

	private static EditorSpec deserialize(String xmi) throws Exception {
		ResourceSet resourceSet = newResourceSet();
		Resource resource = createXmiResource(resourceSet);
		try (ByteArrayInputStream in = new ByteArrayInputStream(xmi.getBytes(StandardCharsets.UTF_8))) {
			resource.load(in, null);
		}
		if (resource.getContents().isEmpty()) {
			throw new IllegalArgumentException("Intermediate XMI does not contain an EditorSpec root.");
		}
		return asEditorSpec(resource.getContents().get(0));
	}

	private static ResourceSet newResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(BlocklySpecPackage.eNS_URI, BlocklySpecPackage.eINSTANCE);
		return resourceSet;
	}

	private static Resource createXmiResource(ResourceSet resourceSet) {
		Resource resource = new XMIResourceImpl(URI.createURI("memory:/blocklyspec.xmi"));
		resourceSet.getResources().add(resource);
		return resource;
	}

	private static EditorSpec asEditorSpec(EObject object) {
		if (object instanceof EditorSpec) {
			return (EditorSpec) object;
		}
		String rootName = object != null && object.eClass() != null ? object.eClass().getName() : "null";
		throw new IllegalArgumentException("Expected generated EditorSpec root, got " + rootName + ".");
	}
}
