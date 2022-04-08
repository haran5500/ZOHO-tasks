package jsontry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonLogic {
	static JSONObject json = new JSONObject();
	public static void writeToFile(BookData bookObj1) throws Exception {

		readJsonFromFile();
		String path = "/home/inc10/Desktop/book1data.txt";

		ObjectMapper map = new ObjectMapper();
		String wr = map.writeValueAsString(bookObj1);

		
		json.put(bookObj1.getBookID(), wr);
//json.put(String.valueOf(bookObj1.getBookID()), bookObj1.toString());

		try (FileWriter writer = new FileWriter(path); BufferedWriter buffer = new BufferedWriter(writer)) {
			buffer.write(json.toJSONString());

			System.out.println("Written!");
		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}

public static JSONObject readJsonFromFile() {
	String fileName= "/home/inc10/Desktop/book1data.txt";
		try (FileReader data = new FileReader(fileName); BufferedReader buffer = new BufferedReader(data)) {
			JSONParser parser = new JSONParser();
			String inpLine;

			while ((inpLine = buffer.readLine()) != null) {
json=(JSONObject)parser.parse(inpLine);
				return (JSONObject) parser.parse(inpLine);

			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
