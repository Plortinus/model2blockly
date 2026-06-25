#!/usr/bin/env node

import { execFileSync } from 'node:child_process';
import {
  existsSync,
  mkdirSync,
  readdirSync,
  readFileSync,
  rmSync,
  writeFileSync,
} from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const projectDir = path.join(repoRoot, 'io.github.plortinus.model2blockly');
const examplesRoot = path.join(repoRoot, 'docs/assets/ecore-feature-gallery');
const pluginVersion = '1.0.9.qualifier';
const coreUpdateSiteJar = path.join(
  repoRoot,
  'io.github.plortinus.model2blockly.updatesite',
  'repository',
  'plugins',
  `io.github.plortinus.model2blockly_${pluginVersion}.jar`,
);
const eclipsePlugins = process.env.ECLIPSE_PLUGINS || '/Applications/Eclipse.app/Contents/Eclipse/plugins';
const javaHome = process.env.JAVA_HOME || findBundledJavaHome(eclipsePlugins);
const java = path.join(javaHome, 'bin/java');
const classpath = `bin:${coreUpdateSiteJar}:${eclipsePlugins}/*`;

const examples = [
  {
    slug: '00-zero-annotation-defaults',
    title: 'zero annotation Ecore defaults',
    zhTitle: '无注解 Ecore 默认生成',
    description: 'A plain Ecore model without eAnnotations still generates blocks, fields, dropdowns, statement inputs and validation from structure.',
    zhDescription: '完全不写 eAnnotations 时，生成器仍会从 EClass、EAttribute、EReference 和 EEnum 推断区块、字段、下拉框、嵌套输入和校验。',
    ecore: plainFeaturePackage('zerodefaults', `
  <eClassifiers xsi:type="ecore:EClass" name="TaskBoard">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Sprint board"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="tasks" lowerBound="1"
        upperBound="-1" eType="#//Task" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Task">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Write docs"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="done"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean"
        defaultValueLiteral="false"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="priority"
        eType="#//Priority" defaultValueLiteral="medium"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EEnum" name="Priority">
    <eLiterals name="low" literal="low"/>
    <eLiterals name="medium" literal="medium"/>
    <eLiterals name="high" literal="high"/>
  </eClassifiers>
`),
  },
  {
    slug: '00-annotation-customization',
    title: 'annotations customize Blockly output',
    zhTitle: '添加注解定制 Blockly',
    description: 'Adding blockly and ui annotations to the same kind of model controls toolbox categories, colours, tooltips, labels and workspace options.',
    zhDescription: '在同类任务模型上添加 blockly 和 ui 注解后，可以指定工具箱分类、区块颜色、tooltip、字段标签和工作区行为。',
    ecore: featurePackage('annotatedcustomization', `
  <eClassifiers xsi:type="ecore:EClass" name="TaskBoard">
    <eAnnotations source="blockly">
      <details key="category" value="Work/Boards"/>
      <details key="colour" value="210"/>
      <details key="tooltip" value="A board that contains tasks."/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Sprint board">
      <eAnnotations source="ui">
        <details key="label" value="Board name"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EReference" name="tasks" lowerBound="1"
        upperBound="-1" eType="#//Task" containment="true">
      <eAnnotations source="ui">
        <details key="label" value="Tasks"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Task">
    <eAnnotations source="blockly">
      <details key="category" value="Work/Tasks"/>
      <details key="colour" value="120"/>
      <details key="inputsInline" value="true"/>
      <details key="tooltip" value="Task details."/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Write docs">
      <eAnnotations source="ui">
        <details key="label" value="Title"/>
        <details key="placeholder" value="Task title"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="done"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean"
        defaultValueLiteral="false">
      <eAnnotations source="ui">
        <details key="label" value="Done"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="priority"
        eType="#//Priority" defaultValueLiteral="medium">
      <eAnnotations source="ui">
        <details key="label" value="Priority"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EEnum" name="Priority">
    <eLiterals name="low" literal="low"/>
    <eLiterals name="medium" literal="medium"/>
    <eLiterals name="high" literal="high"/>
  </eClassifiers>
`, `
    <details key="workspace.grid.spacing" value="24"/>
    <details key="workspace.grid.snap" value="true"/>
    <details key="workspace.zoom.controls" value="true"/>
`),
  },
  {
    slug: '01-text-field',
    title: 'EAttribute -> text field',
    zhTitle: 'EAttribute -> 文本字段',
    description: 'A required EString attribute becomes a Blockly text field and a required-field validation.',
    zhDescription: '必填的 EString 属性生成 Blockly 文本字段，并生成 required-field 校验。',
    ecore: featurePackage('textfield', `
  <eClassifiers xsi:type="ecore:EClass" name="Note">
    <eAnnotations source="blockly">
      <details key="colour" value="210"/>
      <details key="category" value="Basics"/>
      <details key="tooltip" value="A note with a required title."/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Design note">
      <eAnnotations source="ui">
        <details key="label" value="Title"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '02-enum-dropdown',
    title: 'EEnum -> dropdown',
    zhTitle: 'EEnum -> 下拉框',
    description: 'An EEnum attribute becomes a Blockly dropdown with one option per enum literal.',
    zhDescription: 'EEnum 属性生成 Blockly 下拉框，每个枚举字面量都会成为一个选项。',
    ecore: featurePackage('enumdropdown', `
  <eClassifiers xsi:type="ecore:EClass" name="Task">
    <eAnnotations source="blockly">
      <details key="colour" value="120"/>
      <details key="category" value="Basics"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="priority"
        eType="#//Priority" defaultValueLiteral="medium">
      <eAnnotations source="ui">
        <details key="label" value="Priority"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EEnum" name="Priority">
    <eLiterals name="low" literal="low"/>
    <eLiterals name="medium" literal="medium"/>
    <eLiterals name="high" literal="high"/>
  </eClassifiers>
`),
  },
  {
    slug: '03-typed-fields',
    title: 'EAttribute types -> Blockly fields',
    zhTitle: 'EAttribute 类型 -> Blockly 字段控件',
    description: 'Boolean, integer and colour annotations generate checkbox, number and colour controls.',
    zhDescription: 'EBoolean、EInt 和 field_colour 注解分别生成复选框、数字输入和颜色选择器。',
    ecore: featurePackage('typedfields', `
  <eClassifiers xsi:type="ecore:EClass" name="WidgetStyle">
    <eAnnotations source="blockly">
      <details key="colour" value="20"/>
      <details key="category" value="Fields"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="visible"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean"
        defaultValueLiteral="true">
      <eAnnotations source="ui">
        <details key="label" value="Visible"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="width"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="320">
      <eAnnotations source="blockly">
        <details key="min" value="0"/>
        <details key="max" value="960"/>
      </eAnnotations>
      <eAnnotations source="ui">
        <details key="label" value="Width"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="accentColor"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="#1f6feb">
      <eAnnotations source="blockly">
        <details key="type" value="field_colour"/>
      </eAnnotations>
      <eAnnotations source="ui">
        <details key="label" value="Accent"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '04-containment-statement',
    title: 'containment EReference -> statement input',
    zhTitle: 'containment EReference -> statement 输入',
    description: 'A containment reference becomes a statement area where child blocks can be stacked.',
    zhDescription: 'containment="true" 的引用生成 statement 区域，用户可以把子区块堆叠进去。',
    ecore: featurePackage('statementinput', `
  <eClassifiers xsi:type="ecore:EClass" name="Page">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Layout"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Home"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="components"
        lowerBound="1" upperBound="4" eType="#//Component" containment="true">
      <eAnnotations source="ui">
        <details key="label" value="Components"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Component" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Button" eSuperTypes="#//Component">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="label"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Save"/>
  </eClassifiers>
`),
  },
  {
    slug: '05-value-input-shadow',
    title: 'input_value + shadow block',
    zhTitle: 'input_value + shadow block',
    description: 'A containment reference annotated as input_value becomes an expression socket with a default shadow block.',
    zhDescription: '把 containment 引用标注为 input_value 后，会生成表达式插口；shadow 指定默认影子区块。',
    ecore: featurePackage('valueinput', `
  <eClassifiers xsi:type="ecore:EClass" name="TextLabel">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="content"
        lowerBound="1" eType="#//TextExpression" containment="true">
      <eAnnotations source="blockly">
        <details key="type" value="input_value"/>
        <details key="shadow" value="TextLiteral"/>
      </eAnnotations>
      <eAnnotations source="ui">
        <details key="label" value="Text"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="TextExpression" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="230"/>
      <details key="category" value="Expressions"/>
      <details key="output" value="true"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="TextLiteral" eSuperTypes="#//TextExpression">
    <eAnnotations source="blockly">
      <details key="colour" value="230"/>
      <details key="category" value="Expressions"/>
      <details key="output" value="true"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="content"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Hello"/>
  </eClassifiers>
`),
  },
  {
    slug: '06-reference-dropdown',
    title: 'non-containment EReference -> reference selector',
    zhTitle: '普通 EReference -> 引用选择器',
    description: 'A normal reference does not create a child block; it becomes a selector for existing target blocks.',
    zhDescription: '普通引用不会创建子对象，而是生成从已有目标区块中选择的引用字段。',
    ecore: featurePackage('referenceselect', `
  <eClassifiers xsi:type="ecore:EClass" name="Flow">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Flow"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="pages"
        lowerBound="1" upperBound="3" eType="#//Page" containment="true"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="actions"
        lowerBound="1" upperBound="3" eType="#//Action" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Page">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Flow"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" iD="true"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="home"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Action" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Navigate" eSuperTypes="#//Action">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="target"
        lowerBound="1" eType="#//Page">
      <eAnnotations source="ui">
        <details key="widget" value="reference-select"/>
        <details key="referenceLabelField" value="name"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '07-abstract-inheritance',
    title: 'abstract EClass -> typed connection',
    zhTitle: '抽象 EClass -> 类型连接',
    description: 'An abstract parent stays out of the toolbox but provides the connection type for concrete children.',
    zhDescription: '抽象父类不会出现在工具箱里，但会成为 statement 输入的连接类型，具体子类可以放入同一个插槽。',
    ecore: featurePackage('abstracttype', `
  <eClassifiers xsi:type="ecore:EClass" name="Screen">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Screens"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="components"
        lowerBound="1" upperBound="4" eType="#//Component" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Component" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Text" eSuperTypes="#//Component">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="value"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Name"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Image" eSuperTypes="#//Component">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="Components"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="url"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="https://example.com/avatar.png"/>
  </eClassifiers>
`),
  },
  {
    slug: '08-validation-rule',
    title: 'validation annotation -> generated warning',
    zhTitle: 'validation 注解 -> 生成校验提示',
    description: 'A source="validation" annotation becomes generated Blockly validation logic.',
    zhDescription: 'source="validation" 注解会进入生成的验证规则，并在 Blockly 工作区里产生运行时提示。',
    ecore: featurePackage('validationrule', `
  <eClassifiers xsi:type="ecore:EClass" name="Flow">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Flow"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="actions"
        lowerBound="1" upperBound="4" eType="#//Action" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Action" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Alert" eSuperTypes="#//Action">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="message"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Leaving page"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Navigate" eSuperTypes="#//Action">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
    </eAnnotations>
    <eAnnotations source="validation">
      <details key="mustFollow" value="Alert"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="target"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="details"/>
  </eClassifiers>
`),
  },
  {
    slug: '09-multivalue-field',
    title: 'multi-valued EAttribute -> list field',
    zhTitle: '多值 EAttribute -> 列表字段',
    description: 'An EAttribute with upperBound="-1" becomes a list-style text field and gets cardinality/uniqueness validation.',
    zhDescription: 'upperBound="-1" 的属性会生成列表式文本字段，并进入字段基数和唯一性校验。',
    ecore: featurePackage('multivaluefield', `
  <eClassifiers xsi:type="ecore:EClass" name="TagSet">
    <eAnnotations source="blockly">
      <details key="colour" value="200"/>
      <details key="category" value="Fields"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Filters">
      <eAnnotations source="ui">
        <details key="label" value="Name"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="tags" lowerBound="1" upperBound="-1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="urgent,review">
      <eAnnotations source="ui">
        <details key="label" value="Tags"/>
        <details key="help" value="Comma or newline separated values."/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '10-image-angle-label',
    title: 'field_image / field_angle / field_label',
    zhTitle: 'field_image / field_angle / field_label',
    description: 'Blockly field annotations can turn normal attributes into image, angle and read-only label fields.',
    zhDescription: 'blockly type 注解可以把普通属性改成图片、角度盘或只读标签字段。',
    ecore: featurePackage('visualfields', `
  <eClassifiers xsi:type="ecore:EClass" name="IconBadge">
    <eAnnotations source="blockly">
      <details key="colour" value="45"/>
      <details key="category" value="Fields"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="icon"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Blockly">
      <eAnnotations source="blockly">
        <details key="type" value="field_image"/>
        <details key="src" value="https://developers.google.com/blockly/images/logos/logo_built_on.svg"/>
        <details key="width" value="32"/>
        <details key="height" value="32"/>
        <details key="alt" value="Blockly"/>
      </eAnnotations>
      <eAnnotations source="ui">
        <details key="label" value="Icon"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="rotation"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="45">
      <eAnnotations source="blockly">
        <details key="type" value="field_angle"/>
      </eAnnotations>
      <eAnnotations source="ui">
        <details key="label" value="Angle"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="caption"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Preview only">
      <eAnnotations source="blockly">
        <details key="type" value="field_label"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '11-custom-message',
    title: 'message0 annotation -> custom block text',
    zhTitle: 'message0 注解 -> 自定义区块文本',
    description: 'EClass message0 controls the first row of the generated block and the order of visible fields.',
    zhDescription: 'EClass 上的 message0 控制区块第一行文案，并决定字段在区块上的显性顺序。',
    ecore: featurePackage('custommessage', `
  <eClassifiers xsi:type="ecore:EClass" name="Rule">
    <eAnnotations source="blockly">
      <details key="colour" value="10"/>
      <details key="category" value="Rules"/>
      <details key="message0" value="when %1 then %2"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="condition" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="cart.total &gt; 100">
      <eAnnotations source="ui">
        <details key="label" value="Condition"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="result" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="apply discount">
      <eAnnotations source="ui">
        <details key="label" value="Result"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '12-nested-category',
    title: 'category path -> nested toolbox category',
    zhTitle: 'category 路径 -> 嵌套工具箱分类',
    description: 'A category value with slashes creates nested toolbox categories such as UI/Screens and UI/Actions.',
    zhDescription: 'category 中使用斜杠可以生成嵌套工具箱，例如 UI/Screens、UI/Inputs 和 UI/Actions。',
    ecore: featurePackage('nestedcategory', `
  <eClassifiers xsi:type="ecore:EClass" name="Screen">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="UI/Screens"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="controls"
        lowerBound="1" upperBound="4" eType="#//Component" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Component" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="UI/Components"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="TextInput" eSuperTypes="#//Component">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="UI/Inputs"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="label"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Name"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="SubmitButton" eSuperTypes="#//Component">
    <eAnnotations source="blockly">
      <details key="colour" value="160"/>
      <details key="category" value="UI/Actions"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="label"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Submit"/>
  </eClassifiers>
`),
  },
  {
    slug: '13-code-template',
    title: 'source="code" -> generated code export',
    zhTitle: 'source="code" -> 代码导出模板',
    description: 'A code annotation on an EClass is copied into the generated editor code export logic.',
    zhDescription: 'EClass 上的 code 注解会进入生成编辑器的代码导出逻辑，可在完整编辑器里用 Export Code 检查。',
    ecore: featurePackage('codetemplate', `
  <eClassifiers xsi:type="ecore:EClass" name="ApiCall">
    <eAnnotations source="blockly">
      <details key="colour" value="30"/>
      <details key="category" value="Actions"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eAnnotations source="code">
      <details key="template" value="fetch(&quot;{{url}}&quot;, { method: &quot;{{method}}&quot; });"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="url" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="/api/tasks">
      <eAnnotations source="ui">
        <details key="label" value="URL"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="method"
        eType="#//HttpMethod" defaultValueLiteral="GET">
      <eAnnotations source="ui">
        <details key="label" value="Method"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EEnum" name="HttpMethod">
    <eLiterals name="GET" literal="GET"/>
    <eLiterals name="POST" literal="POST"/>
    <eLiterals name="PUT" literal="PUT"/>
  </eClassifiers>
`, '', `
  <eAnnotations source="code">
    <details key="language" value="JavaScript"/>
    <details key="fileExtension" value="js"/>
  </eAnnotations>
`),
  },
  {
    slug: '14-workspace-options',
    title: 'workspace.* annotations -> Blockly workspace options',
    zhTitle: 'workspace.* 注解 -> 工作区配置',
    description: 'Workspace annotations on EPackage are converted into Blockly.inject options such as grid, zoom and renderer.',
    zhDescription: 'EPackage 上的 workspace.* details 会进入 Blockly.inject 配置，例如 renderer、grid、zoom 和 move。',
    ecore: featurePackage('workspaceoptions', `
  <eClassifiers xsi:type="ecore:EClass" name="Board">
    <eAnnotations source="blockly">
      <details key="colour" value="280"/>
      <details key="category" value="Workspace"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Grid board"/>
  </eClassifiers>
`, `
    <details key="workspace.grid.spacing" value="24"/>
    <details key="workspace.grid.length" value="3"/>
    <details key="workspace.grid.colour" value="#d0d7de"/>
    <details key="workspace.grid.snap" value="true"/>
    <details key="workspace.zoom.startScale" value="0.8"/>
`),
  },
  {
    slug: '15-auto-category',
    title: 'zero annotation inheritance -> auto categories',
    zhTitle: '零注解继承 -> 自动分类',
    description: 'When classes do not declare categories, the adapter groups concrete blocks from the inheritance tree.',
    zhDescription: 'EClass 不写 category 时，生成器会根据继承结构自动创建 toolbox 分类。',
    ecore: featurePackage('autocategory', `
  <eClassifiers xsi:type="ecore:EClass" name="Sprite">
    <eStructuralFeatures xsi:type="ecore:EReference" name="commands"
        lowerBound="1" upperBound="4" eType="#//Command" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Command" abstract="true"/>

  <eClassifiers xsi:type="ecore:EClass" name="MovementCommand" abstract="true" eSuperTypes="#//Command"/>

  <eClassifiers xsi:type="ecore:EClass" name="Move" eSuperTypes="#//MovementCommand">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="steps"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="10"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Turn" eSuperTypes="#//MovementCommand">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="degrees"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="15"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Say" eSuperTypes="#//Command">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="text"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Hello"/>
  </eClassifiers>
`),
  },
  {
    slug: '16-output-blocks',
    title: 'output blocks -> value expressions',
    zhTitle: 'output block -> 表达式插口',
    description: 'Classes annotated with output become value-producing blocks that plug into input_value sockets.',
    zhDescription: '带 output 注解的 EClass 会生成表达式区块，并能接入 input_value 插口。',
    ecore: featurePackage('outputblocks', `
  <eClassifiers xsi:type="ecore:EClass" name="Threshold">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Rules"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="value"
        lowerBound="1" eType="#//NumberExpression" containment="true">
      <eAnnotations source="blockly">
        <details key="type" value="input_value"/>
        <details key="check" value="NumberExpression"/>
        <details key="shadow" value="NumberLiteral"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="NumberExpression" abstract="true">
    <eAnnotations source="blockly">
      <details key="colour" value="230"/>
      <details key="category" value="Expressions"/>
      <details key="output" value="true"/>
    </eAnnotations>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="NumberLiteral" eSuperTypes="#//NumberExpression">
    <eAnnotations source="blockly">
      <details key="colour" value="230"/>
      <details key="category" value="Expressions"/>
      <details key="output" value="true"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="value"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="5"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Add" eSuperTypes="#//NumberExpression">
    <eAnnotations source="blockly">
      <details key="colour" value="230"/>
      <details key="category" value="Expressions"/>
      <details key="output" value="NumberExpression"/>
      <details key="message0" value="%1 + %2"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="left"
        lowerBound="1" eType="#//NumberExpression" containment="true">
      <eAnnotations source="blockly">
        <details key="type" value="input_value"/>
        <details key="check" value="NumberExpression"/>
        <details key="shadow" value="NumberLiteral"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EReference" name="right"
        lowerBound="1" eType="#//NumberExpression" containment="true">
      <eAnnotations source="blockly">
        <details key="type" value="input_value"/>
        <details key="check" value="NumberExpression"/>
        <details key="shadow" value="NumberLiteral"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '17-class-presentation',
    title: 'EClass presentation annotations',
    zhTitle: 'EClass 展示注解',
    description: 'Class-level label, tooltip, helpUrl, colour and inputsInline affect the generated block definition.',
    zhDescription: 'EClass 上的 label、tooltip、helpUrl、colour、inputsInline 会进入生成的 Blockly block 定义。',
    ecore: featurePackage('classpresentation', `
  <eClassifiers xsi:type="ecore:EClass" name="Panel">
    <eAnnotations source="blockly">
      <details key="colour" value="300"/>
      <details key="category" value="Presentation"/>
      <details key="message0" value="panel %1 width %2"/>
      <details key="tooltip" value="A styled UI panel."/>
      <details key="helpUrl" value="https://developers.google.com/blockly"/>
      <details key="inputsInline" value="false"/>
    </eAnnotations>
    <eAnnotations source="ui">
      <details key="label" value="Styled panel"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Settings"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="width"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="480"/>
  </eClassifiers>
`),
  },
  {
    slug: '18-field-overrides',
    title: 'field_input / field_number / field_checkbox overrides',
    zhTitle: '字段类型覆盖注解',
    description: 'The blockly type detail can override the default field inferred from the EAttribute type.',
    zhDescription: 'EAttribute 默认字段类型可以被 blockly type 覆盖，例如文本、数字和复选框。',
    ecore: featurePackage('fieldoverrides', `
  <eClassifiers xsi:type="ecore:EClass" name="ControlSettings">
    <eAnnotations source="blockly">
      <details key="colour" value="190"/>
      <details key="category" value="Fields"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="caption"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Volume">
      <eAnnotations source="blockly">
        <details key="type" value="field_input"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="ratio"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="0.75">
      <eAnnotations source="blockly">
        <details key="type" value="field_number"/>
        <details key="min" value="0"/>
        <details key="max" value="1"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="locked"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="true">
      <eAnnotations source="blockly">
        <details key="type" value="field_checkbox"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '19-ui-metadata',
    title: 'source="ui" metadata',
    zhTitle: 'source="ui" 元数据',
    description: 'UI annotations are preserved in the intermediate model for labels, help text, grouping, order and edit hints.',
    zhDescription: 'source="ui" 会把 label、help、placeholder、group、order、readonly、hidden 等信息写入中间模型和生成器报告。',
    ecore: featurePackage('uimetadata', `
  <eClassifiers xsi:type="ecore:EClass" name="ProfileForm">
    <eAnnotations source="blockly">
      <details key="colour" value="170"/>
      <details key="category" value="UI"/>
    </eAnnotations>
    <eAnnotations source="ui">
      <details key="label" value="Profile form"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="displayName" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Ada">
      <eAnnotations source="ui">
        <details key="widget" value="text"/>
        <details key="label" value="Display name"/>
        <details key="help" value="Shown in the account header."/>
        <details key="placeholder" value="Full name"/>
        <details key="group" value="Identity"/>
        <details key="order" value="1"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="slug"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="ada">
      <eAnnotations source="ui">
        <details key="label" value="Slug"/>
        <details key="readonly" value="true"/>
        <details key="group" value="Identity"/>
        <details key="order" value="2"/>
      </eAnnotations>
    </eStructuralFeatures>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="internalToken"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="sys-001">
      <eAnnotations source="ui">
        <details key="hidden" value="true"/>
        <details key="variant" value="system"/>
        <details key="group" value="System"/>
        <details key="order" value="99"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '20-id-reference-label',
    title: 'iD attribute -> identity and reference labels',
    zhTitle: 'iD 属性 -> 身份与引用标签',
    description: 'An EAttribute marked iD becomes the default identity field and is used for reference dropdown labels.',
    zhDescription: 'iD="true" 的 EAttribute 会成为模型身份字段，并作为引用下拉框的默认标签来源。',
    ecore: featurePackage('idreferencelabel', `
  <eClassifiers xsi:type="ecore:EClass" name="Library">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Library"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="authors"
        lowerBound="1" upperBound="-1" eType="#//Author" containment="true"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="books"
        lowerBound="1" upperBound="-1" eType="#//Book" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Author">
    <eAnnotations source="blockly">
      <details key="colour" value="60"/>
      <details key="category" value="Library"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="authorId" iD="true" lowerBound="1"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="author-1"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="displayName"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Ada Lovelace"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Book">
    <eAnnotations source="blockly">
      <details key="colour" value="120"/>
      <details key="category" value="Library"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Notes"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="author"
        lowerBound="1" eType="#//Author"/>
  </eClassifiers>
`),
  },
  {
    slug: '21-multireference-opposite',
    title: 'multi EReference + eOpposite',
    zhTitle: '多值引用 + eOpposite',
    description: 'A many non-containment reference becomes a reference multiselect; eOpposite metadata drives opposite-reference sync.',
    zhDescription: '多值普通 EReference 会生成引用多选字段；eOpposite 会进入生成的双向引用同步逻辑。',
    ecore: featurePackage('multirefopposite', `
  <eClassifiers xsi:type="ecore:EClass" name="Organization">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Organization"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EReference" name="teams"
        lowerBound="1" upperBound="-1" eType="#//Team" containment="true"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="members"
        lowerBound="1" upperBound="-1" eType="#//Member" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Team">
    <eAnnotations source="blockly">
      <details key="colour" value="200"/>
      <details key="category" value="Organization"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" iD="true"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="platform"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="members"
        upperBound="-1" eType="#//Member" eOpposite="#//Member/team">
      <eAnnotations source="ui">
        <details key="referenceLabelField" value="name"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Member">
    <eAnnotations source="blockly">
      <details key="colour" value="120"/>
      <details key="category" value="Organization"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" iD="true"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="alice"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="team"
        eType="#//Team" eOpposite="#//Team/members">
      <eAnnotations source="ui">
        <details key="referenceLabelField" value="name"/>
      </eAnnotations>
    </eStructuralFeatures>
  </eClassifiers>
`),
  },
  {
    slug: '22-expression-validation',
    title: 'validation expression -> generated rule',
    zhTitle: '表达式校验 -> 生成规则',
    description: 'source="validation" expression/condition/js entries become generated browser-side validation rules.',
    zhDescription: 'source="validation" 的 expression、condition、js 会生成浏览器端校验规则和可视化验证块。',
    ecore: featurePackage('expressionvalidation', `
  <eClassifiers xsi:type="ecore:EClass" name="Range">
    <eAnnotations source="blockly">
      <details key="colour" value="20"/>
      <details key="category" value="Validation"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eAnnotations source="validation">
      <details key="expression" value="number(&quot;min&quot;) &lt;= number(&quot;max&quot;)"/>
      <details key="message" value="Minimum must be less than or equal to maximum."/>
      <details key="condition.hasLabel" value="has(&quot;label&quot;)"/>
      <details key="message.hasLabel" value="Range label is required."/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="label"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Allowed range"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="min"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="0"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="max"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EInt"
        defaultValueLiteral="10"/>
  </eClassifiers>
`),
  },
  {
    slug: '23-ocl-validation',
    title: 'Ecore OCL constraint -> validation rule',
    zhTitle: 'Ecore OCL 约束 -> 校验规则',
    description: 'Supported Ecore/OCL constraints are translated into generated validation expressions.',
    zhDescription: '支持的 Ecore/OCL 约束会被翻译成生成器可执行的 validation expression。',
    ecore: featurePackage('oclvalidation', `
  <eClassifiers xsi:type="ecore:EClass" name="Checklist">
    <eAnnotations source="blockly">
      <details key="colour" value="20"/>
      <details key="category" value="Validation"/>
    </eAnnotations>
    <eAnnotations source="http://www.eclipse.org/emf/2002/Ecore">
      <details key="constraints" value="HasName HasItems"/>
    </eAnnotations>
    <eAnnotations source="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot">
      <details key="HasName" value="self.name-&gt;notEmpty()"/>
      <details key="HasItems" value="self.items-&gt;size() &gt;= 1"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Launch"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="items"
        upperBound="-1" eType="#//ChecklistItem" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="ChecklistItem">
    <eAnnotations source="blockly">
      <details key="colour" value="110"/>
      <details key="category" value="Validation"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="text"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Review"/>
  </eClassifiers>
`),
  },
  {
    slug: '24-ignored-features',
    title: 'derived/transient/volatile features are ignored',
    zhTitle: 'derived/transient/volatile 特征会被忽略',
    description: 'Non-editable Ecore features are skipped and do not become Blockly fields or inputs.',
    zhDescription: 'derived、transient、volatile 或 changeable=false 的特征不会生成 Blockly 字段或输入。',
    ecore: featurePackage('ignoredfeatures', `
  <eClassifiers xsi:type="ecore:EClass" name="DebugRecord">
    <eAnnotations source="blockly">
      <details key="colour" value="210"/>
      <details key="category" value="Ecore"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="visibleName"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Shown"/>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="computedHash" transient="true" volatile="true" derived="true" changeable="false"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="hidden"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="cachedOwner" transient="true" volatile="true" derived="true" changeable="false"
        eType="#//DebugRecord"/>
  </eClassifiers>
`),
  },
  {
    slug: '25-subpackages',
    title: 'EPackage subpackages -> generated blocks',
    zhTitle: 'EPackage 子包 -> 生成区块',
    description: 'Classifiers inside nested EPackages are collected and converted like root package classifiers.',
    zhDescription: '嵌套 EPackage 里的 EClass 也会被收集，并和根包里的分类器一样转换成区块。',
    ecore: featurePackage('subpackages', `
  <eClassifiers xsi:type="ecore:EClass" name="Dashboard">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Root"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Metrics"/>
  </eClassifiers>

  <eSubpackages name="controls"
      nsURI="https://plortinus.github.io/model2blockly/examples/subpackages/controls"
      nsPrefix="controls">
    <eClassifiers xsi:type="ecore:EClass" name="Chart">
      <eAnnotations source="blockly">
        <details key="colour" value="160"/>
        <details key="category" value="Subpackage"/>
        <details key="inputsInline" value="true"/>
      </eAnnotations>
      <eStructuralFeatures xsi:type="ecore:EAttribute" name="series"
          eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
          defaultValueLiteral="revenue"/>
    </eClassifiers>
  </eSubpackages>
`),
  },
  {
    slug: '26-flyout-toolbox',
    title: 'workspace.toolboxType=flyout',
    zhTitle: 'workspace.toolboxType=flyout',
    description: 'The EPackage workspace.toolboxType option switches the generated toolbox from categories to a flyout.',
    zhDescription: 'EPackage 上的 workspace.toolboxType=flyout 会把分类工具箱切换成单列 flyout toolbox。',
    ecore: featurePackage('flyouttoolbox', `
  <eClassifiers xsi:type="ecore:EClass" name="QuickNote">
    <eAnnotations source="blockly">
      <details key="colour" value="210"/>
      <details key="category" value="Notes"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="text"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="One-click block"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="QuickFlag">
    <eAnnotations source="blockly">
      <details key="colour" value="120"/>
      <details key="category" value="Notes"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="enabled"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean"
        defaultValueLiteral="true"/>
  </eClassifiers>
`, `
    <details key="workspace.toolboxType" value="flyout"/>
`),
  },
  {
    slug: '27-runtime-kind',
    title: 'source="runtime" kind -> preview runtime',
    zhTitle: 'source="runtime" kind -> 预览运行时',
    description: 'A package-level runtime kind enables domain-specific runtime tabs in the generated standalone editor.',
    zhDescription: 'EPackage 上的 runtime kind 会启用生成编辑器里的领域运行时预览，例如 AppMaker Preview tab。',
    ecore: featurePackage('runtimekind', `
  <eClassifiers xsi:type="ecore:EClass" name="App">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Pages"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Runtime demo"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="pages"
        upperBound="-1" eType="#//Page" containment="true"/>
  </eClassifiers>

  <eClassifiers xsi:type="ecore:EClass" name="Page">
    <eAnnotations source="blockly">
      <details key="colour" value="260"/>
      <details key="category" value="Pages"/>
      <details key="inputsInline" value="true"/>
    </eAnnotations>
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
        eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"
        defaultValueLiteral="Home"/>
  </eClassifiers>
`, '', `
  <eAnnotations source="runtime">
    <details key="kind" value="appMaker"/>
  </eAnnotations>
`),
  },
];

const coverageBySlug = {
  '00-zero-annotation-defaults': {
    group: '从默认到定制',
    covers: [
      'no eAnnotations -> inferred Blockly editor',
      'EClass -> block type',
      'EAttribute/EEnum -> fields and dropdowns',
      'containment EReference -> statement input',
      'lowerBound >= 1 -> required validation',
    ],
  },
  '00-annotation-customization': {
    group: '从默认到定制',
    covers: [
      'blockly category/colour/tooltip -> block presentation',
      'ui label/placeholder -> readable fields',
      'workspace.* -> Blockly workspace options',
      'same Ecore structure with explicit generated UI',
    ],
  },
  '01-text-field': {
    group: '零注解与字段',
    covers: ['EClass -> block type', 'EAttribute:EString -> field_input', 'lowerBound >= 1 -> required validation', 'defaultValueLiteral -> field default'],
  },
  '02-enum-dropdown': {
    group: '零注解与字段',
    covers: ['EEnum -> field_dropdown', 'EEnumLiteral literal/name -> dropdown value/label', 'inputsInline=true'],
  },
  '03-typed-fields': {
    group: '字段类型',
    covers: ['EBoolean -> field_checkbox', 'EInt -> field_number', 'blockly min/max -> numeric bounds', 'field_colour override'],
  },
  '04-containment-statement': {
    group: '引用与连接',
    covers: ['containment EReference -> input_statement', 'lower/upperBound on containment -> cardinality validation', 'root container -> no previous/next connection'],
  },
  '05-value-input-shadow': {
    group: '引用与连接',
    covers: ['EReference type=input_value -> value socket', 'shadow -> toolbox shadow input', 'abstract output type -> expression connection'],
  },
  '06-reference-dropdown': {
    group: '引用与连接',
    covers: ['non-containment EReference -> reference dropdown', 'referenceLabelField -> dropdown labels', 'required reference -> required validation'],
  },
  '07-abstract-inheritance': {
    group: '继承与连接',
    covers: ['abstract EClass -> hidden from toolbox', 'eSuperTypes -> typed previous/next connection', 'concrete subclasses share parent input type'],
  },
  '08-validation-rule': {
    group: '校验',
    covers: ['validation mustFollow -> previous-block validation', 'validation_workspace.html generation', 'generated warning runtime'],
  },
  '09-multivalue-field': {
    group: '字段类型',
    covers: ['upperBound=-1 EAttribute -> field_multivalue', 'multi-valued field -> field cardinality/unique validation'],
  },
  '10-image-angle-label': {
    group: '字段类型',
    covers: ['field_image src/width/height/alt', 'field_angle', 'field_label'],
  },
  '11-custom-message': {
    group: 'Blockly 注解',
    covers: ['EClass message0 -> custom block text', 'ordered structural features -> args0 order'],
  },
  '12-nested-category': {
    group: 'Toolbox 分类',
    covers: ['category path with / -> nested toolbox categories', 'leaf category assignment'],
  },
  '13-code-template': {
    group: '代码生成',
    covers: ['EPackage code language/fileExtension', 'EClass code template/codeTemplate', 'Export Code runtime'],
  },
  '14-workspace-options': {
    group: 'Workspace 配置',
    covers: ['workspace.renderer', 'workspace.grid.*', 'workspace.zoom.*', 'workspace.move.*'],
  },
  '15-auto-category': {
    group: 'Toolbox 分类',
    covers: ['no category annotations -> auto-generated categories', 'inheritance tree -> nested category tree'],
  },
  '16-output-blocks': {
    group: '继承与连接',
    covers: ['output=true -> output block', 'output=<type> -> typed output', 'input_value check -> typed socket'],
  },
  '17-class-presentation': {
    group: 'Blockly 注解',
    covers: ['ui label -> block label', 'tooltip/helpUrl -> block metadata', 'colour -> block colour', 'inputsInline=false'],
  },
  '18-field-overrides': {
    group: '字段类型',
    covers: ['field_input override', 'field_number override', 'field_checkbox override', 'min/max on overridden number'],
  },
  '19-ui-metadata': {
    group: 'UI 元数据',
    covers: ['ui widget/label/help/placeholder', 'ui group/order/variant', 'ui readonly/hidden'],
  },
  '20-id-reference-label': {
    group: '引用与连接',
    covers: ['EAttribute iD=true -> identity field', 'ID field -> unique validation', 'default reference label field'],
  },
  '21-multireference-opposite': {
    group: '引用与连接',
    covers: ['many non-containment reference -> reference multiselect', 'eOpposite -> opposite-reference sync', 'multi reference -> unique validation'],
  },
  '22-expression-validation': {
    group: '校验',
    covers: ['validation expression/condition/js subset', 'named validation message', 'number()/has() visual validation expressions'],
  },
  '23-ocl-validation': {
    group: '校验',
    covers: ['Ecore constraints detail', 'OCL/Pivot annotation', 'OCL size/notEmpty/comparison translation'],
  },
  '24-ignored-features': {
    group: 'Ecore 结构',
    covers: ['derived/transient/volatile/changeable=false -> skipped feature', 'only editable features become block inputs'],
  },
  '25-subpackages': {
    group: 'Ecore 结构',
    covers: ['nested EPackage/eSubpackages', 'classifiers in subpackages -> block types'],
  },
  '26-flyout-toolbox': {
    group: 'Workspace 配置',
    covers: ['workspace.toolboxType=flyout -> flyout toolbox', 'explicit toolbox type overrides auto category toolbox'],
  },
  '27-runtime-kind': {
    group: '运行时',
    covers: ['runtime kind=appMaker -> Preview tab', 'domain-specific runtime hook'],
  },
};

for (const example of examples) {
  Object.assign(example, coverageBySlug[example.slug] || { group: '其他', covers: [] });
}

main();

function main() {
  checkRuntime();
  rmSync(examplesRoot, { recursive: true, force: true });
  mkdirSync(examplesRoot, { recursive: true });

  const manifest = [];
  for (const example of examples) {
    const exampleDir = path.join(examplesRoot, example.slug);
    const generatedDir = path.join(exampleDir, 'generated');
    mkdirSync(exampleDir, { recursive: true });
    const sourcePath = path.join(exampleDir, 'source.ecore');
    writeFileSync(sourcePath, example.ecore);
    writeFileSync(path.join(exampleDir, 'source.html'), sourceHtml(example, sourcePath));

    runGenerator(sourcePath, generatedDir);
    rmSync(path.join(generatedDir, 'README.md'), { force: true });
    const standalone = findStandalone(generatedDir);
    if (!standalone) {
      throw new Error(`No standalone HTML generated for ${example.slug}`);
    }
    writeFileSync(path.join(exampleDir, 'preview.html'), previewHtml(example, standalone));
    manifest.push({
      slug: example.slug,
      title: example.title,
      zhTitle: example.zhTitle,
      description: example.description,
      zhDescription: example.zhDescription,
      group: example.group,
      covers: example.covers,
      source: `${example.slug}/source.ecore`,
      sourcePreview: `${example.slug}/source.html`,
      preview: `${example.slug}/preview.html`,
      report: `${example.slug}/generated/generation_report.html`,
      standalone: `${example.slug}/${path.relative(exampleDir, standalone).split(path.sep).join('/')}`,
    });
    console.log(`[docs examples] generated ${example.slug}`);
  }

  writeFileSync(path.join(examplesRoot, 'manifest.json'), `${JSON.stringify(manifest, null, 2)}\n`);
}

function plainFeaturePackage(name, classifiers) {
  return `<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore"
    name="${name}"
    nsURI="https://plortinus.github.io/model2blockly/examples/${name}"
    nsPrefix="${name}">
${classifiers}
</ecore:EPackage>
`;
}

function featurePackage(name, classifiers, blocklyDetails = '', packageAnnotations = '') {
  return `<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore"
    name="${name}"
    nsURI="https://plortinus.github.io/model2blockly/examples/${name}"
    nsPrefix="${name}">

  <eAnnotations source="blockly">
    <details key="workspace.renderer" value="zelos"/>
    <details key="workspace.trashcan" value="true"/>
    <details key="workspace.zoom.controls" value="true"/>
    <details key="workspace.zoom.wheel" value="true"/>
    <details key="workspace.move.scrollbars" value="true"/>
    <details key="workspace.move.drag" value="true"/>
${blocklyDetails}
  </eAnnotations>
${packageAnnotations}
${classifiers}
</ecore:EPackage>
`;
}

function runGenerator(sourcePath, generatedDir) {
  execFileSync(java, [
    '-cp',
    classpath,
    'io.github.plortinus.model2blockly.standalone.EcoreToBlocklyMain',
    path.relative(projectDir, sourcePath),
    path.relative(projectDir, generatedDir),
  ], {
    cwd: projectDir,
    encoding: 'utf8',
    stdio: ['ignore', 'pipe', 'pipe'],
  });
}

function previewHtml(example, standalone) {
  const standaloneRel = path.relative(path.join(examplesRoot, example.slug), standalone).split(path.sep).join('/');
  return `<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${escapeHtml(example.title)}</title>
  <style>
    html, body {
      margin: 0;
      width: 100%;
      height: 100%;
      overflow: hidden;
      background: #f4f6f8;
    }
    iframe {
      display: block;
      width: 100%;
      height: 100%;
      border: 0;
      background: #fff;
    }
  </style>
</head>
<body>
  <iframe id="generatedEditor" title="${escapeHtml(example.title)}" src="${standaloneRel}"></iframe>
  <script>
    const frame = document.getElementById('generatedEditor');
    frame.addEventListener('load', () => {
      let attempts = 0;
      const timer = window.setInterval(() => {
        const win = frame.contentWindow;
        if (!win || !win.document) return stopAfterLimit();
        if (typeof win.loadSampleModel !== 'function' || !win.workspace) return stopAfterLimit();
        focusGeneratedWorkspace(win);
        window.clearInterval(timer);
      }, 200);

      function stopAfterLimit() {
        attempts += 1;
        if (attempts > 50) window.clearInterval(timer);
      }
    });

    function focusGeneratedWorkspace(win) {
      const doc = win.document;
      if (!doc.getElementById('docsPreviewStyle')) {
        const style = doc.createElement('style');
        style.id = 'docsPreviewStyle';
        style.textContent = [
          '#header.appbar{display:none!important}',
          '#main{display:block!important;padding:0!important;height:100vh!important;overflow:hidden!important}',
          '.workspace-shell{height:100vh!important;border:0!important;border-radius:0!important;box-shadow:none!important}',
          '.workspace-panel-header{display:none!important}',
          '#outputPanel{display:none!important}',
          '#blocklyDiv{height:100vh!important}',
          '.blocklyToolboxDiv{font-size:12px!important}'
        ].join('\\n');
        doc.head.appendChild(style);
      }
      try {
        win.loadSampleModel();
        [0, 150, 500].forEach((delay) => {
          window.setTimeout(() => fitPreviewWorkspace(win), delay);
        });
      } catch (error) {
        // Keep the generated editor visible even if sample import fails.
      }
    }

    function fitPreviewWorkspace(win) {
      if (!win.workspace) return;
      if (win.workspace.setScale) win.workspace.setScale(0.72);
      const topBlocks = win.workspace.getTopBlocks ? win.workspace.getTopBlocks(false) : [];
      const firstBlock = topBlocks && topBlocks.length ? topBlocks[0] : null;
      if (firstBlock && win.workspace.centerOnBlock) win.workspace.centerOnBlock(firstBlock.id);
      if (win.Blockly && win.Blockly.svgResize) win.Blockly.svgResize(win.workspace);
    }
  </script>
</body>
</html>
`;
}

function sourceHtml(example, sourcePath) {
  const source = readFileSync(sourcePath, 'utf8');
  return `<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${escapeHtml(example.title)} Ecore</title>
  <style>
    :root {
      color-scheme: light;
      --bg: #f6f8fa;
      --text: #24292f;
      --tag: #116329;
      --attr: #0550ae;
      --value: #953800;
      --line: #d0d7de;
    }
    * { box-sizing: border-box; }
    html, body {
      margin: 0;
      min-height: 100%;
      background: var(--bg);
      color: var(--text);
      font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", monospace;
    }
    pre {
      margin: 0;
      min-height: 100vh;
      padding: 14px 16px;
      overflow: auto;
      font-size: 12px;
      line-height: 1.55;
      white-space: pre;
      tab-size: 2;
    }
    .tag { color: var(--tag); font-weight: 700; }
    .attr { color: var(--attr); }
    .value { color: var(--value); }
  </style>
</head>
<body>
  <pre><code>${highlightXml(source)}</code></pre>
</body>
</html>
`;
}

function highlightXml(xml) {
  return escapeHtml(xml)
    .replace(/(&lt;\/?[A-Za-z0-9:_-]+)/g, '<span class="tag">$1</span>')
    .replace(/([A-Za-z0-9:_-]+)=(&quot;[^&]*?&quot;)/g, '<span class="attr">$1</span>=<span class="value">$2</span>')
    .replace(/(\/?&gt;)/g, '<span class="tag">$1</span>');
}

function findStandalone(generatedDir) {
  const htmlDir = path.join(generatedDir, 'html');
  if (!existsSync(htmlDir)) return null;
  const html = readdirSync(htmlDir).find((file) => file.endsWith('_standalone.html'));
  return html ? path.join(htmlDir, html) : null;
}

function checkRuntime() {
  for (const file of [projectDir, coreUpdateSiteJar, eclipsePlugins, java]) {
    if (!existsSync(file)) {
      throw new Error(`Required file or directory not found: ${file}`);
    }
  }
}

function findBundledJavaHome(pluginsDir) {
  if (!existsSync(pluginsDir)) return '';
  const candidates = readdirSync(pluginsDir)
    .filter((entry) => entry.startsWith('org.eclipse.justj.openjdk.hotspot.jre.full.'))
    .sort()
    .reverse()
    .map((entry) => path.join(pluginsDir, entry, 'jre'));
  return candidates.find((candidate) => existsSync(path.join(candidate, 'bin/java'))) || '';
}

function escapeHtml(value) {
  return String(value)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}
