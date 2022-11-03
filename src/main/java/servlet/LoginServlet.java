package servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.UsersDAO;
import bean.UsersBean;


class LoginServlet  {
	String execute(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		UsersDAO dao = new UsersDAO();
		boolean result = false;
		String username = request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		String hashedpass = PasswordHashServlet.getHashedPassword(password, "HDJFUESLO83");
		System.out.println(hashedpass);
		
		String message = "";
	
	
		
		result = dao.exeLoginSelect(username, hashedpass);
		
		if(result) {
			UsersBean bean = new UsersBean();
			bean = dao.exeLoginBean(username);
			
			
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);
			
			return "mypage.jsp";
		} else {
			return "/WEB-INF/login_failer.jsp";
		}
		
	}
}