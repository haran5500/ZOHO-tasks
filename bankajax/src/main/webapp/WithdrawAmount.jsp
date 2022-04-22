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
<title>Withdraw Amount</title>
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>

<script>
	function resetform() {
		document.getElementById("transferform").reset();
	}

	function validateWdlForm() {
		var accountNum = document.getElementById("accNum");
		var amount = document.getElementById("amount");
		var message = document.getElementById('error');

		if (accountNum.value === '') {
			accountNum.focus();
			message.innerHTML = "***Account Number cannot be Empty!";
			return false;
		} else if (isNaN(accountNum.value)) {
			accountNum.focus();
			message.innerHTML = "***Account Number must be Number!"
			return false;
		}

		else if (amount.value === '') {
			amount.focus();
			message.innerHTML = "***Amount cannot be Empty!";
			return false;
		} else if (isNaN(amount.value)) {
			amount.focus();
			message.innerHTML = "***Amount must be a Number";
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
	%>
	<jsp:include page="MenuBar.jsp" />


	<div class="div">
		<form action="withdraw" method="post" id="transferform"
			onsubmit="return validateWdlForm();">
			<fieldset>

				<legend>
					<img alt="" src="cash-withdrawal.png">
				</legend>
				<table class="wdltable">
					<tr>
						<td><h2>Withdraw Amount</h2></td>
					</tr>
					<tr>
						<td><label class="required">Account Number</label></td>
					</tr>
					<tr>
						<td><select autofocus="autofocus" name="AccountNumber"
							required="required" class="dropdn" id="accNum"
							onchange="ajaxInvoke();">
								<option selected disabled value="">Select Account
									Number</option>
								<%
								BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
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
								%>
						</select></td>

						<!-- 						<td><input type="text" name="accountnumber" min="6" -->
						<!-- 							maxlength="10" required onkeypress="return isNumberKey(event);" -->
						<!-- 							placeholder="Account Number"></td> -->

					</tr>
					<tr>
						<td style="display: none;" id="balCol"><label>Available
								Balance:<input type="text" id="balDisp" readonly="readonly">
						</label></td>
					</tr>
					<tr>
						<td><label class="required">Amount</label></td>
					</tr>
					<tr>
						<td><input type="text" name="amount" min="1" maxlength="10"
							required onkeypress="return isNumberKey(event);"
							placeholder="Amount to be Withdrawn" id="amount"></td>
					</tr>
					<tr>
						<td><h3 class="error" id="error"></h3></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"><input
							type="submit" name="WithdrawAmount" value="Withdraw Amount"
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
			$('#accNum').change(function() {
				var accNumber = $('#accNum').val();
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