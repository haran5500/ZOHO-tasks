package sampleyaml;

import java.io.Serializable;
import java.text.MessageFormat;

public class BookData implements Serializable {

	private int bookID;
	private String bookName;
	private String authorName;
	private int year;

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString()
	{
		return MessageFormat.format("Book ID: {0} Book Name: {1} Author Name: {2} Year: {3}\n", getBookID(),getBookName(),getAuthorName(),getYear());
	}
}
