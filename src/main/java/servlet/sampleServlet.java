package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sampleServlet")
/**
 * Servlet implementation class SampleServlet
 */
public class sampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		
		response.setContentType("text/html; charset=utf8");
		PrintWriter out = response.getWriter();
		
		try {
			//JDBCドライバの登録
			String RDB_DRIVE = "com.mysql.jdbc.Driver";
			 Class.forName(RDB_DRIVE).newInstance();
			 
			 String url = "jdbc:mysql://localhost:3306/library";
			String user = "root";
			String password = "password";
			
			conn = DriverManager.getConnection(url, user, password);
			
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			// データの表示
            while (rs.next()) {
                out.println(
                    rs.getString("user_id")
                );
            }
            // データベース切断
            stmt.close();
            conn.close();
            
            
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMessage = e.getClass().getName() + ":" + e.getMessage();
			
			out.println(exceptionMessage);
		} finally {
			try {
				if (conn != null) { conn.close();}
			} catch (SQLException e ) {}
		}
	}
}