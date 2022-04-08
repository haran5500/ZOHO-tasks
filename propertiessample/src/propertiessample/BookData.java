package propertiessample;


public class BookData {

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
		return String.format("Book ID:%d|Book Name:%s|Author Name:%s|Year:%d", getBookID(),getBookName(),getAuthorName(),getYear());
	}
}
