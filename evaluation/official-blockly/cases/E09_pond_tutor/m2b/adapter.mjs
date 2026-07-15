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

const valueTypes = new Set([
  'pond_scan',
  'pond_getX',
  'pond_getY',
  'logic_compare',
  'logic_boolean',
  'pond_math_number',
]);

const adapter = {
  caseId: 'E09_pond_tutor',
  treatment: 'm2b',
  blockTypes,
  toolbox: null,
  initialWorkspace: null,
  dynamicBehaviour: {},
  generatorMetadata: {},
  async register({ Blockly, javascriptGenerator }) {
    const javascript = window.javascript;
    const [blocksSource, toolboxSource, generatorsSource] = await Promise.all([
      load('../generated/html/PondTutor_blocks.js'),
      load('../generated/html/PondTutor_toolbox.js'),
      load('../generated/html/PondTutor_generators.js'),
    ]);

    Function('Blockly', 'javascript', 'window', blocksSource)(Blockly, javascript, window);
    Blockly.defineBlocksWithJsonArray(window.BLOCKLY_BLOCKS);
    Function('Blockly', 'javascript', 'window', toolboxSource)(Blockly, javascript, window);
    Function('Blockly', 'javascript', 'window', generatorsSource)(Blockly, javascript, window);
    adapter.toolbox = window.BLOCKLY_TOOLBOX;
    adapter.generatorMetadata = Object.fromEntries(blockTypes.map((type) => {
      const config = window.BLOCKLY_DOMAIN_CODEGEN?.blocks?.[type] || {};
      return [type, {
        kind: valueTypes.has(type) ? 'value' : 'statement',
        template: config.template ?? null,
      }];
    }));

    if (javascriptGenerator !== javascript.javascriptGenerator) {
      throw new Error('Unexpected JavaScript generator instance.');
    }
  },
};

export default adapter;

async function load(relative) {
  const response = await fetch(new URL(relative, import.meta.url));
  if (!response.ok) throw new Error(`Unable to load ${relative}: HTTP ${response.status}`);
  return response.text();
}
