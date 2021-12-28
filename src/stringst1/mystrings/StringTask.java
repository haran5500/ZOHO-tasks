package mystrings;
import userexception.CustomException;

public class StringTask
{

//method to check a String is empty or null
 public void validateString(String inpString) throws CustomException
 {
  	if(inpString==null || inpString.isEmpty())
  	{
		throw new CustomException("\"Input String cannot be EMPTY or null!\"");
  	}
 }
 
 //method to check the length of String
 public void lengthCheck(String inpString,int num) throws CustomException
 {
	  int length=getStringLength(inpString);
	  if(length<num)
	  {
	  	throw new CustomException("Input String is less than number of characters to return!");
	  }
 }
 
 //method to check the length of String
 public void lengthCheck(int length,int num) throws CustomException
 {
	  if(length<num)
	  {
	  	throw new CustomException("Input String is less than number of characters to return!");
	  }
 }
 
//method to check length of String Array with range
public void arrayLengthCheck(int length,int limit)throws CustomException
{
	  if(length<limit)
	  {
	  	throw new CustomException("Array Length is less than range specified!");
	  }
}
 //method to check array tring is null or empty
 public  void nullArrayCheck(String[] inpStrings) throws CustomException
 {
 	for(String iString:inpStrings)
 	{
		validateString(iString);
 	}
 }

//method to check a number is negative or not
 public void checkNumberNegative(int num) throws CustomException
{
	if(num<0)
	{
		throw new CustomException("Number cannot be Negative!");
	}
}
 //program 1
 public int getStringLength(String inpString) throws CustomException
 {
	validateString(inpString);
	return inpString.length();
 }
 
  //program 2
 public char[] toCharArray(String inpString) throws CustomException
 {
	  validateString(inpString);
	  return inpString.toCharArray();
 }
 
  //program 3
 public char getCharAt(String inpString,int num) throws CustomException
 {
	  validateString(inpString);
	  checkNumberNegative(num);
	  lengthCheck(inpString,num);
	  return inpString.charAt(num);
 }
 
  //program 4
 public int charOccurCount(String inpString,char character) throws CustomException
 {
	  validateString(inpString);
	  int count=0;
	  char[] chararray=toCharArray(inpString);
	  for(char i:chararray)
	  {
		   if(i==character)
		   {
			count++;
		   }
	  }
  	  return count;
 }
 
  //program 5
 public int charLastIndex(String inpString,char character) throws CustomException
 {
	   validateString(inpString);
	   return inpString.lastIndexOf(character);
}
  
  //program 6
  public String getLastNChar(String inpString,int num) throws CustomException
 {
	  validateString(inpString);
	  checkNumberNegative(num);
	  lengthCheck(inpString,num);
	  int length=getStringLength(inpString);
	   return inpString.substring(length-num);
  }
  
  //program 7
  public String getFirstNChar(String inpString,int num) throws CustomException
 {
	  validateString(inpString);
	  checkNumberNegative(num);
	  lengthCheck(inpString,num);
	   return inpString.substring(0,num);
  }
  
  //program 8
  public String replaceFirstN(String inpString,String string2,int num) throws CustomException
 {
	  validateString(inpString);
	  validateString(string2);
	  checkNumberNegative(num);
	  lengthCheck(inpString,num);
	  String repstring=getFirstNChar(inpString,num);
	  return inpString.replace(repstring,string2);
  }
  
  //program 9
  public boolean checkStartsWith(String inpString,String start) throws CustomException
 {
	  validateString(inpString);
	  validateString(start);
	  
	  return inpString.startsWith(start);
  }
  
  //program 10
  public boolean checkEndsWith(String inpString,String end) throws CustomException
  {
  	validateString(inpString);
  	validateString(end);
	 return inpString.endsWith(end);
  }
  
  //for unit testing...
  public static void main(String[] args)
  {
  try{
   StringTask strtask=new StringTask();
   System.out.println(strtask.checkStartsWith(" Zoho"," "));
   }catch(Exception ex)
   {
    ex.printStackTrace();
   }
  }
  
  //program 11
  public String toUpper(String inpString) throws CustomException
 {
	  validateString(inpString);
	  return inpString.toUpperCase();
  }
  
  //program 12
  public String toLower(String inpString) throws CustomException
  {
	  validateString(inpString);
	  return inpString.toLowerCase();
  }
 
  //program 13
  public String strReverse(String inpString) throws CustomException
 {
  	validateString(inpString);
	String rev="";
	int length=getStringLength(inpString);
	for(int i=length-1;i>=0;i--)
	{
	    rev+=inpString.charAt(i);
	}
	return rev;
  }
 
  //program 14
  public void getmulti()
  {

  }
  
  //program 15
  public String removeWhiteSpace(String inpString) throws CustomException
  {
	  validateString(inpString);
	  return inpString.replaceAll("\\s","");
  }
  
  //program 16
  public String[] splitWithSpace(String inpString) throws CustomException
  {
 	 validateString(inpString);
	 String[] stringarray=inpString.split(" ");
	 return stringarray;
  }
  
  //program 17
  public String stringMerge(String[] strings,String joinstring) throws CustomException
  {
	  if(strings.length<=0)
	  {
		   throw new CustomException("\"Exception Occured: String array cannot be EMPTY!\"");
	  }
	  nullArrayCheck(strings);
	  validateString(joinstring);
	  String joined=String.join(joinstring,strings);
	  return joined;
  }
  
  //program 18
  public boolean equalsCase(String string1,String string2) throws CustomException
 {
 	validateString(string1);
 	validateString(string2);
	return string1.equals(string2);
  }
  
  //program 19
  public boolean equalsNonCase(String string1,String string2) throws CustomException
 {
	validateString(string1);
	validateString(string2);
	return string1.equalsIgnoreCase(string2);  
   }
   
  //program 20
  public String trimSpaces(String inpString) throws CustomException
 {
	validateString(inpString);
	return inpString.trim();
   }
} //end of class
