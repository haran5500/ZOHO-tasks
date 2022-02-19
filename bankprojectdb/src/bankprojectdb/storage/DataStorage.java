package bankprojectdb.storage;

import java.util.HashMap;
import java.util.Map;

import bankprojectdb.account.AccountData;
import bankprojectdb.customer.CustomerData;
import bankprojectdb.utilities.CheckerUtil;
import bankprojectdb.utilities.MapUtility;
import userexception.CustomException;

public class DataStorage {

	Map<Long, CustomerData> customerDataMap = new HashMap<Long, CustomerData>();

	Map<Long, Map<Long, AccountData>> customerAccountMap = new HashMap<Long, Map<Long, AccountData>>();

	MapUtility utility = new MapUtility();

	// validator methods

	public void checkCustomerID(long customerID) throws CustomException {
		if (!customerAccountMap.containsKey(customerID)) {
			System.out.println("Key:"+customerAccountMap.keySet());
			throw new CustomException("CustomerID doesn't have any accounts!");
		}
	}

	public void checkCustomerExists(long customerID) throws CustomException {
		if (!customerDataMap.containsKey(customerID)) {
			throw new CustomException("Customer Doesn't Exist");
		}
	}

	public void checkCustomerAccount(long customerID, long accountNum) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		if (!accounts.containsKey(accountNum)) {
			throw new CustomException("Account Number doesn't match for the customer!");
		}
	}

	// storing records from the File and DB layer

	public void setData(Map<Long, CustomerData> customerMap, Map<Long, Map<Long, AccountData>> accountMap) {

		customerDataMap = customerMap;

		customerAccountMap = accountMap;

	}

	// customer details access methods

	public void addNewCustomer(long customerID, CustomerData customerInfo) throws CustomException {

		CheckerUtil.validateNumbeNegative(customerID);

		customerDataMap.put(customerID, customerInfo);
	}

	public Map<Long, CustomerData> getAllCustomer() {
		return customerDataMap;
	}

	public CustomerData getCustomerByID(long customerID) throws CustomException {

		CheckerUtil.validateNumbeNegative(customerID);

		CustomerData customer = customerDataMap.get(customerID);
		return customer;
	}

	public void changeCustomerStatus(long customerID, boolean statusVal) throws CustomException {

		CheckerUtil.validateNumbeNegative(customerID);

		customerDataMap.get(customerID).setStatus(statusVal);
	}

	// methods to access account details

	public void addAccount(long accountID, AccountData accountInfo) throws CustomException {

		long customerID = accountInfo.getCustID();

		CheckerUtil.validateNumbeNegative(customerID);

		CheckerUtil.validateNumbeNegative(accountID);

		Map<Long, AccountData> accountDet = customerAccountMap.get(customerID);

		if (accountDet == null) {
			accountDet = new HashMap<Long, AccountData>();
			customerAccountMap.put(customerID, accountDet);
		}

		accountDet.put(accountID, accountInfo);

	}

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() {

		Map<Long, Map<Long, AccountData>> allCustomerAccounts = customerAccountMap;

		return allCustomerAccounts;
	}

	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {

		CheckerUtil.validateNumbeNegative(customerID);

		return customerAccountMap.get(customerID);
	}

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		return accounts.get(accountNum);
	}

	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData object1 = getCustomerAccountByAccountNum(customerID, accountNum);

		double balance = object1.getBalance();
		return balance;
	}

	public void changeAccountStatus(long customerID, long accountID, boolean statusVal) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountID).setStatus(statusVal);

	}

	public void depositAmount(long customerID, long accountID, double amount) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountID).setBalance(amount);

	}

	public void withdrawAmount(long customerID, long accountID, double amount) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountID).setBalance(amount);
	}

	private Map<Long, AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException {
		checkCustomerID(customerID);

		Map<Long, AccountData> customerAcc = getCustomerAccountByID(customerID);

		Map<Long, AccountData> resultMap = new HashMap<Long, AccountData>();

		for (long key : customerAcc.keySet()) {
			boolean status = customerAcc.get(key).getStatus();
			if (status == boolValue) {
				resultMap.put(key, customerAcc.get(key));
			}
		}
		return resultMap;
	}

	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, true);

		return resultMap;

	}

	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, false);

		return resultMap;

	}
}
