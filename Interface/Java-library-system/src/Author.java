import java.util.ArrayList;

public class Author {
	private String name;
	private Integer birthYear;
	private Integer deathYear;
	private Integer age;
	private ArrayList<Book> books = new ArrayList<Book>();
	
	public Author(String name, Integer birthYear, Integer deathYear) {
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		setAge();
	}
	
	public Author(String name, Integer birthYear) {
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = null;
		setAge();
	}
	
	public void addBooks(Book book) {
		books.add(book);
	}
	
	public ArrayList getBooks() {
		return books;
	}
	
	public Integer getBookNum() {
		return books.size();
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getBirthYear() {
		return birthYear;
	}
	
	public Integer getDeathYear() {
		return deathYear;
	}
	
	public void setAge() {
		if (this.deathYear != null) {
			this.age = this.deathYear - this.birthYear;
		}
		else {
			this.age = 2022 - this.birthYear;
		}
	}
	
	public Integer getAge() {
		return age;
	}
	
}
