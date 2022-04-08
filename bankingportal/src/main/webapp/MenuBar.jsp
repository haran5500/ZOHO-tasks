<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Bar</title>
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>

<script type="text/javascript">
	function showOpt() {

		var profElem = document.getElementById('profdiv1');
		if (profElem.style.display === 'none') {

			profElem.style.display = 'block';
		} else {
			profElem.style.display = 'none';
		}
	}
</script>


</head>
<body>
	<div id="menu">
		<%
		String userName = "";
		if (session.getAttribute("Role") == null) {
		%>
		<jsp:forward page="LoginPage.jsp"></jsp:forward>
		<%
		} else {
		String roleValue = (String) session.getAttribute("Role");
		if (roleValue.equals("Admin")) {
		%>

		<div id="div2">
			<a href="CustomerList.jsp" class="anchor">CustomerList </a> <a
				href="AdminDashBoard.jsp" class="anchor">Account List </a> <a
				href="WithdrawAmount.jsp" class="anchor">Withdraw Amount </a> <a
				href="DepositAmount.jsp" class="anchor">Deposit Amount</a>


			<%
			} else if (roleValue.equals("Customer")) {
			userName = (String) request.getParameter("UserName");
			%>



			<div id="div2">

				<input type="button" name="Show Profile" value="Profile"
					onclick="showProfile();" class="menubutton"> <a
					href="TransferAmount.jsp" class="anchor">Transfer to Account </a>

				<!-- 				 <input -->
				<!-- 				type="button" name="Logout" value="Logout" class="menubutton" -->
				<!-- 				onclick="logoutSession();"> -->

				<%
				}
				%>


				<a href="TransferAmount.jsp" class="anchor">Transfer to Account
				</a> <img alt="" src="user.png" onclick="showOpt();" id="profimg"
					title="profile">
				<div id="profdiv1" style="display: none;">

					<h1 style="display: inline-block;">

						<%
						if (userName.equals("")) {
						%>

						Admin!

						<%
						} else {
						%>
						Hi
						<%
						out.print(userName);
						}
						}
						%>

					</h1>
					<a href="logout" class="logoutbtn" style="display: inline-block;">Logout</a>
				</div>
			</div>
		</div>
</body>
</html>