// Toolbox for domain "Statementinput".
// Auto-generated from metamodel.
window.BLOCKLY_TOOLBOX = {
	"kind": "categoryToolbox",
	"contents": [
		{
			"kind": "category",
			"name": "Layout",
			"colour": "260",
			"contents": [
				{"kind": "block", "type": "Page"}
			]
		},
					{
			"kind": "category",
			"name": "Components",
			"colour": "160",
			"contents": [
				{"kind": "block", "type": "Button"}
			]
		},
						{"kind": "sep", "gap": "32"},
		{
			"kind": "category",
			"name": "Logic",
			"colour": "210",
			"contents": [
				{"kind": "block", "type": "controls_if"},
				{"kind": "block", "type": "controls_if", "extraState": {"hasElse": true}},
				{"kind": "block", "type": "logic_compare"},
				{"kind": "block", "type": "logic_operation"},
				{"kind": "block", "type": "logic_negate"},
				{"kind": "block", "type": "logic_boolean"},
				{"kind": "block", "type": "logic_null"},
				{"kind": "block", "type": "logic_ternary"}
			]
		},
		{
			"kind": "category",
			"name": "Loops",
			"colour": "120",
			"contents": [
				{"kind": "block", "type": "controls_repeat_ext", "inputs": {"TIMES": {"shadow": {"type": "math_number", "fields": {"NUM": 10}}}}},
				{"kind": "block", "type": "controls_whileUntil"},
				{"kind": "block", "type": "controls_for", "inputs": {
					"FROM": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"TO":   {"shadow": {"type": "math_number", "fields": {"NUM": 10}}},
					"BY":   {"shadow": {"type": "math_number", "fields": {"NUM": 1}}}
				}},
				{"kind": "block", "type": "controls_forEach"},
				{"kind": "block", "type": "controls_flow_statements"}
			]
		},
		{
			"kind": "category",
			"name": "Math",
			"colour": "230",
			"contents": [
				{"kind": "block", "type": "math_number", "fields": {"NUM": 0}},
				{"kind": "block", "type": "math_arithmetic", "inputs": {
					"A": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"B": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}}
				}},
				{"kind": "block", "type": "math_single", "inputs": {
					"NUM": {"shadow": {"type": "math_number", "fields": {"NUM": 9}}}
				}},
				{"kind": "block", "type": "math_round", "inputs": {
					"NUM": {"shadow": {"type": "math_number", "fields": {"NUM": 3.1}}}
				}},
				{"kind": "block", "type": "math_modulo", "inputs": {
					"DIVIDEND": {"shadow": {"type": "math_number", "fields": {"NUM": 64}}},
					"DIVISOR":  {"shadow": {"type": "math_number", "fields": {"NUM": 10}}}
				}},
				{"kind": "block", "type": "math_constrain", "inputs": {
					"VALUE": {"shadow": {"type": "math_number", "fields": {"NUM": 50}}},
					"LOW":   {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"HIGH":  {"shadow": {"type": "math_number", "fields": {"NUM": 100}}}
				}},
				{"kind": "block", "type": "math_random_int", "inputs": {
					"FROM": {"shadow": {"type": "math_number", "fields": {"NUM": 1}}},
					"TO":   {"shadow": {"type": "math_number", "fields": {"NUM": 100}}}
				}},
				{"kind": "block", "type": "math_random_float"}
			]
		},
		{
			"kind": "category",
			"name": "Variables",
			"colour": "330",
			"custom": "VARIABLE"
		},
		{
			"kind": "category",
			"name": "Functions",
			"colour": "290",
			"custom": "PROCEDURE"
		}
	]
};
