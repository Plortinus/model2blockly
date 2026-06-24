# Ecore 注解参考

Model2Blockly 可以直接从带注解的 Ecore 元模型生成 Blockly 编辑器。输入使用标准 Ecore 结构，加上 `EAnnotation`。

Ecore 路线也会进入同一条中间模型流水线：`EPackage` 会被适配成生成的 EMF
`EditorSpec`，写成 `intermediate/*_blocklyspec.xmi`，再从 XMI 读回后生成
Blockly HTML/JavaScript。

完整 key 表和生成细节以英文参考为准：

- [`../../ECORE_REFERENCE.md`](../../ECORE_REFERENCE.md)
- 适配器：[`../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java`](../../io.github.plortinus.model2blockly/src/io/github/plortinus/model2blockly/adapter/EcoreAdapter.java)
- 示例：[`../../io.github.plortinus.model2blockly/model/app_maker.ecore`](../../io.github.plortinus.model2blockly/model/app_maker.ecore)

## 注解长什么样

XMI 中：

```xml
<eAnnotations source="blockly">
  <details key="colour" value="260"/>
  <details key="category" value="Pages"/>
  <details key="tooltip" value="Root app model."/>
</eAnnotations>
```

Eclipse Ecore tree editor 中对应：

```text
EAnnotation
  source = blockly
  details:
    colour -> 260
    category -> Pages
    tooltip -> Root app model.
```

普通 XML 注释不会参与生成。生成 metadata 必须放在 `EAnnotation` 中。

## 支持的 source

| Source | 使用位置 | 作用 |
| --- | --- | --- |
| `blockly` | `EPackage`, `EClass`, `EAttribute`, `EReference` | Blockly 展示、字段覆盖、value input、workspace |
| `ui` | `EClass`, `EAttribute`, `EReference` | 人类可读 label 和 UI metadata |
| `code` | `EPackage`, `EClass` | 语言、扩展名、代码模板 |
| `runtime` | `EPackage` | 可选运行时类型 |
| `validation` | `EClass` | 语义规则和 must-follow |
| `http://www.eclipse.org/emf/2002/Ecore` | `EClass` | EMF constraints 声明 |
| `http://www.eclipse.org/emf/2002/Ecore/OCL` | `EClass` | 简单 OCL invariant body |
| `http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot` | `EClass` | 简单 OCL invariant body |

## 零注解映射

Ecore 路线不加自定义注解也能工作，注解只是进一步控制生成结果。

| Ecore | 生成结果 |
| --- | --- |
| `EPackage.name` | domain/editor 名称 |
| 具体 `EClass` | Blockly block |
| `EClass.abstract=true` 或 `interface=true` | 抽象类型，不作为普通 block 显示 |
| 第一个 `eSuperTypes` | 连接类型父类 |
| `EAttribute : EString` | 文本字段 |
| `EAttribute : EInt`, `ELong` 等 | 数字字段 |
| `EAttribute : EBoolean` | checkbox |
| `EAttribute : EEnum` | dropdown |
| `EAttribute.lowerBound >= 1` | 必填验证 |
| `EAttribute.iD=true` | ID 和唯一性验证 |
| `EReference containment=true` | `input_statement` |
| `EReference containment=false` | 动态 reference selector |
| `EReference.eOpposite` | runtime 同步 metadata |
| derived/transient/volatile/不可修改 feature | 跳过 |

## package 注解

workspace 和 toolbox：

```xml
<eAnnotations source="blockly">
  <details key="workspace.renderer" value="zelos"/>
  <details key="workspace.trashcan" value="true"/>
  <details key="workspace.zoom.controls" value="true"/>
  <details key="workspace.grid.spacing" value="20"/>
  <details key="workspace.toolboxType" value="category"/>
</eAnnotations>
```

代码 metadata：

```xml
<eAnnotations source="code">
  <details key="language" value="javascript"/>
  <details key="fileExtension" value="js"/>
</eAnnotations>
```

runtime：

```xml
<eAnnotations source="runtime">
  <details key="kind" value="appMaker"/>
</eAnnotations>
```

## class 注解

```xml
<eAnnotations source="blockly">
  <details key="message0" value="Page %1 route %2"/>
  <details key="colour" value="260"/>
  <details key="category" value="Pages"/>
  <details key="tooltip" value="A navigable page."/>
  <details key="helpUrl" value="https://example.com/page-help"/>
  <details key="inputsInline" value="true"/>
  <details key="output" value="true"/>
</eAnnotations>
```

常用 key：

| Key | 生成结果 |
| --- | --- |
| `message0` | block 文本布局 |
| `colour` | block 颜色 |
| `category` | toolbox 分类；`UI/Inputs` 会生成嵌套分类 |
| `tooltip` | Blockly tooltip |
| `helpUrl` | 帮助链接 |
| `inputsInline` | `true` 或 `false` |
| `output` | `true` 表示 value block，也可以填类型名做 typed output |

人类可读 label：

```xml
<eAnnotations source="ui">
  <details key="label" value="Data source"/>
</eAnnotations>
```

代码模板：

```xml
<eAnnotations source="code">
  <details key="template" value="page(&quot;{{title}}&quot;, [&#10;{{statements:components}}&#10;]);"/>
</eAnnotations>
```

## attribute 和 reference

attribute UI 注解：

```xml
<eAnnotations source="ui">
  <details key="label" value="Title"/>
  <details key="group" value="Main"/>
  <details key="order" value="1"/>
</eAnnotations>
```

reference 作为 value input：

```xml
<eAnnotations source="blockly">
  <details key="type" value="input_value"/>
  <details key="check" value="Expression"/>
  <details key="shadow" value="TextLiteral"/>
</eAnnotations>
```

reference 下拉框 label：

```xml
<eAnnotations source="ui">
  <details key="referenceLabelField" value="name"/>
</eAnnotations>
```

## 验证规则和 OCL

验证规则可以来自 cardinality、ID、unique，也可以来自注解。

显式规则：

```xml
<eAnnotations source="validation">
  <details key="mustFollow" value="Page"/>
  <details key="message" value="Button must be inside a page."/>
</eAnnotations>
```

简单 OCL：

```xml
<eAnnotations source="http://www.eclipse.org/emf/2002/Ecore/OCL">
  <details key="HasName" value="not self.name.oclIsUndefined() and self.name.size() > 0"/>
</eAnnotations>
```

当前只支持小 OCL 子集：`self.<feature>`、`size()`、`notEmpty()`、
`isEmpty()`、`oclIsUndefined()`、比较，以及 `and`/`or`/`not`。
高级 OCL 会在生成前报错。

## 官方参考

- EMF: <https://eclipse.dev/emf/>
- `EAnnotation` Javadoc: <https://download.eclipse.org/modeling/emf/emf/javadoc/2.11/org/eclipse/emf/ecore/EAnnotation.html>
- Blockly block JSON: <https://docs.blockly.com/guides/create-custom-blocks/define/structure-json/>
- Blockly fields: <https://docs.blockly.com/guides/create-custom-blocks/fields/overview/>
- Blockly workspace config: <https://docs.blockly.com/guides/configure/configuration_struct/>
- Blockly code generation: <https://docs.blockly.com/guides/create-custom-blocks/code-generation/overview/>
