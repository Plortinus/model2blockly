const blockTypes = [
  'pond_scan',
  'pond_cannon',
  'pond_swim',
  'pond_stop',
  'pond_health',
  'pond_speed',
  'pond_getX',
  'pond_getY',
  'pond_log',
  'controls_if',
  'logic_compare',
  'logic_operation',
  'logic_boolean',
  'controls_whileUntil',
  'pond_math_number',
  'math_arithmetic',
  'pond_math_single',
  'math_random_float',
  'math_change',
  'variables_set',
  'procedures_defnoreturn',
  'procedures_defreturn',
  'procedures_callnoreturn',
  'procedures_callreturn',
];

const adapter = {
  caseId: 'E10_pond_duck',
  treatment: 'baseline',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {
    controls_if: [
      'dynamic-elseif-else-shape',
      'legacy-mutator:controls_if',
      'tooltip-from-mutator-state',
    ],
    logic_compare: [
      'connection-type-validator',
      'dropdown-dependent-tooltip',
    ],
    logic_operation: ['dropdown-dependent-tooltip'],
    pond_math_number: [
      'angle-field-switch',
      'parent-tooltip-when-inline',
    ],
    math_arithmetic: ['dropdown-dependent-tooltip'],
    pond_math_single: ['dropdown-dependent-tooltip'],
    math_change: ['variable-field', 'variable-dependent-tooltip'],
    variables_set: ['variable-field', 'variable-context-menu'],
    procedures_defnoreturn: [
      'dynamic-parameter-list',
      'legacy-mutator:procedure-arguments',
      'procedure-rename',
      'statement-body-toggle',
    ],
    procedures_defreturn: [
      'dynamic-parameter-list',
      'legacy-mutator:procedure-arguments',
      'procedure-rename',
      'statement-body-toggle',
    ],
    procedures_callnoreturn: [
      'dynamic-argument-inputs',
      'procedure-rename',
    ],
    procedures_callreturn: [
      'dynamic-argument-inputs',
      'procedure-rename',
    ],
  },
  generatorMetadata: {
    pond_scan: {
      kind: 'value',
      template: 'scan({{value:DEGREE}})',
      sideEffects: ['missing-degree-default-zero'],
    },
    pond_cannon: {
      kind: 'statement',
      template: 'cannon({{value:DEGREE}}, {{value:RANGE}});',
      sideEffects: ['missing-degree-default-zero', 'missing-range-default-zero'],
    },
    pond_swim: {
      kind: 'statement',
      template: 'swim({{value:DEGREE}});',
      sideEffects: ['missing-degree-default-zero'],
    },
    pond_stop: { kind: 'statement', template: 'stop();' },
    pond_health: { kind: 'value', template: 'health()' },
    pond_speed: { kind: 'value', template: 'speed()' },
    pond_getX: { kind: 'value', template: 'getX()' },
    pond_getY: { kind: 'value', template: 'getY()' },
    pond_log: {
      kind: 'statement',
      template: 'log({{value:VALUE}});',
      sideEffects: ['missing-value-default-empty-string'],
    },
    controls_if: {
      kind: 'statement',
      template: 'if ({{value:IF0}}) {\n{{statements:DO0}}\n}{{mutated:elseif-else}}',
      sideEffects: ['missing-condition-default-false'],
    },
    logic_compare: {
      kind: 'value',
      template: '{{value:A}} {{mapped:OP:EQ===,NEQ=!=,LT=<,LTE=<=,GT=>,GTE=>=}} {{value:B}}',
      sideEffects: ['missing-operands-default-empty-string'],
    },
    logic_operation: {
      kind: 'value',
      template: '{{value:A}} {{mapped:OP:AND=&&,OR=||}} {{value:B}}',
      sideEffects: ['missing-operands-default-false'],
    },
    logic_boolean: {
      kind: 'value',
      template: '{{mapped:BOOL:TRUE=true,FALSE=false}}',
    },
    controls_whileUntil: {
      kind: 'statement',
      template: 'while ({{value:BOOL}}) {\n{{statements:DO}}\n}',
      sideEffects: ['missing-condition-default-false', 'infinite-loop-trap'],
    },
    pond_math_number: { kind: 'value', template: '{{NUM}}' },
    math_arithmetic: {
      kind: 'value',
      template: '{{value:A}} {{mapped:OP:ADD=+,MINUS=-,MULTIPLY=*,DIVIDE=/}} {{value:B}}',
      sideEffects: ['missing-operands-default-zero'],
    },
    pond_math_single: {
      kind: 'value',
      template: 'Math.{{mapped:OP:ROOT=sqrt,ABS=abs,SIN=sin_deg,COS=cos_deg,TAN=tan_deg,ASIN=asin_deg,ACOS=acos_deg,ATAN=atan_deg}}({{value:NUM}})',
      sideEffects: ['missing-operand-default-zero'],
    },
    math_random_float: { kind: 'value', template: 'Math.random()' },
    math_change: {
      kind: 'statement',
      template: '{{variable:VAR}} += {{value:DELTA}};',
      sideEffects: ['missing-delta-default-zero', 'variable-name-database'],
    },
    variables_set: {
      kind: 'statement',
      template: 'var {{variable:VAR}} = {{value:VALUE}};',
      sideEffects: ['missing-value-default-zero', 'variable-name-database'],
    },
    procedures_defnoreturn: {
      kind: 'statement',
      template: 'function {{procedure:NAME}}({{mutated:parameters}}) {\n{{statements:STACK}}\n}',
      sideEffects: ['procedure-definition-database'],
    },
    procedures_defreturn: {
      kind: 'statement',
      template: 'function {{procedure:NAME}}({{mutated:parameters}}) {\n{{statements:STACK}}\nreturn {{value:RETURN}};\n}',
      sideEffects: ['procedure-definition-database', 'missing-return-default-empty-string'],
    },
    procedures_callnoreturn: {
      kind: 'statement',
      template: '{{procedure:NAME}}({{mutated:arguments}});',
      sideEffects: ['procedure-name-database'],
    },
    procedures_callreturn: {
      kind: 'value',
      template: '{{procedure:NAME}}({{mutated:arguments}})',
      sideEffects: ['procedure-name-database'],
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const [
      domainBlocksSource,
      domainGeneratorsSource,
      adaptiveNumberSource,
      unaryMathSource,
      unaryMathGeneratorSource,
      standardBlockSource,
      toolboxSource,
      initialWorkspaceSource,
      pondCategorySource,
      standardCategoriesSource,
      pondMessagesSource,
    ] = await Promise.all([
      load('./upstream/domain-blocks.js'),
      load('./upstream/domain-generators.js'),
      load('./upstream/adaptive-number-block.js'),
      load('./upstream/advanced-unary-math-block.js'),
      load('./upstream/advanced-unary-math-generator.js'),
      load('./upstream/standard-block-runtime.js'),
      load('./upstream/full-toolbox.js'),
      load('./upstream/default-editable-duck-workspace.js'),
      load('./upstream/pond-category-message.jsonfrag'),
      load('./upstream/standard-category-messages.jsonfrag'),
      load('./upstream/pond-block-messages.jsonfrag'),
    ]);

    const messages = Function(`return ({${pondCategorySource}${standardCategoriesSource}${pondMessagesSource}});`)();
    const BlocklyGames = {
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Pond = { Blocks: {}, Duck: { html: {} } };
    Blockly.JavaScript = javascriptGenerator;
    installLegacyCompatibility(Blockly);

    Function('Blockly', 'BlocklyGames', 'Pond', domainBlocksSource)(Blockly, BlocklyGames, Pond);
    Pond.Blocks.init();
    Function('Blockly', adaptiveNumberSource)(Blockly);
    Function('Blockly', unaryMathSource)(Blockly);
    Function('Blockly', standardBlockSource)(Blockly);

    javascriptGenerator.math_number = javascriptGenerator.forBlock.math_number;
    Function('Blockly', domainGeneratorsSource)(Blockly);
    Function('Blockly', unaryMathGeneratorSource)(Blockly);
    const legacyGeneratorTypes = [
      'pond_scan',
      'pond_cannon',
      'pond_swim',
      'pond_stop',
      'pond_health',
      'pond_speed',
      'pond_getX',
      'pond_getY',
      'pond_log',
      'pond_math_single',
      'math_change',
    ];
    for (const type of legacyGeneratorTypes) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }
    javascriptGenerator.forBlock.pond_math_number = javascriptGenerator.forBlock.math_number;

    Function('BlocklyGames', 'Pond', toolboxSource)(BlocklyGames, Pond);
    const toolboxXml = Blockly.utils.xml.textToDom(Pond.Duck.html.toolbox_());
    adapter.toolbox = Blockly.utils.toolbox.convertToolboxDefToJson(toolboxXml);

    const xmlText = initialWorkspaceSource.match(/blockly:\s*`([\s\S]*?)`/)?.[1];
    if (!xmlText) throw new Error('Default editable duck workspace was not found.');
    const workspace = new Blockly.Workspace();
    try {
      const xml = Blockly.utils.xml.textToDom(xmlText);
      Blockly.Xml.domToWorkspace(xml, workspace);
      adapter.initialWorkspace = Blockly.serialization.workspaces.save(workspace);
    } finally {
      workspace.dispose();
    }
  },
};

export default adapter;

function installLegacyCompatibility(Blockly) {
  if (typeof Blockly.FieldAngle !== 'function') {
    Blockly.FieldAngle = class LegacyFieldAngle extends Blockly.FieldNumber {
      constructor(value) {
        super(value ?? 0, 0, 360, 1);
      }

      getText() {
        return `${super.getText()}°`;
      }
    };
  }

  Blockly.ALIGN_RIGHT ??= Blockly.inputs?.Align?.RIGHT ?? 2;

  const workspace = new Blockly.Workspace();
  const stock = workspace.newBlock('controls_if');
  const methodNames = [
    'mutationToDom',
    'domToMutation',
    'decompose',
    'compose',
    'saveConnections',
  ];
  const mixin = Object.fromEntries(methodNames
    .filter((name) => typeof stock[name] === 'function')
    .map((name) => [name, stock[name]]));
  stock.dispose();
  workspace.dispose();

  Blockly.Constants = Blockly.Constants || {};
  Blockly.Constants.Logic = Blockly.Constants.Logic || {};
  Blockly.Constants.Logic.CONTROLS_IF_MUTATOR_MIXIN = mixin;
  Blockly.Constants.Logic.CONTROLS_IF_TOOLTIP_EXTENSION = function() {
    this.setTooltip(() => {
      if (!this.elseifCount_ && !this.elseCount_) return Blockly.Msg.CONTROLS_IF_TOOLTIP_1;
      if (!this.elseifCount_ && this.elseCount_) return Blockly.Msg.CONTROLS_IF_TOOLTIP_2;
      return Blockly.Msg.CONTROLS_IF_TOOLTIP_3;
    });
  };

  const blockPrototype = Blockly.BlockSvg?.prototype ?? Blockly.Block.prototype;
  const originalSetMutator = blockPrototype.setMutator;
  Blockly.Mutator = function(quarkNames) {
    this.quarkNames = quarkNames;
  };
  blockPrototype.setMutator = function(mutator) {
    if (mutator instanceof Blockly.Mutator) {
      return originalSetMutator.call(
        this,
        new Blockly.icons.MutatorIcon(mutator.quarkNames, this),
      );
    }
    return originalSetMutator.call(this, mutator);
  };
}

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
