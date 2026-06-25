# 故障排查

## GitHub Pages 显示不正确

GitHub 仓库应设置：

```text
Settings -> Pages -> Build and deployment -> Source -> GitHub Actions
```

workflow 会把 VitePress 发布到站点根路径：

```text
https://plortinus.github.io/model2blockly/
```

## Eclipse 无法安装插件

使用：

```text
https://plortinus.github.io/model2blockly/update-site/
```

如果列表为空，点击 `Reload`，取消 `Group items by category`，并保持依赖解析开启。

## 看不到 Generate 命令

右键命令只对单个文件显示，扩展名需要是：

- `.ecore`
- `.m2b`
- `.model2blockly`

实现类是 `GenerateBlocklyEditorHandler`。

## Ecore 生成失败

确认文件包含 `EPackage`，引用的 classifier 能解析，`colour`、`width`、
`height`、`order` 等值符合数字格式。

## HTML 看起来是旧的

当前输出写在 `html/` 下。重新生成后打开该目录里的文件，并强制刷新浏览器。

## 常用验证

```bash
npm run verify:docs
npm run verify:plugin
npm run smoke
npm run verify:domain-xmi
```
