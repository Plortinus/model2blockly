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
  treatment: 'baseline',
  blockTypes: ['evaluation_number', 'evaluation_print'],
  toolbox,
  generatorMetadata: {
    evaluation_number: { kind: 'value' },
    evaluation_print: { kind: 'statement' },
  },
  register({ Blockly, javascriptGenerator }) {
    Blockly.defineBlocksWithJsonArray([
      {
        type: 'evaluation_number',
        message0: 'number %1',
        args0: [
          {
            type: 'field_number',
            name: 'VALUE',
            value: 1,
            min: 0,
            max: 10,
            precision: 1,
          },
        ],
        output: 'Number',
        colour: 230,
        tooltip: 'A number used by the harness self-test.',
        helpUrl: '',
      },
      {
        type: 'evaluation_print',
        message0: 'print %1',
        args0: [
          {
            type: 'input_value',
            name: 'TEXT',
            check: 'String',
          },
        ],
        previousStatement: null,
        nextStatement: null,
        inputsInline: true,
        colour: 160,
        tooltip: 'Print a value.',
        helpUrl: '',
      },
    ]);

    javascriptGenerator.forBlock.evaluation_number = (block) => [
      String(block.getFieldValue('VALUE')),
      0,
    ];
    javascriptGenerator.forBlock.evaluation_print = () => "console.log('');\n";
  },
};
