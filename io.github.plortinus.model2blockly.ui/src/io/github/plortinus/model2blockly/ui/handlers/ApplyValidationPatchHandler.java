package io.github.plortinus.model2blockly.ui.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import io.github.plortinus.model2blockly.ValidationPatchResult;
import io.github.plortinus.model2blockly.ValidationPatchService;

/**
 * Workspace command that writes edited validation Blockly JSON back to the
 * selected .ecore or .model2blockly source file.
 */
public class ApplyValidationPatchHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		IFile selectedFile = GenerateBlocklyEditorHandler.selectedFile(HandlerUtil.getCurrentSelection(event));
		if (selectedFile == null) {
			MessageDialog.openInformation(shell, "Apply Validation Patch", "Select one .ecore or .model2blockly file.");
			return null;
		}
		String extension = selectedFile.getFileExtension();
		if (!"ecore".equals(extension) && !"model2blockly".equals(extension)) {
			MessageDialog.openInformation(shell, "Apply Validation Patch",
				"Unsupported file type: " + selectedFile.getName() + "\nSelect a .ecore or .model2blockly file.");
			return null;
		}
		if (selectedFile.getLocation() == null) {
			MessageDialog.openError(shell, "Apply Validation Patch",
				"The selected file has no local filesystem location.");
			return null;
		}

		Path validationModel = chooseValidationModel(shell, selectedFile);
		if (validationModel == null) return null;

		try {
			Path source = selectedFile.getLocation().toFile().toPath();
			ValidationPatchResult dryRun = ValidationPatchService.dryRun(source, validationModel);
			if (dryRun.getChanges().isEmpty()) {
				MessageDialog.openInformation(shell, "Apply Validation Patch", noChangesText(dryRun));
				return null;
			}
			if (!MessageDialog.openQuestion(shell, "Apply Validation Patch", confirmationText(dryRun))) {
				return null;
			}
			ValidationPatchResult applied = applyWithProgress(selectedFile, validationModel);
			selectedFile.refreshLocal(IResource.DEPTH_ZERO, null);
			if (selectedFile.getParent() != null) selectedFile.getParent().refreshLocal(IResource.DEPTH_ONE, null);
			openSourceFile(selectedFile);
			MessageDialog.openInformation(shell, "Apply Validation Patch", appliedText(applied));
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause() != null ? e.getCause() : e;
			MessageDialog.openError(shell, "Apply Validation Patch", cause.getMessage());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (IOException | CoreException e) {
			MessageDialog.openError(shell, "Apply Validation Patch", e.getMessage());
		}
		return null;
	}

	private static Path chooseValidationModel(Shell shell, IFile selectedFile) {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setText("Select edited validation_blocks JSON");
		dialog.setFilterExtensions(new String[] { "*.json", "*.*" });
		dialog.setFilterNames(new String[] { "Validation JSON (*.json)", "All files (*.*)" });
		configureInitialValidationPath(dialog, selectedFile);
		String selected = dialog.open();
		return selected == null ? null : Path.of(selected);
	}

	private static void configureInitialValidationPath(FileDialog dialog, IFile selectedFile) {
		for (Path candidate : validationCandidates(selectedFile)) {
			if (candidate.toFile().isFile()) {
				dialog.setFilterPath(candidate.getParent().toString());
				dialog.setFileName(candidate.getFileName().toString());
				return;
			}
		}
		for (Path candidate : validationCandidates(selectedFile)) {
			Path parent = candidate.getParent();
			if (parent != null && parent.toFile().isDirectory()) {
				dialog.setFilterPath(parent.toString());
				dialog.setFileName("validation_blocks.edited.json");
				return;
			}
		}
		if (selectedFile.getLocation() != null) {
			java.io.File parent = selectedFile.getLocation().toFile().getParentFile();
			if (parent != null) dialog.setFilterPath(parent.getAbsolutePath());
		}
		dialog.setFileName("validation_blocks.edited.json");
	}

	private static List<Path> validationCandidates(IFile selectedFile) {
		Set<Path> candidates = new LinkedHashSet<>();
		if (selectedFile.getLocation() == null) return new ArrayList<>(candidates);
		Path source = selectedFile.getLocation().toFile().toPath();
		Path sourceParent = source.getParent();
		String baseName = selectedFile.getName();
		int dot = baseName.lastIndexOf('.');
		if (dot > 0) baseName = baseName.substring(0, dot);
		if (sourceParent != null) {
			addValidationCandidates(candidates, sourceParent.resolve(baseName + "_generated").resolve("html"));
		}
		for (Path root : candidateProjectRoots(selectedFile, source)) {
			for (String caseName : generatedCaseNames(selectedFile, baseName)) {
				addValidationCandidates(candidates, root.resolve("examples/generated").resolve(caseName).resolve("html"));
			}
		}
		return new ArrayList<>(candidates);
	}

	private static List<String> generatedCaseNames(IFile selectedFile, String baseName) {
		List<String> names = new ArrayList<>();
		if ("ecore".equalsIgnoreCase(selectedFile.getFileExtension())) {
			names.add(baseName + "_ecore");
			names.add(baseName);
		} else {
			names.add(baseName);
			names.add(baseName + "_ecore");
		}
		return names;
	}

	private static List<Path> candidateProjectRoots(IFile selectedFile, Path source) {
		Set<Path> roots = new LinkedHashSet<>();
		if (selectedFile.getProject() != null && selectedFile.getProject().getLocation() != null) {
			Path project = selectedFile.getProject().getLocation().toFile().toPath();
			addRoot(roots, project);
			if (selectedFile.getProjectRelativePath().segmentCount() > 1) {
				String firstSegment = selectedFile.getProjectRelativePath().segment(0);
				if (firstSegment != null && firstSegment.startsWith("io.github.plortinus.model2blockly")) {
					addRoot(roots, project.resolve(firstSegment));
				}
			}
		}
		for (Path current = source.getParent(); current != null; current = current.getParent()) {
			if (current.resolve("model/blockly_editor_spec.ecore").toFile().isFile()
					|| current.resolve("examples/generated").toFile().isDirectory()) {
				addRoot(roots, current);
			}
		}
		return new ArrayList<>(roots);
	}

	private static void addRoot(Set<Path> roots, Path root) {
		roots.add(root.toAbsolutePath().normalize());
	}

	private static void addValidationCandidates(Set<Path> candidates, Path folder) {
		candidates.add(folder.resolve("validation_blocks.edited.json").toAbsolutePath().normalize());
		candidates.add(folder.resolve("validation_blocks.json").toAbsolutePath().normalize());
	}

	private static ValidationPatchResult applyWithProgress(IFile selectedFile, Path validationModel)
			throws InvocationTargetException, InterruptedException {
		ValidationPatchResult[] result = new ValidationPatchResult[1];
		PlatformUI.getWorkbench().getProgressService().run(true, false, new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					monitor.beginTask("Applying validation patch", IProgressMonitor.UNKNOWN);
					result[0] = ValidationPatchService.apply(
						selectedFile.getLocation().toFile().toPath(),
						validationModel,
						null);
				} catch (IOException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		});
		return result[0];
	}

	private static String noChangesText(ValidationPatchResult report) {
		StringBuilder out = new StringBuilder();
		out.append("No source changes are required.\n\n");
		out.append("Source:\n").append(report.getSource()).append("\n\n");
		out.append("Validation model:\n").append(report.getValidationModel()).append("\n\n");
		out.append("Rules read: ").append(report.getRulesRead()).append("\n");
		out.append("Warnings: ").append(report.getWarnings().size()).append("\n");
		out.append("Notes: ").append(report.getNotes().size()).append("\n");
		appendPreview(out, "Warnings", report.getWarnings(), 8);
		appendPreview(out, "Notes", report.getNotes(), 8);
		return out.toString();
	}

	private static String confirmationText(ValidationPatchResult report) {
		StringBuilder out = new StringBuilder();
		out.append("Source:\n").append(report.getSource()).append("\n\n");
		out.append("Validation model:\n").append(report.getValidationModel()).append("\n\n");
		out.append("Rules read: ").append(report.getRulesRead()).append("\n");
		out.append("Changes to apply: ").append(report.getChanges().size()).append("\n");
		out.append("Warnings: ").append(report.getWarnings().size()).append("\n");
		out.append("Notes: ").append(report.getNotes().size()).append("\n");
		appendPreview(out, "Changes", report.getChanges(), 12);
		appendPreview(out, "Warnings", report.getWarnings(), 8);
		out.append("\nApply these changes to the selected source file?");
		return out.toString();
	}

	private static String appliedText(ValidationPatchResult report) {
		StringBuilder out = new StringBuilder();
		out.append("Validation patch applied.\n\n");
		out.append("Output: ").append(report.getOutput()).append("\n");
		if (report.getBackup() != null) out.append("Backup: ").append(report.getBackup()).append("\n");
		out.append("Changes: ").append(report.getChanges().size()).append("\n");
		out.append("Warnings: ").append(report.getWarnings().size()).append("\n");
		out.append("Notes: ").append(report.getNotes().size()).append("\n");
		return out.toString();
	}

	private static void appendPreview(StringBuilder out, String title, List<String> values, int limit) {
		if (values.isEmpty()) return;
		out.append("\n").append(title).append(":\n");
		int count = Math.min(values.size(), limit);
		for (int i = 0; i < count; i++) {
			out.append("- ").append(values.get(i)).append("\n");
		}
		if (values.size() > limit) out.append("- ... ").append(values.size() - limit).append(" more\n");
	}

	private static void openSourceFile(IFile selectedFile) {
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window == null) return;
			IWorkbenchPage page = window.getActivePage();
			if (page == null) return;
			IDE.openEditor(
				page,
				selectedFile,
				true);
		} catch (PartInitException ignored) {
			// The success dialog still reports where the file and backup were written.
		}
	}
}
