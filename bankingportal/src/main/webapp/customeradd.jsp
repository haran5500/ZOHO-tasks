<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="customer.CustomerData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add or Update Customer</title>
<link href="CommonStyles.css" rel="stylesheet" />
<script type="text/javascript" src="new.js">

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
	border: none;
	border-bottom: 2px solid black;
	outline: none;
	background: none;
}

input[type="text"]:focus {
	background: white;
}

fieldset {
	border: none;
}

#customerform {
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
		document.getElementById("customerform").reset();
	}
</script>
<body>
	<div class="div">
		<form action="updateInfo" method="post" id="customerform">

			<fieldset>

				<legend>
					<img alt="" src="add-user.png">
				</legend>
				<table>

					<tr>
						<td colspan="2" style="text-align: center;"><h1>Update
								Customer</h1></td>
					</tr>
					<tr>
						<td><label class="required">Customer ID</label></td>
						<td><input type="text" name="CustomerID"
							placeholder="Customer ID" disabled="disabled"
							 required></td>
					</tr>

					<tr>
						<td><label class="required">Customer Name</label></td>
						<td><input type="text" name="CustomerName"
							autofocus="autofocus" placeholder="Customer Name" required></td>
					</tr>
					<tr>
						<td><label class="required">Gender</label></td>
						<td><input type="radio" id="Gender" name="Gender"
							value="Male" required><label for="Gender">Male</label> <input
							type="radio" id="Gender1" name="Gender" value="Female" required><label
							for="Gender1">Female</label></td>
					</tr>
					<tr>
						<td><label class="required">City</label></td>
						<td><input type="text" name="City"
							placeholder="Resident City" required></td>
					</tr>
					<tr>
						<td><label class="required">Mobile Number</label></td>
						<td><input type="text" name="MobileNo" min="10"
							maxlength="10" required onkeypress="return isNumberKey(event);"
							placeholder="Mobile Number"></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"></td>

						<td><input type="submit" name="Update Info"
							value="Update Info"></td>
					</tr>
				</table>
			</fieldset>
			
		</form>
<button onclick="hello();"></button>

	</div>
</body>
</html>