# Model2Blockly 文档

语言：[English](../en/README.md) | [Español](../es/README.md) | **中文**

Model2Blockly 可以从两类模型生成 Blockly 编辑器：

- 文本 DSL：`.model2blockly`
- 带注解的 Ecore 元模型

两条路线都会先转换成生成的 EMF `EditorSpec` 实例，它符合
`BlocklyEditorSpec` 中间元模型；随后序列化为
`intermediate/*_blocklyspec.xmi`，再从这个 XMI 读回并生成 Blockly 所需的 HTML
和 JavaScript 文件。生成出来的编辑器还可以把用户搭出来的 block 导出成领域实例模型
JSON/XMI。

所以它的生成链路是模型驱动的：

```text
.model2blockly / .ecore -> EMF EditorSpec -> 中间 XMI -> Blockly HTML/JavaScript -> 领域实例 XMI
```

注意这里有两种 XMI：`intermediate/*_blocklyspec.xmi` 是生成器内部的
`EditorSpec` 实例；生成编辑器里 `Export XMI` 下载的 `*_model.xmi` 才是用户创建的
领域实例模型。

## 从哪里开始

| 目标 | 文档 |
| --- | --- |
| 生成第一个编辑器 | [`GETTING_STARTED.md`](GETTING_STARTED.md) |
| 解决安装或生成问题 | [`TROUBLESHOOTING.md`](TROUBLESHOOTING.md) |
| 查看 `.model2blockly` 语法 | [`DSL_REFERENCE.md`](DSL_REFERENCE.md) |
| 查看 Ecore 注解规则 | [`ECORE_REFERENCE.md`](ECORE_REFERENCE.md) |
| 理解中间 XMI 流水线 | [项目概览](../../README.md) |
| 发布新的 update site | [`RELEASE_CHECKLIST.md`](RELEASE_CHECKLIST.md) |

## 深入参考

中文文档优先解释使用流程和核心概念。完整字段表、注解 key 和生成映射目前集中在英文参考页：

- [项目概览](../../README.md)
- [文档地图](../../DOCS.md)
- [DSL 完整参考](../../DSL_REFERENCE.md)
- [Ecore 完整参考](../../ECORE_REFERENCE.md)

如果说明和实际行为不一致，以当前实现代码为准。

## 公开链接

- 项目站点：<https://plortinus.github.io/model2blockly/>
- Eclipse update site：<https://plortinus.github.io/model2blockly/update-site/>
- 源码仓库：<https://github.com/Plortinus/model2blockly>
