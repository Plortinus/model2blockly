# 使用指南

推荐路线从带注解的 Ecore 元模型开始。`.m2b` 和 `.model2blockly` 的 Xtext
语法仍然存在，但它是辅助路线。

## 安装插件

完整步骤见[安装指南](./INSTALL.md)。p2 仓库 URL：

```text
https://plortinus.github.io/model2blockly/update-site/
```

插件提供：

- `.m2b` 和 `.model2blockly` 的 Xtext 编辑器；
- `Generate Blockly Editor` 命令；
- `Apply Validation Blocks to Source` 命令；
- `.ecore`、`.m2b`、`.model2blockly` 的右键菜单支持。

## 从 Ecore 生成

参考输入：

```text
io.github.plortinus.model2blockly/model/app_maker.ecore
```

在 Eclipse 中：

1. 导入插件项目。
2. 打开 `io.github.plortinus.model2blockly`。
3. 选择 `model/app_maker.ecore`。
4. 右键选择 `Generate Blockly Editor`。
5. 打开生成的 `generation_report.html`。

同一条 Ecore 路线由以下入口实现：

```text
io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/EcoreToBlocklyMain.java
```

`Generate AppMaker from Ecore.launch` 使用参数：

```text
model/app_maker.ecore
examples/generated/app_maker_ecore
```

## 生成目录

AppMaker 生成结果：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

| 路径 | 作用 |
| --- | --- |
| `generation_report.html` | Ecore 到 Blockly 的映射报告。 |
| `README.md` | 生成目录简要说明。 |
| `intermediate/Appmaker_blocklyspec.xmi` | 序列化后的 EMF `EditorSpec` 中间模型。 |
| `html/Appmaker_standalone.html` | 浏览器可直接打开的编辑器。 |
| `html/Appmaker_editor.html` | 加载生成资源的编辑器页面。 |
| `html/Appmaker_blocks.js` | Blockly 区块定义。 |
| `html/Appmaker_toolbox.js` | 工具箱和分类结构。 |
| `html/Appmaker_generators.js` | 模板驱动的代码导出。 |
| `html/Appmaker_validations.js` | 用户模型运行时验证。 |
| `html/validation_workspace.html` | 可视化验证规则工作区。 |
| `html/validation_blocks.json` | 验证区块模型数据。 |
| `html/validation_runtime.js` | 验证工作区运行时。 |
| `html/sample_model.json` | `Load Sample` 加载的示例模型。 |

## 使用生成编辑器

打开：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore/html/Appmaker_standalone.html
```

然后：

1. 点击 `Load Sample`。
2. 查看 workspace 中的区块。
3. 编辑模型。
4. 导出 JSON、XML、domain XMI 或代码。
5. 打开 `validation_workspace.html` 查看验证规则区块。

![生成的 AppMaker 编辑器](../assets/screenshots/appmaker-editor.png)

![验证工作区](../assets/screenshots/validation-workspace.png)

## 验证输出

在仓库根目录运行：

```bash
npm run smoke
npm run verify:domain-xmi
npm run verify:plugin
```

| 命令 | 验证内容 |
| --- | --- |
| `npm run smoke` | 用 Playwright 打开生成编辑器、加载示例并检查浏览器行为。 |
| `npm run verify:domain-xmi` | 用 EMF 按 `app_maker.ecore` 加载导出的 domain XMI。 |
| `npm run verify:plugin` | 检查插件元数据和生成示例。 |

## 何时使用 Xtext

只有在需要辅助文本语法时才使用 `.m2b` 或 `.model2blockly`。两条输入路线
最终都会归一化到同一个 `EditorSpec`。
