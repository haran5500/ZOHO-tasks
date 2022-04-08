/**
 * 
 */

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}

function Toggle() {
	var x = document.getElementById("showp");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

function validateSubmit() {

}

function validate() {
	var userId = document.getElementById("UserId");
	var password = document.getElementById("showp")
	var message = document.getElementById('error');

	if (userId.value === '') {
		userId.focus();
		message.innerHTML = "***UserId cannot be Empty!";
		return false;
	}
	else if (isNaN(userId.value)) {
		userId.focus();
		message.innerHTML = "***UserId must be Number!";
		return false;
	}
	else if (password.value === '') {
		password.focus();
		message.innerHTML = "***Password cannot be Empty!";
		return false;
	}

	// 			message="***Fields cannot be Empty!";

	else {
		return true;
	}

}

function isSpaceEntered(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;

	if (charCode === 32) {
		document.getElementById('error').innerHTML = "***Space is not valid character!";
		return false;
	}
	document.getElementById('error').innerHTML = '';
	return true;
}

function showActive() {

	var activeDiv = document.getElementById('accdetails');
	var inActiveDiv = document.getElementById('inactivedetails');

	if (activeDiv.style.display === "none") {
		activeDiv.style.display = "block";
	}

	if (inActiveDiv.style.display === "block") {

		inActiveDiv.style.display = "none";
	}

}

function showInactive() {
	var activeDiv = document.getElementById('accdetails');
	var inActiveDiv = document.getElementById('inactivedetails');

	if (inActiveDiv.style.display === "none") {
		inActiveDiv.style.display = "block";
	}

	if (activeDiv.style.display === "block") {
		activeDiv.style.display = "none";
	}

}

function showProfile() {

	var div = document.getElementById("profile");

	if (document.body.contains(div)) {
		var accdiv = document.getElementById("accounts");

		if (div.style.display === "none") {

			div.style.display = "block";

		}

		if (accdiv.style.display === "block") {
			accdiv.style.display = "none";
		}
		// 			else {
		// 				div.style.display = "none";
		// 				det.style.display = "block";
		// 			}
	} else {

		document.forms[0].action = "UserDashBoard.jsp";
		document.forms[0].method = "post"; // "get"
		document.forms[0].submit();

	}
}

function closeProfile() {
	var div = document.getElementById("profile");
	div.style.display = "none";

	var accdiv = document.getElementById("accounts");

	if (div.style.display === "block") {
		div.style.display = "none";
	}

	if (accdiv.style.display === "none") {
		accdiv.style.display = "block";
	}
}

function showActiveCustomer() {

	var activeDiv = document.getElementById('customerdetails');
	var inActiveDiv = document.getElementById('inactivedetails');

	if (activeDiv.style.display === "none") {
		activeDiv.style.display = "block";
	}

	if (inActiveDiv.style.display === "block") {

		inActiveDiv.style.display = "none";
	}

}

function showInactiveCustomer() {
	var activeDiv = document.getElementById('customerdetails');
	var inActiveDiv = document.getElementById('inactivedetails');

	if (inActiveDiv.style.display === "none") {
		inActiveDiv.style.display = "block";
	}

	if (activeDiv.style.display === "block") {
		activeDiv.style.display = "none";
	}

}

function confirmation() {
	var confirmValue = confirm();
}

function logoutSession() {

}