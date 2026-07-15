const toolbox = {
  kind: 'categoryToolbox',
  contents: [
    {
      kind: 'category',
      name: 'Evaluation',
      colour: '230',
      contents: [
        { kind: 'block', type: 'evaluation_number' },
        { kind: 'block', type: 'evaluation_print' },
      ],
    },
  ],
};

export default {
  caseId: 'SELF_harness',
  treatment: 'm2b',
  blockTypes: ['evaluation_number', 'evaluation_print'],
  toolbox,
  generatorMetadata: {
    evaluation_number: { kind: 'value' },
    evaluation_print: { kind: 'statement' },
  },
  register({ Blockly, javascriptGenerator }) {
    Blockly.Blocks.evaluation_number = {
      init() {
        this.appendDummyInput()
          .appendField('number')
          .appendField(new Blockly.FieldNumber(1, 0, 10, 1), 'VALUE');
        this.setOutput(true, 'Number');
        this.setColour(230);
        this.setTooltip('A number used by the harness self-test.');
        this.setHelpUrl('');
      },
    };

    Blockly.Blocks.evaluation_print = {
      init() {
        this.appendValueInput('TEXT')
          .setCheck('String')
          .appendField('print');
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setInputsInline(true);
        this.setColour(160);
        this.setTooltip('Print a value.');
        this.setHelpUrl('');
      },
    };

    javascriptGenerator.forBlock.evaluation_number = (block) => [
      String(block.getFieldValue('VALUE')),
      0,
    ];
    javascriptGenerator.forBlock.evaluation_print = () => "console.log('');\n";
  },
};
