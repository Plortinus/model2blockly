# AppMaker 案例

AppMaker 是 Model2Blockly 的主要验证案例。它展示如何从带注解的 Ecore 元模型生成 Blockly 编辑器。

![生成的 AppMaker 编辑器](../assets/screenshots/appmaker-editor.png)

## 这个案例展示什么

- 带注解 `.ecore` 里的编辑器定义。
- 中间文件 `intermediate/*_blocklyspec.xmi`。
- 生成的 Blockly JavaScript 文件。
- 由生成编辑器导出的领域实例 XMI。
- `generation_report.html` 生成报告。
- `validation_workspace.html`，用于检查可视化验证规则。

## 建议检查的文件

| 产物 | 用途 |
| --- | --- |
| `io.github.plortinus.model2blockly/model/app_maker.ecore` | AppMaker 的 Ecore 输入 |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/` | 生成出的 Blockly 文件 |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` | 生成报告 |
| `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/validation_workspace.html` | 验证工作区 |

[英文完整案例](../../RUNNING_EXAMPLE.md) 保留了更详细的代码片段、生成产物和验证说明。
