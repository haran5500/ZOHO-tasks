package dblayer;

import java.sql.Connection;
import java.sql.DriverManager;

import userexception.CustomException;
import validatorutil.Validator;

public enum Connectivity {

	CONNECTION;

	static final String url = "jdbc:mysql://localhost/BankProject";
	static final String userName = "root";
	static final String passWord = "Root@123";

	public Connection getMySQLConnection() throws CustomException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Validator.validateString(url);
			Validator.validateString(userName);
			Validator.validateString(passWord);

			Connection conn = DriverManager.getConnection(url, userName, passWord);
			return conn;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CustomException(ex);
		}

	}

}