<%@page import="bean.DiningStatusBean" %>
<%@page import="bean.UsersBean" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    

    
<!DOCTYPE html>
<HEAD>
	<TITLE>施設利用システム</TITLE>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
</HEAD>
<BODY>

<% 
try {
UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得

String username = user.getUsername();
} catch(NullPointerException e) {
	e.printStackTrace();
	System.out.println("beanなし");
	
	response.sendRedirect("index.jsp");


	
}

%>




<%
String message = "";
message = (String)request.getAttribute("message");
%>

<h3><%if(message != null) { %>
		<%= message %><%}  %>
	</h3>


  <H2>施設管理システム</H2>
 
  ${sessionScope.user.getUsername()}さん、こんにちは！
  <UL>
	<LI><A href='SelectBookServlet'>図書レンタル</A>
	<LI><A href='UserChange.jsp'>ユーザー情報変更</A>
	<LI><A href='deleteForm.html'>ログアウト</A>
	<LI><A href='UsageStatusServlet'>食堂/入浴場使用状況</A>
  </UL>
  
  <TABLE BORDER ALIGN="left">
   
    
    
  </table>
  
  
  <form action="FrontControllerServlet" method="post">
   
         
  <input type="hidden" name="action" value="logout">
  <input type="submit" value="返却" >
  </form>
   
  
</BODY>
</HTML>