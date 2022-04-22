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
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>

<style type="text/css">



</style>

<script type="text/javascript">
	
</script>
</head>
<body>
	<%
	if (session.getAttribute("Role") == null) {
	%>
	<jsp:forward page="LoginPage.jsp"></jsp:forward>
	<%
	} else {
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
			<!-- 			<tr> -->
			<!-- 				<td><Label></Label>Status</td> -->

			<%-- 				<td><%=customer.getStatus()%></td> --%>
			<!-- 			</tr> -->

		</table>
	</div>

	<div id="accounts" style="display: block;">
		<h3 class="success" id="success">
			<%
			if (request.getAttribute("successMessage") != null) {
				out.println("***" + request.getAttribute("successMessage") + "**!");
			}
			%>
		</h3>

		<h3 class="error" id="error">
			<%
			if (request.getAttribute("errorMessage") != null) {
				out.println("***" + request.getAttribute("errorMessage") + "**!");
			}
			%>
		</h3>
		<table id="customerAccountstable">
			<tr>
				<td colspan="7"><h1>Account Details</h1></td>
			</tr>

			<tr style="font-weight: bold;" class="hrow">
				<td><Label>Account Number</Label></td>
				<td><Label>Customer ID</Label></td>
				<td><Label>Account Type</Label></td>
				<td><Label>Branch</Label></td>
				<td><Label>IFSC Code</Label></td>
				<td><Label>Account Balance</Label></td>
				<!-- 				<td><Label>Status</Label></td> -->
				<!-- 				<td><Label>Operations</Label></td> -->
			</tr>
			<%
			if (accountsMap != null) {
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
				<%-- 				<td><%=account.getStatus()%></td> --%>

			</tr>
			<%
			}
			}
			}
			%>
		</table>
	</div>
</body>
</html>