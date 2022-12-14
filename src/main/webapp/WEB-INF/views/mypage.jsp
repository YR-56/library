
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

} catch(NullPointerException e) {
	e.printStackTrace();
	System.out.println("セッションが切れました。");
	
	response.sendRedirect("index.jsp");


	
}



%>

<% 
 String returnsuccess = (String)request.getAttribute("returnsuccess");
 String rentsuccess = (String)request.getAttribute("success");
 
 %>
 
 <% if(returnsuccess != null){ %>
 <div class="alert alert-success" role="alert">
 <%= returnsuccess %><%} %>
 </div>
 
 <% if(rentsuccess != null){ %>
 <div class="alert alert-primary" role="alert">
 <%= rentsuccess %><%} %>
 </div>
 
 




<%
String message = "";
message = (String)request.getAttribute("message");

String climbmessage = "";
climbmessage = (String)request.getAttribute("climbmessage");

UsersBean user = (UsersBean) session.getAttribute("user");
int userid = user.getUser_id();

%>


  <%if(message != null) { %>
		<div class="alert alert-primary" role="alert">
		<%= message %></div><%}  %>



 <%if(climbmessage != null) { %>
 <div class="alert alert-success" role="alert">
<%= climbmessage %>
</div><%} %>




  <H2>施設管理システム</H2>
 
  ${sessionScope.user.getUsername()}さん、こんにちは！
  <UL>
	<LI><A href='SelectBookServlet'>図書レンタル</A>
	<LI><A href='UserChange.jsp'>ユーザー情報変更</A>
	<LI><A href='deleteForm.html'>ログアウト</A>
	<LI><A href='UsageStatusServlet'>食堂/入浴場使用状況</A>
	<%if(userid == 24) {%>
	<LI><A href='SelectlendingServlet'>管理者ページ</A>
	<%} %>
	
  </UL>
  
  <TABLE BORDER ALIGN="left">
   
    
    
  </table>
  
  
  <form action="FrontControllerServlet" method="post">
   
         
  <input type="hidden" name="action" value="logout">
  <input class="btn btn-info" type="submit" value="ログアウト" >
  </form>
   
  
</BODY>
</HTML>