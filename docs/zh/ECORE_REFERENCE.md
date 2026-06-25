# Ecore 注解参考

Ecore 路线不写注解也能工作，注解用于细化生成的 Blockly 编辑器。实现位于
`EcoreAdapter.java`。

## 零注解推断

| Ecore 元素 | 默认映射 |
| --- | --- |
| `EPackage` | 编辑器 domain 名称、nsURI、nsPrefix。 |
| 具体 `EClass` | Blockly 区块类型。 |
| 抽象 `EClass` 或接口 | 抽象类型，不输出为具体 toolbox 区块。 |
| supertype | 继承和连接类型。 |
| `EAttribute:EString` | 文本字段。 |
| `EAttribute:EInt`, `ELong`, `EShort`, `EBigInteger` | 整数字段。 |
| `EAttribute:EFloat`, `EDouble`, `EBigDecimal` | 小数字段。 |
| `EAttribute:EBoolean` | 复选框。 |
| `EEnum` 属性 | 下拉字段。 |
| containment `EReference` | statement input。 |
| 非 containment `EReference` | 动态引用字段。 |
| `lowerBound >= 1` | 必填验证。 |
| 多值 unique feature | 重复值验证。 |
| `iD` 属性 | 引用标签和 XMI 导出的身份字段。 |

## `source="blockly"` on `EPackage`

| key | 含义 |
| --- | --- |
| `workspace.toolboxType` | toolbox 类型。 |
| `workspace.renderer` | 传给 `Blockly.inject` 的 renderer。 |
| `workspace.zoom.controls` | 缩放控件。 |
| `workspace.zoom.maxScale` | 最大缩放。 |
| `workspace.grid.spacing` | 网格间距。 |
| `workspace.grid.snap` | 网格吸附。 |

## `source="blockly"` on `EClass`

| key | 含义 |
| --- | --- |
| `message0` | 显式 Blockly message。 |
| `colour` | 数字颜色。 |
| `category` | toolbox 分类；用 `/` 表示嵌套分类。 |
| `output` | 标记为 output block；可为 `true` 或类型名。 |
| `inputsInline` | `true` 或 `false`。 |
| `tooltip` | tooltip。 |
| `helpUrl` | 帮助链接。 |

## `source="blockly"` on `EAttribute`

| key | 含义 |
| --- | --- |
| `type` | `field_colour`、`field_angle`、`field_image`、`field_label`、`field_input`、`field_number`、`field_checkbox`。 |
| `min` | 最小值。 |
| `max` | 最大值。 |
| `src` | 图片 URL。 |
| `width` | 图片宽度。 |
| `height` | 图片高度。 |
| `alt` | 图片替代文本。 |

## `source="blockly"` on `EReference`

| key | 含义 |
| --- | --- |
| `type=input_value` | 生成 Blockly value input。 |
| `check` | Blockly 类型检查。 |
| `shadow` | shadow block 提示。 |

## `source="ui"`

在 `EClass` 上支持 `label`。

在 `EAttribute` 和 `EReference` 上支持 `widget`、`label`、`help`、
`placeholder`、`group`、`variant`、`readonly`、`hidden`、`order`，引用还支持
`referenceLabelField`。

## `source="code"`

在 `EPackage` 上支持 `language` 和 `fileExtension`。

在 `EClass` 上支持 `template` 或 `codeTemplate`。

## `source="runtime"`

在 `EPackage` 上支持 `kind`。

## `source="validation"`

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
