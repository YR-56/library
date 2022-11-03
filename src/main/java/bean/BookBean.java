package bean;

import java.io.Serializable;

public class BookBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
   
	private String bookId;
	private String title;
	private String author;
	private String published_year;
	private String code;
	private String status;
	private String keyword;
	
	public void setBookBean(String bookId,String title, String author, String published_year, String code, String status, String keyword)
	{
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.published_year = published_year;
		this.code = code;
		this.status = status;
		this.keyword = keyword;
	}
	
	public String getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	
	public String getPublished_year() {
		return published_year;
	}
	public String getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}

	public String getKeyword() {
		return keyword;
	}
	
	public void setBookId(String value) {
		bookId = value;
	}
	public void setTitle(String value) {
		title = value;
	}
	public void setAuthor(String value) {
		author = value;
	}
	
	public void setPublished_year(String value) {
		published_year = value;
	}
	public void setCode(String value) {
		code = value;
	}
	public void setStatus(String value) {
		status = value;
	}
	public void setKeyword(String value) {
		keyword = value;
	}
	
	
	

}
	