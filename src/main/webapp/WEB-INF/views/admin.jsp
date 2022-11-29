<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

String bookid = (String) request.getParameter("bookid");

%>

<form action="FrontControllerServlet" method="post">
		管理者名：<br>
		<input type="text" name="username"><br>
		パスワード:<br>
		<input type="text" name="password"><br>
		
		<input type="hidden" name="bookid" value="<%= bookid %>">
		<input type="hidden" name="action" value="admin">
		<input type="submit" name="action" value="ログイン">
	</form>

</body>
</html>