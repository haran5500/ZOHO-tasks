package bankproject.gateway;

import java.util.HashMap;
import java.util.Map;
import bankproject.account.AccountData;
import bankproject.branches.BankBranchDetails;
import bankproject.utilities.CheckerUtil;
import userexception.CustomException;
import validatorutil.Validator;

public class AccountGateway {

	private static long generateAccID = 100000;

	static CheckerUtil utility = new CheckerUtil();
//	Map<Long,AccountData> accountInfo=new HashMap<Long,AccountData>();

	public static Map<Long, Map<Long, AccountData>> customerAccounts = new HashMap<Long, Map<Long, AccountData>>();
	BankBranchDetails branchesDetails = new BankBranchDetails();

	public void checkCustomerAccount(long customerID, long accountNum) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		if (!accounts.containsKey(accountNum)) {
			throw new CustomException("Account Number doesn't match for the customer!");
		}
	}

	public void checkWithdrawPossible(long customerID, long accountNum) throws CustomException {

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

	public void autoStoreAccounts() throws CustomException {
		AccountData account = null;

		long[] custID = new long[] { 1008, 1001, 1002, 1001, 1007, 1006, 1001, 1008, 1010, 1003, 1005, 1004, 1009 };
		String[] accType = { "Savings", "Current", "Deposit", "Deposit", "Savings", "Savings", "Savings", "Current",
				"Deposit", "Savings", "Savings", "Savings", "Current" };

		String[] locations = { "Madurai", "Chennai", "Chennai", "Madurai", "Madurai", "Karaikudi", "Salem", "Trichy",
				"Chennai", "Coimbatore", "Karaikudi", "Trichy", "Karaikudi" };
		long[] balances = new long[] { 12500, 222535, 105230, 78002, 26530, 112508, 22300, 45065, 88500, 12365, 4250,
				1450, 342650 };

		for (int i = 0; i < custID.length; i++) {
			long accountID = incrementAccountID();
			account = new AccountData();
			account.setAccID(accountID);

			long customerID = custID[i];

			CheckerUtil.checkCustomerExists(customerID);

			account.setCustID(customerID);
			account.setAccType(accType[i]);

			String branchName = locations[i];
			account.setLocation(locations[i]);
			account.setBalance(balances[i]);

			String ifsc = branchesDetails.fetchIFSCCode(branchName);
			account.setIfscCode(ifsc);
			addAccount(account);
		}
	}

	/*
	 * public Map<Long,AccountData> getAccountDetails() { Map<Long,AccountData>
	 * accountDetails=accountInfo;
	 * 
	 * return accountDetails; }
	 */

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

	public void addAccount(AccountData account) throws CustomException {
		Validator.validateObject(account);

		long custID = account.getCustID();
		CheckerUtil.checkCustomerExists(custID);

		addCustomerAccounts(account);
	}

	public void addCustomerAccounts(AccountData account) throws CustomException {
		Validator.validateObject(account);

		long custID = account.getCustID();

		Map<Long, AccountData> accountDet = customerAccounts.get(custID);

		if (accountDet == null) {
			accountDet = new HashMap<Long, AccountData>();
			customerAccounts.put(custID, accountDet);
		}

		accountDet.put(account.getAccID(), account);
	}

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() {

		Map<Long, Map<Long, AccountData>> allCustomerAccounts = customerAccounts;

		return allCustomerAccounts;
	}

	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);

		return customerAccounts.get(customerID);
	}

	public Map<Long, AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);

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

		CheckerUtil.checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, true);

		return resultMap;

	}

	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException {

		CheckerUtil.checkCustomerID(customerID);

		Map<Long, AccountData> resultMap = getAccountsByStatus(customerID, false);

		return resultMap;

	}

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		return accounts.get(accountNum);
	}

	public long getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		AccountData object1 = getCustomerAccountByAccountNum(customerID, accountNum);

		long balance = object1.getBalance();
		return balance;
	}

	public void updateBalance(long customerID, long accountNum, long newBal) throws CustomException {

		CheckerUtil.validateNumbeNegative(newBal);

		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountNum).setBalance(newBal);
	}

	public void switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException {
		CheckerUtil.checkCustomerID(customerID);
		checkCustomerAccount(customerID, accountNum);

		Map<Long, AccountData> accounts = getCustomerAccountByID(customerID);
		accounts.get(accountNum).setStatus(statusVal);
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
	 * public Map<Long,Long> getBalancesofAllAccounts(int custID) {
	 * Map<Long,AccountData> customerAcc=getCustomerAccountByID(custID);
	 * 
	 * Map<Long,Long> balanceMap=new HashMap<Long,Long>();
	 * 
	 * for(long key:customerAcc.keySet()) { balanceMap.put(key,
	 * customerAcc.get(key).getBalance()); } return balanceMap; }
	 */

}
