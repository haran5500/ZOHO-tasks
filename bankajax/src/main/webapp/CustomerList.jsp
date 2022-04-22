<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="customer.CustomerData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>
<title>Customer List</title>
<style type="text/css">
td {
	padding-left: 8px;
	height: 50px;
}
</style>

<script>
	
</script>
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
		<div id="customerdetails" style="display: block;">

			<a href="AddorUpdateCustomer.jsp?CustomerId=0" class="iconbtn"><img
				alt="" class="iconimg" src="add-contact.png" title="Add Customer"></a>
			<img alt="" src="block-user.png" title="Show Inactive Customers"
				class="iconbtn iconimg" onclick="showInactiveCustomer();">
			<table id="customertable">
				<tr>
					<td colspan="6"><h1>Customer Details</h1></td>
				</tr>
				<!-- <tr class="btntd">
										<td colspan="6" class="detright"><a
											href="customeradd.jsp?CustomerId=0" class="button">Add Customer</a></td>
					<td colspan="6" class="detright"></td>
										<input type="button" name="Add Customer" value="Add Customer">
				</tr> -->

				<tr class="hrow">
					<td><Label>CustomerID</Label></td>
					<td><Label>Name</Label></td>
					<td><Label>Gender</Label></td>
					<td><Label>City</Label></td>
					<td><Label>MobileNumber</Label></td>
					<td><Label>Operations</Label></td>
				</tr>

				<%
				BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, CustomerData> customerList = logic.getAllActiveCustomers();
				if (customerList.isEmpty()) {
				%>
				<tr>
					<td colspan="6" class="detcenter">No Customer Records!</td>
				</tr>

				<%
				} else {
				for (long key : customerList.keySet()) {

					CustomerData customer = logic.getCustomerDetailsByID(key);
				%>
				<tr>
					<!-- 					<td><a -->
					<%-- 						href="customeradd.jsp?CustomerId= --%>
					<%-- 						<%=customer.getId()%>" --%>
					<%-- 						class="idanchor"><%=customer.getId()%> </a></td> --%>
					<td><%=customer.getId()%></td>
					<td><%=customer.getName()%></td>
					<td><%=customer.getGender()%></td>
					<td><%=customer.getCity()%></td>
					<td><%=customer.getMobileNo()%></td>
					<td><a
						href="AddorUpdateCustomer.jsp?CustomerId=
											<%=customer.getId()%>"
						class="idanchor"> <img alt="" src="editing.png"
							class="anchorimg" title="Edit Info"></a> <a
						href="deleteCustomer?customerId=<%=customer.getId()%>"
						class="idanchor"><img alt="" src="delete-icon.png"x
							class="anchorimg" title="De-Activate"></a></td>
				</tr>
				<%
				}
				}
				%>
			</table>
		</div>

		<div id="inactivedetails" style="display: none;">
			<img alt="" src="verified-user.png" title="Show Active Customers"
				class="iconbtn iconimg" onclick="showActiveCustomer();">
			<table id="customertable">
				<tr>
					<td colspan="6"><h1>Customer Details</h1></td>
				</tr>


				<tr class="hrow">
					<td><Label>CustomerID</Label></td>
					<td><Label>Name</Label></td>
					<td><Label>Gender</Label></td>
					<td><Label>City</Label></td>
					<td><Label>MobileNumber</Label></td>
					<td><Label>Operations</Label></td>
				</tr>

				<%
				logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, CustomerData> inActiveList = logic.getAllInActiveCustomers();

				if (inActiveList.isEmpty()) {
				%>
				<tr>
					<td colspan="6" class="detcenter">No Inactive Customer
						Records!</td>
				</tr>

				<%
				} else {
				for (long key : inActiveList.keySet()) {

					CustomerData customer = logic.getCustomerDetailsByID(key);
				%>
				<tr>
					<td><a
						href="AddorUpdateCustomer.jsp?CustomerId=
											<%=customer.getId()%>"
						class="idanchor"><%=customer.getId()%> </a></td>
					<td><%=customer.getName()%></td>
					<td><%=customer.getGender()%></td>
					<td><%=customer.getCity()%></td>
					<td><%=customer.getMobileNo()%></td>
					<td><a
						href="activateCustomer?customerId=<%=customer.getId()%>"
						class="idanchor"><img alt="" src="customer.png"
							title="Activate" class="anchorimg"></a></td>
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