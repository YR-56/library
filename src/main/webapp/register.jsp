<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>

window.onload = () => {

	const checkButton = document.querySelector("#displayPasswordCheck");
	checkbutton.onchange = () => {

	document.querySelector("#password").type = checkButton.checkd ? "text" : "password";

	}

	}


</script>





</head>
<body>

<%String message = "";
 message = (String)request.getAttribute("message");
 String username = (String)request.getAttribute("username");
 String password = (String)request.getAttribute("password");
 
 if(message != null) {
%>
<%= message %>

<%} %>

<form action="FrontControllerServlet" method="post">
		ユーザー名：<br>
		<input type="text" name="username" value="<%= username == null ? "" : username %>"><br>
		パスワード:<br>
		<input type="password" name="password" class="form-control"value="<%= password == null ? "" : password %>"><br>
		
		
		
		<div class="form-check">
   <input class="form-check-input" name="displayPasswordCheck" id="displayPasswordCheck"
type="checkbox">
<label class="form-check-label" for="displayPasswordCheck">パスワードを表示する
</label></div>
		
		<input type="hidden" name="action" value="register">
		<input type="submit"  value="新規登録">
	</form>

</body>
</html>