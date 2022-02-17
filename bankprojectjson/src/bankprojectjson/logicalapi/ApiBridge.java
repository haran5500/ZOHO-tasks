package bankprojectjson.logicalapi;

import java.util.List;

import bankprojectjson.account.AccountData;
import bankprojectjson.branches.BankBranchDetails;
import bankprojectjson.customer.CustomerData;
import bankprojectjson.gateways.AccountGateway;
import bankprojectjson.gateways.CustomerGateway;
import bankprojectjson.utilities.CheckerUtil;
import userexception.CustomException;
import validatorutil.Validator;

public class ApiBridge {

	static CustomerGateway customerObj = new CustomerGateway();
	static AccountGateway accGate = new AccountGateway();

	// Method to load defaults
	public void loadDefault() throws CustomException 
	{
		loadDefaultCustomers();
		loadDefaultAccounts();
	}
	// Customer Gateway access methods

	private void loadDefaultCustomers() throws CustomException {
		customerObj.readJsonFromFile();
	}

	public void addNewCustomer(CustomerData newCustomer) throws CustomException {
		Validator.validateObject(newCustomer);
		customerObj.addNewCustomer(newCustomer);
	}

	public List<CustomerData> getAllCustomerDetails() throws CustomException {
		return customerObj.getAllCustomers();

	}

	public CustomerData getCustomerDetailsByID(long customerID) throws CustomException {

		return customerObj.getCustomerDetailsByID(customerID);
	}

	// Account Gateway access methods

	private void loadDefaultAccounts() throws CustomException {
		accGate.readJsonFromFile();
	}

	public void addNewAccount(long customerID, AccountData newAccount) throws CustomException {
		Validator.validateObject(newAccount);

		accGate.addNewAccount(customerID, newAccount);
	}

	public List<AccountData> getAllAccounts() throws CustomException {
		return accGate.getAllCustomerAccounts();
	}

	public List<AccountData> getAccountByCustomerID(long customerID) throws CustomException {

		return accGate.getCustomerAccountByID(customerID);
	}

	public AccountData getAccountByAccountNum(long customerID, long AccountNumber) throws CustomException {

		return accGate.getCustomerAccountByAccountNum(customerID, AccountNumber);
	}

	public List<AccountData> getActiveAccounts(long customerID) throws CustomException {

		return accGate.getActiveCustomerAccounts(customerID);
	}

	public List<AccountData> getInActiveAccounts(long customerID) throws CustomException {

		return accGate.getInActiveCustomerAccounts(customerID);
	}

	public long getBalance(long customerID, long AccountNum) throws CustomException {

		return accGate.getAccountBalanceByAccountNum(customerID, AccountNum);
	}

	public void withdrawAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		accGate.withdrawAmount(customerID, accountNum, amount);
	}

	public void depositAmount(long customerID, long accountNum, long amount) throws CustomException {
		CheckerUtil.validateNumbeNegative(amount);

		accGate.depositAmount(customerID, accountNum, amount);
	}

	public void setAccountStatus(long customerID, long accountNum, boolean statusValue) throws CustomException {
		accGate.switchAccountStatus(customerID, accountNum, statusValue);
	}

	// Branch access methods
	public String[] loadBranches() {
		BankBranchDetails branch = new BankBranchDetails();
		return branch.getBranches();
	}
}
