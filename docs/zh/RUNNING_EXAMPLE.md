# AppMaker 案例

AppMaker 是 Ecore-first 路线的参考案例。

## 源和输出

| 项目 | 路径 |
| --- | --- |
| 源元模型 | `io.github.plortinus.model2blockly/model/app_maker.ecore` |
| 生成输出 | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore` |
| standalone 编辑器 | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html` |
| 中间模型 | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/intermediate/Appmaker_blocklyspec.xmi` |
| 生成报告 | `io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/generation_report.html` |

公开演示：

```text
https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
```

## 重新生成

在 Eclipse 中运行：

```text
Generate AppMaker from Ecore.launch
```

它调用 `EcoreToBlocklyMain`，参数为：

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

## 检查顺序

1. `generation_report.html`
2. `intermediate/Appmaker_blocklyspec.xmi`
3. `html/Appmaker_standalone.html`
4. `html/validation_workspace.html`
5. `html/sample_model.json`

## 编辑器行为

生成编辑器支持加载示例、编辑 Blockly workspace、查看 JSON/代码、导出
JSON/XML/domain XMI、报告验证问题，以及使用动态引用字段。

## Smoke test

```bash
npm run smoke
```

该测试会用 Playwright 打开 standalone 编辑器，点击 `Load Sample`，检查验证
输出并验证 domain XMI 的形态。
