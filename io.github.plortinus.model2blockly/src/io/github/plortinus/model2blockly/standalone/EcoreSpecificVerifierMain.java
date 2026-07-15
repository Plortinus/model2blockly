package io.github.plortinus.model2blockly.standalone;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.BlockTypeSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.FieldType;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.ReferenceFieldSpec;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.StatementInputSpec;

/**
 * Verifies the Ecore-only example capabilities that cannot participate in a
 * strict Ecore/.m2b comparison.
 */
public final class EcoreSpecificVerifierMain {

	private EcoreSpecificVerifierMain() {
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: EcoreSpecificVerifierMain <editor-spec-xmi>");
			System.exit(2);
		}

		EditorSpec spec = BlocklySpecXmiSerializer.fromXmiToEditorSpec(
			Files.readString(Path.of(args[0])));

		require("EcoreSpecific".equals(spec.getDomainName()),
			"domain name was not preserved");
		require("http://www.example.org/ecorespecific".equals(spec.getNsURI()),
			"explicit nsURI was not preserved");
		require("ecorespecific".equals(spec.getNsPrefix()),
			"explicit nsPrefix was not preserved");

		Set<String> blockNames = spec.getBlockTypes().stream()
			.map(BlockTypeSpec::getTypeName)
			.collect(Collectors.toSet());
		require(blockNames.equals(Set.of("Catalog", "NamedElement", "Entry")),
			"recursive subpackage traversal produced unexpected block types: " + blockNames);

		BlockTypeSpec namedElement = block(spec, "NamedElement");
		require(namedElement.isAbstract(),
			"EClass interface was not converted to an abstract block type");

		BlockTypeSpec entry = block(spec, "Entry");
		require("NamedElement".equals(entry.getSuperTypeName()),
			"subpackage class inheritance was not preserved");
		require("entryId".equals(entry.getIdFieldName()),
			"Ecore ID attribute was not preserved as model identity");
		assertFieldType(entry, "longCount", FieldType.INTEGER);
		assertFieldType(entry, "shortCount", FieldType.INTEGER);
		assertFieldType(entry, "bigCount", FieldType.INTEGER);
		assertFieldType(entry, "doubleRatio", FieldType.FLOAT);
		assertFieldType(entry, "decimalRatio", FieldType.FLOAT);

		BlockTypeSpec catalog = block(spec, "Catalog");
		Set<String> catalogFields = catalog.getFields().stream()
			.map(FieldSpec::getName)
			.collect(Collectors.toSet());
		require(catalogFields.equals(Set.of("name")),
			"derived/transient/volatile/non-changeable fields were not filtered: "
				+ catalogFields);

		StatementInputSpec entries = catalog.getStatementInputs().stream()
			.filter(input -> "entries".equals(input.getName()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(
				"subpackage containment 'entries' was not generated"));
		require("NamedElement".equals(entries.getCheckType()),
			"abstract containment target was not preserved");

		ReferenceFieldSpec featured = catalog.getReferenceFields().stream()
			.filter(reference -> "featured".equals(reference.getName()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(
				"cross-package reference 'featured' was not generated"));
		require("Entry".equals(featured.getTargetTypeName()),
			"cross-package reference target was not preserved");
		require("entryId".equals(featured.getReferenceLabelField()),
			"automatic reference-label fallback did not select the target ID field");

		boolean hasTranslatedOcl = spec.getValidations().stream()
			.anyMatch(validation -> ("Entry".equals(validation.getTargetType())
					|| "Entry".equals(validation.getOwnerType()))
				&& validation.getExpression() != null
				&& validation.getExpression().contains("has(\"title\")"));
		require(hasTranslatedOcl,
			"standard Ecore/Pivot OCL annotation was not translated");

		System.out.println("Ecore-only capability assertions passed (7/7 groups).");
	}

	private static BlockTypeSpec block(EditorSpec spec, String name) {
		return spec.getBlockTypes().stream()
			.filter(block -> name.equals(block.getTypeName()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(
				"missing block type: " + name));
	}

	private static void assertFieldType(BlockTypeSpec block, String name,
			FieldType expected) {
		FieldSpec field = block.getFields().stream()
			.filter(candidate -> name.equals(candidate.getName()))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(
				"missing field " + block.getTypeName() + "." + name));
		require(expected == field.getFieldType(),
			"unexpected field type for " + block.getTypeName() + "." + name
				+ ": " + field.getFieldType());
	}

	private static void require(boolean condition, String message) {
		if (!condition) throw new IllegalStateException(message);
	}
}
