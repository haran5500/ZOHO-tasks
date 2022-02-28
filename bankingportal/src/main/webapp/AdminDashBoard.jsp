<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="account.AccountData"%>

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
	border-collapse: collapse;
	width: 70%;
}

#customertable tr:hover, #accountstable  tr:hover {
	background-color: #ddd;
}

#customertable tr td, #accountstable tr td {
	border: 1px solid black;
}

td {
	height: 50px;
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

.idanchor {
	text-decoration: none;
}
</style>

</head>
<body onload="accountView();">


	<jsp:include page="MenuBar.jsp" />

	<div id="maindiv">
		<div id="accdetails">
			<table id="accountstable">
				<tr>
					<td colspan="7"><h1>Account Details</h1></td>
				</tr>
				<tr>
					<td colspan="7" class="detright"><a href="AddAccount.jsp"
						class="button">Add Account</a> <!-- 				<input type="button" name="Add Account" value="Add Account"></td> -->
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
				BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, Map<Long, AccountData>> accountList = logic.getAllAccounts();

				for (long key : accountList.keySet()) {
					Map<Long, AccountData> subAccountList = accountList.get(key);
					for (long subKey : subAccountList.keySet()) {
						AccountData accounts = subAccountList.get(subKey);
				%>

				<tr>
					<td><%=accounts.getAccID()%></td>
					<td><%=accounts.getCustID()%></td>

					<td><%=accounts.getAccType()%></td>
					<td><%=accounts.getLocation()%></td>
					<td><%=accounts.getIfscCode()%></td>
					<td><%=accounts.getBalance()%></td>
					<td><%=accounts.getStatus()%></td>
				</tr>

				<%
				}
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>