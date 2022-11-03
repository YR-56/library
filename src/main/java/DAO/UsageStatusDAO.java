package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UsersBean;

public class UsageStatusDAO extends CommonDAO {
	
	
	
	/**　ユーザー利用状況を検索するメソッド
	 * 処理　Select文
	 * @return 
	 * @throws ClassNotFoundException 
	 */
	
	
	
	public int countCurrentDiningUsers() throws SQLException , ClassNotFoundException {
		
		Connection con = null;
		Statement prepareStatement = null;
		
		ResultSet rs = null;
		
		con = createConnection();
		
		prepareStatement = con.createStatement();
		
		String sql = "SELECT COUNT(user_id) as num FROM usesfood WHERE (food_date > (NOW() - INTERVAL 30 MINUTE))";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		//全ユーザー情報から絞るため、setStringなどの処理は不要
		
	  
		
		st.execute(sql);
		rs = st.getResultSet();
		
		
		
		
		
		
		if(rs.next()) {
			int usageNum = rs.getInt("num");
			return usageNum;
		}
		return 6;
		
		
	}
	
	
	
	public int userApplication(UsersBean user) throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		Statement prepareStatement = null;
		
		//HttpSession session = request.getSession(false);
		 //UsersBean user = (UsersBean) session.getAttribute("user");
		   
		 
		//UsersBean user = (UsersBean)Session.users.getAttribute("user");
		
		//UsersBean user =  ${Session.users.getUsername()};
		
		int userid = user.getUser_id();
	   
	   
	   // session.getAttribute("user");
	   // UsersBean user = (UsersBean) session.getAttribute("user");
	    
	  
	    
		
		int rs = 0;
		con = createConnection();
		
		prepareStatement = con.createStatement();
		
		String sql = "INSERT INTO usesfood (user_id, food_date) values (?, CURRENT_TIMESTAMP)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, userid);
		
		
		
		rs = st.executeUpdate();	
		//Selectの実行(executeQuery)
		
		
	
		//レコードから、各カラムの値を取得
		if(rs == 1) {
			return 1;
			} else {
				return 0;
			}
		
		
		
	}

	
	
public boolean duplication(UsersBean user) throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		Statement prepareStatement = null;
		
		// UsersBean user = (UsersBean)Session.users.getAttribute("user");
		
		//UsersBean user =  ${Session.users.getUsername()};
		
		int userid = user.getUser_id();
		   
		
	   
	   
	   // session.getAttribute("user");
	   // UsersBean user = (UsersBean) session.getAttribute("user");
	    
	    
	    
		
	    ResultSet rs = null;
		
		con = createConnection();
		
		prepareStatement = con.createStatement();
		
		String sql = "SELECT * FROM usesfood WHERE user_id = ? AND (food_date > (NOW() - INTERVAL 30 MINUTE))";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, userid);
		
		
		
		rs = st.executeQuery();	
		//Selectの実行(executeQuery)
		
		
	

		return rs.isBeforeFirst();
		
		
		
	}

	


	
	
}