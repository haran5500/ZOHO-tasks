package bankprojectdb.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bankprojectdb.customer.CustomerData;
import bankprojectdb.utilities.CheckerUtil;
import userexception.CustomException;
import validatorutil.Validator;

public class CustomerDBLogic {

	public void createCustomerTable() throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();
				Statement statement = connection.createStatement();) {
			String sql = "CREATE TABLE if not exists CustomerData(CustomerID int not null auto_increment primary key,Name varchar(20),City varchar(20),MobileNO bigint);";

			statement.executeUpdate(sql);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	public long addNewCustomer(CustomerData customerObj) throws CustomException {
		Validator.validateObject(customerObj);

		long customerID = -1;

		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String insertQuery = "insert into CustomerData(Name,Gender,City,MobileNo) values(?,?,?,?);";

			try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {

				statement.setString(1, customerObj.getName());
				statement.setString(2, customerObj.getGender());
				statement.setString(3, customerObj.getCity());
				statement.setLong(4, customerObj.getMobileNo());

//				statement.executeQuery(insertQuery);
				statement.executeUpdate();
				try (ResultSet keySet = statement.getGeneratedKeys();) {
					while (keySet.next()) {
						customerID = keySet.getInt(1);
					}
				} catch (Exception ex) {
					throw new CustomException(ex);

				}

			} catch (SQLException ex) {
				throw new CustomException(ex);
			}
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
		return customerID;
	}

	public Map<Long, CustomerData> getAllCustomers() throws CustomException {

		Map<Long, CustomerData> customerMap = new HashMap<Long, CustomerData>();

		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			try (Statement statement = connection.createStatement()) {

				String sql = "Select * From CustomerData;";

				try (ResultSet result = statement.executeQuery(sql);) {

					while (result.next()) {

						CustomerData customer = new CustomerData();

						customer.setId(result.getLong("CustomerID"));

						customer.setName(result.getString("Name"));

						customer.setCity(result.getString("City"));

						customer.setGender(result.getString("Gender"));

						customer.setMobileNo(result.getLong("MobileNo"));

						long custID = customer.getId();
						customerMap.put(custID, customer);
						result.close();
					}
				}
				return customerMap;

			} catch (SQLException ex) {
				throw new CustomException(ex);

			}

		} catch (Exception ex) {
			throw new CustomException(ex);

		}

	}

	public CustomerData getCustomerDetailsByID(long customerID) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			CustomerData customer = new CustomerData();

			String sql = "SELECT * From CustomerData where CustomerID=?;";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setLong(1, customerID);

				try (ResultSet result = statement.executeQuery();) {
					if (result == null) {
						throw new CustomException("No such Customer!");
					}
					while (result.next()) {

						customer.setName(result.getString("Name"));

						customer.setCity(result.getString("Cisty"));

						customer.setGender(result.getString("Gender"));

						customer.setMobileNo(result.getLong("MobileNo"));

					}
				}
				return customer;

			} catch (SQLException ex) {
				throw new CustomException(ex);

			}

		} catch (Exception ex) {
			throw new CustomException(ex);

		}

	}

}
