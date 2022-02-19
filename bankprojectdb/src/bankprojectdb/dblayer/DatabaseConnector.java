package bankprojectdb.dblayer;

import java.util.Map;

import bankprojectdb.account.AccountData;
import bankprojectdb.customer.CustomerData;
import bankprojectdb.interfaces.InterConnector;
import userexception.CustomException;

public class DatabaseConnector implements InterConnector {
	CustomerDBLogic customerLogic = new CustomerDBLogic();
	AccountDBLogic accountLogic = new AccountDBLogic();

	// Methods for Initialize
	public void initialize()throws CustomException
	{
		customerLogic.createCustomerTable();
		accountLogic.createAccountTable();
	}
	// Methods for Customer Data access
	public Map<Long, CustomerData> getAllCustomers() throws CustomException {
		return customerLogic.getAllCustomers();
	}

	public long addNewCustomer(CustomerData customer) throws CustomException {
		return customerLogic.addNewCustomer(customer);
	}

	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException {
		return customerLogic.getCustomerDetailsByID(customerId);

	}

	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException {
		return customerLogic.changeCustomerStatus(customerID, statusVal);
	}

	// Methods for Account Data access

	public long addNewAccount(long customerID, AccountData account) throws CustomException {
		return accountLogic.addNewAccount(account);
	}

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException {
		return accountLogic.getAllCustomerAccounts();
	}

	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {
		return accountLogic.getAccountByCustomerID(customerID);
	}
	
	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException {
		return accountLogic.getCustomerAccountsByStatus(customerID, true);
	}

	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {
		return accountLogic.getCustomerAccountsByStatus(customerID, false);
	}

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		return accountLogic.getCustomerAccountByAccountNum(customerID, accountNum);
	}

	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		return accountLogic.getBalanceByAccountNum(customerID, accountNum);
	}

	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {
		return accountLogic.changeStatus(customerID, accountNum, statusVal);
	}

	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException {
		return accountLogic.depositAmount(customerID, accountNum, amount);
	}

	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException {
		return accountLogic.withdrawAmount(customerID, accountNum, amount);
	}
	
}
