<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="FrontControllerServlet" method="post">
		ユーザー名：<br>
		<input type="text" name="username"><br>
		パスワード:<br>
		<input type="text" name="password"><br>
		
		<input type="hidden" name="action" value="register">
		<input type="submit"  value="新規登録">
	</form>

</body>
</html>