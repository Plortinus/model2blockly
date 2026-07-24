# Model2Blockly examples

This directory is an independent Eclipse project containing the executable
examples used by the documentation and thesis. Import it with **File > Import
> Existing Projects into Workspace** and select this `examples` directory.
After the Model2Blockly plug-in is installed, right-click an `.ecore` or `.m2b`
file and choose **Generate Blockly Editor**.

- `feature_pairs/` contains the six incremental examples shared by the Ecore
  and textual Model2Blockly routes.
- `ecore_specific/` contains the separate example for Ecore-only capabilities.
- `generated/` contains the checked-in AppMaker output used by the user guide
  and browser smoke tests.
- `app_maker.m2b` is the larger textual AppMaker example used by the guides.

Start with `feature_pairs/01_basic_structure/basicStructure.ecore` or
`feature_pairs/01_basic_structure/basicStructure.m2b`.
