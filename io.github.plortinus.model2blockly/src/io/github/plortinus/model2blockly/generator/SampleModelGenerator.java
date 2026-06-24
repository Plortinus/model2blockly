package io.github.plortinus.model2blockly.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ConnectionType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.DropdownOption;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValidationType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ValueInputSpec;

/**
 * Builds a small domain model that can be imported by the generated Blockly
 * editor. The sample is derived from EditorSpec, so both Ecore and
 * .model2blockly inputs get the same quick-start experience.
 */
public final class SampleModelGenerator {

	private static final int MAX_DEPTH = 4;
	private static final int MAX_ROOTS = 4;
	private static final int MAX_CHILDREN_PER_INPUT = 30;

	private SampleModelGenerator() {}

	public static String generate(EditorSpec spec) {
		return new Builder(spec).generate();
	}

	private static final class Builder {
		private final EditorSpec spec;
		private final Map<String, BlockTypeSpec> byType = new LinkedHashMap<>();
		private final Map<String, Integer> counters = new HashMap<>();
		private final Map<String, String> requiredPredecessorByType = new HashMap<>();
		private final Map<String, Map<String, Object>> nodesById = new LinkedHashMap<>();
		private final IdentityHashMap<Map<String, Object>, BlockTypeSpec> nodeTypes = new IdentityHashMap<>();
		private final List<PendingReference> pendingReferences = new ArrayList<>();

		Builder(EditorSpec spec) {
			this.spec = spec;
			for (BlockTypeSpec block : BlocklySpecModelQueries.concreteBlockTypes(spec)) {
				byType.put(block.getTypeName(), block);
			}
			for (ValidationSpec validation : spec.getValidations()) {
				if (validation.getType() == ValidationType.MUST_FOLLOW
						&& validation.getTargetType() != null
						&& validation.getPredecessorType() != null) {
					requiredPredecessorByType.put(validation.getTargetType(), validation.getPredecessorType());
				}
			}
		}

		String generate() {
			List<Map<String, Object>> roots = new ArrayList<>();
			for (BlockTypeSpec rootType : rootCandidates()) {
				Map<String, Object> root = createNode(rootType, 0, new HashSet<>());
				if (root != null) roots.add(root);
				if (roots.size() >= MAX_ROOTS) break;
			}
			resolveReferences(roots);
			return toJson(roots, 0);
		}

		private List<BlockTypeSpec> rootCandidates() {
			List<BlockTypeSpec> roots = new ArrayList<>();
			Set<String> containedTypes = containedBlockTypeNames();
			for (BlockTypeSpec block : byType.values()) {
				if (block.getConnectionType() == ConnectionType.NONE
						&& !containedTypes.contains(block.getTypeName())) roots.add(block);
			}
			if (!roots.isEmpty()) return roots;
			for (BlockTypeSpec block : byType.values()) {
				if (block.getConnectionType() == ConnectionType.FREE
						&& !containedTypes.contains(block.getTypeName())) roots.add(block);
			}
			if (!roots.isEmpty()) return roots;
			for (BlockTypeSpec block : byType.values()) {
				if (block.getConnectionType() == ConnectionType.NONE) roots.add(block);
			}
			if (!roots.isEmpty()) return roots;
			for (BlockTypeSpec block : byType.values()) {
				if (block.getConnectionType() == ConnectionType.FREE) roots.add(block);
			}
			if (!roots.isEmpty()) return roots;
			for (BlockTypeSpec block : byType.values()) {
				if (block.getConnectionType() != ConnectionType.OUTPUT
						&& block.getConnectionType() != ConnectionType.OUTPUT_TYPED) {
					roots.add(block);
					break;
				}
			}
			return roots;
		}

		private Set<String> containedBlockTypeNames() {
			Set<String> contained = new HashSet<>();
			for (BlockTypeSpec owner : byType.values()) {
				for (StatementInputSpec input : owner.getStatementInputs()) {
					for (BlockTypeSpec candidate : byType.values()) {
						if (matchesStatement(candidate, input.getCheckType())) {
							contained.add(candidate.getTypeName());
						}
					}
				}
			}
			return contained;
		}

		private Map<String, Object> createNode(BlockTypeSpec type, int depth, Set<String> path) {
			if (type == null || depth > MAX_DEPTH || path.contains(type.getTypeName())) return null;
			Set<String> nextPath = new HashSet<>(path);
			nextPath.add(type.getTypeName());

			int index = counters.merge(type.getTypeName(), 1, Integer::sum);
			String id = "sample_" + sanitizeId(type.getTypeName()) + "_" + index;
			Map<String, Object> node = new LinkedHashMap<>();
			node.put("_type", type.getTypeName());
			node.put("_blockId", id);
			nodesById.put(id, node);
			nodeTypes.put(node, type);

			for (FieldSpec field : type.getFields()) {
				node.put(field.getName(), sampleFieldValue(type, field, index));
			}
			for (ReferenceFieldSpec ref : type.getReferenceFields()) {
				Object emptyValue = BlocklySpecModelQueries.isMany(ref) ? new ArrayList<String>() : "";
				node.put(ref.getName(), emptyValue);
				pendingReferences.add(new PendingReference(node, ref));
			}
			for (ValueInputSpec input : type.getValueInputs()) {
				BlockTypeSpec childType = firstMatchingOutput(input.getCheckType(), nextPath);
				Map<String, Object> child = createNode(childType, depth + 1, nextPath);
				if (child != null) node.put(input.getName(), child);
			}
			for (StatementInputSpec input : type.getStatementInputs()) {
				node.put(input.getName(), createStatementChildren(input, depth, nextPath));
			}
			return node;
		}

		private List<Map<String, Object>> createStatementChildren(StatementInputSpec input, int depth, Set<String> path) {
			List<Map<String, Object>> children = new ArrayList<>();
			if (depth >= MAX_DEPTH) return children;
			List<BlockTypeSpec> matches = matchingStatements(input.getCheckType(), path);
			int desired = desiredChildCount(input, depth, matches);
			String previousType = null;
			int attempts = 0;
			int maxAttempts = Math.max(desired * Math.max(1, matches.size()) * 2, matches.size());
			while (children.size() < desired && attempts++ < maxAttempts) {
				boolean added = false;
				for (BlockTypeSpec candidate : matches) {
					if (children.size() >= desired) break;
					String requiredPrevious = requiredPredecessorByType.get(candidate.getTypeName());
					if (requiredPrevious != null && !requiredPrevious.equals(previousType)) {
						BlockTypeSpec predecessor = byType.get(requiredPrevious);
						if (predecessor == null
								|| path.contains(predecessor.getTypeName())
								|| !matchesStatement(predecessor, input.getCheckType())) {
							continue;
						}
						Map<String, Object> predecessorNode = createNode(predecessor, depth + 1, path);
						if (predecessorNode == null) continue;
						children.add(predecessorNode);
						previousType = predecessor.getTypeName();
						added = true;
						if (children.size() >= desired) break;
					}
					Map<String, Object> child = createNode(candidate, depth + 1, path);
					if (child != null) {
						children.add(child);
						previousType = candidate.getTypeName();
						added = true;
					}
				}
				if (!added) break;
			}
			return children;
		}

		private int desiredChildCount(StatementInputSpec input, int depth, List<BlockTypeSpec> matches) {
			int available = matches.size();
			if (available <= 0) return 0;
			int upper = input.getUpperBound() == 0 ? MAX_CHILDREN_PER_INPUT : input.getUpperBound();
			int minimum = Math.max(1, input.getLowerBound());
			int exploratory = depth <= 1 ? Math.min(MAX_CHILDREN_PER_INPUT, available) : 1;
			int predecessorOverhead = 0;
			for (BlockTypeSpec match : matches) {
				if (requiredPredecessorByType.containsKey(match.getTypeName())) predecessorOverhead++;
			}
			return Math.min(upper, Math.max(minimum, exploratory + predecessorOverhead));
		}

		private void resolveReferences(List<Map<String, Object>> roots) {
			for (int i = 0; i < pendingReferences.size(); i++) {
				PendingReference pending = pendingReferences.get(i);
				List<String> targetIds = targetIdsFor(pending.ref, pending.owner, roots);
				if (targetIds.isEmpty()) continue;
				if (BlocklySpecModelQueries.isMany(pending.ref)) {
					pending.owner.put(pending.ref.getName(), targetIds.subList(0, Math.min(2, targetIds.size())));
				} else {
					pending.owner.put(pending.ref.getName(), targetIds.get(0));
				}
			}
		}

		private List<String> targetIdsFor(ReferenceFieldSpec ref, Map<String, Object> owner, List<Map<String, Object>> roots) {
			List<String> ids = new ArrayList<>();
			for (Map.Entry<String, Map<String, Object>> entry : nodesById.entrySet()) {
				if (entry.getValue() == owner) continue;
				BlockTypeSpec type = nodeTypes.get(entry.getValue());
				if (matchesReference(type, ref.getTargetTypeName())) ids.add(entry.getKey());
			}
			if (!ids.isEmpty()) return ids;
			if (!ref.isRequired()) return ids;

			BlockTypeSpec targetBlock = firstMatchingReference(ref.getTargetTypeName(), new HashSet<>());
			if (targetBlock != null) {
				Map<String, Object> extraRoot = createNode(targetBlock, 0, new HashSet<>());
				if (extraRoot != null) {
					roots.add(extraRoot);
					String id = String.valueOf(extraRoot.get("_blockId"));
					ids.add(id);
				}
			}
			return ids;
		}

		private List<BlockTypeSpec> matchingStatements(String checkType, Set<String> path) {
			List<BlockTypeSpec> result = new ArrayList<>();
			for (BlockTypeSpec block : byType.values()) {
				if (path.contains(block.getTypeName())) continue;
				if (matchesStatement(block, checkType)) result.add(block);
			}
			return result;
		}

		private BlockTypeSpec firstMatchingOutput(String checkType, Set<String> path) {
			for (BlockTypeSpec block : byType.values()) {
				if (path.contains(block.getTypeName())) continue;
				if (matchesOutput(block, checkType)) return block;
			}
			return null;
		}

		private BlockTypeSpec firstMatchingReference(String targetType, Set<String> path) {
			for (BlockTypeSpec block : byType.values()) {
				if (path.contains(block.getTypeName())) continue;
				if (matchesReference(block, targetType)) return block;
			}
			return null;
		}

		private boolean matchesStatement(BlockTypeSpec block, String checkType) {
			if (block == null || block.getConnectionType() == ConnectionType.OUTPUT
					|| block.getConnectionType() == ConnectionType.OUTPUT_TYPED) return false;
			if (checkType == null || checkType.isBlank()) return true;
			return checkType.equals(block.getTypeName())
				|| checkType.equals(block.getConnectionTypeName())
				|| checkType.equals(block.getSuperTypeName());
		}

		private boolean matchesOutput(BlockTypeSpec block, String checkType) {
			if (block == null) return false;
			if (block.getConnectionType() != ConnectionType.OUTPUT
					&& block.getConnectionType() != ConnectionType.OUTPUT_TYPED) return false;
			if (checkType == null || checkType.isBlank()) return true;
			return checkType.equals(block.getTypeName())
				|| checkType.equals(block.getOutputType())
				|| checkType.equals(block.getSuperTypeName());
		}

		private boolean matchesReference(BlockTypeSpec block, String targetType) {
			if (block == null || targetType == null || targetType.isBlank()) return false;
			return targetType.equals(block.getTypeName())
				|| targetType.equals(block.getConnectionTypeName())
				|| targetType.equals(block.getOutputType())
				|| targetType.equals(block.getSuperTypeName());
		}

		private Object sampleFieldValue(BlockTypeSpec owner, FieldSpec field, int index) {
			Object value;
			if (field.getDefaultValue() != null && !field.getDefaultValue().isBlank()) {
				value = field.getDefaultValue();
			} else if (field.isId()) {
				value = sanitizeId(owner.getTypeName()).toLowerCase() + "_" + index;
			} else {
				switch (field.getFieldType()) {
					case INTEGER:
						value = boundedNumber(field, index);
						break;
					case FLOAT:
						value = String.valueOf(boundedNumber(field, index) + 0.5);
						break;
					case BOOLEAN:
						value = "TRUE";
						break;
					case DROPDOWN:
						value = firstOption(field);
						break;
					case COLOUR:
						value = "#4a90e2";
						break;
					case ANGLE:
						value = "90";
						break;
					case IMAGE:
						value = field.getImageUrl() != null ? field.getImageUrl() : "";
						break;
					case LABEL:
						value = field.getDefaultValue() != null ? field.getDefaultValue() : field.getName();
						break;
					case TEXT:
					default:
						value = sampleText(owner, field, index);
						break;
				}
			}
			if (BlocklySpecModelQueries.isMany(field)) {
				return sampleManyFieldValues(owner, field, value, index);
			}
			return value;
		}

		private List<Object> sampleManyFieldValues(BlockTypeSpec owner, FieldSpec field, Object rawValue, int index) {
			List<Object> values = new ArrayList<>();
			for (String part : splitManyDefault(rawValue)) {
				if (!part.isBlank()) values.add(part);
			}
			int upper = field.getUpperBound() == 0 ? 2 : field.getUpperBound();
			int desired = Math.max(field.getLowerBound(), Math.min(2, upper));
			while (values.size() < desired) {
				values.add(sampleText(owner, field, index + values.size()));
			}
			if (field.getUpperBound() != 0 && values.size() > field.getUpperBound()) {
				return new ArrayList<>(values.subList(0, field.getUpperBound()));
			}
			return values;
		}

		private List<String> splitManyDefault(Object rawValue) {
			List<String> values = new ArrayList<>();
			if (rawValue == null) return values;
			for (String part : String.valueOf(rawValue).split("[,\\n]")) {
				String trimmed = part.trim();
				if (!trimmed.isEmpty()) values.add(trimmed);
			}
			return values;
		}

		private int boundedNumber(FieldSpec field, int fallback) {
			int value = Math.max(1, fallback);
			try {
				if (field.getMin() != null && !field.getMin().isBlank()) {
					value = Math.max(value, (int) Math.ceil(Double.parseDouble(field.getMin())));
				}
				if (field.getMax() != null && !field.getMax().isBlank()) {
					value = Math.min(value, (int) Math.floor(Double.parseDouble(field.getMax())));
				}
			} catch (NumberFormatException ignored) {
				// Keep fallback value.
			}
			return value;
		}

		private String firstOption(FieldSpec field) {
			for (DropdownOption option : field.getOptions()) {
				if (option.getValue() != null) return option.getValue();
			}
			return "";
		}

		private String sampleText(BlockTypeSpec owner, FieldSpec field, int index) {
			String base = field.getUiLabel() != null ? field.getUiLabel() : field.getName();
			if (base == null || base.isBlank()) base = owner.getTypeName();
			return "Sample " + base + " " + index;
		}

		private String sanitizeId(String value) {
			if (value == null || value.isBlank()) return "node";
			return value.replaceAll("[^A-Za-z0-9_]+", "_");
		}
	}

	private static final class PendingReference {
		private final Map<String, Object> owner;
		private final ReferenceFieldSpec ref;

		PendingReference(Map<String, Object> owner, ReferenceFieldSpec ref) {
			this.owner = owner;
			this.ref = ref;
		}
	}

	@SuppressWarnings("unchecked")
	private static String toJson(Object value, int indent) {
		if (value == null) return "null";
		if (value instanceof String) return quote((String) value);
		if (value instanceof Number || value instanceof Boolean) return String.valueOf(value);
		if (value instanceof Map<?, ?>) {
			Map<String, Object> map = (Map<String, Object>) value;
			if (map.isEmpty()) return "{}";
			StringBuilder out = new StringBuilder();
			out.append("{\n");
			int index = 0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (index++ > 0) out.append(",\n");
				out.append(spaces(indent + 2))
					.append(quote(entry.getKey()))
					.append(": ")
					.append(toJson(entry.getValue(), indent + 2));
			}
			out.append("\n").append(spaces(indent)).append("}");
			return out.toString();
		}
		if (value instanceof List<?>) {
			List<Object> list = (List<Object>) value;
			if (list.isEmpty()) return "[]";
			StringBuilder out = new StringBuilder();
			out.append("[\n");
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) out.append(",\n");
				out.append(spaces(indent + 2)).append(toJson(list.get(i), indent + 2));
			}
			out.append("\n").append(spaces(indent)).append("]");
			return out.toString();
		}
		return quote(String.valueOf(value));
	}

	private static String spaces(int count) {
		return " ".repeat(Math.max(0, count));
	}

	private static String quote(String value) {
		if (value == null) return "\"\"";
		StringBuilder out = new StringBuilder("\"");
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
				case '\\': out.append("\\\\"); break;
				case '"': out.append("\\\""); break;
				case '\n': out.append("\\n"); break;
				case '\r': out.append("\\r"); break;
				case '\t': out.append("\\t"); break;
				default:
					if (ch < 0x20) out.append(String.format("\\u%04x", (int) ch));
					else out.append(ch);
			}
		}
		return out.append('"').toString();
	}
}
