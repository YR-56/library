package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UsersBean;

/**
 * usersテーブルに対する処理を記載する
 * CommonDAOを継承することで、CommonDAOのメソッドを使用する事が出来る
 */
public class UsersDAO extends CommonDAO {

	/**
	 * ユーザー全検索の実行メソッド
	 * 処理：Select文の作成及び実行
	 * @throws ClassNotFoundException 
	 */
	
	
	//ログイン検索
	public boolean exeLoginSelect(String name, String pass) throws SQLException, ClassNotFoundException {
		
		//SQL文の実行に必要なクラス
		Connection con = null;
		Statement prepareStatement = null;
		
		//Selectの結果を格納するクラス
		ResultSet rs = null;
		
		
			//SQL実行の準備(お決まり)
			con = createConnection();
			prepareStatement = con.createStatement();
			
			//SQL文の作成
			String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
			//rs = stmt.executeQuery(sql);
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, pass);
			rs = st.executeQuery();	
			//Selectの実行(executeQuery)
		
			//レコードから、各カラムの値を取得
			if(rs.next()) {
				return true;
				} else {
					return false;
				}
	}
	
	public UsersBean exeLoginBean(String name) throws SQLException, ClassNotFoundException  {
		
		//SQL文の実行に必要なクラス
		Connection con = null;
		Statement prepareStatement = null;
		
		//Selectの結果を格納するクラス
		ResultSet rs = null;
		UsersBean bean = new UsersBean();
		
		
			//SQL実行の準備(お決まり)
			con = createConnection();
			//stmt = con.createStatement();
			
			//SQL文の作成
			String sql = "SELECT * FROM user WHERE user_name = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();			
			//Selectの実行(executeQuery)
			//rs = stmt.executeQuery(sql);
			//レコードから、各カラムの値を取得
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				
				String username = rs.getString("user_name");
				
				String password = rs.getString("password");
				bean.setUsersBean(user_id, username, password);
			}
			
		
			
		
		return bean;
	}
	
	public int limitrent(int userid) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement prepareStatement = null;
		
		ResultSet rs = null;
		
		con = createConnection();
		
		prepareStatement = con.createStatement();
		
		String sql = "SELECT COUNT(userid) as num FROM bookrecord WHERE userid = ? AND returndate is NULL";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		//全ユーザー情報から絞るため、setStringなどの処理は不要
		
	  st.setInt(1, userid);
		
		st.execute();
		rs = st.getResultSet();
		
		
		
		
		
		
		if(rs.next()) {
			int rentnum = rs.getInt("num");
				return rentnum;
		}
		return 0;
	}
	
	
	public int register(String username, String password) throws ClassNotFoundException, SQLException {
		
		
		
		
		Connection con = null;
		Statement prepareStatement = null;
		int rs = 0;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		String sql = "INSERT INTO user (user_name, password) VALUES (?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, username);
		st.setString(2, password);
		
		rs = st.executeUpdate();
		
		
		
		if(rs == 1) {
			return 1;
		}else {
			return 0;
		}
		
		
		
		}		
	
	/**
	 * 
	 * パスワード変更の時にパスワード確認をするためのDAO
	 * */
	public boolean checkpassword(String password, int userid) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement prepareStatement = null;
		ResultSet rs = null;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		String sql = "SELECT COUNT(*) FROM user WHERE password = ? AND user_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, password);
		st.setInt(2, userid);
		
		rs = st.executeQuery();
		
		
		if (rs.next())
		{
			 return rs.getInt(1) > 0;
		   
		   
		} else {
        return false;
		} 
	}
	
	/**
	 * 
	 * usernameの重複チェック
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * */
	
	public boolean checkusername(String username) throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		Statement prepareStatement = null;
		ResultSet rs = null;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		String sql = "SELECT COUNT(*) FROM user WHERE user_name = ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, username);
		rs = st.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(1) > 0;
		} else {
			return false;
		}
	}
	
	
	
	public int changedUserInfo(String password, String username, int userid) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement prepareStatement = null;
		int rs = 0;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		String sql = "UPDATE  user SET password = ?, user_name = ? WHERE user_id = ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, password);
		st.setString(2, username);
		st.setInt(3, userid);
		
		rs = st.executeUpdate();
		
		if(rs == 1) {
			return 1;
		} else {
			return 0;
		}
				
		
		
	}
	
}
		
		
		
		
	
	

	





