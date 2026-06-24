/*
 * Xtext generator entry point.
 *
 * Converts each DomainModel (from .model2blockly) into a BlocklyEditorSpec
 * (platform-independent intermediate model), serializes it as XMI, reloads
 * that XMI, then delegates to
 * BlocklyCodeGenerator to produce the Blockly HTML output files.
 *
 * This keeps the Xtext integration layer thin; the actual generation
 * logic lives in BlocklyCodeGenerator and can also be driven from
 * EcoreToBlocklyMain (Ecore input path).
 */
package io.github.plortinus.model2blockly.generator

import java.util.ArrayList
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import io.github.plortinus.model2blockly.model2Blockly.DomainModel
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer

class Model2BlocklyGenerator extends AbstractGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val codeGen = new BlocklyCodeGenerator()
		for (domain : resource.allContents.filter(DomainModel).toIterable) {
			val sourceLabel = resource.URI?.toString
			val intermediateModel = DomainModelAdapter.toEditorSpec(domain)
			BlocklySpecDiagnostics.assertValid(intermediateModel, sourceLabel,
				BlocklySpecSourceMapper.forDomainModel(domain, sourceLabel))
			val intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel)
			val reloadedIntermediateModel = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi)
			BlocklySpecDiagnostics.assertValid(reloadedIntermediateModel, sourceLabel + " intermediate XMI")
			val files = codeGen.generate(reloadedIntermediateModel)
			val generatedFiles = new ArrayList<String>()
			val intermediatePath = BlocklySpecXmiSerializer.generatedIntermediatePath(reloadedIntermediateModel)
			fsa.generateFile(intermediatePath, intermediateXmi)
			generatedFiles.add(intermediatePath)
			for (entry : files.entrySet) {
				val path = "html/" + entry.key
				fsa.generateFile(path, entry.value)
				generatedFiles.add(path)
			}
			fsa.generateFile("generation_report.html",
				GenerationReportHtmlRenderer.renderModel2BlocklySummary(domain, reloadedIntermediateModel, resource.URI?.toString, generatedFiles))
		}
	}
}
