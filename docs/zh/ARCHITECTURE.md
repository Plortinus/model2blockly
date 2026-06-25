# 架构与实现

Model2Blockly 按模型驱动工程流程组织。主路线从带注解的 Ecore 元模型开始，把 EMF 加载得到的 `EPackage` 转换成生成的 EMF `EditorSpec` 中间模型，再把中间模型保存为 XMI、读回、验证，最后从读回的模型生成 Blockly 编辑器。

## 对齐 EMF/MDE 流程

项目遵循 Eclipse [EMF 文档](https://eclipse.dev/emf/docs.html)里的基本模式：用 Ecore 定义元模型，用 `.genmodel` 和生成代码维护生成器需要的 EMF API，用 XMI 保存模型实例，并且从模型生成目标产物，而不是直接拼接文本。

Model2Blockly 的核心链路是：

```text
带注解的 Ecore 元模型 (.ecore)
  -> EMF ResourceSet / EPackage
  -> 模型到模型转换
  -> 生成的 EMF EditorSpec 模型
  -> intermediate/*_blocklyspec.xmi
  -> 读回并验证 XMI
  -> 模型到文本生成
  -> Blockly HTML/JavaScript 编辑器
  -> 用户创建的领域模型 JSON/XMI
```

内部生成元模型保留为 EMF 产物：

```text
io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore
io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.genmodel
io.github.plortinus.model2blockly/model/blockly_editor_spec.ecore
io.github.plortinus.model2blockly/model/metamodel/BlocklyEditorSpec.genmodel
io.github.plortinus.model2blockly/emf-gen/
```

文本 DSL 使用 Xtext 作为固定 `Model2Blockly.ecore` 抽象语法之上的具体语法。
Xtext 生成的 parser、service 和 IDE 代码写入 `src-gen`；固定元模型对应的
EMF Java API 放在 `emf-gen`，这样重新生成 DSL 基础设施时不会被清理。

Ecore 路线也可以动态处理源 `.ecore`，不要求为该领域先生成 Java 代码。领域 `EPackage` 由 EMF 加载，并作为转换的源模型。

## 系统架构

Model2Blockly 记录为一条 MDE 路线：带注解的 Ecore 由 EMF 加载为 `EPackage`，转换成生成的 `EditorSpec` 模型，然后由同一个生成后端输出 HTML 和 JavaScript。

![Model2Blockly 系统架构](../assets/diagrams/system-architecture.svg)

## 生成流程

生成器会先写出中间 XMI，再从 XMI 读回并验证，最后生成浏览器端文件。这个步骤让生成出的形式化模型可以被查看、复现和调试。

![Model2Blockly 生成流程](../assets/diagrams/generation-flow.svg)

## 输出产物

生成目录分成三类：用户直接打开的页面、Blockly 运行所需的 JavaScript、以及用于解释和调试的报告与中间模型。

![生成目录产物](../assets/diagrams/output-artifacts.svg)

## 核心与扩展边界

项目的核心贡献是元模型到 Blockly 编辑器的生成链路：

- 带注解的 Ecore 输入；
- 生成的 EMF `EditorSpec` 中间模型；
- 中间 XMI 的保存、读回和验证；
- Blockly block、toolbox 和编辑器生成；
- 一个代表性案例 AppMaker。

下面这些功能是有用的扩展，但不是架构主线：

- 从用户模型导出代码；
- 可视化验证规则工作区；
- Eclipse update site 打包；
- AppMaker 专用浏览器预览。

## 实现位置

| 职责 | 主要实现 |
| --- | --- |
| Ecore 注解转 `EditorSpec` | [`EcoreAdapter.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java) |
| 中间 XMI 保存 | [`BlocklySpecXmiSerializer.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/intermediate/BlocklySpecXmiSerializer.java) |
| 中间模型验证 | [`BlocklyEditorSpecValidator.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/blocklyspec/BlocklyEditorSpecValidator.java) |
| Blockly HTML 和 JavaScript 输出 | [`BlocklyCodeGenerator.xtend`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/BlocklyCodeGenerator.xtend) |
| 生成流程编排 | [`Model2BlocklyGenerator.xtend`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/generator/Model2BlocklyGenerator.xtend) |
| 导出领域 XMI 的 EMF 加载与验证 | [`verify-domain-xmi.mjs`](../../scripts/verify-domain-xmi.mjs) |

## 实现要点

- 编辑器是模型驱动生成的，不是手写 Blockly blocks。
- 中间 XMI 不是临时日志，而是会被重新读回并验证的正式中间产物。
- Ecore 路线生成的 AppMaker 编辑器会导出示例领域 XMI，`npm run verify:domain-xmi` 会加载 `app_maker.ecore`、注册动态 `EPackage`、用 EMF 读入导出的 XMI，并运行 `Diagnostician`。
- AppMaker 案例同时展示 Ecore 注解、中间 XMI、生成 JavaScript、截图、生成报告和验证工作区。
