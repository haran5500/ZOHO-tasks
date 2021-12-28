package validatorutil;
import java.io.File;

import userexception.CustomException;

public class Validator
{

//method to validate length of list the index
public static void validateLength(int length,int index) throws CustomException
{
	if(length<index)
	{
		throw new CustomException("Index is greater than the length of List!");
	}
}

//method to validate range
public static void validateRange(int start,int end)throws CustomException
{
	if(end<start)
	{
		throw new CustomException("Starting limit is greater than Ending limit!");
	}
}

//method to check a String is empty or null
 public static void validateString(String inpString) throws CustomException
 {
  	if(inpString==null || inpString.isEmpty())
  	{
		throw new CustomException("\"Input String cannot be EMPTY or null!\"");
  	}
 }
 
 //method to get the length of the 
 public static int getStringLength(String inpString) throws CustomException
 {
	validateString(inpString);
	return inpString.length();
 }
 
 //method to check a number is negative or not
 public static void validateNumberNegative(int num) throws CustomException
{
	if(num<0)
	{
		throw new CustomException("Number cannot be Negative!");
	}
}

//method to validate Object Array is null or empty
public static void validateObject(Object[] objArray)throws CustomException
{
	if(objArray==null || objArray.length<=0)
	{
		throw new CustomException("Object cannot be NULL or EMPTY!");
	}
}

//method to validate Object is null
public static void validateObject(Object objName)throws CustomException
{
	if(objName==null)
	{
		throw new CustomException("Object cannot be NULL!");
	}
}

//method to check did a file exists
public static void validateFile(File fileName)throws CustomException
{
	if(!fileName.exists())
	{
		throw new CustomException("File doesn't exists!");
	}
}

//method to get the length of the array
public static int getArrayLength(Object[] objArray)throws CustomException
{
		validateObject(objArray);
		return objArray.length;
}

}
