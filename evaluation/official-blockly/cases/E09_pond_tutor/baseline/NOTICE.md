# Upstream notice

The files under `upstream/` are reproducible excerpts from
`google/blockly-games` commit
`5d2ff6f39207f7ed31e3d11ba1de277c85efe4c2`, licensed under Apache-2.0.
Their exact source paths, line ranges and SHA-256 hashes are recorded in
`baseline-extraction.json`.

The baseline adapter is local experimental glue. Its Blockly 13 compatibility
layer only maps the legacy mutator API used by the unchanged Pond source to the
current mutator icon API and supplies the removed legacy `FieldAngle` class; it
is not counted as upstream implementation LOC.
