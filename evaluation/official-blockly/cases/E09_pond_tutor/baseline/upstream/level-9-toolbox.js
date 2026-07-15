Pond.Tutor.html.toolbox_ = function(level) {
  let xml;
  const scanBlock = level >= 5 ? `
  <block type="pond_scan">
    <value name="DEGREE">
      <shadow type="pond_math_number">
        <mutation angle_field="true"></mutation>
        <field name="NUM">0</field>
      </shadow>
    </value>
  </block>
` : '';
  const swimBlock = level >= 7 ? `
  <block type="pond_swim">
    <value name="DEGREE">
      <shadow type="pond_math_number">
        <mutation angle_field="true"></mutation>
        <field name="NUM">0</field>
      </shadow>
    </value>
  </block>
` : '';
  const stopBlock = level >= 9 ? '<block type="pond_stop"></block>' : '';
  const getXYBlocks = level >= 9 ? '<block type="pond_getX"></block><block type="pond_getY"></block>' : '';
  xml = `
<category name="${BlocklyGames.getMsg('Games.pond', true)}">
  <block type="pond_cannon">
    <value name="DEGREE">
      <shadow type="pond_math_number">
        <mutation angle_field="true"></mutation>
        <field name="NUM">0</field>
      </shadow>
    </value>
    <value name="RANGE">
      <shadow type="pond_math_number">
        <mutation angle_field="false"></mutation>
        <field name="NUM">70</field>
      </shadow>
    </value>
  </block>
  ${scanBlock}
  ${swimBlock}
  ${stopBlock}
  ${getXYBlocks}
</category>
`;
  if (level >= 3) {
    const ifBlock = level >= 9 ? '<block type="controls_if"></block>' : '';
    const compareBlock = level >= 9 ? '<block type="logic_compare"></block>' : '';
    xml += `
<category name="${BlocklyGames.getMsg('Games.catLogic', true)}">
  ${ifBlock}
  ${compareBlock}
  <block type="logic_boolean"></block>
</category>
<category name="${BlocklyGames.getMsg('Games.catLoops', true)}">
  <block type="controls_whileUntil"></block>
</category>
`;
  }
  xml += `
<category name="${BlocklyGames.getMsg('Games.catMath', true)}">
  <block type="pond_math_number">
    <mutation angle_field="false"></mutation>
  </block>
</category>
`;
  return `<xml id="toolbox" xmlns="https://developers.google.com/blockly/xml">${xml}</xml>`;
};
