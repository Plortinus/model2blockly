package io.github.plortinus.model2blockly.blocklyspec;

/**
 * Conservative syntax checks for browser-side validation expressions.
 *
 * This is not a full JavaScript parser. It catches common authoring mistakes
 * without rejecting the method-call expressions that the generated runtime
 * deliberately allows.
 */
public final class ValidationExpressionSyntax {

	private ValidationExpressionSyntax() {
	}

	public static String firstSyntaxError(String expression) {
		if (expression == null || expression.isBlank()) return null;

		char[] stack = new char[Math.max(8, expression.length())];
		int depth = 0;
		char quote = 0;
		boolean escaped = false;

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (quote != 0) {
				if (escaped) {
					escaped = false;
				} else if (c == '\\') {
					escaped = true;
				} else if (c == quote) {
					quote = 0;
				}
				continue;
			}

			if (c == '\'' || c == '"' || c == '`') {
				quote = c;
				continue;
			}
			if (c == '(' || c == '[' || c == '{') {
				stack[depth++] = c;
				continue;
			}
			if (c == ')' || c == ']' || c == '}') {
				if (depth == 0) return "Unexpected closing '" + c + "'.";
				char open = stack[--depth];
				if (!matches(open, c)) {
					return "Mismatched '" + open + "' and '" + c + "'.";
				}
			}
		}

		if (quote != 0) return "Unclosed string literal.";
		if (depth > 0) return "Unclosed '" + stack[depth - 1] + "'.";
		return null;
	}

	private static boolean matches(char open, char close) {
		return (open == '(' && close == ')')
			|| (open == '[' && close == ']')
			|| (open == '{' && close == '}');
	}
}
