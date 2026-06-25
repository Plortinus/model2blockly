# 安装 Eclipse 插件

1. 打开 Eclipse。
2. 选择 `Help` -> `Install New Software...`。
3. 点击 `Add...`。
4. `Name` 填写 `Model2Blockly`。
5. `Location` 填写：

   ```text
   https://plortinus.github.io/model2blockly/update-site/
   ```

6. 点击 `Add`。
7. 在可安装内容中勾选 `Model2Blockly` feature。
8. 保持 `Contact all update sites during install to find required software` 处于勾选状态。
9. 点击 `Next`。
10. 按向导确认安装内容。
11. 接受许可证并点击 `Finish`。
12. 如果 Eclipse 提示信任未签名内容，确认继续安装。
13. 安装完成后按提示重启 Eclipse。
14. 重启后，在 workspace 中选中 `.ecore` 文件，确认右键菜单中出现 `Generate Blockly Editor`。
