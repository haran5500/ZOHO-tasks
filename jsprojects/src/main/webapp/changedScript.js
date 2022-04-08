function operatorInput(event) {
	var element = "";


	if (eleId === '') {
		eleId = event.id;
	}

	else if (eleId !== '') {
		element = document.getElementById(eleId);

		element.classList.remove("selected");
		eleId = event.id;
	}

	element = document.getElementById(eleId);

	element.classList.add("selected");



	if (document.getElementById(event.id).value === '-' && input === '') {
		var localOp = document.getElementById(event.id);
		if (!input.includes('-')) {
			input += localOp.value;
			document.getElementById("textbox").value += localOp.value;
		}
	}
	else if (!(input[0] === '-' && input.length === 1)) {
		if (operand1 == 0) {
			operand1 = Number(input);
		}

		else if (operand1 !== 0 && enteredOp !== '' && input !== '') {
			calculate();
			operand1 = Number(input);
		}

		input = '';
		document.getElementById("textbox").value = '';

		enteredOp = element.value;
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
	if (enteredOp !== '' && enteredOp === document.getElementById(eleId).value || eleId === 'minus') {
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