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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
<title>貸出、返却履歴</title>
</head>
<body>

<div class="p-3 mb-2 bg-success text-white">
貸出、返却履歴
</div>



  
   

<% for(HistoryBean bean : history){ %>

     <div class="row mx-md-n5">
          <div class="col py-3 px-md-5 border bg-light">
  
   
      <div class="main">
           <div class="bookid"><% String bookid = bean.getBookId(); %>
          <%= bookid %></div>
           <div class="title"><% String title = bean.getTitle(); %>
          <%= title %></div>
          <div class="rentdate"><% String rentdate = bean.getRentdate(); %>
         貸出日: <%= rentdate %></div>
          <div class="returndate"><% String returndate = bean.getReturndate(); %>
         返却日:<%= returndate %></div>
         
         </div>
   
  </div>
 </div>
        
          
          <%} %>
  
   
 


</body>
</html>