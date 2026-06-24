# 排错指南

安装或生成结果不符合预期时，可以按症状定位问题。

## 安装列表为空

现象：

```text
There are no categorized items
```

检查：

1. 确认 update site URL 完全正确：

```text
https://plortinus.github.io/model2blockly/update-site/
```

2. 在 Eclipse `Help -> Install New Software...` 里取消勾选
   `Group items by category`。
3. 点 `Manage...`，删除旧的 Model2Blockly site，重新添加 URL 并刷新。
4. 保持 `Contact all update sites during install to find required software`
   勾选，这样 Eclipse 才能解析 EMF 依赖。
5. 如果使用本地 update site，选择这个目录：

```text
io.github.plortinus.model2blockly.updatesite/repository/
```

不要选择父目录 `io.github.plortinus.model2blockly.updatesite/`。

## Eclipse 看不到新版本

常见原因：

- 本地 p2 repository 没有重新生成。
- Eclipse 缓存了旧 metadata。
- bundle 或 feature 版本号没更新。
- `Hide items that are already installed` 隐藏了已安装版本。

检查：

1. 取消勾选 `Hide items that are already installed`。
2. 在 `Manage...` 里删除并重新添加 update site。
3. 如果用本地仓库，执行：

```bash
npm run rebuild:update-site
npm run verify:plugin
```

4. 检查这些目录里是否有预期版本的 jar：

```text
io.github.plortinus.model2blockly.updatesite/repository/features/
io.github.plortinus.model2blockly.updatesite/repository/plugins/
```

## 找不到 `Generate Blockly Editor`

检查：

1. 只选择一个文件。
2. 文件扩展名必须是 `.ecore`。
3. 使用 Project Explorer、Package Explorer，或者顶部 `Model2Blockly` 菜单。
4. 在 `Help -> About Eclipse IDE -> Installation Details` 里确认插件已安装。
5. 安装或更新后重启 Eclipse。

## 输出目录不在预期位置

Eclipse 命令会在被选文件旁边写一个同级目录：

```text
<不带扩展名的文件名>_generated
```

例子：

| 选择的文件 | 输出目录 |
| --- | --- |
| `model/app_maker.ecore` | `model/app_maker_generated/` |

如果没看到目录，手动 refresh Eclipse 项目。

## 中间 XMI 无法读回

现象：

```text
Could not load intermediate EMF model from XMI:
Expected generated EditorSpec root, got ...
```

这通常说明插件版本太旧，或者核心 bundle 没有正确打包 generated EMF 中间模型。
当前版本直接使用 `io.github.plortinus.model2blockly.intermediate.blocklyspec` 里的 generated
`BlocklySpecPackage` / `EditorSpec`，不再运行时加载 `model/blockly_editor_spec.ecore`。

修复：

1. 从 update site 或本地 p2 repository 安装当前版本。
2. 重启 Eclipse。
3. 在 `Installation Details` 里确认安装版本。
4. 如果是本地构建，重新运行 update site 构建，确认 core jar 里包含
   `io/github/plortinus/model2blockly/intermediate/blocklyspec/*`。
5. 重新对选中的 `.ecore` 文件执行生成。

## 缺少或没有更新中间 XMI

每次成功生成都应该写出：

```text
intermediate/*_blocklyspec.xmi
```

这个文件不是普通日志。生成器会把 generated EMF `EditorSpec` 写成 XMI，再从这个
XMI 读回、桥接验证，然后才生成 Blockly HTML/JavaScript。

如果输出里有 HTML 但没有这个 XMI，说明安装的插件或本地 update site 还是旧的。
重新构建并安装当前 update site 后再生成。

## `.ecore` 生成失败

检查：

1. `.ecore` 文件根对象必须是 `EPackage`。
2. 被引用的 classifier 必须能在模型或导入包里解析。
3. `derived`、`transient`、`volatile` 或不可修改的 feature 会被跳过。
4. value input 需要在 `EReference` 上加注解：

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="shadow" value="TextLiteral"/>
</eAnnotations>
```

## reference 下拉框为空

reference 字段列出的是 workspace 里已经存在的兼容 block。

检查：

1. 先创建至少一个目标类型的 block。
2. 确认引用目标类型和目标 block 类型或 connection type 兼容。
3. 增加可读的 label 字段：

```xml
<eAnnotations source="ui">
  <details key="referenceLabelField" value="name"/>
</eAnnotations>
```

## value block 不能连接

`.ecore` 里给 class 加 output 注解：

```xml
<eAnnotations source="blockly">
  <details key="output" value="true"/>
</eAnnotations>
```

并给 socket reference 加：

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="check" value="TextExpression"/>
</eAnnotations>
```

## 验证规则看起来不符合预期

验证规则的来源包括：

| 来源 | 生成的规则 |
| --- | --- |
| `lowerBound >= 1` | 必填字段或必填引用 |
| 带上下界的 containment | statement input 数量限制 |
| `iD=true` | 类型内唯一性 |
| 多值字段或引用的 `unique=true` | 值唯一性 |
| `mustFollow` | 前置 block 规则 |
| validation/OCL 注解 | 表达式规则 |

打开生成目录里的 `html/validation_workspace.html`，可以用 Blockly 块的形式查看规则。

## 常用本地验证命令

```bash
npm install
npm run verify:plugin
npm run verify:domain-xmi
npm run verify:patch
npm run smoke
```
