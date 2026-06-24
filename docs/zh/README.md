---
layout: home
hero:
  name: Model2Blockly
  text: 用 Eclipse 插件生成 Blockly 编辑器
  tagline: 在 Eclipse 中安装 Model2Blockly，选择带注解的 `.ecore` 元模型，一键生成可直接打开的 Blockly 块式 DSL 编辑器。
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly Eclipse 插件将 Ecore 元模型转换为 Blockly 编辑器
  actions:
    - theme: brand
      text: 开始使用
      link: /zh/user-guide
    - theme: alt
      text: AppMaker 案例
      link: /zh/running-example
    - theme: alt
      text: 安装插件
      link: https://plortinus.github.io/model2blockly/update-site/
features:
  - title: 从 Ecore 元模型生成
    details: 复用已有 `.ecore` 元模型，通过注解补充 Blockly 标签、颜色、分类、字段和验证信息。
    link: /zh/ecore-reference
    linkText: 查看 Ecore 支持
  - title: 使用 EMF 中间模型
    details: 把源 EPackage 转换成生成的 EditorSpec 模型，保存为 XMI、读回，再从该模型生成编辑器。
    link: /zh/architecture
    linkText: 查看架构
  - title: 生成可运行的 Blockly 页面
    details: 输出包含 toolbox、workspace、块定义、代码生成器和 standalone HTML，浏览器打开即可试用。
    link: /zh/running-example
    linkText: 查看 AppMaker
  - title: 可视化验证规则
    details: 自动生成验证工作区，把必填、数量、顺序、引用和简单表达式规则呈现为 Blockly blocks。
    link: /zh/user-guide
    linkText: 查看使用流程
  - title: 导出 JSON、XMI 和代码
    details: 用户在生成编辑器里搭建的模型可以保存、加载，并导出为 JSON、Ecore 路线可由 EMF 检查的领域 XMI 或模板生成代码。
    link: /zh/user-guide
    linkText: 查看导出流程
  - title: 生成报告和中间 XMI
    details: 每次生成都会留下 generation report 和 BlocklyEditorSpec XMI，方便检查输入如何变成编辑器。
    link: /zh/architecture
    linkText: 查看生成流程
---
