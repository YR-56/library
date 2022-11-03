package bean;

import java.io.Serializable;

public class UserBookBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int rentNum;
	private boolean isLimit;
	
	
	public UserBookBean(int rentlimit) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public int getrentNum() {
		
		return rentNum;
	}
	
	public void setrentNum(int rentNum) {
		this.rentNum = rentNum;
	}
	
	public boolean isLimit() {
		return isLimit;
	}
	
	public void setIsLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}
	

	
}
	
	
   