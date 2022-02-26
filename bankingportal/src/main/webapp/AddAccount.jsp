<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Account</title>
<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
}

.div {
	margin-top: 10%;
	margin-left: 25%;
}

legend img {
	height: 75px;
	width: 75px;
}

legend {
	text-align: center;
}

input[type="text"] {
	height: 40px;
	width: 80%;
	/* 	border: none; */
	border-bottom: 2px solid black;
	outline: none;
	background: none;
}

input[type="text"]:focus {
	background: white;
}

#accountform {
	/* box-shadow: 2px 2px 5px 5px black; */
	width: 50%;
	background-color: whitesmoke;
	/* 	background-image: linear-gradient(to bottom right, darkblue 50%, mediumorchid 40%); */
	border-radius: 5px;
}

.required:after {
	content: " *";
	color: red;
}

::placeholder {
	/* color: white; */
	opacity: 1; /* Firefox */
}

table {
	width: 100%;
	height: 450px;
}

td {
	height: 50px;
}

fieldset {
	border: none;
}

input[type="submit"], .resetbtn {
	width: 80%;
	height: 50px;
	border: none;
	background-color: darkblue;
	color: white;
	border-radius: 25px;
}

input[type="submit"]:hover {
	background-color: forestgreen;
	color: black;
	box-shadow: 0px 5px 5px 0px darkblue;
}

.resetbtn:hover {
	background-color: tomato;
	color: black;
	box-shadow: 0px 5px 5px 0px darkblue;
}
</style>

<script>
function isNumberKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;

	return true;
}

	function resetform() {
		document.getElementById("accountform").reset();
	}
</script>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />

	<div class="div">
		<form action="" id="accountform">
			<fieldset>

				<legend>
					<img alt="" src="icons8-bank-account-50.png">
				</legend>

				<table>
					<tr>
						<td colspan="2" style="text-align: center;"><h1>Add
								Account</h1></td>
					</tr>
					<tr>
						<td><label class="required">CustomerID</label></td>
						<td><input type="text" name="CustomerID"
							placeholder="Customer ID" onkeypress="return isNumberKey(event);"
							autofocus="autofocus"></td>
					</tr>
					<tr>
						<td><label class="required">Account Type</label></td>
						<td><input type="text" name="AccType"
							placeholder="Account Type"></td>
					</tr>
					<tr>
						<td><label class="required">Location</label></td>
						<td><input type="text" name="Location" placeholder="Location"></td>
					</tr>
					<tr>
						<td><label class="required">Opening Balance</label></td>
						<td><input type="text" name="Balance"
							placeholder="Opening Balance"
							onkeypress="return isNumberKey(event);"></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"></td>

						<td><input type="submit" name="Add Account"
							value="Add Account"></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>