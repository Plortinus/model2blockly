# 发布检查清单

语言：[English](../../RELEASE_CHECKLIST.md) | [Español](../es/RELEASE_CHECKLIST.md) | **中文**

发布新的 Eclipse 插件和 update site 时使用这篇。完整英文清单在
[`../../RELEASE_CHECKLIST.md`](../../RELEASE_CHECKLIST.md)。

## 版本号

这些位置的版本号必须保持一致：

| 文件 | 字段 |
| --- | --- |
| `io.github.plortinus.model2blockly/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ide/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.ui/META-INF/MANIFEST.MF` | `Bundle-Version` |
| `io.github.plortinus.model2blockly.feature/feature.xml` | `feature/@version` |
| `io.github.plortinus.model2blockly.updatesite/category.xml` | `feature/@version` |
| `scripts/verify-eclipse-plugin.mjs` | `pluginVersion` |

如果版本号没变，Eclipse 在勾选 `Hide items that are already installed`
时可能看不到更新。

## 重建本地 update site

1. 用 Java 21 编译 core、IDE、UI bundles。
2. 生成带版本号的 jars：

```text
io.github.plortinus.model2blockly.updatesite/repository/plugins/
io.github.plortinus.model2blockly.updatesite/repository/features/
```

3. 重新生成 p2 metadata：

```bash
npm run rebuild:update-site
```

如果 Eclipse 不在 macOS 默认路径：

```bash
ECLIPSE=/path/to/eclipse npm run rebuild:update-site
```

## 必跑验证

```bash
npm run verify:plugin
npm run verify:docs
npm run verify:dsl-validation
npm run verify:patch
npm run smoke
```

如果 Eclipse 不在 macOS 默认路径，验证前设置插件目录和 Java 路径：

```bash
export ECLIPSE_PLUGINS=/path/to/eclipse/plugins
export JAVA_HOME=/path/to/jdk-21
```

任何命令失败都不要发布。

## GitHub Pages 发布

`.github/workflows/pages.yml` 会在 push 到 `main` 后发布站点，并把 Markdown
文档渲染成 `/docs/` 下的站内 HTML 页面。

公开 update site URL：

```text
https://plortinus.github.io/model2blockly/update-site/
```

发布完成后检查：

```text
https://plortinus.github.io/model2blockly/
https://plortinus.github.io/model2blockly/update-site/
https://plortinus.github.io/model2blockly/update-site/content.jar
https://plortinus.github.io/model2blockly/update-site/artifacts.jar
```

## 给用户的更新说明

1. 打开 `Help -> Install New Software...`。
2. 使用：

```text
https://plortinus.github.io/model2blockly/update-site/
```

3. 取消勾选 `Hide items that are already installed`。
4. 如果没有条目，取消勾选 `Group items by category`。
5. 在 `Manage...` 里删除旧 Model2Blockly 条目，重新添加并刷新。
6. 保持 `Contact all update sites during install to find required software`
   勾选。
7. 安装 Model2Blockly 并重启 Eclipse。

## 发布后冒烟检查

1. 在干净 Eclipse workspace 里准备一个 `.model2blockly` 文件。
2. 执行 `Generate Blockly Editor`。
3. 确认 `generation_report.html` 打开。
4. 确认输出里有 `intermediate/*_blocklyspec.xmi`。
5. 确认无效 DSL 会在 `Problems` 中报错，而不是显示 Java stack trace。
6. 如果测试 Ecore，从 `app_maker.ecore` 生成并确认编辑器能打开。
