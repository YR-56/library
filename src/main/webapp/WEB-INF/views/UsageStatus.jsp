<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="bean.DiningStatusBean" %>
<%@page import="bean.UsersBean" %>

    
    
    
<%
 
     
      DiningStatusBean usage = (DiningStatusBean)request.getAttribute("diningStatus");
      int usageCount = usage.getUserCount();
      boolean isfull = usage.isFull();
      
     
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
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
      <td> <input type="submit" name="action" value="食堂利用申請">
            
            </td>
    </tr>
    <tr>
      <th scope="row"></th>
      <td>入浴利用状況</td>
      <td>満員</td>
      <td><input type="submit" value="入浴利用申請">
            <input type="hidden" name="action" value="food">
         </td>
    </tr>
   
  </tbody>
</table>

</form>


</body>
</html>