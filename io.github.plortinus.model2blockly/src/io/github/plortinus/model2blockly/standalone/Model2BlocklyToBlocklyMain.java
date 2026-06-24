package io.github.plortinus.model2blockly.standalone;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import io.github.plortinus.model2blockly.Model2BlocklyStandaloneSetup;
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper;
import io.github.plortinus.model2blockly.generator.BlocklyCodeGenerator;
import io.github.plortinus.model2blockly.generator.GenerationReportHtmlRenderer;
import io.github.plortinus.model2blockly.generator.BlocklySpecModelQueries;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.validation.Model2BlocklyValidationDiagnostics;

import com.google.inject.Injector;

/**
 * Standalone entry point: reads a .model2blockly file and generates a complete
 * Blockly editor plus generation_report.html.
 *
 * Usage:
 *   java Model2BlocklyToBlocklyMain path/to/domain.model2blockly [output-dir]
 */
public class Model2BlocklyToBlocklyMain {

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

		String dslPath = positional.get(0);
		String outputDir = positional.size() > 1
			? positional.get(1)
			: defaultOutputDir(dslPath);

		Map<String, String> files = new LinkedHashMap<>();
		try {
			DomainModel domain = loadDomainModel(dslPath);
			System.out.println("Loaded DomainModel: " + domain.getName());

			EditorSpec intermediateModel = DomainModelAdapter.toEditorSpec(domain);
			System.out.println("Transformed to intermediate EditorSpec: "
				+ BlocklySpecModelQueries.concreteBlockTypes(intermediateModel).size() + " concrete block types, "
				+ intermediateModel.getCategories().size() + " top-level categories");
			BlocklySpecDiagnostics.assertValid(intermediateModel, dslPath,
				BlocklySpecSourceMapper.forDomainModel(domain, dslPath));
			String intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel);
			EditorSpec reloadedIntermediateModel = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi);
			BlocklySpecDiagnostics.assertValid(reloadedIntermediateModel, dslPath + " intermediate XMI");
			System.out.println("Reloaded intermediate XMI for HTML generation.");

			files.putAll(generateFiles(reloadedIntermediateModel));
			files.put(BlocklySpecXmiSerializer.generatedIntermediatePath(reloadedIntermediateModel), intermediateXmi);
			files.put("README.md", generateOutputReadme(dslPath, files.keySet()));
			files.put("generation_report.html", GenerationReportHtmlRenderer.renderModel2BlocklySummary(
				domain,
				reloadedIntermediateModel,
				dslPath,
				new ArrayList<>(files.keySet())));
		} catch (BlocklySpecDiagnostics.DiagnosticException e) {
			System.err.println(e.getMessage());
			System.exit(3);
			return;
		} catch (Model2BlocklyValidationDiagnostics.ValidationException e) {
			System.err.println(e.getMessage());
			System.exit(3);
			return;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(3);
			return;
		} catch (Exception e) {
			System.err.println("Failed to load .model2blockly model: " + e.getMessage());
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
		System.err.println("Usage: Model2BlocklyToBlocklyMain <model2blockly-file> [output-dir]");
		System.err.println();
		System.err.println("Recommended Eclipse workflow:");
		System.err.println("  Run -> Run Configurations... -> Java Application");
		System.err.println("  Select Generate AppMaker from Model2Blockly or another Generate ... launch configuration.");
		System.err.println();
		System.err.println("If output-dir is omitted, files are written next to the input as <name>_generated/.");
	}

	private static DomainModel loadDomainModel(String dslPath) {
		Injector injector = new Model2BlocklyStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet resourceSet = injector.getInstance(ResourceSet.class);
		URI uri = URI.createFileURI(new File(dslPath).getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		if (resource instanceof XtextResource) {
			((XtextResource) resource).validateConcreteSyntax();
			Model2BlocklyValidationDiagnostics.throwIfSyntaxErrors(dslPath, resource.getErrors());
			Model2BlocklyValidationDiagnostics.throwIfErrors(dslPath,
				((XtextResource) resource).getResourceServiceProvider()
				.getResourceValidator()
				.validate(resource, org.eclipse.xtext.validation.CheckMode.ALL, CancelIndicator.NullImpl));
		}
		if (!resource.getAllContents().hasNext()) {
			throw new IllegalArgumentException("No DomainModel found in " + dslPath);
		}
		Object root = resource.getAllContents().next();
		if (!(root instanceof DomainModel)) {
			throw new IllegalArgumentException("First model element is not a DomainModel in " + dslPath);
		}
		return (DomainModel) root;
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

	private static String generateOutputReadme(String dslPath, Iterable<String> generatedFiles) {
		String standalone = findStandalone(generatedFiles);
		String sampleModel = findSampleModel(generatedFiles);
		String validationWorkspace = findValidationWorkspace(generatedFiles);
		String intermediate = findIntermediateModel(generatedFiles);
		StringBuilder out = new StringBuilder();
		out.append("# Generated Blockly Editor\n\n");
		out.append("Input DSL model: `").append(dslPath).append("`\n\n");
		out.append("Output: EMF EditorSpec intermediate XMI and HTML Blockly editor\n\n");
		out.append("## Start Here\n\n");
		int step = 1;
		out.append(step++).append(". Open `generation_report.html` to inspect the DSL -> Blockly mapping.\n");
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
		out.append("- Edit the `.model2blockly` source if the domain structure should change.\n");
		out.append("- Use labels, colours, widgets, `referenceLabelField`, `shadow` and `code` templates to improve the generated editor.\n");
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
}
