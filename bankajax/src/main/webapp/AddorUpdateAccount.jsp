<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="customer.CustomerData"%>
<%@ page import="account.AccountData"%>
<%@ page import="banking.BankingLogic"%>
<%@ page import="branches.BankBranchDetails"%>
<%@ page import="java.util.Map"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add or Update Account</title>

<script>
	function resetform() {
		document.getElementById("accountform").reset();
	}

	function isSpaceEntered(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;

		if (charCode === 32) {
			document.getElementById('error').innerHTML = "***Space is not valid character!";
			return false;
		}
		document.getElementById('error').innerHTML = '';
		return true;
	}

	function validateAccountForm() {

		var accountNum = document.getElementById("accNum");
		var customerId = document.getElementById("custId");
		var acType = document.getElementById("accType");
		var location = document.getElementById("location");
		var balance = document.getElementById("openBal");
		var errorvalue = document.getElementsById("error");

		if (document.body.contains(accountNum)) {
			if (accountNum.value === '') {
				accountNum.focus();
				errorvalue.innerHTML = "***Account Number cannot be Empty!"
				return false;
			} else if (isNaN(accountNum.value)) {
				accountNum.focus();
				errorvalue.innerHTML = "***Account Number must be a Number!"
				return false;
			}
			// 			else {
			// 				return true;
			// 			}
		}

		if (customerId.value === '') {
			customerId.focus();
			errorvalue.innerHTML = "***CustomerId must be selected!";
			return false;
		} else if (isNaN(customerId.value)) {
			customerId.focus();
			errorvalue.innerHTML = "***CustomerId must be a Number!";
			return false;
		} else if (acType.value === '') {
			acType.focus();
			errorvalue.innerHTML = "***Account Type must be selected!";
			return false;
		} else if (location.value === '') {
			location.focus();
			errorvalue.innerHTML = "***Branch Location must be selected!";
			return false;
		} else if (balance.value === '') {
			balance.focus();
			errorvalue.innerHTML = "***Must specify the Opening Balance!";
			return false;
		} else if (isNaN(balance.value)) {
			balance.focus();
			errorvalue.innerHTML = "***Opening Balance must be a Number!";

			return false;
		}

		else {
			return true;
		}
	}
</script>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />

	<div class="div">

		<%
		long accountId = Long.valueOf(request.getParameter("accountId"));
		BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
		AccountData account = null;
		if (accountId == 0) {
		%>
		<form action="addAccount" method="post" id="accountform"
			onsubmit="return validateAccountForm();">
			<fieldset>

				<legend>
					<img alt="" src="icons8-bank-account-50.png">
				</legend>
				<table class="formTable">
					<tr>
						<td colspan="2" style="text-align: center;"><h1>Add
								Account</h1></td>
					</tr>
					<%
					} else {
					long customerId = logic.getCustomerIDByAccNum(accountId);
					account = logic.getAccountByAccountNum(customerId, accountId);
					%>
					<form action="updateAccount" method="post" id="accountform"
						onsubmit="return validateAccountForm();">
						<fieldset>

							<legend>
								<img alt="" src="icons8-bank-account-50.png">
							</legend>
							<table class="formTable">
								<tr>
									<td colspan="2" style="text-align: center;"><h1>Update
											Account</h1></td>
								</tr>
								<tr>
									<td><label class="required">Account Number</label></td>
									<td><input type="text" name="AccountNumber"
										placeholder="Account Number" readonly="readonly"
										value="<%=account.getAccID()%>" required id="accNum"
										onkeypress="return isNumberKey(event);"></td>
								</tr>
								<%
								}
								%>

								<tr>
									<td><label class="required">CustomerID</label></td>
									<td><select autofocus="autofocus" name="CustomerID"
										required="required" class="dropdn" id="custId">
											<option selected disabled value="">Select Customer
												ID</option>
											<%
											Map<Long, CustomerData> customers = logic.getAllActiveCustomers();

											for (long key : customers.keySet()) {
											%>
											<option value="<%=key%>"
												<%if (account != null) {
	if (account.getCustID() == key) {%>
												selected="selected" <%}
}%>><%=key%></option>
											<%
											}
											%>
									</select></td>
								</tr>
								<tr>
									<td><label class="required">Account Type</label></td>
									<td><select required="required" name="AccType"
										class="dropdn" id="accType">
											<option selected disabled value="">Select Account
												Type</option>
											<%
											BankBranchDetails branch = new BankBranchDetails();
											String[] accTypes = branch.getAccountTypes();
											for (String value : accTypes) {
											%>
											<option value="<%=value%>"
												<%if (account != null) {
	if (account.getAccType().equals(value)) {%>
												selected="selected" <%}
}%>><%=value%></option>
											<%
											}
											%>
									</select></td>
								</tr>
								<tr>
									<td><label class="required">Location</label></td>

									<!-- <td><input type="text" name="Location" placeholder="Location"></td> -->
									<td><select required="required" name="Location"
										class="dropdn" id="location">
											<option selected disabled value="">Select Branch
												Location</option>
											<%
											String[] locations = branch.getBranches();
											for (String value : locations) {
											%>
											<option value="<%=value%>"
												<%if (account != null) {
	if (account.getLocation().equals(value)) {%>
												selected="selected" <%}
}%>><%=value%></option>
											<%
											}
											%>
									</select></td>
								</tr>
								<tr>
									<td><label class="required">Opening Balance</label></td>
									<td><input type="text" name="Balance"
										placeholder="Opening Balance"
										onkeypress="return isNumberKey(event);"
										<%if (account != null) {%> value="<%=account.getBalance()%>"
										readonly="readonly" <%} else {%> value="" <%}%>
										required="required" id="openBal"></td>
								</tr>
								<tr>
									<td><input type="button" name="Reset" value="Reset"
										onclick="resetform();" class="accResetbtn"></td>

									<%
									if (accountId == 0) {
									%>
									<td><input type="submit" name="Add Account"
										value="Add Account" class="accSubmitbtn"></td>
								</tr>
								<%
								} else {
								%>
								<td><input type="submit" name="Update Account"
									value="Update Account" class="accSubmitbtn"></td>
								</tr>
								<%
								}
								%>
							</table>
						</fieldset>
						<h3 class="error" id="error"></h3>
					</form>
					<h3 class="errorText" id="error">
						<%
						if (request.getAttribute("errorMessage") != null) {
							out.println("***" + request.getAttribute("errorMessage") + "**!");
						}
						%>
					</h3>
					</div>
</body>
</html>