<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="banking.BankingLogic"%>

<%@ page import="customer.CustomerData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add or Update Customer2</title>

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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

<script>
	$(function() {
		$("#submitForm").click(function() {
			if (validateCustomerForm() === true) {
				var url = "";
				var set = "";

				if ($("#customerform").attr("name") === "addform") {

					url = "addCustomers";

					set = {

						customerName : $("#custName").val(),
						gender : $("#Gender").val(),
						city : $("#city").val(),
						mobileNum : $("#mobileNo").val(),

					};

				} else if ($("#customerform").attr("name") === "updateform") {
					url = "updateInfos";
					set = {

						customerId : $("#custId").val(),
						customerName : $("#custName").val(),
						gender : $("#Gender").val(),
						city : $("#city").val(),
						mobileNum : $("#mobileNo").val(),

					};
				}

				$.post(url, set, function(data) {

					var objkeys = $.parseJSON(data);

					$.each(objkeys, function(a, b) {

						if (a === 'successMessage') {
							window.location.href = "CustomerList.jsp";
						} else if (a === 'errorMessage') {
							alert('error');
							$("#error").html(objkeys[a]);
						}
					});

				});

			}
		});
	});
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
		<form id="customerform" name="addform">
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
					<form id="customerform" name="updateform">
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
									<td><input type="button" name="Add Customer"
										value="Add Customer" class="accSubmitbtn" id="submitForm"></td>

								</tr>
								<%
								} else {
								%>

								<td><input type="button" name="Update Info"
									value="Update Info" class="accSubmitbtn" id="submitForm"></td>
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