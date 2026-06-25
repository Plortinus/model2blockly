---
pageClass: ecore-mapping-page
---

# Ecore 到 Blockly 映射规则

本页说明 Model2Blockly 如何把 Ecore 元模型转换成 Blockly 编辑器。它适合在
设计 `.ecore` 时查阅：先看默认推断规则，再决定是否通过 Ecore 注解定制生成效果。

完整项目级输出请看 [AppMaker 案例](./RUNNING_EXAMPLE.md)。

## 转换规则

Model2Blockly 的主要映射规则：

| Ecore 元素 | 生成结果 |
| --- | --- |
| `EClass` | 一个 Blockly block type。 |
| `abstract="true"` 的 `EClass` | 不直接出现在工具箱中，但作为连接类型使用。 |
| `EAttribute` | 普通字段，例如文本框、下拉框、颜色选择器、复选框或数字输入。 |
| `containment="true"` 的 `EReference` | `input_statement` 或 `input_value`，用于嵌套子区块。 |
| 普通 `EReference` | 引用选择字段，用于选择已有区块，例如选择目标页面或数据源。 |
| `source="blockly"` 注解 | 控制分类、颜色、tooltip、输入类型和 shadow block。 |
| `source="ui"` 注解 | 控制字段标签、控件类型、分组和顺序。 |
| `source="code"` 注解 | 控制从区块导出的代码模板。 |
| `source="validation"` 注解 | 控制生成的 Blockly 校验规则。 |

## 从默认生成到定制

Ecore 模型不写注解也可以生成 Blockly 编辑器。注解的作用是覆盖默认推断，
让生成结果更接近目标用户界面。推荐的使用方式是：先用纯 Ecore 生成一次，
确认结构、字段和引用关系正确，再按需要补充注解。

### 没有注解时

| Ecore 写法 | 默认生成结果 |
| --- | --- |
| 具体 `EClass` | 一个可拖拽的 Blockly 区块。 |
| 抽象 `EClass` 或接口 | 不进入工具箱，用作连接类型或父类型。 |
| `EAttribute:EString` | 文本输入字段。 |
| `EAttribute:EBoolean` | 复选框字段。 |
| 数值类型属性 | 数字输入字段。 |
| `EEnum` 属性 | 下拉选择字段。 |
| containment `EReference` | statement 输入，用于嵌套子区块。 |
| 非 containment `EReference` | 动态引用下拉字段，用于选择已有对象。 |
| `lowerBound >= 1` | 必填或最少数量校验。 |
| 没有任何 `category` 注解 | 根据继承结构自动生成工具箱分类。 |

### 需要改变效果时

| 想要的 Blockly 效果 | 加在哪里 | 常用 key |
| --- | --- | --- |
| 放到指定工具箱分类 | `EClass` 的 `source="blockly"` | `category`，支持 `UI/Inputs` 这类嵌套路径。 |
| 改区块颜色、提示和帮助链接 | `EClass` 的 `source="blockly"` | `colour`、`tooltip`、`helpUrl`。 |
| 改区块显示文字或字段顺序 | `EClass` 的 `source="blockly"` | `message0`、`inputsInline`。 |
| 把普通属性改成颜色、角度、图片或只读标签 | `EAttribute` 的 `source="blockly"` | `type=field_colour`、`field_angle`、`field_image`、`field_label`。 |
| 限制数字字段范围 | `EAttribute` 的 `source="blockly"` | `type=field_number`、`min`、`max`。 |
| 改字段标签、提示、占位符或显示顺序 | `EAttribute` 或 `EReference` 的 `source="ui"` | `label`、`help`、`placeholder`、`order`、`readonly`、`hidden`。 |
| 把 containment 改成表达式插口 | `EReference` 的 `source="blockly"` | `type=input_value`、`check`、`shadow`。 |
| 调整引用下拉框显示文本 | 非 containment `EReference` 的 `source="ui"` | `referenceLabelField`。 |
| 增加语义校验 | `EClass` 的 `source="validation"` | `mustFollow`、`expression`、`condition`、`ocl`、`message`。 |
| 增加代码导出 | `EPackage` 或 `EClass` 的 `source="code"` | `language`、`fileExtension`、`template`、`codeTemplate`。 |
| 调整 Blockly 工作区 | `EPackage` 的 `source="blockly"` | `workspace.renderer`、`workspace.grid.*`、`workspace.zoom.*`、`workspace.toolboxType`。 |

最小定制通常只需要给对应的 Ecore 元素补一个 `eAnnotations`：

```xml
<eClassifiers xsi:type="ecore:EClass" name="Task">
  <eAnnotations source="blockly">
    <details key="category" value="Work/Tasks"/>
    <details key="colour" value="210"/>
    <details key="tooltip" value="Create a task"/>
  </eAnnotations>

  <eStructuralFeatures xsi:type="ecore:EAttribute" name="title"
      eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString">
    <eAnnotations source="ui">
      <details key="label" value="Title"/>
      <details key="placeholder" value="Task title"/>
    </eAnnotations>
  </eStructuralFeatures>
</eClassifiers>
```

## 注解 key 速查

### `source="blockly"` on `EPackage`

| key | 含义 |
| --- | --- |
| `workspace.toolboxType` | toolbox 类型。 |
| `workspace.renderer` | 传给 `Blockly.inject` 的 renderer。 |
| `workspace.zoom.controls` | 缩放控件。 |
| `workspace.zoom.maxScale` | 最大缩放。 |
| `workspace.grid.spacing` | 网格间距。 |
| `workspace.grid.snap` | 网格吸附。 |

### `source="blockly"` on `EClass`

| key | 含义 |
| --- | --- |
| `message0` | 显式 Blockly message。 |
| `colour` | 数字颜色。 |
| `category` | toolbox 分类；用 `/` 表示嵌套分类。 |
| `output` | 标记为 output block；可为 `true` 或类型名。 |
| `inputsInline` | `true` 或 `false`。 |
| `tooltip` | tooltip。 |
| `helpUrl` | 帮助链接。 |

### `source="blockly"` on `EAttribute`

| key | 含义 |
| --- | --- |
| `type` | `field_colour`、`field_angle`、`field_image`、`field_label`、`field_input`、`field_number`、`field_checkbox`。 |
| `min` | 最小值。 |
| `max` | 最大值。 |
| `src` | 图片 URL。 |
| `width` | 图片宽度。 |
| `height` | 图片高度。 |
| `alt` | 图片替代文本。 |

### `source="blockly"` on `EReference`

| key | 含义 |
| --- | --- |
| `type=input_value` | 生成 Blockly value input。 |
| `check` | Blockly 类型检查。 |
| `shadow` | shadow block 提示。 |

### `source="ui"`

在 `EClass` 上支持 `label`。

在 `EAttribute` 和 `EReference` 上支持 `widget`、`label`、`help`、
`placeholder`、`group`、`variant`、`readonly`、`hidden`、`order`，引用还支持
`referenceLabelField`。

### `source="code"`

在 `EPackage` 上支持 `language` 和 `fileExtension`。

在 `EClass` 上支持 `template` 或 `codeTemplate`。

### `source="runtime"`

在 `EPackage` 上支持 `kind`。

### `source="validation"`

在 `EClass` 上：

| key | 含义 |
| --- | --- |
| `mustFollow` | 顺序验证，要求当前类型跟在某个前置类型后。 |
| `expression`, `condition`, `js` | 表达式验证。 |
| `message` | 默认诊断消息。 |
| `expression.<name>`, `condition.<name>`, `js.<name>` | 命名表达式验证。 |
| `message.<name>` | 命名验证消息。 |
| `ocl`, `ocl.<name>` | 可翻译的 OCL 验证。 |

支持的 OCL 子集包括简单 feature 导航、`size()`、`notEmpty()`、`isEmpty()`、
`oclIsUndefined()`、比较和布尔运算。

## Ecore 功能转换示例

每组示例都是可单独生成的 `.ecore`。左侧展示源模型，右侧展示同一模型通过
`EcoreToBlocklyMain` 生成的 Blockly HTML 预览。覆盖标签标明对应的 Ecore
语法或注解；资源按钮提供源码、standalone 编辑器和生成报告。

<!-- GENERATED_ECORE_FEATURE_GALLERY -->
