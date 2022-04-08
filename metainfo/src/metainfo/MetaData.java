package metainfo;

import userexception.CustomException;

public class MetaData {

	public static void main(String[] args) {
		try {
			Connectivity connect = new Connectivity();
			connect.createTable();
			System.out.println("Meta Information:");
			System.out.println(connect.getStudentDetails());
		} catch (CustomException ex) {
			ex.printStackTrace();
		}
	}

}
