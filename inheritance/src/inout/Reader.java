package inout;
import java.util.Scanner;

import validatorutil.Validator;

public class Reader
{
static Scanner scan=new Scanner(System.in);

//method to prevent nextLine override
 public static void getInput()
 {
 	scan.nextLine();
 }
 
 //method to get a string 
 public static String getString(String message)
 {
	print(message);
	String string=scan.nextLine();
	if(string.isEmpty())
	{
		print("Entered input cannot be EMPTY!");
		return getString(message);
	}
	return string;
 }
 
 //method to get a string array//
 public static String[] getStringArray(String message,int size)
 {

	String[] strings=new String[size];
	print(message);
	for(int i=0;i<size;i++)
	{
		strings[i]=scan.nextLine();
	}
	return strings;
 }
 
 //method to get a character
 public static char getChar(String message)
 {
	String string=getString(message);
	char character=string.charAt(0);
	return character;
 }
  
  //method to get a integer
 public static int getInt(String message)
 {
	print(message);
	int num;
	try{
		num=scan.nextInt();
	}
	catch(Exception ex)
	{
		print("Enter only Integer!");
		getInput();
		return getInt(message);
	}
	getInput();
	return num;
 }

 //method to get a integer
 public static int getInteger(String message)
 {
	print(message);
	int num;
	try{
		num=scan.nextInt();
	}
	catch(Exception ex)
	{
		print("Enter only Integer!");
		getInput();
		return getInteger(message);
	}
	getInput();
	return num;
 }

 //method to get a Integer Array//
 public static Integer[] getIntegerArray(String message,int size)
 {
	print(message);
	Integer[] intArray=new Integer[size];
	try{
		for(int i=0;i<size;i++)
		{

			intArray[i]=scan.nextInt();
		}
 	 
	}
	catch(Exception ex)
	{
		print("Enter only numbers!");
		getInput();
		return getIntegerArray(message,size);
	}
	getInput();
	return intArray;
}

public static long getLong(String message)
{
	print(message);
	Long num;
	try{
		num=scan.nextLong();
	}
	catch(Exception ex)
	{
		print("Enter only Integer!");
		getInput();
		return getLong(message);
	}
	getInput();
	return num;
}
//method to get a Long Array//
public static Long[] getLongArray(String message,int size)
{
	print(message);
	Long[] longArray=new Long[size];
	try{
		for(int i=0;i<size;i++)
		{
			longArray[i]=scan.nextLong();
		}
	}
	catch(Exception ex)
	{
		print("Enter only numbers!");
		getInput();
		return getLongArray(message,size);
	}
	getInput();
	return longArray;
}

 //method to get a Double
 public static double getDouble(String message)
 {
	print(message);
	double num=0.0;
	try{
		num=scan.nextDouble();
	}
	catch(Exception ex)
	{
		print("Enter only numbers!");
		getInput();
		return getDouble(message);
	}
	getInput();
	return num;
 }

//method to get a Double Array//
 public static Double[] getDoubleArray(String message,int size)
 {
	print(message);
	Double[] doubleArray=new Double[size];
	try{
		for(int i=0;i<size;i++)
		{
			doubleArray[i]=scan.nextDouble();
		}
	}
	catch(Exception ex)
	{
		print("Enter only numbers!");
		getInput();
		return getDoubleArray(message,size);
	}
	getInput();
	return doubleArray;
}


//method to extract elements between range from a String array
public static String[] getElementsInRange(String[] srcArray,int start,int end)throws Exception
{
	Validator.validateObject(srcArray);
	
	Validator.validateNumberNegative(start);
	Validator.validateNumberNegative(end);
	
	int length=srcArray.length;
	Validator.validateLength(length,start);
	Validator.validateLength(length,end);
	
	String[] filteredArray=new String[end];
	
	for(int iter=0;iter<end;iter++)
	{
		filteredArray[iter] = srcArray[iter];
	}
	return filteredArray;
}

//method to print a message
 public static void print(String value)
 {
	System.out.println(value);
 }
 
 //method to close opened resources
 public static void close()
 {
	print("Resources Closed!");
	scan.close();
 }

 }//end of class

