# 发布清单

## 文档

```bash
npm run build:site-docs
npm run verify:docs
```

确认存在：

- `_site/en/install.html`
- `_site/es/install.html`
- `_site/zh/install.html`
- `_site/en/architecture.html`
- `_site/es/architecture.html`
- `_site/zh/architecture.html`

## 插件和生成示例

```bash
npm run verify:plugin
npm run smoke
npm run verify:domain-xmi
npm run verify:patch
```

## Update site

插件可安装 artefact 改变时运行：

```bash
npm run rebuild:update-site
```

发布前确认：

```text
io.github.plortinus.model2blockly.updatesite/repository/content.jar
io.github.plortinus.model2blockly.updatesite/repository/artifacts.jar
io.github.plortinus.model2blockly.updatesite/category.xml
```

## GitHub Pages

workflow 应构建 VitePress 到 `_site/`，复制 AppMaker 到 `_site/app_maker_ecore/`，
复制 p2 仓库到 `_site/update-site/`，然后部署 `_site`。
