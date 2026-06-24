# 入门指南

从安装插件到生成 Blockly 编辑器，可以按下面的步骤完成。

## 你会做什么

1. 先打开已经生成好的示例，确认最终效果。
2. 从 `.ecore` 示例生成编辑器。
3. 查看生成报告、中间 XMI 和验证工作区。
4. 可选：编辑生成出来的验证块，并把支持的修改应用回源文件。

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
AppMaker 编辑器，最后查看验证工作区。

![中文首页里的 Eclipse 安装区域](../assets/screenshots/homepage-install-zh.png)

首页安装区域给出 Eclipse `Install New Software` 需要的 p2 update site URL。

![加载示例模型后的 AppMaker 编辑器](../assets/screenshots/appmaker-editor.png)

生成后的 standalone HTML 应该包含 toolbox、workspace、预览区，以及
`Load Sample` 按钮。

![以 Blockly 块展示规则的验证工作区](../assets/screenshots/validation-workspace.png)

`validation_workspace.html` 会以 Blockly 块的形式显示模型规则，方便检查生成器
到底生成了哪些约束。

## 先试运行生成好的示例

仓库里已经带了 AppMaker 的生成结果：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

打开后：

1. 点击 `Load Sample`。
2. 拖动几个 Blockly 块。
3. 查看代码或输出面板。
4. 如果想看验证规则，打开 `html/validation_workspace.html`。
5. 通过 `More -> Export XMI` 导出当前块对应的领域实例模型。

也可以直接打开公开示例：

```text
https://plortinus.github.io/model2blockly/
```

这些仓库内置示例放在 `examples/generated/...`，方便文档和 GitHub Pages 稳定引用。
如果你自己在 Eclipse 里右键生成，输出会写到源文件旁边的 `<name>_generated/` 目录。

## 从 `.ecore` 生成

如果你已经有 EMF 元模型，或者想直接使用 `EPackage`、`EAnnotation`、
`EReference.eOpposite`、已有 EMF 代码等概念，用这条路线。

1. 在 Eclipse 里打开或导入当前仓库。
2. 选择文件：

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

3. 右键选择 `Generate Blockly Editor`。
4. Eclipse 应该会打开：

```text
io.github.plortinus.model2blockly/model/app_maker_generated/generation_report.html
```

5. 打开：

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
| `html/*_validations.js` | 验证运行时代码 |
| `html/validation_workspace.html` | 以 Blockly 块展示验证规则 |
| `html/validation_blocks.json` | 以 Blockly 风格数据保存的验证规则 |
| `html/validation_runtime.js` | 验证工作区的运行时支持 |
| `html/sample_model.json` | 示例模型 |
| `intermediate/*_blocklyspec.xmi` | 正式的 EMF/XMI 中间模型；HTML 生成前会从它读回 |

先看 `generation_report.html`。它会告诉你生成器从模型中推断出了什么，
以及写出了哪些文件。

注意：`intermediate/*_blocklyspec.xmi` 和编辑器里导出的 `*_model.xmi` 不是同一个层次。
前者是生成器内部的 `EditorSpec` 实例，后者是用户创建的领域实例模型。

如果要可视化查看规则，打开 `html/validation_workspace.html`。这个页面可以导出
`validation_blocks.edited.json`。要把支持的规则修改写回源文件，在 Eclipse 中选中原始
`.ecore` 文件，然后运行 `Apply Validation Blocks to Source`。

## 第一个小修改

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
| Ecore 注解 | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| 常见安装和生成问题 | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| 英文完整文档地图 | [`../../DOCS.md`](../../DOCS.md) |
