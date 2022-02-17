package bankproject.logical;

import java.util.Map;

import bankproject.account.AccountData;
import bankproject.branches.BankBranchDetails;
import bankproject.customer.CustomerData;
import bankproject.gateway.AccountGateway;
import bankproject.gateway.CustomerGateway;
import bankproject.utilities.CheckerUtil;
import userexception.CustomException;
import validatorutil.Validator;

public class BankLogic {

	static CustomerGateway customerObj = new CustomerGateway();
	static AccountGateway accGate = new AccountGateway();

	// Customer Gateway access methods

	public void loadDefaultCustomers() throws CustomException {
		customerObj.autoStoreCustomer();
	}

	public void addNewCustomer(CustomerData newCustomer) throws CustomException {
		Validator.validateObject(newCustomer);
		customerObj.addNewCustomer(newCustomer);
	}

	public Map<Long, CustomerData> getAllCustomerDetails() {
		return customerObj.getAllCustomerDetails();
	}

	public CustomerData getCustomerDetailsByID(long customerID){

		return customerObj.getCustomerByID(customerID);
	}

	// Account Gateway access methods

	public void loadDefaultAccounts() throws CustomException {
		accGate.autoStoreAccounts();
	}

	public void addNewAccount(long customerID, AccountData newAccount) throws CustomException {
		Validator.validateObject(newAccount);

		accGate.addNewAccount(customerID, newAccount);
	}

	public Map<Long, Map<Long, AccountData>> getAllAccounts() {
		return accGate.getAllCustomerAccounts();
	}

	public Map<Long, AccountData> getAccountByCustomerID(long customerID) throws CustomException {

		return accGate.getCustomerAccountByID(customerID);
	}

	public AccountData getAccountByAccountNum(long customerID, long AccountNumber) throws CustomException {

		return accGate.getCustomerAccountByAccountNum(customerID, AccountNumber);
	}

	public Map<Long, AccountData> getActiveAccounts(long customerID) throws CustomException {

		return accGate.getActiveCustomerAccounts(customerID);
	}

	public Map<Long, AccountData> getInActiveAccounts(long customerID) throws CustomException {

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
	public String[] loadBranches()
	{
		BankBranchDetails branch=new BankBranchDetails();
		return branch.getBranches();
	}
	
	// for testing purpose....

	public static void main(String[] args) {
		try {
			// CustomerGateway customerObj1=new CustomerGateway();
			BankLogic logics = new BankLogic();
			logics.loadDefaultCustomers();

//			AccountGateway accGate = new AccountGateway();
			logics.loadDefaultAccounts();

			CustomerData customer = new CustomerData();
			customer.setName("James");
			customer.setCity("Pollachi");
			customer.setGender("Male");

			logics.addNewCustomer(customer);

			long custID = customer.getId();
			AccountData account = new AccountData();
			account.setLocation("Pollachi");
			account.setBalance(12500);
			account.setAccType("Savings");

			accGate.addNewAccount(custID, account);

			// System.out.println("Current Customer ID:"+custID);
			System.out.println("Customers:" + logics.getAllCustomerDetails());
			System.out.println("Customers and Accounts:" + logics.getAllAccounts());
			System.out.println("Customer Account of CustomerID 1008 :----> " + accGate.getActiveCustomerAccounts(1008));

			accGate.switchAccountStatus(1008, 100001, false);
			System.out.println("Customer Account of CustomerID 1008 :----> " + logics.getActiveAccounts(1008));

			System.out.println(
					"Customer Inactivated Account of CustomerID 1008 :----> " + logics.getInActiveAccounts(1008));

			/*
			 * logics.depositAmount(1008, 100001, 1000);
			 * System.out.println("After deposit 1000 to cID 1008 and AcNUM 100001:"+accGate
			 * .getAccountBalanceByAccountNum(1008, 100001)); logics.withdrawAmount(1008,
			 * 100001, 6000);
			 * System.out.println("After withdraw 6000 to cID 1008 and AcNUM 100001:"+logics
			 * .getBalance(1008, 100001));
			 * //System.out.println("Customers:"+customerObj.getCustomerDetails());
			 */

		} catch (CustomException ex) {
			ex.printStackTrace();
		}
	}
}
