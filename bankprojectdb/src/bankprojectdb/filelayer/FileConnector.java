package bankprojectdb.filelayer;

import java.util.Map;

import bankprojectdb.account.AccountData;
import bankprojectdb.customer.CustomerData;
import bankprojectdb.files.AccountFileLogic;
import bankprojectdb.files.CustomerFileLogic;
import bankprojectdb.interfaces.InterConnector;
import userexception.CustomException;

public class FileConnector implements InterConnector {

	CustomerFileLogic customerFiles = new CustomerFileLogic();
	AccountFileLogic accountFiles = new AccountFileLogic();

	// Method to do some operation at an initial stage
	@Override
	public void initialize() throws CustomException {

		customerFiles.initializeCustomerFiles();
		accountFiles.initializeAccountFiles();
	}

	// Methods for Customer Data access
	@Override
	public Map<Long, CustomerData> getAllCustomers() throws CustomException
	{
		return customerFiles.getAllCustomers();
	}

	@Override
	public long addNewCustomer(CustomerData customer) throws CustomException
	{
		return customerFiles.addNewCustomer(customer);
	}
	@Override
	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException
	{
		return customerFiles.getCustomerDetailsByID(customerId);
	}
	@Override
	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException
	{
		return customerFiles.switchCustomerStatus(customerID, statusVal);
	}

	// Methods for Account Data access
	@Override
	public long addNewAccount(long customerID, AccountData account) throws CustomException
	{
		return accountFiles.addNewAccount(customerID, account);
	}
	@Override
	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException
	{
		return accountFiles.getAllCustomerAccounts();
	}
	@Override
	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException
	{
		return accountFiles.getCustomerAccountByID(customerID);
	}
	
//		public Map<Long, AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException;
	@Override
	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException
	{
		return accountFiles.getActiveCustomerAccounts(customerID);
	}
	@Override
	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException
	{
		return accountFiles.getInActiveCustomerAccounts(customerID);
	}
	@Override
	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException
	{
		return accountFiles.getJSONCustomerAccountByAccountNum(customerID, accountNum);
	}
	@Override
	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException
	{
		return accountFiles.getAccountBalanceByAccountNum(customerID, accountNum);
	}
	@Override
	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException
	{
		return accountFiles.switchAccountStatus(customerID, accountNum, statusVal);
	}
	@Override
	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException
	{
		return accountFiles.depositAmount(customerID, accountNum, amount);
	}
	@Override
	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException
	{
		return accountFiles.withdrawAmount(customerID, accountNum, amount);
	}
	
	/*
	 * public static void main(String[] args) { try { FileConnector connect = new
	 * FileConnector();
	 * 
	 * 
	 * CustomerData customer = new CustomerData(); customer.setName("Rajesh");
	 * customer.setGender("Male"); customer.setMobileNo(8520123600L);
	 * customer.setCity("Trichy");
	 * 
	 * connect.addNewCustomer(customer);
	 * 
	 * 
	 * System.out.println("id 1003:" + connect.getCustomerDetailsByID(1003));
	 * 
	 * 
	 * AccountData accounts=new AccountData(); accounts.setCustID(1004);
	 * accounts.setLocation("Coimbatore"); accounts.setBalance(5000L);
	 * accounts.setAccType("Savings");
	 * 
	 * connect.addNewAccount(accounts.getCustID(), accounts);
	 * 
	 * 
	 * 
	 * System.out.println("All Customers:" + connect.getAllCustomers());
	 * 
	 * System.out.println("All Accounts:" + connect.getAllCustomerAccounts());
	 * 
	 * System.out.println("Accounts of 1002:" +
	 * connect.getCustomerAccountByID(1002));
	 * 
	 * // connect.withdrawAmount(1002, 100006, 500);
	 * System.out.println("Accounts of 1002:" +
	 * connect.getCustomerAccountByID(1002)); } catch (
	 * 
	 * CustomException ex) { ex.printStackTrace(); } }
	 */
}
