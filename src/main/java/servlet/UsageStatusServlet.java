package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UsageStatusDAO;
import bean.ClimingStatusBean;
import bean.GamingStatusBean;

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
		usageNum = Countdao.countCurrentGamingUsers();
		
		int userCountInGaming = usageNum;
		GamingStatusBean GamingStatus = new GamingStatusBean(userCountInGaming);
		
		GamingStatus.setUserCount(userCountInGaming);
		GamingStatus.setIsFull(userCountInGaming>8);
		
		request.setAttribute("GamingStatus", GamingStatus);
		
		int climbNum = 0;
		climbNum = Countdao.countCurrentClimbUsers();
		
		int userCountIncliming = climbNum;
		ClimingStatusBean climingStatus = new ClimingStatusBean(userCountIncliming);
		
		climingStatus.setUserCount(userCountIncliming);
		climingStatus.setIsFull(userCountIncliming>2);
		
		request.setAttribute("climingStatus", climingStatus);
		
	    forward = "/WEB-INF/views/UsageStatus.jsp";
		request.getRequestDispatcher(forward).forward(request, response);
		
		
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		
	}
	


}