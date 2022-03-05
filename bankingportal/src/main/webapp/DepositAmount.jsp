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
<title>Deposit Amount</title>
<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
}

.div {
	margin-top: 10%;
	margin-left: 25%;
}

legend img {
	height: 75px;
	width: 75px;
}

legend {
	text-align: center;
}

fieldset {
	border: none;
	text-align: center;
}

input[type="text"], .dropdn {
	height: 40px;
	width: 80%;
	border: none;
	border-bottom: 2px solid black;
	outline: none;
	background: none;
}

input[type="text"]:focus, .dropdn:focus {
	background: white;
}

.required {
	font-weight: bold;
}

.required:after {
	content: " *";
	color: red;
}

#transferform {
	width: 50%;
	background-color: whitesmoke;
	border-radius: 5px;
}

table {
	width: 100%;
	height: 450px;
}

td {
	text-align: center;
	height: 50px;
}

input[type="submit"], .resetbtn {
	height: 50px;
	border: none;
	background-color: darkblue;
	color: white;
	border-radius: 25px;
}

.resetbtn {
	width: 20%;
}

input[type="submit"] {
	width: 40%;
	margin-left: 10px;
}

input[type="submit"]:hover {
	background-color: forestgreen;
	color: black;
	box-shadow: 0px 5px 5px 0px darkblue;
}

.resetbtn:hover {
	background-color: tomato;
	color: black;
	box-shadow: 0px 5px 5px 0px darkblue;
}
</style>
<script>
function isNumberKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;

	return true;
}

	function resetform() {
		document.getElementById("transferform").reset();
	}
</script>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />


	<div class="div">
		<form action="deposit" method="post" id="transferform">
			<fieldset>

				<legend>
					<img alt="" src="arrow.png">
				</legend>
				<table>
					<tr>
						<td><h2>Deposit Amount</h2></td>
					</tr>
					<tr>
						<td><label class="required">Account Number</label></td>
					</tr>
					<tr>
						<td><select autofocus="autofocus" name="AccountNumber"
							required="required" class="dropdn">
								<option selected disabled value="">Select Account
									Number</option>
								<%
								BankingLogic logic = new BankingLogic();
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

						<!-- 						<td><input type="text" name="accountnumber" min="6" -->
						<!-- 							maxlength="10" required onkeypress="return isNumberKey(event);" -->
						<!-- 							placeholder="Account Number"></td> -->

					</tr>
					<tr>
						<td><label class="required">Amount</label></td>
					</tr>
					<tr>
						<td><input type="text" name="amount" min="1" maxlength="10"
							required onkeypress="return isNumberKey(event);"
							placeholder="Amount to be Deposited"></td>
					</tr>
					<tr>
						<td><input type="button" name="Reset" value="Reset"
							onclick="resetform();" class="resetbtn"><input
							type="submit" name="DepositAmount" value="Deposit Amount"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>