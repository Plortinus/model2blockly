var DUCKS = [
  {name: null, id: "default", editable: true, blockly: `
<xml>
  <block type="pond_cannon" x="70" y="70">
    <value name="DEGREE">
      <shadow type="pond_math_number">
        <mutation angle_field="true" />
        <field name="NUM">0</field>
      </shadow>
    </value>
    <value name="RANGE">
      <shadow type="pond_math_number">
        <mutation angle_field="false" />
        <field name="NUM">70</field>
      </shadow>
    </value>
  </block>
</xml>`, compiled: "cannon(0, 70);", competent: false},
