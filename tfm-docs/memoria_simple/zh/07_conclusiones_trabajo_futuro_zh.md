# 第 7 章中文审阅稿：结论与未来工作

这一章是论文的收束。它不再展开新的实现细节，而是回答三个问题：

1. 本 TFM 要解决什么问题；
2. 当前工作完成到了什么程度；
3. 后续还可以如何扩展。

## 7.1 方法可行性

论文的总目标是：

> 设计并实现一个环境，用于创建具有基于积木语法的领域特定语言。

当前实现可以支撑这个目标。BlocklyDSL 支持两条输入路线：

- Ecore metamodel；
- 文本 DSL。

两条路线都会转换到统一中间模型，再生成：

- Blockly HTML/JavaScript editor；
- HTML 输出。

这一节的核心结论是：项目不是只写了一个具体的积木语言，而是实现了一条从领域描述到 Blockly editor 的生成链。

## 7.2 主要贡献

论文总结了六个贡献。

### 贡献 1：连接 Ecore、Xtext 和 Blockly 的生成架构

架构不是 Ecore 直接生成 JS，也不是 DSL 直接生成 JS，而是：

```text
Ecore / 文本 DSL
        ↓
输入适配层
        ↓
统一中间模型
        ↓
生成层
        ↓
Blockly / HTML
```

这个设计把输入格式和输出技术解耦。

### 贡献 2：统一中间模型

中间模型保存：

- blocks；
- fields；
- categories；
- 结构性输入；
- 值输入；
- references；
- validations；
- workspace options；
- code metadata。

它定义了适配器和生成器之间的稳定边界。

### 贡献 3：Ecore 路线

Ecore 路线直接回应 TFM 原始要求：从 metamodel 生成 Blockly 代码。

它能处理：

- 类；
- 属性；
- 包含引用和非包含引用；
- 继承；
- cardinality；
- 注解。

AppMaker 的 Ecore 版本用于展示：普通 Ecore 结构可以作为 metamodel 输入；在需要更好视觉语法、UI 元数据、代码模板和验证规则时，可以通过 annotation 补充信息。

### 贡献 4：文本 DSL

文本 DSL 让定义领域更紧凑，不需要直接编辑 Ecore 文件。

它也支持 Xtext 静态验证，可以提前发现一部分定义错误。

### 贡献 5：完整编辑器生成

生成内容不只是 block 外观，还包括：

- 块定义；
- 工具箱；
- 导出逻辑；
- 验证逻辑；
- HTML 页面；
- 保存、加载和导出；
- 示例模型；
- 生成报告；
- 代码模板；
- HTML 输出。

### 贡献 6：AppMaker 双路线和测试

论文使用 AppMaker 作为 running example，并分别提供：

- 文本 DSL 版本；
- Ecore 注解版本。

同时，项目包含 144 个 JUnit 测试，并对生成 JS 做语法检查。这些证据说明项目不是手写单一 Blockly 页面，而是通过两条输入路线复用同一条 MDE 生成链。

## 7.3 按目标总结

第 2 章的目标可以这样对应：

- OE1：已经分析并建模 Blockly editor 需要的元素。
- OE2：已经实现 Ecore 路线。
- OE3：已经实现 Xtext 文本 DSL 路线。
- OE4：已经实现统一中间模型。
- OE5：已经生成 HTML/JS Blockly editor。
- OE6：已经支持验证、引用、个性化、代码模板、报告和示例模型。
- OE7：已经用 AppMaker 双路线和测试进行验证。

这一节的结论是：项目满足 TFM 核心目标，但它仍应被表述为研究型功能原型，而不是完整商业工具。

## 7.4 限制

论文需要诚实说明限制。

### 限制 1：评估案例主要是项目内设计的

案例覆盖多个领域，但还不是工业级大规模外部模型评估。因此，论文可以说“在所选案例中证明可行”，不应说“已经覆盖所有元模型”。

### 限制 2：验证能力有限

当前主要支持：

- containment cardinality；
- required field；
- required reference；
- unique；
- `must follow`；
- 简单 declarative expression；
- 一部分简单 OCL-like invariant。

但还没有完整支持：

- 任意 OCL invariant；
- 集合量词；
- 复杂对象导航；
- 多个不相邻元素之间的全局语义条件；
- 深层语义分析；
- 完全阻止编辑的强制 validation。

当前 validation 主要表现为 warning 和导出前确认。

### 限制 3：引用支持仍有边界

系统支持动态 dropdown reference。HTML runtime 还支持一部分 non-containment opposite reference 同步。

但复杂情况仍有限，例如：

- containment opposite 的复杂语义；
- HTML runtime 中等价的 opposite 同步；
- 删除目标元素后的更高级 dangling reference 处理；
- 图形化展示复杂引用关系。

### 限制 4：代码生成还比较基础

当前代码生成是模板式的，适合证明从 blocks 到文本输出的路径，但还不是完整编译器。

还没有：

- 高级类型分析；
- formatter；
- imports 管理；
- multi-file generation；
- 外部构建工具集成。

### 限制 5：没有用户实验

论文没有证明“用户使用后学习效果更好”。这需要单独设计用户研究，包括参与者、任务、指标和统计分析。

## 7.5 未来工作

未来工作可以围绕这些限制展开。

### 方向 1：增强验证

可以加入更强的规则语言或约束机制，支持比 cardinality、required 和 must-follow 更复杂的语义条件。

### 方向 2：增强引用

可以继续扩展：

- HTML runtime 中的 opposite reference 同步；
- dangling reference 检测；
- containment opposite 的处理；
- 更清晰的引用可视化。

### 方向 3：导入已有模型

未来可以从 XMI 导入已有模型，并重建 Blockly workspace。

这会让工具更接近完整 MDE 工作流。

### 方向 4：增强代码生成

可以增加：

- 模板验证；
- 类型检查；
- 格式化；
- 多文件生成；
- 特定目标语言支持。

### 方向 5：增强 HTML UI

HTML 输出可以继续扩展：

- inspector 中创建、删除或重连 blocks；
- domain themes；
- custom components；
- 更适合最终用户的界面模式。

### 方向 6：用户实验

可以比较：

- 手写 Blockly editor 需要多少工作；
- 用 BlocklyDSL 生成需要多少工作；
- 非技术用户是否更容易使用生成的 editor。

## 本章核心结论

本章最终结论是：

> BlocklyDSL 证明可以用 Ecore、Xtext 和 Blockly 结合，生成具有积木语法的领域特定语言编辑器。它不是一个单独 editor，而是一条可复用的生成链。

答辩时应强调：当前工作已经足以证明方法可行，同时保留了验证、引用、XMI import、代码生成和用户实验等未来扩展空间。
