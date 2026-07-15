Maze.html.toolbox_ = function(level) {
  let xml = `
<block type="maze_moveForward"></block>
<block type="maze_turn"><field name="DIR">turnLeft</field></block>
<block type="maze_turn"><field name="DIR">turnRight</field></block>
`;
  if (level > 2) {
    xml += '<block type="maze_forever"></block>\n';
    if (level === 6) {
      xml += '<block type="maze_if"><field name="DIR">isPathLeft</field></block>\n';
    } else if (level > 6) {
      xml += '<block type="maze_if"></block>\n';
      if (level > 8) {
        xml += '<block type="maze_ifElse"></block>\n';
      }
    }
  }
  return `<xml id="toolbox" xmlns="https://developers.google.com/blockly/xml">${xml}</xml>`;
};
