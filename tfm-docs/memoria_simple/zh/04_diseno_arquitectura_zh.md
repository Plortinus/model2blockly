# 第 4 章中文审阅稿：解决方案设计与架构

这一章的作用是把前面提出的问题落到系统设计上：BlocklyDSL 不是为某一个例子手写 Blockly 积木，而是把 Blockly 编辑器看成一种“领域语言的可视化具体语法”。因此，系统应该从领域描述出发，自动生成可运行的 Blockly 编辑器。

## 4.1 架构思路

BlocklyDSL 采用模型驱动工程的思路。开发者不直接写 JavaScript 积木定义，而是先用更高层的形式描述领域：可以是 Ecore 元模型，也可以是 Xtext 定义的文本 DSL。

这一设计背后的论点是：

- MDE 的核心是把模型作为软件开发和代码生成的主要 artefact。
- DSL 通常区分抽象语法和具体语法。
- 在本项目中，Ecore 或文本 DSL 描述抽象语法，Blockly 则是面向用户的可视化具体语法。

这一章强调了三个架构原则：

1. 输入和输出分离：读取 Ecore 的逻辑不应该和生成 JavaScript 的逻辑混在一起。
2. 使用统一中间模型：无论输入来自 Ecore 还是 Xtext，都先转换成统一的编辑器规格。
3. 系统化生成行为：不仅生成块和 toolbox，也生成连接、验证、引用、导出和界面元数据。

这个论点直接回应论文开头的问题：Blockly 可以提供拖拽和积木交互，但每个领域仍然需要手写很多 JavaScript 配置。BlocklyDSL 的价值就是把这些配置提升到领域模型层面。

## 4.2 整体生成流程

论文把流程分成四步：

1. 用 Ecore 或文本 DSL 定义领域。
2. 通过适配器转换成统一中间模型。
3. 从中间模型生成 Blockly 相关文件。
4. 用户在浏览器中运行生成的编辑器。

正式 PDF 在这里加入了一张总体架构图。用文字表示就是：

```text
Ecore 元模型 ─────┐
                  ├─> 输入适配层 ─> 统一中间模型
Xtext 文本 DSL ───┘                         │
                                            v
                              Web 生成器 ─> Blockly 编辑器
                                                    │
                                                    v
                                  导出模型、代码和生成报告
```

关键点是第二步：输入适配层负责吸收不同格式的差异。转换完成后，生成器不需要知道某个块来自 Ecore 类，还是来自文本 DSL 的声明。

这就是本章最核心的设计论证：适配器层降低输入差异，中间模型稳定生成逻辑。

## 4.3 中间模型

统一的编辑器规格是系统核心。它保存生成 Blockly 编辑器所需的信息，但还没有进入具体 JavaScript 语法层。

它包含：

- 领域名称；
- 命名空间信息；
- 分类；
- 块类型；
- 验证规则；
- workspace 选项；
- 代码生成元数据。

每个块类型会记录以下信息：

- 类型名；
- 显示标签；
- 颜色；
- toolbox 分类；
- 继承关系；
- 连接类型；
- 字段；
- 结构性子块输入；
- 表达式或值输入；
- 动态引用字段。

这一节的论点是：中间模型不是随便的数据搬运结构，而是整个架构的解耦边界。因为有它，Ecore、Xtext 和 HTML Blockly 输出才能在同一个架构中协同。

## 4.4 Ecore 路线

Ecore 路线直接对应 TFM 原始要求：从元模型自动生成 Blockly 编辑器。

基本映射如下：

| Ecore 元素 | Blockly 含义 |
|---|---|
| 类 | 一个积木类型 |
| 属性 | 积木字段 |
| 包含引用 | 可嵌套子块的结构输入 |
| 非包含引用 | 动态下拉引用字段 |
| 继承关系 | Blockly 类型连接规则 |
| cardinality | 验证规则 |

论文还说明了 Ecore annotations 的作用：

- `source="blockly"`：控制 label、colour、category、tooltip、inline、output、min/max 等。
- `source="ui"`：控制生成字段使用的 widget、group、order、help 等。
- `source="code"`：控制代码导出模板。

这里的论点是：Ecore 保留领域结构，annotations 只补充可视化语法和界面呈现信息。

## 4.5 Xtext 路线

第二条输入路线是文本 DSL，由 Xtext 定义语法。

它能定义：

- domain；
- category；
- class；
- attribute；
- containment；
- reference；
- value input；
- constraint；
- workspace options；
- UI options；
- code template。

这一节强调：Xtext 路线不是替代 Ecore，而是补充 Ecore。它更适合快速写例子、测试不同领域、展示生成器的通用性。

论文中的论点是：如果同一个生成器既能处理 Ecore，又能处理文本 DSL，就说明项目不是绑定在某一个输入格式上的手写工具。

## 4.6 Blockly 编辑器生成

从统一的编辑器规格出发，系统生成经典 HTML/JavaScript 输出，包括积木定义、工具箱、导出逻辑、运行时验证和可直接打开的网页。这些产物共同组成一个可以在浏览器中运行的 Blockly 编辑器。

生成规则包括：

- 字符串、数字、布尔、颜色、角度、枚举属性生成对应 Blockly field。
- containment 生成 statement input。
- value input 生成水平连接。
- category 生成 toolbox 分类。
- validation 生成 JavaScript workspace 检查。

当前项目聚焦 HTML 输出。只要中间模型稳定，未来也可以增加新的生成目标，而不用重写输入适配器。

## 4.7 验证、引用和个性化

这一节说明生成的编辑器不只是“能拖块”，还应该帮助用户构建合理模型。

验证包括：

- cardinality 检查；
- required attribute 检查；
- required reference 检查；
- must-follow 顺序检查。

这里需要和 OCL 区分清楚。OCL 是一种完整的模型约束语言，可以写 invariant、集合量词、对象导航等复杂规则。BlocklyDSL 现在不是完整 OCL 引擎，只支持一部分可以结构化转换或简化表达的规则。论文里把支持范围写成表格：

| 规则类型 | 支持情况 |
| --- | --- |
| containment cardinality | 支持。检查某个 statement input 里连接的子块数量是否满足最小/最大数量。 |
| required field / reference | 支持。Ecore 的 `lowerBound >= 1` 或 DSL 的 `required` 会生成 warning。 |
| `must follow` | 有限支持。只检查当前块是否紧跟在指定类型块后面。 |
| numeric `min` / `max` | 部分支持。它们会变成 Blockly 数字字段的输入限制，不是通用约束表达式。 |
| 简单 declarative expression / OCL-like invariant | 部分支持。可转换为运行时 warning，但不覆盖任意 OCL。 |
| Xtext 静态检查 | 部分支持。能提前发现重复类名、错误 cardinality、无效 `required`、不合理 UI 注解等。 |
| non-containment opposite reference | HTML runtime 有限支持。两个端点都可编辑时可以同步；复杂容器语义仍有限。 |
| 任意 OCL invariant、集合量词、复杂对象导航 | 不支持完整自动转换。 |
| 全局复杂语义规则、阻止编辑的强制 validation | 不支持。当前以 warning 和导出前确认为主。 |

引用设计也很重要：

- containment 表示结构上的父子关系，所以生成 statement input；
- non-containment reference 表示指向已有元素，所以生成动态 dropdown；
- dropdown 会根据目标类型和子类型筛选候选块。

个性化信息包括：

- 颜色；
- 标签；
- 分类；
- tooltip；
- inline；
- UI widget；
- code template。

本节论点是：这些信息不改变领域本身，但会影响最终用户体验，因此应该作为可控的元数据，而不是散落在手写 JavaScript 中。

## 4.8 和目标的对应关系

最后一节用表格把架构设计和第 2 章 OE1 到 OE7 对齐：

- OE1：中间模型明确列出 Blockly 编辑器需要哪些元素。
- OE2：Ecore 输入路线实现从元模型到积木编辑器的转换。
- OE3：Xtext grammar 实现文本 DSL 路线。
- OE4：统一的编辑器规格解耦输入和输出。
- OE5：生成 HTML/JavaScript 编辑器。
- OE6：支持验证、引用、UI 注解和代码模板。
- OE7：架构分层方便测试，也方便用 AppMaker 的 DSL/Ecore 双路线验证。

## 本章的写作重点

这一章不是代码实现细节，而是“为什么这样设计”。它的核心论点可以总结为：

> BlocklyDSL 的主要贡献不是某一个具体积木，而是一个可复用的生成机制：用户用 Ecore 或 DSL 描述领域，系统把它转换成统一中间模型，再生成可运行的 Blockly 编辑器。
