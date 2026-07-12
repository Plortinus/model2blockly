# 第 6 章：示例与评估

本章评估 BlocklyDSL 的技术可行性。评估重点不是用户学习效果，也不是最终低代码平台的商业完整性，而是检查：能否从领域描述自动生成可运行的 Blockly 编辑器，能否支持两条输入路线，生成物是否可验证。

## 6.1 评估策略

评估使用三类证据：

1. AppMaker running example，分别用文本 DSL 和 Ecore 注解描述；
2. 对生成 HTML/JavaScript、XMI、报告和示例模型的检查；
3. 对中间模型、Ecore adapter、DSL adapter 和 HTML/JavaScript generator 的自动化测试。

评估流程可以概括为：

```text
AppMaker（文本 DSL） ─┐
                     ├─> 同一条适配/生成链 ─> Blockly HTML 编辑器 ─> 技术证据
AppMaker（Ecore）────┘                         报告、XMI、JS 检查、JUnit
```

这个设计回答三个问题：

- DSL 路线和 Ecore 路线是否能使用同一个架构；
- 中间模型是否能表达生成 Blockly 编辑器需要的信息；
- 生成出的编辑器、验证规则和示例模型是否可以重复验证。

## 6.2 AppMaker 示例

AppMaker 是一个简化的低代码应用构建领域。它不是生成最终可部署的移动 App，而是生成一个 Blockly 建模编辑器，让用户用积木描述应用模型。

AppMaker 包含：

| 概念 | 作用 |
|---|---|
| `App` | 应用根节点，包含数据源和页面 |
| `DataSource` | HTTP 数据源，可被列表和提交动作引用 |
| `Page` | 页面，包含组件和进入页面时的动作 |
| `Component` | 抽象组件父类 |
| `Button` | 按钮组件，包含 `onClick` 动作 |
| `TextInput` | 文本输入组件，包含 `inputType` 和 `mandatory` |
| `ListView` | 绑定数据源的列表组件 |
| `ImageView` | 通过表达式提供 URL 的图片组件 |
| `Card` | 可以嵌套其他组件的容器 |
| `Action` | 抽象动作父类 |
| `Navigate` | 页面导航动作 |
| `Alert` | 弹窗动作 |
| `SubmitForm` | 提交表单到数据源 |
| `SetInputValue` | 设置输入框的值 |
| `TextExpression` | 抽象文本表达式 |
| `TextLiteral`, `InputValue`, `DataField`, `JoinText` | 具体表达式积木 |

## 6.3 两条输入路线

文本 DSL 路线使用：

```text
examples/app_maker.m2b
```

Ecore 路线使用：

```text
model/app_maker.ecore
```

两条路线都转换到同一个中间 EMF 模型 `BlocklyEditorSpec`，然后生成：

- Blockly block definitions；
- toolbox；
- generator；
- validation rules；
- standalone HTML editor；
- sample model；
- intermediate XMI；
- generation report。

## 6.4 覆盖能力

| 能力 | AppMaker 中的证据 |
|---|---|
| Ecore 路线 | `model/app_maker.ecore` 带 `blockly`、`ui`、`code`、`runtime` 注解 |
| DSL 路线 | `examples/app_maker.m2b` |
| containment/cardinality | `App.dataSources`、`App.pages`、`Page.components` |
| inheritance/abstract class | `Component`、`Action`、`TextExpression` |
| required fields | `App.name`、`Page.title`、`TextInput.name` 等 |
| required references | `ListView.source`、`Navigate.target`、`SubmitForm.endpoint` |
| dynamic references | 引用字段生成为动态下拉列表 |
| value input/output block | `Alert.message`、`ImageView.url`、`SetInputValue.newValue` |
| shadow blocks | 表达式槽默认使用 `TextLiteral` |
| simple order validation | `Navigate must follow Alert` |
| enum/boolean fields | `theme`、`layout`、`inputType`、`mandatory` |
| code templates | 生成 JavaScript 风格的模型表示 |

## 6.5 required 的说明

AppMaker 中要区分两类 required。

第一类是模型编辑器层面的 required。例如：

- `Page.title` 不能为空；
- `ListView.source` 必须选择一个 `DataSource`；
- `Navigate.target` 必须选择一个 `Page`。

这些已经完整支持。DSL 里的 `required` 或 Ecore 里的 `lowerBound="1"` 会进入中间模型，并生成 `REQUIRED` validation。

第二类是应用运行时层面的必填。例如 `TextInput.mandatory=true` 表示未来运行 App 时该输入框应该必填。当前系统支持把它建模为 checkbox 并导出到 JSON/代码模板，但不会生成完整 App runtime 去执行表单提交前校验。因此这部分应写作“可建模、可导出，运行时执行属于 future work”。

## 6.6 自动化验证

当前项目包含 142 个 JUnit 测试：

| 层 | 数量 | 内容 |
|---|---:|---|
| 中间模型 | 26 | 默认值、连接类型、字段类型 |
| Ecore adapter | 39 | Ecore 到中间模型的映射 |
| DSL adapter | 5 | DSL 到中间模型的映射 |
| HTML/JavaScript generator | 60 | blocks、toolbox、validation、reference、export |

此外，项目提供：

```bash
npm run smoke
npm run verify:plugin
```

`smoke` 会打开生成的两个 AppMaker 编辑器并加载 sample model。`verify:plugin` 会检查 Eclipse plugin、feature/update site 元数据、生成物、HTML-only 清理状态和 XML/XMI 可解析性。

## 6.7 评估结论

AppMaker 证明当前系统不是手写一个固定 Blockly 页面，而是能够从两个高层输入路线生成同一类领域编辑器。它覆盖了页面、组件、数据源、引用、动作、表达式、required validation、cardinality 和代码模板。

需要承认的限制是：AppMaker 仍然是一个建模编辑器示例，不是完整 low-code/no-code runtime。生成器能导出模型和代码片段，但不生成可部署的最终应用。这个边界应在论文和答辩中明确说明。
