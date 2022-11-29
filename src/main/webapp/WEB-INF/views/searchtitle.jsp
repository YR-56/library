<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.BookBean" %>
<%@ page import="java.util.ArrayList" %>
<% // beanList をリクエストデータから取得
ArrayList<BookBean>findtitle = (ArrayList<BookBean>)request.getAttribute("findtitle"); %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <% for(BookBean bean : findtitle){ %>
  
   <tr>
          <td><% String bookid = bean.getBookId(); %>
          <%= bookid %></td>
          <td><% String title = bean.getTitle(); %>
          <%= title %></td>
          <td><% String author = bean.getAuthor(); %>
          <%= author %></td>
          <td><% String published_year = bean.getPublished_year(); %>
          <%= published_year %></td>
          <td><% String code = bean.getCode(); %>
          <%= code %></td>
          <td><%String status = bean.getStatus(); %>
          <%= status %></td>
          <td><% String keyword = bean.getKeyword(); %>
          <%= keyword %></td>
          
          <form action="rentConfirm.jsp" method="get">
          <input type="hidden" name="bookid" value="<%= bookid %>">
           <input type="hidden" name="title" value="<%= title %>">
           <input type="hidden" name="author" value="<%= author %>">
           <input type="hidden" name="published_year" value="<%= published_year %>">
           <input type="hidden" name="code" value="<%= code %>">
           <input type="hidden" name="status" value="<%= status %>">
           <input type="hidden" name="keyword" value="<%= keyword %>">
           <%if(status != "貸し出し中") { %>
            <input type="submit" name="rent" value="借りる"  >
            <%} else {%>
            <input type="submit" name="rent" value="借りる"  disabled>
            <%} %>
            
            <%} %>


</body>
</html>