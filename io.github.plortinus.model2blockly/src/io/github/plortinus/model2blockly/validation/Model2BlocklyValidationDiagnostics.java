package io.github.plortinus.model2blockly.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Issue;

/**
 * Shared formatting for DSL syntax/linking/semantic diagnostics.
 */
public final class Model2BlocklyValidationDiagnostics {
	public static final class ValidationException extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		private final List<Issue> issues;

		private ValidationException(String message, List<Issue> issues) {
			super(message);
			this.issues = Collections.unmodifiableList(new ArrayList<>(issues));
		}

		public List<Issue> getIssues() { return issues; }
	}

	private Model2BlocklyValidationDiagnostics() {}

	public static void throwIfSyntaxErrors(String sourceLabel, List<Resource.Diagnostic> errors) {
		if (errors == null || errors.isEmpty()) return;
		StringBuilder message = new StringBuilder();
		message.append("Invalid .m2b syntax");
		if (!isBlank(sourceLabel)) message.append(" in ").append(sourceLabel);
		message.append(".");
		int limit = Math.min(errors.size(), 20);
		for (int i = 0; i < limit; i++) {
			Resource.Diagnostic error = errors.get(i);
			message.append("\n\n").append(i + 1).append(". ERROR at ")
				.append(location(sourceLabel, error.getLine(), error.getColumn()))
				.append("\n   ").append(error.getMessage());
		}
		if (errors.size() > limit) {
			message.append("\n\n... and ").append(errors.size() - limit).append(" more syntax issue(s).");
		}
		throw new IllegalArgumentException(message.toString());
	}

	public static void throwIfErrors(String sourceLabel, List<Issue> issues) {
		List<Issue> errors = errors(issues);
		if (errors.isEmpty()) return;
		throw new ValidationException(format(sourceLabel, errors), errors);
	}

	public static String format(String sourceLabel, List<Issue> issues) {
		StringBuilder message = new StringBuilder();
		message.append("Invalid .m2b model");
		if (!isBlank(sourceLabel)) message.append(" in ").append(sourceLabel);
		message.append(".");
		int limit = Math.min(issues.size(), 20);
		for (int i = 0; i < limit; i++) {
			Issue issue = issues.get(i);
			message.append("\n\n").append(i + 1).append(". ").append(issue.getSeverity())
				.append(" at ").append(location(sourceLabel, issue.getLineNumber(), issue.getColumn()))
				.append("\n   ").append(issue.getMessage());
		}
		if (issues.size() > limit) {
			message.append("\n\n... and ").append(issues.size() - limit).append(" more issue(s).");
		}
		return message.toString();
	}

	private static List<Issue> errors(List<Issue> issues) {
		List<Issue> errors = new ArrayList<>();
		if (issues == null) return errors;
		for (Issue issue : issues) {
			if (issue.getSeverity() == Severity.ERROR) errors.add(issue);
		}
		return errors;
	}

	private static String location(String sourceLabel, Integer line, Integer column) {
		StringBuilder out = new StringBuilder(isBlank(sourceLabel) ? "source" : sourceLabel);
		if (line != null && line > 0) {
			out.append(":").append(line);
			if (column != null && column > 0) out.append(":").append(column);
		}
		return out.toString();
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
