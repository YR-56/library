package servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SelectBookDAO;
import DAO.UsersDAO;
import bean.BookBean;
import bean.HistoryBean;
import bean.UsersBean;



/**
 * ログイン実行時に使用するクラス
 */
@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	/**
	 * 「http://.../EMS/LoginServlet」にリクエストが来た際に実行される
	 * @return 
	 */
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		//文字コードの設定(必須)
		request.setCharacterEncoding("UTF-8");
		//遷移元画面のformから値の取得
		//request.getParameter("inputタグのname属性")
	
	    	 
	    String action = request.getParameter("action");
        String forward = "";
        
        try {

        	if(action.equals("login")) {
        		
        		
        		
    		 LoginServlet logic = new LoginServlet();
    		 forward = logic.execute(request);
    		 
				
				
				
                } 
        	else if(action.equals("bookconfirm")){
        		
        		
				
				SelectBookDAO rent = new SelectBookDAO();
				
			    HttpSession session = request.getSession(false);
			    UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得
				int userid = user.getUser_id();
				
				//リクエストパラメータにて取得
				String bookid = (String) request.getParameter("bookid");
				String title = (String)request.getParameter("title");
				
				int rs = 0;
				rs = rent.rentbook(bookid, userid, title);
				
				if(rs == 1) {
					String success = "貸し出しました";
					request.setAttribute("success", success);
				}else {
					String failure = "貸しだしに失敗しました";
					request.setAttribute("failure", failure);
				}
				
			forward = "mypage.jsp";
        } else if(action.equals("history")) {
        	
        	
        	SelectBookDAO historydao = new SelectBookDAO();
        	
        	 HttpSession session = request.getSession(false);
			    UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得
				int userid = user.getUser_id();
				
				System.out.println(userid);
				
				List<HistoryBean> history = null;
				
			
				history = historydao.userhistory(userid);
				
				request.setAttribute("history", history);
				
				forward = "renthistory.jsp";
                
        } else if(action.equals("search")) {
        	
        	System.out.println("history");
        	
        	String searchtitle = (String)request.getParameter("titlesearch");
        	
        	System.out.println(searchtitle);
        	
        	SelectBookDAO searchdao = new SelectBookDAO();
        	
        	List<BookBean> findtitle = null;
        	
        	findtitle = searchdao.searchbook(searchtitle);
        	
        	request.setAttribute("findtitle", findtitle);
        	
        	forward = "searchtitle.jsp";
        	
        	
        	
        } else if(action.equals("return")) {
        	System.out.println("return");
        	
        	String bookid = request.getParameter("bookid");
        	SelectBookDAO returndao = new SelectBookDAO();
        	
        	int result = returndao.returnbook(bookid);
        	
        	if(result == 1) {
        		String message = "返却しました";
        		request.setAttribute("returnsuccess", message);
        		
        		SelectBookDAO dao = new SelectBookDAO();
        		List<BookBean> booklist = null;
        		booklist = dao.selectbook();
        		request.setAttribute("booklist", booklist);
        		
        		
        		RentLimitServlet rentLimit = new RentLimitServlet();
        		forward = rentLimit.execute(request);
        	}
        	
        	}
        	
        	
        	else if(action.equals("register")) {
        		System.out.println("register");
        		
        		String user_name = request.getParameter("username");
        		String Password = request.getParameter("password");
        		
        		
        		
        		
        		String hashedpass = PasswordHashServlet.getHashedPassword(Password, "HDJFUESLO83");
				System.out.println(hashedpass);
        		UsersDAO dao = new UsersDAO();
        		
        		
        		
        		int passchecker = Password.length();
        		
        		System.out.println(passchecker);
        		
        		
        		
        		if(passchecker < 6) {
        			String message = "パスワードは6文字以上です";
        			request.setAttribute("message", message);
        			
        			forward = "register.jsp";
        			request.getRequestDispatcher(forward).forward(request, response);
        			
        		 forward = "register.jsp";
        		} else {
        			 
        			
        		    
        			
        		
        	
        		
        		int rs = dao.register(user_name, hashedpass);
        		if(rs == 0) {
        			forward = "login_failier.jsp";
        			return;
        		} 
        		
        		boolean result = dao.exeLoginSelect(user_name, hashedpass);
    			
    			if(result) {
    				UsersBean bean = new UsersBean();
    				bean = dao.exeLoginBean(user_name);
    				
    				
    				HttpSession session = request.getSession();
    				session.setAttribute("user", bean);
    				
    				forward = "mypage.jsp";
    			}}
        	
        }    else if(action.equals("change")) {
        	
        	 
        	
        	String password = request.getParameter("password");
        	String hashedpass = PasswordHashServlet.getHashedPassword(password, "HDJFUESLO83");
        	 HttpSession session = request.getSession(false);
			    UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得
				int userid = user.getUser_id();
				
				String afterusername = request.getParameter("afterusername");
	        	String afterpassword = request.getParameter("afterpassword");
	        	String afterpass = PasswordHashServlet.getHashedPassword(afterpassword, "HDJFUESLO83");
	           
			
        	
        	UsersDAO dao = new UsersDAO();
        	boolean rs = false;
        	rs = dao.checkpassword(hashedpass, userid);
        	
        	if(rs) {
        		
        		int rs2 = 0;
        		rs2 = dao.changedUserInfo(afterpassword, afterusername, userid);
        		
        		if(rs2 == 1) {
        			String message = "ユーザー情報を変更しました";
        			request.setAttribute("message", message);
        			forward = "mypage.jsp";
        		} else {
        			forward = "UserChange.jsp";
        		}
        		
        	}else {
        		
        		String message = "現在のパスワードが違います";
        		request.setAttribute("message", message);
        		forward = "UserChange.jsp";
        		
        		
        	} 
        	
        	
  
        	
        } else if(action.equals("logout")) {
        	   HttpSession session = request.getSession();
               session.invalidate();           
               forward = "mypage.jsp";
          
        }
        	
        	
        	
        	
        else {
        	
        	
        	forward = "login_fail.jsp";
        }
        
	
	
		request.getRequestDispatcher(forward).forward(request, response);
	   
        }

        
        
        

        
        
        catch (SQLException | ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            
            String exceptionMessage = e.getClass().getName() + ":" + e.getMessage();
			
            System.out.println(exceptionMessage);
           
            forward = "/WEB-INF/login_failer.jsp";
            request.getRequestDispatcher(forward).forward(request, response);
        }
        
        
        
	}
	
	private static String escape(String val) {
        if (val == null) return "";
        val = val.replaceAll("&", "& amp;");
        val = val.replaceAll("<", "& lt;");
        val = val.replaceAll(">", "& gt;");
        val = val.replaceAll("\"", "&quot;");
        val = val.replaceAll("'", "&apos;");
        return val;
      }
	
	public static String sanitize (String word) {
		
		String newWord = word;
		newWord = newWord.replace("&", "&amp;");
		newWord = newWord.replace("\"", "&quot;");
		newWord = newWord.replace("<", "&lt;");
		newWord = newWord.replace(">", "&gt");
		newWord = newWord.replace("", "&nbsp");
		return newWord;
	}
	
	
	
	
}
