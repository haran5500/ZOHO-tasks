package storage;

import java.util.HashMap;
import java.util.Map;

import account.AccountData;
import customer.CustomerData;
import utilities.CheckerUtil;
import validatorutil.Validator;
import userexception.CustomException;

public class DataStorage {

	Map<Long, CustomerData> customerDataMap = new HashMap<Long, CustomerData>();

	Map<Long, Map<Long, AccountData>> customerAccountMap = new HashMap<Long, Map<Long, AccountData>>();

	// validator methods

	public void checkCustomerID(long customerID) throws CustomException {
		if (!customerAccountMap.containsKey(customerID)) {
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

	public void setCustomerData(Map<Long, CustomerData> customerMap) {

		customerDataMap = customerMap;

	}

	public void setAccountData(Map<Long, Map<Long, AccountData>> accountMap) {

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

	public void updateCustomerInfo(CustomerData customerObj) throws CustomException {
		Validator.validateObject(customerObj);

		long custId = customerObj.getId();

		checkCustomerExists(custId);

		CustomerData customer = getCustomerByID(customerObj.getId());

		customer.setName(customerObj.getName());
		customer.setCity(customerObj.getCity());
		customer.setGender(customerObj.getGender());
		customer.setMobileNo(customerObj.getMobileNo());
		customer.setStatus(customerObj.getStatus());

	}

	private Map<Long, CustomerData> getCustomersByStatus(boolean boolValue) throws CustomException {
		Map<Long, CustomerData> customersData = customerDataMap;

		Map<Long, CustomerData> resultMap = new HashMap<Long, CustomerData>();

		for (long key : customersData.keySet()) {
			CustomerData customerObj = getCustomerByID(key);
			if (customerObj != null) {
				if (customerObj.getStatus() == boolValue) {
					resultMap.put(key, customerObj);
				}
			}
		}
		return resultMap;
	}

	public Map<Long, CustomerData> getAllActiveCustomers() throws CustomException {
		Map<Long, CustomerData> resultMap = getCustomersByStatus(true);

		return resultMap;
	}

	public Map<Long, CustomerData> getAllInActiveCustomers() throws CustomException {
		Map<Long, CustomerData> resultMap = getCustomersByStatus(false);

		return resultMap;
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

	public void updateAccountInfo(AccountData accountObj) throws CustomException {

		Validator.validateObject(accountObj);

		long custId = accountObj.getCustID();

		checkCustomerExists(custId);

		long accId = accountObj.getAccID();

		checkCustomerAccount(custId, accId);

		AccountData account = getCustomerAccountByAccountNum(custId, accId);
		account.setAccID(accId);
		account.setCustID(custId);
		account.setLocation(accountObj.getLocation());
		account.setIfscCode(accountObj.getIfscCode());
		account.setAccType(account.getAccType());
		account.setBalance(accountObj.getBalance());
		account.setStatus(account.getStatus());

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

	// method to do transaction operation between two accounts
	public void tranferAmount(long fromCustomer, long fromAccountNum, long toCustomer, long toAccountNum,
			double[] newBalances) throws CustomException {
		checkCustomerID(fromCustomer);
		checkCustomerID(fromCustomer);

		checkCustomerAccount(fromCustomer, fromAccountNum);
		checkCustomerAccount(toCustomer, toAccountNum);

		Map<Long, AccountData> accounts1 = getCustomerAccountByID(fromCustomer);
		accounts1.get(fromAccountNum).setBalance(newBalances[0]);

		Map<Long, AccountData> accounts2 = getCustomerAccountByID(toCustomer);
		accounts2.get(toAccountNum).setBalance(newBalances[1]);

	}

	private Map<Long, Map<Long, AccountData>> getAccountsByStatus(boolean boolValue) throws CustomException {
		Map<Long, Map<Long, AccountData>> accountsMap = getAllCustomerAccounts();
		Map<Long, Map<Long, AccountData>> resultMap = new HashMap<Long, Map<Long, AccountData>>();

		for (long key : accountsMap.keySet()) {
			Map<Long, AccountData> fetchedMap = getAccountsByStatus(key, boolValue);

			if (!fetchedMap.isEmpty()) {
				resultMap.put(key, fetchedMap);
			}
		}
		return resultMap;
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

	public Map<Long, Map<Long, AccountData>> getAllActiveAccounts() throws CustomException {
		Map<Long, Map<Long, AccountData>> resultMap = getAccountsByStatus(true);
		return resultMap;
	}

	public Map<Long, Map<Long, AccountData>> getAllInActiveAccounts() throws CustomException {
		Map<Long, Map<Long, AccountData>> resultMap = getAccountsByStatus(false);

		return resultMap;
	}

	public Map<Long, AccountData> getActiveCustomerAccountsByCustomerID(long customerID) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, true);

		return resultMap;

	}

	public Map<Long, AccountData> getInActiveCustomerAccountsByCustomerID(long customerID) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, false);

		return resultMap;

	}
}
