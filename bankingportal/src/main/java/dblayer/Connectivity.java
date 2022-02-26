package dblayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import userexception.CustomException;

public enum Connectivity {

	CONNECTION;

	static final String url = "jdbc:mysql://localhost/BankProject";
	static final String userName = "root";
	static final String passWord = "Root@123";

	public Connection getMySQLConnection() throws CustomException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, passWord);
			return conn;
		} catch (Exception ex) {
			throw new CustomException(ex);
		}

	}

}