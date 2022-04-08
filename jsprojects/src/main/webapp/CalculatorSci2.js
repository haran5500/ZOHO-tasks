
var operatedString = '';
var fullExp = '';
var input = "";
var enteredOp = '';
var eleId = '';
var paranId = '';
var step = 0;
var expResult = "";
var dotFlag = false;
var paranCount = 0;
var calcStep = 0;

function appendField() {
	if (step === 1 && !(fullExp.endsWith('**2') || fullExp.endsWith('**3'))) {
		document.getElementById("textbox").value = '';
		step = 0;
	}
}

function toggleHistory() {
	if (calcStep === 1) {
		document.getElementById("historyBox").value = expResult;
		fullExp = expResult;
		expResult = '';
		calcStep = 0;
	}

}

function toggleZero() {
	var textboxVal = document.getElementById("textbox");

	if (textboxVal.value === '0') {
		textboxVal.value = '';
	}

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

	if (fullExp.endsWith('(') || fullExp.endsWith(')')) {
		document.getElementById("textbox").value = '';
	}
	if (!(fullExp.endsWith('**2') || fullExp.endsWith('**3'))) {

		input += val;
		toggleHistory();
		fullExp += val;

		document.getElementById("textbox").value += val;

		document.getElementById("historyBox").value += val;
	}
}

function showRows() {
	var lastRow1 = document.getElementById('last1');
	var btnElem = document.getElementById('moreBtn');
	if (lastRow1.style.display === 'none') {
		lastRow1.style.display = '';
		btnElem.title = 'Less';
		btnElem.classList.add('morebtn');
	} else {
		lastRow1.style.display = 'none';
		btnElem.title = 'More';
		btnElem.classList.remove('morebtn');
	}
}

function paranthesisInput(event) {

	var element = document.getElementById(event.id);
	if (element.value === '(') {
		paranCount++;
		document.getElementById(event.id).classList.add("paranSelected");
		paranId = event.id;


	}

	else if (element.value === ')') {
		if (paranCount > 0) {
			paranCount--;
		}
		else {
			element = '';
		}

	}

	if (paranCount === 0) {
		document.getElementById('openParan').classList.remove("paranSelected");
		paranId = '';
	}
	if (element !== '') {
		document.getElementById("historyBox").value += element.value;
		fullExp += element.value;
	}

	//	toggleZero();
	//	document.getElementById("textbox").value += element.value;
}

function endsWithOperators() {
	var hstBx = document.getElementById("historyBox").value;

	return (hstBx.endsWith('+') || hstBx.endsWith('-') || hstBx.endsWith('*') || hstBx.endsWith('/') || hstBx.endsWith('%'));
}

function operatorInput(event) {
	var element = document.getElementById(event.id);
	var sqrtElem = document.getElementById('sqrt');

	if (input === '' && element.value === '-' && !fullExp.endsWith(sqrtElem.value)) {
		var localOp = element.value;
		if (!input.includes('-')) {
			input += localOp;
			appendField();
			document.getElementById("historyBox").value += ' ' + localOp;
			toggleZero();
			document.getElementById("textbox").value += localOp;
			fullExp += localOp;
		}
	}

	if (input === '' && event.id === 'sqrt') {
		if (eleId !== '') {
			document.getElementById(eleId).classList.remove("selected");
		}
		step = 1;
		eleId = event.id;
		document.getElementById(eleId).classList.add("selected");
		enteredOp = document.getElementById(eleId).value;
		document.getElementById("historyBox").value += enteredOp;
		fullExp += enteredOp;
	}

	else if (input !== '' || enteredOp !== '' && !fullExp.endsWith('(') && !fullExp.endsWith(sqrtElem.value)) {
		if (!(input[0] === '-' && input.length === 1)) {
			if (eleId === '') {
				eleId = event.id;
			}
			else if (eleId !== '') {
				document.getElementById(eleId).classList.remove("selected");
				eleId = event.id;
			}

			/*		if (operand1 == 0) {
						operand1 = Number(input);
					}
		
					else if (operand1 !== 0 && enteredOp !== '' && input !== '') {
						calculate(false);
						operand1 = Number(input);
					}
		*/
			input = '';

			document.getElementById(eleId).classList.add("selected");

			enteredOp = document.getElementById(eleId).value;
			step = 1;
			toggleHistory();
			dotFlag = false;

			var hstBx = document.getElementById("historyBox").value;
			if (endsWithOperators() && enteredOp !== sqrtElem.value) {
				document.getElementById("historyBox").value = hstBx.slice(0, -1);
				fullExp = fullExp.slice(0, -1);
			}

			if ((hstBx.endsWith('**2') || hstBx.endsWith('**3')) && (enteredOp == '^2' || enteredOp == '^3')) {
				document.getElementById("historyBox").value = hstBx.slice(0, -3);
				fullExp = fullExp.slice(0, -3);
			}
			if (enteredOp === '^2') {
				enteredOp = '**2';
			}
			else if (enteredOp === '^3') {
				enteredOp = '**3';
			}
			document.getElementById("historyBox").value += enteredOp;
			fullExp += enteredOp;
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
	expResult = '';
}

function backSpace() {

	var hstBx = document.getElementById("historyBox").value;
	var sqrtElem = document.getElementById("sqrt").value;
	if (endsWithOperators() || hstBx.endsWith(sqrtElem) || hstBx.endsWith('**2') || hstBx.endsWith('**3')) {
		document.getElementById(eleId).classList.remove('selected');
	}
	if (hstBx.endsWith('(')) {
		if (paranCount === 1) {
			document.getElementById(paranId).classList.remove('paranSelected');
		}

		paranCount--;
	}

	if (hstBx.endsWith(')')) {
		document.getElementById('openParan').classList.add('paranSelected');
		paranCount++;
	}
	document.getElementById("historyBox").value = document.getElementById("historyBox").value.slice(0, -1);
	if (document.getElementById("textbox").value != 0) {
		document.getElementById("textbox").value = document.getElementById("textbox").value.slice(0, -1);
	}
	fullExp = fullExp.slice(0, -1);
	input = input.slice(0, -1);
}

function executeOperation() {
	if (eleId !== '') {
		document.getElementById(eleId).classList.remove('selected');
	}
	console.log(fullExp);
	if (fullExp !== '') {
		expResult = bodmasEval(fullExp);
	}
	if (expResult === null) {
		expResult = 'error';
	}
	document.getElementById("textbox").value = expResult !== '' ? expResult : 0;
}

function checkOccur(value, checkVar) {


	var count = 0;
	value = String(value);
	checkVar = String(checkVar);
	for (var iter = 0; iter < value.length; iter++) {
		if (value.charAt(iter) === checkVar) {
			count++;
		}
	}
	return count;
}

function validParenthesis(value) {

	var openCou = 0, closeCou = 0;

	openCou = checkOccur(value, '(');
	closeCou = checkOccur(value, ')');

	return openCou === closeCou;
}

function bodmasEval(value) {
	var sqrtElem = document.getElementById('sqrt');

	value = String(value);
	console.log("bod" + value);

	if (!(value.includes('+') || value.includes('-') || value.includes('*') || value.includes('/') || value.includes('(') || value.includes(')') || value.includes('**') || value.includes(sqrtElem.value) || value.includes('%')) || value < 0) {
		console.log('exit');
		calcStep = 1;
		return value;
	}

	if (value.includes('(')) {

		if (!validParenthesis(value)) {
			expResult = null;
			return null;
		}
		var ind = value.lastIndexOf('(');
		var closeInd = value.indexOf(')', ind);

		operatedString = value.substring(0, ind);
		var operators = ['+', '-', '*', '/', String(sqrtElem.value), '**', '%'];

		if (!operators.includes(value.charAt(ind - 1)) && value.charAt(ind - 1) !== '(' && ind !== 0) {
			operatedString += '*';
		}
		var res = value.substring(ind + 1, closeInd);
		console.log(value.charAt(closeInd - 1));
		var passValue = splitOperand(res);
		console.log('splited:' + passValue);
		var result = operationExec(passValue);
		operatedString += result;
		if (!operators.includes(value.charAt(closeInd + 1)) && value.charAt(closeInd + 1) !== ')' && closeInd !== value.length - 1) {
			operatedString += '*';
		}
		operatedString += value.substr(closeInd + 1);

		console.log('after:' + operatedString);
	}

	else {
		var passValue = splitOperand(value);
		var result = operationExec(passValue);
		operatedString = result;
	}
	//	alert(value.indexOf(')', ind));



	return bodmasEval(operatedString);
}

function splitOperand(value) {
	var sqrtElem = document.getElementById('sqrt').value;
	value = String(value);
	var operators = ['+', '-', '*', '/', String(sqrtElem), '**', '%'];
	var returnOper = [];
	var local = '';
	for (var iter = 0; iter < value.length; iter++) {
		var appendVal = value[iter];
		if (!operators.includes(appendVal)) {
			if (returnOper !== '') {
				var val = returnOper.pop();

				if ((val !== undefined && !operators.includes(val))) {
					appendVal = String(val) + appendVal;

					if (local !== '') {
						appendVal = local + appendVal;
						local = '';
					}
				}

				else if (operators.includes(val)) {
					if (appendVal === '-' && !operators.includes(returnOper.slice(-1))) {
						local = appendVal;
						appendVal = '';
					}
					returnOper.push(val);
				}
			}
		}
		else if (operators.includes(appendVal) && appendVal === '-') {
			if (returnOper.length === 0) {
				local = appendVal;
				appendVal = '';
			}
			else if (returnOper.length > 0) {
				var check = returnOper.slice(-1).toString();

				if (operators.includes(check)) {


					local = appendVal;
					appendVal = '';
				}
			}

		}

		if (appendVal === '*' && value[iter + 1] === '*') {
			appendVal += value[iter + 1];
			iter++;
		}

		if (appendVal === sqrtElem && !operators.includes(value[iter - 1]) && iter != 0) {
			returnOper.push('*');
		}
		returnOper.push(appendVal);
	}
	console.log("split:" + returnOper);
	return returnOper;
}

function operationExec(operands) {
	//	operands = String(operands);
	var result = '', ind1 = 0, localOp = '';
	console.log(operands);
	var sqrtElem = document.getElementById('sqrt');
	if (operands.includes('**')) {
		ind1 = operands.indexOf('**');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}

	else if (operands.includes(sqrtElem.value)) {
		ind1 = operands.indexOf(sqrtElem.value);
		var operand1 = operands[ind1 + 1];
		var operand2 = 0;
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}

	else if (operands.includes('/')) {
		ind1 = operands.indexOf('/');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}
	else if (operands.includes('*')) {
		ind1 = operands.indexOf('*');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}

	else if (operands.includes('%')) {
		ind1 = operands.indexOf('%');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}

	else if (operands.includes('+')) {
		ind1 = operands.indexOf('+');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}
	else if (operands.includes('-')) {
		ind1 = operands.indexOf('-');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
		localOp = operator;
	}

	else {
		return operands;
	}

	localOp !== sqrtElem.value ? operands.splice(ind1 - 1, 3, result) : operands.splice(ind1, 2, result);

	return operationExec(operands);
}

function castNumber(value) {
	if (value === '.') {
		return 0;
	}
	return value;
}

function calculate(operand1, operand2, enteredOp) {
	var sqrtElem = document.getElementById('sqrt');
	var result = '';
	operand1 = Number(castNumber(operand1));
	operand2 = Number(castNumber(operand2));
	switch (enteredOp) {
		case '+':
			{
				result = operand1 + operand2;
				break;
			}
		case '-':
			{
				result = operand1 - operand2;
				break;
			}
		case '*':
			{
				result = operand1 * operand2;
				break;
			}
		case '/':
			{
				result = operand1 / operand2;
				break;
			}
		case '%':
			{
				result = operand1 % operand2;
				break;
			}
		case '**':
			{
				result = operand1 ** operand2;
				break;
			}
		case sqrtElem.value:
			{
				result = Math.sqrt(operand1);
				break;
			}
	}
	return result;
}
