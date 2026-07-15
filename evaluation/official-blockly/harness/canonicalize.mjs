export function buildCanonicalDescriptor({ Blockly, javascriptGenerator, workspace, adapter, controls }) {
  const blocks = [];
  const errors = [];
  let row = 0;

  for (const type of adapter.blockTypes) {
    try {
      if (!Blockly.Blocks[type]) throw new Error(`Block type ${type} is not registered.`);
      const block = workspace.newBlock(type);
      adapter.prepareBlock?.({ Blockly, type, block });
      block.initSvg?.();
      block.render?.();
      block.moveBy(32 + (row % 3) * 280, 32 + Math.floor(row / 3) * 150);
      blocks.push(describeBlock(Blockly, block, adapter.dynamicBehaviour?.[type]));
      row += 1;
    } catch (error) {
      errors.push(`${type}: ${error instanceof Error ? error.message : String(error)}`);
    }
  }

  return {
    schemaVersion: 2,
    caseId: adapter.caseId,
    treatment: adapter.treatment,
    controls: canonicalJson(controls),
    blocks,
    toolbox: canonicalToolbox(Blockly, adapter.toolbox ?? null),
    initialWorkspace: canonicalJson(adapter.initialWorkspace ?? null),
    generators: adapter.blockTypes.map((type) => ({
      type,
      language: 'javascript',
      registered: typeof javascriptGenerator?.forBlock?.[type] === 'function',
      metadata: canonicalJson(adapter.generatorMetadata?.[type] ?? null),
    })),
    errors,
  };
}

function canonicalToolbox(Blockly, value) {
  const canonical = canonicalJson(normalizeToolbox(Blockly, value));
  return replaceMessageReferences(canonical, Blockly.Msg ?? {});
}

function normalizeToolbox(Blockly, value) {
  if (value == null || typeof value !== 'object') return value;
  if (Array.isArray(value)) return value.map((item) => normalizeToolbox(Blockly, item));

  const rawKind = typeof value.kind === 'string' ? value.kind : null;
  const kind = rawKind && rawKind === rawKind.toUpperCase() ? rawKind.toLowerCase() : rawKind;
  if (kind === 'block') {
    const xmlConfig = blockConfigFromXml(Blockly, value.blockxml);
    const xmlFields = fieldsFromBlockXml(value.blockxml);
    const fields = value.fields ?? xmlConfig.fields ?? (Object.keys(xmlFields).length ? xmlFields : undefined);
    return {
      kind: 'block',
      type: value.type,
      enabled: value.enabled ?? xmlConfig.enabled ?? true,
      ...(fields !== undefined ? { fields } : {}),
      ...(value.inputs ?? xmlConfig.inputs ? { inputs: value.inputs ?? xmlConfig.inputs } : {}),
      ...(value.extraState ?? xmlConfig.extraState ? { extraState: value.extraState ?? xmlConfig.extraState } : {}),
    };
  }

  const contents = Array.isArray(value.contents)
    ? value.contents.map((item) => normalizeToolbox(Blockly, item))
    : null;
  const inferredRootKind = contents
    ? (contents.some((item) => item?.kind === 'category') ? 'categoryToolbox' : 'flyoutToolbox')
    : null;
  const result = Object.fromEntries(
    Object.entries(value).filter(([key]) => !['kind', 'contents', 'id', 'xmlns', 'blockxml'].includes(key)),
  );
  if (kind ?? inferredRootKind) result.kind = kind ?? inferredRootKind;
  if (contents) result.contents = contents;
  return result;
}

function blockConfigFromXml(Blockly, blockXml) {
  if (!blockXml || typeof Blockly.Xml?.domToBlock !== 'function') return {};
  const workspace = new Blockly.Workspace();
  try {
    const block = Blockly.Xml.domToBlock(blockXml.cloneNode(true), workspace);
    const state = Blockly.serialization.blocks.save(block, {
      addCoordinates: false,
      addInputBlocks: true,
      addNextBlocks: true,
      doFullSerialization: true,
      saveIds: false,
    });
    const projected = projectExplicitBlockState(blockXml, state ?? {});
    delete projected.type;
    return projected;
  } catch {
    return {};
  } finally {
    workspace.dispose();
  }
}

function projectExplicitBlockState(blockXml, state) {
  const result = { type: blockXml.getAttribute('type') || state.type };
  const explicitFields = [...blockXml.children]
    .filter((child) => child.tagName?.toLowerCase() === 'field' && child.getAttribute('name'));
  if (explicitFields.length) {
    result.fields = Object.fromEntries(explicitFields.map((field) => {
      const name = field.getAttribute('name');
      return [name, state.fields?.[name] ?? field.textContent ?? ''];
    }));
  }

  const explicitInputs = [...blockXml.children]
    .filter((child) => ['value', 'statement'].includes(child.tagName?.toLowerCase()) && child.getAttribute('name'));
  if (explicitInputs.length) {
    result.inputs = {};
    for (const input of explicitInputs) {
      const name = input.getAttribute('name');
      const nested = [...input.children]
        .find((child) => ['block', 'shadow'].includes(child.tagName?.toLowerCase()));
      if (!nested) continue;
      const kind = nested.tagName.toLowerCase();
      const nestedState = state.inputs?.[name]?.[kind] ?? {};
      result.inputs[name] = { [kind]: projectExplicitBlockState(nested, nestedState) };
    }
    if (!Object.keys(result.inputs).length) delete result.inputs;
  }

  if ([...blockXml.children].some((child) => child.tagName?.toLowerCase() === 'mutation') && state.extraState !== undefined) {
    result.extraState = state.extraState;
  }
  for (const [xmlName, stateName] of [
    ['disabled', 'enabled'],
    ['movable', 'movable'],
    ['deletable', 'deletable'],
    ['collapsed', 'collapsed'],
    ['inline', 'inline'],
  ]) {
    if (!blockXml.hasAttribute(xmlName)) continue;
    const declared = blockXml.getAttribute(xmlName) !== 'false';
    result[stateName] = xmlName === 'disabled' ? !declared : declared;
  }
  return result;
}

function fieldsFromBlockXml(blockXml) {
  if (!blockXml?.children) return {};
  return Object.fromEntries(
    [...blockXml.children]
      .filter((child) => child.tagName?.toLowerCase() === 'field' && child.getAttribute('name'))
      .map((child) => [child.getAttribute('name'), child.textContent ?? '']),
  );
}

function replaceMessageReferences(value, messages) {
  if (Array.isArray(value)) return value.map((item) => replaceMessageReferences(item, messages));
  if (value && typeof value === 'object') {
    return Object.fromEntries(
      Object.entries(value).map(([key, item]) => [key, replaceMessageReferences(item, messages)]),
    );
  }
  if (typeof value !== 'string') return value;
  return value.replace(/%\{BKY_([A-Z0-9_]+)\}/g, (match, key) => messages[key] ?? match);
}

function describeBlock(Blockly, block, declaredDynamicBehaviour = []) {
  const inputs = block.inputList.map((input) => describeInput(Blockly, input));
  const elements = inputs.flatMap((input) => [
    {
      kind: 'input',
      name: input.name,
      inputKind: input.kind,
    },
    ...input.fields.map((field) => ({
      kind: 'field',
      name: field.name,
      fieldKind: field.kind,
      inputName: input.name,
    })),
  ]);

  const inferredDynamicBehaviour = [
    block.mutator ? 'mutator' : null,
    typeof block.onchange === 'function' ? 'onchange' : null,
    typeof block.saveExtraState === 'function' ? 'save-extra-state' : null,
    typeof block.loadExtraState === 'function' ? 'load-extra-state' : null,
    typeof block.mutationToDom === 'function' ? 'mutation-to-dom' : null,
    typeof block.domToMutation === 'function' ? 'dom-to-mutation' : null,
  ].filter(Boolean);

  return {
    type: block.type,
    elements,
    inputs,
    connections: {
      previous: describeConnection(block.previousConnection),
      next: describeConnection(block.nextConnection),
      output: describeConnection(block.outputConnection),
    },
    inputsInline: Boolean(block.getInputsInline?.()),
    colour: block.getColour?.() ?? null,
    tooltip: resolveTextProperty(block, 'tooltip'),
    helpUrl: resolveHelpUrl(block),
    dynamicBehaviour: [...new Set([...inferredDynamicBehaviour, ...declaredDynamicBehaviour])].sort(),
  };
}

function describeInput(Blockly, input) {
  return {
    name: input.name || '',
    kind: inputKind(Blockly, input.type),
    check: normalizeCheck(input.connection?.getCheck?.()),
    alignment: input.align ?? null,
    fields: input.fieldRow.map((field) => describeField(Blockly, field)),
  };
}

function describeField(Blockly, field) {
  let options = null;
  if (typeof field.getOptions === 'function') {
    try {
      options = field.getOptions(false).map(([label, value]) => ({
        label: canonicalJson(label),
        value: canonicalJson(value),
      }));
    } catch {
      options = null;
    }
  }

  return {
    name: field.name ?? null,
    kind: fieldKind(Blockly, field),
    value: canonicalJson(safeCall(field, 'getValue')),
    text: String(safeCall(field, 'getText') ?? ''),
    options,
    constraints: field instanceof Blockly.FieldNumber
      ? {
          min: canonicalJson(safeCall(field, 'getMin')),
          max: canonicalJson(safeCall(field, 'getMax')),
          precision: canonicalJson(safeCall(field, 'getPrecision')),
        }
      : null,
  };
}

function describeConnection(connection) {
  if (!connection) return null;
  return {
    check: normalizeCheck(connection.getCheck?.()),
  };
}

function normalizeCheck(check) {
  if (check == null) return null;
  const values = Array.isArray(check) ? check : [check];
  return [...new Set(values.map(String))].sort();
}

function inputKind(Blockly, value) {
  const inputTypes = Blockly.inputs?.inputTypes ?? {};
  if (value === inputTypes.VALUE || value === Blockly.INPUT_VALUE) return 'value';
  if (value === inputTypes.STATEMENT || value === Blockly.NEXT_STATEMENT) return 'statement';
  if (value === inputTypes.DUMMY || value === Blockly.DUMMY_INPUT) return 'dummy';
  if (value === inputTypes.END_ROW) return 'end-row';
  return `unknown:${String(value)}`;
}

function fieldKind(Blockly, field) {
  const mappings = [
    ['field_angle', Blockly.FieldAngle],
    ['field_variable', Blockly.FieldVariable],
    ['field_number', Blockly.FieldNumber],
    ['field_dropdown', Blockly.FieldDropdown],
    ['field_checkbox', Blockly.FieldCheckbox],
    ['field_image', Blockly.FieldImage],
    ['field_label_serializable', Blockly.FieldLabelSerializable],
    ['field_label', Blockly.FieldLabel],
    ['field_textinput', Blockly.FieldTextInput],
  ];
  for (const [name, constructor] of mappings) {
    if (typeof constructor === 'function' && field.constructor === constructor) return name;
  }
  if (typeof Blockly.FieldTextInput === 'function'
      && field instanceof Blockly.FieldTextInput
      && field.constructor !== Blockly.FieldTextInput) {
    return `custom:${field.constructor?.name || 'FieldTextInput'}`;
  }
  for (const [name, constructor] of mappings) {
    if (typeof constructor === 'function' && field instanceof constructor) return name;
  }
  return field.constructor?.name || 'custom-field';
}

function resolveTextProperty(block, property) {
  const value = block[property];
  try {
    const resolved = typeof value === 'function' ? value.call(block) : value;
    return resolved == null ? null : String(resolved);
  } catch {
    return null;
  }
}

function resolveHelpUrl(block) {
  try {
    const value = typeof block.getHelpUrl === 'function' ? block.getHelpUrl() : block.helpUrl;
    if (value == null || String(value).trim() === '') return null;
    return String(value);
  } catch {
    return null;
  }
}

function safeCall(target, method) {
  try {
    return typeof target[method] === 'function' ? target[method]() : null;
  } catch {
    return null;
  }
}

function canonicalJson(value) {
  if (value === undefined || typeof value === 'function' || typeof value === 'symbol') return null;
  if (typeof value === 'number' && !Number.isFinite(value)) return null;
  if (value === null || typeof value !== 'object') return value;
  if (Array.isArray(value)) return value.map(canonicalJson);
  return Object.fromEntries(
    Object.keys(value)
      .sort()
      .map((key) => [key, canonicalJson(value[key])]),
  );
}
