---
layout: home
hero:
  name: Model2Blockly
  text: 从带注解的 Ecore 生成 Blockly 编辑器
  tagline: 一个 Eclipse 插件和 standalone 生成流水线，通过可检查的 EditorSpec XMI 中间模型，把 EMF Ecore 元模型转换为浏览器可运行的 Blockly 编辑器。
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly 将带注解的 Ecore 转换为 EditorSpec 中间模型，再生成 Blockly HTML 输出
  actions:
    - theme: brand
      text: 从 Ecore 开始
      link: /zh/user-guide
    - theme: alt
      text: 安装插件
      link: /zh/install
    - theme: alt
      text: AppMaker 案例
      link: /zh/running-example
features:
  - title: Ecore 是主输入
    details: 在 Eclipse 中选择 Ecore 文件，或运行 Ecore standalone 入口。可选注解用于细化区块标签、分类、控件、验证和代码模板。
    link: /zh/ecore-reference
    linkText: Ecore 注解参考
  - title: EditorSpec 是生成契约
    details: 源 EPackage 会转换为 EMF EditorSpec 模型，序列化为 XMI，重新加载并验证后再生成 HTML。
    link: /zh/architecture
    linkText: 查看架构
  - title: Blockly 输出是静态 HTML
    details: 生成结果包含区块定义、工具箱、代码生成器、standalone 编辑器、验证工作区、示例模型和生成报告。
    link: /zh/running-example
    linkText: 查看 AppMaker
  - title: Xtext 是辅助层
    details: m2b 和 model2blockly 文件仍由 Eclipse 和 standalone 路径支持，但项目文档主线是 Ecore first。
    link: /zh/architecture
    linkText: 查看边界
  - title: 验证规则会被生成
    details: 必填字段、包含关系基数、引用、唯一性和一部分表达式/OCL 约束会转换为运行时检查和可视化验证区块。
    link: /zh/user-guide
    linkText: 使用流程
  - title: 插件安装仍然可用
    details: GitHub Pages 现在是 VitePress 文档站点。Eclipse update site 仍作为插件安装端点发布。
    link: /zh/install
    linkText: 安装指南
---

## 当前项目形态

Model2Blockly 不是手写 Blockly 模板库。当前代码是一条模型驱动流水线：

```text
annotated .ecore
  -> EMF EPackage
  -> EcoreAdapter
  -> EditorSpec EMF model
  -> intermediate/*_blocklyspec.xmi
  -> BlocklyCodeGenerator
  -> static HTML/JavaScript editor
```

仓库中的 AppMaker 是这条流水线的参考案例。生成结果位于：

```text
io.github.plortinus.model2blockly/examples/generated/app_maker_ecore
```
