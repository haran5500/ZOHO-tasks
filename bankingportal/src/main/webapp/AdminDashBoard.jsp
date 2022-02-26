<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin DashBoard</title>

<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
}

/* input[type="button"] {
	background-color: darkblue;
	border: none;
	height: 40px;
	border-radius: 25px;
	color: ghostWhite;
} */

/* input[type="button"]:hover,*/
.button1:hover {
	box-shadow: 0px 5px 5px 0px #ffa260;
}

.detcenter {
	text-align: center;
}

.detright {
	text-align: right;
}

#customerdetails, #accdetails {
	position: relative;
	top: 50px;
}

#customertable, #accountstable {
	width: 70%;
}

.button, .button1 {
	text-decoration: none;
	width: auto;
	height: 25px;
	padding: 10px;
	text-align: center;
	font-weight: bold;
	line-height: 25px;
	background: darkblue;
	/* 	border: none; */
	/* 	height: 40px; */
	border-radius: 25px;
	color: ghostWhite;;
}

.button {
	float: right;
	display: block;
}

.button1 {
	display: inline-block;
}

.button:hover {
	box-shadow: 0px 5px 5px 0px lightgreen;
}

#maindiv {
	position: relative;
}
</style>

</head>
<body onload="accountView();">


	<jsp:include page="MenuBar.jsp" />

	<div id="maindiv">
		<div id="customerdetails" style="display: none;">
			<table id="customertable">
				<tr>
					<td colspan="6" style="text-align: center;"><h1>Customer
							Details</h1></td>
				</tr>
				<tr>
					<td colspan="6" class="detright"><a href="AddCustomer.jsp"
						class="button">Add Customer</a> <!-- 				<input type="button" name="Add Customer" value="Add Customer"></td> -->
				</tr>
				<tr>
					<td><Label>CustomerID</Label></td>
					<td><Label>Name</Label></td>
					<td><Label>Gender</Label></td>
					<td><Label>City</Label></td>
					<td><Label>MobileNumber</Label></td>
					<td><Label></Label>Status</td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>

		<div id="accdetails" style="display: none;">
			<table id="accountstable">
				<tr>
					<td colspan="7" class="detcenter"><h1>Account Details</h1></td>
				</tr>
				<tr>
					<td colspan="7" class="detright"><a href="AddAccount.jsp"
						class="button">Add Account</a> <!-- 				<input type="button" name="Add Account" value="Add Account"></td> -->
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><Label>Account Number</Label></td>
					<td><Label>Customer ID</Label></td>
					<td><Label>Account Type</Label></td>
					<td><Label>Branch</Label></td>
					<td><Label>IFSC Code</Label></td>
					<td><Label>Account Balance</Label></td>
					<td><Label>Status</Label></td>
					<!-- 				<td><Label>Operations</Label></td> -->
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>

				</tr>
			</table>
		</div>
	</div>
</body>
</html>