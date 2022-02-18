
package bankprojectdb.utilities;

import bankprojectdb.filelayer.FileConnector;
//import bankprojectjson.gateways.AccountGateway;
//import bankprojectjson.gateways.CustomerGateway;
import userexception.CustomException;

public class CheckerUtil {

	

	public static void checkLowBalance(long balance, long wdlAmt) throws CustomException {
		if (balance < wdlAmt) {
			throw new CustomException("Low Balance in the Account!");
		}
	}

	public static void checkLowBalance(double balance, double wdlAmt) throws CustomException {
		if (balance < wdlAmt) {
			throw new CustomException("Low Balance in the Account!");
		}
	}
	
	public static void validateNumbeNegative(long inpLong) throws CustomException {
		if (inpLong < 0) {
			throw new CustomException("Number cannot be negative!");
		}
	}
}
