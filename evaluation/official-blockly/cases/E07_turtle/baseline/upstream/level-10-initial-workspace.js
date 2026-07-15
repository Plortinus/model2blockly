  let defaultXml;
  if (BlocklyGames.LEVEL === BlocklyGames.MAX_LEVEL) {
    defaultXml =
        '<xml>' +
          '<block type="turtle_move" x="70" y="70">' +
            '<value name="VALUE">' +
              '<shadow type="math_number">' +
                '<field name="NUM">10</field>' +
              '</shadow>' +
            '</value>' +
          '</block>' +
        '</xml>';
  } else {
    defaultXml =
        '<xml>' +
          '<block type="turtle_move_internal" x="70" y="70">' +
            '<field name="VALUE">100</field>' +
          '</block>' +
        '</xml>';
  }
  BlocklyInterface.loadBlocks(defaultXml,
      BlocklyGames.LEVEL !== BlocklyGames.MAX_LEVEL || transform10);
