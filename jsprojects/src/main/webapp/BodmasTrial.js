/**
 *
 */

var operatedString = '';

function getInput() {
	var value = prompt("enter a value:");
	bodmasEval(value);
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

	value = String(value);
	console.log("bod" + value);

	if (!(value.includes('+') || value.includes('-') || value.includes('*') || value.includes('/') || value.includes('(') || value.includes(')')) || value < 0) {
		console.log('exit');
		return value;
	}

	if (value.includes('(')) {

		if (!validParenthesis(value)) {
			return null;
		}
		var ind = value.lastIndexOf('(');
		var closeInd = value.indexOf(')', ind);

		operatedString = value.substr(0, ind);
		var res = value.substring(ind + 1, closeInd);
		console.log(value.charAt(closeInd - 1));
		var passValue = splitOperand(res);
		console.log('splited:' + passValue);
		var result = operationExec(passValue);
		operatedString += result + value.substr(closeInd + 1);

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
	value = String(value);
	var operators = ['+', '-', '*', '/'];
	var returnOper = [];
	var cou = 0;
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
					alert('yes');

					local = appendVal;
					appendVal = '';
				}
			}

		}
		returnOper.push(appendVal);
	}
	return returnOper;
}

function operationExec(operands) {
	//	operands = String(operands);
	var result = '', ind1 = 0;
	console.log(operands);
	if (operands.includes('/')) {
		ind1 = operands.indexOf('/');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
	}
	else if (operands.includes('*')) {
		ind1 = operands.indexOf('*');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
	}
	else if (operands.includes('+')) {
		ind1 = operands.indexOf('+');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
	}
	else if (operands.includes('-')) {
		ind1 = operands.indexOf('-');
		var operand1 = operands[ind1 - 1];
		var operand2 = operands[ind1 + 1];
		var operator = operands[ind1];
		result = calculate(operand1, operand2, operator);
	}

	else {
		return operands;
	}
	operands.splice(ind1 - 1, 3, result);

	return operationExec(operands);
}

function castNumber(value) {
	if (value === '.') {
		return 0;
	}
	return value;
}

function calculate(operand1, operand2, enteredOp) {

	var result = '';
	operand1 = Number(String(castNumber(operand1)));
	
	operand2 = Number(String(castNumber(operand2)));

	alert(operand1 + enteredOp + operand2);
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
	}
	//	alert(operand1 + " " + operand2+" "+result);
	return result;
}
//1+2*(3+5)*(3*5+7)