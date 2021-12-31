package mapexecutor;
import java.util.Map;
import mapcollections.MapCollection;
import userexception.CustomException;
import inout.Reader;

public class MapRunner
{

//declaring necessary variables
int option,length,num;
Object strKey,strValue,checkKey,checkValue;
Object intKey,intValue;
boolean result;

//initiating necessary objects
MapCollection mapper=new MapCollection();
Map<Object,Object> mapObj,mapObj2;
Reader read=new Reader();

private void showMapLength(Map<Object,Object> mapObj)throws CustomException
{
	length=mapper.sizeOfMap(mapObj);
	Reader.print("Size of the Map:"+length);
}

private Map<Object,Object> getStringMap(Map<Object,Object> mapObj)throws CustomException
{
	num=Reader.getInt("Enter no of String Object key:value pairs to be entered:");
			
	for(int iter=0;iter<num;iter++)
	{
		Reader.print("Enter the key:value pairs of pair "+(iter+1)+":-");
		strKey=Reader.getString("");
		strValue=Reader.getString("");
				
		mapper.addKeyValues(mapObj,strKey,strValue);
	}
	return mapObj;	
}

private void caseOne()
{
	try{
		mapObj=mapper.createMap();
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.getMessage());
	}
}

private void caseTwo()
 {
	try{
		mapObj=mapper.createMap();
	
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseThree()
 {
	try{
		mapObj=mapper.createMap();
		num=Reader.getInt("Enter no of Integer key:value pairs to be entered:");
			
		for(int iter=0;iter<num;iter++)
		{
			Reader.print("Enter the key:value pairs of pair "+(iter+1)+":-");
			intKey=Reader.getInt("");
			intValue=Reader.getInt("");
			
			mapper.addKeyValues(mapObj,intKey,intValue);
		}
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseFour()
 {
	try{
		mapObj=mapper.createMap();
		num=Reader.getInt("Enter no of String key: Integer value pairs to be entered:");
			
		for(int iter=0;iter<num;iter++)
		{
			Reader.print("Enter the key:value pairs of pair "+(iter+1)+":-");
			strKey=Reader.getString("");
			intValue=Reader.getInt("");
				
			mapper.addKeyValues(mapObj,strKey,intValue);
		}
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseFive()
 {
	try{
		mapObj=mapper.createMap();
		
		Object[] objArray=new Object[]{read,mapper};
			
		num=objArray.length;
			
		Reader.print("Enter "+num+" String keys:");
		for(int iter=0;iter<num;iter++)
		{
			strKey=Reader.getString("");
				
			mapper.addKeyValues(mapObj,strKey,objArray[iter]);
		}
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseSix()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseSeven()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseEight()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
			
		checkKey=Reader.getString("enter a Object Key to check exists in a map:");
		result=mapper.checkKeyExists(mapObj,checkKey);
			
		Reader.print(String.format("The key %s exists in map:%b",checkKey,result));
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseNine()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
	
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
					
		checkValue=Reader.getString("enter a Object value to check exists in a map:");
		result=mapper.checkValueExists(mapObj,checkValue);
			
		Reader.print(String.format("The value %s exists in map:%b",checkValue,result));
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseTen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
				
		Reader.print("HashMap:"+mapObj);
		
		showMapLength(mapObj);			
		
		mapObj2=mapper.createMap();
		Reader.print("Enter some keys duplicate to previous map!");
		
		mapObj2=getStringMap(mapObj2);
		
		mapper.copyMap(mapObj2,mapObj);
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseEleven()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			
		
		checkKey=Reader.getString("Enter a Object Key to get the value from map:");
			
		Object value=mapper.getValueOfKey(mapObj,checkKey);
		Reader.print("The value of key "+checkKey+" is "+value);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseTwelve()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			
		
		checkKey=Reader.getString("enter a Object Key to get the value from map:");
			
		Object value=mapper.getValueOfKey(mapObj,checkKey);
		Reader.print("The value of key "+checkKey+" is "+value);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}

 }
 
 private void caseThirteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			
		
		checkKey=Reader.getString("Enter a Object Key to get the value from map:");
			
		checkValue=Reader.getString("Enter a default Value to be returned from Key:");
		Object value=mapper.getDefaultValue(mapObj,checkKey,checkValue);
			
		Reader.print("The value of key "+checkKey+" is "+value);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseFourteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
		
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			
		
		checkKey=Reader.getString("Enter a Object Key to remove from a map:");
			
		mapper.removeFromMap(mapObj,checkKey);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseFifteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
				
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			

		checkKey=Reader.getString("Enter a Object Key to remove from a map:");
		checkValue=Reader.getString("Enter a Object Value to check match with key:");
			
		mapper.removeFromMap(mapObj,checkKey,checkValue);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseSixteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
				
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);			

		checkKey=Reader.getString("Enter a Object Key to replace its value from a map:");
		checkValue=Reader.getString("Enter a Object Value to be placed for the key:");
			
		mapper.changeKeyValues(mapObj,checkKey,checkValue);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseSeventeen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
				
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
			
		checkKey=Reader.getString("Enter a Object Key to replace its value from a map:");
		checkValue=Reader.getString("Enter a Object Value to check match with key:");
	
		String newStr=Reader.getString("Enter the Object value to be placed for the key:");
			
		mapper.changeKeyValues(mapObj,checkKey,checkValue,newStr);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseEighteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
				
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
					
		mapObj2=mapper.createMap();
	
		mapObj2=getStringMap(mapObj2);			
		mapper.copyMap(mapObj2,mapObj);
			
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj2);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseNineteen()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
					
		Reader.print("Iterating through map elements:");
		Reader.print("Key\tValue\n______________________");
		for (Map.Entry<Object,Object> entry : mapObj.entrySet())
		{  
			Reader.print(entry.getKey() + "\t" + entry.getValue());   
		}   
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }
 
 private void caseTwenty()
 {
	try{
		mapObj=mapper.createMap();
		mapObj=getStringMap(mapObj);
					
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
			
		mapper.clearMap(mapObj);
			 
		Reader.print("HashMap:"+mapObj);
			
		showMapLength(mapObj);
	}
	catch(CustomException ex)
	{
		Reader.print(ex.toString());
	}
 }

public static void main(String[] args)
{

//declaring necessary variables
int option;

//initiating necessary objects
Reader read=new Reader();

MapRunner runner=new MapRunner();

Reader.print("Map Collection Programs!");
Reader.print("Options:");
Reader.print("1. Create a HashMap and print size \n2. Adding strings pairs to HashMap\n3. Adding Integers pairs to HashMap\n4. Adding String Keys and Integer values to HashMap\n5. Adding String Keys and Object values to HashMap\n6. Adding String pairs with null values\n7. Adding null key with non-null value\n8. Check a key exists\n9. Check a value exists\n10. Changing the values of all keys in a Map\n11. Get the value of a Key\n12. Get the value of a non-existing key\n13.Getting default value of a Key\n14. Removing a Key\n15. Removing a key with value matching\n16.Replace value of a key \n17. Replace value of a key with value matching\n18. Copy a Map to another Map\n19. Iterating over a Map\n20. Remove all elements of a Map\n21. Exit!");
do{
option=Reader.getInt("Enter your option:");

switch(option)
{
	case 1:
	{
		runner.caseOne();
	   	break;
 	}
	case 2:
	{
	 	runner.caseTwo();
		break;
	}
	case 3:
	{
		runner.caseThree();
		break;
	}
	case 4:
	{
	  	runner.caseFour();
		break;
	}
	case 5:
	{
	 	runner.caseFive();
		break;
	}
	case 6:
	{
	  	runner.caseSix();
		break;
	}
	case 7:
	{
	  	runner.caseSeven();
		break;
	}
	case 8:
	{
		runner.caseEight();
		break;
	}
	case 9:
	{
	  	runner.caseNine();
		break;
	}
	case 10:
	{
	  	runner.caseTen();
	  	break;
	} 
	case 11:
	{
	  	runner.caseEleven();
		break;
	} 
	case 12:
	{
	  	runner.caseTwelve();
		break;
	} 
	case 13:
	{
	 	runner.caseThirteen();
		break;
	} 
	case 14:
	{
	 	runner.caseFourteen();
		break;
	} 
	case 15:
	{
	 	runner.caseFifteen();
		break;
	} 
	case 16:
	{
	  	runner.caseSixteen();
		break;
	} 
	case 17:
	{
	  	runner.caseSeventeen();
		break;
	} 
	case 18:
	{
	  	runner.caseEighteen();
		break;
	} 
	case 19:
	{
	  	runner.caseNineteen();
		break;
	} 
	case 20:
	{
	  	runner.caseTwenty();
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
}while(option!=21); //end of loop

}//end of main()

}//end of class
