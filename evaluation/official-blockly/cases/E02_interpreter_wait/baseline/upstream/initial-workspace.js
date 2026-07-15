      const startBlocks = {
        blocks: {
          blocks: [
            {
              type: 'variables_set',
              x: 20,
              y: 20,
              inline: true,
              fields: {
                VAR: {id: 'n'},
              },
              inputs: {
                VALUE: {
                  block: {
                    type: 'math_number',
                    fields: {NUM: 1},
                  },
                },
              },
              next: {
                block: {
                  type: 'controls_repeat_ext',
                  inline: true,
                  inputs: {
                    TIMES: {
                      block: {
                        type: 'math_number',
                        fields: {NUM: 4},
                      },
                    },
                    DO: {
                      block: {
                        type: 'wait_seconds',
                        fields: {SECONDS: 1},
                        next: {
                          block: {
                            type: 'variables_set',
                            inline: true,
                            fields: {
                              VAR: {id: 'n'},
                            },
                            inputs: {
                              VALUE: {
                                block: {
                                  type: 'math_arithmetic',
                                  fields: {OP: 'MULTIPLY'},
                                  inputs: {
                                    A: {
                                      block: {
                                        type: 'variables_get',
                                        fields: {
                                          VAR: {id: 'n'},
                                        },
                                      },
                                    },
                                    B: {
                                      block: {
                                        type: 'math_number',
                                        fields: {NUM: 2},
                                      },
                                    },
                                  },
                                },
                              },
                            },
                            next: {
                              block: {
                                type: 'text_print',
                                inline: false,
                                inputs: {
                                  TEXT: {
                                    block: {
                                      type: 'variables_get',
                                      fields: {
                                        VAR: {id: 'n'},
                                      },
                                    },
                                  },
                                },
                              },
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
        variables: [
          {
            name: 'n',
            id: 'n',
          },
        ],
      };
