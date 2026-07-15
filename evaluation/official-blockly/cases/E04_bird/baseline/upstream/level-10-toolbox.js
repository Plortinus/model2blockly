Bird.html.toolbox_ = function(level) {
  let xml = '<block type="bird_heading"></block>\n';
  if (level >= 2) {
    xml += `<block type="bird_noWorm" disabled="${level === 4 || level === 5}"></block>\n`;
  }
  if (level >= 4) {
    xml += `
<block type="bird_compare">
  <field name="OP">LT</field>
  <value name="A">
    <block type="bird_position" movable="false">
      <field name="XY">X</field>
    </block>
  </value>
  <value name="B">
    <block type="math_number" movable="false">
      <field name="NUM">50</field>
    </block>
  </value>
</block>
`;
  }
  if (level >= 5) {
    xml += `
<block type="bird_compare">
  <field name="OP">LT</field>
  <value name="A">
    <block type="bird_position" movable="false">
      <field name="XY">Y</field>
    </block>
  </value>
  <value name="B">
    <block type="math_number" movable="false">
      <field name="NUM">50</field>
    </block>
  </value>
</block>
`;
  }
  if (level >= 8) {
    xml += '<block type="bird_and"></block>\n';
  }
  return `<xml id="toolbox" xmlns="https://developers.google.com/blockly/xml">${xml}</xml>`;
};
