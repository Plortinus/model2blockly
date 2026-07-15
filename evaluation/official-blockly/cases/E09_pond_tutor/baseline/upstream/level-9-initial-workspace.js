    let defaultXml;
    if (BlocklyGames.LEVEL === 7) {
      defaultXml =
        '<xml>' +
          '<block type="pond_swim" x="70" y="70">' +
            '<value name="DEGREE">' +
              '<shadow type="pond_math_number">' +
                '<mutation angle_field="true"></mutation>' +
                '<field name="NUM">0</field>' +
              '</shadow>' +
            '</value>' +
          '</block>' +
        '</xml>';
    } else {
      defaultXml =
        '<xml>' +
          '<block type="pond_cannon" x="70" y="70">' +
            '<value name="DEGREE">' +
              '<shadow type="pond_math_number">' +
                '<mutation angle_field="true"></mutation>' +
                '<field name="NUM">0</field>' +
              '</shadow>' +
            '</value>' +
            '<value name="RANGE">' +
              '<shadow type="pond_math_number">' +
                '<mutation angle_field="false"></mutation>' +
                '<field name="NUM">70</field>' +
              '</shadow>' +
            '</value>' +
          '</block>' +
        '</xml>';
    }
    BlocklyInterface.loadBlocks(defaultXml);
