/**
 * 
 */

function operatorInput(event) {
	var element = document.getElementById(eventId).value;

	if (input === '' && element.value === '-') {
		var localOp = element.value;
		if (!input.includes('-')) {
			input += localOp;
			document.getElementById("textbox").value += localOp;
		}

	}
	else {
		if (eleId === '') {
			eleId = event.id;
		}
		else if (eleId !== '') {
			document.getElementById(eleId).classList.remove("selected");
			eleId = event.id;
		}

		document.getElementById(eleId).classList.add("selected");
	}


}

function commaSeparation() {
	var resultStr = '';
	var dotted = '';
	var value = prompt("enter a value:");
	if (value.includes('.')) {
		var index = value.indexOf('.');
		dotted += '.' + value.substr(index + 1);
		value = value.substr(0, index);
		alert('dotted:' + dotted + ' value:' + value);
	}
	var len = value.length;

	if (len > 3) {
		var tmp1 = value % 1000;
		resultStr += tmp1.toString().length === 3 ? tmp1 : ('0' + tmp1);
		alert("temp:" + resultStr);

		value = value.slice(0, -3);
		while (value.length >= 2) {
			resultStr = ','.concat(resultStr);
			tmp1 = String(value % 100);
			resultStr = tmp1.length === 2 ? tmp1.concat(resultStr) : ('0' + tmp1).concat(resultStr);
			value = value.slice(0, -2);
		}
		if (value.length == 1) {
			resultStr = ','.concat(resultStr);
			resultStr = value.concat(resultStr);
			value = value.slice(0, -1);
		}
	}
	alert(resultStr + dotted);
}
