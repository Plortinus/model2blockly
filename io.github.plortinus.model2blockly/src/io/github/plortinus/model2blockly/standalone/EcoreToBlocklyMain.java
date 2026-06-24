package io.github.plortinus.model2blockly.standalone;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import io.github.plortinus.model2blockly.adapter.EcoreAdapter;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper;
import io.github.plortinus.model2blockly.generator.BlocklyCodeGenerator;
import io.github.plortinus.model2blockly.generator.GenerationReportHtmlRenderer;
import io.github.plortinus.model2blockly.generator.BlocklySpecModelQueries;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * Standalone entry point: reads an .ecore file and generates a complete
 * Blockly editor. No Xtext or DSL involved.
 *
 * Usage:
 *   java EcoreToBlocklyMain path/to/metamodel.ecore [output-dir]
 *
 * The .ecore file can contain optional EAnnotations whose source/key names
 * stay close to Blockly JSON, e.g. source="blockly" message0, colour,
 * tooltip, helpUrl, inputsInline, output, type=input_value and shadow.
 */
public class EcoreToBlocklyMain {

	public static void main(String[] args) {
		if (args.length < 1 || "--help".equals(args[0]) || "-h".equals(args[0])) {
			printUsage();
			if (args.length < 1) System.exit(1);
			return;
		}

		ArrayList<String> positional = parseArguments(args);
		if (positional.isEmpty()) {
			printUsage();
			System.exit(1);
		}
		if (positional.size() > 2) {
			System.err.println("Unexpected argument: " + positional.get(2));
			printUsage();
			System.exit(1);
		}

		String ecorePath = positional.get(0);
		String outputDir = positional.size() > 1
			? positional.get(1)
			: defaultOutputDir(ecorePath);

		Map<String, String> files;
		try {
			EPackage pkg = loadEPackage(ecorePath);
			System.out.println("Loaded EPackage: " + pkg.getName() + " (" + pkg.getNsURI() + ")");

			EditorSpec intermediateModel = EcoreAdapter.toEditorSpec(pkg);
			System.out.println("Transformed to intermediate EditorSpec: "
				+ BlocklySpecModelQueries.concreteBlockTypes(intermediateModel).size() + " concrete block types, "
				+ intermediateModel.getCategories().size() + " categories");
			BlocklySpecDiagnostics.assertValid(intermediateModel, ecorePath,
				BlocklySpecSourceMapper.forEPackage(pkg, ecorePath));
			String intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel);
			EditorSpec reloadedIntermediateModel = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi);
			BlocklySpecDiagnostics.assertValid(reloadedIntermediateModel, ecorePath + " intermediate XMI");
			System.out.println("Reloaded intermediate XMI for HTML generation.");

			files = generateFiles(reloadedIntermediateModel);
			files.put(BlocklySpecXmiSerializer.generatedIntermediatePath(reloadedIntermediateModel), intermediateXmi);
			files.put("README.md", generateOutputReadme(ecorePath, files.keySet()));
			files.put("generation_report.html", GenerationReportHtmlRenderer.renderEcoreSummary(
				pkg,
				reloadedIntermediateModel,
				ecorePath,
				"html",
				new ArrayList<>(files.keySet())));
		} catch (BlocklySpecDiagnostics.DiagnosticException e) {
			System.err.println(e.getMessage());
			System.exit(3);
			return;
		} catch (Exception e) {
			System.err.println("Failed to load .ecore model: " + e.getMessage());
			e.printStackTrace();
			System.exit(3);
			return;
		}

		File outDir = new File(outputDir);
		outDir.mkdirs();
		deleteStaleRootHtmlFiles(outDir, files);

		boolean writeFailed = false;
		for (Map.Entry<String, String> entry : files.entrySet()) {
			File targetFile = new File(outDir, entry.getKey());
			File parent = targetFile.getParentFile();
			if (parent != null) parent.mkdirs();
			try (FileWriter writer = new FileWriter(targetFile)) {
				writer.write(entry.getValue());
				System.out.println("  -> " + entry.getKey());
			} catch (Exception e) {
				System.err.println("Error writing " + entry.getKey() + ": " + e.getMessage());
				writeFailed = true;
			}
		}

		if (writeFailed) {
			System.err.println("Generation finished with write errors.");
			System.exit(4);
		}
		printSuccess(outDir, files);
	}

	private static ArrayList<String> parseArguments(String[] args) {
		ArrayList<String> positional = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("--html".equals(arg)) {
				continue;
			}
			if ("--target".equals(arg)) {
				if (i + 1 >= args.length) {
					printUsage();
					System.exit(1);
				}
				String target = args[++i];
				if (!"html".equals(target)) {
					System.err.println("Unsupported target: " + target + ". Only HTML output is supported.");
					System.exit(2);
				}
				continue;
			}
			if (arg.startsWith("--")) {
				System.err.println("Unsupported option: " + arg + ". Only HTML output is supported.");
				System.exit(2);
			}
			positional.add(arg);
		}
		return positional;
	}

	private static void printUsage() {
		System.err.println("Usage: EcoreToBlocklyMain <ecore-file> [output-dir]");
		System.err.println();
		System.err.println("Recommended Eclipse workflow:");
		System.err.println("  Run -> Run Configurations... -> Java Application");
		System.err.println("  Select one of the Generate ... from Ecore launch configurations.");
		System.err.println();
		System.err.println("If output-dir is omitted, files are written next to the input as <name>_generated/.");
	}

	private static Map<String, String> generateFiles(EditorSpec spec) {
		Map<String, String> files = new LinkedHashMap<>();
		new BlocklyCodeGenerator().generate(spec)
			.forEach((path, content) -> files.put("html/" + path, content));
		return files;
	}

	private static String defaultOutputDir(String inputPath) {
		File input = new File(inputPath);
		String name = input.getName();
		int dot = name.lastIndexOf('.');
		if (dot > 0) name = name.substring(0, dot);
		File parent = input.getParentFile();
		return new File(parent != null ? parent : new File("."), name + "_generated").getPath();
	}

	private static void deleteStaleRootHtmlFiles(File outDir, Map<String, String> files) {
		for (String path : files.keySet()) {
			if (!path.startsWith("html/")) continue;
			File staleRootFile = new File(outDir, path.substring("html/".length()));
			if (staleRootFile.isFile() && !staleRootFile.delete()) {
				System.err.println("Warning: could not delete stale root HTML artifact: " + staleRootFile.getPath());
			}
		}
	}

	private static String generateOutputReadme(String ecorePath, Iterable<String> generatedFiles) {
		String standalone = findStandalone(generatedFiles);
		String sampleModel = findSampleModel(generatedFiles);
		String validationWorkspace = findValidationWorkspace(generatedFiles);
		String intermediate = findIntermediateModel(generatedFiles);
		StringBuilder out = new StringBuilder();
		out.append("# Generated Blockly Editor\n\n");
		out.append("Input Ecore metamodel: `").append(ecorePath).append("`\n\n");
		out.append("Output: EMF EditorSpec intermediate XMI and HTML Blockly editor\n\n");
		out.append("## Start Here\n\n");
		int step = 1;
		out.append(step++).append(". Open `generation_report.html` to inspect the Ecore -> Blockly mapping.\n");
		if (intermediate != null) {
			out.append(step++).append(". Inspect `").append(intermediate)
				.append("`, the generated EMF/XMI `EditorSpec` intermediate model.\n");
		}
			if (standalone != null) {
				out.append(step++).append(". Open `").append(standalone).append("` in a browser to use the generated editor.\n");
				out.append(step++).append(". Click `Load Sample` or import `")
					.append(sampleModel != null ? sampleModel : "sample_model.json")
					.append("` to inspect a representative model.\n");
				out.append(step++).append(". Edit the model, then export JSON/XML/XMI or code from the toolbar.\n");
				if (validationWorkspace != null) {
					out.append(step++).append(". Open `").append(validationWorkspace).append("` to inspect generated validation rules as Blockly blocks.\n");
					out.append(step++).append(". Use `Download Source Snippets` in the validation workspace to export Ecore/DSL validation snippets from edited rules.\n");
				}
			} else {
				out.append(step++).append(". Open the generated HTML output.\n");
			}
		out.append("\n## Typical Iteration\n\n");
		out.append("- If the structure is correct but the blocks are generic, add `source=\"blockly\"` annotations for labels, colours, `message0` or tooltips.\n");
		out.append("- If field layout or inspector metadata should improve, add `source=\"ui\"` annotations.\n");
		out.append("- If code export should be domain-specific, add `source=\"code\"` annotations or templates.\n");
		out.append("- Rerun the same Eclipse launch configuration and reopen `generation_report.html`.\n");
		return out.toString();
	}

	private static String findStandalone(Iterable<String> files) {
		for (String file : files) {
			if (file.endsWith("_standalone.html")) return file;
		}
		return null;
	}

	private static String findSampleModel(Iterable<String> files) {
		for (String file : files) {
			if (file.endsWith("sample_model.json")) return file;
		}
		return null;
	}

	private static String findValidationWorkspace(Iterable<String> files) {
		for (String file : files) {
			if (file.endsWith("validation_workspace.html")) return file;
		}
		return null;
	}

	private static String findIntermediateModel(Iterable<String> files) {
		for (String file : files) {
			if (file.startsWith("intermediate/") && file.endsWith("_blocklyspec.xmi")) return file;
		}
		return null;
	}

	private static void printSuccess(File outDir, Map<String, String> files) {
		System.out.println();
		System.out.println("Generation complete.");
		System.out.println("Output directory: " + outDir.getAbsolutePath());
		System.out.println("Report: " + new File(outDir, "generation_report.html").getAbsolutePath());
		String standalone = findStandalone(files.keySet());
		if (standalone != null) {
			System.out.println("Editor: " + new File(outDir, standalone).getAbsolutePath());
		}
		String intermediate = findIntermediateModel(files.keySet());
		if (intermediate != null) {
			System.out.println("Intermediate EMF/XMI: " + new File(outDir, intermediate).getAbsolutePath());
		}
		String validationWorkspace = findValidationWorkspace(files.keySet());
		if (validationWorkspace != null) {
			System.out.println("Validation workspace: " + new File(outDir, validationWorkspace).getAbsolutePath());
		}
		System.out.println("Next step: open generation_report.html, then open the standalone editor or validation workspace.");
		System.out.println("Files generated: " + files.size());
	}

	/** Load .ecore from disk; first root object must be an {@link EPackage}. */
	private static EPackage loadEPackage(String ecorePath) {
		Resource.Factory.Registry.INSTANCE
			.getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(new File(ecorePath).getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		return (EPackage) resource.getContents().get(0);
	}
}
