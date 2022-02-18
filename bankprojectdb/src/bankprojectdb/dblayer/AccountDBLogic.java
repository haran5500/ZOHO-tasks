package bankprojectdb.dblayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bankprojectdb.account.AccountData;
import bankprojectdb.customer.CustomerData;
import userexception.CustomException;

public class AccountDBLogic {

	public void createAccountTable() throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();

				Statement statement = connection.createStatement();) {

			String sql = "CREATE TABLE if not exists AccountData(AccountID int not null auto_increment PRIMARY KEY,CustomerID int not null,AccountType varchar(20),Location varchar(20),IFSCCode varchar(20),AccountBalance double,Status BOOLEAN,FOREIGN KEY (CustomerID) REFERENCES CustomerData(CustomerID))";

			statement.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new CustomException(ex);

		}

	}

	public long addNewAccount(AccountData accountObj) throws CustomException {
		long AccountID = -1;
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {
			String insertQuery = "insert into AccountData(CustomerID,AccountType,Location,IFSCCode,Balance,Status) valuAccountes(?,?,?,?,?,?);";

			try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
				statement.setLong(1, accountObj.getCustID());
				statement.setString(2, accountObj.getAccType());
				statement.setString(3, accountObj.getLocation());
				statement.setString(4, accountObj.getIfscCode());
				statement.setDouble(5, accountObj.getBalance());
				statement.setBoolean(6, accountObj.getStatus());

//				statement.executeQuery(insertQuery);
				statement.executeUpdate();
				try (ResultSet keySet = statement.getGeneratedKeys();) {
					while (keySet.next()) {
						AccountID = keySet.getInt(1);
					}
				} catch (Exception ex) {
					throw new CustomException(ex);

				}
			} catch (SQLException ex) {
				throw new CustomException(ex);

			}

		} catch (SQLException ex) {
			throw new CustomException(ex);

		}
		return AccountID;
	}

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException {
		Map<Long, Map<Long, AccountData>> customerAccountsMap = new HashMap<Long, Map<Long, AccountData>>();
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			try (Statement statement = connection.createStatement()) {

				String sql = "Select * From CustomerData;";

				try (ResultSet result = statement.executeQuery(sql);) {

					while (result.next()) {

						AccountData accounts = new AccountData();

						accounts.setAccID(result.getLong("AccountID"));

						accounts.setCustID(result.getLong("CustomerID"));

						accounts.setAccType(result.getString("AccountType"));

						accounts.setLocation(result.getString("Location"));

						accounts.setIfscCode(result.getString("IFSCCode"));

						accounts.setBalance(result.getDouble("AccountBalance"));

						accounts.setStatus(result.getBoolean("Status"));

						long custID = accounts.getCustID();
						long accID = accounts.getAccID();

						Map<Long, AccountData> accountsDet = customerAccountsMap.get(custID);

						if (accountsDet == null) {
							accountsDet = new HashMap<Long, AccountData>();
							customerAccountsMap.put(custID, accountsDet);
						}

						accountsDet.put(accID, accounts);
						result.close();
					}
				}
				return customerAccountsMap;

			} catch (SQLException ex) {
				throw new CustomException(ex);

			}

		} catch (Exception ex) {
			throw new CustomException(ex);

		}

	}

	public Map<Long, AccountData> getAccountByCustomerID(long customerID) throws CustomException {

		Map<Long, AccountData> customerMap = new HashMap<Long, AccountData>();

		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			try (Statement statement = connection.createStatement()) {

				String sql = "Select * From AccountData where CustomerID=" + customerID + ";";

				try (ResultSet result = statement.executeQuery(sql);) {

					while (result.next()) {

						AccountData accounts = new AccountData();

						accounts.setAccID(result.getLong("AccountID"));

						accounts.setCustID(result.getLong("CustomerID"));

						accounts.setAccType(result.getString("AccountType"));

						accounts.setLocation(result.getString("Location"));

						accounts.setIfscCode(result.getString("IFSCCode"));

						accounts.setBalance(result.getDouble("AccountBalance"));

						accounts.setStatus(result.getBoolean("Status"));

						long accID = accounts.getAccID();
						customerMap.put(accID, accounts);
					}
				} catch (Exception ex) {
					throw new CustomException(ex);

				}
				return customerMap;

			} catch (SQLException ex) {
				throw new CustomException(ex);

			}

		} catch (Exception ex) {
			throw new CustomException(ex);

		}
	}

	public void changeStatus()throws CustomException
	{
		
	}
}
