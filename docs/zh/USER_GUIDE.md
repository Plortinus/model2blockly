# 使用指南

本页说明安装 Model2Blockly 插件后，如何在 Eclipse 中使用它生成 Blockly
编辑器。安装步骤见[安装指南](./INSTALL.md)。

## 选择输入文件

插件支持三类输入：

| 输入 | 推荐程度 | 说明 |
| --- | --- | --- |
| `.ecore` | 推荐 | 标准 EMF 元模型，可用 Ecore 注解细化 Blockly 生成效果。 |
| `.m2b` | 推荐 | Model2Blockly 文本 DSL，适合用简洁文本定义 Blockly 语言。 |
| `.model2blockly` | 兼容 | 旧扩展名，功能等同于 `.m2b`。 |

AppMaker 案例同时提供：

- Ecore route：`io.github.plortinus.model2blockly/model/app_maker.ecore`
- DSL route：`io.github.plortinus.model2blockly/examples/app_maker.m2b`

## 生成编辑器

1. 打开 Eclipse workspace。
2. 打开包含 `.ecore` 或 `.m2b` 文件的项目。
3. 在 Project Explorer 中选中目标源文件。
4. 右键选择 `Generate Blockly Editor`。
5. 等待生成完成。

生成完成后，插件会弹出提示，并在源文件旁边创建输出目录：

```text
<源文件名>_generated
```

例如：

```text
app_maker.ecore -> app_maker_generated
app_maker.m2b -> app_maker_generated
```

## 查看结果

进入生成目录后，优先打开：

1. `generation_report.html`：查看生成报告。
2. `html/*_standalone.html`：打开浏览器中的 Blockly 编辑器。
3. `html/validation_workspace.html`：查看生成的验证规则区块。

在 standalone 编辑器中，可以点击 `Load Sample` 加载示例模型，然后编辑
Blockly 区块并导出 JSON、XML、domain XMI 或代码。

## 继续修改

如果生成结果不符合预期，回到 `.ecore` 或 `.m2b` 源文件修改模型定义，然后再次右键运行
`Generate Blockly Editor`。插件会覆盖更新同一个生成目录。

常见调整方向：

- 修改 Ecore 类、属性、引用或包含关系。
- 添加 `blockly` 注解调整区块名称、分类、颜色和字段显示。
- 添加 `validation` 注解生成验证规则。
- 添加 `code` 注解调整代码导出。
- 在 `.m2b` 中修改 `class`、`attribute`、`contains`、`reference`、`value` 或 `validation`。

注解写法见 [Ecore 到 Blockly 映射规则](./ECORE_TO_BLOCKLY_MAPPING.md)。文本 DSL 语法见
[Model2Blockly 文本 DSL](./TEXTUAL_DSL.md)。

## 其他命令

如果使用验证工作区编辑了验证规则，可以通过 `Apply Validation Blocks to Source`
把规则片段应用回源文件。普通生成流程不需要使用这个命令。

如果右键菜单中没有 `Generate Blockly Editor`，请先确认插件安装完成并重启
Eclipse，再查看[故障排查](./TROUBLESHOOTING.md)。
