# 第 3 章：相关工作与技术（中文审阅稿）

## 本章定位

这一章的目的不是泛泛介绍所有技术，而是说明本 TFM 为什么选择这些技术，以及它们如何共同支撑“从元模型生成 Blockly 块式 DSL 编辑器”这个目标。

本章分成 6 部分：

1. 基于积木的编程；
2. Blockly；
3. 领域专用语言与模型驱动工程；
4. EMF/Ecore；
5. Xtext；
6. 技术之间的关系。

## 1. 基于积木的编程

本节说明块式编程为什么适合作为领域语言的可视化语法。

主要观点：

- 块式编程用可拖拽、可连接的视觉部件替代部分文本输入；
- 这种形式可以避免许多语法错误；
- 用户可以通过积木形状、颜色、标签、分类来理解可用结构；
- 块式环境已经从教育扩展到机器人、数据科学、移动开发等场景。

支撑文献：

- Lin & Weintrop, 2021, ScienceDirect：综述块式编程环境的生态和设计特征。
- Weintrop & Wilensky, 2018, ScienceDirect：说明编程界面的 modality 会影响新手编程实践。

和本项目的关系：

本项目把“块式编程”看成一种 **visual concrete syntax**，也就是领域专用语言的一种可视化具体语法。用户不用看到 JavaScript 或 Ecore 内部结构，而是通过代表领域概念的积木来构建程序或模型。

## 2. Blockly

本节说明 Blockly 是项目使用的底层块式编辑器库。

主要观点：

- Blockly 是一个开源 Web 库；
- 它负责积木渲染、拖拽、拼接、workspace、toolbox、序列化等基础设施；
- 但开发者仍然要为每个领域定义 blocks、fields、connections、toolbox、generators 等；
- 所以 Blockly 解决了“可视化交互基础设施”，但没有解决“每个领域都要手写配置和 JS 代码”的问题。

支撑来源：

- Blockly 官方文档。

和本项目的关系：

Blockly 在本项目中不是研究对象本身，而是生成目标。项目真正解决的是：

> 如何从 Ecore 或 DSL 自动生成 Blockly 所需的 JavaScript/HTML 代码。

也就是说，Blockly 是运行平台，BlocklyDSL 是生成环境。

## 3. 领域专用语言与模型驱动工程

本节解释为什么可以用 MDE/DSML 方法解决 Blockly 编辑器重复开发问题。

主要观点：

- DSL 用领域概念表达问题，比通用语言更贴近用户任务；
- DSML 通常区分 abstract syntax 和 concrete syntax；
- abstract syntax 可以由 metamodel 描述；
- concrete syntax 可以是文本，也可以是图形或块式语法；
- MDE 把模型作为核心工件，用于规格说明、设计、分析、转换和代码生成。

支撑文献：

- Sal et al., 2024, ScienceDirect：讲 DSL、metamodel、abstract syntax、concrete syntax、Ecore、Xtext 的关系。
- Jiménez-Pastor et al., 2017, ScienceDirect：说明 MDE 以模型为中心，支持规格说明、设计、测试和代码生成。
- Akdur et al., 2018, ScienceDirect：工业调查显示 MDE 的动机包括节省成本、缩短开发时间、复用和质量改进。

和本项目的关系：

本项目的核心就是把块式语法作为 DSML 的一种 concrete syntax：

```text
abstract syntax: Ecore metamodel / Xtext model
concrete syntax: Blockly blocks
generator: BlocklyDSL
```

因此论文要强调：这不是单纯做网页，也不是单纯做 Blockly demo，而是把 DSML/MDE 方法应用到块式语言编辑器生成。

## 4. EMF/Ecore

本节说明为什么 Ecore 是项目的核心输入。

主要观点：

- EMF 是 Eclipse 的建模框架；
- Ecore 是 EMF 的核心元模型；
- Ecore 可以表示 package、class、attribute、reference、datatype、inheritance、cardinality；
- 这些元素可以直接映射到 Blockly 编辑器结构。

映射关系：

| Ecore 元素 | Blockly 生成结果 |
|---|---|
| EClass | block type |
| EAttribute | field |
| containment EReference | statement input / structural connection |
| non-containment EReference | reference dropdown |
| lowerBound / upperBound | required / cardinality validation |
| EAnnotation | label、colour、category、tooltip 等具体语法信息 |

支撑来源：

- EMF 官方文档。

和本项目的关系：

Ecore 路径直接对应原始 TFM oferta：

> 从 meta-model，必要时加 annotations，自动生成使用 Blockly 的代码。

这也是论文里最重要的一条技术主线。

## 5. Xtext

本节说明为什么项目还需要文本 DSL。

主要观点：

- Xtext 是 Eclipse 的文本 DSL 框架；
- 从 grammar 可以生成 parser、EMF-based AST、Eclipse editor 支持；
- 文本 DSL 让用户不用直接编辑 Ecore，也能定义领域；
- 它是 Ecore 路径的补充，而不是替代。

支撑来源：

- Xtext 官方文档；
- Bettini, 2016, ScienceDirect：Xtext 从 grammar 生成 parser、EMF AST 和 Eclipse 编辑器功能。

和本项目的关系：

文本 DSL 让示例和案例更容易编写：

```model2blockly
domain AppMaker
class App { contains Screen screens [1..*] }
class Screen { attribute title : string }
```

然后系统把它转换成同一个中间模型，再复用 Blockly 生成器。

## 6. 技术之间的关系

本节给出整体技术链：

```text
Ecore / Xtext DSL
        ↓
统一的编辑器规格
        ↓
Blockly 生成器
        ↓
HTML + JavaScript Blockly editor
```

各技术角色：

| 技术 | 角色 |
|---|---|
| Blockly | 可视化积木编辑器运行平台 |
| EMF/Ecore | 元模型和领域结构定义 |
| Xtext | 文本 DSL 和编辑器支持 |
| Java/Xtend | 适配与生成逻辑的实现 |
| HTML/JavaScript | 生成编辑器的运行形式 |

本节最后强调：

项目价值不是“使用了 Blockly”，而是把 Blockly 和 MDE/DSML 结合起来，让不同领域都可以从抽象定义自动获得块式编辑器。

## 本章论点与支撑关系

| 论点 | 支撑来源 |
|---|---|
| 块式编程是常见的新手编程入口，并已扩展到多种应用领域 | Lin & Weintrop, 2021, ScienceDirect |
| 编程界面的 modality 会影响用户编程实践 | Weintrop & Wilensky, 2018, ScienceDirect |
| Blockly 提供块式编辑器基础设施，但领域配置仍由开发者定义 | Blockly 官方文档 |
| DSL 可以通过 metamodel 定义 abstract syntax，并有文本或图形 concrete syntax | Sal et al., 2024, ScienceDirect |
| MDE 使用模型支持规格说明、设计、测试和代码生成 | Jiménez-Pastor et al., 2017, ScienceDirect |
| 工业中采用 MDE 的动机包括复用、质量、成本和时间 | Akdur et al., 2018, ScienceDirect |
| Ecore 是 EMF 的核心元模型，适合描述领域结构 | EMF 官方文档 |
| Xtext 可以从 grammar 生成 parser、EMF AST 和 Eclipse 编辑支持 | Xtext 官方文档; Bettini, 2016, ScienceDirect |
