      javascript.javascriptGenerator.forBlock['graph_set_y'] = function (
        block,
        generator,
      ) {
        // block.setDeletable cannot be set from json
        block.setDeletable(false);

        // y variable setter.
        var argument0 =
          generator.valueToCode(block, 'VALUE', javascript.Order.ASSIGNMENT) ||
          '';
        return 'y = ' + argument0 + ';';
      };
