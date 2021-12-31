package superclasses;
import inout.Reader;

public class Car {
	private int yearOfMake;
	
	private String engineNumber;
	private String vehicleType;
	
	public Car()
	{
		
	}
	
	public Car(String inpStr)
	{
		Reader.print(inpStr);
	}
	
	public void setYear(int year)
	{
		yearOfMake=year;
	}
	
	public int getYear()
	{
		return yearOfMake;
	}

	public void setEngineNumber(String engineNo)
	{
		engineNumber=engineNo;
	}
	
	public String getEngineNumber()
	{
		return engineNumber;
	}
	
	public void setVehicleType(String type)
	{
		vehicleType=type;
	}
	
	public String getVehicleType()
	{
		return vehicleType;
	}
	
	public void maintenance()
	{
		Reader.print("Car under maintenance!");
	}
}