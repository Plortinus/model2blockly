// Validation block runtime for domain "Autocategory".
// Auto-generated from EditorSpec.
(function(root) {
  const VALIDATION_BLOCK_MODEL = JSON.parse("{\n  \"format\": \"model2blockly.validationBlocks.v1\",\n  \"domain\": \"Autocategory\",\n  \"customBlockTypes\": [\n    { \"type\": \"validation_rule\", \"label\": \"Validation rule wrapper\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"wrapper\", \"expressionTemplate\": \"\" } },\n    { \"type\": \"validation_required_field\", \"label\": \"Validation rule: required field/reference\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"requiredField\", \"expressionTemplate\": \"has(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_statement_cardinality\", \"label\": \"Validation rule: statement input cardinality\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"statementCardinality\", \"expressionTemplate\": \"inputCount(\\\"${INPUT}\\\") within ${MIN}..${MAX}\" } },\n    { \"type\": \"validation_field_cardinality\", \"label\": \"Validation rule: multi-valued field/reference cardinality\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"fieldCardinality\", \"expressionTemplate\": \"fieldCount(\\\"${FIELD}\\\") within ${MIN}..${MAX}\" } },\n    { \"type\": \"validation_expression_rule\", \"label\": \"Validation rule: custom semantic expression\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"semanticExpression\", \"expressionTemplate\": \"${EXPRESSION}\" } },\n    { \"type\": \"validation_field_exists\", \"label\": \"Domain accessor: field/reference has a value\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"expressionNode\", \"expressionTemplate\": \"has(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_field_value\", \"label\": \"Domain accessor: field/reference value as text\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"expressionNode\", \"expressionTemplate\": \"value(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_field_number\", \"label\": \"Domain accessor: field value as number\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"expressionNode\", \"expressionTemplate\": \"number(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_field_count\", \"label\": \"Domain accessor: multi-valued field/reference count\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"expressionNode\", \"expressionTemplate\": \"fieldCount(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_field_unique\", \"label\": \"Domain accessor: multi-valued field/reference values are unique\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"fieldUnique\", \"expressionTemplate\": \"fieldUnique(\\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_type_field_unique\", \"label\": \"Domain accessor: field values are unique across a block type\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"typeFieldUnique\", \"expressionTemplate\": \"typeFieldUnique(\\\"${TYPE}\\\", \\\"${FIELD}\\\")\" } },\n    { \"type\": \"validation_input_count\", \"label\": \"Domain accessor: statement input child count\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"expressionNode\", \"expressionTemplate\": \"inputCount(\\\"${INPUT}\\\")\" } },\n    { \"type\": \"validation_previous_block_is\", \"label\": \"Domain accessor: previous block type\", \"generator\": { \"format\": \"model2blockly.validationGenerator.v1\", \"runtimeCheck\": \"previousBlockIs\", \"expressionTemplate\": \"previousBlockIs(\\\"${TYPE}\\\")\" } }\n  ],\n  \"blockDefinitions\": [\n    {\n      \"type\": \"validation_rule\",\n      \"message0\": \"rule %1\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"NAME\", \"text\": \"validation\"}],\n      \"message1\": \"target %1 type %2\",\n      \"args1\": [\n        {\"type\": \"field_input\", \"name\": \"TARGET\", \"text\": \"-\"},\n        {\"type\": \"field_input\", \"name\": \"VALIDATION_TYPE\", \"text\": \"-\"}\n      ],\n      \"message2\": \"message %1\",\n      \"args2\": [{\"type\": \"field_input\", \"name\": \"MESSAGE\", \"text\": \"\"}],\n      \"message3\": \"condition %1\",\n      \"args3\": [{\"type\": \"input_value\", \"name\": \"CONDITION\", \"check\": \"Boolean\"}],\n      \"colour\": 20,\n      \"tooltip\": \"Generated validation rule\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_required_field\",\n      \"message0\": \"%1.%2 is required\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}\n      ],\n      \"message1\": \"kind %1\",\n      \"args1\": [{\"type\": \"field_input\", \"name\": \"FIELD_KIND\", \"text\": \"field\"}],\n      \"output\": \"Boolean\",\n      \"colour\": 145,\n      \"tooltip\": \"Checks that a required field or reference has a value\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_statement_cardinality\",\n      \"message0\": \"%1.%2 children count\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"INPUT\", \"text\": \"children\"}\n      ],\n      \"message1\": \"min %1 max %2\",\n      \"args1\": [\n        {\"type\": \"field_input\", \"name\": \"MIN\", \"text\": \"0\"},\n        {\"type\": \"field_input\", \"name\": \"MAX\", \"text\": \"unbounded\"}\n      ],\n      \"output\": \"Boolean\",\n      \"colour\": 260,\n      \"tooltip\": \"Checks the number of child blocks in a statement input\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_cardinality\",\n      \"message0\": \"%1.%2 value count\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}\n      ],\n      \"message1\": \"kind %1 min %2 max %3\",\n      \"args1\": [\n        {\"type\": \"field_input\", \"name\": \"FIELD_KIND\", \"text\": \"field\"},\n        {\"type\": \"field_input\", \"name\": \"MIN\", \"text\": \"0\"},\n        {\"type\": \"field_input\", \"name\": \"MAX\", \"text\": \"unbounded\"}\n      ],\n      \"output\": \"Boolean\",\n      \"colour\": 260,\n      \"tooltip\": \"Checks the number of values in a multi-valued field or reference\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_expression_rule\",\n      \"message0\": \"%1 expression %2\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"EXPRESSION\", \"text\": \"true\"}\n      ],\n      \"message1\": \"expanded %1\",\n      \"args1\": [{\"type\": \"input_value\", \"name\": \"CONDITION\", \"check\": \"Boolean\"}],\n      \"output\": \"Boolean\",\n      \"colour\": 0,\n      \"tooltip\": \"Semantic expression validation, optionally expanded into lower-level blocks\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_exists\",\n      \"message0\": \"field %1 exists\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}],\n      \"output\": \"Boolean\",\n      \"colour\": 145,\n      \"tooltip\": \"Checks whether a field or reference has a value\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_value\",\n      \"message0\": \"field %1 text\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}],\n      \"output\": \"String\",\n      \"colour\": 160,\n      \"tooltip\": \"Reads a field value as text\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_number\",\n      \"message0\": \"field %1 number\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}],\n      \"output\": \"Number\",\n      \"colour\": 230,\n      \"tooltip\": \"Reads a field value as a number\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_count\",\n      \"message0\": \"field %1 count\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}],\n      \"output\": \"Number\",\n      \"colour\": 260,\n      \"tooltip\": \"Counts values in a multi-valued field or reference\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_field_unique\",\n      \"message0\": \"%1.%2 values unique\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}\n      ],\n      \"message1\": \"kind %1\",\n      \"args1\": [{\"type\": \"field_input\", \"name\": \"FIELD_KIND\", \"text\": \"field\"}],\n      \"output\": \"Boolean\",\n      \"colour\": 145,\n      \"tooltip\": \"Checks that values inside a multi-valued field or reference are unique\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_type_field_unique\",\n      \"message0\": \"all %1 field %2 unique\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"},\n        {\"type\": \"field_input\", \"name\": \"FIELD\", \"text\": \"field\"}\n      ],\n      \"output\": \"Boolean\",\n      \"colour\": 145,\n      \"tooltip\": \"Checks that field values are unique across all blocks of a type\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_input_count\",\n      \"message0\": \"input %1 count\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"INPUT\", \"text\": \"children\"}],\n      \"output\": \"Number\",\n      \"colour\": 260,\n      \"tooltip\": \"Counts child blocks in a statement input\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_previous_block_is\",\n      \"message0\": \"%1 previous block is %2\",\n      \"args0\": [\n        {\"type\": \"field_input\", \"name\": \"TARGET\", \"text\": \"target\"},\n        {\"type\": \"field_input\", \"name\": \"TYPE\", \"text\": \"Type\"}\n      ],\n      \"output\": \"Boolean\",\n      \"colour\": 35,\n      \"tooltip\": \"Checks the previous connected block type\",\n      \"helpUrl\": \"\"\n    },\n    {\n      \"type\": \"validation_custom\",\n      \"message0\": \"custom check %1\",\n      \"args0\": [{\"type\": \"field_input\", \"name\": \"VALUE\", \"text\": \"\"}],\n      \"output\": \"Boolean\",\n      \"colour\": 0,\n      \"tooltip\": \"Custom validation check\",\n      \"helpUrl\": \"\"\n    }\n  ],\n  \"rules\": [\n    {\n      \"name\": \"Sprite_commands\",\n      \"validationType\": \"CARDINALITY\",\n      \"targetType\": \"Sprite\",\n      \"message\": \"Sprite.commands has invalid cardinality.\",\n      \"blockTree\": {\n        \"type\": \"validation_rule\",\n        \"fields\": {\n          \"NAME\": \"Sprite_commands\",\n          \"TARGET\": \"Sprite\",\n          \"VALIDATION_TYPE\": \"CARDINALITY\",\n          \"MESSAGE\": \"Sprite.commands has invalid cardinality.\"\n        },\n        \"inputs\": {\n          \"CONDITION\": {\n            \"type\": \"validation_statement_cardinality\",\n            \"fields\": {\n              \"TYPE\": \"Sprite\",\n              \"INPUT\": \"commands\",\n              \"MIN\": \"1\",\n              \"MAX\": \"4\"\n            }\n          }\n        }\n      },\n      \"expandedExpression\": {\n        \"type\": \"logic_operation\",\n        \"fields\": { \"OP\": \"AND\" },\n        \"inputs\": {\n          \"A\": {\n            \"type\": \"logic_compare\",\n            \"fields\": { \"OP\": \"GTE\" },\n            \"inputs\": {\n              \"A\": {\n                \"type\": \"validation_input_count\",\n                \"fields\": { \"INPUT\": \"commands\" }\n              },\n              \"B\": {\n                \"type\": \"math_number\",\n                \"fields\": { \"NUM\": \"1\" }\n              }\n            }\n          },\n          \"B\": {\n            \"type\": \"logic_compare\",\n            \"fields\": { \"OP\": \"LTE\" },\n            \"inputs\": {\n              \"A\": {\n                \"type\": \"validation_input_count\",\n                \"fields\": { \"INPUT\": \"commands\" }\n              },\n              \"B\": {\n                \"type\": \"math_number\",\n                \"fields\": { \"NUM\": \"4\" }\n              }\n            }\n          }\n        }\n      },\n      \"generator\": {\n        \"format\": \"model2blockly.validationGenerator.v1\",\n        \"source\": \"visualBlock\",\n        \"blockType\": \"validation_statement_cardinality\",\n        \"runtimeCheck\": \"statementCardinality\",\n        \"runtimeExpression\": \"inputCount(\\\"commands\\\") \u003e= 1 \u0026\u0026 inputCount(\\\"commands\\\") \u003c= 4\",\n        \"parameters\": {\n          \"TYPE\": \"Sprite\",\n          \"INPUT\": \"commands\",\n          \"MIN\": \"1\",\n          \"MAX\": \"4\"\n        }\n      }\n    }\n  ]\n}\n");

  function generateValidationRuntimeRules(model = VALIDATION_BLOCK_MODEL) {
    const rules = (model && Array.isArray(model.rules)) ? model.rules : [];
    return rules.map((rule) => {
      const runtimeExpression = ruleRuntimeExpression(rule);
      return {
        name: rule && rule.name ? rule.name : 'validation',
        validationType: rule && rule.validationType ? rule.validationType : '',
        targetType: rule && rule.targetType ? rule.targetType : '',
        message: rule && rule.message ? rule.message : '',
        runtimeCheck: runtimeCheckForRule(rule),
        runtimeExpression,
        runtimeFunctionBody: runtimeExpressionToFunctionBody(runtimeExpression)
      };
    });
  }

  function compileValidationBlockModel(model = VALIDATION_BLOCK_MODEL) {
    return generateValidationRuntimeRules(model);
  }

  function blockTreeToRuntimeExpression(node) {
    if (!node || !node.type) return '';
    const fields = node.fields || {};
    const inputs = node.inputs || {};
    switch (node.type) {
      case 'validation_rule':
        return blockTreeToRuntimeExpression(inputs.CONDITION);
      case 'validation_required_field':
        return 'has(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_statement_cardinality':
        return rangeExpression('inputCount(' + quoteExpressionString(fields.INPUT) + ')', fields.MIN, fields.MAX);
      case 'validation_field_cardinality':
        return rangeExpression('fieldCount(' + quoteExpressionString(fields.FIELD) + ')', fields.MIN, fields.MAX);
      case 'validation_expression_rule':
        return blockTreeToRuntimeExpression(inputs.CONDITION) || String(fields.EXPRESSION || '');
      case 'validation_field_exists':
        return 'has(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_field_value':
        return 'value(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_field_number':
        return 'number(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_field_count':
        return 'fieldCount(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_field_unique':
        return 'fieldUnique(' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_type_field_unique':
        return 'typeFieldUnique(' + quoteExpressionString(fields.TYPE) + ', ' + quoteExpressionString(fields.FIELD) + ')';
      case 'validation_input_count':
        return 'inputCount(' + quoteExpressionString(fields.INPUT) + ')';
      case 'validation_previous_block_is':
        return 'previousBlockIs(' + quoteExpressionString(fields.TYPE) + ')';
      case 'logic_compare':
        return joinCompareExpression(blockTreeToRuntimeExpression(inputs.A), fields.OP, blockTreeToRuntimeExpression(inputs.B));
      case 'logic_operation':
        return joinLogicExpression(blockTreeToRuntimeExpression(inputs.A), fields.OP, blockTreeToRuntimeExpression(inputs.B));
      case 'logic_negate':
        return '!(' + (blockTreeToRuntimeExpression(inputs.BOOL) || 'false') + ')';
      case 'logic_boolean':
        return fields.BOOL === 'FALSE' ? 'false' : 'true';
      case 'math_number':
        return normalizedNumber(fields.NUM);
      case 'text':
        return quoteExpressionString(fields.TEXT);
      default:
        return String(fields.VALUE || fields.EXPRESSION || '');
    }
  }

  function runtimeExpressionToFunctionBody(expression) {
    const text = String(expression || '').trim();
    return text ? 'return Boolean(' + text + ');' : 'return true;';
  }

  function createValidationAccessors(block, workspace) {
    function value(name) {
      const raw = block && block.getFieldValue ? block.getFieldValue(name) : '';
      if (raw === null || raw === undefined) return '';
      return String(raw).trim();
    }
    function number(name) {
      const parsed = Number(value(name));
      return Number.isFinite(parsed) ? parsed : 0;
    }
    function fieldCount(name) {
      return fieldValuesFromRaw(value(name)).length;
    }
    function inputCount(name) {
      let count = 0;
      let child = block && block.getInputTargetBlock ? block.getInputTargetBlock(name) : null;
      while (child) {
        count += 1;
        child = child.getNextBlock ? child.getNextBlock() : null;
      }
      return count;
    }
    function has(name) {
      return fieldCount(name) > 0 && value(name) !== '';
    }
    function includes(name, item) {
      return fieldValuesFromRaw(value(name)).includes(String(item));
    }
    function fieldUnique(name) {
      return duplicateValues(fieldValuesFromRaw(value(name))).length === 0;
    }
    function typeFieldUnique(type, name) {
      if (!workspace || !workspace.getAllBlocks || !block) return true;
      const current = value(name);
      if (!current) return true;
      let count = 0;
      workspace.getAllBlocks(false).forEach((candidate) => {
        if (!candidate || candidate.type !== type || !candidate.getFieldValue) return;
        const raw = candidate.getFieldValue(name);
        const candidateValue = raw === null || raw === undefined ? '' : String(raw).trim();
        if (candidateValue === current) count += 1;
      });
      return count <= 1;
    }
    function previousBlockIs(type) {
      const previous = block && block.getPreviousBlock ? block.getPreviousBlock() : null;
      return Boolean(previous && previous.type === type);
    }
    return {
      value,
      number,
      has,
      includes,
      fieldCount,
      inputCount,
      fieldUnique,
      typeFieldUnique,
      previousBlockIs,
      size: fieldCount
    };
  }

  function evaluateRuntimeExpression(block, workspace, expression) {
    const text = String(expression || '').trim();
    if (!text) return { ok: true };
    const accessors = createValidationAccessors(block, workspace);
    try {
      const fn = new Function(
        'value',
        'number',
        'has',
        'includes',
        'fieldCount',
        'inputCount',
        'fieldUnique',
        'typeFieldUnique',
        'previousBlockIs',
        'size',
        'block',
        'workspace',
        'return Boolean(' + text + ');'
      );
      return {
        ok: Boolean(fn(
          accessors.value,
          accessors.number,
          accessors.has,
          accessors.includes,
          accessors.fieldCount,
          accessors.inputCount,
          accessors.fieldUnique,
          accessors.typeFieldUnique,
          accessors.previousBlockIs,
          accessors.size,
          block,
          workspace
        ))
      };
    } catch (error) {
      return { ok: false, error: error && error.message ? error.message : String(error) };
    }
  }

  function ruleRuntimeExpression(rule) {
    if (!rule) return '';
    const fromTree = blockTreeToRuntimeExpression(rule.blockTree);
    if (fromTree) return fromTree;
    if (rule.generator && rule.generator.runtimeExpression) return rule.generator.runtimeExpression;
    return rule.runtimeExpression || '';
  }

  function runtimeCheckForRule(rule) {
    if (rule && rule.generator && rule.generator.runtimeCheck) return rule.generator.runtimeCheck;
    const root = rule && rule.blockTree ? rule.blockTree : null;
    const condition = root && root.type === 'validation_rule' && root.inputs ? root.inputs.CONDITION : root;
    return runtimeCheckForBlockType(condition && condition.type);
  }

  function runtimeCheckForBlockType(type) {
    if (type === 'validation_required_field') return 'requiredField';
    if (type === 'validation_statement_cardinality') return 'statementCardinality';
    if (type === 'validation_field_cardinality') return 'fieldCardinality';
    if (type === 'validation_field_unique') return 'fieldUnique';
    if (type === 'validation_type_field_unique') return 'typeFieldUnique';
    if (type === 'validation_previous_block_is') return 'previousBlockIs';
    if (type === 'validation_expression_rule') return 'semanticExpression';
    if (type) return 'expressionNode';
    return 'custom';
  }

  function joinCompareExpression(left, operator, right) {
    return (left || 'undefined') + ' ' + compareOperator(operator) + ' ' + (right || 'undefined');
  }

  function joinLogicExpression(left, operator, right) {
    const op = operator === 'OR' ? '||' : '&&';
    return '(' + (left || 'true') + ') ' + op + ' (' + (right || 'true') + ')';
  }

  function compareOperator(operator) {
    if (operator === 'NEQ') return '!==';
    if (operator === 'GTE') return '>=';
    if (operator === 'LTE') return '<=';
    if (operator === 'GT') return '>';
    if (operator === 'LT') return '<';
    return '===';
  }

  function rangeExpression(accessor, min, max) {
    const lower = positiveBound(min) ? accessor + ' >= ' + Number(min) : '';
    const upper = finiteUpperBound(max) ? accessor + ' <= ' + Number(max) : '';
    if (lower && upper) return lower + ' && ' + upper;
    if (lower) return lower;
    if (upper) return upper;
    return 'true';
  }

  function positiveBound(value) {
    const parsed = Number(value || 0);
    return Number.isFinite(parsed) && parsed > 0;
  }

  function finiteUpperBound(value) {
    if (value === null || value === undefined || String(value).trim() === '') return false;
    if (String(value).toLowerCase() === 'unbounded') return false;
    const parsed = Number(value);
    return Number.isFinite(parsed) && parsed > 0;
  }

  function normalizedNumber(value) {
    const parsed = Number(value);
    return Number.isFinite(parsed) ? String(parsed) : '0';
  }

  function quoteExpressionString(value) {
    return JSON.stringify(value === null || value === undefined ? '' : String(value));
  }

  function fieldValuesFromRaw(raw) {
    if (raw === null || raw === undefined || raw === '') return [];
    return String(raw)
      .split(/[,\n]/)
      .map((part) => part.trim())
      .filter((part) => part.length > 0);
  }

  function duplicateValues(values) {
    const seen = new Set();
    const duplicates = new Set();
    values.forEach((value) => {
      if (seen.has(value)) duplicates.add(value);
      seen.add(value);
    });
    return Array.from(duplicates);
  }


  const api = {
    VALIDATION_BLOCK_MODEL,
    blockTreeToRuntimeExpression,
    compileValidationBlockModel,
    createValidationAccessors,
    evaluateRuntimeExpression,
    generateValidationRuntimeRules,
    runtimeExpressionToFunctionBody
  };

  root.BLOCKLY_VALIDATION_BLOCK_MODEL = VALIDATION_BLOCK_MODEL;
  root.Model2BlocklyValidationRuntime = api;
  if (typeof module !== "undefined" && module.exports) module.exports = api;
})(typeof window !== "undefined" ? window : globalThis);
