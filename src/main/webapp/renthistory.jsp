<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="bean.HistoryBean" %>
<%@ page import="java.util.ArrayList" %>
<% // beanList をリクエストデータから取得
ArrayList<HistoryBean>history = (ArrayList<HistoryBean>)request.getAttribute("history"); %>

    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

貸出、返却履歴


<% for(HistoryBean bean : history){ %>
  
   <tr>
          <td><% String bookid = bean.getBookId(); %>
          <%= bookid %></td>
          <td><% String title = bean.getTitle(); %>
          <%= title %></td>
          <td><% String rentdate = bean.getRentdate(); %>
          <%= rentdate %></td>
          <td><% String returndate = bean.getReturndate(); %>
          <%= returndate %></td>
        
          
          <%} %>

</body>
</html>