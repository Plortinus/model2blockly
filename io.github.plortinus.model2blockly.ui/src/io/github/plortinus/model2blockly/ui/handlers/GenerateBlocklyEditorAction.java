package io.github.plortinus.model2blockly.ui.handlers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Legacy popup action contribution for workbench views that do not show the
 * command-based menu contribution reliably.
 */
public class GenerateBlocklyEditorAction implements IObjectActionDelegate {

	private ISelection selection;
	private Shell shell;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.shell = targetPart != null ? targetPart.getSite().getShell() : null;
	}

	@Override
	public void run(IAction action) {
		IFile file = GenerateBlocklyEditorHandler.selectedFile(selection);
		if (file == null) {
			MessageDialog.openInformation(shell, "Generate Blockly Editor", "Select one .ecore or .model2blockly file.");
			return;
		}
		String extension = file.getFileExtension();
		if (!"ecore".equals(extension) && !"model2blockly".equals(extension)) {
			MessageDialog.openInformation(shell, "Generate Blockly Editor",
				"Unsupported file type: " + file.getName() + "\nSelect a .ecore or .model2blockly file.");
			return;
		}
		try {
			GenerateBlocklyEditorHandler.generate(shell, file);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause() != null ? e.getCause() : e;
			GenerateBlocklyEditorHandler.showGenerationError(shell, file, cause);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (CoreException e) {
			MessageDialog.openError(shell, "Generate Blockly Editor", e.getMessage());
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
