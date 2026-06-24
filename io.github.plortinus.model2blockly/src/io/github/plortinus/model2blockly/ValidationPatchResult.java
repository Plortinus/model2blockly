package io.github.plortinus.model2blockly;

import java.nio.file.Path;
import java.util.List;

/**
 * Public API result for validation patch dry-runs and apply operations.
 */
public final class ValidationPatchResult {
	private final Path source;
	private final Path validationModel;
	private final Path output;
	private final Path backup;
	private final boolean apply;
	private final String domain;
	private final int rulesRead;
	private final List<String> changes;
	private final List<String> notes;
	private final List<String> warnings;

	ValidationPatchResult(Path source, Path validationModel, Path output, Path backup, boolean apply,
			String domain, int rulesRead, List<String> changes, List<String> notes, List<String> warnings) {
		this.source = source;
		this.validationModel = validationModel;
		this.output = output;
		this.backup = backup;
		this.apply = apply;
		this.domain = domain;
		this.rulesRead = rulesRead;
		this.changes = List.copyOf(changes);
		this.notes = List.copyOf(notes);
		this.warnings = List.copyOf(warnings);
	}

	public Path getSource() {
		return source;
	}

	public Path getValidationModel() {
		return validationModel;
	}

	public Path getOutput() {
		return output;
	}

	public Path getBackup() {
		return backup;
	}

	public boolean isApply() {
		return apply;
	}

	public String getDomain() {
		return domain;
	}

	public int getRulesRead() {
		return rulesRead;
	}

	public List<String> getChanges() {
		return changes;
	}

	public List<String> getNotes() {
		return notes;
	}

	public List<String> getWarnings() {
		return warnings;
	}
}
