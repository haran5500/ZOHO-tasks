package runner;
import mystrings.StringTask;
import inout.Reader;
import userexception.CustomException;

public class Runable
{
 
 //declaration of repeated variables
  String message,string,string2,value;
  char character;
  int option,num,length;
  boolean result;
  
 private void caseOne(Reader read,String inpStr)
 {
 	try{ 
	    length=fetch.getStringLength(string);
	    message=String.format("Length of string \" %s \" is %s",string,length);
	   }
	catch(CustomException exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
 }
 
 private void caseTwo(Reader read)
 {
 	string=read.getString("Enter a string to convert to character array:");
	try{
	    	char[] charArray=fetch.toCharArray(string);

	   	 read.print("Character Array:");
		 System.out.println();
		 System.out.println(charArray);
	 }
       catch(CustomException exception){
	      	read.print(exception.getMessage());
   	}
	
 }
 
 //main() method
 public static void main(String[] args)
 {

  //declaration of repeated variables
  String message,string,string2,value;
  char character;
  int option,num,length;
  boolean result;
  
  //creating necessary object
  Runable run=new Runable();
  Reader read=new Reader();
  StringTask fetch=new StringTask();
  
  //start of execution part
  read.print("String Programs!");
  read.print("Options: \n1. Length of string| \n2.String convert to char array| \n3. Character at position| \n4. Char occurrence count| \n5. Char last index| \n6. Print last N char| \n7.First N char| \n8.Replacing N char| \n9. Checking string start | \nt10. Checking end string| \n11.Toupper| \n12. Tolower| \n13. Reverse a string| \n14. Read multiple string|\n 15. Remove white space| \n 16. Array without space| \n17. Merge strings| \n18.Strings 	equality check| \n19. Strings equailty check non-case | \n20. Trim spaces|\n21. Exit");
  do{
   option=read.getInt("Enter the option:");
  switch(option)
  {
  case 1:
  {
   	if(args.length>0)
   	{
   		run.caseOne(read,args[0]);
   	}
   	else
   	{
    		read.print("No arguments passed, cannot find the length!");
   	}
   	break;
  }
  case 2:
  {
  	run.caseTwo();
	break;
  }
  case 3:
  {
	string=read.getString("Enter a string to fetch character from a position:");
	num=read.getInt("Enter the position number to return a character:");
	try{
	     length=fetch.getStringLength("");
	     if(length>=num)
	     {
	      character=fetch.getCharAt(string,num-1);
	      message=String.format("Character at %d in \"%s\" is %c",num,string,character);
		}
	     else
	     {
		      message="String entered is less than number of characters to return!";
	     }
	     }
	   catch(CustomException exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 4:
  {
	   string=read.getString("Enter a string:");
	   character=read.getChar("Enter a character to find its occurence:");
	   try{
		num=fetch.charOccurCount(string,character);
		message=String.format("The occurence of '%c' in \"%s\" is %d",character,string,num);
	   }
	catch(CustomException exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 5:
  {
	   string=read.getString("Enter a string:");
	   character=read.getChar("Enter a character to find its last index:");
	   try{
		   num=fetch.charLastIndex(string,character);
		   message=String.format("The last index of '%c' in \"%s\" is %d",character,string,num);
		}
	     catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 6:
  {
	   string=read.getString("Enter a string:");
	   num=read.getInt("Enter no of character to return:");
	   try{
		   length=fetch.getStringLength(string);
		   if(length>=num)
		   {
		    value=fetch.getLastNChar(string,num);
		    message=String.format("The last %d characters of \"%s\" is %s",num,string,value);
		   }
		   else
		   {
		    message="String entered is less than number of characters to return!";
		   }
	   }
	   catch(CustomException exception){
	    message=exception.getMessage();
   	}
	read.print(message);
	break;
  }
  case 7:
  {
   	string=read.getString("Enter a string:");
	num=read.getInt("Enter no of character to return:");
	try{
	   length=fetch.getStringLength(string);
	   if(length>=num)
	   {
		value=fetch.getFirstNChar(string,num);
    		message=String.format("The first %d characters of \"%s\" is %s",num,string,value);
	   }
	   else
	   {
		    message="String entered is less than number of characters to return!";
	   }
	   }
	catch(CustomException exception){
	    message=exception.getMessage();
	}
	read.print(message);
	break;
  }
  case 8:
  {
	   string=read.getString("Enter a string:");
	   num=read.getInt("Enter no of character to replace:");
	   string2=read.getString("Enter the string to replace:");
	   try{
		   length=fetch.getStringLength(string);
		   if(length>=num)
		   {
		    value=fetch.replaceFirstN(string,string2,num);
		    message=String.format("The first %d replaced characters in \"%s\" is %s",num,string,value);
		   }
		   else
		   {
		    message="String entered is less than number of characters to return!";
		    }
	   }
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  }
  case 9:
  {
	   string=read.getString("Enter a string");
	   string2=read.getString("Enter a starting string to check:");
	   try{
		   result=fetch.checkStartsWith(string,string2);
		   message=String.format("The string \"%s\" startswith \"%s\" is %b",string,string2,result);
		   }
	  catch(CustomException exception){
		    message=exception.getMessage();
   	}
   	read.print(message);
   	break;
  }
  case 10:
  {
  	 string=read.getString("Enter a string");
 	  string2=read.getString("Enter a ending string to check:");
   	try{
   		result=fetch.checkEndsWith(string,string2);
   		message=String.format("The string \"%s\" endswith \"%s\" is 				%b",string,string2,result);
	   }
	catch(CustomException exception){
    		message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 11:
  {
	string=read.getString("Tnter a string:");
	try{
	value=fetch.toUpper(string);
	   message=String.format("The UPPERCASE of string \"%s\" is %s",string,value);
	}
   	catch(CustomException exception){
	    message=exception.getMessage();
	}
	read.print(message);
	break;
  } 
  case 12:
  {
	   string=read.getString("Enter a string:");
	   try{
		   value=fetch.toLower(string);
		   message=String.format("The lowercase of string \"%s\" is %s",string,value);
		}
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 13:
  {
	   string=read.getString("Enter a string to reverse:");
	   try{
		   value=fetch.strReverse(string);
		   message=String.format("The reverse of string \"%s\" is %s",string,value);
	   }
	    catch(CustomException exception){
		    message=exception.getMessage();
   	  }
	   read.print(message);
	   break;
  } 
  case 14:
  {
	   string=read.getString("Enter multiple string in a line:");
	   read.print(String.format("The multiple string entered \" %s \"",string));
	   break;
  } 
  case 15:
  {
	   string=read.getString("Enter space separated strings:");
	   try{
		   value=fetch.removeWhiteSpace(string);
		   message=String.format("The concatenated string \"%s\" without whitespace is %s",string,value);
	   }
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 16:
  {
	   string=read.getString("Enter space separated strings:");
	   try{
		   String[] array=fetch.splitWithSpace(string);
		   read.print(String.format("The splited string  \"%s\" into array without whitespace is ",string));
	   for(String str:array)
	   {
		    System.out.println(str);
	   }
	   System.out.println();
	   }
	 catch(CustomException exception){
	 	read.print(exception.getMessage());
	   }
	   break;
  } 
  case 17:
  {
	   num=read.getInt("Enter no of strings:");
	   message="Enter "+num+" strings:";
	   String[] array=read.getStringArray(message,num);
	   string2=read.getString("Enter a joining string:");
	   try{
		   value=fetch.stringMerge(array,string2);
   
		   message=String.format("The joined string \"%s\"",value);
	   }
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 18:
  {
	   string=read.getString("Enter a string:");
	   string2=read.getString("Enter a string to check equality:");
	   try{
		   result=fetch.equalsCase(string,string2);
		   message=String.format("The string \"%s\" case sensitive equals \"%s\" : %b ",string,string2,result);
		}
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 19:
  {
	   string=read.getString("Enter a string:");
	   string2=read.getString("Enter a string to check equality:");
	   try{
		   result=fetch.equalsNonCase(string,string2);
		   message=String.format("The string \"%s\" case sensitive equals \"%s\" : %b ",string,string2,result);
	   }
	   catch(CustomException exception){
		    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 20:
  {
	   string=read.getString("Enter a string with leading and trailing spaces:");
	   try{
		   value=fetch.trimSpaces(string);
		   message=String.format("The trimmed string \"%s\" is \"%s\"",string,value);
		}
	   catch(CustomException exception){
	    message=exception.getMessage();
	   }
	   read.print(message);
	   break;
  } 
  case 21:
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
  } //end of switch
  }while(option!=21);
 } //end of main()
}
