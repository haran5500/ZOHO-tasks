package dblayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import account.AccountData;
import utilities.CheckerUtil;
import userexception.CustomException;

public class AccountDBLogic {

	public void createAccountTable() throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();

				Statement statement = connection.createStatement();) {

			String sql = "CREATE TABLE if not exists AccountData(AccountID int not null auto_increment PRIMARY KEY,CustomerID int not null,AccountType varchar(20),Location varchar(20),IFSCCode varchar(20),AccountBalance double,Status BOOLEAN default TRUE,FOREIGN KEY (CustomerID) REFERENCES CustomerData(CustomerID))ENGINE=InnoDB auto_increment=100001;";

			statement.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new CustomException(ex);

		}

	}

	public long addNewAccount(AccountData accountObj) throws CustomException {
		long AccountID = -1;
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {
			String insertQuery = "insert into AccountData(CustomerID,AccountType,Location,IFSCCode,AccountBalance,Status) values(?,?,?,?,?,?);";

			try (PreparedStatement statement = connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)) {
				statement.setLong(1, accountObj.getCustID());
				statement.setString(2, accountObj.getAccType());
				statement.setString(3, accountObj.getLocation());
				statement.setString(4, accountObj.getIfscCode());
				statement.setDouble(5, accountObj.getBalance());
				statement.setBoolean(6, accountObj.getStatus());

//				statement.executeQuery(insertQuery);
				statement.executeUpdate();
				try (ResultSet keySet = statement.getGeneratedKeys();) {
					if (keySet.next()) {
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

				String sql = "Select * From AccountData;";

				try (ResultSet result = statement.executeQuery(sql);) {

					if(result==null)
					{
						return customerAccountsMap;
					}
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

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		
		Map<Long, AccountData> accounts = getAccountByCustomerID(customerID);
		return accounts.get(accountNum);
	}
	
	public boolean changeStatus(long customerID, long accountID, boolean statusVal) throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String sql = "update AccountData set Status=? where AccountID=? and CustomerID=?;";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setBoolean(1, statusVal);
				statement.setLong(2, accountID);
				statement.setLong(3, customerID);

				statement.executeUpdate();
			} catch (Exception ex) {
				throw new CustomException(ex);

			}
		} catch (Exception ex) {
			throw new CustomException(ex);

		}
		return statusVal;
	}

	public double getBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String selectQuery = "select AccountBalance from AccountData where AccountID=? and CustomerID=?;";

			try (PreparedStatement preparedSelect = connection.prepareStatement(selectQuery)) {
				preparedSelect.setLong(1, accountNum);
				preparedSelect.setLong(2, customerID);

				try (ResultSet result = preparedSelect.executeQuery()) {
					long fetchedBalance = 0;
					while (result.next()) {
						fetchedBalance = result.getLong(1);
					}
					return fetchedBalance;
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

	public void checkAccountType(String accountType) throws CustomException {
		if (accountType.equals("Deposit")) {
			throw new CustomException("Withdraw cannot be possible in deposit account!");
		}
	}

	public void updateBalance(long customerID, long accountID, double amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountID);
		CheckerUtil.validateNumbeNegative(amount);

		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String sql = "update AccountData set AccountBalance=? where AccountID=? and CustomerID=?;";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setDouble(1, amount);
				statement.setLong(2, accountID);
				statement.setLong(3, customerID);
				statement.executeUpdate();

			} catch (Exception ex) {
				throw new CustomException(ex);

			}
		} catch (Exception ex) {
			throw new CustomException(ex);

		}
	}

	public double withdrawAmount(long customerID, long accountID, double amount) throws CustomException {

		double newBalance = -1;
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountID);
		CheckerUtil.validateNumbeNegative(amount);
		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String selectQuery = "select AccountType from AccountData where AccountID=? and CustomerID=?";

			try (PreparedStatement preparedSelect = connection.prepareStatement(selectQuery)) {
				preparedSelect.setLong(1, accountID);
				preparedSelect.setLong(2, customerID);
				double fetchedBalance = 0;
				try (ResultSet result = preparedSelect.executeQuery()) {
					while (result.next()) {
						String accountType = result.getString(1);
						checkAccountType(accountType);
						fetchedBalance = getBalanceByAccountNum(customerID, accountID);
						CheckerUtil.checkLowBalance(fetchedBalance, amount);

					}
					if (fetchedBalance != 0) {
						newBalance = fetchedBalance - amount;

						updateBalance(customerID, accountID, newBalance);
					}
				} catch (Exception ex) {
					throw new CustomException(ex);

				}

			}
		} catch (Exception ex) {
			throw new CustomException(ex);

		}
		return newBalance;
	}

	public double depositAmount(long customerID, long accountID, double amount) throws CustomException {
		double newBalance = -1;

		try (Connection connection = Connectivity.CONNECTION.getMySQLConnection();) {

			String selectQuery = "select AccountType from AccountData where AccountID=? and CustomerID=?";

			try (PreparedStatement preparedSelect = connection.prepareStatement(selectQuery)) {
				preparedSelect.setLong(1, accountID);
				preparedSelect.setLong(2, customerID);

				try (ResultSet result = preparedSelect.executeQuery()) {
					while (result.next()) {
						String accountType = result.getString(1);
						checkAccountType(accountType);
						double fetchedBalance = getBalanceByAccountNum(customerID, accountID);

						newBalance = fetchedBalance + amount;

						updateBalance(customerID, accountID, newBalance);
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
		return newBalance;
	}
	
	public Map<Long, AccountData> getCustomerAccountsByStatus(long customerID,boolean statusValue)throws CustomException
	{

		Map<Long, AccountData> customerAcc = getAccountByCustomerID(customerID);

		Map<Long, AccountData> resultMap = new HashMap<Long, AccountData>();

		for (long key : customerAcc.keySet()) {
			boolean status = customerAcc.get(key).getStatus();
			if (status == statusValue) {
				resultMap.put(key, customerAcc.get(key));
			}
		}
		return resultMap;
	}
}
