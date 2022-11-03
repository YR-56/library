<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="servlet.FrontControllerServlet" %>

   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>

</head>
<body>
	<h1>システム　～ログイン～</h1>
	
	 <% String bookid = null;
	
	bookid = (String) request.getParameter("bookid");
	
	
	%>
	
	<%= bookid %>
	
	
	<form action="FrontControllerServlet" method="post">
		ユーザー名：<br>
		<input type="text" name="username" ><br>
		パスワード:<br>
		<%String message = (String)request.getAttribute("message");%>
		<%if(message != null) { %>
		<%= message %><%}  %>
		<input type="text" name="password"><br>
		
		<input type="hidden" name="action" value="login">
		<input type="submit" name="action" value="ログイン">
	</form>
	
	<a href="register.jsp">新規登録</a>
</body>
</html>