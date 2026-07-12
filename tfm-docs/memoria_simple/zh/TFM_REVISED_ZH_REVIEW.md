# TFM 中文修订审阅稿

文件用途：本稿用于先审阅论文叙事、术语和章节结构。确认后，再把内容改写进正式西语论文。

## 需要先确认的口径

1. 项目名称统一写作 **Model2Blockly**。`.m2b` 是 Model2Blockly 文本 DSL 的文件扩展名，不是项目名。
2. 用户看到并使用的结果统一称为 **Blockly 编辑器**。在论文中可以解释它是“基于 Blockly 的图形化建模界面”，但正文中不把它简单称为“DSL 编辑器”。
3. `.m2b` 文本 DSL 的使用者是语言设计者或工具开发者。它用于定义要生成的 Blockly 语言和编辑器结构。最终领域用户使用的是生成后的 Blockly 编辑器。
4. AppMaker 作为唯一 running example。论文中用 AppMaker 同时展示 Ecore route 和 `.m2b` route，并说明两条路线都进入同一个 EMF 中间模型 `EditorSpec`。
5. 当前仓库已经有 AppMaker 的 Ecore 生成输出：`examples/generated/app_maker_ecore`。`.m2b` 源文件已经补齐为 `examples/app_maker.m2b`，但最终西语论文前还应实际跑通并保存 DSL route 的生成输出，或在论文中明确它是对照输入而不是已提交的完整生成基线。

## 术语统一

| 概念 | 论文推荐写法 | 中文含义 |
| --- | --- | --- |
| Tool | Model2Blockly | 本项目实现的生成工具 |
| Generated editor | Blockly editor | 生成出来、给领域用户拖拽使用的编辑器 |
| Textual DSL | Model2Blockly textual DSL / `.m2b` DSL | 给语言设计者写的语言定义 DSL |
| Ecore route | Ecore route | 从带注解的 `.ecore` 元模型生成 |
| DSL route | `.m2b` route / textual DSL route | 从 `.m2b` 文本语言定义生成 |
| Intermediate model | `EditorSpec` EMF intermediate model | 统一的中间模型 |
| Running example | AppMaker | 贯穿论文的示例领域 |

# 摘要

本硕士论文研究如何通过模型驱动工程生成基于 Blockly 的领域编辑器。Blockly 提供了可拖拽积木、workspace、toolbox 和序列化等基础设施，但开发者在每个新领域中仍然需要手工定义 blocks、fields、connections、toolbox、验证规则和代码生成逻辑。这些工作具有重复性，而且要求开发者熟悉较底层的 Blockly JavaScript API。

为降低这种重复开发成本，本文提出并实现了 Model2Blockly。该工具把领域定义转换为可运行的 Blockly 编辑器。系统支持两种输入路线：一种是带注解的 Ecore 元模型，另一种是由 Xtext 定义的 Model2Blockly 文本 DSL，即 `.m2b` 文件。两种输入不会直接生成 JavaScript，而是先转换到统一的 EMF 中间模型 `EditorSpec`。该中间模型描述生成 Blockly 编辑器所需的信息，包括 block 类型、字段、toolbox 分类、结构输入、值输入、引用、验证规则、workspace 选项和代码导出元数据。随后，生成器基于 `EditorSpec` 输出 HTML 和 JavaScript 文件。

论文使用 AppMaker 作为 running example。AppMaker 是一个简化的低代码应用建模领域，包含 App、DataSource、Page、Component、Action 和 TextExpression 等概念。本文分别展示 AppMaker 的 Ecore 版本和 `.m2b` 版本，并说明它们如何映射到同一个 `EditorSpec`，再生成 AppMaker Blockly 编辑器。生成的编辑器允许领域用户通过拖拽积木创建应用模型，进行运行时验证，并导出 JSON、XML、domain XMI 或代码片段。

评估部分不声称 Model2Blockly 已经覆盖所有可能的 DSL 或所有 Ecore 元模型，而是证明在 AppMaker 这个代表性案例中，工具可以系统地从模型定义生成可用的 Blockly 编辑器。项目同时提供 GitHub 仓库、Eclipse update site、生成示例页面、生成报告、中间 XMI 和自动化验证脚本，从而使评审可以检查实现代码和生成物。结果表明，Model2Blockly 不是一个手写的单一 Blockly 页面，而是一条可复用的 MDE 生成链。

# 第 1 章 引言

程序设计不仅用于实现软件系统，也用于表达领域知识和自动化任务。然而，对初学者和非技术用户来说，传统文本语言存在明显的语法门槛。基于积木的编程环境通过可拖拽、可连接的视觉部件降低了这种门槛。Scratch、App Inventor、Tynker 和 MakeCode 等系统说明，块式交互已经广泛应用在教育、机器人、移动应用和游戏等场景中。

Blockly 是构建这类环境的重要 Web 库。它提供积木渲染、拖拽、连接、workspace、toolbox 和序列化等基础能力。开发者可以用 Blockly 构建一个面向特定领域的可视化编辑器。但是，Blockly 本身并不会自动理解领域模型。每创建一个新领域，开发者仍然需要手写 block 定义、字段、连接规则、toolbox 分类、验证逻辑和代码生成器。

这引出了本文的核心问题：如果一个领域已经可以用元模型或语言定义描述，为什么还要把同样的结构再次手写成 Blockly JavaScript 代码？模型驱动工程的基本思想是把模型作为核心工件，通过模型转换和代码生成减少手工实现。对于领域特定语言而言，抽象语法可以由元模型描述，而具体语法可以是文本、图形或积木形式。因此，Blockly 编辑器可以被看作某个领域语言的一种图形化具体语法。

本文提出的 Model2Blockly 位于 MDE、DSL 和 Blockly 的交叉位置。它不是一个面向最终用户的固定 AppMaker 编辑器，而是一个用于生成 Blockly 编辑器的工具。它允许语言设计者从两类输入出发：

- Ecore route：使用标准 EMF `.ecore` 元模型描述领域结构，并通过 EAnnotation 补充 Blockly 显示、UI、代码生成和验证信息。
- `.m2b` route：使用 Xtext 定义的 Model2Blockly 文本 DSL 更紧凑地描述 Blockly 语言结构。

两条路线都会转换到同一个 EMF 中间模型 `EditorSpec`。之后，生成器只依赖这个中间模型输出 Blockly 编辑器。因此，输入路线和输出技术被解耦：Ecore 和 `.m2b` 的差异被限制在适配层，而 HTML/JavaScript 生成逻辑保持共享。

为了使论文更容易理解，本文采用 AppMaker 作为贯穿全文的 running example。AppMaker 用于描述简单的低代码应用：应用包含数据源和页面，页面包含组件，组件触发动作，动作可以引用页面或数据源，表达式可以提供文本内容。该例子覆盖 containment、reference、inheritance、abstract class、output block、required field、cardinality 和 validation 等特征。

本项目的公开材料包括：

- 代码仓库：`https://github.com/Plortinus/model2blockly`
- 项目文档：`https://plortinus.github.io/model2blockly/`
- Eclipse update site：`https://plortinus.github.io/model2blockly/update-site/`
- AppMaker Ecore 生成示例：`https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html`

论文其余部分组织如下：第 2 章说明目标和范围；第 3 章介绍 MDE、EMF、Xtext、Blockly 和相关工作；第 4 章说明 Model2Blockly 的架构；第 5 章说明实现和转换规则；第 6 章用 AppMaker 进行评估；第 7 章总结贡献、限制和未来工作。

# 第 2 章 目标与范围

## 2.1 总目标

本文的总目标是设计并实现一个模型驱动的生成工具，使语言设计者能够从高层领域定义生成可运行的 Blockly 编辑器。该目标对应原始 TFM 提案中“从元模型生成使用 Blockly 的代码”的要求，同时扩展出一条 `.m2b` 文本 DSL 路线，用于更紧凑地定义 Blockly 语言。

换句话说，Model2Blockly 的目标不是替代 Blockly，也不是实现一个完整 low-code 平台，而是生成 Blockly 编辑器。生成后的编辑器才是领域用户使用的界面；Model2Blockly 本身面向的是语言设计者、MDE 工程师和工具开发者。

## 2.2 具体目标

**OE1：分析 Blockly 编辑器所需的结构。** 需要识别一个 Blockly 编辑器通常需要哪些信息：block 类型、field、toolbox 分类、statement input、value input、reference、validation、workspace 配置、代码生成器和 HTML 页面。

**OE2：实现 Ecore route。** 允许从 EMF `.ecore` 元模型生成 Blockly 编辑器。`EClass` 映射为 block type，`EAttribute` 映射为 field，containment `EReference` 映射为 statement input，non-containment `EReference` 映射为动态引用字段，cardinality 映射为验证规则。

**OE3：实现 `.m2b` textual DSL route。** 使用 Xtext 定义 Model2Blockly 文本 DSL，使语言设计者可以用紧凑文本描述 Blockly 语言的结构。`.m2b` 文件不是领域用户创建 App 实例的语言，而是定义要生成哪些 block、字段、连接和验证规则的语言定义 DSL。

**OE4：设计统一 EMF 中间模型。** 不让 Ecore 或 `.m2b` 直接生成 JavaScript，而是都转换到 `EditorSpec`。这样可以把输入解析、模型转换和代码生成分离。

**OE5：生成可运行的 Blockly 编辑器。** 生成 HTML/JavaScript 文件，包括 block definitions、toolbox、generators、validations、standalone HTML、sample model、generation report 和 intermediate XMI。

**OE6：支持引用、验证和展示定制。** 生成的编辑器应支持 required field、cardinality、dynamic reference dropdown、inheritance-based typed connection、颜色、标签、tooltip、workspace 选项和代码模板。

**OE7：用 AppMaker 和自动化检查评估。** AppMaker 作为主要案例，展示 Ecore route 和 `.m2b` route 如何描述同一个应用建模领域，并检查生成物、报告、XMI 和脚本。

## 2.3 范围

本文范围包括：

| 能力 | 是否属于核心范围 |
| --- | --- |
| 从 `.ecore` 生成 Blockly 编辑器 | 是 |
| 从 `.m2b` 生成 Blockly 编辑器 | 是 |
| 统一 EMF 中间模型 `EditorSpec` | 是 |
| HTML/JavaScript standalone 编辑器生成 | 是 |
| required/cardinality/reference validation | 是 |
| Ecore annotation 映射规则 | 是 |
| AppMaker running example | 是 |
| Eclipse plugin 和 update site | 是 |
| 完整 low-code app runtime | 否，作为 AppMaker 案例的扩展展示 |
| 任意 OCL 完整支持 | 否，作为未来工作 |
| 从已有 domain XMI 自动恢复 Blockly workspace | 否，作为未来工作 |
| 用户实验或可用性统计 | 否，作为未来工作 |

# 第 3 章 相关工作与技术

## 3.1 基于积木的编程

基于积木的编程环境用可视化部件表示程序或模型结构。积木的形状、颜色、标签和分类帮助用户理解可用构造，并减少传统文本语法中的拼写和标点错误。Scratch 说明块式环境可以有效降低初学者门槛；Dr. Scratch 等工具说明块式程序本身也具有可分析的结构。

在本文中，积木不是简单的 UI 装饰，而是领域语言的一种具体语法。领域用户通过拖拽积木构造模型，语言设计者通过 Ecore 或 `.m2b` 定义这些积木背后的抽象结构。

## 3.2 Blockly

Blockly 是本文的生成目标。它负责运行时交互，包括积木渲染、拖拽、拼接、workspace 管理、toolbox 和序列化。开发者仍然需要定义每个 block 的字段、输入、连接、颜色、tooltip、代码生成逻辑和验证逻辑。Model2Blockly 的作用就是自动生成这些领域相关部分。

## 3.3 MDE、DSL 和 EMF

MDE 强调使用模型作为开发过程中的核心工件。DSL 则强调使用领域概念表达问题。二者结合时，领域可以由元模型描述，再通过模型转换或代码生成得到工具、代码或文档。

本项目符合 MDE 的标准流程：

```text
定义源语言或源元模型
  -> 创建源模型
  -> 模型转换到中间模型
  -> 从中间模型生成系统代码或工具
```

在 Model2Blockly 中，这个流程具体化为：

```text
Ecore route:
  app_maker.ecore -> EPackage -> EcoreAdapter -> EditorSpec -> Blockly editor

.m2b route:
  app_maker.m2b -> Xtext parser -> DomainModel -> DomainModelAdapter -> EditorSpec -> Blockly editor
```

## 3.4 Xtext 和 `.m2b`

Xtext 用于定义 Model2Blockly 文本 DSL 的语法。`.m2b` 文件会被解析成 EMF 模型 `DomainModel`。因此，`.m2b` 不是普通配置文件，而是 Xtext/EMF 支撑的文本模型。

`.m2b` 的作用是定义一个 Blockly 语言，而不是创建最终 App 实例。例如：

```text
class Button extends Component category Components colour 160 label "Button" {
  attribute labelText : string default "Save" required
  contains Action onClick [0..10]
}
```

这段代码的意思不是“创建一个按钮”，而是“定义 AppMaker 语言中有一种 Button block，它有 `labelText` 字段和 `onClick` 子动作输入”。

## 3.5 相关工作定位

本文需要补充两类相关工作。第一类是 MDE、EMF、Xtext 和 DSL 的基础文献，用来支撑模型转换和代码生成方法。第二类是 block-based DSL 或 Blockly language workbench 相关研究，用来说明本文和已有 block language 生成工具之间的关系。

Model2Blockly 的差异点在于：它把 Ecore route、`.m2b` route 和 EMF 中间模型连接起来，并以 Eclipse plugin、update site、生成报告和生成示例的形式交付可检查工具。

# 第 4 章 设计与架构

## 4.1 总体设计

Model2Blockly 的架构基于一个核心原则：输入模型和输出 Blockly 代码不直接耦合。系统先把输入转换到统一的 `EditorSpec`，然后由生成器从 `EditorSpec` 输出 HTML/JavaScript。

```text
Annotated Ecore metamodel             Model2Blockly textual DSL
(.ecore)                              (.m2b)
        |                                  |
        v                                  v
EMF ResourceSet / EPackage          Xtext parser / DomainModel
        |                                  |
        v                                  v
EcoreAdapter                         DomainModelAdapter
        \                                  /
         \                                /
          v                              v
           EditorSpec EMF intermediate model
                         |
                         v
          intermediate/*_blocklyspec.xmi
                         |
                         v
              XMI reload + diagnostics
                         |
                         v
              BlocklyCodeGenerator.xtend
                         |
                         v
      HTML + JS + toolbox + validations + sample model
```

这个结构说明，本文不是“Ecore 直接拼接 JavaScript”，也不是“.m2b 直接生成网页”。两条路线都经过显式模型转换，并以 EMF `EditorSpec` 作为生成契约。

## 4.2 `EditorSpec` 中间模型

`EditorSpec` 是项目中的 EMF 中间模型，定义在 `model/blockly_editor_spec.ecore`。它的实例会序列化为 `intermediate/*_blocklyspec.xmi`，并在最终代码生成前重新加载和诊断。这样做可以让模型转换结果可检查，而不是只存在于临时 Java 对象中。

`EditorSpec` 包含：

| 元素 | 作用 |
| --- | --- |
| `EditorSpec` | 根对象，保存 domain 名称、namespace、runtime、代码语言等 |
| `CategorySpec` | Blockly toolbox 分类 |
| `BlockTypeSpec` | 一个 Blockly block type |
| `FieldSpec` | 文本、数字、布尔、枚举、颜色等字段 |
| `ReferenceFieldSpec` | 指向已有模型元素的动态引用字段 |
| `StatementInputSpec` | containment 子块输入 |
| `ValueInputSpec` | 表达式或参数输入 |
| `ValidationSpec` | 运行时验证规则 |
| `WorkspaceOption` | Blockly workspace 配置 |

中间模型是论文 MDE 论证的关键。它证明项目不是只写了一个 JavaScript 生成脚本，而是存在明确的模型转换目标。

## 4.3 Ecore route

Ecore route 适合已有 EMF 元模型的场景。它的输入是 `.ecore` 文件，核心转换由 `EcoreAdapter` 实现。

基础映射如下：

| Ecore 元素 | `EditorSpec` 元素 | Blockly 结果 |
| --- | --- | --- |
| `EPackage` | `EditorSpec` | 编辑器 domain、namespace、代码导出配置 |
| `EClass` | `BlockTypeSpec` | 一个 block type |
| abstract `EClass` | `BlockTypeSpec.abstract=true` | 抽象连接类型，不直接出现在 toolbox |
| `EAttribute` | `FieldSpec` | Blockly field |
| `EEnum` | `DropdownOption` | 下拉字段 |
| containment `EReference` | `StatementInputSpec` | 子块区域 |
| non-containment `EReference` | `ReferenceFieldSpec` | 动态引用下拉字段 |
| `lowerBound` / `upperBound` | `ValidationSpec` | required 或 cardinality warning |
| inheritance | connection type | 类型化连接 |

Ecore 本身表达结构，EAnnotation 补充 Blockly 需要但 Ecore 不直接表达的展示信息。

## 4.4 `.m2b` route

`.m2b` route 适合没有现成 Ecore 元模型、或者需要在论文中清晰展示语言结构的场景。它的输入是 Model2Blockly textual DSL 文件，核心转换由 `DomainModelAdapter` 实现。

典型映射如下：

| `.m2b` 元素 | `EditorSpec` 元素 | Blockly 结果 |
| --- | --- | --- |
| `domain Appmaker` | `EditorSpec.domainName` | 编辑器标题和生成文件前缀 |
| `category Pages` | `CategorySpec` | toolbox 分类 |
| `class App` | `BlockTypeSpec` | App block |
| `abstract class Component` | abstract block type | 连接类型，不作为普通 block 使用 |
| `output class TextLiteral` | output block type | 表达式积木 |
| `attribute name : string` | `FieldSpec` | 文本字段 |
| `attribute theme : enum` | dropdown field | 下拉字段 |
| `contains Page pages [1..20]` | `StatementInputSpec` | 子块输入和 cardinality validation |
| `reference Page target required` | `ReferenceFieldSpec` | 动态引用字段 |
| `value TextExpression message` | `ValueInputSpec` | 表达式输入 |
| `validation ...` | `ValidationSpec` | 运行时 warning |

这条路线的价值不是替代 Ecore，而是提供更紧凑的语言定义方式。它也有利于解释 DSL 的元模型、语法和示例。

## 4.5 Ecore 注解表

论文中应加入注解表，回应老师“Describir en una tabla las anotaciones en detalle”的批注。

| Source | Key | 位置 | 作用 |
| --- | --- | --- | --- |
| `blockly` | `category` | `EClass` | toolbox 分类 |
| `blockly` | `colour` | `EClass` | block 颜色 |
| `blockly` | `tooltip` | `EClass` | 鼠标提示 |
| `blockly` | `inputsInline` | `EClass` | 输入是否行内显示 |
| `blockly` | `type` | `EAttribute` | 指定 Blockly 字段类型 |
| `ui` | `label` | class 或 feature | 显示标签 |
| `ui` | `widget` | feature | 建议控件类型 |
| `ui` | `group` | feature | inspector 或报告中的分组 |
| `ui` | `order` | feature | 字段顺序 |
| `ui` | `referenceLabelField` | reference | 引用下拉显示哪个字段 |
| `code` | `template` | `EClass` | 代码生成模板 |
| `code` | `language` | `EPackage` | 输出语言 |
| `code` | `fileExtension` | `EPackage` | 输出文件扩展名 |
| `runtime` | `kind` | `EPackage` | 特定 runtime，例如 AppMaker |
| `validation` | `expression` | `EClass` | 简单验证表达式 |
| `validation` | `message` | `EClass` | 验证消息 |

## 4.6 生成输出

生成器从 `EditorSpec` 输出一组静态文件：

| 文件 | 作用 |
| --- | --- |
| `html/<Domain>_blocks.js` | Blockly block definitions |
| `html/<Domain>_toolbox.js` | toolbox 分类 |
| `html/<Domain>_generators.js` | 代码或模型导出逻辑 |
| `html/<Domain>_validations.js` | 运行时验证 |
| `html/<Domain>_editor.html` | 嵌入式编辑器页面 |
| `html/<Domain>_standalone.html` | 可直接打开的完整 Blockly 编辑器 |
| `html/validation_workspace.html` | 用 Blockly 展示验证规则 |
| `html/sample_model.json` | 示例模型 |
| `generation_report.html` | 生成报告 |
| `intermediate/*_blocklyspec.xmi` | 中间模型 XMI |

# 第 5 章 实现

## 5.1 项目组织

Model2Blockly 以 Eclipse/Xtext 项目形式组织。核心项目包含 Xtext grammar、EMF 模型、adapter、中间模型映射、生成器、standalone 入口和测试。UI 项目提供 Eclipse 右键命令。feature 和 update site 项目用于插件打包和安装。

主要实现文件包括：

| 组件 | 文件 |
| --- | --- |
| Xtext grammar | `Model2Blockly.xtext` |
| `.m2b` 元模型 | `model/metamodel/Model2Blockly.ecore` |
| 中间模型 | `model/blockly_editor_spec.ecore` |
| Ecore 转换 | `EcoreAdapter.java` |
| `.m2b` 转换 | `DomainModelAdapter.java` |
| Java 对象到 EMF 模型映射 | `BlocklySpecModelMapper.java` |
| XMI 序列化和读回 | `BlocklySpecXmiSerializer.java` |
| Blockly 生成器 | `BlocklyCodeGenerator.xtend` |
| Ecore standalone 入口 | `EcoreToBlocklyMain.java` |
| `.m2b` standalone 入口 | `Model2BlocklyToBlocklyMain.java` |
| Eclipse 右键生成 | `GenerateBlocklyEditorHandler.java` |

## 5.2 `.m2b` 文本 DSL

`.m2b` 语法从 `domain` 开始，然后声明分类、类、字段、包含关系、引用、值输入和验证规则。

```text
domain Appmaker
runtimeKind "appMaker"

category Pages label "Pages" colour 260
category Components label "Components" colour 160

class App category Pages colour 260 label "App" {
  attribute name : string default "Task Tracker" required
  contains DataSource dataSources [1..20]
  contains Page pages [1..20]
}

class Page category Pages colour 260 label "Page" {
  attribute title : string default "Home" required
  attribute route : string default "/home" required
  contains Component components [1..40]
  contains Action onEnter [0..10]
}
```

这段文本定义的是 AppMaker Blockly 语言，而不是一个具体 App 实例。生成后，领域用户才在 Blockly 编辑器里创建具体 App 模型。

## 5.3 Ecore route 实现

Ecore route 通过 EMF `ResourceSet` 加载 `.ecore`，得到 `EPackage`。`EcoreAdapter` 遍历 package、classifier、attribute 和 reference，构造内部 `BlocklyEditorSpec`，再映射到 EMF `EditorSpec`。

转换过程分为：

1. 读取 `EPackage` 的 name、nsURI、nsPrefix。
2. 读取 package 级 `blockly`、`code`、`runtime` 注解。
3. 收集所有 `EClass` 和 `EEnum`。
4. 为每个 `EClass` 创建 `BlockTypeSpec`。
5. 将 `EAttribute` 转换为 `FieldSpec`。
6. 将 containment `EReference` 转换为 `StatementInputSpec`。
7. 将 non-containment `EReference` 转换为 `ReferenceFieldSpec`。
8. 根据 lowerBound、upperBound、required、mustFollow 和 validation 注解生成验证规则。

## 5.4 `.m2b` route 实现

`.m2b` route 由 Xtext parser 产生 `DomainModel`。`DomainModelAdapter` 读取 `DomainModel` 中的 domain、category、class、attribute、contains、reference、value 和 validation 声明，并转换到相同的 `EditorSpec`。

这说明 `.m2b` 并不是绕过 MDE 的配置文件，而是“文本模型 -> EMF AST -> 模型转换 -> EMF 中间模型 -> 代码生成”的链路。

## 5.5 代码生成实现

最终生成由 `BlocklyCodeGenerator.xtend` 完成。它不直接读取 Ecore 或 `.m2b`，只读取 `EditorSpec`。这回答老师关于“生成器如何实现，是否使用 Xtend/Acceleo”的问题：当前版本使用 Java adapter 和 Xtend/Java 生成器，没有使用 ATL、ETL 或 Acceleo。

选择 Xtend 的原因是项目本身基于 Xtext/Eclipse，Xtend 与 EMF 模型和 Java 代码集成方便，适合生成 HTML/JavaScript 字符串和文件。

## 5.6 验证和引用

系统区分两类验证：

- 生成前验证：检查源定义是否合理，例如重复名称、无效 cardinality、字段类型和 widget 不匹配。
- 运行时验证：用户拖拽 Blockly block 后，检查当前 workspace 是否满足 required、cardinality、reference 和简单顺序规则。

示例：

```text
contains Page pages [1..20]
```

会生成一个 statement input，并生成验证逻辑：`App` 至少包含 1 个 `Page`，最多包含 20 个 `Page`。如果用户在 Blockly 编辑器中没有放入任何页面，编辑器显示 warning。

non-containment reference 使用动态 dropdown。编辑器会扫描 workspace 中已有 block，根据目标类型和继承关系筛选可引用元素。例如 `Navigate.target` 只显示可作为页面目标的 `Page` block。

# 第 6 章 AppMaker running example 与评估

## 6.1 评估策略

本文的评估重点是技术可行性，而不是用户实验。评估回答四个问题：

1. 能否从领域定义生成可运行的 Blockly 编辑器？
2. Ecore route 和 `.m2b` route 是否能进入同一个中间模型？
3. 生成物是否可检查、可打开、可验证？
4. 项目是否提供仓库、update site、生成报告和公开示例，便于复现？

## 6.2 AppMaker 领域

AppMaker 是一个简化低代码应用建模领域。它不是最终可部署的移动应用平台，而是一个用于验证 Model2Blockly 的代表性建模领域。

| 概念 | 作用 |
| --- | --- |
| `App` | 应用根模型，包含数据源和页面 |
| `DataSource` | HTTP 数据源，可被组件和动作引用 |
| `Page` | 应用页面，包含组件和进入页面动作 |
| `Component` | 页面组件抽象父类 |
| `Button` | 按钮组件，包含点击动作 |
| `TextInput` | 输入组件，可被表达式引用 |
| `ListView` | 引用数据源并展示列表 |
| `Container` / `Form` | 布局和表单容器 |
| `Action` | 动作抽象父类 |
| `Navigate` / `Alert` / `SubmitForm` | 具体动作 |
| `TextExpression` | 文本表达式抽象父类 |
| `TextLiteral` / `InputValue` / `DataField` / `JoinText` | 输出文本的表达式 block |

## 6.3 两条输入路线

Ecore route 使用：

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

`.m2b` route 使用：

```text
io.github.plortinus.model2blockly/examples/app_maker.m2b
```

两条路线都应在论文中展示片段。Ecore 片段用于说明注解，`.m2b` 片段用于说明文本 DSL 的紧凑性。

## 6.4 AppMaker 指标

当前 AppMaker Ecore 模型可以统计出：

| 指标 | Ecore route |
| --- | ---: |
| `EClass` | 40 |
| `EEnum` | 11 |
| `EAttribute` | 85 |
| `EReference` | 29 |
| containment references | 21 |
| `EAnnotation` | 221 |
| annotation detail entries | 568 |

当前 `.m2b` AppMaker 源文件可以统计出：

| 指标 | `.m2b` route |
| --- | ---: |
| category | 6 |
| class total | 20 |
| abstract class | 3 |
| output class | 4 |
| attribute | 30 |
| reference | 6 |
| contains | 8 |
| value input | 6 |
| validation | 1 |
| constraint | 1 |

这些指标可以用于说明两点。第一，Ecore route 是更完整的 AppMaker 元模型表达。第二，`.m2b` route 提供更紧凑、更适合论文展示的语言定义方式。最终西语论文前，需要决定是否把 `.m2b` 扩展到和 Ecore 完全等价，或者明确它是 AppMaker 的文本 DSL 对照版本。

## 6.5 生成物证据

AppMaker Ecore route 当前生成输出位于：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

应在论文中展示：

- `generation_report.html` 截图；
- `intermediate/Appmaker_blocklyspec.xmi` 片段；
- `html/Appmaker_standalone.html` 截图；
- `validation_workspace.html` 截图；
- 公开 AppMaker demo 链接；
- GitHub repository 和 update site 链接。

## 6.6 覆盖能力

| 能力 | AppMaker 中的证据 |
| --- | --- |
| Ecore route | `app_maker.ecore` |
| `.m2b` route | `app_maker.m2b` |
| containment/cardinality | `App.pages`, `Page.components`, `Container.children` |
| inheritance/abstract class | `Component`, `Action`, `TextExpression` |
| dynamic reference | `ListView.source`, `Navigate.target`, `SubmitForm.endpoint` |
| value input/output block | `Alert.message`, `ImageView.url`, `TextLiteral`, `JoinText` |
| required validation | `App.name`, `Page.title`, `Navigate.target` |
| UI metadata | label、group、order、widget、colour |
| runtime extension | `runtimeKind "appMaker"` 或 Ecore `runtime kind=appMaker` |

## 6.7 表达能力讨论

论文不应声称“元模型和 Blockly 编辑器完全等价”。更准确的表述是：

Ecore 或 `.m2b` 描述的是领域结构和生成规则。生成的 Blockly 编辑器是面向领域用户的图形化建模界面，它把一部分结构约束转化为可拖拽的 block、字段、连接和 warning。某些限制可以通过 block 连接形状直接表达，另一些限制只能通过运行时验证提示表达。反过来，Blockly workspace 还包含位置信息、折叠状态等视觉状态，这些信息并不属于领域元模型。

因此，Model2Blockly 提供的是可追踪的生成映射，而不是双向完全等价。完整的 Blockly workspace 到源 Ecore 或 `.m2b` 的同步属于未来工作。

# 第 7 章 结论与未来工作

本文实现了 Model2Blockly，一个用于从领域定义生成 Blockly 编辑器的 MDE 工具链。它支持 Ecore route 和 `.m2b` textual DSL route，并把两种输入统一转换到 EMF 中间模型 `EditorSpec`。最终生成 HTML/JavaScript Blockly 编辑器、验证脚本、toolbox、sample model、生成报告和中间 XMI。

主要贡献包括：

1. 提出 Ecore/`.m2b` 到 Blockly 编辑器的生成架构。
2. 定义并使用 EMF 中间模型 `EditorSpec` 作为模型转换目标。
3. 实现 Ecore adapter，将 Ecore 类、属性、引用、继承、基数和注解映射到 Blockly 编辑器规格。
4. 实现 `.m2b` textual DSL route，使语言设计者可以用紧凑文本定义 Blockly 语言。
5. 实现 Xtend/Java 生成器，输出可运行的 Blockly 编辑器和相关 artefacts。
6. 用 AppMaker running example、生成报告、公开 demo、update site 和验证脚本支撑评估。

本文的限制包括：

- 当前评估主要围绕 AppMaker，尚未覆盖大量外部工业元模型。
- 运行时验证以 warning 为主，不是完整 OCL 或形式化验证。
- `.m2b` route 在最终论文前应补齐生成输出证据，或明确其作为文本定义对照的范围。
- 代码生成仍以模板式输出为主，不包含完整编译器级类型分析、formatter 或多文件构建。
- 未进行用户实验，因此不能证明生成编辑器一定提升最终用户学习效果。

未来工作包括：

1. 扩展 OCL 或声明式约束支持。
2. 增强 Blockly workspace 到 domain XMI、Ecore 或 `.m2b` 的反向同步。
3. 支持从已有 domain XMI 导入并重建 Blockly workspace。
4. 增强代码生成，支持 formatter、类型检查、多文件输出和外部构建工具。
5. 增加更多外部案例，验证 Ecore route 和 `.m2b` route 的通用性。
6. 进行用户实验，比较手写 Blockly 编辑器和使用 Model2Blockly 生成编辑器的成本差异。

最终结论可以写成：

> Model2Blockly 说明，使用 Ecore、Xtext 和 EMF 中间模型可以系统化地生成 Blockly 编辑器。该工具不是一个单一 AppMaker 编辑器，而是一条从领域定义到 Blockly 编辑器的可复用 MDE 生成链。它保留了领域知识在模型层的表达，同时自动导出领域用户可以实际操作的图形化 Blockly 界面。
