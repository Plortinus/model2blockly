# 论文批注中文分析

文件：`/Users/wxy/Downloads/MEMORIA_TFM_XIAOYONG_WANG_revisado_pablo_juan.pdf`

PDF 共 57 页。可提取到的批注分三类：

- 文字气泡评论：34 条。这些是老师明确写出来的修改意见，优先级最高。
- 高亮：37 条。多数没有额外说明，PDF 里只保存了被高亮的原文，主要表示措辞、引用或论证需要检查。
- 划掉：2 条。表示对应段落建议删掉或重写。

## 总体意思

老师整体不是说论文方向不行。Pablo 在第 55 页总评写的是“En general está bien”，也就是“总体可以/总体不错”。但两位老师都认为现在论文缺少足够的可验证材料：DSL 示例、Ecore 示例、模型/元模型图、转换表、生成结果截图、代码仓库链接、相关工作、评价指标和对表达能力的讨论。

最重要的修改方向：

1. 补充可视化和代码证据：DSL 片段、Ecore 元模型、Blockly 编辑器截图、生成结果截图、中间模型示例。
2. 把“转换过程”讲清楚：Ecore/DSL 如何转换到中间模型，中间模型如何生成 Blockly/网页输出，最好用表格说明。
3. 把 DSL 讲清楚：它的元模型、文本语法、示例、和 Ecore 相比的优势。
4. 补代码仓库和示例仓库链接，最好有公开可访问的生成示例 URL。
5. 加强相关工作和引用，尤其是块语言、Scratch、Dr. Scratch、block-based DSL/MDE 相关文献。
6. 加强评价：加入每个案例的度量，例如类、属性、引用、规则数量，并讨论 Blockly 编辑器和元模型是否表达能力等价。

## 文字气泡批注逐条解释

| PDF 页 | 章节/位置 | 老师原话要点 | 中文意思/应该怎么改 |
|---|---|---|---|
| 8 | 引言，MDE 引用附近 | La referencia [5] no es muy buena... pon esta: Brambilla, Cabot, Wimmer... | 参考文献 [5] 不适合作为 MDE 入门介绍。换成或补充 Brambilla/Cabot/Wimmer 的《Model-Driven Software Engineering in Practice》。 |
| 8 | 引言，MDE 动机附近 | Además, puedes añadir esta referencia... Hutchinson, Whittle, Rouncefield... | 可以再加一篇 MDE 工业实践的文献，用来支持“复用、质量、开发效率”等动机。 |
| 8 | EMF/Ecore 段落 | Añadir esta referencia a EMF... Steinberg et al. | EMF 介绍应引用权威 EMF 书籍，而不只是网页或一般资料。 |
| 8 | Xtext 段落 | Sustituir la referencia 9 por la 17... Bettini 2016 | Xtext/Xtend 的引用建议换成 Bettini 的书，更合适。 |
| 9 | 引言末尾/贡献后 | Poner un link a un repositorio github con el código del proyecto | 加 GitHub 仓库链接，让评审能看到项目代码。 |
| 11 | OE3，DSL 文本语法目标 | ¿Está orientado a describir lenguajes de bloques?... justificar por qué es necesario en vez de Ecore | 你说要做一个 Xtext DSL，但没说清它是不是专门用于描述块语言。要解释为什么需要 DSL，而不是只用 Ecore。 |
| 12 | 2.3 Alcance de la solución | Poner la tabla aquí abajo | 表 2.1 应放在介绍它的段落下面，而不是隔页或位置太远。 |
| 13 | 表 2.1，Entrada DSL textual | ¿Qué aporta respecto a Ecore? | 需要说明文本 DSL 相比 Ecore 带来什么价值，例如更紧凑、更接近领域、写例子更快、便于加 Blockly 语义等。 |
| 14 | 2.4 Límites del trabajo | Indica que podrían incluirse como trabajo futuro | 这些“不在范围内”的内容可以同时写成“未来工作”，不要只说排除。 |
| 14 | 2.5 Criterios de evaluación | No entiendo, ¿sólo un dominio? | “至少一个领域”这个评价标准太弱或不清楚。你的论文后面其实有多个案例，应改成多个代表性领域，或解释为什么一个 Ecore 域足够。 |
| 17 | 3.2 Blockly | Podrías poner: captura de pantalla... ejemplos de listado... | Blockly 背景介绍太抽象。加一张 Blockly 环境截图，再放一段生成/定义 Blockly 环境的代码或列表。 |
| 21 | 3.6/相关技术定位后 | Debes revisar trabajo relacionado... artículos... Scratch... Dr. Scratch | 缺少真正的 related work。要找“帮助创建基于块的 DSL/Blockly 环境”的论文；并在前文引用 Scratch 和 Dr. Scratch 来说明块语言重要性。 |
| 24 | 4.3 Modelo intermedio | Poner el meta-modelo... Asumo que... tiene un meta-modelo EMF? | 中间模型讲得不够。需要放中间模型的元模型图或类图，并详细解释。如果你走 MDE 路线，老师期待它有 EMF 元模型或至少结构化定义。 |
| 26 | 4.5 Ruta basada en Xtext，注解/检查附近 | Describir en una tabla las anotaciones en detalle | 把 Ecore/DSL 注解列成表：注解名、位置、含义、例子、生成效果。 |
| 26 | 4.5 Ruta basada en Xtext | Describir este DSL con su meta-modelo, su sintaxis textual y con ejemplos... mismo ejemplo en Ecore... | 文本 DSL 需要系统说明：元模型、语法、完整示例。最好用同一个例子分别写成 Ecore+注解和 DSL，比较两种路线优缺点。 |
| 27 | 4.6 Generación de editores Blockly | ¿Cómo se ha hecho este generador? ¿Qué artefacts genera? poner un ejemplo | 生成器说明不够。要写它是怎么实现的，输入输出是什么，生成哪些文件，例如 `blocks.js`、`toolbox.xml/json`、`index.html`、validations、网页输出文件等，并给示例。 |
| 28 | 4.7 Validaciones | Explicar estas comprobaciones con un ejemplo | 你说会生成检查/验证，但没举例。给一个约束如何变成 Blockly 警告或导出检查的具体例子。 |
| 29 | 表/规则说明 | No se entiende casi nada... expresiones del DSL que no has explicado | 这里大量引用 DSL 表达式，但前面没解释 DSL 语法，所以读者看不懂。先解释 DSL，再展示规则表。 |
| 31 | 5.1 Organización del proyecto | Poner un link a un repositorio Github con el código | 再次要求加代码仓库链接。说明这个问题很重要。 |
| 33 | 5.3 Modelo intermedio en Java，上部 | Ejemplo del DSL. Captura de pantalla con el editor. | 在实现章节加一个 DSL 示例和对应生成出来的编辑器截图。 |
| 33 | 5.3 Modelo intermedio en Java，下部 | Ejemplo del modelo intermedio | 给一个中间模型实例，例如用 Java 对象、JSON、表格或简化类图展示一个输入域如何变成中间模型。 |
| 34 | 5.4 Adaptador desde Ecore | Esto es una transformación de modelos... no has explicado ni las anotaciones, ni el modelo destino... Java/ATL/ETL? | Ecore 到中间模型本质上是模型转换，但论文没按模型转换讲。要说明源模型、目标模型、注解、映射规则，以及实现技术是 Java 还是 ATL/ETL 这类转换语言。 |
| 34 | 5.5 Adaptador desde el DSL textual | De nuevo esto es una transformación de modelos. Explica la transformación mediante una tabla | DSL 到中间模型也是模型转换。建议用表格写：DSL 元素 -> 中间模型元素 -> Blockly 结果。 |
| 35 | 5.6 Generador HTML/JavaScript | ¿Cómo has hecho esta generación de código? ¿Con lenguajes de plantilla tipo Xtendo o Acceleo? | 代码生成部分需要说清实现方式：是手写 Java 字符串/模板，还是 Xtend、Acceleo 等模板语言。 |
| 37 | 5.8 Salida web/Vite | Captura de pantalla del resultado final | 网页前端输出应加最终界面截图。 |
| 38 | 5.9 Entrada standalone para Ecore | No queda claro, captura de pantalla | standalone Ecore 入口讲得不直观。用截图说明怎么运行、输入输出是什么。 |
| 40 | 6.1 Estrategia de evaluación | Poner un link a un repositorio con los ejemplos generados | 评价章节要给生成案例的仓库链接，不只是项目源码。 |
| 41 | 评价流程图/案例 | ¿Por qué no los mismos ejemplos en Ecore/DSL?... comparar el esfuerzo... | 老师希望用同一个例子分别用 Ecore 和 DSL 实现，这样才能比较两条路线的成本和效果。 |
| 42 | 表 6.1 Casos de uso | Poner métricas de número de clases, atributos, referencias, etc | 案例表不够量化。加度量：类数、属性数、引用数、containment 数、规则/验证数、生成文件数等。 |
| 42 | 6.3 SprintPlanning desde Ecore | POner el meta-modelo | SprintPlanning 案例要放 Ecore 元模型图或结构表。 |
| 43 | 6.4.1 PlanTasks | POner fragmentos del DSL | DSL 案例不能只文字描述，要放实际 DSL 代码片段。 |
| 46 | 6.6 Verificaciones técnicas | Poner capturas de pantalla de los editores finales. | 评价不能只有测试和 `node --check`，也要展示最终编辑器截图。 |
| 48 | 6.8 Limitaciones de la evaluación | ¿Realmente los meta-modelos y los editores Blockly tienen la misma expresividad?... | 要讨论表达能力：Ecore 能表达但 Blockly 不能表达什么？Blockly 能表达但元模型没覆盖什么？不要默认二者等价。 |
| 55 | 总评 | En general está bien... falta incluir listados DSL, figuras, modelo intermedio Java, captura resultado final, repo, URL pública | 总体可以，但必须补证据材料：DSL 列表、图、Java 中间模型示例、最终截图、代码仓库。如果能部署一个生成的示例并给公开 URL，会更好。 |

## 高亮和划掉的意思

### 第 2-3 页：Resumen / Abstract

Pablo 高亮了摘要和英文摘要中的一些句子，例如“obligan/requieren que el desarrollador...”、“Este trabajo se repite”、“También cubren...”、“with less low-level manual programming”等。这里没有单独文字评论，意思大概率是让你润色摘要表达：

- 西语摘要和英文摘要中有些句子偏笨重或重复。
- “reduciendo/proporcionando/reducing/providing”这类现在分词结构可能不够自然。
- “También cubren...”这句列举太长，可以拆句或更准确地表达评价覆盖范围。
- 英文摘要里 “support saving, loading, export and validation warnings” 可以改成更自然的并列结构。

### 第 7-8 页：Introducción

高亮集中在连接词、动词和引用上，例如“No obstante/Sin embargo”、“utilizan”、“exclusivamente/solo”、“requerir la programación de”、“[1, 2]”、“[8, 9]”、“El objetivo es proporcionar”。意思是：

- 引言里的连接词和句式需要更自然，避免重复使用“sin embargo/además/por tanto”。
- 有些动词不够准确，例如“utilizan/usadas”可以换成更明确的表达。
- 引用位置需要检查，尤其是块语言、Blockly、MDE、Xtext 的引用是否对应正确。
- “El objetivo...”这类句子要更明确，避免泛泛地说“proporcionar/desarrollar un entorno”。

第 7 页有一整段关于 Blockly 的介绍被划掉：

> Blockly es una de las bibliotecas más usadas...

意思不是一定完全删除 Blockly 介绍，而是这一段可能和前后内容重复或表述不够好。可以重写为更短、更有引用支撑的版本，并配合截图/代码示例。

### 第 9-14 页：Objetivos y alcance

Pablo 高亮了“A continuación”、“La idea principal es sencilla”、“une/conecta dos ideas”、“utiliza/usa”、“Este”等表达。第 14 页划掉了“Límites del trabajo”开头那段泛泛说明。意思是：

- 目标章节要少用口语化过渡句，直接说目标、输入、输出和评价标准。
- 指代词“este/esto”要避免不清楚。
- “Límites del trabajo”开头段落重复前文，可以删减，直接进入范围外内容和未来工作。

### 第 17-19、26、32 页：引用高亮

Pablo 高亮了多处引用，如 `[11, 12]`、`[13, 14]`、`[7, 16]`、`[8, 9, 17]`。意思是让你检查引用是否合适、是否需要更权威来源、是否与正文断言匹配。结合 jdela 的文字批注，引用部分需要系统性调整：

- MDE 引用换/补 Brambilla 等。
- EMF 引用补 Steinberg 等。
- Xtext 引用补 Bettini。
- 块语言和 Scratch/Dr. Scratch 相关引用要加入。
- related work 要增加，而不仅是技术介绍。

### 第 29 页：DSL 规则表

jdela 高亮并写了“¿Qué regla es esta? ¿Es del DSL?”。这是明确问题：表里提到 `contains [m..n]` 这类规则，但读者还不知道它是不是 DSL 语法、Ecore 注解，还是你自己的中间表示。需要先定义语法和规则来源，再放表。

## 建议修改顺序

1. 先补 GitHub 仓库链接和示例仓库/部署链接。
2. 补 4 类核心图/例子：Ecore 元模型、DSL 代码片段、中间模型示例、生成编辑器截图。
3. 重写第 4-5 章的转换说明：Ecore -> 中间模型、DSL -> 中间模型、中间模型 -> Blockly/网页输出。
4. 增加 related work 和替换/补充参考文献。
5. 加强第 6 章评价：同例子对比 Ecore/DSL，增加案例指标，讨论表达能力差异。
6. 最后再处理摘要、引言和目标章节的语言润色。
