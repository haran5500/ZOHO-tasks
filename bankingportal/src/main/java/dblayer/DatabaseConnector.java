package dblayer;

import java.util.Map;

import account.AccountData;
import customer.CustomerData;
import interfaces.InterConnector;
import userexception.CustomException;
import utilities.CheckerUtil;
import validatorutil.Validator;

public class DatabaseConnector implements InterConnector {
	CustomerDBLogic customerLogic = new CustomerDBLogic();
	AccountDBLogic accountLogic = new AccountDBLogic();
	LoginDBLogic loginDB = new LoginDBLogic();
	CredentialsDBLogic credDB = new CredentialsDBLogic();

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

		CheckerUtil.validateNumbeNegative(userID);
		Validator.validateString(password);

		return loginDB.validateLogin(userID, password);
	}

	// Methods for Customer Data access
	@Override
	public Map<Long, CustomerData> getAllCustomers() throws CustomException {
		return customerLogic.getAllCustomers();
	}

	@Override
	public long addNewCustomer(CustomerData customer) throws CustomException {
		Validator.validateObject(customer);

		return customerLogic.addNewCustomer(customer);
	}

	@Override
	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerId);

		return customerLogic.getCustomerDetailsByID(customerId);

	}

	@Override
	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);

		return customerLogic.changeCustomerStatus(customerID, statusVal);
	}

	@Override
	public void updateCustomerInfo(CustomerData customerObj) throws CustomException {
		Validator.validateObject(customerObj);

		customerLogic.updateCustomerInfo(customerObj);
	}

	// Methods for Account Data access
	@Override
	public long addNewAccount(long customerID, AccountData account) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		Validator.validateObject(account);

		account.setCustID(customerID);
		return accountLogic.addNewAccount(account);
	}

	@Override
	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException {
		return accountLogic.getAllCustomerAccounts();
	}

	@Override
	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);

		return accountLogic.getAccountByCustomerID(customerID);
	}

	@Override
	public void updateAccountInfo(AccountData accountObj) throws CustomException {
		Validator.validateObject(accountObj);

		accountLogic.updateAccountInfo(accountObj);
	}

	@Override
	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);

		return accountLogic.getCustomerAccountsByStatus(customerID, true);
	}

	@Override
	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);

		return accountLogic.getCustomerAccountsByStatus(customerID, false);
	}

	@Override
	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountNum);

		return accountLogic.getCustomerAccountByAccountNum(customerID, accountNum);
	}

	@Override
	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountNum);

		return accountLogic.getBalanceByAccountNum(customerID, accountNum);
	}

	@Override
	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountNum);

		return accountLogic.changeStatus(customerID, accountNum, statusVal);
	}

	@Override
	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountNum);
		CheckerUtil.validateNumbeNegative(amount);

		return accountLogic.depositAmount(customerID, accountNum, amount);
	}

	@Override
	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerID);
		CheckerUtil.validateNumbeNegative(accountNum);
		CheckerUtil.validateNumbeNegative(amount);

		return accountLogic.withdrawAmount(customerID, accountNum, amount);
	}

	@Override
	public long getCustomerIDByAccountNum(long accountNum) throws CustomException {
		CheckerUtil.validateNumbeNegative(accountNum);

		return accountLogic.getCustomerIDByAccountNum(accountNum);
	}

	// method to tranfer amount between accounts
	@Override
	public double[] tranferAmount(long fromAccountNum, long toAccountNum, double amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(fromAccountNum);
		CheckerUtil.validateNumbeNegative(toAccountNum);
		CheckerUtil.validateNumbeNegative(amount);

		return accountLogic.tranferAmount(fromAccountNum, toAccountNum, amount);
	}

	// method to add credentials of customer
	@Override
	public void addCustomerCredentials(long customerId, String password) throws CustomException {
		CheckerUtil.validateNumbeNegative(customerId);
		Validator.validateString(password);

		credDB.addCustomerCredentials(customerId, password);
	}
}
