  const defaultXml =
      '<xml>' +
        '<block type="music_start" deletable="' +
          (BlocklyGames.LEVEL > 6) + '" x="180" y="50"></block>' +
      '</xml>';
  BlocklyInterface.loadBlocks(defaultXml,
      BlocklyGames.LEVEL !== BlocklyGames.MAX_LEVEL || transform10);
