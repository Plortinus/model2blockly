---
layout: home
hero:
  name: Model2Blockly
  text: 用 MDE 生成 Blockly 编辑器
  tagline: 从带注解的 Ecore 元模型或 Model2Blockly `.m2b` 文本 DSL 出发，转换到 EMF EditorSpec 中间模型，再生成给领域用户使用的 Blockly 编辑器。
  image:
    src: /assets/diagrams/model2blockly-concept.svg
    alt: Model2Blockly 从 Ecore 或 m2b 转换到 EditorSpec 中间模型，再生成 Blockly HTML 编辑器
  actions:
    - theme: brand
      text: 查看在线示例
      link: https://plortinus.github.io/model2blockly/app_maker_ecore/Appmaker_standalone.html
    - theme: alt
      text: 安装插件
      link: /zh/install
    - theme: alt
      text: 了解架构
      link: /zh/architecture
features:
  - title: 先看生成效果
    details: 打开 AppMaker 在线示例，直接体验生成后的 Blockly 编辑器、工具箱、验证按钮和示例模型。
    link: /zh/running-example
    linkText: 了解示例
  - title: 双输入 MDE 架构
    details: Ecore route 和 .m2b DSL route 都转换到 EMF EditorSpec 中间模型，再由 Xtend 生成 Blockly 编辑器。
    link: /zh/architecture
    linkText: 架构说明
  - title: 在 Eclipse 中安装
    details: 通过 GitHub Pages 上的 update site 安装插件，然后在 Ecore 或 .m2b 文件上触发 Blockly 编辑器生成。
    link: /zh/install
    linkText: 安装指南
  - title: 使用 Ecore 或 m2b
    details: 既可以从标准 .ecore 元模型开始，也可以用 .m2b 文本 DSL 更紧凑地定义 Blockly 语言。
    link: /zh/textual-dsl
    linkText: 文本 DSL
  - title: 理解 Ecore 注解
    details: Ecore route 支持零注解默认生成，也支持 blockly、ui、code 和 validation 注解细化区块效果。
    link: /zh/ecore-to-blockly-mapping
    linkText: 映射规则
  - title: 理解生成结果
    details: 生成目录中包含 HTML、JavaScript、工具箱、验证工作区、示例模型、EditorSpec XMI 和生成报告。
    link: /zh/running-example
    linkText: 输出文件说明
---

## 推荐阅读路径

如果你是第一次访问这个项目，建议按下面的顺序阅读：

1. 打开 [AppMaker 示例](/zh/running-example)，先确认生成后的 Blockly 编辑器长什么样。
2. 按 [安装指南](/zh/install) 在 Eclipse 中安装插件。
3. 阅读 [架构说明](/zh/architecture)，理解 Ecore route、DSL route 和 EditorSpec 中间模型。
4. 阅读 [Model2Blockly 文本 DSL](/zh/textual-dsl)，理解 `.m2b` 在项目里的角色。
5. 阅读 [Ecore 到 Blockly 映射规则](/zh/ecore-to-blockly-mapping)，理解默认生成和注解定制的差异。

## 你需要准备什么

可以选择两类输入：

- `.ecore`：适合已有 EMF 元模型，按需添加 `blockly`、`ui`、`code`、`validation` 注解。
- `.m2b`：适合语言设计者用简洁文本定义 Blockly 语言。`.model2blockly` 是兼容保留的旧扩展名。

如果只是想先验证流程，可以直接使用仓库中的 AppMaker 示例。

## 你会得到什么

一次生成会输出一个可直接发布或本地打开的静态编辑器。典型输出包括：

- Blockly 区块定义和工具箱配置。
- 浏览器中的 standalone Blockly 编辑器页面。
- 运行时验证脚本和可视化验证工作区。
- 示例模型、生成报告和可检查的 EditorSpec XMI 中间模型。
