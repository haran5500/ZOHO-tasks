
//global variables

var operand1 = 0;
var input = "";
var enteredOp = '';
var eleId = '';
var step = 0;
var expResult = "";
var dotFlag = false;

function appendField() {
	if (step === 1) {
		document.getElementById("textbox").value = '';
		step = 0;
	}
}

function toggleHistory() {
	if (expResult) {
		document.getElementById("historyBox").value = expResult;
		expResult = '';
	}
}

function toggleZero() {
	var textboxVal = document.getElementById("textbox");

	if (textboxVal.value === '0') {
		textboxVal.value = '';
	}

}

function commaSeparation(value) {
	value = String(value);
	var resultStr = '';
	var dotted = '';
	if (value.includes('.')) {
		var index = value.indexOf('.');
		dotted += '.' + value.substr(index + 1);
		value = value.substr(0, index);
	}
	var len = value.length;

	if (len > 3) {
		var tmp1 = value % 1000;
		resultStr += tmp1.toString().length === 3 ? tmp1 : ('0' + tmp1);

		value = value.slice(0, -3);
		while (value.length >= 2) {
			resultStr = ','.concat(resultStr);
			tmp1 = String(value % 100);
			resultStr = String(tmp1.length === 2 ? tmp1 : ('0' + tmp1)).concat(resultStr);
			value = value.slice(0, -2);
		}
		if (value.length == 1) {
			resultStr = ','.concat(resultStr);
			resultStr = value.concat(resultStr);
			value = value.slice(0, -1);
		}
	}

	return (resultStr !== '' ? resultStr : value) + dotted;
}

function getInput(val) {

	toggleZero();
	appendField();

	if (val === '.') {
		if (dotFlag) {
			val = '';
		}
		dotFlag = true;
	}
	input += val;
	toggleHistory();

	var commaVal = commaSeparation(input);
	//	document.getElementById("textbox").value += val;
	document.getElementById("textbox").value = commaVal;
	document.getElementById("historyBox").value += val;

}

function operatorInput(event) {
	var element = document.getElementById(event.id);

	if (input === '' && element.value === '-') {
		var localOp = element.value;
		if (!input.includes('-')) {
			input += localOp;
			appendField();
			document.getElementById("historyBox").value += ' ' + localOp;
			toggleZero();
			document.getElementById("textbox").value += localOp;
		}
	}


	if (input !== '' || enteredOp !== '') {
		if (!(input[0] === '-' && input.length === 1)) {
			if (eleId === '') {
				eleId = event.id;
			}
			else if (eleId !== '') {
				document.getElementById(eleId).classList.remove("selected");
				eleId = event.id;
			}

			if (operand1 == 0) {
				operand1 = Number(input);
			}

			else if (operand1 !== 0 && enteredOp !== '' && input !== '') {
				calculate(false);
				operand1 = Number(input);
			}


			input = '';

			document.getElementById(eleId).classList.add("selected");

			enteredOp = document.getElementById(eleId).value;
			step = 1;
			toggleHistory();
			dotFlag = false;

			var hstBx = document.getElementById("historyBox").value;
			if (hstBx.endsWith('+') || hstBx.endsWith('-') || hstBx.endsWith('*') || hstBx.endsWith('/')) {
				document.getElementById("historyBox").value = hstBx.slice(0, -1);
			}

			document.getElementById("historyBox").value += enteredOp;
			console.log("Operand:" + enteredOp);
		}
	}
}

function clearInput() {
	if (eleId !== '') {
		document.getElementById(eleId).classList.remove("selected");
	}
	document.getElementById("textbox").value = 0;
	document.getElementById("historyBox").value = '';

	operand1 = 0;
	input = '';
	enteredOp = '';
}

function calculate(value) {
	if (enteredOp !== '' && enteredOp === document.getElementById(eleId).value) {
		document.getElementById(eleId).classList.remove("selected");
	}

	var result = '';
	if (operand1 === 0 && (input === '' || input === '-')) {
		result = 0;
	}
	else if (operand1 !== 0 && input === '') {
		result = operand1;
	}
	else if (operand1 === 0 && input !== '') {
		result = input;
	}
	else {
		input = Number(input);
		//		document.getElementById("historyBox").value += input;
		switch (enteredOp) {
			case '+':
				{
					result = operand1 + input;
					break;
				}
			case '-':
				{
					result = operand1 - input;
					break;
				}
			case '*':
				{
					result = operand1 * input;
					break;
				}
			case '/':
				{
					result = operand1 / input;
					break;
				}
		}
		enteredOp = '';

	}
	console.log(result);
	input = result;
	operand1 = 0;
	if (value) {
		expResult = result;
	}

	document.getElementById("textbox").value = commaSeparation(result);

}