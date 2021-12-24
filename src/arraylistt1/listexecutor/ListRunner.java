package listexecutor;
import mycollections.CollectionClass;
import java.util.*;
import inout.Reader;

public class ListRunner
{

int inpNum;
char inpChar;

public static void main(String[] args)
{
int option,length,num,index,lastIndex;

Integer[] intArray;
String message,inpStr;
String[] strArray;
Object[] objArray;

Reader read=new Reader();
CollectionClass collExec=new CollectionClass();
ListRunner fetch=new ListRunner();

List myList,myList2;

read.print("Array List programs!");
read.print("Options:");
read.print("1. Create an Arraylist and print size \n2. Adding strings to List\n3. Adding Integers to List\n4. Adding custom objects to List\n5. Adding different objects to List\n6. Find index of Object\n7. Iterator method to print List\n8. Element from an index\n9. fetch first and last index of Object\n10. Insert an element at N postion\n11. Storing elements from range\n12. Merge two List\n13. Merge two List with changing order\n14. Removing a particular object from a List\n15. Removing an Object at an Index\n16. \n17. Remove all from a collection\n18. Retain all from a List\n19. Clearing a List\n20. Check an object present in a List");
do{
option=read.getInt("Enter your option:");
	
switch(option)
{
	case 1:
	{
		try{
			myList=collExec.createList();
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 2:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			read.print("Elements of List:"+myList);

			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 3:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of integers:");
			intArray=new Integer[num];
			
			message="Enter "+num+" integers:";
			intArray=read.getIntegerArray(message,num);
			myList=collExec.addElements(myList,intArray);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 4:
	{
		try{
			myList=collExec.createList();
			fetch.inpChar=read.getChar("Enter a character:");
			fetch.inpNum=read.getInt("Enter an Integer");
			objArray=new Object[]{read,fetch.inpChar,fetch.inpNum};
			
			myList=collExec.addElements(myList,objArray);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 5:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			num=read.getInt("Enter no of integers:");
			intArray=new Integer[num];
			
			message="Enter "+num+" integers:";
			intArray=read.getIntegerArray(message,num);
			myList=collExec.addElements(myList,intArray);
			
			fetch.inpChar=read.getChar("Enter a character:");
			fetch.inpNum=read.getInt("Enter an Integer");
			objArray=new Object[]{read,fetch.inpChar,fetch.inpNum};
			
			myList=collExec.addElements(myList,objArray);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 6:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			inpStr=read.getString("Enter a String to find its index:");
			
			read.print("Elements of List:"+myList);

			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
			
			index=collExec.indexOfObject(myList,inpStr);
			
			message=String.format("The index of object %s is %d",inpStr,index);
			read.print(message);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 7:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			Iterator<String> myIterator = myList.iterator();  
			
			read.print("Printing the Elements of the List:");
			for(;myIterator.hasNext();)
			{
				read.print(myIterator.next());
			}
			
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 8:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			num=read.getInt("Enter the index to find the element:");
			
			Object obj=collExec.getObjectAtIndex(myList,num);
			
			message="the object at index "+num+" is "+obj;
			read.print(message);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 9:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings with duplicates:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			inpStr=read.getString("Enter a String to find its first & last index:");
			
			index=collExec.indexOfObject(myList,inpStr);
			
			message=String.format("The index of object %s is %d",inpStr,index);
			
			lastIndex=collExec.lastIndexOfObject(myList,inpStr);
			
			message=String.format("The first index of object %s is %d and the last index of object %s is %d",inpStr,index,inpStr,lastIndex);
			read.print(message);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 10:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings with duplicates:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			num=read.getInt("Enter the position to insert a String:");
			
			inpStr=read.getString("Enter a String to insert at "+num+" position:");
			
			myList=collExec.insertAtNPosition(myList,inpStr,num);
			read.print("Elements of List:"+myList);

			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
			
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 11:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			myList2=collExec.createList();
			
			int start=read.getInt("Enter the starting index:");
			int end=read.getInt("Enter the ending index:");
			
			myList2=collExec.getSubList(myList,myList2,start,end);
			
			read.print("Elements of List:"+myList2);

			length=collExec.getListLength(myList2);
			read.print("Size of the List:"+length);
			
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 12:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			myList2=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList2=collExec.addElements(myList2,strArray);
			
			List myList3=collExec.createList();
			
			myList3=collExec.addList(myList,myList3);
			myList3=collExec.addList(myList2,myList3);
			
			read.print("Elements of List:"+myList3);

			length=collExec.getListLength(myList3);
			read.print("Size of the List:"+length);
			
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
			break;
	}
	case 13:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			myList2=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList2=collExec.addElements(myList2,strArray);
			
			List myList3=collExec.createList();
			
			myList3=collExec.addList(myList2,myList3);
			myList3=collExec.addList(myList,myList3);
			
			read.print("Elements of List:"+myList3);

			length=collExec.getListLength(myList3);
			read.print("Size of the List:"+length);
					
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 14:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of double values:");
			
			Double[] doubleArray=new Double[num];
			
			message="Enter "+num+" Double values:";
			doubleArray=read.getDoubleArray(message,num);
			
			myList=collExec.addElements(myList,doubleArray);
			
			Double inpDouble=read.getDouble("Enter a double number to remove from List:");
			
			myList=collExec.removeObject(myList,inpDouble);
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 15:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of Double values:");
			Double[] doubleArray=new Double[num];
			
			message="Enter "+num+" Double values:";
			doubleArray=read.getDoubleArray(message,num);
			
			myList=collExec.addElements(myList,doubleArray);
			
			num=read.getInt("Enter a position to remove the List value at the position:");
			
			myList=collExec.removeObjectAtIndex(myList,num-1);
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 16:
	{
		try{
		read.print("Thank You!");
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 17:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			myList2=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList2=collExec.addElements(myList2,strArray);
			
			myList=collExec.removeCollection(myList,myList2);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 18:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			myList2=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList2=collExec.addElements(myList2,strArray);
			
			myList=collExec.retainCollection(myList,myList2);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 19:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of Long values:");
			Long[] longArray=new Long[num];
			
			message="Enter "+num+" Double values:";
			longArray=read.getLongArray(message,num);
			
			myList=collExec.addElements(myList,longArray);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
			
			myList=collExec.removeAllElements(myList);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	
	case 20:
	{
		try{
			myList=collExec.createList();
			num=read.getInt("Enter no of strings:");
			strArray=new String[num];
			
			message="Enter "+num+" strings:";
			strArray=read.getStringArray(message,num);
			
			myList=collExec.addElements(myList,strArray);
			
			message="Enter a string if it is present in a List:";
			inpStr=read.getString(message);
			
			boolean result=collExec.checkItContains(myList,inpStr);
			
			message="The String "+inpStr+" is present:"+result;
			
			read.print(message);
			
			read.print("Elements of List:"+myList);
			
			length=collExec.getListLength(myList);
			read.print("Size of the List:"+length);
		}
		catch(Exception ex)
		{
			read.print(ex.getMessage());
		}
		break;
	}
	case 21:
	{
		read.print("Exit Point!");
		break;
	}
	default:
	{
		read.print("No such Option!");
		break;
	}
}//end of switch
}while(option!=21); //end of while
}//end of main
}//end of class
