  let blockType;
  if (BlocklyGames.LEVEL === 1) {
    blockType = 'bird_heading';
  } else if (BlocklyGames.LEVEL < 5) {
    blockType = 'bird_ifElse';
  } else {
    blockType = 'controls_if';
  }
  BlocklyInterface.loadBlocks(
      `<xml><block type="${blockType}" x="70" y="70" deletable="false"></block></xml>`, false);
