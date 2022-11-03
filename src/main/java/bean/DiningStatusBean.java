package bean;

import java.io.Serializable;

public class DiningStatusBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int userCount;
	private boolean isFull;
	
	
	

	public DiningStatusBean(int usageCount) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	

	public int getUserCount() {
		return userCount;
	}
	
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	public boolean isFull() {
		return isFull;
	}
	
	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	
}