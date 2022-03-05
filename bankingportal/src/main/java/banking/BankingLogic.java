package banking;

import java.util.Map;
import java.util.Properties;

import account.AccountData;
import branches.BankBranchDetails;
import customer.CustomerData;
import dblayer.DatabaseConnector;
import interfaces.InterConnector;
import storage.DataStorage;
import userexception.CustomException;
import utilities.CheckerUtil;
import validatorutil.Validator;

public class BankingLogic {

	//
	DataStorage cache = new DataStorage();

	Properties property = new Properties();

	InterConnector connector = null;

	public BankingLogic() throws CustomException {
		loadInterface();

		loadData();

	}

	private void loadInterface() throws CustomException {
		try {
//
//			FileReader readData = new FileReader("className.txt");
//
//			BufferedReader buffer = new BufferedReader(readData);
//
//			property.load(buffer);
//
//			String name = property.getProperty("ackey");

			/* Class className = Class.forName(name); */

//			Class className = Class.forName("DatabaseConnector");
//			Object object = className.getDeclaredConstructor().newInstance();

			connector = (InterConnector) new DatabaseConnector();

			connector.initialize();

		} catch (Exception e) {

			throw new CustomException(e);

		}

	}

	private void loadData() throws CustomException {
		Map<Long, CustomerData> customerMap = connector.getAllCustomers();
		Map<Long, Map<Long, AccountData>> accountMap = connector.getAllCustomerAccounts();

		cache.setData(customerMap, accountMap);
	}

	// Methods to validate User Login
	public int validateUserLogin(long userID, String password) throws CustomException {
		try {
			return connector.validateLogin(userID, password);
		} catch (CustomException ex) {

			throw new CustomException(ex);
		}
	}

	// Customer Gateway access methods

	public long addNewCustomer(CustomerData newCustomer) throws CustomException {
		Validator.validateObject(newCustomer);

		long customerId = connector.addNewCustomer(newCustomer);

		newCustomer.setId(customerId);
		cache.addNewCustomer(customerId, newCustomer);

		String password = "customer" + customerId;
		addCustomerCredentials(customerId, password);

		return customerId;
	}

	public Map<Long, CustomerData> getAllCustomerDetails() {
		return cache.getAllCustomer();
	}

	public CustomerData getCustomerDetailsByID(long customerID) throws CustomException {

		return cache.getCustomerByID(customerID);

	}

	public void changeCustomerStatus(long customerId, boolean statusValue) throws CustomException {
		boolean statusVal = connector.switchCustomerStatus(customerId, statusValue);

		cache.changeCustomerStatus(customerId, statusVal);

	}

	public void updateCustomerInfo(CustomerData customerObj) throws CustomException {

		Validator.validateObject(customerObj);

		connector.updateCustomerInfo(customerObj);

		cache.updateCustomerInfo(customerObj);
	}

	public Map<Long, CustomerData> getAllActiveCustomers() throws CustomException {
		return cache.getAllActiveCustomers();
	}

	public Map<Long, CustomerData> getAllInActiveCustomers() throws CustomException {

		return cache.getAllInActiveCustomers();
	}

	// Account Gateway access methods

	public void addNewAccount(long customerID, AccountData newAccount) throws CustomException {
		Validator.validateObject(newAccount);

		BankBranchDetails branch = new BankBranchDetails();

		String ifsc = branch.fetchIFSCCode(newAccount.getLocation());

		newAccount.setIfscCode(ifsc);

		long accountNum = connector.addNewAccount(customerID, newAccount);

		newAccount.setAccID(accountNum);
		cache.addAccount(customerID, newAccount);
	}

	public void updateAccountInfo(AccountData accountObj) throws CustomException {

		Validator.validateObject(accountObj);

		BankBranchDetails branch = new BankBranchDetails();
		
		String ifsc = branch.fetchIFSCCode(accountObj.getLocation());

		accountObj.setIfscCode(ifsc);

		connector.updateAccountInfo(accountObj);
		
		cache.updateAccountInfo(accountObj);
	}

	public Map<Long, Map<Long, AccountData>> getAllAccounts() {
		return cache.getAllCustomerAccounts();
	}

	public Map<Long, AccountData> getAccountByCustomerID(long customerID) throws CustomException {

		return cache.getCustomerAccountByID(customerID);
	}

	public AccountData getAccountByAccountNum(long customerID, long AccountNumber) throws CustomException {

		return cache.getCustomerAccountByAccountNum(customerID, AccountNumber);
	}

	public Map<Long, Map<Long, AccountData>> getAllActiveAccounts() throws CustomException {
		return cache.getAllActiveAccounts();
	}

	public Map<Long, Map<Long, AccountData>> getAllInActiveAccounts() throws CustomException {

		return cache.getAllInActiveAccounts();
	}

	public Map<Long, AccountData> getActiveAccounts(long customerID) throws CustomException {

		return cache.getActiveCustomerAccountsByCustomerID(customerID);
	}

	public Map<Long, AccountData> getInActiveAccounts(long customerID) throws CustomException {

		return cache.getInActiveCustomerAccountsByCustomerID(customerID);
	}

	public double getBalance(long customerID, long AccountNum) throws CustomException {

		return cache.getAccountBalanceByAccountNum(customerID, AccountNum);
	}

	public void withdrawAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		double newAmount = connector.withdrawAmount(customerID, accountNum, amount);
		cache.withdrawAmount(customerID, accountNum, newAmount);
	}

	public void depositAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		double newAmount = connector.depositAmount(customerID, accountNum, amount);
		cache.depositAmount(customerID, accountNum, newAmount);
	}

	public void setAccountStatus(long customerID, long accountNum, boolean statusValue) throws CustomException {

		connector.switchAccountStatus(customerID, accountNum, statusValue);
		cache.changeAccountStatus(customerID, accountNum, statusValue);
	}

	// method to do transaction operation between two accounts
	public void tranferAmount(long fromAccountNum, long toAccountNum, double amount) throws CustomException {
		long fromCustomer = connector.getCustomerIDByAccountNum(fromAccountNum);
		long toCustomer = connector.getCustomerIDByAccountNum(toAccountNum);

		double[] newBalances = connector.tranferAmount(fromAccountNum, toAccountNum, amount);
	
		cache.tranferAmount(fromCustomer, fromAccountNum, toCustomer, toAccountNum, newBalances);
	}

	// getting customer id By account num
	public long getCustomerIDByAccNum(long accountNum) throws CustomException {
		return connector.getCustomerIDByAccountNum(accountNum);
	}

	// Branch access methods
	public String[] loadBranches() {
		BankBranchDetails branch = new BankBranchDetails();
		return branch.getBranches();
	}

	// method to add credentials of customer

	public void addCustomerCredentials(long customerId, String password) throws CustomException {
		connector.addCustomerCredentials(customerId, password);
	}

	public static void main(String[] args) {
		try {
			BankingLogic logic = new BankingLogic();
			System.out.println("Done!");

			System.out.println("returned:" + logic.validateUserLogin(111, "admin123"));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
