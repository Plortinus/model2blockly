      Blockly.defineBlocksWithJsonArray([
        {
          type: 'graph_set_y',
          message0: 'y = %1',
          args0: [
            {
              type: 'input_value',
              name: 'VALUE',
              check: 'Number',
            },
          ],
          colour: Blockly.Msg['VARIABLES_HUE'],
          tooltip: Blockly.Msg['VARIABLES_SET_TOOLTIP'],
          helpUrl: Blockly.Msg['VARIABLES_SET_HELPURL'],
        },
      ]);
