# Model2Blockly 文本 DSL

Model2Blockly 文本 DSL 使用 `.m2b` 文件。`.model2blockly` 仍然被插件兼容支持，
但文档和案例统一使用较短的 `.m2b` 扩展名。

这个 DSL 的角色需要准确理解：它不是 AppMaker 终端用户用来创建某个具体 App 的
业务模型语言，而是开发者用来定义 **block-based language** 的文本语言。也就是说，
`.m2b` 描述的是要生成哪些 Blockly block、字段、连接、分类和验证规则。

最终用户真正使用的是生成出来的 Blockly 编辑器，而不是 `.m2b` 文件本身。

| 对象 | 使用者 | 作用 |
| --- | --- | --- |
| `.m2b` 文本 DSL | 语言设计者、工具开发者 | 定义要生成的 Blockly 语言和编辑器结构。 |
| 生成的 Blockly 编辑器 | 领域用户 | 用拖拽区块的方式创建领域模型。 |

## 在 MDE 架构中的位置

```text
.m2b source file
  -> Xtext parser
  -> DomainModel
  -> DomainModelAdapter
  -> EditorSpec EMF intermediate model
  -> BlocklyCodeGenerator.xtend
  -> generated Blockly editor for domain users
```

这条路线和 Ecore route 共享同一个中间模型和同一个代码生成器：

```text
Ecore route: .ecore -> EcoreAdapter -> EditorSpec
DSL route:   .m2b   -> DomainModelAdapter -> EditorSpec
```

因此，`.m2b` 不是绕过 MDE 的配置文件；它是一个由 Xtext 定义、由 EMF 模型承载、
再转换到 EditorSpec 的文本建模入口。

## 相关模型和代码

| 内容 | 位置 |
| --- | --- |
| Xtext grammar | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/Model2Blockly.xtext` |
| DSL metamodel | `io.github.plortinus.model2blockly/model/metamodel/Model2Blockly.ecore` |
| EMF-generated DSL API | `io.github.plortinus.model2blockly/emf-gen/io/github/plortinus/model2blockly/model2Blockly` |
| DSL 到 EditorSpec 转换 | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/DomainModelAdapter.java` |
| standalone DSL 入口 | `io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/standalone/Model2BlocklyToBlocklyMain.java` |
| AppMaker DSL 示例 | `io.github.plortinus.model2blockly/examples/app_maker.m2b` |

## 语法概览

一个 `.m2b` 文件从 `domain` 开始，然后声明分类、类、字段、包含关系、引用、
value input、约束和验证规则。

```model2blockly
domain Appmaker
codeLanguage "javascript"
codeFileExtension "js"
runtimeKind "appMaker"

category Pages label "Pages" colour 260
category Components label "Components" colour 160
category Actions label "Actions" colour 20

class App category Pages colour 260 label "App" {
  attribute name : string default "Task Tracker" required
  attribute theme : enum { light, dark, system } default "light"
  contains Page pages [1..20]
}

class Page category Pages colour 260 label "Page" {
  attribute title : string default "Home" required
  attribute route : string default "/home" required
  contains Component components [1..40]
  contains Action onEnter [0..10]
}

abstract class Component category Components colour 160 {
}

class Button extends Component category Components colour 160 label "Button" {
  attribute labelText : string default "Save" required
  contains Action onClick [0..10]
}
```

这段文本定义的不是一个具体 App，而是 AppMaker 这种 Blockly 语言的结构。
生成器会据此创建 App、Page、Button 等 Blockly block。

## 支持的语言元素

| DSL 元素 | 生成含义 |
| --- | --- |
| `domain` | 生成编辑器的 domain 名称。 |
| `category` | Blockly toolbox 分类。 |
| `class` | Blockly block type。 |
| `abstract class` | 抽象 block type，不直接出现在工具箱中。 |
| `output class` | 生成带 output connection 的表达式 block。 |
| `extends` | 继承和 typed connection。 |
| `attribute` | Blockly field。 |
| `enum { ... }` | Dropdown field。 |
| `contains` | Statement input 和 containment 基数验证。 |
| `reference` | 引用下拉框或引用字段。 |
| `value` | Blockly value input。 |
| `constraint ... must follow ...` | 顺序约束验证。 |
| `validation ... expression/condition/js/ocl` | 生成运行时验证规则。 |
| `workspace { ... }` | Blockly workspace options。 |

## 和 Ecore 注解路线的区别

| 维度 | Ecore + annotations | `.m2b` textual DSL |
| --- | --- | --- |
| 输入对象 | 标准 EMF `.ecore` 元模型。 | Xtext 文本 DSL 文件。 |
| 适合场景 | 已有 Ecore 元模型，需要复用 EMF 工具链。 | 从零定义一个 Blockly 语言，追求简洁可读。 |
| 可读性 | XML/XMI 较冗长，注解分散在 EClass/EFeature 上。 | 结构集中，适合论文展示和快速示例。 |
| MDE 集成 | 直接基于 Ecore、EPackage、EAttribute、EReference。 | 基于 Xtext parser 和 DSL metamodel。 |
| 维护成本 | 不需要维护额外文本语法。 | 需要维护 grammar、parser 和 DomainModelAdapter。 |
| 与生成器关系 | 通过 `EcoreAdapter` 转到 EditorSpec。 | 通过 `DomainModelAdapter` 转到 EditorSpec。 |

两条路线不是竞争关系。Ecore route 更适合正式 EMF 元模型；DSL route 更适合清晰展示和快速定义。

## AppMaker 中的用法

AppMaker 案例保留两种输入：

| 路线 | 输入 |
| --- | --- |
| Ecore route | `io.github.plortinus.model2blockly/model/app_maker.ecore` |
| DSL route | `io.github.plortinus.model2blockly/examples/app_maker.m2b` |

文档中的 [AppMaker 案例](./RUNNING_EXAMPLE.md) 使用这两个输入说明同一个案例如何进入
统一的 EditorSpec 生成链路。
