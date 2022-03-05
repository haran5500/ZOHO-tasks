<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
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

.legend {
	text-align: center;
}

fieldset {
	width: 100%;
	border: none;
	/* 	 box-shadow:2px 2px green; */
}

#loginform {
	/* box-shadow: 2px 2px 5px 5px black; */
	width: 50%;
	background-color: whitesmoke;
	/* 	background-image: linear-gradient(to bottom right, darkblue 50%, mediumorchid 40%); */
	border-radius: 5px;
}

.required:after {
	content: " *";
	color: red;
}

::placeholder {
	/* color: white; */
	opacity: 1; /* Firefox */
}

input[type="text"], input[type="password"] {
	height: 40px;
	width: 80%;
	border: none;
	border-bottom: 2px solid black;
	outline: none;
	background: none;
}

input[type="submit"], input[type="button"] {
	background-color: darkblue;
	color: white;
	border-radius: 25px;
}

input[type="submit"] {
	width: 80%;
	height: 50px;
	border: none;
}

input[type="submit"]:hover {
	background-image: linear-gradient(to bottom right, skyblue, purple, purple, black);
}

input[type="button"] {
	width: 125px;
	height: 35px;
	margin-left: 35%;
	border: none;
}

input[type="button"]:hover {
	background-image: linear-gradient(to bottom right, skyblue, skyblue, skyblue, black);
	color: black;
}

.error {
	color: red;
}
</style>

<script>

	var error = '${errorMessage}';
 	document.getElementById("error").innerHTML = error;
	
	if (performance.navigation.type == performance.navigation.TYPE_RELOAD) {
		
		alert(error);
		document.getElementById("error").innerHTML = '';
	}

	function isNumberKey(evt)
	{
		var charCode = (evt.which) ? evt.which : event.keyCode
		if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

		return true;
	}
	
	function Toggle() {
		var x = document.getElementById("showp");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}

	function resetform() {
		document.getElementById("loginform").reset();
	}
</script>

</head>
<body>
	<div class="div">
		<form id="loginform" action="login" method="post">
			<fieldset>
				<legend class="legend">
					<img alt="" src="icons8-login-50.png">
				</legend>
				<h1 style="text-align: center; font-size: 35px;">Login</h1>
				<label class="required"> UserID </label> <br> <br> <input
					type="text" placeholder="UserID" name="UserID" required
					autofocus="autofocus" onkeypress="return isNumberKey(event);">
				<br> <br> <label class="required"> Password </label> <br>
				<br> <input type="password" placeholder="Password"
					name="Password" id="showp" required> <br> <br> <input
					type="checkbox" onclick="Toggle();" id="showpassword"
					name="showpassword"><label for="showpassword">Show
					Password</label> <input type="button" name="Reset" value="Reset"
					onclick="resetform();"> <br> <br>
				<div class="error" id="error">
					<%
					if (null != request.getAttribute("errorMessage")) {
						out.println("***" + request.getAttribute("errorMessage") + "**!");
					}
					%>
				</div>
				<br> <br> <input type="submit" name="Login" value="Login">
			</fieldset>
		</form>

	</div>

</body>
</html>