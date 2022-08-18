import java.util.ArrayList;

public class Book {
	private String title;
	private String author;
	private String pulicationDate;
	private Boolean borrowed;
	private ArrayList<Book> books = new ArrayList<Book>();
	
	public Book(String title, String pulicationDate, String author) {
		this.title = title;
		this.pulicationDate = pulicationDate;
		this.author = author;
		this.borrowed = false;
	}
	
	public String toString() {
		return title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList getBookList() {
		return books;
	}
	
	public String getPublicationDate() {
		return pulicationDate;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setBorrowedStatus() {
		this.borrowed = true;
	}
	
	public Boolean getBorrowedStatus() {
		return borrowed;
	}
	
	
}
