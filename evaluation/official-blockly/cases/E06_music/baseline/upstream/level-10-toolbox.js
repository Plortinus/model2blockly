Music.html.toolbox_ = function(level) {
  let xml;
  if (level === 10) {
    xml = `
<category name="${BlocklyGames.getMsg('Games.music', true)}">
  <block type="music_pitch">
    <field name="PITCH">7</field>
  </block>
  <block type="music_note">
    <field name="DURATION">0.25</field>
    <value name="PITCH">
      <shadow type="music_pitch">
        <field name="PITCH">7</field>
      </shadow>
    </value>
  </block>
  <block type="music_rest"></block>
  <block type="music_instrument"></block>
  <block type="music_start" id="music_start"></block>
</category>
<category name="${BlocklyGames.getMsg('Games.catLogic', true)}">
  <block type="controls_if"></block>
  <block type="logic_compare"></block>
  <block type="logic_operation"></block>
  <block type="logic_negate"></block>
  <block type="logic_boolean"></block>
  <block type="logic_ternary"></block>
</category>
<category name="${BlocklyGames.getMsg('Games.catLoops', true)}">
  <block type="controls_repeat_ext">
    <value name="TIMES">
      <shadow type="math_number">
        <field name="NUM">10</field>
      </shadow>
    </value>
  </block>
  <block type="controls_whileUntil"></block>
  <block type="controls_for">
    <value name="FROM">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
    <value name="TO">
      <shadow type="math_number">
        <field name="NUM">10</field>
      </shadow>
    </value>
    <value name="BY">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
  </block>
  <block type="controls_forEach"></block>
  <block type="controls_flow_statements"></block>
</category>
<category name="${BlocklyGames.getMsg('Games.catMath', true)}">
  <block type="math_number"></block>
  <block type="math_arithmetic">
    <value name="A">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
    <value name="B">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
  </block>
  <block type="math_single">
    <value name="NUM">
      <shadow type="math_number">
        <field name="NUM">9</field>
      </shadow>
    </value>
  </block>
  <block type="math_number_property">
    <value name="NUMBER_TO_CHECK">
      <shadow type="math_number">
        <field name="NUM">0</field>
      </shadow>
    </value>
  </block>
  <block type="math_round">
    <value name="NUM">
      <shadow type="math_number">
        <field name="NUM">3.1</field>
      </shadow>
    </value>
  </block>
  <block type="math_modulo">
    <value name="DIVIDEND">
      <shadow type="math_number">
        <field name="NUM">64</field>
      </shadow>
    </value>
    <value name="DIVISOR">
      <shadow type="math_number">
        <field name="NUM">10</field>
      </shadow>
    </value>
  </block>
  <block type="math_constrain">
    <value name="VALUE">
      <shadow type="math_number">
        <field name="NUM">50</field>
      </shadow>
    </value>
    <value name="LOW">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
    <value name="HIGH">
      <shadow type="math_number">
        <field name="NUM">100</field>
      </shadow>
    </value>
  </block>
  <block type="math_random_int">
    <value name="FROM">
      <shadow type="math_number">
        <field name="NUM">1</field>
      </shadow>
    </value>
    <value name="TO">
      <shadow type="math_number">
        <field name="NUM">100</field>
      </shadow>
    </value>
  </block>
  <block type="math_random_float"></block>
</category>
<category name="${BlocklyGames.getMsg('Games.catLists', true)}">
  <block type="lists_create_with">
    <mutation items="0"></mutation>
  </block>
  <block type="lists_create_with"></block>
  <block type="lists_repeat">
    <value name="NUM">
      <shadow type="math_number">
        <field name="NUM">5</field>
      </shadow>
    </value>
  </block>
  <block type="lists_length"></block>
  <block type="lists_isEmpty"></block>
  <block type="lists_indexOf">
    <value name="VALUE">
      <block type="variables_get">
        <field name="VAR">list</field>
      </block>
    </value>
  </block>
  <block type="lists_getIndex">
    <value name="VALUE">
      <block type="variables_get">
        <field name="VAR">list</field>
      </block>
    </value>
  </block>
  <block type="lists_setIndex">
    <value name="LIST">
      <block type="variables_get">
        <field name="VAR">list</field>
      </block>
    </value>
  </block>
  <block type="lists_getSublist">
    <value name="LIST">
      <block type="variables_get">
        <field name="VAR">list</field>
      </block>
    </value>
  </block>
  <block type="lists_sort"></block>
  <block type="lists_reverse"></block>
</category>
<sep></sep>
<category name="${BlocklyGames.getMsg('Games.catVariables', true)}" custom="VARIABLE"></category>
<category name="${BlocklyGames.getMsg('Games.catProcedures', true)}" custom="PROCEDURE"></category>
`;
  } else {
    const restBlock = level > 6 ? '<block type="music_rest_whole"></block>' : '';
    const instrumentBlock = level > 5 ? '<block type="music_instrument"></block>' : '';
    const startBlock = level > 6 ? '<block type="music_start" id="music_start"></block>' : '';
    const procedureCat = level > 1 ? `<category name="${BlocklyGames.getMsg('Games.catProcedures', true)}" custom="PROCEDURE"></category>` : '';
    xml = `
<category name="${BlocklyGames.getMsg('Games.music', true)}">
  <block type="music_note">
    <field name="DURATION">0.25</field>
    <value name="PITCH">
      <shadow type="music_pitch">
        <field name="PITCH">7</field>
      </shadow>
    </value>
  </block>
  ${restBlock}
  ${instrumentBlock}
  ${startBlock}
</category>
${procedureCat}
`;
  }
  return `<xml id="toolbox" xmlns="https://developers.google.com/blockly/xml">${xml}</xml>`;
};
