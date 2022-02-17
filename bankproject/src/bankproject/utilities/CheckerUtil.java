package bankproject.utilities;

import bankproject.gateway.AccountGateway;
import bankproject.gateway.CustomerGateway;
import userexception.CustomException;

public class CheckerUtil {
	static CustomerGateway customerObj=new CustomerGateway();
	public static void checkCustomerExists(long customerID)throws CustomException
	{
		if(!CustomerGateway.customerInfo.containsKey(customerID))
		{
			throw new CustomException("Customer Doesn't Exist");
		}
	}
	
	public static void checkLowBalance(long balance, long wdlAmt) throws CustomException {
		if (balance < wdlAmt) {
			throw new CustomException("Low Balance in the Account!");
		}
	}
	
	public static void checkCustomerID(long customerID) throws CustomException {
		if (!AccountGateway.customerAccounts.containsKey(customerID)) {
			throw new CustomException("CustomerID doesn't have any accounts!");
		}
	}
	
	public static void validateNumbeNegative(long inpLong)throws CustomException
	{
		if(inpLong<0)
		{
			throw new CustomException("Number cannot be negative!");
		}
	}
}
