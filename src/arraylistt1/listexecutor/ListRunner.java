package listexecutor;
import mycollections.CollectionClass;
import java.util.*;
import inout.Reader;
import userexception.CustomException;

public class ListRunner
{

int inpNum;
char inpChar;

int option,length,num,index,lastIndex;

Integer[] intArray;
String message,inpStr;
String[] strArray;
Object[] objArray;

Reader read=new Reader();
CollectionClass collExec=new CollectionClass();

List myList,myList2;

private List getStringList(List myList)throws CustomException
{
	num=Reader.getInt("Enter no of strings:");
	strArray=new String[num];
		
	message="Enter "+num+" strings:";
	strArray=Reader.getStringArray(message,num);
		
	myList=addElementsToList(myList,strArray);
		
	return myList;		
}

private List getIntegerList(List myList)throws CustomException
{
	num=Reader.getInt("Enter no of integers:");
	intArray=new Integer[num];
	
	message="Enter "+num+" integers:";
	intArray=Reader.getIntegerArray(message,num);
			
	myList=addElementsToList(myList,intArray);
			
	return myList;
		
}

private void showListLength(List myList)throws CustomException
{
	length=collExec.getListLength(myList);
	Reader.print("Size of the List:"+length);
}

private List addElementsToList(List myList,Object[] objArray)throws CustomException
{
	myList=collExec.addElements(myList,objArray);
	return myList;
}


private void caseOne()
 {
	try{
		myList=collExec.createList();
		
		Reader.print("Elements of List:"+myList);
		
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseTwo()
 {
	try{
		myList=collExec.createList();
		
		myList=getStringList(myList);

		Reader.print("Elements of List:"+myList);
		
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseThree()
 {
	try{
		myList=collExec.createList();
			
		myList=getIntegerList(myList);
Reader.print("Elements of List:"+myList);			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseFour()
 {
	try{
		myList=collExec.createList();
		inpChar=Reader.getChar("Enter a character:");
		inpNum=Reader.getInt("Enter an Integer");
		objArray=new Object[]{read,inpChar,inpNum};
			
		myList=addElementsToList(myList,objArray);

		Reader.print("Elements of List:"+myList);

		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseFive()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		myList=getIntegerList(myList);
			
		inpChar=Reader.getChar("Enter a character:");
		inpNum=Reader.getInt("Enter an Integer");
		objArray=new Object[]{read,inpChar,inpNum};
			
		myList=addElementsToList(myList,objArray);
			
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseSix()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		inpStr=Reader.getString("Enter a String to find its index:");
			
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
			
		index=collExec.indexOfObject(myList,inpStr);
			
		message=String.format("The index of object %s is %d",inpStr,index);
		Reader.print(message);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseSeven()
 {
	try{
		myList=collExec.createList();
		num=Reader.getInt("Enter no of strings:");
		strArray=new String[num];
			
		message="Enter "+num+" strings:";
		strArray=Reader.getStringArray(message,num);
			
		myList=addElementsToList(myList,strArray);
			
		Iterator<String> myIterator = myList.iterator();  
			
		Reader.print("Printing the Elements of the List:");
		for(;myIterator.hasNext();)
		{
			Reader.print(myIterator.next());
		}
			
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseEight()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		Reader.print("Elements of List:"+myList);
			
		num=Reader.getInt("Enter the index to find the element:");
			
		Object obj=collExec.getObjectAtIndex(myList,num);
			
		message="the object at index "+num+" is "+obj;
		Reader.print(message);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseNine()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		inpStr=Reader.getString("Enter a String to find its first & last index:");
			
		index=collExec.indexOfObject(myList,inpStr);
			
		message=String.format("The index of object %s is %d",inpStr,index);
			
		lastIndex=collExec.lastIndexOfObject(myList,inpStr);
			
		message=String.format("The first index of object %s is %d and the last index of object %s is %d",inpStr,index,inpStr,lastIndex);
		Reader.print(message);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseTen()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		num=Reader.getInt("Enter the position to insert a String:");
			
		inpStr=Reader.getString("Enter a String to insert at "+num+" position:");
			
		myList=collExec.insertAtNPosition(myList,inpStr,num);
		Reader.print("Elements of List:"+myList);

		showListLength(myList);
			
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseEleven()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		myList2=collExec.createList();
			
		int start=Reader.getInt("Enter the starting index:");
		int end=Reader.getInt("Enter the ending index:");
			
		myList2=collExec.getSubList(myList,myList2,start,end);
			
		Reader.print("Elements of List:"+myList2);

		showListLength(myList2);
			
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseTwelve()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		myList2=collExec.createList();
			
		myList2=getStringList(myList2);
			
		List myList3=collExec.createList();
		
		myList3=collExec.addList(myList,myList3);
		myList3=collExec.addList(myList2,myList3);
			
		Reader.print("Elements of List:"+myList3);

		showListLength(myList3);
			
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseThirteen()
 {
	try{
		myList=collExec.createList();
		
		myList=getStringList(myList);
			
		myList2=collExec.createList();
			
		myList2=getStringList(myList2);
			
		List myList3=collExec.createList();
			
		myList3=collExec.addList(myList2,myList3);
		myList3=collExec.addList(myList,myList3);
			
		Reader.print("Elements of List:"+myList3);

		showListLength(myList3);
					
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseFourteen()
 {
	 try{
		myList=collExec.createList();
		num=Reader.getInt("Enter no of double values:");
			
		Double[] doubleArray=new Double[num];
			
		message="Enter "+num+" Double values:";
		doubleArray=Reader.getDoubleArray(message,num);
			
		myList=addElementsToList(myList,doubleArray);
			
		Double inpDouble=Reader.getDouble("Enter a double number to remove from List:");
			
		myList=collExec.removeObject(myList,inpDouble);
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
		
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseFifteen()
 {
	 try{
		myList=collExec.createList();
		num=Reader.getInt("Enter no of Double values:");
		Double[] doubleArray=new Double[num];
			
		message="Enter "+num+" Double values:";
		doubleArray=Reader.getDoubleArray(message,num);
			
		myList=addElementsToList(myList,doubleArray);
		
		num=Reader.getInt("Enter a position to remove the List value at the position:");
			
		myList=collExec.removeObjectAtIndex(myList,num-1);
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
		
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseSixteen()
 {
	 Reader.print("Thank You!");
 }
 
 private void caseSeventeen()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		myList2=collExec.createList();
			
		myList2=getStringList(myList2);

		myList=collExec.removeCollection(myList,myList2);
			
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseEighteen()
 {
	try{
		myList=collExec.createList();
			
		myList=getStringList(myList);
			
		myList2=collExec.createList();
			
		myList2=getStringList(myList2);
			
		myList=collExec.retainCollection(myList,myList2);
		
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseNineteen()
 {
	try{
		myList=collExec.createList();
		num=Reader.getInt("Enter no of Long values:");
		Long[] longArray=new Long[num];
			
		message="Enter "+num+" Double values:";
		longArray=Reader.getLongArray(message,num);
			
		myList=addElementsToList(myList,longArray);
			
		Reader.print("Elements of List:"+myList);
			
		length=collExec.getListLength(myList);
		Reader.print("Size of the List:"+length);
			
		myList=collExec.removeAllElements(myList);
			
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }
 
 private void caseTwenty()
 {
	try{
		myList=collExec.createList();
		
		myList=getStringList(myList);

		message="Enter a string if it is present in a List:";
		inpStr=Reader.getString(message);
			
		boolean result=collExec.checkItContains(myList,inpStr);
			
		message="The String "+inpStr+" is present:"+result;
			
		Reader.print(message);
			
		Reader.print("Elements of List:"+myList);
			
		showListLength(myList);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
 }

public static void main(String[] args)
{
int option;

ListRunner fetch=new ListRunner();

Reader.print("Array List programs!");
Reader.print("Options:");
Reader.print("1. Create an Arraylist and print size \n2. Adding strings to List\n3. Adding Integers to List\n4. Adding custom objects to List\n5. Adding different objects to List\n6. Find index of Object\n7. Iterator method to print List\n8. Element from an index\n9. fetch first and last index of Object\n10. Insert an element at N postion\n11. Storing elements from range\n12. Merge two List\n13. Merge two List with changing order\n14. Removing a particular object from a List\n15. Removing an Object at an Index\n16. \n17. Remove all from a collection\n18. Retain all from a List\n19. Clearing a List\n20. Check an object present in a List");
do{
option=Reader.getInt("Enter your option:");
	
switch(option)
{
 case 1:
 {
  	fetch.caseOne();
   	break;
 }
 case 2:
 {
 	fetch.caseTwo();
	break;
 }
 case 3:
 {
	fetch.caseThree();
	break;
 }
 case 4:
 {
  	fetch.caseFour();
	break;
 }
 case 5:
 {
 	fetch.caseFive();
	break;
 }
 case 6:
 {
  	fetch.caseSix();
	break;
 }
 case 7:
 {
  	fetch.caseSeven();
	break;
 }
 case 8:
 {
	fetch.caseEight();
	break;
 }
 case 9:
 {
  	fetch.caseNine();
	break;
 }
 case 10:
 {
  	fetch.caseTen();
  	break;
 } 
 case 11:
 {
  	fetch.caseEleven();
	break;
 } 
 case 12:
 {
  	fetch.caseTwelve();
	break;
 } 
 case 13:
 {
  	fetch.caseThirteen();
	break;
 } 
 case 14:
 {
  	fetch.caseFourteen();
	break;
 } 
 case 15:
 {
 	fetch.caseFifteen();
	break;
 } 
 case 16:
 {
  	fetch.caseSixteen();
	break;
 } 
 case 17:
 {
  	fetch.caseSeventeen();
	break;
 } 
 case 18:
 {
  	fetch.caseEighteen();
	break;
 } 
 case 19:
 {
  	fetch.caseNineteen();
	break;
 } 
 case 20:
 {
  	fetch.caseTwenty();
	break;
 } 
 case 21:
 {
	Reader.print("Exit Point!");
	break;
 }
 default:
 {
	Reader.print("No such Option!");
	break;
 }
}//end of switch
}while(option!=21); //end of while
}//end of main
}//end of class
