---
layout: home
hero:
  name: Model2Blockly
  text: 从 Ecore 生成可用的 Blockly 编辑器
  tagline: 准备一个 EMF Ecore 元模型，安装 Eclipse 插件或运行 standalone 生成器，即可得到包含区块、工具箱、代码生成器、验证逻辑和示例数据的静态 Blockly 编辑器。
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly 从 Ecore 元模型生成 EditorSpec 中间模型，再生成 Blockly HTML 编辑器
  actions:
    - theme: brand
      text: 查看在线示例
      link: https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
    - theme: alt
      text: 安装插件
      link: /zh/install
    - theme: alt
      text: 用 Ecore 生成
      link: /zh/user-guide
features:
  - title: 先看生成效果
    details: 打开 AppMaker 在线示例，直接体验生成后的 Blockly 编辑器、工具箱、验证按钮和示例模型。
    link: /zh/running-example
    linkText: 了解示例
  - title: 在 Eclipse 中安装
    details: 通过 GitHub Pages 上的 update site 安装插件，然后在 Ecore 文件上触发 Blockly 编辑器生成。
    link: /zh/install
    linkText: 安装指南
  - title: 使用自己的 Ecore
    details: 从 .ecore 元模型开始，按需添加 blockly 和 validation 注解，生成面向该领域模型的可视化编辑器。
    link: /zh/ecore-reference
    linkText: Ecore 注解参考
  - title: 理解生成结果
    details: 生成目录中包含 HTML、JavaScript、工具箱、验证工作区、示例模型、EditorSpec XMI 和生成报告。
    link: /zh/running-example
    linkText: 输出文件说明
  - title: 排查安装和生成问题
    details: 如果 Eclipse 找不到插件、update site 列表为空、生成文件缺失或 Pages 没有更新，可以从故障排查开始。
    link: /zh/troubleshooting
    linkText: 故障排查
  - title: 了解内部架构
    details: 当需要扩展生成器、解释论文中的技术路线或维护项目时，可以查看 Ecore、EditorSpec 和 Blockly 生成器之间的关系。
    link: /zh/architecture
    linkText: 架构说明
---

## 推荐阅读路径

如果你是第一次访问这个项目，建议按下面的顺序阅读：

1. 打开 [AppMaker 示例](/zh/running-example)，先确认生成后的编辑器长什么样。
2. 按 [安装指南](/zh/install) 在 Eclipse 中安装插件。
3. 阅读 [使用指南](/zh/user-guide)，用示例 Ecore 或自己的 Ecore 生成编辑器。
4. 需要自定义区块、字段、分类或验证规则时，查阅 [Ecore 注解参考](/zh/ecore-reference)。
5. 如果生成结果不符合预期，再查看 [架构说明](/zh/architecture) 和 [故障排查](/zh/troubleshooting)。

## 你需要准备什么

最小输入是一个 EMF Ecore 元模型。为了让生成出的 Blockly 编辑器更贴合领域语言，可以在 Ecore 元素上添加注解：

- `blockly` 注解用于控制区块名称、分类、字段显示、连接关系和代码生成模板。
- `validation` 注解用于生成必填项、基数、引用、唯一性和部分表达式约束检查。
- 如果只是想先验证流程，可以直接使用仓库中的 AppMaker 示例模型。

## 你会得到什么

一次生成会输出一个可直接发布或本地打开的静态编辑器。典型输出包括：

- Blockly 区块定义和工具箱配置。
- 浏览器中的 standalone 编辑器页面。
- 运行时验证脚本和可视化验证工作区。
- 示例模型、生成报告和可检查的 EditorSpec XMI 中间模型。

## 生成流程概览

生成器内部按下面的顺序处理模型：

```text
annotated .ecore
  -> EMF EPackage
  -> EcoreAdapter
  -> EditorSpec EMF model
  -> intermediate/*_blocklyspec.xmi
  -> BlocklyCodeGenerator
  -> static HTML/JavaScript editor
```

普通使用者不需要手工编辑 EditorSpec。它主要用于调试、复现实验和解释生成过程。
