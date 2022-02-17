package bankprojectjson.gateways;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import bankprojectjson.customer.CustomerData;
import bankprojectjson.files.FilesIO;
import bankprojectjson.utilities.MapperUtility;
import userexception.CustomException;
import validatorutil.Validator;

public class CustomerGateway {
	public static JSONObject customerDetails = new JSONObject();

	private static long generateCustID = 1000;
	private static String fileName = "customerdata.txt";

	private static JSONObject custIDJson = new JSONObject();

	private long incrementCustomerID() {
		return ++generateCustID;
	}

	private void setIDStart() {
		generateCustID = Integer.parseInt((String) custIDJson.get("CustomerID"));
	}

	public void writeToFile() throws CustomException {
		FilesIO.writeJsonToFile("#CustomerData#", "#CustomerID#", customerDetails, custIDJson, fileName);

	}

	public void readJsonFromFile() throws CustomException {
		customerDetails = FilesIO.readJsonFromFile(fileName, "#CustomerData#");
		readLastID();
		setIDStart();
	}

	private void readLastID() throws CustomException {
		custIDJson = FilesIO.readJsonFromFile(fileName, "#CustomerID#");
	}

	private JSONObject getFromFile(String fileName, String input) throws CustomException {
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(fileName)) {
			JSONObject returnJSON = (JSONObject) parser.parse(reader);
			return returnJSON;
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	public List<CustomerData> getAllCustomers() throws CustomException {
		JSONObject customers = customerDetails;

		List<CustomerData> customerList = new ArrayList<CustomerData>();
		for (Object keys : customers.keySet()) {
			customerList.add(MapperUtility.fromJsonToCustomerData((String) customerDetails.get(keys)));
		}

		return customerList;
	}

	public void addNewCustomer(CustomerData customer) throws CustomException {
		Validator.validateObject(customer);

		long genID = incrementCustomerID();
		customer.setId(genID);

		addCustomer(customer);
	}

	private void addCustomer(CustomerData customer) throws CustomException {

		Validator.validateObject(customer);

		String customerID = String.valueOf(customer.getId());
		String data = MapperUtility.toJson(customer);

		customerDetails.put(customerID, data);
		custIDJson.put("CustomerID", String.valueOf(customer.getId()));
		writeToFile();
	}

	public CustomerData getCustomerDetailsByID(long customerId) throws CustomException {

		String id = String.valueOf(customerId);

		String resultJson = (String) customerDetails.get(id);

		if (resultJson != null) {

			return MapperUtility.fromJsonToCustomerData(resultJson);
		}
		throw new CustomException("Customer ID is not present");

	}


	/*
	 * public static void main(String[] args) { try { CustomerGateway gate1 = new
	 * CustomerGateway(); gate1.readJsonFromFile();
	 * 
	 * System.out.println(gate1.getAllCustomers()); CustomerData data = new
	 * CustomerData(); data.setGender("Male"); data.setCity("Trichy");
	 * data.setMobileNo(9940); data.setName("Jack");
	 * 
	 * gate1.addNewCustomer(data);
	 * 
	 * System.out.println(gate1.getAllCustomers());
	 * 
	 * } catch (CustomException ex) { ex.printStackTrace(); } }
	 */
}
