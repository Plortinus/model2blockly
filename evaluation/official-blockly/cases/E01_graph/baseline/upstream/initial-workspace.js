      const startBlocks = {
        blocks: {
          blocks: [
            {
              kind: 'block',
              type: 'graph_set_y',
              x: 100,
              y: 100,
              inputs: {
                VALUE: {
                  block: {
                    type: 'math_arithmetic',
                    fields: {
                      OP: 'POWER',
                    },
                    inputs: {
                      A: {
                        block: {
                          type: 'graph_get_x',
                          shadow: {
                            type: 'math_number',
                            fields: {
                              NUM: 1,
                            },
                          },
                        },
                      },
                      B: {
                        block: {
                          type: 'math_number',
                          fields: {
                            NUM: 2,
                          },
                          shadow: {
                            type: 'math_number',
                            fields: {
                              NUM: 1,
                            },
                          },
                        },
                      },
                    },
                  },
                },
              },
            },
          ],
        },
      };
