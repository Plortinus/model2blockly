// Toolbox for domain "Pond".
// Auto-generated from metamodel.
window.BLOCKLY_TOOLBOX = {
	"kind": "categoryToolbox",
	"contents": [
		{
			"kind": "category",
			"name": "Pond",
			"colour": "290",
			"contents": [
				{"kind": "block", "type": "pond_scan", "inputs": {"DEGREE": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "pond_cannon", "inputs": {"DEGREE": {
					"shadow": { "type": "pond_math_number" }
				},"RANGE": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "pond_swim", "inputs": {"DEGREE": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "pond_stop"},
								{"kind": "block", "type": "pond_health"},
								{"kind": "block", "type": "pond_speed"},
								{"kind": "block", "type": "pond_getX"},
								{"kind": "block", "type": "pond_getY"},
								{"kind": "block", "type": "pond_log", "inputs": {"VALUE": {
					"shadow": { "type": "pond_math_number" }
				}
								}}
			]
		},
					{
			"kind": "category",
			"name": "Logic",
			"colour": "210",
			"contents": [
				{"kind": "block", "type": "controls_if"},
								{"kind": "block", "type": "logic_compare"},
								{"kind": "block", "type": "logic_operation"},
								{"kind": "block", "type": "logic_boolean"}
			]
		},
					{
			"kind": "category",
			"name": "Loops",
			"colour": "120",
			"contents": [
				{"kind": "block", "type": "controls_whileUntil"}
			]
		},
					{
			"kind": "category",
			"name": "Math",
			"colour": "230",
			"contents": [
				{"kind": "block", "type": "pond_math_number"},
								{"kind": "block", "type": "math_arithmetic", "inputs": {"A": {
					"shadow": { "type": "pond_math_number" }
				},"B": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "pond_math_single", "inputs": {"NUM": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "math_random_float"}
			]
		},
					{
			"kind": "category",
			"name": "Variables",
			"colour": "330",
			"contents": [
				{"kind": "block", "type": "math_change", "inputs": {"DELTA": {
					"shadow": { "type": "pond_math_number" }
				}
								}},
								{"kind": "block", "type": "variables_set"}
			]
		},
					{
			"kind": "category",
			"name": "Functions",
			"colour": "290",
			"contents": [
				{"kind": "block", "type": "procedures_defnoreturn"},
								{"kind": "block", "type": "procedures_defreturn"},
								{"kind": "block", "type": "procedures_callnoreturn"},
								{"kind": "block", "type": "procedures_callreturn"}
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
