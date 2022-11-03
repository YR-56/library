package bean;

import java.io.Serializable;

public class UsersBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int user_id;
	private String password;
	private String username;
	
	public void setUsersBean(int user_id,String username, String password) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	
	
}