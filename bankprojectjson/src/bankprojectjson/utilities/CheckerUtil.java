
package bankprojectjson.utilities;

import bankprojectjson.gateways.AccountGateway;
import bankprojectjson.gateways.CustomerGateway;
import userexception.CustomException;

public class CheckerUtil {
	
	public static void checkCustomerExists(long customerID) throws CustomException {
		if (!CustomerGateway.customerDetails.containsKey(String.valueOf(customerID))) {
			throw new CustomException("Customer Doesn't Exist");
		}
	}

	public static void checkLowBalance(long balance, long wdlAmt) throws CustomException {
		if (balance < wdlAmt) {
			throw new CustomException("Low Balance in the Account!");
		}
	}

	public static void checkCustomerID(long customerID) throws CustomException {
		if (!AccountGateway.customerAccounts.containsKey(String.valueOf(customerID))) {
			throw new CustomException("CustomerID doesn't have any accounts!");
		}
	}

	public static void validateNumbeNegative(long inpLong) throws CustomException {
		if (inpLong < 0) {
			throw new CustomException("Number cannot be negative!");
		}
	}
}
