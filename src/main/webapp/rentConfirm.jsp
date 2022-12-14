
<%@page import="bean.BookBean" %>
<%@page import="bean.UsersBean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>この本を借りますか？</title>
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

<h1>本当にこの本を借りますか？</h1>

<% 

String bookid = (String) request.getParameter("bookid");
String title = (String) request.getParameter("title");
String author = (String) request.getParameter("author");
String published_year = (String) request.getParameter("published_year");


%>

<tr>

<td><%= bookid %></td>
<td><%= title %></td>
<td><%= author %></td>
<td><%= published_year %></td>



</tr>

  <form action="FrontControllerServlet" method="post">
     <input type="hidden" name="bookid" value="<%= bookid %>">
     <input type="hidden" name="title" value="<%= title %>">
     <input type="hidden" name="action" value="bookconfirm">
     <input type="submit" value="借りる">
</form>

  

</body>
</html>