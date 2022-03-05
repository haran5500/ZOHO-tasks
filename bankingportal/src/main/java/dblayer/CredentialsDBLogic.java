package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import userexception.CustomException;

public class CredentialsDBLogic {

	// method to add credentials of customer

	public void addCustomerCredentials(long customerId, String password) throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			try (Statement stmt = connection.createStatement()) {

				try (ResultSet rs = stmt.executeQuery("Select RoleId from Roles where RoleName='Customer';")) {

					long roleId = -1;
					while (rs.next()) {
						roleId = rs.getLong(1);
					}
					String insertQuery = "insert into LoginCredentials(UserId,Password,RoleId) values(?,?,?);";

					try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
						if (roleId == -1) {
							throw new CustomException("Role Id is not fetched! Couldn't process!");
						}
						statement.setLong(1, customerId);
						statement.setString(2, password);
						statement.setLong(3, roleId);

						statement.executeUpdate();
					} catch (Exception ex) {
						throw new CustomException(ex);

					}
				} catch (Exception ex) {
					throw new CustomException(ex);

				}
			} catch (Exception ex) {
				throw new CustomException(ex);

			}
		} catch (Exception ex) {
			throw new CustomException(ex);

		}
	}
}
