# Model2Blockly 项目概览

Model2Blockly 是一个 Eclipse 插件，用来从带注解的 `.ecore` 元模型生成 Blockly 编辑器。

Ecore 元模型会被 EMF 加载成 `EPackage`，再转换成 `EditorSpec` 中间模型，保存为 XMI、读回、验证并生成 Blockly 编辑器文件。

## 核心流程

1. 从 update site 安装插件。
2. 创建或打开 Eclipse 项目。
3. 给 `.ecore` 添加 Blockly、UI、验证和代码生成注解。
4. 执行生成动作。
5. 查看 `generation_report.html`、`intermediate/*_blocklyspec.xmi`、生成的编辑器和验证工作区 `validation_workspace.html`。

## 接下来读什么

- [快速开始](GETTING_STARTED.md)
- [AppMaker 案例](RUNNING_EXAMPLE.md)
- [Ecore 参考](ECORE_REFERENCE.md)
- [排错](TROUBLESHOOTING.md)

[英文项目概览](../../README.md) 保留了仓库和实现层面的完整细节。
