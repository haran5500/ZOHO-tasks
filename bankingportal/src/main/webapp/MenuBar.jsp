<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Menu</title>

<style type="text/css">
* {
	font-family: Helvetica;
	font-size: 22px;
}

#menu {
	text-align: center;
	background-color: darkslategrey;
	width: 100%;
	height: 150px;
}

#menu #div1 {
	color: white;
	margin-top: 10px;
	display: block;
	text-align: center;
	margin-top: 10px;
}

#menu #div2 {
	margin-top: 10px;
	display: inline-block;
	color: white;
	font-weight: bolder;
	width: 100%;
}

.detcenter {
	text-align: center;
}

.detright {
	text-align: right;
}

.menubutton {
	/* 	background-color: darkblue; */
	background: none;
	border: none;
	height: 40px;
	border-radius: 25px;
	color: ghostWhite;
	font-weight: bold;
}

.anchor {
	text-decoration: none;
	width: auto;
	height: 25px;
	padding: 10px;
	text-align: center;
	font-weight: bold;
	line-height: 25px;
	/* 	background: darkblue; */
	border-radius: 25px;
	color: ghostWhite;
	height: auto;
	display: inline-block;
}

.menubutton:hover, .anchor:hover {
	background-color: indigo;
	box-shadow: 0px 5px 5px 0px #ffa260;
	cursor: grab;
}


</style>

<script type="text/javascript">
	function customerView() {

		var custbtn = document.getElementById("custbtn");
		var accbtn = document.getElementById("accbtn");

		var custview = document.getElementById("customerdetails");
		var accview = document.getElementById("accdetails");

		if (document.body.contains(custview)) {

			if (custbtn.style.display === "inline-block") {
				custbtn.style.display = "none";
			}

			if (accbtn.style.display = "none") {
				accbtn.style.display = "inline-block";
			}

			if (custview.style.display === "none") {
				custview.style.display = "block";
			}

			if (accview.style.display === "block") {
				accview.style.display = "none";
			}
		} else {
			document.forms[0].action = "AdminDashBoard.jsp";
			document.forms[0].method = "post"; // "get"
			document.forms[0].submit();
		}
	}

	function accountView() {

		var custbtn = document.getElementById("custbtn");
		var accbtn = document.getElementById("accbtn");

		var custview = document.getElementById("customerdetails");
		var accview = document.getElementById("accdetails");

		if (document.body.contains(accview)) {

			if (accbtn.style.display === "inline-block") {
				accbtn.style.display = "none";
			}

			if (custbtn.style.display === "none") {
				custbtn.style.display = "inline-block";
			}

			if (accview.style.display === "none") {
				accview.style.display = "block";
			}

			if (custview.style.display === "block") {
				custview.style.display = "none";
			}
		} else {
			document.forms[0].action = "AdminDashBoard.jsp";
			document.forms[0].method = "post"; // "get"
			document.forms[0].submit();
		}
	}

	function showProfile() {

		var div = document.getElementById("profile");

		if (document.body.contains(div)) {
			var accdiv = document.getElementById("accounts");

			if (div.style.display === "none") {

				div.style.display = "block";

			} else {
				div.style.display = "none";
			}
		} else {
			document.forms[0].action = "UserDashBoard.jsp";
			document.forms[0].method = "post"; // "get"
			document.forms[0].submit();
		}
	}
</script>


</head>
<body>

	<div id="menu">
		<%
		String roleValue = (String) session.getAttribute("Role");
		if (roleValue.equals("Admin")) {
		%>
		<div id="div1">
			<h1>Welcome Admin!</h1>
			<h2>DashBoard</h2>
		</div>
		<div id="div2">
			<input type="button" name="Customers" value="View Customers"
				id="custbtn" onclick="customerView();"
				style="display: inline-block;" class="menubutton"> <input
				type="button" name="Accounts" value="View Accounts" id="accbtn"
				onclick="accountView();" style="display: inline-block;"
				class="menubutton"> <a href="WithdrawAmount.jsp"
				class="anchor">Withdraw Amount </a> <a href="DepositAmount.jsp"
				class="anchor">Deposit Amount</a> <a href="TransferAmount.jsp"
				class="anchor">Transfer to Account </a> <input type="button"
				name="Logout" value="Logout" class="menubutton">
		</div>
		<%
		} else {
			String userName=(String) session.getAttribute("Name");
					
		%>
		<div id="div1">
			<h1><% out.print(userName); %></h1>
			<h2>DashBoard</h2>
		</div>
		<div id="div2">

		<input type="button" name="Show Profile" value="Profile"
			onclick="showProfile();" class="menubutton"> <a
			href="TransferAmount.jsp" class="anchor">Transfer to Account </a> <input
			type="button" name="Logout" value="Logout" class="menubutton">
			</div>
	<%
	}
	%>



	</div>

</body>
</html>