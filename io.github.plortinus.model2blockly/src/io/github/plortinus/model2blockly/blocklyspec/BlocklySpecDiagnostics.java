package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator.Issue;
import io.github.plortinus.model2blockly.blocklyspec.BlocklyEditorSpecValidator.Result;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;

/**
 * User-facing diagnostics for invalid BlocklyEditorSpec instances.
 *
 * BlocklyEditorSpecValidator stays source-agnostic; this class formats its
 * issues with an optional mapping back to the DSL or Ecore source model.
 */
public final class BlocklySpecDiagnostics {

	public interface SourceLocator {
		SourceLocation locate(Issue issue);
	}

	public static final class SourceLocation {
		private final String sourceLabel;
		private final int line;
		private final int column;
		private final String detail;

		public SourceLocation(String sourceLabel, int line, int column, String detail) {
			this.sourceLabel = sourceLabel;
			this.line = line;
			this.column = column;
			this.detail = detail;
		}

		public String getSourceLabel() { return sourceLabel; }
		public int getLine() { return line; }
		public int getColumn() { return column; }
		public String getDetail() { return detail; }

		public String format(String fallbackSource) {
			StringBuilder out = new StringBuilder();
			String label = firstNonBlank(sourceLabel, fallbackSource);
			if (label != null) out.append(label);
			if (line > 0) {
				if (out.length() > 0) out.append(":");
				out.append(line);
				if (column > 0) out.append(":").append(column);
			}
			if (!isBlank(detail)) {
				if (out.length() > 0) out.append(" ");
				out.append("(").append(detail).append(")");
			}
			return out.length() == 0 ? "source" : out.toString();
		}
	}

	public static final class Diagnostic {
		private final Issue issue;
		private final SourceLocation sourceLocation;
		private final String hint;

		private Diagnostic(Issue issue, SourceLocation sourceLocation, String hint) {
			this.issue = issue;
			this.sourceLocation = sourceLocation;
			this.hint = hint;
		}

		public Issue getIssue() { return issue; }
		public SourceLocation getSourceLocation() { return sourceLocation; }
		public String getHint() { return hint; }
	}

	public static final class DiagnosticException extends IllegalStateException {
		private static final long serialVersionUID = 1L;

		private final Result result;
		private final List<Diagnostic> diagnostics;

		private DiagnosticException(Result result, List<Diagnostic> diagnostics, String message) {
			super(message);
			this.result = result;
			this.diagnostics = Collections.unmodifiableList(new ArrayList<>(diagnostics));
		}

		public Result getResult() { return result; }
		public List<Diagnostic> getDiagnostics() { return diagnostics; }
	}

	private BlocklySpecDiagnostics() {}

	public static void assertValid(BlocklyEditorSpec spec, String sourceLabel) {
		assertValid(spec, sourceLabel, null);
	}

	public static void assertValid(BlocklyEditorSpec spec, String sourceLabel, SourceLocator locator) {
		Result result = BlocklyEditorSpecValidator.validate(spec);
		throwIfErrors(result, sourceLabel, locator);
	}

	public static void assertValid(EditorSpec spec, String sourceLabel) {
		assertValid(spec, sourceLabel, null);
	}

	public static void assertValid(EditorSpec spec, String sourceLabel, SourceLocator locator) {
		Result result = BlocklyEditorSpecValidator.validate(spec);
		throwIfErrors(result, sourceLabel, locator);
	}

	public static void throwIfErrors(Result result, String sourceLabel, SourceLocator locator) {
		if (result == null || !result.hasErrors()) return;
		List<Diagnostic> diagnostics = diagnostics(result.getErrors(), locator);
		throw new DiagnosticException(result, diagnostics, formatErrors(sourceLabel, diagnostics));
	}

	public static String formatErrors(String sourceLabel, List<Diagnostic> diagnostics) {
		StringBuilder out = new StringBuilder();
		out.append("Invalid Blockly editor model");
		if (!isBlank(sourceLabel)) out.append(" generated from ").append(sourceLabel);
		out.append(".\n");
		out.append("Fix these source model issues before generating again.");

		int limit = Math.min(diagnostics.size(), 20);
		for (int i = 0; i < limit; i++) {
			Diagnostic diagnostic = diagnostics.get(i);
			Issue issue = diagnostic.getIssue();
			out.append("\n\n").append(i + 1).append(". ").append(issue.getSeverity());
			out.append(" at ").append(locationText(sourceLabel, diagnostic));
			out.append("\n   ").append(issue.getMessage());
			String hint = diagnostic.getHint();
			if (!isBlank(hint)) out.append("\n   Hint: ").append(hint);
		}
		if (diagnostics.size() > limit) {
			out.append("\n\n... and ").append(diagnostics.size() - limit).append(" more issue(s).");
		}
		return out.toString();
	}

	public static String formatWarnings(String sourceLabel, Result result, SourceLocator locator) {
		List<Diagnostic> diagnostics = diagnostics(result.getWarnings(), locator);
		if (diagnostics.isEmpty()) return "";
		StringBuilder out = new StringBuilder();
		out.append("Blockly editor model warnings");
		if (!isBlank(sourceLabel)) out.append(" for ").append(sourceLabel);
		out.append(":");
		for (int i = 0; i < diagnostics.size(); i++) {
			Diagnostic diagnostic = diagnostics.get(i);
			out.append("\n").append(i + 1).append(". ").append(locationText(sourceLabel, diagnostic))
				.append(": ").append(diagnostic.getIssue().getMessage());
		}
		return out.toString();
	}

	public static List<Diagnostic> diagnostics(List<Issue> issues, SourceLocator locator) {
		List<Diagnostic> diagnostics = new ArrayList<>();
		for (Issue issue : issues) {
			SourceLocation sourceLocation = null;
			if (locator != null) {
				try {
					sourceLocation = locator.locate(issue);
				} catch (RuntimeException ignored) {
					// Diagnostics must never hide the original validation issue.
				}
			}
			diagnostics.add(new Diagnostic(issue, sourceLocation, hintFor(issue)));
		}
		return diagnostics;
	}

	private static String locationText(String sourceLabel, Diagnostic diagnostic) {
		String detail = diagnostic.getIssue().getLocation();
		SourceLocation source = diagnostic.getSourceLocation();
		if (source != null) {
			String sourceText = source.format(sourceLabel);
			if (!isBlank(detail)) return sourceText + " [" + detail + "]";
			return sourceText;
		}
		return isBlank(detail) ? firstNonBlank(sourceLabel, "source") : firstNonBlank(sourceLabel, "source") + " [" + detail + "]";
	}

	private static String hintFor(Issue issue) {
		String message = issue.getMessage();
		if (message == null) return null;
		if (message.contains("does not exist")) {
			return "Check the referenced class, feature, input or category name, or define the missing element.";
		}
		if (message.contains("must not be blank")) {
			return "Give this DSL/Ecore element an explicit name or value.";
		}
		if (message.contains("Duplicate")) {
			return "Rename one of the duplicate elements; generated Blockly type and field names must be unique.";
		}
			if (message.contains("not an output block")) {
				return "Use an output class for value-input shadow blocks.";
			}
		if (message.contains("Typed output blocks must define outputType")) {
			return "Set an output type/check value that matches the value-input blocks this block should plug into.";
		}
		if (message.contains("Typed statement blocks must define connectionTypeName")) {
			return "Set the statement connection type/check value, or use an untyped statement block.";
		}
		if (message.contains("Dropdown fields must define at least one option")) {
			return "Add enum/dropdown literals so Blockly can render a selectable field.";
		}
		if (message.contains("Upper bound") || message.contains("Lower bound")) {
			return "Use [lower..upper] with lower >= 0; use upper 0 only for unbounded.";
		}
		if (message.contains("Expression validation must define an expression")) {
			return "Add an expression/ocl body to the validation rule, or remove the empty rule.";
		}
		return null;
	}

	private static String firstNonBlank(String first, String fallback) {
		return isBlank(first) ? fallback : first;
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
