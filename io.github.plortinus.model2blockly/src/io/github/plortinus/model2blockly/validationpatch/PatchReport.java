package io.github.plortinus.model2blockly.validationpatch;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Result object for validation patch dry-runs and apply operations.
 */
public final class PatchReport {
	private final Path source;
	private final Path validationModel;
	private final boolean apply;
	Path output;
	Path backup;
	String domain;
	int rulesRead;
	private final List<String> changes = new ArrayList<>();
	private final List<String> notes = new ArrayList<>();
	private final List<String> warnings = new ArrayList<>();

	PatchReport(Path source, Path validationModel, Path output, boolean apply) {
		this.source = source;
		this.validationModel = validationModel;
		this.output = output;
		this.apply = apply;
	}

	void change(String message) {
		changes.add(message);
	}

	void note(String message) {
		notes.add(message);
	}

	void warn(String message) {
		warnings.add(message);
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
		return Collections.unmodifiableList(changes);
	}

	public List<String> getNotes() {
		return Collections.unmodifiableList(notes);
	}

	public List<String> getWarnings() {
		return Collections.unmodifiableList(warnings);
	}

	public String toConsoleString() {
		StringBuilder out = new StringBuilder();
		out.append(apply ? "Validation patch apply" : "Validation patch dry-run").append("\n");
		out.append("Source: ").append(source).append("\n");
		out.append("Validation model: ").append(validationModel).append("\n");
		if (!isBlank(domain)) out.append("Domain: ").append(domain).append("\n");
		out.append("Rules read: ").append(rulesRead).append("\n");
		out.append("Changes: ").append(changes.size()).append("\n");
		out.append("Warnings: ").append(warnings.size()).append("\n");
		out.append("Notes: ").append(notes.size()).append("\n");
		if (output != null) out.append("Output: ").append(output).append("\n");
		if (backup != null) out.append("Backup: ").append(backup).append("\n");
		appendSection(out, "Changes", changes);
		appendSection(out, "Warnings", warnings);
		appendSection(out, "Notes", notes);
		return out.toString();
	}

	private static void appendSection(StringBuilder out, String title, List<String> values) {
		if (values.isEmpty()) return;
		out.append("\n").append(title).append(":\n");
		for (String value : values) out.append("- ").append(value).append("\n");
	}

	private static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
