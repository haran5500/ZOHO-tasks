<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account List</title>
</head>
<body>

	<jsp:include page="MenuBar.jsp" />


	<div id="accdetails">
		<table id="accountstable">
			<tr>
				<td colspan="7" class="detcenter"><h1>Account Details</h1></td>
			</tr>
			<tr>
				<td colspan="7" class="detright"><a href="AddAccount.jsp"
					class="button">Add Account</a> <!-- 				<input type="button" name="Add Account" value="Add Account"></td> -->
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><Label>Account Number</Label></td>
				<td><Label>Customer ID</Label></td>
				<td><Label>Account Type</Label></td>
				<td><Label>Branch</Label></td>
				<td><Label>IFSC Code</Label></td>
				<td><Label>Account Balance</Label></td>
				<td><Label>Status</Label></td>
				<!-- 				<td><Label>Operations</Label></td> -->
			</tr>

			<%-- 				<c:forEach items="${AccountList}" var="current"> --%>
			<%-- 					<c:forEach items="${current.value}" var="current1"> --%>
			<!-- 						<tr> -->
			<%-- 							<td><c:out value="${current.key}" /></td> --%>
			<%-- 							<td><c:out value="${current1.key}" /></td> --%>
			<%-- 							<td><c:out value="${current1.value.getAccType()}" /></td> --%>
			<%-- 							<td><c:out value="${current1.value.getLocation()}" /></td> --%>
			<%-- 							<td><c:out value="${current1.value.getIfscCode()}" /></td> --%>
			<%-- 							<td><c:out value="${current1.value.getBalance()}" /></td> --%>
			<%-- 							<td><c:out value="${current1.value.getStatus()}" /></td> --%>
			<!-- 						</tr> -->
			<%-- 					</c:forEach> --%>
			<%-- 				</c:forEach> --%>
		</table>
	</div>

</body>
</html>