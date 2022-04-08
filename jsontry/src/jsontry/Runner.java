package jsontry;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

public class Runner {
	
public static void main(String[] args) {
	
	JSONObject customerData=new JSONObject();
	BookData book1=new BookData();
	
	book1.setBookID(1234);
	book1.setBookName("Hello world");
	book1.setAuthorName("Java");
	book1.setYear(1972);
	
	JsonLogic log=new JsonLogic();
	try
	{
	log.writeToFile(book1);
	
	customerData=log.readJsonFromFile();
	ObjectMapper mapper=new ObjectMapper();
	List<BookData> bookList=new ArrayList<BookData>();
	for(Object keys:customerData.keySet())
	{
		bookList.add(mapper.readValue((String)customerData.get(keys),BookData.class));
	}
	System.out.println(bookList);
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
}
