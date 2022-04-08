package access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AccessKeyStore {

	public static void main(String[] args) {
		Properties data = new Properties();

//		String className="bankprojectdb.filelayer.FileConnector";
		
		String className="dblayer.DatabaseConnector";
		
		data.setProperty("ackey", className);

		try {

			FileWriter writeData = new FileWriter("className.txt");

			File f=new File("className.txt");
			System.out.println("path:"+f.getAbsolutePath());
			data.store(writeData, "contains value ");
			
			System.out.println("Access key stored!");

		} catch (IOException e) {

			System.out.println(e);

		}

	}

}
