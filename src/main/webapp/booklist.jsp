
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="bean.BookBean" %>
<%@ page import="bean.UserBookBean" %>
<%@ page import="java.util.ArrayList" %>
<% // beanList をリクエストデータから取得
ArrayList<BookBean>booklist = (ArrayList<BookBean>)request.getAttribute("booklist"); 





UserBookBean rentNum = (UserBookBean)request.getAttribute("rentNum");
int rentCount = rentNum.getrentNum();
boolean isLimit = rentNum.isLimit();






%>







<HTML>
 <HEAD><TITLE>図書一覧</TITLE></HEAD>
 <BODY>
 
 <%= rentCount %>
 
 <% 
 String returnsuccess = (String)request.getAttribute("returnsuccess");
 
 %>
 
 <% if(returnsuccess == null){ %>""<%}else { %>
 <%= returnsuccess %><%} %>
 
   <%if(isLimit) { %>※貸し出し可能冊数は２冊までです。<%} %>
         
 	    	
 
 <p>検索画面</p>
	<form action="FrontControllerServlet" method="post">
		書籍名：
		著者名:
		キーワード:
		<br> <input type="text" name="titlesearch"><br>

			<input type="submit" value="検索"> 
			<input type="hidden"name="action" value="search"><br>
	</form>
 

 
 
  <% for(BookBean bean : booklist){ %>
  
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
           <input type="submit" name="rent" value="借りる"   <% if(status.equals("貸し出し中") || isLimit){%> disabled<%} %>>
         
	       
</form>
</tr>

<form action="FrontControllerServlet" method="post">
   <input type="hidden" name="bookid" value="<%= bookid %>">
         
  <input type="hidden" name="action" value="return">
  <input type="submit" value="返却" <%if(status.equals("貸し出し可能")){ %>disabled <%} %>>
  </form>
  
        <% } // endfor %>
        
       
  <form action="FrontControllerServlet" method="post">
  
  <input type="hidden" name="action" value="history">
  <input type="submit" value="貸出/返却履歴">
  </form>
  
 
  
  
  <A href='mypage.jsp'>メニューに戻る</A>
  
 
 </BODY>
</HTML>

