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

#customerdetails, #accdetails,#inactivedetails {
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

.button, .showbtn {
	float: right;
	display: block;
}

.button1 {
	display: inline-block;
}

.showbtn {
	width: auto;
	height: 45px;
	text-align: center;
	font-weight: bold;
	line-height: 40px;
	background: darkblue;
	/* 	border: none; */
	/* 	height: 40px; */
	border-radius: 25px;
	color: ghostWhite;
	border: none;
	margin-right: 5px;
}

.showbtn:hover, .button:hover {
	box-shadow: 0px 5px 5px 0px lightgreen;
}

#maindiv {
	position: relative;
}

.idanchor {
	text-decoration: none;
}

</style>

<script>
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
</script>
</head>
<body>


	<jsp:include page="MenuBar.jsp" />

	<div id="maindiv">
		<div id="accdetails" style="display: block;">
			<table id="accountstable">
				<tr>
					<td colspan="7"><h1>Account Details</h1></td>
				</tr>
				<tr>
					<td colspan="7" class="detright"><a
						href="AddorUpdateAccount.jsp?accountId=0" class="button">Add
							Account</a>
						<button class="showbtn" onclick="showInactive();">Show
							InActive Accounts</button> <!-- 				<input type="button" name="Add Account" value="Add Account"></td> -->
				</tr>

				<tr style="font-weight: bold;">
					<td><Label>Account Number</Label></td>
					<td><Label>Customer ID</Label></td>
					<td><Label>Account Type</Label></td>
					<td><Label>Branch</Label></td>
					<td><Label>IFSC Code</Label></td>
					<td><Label>Account Balance</Label></td>
					<td><Label>Operations</Label></td>
					<!-- 				<td><Label>Operations</Label></td> -->
				</tr>

				<%
				BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, Map<Long, AccountData>> accountList = logic.getAllActiveAccounts();

				for (long key : accountList.keySet()) {
					Map<Long, AccountData> subAccountList = accountList.get(key);
					for (long subKey : subAccountList.keySet()) {
						AccountData accounts = subAccountList.get(subKey);
				%>

				<tr>
					<td><a
						href="AddorUpdateAccount.jsp?accountId=<%=accounts.getAccID()%>"
						class="idanchor"><%=accounts.getAccID()%></a></td>
					<td><%=accounts.getCustID()%></td>

					<td><%=accounts.getAccType()%></td>
					<td><%=accounts.getLocation()%></td>
					<td><%=accounts.getIfscCode()%></td>
					<td><%=accounts.getBalance()%></td>
					<td><a
						href="deleteAccount?accountId=<%=accounts.getAccID()%>&customerId=<%=accounts.getCustID()%>"
						class="idanchor">Delete</a></td>
					<%-- 						<%=accounts.getStatus()%> --%>
				</tr>

				<%
				}
				}
				%>
			</table>
		</div>
		<div id="inactivedetails" style="display: none;">
			<table id="accountstable">
				<tr>
					<td colspan="7"><h1>Account Details</h1></td>
				</tr>
				<tr>
					<td colspan="7" class="detright"><button class="showbtn"
							onclick="showActive();">Show Active Accounts</button></td>
				</tr>

				<tr style="font-weight: bold;">
					<td><Label>Account Number</Label></td>
					<td><Label>Customer ID</Label></td>
					<td><Label>Account Type</Label></td>
					<td><Label>Branch</Label></td>
					<td><Label>IFSC Code</Label></td>
					<td><Label>Account Balance</Label></td>
					<td><Label>Operations</Label></td>
					<!-- 				<td><Label>Operations</Label></td> -->
				</tr>

				<%
				logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, Map<Long, AccountData>> inActiveAccountList = logic.getAllInActiveAccounts();

				for (long key : inActiveAccountList.keySet()) {
					Map<Long, AccountData> subAccountList = inActiveAccountList.get(key);
					for (long subKey : subAccountList.keySet()) {
						AccountData accounts = subAccountList.get(subKey);
				%>

				<tr>
					<td><a
						href="AddorUpdateAccount.jsp?accountId=<%=accounts.getAccID()%>"
						class="idanchor"><%=accounts.getAccID()%></a></td>
					<td><%=accounts.getCustID()%></td>

					<td><%=accounts.getAccType()%></td>
					<td><%=accounts.getLocation()%></td>
					<td><%=accounts.getIfscCode()%></td>
					<td><%=accounts.getBalance()%></td>
					<td><a
						href="activateAccount?accountId=<%=accounts.getAccID()%>&customerId=<%=accounts.getCustID()%>"
						class="idanchor">Activate</a></td>
					<%-- 						<%=accounts.getStatus()%> --%>
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