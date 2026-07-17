# Memoria TFM

Este directorio contiene la memoria del TFM en tres niveles de lectura:

1. **Versión final maquetada**: `main.tex` y `main.pdf`.
2. **Capítulos fuente en LaTeX**: `chapters/*.tex`, usados para generar el PDF.
3. **Versiones Markdown de revisión**: `es/*.md` en español y `zh/*.md` en chino.

La versión oficial para entrega es la versión española en LaTeX/PDF. Los ficheros chinos no forman parte de la entrega formal; sirven para revisar el contenido, preparar la defensa y comprobar que la argumentación se entiende correctamente.

## Estructura

| Parte | Español | Chino | LaTeX |
|---|---|---|---|
| Resumen / abstract | `es/00_resumen_abstract.md` | `zh/00_resumen_abstract_zh.md` | `frontmatter/resumen.tex` |
| 1. Introducción | `es/01_introduccion.md` | `zh/01_introduccion_zh.md` | `chapters/01_introduccion.tex` |
| 2. Objetivos y alcance | `es/02_objetivos_alcance.md` | `zh/02_objetivos_alcance_zh.md` | `chapters/02_objetivos_alcance.tex` |
| 3. Estado del arte y tecnologías | `es/03_estado_arte_tecnologias.md` | `zh/03_estado_arte_tecnologias_zh.md` | `chapters/03_estado_arte_tecnologias.tex` |
| 4. Diseño y arquitectura | `es/04_diseno_arquitectura.md` | `zh/04_diseno_arquitectura_zh.md` | `chapters/04_diseno_arquitectura.tex` |
| 5. Implementación | `es/05_implementacion.md` | `zh/05_implementacion_zh.md` | `chapters/05_implementacion.tex` |
| 6. Casos de uso y evaluación | `es/06_casos_uso_evaluacion.md` | `zh/06_casos_uso_evaluacion_zh.md` | `chapters/06_casos_uso_evaluacion.tex` |
| 7. Conclusiones y trabajo futuro | `es/07_conclusiones_trabajo_futuro.md` | `zh/07_conclusiones_trabajo_futuro_zh.md` | `chapters/07_conclusiones_trabajo_futuro.tex` |

第五、六章另有逐段完整翻译的中文阅读版：

- `zh/05_implementacion_full_zh.pdf`：保留原图、表格和代码清单的独立 PDF；
- `zh/05_implementacion_full_zh.tex`：完整译文入口，正文分段位于 `zh/parts/`；
- `zh/05_implementacion_zh.md`：原有的快速提要版。
- `zh/06_casos_uso_evaluacion_full_zh.pdf`：保留原图、表格、流程图和数据的独立 PDF；
- `zh/06_casos_uso_evaluacion_full_zh.tex`：完整译文入口，正文分段位于 `zh/parts/`；
- `zh/06_casos_uso_evaluacion_zh.md`：原有的快速提要版。

## Lectura recomendada

Para revisar el contenido rápidamente:

1. Leer `es/00_resumen_abstract.md`.
2. Leer `es/02_objetivos_alcance.md` para fijar el alcance.
3. Leer `es/04_diseno_arquitectura.md` y `es/05_implementacion.md` para entender la solución.
4. Leer `es/06_casos_uso_evaluacion.md` para preparar la defensa.
5. Usar los ficheros `zh/*.md` como apoyo de comprensión.

También se puede abrir el visor HTML de Markdown desde la raíz del repositorio:

```bash
python3 -m http.server 8767
```

Después abrir:

```text
http://127.0.0.1:8767/docs/
```

## Criterio de sincronización

- Si cambia la implementación, revisar primero los capítulos 5 y 6.
- Si cambia el alcance o la forma de defender el trabajo, revisar los capítulos 2, 6 y 7.
- Si cambia la ruta Ecore o la ruta textual del DSL, revisar los capítulos 4, 5 y 6.
- La versión española debe mantenerse como fuente principal; la versión china debe seguirla en contenido, pero puede conservar explicaciones adicionales para facilitar la preparación de la defensa.
