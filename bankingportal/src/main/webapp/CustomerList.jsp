<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="customer.CustomerData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>

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
	border:none;
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

		var activeDiv = document.getElementById('customerdetails');
		var inActiveDiv = document.getElementById('inactivedetails');

		if (activeDiv.style.display === "none") {
			activeDiv.style.display = "block";
		}

		if (inActiveDiv.style.display === "block") {

			inActiveDiv.style.display = "none";
		}

	}

	function showInactive() {
		var activeDiv = document.getElementById('customerdetails');
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
		<div id="customerdetails" style="display: block;">
			<table id="customertable">
				<tr>
					<td colspan="6"><h1>Customer Details</h1></td>
				</tr>
				<tr>
					<!-- 					<td colspan="6" class="detright"><a -->
					<!-- 						href="customeradd.jsp?CustomerId=0" class="button">Add Customer</a></td> -->
					<td colspan="6" class="detright"><a
						href="AddorUpdateCustomer.jsp?CustomerId=0" class="button">Add
							Customer</a>
						<button class="showbtn" onclick="showInactive();">Show
							InActive Customers</button></td>
					<!-- 					<input type="button" name="Add Customer" value="Add Customer"> -->
				</tr>

				<tr style="font-weight: bold;">
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

				for (long key : customerList.keySet()) {

					CustomerData customer = logic.getCustomerDetailsByID(key);
				%>
				<tr>
					<!-- 					<td><a -->
					<%-- 						href="customeradd.jsp?CustomerId= --%>
					<%-- 						<%=customer.getId()%>" --%>
					<%-- 						class="idanchor"><%=customer.getId()%> </a></td> --%>
					<td><a
						href="AddorUpdateCustomer.jsp?CustomerId=
											<%=customer.getId()%>"
						class="idanchor"><%=customer.getId()%> </a></td>
					<td><%=customer.getName()%></td>
					<td><%=customer.getGender()%></td>
					<td><%=customer.getCity()%></td>
					<td><%=customer.getMobileNo()%></td>
					<td><a href="deleteCustomer?customerId=<%=customer.getId()%>"
						class="idanchor">delete</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>

		<div id="inactivedetails" style="display: none;">
			<table id="customertable">
				<tr>
					<td colspan="6"><h1>Customer Details</h1></td>
				</tr>
				<tr>
					<!-- 					<td colspan="6" class="detright"><a -->
					<!-- 						href="customeradd.jsp?CustomerId=0" class="button">Add Customer</a></td> -->
					<td colspan="6" class="detright"><button class="showbtn"
							onclick="showActive();">Show Active Customers</button></td>
				</tr>

				<tr style="font-weight: bold;">
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
						class="idanchor">Activate</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>