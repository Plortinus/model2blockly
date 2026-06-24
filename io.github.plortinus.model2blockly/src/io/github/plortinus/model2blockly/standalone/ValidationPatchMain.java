package io.github.plortinus.model2blockly.standalone;

import java.nio.file.Path;

import io.github.plortinus.model2blockly.validationpatch.PatchReport;
import io.github.plortinus.model2blockly.validationpatch.ValidationSourcePatcher;

/**
 * Standalone CLI for writing edited validation Blockly models back to source.
 */
public final class ValidationPatchMain {

	private ValidationPatchMain() {
	}

	public static void main(String[] args) throws Exception {
		Options options = Options.parse(args);
		if (options.help) {
			System.out.println(usage());
			return;
		}
		if (options.source == null || options.validation == null) {
			System.err.println(usage());
			System.exit(2);
		}
		PatchReport report = options.apply
				? ValidationSourcePatcher.apply(options.source, options.validation, options.output)
				: ValidationSourcePatcher.dryRun(options.source, options.validation);
		System.out.print(report.toConsoleString());
	}

	private static String usage() {
		return """
			Usage:
			  java io.github.plortinus.model2blockly.standalone.ValidationPatchMain \\
			    --source <file.ecore|file.model2blockly> \\
			    --validation <validation_blocks.edited.json> \\
			    [--dry-run|--apply] [--out <file>]

			Default mode is --dry-run. When --apply is used without --out, the source
			file is overwritten and a sibling .bak file is created first.
			""";
	}

	private static final class Options {
		private Path source;
		private Path validation;
		private Path output;
		private boolean apply;
		private boolean help;

		private static Options parse(String[] args) {
			Options options = new Options();
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				switch (arg) {
					case "--source":
						options.source = Path.of(requiredValue(args, ++i, arg));
						break;
					case "--validation":
						options.validation = Path.of(requiredValue(args, ++i, arg));
						break;
					case "--out":
						options.output = Path.of(requiredValue(args, ++i, arg));
						break;
					case "--apply":
						options.apply = true;
						break;
					case "--dry-run":
						options.apply = false;
						break;
					case "--help":
					case "-h":
						options.help = true;
						break;
					default:
						throw new IllegalArgumentException("Unknown argument: " + arg);
				}
			}
			return options;
		}

		private static String requiredValue(String[] args, int index, String option) {
			if (index >= args.length) throw new IllegalArgumentException("Missing value for " + option);
			return args[index];
		}
	}
}
