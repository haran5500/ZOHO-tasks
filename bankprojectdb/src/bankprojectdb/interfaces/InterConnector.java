package bankprojectdb.interfaces;

import java.util.Map;

import bankprojectdb.customer.CustomerData;
import bankprojectdb.account.AccountData;
import userexception.CustomException;

public interface InterConnector {

	// Methods to initiate some operations
	public void initialize() throws CustomException;

	// Methods for Customer Data access
	public Map<Long, CustomerData> getAllCustomers() throws CustomException;

	public long addNewCustomer(CustomerData customer) throws CustomException;

	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException;

	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException;

	// Methods for Account Data access

	public long addNewAccount(long customerID, AccountData account) throws CustomException;

	public Map<Long, Map<Long, AccountData>> getAllCustomerAccounts() throws CustomException;

	public Map<Long, AccountData> getCustomerAccountByID(long customerID) throws CustomException;

//	public Map<Long, AccountData> getAccountsByStatus(long customerID, boolean boolValue) throws CustomException;

	public Map<Long, AccountData> getActiveCustomerAccounts(long customerID) throws CustomException;

	public Map<Long, AccountData> getInActiveCustomerAccounts(long customerID) throws CustomException;

	public AccountData getCustomerAccountByAccountNum(long customerID, long accountNum) throws CustomException;

	public double getAccountBalanceByAccountNum(long customerID, long accountNum) throws CustomException;

	public boolean switchAccountStatus(long customerID, long accountNum, boolean statusVal) throws CustomException;

	public double depositAmount(long customerID, long accountNum, double amount) throws CustomException;

	public double withdrawAmount(long customerID, long accountNum, double amount) throws CustomException;

}
