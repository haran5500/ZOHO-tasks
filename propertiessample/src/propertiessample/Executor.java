package propertiessample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import inout.Reader;
import userexception.CustomException;

public class Executor {

	public void getBookInfo() throws CustomException {
		BookData book1 = new BookData();
		book1.setBookID(Reader.getInt("Enter the Book ID:"));
		book1.setBookName(Reader.getString("Enter the Book Name;"));
		book1.setAuthorName(Reader.getString("Enter the Author Name:"));
		book1.setYear(Reader.getInt("Enter the Year:"));

		Map<String, BookData> mapper = new HashMap<String, BookData>();
		mapper.put(String.valueOf(book1.getBookID()), book1);

		writeToFile(book1);
	}

	public void writeToFile(BookData bookObj) throws CustomException {

		Properties propObj = new Properties();
		propObj.setProperty(String.valueOf(bookObj.getBookID()), bookObj.toString());
		String path = "/home/inc10/Desktop/BookProperty.property";

		try (FileWriter fileObj = new FileWriter(path, true); BufferedWriter out = new BufferedWriter(fileObj)) {
			propObj.store(out, "Book Data");
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
		
		System.out.println(bookObj);
		Reader.print("Written Successfully!");
	}

	public Properties loadProperties() throws CustomException {
		Properties propObj = new Properties();
		String path = "/home/inc10/Desktop/BookProperty.property";

		try (FileReader fileObj = new FileReader(path); BufferedReader out = new BufferedReader(fileObj)) {
			propObj.load(out);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
		Reader.print("Written Successfully!");
		return propObj;
	}

	public BookData stringToBookData(String[] inpStr) {
		BookData bookObj=new BookData();
		for(String values:inpStr)
		{
			String[] subValues=values.split(":");
			for(int i=0;i<subValues.length;i++)
			{
				if(subValues[0].equals("Book ID"))
				{
					bookObj.setBookID(Integer.parseInt(subValues[1]));
				}
				else if(subValues[0].equals("Book Name"))
				{
					bookObj.setBookName(subValues[1]);
				}
				else if(subValues[0].equals("Author Name"))
				{
					bookObj.setAuthorName(subValues[1]);
				}
				else if(subValues[0].equals("Year"))
				{
					bookObj.setYear(Integer.parseInt(subValues[1]));
				}
			}
			//System.out.println(Arrays.toString(subValues));
		}
		return bookObj;
	}

	public static void main(String[] args) {
		try {
			Executor execute = new Executor();
			Reader.print("Properties writing and reading!");
			Reader.print("1. Store to a File\n2. Read from a File");

			int option = Reader.getInt("Enter the Option:");
			switch (option) {
			case 1: {
				execute.getBookInfo();
				break;
			}
			case 2: {
				Map<Integer, BookData> mapper = new HashMap<Integer, BookData>();
				Properties propObj = execute.loadProperties();
				Reader.print("Properties:" + propObj);

				for (String prop : propObj.stringPropertyNames()) {
					String[] subKeys = propObj.getProperty(prop).split("\\|");
					System.out.println(Arrays.toString(subKeys) + "\nlength:" + subKeys.length);
BookData bookObj=execute.stringToBookData(subKeys);
Reader.print("Books:"+bookObj);

				}

				break;
			}
			default: {
				break;
			}
			}
		} catch (CustomException ex) {
			ex.printStackTrace();
		}
	}
}
