package io.github.plortinus.model2blockly.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import io.github.plortinus.model2blockly.Model2BlocklyStandaloneSetup;
import io.github.plortinus.model2blockly.validation.Model2BlocklyValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.inject.Injector;

/**
 * Regression tests for the bridge from BlocklyEditorSpecValidator back into the
 * .model2blockly Xtext validator.
 */
class Model2BlocklyValidatorBridgeTest {

	@Test
	@DisplayName("Value-input shadow blocks must be output blocks")
	void nonOutputShadowBlockIsDslValidationError() throws Exception {
		List<Issue> issues = validate("""
			domain BrokenShadow
			category Main colour 120
			output class Expr category Main {
			}
			class Statement category Main {
			  attribute name : string default "statement"
			}
			class Host category Main {
			  value Expr expr shadow Statement
			}
			""");

		assertHasBridgeError(issues, "Shadow block type 'Statement' is not an output block.", "block[Host].expr");
	}

	@Test
	@DisplayName("Output blocks may have statement inputs")
	void outputBlockWithContainmentIsAccepted() throws Exception {
		List<Issue> issues = validate("""
			domain OutputStatement
			category Main colour 120
			output class ValueWithBody category Main {
			  attribute name : string default "value"
			  contains Statement body [0..3]
			}
			class Statement category Main {
			  attribute name : string default "statement"
			}
			""");

		assertFalse(issues.stream().anyMatch(issue ->
				issue.getMessage().contains("Output blocks cannot have statement inputs")),
			() -> "Output block statement inputs should be allowed, got: " + issues);
	}

	@Test
	@DisplayName("Duplicate feature/input names are rejected during DSL validation")
	void duplicateFeatureNamesAreDslValidationError() throws Exception {
		List<Issue> issues = validate("""
			domain DuplicateFeature
			category Main colour 120
			class Card category Main {
			  attribute title : string default "Title"
			  contains Child title [0..1]
			}
			class Child category Main {
			  attribute name : string default "child"
			}
			""");

		assertHasBridgeError(issues, "Duplicate feature/input name 'title'.", "block[Card].title");
	}

	@Test
	@DisplayName("referenceLabelField must point to a target field")
	void missingReferenceLabelFieldIsDslValidationError() throws Exception {
		List<Issue> issues = validate("""
			domain BrokenReferenceLabel
			category Main colour 120
			class User category Main {
			  attribute name : string default "Ada"
			}
			class Task category Main {
			  reference User assignee
			    widget reference-select referenceLabelField displayName
			}
			""");

		assertHasBridgeError(issues,
			"Reference label field 'displayName' does not exist on target type 'User'.",
			"block[Task].assignee");
	}

	@Test
	@DisplayName("Unsupported OCL validation expressions are rejected")
	void unsupportedOclIsDslValidationError() throws Exception {
		List<Issue> issues = validate("""
			domain UnsupportedOcl
			category Main colour 120
			class Item category Main {
			  attribute name : string default "item"
			}
			validation badOcl on Item : ocl "self.name->exists(x | x = 'item')" errorMessage "Should not disappear."
			""");

		assertTrue(issues.stream().anyMatch(issue ->
				issue.getSeverity() == org.eclipse.xtext.diagnostics.Severity.ERROR
				&& issue.getMessage().contains("Unsupported OCL validation expression")),
			() -> "Expected unsupported OCL error, got: " + issues);
	}

	private List<Issue> validate(String source) throws Exception {
		Path temp = Files.createTempFile("model2blockly-validator-bridge-", ".model2blockly");
		try {
			Files.writeString(temp, source.stripLeading());
			Injector injector = new Model2BlocklyStandaloneSetup().createInjectorAndDoEMFRegistration();
			ResourceSet resourceSet = injector.getInstance(ResourceSet.class);
			Resource resource = resourceSet.getResource(
				URI.createFileURI(temp.toAbsolutePath().toString()), true);
			assertTrue(resource.getErrors().isEmpty(), () -> "Syntax errors: " + resource.getErrors());
			assertInstanceOf(XtextResource.class, resource);
			return ((XtextResource) resource).getResourceServiceProvider()
				.getResourceValidator()
				.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		} finally {
			Files.deleteIfExists(temp);
		}
	}

	private void assertHasBridgeError(List<Issue> issues, String messagePart, String locationPart) {
		assertTrue(issues.stream().anyMatch(issue ->
				issue.getSeverity() == org.eclipse.xtext.diagnostics.Severity.ERROR
				&& Model2BlocklyValidator.INTERMEDIATE_MODEL_INVALID.equals(issue.getCode())
				&& issue.getMessage().contains(messagePart)
				&& issue.getMessage().contains(locationPart)),
			() -> "Expected bridge error containing '" + messagePart + "' and '" + locationPart
				+ "', got: " + issues);
	}
}
