package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.HistoryBean;



public class SelectBookDAO extends CommonDAO {
	
	/**
	 * 本の情報を取り出すメソッド
	 * @return
	 * @throw
	 */
	
	public List<BookBean> selectbook() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement prepareStatement = null;
		
		ResultSet rs = null;
		
		con = createConnection();
		prepareStatement = con.createStatement();
		
	
		
		String sql = "SELECT bookid, title, author, published_year, code,status, keyword FROM book";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.execute(sql);
		rs = st.getResultSet();
		
		
		
		
		 List<BookBean> booklist = new ArrayList<BookBean>();
		 
		
		 
		 while(rs.next()) {
			 
			 BookBean bean = new BookBean();
			 
			 String bookId = rs.getString("bookId");
			 String title = rs.getString("title");
			 String author = rs.getString("author");
			 String published_year = rs.getString("published_year");
			 String code = rs.getString("code");
			 String status = rs.getString("status");
			 String keyword = rs.getString("keyword");
			 
			 bean.setBookBean(bookId, title, author, published_year, code,status, keyword);
			 
			 booklist.add(bean);

		 }
		 
		 return booklist;

	}
	 
	public int rentbook(String bookid, int userid, String title) throws  SQLException, ClassNotFoundException {
		
		
		Connection con = null;
		Statement prepareStatement = null;
		
		

		
		int rs = 0;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		/*
		String sql = "DELETE FROM rentrecord WHERE bookid = ?";
		PreparedStatement st = con.prepareStatement(sql);
		*/
		
		String  sql = "INSERT INTO bookrecord (bookid, userid, title, rentdate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		
		st.setString(1, bookid);
		st.setInt(2, userid);
		st.setString(3, title);
	
		
		rs = st.executeUpdate();	
		
		
		
		
		
		
		
  
		String sql1 = "UPDATE  book SET status = '貸し出し中' WHERE bookid = ?";
		
		PreparedStatement st2 = con.prepareStatement(sql1);
		
		st2.setString(1,  bookid);
		
		rs = st2.executeUpdate();
		
		if(rs == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public int returnbook(String bookid) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement prepareStatement = null;
		
		int rs = 0;
		con = createConnection();
		prepareStatement = con.createStatement();
		
		String sql = "UPDATE book SET status = '貸し出し可能' WHERE bookid = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bookid);
		rs = st.executeUpdate();
		
		
		
		String sql2 = "UPDATE bookrecord SET returndate = CURRENT_TIMESTAMP WHERE bookid = ? AND returndate is Null";
		
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setString(1, bookid);
		
		rs = st2.executeUpdate();
		
		
		if(rs == 1) {
			return 1;
		} else {
			return 0;
		}
		
		
		
	}
	
	
	/**
	 * 貸し出し返却履歴
	 * 
	 * 
	 * @param userid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public List<HistoryBean> userhistory(int userid) throws ClassNotFoundException, SQLException {

	    Connection con = null;
	    Statement prepareStatement = null;

	    ResultSet rs = null;
	    
	    System.out.println(userid);

	    con = createConnection();
	    prepareStatement = con.createStatement();

	    String sql = "SELECT bookid, title, rentdate, returndate FROM bookrecord WHERE userid = ?";

	    PreparedStatement st = con.prepareStatement(sql);
	    st.setInt(1, userid);

	    st.execute();
	    rs = st.getResultSet();

	    List<HistoryBean> history = new ArrayList<HistoryBean>();

	    while(rs.next()) {
	        HistoryBean bean = new HistoryBean();

	        String bookid = rs.getString("bookid");
	        String title = rs.getString("title");
	        String rentdate = rs.getString("rentdate");
	        String returndate = rs.getString("returndate");

	        bean.setHistoryBean(bookid, title, rentdate, returndate);

	        history.add(bean);
	    }
	    return history;
	}
	
	public List<BookBean> searchbook(String searchtitle) throws ClassNotFoundException, SQLException {
		
		  Connection con = null;
		    Statement prepareStatement = null;

		    ResultSet rs = null;
		    
		    con = createConnection();
		    prepareStatement = con.createStatement();
		    
		   
		    		
		    String sql = "SELECT * FROM book WHERE title LIKE ? OR author LIKE ? OR keyword LIKE ?";
		    
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setString(1, "%" + searchtitle + "%");
		    st.setString(2, "%" + searchtitle + "%");
		    st.setString(3, "%" + searchtitle + "%");
		    st.execute();
		    
		    rs = st.getResultSet();
		    
		    List<BookBean> findtitle = new ArrayList<BookBean>();

		    while(rs.next()) {
		    	BookBean bean = new BookBean();
		    	 String bookId = rs.getString("bookId");
				 String title = rs.getString("title");
				 String author = rs.getString("author");
				 String published_year = rs.getString("published_year");
				 String code = rs.getString("code");
				 String status = rs.getString("status");
				 String keyword = rs.getString("keyword");
				 
				 bean.setBookBean(bookId, title, author, published_year, code,status, keyword);
				 
				 findtitle.add(bean);
		    	
		    	
		    }
		return findtitle;
	}
	
		
	}

	
	
	
