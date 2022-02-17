package bankprojectjson.gateways;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import bankprojectjson.account.AccountData;
import bankprojectjson.branches.BankBranchDetails;
import bankprojectjson.files.FilesIO;
import bankprojectjson.utilities.CheckerUtil;
import bankprojectjson.utilities.MapperUtility;
import userexception.CustomException;
import validatorutil.Validator;

public class AccountGateway {

	public static JSONObject customerAccounts = new JSONObject();
	public static JSONObject accIDJson = new JSONObject();
	
	private static long generateAccID = 100000;
	private static String fileName = "accountdata.txt";

	BankBranchDetails branchesDetails = new BankBranchDetails();

	private void checkCustomerAccount(long customerID, long accountNum) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);

		JSONObject accounts = getJSONCustomerAccountByID(customerID);
		if (!accounts.containsKey(String.valueOf(accountNum))) {
			throw new CustomException("Account Number doesn't match for the customer!");
		}
	}

	private void checkWithdrawPossible(long customerID, long accountNum) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData accounts = getCustomerAccountByAccountNum(customerID, accountNum);

		if (accounts.getAccType().equals("Deposit")) {
			throw new CustomException("Withdraw cannot be possible in deposit account!");
		}

	}

	private long incrementAccountID() {
		return ++generateAccID;
	}

	private void setIDStart() {

		generateAccID = Long.parseLong((String) accIDJson.get("AccountID"));
	}

	public void writeToFile() throws CustomException {
		FilesIO.writeJsonToFile("#AccountData#", "#AccountID#", customerAccounts, accIDJson, fileName);

	}

	public void readJsonFromFile() throws CustomException {
		customerAccounts = FilesIO.readJsonFromFile(fileName, "#AccountData#");
		readLastID();
		setIDStart();
	}

	public void readLastID() throws CustomException {
		accIDJson = FilesIO.readJsonFromFile(fileName, "#AccountID#");
	}

	public JSONObject getCustomerAccount(int customerId) throws CustomException {

		String id = String.valueOf(customerId);

		if (customerAccounts != null) {
			return (JSONObject) customerAccounts.get(id);
		} else {
			customerAccounts = new JSONObject();
			return (JSONObject) customerAccounts.get(id);
		}

	}

	private JSONObject fetchCustomerJSON(AccountData account) throws CustomException {
		Validator.validateObject(account);

		String custID = String.valueOf(account.getCustID());

		JSONObject accountDet = (JSONObject) customerAccounts.get(custID);
		return accountDet;

	}

	public void addNewAccount(long customerID, AccountData account) throws CustomException {

		Validator.validateObject(account);

		CheckerUtil.checkCustomerExists(customerID);

		Long accNum = incrementAccountID();
		String iFSC = branchesDetails.fetchIFSCCode(account.getLocation());
		account.setAccID(accNum);

		account.setCustID(customerID);
		account.setIfscCode(iFSC);
		addAccount(account);

	}

	private void addAccount(AccountData account) throws CustomException {

		Validator.validateObject(account);

		long custID = account.getCustID();
		CheckerUtil.checkCustomerExists(custID);
		accIDJson.put("AccountID", String.valueOf(account.getAccID()));
		addCustomerAccounts(account);
	}

	private void addCustomerAccounts(AccountData account) throws CustomException {
		Validator.validateObject(account);

		JSONObject accountDet = fetchCustomerJSON(account);

		if (accountDet == null) {
			accountDet = new JSONObject();
			String custID = String.valueOf(account.getCustID());

			customerAccounts.put(custID, accountDet);
		}

		String accountID = String.valueOf(account.getAccID());
		String data = MapperUtility.toJson(account);

		accountDet.put(accountID, data);

		writeToFile();
	}

	private void updateCustomerAccounts(AccountData account) throws CustomException {
		Validator.validateObject(account);

		JSONObject accountDet = fetchCustomerJSON(account);

		String accountID = String.valueOf(account.getAccID());
		String data = MapperUtility.toJson(account);

		accountDet.put(accountID, data);

		writeToFile();
	}

	public List<AccountData> getAllCustomerAccounts() throws CustomException {

		JSONObject allCustomerAccounts = customerAccounts;

		List<AccountData> accountList = new ArrayList<AccountData>();

		for (Object keys : allCustomerAccounts.keySet()) {

			JSONObject subJson = (JSONObject) allCustomerAccounts.get(keys);
			accountList.addAll(getListOfAccounts(subJson));
//			for (Object subKeys : subJson.keySet()) {
//				
//				accountList.add(MapperUtility.fromJsonToAccountData(String.valueOf(subJson.get(subKeys))));
//			}
		}

		return accountList;
	}

	private List<AccountData> getListOfAccounts(JSONObject inpObj) throws CustomException {
		List<AccountData> accountList = new ArrayList<AccountData>();

		for (Object key : inpObj.keySet()) {
			accountList.add(MapperUtility.fromJsonToAccountData(String.valueOf(inpObj.get(key))));

		}
		return accountList;
	}

	private JSONObject getJSONCustomerAccountByID(long customerID) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);

		String custID = String.valueOf(customerID);
		return (JSONObject) customerAccounts.get(custID);
	}

	public List<AccountData> getCustomerAccountByID(long customerID) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);

		String id = String.valueOf(customerID);

		JSONObject resultJson = (JSONObject) customerAccounts.get(id);
		List<AccountData> accountList = getListOfAccounts(resultJson);

		return accountList;
	}

	private List<AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);

		JSONObject customerAcc = getJSONCustomerAccountByID(customerID);

		List<AccountData> resultList = new ArrayList<AccountData>();

		for (Object key : customerAcc.keySet()) {
//			String returnData = (String) accounts.get(String.valueOf(accountNum));

			AccountData accData = MapperUtility.fromJsonToAccountData(String.valueOf(customerAcc.get(key)));
			boolean status = accData.getStatus();
			if (status == boolValue) {
				resultList.add(accData);
			}
		}
		return resultList;
	}

	public List<AccountData> getActiveCustomerAccounts(long customerID) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);

		List<AccountData> resultList = getAccountsByStatus(customerID, true);

		return resultList;

	}

	public List<AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);

		List<AccountData> resultList = getAccountsByStatus(customerID, false);

		return resultList;

	}

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		JSONObject accounts = getJSONCustomerAccountByID(customerID);
		String returnData = (String) accounts.get(String.valueOf(accountNum));

		AccountData accData = MapperUtility.fromJsonToAccountData(returnData);
//		return (AccountData) accounts.get(String.valueOf(accountNum));
		return accData;
	}

	public long getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData object1 = getCustomerAccountByAccountNum(customerID, accountNum);
		long balance = object1.getBalance();
		return balance;
	}

	private void updateBalance(long customerID, long accountNum, long newBal) throws CustomException {

		CheckerUtil.validateNumbeNegative(newBal);

		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData accounts = getCustomerAccountByAccountNum(customerID, accountNum);
		accounts.setBalance(newBal);
		updateCustomerAccounts(accounts);
		writeToFile();
	}

	public void switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData accounts = getCustomerAccountByAccountNum(customerID, accountNum);
		accounts.setStatus(statusVal);

		updateCustomerAccounts(accounts);
		writeToFile();
	}

	public void depositAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);
		long balance = getAccountBalanceByAccountNum(customerID, accountNum);
		balance += amount;
		updateBalance(customerID, accountNum, balance);
	}

	public void withdrawAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		checkWithdrawPossible(customerID, accountNum);

		long balance = getAccountBalanceByAccountNum(customerID, accountNum);

		CheckerUtil.checkLowBalance(balance, amount);

		balance -= amount;
		updateBalance(customerID, accountNum, balance);
	}

	/*
	 * public static void main(String[] args) { try { AccountGateway gate2 = new
	 * AccountGateway(); gate2.readJsonFromFile(); BankBranchDetails branch = new
	 * BankBranchDetails(); CustomerGateway gate1 = new CustomerGateway();
	 * gate1.readJsonFromFile();
	 * 
	 * AccountData accData = new AccountData(); accData.setCustID(1002);
	 * accData.setLocation("Salem"); accData.setBalance(2000);
	 * accData.setStatus(true); accData.setIfscCode(branch.fetchIFSCCode("Salem"));
	 * accData.setAccType("Savings");
	 * 
	 * gate2.addNewAccount(1001, accData);
	 * 
	 * gate2.getAllCustomerAccounts();
	 * System.out.println("Customer Account of 1001:" +
	 * gate2.getCustomerAccountByID(1001L)); // gate2.withdrawAmount(1001L, 100002L,
	 * 1100L); System.out.println("Balance:" +
	 * gate2.getAccountBalanceByAccountNum(1001L, 100002L)); } catch
	 * (CustomException ex) { ex.printStackTrace(); } }
	 */
}
