package io.github.plortinus.model2blockly;

import java.io.IOException;
import java.nio.file.Path;

import io.github.plortinus.model2blockly.validationpatch.PatchReport;
import io.github.plortinus.model2blockly.validationpatch.ValidationSourcePatcher;

/**
 * Public API facade for applying edited validation Blockly models to sources.
 */
public final class ValidationPatchService {

	private ValidationPatchService() {
	}

	public static ValidationPatchResult dryRun(Path source, Path validationModel) throws IOException {
		return fromReport(ValidationSourcePatcher.dryRun(source, validationModel));
	}

	public static ValidationPatchResult apply(Path source, Path validationModel, Path output) throws IOException {
		return fromReport(ValidationSourcePatcher.apply(source, validationModel, output));
	}

	private static ValidationPatchResult fromReport(PatchReport report) {
		return new ValidationPatchResult(
			report.getSource(),
			report.getValidationModel(),
			report.getOutput(),
			report.getBackup(),
			report.isApply(),
			report.getDomain(),
			report.getRulesRead(),
			report.getChanges(),
			report.getNotes(),
			report.getWarnings());
	}
}
