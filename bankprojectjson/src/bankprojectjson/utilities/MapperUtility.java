package bankprojectjson.utilities;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import bankprojectjson.account.AccountData;
import bankprojectjson.customer.CustomerData;
import userexception.CustomException;

public class MapperUtility {

	static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(CustomerData customerObj) throws CustomException {
		try {

			String jsonStr = mapper.writeValueAsString(customerObj);

			return jsonStr;

		}

		catch (IOException ex) {

			throw new CustomException(ex);
		}
	}

	public static String toJson(AccountData accountObj) throws CustomException {
		try {

			String jsonStr = mapper.writeValueAsString(accountObj);

			return jsonStr;

		}

		catch (IOException ex) {

			throw new CustomException(ex);
		}
	}

	public static CustomerData fromJsonToCustomerData(String inputJson) throws CustomException {

		try {
			return mapper.readValue(inputJson, CustomerData.class);

		} catch (IOException ex) {
			throw new CustomException(ex);
		}
	}

	public static AccountData fromJsonToAccountData(String inputJson) throws CustomException {

		try {
			return mapper.readValue(inputJson, AccountData.class);

		} catch (IOException ex) {
			throw new CustomException(ex);
		}
	}
}
