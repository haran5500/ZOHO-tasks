
//global variables

var operand1 = 0;
var input = "";
var enteredOp = '';
var eleId = '';
function getInput(val) {

	document.getElementById("textbox").value += val;
	input += val;

}

function operatorInput(event) {
	var element = document.getElementById(event.id);

	if (input === '' && element.value === '-') {
		var localOp = element.value;
		if (!input.includes('-')) {
			input += localOp;
			document.getElementById("textbox").value += localOp;
		}
	}

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
			calculate();
			operand1 = Number(input);
		}

		input = '';
		document.getElementById("textbox").value = '';

		document.getElementById(eleId).classList.add("selected");

		enteredOp = document.getElementById(eleId).value;
		console.log("Operand:" + enteredOp);
	}

}

function clearInput() {
	if (eleId !== '') {
		document.getElementById(eleId).classList.remove("selected");
	}
	document.getElementById("textbox").value = '';

	operand1 = 0;
	input = '';
	enteredOp = '';
}

function calculate() {
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
	document.getElementById("textbox").value = result;
}