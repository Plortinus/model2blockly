# 安装 Eclipse 插件

本页说明如何从已发布的 Eclipse p2 update site 安装 Model2Blockly。这个
URL 是 Eclipse 使用的安装仓库端点，不是另一套文档站点。

## Update Site URL

在 Eclipse 中使用：

```text
https://plortinus.github.io/model2blockly/update-site/
```

该端点包含 p2 元数据：

- `content.jar`
- `artifacts.jar`
- `category.xml`

Eclipse 会直接读取这些文件来解析可安装 feature。

## 要求

| 要求 | 原因 |
| --- | --- |
| 支持 JavaSE-21 的 Eclipse | 插件 bundle 声明 JavaSE-21。 |
| Eclipse 使用 JDK 21 运行 | launch 配置和插件元数据面向 Java 21。 |
| 开启依赖解析 | Eclipse 需要从配置的 update site 解析 Xtext 和 EMF 依赖。 |

安装向导中建议保留 `Contact all update sites during install to find required
software`。

## 安装步骤

1. 打开 Eclipse。
2. 选择 `Help` -> `Install New Software...`。
3. 点击 `Add...`。
4. 名称可填 `Model2Blockly`。
5. 粘贴 update-site URL：

   ```text
   https://plortinus.github.io/model2blockly/update-site/
   ```

6. 选择 Model2Blockly feature。
7. 完成安装向导。
8. 按提示重启 Eclipse。

安装后，在 workspace 中选中 `.ecore`、`.m2b` 或 `.model2blockly` 文件，
右键运行 `Generate Blockly Editor`。

## 如果列表为空

如果安装窗口没有可选项：

1. 点击 `Reload`。
2. 取消 `Group items by category`。
3. 确认 URL 以 `/update-site/` 结尾。
4. 保持依赖解析开启。
5. 在 `Manage...` 中删除旧的 Model2Blockly 站点，重新添加并刷新。

浏览器不需要显示目录列表。Eclipse 会直接读取 `content.jar` 和
`artifacts.jar`。

## 更新已有安装

如果已经安装过 Model2Blockly，但看不到新版本：

1. 在 `Install New Software...` 中取消 `Hide items that are already installed`。
2. 打开 `Manage...`，删除旧的 Model2Blockly 站点。
3. 重新添加 update-site URL。
4. 点击 `Reload`。
5. 安装可见 feature，或使用 `Help` -> `Check for Updates`。

更新后重启 Eclipse。

## 验证安装

先用仓库内的 AppMaker Ecore：

1. 导入 Model2Blockly 项目，或打开包含 `.ecore` 的 workspace。
2. 选择 `io.github.plortinus.model2blockly/model/app_maker.ecore`。
3. 右键选择 `Generate Blockly Editor`。
4. 打开 `generation_report.html`。
5. 打开 `html/Appmaker_standalone.html` 并点击 `Load Sample`。

期望生成目录：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

## 相关页面

- [使用指南](./USER_GUIDE.md)
- [故障排查](./TROUBLESHOOTING.md)
- [发布清单](./RELEASE_CHECKLIST.md)
