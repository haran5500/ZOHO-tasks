package mycollections;
import java.util.*;
import userexception.CustomException;
import validatorutil.Validator;

public class CollectionClass
{

// method to validate list is null or empty
public void validateList(List inpList)throws CustomException
{
	if(inpList==null)
	{
		throw new CustomException("List cannot be NULL!");
	}
}

//method to validate Object is null or empty
public void validateObject(Object inputObj)throws CustomException
{
	if(inputObj==null)
	{
		throw new CustomException("Object cannot be null!");
	}
}

//method to validate Object Array is null or empty
public void validateObject(Object[] objArray)throws CustomException
{
	if(objArray==null || objArray.length<=0)
	{
		throw new CustomException("Object cannot be NULL or EMPTY!");
	}
}

//method to validate length of list the index
public void validateLength(List listObj,int index) throws CustomException
{
	int size=getListLength(listObj);
	if(size<index)
	{
		throw new CustomException("Index is greater than the length of List!");
	}
}

//method to validate range
public void validateRange(int start,int end)throws CustomException
{
	if(end<start)
	{
		throw new CustomException("Starting limit is greater than Ending limit!");
	}
}

// method to create a list
public List createList()
{
	List userList=new ArrayList();
	return userList;
}

//#1 method to fetch size of List
public int getListLength(List listObj)throws CustomException
{
	validateList(listObj);
	int length=listObj.size();
	
	return length;
}

//#2 method to add Strings Collections to a List
/*public List addElements(List listObj,String[] inpStr)throws CustomException
{
	validateList(listObj);

	validateObject(inpStr);
	
	Collections.addAll(listObj,inpStr);
	return listObj;
}*/

//#3 method to add Integers Collections to a List
/*public List addElements(List listObj,Integer[] inpIntArray)throws CustomException
{
	validateList(listObj);
	validateObject(inpIntArray);

	Collections.addAll(listObj,inpIntArray);
	
	return listObj;
}*/

//#4 method to add Custom Object Collection to a List
public List addElements(List listObj,Object[] inpObj)throws CustomException
{
	validateList(listObj);
	validateObject(inpObj);
	
	Collections.addAll(listObj,inpObj);
	
	return listObj;
}

//#12 #13 method to merge a List with another List
public List addList(List srcList,List destList)throws CustomException
{
	validateList(srcList);
	validateList(destList);
	
	destList.addAll(srcList);
	
	return destList;
}
//#10 method to insert element at N position
public List insertAtNPosition(List listObj,Object inpObj,int index)throws CustomException
{
	validateList(listObj);
	Validator.validateNumberNegative(index);
//	validateObject(inpObj);
	
	validateLength(listObj,index);
	
	listObj.add(index,inpObj);

	return listObj;
}
//#6 method to find index of object from a List
public int indexOfObject(List listObj,Object inpObj)throws CustomException
{
	validateList(listObj);
//	validateObject(inpObj);
	
	int index=listObj.indexOf(inpObj);
	
	return index;
}

//#9 method to find last index of object from a List
public int lastIndexOfObject(List listObj,Object inpObj)throws CustomException
{
	validateList(listObj);
//	validateObject(inpObj);
	
	int lastIndex=listObj.lastIndexOf(inpObj);
	
	return lastIndex;
}

//#8 method to get Object at an Index in a list
public Object getObjectAtIndex(List listObj,int index)throws CustomException
{
	validateList(listObj);
	Validator.validateNumberNegative(index);
	
	validateLength(listObj,index);
	
	Object resultObj=listObj.get(index);
	
	return resultObj;
}

//#11 method to get elements in between range
public List getSubList(List srcList,List destList,int start,int end)throws CustomException
{
	validateList(srcList);
	validateList(destList);
	
	Validator.validateNumberNegative(start);
	Validator.validateNumberNegative(end);
	
	validateLength(srcList,start);
	validateLength(srcList,end);
	
	destList=srcList.subList(start,end);
	
	return destList;
}

//#14 method to remove an Object from a List
public List removeObject(List inpList,Object inpObj)throws CustomException
{
	validateList(inpList);
//	validateObject(inpObj);
	
	inpList.remove(inpObj);	
	
	return inpList;
}

//#15 method to remove an Object from a List
public List removeObjectAtIndex(List inpList,int index)throws CustomException
{
	validateList(inpList);
	Validator.validateNumberNegative(index);
	
	inpList.remove(index);	
	
	return inpList;
}

//#17 method to remove Collection from a List
public List removeCollection(List srcList,List listToRemove)throws CustomException
{
	validateList(srcList);
	validateList(listToRemove);
	
	srcList.removeAll(listToRemove);
	
	return srcList;
}

//#19 method to remove all values from a List
public List removeAllElements(List srcList)throws CustomException
{
	validateList(srcList);
	
	srcList.clear();
	
	return srcList;
}

//#18 method to remove Elements from a List that are not in another Collection
public List retainCollection(List srcList,List listToRemove)throws CustomException
{
	validateList(srcList);
	validateList(listToRemove);
	
	srcList.retainAll(listToRemove);
	
	return srcList;
}

//#20 method to check whether an Object is in a Collection
public boolean checkItContains(List srcList,Object inpObj)throws CustomException
{
	validateList(srcList);
	validateObject(inpObj);
	
	return srcList.contains(inpObj);
}
	
//main() for testing
public static void main(String[] args)
{
	System.out.println("Hello");
}

}
