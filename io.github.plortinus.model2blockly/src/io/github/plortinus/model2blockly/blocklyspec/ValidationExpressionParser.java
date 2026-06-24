package io.github.plortinus.model2blockly.blocklyspec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Parses the small browser-side validation expression subset into visual blocks.
 */
public final class ValidationExpressionParser {

	private ValidationExpressionParser() {
	}

	public static Optional<ValidationExpressionSpec> parse(String expression) {
		return parse(expression, ValidationExpressionSpec::fieldCount);
	}

	public static Optional<ValidationExpressionSpec> parse(String expression,
			Function<String, ValidationExpressionSpec> sizeAccessor) {
		if (expression == null || expression.isBlank()) return Optional.empty();
		Parser parser = new Parser(expression, sizeAccessor != null
			? sizeAccessor
			: ValidationExpressionSpec::fieldCount);
		ValidationExpressionSpec parsed = parser.parseExpression();
		if (parsed == null || parser.failed || parser.current.kind != TokenKind.END) {
			return Optional.empty();
		}
		return Optional.of(parsed);
	}

	private static final class Parser {
		private final Lexer lexer;
		private final Function<String, ValidationExpressionSpec> sizeAccessor;
		private Token current;
		private boolean failed;

		private Parser(String expression, Function<String, ValidationExpressionSpec> sizeAccessor) {
			this.lexer = new Lexer(expression);
			this.sizeAccessor = sizeAccessor;
			advance();
		}

		private ValidationExpressionSpec parseExpression() {
			return parseOr();
		}

		private ValidationExpressionSpec parseOr() {
			List<ValidationExpressionSpec> children = new ArrayList<>();
			ValidationExpressionSpec first = parseAnd();
			if (first == null) return null;
			children.add(first);
			while (match(TokenKind.OPERATOR, "||")) {
				ValidationExpressionSpec next = parseAnd();
				if (next == null) return null;
				children.add(next);
			}
			return children.size() == 1 ? first
				: ValidationExpressionSpec.logic("OR", children.toArray(new ValidationExpressionSpec[0]));
		}

		private ValidationExpressionSpec parseAnd() {
			List<ValidationExpressionSpec> children = new ArrayList<>();
			ValidationExpressionSpec first = parseComparison();
			if (first == null) return null;
			children.add(first);
			while (match(TokenKind.OPERATOR, "&&")) {
				ValidationExpressionSpec next = parseComparison();
				if (next == null) return null;
				children.add(next);
			}
			return children.size() == 1 ? first
				: ValidationExpressionSpec.logic("AND", children.toArray(new ValidationExpressionSpec[0]));
		}

		private ValidationExpressionSpec parseComparison() {
			ValidationExpressionSpec left = parseUnary();
			if (left == null) return null;
			if (current.kind == TokenKind.OPERATOR && isCompareOperator(current.text)) {
				String operator = current.text;
				advance();
				ValidationExpressionSpec right = parseUnary();
				if (right == null) return null;
				return ValidationExpressionSpec.compare(toBlocklyCompareOperator(operator), left, right);
			}
			return left;
		}

		private ValidationExpressionSpec parseUnary() {
			if (match(TokenKind.OPERATOR, "!")) {
				ValidationExpressionSpec child = parseUnary();
				return child != null ? ValidationExpressionSpec.not(child) : null;
			}
			return parsePrimary();
		}

		private ValidationExpressionSpec parsePrimary() {
			if (current.kind == TokenKind.LPAREN) {
				advance();
				ValidationExpressionSpec nested = parseExpression();
				if (nested == null || !match(TokenKind.RPAREN, null)) return fail();
				return nested;
			}
			if (current.kind == TokenKind.NUMBER) {
				String value = current.text;
				advance();
				return ValidationExpressionSpec.number(value);
			}
			if (current.kind == TokenKind.STRING) {
				String value = current.text;
				advance();
				return ValidationExpressionSpec.string(value);
			}
			if (current.kind == TokenKind.IDENTIFIER) {
				String identifier = current.text;
				advance();
				if ("true".equals(identifier)) return ValidationExpressionSpec.bool(true);
				if ("false".equals(identifier)) return ValidationExpressionSpec.bool(false);
				return parseCall(identifier);
			}
			return fail();
		}

		private ValidationExpressionSpec parseCall(String identifier) {
			if (!match(TokenKind.LPAREN, null)) return fail();
			if (current.kind != TokenKind.STRING) return fail();
			String fieldName = current.text;
			advance();
			if (!match(TokenKind.RPAREN, null)) return fail();
			switch (identifier) {
				case "has":
					return ValidationExpressionSpec.fieldExists(fieldName);
				case "value":
					return ValidationExpressionSpec.fieldValue(fieldName);
				case "number":
					return ValidationExpressionSpec.fieldNumber(fieldName);
				case "size":
				case "count": {
					ValidationExpressionSpec expression = sizeAccessor.apply(fieldName);
					return expression != null ? expression : fail();
				}
				case "fieldCount":
					return ValidationExpressionSpec.fieldCount(fieldName);
				case "inputCount":
					return ValidationExpressionSpec.inputCount(fieldName);
				default:
					return fail();
			}
		}

		private boolean match(TokenKind kind, String text) {
			if (current.kind != kind) return false;
			if (text != null && !text.equals(current.text)) return false;
			advance();
			return true;
		}

		private void advance() {
			current = lexer.next();
			if (current.kind == TokenKind.INVALID) failed = true;
		}

		private ValidationExpressionSpec fail() {
			failed = true;
			return null;
		}
	}

	private static boolean isCompareOperator(String operator) {
		return "===".equals(operator) || "==".equals(operator)
			|| "!==".equals(operator) || "!=".equals(operator)
			|| ">=".equals(operator) || "<=".equals(operator)
			|| ">".equals(operator) || "<".equals(operator);
	}

	private static String toBlocklyCompareOperator(String operator) {
		switch (operator) {
			case "===":
			case "==":
				return "EQ";
			case "!==":
			case "!=":
				return "NEQ";
			case ">=":
				return "GTE";
			case "<=":
				return "LTE";
			case ">":
				return "GT";
			case "<":
				return "LT";
			default:
				return "EQ";
		}
	}

	private enum TokenKind {
		END,
		INVALID,
		IDENTIFIER,
		NUMBER,
		STRING,
		LPAREN,
		RPAREN,
		OPERATOR
	}

	private static final class Token {
		private final TokenKind kind;
		private final String text;

		private Token(TokenKind kind, String text) {
			this.kind = kind;
			this.text = text;
		}
	}

	private static final class Lexer {
		private final String input;
		private int index;

		private Lexer(String input) {
			this.input = input;
		}

		private Token next() {
			skipWhitespace();
			if (index >= input.length()) return new Token(TokenKind.END, "");

			char c = input.charAt(index);
			if (c == '(') {
				index++;
				return new Token(TokenKind.LPAREN, "(");
			}
			if (c == ')') {
				index++;
				return new Token(TokenKind.RPAREN, ")");
			}
			if (c == '"' || c == '\'') return stringToken(c);
			if (isIdentifierStart(c)) return identifierToken();
			if (isNumberStart(c)) return numberToken();

			for (String operator : new String[] {"===", "!==", "&&", "||", ">=", "<=", "==", "!=", "!", ">", "<"}) {
				if (input.startsWith(operator, index)) {
					index += operator.length();
					return new Token(TokenKind.OPERATOR, operator);
				}
			}
			index++;
			return new Token(TokenKind.INVALID, Character.toString(c));
		}

		private void skipWhitespace() {
			while (index < input.length() && Character.isWhitespace(input.charAt(index))) {
				index++;
			}
		}

		private Token identifierToken() {
			int start = index++;
			while (index < input.length() && isIdentifierPart(input.charAt(index))) {
				index++;
			}
			return new Token(TokenKind.IDENTIFIER, input.substring(start, index));
		}

		private Token numberToken() {
			int start = index;
			if (input.charAt(index) == '-') index++;
			boolean digit = false;
			while (index < input.length() && Character.isDigit(input.charAt(index))) {
				digit = true;
				index++;
			}
			if (index < input.length() && input.charAt(index) == '.') {
				index++;
				while (index < input.length() && Character.isDigit(input.charAt(index))) {
					digit = true;
					index++;
				}
			}
			if (!digit) return new Token(TokenKind.INVALID, input.substring(start, index));
			if (index < input.length() && (input.charAt(index) == 'e' || input.charAt(index) == 'E')) {
				int exponentStart = index++;
				if (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) index++;
				boolean exponentDigit = false;
				while (index < input.length() && Character.isDigit(input.charAt(index))) {
					exponentDigit = true;
					index++;
				}
				if (!exponentDigit) {
					index = exponentStart;
				}
			}
			return new Token(TokenKind.NUMBER, input.substring(start, index));
		}

		private Token stringToken(char quote) {
			index++;
			StringBuilder value = new StringBuilder();
			while (index < input.length()) {
				char c = input.charAt(index++);
				if (c == quote) return new Token(TokenKind.STRING, value.toString());
				if (c == '\\') {
					if (index >= input.length()) return new Token(TokenKind.INVALID, value.toString());
					char escaped = input.charAt(index++);
					switch (escaped) {
						case 'n': value.append('\n'); break;
						case 'r': value.append('\r'); break;
						case 't': value.append('\t'); break;
						case '\\': value.append('\\'); break;
						case '"': value.append('"'); break;
						case '\'': value.append('\''); break;
						default: value.append(escaped);
					}
				} else {
					value.append(c);
				}
			}
			return new Token(TokenKind.INVALID, value.toString());
		}

		private boolean isNumberStart(char c) {
			return Character.isDigit(c)
				|| (c == '-' && index + 1 < input.length() && Character.isDigit(input.charAt(index + 1)));
		}

		private boolean isIdentifierStart(char c) {
			return Character.isLetter(c) || c == '_';
		}

		private boolean isIdentifierPart(char c) {
			return Character.isLetterOrDigit(c) || c == '_';
		}
	}
}
