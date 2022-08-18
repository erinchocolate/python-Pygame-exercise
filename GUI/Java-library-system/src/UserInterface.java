import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ecs100.UI;

public class UserInterface {
	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<Author> authors = new ArrayList<Author>();
	private ArrayList<Patron> patrons = new ArrayList<Patron>();
	
	public UserInterface() {
		UI.initialise();
		UI.addButton("List authors", this::listAuthors);
		UI.addButton("List all books", this::listBooks);
		UI.addButton("List books of author", this::listAuthorBooks);
		UI.addButton("Look up book by title", this::lookUpBook);
		UI.addButton("Issue book", this::issueBook);
		UI.addButton("Check book status", this::checkBookStatus);
		UI.addButton("Check patron", this::searchPatron);
		
		try {		
			Scanner scan = new Scanner(new File("books.txt"));
			String authorInfo; 
			scan.useDelimiter("---");
			while(scan.hasNext()) {
				authorInfo = scan.next();
				authorInfo = authorInfo.trim();
				readAuthorInfo(authorInfo);
			}
		} catch (IOException e) {
			UI.println("File error: " + e);
			e.printStackTrace();
		}
	}
	
	public void readAuthorInfo(String authorInfo) {
		Author author;
		Book book;
		Scanner scan = new Scanner(authorInfo);
		while (scan.hasNextLine()) {
			String name = scan.nextLine();
			String lifetime = scan.nextLine();
			String[] date = lifetime.split(" ");
			Integer birthYear = Integer.valueOf(date[0]);
			if(date.length == 2) {
				Integer deathYear = Integer.valueOf(date[1]);
				author = new Author(name, birthYear, deathYear);
			}
			else {
				author = new Author(name, birthYear);	
			}
			authors.add(author);
			while(scan.hasNext()) {
				String title = scan.nextLine();
				String pulication = scan.nextLine();
				book = new Book(title, pulication, author.getName());
				author.addBooks(book);
				books.add(book);
			}
		}	
	}
	
	public void listAuthors() {
		// List names, lifetimes, and number of books
		// in the library for all authors
		for (Author a :authors) {
			UI.println("Author: "+ a.getName());
			UI.println("Birth year: " + a.getBirthYear());
			if(a.getDeathYear()!=null) {
				UI.println("Death year: " + a.getDeathYear());
			}
			UI.println("Age: " + a.getAge());
			UI.println("Number of books: " + a.getBookNum());
			UI.println("------");
		}
		
	}
	
	public void listBooks() {
		// List titles of all books
		for (Book b :books) {
			UI.println(b.getTitle());
		}
	}
	
	public void listAuthorBooks() {
		// List titles of all books by a chosen author
		Author author;
		ArrayList<Book> authorBook;
		String name = UI.askString("Enter author name: ");
		author = getAuthorByName(name);
		if(author!= null) {
			authorBook = author.getBooks();
			UI.println(name + "'s work list:");
			for (Book b: authorBook) {
				UI.println(b.getTitle());
			}
		}
	}
	public Author getAuthorByName(String name) {
		for(Author a: authors) {
			if(a.getName().equals(name)) {
			return a;
			}
		}
		return null;
		}
	
	public void lookUpBook() {
		Book book = null;
		// Show book, publication date, and author information
		String name = UI.askString("Enter book name: ");
		book = getBookByName(name);
		if(book != null) {
			UI.println("Here is the info about book:");
			UI.println("Title: " + book.getTitle());
			UI.println("Publication date: " + book.getPublicationDate());
			UI.println("Author: " + book.getAuthor());
		}
		
	}
	
	public Book getBookByName(String name) {
		for(Book b: books) {
			if(b.getTitle().equals(name)) {
				return b;
			}
		}
		return null;
	}
	
	public void issueBook() {
		// Issue a book to a patron.
		String name = UI.askString("Enter your name: ");
		String title = UI.askString("Enter the book title you want to borrow: ");
		Book book;
		
		book = getBookByName(title);
		if(book != null){
			if(book.getBorrowedStatus() == true) {
				UI.println("Sorry, the book you search for is borrowed away!");
			}
			else {	
				processPatron(name, book);	
			}
		}
		else {
			UI.println("Sorry, the book you search for doesn't exsit!");	
		}
	}
	
	public void processPatron(String name, Book book) {
		Patron patron;
		patron = getPatronByName(name);
		if(patron == null) {
			patron = new Patron(name);
			patrons.add(patron);
		}
		patron.addBook(book);
		book.setBorrowedStatus();
	}
	
	public Patron getPatronByName(String name) {
		for(Patron p: patrons) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public void checkBookStatus() {
		String title = UI.askString("Enter the book title you want to borrow: ");
		Book book = getBookByName(title);
		if(book.getBorrowedStatus() == true) {
			UI.println("Sorry, the book you search for is borrowed away!");
		}
		else {
			UI.println("The book you search for can be borrowed!");
		}
	}
	
	public void searchPatron() {
		Patron patron = null;
		String name = UI.askString("Enter the name you want to search: ");
		patron = getPatronByName(name);
		if(patron != null) {
			UI.println("Here is the info:");
			UI.println("Name: " + patron.getName());
			UI.println("Books borrowed: " + patron.getBook());
		}
	}	
	
	public static void main(String[] args) {
		new UserInterface();
	}
}
