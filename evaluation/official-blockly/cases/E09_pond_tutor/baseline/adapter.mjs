const blockTypes = [
  'pond_scan',
  'pond_cannon',
  'pond_swim',
  'pond_stop',
  'pond_getX',
  'pond_getY',
  'controls_if',
  'logic_compare',
  'logic_boolean',
  'controls_whileUntil',
  'pond_math_number',
];

const adapter = {
  caseId: 'E09_pond_tutor',
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
    pond_math_number: [
      'angle-field-switch',
      'parent-tooltip-when-inline',
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
    pond_stop: {
      kind: 'statement',
      template: 'stop();',
    },
    pond_getX: {
      kind: 'value',
      template: 'getX()',
    },
    pond_getY: {
      kind: 'value',
      template: 'getY()',
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
    logic_boolean: {
      kind: 'value',
      template: '{{mapped:BOOL:TRUE=true,FALSE=false}}',
    },
    controls_whileUntil: {
      kind: 'statement',
      template: 'while ({{value:BOOL}}) {\n{{statements:DO}}\n}',
      sideEffects: ['missing-condition-default-false', 'infinite-loop-trap'],
    },
    pond_math_number: {
      kind: 'value',
      template: '{{NUM}}',
    },
  },
  async register({ Blockly, javascriptGenerator }) {
    const [
      domainBlockSource,
      actionGeneratorsSource,
      coordinateGeneratorsSource,
      adaptiveNumberSource,
      standardBlockSource,
      toolboxSource,
      initialWorkspaceSource,
      pondCategorySource,
      standardCategoriesSource,
      actionMessagesSource,
      coordinateMessagesSource,
    ] = await Promise.all([
      load('./upstream/domain-block-runtime.js'),
      load('./upstream/level-9-domain-action-generators.js'),
      load('./upstream/level-9-coordinate-generators.js'),
      load('./upstream/adaptive-number-block.js'),
      load('./upstream/standard-block-runtime.js'),
      load('./upstream/level-9-toolbox.js'),
      load('./upstream/level-9-initial-workspace.js'),
      load('./upstream/pond-category-message.jsonfrag'),
      load('./upstream/standard-category-messages.jsonfrag'),
      load('./upstream/level-9-action-messages.jsonfrag'),
      load('./upstream/level-9-coordinate-messages.jsonfrag'),
    ]);

    const messages = Function(`return ({${pondCategorySource}${standardCategoriesSource}${actionMessagesSource}${coordinateMessagesSource}});`)();
    const BlocklyGames = {
      LEVEL: 9,
      MAX_LEVEL: 10,
      getMsg(key) {
        return messages[key] ?? key;
      },
    };
    const Pond = { Blocks: {}, Tutor: { html: {} } };
    Blockly.JavaScript = javascriptGenerator;
    installLegacyMutatorCompatibility(Blockly);

    Function('Blockly', 'BlocklyGames', 'Pond', domainBlockSource)(Blockly, BlocklyGames, Pond);
    Pond.Blocks.init();
    Function('Blockly', adaptiveNumberSource)(Blockly);
    Function('Blockly', standardBlockSource)(Blockly);

    javascriptGenerator.math_number = javascriptGenerator.forBlock.math_number;
    Function('Blockly', actionGeneratorsSource)(Blockly);
    Function('Blockly', coordinateGeneratorsSource)(Blockly);
    for (const type of ['pond_scan', 'pond_cannon', 'pond_swim', 'pond_stop', 'pond_getX', 'pond_getY']) {
      javascriptGenerator.forBlock[type] = Blockly.JavaScript[type];
    }
    javascriptGenerator.forBlock.pond_math_number = javascriptGenerator.forBlock.math_number;

    Function('BlocklyGames', 'Pond', toolboxSource)(BlocklyGames, Pond);
    const toolboxXml = Blockly.utils.xml.textToDom(Pond.Tutor.html.toolbox_(9));
    adapter.toolbox = Blockly.utils.toolbox.convertToolboxDefToJson(toolboxXml);

    const BlocklyInterface = {
      loadBlocks(xmlText) {
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
    Function('BlocklyGames', 'BlocklyInterface', initialWorkspaceSource)(BlocklyGames, BlocklyInterface);
  },
};

export default adapter;

function installLegacyMutatorCompatibility(Blockly) {
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
