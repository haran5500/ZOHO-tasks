package bankproject.gateway;

import bankproject.customer.CustomerData;
import userexception.CustomException;

import java.util.Map;
import java.util.HashMap;
import validatorutil.Validator;

public class CustomerGateway {

	private static long generateCustID=1000;

	public static Map<Long,CustomerData> customerInfo=new HashMap<Long,CustomerData>();
	
/*	private int incrementCustID()
	{
		return ++id;
	}*/
	
	private long incrementCustomerID()
	{
		return ++generateCustID;
	}
	
	public void autoStoreCustomer()throws CustomException
	{
		CustomerData customer=null;
		String[] names= {"Arvind","Vikram","John","Rohit","William","Jack","Ajay","Gloria","Daisy","Anandhi"};
		String[] cities= {"Madurai","Chennai","Chennai","Madurai","Madurai","Karaikudi","Salem","Trichy","Chennai","Coimbatore"};
		String[] gender= {"Male","Male","Male","Male","Male","Male","Male","Female","Female","Female"};
		long[] mobileNos= {9845213657L,8854123687L,7352149862L,8852143602L,7658412365L,6958412374L,7740215036L,9942157536L,6385204102L,8855224103L};
		for(int i=0;i<names.length;i++)
		{
			long passID=incrementCustomerID();
			customer=new CustomerData();
			customer.setId(passID);
			customer.setName(names[i]);
			customer.setCity(cities[i]);
			customer.setGender(gender[i]);
			customer.setMobileNo(mobileNos[i]);
			addCustomer(customer);
			
		}
	}
	
	public Map<Long,CustomerData> getAllCustomerDetails()
	{
		Map<Long,CustomerData> custDetails=customerInfo;
		
		return custDetails;
	}
	
	public void addNewCustomer(CustomerData customer)throws CustomException
	{
		Validator.validateObject(customer);
		
		long genID=incrementCustomerID();
		customer.setId(genID);
		
		addCustomer(customer);
	}
	
	public void addCustomer(CustomerData customer)throws CustomException
	{
		Validator.validateObject(customer);
		
		long id=customer.getId();

	/*	String name=customer.getName();
		String gender=customer.getGender();
		String city=customer.getCity();
//		long mobile=customer.getMobileNo();*/
		customerInfo.put(id, customer);
	}
	
	public CustomerData getCustomerByID(long customerID)
	{
		return customerInfo.get(customerID);
		
	}
	
/*	public List<CustomerData> getCustomerByCity(String city)
	{
		List<CustomerData> customerData=new ArrayList<CustomerData>();
		
		if(customerInfo.containsValue(city))
		{
			customerData.add(customerInfo.values());
		}
		return customerData;
	}*/
	
	//for testing purpose.....
	public static void main(String[] args)
	{
		try
		{
		CustomerGateway custGate=new CustomerGateway();
		custGate.autoStoreCustomer();
		
		CustomerData customer=new CustomerData();
		customer.setName("James");
		customer.setCity("Pollachi");
		customer.setGender("Male");
		
		custGate.addNewCustomer(customer);
		
		System.out.println("Customer details:\n"+custGate.getAllCustomerDetails());
		
		System.out.println("Details of Customer of id:1007 :----> "+custGate.getCustomerByID(1007));
		}
		catch(CustomException ex)
		{
			ex.printStackTrace();
		}
	}
}
