package runbuilder;
import mybuilder.Builders;
import inout.Reader;

class RunnerMain
{
 public String multiStrings(int num)
 {
	Reader read=new Reader();
 	String resultStr="";
 	read.print("Enter "+num+" Strings:");
 	for(int iter=0;iter<num;iter++)
 	{
 		String input=read.getString("");
 		resultStr=resultStr.concat(input);
 		if(iter<num-1)
	 		resultStr=resultStr.concat(" ");
 	}
 	return resultStr;
 }
 public static void main(String[] args)
 {
  //declaration of repeated variables
  String message="",inpString,inpString2,inpString3,value,joinStr;
  char joinChar;
  int option,num,length;
  
  //creating necessary object
  Reader read=new Reader();
  Builders exec=new Builders();
  RunnerMain runObj=new RunnerMain();
  StringBuilder userBuilder;
  //start of execution part
  read.print("String Builder Programs!");
  read.print("Options: \n1. Length of string builder basic \n2.Appeding multi strings with a character| \n3. Inserting string between to string| \n4. Deleting the first string| \n5. Replace space with a character| \n6. Reverse a string builder| \n7.Delete few chars| \n8.Replace few chars| \n9. Fetch first index of char | \nt10. Fetch first last index of char| \n11. Exit");
 do{
   option=read.getInt("Enter the option:");
  switch(option)
  {
  case 1:
  {
    	try{ 
	   userBuilder=exec.createBuilder();
	   length=exec.getBuilderLength(userBuilder);
	   read.print("Length of String Builder without string:"+length);
	   
	   inpString=read.getString("Enter a string:");
	   userBuilder.append(inpString);
	   
	   length=exec.getBuilderLength(userBuilder);
	   message="Length of String Builder after append:"+length;
	   }
	catch(Exception exception){
    		message=exception.getMessage();
	   }
   	read.print(message);
   	break;
  }
  case 2:
  {
	
	try{		
		inpString=read.getString("Enter a string:");
		userBuilder=exec.createBuilder(inpString);
		
		length=exec.getBuilderLength(userBuilder);
		read.print("String Builder:"+userBuilder);
		
		read.print("Length of String Builder:"+length);
		
		num=read.getInt("Enter no of strings:");
		inpString=runObj.multiStrings(num);
		
		joinChar=read.getChar("Enter a joining character:");
		
		userBuilder=exec.appendStringsWithChar(userBuilder,inpString,joinChar);
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);		
	   }
     catch(Exception exception){
	      	read.print(exception.getMessage());
   	}
	break;
  }
  case 3:
  {
	
	try{
		inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		
		inpString=read.getString("Enter a string to place between strings:");

		userBuilder=exec.insertString(userBuilder,inpString);
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
	   }
	   catch(Exception exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 4:
  {

	   try{
		inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		
		userBuilder=exec.deleteFirstString(userBuilder);
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
	   }
	catch(Exception exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 5:
  {
	   try{
	   	inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		inpString3=read.getString("Enter third string:");
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		
		joinChar=read.getChar("Enter a joining character:");

		userBuilder=exec.replaceSpace(userBuilder,joinChar);
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		}
	     catch(Exception exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 6:
  {
	   try{
		inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		inpString3=read.getString("Enter third string:");
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		userBuilder=exec.appendBuilder(userBuilder,' ');
		userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
		read.print("String Builder before reverse:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);

		userBuilder=exec.reverseBuilder(userBuilder);
		read.print("String Builder after reverse:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
	   }
	   catch(Exception exception){
	    message=exception.getMessage();
   	}
	read.print(message);
	break;
  }
  case 7:
  {
	try{
		inpString=read.getString("Enter a String:");
		userBuilder=exec.createBuilder(inpString);
		read.print("String Builder before removing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);

		int start=read.getInt("Enter the starting index to remove character:");
		int end=read.getInt("Enter the ending index to remove character:");
		userBuilder=exec.removeChars(userBuilder,start,end);
		
		read.print("String Builder after removing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
	   }
	catch(Exception exception){
	    message=exception.getMessage();
	}
	read.print(message);
	break;
  }
  case 8:
  {
	   try{
		inpString=read.getString("Enter a String:");
		userBuilder=exec.createBuilder(inpString);
		read.print("String Builder before replacing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);

		int start=read.getInt("Enter the starting index to remove character:");
		int end=read.getInt("Enter the ending index to remove character:");
		inpString2=read.getString("Enter a replacing String:");
		
		userBuilder=exec.replaceChars(userBuilder,inpString2,start,end);
		
		read.print("String Builder after replacing:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
	   }
	   catch(Exception exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 9:
  {

	   try{
		inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		inpString3=read.getString("Enter third string:");
		
		joinStr=read.getString("Enter a Joining Character:");
		
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,joinStr);
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		userBuilder=exec.appendBuilder(userBuilder,joinStr);
		userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		
		int index=exec.indexOfChar(userBuilder,joinStr);
		read.print("The Index of "+joinStr+" is "+index);
		}
	  catch(Exception exception){
		    message=exception.getMessage();
   	}
   	read.print(message);
   	break;
  }
  case 10:
  {
   	try{
	   	inpString=read.getString("Enter first string:");
		inpString2=read.getString("Enter second string:");
		inpString3=read.getString("Enter third string:");
		
		joinStr=read.getString("Enter a Joining Character:");
		
		userBuilder=exec.createBuilder();
		
		userBuilder=exec.appendBuilder(userBuilder,inpString);
		userBuilder=exec.appendBuilder(userBuilder,joinStr);
		userBuilder=exec.appendBuilder(userBuilder,inpString2);
		userBuilder=exec.appendBuilder(userBuilder,joinStr);
		userBuilder=exec.appendBuilder(userBuilder,inpString3);
		
		read.print("String Builder:"+userBuilder);
		length=exec.getBuilderLength(userBuilder);
		read.print("Length of String Builder:"+length);
		
		int index=exec.lastIndexOfChar(userBuilder,joinStr);
		read.print("The Last Index of "+joinStr+" is "+index);
 	   }
	catch(Exception exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 11:
  {
 	  read.close();
	  read.print("Exit Point!");
	  break;
  }
  default:
  {
	   read.print("Invalid Option!Enter option between 1 to 20!");
	   break;
  }
  } //End of switch
  }while(option!=11);
} //End of main()
} //End of class
