package dblayer;

import java.util.Map;

import account.AccountData;
import customer.CustomerData;
import interfaces.InterConnector;
import userexception.CustomException;

public class DatabaseConnector implements InterConnector {
	CustomerDBLogic customerLogic = new CustomerDBLogic();
	AccountDBLogic accountLogic = new AccountDBLogic();
	LoginDBLogic loginDB = new LoginDBLogic();

	// Methods for Initialize
	@Override
	public void initialize() throws CustomException {
		customerLogic.createCustomerTable();
		accountLogic.createAccountTable();
		loginDB.createCredentialTable();
	}

	// Methods to validate User Login
	@Override
	public int validateLogin(long userID, String password) throws CustomException {
		return loginDB.validateLogin(userID, password);
	}

	// Methods for Customer Data access
	@Override
	public Map<Long, CustomerData> getAllCustomers() throws CustomException {
		return customerLogic.getAllCustomers();
	}

	@Override
	public long addNewCustomer(CustomerData customer) throws CustomException {
		return customerLogic.addNewCustomer(customer);
	}

	@Override
	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException {
		return customerLogic.getCustomerDetailsByID(customerId);

	}

	@Override
	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException {
		return customerLogic.changeCustomerStatus(customerID, statusVal);
	}

	// Methods for Account Data access
	@Override
	public long addNewAccount(long customerID, AccountData account) throws CustomException {
		account.setCustID(customerID);
		return accountLogic.addNewAccount(account);
	}

	@Override
	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException {
		return accountLogic.getAllCustomerAccounts();
	}

	@Override
	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {
		return accountLogic.getAccountByCustomerID(customerID);
	}

	@Override
	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException {
		return accountLogic.getCustomerAccountsByStatus(customerID, true);
	}

	@Override
	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {
		return accountLogic.getCustomerAccountsByStatus(customerID, false);
	}

	@Override
	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		return accountLogic.getCustomerAccountByAccountNum(customerID, accountNum);
	}

	@Override
	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		return accountLogic.getBalanceByAccountNum(customerID, accountNum);
	}

	@Override
	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {
		return accountLogic.changeStatus(customerID, accountNum, statusVal);
	}

	@Override
	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException {
		return accountLogic.depositAmount(customerID, accountNum, amount);
	}

	@Override
	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException {
		return accountLogic.withdrawAmount(customerID, accountNum, amount);
	}

}
