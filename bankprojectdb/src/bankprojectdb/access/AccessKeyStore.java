package bankprojectdb.access;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AccessKeyStore {

	public static void main(String[] args) {
		Properties data = new Properties();

//		String className="bankprojectdb.filelayer.FileConnector";
		
		String className="bankprojectdb.dblayer.DatabaseConnector";
		
		data.setProperty("ackey", className);

		try {

			FileWriter writeData = new FileWriter("className.txt");

			data.store(writeData, "contains value ");
			
			System.out.println("Access key stored!");

		} catch (IOException e) {

			System.out.println(e);

		}

	}

}
