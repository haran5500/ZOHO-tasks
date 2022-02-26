<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Money Transfer</title>
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

fieldset {
	border: none;
	text-align: center;
}

input[type="text"] {
	height: 40px;
	width: 80%;
	border: none;
	border-bottom: 2px solid black;
	outline: none;
	background: none;
}

input[type="text"]:focus {
	background: white;
}

.required {
	font-weight: bold;
}

.required:after {
	content: " *";
	color: red;
}

#transferform {
	width: 50%;
	background-color: whitesmoke;
	border-radius: 5px;
}

table {
	width: 100%;
	height: 450px;
}

td {
	text-align: center;
	height: 50px;
}

input[type="submit"], .resetbtn {
	height: 50px;
	border: none;
	background-color: darkblue;
	color: white;
	border-radius: 25px;
}

.resetbtn {
	width: 20%;
	
}

input[type="submit"] {
	width: 40%;
	margin-left: 10px;
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
		document.getElementById("transferform").reset();
	}
</script>
</head>
<body>


	<jsp:include page="MenuBar.jsp" />


	<div class="div">
		<form action="" id="transferform">
			<fieldset>

				<legend>
					<img alt="" src="money-transfer.png">
				</legend>
				<table>
					<tr>
						<td><h2>Money Transfer</h2></td>
					</tr>
					<tr>
						<td><label class="required">From Account</label></td>
					</tr>
					<tr>
						<td><input type="text" name="fromaccount" min="6"
							maxlength="10" required onkeypress="return isNumberKey(event);"
							placeholder="From Account Number"></td>

					</tr>
					<tr>
						<td><label class="required">To Account</label></td>
					</tr>
					<tr>
						<td><input type="text" name="toaccount" min="6"
							maxlength="10" required onkeypress="return isNumberKey(event);"
							placeholder="To Account Number"></td>

					</tr>
					<tr>
						<td><label class="required">Transfer Amount</label></td>
					</tr>
					<tr>
						<td><input type="text" name="amount" min="1" maxlength="10"
							required onkeypress="return isNumberKey(event);"
							placeholder="Amount to be Transferred"></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"><input type="submit"
							name="TransferAmount" value="Transfer Amount"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>


</body>
</html>