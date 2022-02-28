package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import userexception.CustomException;

public class LoginDBLogic {

	public void createCredentialTable() throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();
				Statement statement = connection.createStatement();) {
			String sql = "CREATE TABLE if not exists LoginCredentials(Id int primary key auto_increment,UserId long,Password varchar(15),RoleId int,foreign key (RoleId) References Roles(RoleId));";

			statement.executeUpdate(sql);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	public int validateLogin(long userID, String password) throws CustomException {
		int roleId = -1;
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();
				Statement statement = connection.createStatement();) {

			String sql = "Select RoleId from LoginCredentials where UserId=" + userID + " and password='" + password
					+ "';";

			try (ResultSet result = statement.executeQuery(sql);) {
				while (result.next()) {
					roleId = result.getInt(1);
					
				}
				return roleId;
			} catch (Exception ex) {
				throw new CustomException(ex);
			}

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
