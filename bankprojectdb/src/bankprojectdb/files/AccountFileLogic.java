package bankprojectdb.files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import bankprojectdb.account.AccountData;
import bankprojectdb.branches.BankBranchDetails;
import bankprojectdb.customer.CustomerData;
import bankprojectdb.utilities.CheckerUtil;
import bankprojectdb.utilities.MapperUtility;
import userexception.CustomException;
import validatorutil.Validator;

public class AccountFileLogic {

	
	private CustomerFileLogic customerfiles = new CustomerFileLogic();

	// variables to store account related datas

	private static String accountFileName = "accountdata.txt";

	File accountFileObj = new File(accountFileName);

	// Method for initialize
		public void initializeAccountFiles()throws CustomException 
		{
			FilesIO.createNewFile(accountFileObj);
		}
		
	// Bank Branch Access Object
	private BankBranchDetails branchesDetails = new BankBranchDetails();

	// Validating Utility methods

	private void checkCustomerExists(long customerID) throws CustomException {
		Map<Long, CustomerData> customerDetailsMap = customerfiles.getAllCustomers();

		if (!customerDetailsMap.containsKey(customerID)) {
			throw new CustomException("Customer Doesn't Exist");
		}
	}

	private void checkCustomerID(long customerID) throws CustomException {

		Map<Long, Map<Long, AccountData>> accountDetailsMap = getAllCustomerAccounts();

		if (!accountDetailsMap.containsKey(customerID)) {
			throw new CustomException("CustomerID doesn't have any accounts!");
		}
	}

	private void checkCustomerAccount(long customerID, long accountNum) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		if (!accounts.containsKey(accountNum)) {
			throw new CustomException("Account Number doesn't match for the customer!");
		}
	}

	private void checkWithdrawPossible(long customerID, long accountNum) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData accounts = getCustomerAccountByAccountNum(customerID, accountNum);

		if (accounts.getAccType().equals("Deposit")) {
			throw new CustomException("Withdraw cannot be possible in deposit account!");
		}

	}

	private void writeAccountDataToFile(JSONObject customerAccounts, JSONObject accIDJson) throws CustomException {

		FilesIO.writeJsonToFile("#AccountData#", "#AccountID#", customerAccounts, accIDJson, accountFileName);

	}

	private JSONObject readAccountDataJsonFromFile() throws CustomException {
		JSONObject customerAccounts = FilesIO.readJsonFromFile(accountFileName, "#AccountData#");

		return customerAccounts;
	}

	private JSONObject readLastAccountID() throws CustomException {
		JSONObject accIDJson = FilesIO.readJsonFromFile(accountFileName, "#AccountID#");
		return accIDJson;
	}

	private JSONObject fetchCustomerJSON(AccountData account) throws CustomException {
		Validator.validateObject(account);

		JSONObject customerAccounts = readAccountDataJsonFromFile();
		String custID = String.valueOf(account.getCustID());

		JSONObject accountDet = (JSONObject) customerAccounts.get(custID);
		return accountDet;

	}

	public long addNewAccount(long customerID, AccountData account) throws CustomException {

//		readCustomerDataJsonFromFile();
//
//		readAccountDataJsonFromFile();

		Validator.validateObject(account);

		checkCustomerExists(customerID);

		JSONObject lastGenID = readLastAccountID();

		Long accNum = Long.parseLong((String) lastGenID.get("AccountID"));

		accNum++;

		String iFSC = branchesDetails.fetchIFSCCode(account.getLocation());

		account.setAccID(accNum);
		account.setCustID(customerID);
		account.setIfscCode(iFSC);

		addAccount(account);

		return accNum;

	}

	private void addAccount(AccountData account) throws CustomException {

		Validator.validateObject(account);

		long custID = account.getCustID();
		checkCustomerExists(custID);

		addCustomerAccounts(account);

	}

	private void addCustomerAccounts(AccountData account) throws CustomException {

		Validator.validateObject(account);

		JSONObject accIDJson = new JSONObject();
		accIDJson.put("AccountID", String.valueOf(account.getAccID()));

		Map<Long, Map<Long, AccountData>> accountDetailsMap = getAllCustomerAccounts();

		Map<Long, AccountData> accountDet = accountDetailsMap.get(account.getCustID());

		if (accountDet == null) {
			accountDet = new HashMap<Long, AccountData>();
			accountDetailsMap.put(account.getCustID(), accountDet);
		}

		accountDet.put(account.getAccID(), account);

		String convertedString = MapperUtility.accountMapToJson(accountDetailsMap);
		writeAccountDataToFile(MapperUtility.parseStringToJsonObject(convertedString), accIDJson);

	}

	private void updateCustomerAccounts(AccountData account) throws CustomException {
		Validator.validateObject(account);

		JSONObject customerData = readAccountDataJsonFromFile();

		JSONObject accountDet = fetchCustomerJSON(account);

		JSONObject accIDJson = readLastAccountID();

		String accountID = String.valueOf(account.getAccID());
		String data = MapperUtility.accountDataToJson(account);

		accountDet.put(accountID, data);

		customerData.put(account.getCustID(), accountDet);
		writeAccountDataToFile(customerData, accIDJson);
	}

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException {

		JSONObject allCustomerAccounts = readAccountDataJsonFromFile();

		Map<Long, Map<Long, AccountData>> customerAccountsMap = new HashMap<Long, Map<Long, AccountData>>();

		for (Object keys : allCustomerAccounts.keySet()) {

			JSONObject subJson = (JSONObject) allCustomerAccounts.get(keys);

			Long mapKey = Long.parseLong((String) keys);
			for (Object key : subJson.keySet()) {

				Map<Long, AccountData> accountMap = customerAccountsMap.get(mapKey);
				if (accountMap == null) {
					accountMap = new HashMap<Long, AccountData>();
					customerAccountsMap.put(mapKey, accountMap);
				}
				Long subKey = Long.parseLong((String) key);

				accountMap.put(subKey, MapperUtility.fromJsonToAccountData(String.valueOf(subJson.get(key))));

			}
		}
		return customerAccountsMap;

	}

	private List<AccountData> getListOfAccounts(JSONObject inpObj) throws CustomException {
		List<AccountData> accountList = new ArrayList<AccountData>();

		for (Object key : inpObj.keySet()) {
			accountList.add(MapperUtility.fromJsonToAccountData(String.valueOf(inpObj.get(key))));

		}
		return accountList;
	}

	private JSONObject getJSONCustomerAccountByID(long customerID) throws CustomException {
		checkCustomerID(customerID);

		JSONObject customerAccountsJson = readAccountDataJsonFromFile();

		String custID = String.valueOf(customerID);
		return (JSONObject) customerAccountsJson.get(custID);
	}

	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {

		checkCustomerID(customerID);

		Map<Long, Map<Long, AccountData>> accountDetailsMap = getAllCustomerAccounts();

		return accountDetailsMap.get(customerID);

	}

	public Map<Long, AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException {

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

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		return accounts.get(accountNum);

	}

	public AccountData getJSONCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		JSONObject accounts = getJSONCustomerAccountByID(customerID);
		String returnData = (String) accounts.get(String.valueOf(accountNum));

		AccountData accData = MapperUtility.fromJsonToAccountData(returnData);
//		return (AccountData) accounts.get(String.valueOf(accountNum));
		return accData;
	}

	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData object1 = getCustomerAccountByAccountNum(customerID, accountNum);

		double balance = object1.getBalance();
		return balance;
	}

	private void updateBalance(long customerID, long accountNum, double newBal) throws CustomException {

//		CheckerUtil.validateNumbeNegative(newBal);

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountNum).setBalance(newBal);

		JSONObject accIDJson = readLastAccountID();

		Map<Long, Map<Long, AccountData>> accountDetailsMap = getAllCustomerAccounts();

		accountDetailsMap.put(customerID, accounts);

		String convertedString = MapperUtility.accountMapToJson(accountDetailsMap);
		writeAccountDataToFile(MapperUtility.parseStringToJsonObject(convertedString), accIDJson);
	}

	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountNum).setStatus(statusVal);
//		updateCustomerAccounts(accounts);

		JSONObject accIDJson = readLastAccountID();

		Map<Long, Map<Long, AccountData>> accountDetailsMap = getAllCustomerAccounts();

		accountDetailsMap.put(customerID, accounts);

		String convertedString = MapperUtility.accountMapToJson(accountDetailsMap);
		writeAccountDataToFile(MapperUtility.parseStringToJsonObject(convertedString), accIDJson);

		return statusVal;
	}

	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException {

//		CheckerUtil.validateNumbeNegative(amount);

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);
		double balance = getAccountBalanceByAccountNum(customerID, accountNum);
		balance += amount;
		updateBalance(customerID, accountNum, balance);

		return balance;
	}

	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException {

		checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		checkWithdrawPossible(customerID, accountNum);

		double balance = getAccountBalanceByAccountNum(customerID, accountNum);

		CheckerUtil.checkLowBalance(balance, amount);

		balance -= amount;
		updateBalance(customerID, accountNum, balance);

		return balance;
	}

}
