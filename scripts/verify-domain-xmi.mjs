#!/usr/bin/env node

import { spawnSync } from 'node:child_process';
import { createReadStream, existsSync, mkdtempSync, readdirSync, rmSync, writeFileSync } from 'node:fs';
import { readdir, stat } from 'node:fs/promises';
import { createServer } from 'node:http';
import { createRequire } from 'node:module';
import os from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const require = createRequire(import.meta.url);
const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const eclipsePlugins = process.env.ECLIPSE_PLUGINS || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const javaHome = process.env.JAVA_HOME || findEclipseJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin', 'java');
const javac = path.join(javaHome, 'bin', 'javac');

const options = {
  headed: false,
};

const defaultCases = [
  {
    name: 'app_maker_ecore',
    generated: 'io.github.plortinus.model2blockly/examples/generated/app_maker_ecore',
    ecore: 'io.github.plortinus.model2blockly/model/app_maker.ecore',
  },
];

const positional = [];
for (const arg of process.argv.slice(2)) {
  if (arg === '--headed') options.headed = true;
  else if (arg === '--help' || arg === '-h') {
    printUsage();
    process.exit(0);
  } else positional.push(arg);
}

if (positional.length && positional.length % 2 !== 0) {
  printUsage();
  process.exit(1);
}

let chromium;
try {
  ({ chromium } = require('playwright'));
} catch (error) {
  console.error('Playwright is required for generating the browser-side domain XMI sample.');
  console.error('Install dependencies from the repository root with:');
  console.error('  npm install');
  process.exit(2);
}

checkRuntime();

const cases = positional.length
  ? positional.reduce((items, value, index) => {
    if (index % 2 === 0) {
      const generated = value;
      const ecore = positional[index + 1];
      items.push({ name: path.basename(generated), generated, ecore });
    }
    return items;
  }, [])
  : defaultCases;

const tempDir = mkdtempSync(path.join(os.tmpdir(), 'model2blockly-domain-xmi-'));
const verifierClasspath = [tempDir, path.join(eclipsePlugins, '*')].join(path.delimiter);
writeFileSync(path.join(tempDir, 'VerifyDomainXmi.java'), javaVerifierSource(), 'utf8');
compileVerifier(tempDir);

const targets = [];
for (const testCase of cases) {
  targets.push({
    ...testCase,
    generated: path.resolve(repoRoot, testCase.generated),
    ecore: path.resolve(repoRoot, testCase.ecore),
    target: await resolveTarget(testCase.generated),
  });
}

const server = await startServer(repoRoot);
const baseUrl = `http://127.0.0.1:${server.address().port}`;
const browser = await chromium.launch({ headless: !options.headed });
const results = [];

try {
  for (const target of targets) {
    const xmiFile = path.join(tempDir, `${target.name}.xmi`);
    const extracted = await extractDomainXmi(browser, baseUrl, target.target);
    writeFileSync(xmiFile, extracted.xmi, 'utf8');
    const verification = verifyWithEmf(target.ecore, xmiFile);
    results.push({
      name: target.name,
      ok: verification.ok,
      roots: extracted.roots,
      xmiBytes: Buffer.byteLength(extracted.xmi, 'utf8'),
      verifierOutput: verification.output,
    });
  }
} finally {
  await browser.close();
  await new Promise((resolve) => server.close(resolve));
  rmSync(tempDir, { recursive: true, force: true });
}

printResults(results);
process.exit(results.every((result) => result.ok) ? 0 : 1);

function printUsage() {
  console.log(`Usage: node scripts/verify-domain-xmi.mjs [options] [generated-dir ecore-file ...]

Options:
  --headed   Show Chromium while the sample model is exported.
  -h, --help Show this help.

Default case:
  ${defaultCases[0].generated}
  ${defaultCases[0].ecore}`);
}

async function resolveTarget(input) {
  const absolute = path.resolve(repoRoot, input);
  const info = await stat(absolute);
  if (info.isFile()) {
    if (!absolute.endsWith('_standalone.html')) {
      throw new Error(`Expected a *_standalone.html file: ${input}`);
    }
    return {
      name: path.basename(path.dirname(absolute)),
      html: absolute,
    };
  }
  if (!info.isDirectory()) throw new Error(`Target is not a file or directory: ${input}`);
  const html = await findStandaloneHtml(absolute);
  if (!html) throw new Error(`No *_standalone.html found in ${input} or ${input}/html`);
  return {
    name: path.basename(absolute),
    html,
  };
}

async function findStandaloneHtml(dir) {
  const files = await readdir(dir);
  const html = files.find((file) => file.endsWith('_standalone.html'));
  if (html) return path.join(dir, html);

  const htmlDir = path.join(dir, 'html');
  try {
    const htmlDirInfo = await stat(htmlDir);
    if (!htmlDirInfo.isDirectory()) return null;
    const htmlFiles = await readdir(htmlDir);
    const nestedHtml = htmlFiles.find((file) => file.endsWith('_standalone.html'));
    return nestedHtml ? path.join(htmlDir, nestedHtml) : null;
  } catch (error) {
    return null;
  }
}

async function startServer(root) {
  const server = createServer(async (request, response) => {
    try {
      const url = new URL(request.url || '/', 'http://127.0.0.1');
      const rawPath = decodeURIComponent(url.pathname.replace(/^\/+/, ''));
      const filePath = path.resolve(root, rawPath || 'index.html');
      if (!filePath.startsWith(root + path.sep) && filePath !== root) {
        response.writeHead(403);
        response.end('Forbidden');
        return;
      }
      const info = await stat(filePath);
      if (!info.isFile()) {
        response.writeHead(404);
        response.end('Not found');
        return;
      }
      response.writeHead(200, { 'Content-Type': contentType(filePath) });
      createReadStream(filePath).pipe(response);
    } catch (error) {
      response.writeHead(404);
      response.end('Not found');
    }
  });
  await new Promise((resolve) => server.listen(0, '127.0.0.1', resolve));
  return server;
}

async function extractDomainXmi(browser, baseUrl, target) {
  const page = await browser.newPage({ viewport: { width: 1280, height: 800 } });
  try {
    const relative = path.relative(repoRoot, target.html).split(path.sep).map(encodeURIComponent).join('/');
    await page.goto(`${baseUrl}/${relative}`, { waitUntil: 'load', timeout: 30000 });
    await page.waitForFunction(() => Boolean(window.Blockly)
      && typeof window.importModelJSON === 'function'
      && typeof window.modelToDomainXMI === 'function', null, { timeout: 30000 });
    await page.getByText('Load Sample', { exact: true }).click();
    await page.waitForFunction(() => {
      const text = document.getElementById('jsonView')?.textContent || '';
      return text.includes('"_type"');
    }, null, { timeout: 10000 });
    const result = await page.evaluate(() => {
      const jsonText = document.getElementById('jsonView')?.textContent || '';
      const model = JSON.parse(jsonText);
      const xmi = window.modelToDomainXMI(model);
      const doc = new DOMParser().parseFromString(xmi, 'application/xml');
      return {
        xmi,
        roots: Array.isArray(model) ? model.map((node) => node && node._type).filter(Boolean) : [],
        parseErrors: doc.getElementsByTagName('parsererror').length,
        hasXmiNamespace: xmi.includes('xmlns:xmi="http://www.omg.org/XMI"'),
        hasDomainNamespace: /xmlns:[A-Za-z_][\\w.-]*=/.test(xmi),
      };
    });
    if (result.parseErrors !== 0 || !result.hasXmiNamespace || !result.hasDomainNamespace) {
      throw new Error(`Generated domain XMI is not syntactically usable: ${JSON.stringify(result)}`);
    }
    return result;
  } finally {
    await page.close();
  }
}

function compileVerifier(tempDirPath) {
  const sourceFile = path.join(tempDirPath, 'VerifyDomainXmi.java');
  const result = spawnSync(javac, [
    '-cp',
    path.join(eclipsePlugins, '*'),
    '-d',
    tempDirPath,
    sourceFile,
  ], {
    cwd: repoRoot,
    encoding: 'utf8',
  });
  if (result.status !== 0) {
    fail(`Could not compile EMF domain XMI verifier.\n${result.stdout || ''}${result.stderr || ''}`);
  }
}

function verifyWithEmf(ecoreFile, xmiFile) {
  const result = spawnSync(java, [
    '-cp',
    verifierClasspath,
    'VerifyDomainXmi',
    ecoreFile,
    xmiFile,
  ], {
    cwd: repoRoot,
    encoding: 'utf8',
  });
  return {
    ok: result.status === 0,
    output: `${result.stdout || ''}${result.stderr || ''}`.trim(),
  };
}

function printResults(results) {
  for (const result of results) {
    if (result.ok) {
      console.log(`[PASS] ${result.name}: roots=${result.roots.join(', ') || '-'}; domainXmi=${result.xmiBytes} bytes; ${result.verifierOutput}`);
    } else {
      console.error(`[FAIL] ${result.name}: ${result.verifierOutput}`);
    }
  }
  const passed = results.filter((result) => result.ok).length;
  console.log(`\n${passed}/${results.length} generated domain XMI checks passed.`);
}

function checkRuntime() {
  if (!existsSync(eclipsePlugins)) {
    fail(`Eclipse plugin directory not found: ${eclipsePlugins}`);
  }
  if (!existsSync(java) || !existsSync(javac)) {
    fail(`Java runtime is incomplete: ${javaHome}`);
  }
}

function findEclipseJavaHome(pluginDir) {
  if (!existsSync(pluginDir)) {
    fail(`Eclipse plugin directory not found: ${pluginDir}`);
  }
  const justj = readdirSync(pluginDir)
    .filter((name) => name.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .at(-1);
  if (!justj) {
    fail(`Could not find Eclipse-bundled Java runtime under ${pluginDir}`);
  }
  const home = path.join(pluginDir, justj, 'jre');
  if (!existsSync(path.join(home, 'bin', 'java')) || !existsSync(path.join(home, 'bin', 'javac'))) {
    fail(`Eclipse-bundled Java runtime is incomplete: ${home}`);
  }
  return home;
}

function contentType(filePath) {
  if (filePath.endsWith('.html')) return 'text/html; charset=utf-8';
  if (filePath.endsWith('.js')) return 'application/javascript; charset=utf-8';
  if (filePath.endsWith('.json')) return 'application/json; charset=utf-8';
  if (filePath.endsWith('.css')) return 'text/css; charset=utf-8';
  return 'application/octet-stream';
}

function fail(message) {
  console.error(`[FAIL] ${message}`);
  process.exit(1);
}

function javaVerifierSource() {
  return String.raw`import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public final class VerifyDomainXmi {
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      throw new IllegalArgumentException("Usage: VerifyDomainXmi <domain.ecore> <domain.xmi>");
    }

    File ecoreFile = new File(args[0]).getAbsoluteFile();
    File xmiFile = new File(args[1]).getAbsoluteFile();
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

    EPackage domainPackage = loadDomainPackage(resourceSet, ecoreFile);
    resourceSet.getPackageRegistry().put(domainPackage.getNsURI(), domainPackage);

    Resource domainResource = resourceSet.getResource(URI.createFileURI(xmiFile.getPath()), true);
    failOnResourceProblems("domain XMI", domainResource);
    if (domainResource.getContents().isEmpty()) {
      fail("Domain XMI has no EMF root objects.");
    }

    EcoreUtil.resolveAll(resourceSet);

    List<String> failures = new ArrayList<>();
    List<String> proxies = new ArrayList<>();
    Map<Object, Object> validationContext = new LinkedHashMap<>();
    int objectCount = 0;

    for (EObject root : domainResource.getContents()) {
      if (!isFromPackage(root, domainPackage)) {
        failures.add("Root object " + root.eClass().getName()
          + " is from " + root.eClass().getEPackage().getNsURI()
          + ", expected " + domainPackage.getNsURI());
      }
      objectCount += countObjects(root);
      collectUnresolvedProxies(root, proxies);
      collectValidationFailures(Diagnostician.INSTANCE.validate(root, validationContext), failures);
    }

    failures.addAll(proxies);
    if (!failures.isEmpty()) {
      fail(String.join(System.lineSeparator(), failures));
    }

    System.out.print("EMF load/validate OK: nsURI=" + domainPackage.getNsURI()
      + ", roots=" + domainResource.getContents().size()
      + ", objects=" + objectCount);
  }

  private static EPackage loadDomainPackage(ResourceSet resourceSet, File ecoreFile) throws Exception {
    Resource ecoreResource = resourceSet.getResource(URI.createFileURI(ecoreFile.getPath()), true);
    failOnResourceProblems("Ecore metamodel", ecoreResource);
    for (EObject root : ecoreResource.getContents()) {
      if (root instanceof EPackage) {
        return (EPackage) root;
      }
    }
    fail("No EPackage root found in " + ecoreFile);
    return null;
  }

  private static void failOnResourceProblems(String label, Resource resource) {
    List<String> problems = new ArrayList<>();
    resource.getErrors().forEach(error -> problems.add(error.toString()));
    if (!problems.isEmpty()) {
      fail(label + " load errors:" + System.lineSeparator() + String.join(System.lineSeparator(), problems));
    }
  }

  private static boolean isFromPackage(EObject object, EPackage expectedPackage) {
    return object.eClass() != null
      && object.eClass().getEPackage() != null
      && expectedPackage.getNsURI().equals(object.eClass().getEPackage().getNsURI());
  }

  private static int countObjects(EObject root) {
    int count = 1;
    for (TreeIterator<EObject> it = root.eAllContents(); it.hasNext();) {
      it.next();
      count++;
    }
    return count;
  }

  private static void collectUnresolvedProxies(EObject root, List<String> failures) {
    collectUnresolvedProxiesForObject(root, failures);
    for (TreeIterator<EObject> it = root.eAllContents(); it.hasNext();) {
      collectUnresolvedProxiesForObject(it.next(), failures);
    }
  }

  private static void collectUnresolvedProxiesForObject(EObject object, List<String> failures) {
    for (EReference reference : object.eClass().getEAllReferences()) {
      if (reference.isContainment()) continue;
      Object value = object.eGet(reference, false);
      if (value instanceof EObject) {
        EObject referenced = (EObject) value;
        if (referenced.eIsProxy()) {
          failures.add("Unresolved proxy in " + object.eClass().getName() + "." + reference.getName());
        }
      } else if (value instanceof Iterable<?>) {
        for (Object item : (Iterable<?>) value) {
          if (item instanceof EObject && ((EObject) item).eIsProxy()) {
            failures.add("Unresolved proxy in " + object.eClass().getName() + "." + reference.getName());
          }
        }
      }
    }
  }

  private static void collectValidationFailures(Diagnostic diagnostic, List<String> failures) {
    if (diagnostic.getSeverity() == Diagnostic.ERROR || diagnostic.getSeverity() == Diagnostic.CANCEL) {
      failures.add(diagnostic.getMessage());
    }
    for (Diagnostic child : diagnostic.getChildren()) {
      collectValidationFailures(child, failures);
    }
  }

  private static void fail(String message) {
    throw new IllegalStateException(message);
  }
}
`;
}
