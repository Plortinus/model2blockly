Blockly.Blocks['pond_math_number'] = {
  /**
   * Numeric or angle value.
   * @this {Blockly.Block}
   */
  init: function() {
    this.jsonInit({
      "message0": "%1",
      "args0": [{
        "type": "field_number",
        "name": "NUM",
        "value": 0,
      }],
      "output": "Number",
      "helpUrl": "%{BKY_MATH_NUMBER_HELPURL}",
      "colour": "%{BKY_MATH_HUE}",
      "tooltip": "%{BKY_MATH_NUMBER_TOOLTIP}",
      "extensions": ["parent_tooltip_when_inline"]
    });
  },
  /**
   * Create XML to represent whether the 'NUM' field is an angle.
   * @returns {!Element} XML storage element.
   * @this {Blockly.Block}
   */
  mutationToDom: function() {
    const container = document.createElement('mutation');
    const field = this.getField('NUM');
    const isAngle = field.constructor === Blockly.FieldAngle;
    container.setAttribute('angle_field', isAngle);
    return container;
  },
  /**
   * Parse XML to restore the 'NUM' field type.
   * @param {!Element} xmlElement XML storage element.
   * @this {Blockly.Block}
   */
  domToMutation: function(xmlElement) {
    const isAngle = (xmlElement.getAttribute('angle_field') === 'true');
    this.updateField_(isAngle);
  },
  /**
   * Switch between number or angle fields, depending on what this block
   * is plugged into.
   * @this {Blockly.Block}
   */
  onchange: function() {
    if (!this.workspace) {
      // Block has been deleted.
      return;
    }
    if (this.outputConnection.targetConnection &&
        this.outputConnection.targetConnection.check_) {
      // Plugged in to parent.
      const field = this.getField('NUM');
      if (this.outputConnection.targetConnection.check_.includes('Angle')) {
        // Parent wants an angle.
        if (field.constructor !== Blockly.FieldAngle) {
          this.updateField_(true);
        }
      } else {
        // Parent wants a number.
        if (field.constructor !== Blockly.FieldNumber) {
          this.updateField_(false);
        }
      }
    }
  },
  /**
   * Convert the 'NUM' field into either an angle or number field.
   * @param {boolean} isAngle True if angle, false if number.
   * @private
   */
  updateField_: function(isAngle) {
    Blockly.Events.disable();
    // The implicitly-created dummy input.
    const input = this.inputList[0];
    let field = this.getField('NUM');
    const value = field.getValue();
    if (isAngle) {
      input.removeField('NUM');
      field = new Blockly.FieldAngle('');
      input.appendField(field, 'NUM');
      field.setValue(value);
    } else {
      input.removeField('NUM');
      input.appendField(new Blockly.FieldNumber(value), 'NUM');
    }
    if (this.rendered) {
      this.render();
    }
    Blockly.Events.enable();
  },
};
