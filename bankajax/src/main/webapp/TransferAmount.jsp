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
<title>Money Transfer</title>
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>

<style type="text/css">
</style>
<script>
	function resetform() {
		document.getElementById("transferform").reset();
	}

	function validateTransferForm() {
		var fromAccNum = document.getElementById("fromAccount");
		var toAccNum = document.getElementById("toAccount");
		var amount = document.getElementById("amount");
		var message = document.getElementById('error');

		if (fromAccNum.value === '') {
			fromAccNum.focus();
			message.innerHTML = "***Account Number cannot be Empty!";
			return false;
		} else if (isNaN(fromAccNum.value)) {
			fromAccNum.focus();
			message.innerHTML = "***Account Number must be Number!";
			return false;
		} else if (toAccNum.value === '') {
			toAccNum.focus();
			message.innerHTML = "***Account Number cannot be Empty!";
			return false;
		} else if (isNaN(toAccNum.value)) {
			toAccNum.focus();
			message.innerHTML = "***Account Number must be Number!";
			return false;
		} else if (amount.value === '') {
			amount.focus();
			message.innerHTML = "***Amount cannot be Empty!";
			return false;
		} else if (isNaN(amount.value)) {
			amount.focus();
			message.innerHTML = "***Amount must be a Number";
			return false;
		} else if (fromAccNum.value === toAccNum.value) {
			toAccNum.focus();
			message.innerHTML = "***From and To Account cannot be same!"
			return false;
		} else if (parseFloat(amount.value) > document
				.getElementById('balDisp').value) {

			amount.focus();
			message.innerHTML = "***Low Balance!";
			return false;
		}

		else {

			return true;
		}

	}
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
	String roleValue = (String) session.getAttribute("Role");
	if (roleValue.equals("Admin")) {
	%>
	<jsp:include page="MenuBar.jsp" />
	<%
	} else {

	long custId = (long) session.getAttribute("customerId");
	CustomerData customer = logic.getCustomerDetailsByID(custId);
	%>
	<jsp:include page="MenuBar.jsp">
		<jsp:param value="<%=customer.getName()%>" name="UserName" />
	</jsp:include>
	<%
	}
	%>
	<div class="div">
		<form action="transfer" method="post" id="transferform"
			onsubmit="return validateTransferForm();">
			<fieldset>

				<legend>
					<img alt="" src="money-transfer.png">
				</legend>
				<table class="wdltable">
					<tr>
						<td><h2>Money Transfer</h2></td>
					</tr>
					<tr>
						<td><label class="required">From Account</label></td>
					</tr>
					<tr>
						<td><select autofocus="autofocus" name="fromAccountNumber"
							required="required" class="dropdn" id="fromAccount">
								<option selected disabled value="">Select Account
									Number</option>
								<%
								if (roleValue.equals("Admin")) {
									Map<Long, Map<Long, AccountData>> accountsMap = logic.getAllActiveAccounts();
									for (long key : accountsMap.keySet()) {
										Map<Long, AccountData> subAccountsMap = logic.getActiveAccounts(key);
										for (long subKey : subAccountsMap.keySet()) {
									if (!subAccountsMap.get(subKey).getAccType().equals("Deposit")) {
								%>
								<option value="<%=subKey%>"><%=subKey%></option>
								<%
								}
								}
								}
								} else {
								long custId = (long) session.getAttribute("customerId");
								Map<Long, AccountData> subAccountsMap = logic.getActiveAccounts(custId);
								for (long subKey : subAccountsMap.keySet()) {
								if (!subAccountsMap.get(subKey).getAccType().equals("Deposit")) {
								%>
								<option value="<%=subKey%>"><%=subKey%></option>
								<%
								}
								}
								}
								%>

						</select></td>
						<!-- 						<td><input type="text" name="fromaccount" min="6" -->
						<!-- 							maxlength="10" required onkeypress="return isNumberKey(event);" -->
						<!-- 							placeholder="From Account Number" autofocus="autofocus"></td> -->

					</tr>
					<tr>
						<td style="display: none;" id="balCol"><label>Available
								Balance:<input type="text" id="balDisp" readonly="readonly">
						</label></td>
					</tr>
					<tr>
						<td><label class="required">To Account</label></td>
					</tr>
					<tr>
						<%
						if (roleValue.equals("Admin")) {
						%>
						<td><select autofocus="autofocus" name="toAccountNumber"
							required="required" class="dropdn" id="toAccount">
								<option selected disabled value="">Select Account
									Number</option>
								<%
								Map<Long, Map<Long, AccountData>> accountsMap = logic.getAllActiveAccounts();
								for (long key : accountsMap.keySet()) {
									Map<Long, AccountData> subAccountsMap = logic.getActiveAccounts(key);
									for (long subKey : subAccountsMap.keySet()) {
										if (subAccountsMap.get(subKey).getStatus()) {
								%>
								<option value="<%=subKey%>"><%=subKey%></option>
								<%
								}
								}
								}
								%>
						</select></td>
						<%} else {%>

						<td><input type="text" name="toAccountNumber" min="6"
							maxlength="10" required onkeypress="return isNumberKey(event);"
							placeholder="To Account Number" id="toAccount"></td>
						<%
						}
						%>

					</tr>
					<tr>
						<td><label class="required">Transfer Amount</label></td>
					</tr>
					<tr>
						<td><input type="text" name="amount" min="1" maxlength="10"
							required onkeypress="return isNumberKey(event);"
							placeholder="Amount to be Transferred" id="amount"></td>
					</tr>
					<tr>
						<td><h3 class="error" id="error"></h3></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"><input
							type="submit" name="TransferAmount" value="Transfer Amount"
							class="submitbtn"></td>
					</tr>

				</table>
			</fieldset>


		</form>
		<h3 class="errorText" id="error">
			<%
			if (request.getAttribute("errorMessage") != null) {
				out.println("***" + request.getAttribute("errorMessage") + "**!");
			}
			%>
		</h3>
		<%
		}
		%>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('#fromAccount').change(function() {
				var accNumber = $('#fromAccount').val();
				$('#balCol').show();

				$.ajax({
					url : 'balanceFetch',
					method : 'POST',
					data : {
						fromAccountNumber : accNumber
					},
					success : function(resultText) {
						$('#balDisp').val(resultText);
					},
					error : function(jqXHR, exception) {
						console.log('Error occured!!');
					}
				});
			});
		});
	</script>
	
</body>
</html>