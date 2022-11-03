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
import bean.BookBean;

/**
*本一覧を取得するクラス
 * @param 
 */

@WebServlet("/SelectBookServlet")

public class SelectBookServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		SelectBookDAO dao = new SelectBookDAO();
		
		HttpSession session = request.getSession(false);
		
		String bookid = null;
		
		bookid = (String) request.getParameter(bookid);
		
		
	
		List<BookBean> booklist = null;
		
		String forward = "";
		
		
		
		
				try {
					booklist = dao.selectbook();
					System.out.println(booklist);
				} catch (ClassNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			
		
		
		request.setAttribute("booklist", booklist);
		
		RentLimitServlet rentLimit = new RentLimitServlet();
		forward = rentLimit.execute(request);
		
		
		request.getRequestDispatcher(forward).forward(request, response);
		
		     return;
		
		
		
	}
}
