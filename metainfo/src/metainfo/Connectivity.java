package metainfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import userexception.CustomException;
import validatorutil.Validator;

public class Connectivity {

	static final String url = "jdbc:mysql://localhost/practice";
	static final String userName = "root";
	static final String passWord = "Root@123";

	public Connection getMySQLConnection() throws CustomException {

		try {
			Connection conn = DriverManager.getConnection(url, userName, passWord);
			return conn;
		} catch (SQLException ex) {
			throw new CustomException(ex);
		}

	}

	public void createTable() throws CustomException {

		String createQuery = "create table if not exists student(id int primary key auto_increment,name varchar(20),age int)";

		try (Connection connect = getMySQLConnection(); Statement stmt = connect.createStatement()) {
			stmt.executeUpdate(createQuery);
		} catch (SQLException ex) {
			throw new CustomException(ex);
		}

	}

	public ArrayList<Object> getStudentDetails() throws CustomException {
		String fetch = "Select * from student";

		ArrayList<Object> list = new ArrayList<Object>();

		try (Connection connect = getMySQLConnection(); Statement stmt = connect.createStatement()) {

			ResultSet result = stmt.executeQuery(fetch);
			ResultSetMetaData metaData = result.getMetaData();

			int columns = metaData.getColumnCount();

			while (result.next()) {
			}

			HashMap<Object, Object> row = new HashMap<Object, Object>();
			for (int i = 1; i <= columns; ++i) {
				row.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
			}

			list.add(row);

			result.close();
		} catch (SQLException ex) {
			throw new CustomException(ex);
		}
		return list;
	}

	public void close(Connection inpObj) throws CustomException {
		try {
			Validator.validateObject(inpObj);
			inpObj.close();
		} catch (SQLException ex) {
			throw new CustomException(ex);
		}
	}

}
