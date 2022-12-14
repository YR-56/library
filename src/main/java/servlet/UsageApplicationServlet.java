package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UsageStatusDAO;
import bean.GamingStatusBean;
import bean.UsersBean;




/**
*利用申請に使用するクラス
 * @param <usageStatus>
 */

 @WebServlet("/usageApplicationServlet")

 public class UsageApplicationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
       String action = request.getParameter("action");
       UsageStatusDAO dao = new UsageStatusDAO();
       HttpSession session = request.getSession(false);
    UsersBean user = (UsersBean) session.getAttribute("user");

    String message = "";
    String forward = "";

    if(!action.equals("食堂利用申請")) {
        forward = "/WEB-INF/views/mypage.jsp";
        request.getRequestDispatcher(forward).forward(request, response);
        return;
    }    

    int userCountInGaming = 0;
	try {
		userCountInGaming = dao.countCurrentGamingUsers();
	} catch (ClassNotFoundException | SQLException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}

    if(userCountInGaming>2) {
        //DingingStatusBeanを生成
    	
    	
		GamingStatusBean GamingStatus = new GamingStatusBean(userCountInGaming);
		
		
		
		
		GamingStatus.setIsFull(userCountInGaming>2);
		
		//beanをsetAttributeする
		request.setAttribute("GamingStatus", GamingStatus);
		
		String message1 = "満員です";
		request.setAttribute("message", message1);


      
        //messageをリクエストにsetAttribute

        forward = "/WEB-INF/views/mypage.jsp";
        request.getRequestDispatcher(forward).forward(request, response);
        return;
    }

    boolean isBeforeFirst = false;

    try {
        isBeforeFirst = dao.duplication(user);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if(isBeforeFirst) {
        //DIningStatusBeanを生成
    	//beanを生成
		GamingStatusBean GamingStatus = new GamingStatusBean(userCountInGaming);
		
		
		
		GamingStatus.setUserCount(userCountInGaming);
		
		//beanをsetAttributeする
		request.setAttribute("diningStatus", GamingStatus);
		
		message = "30分以内に重複した申請はできません";
		
		request.setAttribute("message", message);


        //DiningStatusBeanをリクエストにsetAttribute

        //messageをリクエストにsetAttribute

        forward = "/WEB-INF/views/mypage.jsp";
        request.getRequestDispatcher(forward).forward(request, response);
        return;
    }

    int rows = 0;
    try {
        rows = dao.userApplication(user);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if (rows == 1) {
    	
    	userCountInGaming = userCountInGaming + 1;
        //DiningStatusBeanを生成
    	GamingStatusBean GamingStatus = new GamingStatusBean(userCountInGaming);

        //DiningStatusBeanをリクエストにsetAttribute
    	GamingStatus.setUserCount(userCountInGaming);
    	
    	request.setAttribute("diningStatus", GamingStatus);

        message = "追加に成功しました！";
        
        
        request.setAttribute("message", message);
        forward = "/WEB-INF/views/mypage.jsp";
    }
    else {
        message = "追加に失敗しました！";
        request.setAttribute("message", message);
        
        
        forward = "/WEB-INF/failer.jsp";
    }

    request.getRequestDispatcher(forward).forward(request, response);
    }}