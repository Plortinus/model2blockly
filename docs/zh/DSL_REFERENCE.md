# Model2Blockly 语言参考

语言：[English](../../DSL_REFERENCE.md) | [Español](../es/DSL_REFERENCE.md) | **中文**

这篇是 `.model2blockly` 文本语法的中文导览。完整字段表、widget 表、
workspace 选项和生成细节，以英文参考为准：

- [`../../DSL_REFERENCE.md`](../../DSL_REFERENCE.md)
- 语法文件：[`../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext)
- 主示例：[`../../io.github.plortinus.model2blockly/examples/app_maker.model2blockly`](../../io.github.plortinus.model2blockly/examples/app_maker.model2blockly)

## 核心概念

`.model2blockly` 文件定义一个领域模型。生成器先把它转换成生成的 EMF
`EditorSpec` 实例，它符合 `BlocklyEditorSpec` 中间元模型；随后序列化为
`intermediate/*_blocklyspec.xmi`，再从 XMI 读回，最后生成 block 定义、toolbox、
代码生成器、validation 和 HTML 页面。

常见结构：

```text
domain <Name>
  [codeLanguage "..."]
  [codeFileExtension "..."]
  [runtimeKind "..."]

[workspace { ... }]

category ...
class ...
constraint ...
validation ...
```

## 最小示例

```model2blockly
domain TodoApp codeLanguage "javascript" codeFileExtension "js"

category Tasks label "Tasks" colour 210

class App category Tasks colour 260 label "App" {
  attribute name : string default "My Todo App" required
    widget text uiLabel "App name" group "App" order 1

  contains Task tasks [0..50]
    uiLabel "Tasks" group "Tasks" order 2
}

class Task category Tasks colour 210 label "Task" inline {
  attribute title : string default "Buy milk" required
    widget text uiLabel "Title" group "Task" order 1
}
```

这个模型会生成一个 `Tasks` toolbox 分类、`App` block、`Task` block、
文本字段，以及 `App` 上的 statement input `tasks`。

## 快速映射

| DSL | 生成结果 |
| --- | --- |
| `domain` | 文件前缀、编辑器标题、领域 metadata |
| `workspace` | 传给 `Blockly.inject(...)` 的配置 |
| `category` | toolbox 分类 |
| `class` | Blockly block 定义 |
| `abstract class` | 连接类型父类，不作为普通 block 出现在 toolbox |
| `output class` | 带 `output` 连接的表达式 block |
| `attribute` | Blockly field |
| `contains` | `input_statement` |
| `reference` | workspace 中已有 block 的动态引用选择 |
| `value` | `input_value`，可带 `shadow` |
| `constraint` | 生成 validation 规则 |
| `validation` | expression 或简单 OCL validation |
| `code` | 领域代码生成模板 |

## category 和 toolbox

```model2blockly
category Pages label "Pages" colour 260
category UI label "UI" colour 180 {
  category Inputs label "Inputs" colour 190
}
```

class 可以声明 `category <Category>`。如果模型里使用 category，生成器会创建
category toolbox；如果没有 category，可能生成简单 flyout toolbox。

## class 和连接

```model2blockly
abstract class Component category Components colour 160

class Button extends Component category Components label "Button" {
  attribute labelText : string default "Button" required
}

output class TextLiteral category Values label "Text" {
  attribute value : string default "hello"
}
```

实用规则：

- 普通 `class` 是 statement block。
- `output class` 是值 block，可以放进 `value` input。
- `abstract class` 不直接显示为普通 block，但可以作为共同类型。
- `value` 的 `shadow` 必须指向 `output class`。

## features

### `attribute`

```model2blockly
attribute title : string default "Title" required
  widget text uiLabel "Title" group "Main" order 1
```

常见类型：

| 类型 | Blockly field |
| --- | --- |
| `string` | 文本 |
| `int`, `double`, `float` | 数字 |
| `boolean` | checkbox |
| `enum { ... }` | dropdown |

### `contains`

```model2blockly
contains Component children [0..*]
```

生成 `input_statement`。上下界会变成数量 validation。

### `reference`

```model2blockly
reference DataSource source required
  widget reference-select referenceLabelField name
```

生成动态引用选择器，列出 workspace 里已经存在并且类型兼容的 block。

### `value`

```model2blockly
value Expression condition shadow BooleanLiteral
```

生成 `input_value`。目标类型会变成 Blockly 的 `check`。

## validation

validation 的来源包括：

- `required`
- `[lower..upper]` 上下界
- `constraint ... must follow ...`
- `validation ... : expression "..."`
- `validation ... : ocl "..."`

示例：

```model2blockly
validation titleRequired on Task : expression "notEmpty(title)" errorMessage "Title is required."
```

OCL 只支持一个小子集：`self.<feature>`、`size()`、`notEmpty()`、
`isEmpty()`、`oclIsUndefined()`、比较，以及 `and`/`or`/`not`。
如果使用 `forAll`、`exists`、`select`、`oclIsTypeOf` 等高级 OCL，
工具会在生成前报错，而不是静默忽略。

## 代码模板

```model2blockly
class Button code "button(\"{{labelText}}\");" {
  attribute labelText : string default "Button"
}
```

常用占位符：

| 占位符 | 含义 |
| --- | --- |
| `{{fieldName}}` | attribute 或 reference 的值 |
| `{{statements:inputName}}` | statement input 子 block 生成的代码 |
| `{{value:inputName}}` | value input 生成的代码 |

## 相关官方文档

Model2Blockly 会隐藏一部分 Blockly JSON，但生成结果本质上仍是 Blockly 应用。
需要精确行为时看官方文档：

- Blockly block JSON: <https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/>
- Blockly fields: <https://docs.blockly.com/guides/create-custom-blocks/fields/overview/>
- Connection checks: <https://docs.blockly.com/guides/create-custom-blocks/inputs/connection-checks/>
- Toolboxes: <https://docs.blockly.com/guides/configure/toolboxes/category/>
- Workspace config: <https://docs.blockly.com/guides/configure/configuration_struct/>
- Code generation: <https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/>
