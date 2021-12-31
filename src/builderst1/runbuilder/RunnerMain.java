package runbuilder;
import mybuilder.Builders;
import inout.Reader;
import userexception.CustomException;

class RunnerMain
{

 //declaration of repeated variables
  String message="",inpString,inpString2,inpString3,value,joinStr;
  char joinChar;
  int num,length;
  
  //creating necessary object
  Builders exec=new Builders();

  Reader read=new Reader();
  StringBuilder userBuilder;
  
 public String multiStrings(int num)
 {
	String resultStr="";
 	Reader.print("Enter "+num+" Strings:");
 	for(int iter=0;iter<num;iter++)
 	{
 		String input=Reader.getString("");
 		resultStr=resultStr.concat(input);
 		if(iter<num-1)
	 		resultStr=resultStr.concat(" ");
 	}
 	return resultStr;
 }

private StringBuilder appender(StringBuilder userBuilder)throws CustomException
{
	inpString=Reader.getString("Enter first string:");
	inpString2=Reader.getString("Enter second string:");

	userBuilder=exec.appendBuilder(userBuilder,inpString);
	userBuilder=exec.appendBuilder(userBuilder,' ');
	userBuilder=exec.appendBuilder(userBuilder,inpString2);
		
	return userBuilder;
}

public StringBuilder appenderThree(StringBuilder userBuilder)throws CustomException
{
	inpString=Reader.getString("Enter first string:");
	inpString2=Reader.getString("Enter second string:");
	inpString3=Reader.getString("Enter third string:");

	userBuilder=exec.appendBuilder(userBuilder,inpString);
	userBuilder=exec.appendBuilder(userBuilder,' ');
	userBuilder=exec.appendBuilder(userBuilder,inpString2);
	userBuilder=exec.appendBuilder(userBuilder,' ');
	userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
	return userBuilder;
}

public StringBuilder appenderThreeWithStr(StringBuilder userBuilder)throws CustomException
{
	inpString=Reader.getString("Enter first string:");
	inpString2=Reader.getString("Enter second string:");
	inpString3=Reader.getString("Enter third string:");

	joinStr=Reader.getString("Enter a Joining Character:");
		
	userBuilder=exec.appendBuilder(userBuilder,inpString);
	userBuilder=exec.appendBuilder(userBuilder,joinStr);
	userBuilder=exec.appendBuilder(userBuilder,inpString2);
	userBuilder=exec.appendBuilder(userBuilder,joinStr);
	userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
	return userBuilder;
}

private void caseOne()
{
	try{ 
		userBuilder=exec.createBuilder();
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder without string:"+length);
	   
		inpString=Reader.getString("Enter a string:");
		userBuilder.append(inpString);
	   
		length=exec.getBuilderLength(userBuilder);
		message="Length of String Builder after append:"+length;
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseTwo()
{
	try{
		inpString=Reader.getString("Enter a string:");
		userBuilder=exec.createBuilder(inpString);
		
		length=exec.getBuilderLength(userBuilder);
		Reader.print("String Builder:"+userBuilder);
		
		Reader.print("Length of String Builder:"+length);
		
		num=Reader.getInt("Enter no of strings:");
		inpString=multiStrings(num);
		
		joinChar=Reader.getChar("Enter a joining character:");
		
		userBuilder=exec.appendStringsWithChar(userBuilder,inpString,joinChar);
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);		
	}
	catch(CustomException exception){
		Reader.print(exception.getMessage());
	}
}

private void caseThree()
{
	try{
		userBuilder=exec.createBuilder();
		userBuilder=appender(userBuilder);
			
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
		
		inpString=Reader.getString("Enter a string to place between strings:");

		userBuilder=exec.insertString(userBuilder,inpString);
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseFour()
{
	try{
		userBuilder=exec.createBuilder();
		userBuilder=appender(userBuilder);
		
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
		
		userBuilder=exec.deleteFirstString(userBuilder);
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseFive()
{
	try{
	   	
		userBuilder=exec.createBuilder();
		userBuilder=appenderThree(userBuilder);		
		
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
		
		joinChar=Reader.getChar("Enter a joining character:");

		userBuilder=exec.replaceSpace(userBuilder,joinChar);
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseSix()
{
	try{
		userBuilder=exec.createBuilder();
		userBuilder=appenderThree(userBuilder);	
		
		Reader.print("String Builder before reverse:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);

		userBuilder=exec.reverseBuilder(userBuilder);
		Reader.print("String Builder after reverse:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseSeven()
{
	try{
		inpString=Reader.getString("Enter a String:");
		userBuilder=exec.createBuilder(inpString);
		Reader.print("String Builder before removing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);

		int start=Reader.getInt("Enter the starting index to remove character:");
		int end=Reader.getInt("Enter the ending index to remove character:");
		userBuilder=exec.removeChars(userBuilder,start,end);
		
		Reader.print("String Builder after removing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}

private void caseEight()
{
	try{
		inpString=Reader.getString("Enter a String:");
		userBuilder=exec.createBuilder(inpString);
		Reader.print("String Builder before replacing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);

		int start=Reader.getInt("Enter the starting index to remove character:");
		int end=Reader.getInt("Enter the ending index to remove character:");
		inpString2=Reader.getString("Enter a replacing String:");
		
		userBuilder=exec.replaceChars(userBuilder,inpString2,start,end);
		
		Reader.print("String Builder after replacing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
	Reader.print(message);
}
 
private void caseNine()
{
	try{
		
		userBuilder=exec.createBuilder();
		
		userBuilder=appenderThreeWithStr(userBuilder);	
		
		
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
		
		int index=exec.indexOfChar(userBuilder,joinStr);
		Reader.print("The Index of "+joinStr+" is "+index);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
   	Reader.print(message);
}

private void caseTen()
{
	try{

		userBuilder=exec.createBuilder();
		
		userBuilder=appenderThreeWithStr(userBuilder);
		
		
		Reader.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		Reader.print("Length of String Builder:"+length);
		
		int index=exec.lastIndexOfChar(userBuilder,joinStr);
		Reader.print("The Last Index of "+joinStr+" is "+index);
	}
	catch(CustomException exception){
		message=exception.getMessage();
	}
   	Reader.print(message);
}

 public static void main(String[] args)
 {
 
  int option;
  
  //creating necessary object
  RunnerMain runObj=new RunnerMain();
  
  //start of execution part
  Reader.print("String Builder Programs!");
  Reader.print("Options: \n1. Length of string builder basic \n2.Appeding multi strings with a character| \n3. Inserting string between to string| \n4. Deleting the first string| \n5. Replace space with a character| \n6. Reverse a string builder| \n7.Delete few chars| \n8.Replace few chars| \n9. Fetch first index of char | \nt10. Fetch first last index of char| \n11. Exit");
 do{
  option=Reader.getInt("Enter the option:");
  switch(option)
  {
  case 1:
  {
    	runObj.caseOne();
   	break;
  }
  case 2:
  {
	runObj.caseTwo();
	break;
  }
  case 3:
  {
	runObj.caseThree();
	break;
  }
  case 4:
  {
	runObj.caseFour();
	break;
  }
  case 5:
  {
	runObj.caseFive();
	break;
  }
  case 6:
  {
	runObj.caseSix();	   
	break;
  }
  case 7:
  {
	runObj.caseSeven();	
	break;
  }
  case 8:
  {
	runObj.caseEight();	  
	break;
  }
  case 9:
  {
	runObj.caseNine();  
   	break;
  }
  case 10:
  {
	runObj.caseTen();
	break;
  }
  case 11:
  {
 	  Reader.close();
	  Reader.print("Exit Point!");
	  break;
  }
  default:
  {
	   Reader.print("Invalid Option!Enter option between 1 to 20!");
	   break;
  }
  } //End of switch
  }while(option!=11);
} //End of main()
} //End of class
