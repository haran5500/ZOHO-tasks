<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="banking.BankingLogic"%>

<%@ page import="customer.CustomerData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add or Update Customer</title>

<script>
	function resetform() {
		document.getElementById("customerform").reset();
	}

	function validateCustomerForm() {
		var customerId = document.getElementById("custId");
		var customerName = document.getElementById("custName");
		var gender = document.getElementsByName("Gender");

		var city = document.getElementById("city");
		var mobileNum = document.getElementById("mobileNo");
		var errorvalue = document.getElementById("error");

		if (document.body.contains(customerId)) {
			if (customerId.value === '') {
				customerId.focus();
				errorvalue.innerHTML = "***Customer ID cannot be Empty!";
				return false;
			}
			// 			else
			// 				{
			// 				return true;
			// 				}
		}

		if (customerName.value === '') {
			customerName.focus();
			errorvalue.innerHTML = "***Customer Name cannot be Empty!";
			return false;
		} else if (!gender[0].checked) {
			if (!gender[1].checked) {
				errorvalue.innerHTML = "***Select the Customer Gender!";
				return false;
			}
		} else if (city.value === '') {
			city.focus();
			errorvalue.innerHTML = "***Customer City cannot be Empty!";
			return false;
		} else if (mobileNum.value === '') {
			mobileNum.focus();
			errorvalue.innerHTML = "***Mobile Number cannot be Empty!";
			return false;
		} else if (isNaN(mobileNum.value)) {
			mobileNum.focus();
			errorvalue.innerHTML = "***Mobile Number must be a Number!";
			return false;
		} else {
			return true;
		}
	}
</script>

</head>
<body>

	<jsp:include page="MenuBar.jsp" />
	<div class="div">
		<%
		long customerId = Long.valueOf(request.getParameter("CustomerId"));
		BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
		CustomerData customer = null;

		// 		if (request.getParameter("CustomerId") == null || request.getParameter("CustomerId").equals("0")) {
		if (customerId == 0) {
		%>
		<form action="addCustomer" method="post" id="customerform"
			onsubmit="return validateCustomerForm();">
			<fieldset>

				<legend>
					<img alt="" src="add-user.png">
				</legend>
				<table class="formTable">

					<tr>
						<td colspan="2" style="text-align: center;"><h1>Add
								Customer</h1></td>
					</tr>
					<%
					} else {

					customer = logic.getCustomerDetailsByID(customerId);
					%>
					<form action="updateInfo" method="post" id="customerform"
						onsubmit="return validateCustomerForm();">

						<fieldset>

							<legend>
								<img alt="" src="add-user.png">
							</legend>
							<table class="formTable">

								<tr>
									<td colspan="2" style="text-align: center;"><h1>Update
											Customer</h1></td>
								</tr>
								<tr>
									<td><label class="required">Customer ID</label></td>
									<td><input type="text" name="CustomerID"
										placeholder="Customer ID" readonly="readonly"
										<%if (customer != null) {%> value="<%=customer.getId()%>"
										<%}%> required id="custId"
										onkeypress="return isNumberKey(event);"></td>
								</tr>

								<%
								}
								%>

								<tr>
									<td><label class="required">Customer Name</label></td>
									<td><input type="text" name="CustomerName"
										autofocus="autofocus" placeholder="Customer Name"
										<%if (customer != null) {%> value="<%=customer.getName()%>"
										<%} else {%> value="" <%}%> required id="custName"></td>
								</tr>
								<tr>
									<td><label class="required">Gender</label></td>
									<td><input type="radio" id="Gender" name="Gender"
										value="Male"
										<%if (customer != null) {
	if (customer.getGender().equals("Male")) {%>
										checked <%}
}%> required><label for="Gender">Male</label>
										<input type="radio" id="Gender1" name="Gender"
										<%if (customer != null) {
	if (customer.getGender().equals("Female")) {%>
										checked <%}
}%> value="Female" required><label
										for="Gender1">Female</label></td>
								</tr>
								<tr>
									<td><label class="required">City</label></td>
									<td><input type="text" name="City" id="city"
										placeholder="Resident City" <%if (customer != null) {%>
										value="<%=customer.getCity()%>" <%} else {%> value="" <%}%>
										required></td>
								</tr>
								<tr>
									<td><label class="required">Mobile Number</label></td>
									<td><input type="text" name="MobileNo" minlength="10"
										maxlength="10" required id="mobileNo"
										onkeypress="return isNumberKey(event);"
										placeholder="Mobile Number" <%if (customer != null) {%>
										value="<%=customer.getMobileNo()%>" <%} else {%> value=""
										<%}%>></td>
								</tr>
								<tr>
									<td><input type="button" name="Reset" value="Reset"
										onclick="resetform();" class="accResetbtn"></td>

									<%
									if (customerId == 0) {
									%>
									<td><input type="submit" name="Add Customer"
										value="Add Customer" class="accSubmitbtn"></td>

								</tr>
								<%
								} else {
								%>

								<td><input type="submit" name="Update Info"
									value="Update Info" class="accSubmitbtn"></td>
								</tr>
								<%
								}
								%>
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
					</div>
</body>
</html>