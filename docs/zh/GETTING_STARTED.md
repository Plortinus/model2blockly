# 入门指南

语言：[English](../../GETTING_STARTED.md) | [Español](../es/GETTING_STARTED.md) | **中文**

这篇文档说明从安装插件到生成 Blockly 编辑器的最快路径。

## 你会做什么

1. 先打开已经生成好的示例，确认最终效果。
2. 从 `.model2blockly` 示例生成编辑器。
3. 从 `.ecore` 示例生成等价编辑器。
4. 查看生成报告，决定下一步修改什么。

## 前提条件

- Eclipse 使用 JDK 21 启动。
- 已安装 Model2Blockly Eclipse 插件，或者把这些项目导入 Eclipse workspace：

```text
io.github.plortinus.model2blockly
io.github.plortinus.model2blockly.ide
io.github.plortinus.model2blockly.ui
io.github.plortinus.model2blockly.feature
io.github.plortinus.model2blockly.updatesite
```

- 一个浏览器，用来打开生成出来的 HTML 文件。

在 Eclipse 中安装插件：

```text
Help -> Install New Software... -> Add...
https://plortinus.github.io/model2blockly/update-site/
```

如果安装列表是空的，看 [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md)。

## 截图指引

下面三张图对应最常见路径：先复制 update site URL，再打开生成好的
AppMaker 编辑器，最后查看 validation workspace。

![中文首页里的 Eclipse 安装区域](../assets/screenshots/homepage-install-zh.png)

首页安装区域给出 Eclipse `Install New Software` 需要的 p2 update site URL。

![加载示例模型后的 AppMaker 编辑器](../assets/screenshots/appmaker-editor.png)

生成后的 standalone HTML 应该包含 toolbox、workspace、预览区，以及
`Load Sample` 按钮。

![用 Blockly blocks 展示的 validation workspace](../assets/screenshots/validation-workspace.png)

`validation_workspace.html` 会把模型规则显示成 Blockly blocks，方便检查生成器
到底生成了哪些约束。

## 先试运行生成好的示例

仓库里已经带了 AppMaker 的生成结果：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker/html/AppMaker_standalone.html
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

打开后：

1. 点击 `Load Sample`。
2. 拖动几个 block。
3. 查看代码或输出面板。
4. 如果想看 validation 规则，打开 `html/validation_workspace.html`。
5. 通过 `More -> Export XMI` 导出当前 blocks 对应的领域实例模型。

也可以直接打开公开示例：

```text
https://plortinus.github.io/model2blockly/
```

## 从 `.model2blockly` 生成

如果你想手写领域模型，用这条路线。

1. 在 Eclipse 里打开或导入当前仓库。
2. 选择文件：

```text
io.github.plortinus.model2blockly/examples/app_maker.model2blockly
```

3. 右键选择 `Generate Blockly Editor`。
4. 等生成完成。
5. Eclipse 应该会打开：

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/generation_report.html
```

6. 打开生成的编辑器：

```text
io.github.plortinus.model2blockly/examples/app_maker_generated/html/AppMaker_standalone.html
```

输出目录总是在被选文件旁边，命名为：

```text
<不带扩展名的文件名>_generated
```

如果生成失败，打开 Eclipse 的 `Problems` 视图和对应的 `.model2blockly`
文件。语法错误、引用错误，以及生成器需要的结构性错误都会标到源文件上。
如果消息里有 `block[Task].assignee` 这样的路径，它指的是中间模型
`EditorSpec` 中无法安全生成的元素。

## 从 `.ecore` 生成

如果你已经有 EMF 元模型，或者想直接使用 `EPackage`、`EAnnotation`、
`EReference.eOpposite`、已有 EMF 代码等概念，用这条路线。

1. 选择文件：

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

2. 右键选择 `Generate Blockly Editor`。
3. Eclipse 应该会打开：

```text
io.github.plortinus.model2blockly/model/app_maker_generated/generation_report.html
```

4. 打开：

```text
io.github.plortinus.model2blockly/model/app_maker_generated/html/Appmaker_standalone.html
```

## 生成目录里有什么

| 文件或目录 | 作用 |
| --- | --- |
| `generation_report.html` | 每次生成后优先查看 |
| `README.md` | 输出目录内的简短说明 |
| `html/*_standalone.html` | 浏览器里直接打开的主编辑器 |
| `html/*_editor.html` | 可嵌入的编辑器页面 |
| `html/*_blocks.js` | Blockly block 定义 |
| `html/*_toolbox.js` | toolbox 定义 |
| `html/*_generators.js` | 代码生成器 |
| `html/*_validations.js` | validation 运行时代码 |
| `html/validation_workspace.html` | 用 block 展示 validation 规则 |
| `html/sample_model.json` | 示例模型 |
| `intermediate/*_blocklyspec.xmi` | 正式的 EMF/XMI 中间模型；HTML 生成前会从它读回 |

先看 `generation_report.html`。它会告诉你生成器从模型中推断出了什么，
以及写出了哪些文件。

注意：`intermediate/*_blocklyspec.xmi` 和编辑器里导出的 `*_model.xmi` 不是同一个层次。
前者是生成器内部的 `EditorSpec` 实例，后者是用户创建的领域实例模型。

## 第一个小修改

在 `.model2blockly` 中，可以先改 label 或 colour：

```model2blockly
class Button extends Component category Components colour 160 label "Button"
```

在 `.ecore` 中，可以改 class 上的注解：

```xml
<eAnnotations source="blockly">
  <details key="colour" value="160"/>
  <details key="category" value="Components"/>
  <details key="tooltip" value="Button component with click actions."/>
</eAnnotations>
```

重新生成后，再打开 `generation_report.html` 检查结果。

## 下一步阅读

| 目标 | 文档 |
| --- | --- |
| 文本 DSL 语法 | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| Ecore 注解 | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| 常见安装和生成问题 | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| 英文完整文档地图 | [`../../DOCS.md`](../../DOCS.md) |
