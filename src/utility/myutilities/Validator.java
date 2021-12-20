package myutilities;

public class Validator
{

//method to validate length of list the index
public static void validateLength(int length,int index) throws Exception
{
	if(length<index)
	{
		throw new Exception("Index is greater than the length of List!");
	}
}

//method to validate range
public static void validateRange(int start,int end)throws Exception
{
	if(end<start)
	{
		throw new Exception("Starting limit is greater than Ending limit!");
	}
}

//method to check a String is empty or null
 public void validateString(String inpString) throws Exception
 {
  	if(inpString==null || inpString.isEmpty())
  	{
		throw new Exception("\"Input String cannot be EMPTY or null!\"");
  	}
 }
 
 //method to get the length of the 
 public int getStringLength(String inpString) throws Exception
 {
	validateString(inpString);
	return inpString.length();
 }
 
 //method to check a number is negative or not
 public static void validateNumberNegative(int num) throws Exception
{
	if(num<0)
	{
		throw new Exception("Number cannot be Negative!");
	}
}

//method to validate Object Array is null or empty
public static void validateObject(Object[] objArray)throws Exception
{
	if(objArray==null || objArray.length<=0)
	{
		throw new Exception("Object cannot be NULL or EMPTY!");
	}
}

}
