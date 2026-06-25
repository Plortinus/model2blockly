---
layout: home
hero:
  name: Model2Blockly
  text: 基于 Ecore 的 Blockly 编辑器生成工具
  tagline: Model2Blockly 以 EMF Ecore 元模型为输入，通过可检查的 EditorSpec 中间模型生成可在浏览器运行的 Blockly 编辑器，并提供 Eclipse 插件与 standalone 两种使用方式。
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly 从 Ecore 元模型生成 EditorSpec 中间模型，再生成 Blockly HTML 编辑器
  actions:
    - theme: brand
      text: 快速开始
      link: /zh/user-guide
    - theme: alt
      text: 安装插件
      link: /zh/install
    - theme: alt
      text: 示例项目
      link: /zh/running-example
features:
  - title: 以 Ecore 为入口
    details: 使用带注解的 .ecore 文件描述领域模型，并通过注解补充区块标签、分类、字段控件、验证规则和代码模板。
    link: /zh/ecore-reference
    linkText: Ecore 注解参考
  - title: 可检查的中间模型
    details: Ecore 会先转换为 EMF EditorSpec 模型并序列化为 XMI，便于检查、调试和复现实验结果。
    link: /zh/architecture
    linkText: 架构说明
  - title: 静态 Blockly 编辑器
    details: 生成结果包含区块定义、工具箱、代码生成器、standalone 编辑器、验证工作区、示例模型和生成报告。
    link: /zh/running-example
    linkText: 查看示例
  - title: Eclipse 与 standalone
    details: 可以在 Eclipse 中通过上下文菜单生成编辑器，也可以使用 Java standalone 入口在命令行或自动化流程中运行生成器。
    link: /zh/architecture
    linkText: 查看运行方式
  - title: 验证能力
    details: 必填字段、包含关系基数、引用、唯一性以及部分表达式约束会转换为运行时检查和可视化验证区块。
    link: /zh/user-guide
    linkText: 使用流程
  - title: 插件安装
    details: Eclipse 插件通过 GitHub Pages 上的 update site 发布，安装后可以直接从 Ecore 文件触发 Blockly 编辑器生成。
    link: /zh/install
    linkText: 安装指南
---

## 文档导览

这份文档围绕当前项目的 Ecore 主线组织：

- [快速开始](/zh/user-guide)：从示例 Ecore 模型生成 Blockly 编辑器。
- [安装插件](/zh/install)：在 Eclipse 中配置 update site 并安装 Model2Blockly。
- [AppMaker 示例](/zh/running-example)：查看仓库中的参考案例和生成结果。
- [架构说明](/zh/architecture)：了解 Ecore、EditorSpec、代码生成器和输出文件之间的关系。
- [Ecore 注解参考](/zh/ecore-reference)：查询可用注解及其生成效果。
- [故障排查](/zh/troubleshooting)：处理安装、构建、生成和 GitHub Pages 发布问题。

## 生成流程

Model2Blockly 的核心流程如下：

```text
annotated .ecore
  -> EMF EPackage
  -> EcoreAdapter
  -> EditorSpec EMF model
  -> intermediate/*_blocklyspec.xmi
  -> BlocklyCodeGenerator
  -> static HTML/JavaScript editor
```

在这个流程中，Ecore 是主要输入；EditorSpec 是生成前的中间模型；最终输出是可以直接在浏览器中打开的 Blockly 编辑器。仓库中的 AppMaker 示例展示了完整生成结果：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```

## 适用场景

Model2Blockly 适合用于从领域元模型快速生成可视化建模编辑器。它尤其适合需要保留 Ecore/EMF 建模基础，同时希望把模型编辑体验发布为浏览器端 Blockly 界面的项目。
