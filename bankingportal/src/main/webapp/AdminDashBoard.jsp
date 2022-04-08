<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="account.AccountData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="CommonStyles.css" rel="stylesheet" />
<title>Admin DashBoard</title>
<script type="text/javascript" src="CommonScript.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
td {
	padding-left: 8px;
	height: 50px;
}
</style>

</head>
<body>


	<jsp:include page="MenuBar.jsp" />

	<div id="maindiv">
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
		<div id="accdetails" style="display: block;">
			<a href="AddorUpdateAccount.jsp?accountId=0" class="iconbtn"><img
				alt="" class="iconimg" src="resume.png" title="Add Account"></a> <img
				alt="" src="credit-card.png" title="Show InActive Account"
				class="iconbtn iconimg" onclick="showInactive();">


			<table id="accountstable">
				<tr>
					<td colspan="7"><h1>Account Details</h1></td>
				</tr>

				<tr class="hrow">
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

				if (accountList.isEmpty()) {
				%>
				<tr>
					<td colspan="7" class="detcenter">No Account Records!</td>
				</tr>
				<%
				} else {
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
					<td><a
						href="AddorUpdateAccount.jsp?accountId=<%=accounts.getAccID()%>"
						class="idanchor"><img alt="" src="editing.png"
							class="anchorimg" title="Edit Info"></a> <a
						href="deleteAccount?accountId=<%=accounts.getAccID()%>&customerId=<%=accounts.getCustID()%>"
						class="idanchor"><img alt="" src="delete-icon.png"
							class="anchorimg" title="De-Activate"></a></td>
					<%-- 						<%=accounts.getStatus()%> --%>
				</tr>

				<%
				}
				}
				}
				%>
			</table>
		</div>
		<div id="inactivedetails" style="display: none;">
			<img alt="" src="completed-task.png" title="Show Active Accounts"
				class="iconbtn iconimg" onclick="showActive();">
			<table id="accountstable">
				<tr>
					<td colspan="7"><h1>Account Details</h1></td>
				</tr>


				<tr  class="hrow">
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

				if (inActiveAccountList.isEmpty()) {
				%>
				<tr>
					<td colspan="7" class="detcenter">No Inactive Account Records!</td>
				</tr>
				<%
				} else {
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
						class="idanchor"><img alt="" src="check-mark.png"
							title="Activate" class="anchorimg"></a></td>
					<%-- 						<%=accounts.getStatus()%> --%>
				</tr>

				<%
				}
				}
				}
				%>
			</table>

		</div>
	</div>
</body>
</html>