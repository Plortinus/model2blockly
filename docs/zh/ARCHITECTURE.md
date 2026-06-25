# 架构

Model2Blockly 当前围绕一条模型驱动路线组织：带注解的 Ecore 转换为 EMF
中间模型，再生成静态 Blockly 输出。

Xtext 语法仍在项目中，但它是辅助层。

## 运行流程

```text
annotated .ecore
  -> EMF ResourceSet
  -> EPackage
  -> EcoreAdapter
  -> EditorSpec EMF model
  -> BlocklySpecXmiSerializer
  -> intermediate/*_blocklyspec.xmi
  -> XMI reload and diagnostics
  -> BlocklyCodeGenerator
  -> HTML/JavaScript artifacts
```

![生成流程](../assets/diagrams/generation-flow.svg)

## 模块

| 模块 | 作用 |
| --- | --- |
| `io.github.plortinus.model2blockly` | 核心插件：Xtext/EMF、适配器、中间模型、生成器和 standalone 入口。 |
| `io.github.plortinus.model2blockly.ui` | Eclipse UI：右键菜单、命令和验证补丁命令。 |
| `io.github.plortinus.model2blockly.ide` | 辅助文本语法的 Xtext IDE 服务。 |
| `io.github.plortinus.model2blockly.feature` | Eclipse feature 打包。 |
| `io.github.plortinus.model2blockly.updatesite` | 发布的 p2 update site。 |
| `docs` | VitePress 文档源。 |
| `scripts` | 验证和站点/update-site 构建脚本。 |

## 生成代码和手写代码

| 目录 | 内容 |
| --- | --- |
| `src` | 手写 Java/Xtend 实现。 |
| `src-gen` | Xtext 生成的解析器、语法访问和验证基础设施。 |
| `emf-gen` | EMF 为固定元模型生成的 Java API。 |
| `xtend-gen` | Xtend 生成的 Java。 |

## 实现地图

| 职责 | 主要文件 |
| --- | --- |
| Ecore standalone 路线 | `standalone/EcoreToBlocklyMain.java` |
| `EPackage` -> `EditorSpec` | `adapter/EcoreAdapter.java` |
| 辅助文本模型 -> `EditorSpec` | `adapter/DomainModelAdapter.java` |
| 中间 XMI 序列化 | `intermediate/BlocklySpecXmiSerializer.java` |
| 中间模型验证 | `blocklyspec/BlocklyEditorSpecValidator.java` |
| Blockly 文件生成 | `generator/BlocklyCodeGenerator.xtend` |
| 生成报告 | `generator/GenerationReportHtmlRenderer.java` |
| Eclipse 右键生成 | `ui/handlers/GenerateBlocklyEditorHandler.java` |

## 中间模型

`EditorSpec` 是源模型分析和 Blockly 输出之间的契约：

```text
Ecore route:
  EPackage -> EcoreAdapter -> EditorSpec

Auxiliary textual route:
  DomainModel -> DomainModelAdapter -> EditorSpec
```

`intermediate/*_blocklyspec.xmi` 会被写出、重新加载并验证，然后才生成 HTML。

![系统架构](../assets/diagrams/system-architecture.svg)

## Ecore 映射

| Ecore 来源 | 生成含义 |
| --- | --- |
| `EClass` | Blockly 区块类型。 |
| 抽象 `EClass` 或接口 | 抽象类型，不作为具体区块输出。 |
| `EAttribute` | Blockly 字段。 |
| `EEnum` 属性 | 下拉字段。 |
| containment `EReference` | statement input 和包含关系验证。 |
| 非 containment `EReference` | 动态引用字段。 |
| `lowerBound >= 1` | 必填验证。 |
| 多值 unique feature | 重复值验证。 |
| `iD` 属性 | 引用和 XMI 导出的身份字段。 |

注解 key 见 [Ecore 参考](./ECORE_REFERENCE.md)。

## GitHub Pages

GitHub Pages 发布 VitePress：

```text
https://plortinus.github.io/model2blockly/
```

VitePress 构建后 workflow 还会复制：

| 路径 | 作用 |
| --- | --- |
| `/update-site/` | Eclipse 插件安装用 p2 endpoint。 |
| `/app_maker_ecore/` | AppMaker 生成编辑器演示。 |
