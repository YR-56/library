package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.UsersDAO;
import bean.UserBookBean;
import bean.UsersBean;


class  RentLimitServlet {
	String execute(HttpServletRequest request) throws ServletException, IOException {
		
		try {
			
			 HttpSession session = request.getSession(false);
			    UsersBean user = (UsersBean) session.getAttribute("user");
//useridを取得
				int userid = user.getUser_id();
				
			
			UsersDAO dao = new UsersDAO();
			int rentnum = dao.limitrent(userid);
			
			UserBookBean rentCount = new UserBookBean(rentnum);
			
			System.out.println(rentnum);
			
			rentCount.setrentNum(rentnum);
			rentCount.setIsLimit(rentnum>1);
			
			request.setAttribute("rentNum", rentCount);
			
			return "/WEB-INF/views/booklist.jsp";
			
			
			
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			 String exceptionMessage = e.getClass().getName() + ":" + e.getMessage();
				
	            System.out.println(exceptionMessage);
	           
			
			return "/WEB-INF/views/login_failer.jsp";
		}
		
		
	}
}