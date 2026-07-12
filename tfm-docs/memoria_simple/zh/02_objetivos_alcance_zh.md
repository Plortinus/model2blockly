# 第 2 章：目标与范围（中文审阅稿）

## 本章定位

这一章把原始 TFM oferta 转换成论文中的正式目标体系。它回答五个问题：

1. 本 TFM 的总目标是什么？
2. 为了完成总目标，需要实现哪些子目标？
3. 哪些功能属于论文主范围？
4. 哪些内容不属于核心范围，应该作为 future work？
5. 用什么标准判断这个项目是否满足 oferta？

## 总目标

本 TFM 的总目标是：

**设计并实现一个用于创建“带块式语法的领域专用语言”的环境。**

也就是说，用户不应该为每个领域手写 Blockly 底层 JavaScript，而是应该能够：

- 用 Ecore 元模型描述领域；
- 或者用 Xtext 实现的文本 DSL 描述领域；
- 然后由系统自动生成可运行的 Blockly 编辑器。

这个目标由两类资料支撑：

- Blockly 官方文档说明 Blockly 提供块式编辑器基础设施，但具体积木、字段、连接和生成逻辑需要开发者定义。
- ScienceDirect 上的 MDE 文献说明，模型驱动工程可以用模型作为核心工件，支持规格说明、设计、测试和代码生成。

因此，本 TFM 的核心就是把这两件事连接起来：**用建模语言工程技术自动生成 Blockly 编辑器**。

## 子目标

### OE1：分析块式语言编辑器需要哪些结构

需要明确一个 Blockly 编辑器通常包含什么：

- 积木类型；
- 字段；
- 工具箱分类；
- 连接规则；
- 校验规则；
- 序列化；
- 必要时的代码生成。

这个目标的支撑是 ScienceDirect 上 Lin 与 Weintrop 对块式编程环境的综述：块式环境之间的差异，主要体现在这些设计结构上。

### OE2：实现基于 Ecore 元模型的生成路径

这是最贴合原始 oferta 的目标。

Ecore 元模型应该能够作为生成输入：

- EClass 映射成 block type；
- EAttribute 映射成 Blockly field；
- containment EReference 映射成结构连接；
- non-containment EReference 映射成引用；
- cardinality 映射成 validation；
- EAnnotation 可以补充颜色、标签、分类、tooltip 等块式语法信息。

这正对应 oferta 中的：

> a partir de un meta-modelo (quizá anotado con detalles adicionales sobre la sintaxis basada en bloques), se generará automáticamente código que usa la librería Blockly

### OE3：提供一个 Xtext 文本 DSL 路径

除了直接写 Ecore，还提供文本 DSL，让用户更方便地定义领域语言。

Xtext 的作用是：

- 定义语法；
- 生成 parser；
- 生成基于 EMF 的抽象模型；
- 提供编辑器支持；
- 让 DSL 文件可以进入同一套生成架构。

这个目标不是替代 Ecore，而是给用户一个更容易写的输入方式。

### OE4：设计独立于输入来源的中间模型

为了避免生成器只绑定 Ecore 或只绑定 DSL，需要设计一个中间模型。

本项目中的中间模型是统一的编辑器规格。

它统一承载：

- blocks；
- fields；
- categories；
- references；
- value inputs；
- validations；
- UI metadata。

这样做的好处是：

- Ecore 路径和 DSL 路径可以复用同一个生成器；
- 后续可以加入新的输入路径；
- 后续也可以加入新的输出目标，例如 HTML。

### OE5：生成可执行的 Blockly 编辑器

生成器需要输出浏览器可运行的文件：

- block definitions；
- toolbox；
- generators；
- validations；
- HTML 页面。

生成的编辑器要能：

- 打开；
- 拖拽积木；
- 创建领域模型/程序；
- 保存和加载 workspace；
- 导出 JSON/XML/XMI/code 等结果。

这部分是证明“不是只生成配置，而是真的能用”的关键。

### OE6：支持通用的校验、引用和个性化

如果只生成孤立积木，项目会太弱。

所以本项目还需要支持：

- required field；
- cardinality validation；
- must-follow 这类简单顺序规则；
- containment reference；
- non-containment reference；
- 动态 reference dropdown；
- block label、colour、category、tooltip；
- UI widget、help、group、order 等界面元数据。

这些能力证明工具不是某个 demo 的硬编码，而是可复用的领域语言生成机制。

### OE7：用 AppMaker 双路线和测试验证

为了证明项目是通用工具，而不是只适配一个例子，需要多个领域案例：

- AppMaker 的文本 DSL 版本；
- AppMaker 的 Ecore 注解版本；

同时，需要测试覆盖：

- 中间模型；
- Ecore adapter；
- DSL adapter；
- Blockly generator；
- HTML generator。

## 功能范围

论文主范围包括：

| 能力 | 是否属于主范围 |
|---|---|
| Ecore 输入 | 是 |
| 文本 DSL 输入 | 是 |
| 统一中间模型 | 是 |
| Blockly HTML/JS 生成 | 是 |
| toolbox/block/generator/validation 文件生成 | 是 |
| required/cardinality/must-follow 校验 | 是 |
| containment/non-containment reference | 是 |
| 颜色、标签、分类、tooltip、widget 等个性化 | 是 |
| 多领域案例和测试 | 是 |

## 不属于核心范围的内容

这些可以写进 future work，避免答辩时被认为是未完成核心要求：

- 通用 Import XMI to Blockly；
- 完整 OCL 或复杂语义约束；
- 双向 reference 的自动同步；
- 每个领域高度定制的最终业务 UI；
- 高级语言级代码生成和 formatter。

注意：项目已经有 JSON/XMI export 和模板式 code export，但它们是扩展能力，不是原始 oferta 的硬性核心。

## 完成标准

如果项目满足下面标准，就可以认为完成了原始 oferta 的核心目标：

1. 能用 Ecore 元模型定义至少一个领域；
2. 能从该元模型自动生成 Blockly 编辑器；
3. 能用注解补充颜色、标签、分类等块式语法信息；
4. 能用 Xtext DSL 提供更方便的领域定义路径；
5. 生成的 HTML/JavaScript 可以在浏览器运行；
6. 多个领域案例证明架构可复用；
7. 有测试或验证材料证明不是手写 demo。

按当前项目状态，这些核心标准已经基本满足。

## 本章论点与支撑关系

| 论点 | 支撑来源 |
|---|---|
| Blockly 能提供块式编辑器基础设施，但具体领域逻辑仍需开发者定义 | Blockly 官方文档 |
| MDE 可以把模型作为核心工件，用于规格说明、设计和代码生成 | Jiménez-Pastor et al., 2017, ScienceDirect |
| 块式编程环境差异体现在 blocks、modality、设计结构等方面 | Lin & Weintrop, 2021, ScienceDirect |
| Xtext 能从语法生成 parser、EMF-based AST 和 Eclipse 编辑支持 | Xtext 官方文档; Bettini, 2016, ScienceDirect |
| Ecore 元模型 + 注解生成 Blockly 是本项目核心要求 | 原始 TFM oferta |
