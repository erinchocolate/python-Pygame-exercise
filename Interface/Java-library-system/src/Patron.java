import java.util.ArrayList;

public class Patron {
	private String name;
	private Integer bookNum;
	private ArrayList<Book> bookBorrowed = new ArrayList<Book>();
	
	public Patron(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addBook(Book book) {
		bookBorrowed.add(book);
	}
	
	public ArrayList getBook() {
		return bookBorrowed;
	}
	
	
}
