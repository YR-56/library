package bean;

import java.io.Serializable;

public class HistoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
   
	private String bookid;
	private String title;
	private String rentdate;
	private String returndate;
	
	public void setHistoryBean(String bookid,String title, String rentdate, String returndate) {
		this.bookid = bookid;
		this.title = title;
		this.rentdate = rentdate;
		this.returndate = returndate;
	}
	
	public String getBookId() {
		return bookid;
	}

	public String getTitle() {
		return title;
	}
	
	public String getRentdate() {
		return rentdate;
	}
	
	public String getReturndate() {
		return returndate;
	}
	
}
	