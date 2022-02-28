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

#customerdetails, #accdetails {
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
border:1px solid black;	

}

td{
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

.button {
	float: right;
	display: block;
}

.button1 {
	display: inline-block;
}

.button:hover {
	box-shadow: 0px 5px 5px 0px lightgreen;
}

#maindiv {
	position: relative;
}

.idanchor {
	text-decoration: none;
}
</style>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />

	<div id="maindiv">
		<div id="customerdetails">
			<table id="customertable">
				<tr>
					<td colspan="6"><h1>Customer
							Details</h1></td>
				</tr>
				<tr>
					<td colspan="6" class="detright"><a href="AddCustomer.jsp"
						class="button">Add Customer</a> <!-- 				<input type="button" name="Add Customer" value="Add Customer"></td> -->
				</tr>

				<tr style="font-weight: bold;">
					<td><Label>CustomerID</Label></td>
					<td><Label>Name</Label></td>
					<td><Label>Gender</Label></td>
					<td><Label>City</Label></td>
					<td><Label>MobileNumber</Label></td>
					<td><Label></Label>Status</td>
				</tr>

				<%
				BankingLogic logic = (BankingLogic) request.getServletContext().getAttribute("logicApi");
				Map<Long, CustomerData> customerList = logic.getAllCustomerDetails();

				for (long key : customerList.keySet()) {

					CustomerData customer = logic.getCustomerDetailsByID(key);
				%>
				<tr>
					<td><a
						href="AddCustomer.jsp?CustomerId=
						<%=customer.getId()%>"
						class="idanchor"><%=customer.getId()%> </a></td>
					<td><%=customer.getName()%></td>
					<td><%=customer.getGender()%></td>
					<td><%=customer.getCity()%></td>
					<td><%=customer.getMobileNo()%></td>
					<td><%=customer.getStatus()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>