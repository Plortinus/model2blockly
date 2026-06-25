---
pageClass: appmaker-case-page
---

# AppMaker 案例

AppMaker 是 Model2Blockly 的 running example。它不是另一个独立系统，而是用来展示
MDE 工具链如何从模型定义生成 Blockly 编辑器。

本案例包含两条输入路线：

```text
Ecore route:
  app_maker.ecore -> EcoreAdapter -> EditorSpec XMI -> Blockly editor

DSL route:
  app_maker.m2b -> Xtext parser -> DomainModelAdapter -> EditorSpec XMI -> Blockly editor
```

两条路线的目标相同：生成一个 AppMaker Blockly 编辑器，让领域用户用区块创建
App、Page、DataSource、Component、Action 和 Expression 模型。

## 生成效果

完整 AppMaker 演示：
[Ecore route 编辑器](https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html)；
[DSL route 编辑器](https://plortinus.github.io/model2blockly/app_maker_dsl/Appmaker_standalone.html)

![生成的 AppMaker 编辑器](../assets/screenshots/appmaker-editor.png)

这个编辑器包含 AppMaker 的工具箱分类、可拖拽区块、验证按钮、示例模型加载、
JSON/XML/domain XMI 导出，以及 AppMaker Preview 运行时视图。

## 输入路线

| 路线 | 输入 | 转换 | 输出 |
| --- | --- | --- | --- |
| Ecore route | `model/app_maker.ecore` | `EcoreAdapter` | `examples/generated/app_maker_ecore` |
| DSL route | `examples/app_maker.m2b` | `DomainModelAdapter` | `examples/generated/app_maker_dsl` |

`.m2b` 是当前推荐的 Model2Blockly 文本 DSL 扩展名。`.model2blockly` 仍然兼容，
但文档和案例统一使用 `.m2b`。

## AppMaker 领域

AppMaker 用来描述低代码应用界面。核心概念包括：

| 概念 | 作用 |
| --- | --- |
| `App` | 应用根模型，包含数据源和页面。 |
| `DataSource` | 可被组件和动作引用的 HTTP 数据源。 |
| `Page` | 应用页面，包含组件和进入页面时的动作。 |
| `Component` | 页面组件的抽象父类。 |
| `Button`, `TextInput`, `ListView` | 具体 UI 组件。 |
| `Action` | 事件动作的抽象父类。 |
| `Navigate`, `Alert`, `SubmitForm` | 具体动作。 |
| `TextExpression` | 输出文本的表达式父类。 |
| `TextLiteral`, `InputValue`, `DataField`, `JoinText` | 具体表达式。 |

## Ecore Route 片段

Ecore route 使用标准 EMF `.ecore` 元模型，并通过 `blockly`、`ui`、`code`、
`runtime` 等注解补充 Blockly 生成信息。

```xml
<eClassifiers xsi:type="ecore:EClass" name="App">
  <eAnnotations source="blockly">
    <details key="colour" value="260"/>
    <details key="category" value="Pages"/>
  </eAnnotations>
  <eStructuralFeatures xsi:type="ecore:EAttribute"
      name="name" lowerBound="1"
      eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  <eStructuralFeatures xsi:type="ecore:EReference"
      name="pages" lowerBound="1" upperBound="20"
      eType="#//Page" containment="true"/>
</eClassifiers>
```

完整源文件：
[app_maker.ecore](../../io.github.plortinus.model2blockly/model/app_maker.ecore)

## DSL Route 片段

DSL route 使用 `.m2b` 文本文件描述同一个 AppMaker Blockly 语言。它更适合在论文和
文档中展示语言结构，因为类、字段、分类和连接关系都集中在一个文本语法里。

```model2blockly
domain Appmaker
runtimeKind "appMaker"

category Pages label "Pages" colour 260
category Components label "Components" colour 160

class App category Pages colour 260 label "App" {
  attribute name : string default "Task Tracker" required
  attribute theme : enum { light, dark, system } default "light"
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

完整源文件：
[app_maker.m2b](../../io.github.plortinus.model2blockly/examples/app_maker.m2b)

DSL 语法和它相对 Ecore 注解的优势见
[Model2Blockly 文本 DSL](./TEXTUAL_DSL.md)。

## 统一中间模型

无论输入来自 Ecore 还是 `.m2b`，生成器都会先得到 EditorSpec EMF 中间模型。
Ecore route 当前已提交的中间模型是：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi
```

DSL route 当前已提交的中间模型是：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_dsl/intermediate/Appmaker_blocklyspec.xmi
```

这个 XMI 是生成链路的一部分，不只是调试输出。它证明项目采用的是模型转换：

```text
source model -> EditorSpec model -> generated Blockly code
```

## EClass 到 Blockly 对照

| AppMaker 元素 | 生成 block type | 工具箱分类 | 主要生成结果 |
| --- | --- | --- | --- |
| `App` | `App` | Pages | 根区块，包含 `dataSources` 和 `pages` statement inputs。 |
| `DataSource` | `DataSource` | Data | 数据源区块，`name` 可作为引用标识。 |
| `Page` | `Page` | Pages | 页面区块，包含组件和进入页面动作。 |
| `Component` | 不进工具箱 | Components | 抽象连接类型。 |
| `TextLabel` | `TextLabel` | Components | 文本组件，使用 `TextExpression` value input。 |
| `Button` | `Button` | Components | 按钮组件，包含 `onClick` 动作列表。 |
| `TextInput` | `TextInput` | Components | 输入组件，可被表达式引用。 |
| `ListView` | `ListView` | Components | 引用 `DataSource` 并显示数据字段。 |
| `Container` | `Container` | Layout | 布局容器，包含子组件。 |
| `Form` | `Form` | Layout | 表单容器，引用提交 endpoint。 |
| `Action` | 不进工具箱 | Actions | 抽象动作连接类型。 |
| `Navigate` | `Navigate` | Actions | 引用目标页面。 |
| `Alert` | `Alert` | Actions | 使用文本表达式作为消息。 |
| `TextExpression` | 不进工具箱 | Expressions | 抽象输出类型。 |
| `TextLiteral` | `TextLiteral` | Expressions | 文本输出 block。 |
| `InputValue` | `InputValue` | Expressions | 引用输入组件并输出文本。 |
| `DataField` | `DataField` | Expressions | 输出当前数据行字段。 |
| `JoinText` | `JoinText` | Expressions | 拼接两个文本表达式。 |

## 重新生成

Ecore route：

```text
Generate AppMaker from Ecore.launch
```

输入和输出：

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

DSL route：

```text
Generate AppMaker from Model2Blockly.launch
```

输入和输出：

```text
examples/app_maker.m2b
examples/generated/app_maker_dsl
```

## 检查生成结果

建议按这个顺序检查 AppMaker 输出：

1. 打开 `generation_report.html`，查看源模型到 Blockly 的映射报告。
2. 打开 `intermediate/Appmaker_blocklyspec.xmi`，查看 EditorSpec 中间模型。
3. 打开 `html/Appmaker_standalone.html`，实际拖拽和编辑区块。
4. 打开 `html/validation_workspace.html`，查看验证规则如何被生成为 Blockly 区块。
5. 打开 `html/sample_model.json`，查看 `Load Sample` 加载的示例模型。

![验证工作区](../assets/screenshots/validation-workspace.png)
