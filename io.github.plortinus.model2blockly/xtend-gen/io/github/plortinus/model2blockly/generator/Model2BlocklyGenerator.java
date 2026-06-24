/**
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
package io.github.plortinus.model2blockly.generator;

import com.google.common.collect.Iterators;
import io.github.plortinus.model2blockly.adapter.DomainModelAdapter;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecDiagnostics;
import io.github.plortinus.model2blockly.blocklyspec.BlocklySpecSourceMapper;
import io.github.plortinus.model2blockly.intermediate.BlocklySpecXmiSerializer;
import io.github.plortinus.model2blockly.intermediate.blocklyspec.EditorSpec;
import io.github.plortinus.model2blockly.model2Blockly.DomainModel;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class Model2BlocklyGenerator extends AbstractGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    final BlocklyCodeGenerator codeGen = new BlocklyCodeGenerator();
    Iterable<DomainModel> _iterable = IteratorExtensions.<DomainModel>toIterable(Iterators.<DomainModel>filter(resource.getAllContents(), DomainModel.class));
    for (final DomainModel domain : _iterable) {
      {
        URI _uRI = resource.getURI();
        String _string = null;
        if (_uRI!=null) {
          _string=_uRI.toString();
        }
        final String sourceLabel = _string;
        final EditorSpec intermediateModel = DomainModelAdapter.toEditorSpec(domain);
        BlocklySpecDiagnostics.assertValid(intermediateModel, sourceLabel, 
          BlocklySpecSourceMapper.forDomainModel(domain, sourceLabel));
        final String intermediateXmi = BlocklySpecXmiSerializer.toXmi(intermediateModel);
        final EditorSpec reloadedIntermediateModel = BlocklySpecXmiSerializer.fromXmiToEditorSpec(intermediateXmi);
        BlocklySpecDiagnostics.assertValid(reloadedIntermediateModel, (sourceLabel + " intermediate XMI"));
        final Map<String, String> files = codeGen.generate(reloadedIntermediateModel);
        final ArrayList<String> generatedFiles = new ArrayList<String>();
        final String intermediatePath = BlocklySpecXmiSerializer.generatedIntermediatePath(reloadedIntermediateModel);
        fsa.generateFile(intermediatePath, intermediateXmi);
        generatedFiles.add(intermediatePath);
        Set<Map.Entry<String, String>> _entrySet = files.entrySet();
        for (final Map.Entry<String, String> entry : _entrySet) {
          {
            String _key = entry.getKey();
            final String path = ("html/" + _key);
            fsa.generateFile(path, entry.getValue());
            generatedFiles.add(path);
          }
        }
        URI _uRI_1 = resource.getURI();
        String _string_1 = null;
        if (_uRI_1!=null) {
          _string_1=_uRI_1.toString();
        }
        fsa.generateFile("generation_report.html", 
          GenerationReportHtmlRenderer.renderModel2BlocklySummary(domain, reloadedIntermediateModel, _string_1, generatedFiles));
      }
    }
  }
}
