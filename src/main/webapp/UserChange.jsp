<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.UsersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録上情報変更</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">



</head>
<body>
<% 
try {
UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得

String username = user.getUsername();
} catch(NullPointerException e) {
	e.printStackTrace();
	System.out.println("不正なリダイレクトです");
	
	response.sendRedirect("index.jsp");


	
}
%>
	<h1>システム　～ログイン～</h1>
	<%
String message = "";
message = (String)request.getAttribute("message");
%>
	
	 
	<form action="FrontControllerServlet" method="post">
	
	
		ユーザー名：  ${sessionScope.user.getUsername()}  <br>
		変更後ユーザー名:
		<input type="text" name="afterusername"><br>
		現在のパスワード:<br>
		<input type="text" name="password"><br>
		
		変更後パスワード:<br>
		<input type="text" name="afterpassword"><br>
		
		
		
		<input type="hidden" name="action" value="change">
		
		
		<input type="submit" value="変更する">
	</form>
	
	
</body>
</html>