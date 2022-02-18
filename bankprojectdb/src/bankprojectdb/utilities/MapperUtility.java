package bankprojectdb.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import bankprojectdb.account.AccountData;
import bankprojectdb.customer.CustomerData;
import userexception.CustomException;

public class MapperUtility {

	static ObjectMapper mapper = new ObjectMapper();

	public static String customerDataToJson(CustomerData customerObj) throws CustomException {
		try {

			String jsonStr = mapper.writeValueAsString(customerObj);
			return jsonStr;

		}

		catch (IOException ex) {

			throw new CustomException(ex);
		}
	}

	public static String accountDataToJson(AccountData accountObj) throws CustomException {
		try {

			String jsonStr = mapper.writeValueAsString(accountObj);

			return jsonStr;

		}

		catch (IOException ex) {

			throw new CustomException(ex);
		}
	}

	public static String customerMapToJson(Map<Long, CustomerData> inpMap) throws CustomException {
		try {
			String jsonStr = mapper.writeValueAsString(inpMap);
			return jsonStr;
		} catch (IOException ex) {
			throw new CustomException(ex);
		}
	}

	public static JSONObject parseStringToJsonObject(String inpString) throws CustomException {
		try {
			JSONParser parser = new JSONParser();
			JSONObject returnObj = (JSONObject) parser.parse(inpString);
			return returnObj;
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	public static String accountMapToJson(Map<Long, Map<Long, AccountData>> inpMap) throws CustomException {
		try {
			String jsonStr = mapper.writeValueAsString(inpMap);
			return jsonStr;
		} catch (IOException ex) {
			throw new CustomException(ex);
		}
	}

	public static Map<Long, CustomerData> JsonToCustomerMap(String inpObj) throws CustomException {
		try {
			Map<Long, CustomerData> resultMap = mapper.readValue(inpObj, HashMap.class);

			return resultMap;
		} catch (IOException ex) {

			throw new CustomException(ex);
		}

	}

	public static Map<Long, Map<Long, AccountData>> JsonToAccountMap(String inpObj) throws CustomException {
		try {
			Map<Long, Map<Long, AccountData>> resultMap = mapper.readValue(inpObj, HashMap.class);

			return resultMap;
		} catch (IOException ex) {

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
