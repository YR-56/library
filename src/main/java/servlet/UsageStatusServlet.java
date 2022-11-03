package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UsageStatusDAO;
import bean.DiningStatusBean;

/**
 * 利用状況照会
 * */
@WebServlet("/UsageStatusServlet")


public class UsageStatusServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
		try {
		request.setCharacterEncoding("UTF-8");
		
		String forward = "";
		
		UsageStatusDAO Countdao = new UsageStatusDAO();
		
		int usageNum = 0;
		usageNum = Countdao.countCurrentDiningUsers();
		
		int userCountInDining = usageNum;
		DiningStatusBean diningStatus = new DiningStatusBean(userCountInDining);
		
		diningStatus.setUserCount(userCountInDining);
		diningStatus.setIsFull(userCountInDining>8);
		
		request.setAttribute("diningStatus", diningStatus);
		
	    forward = "UsageStatus.jsp";
		request.getRequestDispatcher(forward).forward(request, response);
		
		
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		
	}
	


}