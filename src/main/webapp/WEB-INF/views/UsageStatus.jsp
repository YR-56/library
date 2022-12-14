<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="bean.GamingStatusBean" %>
<%@page import="bean.UsersBean" %>
<%@page import="bean.ClimingStatusBean" %>

    
    
    
<%
 
     
      GamingStatusBean usage = (GamingStatusBean)request.getAttribute("GamingStatus");
      int usageCount = usage.getUserCount();
      boolean isfull = usage.isFull();
      
      ClimingStatusBean climb = (ClimingStatusBean)request.getAttribute("climingStatus");
      int climbCount = climb.getUserCount();
      boolean isfullnum = climb.isFull();
      
     
%>


    
<!DOCTYPE html>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/CSS/style.css">

<meta charset="UTF-8">
<title>Insert title here</title>
	
</head>
<body>

<TABLE BORDER ALIGN="left">
   
    
    
  </table>
  
    <form action="usageApplicationServlet">
    
    
    
  
  
  <table class="table table-bordered border-primary">
 
  <tbody>
    <tr>
     <th scope="row"></th>
      <td>食堂利用状況</td>
      <% String message = "";
      
      message = (String)request.getAttribute("message"); 
      
        %>
      
      <%if(message != null) {%>
      <%= message %>
      <%} %>
      <td>
    
      <% 
      
      //リクエストスコープから値を取得
    if(isfull) { %>
    満員です。
    
    <%} else { %>
    <%= usageCount %>人利用中です。
    
    <% } %>
    
      
    
      </td>
        <td> <input class="btn btn-success"　type="submit" name="action" value="食堂利用申請">
            
            </td>
      </form>
      
      
      <form action="ClimbApplicationServlet">
     
    </tr>
    <tr>
      <th scope="row"></th>
      <td>入浴利用状況</td>
      
      <%String climbmessage = "";
      climbmessage = (String)request.getAttribute("message");
      %>
      <%if(climbmessage != null) {%>
      <%= climbmessage %>
      <%} %>
      
      
     
      
      
      
      <td>
      <% if(isfullnum) { %>満員です。
      <%} else { %>
      <%=climbCount %>
      <%} %>人利用中です。</td>
      <td><input class="btn btn-info" type="submit" name="action" value="climb">
            
         </td>
    </tr>
   
  </tbody>
</table>

</form>


</body>
</html>