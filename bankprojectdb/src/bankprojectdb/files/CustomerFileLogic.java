package bankprojectdb.files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import bankprojectdb.account.AccountData;
import bankprojectdb.branches.BankBranchDetails;
import bankprojectdb.customer.CustomerData;
import bankprojectdb.utilities.CheckerUtil;
import bankprojectdb.utilities.MapperUtility;
import userexception.CustomException;
import validatorutil.Validator;

public class CustomerFileLogic {

	// variables to store customer related datas

	private static String customerFileName = "customerdata.txt";

	// file object for validation

	File customerFileObj = new File(customerFileName);
	

	// Method for initialize
	public void initializeCustomerFiles()throws CustomException 
	{
		FilesIO.createNewFile(customerFileObj);
	}
	// Validating Utility methods

	public void checkCustomerExists(long customerID) throws CustomException {
		Map<Long, CustomerData> customerDetailsMap = getAllCustomers();

		if (!customerDetailsMap.containsKey(customerID)) {
			throw new CustomException("Customer Doesn't Exist");
		}
	}

	// Customer access methods

	private void writeCustomerDataToFile(JSONObject customerDetails, JSONObject custIDJson) throws CustomException {

//			convertCustomerMapToJSON();
		FilesIO.writeJsonToFile("#CustomerData#", "#CustomerID#", customerDetails, custIDJson, customerFileName);
	}

	private JSONObject readCustomerDataJsonFromFile() throws CustomException {

		JSONObject customerDetails = new JSONObject();
		if (!FilesIO.validateFileExists(customerFileObj)) {
			return customerDetails;
		}
		customerDetails = FilesIO.readJsonFromFile(customerFileName, "#CustomerData#");

		return customerDetails;
	}

	private Map<Long, CustomerData> convertJSONToCustomerMap() throws CustomException {

		JSONObject customers = readCustomerDataJsonFromFile();

		Map<Long, CustomerData> customerMap = new HashMap<Long, CustomerData>();
		for (Object keys : customers.keySet()) {
			customerMap.put(Long.parseLong(String.valueOf(keys)),
					MapperUtility.fromJsonToCustomerData(String.valueOf(customers.get(keys))));
		}

		return customerMap;

	}

	private JSONObject readLastCustomerID() throws CustomException {
		JSONObject custIDJson = FilesIO.readJsonFromFile(customerFileName, "#CustomerID#");
		return custIDJson;
	}

	public Map<Long, CustomerData> getAllCustomers() throws CustomException {

		readCustomerDataJsonFromFile();

		Map<Long, CustomerData> custDetails = convertJSONToCustomerMap();

		return custDetails;

	}

	public long addNewCustomer(CustomerData customer) throws CustomException {

		readCustomerDataJsonFromFile();

		Validator.validateObject(customer);

		JSONObject custIDJson = readLastCustomerID();

		long genID = Long.parseLong((String) custIDJson.get("CustomerID"));
		genID++;

		customer.setId(genID);
		addCustomer(customer);

		return genID;
	}

	private void addCustomer(CustomerData customer) throws CustomException {

		Validator.validateObject(customer);

//			JSONObject customerDetails = readCustomerDataJsonFromFile();

		Map<Long, CustomerData> customersMap = getAllCustomers();

		customersMap.put(customer.getId(), customer);
		JSONObject custIDJson = new JSONObject();
		custIDJson.put("CustomerID", String.valueOf(customer.getId()));

		String convertedString = MapperUtility.customerMapToJson(customersMap);

		writeCustomerDataToFile(MapperUtility.parseStringToJsonObject(convertedString), custIDJson);
	}

	public CustomerData getCustomerDetailsByID(long customerID) throws CustomException {
		Map<Long, CustomerData> custDetails = getAllCustomers();

		return custDetails.get(customerID);

	}

	public boolean switchCustomerStatus(long customerID, boolean statusVal) throws CustomException {

		checkCustomerExists(customerID);

		Map<Long, CustomerData> customerMap = getAllCustomers();

		customerMap.get(customerID).setStatus(statusVal);
//			updateCustomerAccounts(accounts);

		JSONObject custIDJson = readLastCustomerID();

		String convertedString = MapperUtility.customerMapToJson(customerMap);

		writeCustomerDataToFile(MapperUtility.parseStringToJsonObject(convertedString), custIDJson);

		return statusVal;
	}

}
