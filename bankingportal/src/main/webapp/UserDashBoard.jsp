<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="customer.CustomerData"%>
<%@ page import="account.AccountData"%>
<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User DashBoard</title>
<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
}

#topdiv {
	display: inline-block;
	width: 100%;
}

#accounts {
	margin-top: 10%;
	margin-left: 25%;
	display: block;
}

#profile {
	float: left;
	display: block;
	/* margin-left: 25%; */
	margin-top: 10%;
}

.detcenter {
	text-align: center;
}

#accountstable {
	border-collapse: collapse;
	width: 100%;
	height: 75px;
}

#profiledetails {
	width: 400px;
	border: 1px solid black;
	height: 75px;
	border: 1px solid black;
}

#accountstable tr td {
	border-collapse: collapse;
	height: 50px;
	border: 1px solid black;
}

#profiledetails tr td {
	border-collapse: collapse;
	height: 50px;
}
</style>

<script type="text/javascript">
	
</script>
</head>
<body>
	<%
	BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
	long custId = (long) session.getAttribute("customerId");
	CustomerData customer = logic.getCustomerDetailsByID(custId);

	Map<Long, AccountData> accountsMap = logic.getAccountByCustomerID(custId);
	%>
	<jsp:include page="MenuBar.jsp">
		<jsp:param value="<%=customer.getName()%>" name="UserName" />
	</jsp:include>

	<div id="profile" style="display: none;">
		<table id="profiledetails">
			<tr style="height: 25px;">
				<td colspan="2"><img alt="" src="icons8-close-30.png"
					style="float: right;" height="25px" width="25px"
					onclick="closeProfile();" title="close"></td>
			</tr>
			<tr>
				<td colspan="2"><h1>Profile Details</h1></td>
			</tr>
			<tr>
				<td><Label>CustomerID</Label></td>
				<td><%=customer.getId()%></td>
			</tr>

			<tr>
				<td><Label>Name</Label></td>
				<td><%=customer.getName()%></td>

			</tr>
			<tr>
				<td><Label>Gender</Label></td>
				<td><%=customer.getGender()%></td>
			</tr>
			<tr>
				<td><Label>City</Label></td>

				<td><%=customer.getCity()%></td>
			</tr>
			<tr>
				<td><Label>MobileNumber</Label></td>

				<td><%=customer.getMobileNo()%></td>
			</tr>
			<tr>
				<td><Label></Label>Status</td>

				<td><%=customer.getStatus()%></td>
			</tr>

		</table>
	</div>

	<div id="accounts">
		<table id="accountstable">
			<tr>
				<td colspan="7"><h1>Account Details</h1></td>
			</tr>

			<tr style="font-weight: bold;">
				<td><Label>Account Number</Label></td>
				<td><Label>Customer ID</Label></td>
				<td><Label>Account Type</Label></td>
				<td><Label>Branch</Label></td>
				<td><Label>IFSC Code</Label></td>
				<td><Label>Account Balance</Label></td>
				<td><Label>Status</Label></td>
				<!-- 				<td><Label>Operations</Label></td> -->
			</tr>
			<%
			for (long key : accountsMap.keySet()) {
				AccountData account = accountsMap.get(key);
			%>
			<tr>

				<td><%=account.getAccID()%></td>
				<td><%=account.getCustID()%></td>
				<td><%=account.getAccType()%></td>
				<td><%=account.getLocation()%></td>
				<td><%=account.getIfscCode()%></td>
				<td><%=account.getBalance()%></td>
				<td><%=account.getStatus()%></td>

			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>