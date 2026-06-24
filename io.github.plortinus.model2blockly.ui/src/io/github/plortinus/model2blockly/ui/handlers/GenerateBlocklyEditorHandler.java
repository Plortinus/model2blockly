package io.github.plortinus.model2blockly.ui.handlers;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.Issue;
import io.github.plortinus.model2blockly.Model2BlocklyStandaloneSetup;
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.adapter.EcoreAdapter;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.Diagnostic;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.DiagnosticException;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics.SourceLocation;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper;
import io.github.plortinus.model2blockly.generator.BlocklyCodeGenerator;
import io.github.plortinus.model2blockly.generator.GenerationReportHtmlRenderer;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.validation.Model2BlocklyValidationDiagnostics;
import io.github.plortinus.model2blockly.validation.Model2BlocklyValidationDiagnostics.ValidationException;

import com.google.inject.Injector;

/**
 * Workspace popup handler that generates a Blockly HTML editor from a selected
 * .ecore or .model2blockly file.
 */
public class GenerateBlocklyEditorHandler extends AbstractHandler {
	private static final String DIAGNOSTIC_MARKER_SOURCE = "io.github.plortinus.model2blockly.generator.diagnostics";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		IFile selectedFile = selectedFile(event);
		if (selectedFile == null) {
			MessageDialog.openInformation(shell, "Generate Blockly Editor", "Select one .ecore or .model2blockly file.");
			return null;
		}

		String extension = selectedFile.getFileExtension();
		if (!"ecore".equals(extension) && !"model2blockly".equals(extension)) {
			MessageDialog.openInformation(shell, "Generate Blockly Editor",
				"Unsupported file type: " + selectedFile.getName() + "\nSelect a .ecore or .model2blockly file.");
			return null;
		}

		try {
			generate(shell, selectedFile);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause() != null ? e.getCause() : e;
			showGenerationError(shell, selectedFile, cause);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (CoreException e) {
			MessageDialog.openError(shell, "Generate Blockly Editor", e.getMessage());
		}
		return null;
	}

	public static void generate(Shell shell, IFile selectedFile)
			throws InvocationTargetException, InterruptedException, CoreException {
		PlatformUI.getWorkbench().getProgressService().run(true, false,
			new GenerateRunnable(selectedFile));
		clearDiagnosticMarkers(selectedFile);
		selectedFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		openOutputFolder(selectedFile);
		MessageDialog.openInformation(shell, "Generate Blockly Editor",
			"Generated HTML editor, README.md and generation_report.html in:\n"
				+ outputFolder(selectedFile).getFullPath().toString());
	}

	private static IFile selectedFile(ExecutionEvent event) {
		return selectedFile(HandlerUtil.getCurrentSelection(event));
	}

	public static IFile selectedFile(Object selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		IStructuredSelection structured = (IStructuredSelection) selection;
		if (structured.size() != 1) return null;
		Object first = structured.getFirstElement();
		if (first instanceof IFile) return (IFile) first;
		if (first instanceof IAdaptable) return ((IAdaptable) first).getAdapter(IFile.class);
		return null;
	}

	private static IFolder outputFolder(IFile inputFile) {
		String baseName = inputFile.getName();
		int dot = baseName.lastIndexOf('.');
		if (dot > 0) baseName = baseName.substring(0, dot);
		IContainer parent = inputFile.getParent();
		if (parent instanceof IFolder) {
			return ((IFolder) parent).getFolder(baseName + "_generated");
		}
		return inputFile.getProject().getFolder(baseName + "_generated");
	}

	private static void openOutputFolder(IFile inputFile) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.showView("org.eclipse.ui.navigator.ProjectExplorer");
			IDE.openEditor(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
				outputFolder(inputFile).getFile("generation_report.html"),
				true);
		} catch (PartInitException ignored) {
			// The success dialog still tells the user where the output was written.
		}
	}

	private static final class GenerateRunnable implements IRunnableWithProgress {
		private final IFile inputFile;

		private GenerateRunnable(IFile inputFile) {
			this.inputFile = inputFile;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException {
			try {
				monitor.beginTask("Generating Blockly editor", IProgressMonitor.UNKNOWN);
				Map<String, String> files = "ecore".equals(inputFile.getFileExtension())
					? generateFromEcore(inputFile)
					: generateFromModel2Blockly(inputFile);
				writeFiles(inputFile, files, monitor);
			} catch (Exception e) {
				throw new InvocationTargetException(e);
			} finally {
				monitor.done();
			}
		}
	}

	private static Map<String, String> generateFromEcore(IFile inputFile) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(
			URI.createFileURI(inputFile.getLocation().toFile().getAbsolutePath()), true);
		EPackage pkg = (EPackage) resource.getContents().get(0);
		String sourceLabel = inputFile.getProjectRelativePath().toString();
		EditorSpec intermediateModel = EcoreAdapter.toEditorSpec(pkg);
		BlocklySpecDiagnostics.assertValid(intermediateModel, sourceLabel,
			BlocklySpecSourceMapper.forEPackage(pkg, sourceLabel));
		String intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel);
		EditorSpec spec = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi);
		BlocklySpecDiagnostics.assertValid(spec, sourceLabel + " intermediate XMI");
		Map<String, String> files = new LinkedHashMap<>();
		new BlocklyCodeGenerator().generate(spec)
			.forEach((path, content) -> files.put("html/" + path, content));
		files.put(BlocklySpecXmiSerializer.generatedIntermediatePath(spec),
			intermediateXmi);
		files.put("README.md", generateOutputReadme(inputFile, files.keySet()));
		files.put("generation_report.html", GenerationReportHtmlRenderer.renderEcoreSummary(
			pkg,
			spec,
			inputFile.getProjectRelativePath().toString(),
			"html",
			new ArrayList<>(files.keySet())));
		return files;
	}

	private static Map<String, String> generateFromModel2Blockly(IFile inputFile) {
		DomainModel domain = loadDomainModel(inputFile);
		String sourceLabel = inputFile.getProjectRelativePath().toString();
		EditorSpec intermediateModel = DomainModelAdapter.toEditorSpec(domain);
		BlocklySpecDiagnostics.assertValid(intermediateModel, sourceLabel,
			BlocklySpecSourceMapper.forDomainModel(domain, sourceLabel));
		String intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel);
		EditorSpec spec = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi);
		BlocklySpecDiagnostics.assertValid(spec, sourceLabel + " intermediate XMI");
		Map<String, String> files = new LinkedHashMap<>();
		new BlocklyCodeGenerator().generate(spec)
			.forEach((path, content) -> files.put("html/" + path, content));
		files.put(BlocklySpecXmiSerializer.generatedIntermediatePath(spec),
			intermediateXmi);
		files.put("README.md", generateOutputReadme(inputFile, files.keySet()));
		files.put("generation_report.html", GenerationReportHtmlRenderer.renderModel2BlocklySummary(
			domain,
			spec,
			inputFile.getProjectRelativePath().toString(),
			new ArrayList<>(files.keySet())));
		return files;
	}

	private static String generateOutputReadme(IFile inputFile, Iterable<String> generatedFiles) {
		String standalone = findStandalone(generatedFiles);
		StringBuilder out = new StringBuilder();
		out.append("# Generated Blockly Editor\n\n");
		out.append("Input model: `").append(inputFile.getProjectRelativePath().toString()).append("`\n\n");
		out.append("Output: EMF EditorSpec intermediate XMI and HTML Blockly editor\n\n");
		out.append("## Start Here\n\n");
		out.append("1. Open `generation_report.html` to inspect the model-to-Blockly mapping.\n");
		if (standalone != null) {
			out.append("2. Open `").append(standalone).append("` in a browser to use the generated HTML editor.\n");
			out.append("3. Inspect `intermediate/*_blocklyspec.xmi` to see the EMF intermediate model used for generation.\n");
			out.append("4. Click `Load Sample` or import `html/sample_model.json` to inspect a representative domain instance.\n");
		} else {
			out.append("2. Inspect `intermediate/*_blocklyspec.xmi` to see the EMF intermediate model used for generation.\n");
			out.append("3. Open the generated HTML output.\n");
		}
		out.append("\n## Typical Iteration\n\n");
		out.append("- Edit the Ecore or `.model2blockly` source when the domain structure should change.\n");
		out.append("- Add Blockly/UI/code annotations when block labels, layout, widgets or code templates need refinement.\n");
		out.append("- Rerun this Eclipse action and reopen `generation_report.html`.\n");
		return out.toString();
	}

	private static String findStandalone(Iterable<String> files) {
		for (String file : files) {
			if (file.endsWith("_standalone.html")) return file;
		}
		return null;
	}

	private static DomainModel loadDomainModel(IFile inputFile) {
		Injector injector = new Model2BlocklyStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet resourceSet = injector.getInstance(ResourceSet.class);
		Resource resource = resourceSet.getResource(
			URI.createFileURI(inputFile.getLocation().toFile().getAbsolutePath()), true);
		if (resource instanceof XtextResource) {
			((XtextResource) resource).validateConcreteSyntax();
			Model2BlocklyValidationDiagnostics.throwIfSyntaxErrors(
				inputFile.getProjectRelativePath().toString(), resource.getErrors());
			Model2BlocklyValidationDiagnostics.throwIfErrors(
				inputFile.getProjectRelativePath().toString(),
				((XtextResource) resource).getResourceServiceProvider()
				.getResourceValidator()
				.validate(resource, org.eclipse.xtext.validation.CheckMode.ALL, CancelIndicator.NullImpl));
		}
		if (!resource.getAllContents().hasNext()) {
			throw new IllegalArgumentException("No DomainModel found in " + inputFile.getName());
		}
		Object root = resource.getAllContents().next();
		if (!(root instanceof DomainModel)) {
			throw new IllegalArgumentException("First model element is not a DomainModel in " + inputFile.getName());
		}
		return (DomainModel) root;
	}

	static void showGenerationError(Shell shell, IFile selectedFile, Throwable cause) {
		if (cause instanceof DiagnosticException) {
			DiagnosticException diagnostic = (DiagnosticException) cause;
			try {
				createDiagnosticMarkers(selectedFile, diagnostic);
				openInputFile(selectedFile);
			} catch (CoreException markerError) {
				MessageDialog.openError(shell, "Generate Blockly Editor",
					diagnostic.getMessage() + "\n\nCould not update Eclipse problem markers: "
						+ markerError.getMessage());
				return;
			}
			MessageDialog.openError(shell, "Generate Blockly Editor",
				diagnostic.getMessage() + "\n\nProblem markers were added to "
					+ selectedFile.getProjectRelativePath().toString() + ".");
			return;
		}
		if (cause instanceof ValidationException) {
			ValidationException validation = (ValidationException) cause;
			try {
				createDslValidationMarkers(selectedFile, validation);
				openInputFile(selectedFile);
			} catch (CoreException markerError) {
				MessageDialog.openError(shell, "Generate Blockly Editor",
					validation.getMessage() + "\n\nCould not update Eclipse problem markers: "
						+ markerError.getMessage());
				return;
			}
			MessageDialog.openError(shell, "Generate Blockly Editor",
				validation.getMessage() + "\n\nProblem markers were added to "
					+ selectedFile.getProjectRelativePath().toString() + ".");
			return;
		}
		MessageDialog.openError(shell, "Generate Blockly Editor", cause.getMessage());
	}

	private static void openInputFile(IFile inputFile) {
		try {
			IDE.openEditor(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
				inputFile,
				true);
		} catch (PartInitException ignored) {
			// The error dialog and problem markers still show the diagnostic.
		}
	}

	private static void createDiagnosticMarkers(IFile inputFile, DiagnosticException exception)
			throws CoreException {
		clearDiagnosticMarkers(inputFile);
		for (Diagnostic diagnostic : exception.getDiagnostics()) {
			IMarker marker = inputFile.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.SOURCE_ID, DIAGNOSTIC_MARKER_SOURCE);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.MESSAGE, markerMessage(diagnostic));
			SourceLocation location = diagnostic.getSourceLocation();
			if (location != null) {
				if (location.getLine() > 0) {
					marker.setAttribute(IMarker.LINE_NUMBER, location.getLine());
				}
				marker.setAttribute(IMarker.LOCATION,
					location.format(inputFile.getProjectRelativePath().toString()));
			}
		}
	}

	private static void createDslValidationMarkers(IFile inputFile, ValidationException exception)
			throws CoreException {
		clearDiagnosticMarkers(inputFile);
		for (Issue issue : exception.getIssues()) {
			IMarker marker = inputFile.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.SOURCE_ID, DIAGNOSTIC_MARKER_SOURCE);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.MESSAGE, issue.getMessage());
			if (issue.getLineNumber() != null && issue.getLineNumber() > 0) {
				marker.setAttribute(IMarker.LINE_NUMBER, issue.getLineNumber());
			}
			String location = inputFile.getProjectRelativePath().toString();
			if (issue.getLineNumber() != null && issue.getLineNumber() > 0) {
				location += ":" + issue.getLineNumber();
			}
			marker.setAttribute(IMarker.LOCATION, location);
		}
	}

	private static String markerMessage(Diagnostic diagnostic) {
		StringBuilder message = new StringBuilder();
		message.append(diagnostic.getIssue().getMessage());
		if (diagnostic.getIssue().getLocation() != null
				&& !diagnostic.getIssue().getLocation().isBlank()) {
			message.append(" [").append(diagnostic.getIssue().getLocation()).append("]");
		}
		if (diagnostic.getHint() != null && !diagnostic.getHint().isBlank()) {
			message.append(" Hint: ").append(diagnostic.getHint());
		}
		return message.toString();
	}

	private static void clearDiagnosticMarkers(IFile inputFile) throws CoreException {
		for (IMarker marker : inputFile.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_ZERO)) {
			Object source = marker.getAttribute(IMarker.SOURCE_ID);
			if (DIAGNOSTIC_MARKER_SOURCE.equals(source)) {
				marker.delete();
			}
		}
	}

	private static void writeFiles(IFile inputFile, Map<String, String> files,
			IProgressMonitor monitor) throws CoreException {
		IFolder out = outputFolder(inputFile);
		ensureFolder(out, monitor);
		cleanStaleOutput(out, files, monitor);
		for (Map.Entry<String, String> entry : files.entrySet()) {
			IFile target = out.getFile(new Path(entry.getKey()));
			if (target.getParent() instanceof IFolder) {
				ensureFolder((IFolder) target.getParent(), monitor);
			}
			byte[] bytes = entry.getValue().getBytes(StandardCharsets.UTF_8);
			try (ByteArrayInputStream stream = new ByteArrayInputStream(bytes)) {
				if (target.exists()) {
					target.setContents(stream, true, true, monitor);
				} else {
					target.create(stream, true, monitor);
				}
			} catch (java.io.IOException e) {
				throw new CoreException(new Status(IStatus.ERROR,
					"io.github.plortinus.model2blockly.ui", e.getMessage(), e));
			}
		}
	}

	private static void cleanStaleOutput(IFolder out, Map<String, String> files,
			IProgressMonitor monitor) throws CoreException {
		IFolder legacyFrontendFolder = out.getFolder("re" + "act");
		deleteIfExists(legacyFrontendFolder, monitor);

		for (String path : files.keySet()) {
			if (!path.startsWith("html/")) continue;
			IFile staleRootFile = out.getFile(new Path(path.substring("html/".length())));
			deleteIfExists(staleRootFile, monitor);
		}
	}

	private static void deleteIfExists(IResource resource, IProgressMonitor monitor) throws CoreException {
		if (resource != null && resource.exists()) {
			resource.delete(IResource.FORCE, monitor);
		}
	}

	private static void ensureFolder(IFolder folder, IProgressMonitor monitor) throws CoreException {
		if (folder == null || folder.exists()) return;
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) ensureFolder((IFolder) parent, monitor);
		else if (parent instanceof IProject && !parent.exists()) {
			ResourcesPlugin.getWorkspace().getRoot().getProject(parent.getName()).create(monitor);
		}
		folder.create(true, true, monitor);
	}
}
