<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link href="CommonStyles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="CommonScript.js"></script>

<script>
	// 	var error = '${errorMessage}';
	document.getElementById("error").innerHTML = '${errorMessage}';

	if (performance.navigation.type == performance.navigation.TYPE_RELOAD) {

		document.getElementById("error").innerHTML = '';
	}

	function resetform() {
		document.getElementById("loginform").reset();
	}
</script>

</head>
<body>
	<div class="div">
		<form id="loginform" action="login" method="post"
			onsubmit=" return validate();">
			<fieldset>
				<legend class="legend">
					<img alt="" src="icons8-login-50.png">
				</legend>
				<h1 style="text-align: center; font-size: 35px;">Login</h1>
				<label class="required"> UserID </label> <br> <br> <input
					type="text" placeholder="UserID" name="UserID" required id="UserId"
					autofocus="autofocus" onkeypress="return isNumberKey(event);">
				<br> <br> <label class="required"> Password </label> <br>
				<br> <input type="password" placeholder="Password"
					name="Password" id="showp" required
					onkeypress="return isSpaceEntered(event);"> <br> <br>
				<input type="checkbox" onclick="Toggle();" id="showpassword"
					name="showpassword"><label for="showpassword">Show
					Password</label> <input type="button" name="Reset" value="Reset"
					onclick="resetform();" class="loginReset"> <br> <br>
				<h3 class="error" id="error">
					<%
					if (request.getAttribute("errorMessage") != null) {
						out.println("***" + request.getAttribute("errorMessage") + "**!");
					}
					%>
				</h3>
				<br> <br> <input type="submit" name="Login" value="Login"
					class="loginSubmit">
			</fieldset>
		</form>

	</div>
</body>
</html>