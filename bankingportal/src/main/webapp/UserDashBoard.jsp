<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="customer.CustomerData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User DashBoard</title>
<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
	;
}

#accounts {
	margin-top: 10%;
}

#profile {
	display: none;
	/* margin-left: 25%; */
	margin-top: 10%;
}

.detcenter {
	text-align: center;
}

table {
	border-style: solid;
	height: 50px;
}

#accountstable {
	width: 70%;
}

#profiledetails {
	width: 25%;
}
</style>

<script type="text/javascript">
	
</script>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />
	<div id="profile" style="display: none;">
		<table id="profiledetails">
			<tr>
				<td colspan="2" class="detcenter"><h1>Profile Details</h1></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><Label>CustomerID</Label></td>
				<td>${customerObj.customerId}</td>
			</tr>

			<tr>
				<td><Label>Name</Label></td>
				<td>${customerObj.getName}</td>
			</tr>
			<tr>
				<td><Label>Gender</Label></td>
				<td>${customerObj.Gender}</td>
			</tr>
			<tr>
				<td><Label>City</Label></td>
				<td>${customerObj.City}</td>
			</tr>
			<tr>
				<td><Label>MobileNumber</Label></td>
				<td>${customerObj.MobileNo}</td>
			</tr>
			<tr>
				<td><Label></Label>Status</td>
				<td>${customerObj.Status}</td>
			</tr>

		</table>
	</div>

	<div id="accounts">
		<table id="accountstable">
			<tr>
				<td colspan="7" class="detcenter"><h1>Account Details</h1></td>
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
</body>
</html>