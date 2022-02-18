package bankprojectdb.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import userexception.CustomException;

public class FilesIO {

	public static void writeJsonToFile(String inpStr1, String inpStr2, JSONObject dataJson, JSONObject idJson,
			String fileName) throws CustomException {
		try (FileWriter writer = new FileWriter(fileName); BufferedWriter buffer = new BufferedWriter(writer)) {
			String[] datas = { inpStr1 + dataJson.toJSONString(), inpStr2 + idJson.toJSONString() };

			for (int i = 0; i < datas.length; i++) {
				buffer.write(datas[i]);
				buffer.newLine();
			}

		} catch (Exception ex) {
			throw new CustomException(ex);
		}

	}

	public static JSONObject readJsonFromFile(String fileName, String startStr) throws CustomException {

		try (FileReader data = new FileReader(fileName); BufferedReader buffer = new BufferedReader(data)) {
			JSONParser parser = new JSONParser();
			String inpLine;

			while ((inpLine = buffer.readLine()) != null) {
				if (inpLine.startsWith(startStr)) {
					String value = inpLine.replaceAll(startStr, "");
					return (JSONObject) parser.parse(value);
				}

			}
			return null;
		} catch (Exception e) {

			throw new CustomException(e);
		}
	}
}
