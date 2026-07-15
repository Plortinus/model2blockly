import { buildCanonicalDescriptor } from './canonicalize.mjs';

const CONTROLS = Object.freeze({
  blocklyVersion: '13.1.1',
  renderer: 'geras',
  theme: 'classic',
  locale: 'en',
  browserViewport: { width: 1280, height: 800 },
  workspaceViewport: { width: 1024, height: 640 },
});

const evaluation = {
  status: 'loading',
  descriptor: null,
  error: null,
};
window.__M2B_EVALUATION__ = evaluation;

start().catch((error) => {
  evaluation.status = 'error';
  evaluation.error = error instanceof Error ? error.stack || error.message : String(error);
  setStatus('error');
  document.getElementById('descriptor').textContent = evaluation.error;
});

async function start() {
  const Blockly = window.Blockly;
  const javascriptGenerator = window.javascript?.javascriptGenerator;
  if (!Blockly || !javascriptGenerator) {
    throw new Error('The pinned Blockly runtime and JavaScript generator were not loaded.');
  }
  if (Blockly.VERSION !== CONTROLS.blocklyVersion) {
    throw new Error(`Expected Blockly ${CONTROLS.blocklyVersion}, received ${Blockly.VERSION}.`);
  }

  const adapterUrl = resolveAdapterUrl(new URLSearchParams(window.location.search).get('adapter'));
  const adapterModule = await import(adapterUrl.href);
  const adapter = adapterModule.default;
  validateAdapter(adapter);

  await adapter.register({ Blockly, javascriptGenerator });

  const workspace = Blockly.inject('workspace', {
    toolbox: adapter.toolbox ?? null,
    renderer: CONTROLS.renderer,
    theme: Blockly.Themes.Classic,
    media: '/node_modules/blockly/media/',
    trashcan: false,
    sounds: false,
    move: {
      scrollbars: true,
      drag: true,
      wheel: false,
    },
    zoom: {
      controls: false,
      wheel: false,
      startScale: 1,
      maxScale: 1,
      minScale: 1,
      scaleSpeed: 1,
      pinch: false,
    },
  });

  const descriptor = buildCanonicalDescriptor({
    Blockly,
    javascriptGenerator,
    workspace,
    adapter,
    controls: CONTROLS,
  });

  evaluation.status = 'ready';
  evaluation.descriptor = descriptor;
  document.getElementById('case-title').textContent = `${adapter.caseId} · ${adapter.treatment}`;
  document.getElementById('descriptor').textContent = JSON.stringify(descriptor, null, 2);
  setStatus('ready');
}

function resolveAdapterUrl(value) {
  if (!value) throw new Error('Missing required query parameter: adapter.');
  const url = new URL(value, window.location.origin);
  if (url.origin !== window.location.origin) {
    throw new Error('Treatment adapters must be served from the evaluation origin.');
  }
  return url;
}

function validateAdapter(adapter) {
  if (!adapter || typeof adapter !== 'object') throw new Error('The treatment adapter must export an object as default.');
  if (typeof adapter.caseId !== 'string' || !adapter.caseId) throw new Error('The adapter requires caseId.');
  if (!['baseline', 'm2b', 'ecore'].includes(adapter.treatment)) {
    throw new Error(`Unsupported treatment: ${adapter.treatment}`);
  }
  if (!Array.isArray(adapter.blockTypes) || adapter.blockTypes.some((type) => typeof type !== 'string')) {
    throw new Error('The adapter requires an array of blockTypes.');
  }
  if (typeof adapter.register !== 'function') throw new Error('The adapter requires register().');
}

function setStatus(state) {
  const output = document.getElementById('status');
  output.value = state;
  output.textContent = state;
  output.dataset.state = state;
}
