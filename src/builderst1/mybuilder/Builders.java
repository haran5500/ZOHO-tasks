package mybuilder;
import mystrings.StringTask;

public class Builders
{
 StringTask support=new StringTask();
 
//String Builder reference creation without parameter
 public StringBuilder createBuilder()
 {
	  StringBuilder builderObj=new StringBuilder();
	  return builderObj;
 }
 
 //String Builder reference creation with String parameter
 public StringBuilder createBuilder(String inpString)throws Exception
 {
 	  support.validateString(inpString);
	  StringBuilder builderObj=new StringBuilder(inpString);
	  return builderObj;
 }
 
 //Builder Validator for string empty
 public void builderValidator(StringBuilder inpStr)throws Exception
 {
	  if(inpStr==null)
	  {
		   throw new Exception("StringBuilder cannot be null!");
	  }
 }

 //method to check the String in String Builder
 public void stringValidator(StringBuilder inpStr)throws Exception
 {
 	int length=getBuilderLength(inpStr);
 	if(length<0)
 	{
 		throw new Exception("Builder string cannot be empty! ");
 	}
 }
  
 //method to append string to a StringBuilder
 public StringBuilder appendBuilder(StringBuilder builderStr,String inpStr)throws Exception
 {
 	stringValidator(builderStr);
 	support.validateString(inpStr);
 	builderStr.append(inpStr);
 	return builderStr;
 }
 
  //method to append string to a StringBuilder
 public StringBuilder appendBuilder(StringBuilder builderStr,char inpChar)throws Exception
 {
 	stringValidator(builderStr);
 	builderStr.append(inpChar);
 	return builderStr;
 }
 
 //#1 method to get the String length in a StringBuilder
 public int getBuilderLength(StringBuilder builderStr)throws Exception
 {
	  builderValidator(builderStr);
	  return builderStr.length();
 }
 
 //#2 method to append spaces between strings
 public StringBuilder appendStringsWithChar(StringBuilder builderStr,String inpStr,char joinChar)throws Exception
 {
 	builderValidator(builderStr);
 	support.validateString(inpStr);
	stringValidator(builderStr);
 	appendBuilder(builderStr,inpStr);
 	builderStr=replaceSpace(builderStr,joinChar);
 	return builderStr;
 }
 
 //#5 method to replace Space and insert a string in the index
 public StringBuilder replaceSpace(StringBuilder builderStr,char joinChar)throws Exception
 {
 	builderValidator(builderStr);

 	int len=getBuilderLength(builderStr);
 	for(int iter=0;iter<len;iter++)
 	{
 		if(builderStr.charAt(iter)==' ')
 		{
 			builderStr.setCharAt(iter,joinChar);
 		}
 	}
 	return builderStr;
 }

//# 9 method to get indexOf a character
public int indexOfChar(StringBuilder builderStr,String findStr) throws Exception
{
	builderValidator(builderStr);
	support.validateString(findStr);
	return builderStr.indexOf(findStr);
}

//# 10 method to get last indexOf a character
public int lastIndexOfChar(StringBuilder builderStr,String findStr) throws Exception
{
	builderValidator(builderStr);
	support.validateString(findStr);
	return builderStr.lastIndexOf(findStr);
}

//#3 method to place a string between strings in a String Builder
public StringBuilder insertString(StringBuilder builderStr,String inpStr)throws Exception
{
	builderValidator(builderStr);
	support.validateString(inpStr);
	
	int strLen=support.getStringLength(inpStr);
	
	int index=indexOfChar(builderStr," ");
	
	builderStr.insert(index+1,inpStr);
	
	int spaceIndex=index+strLen;
	
	builderStr.insert(spaceIndex+1," ");
	
	return builderStr;
}

//#4 method to delete first string in a String Builder
public StringBuilder deleteFirstString(StringBuilder builderStr)throws Exception
{
	builderValidator(builderStr);
	int index=indexOfChar(builderStr," ");
	builderStr.delete(0,index+1);
	return builderStr;
}

//#6 method to reverse a String Builder
public StringBuilder reverseBuilder(StringBuilder builderStr)throws Exception
{
	builderValidator(builderStr);
	builderStr.reverse();
	return builderStr;
}

//#7 method to remove few characters between a String builder
public StringBuilder removeChars(StringBuilder inpStr,int start,int end)throws Exception
{
	builderValidator(inpStr);

	support.checkNumberNegative(start);
	support.checkNumberNegative(end);
	
	int length=getBuilderLength(inpStr);
	support.lengthCheck(length,start);
	support.lengthCheck(length,end);
	
	if(end<start)
	{
		throw new Exception("Starting index is greater than Ending index!");
	}
	inpStr.delete(start,end);
	return inpStr;
}

//#8 method to replace few chars
public StringBuilder replaceChars(StringBuilder inpStr,String replaceString,int start,int end)throws Exception
{
	builderValidator(inpStr);
 	support.validateString(replaceString);

	support.checkNumberNegative(start);
	support.checkNumberNegative(end);
	
	int length=getBuilderLength(inpStr);
	support.lengthCheck(length,start);
	support.lengthCheck(length,end);
	
	if(end<start)
	{
		throw new Exception("Starting index is greater than Ending index!");
	}
	inpStr.replace(start,end,replaceString);
	return inpStr;
}
}//End of class
