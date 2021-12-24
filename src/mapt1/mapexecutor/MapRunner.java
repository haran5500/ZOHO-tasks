package mapexecutor;
import java.util.Map;
import mapcollections.MapCollection;
import userexception.CustomException;
import inout.Reader;

public class MapRunner
{

public static void main(String[] args)
{

//declaring necessary variables
int option,length,num;
Object strKey,strValue,checkKey,checkValue;
Object intKey,intValue;
boolean result;

//initiating necessary objects
Reader read=new Reader();
MapCollection mapper=new MapCollection();
Map<Object,Object> mapObj,mapObj2;

read.print("Map Collection Programs!");
read.print("Options:");
read.print("1. Create a HashMap and print size \n2. Adding strings pairs to HashMap\n3. Adding Integers pairs to HashMap\n4. Adding String Keys and Integer values to HashMap\n5. Adding String Keys and Object values to HashMap\n6. Adding String pairs with null values\n7. Adding null key with non-null value\n8. Check a key exists\n9. Check a value exists\n10. Changing the values of all keys in a Map\n11. Get the value of a Key\n12. Get the value of a non-existing key\n13.Getting default value of a Key\n14. Removing a Key\n15. Removing a key with value matching\n16.Replace value of a key \n17. Replace value of a key with value matching\n18. Copy a Map to another Map\n19. Iterating over a Map\n20. Remove all elements of a Map\n21. Exit!");
do{
option=read.getInt("Enter your option:");

switch(option)
{
	case 1:
	{
		try{
			mapObj=mapper.createMap();
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 2:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of String key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 3:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Integer key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				intKey=read.getInt("");
				intValue=read.getInt("");
				
				mapper.addKeyValues(mapObj,intKey,intValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 4:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of String key: Integer value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				intValue=read.getInt("");
				
				mapper.addKeyValues(mapObj,strKey,intValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 5:
	{
		try{
			mapObj=mapper.createMap();
			
			Object[] objArray=new Object[]{read,mapper};
			
			num=objArray.length;
			
			read.print("Enter "+num+" String keys:");
			for(int iter=0;iter<num;iter++)
			{
				strKey=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,objArray[iter]);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 6:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 7:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 8:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("enter a Object Key to check exists in a map:");
			result=mapper.checkKeyExists(mapObj,checkKey);
			
			read.print(String.format("The key %s exists in map:%b",checkKey,result));
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 9:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkValue=read.getString("enter a Object value to check exists in a map:");
			result=mapper.checkValueExists(mapObj,checkValue);
			
			read.print(String.format("The value %s exists in map:%b",checkValue,result));
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 10:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			mapObj2=mapper.createMap();
			read.print(String.format("Enter %d Object keys duplicate to previous map and String values:",length));
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj2,strKey,strValue);
			}
			
			mapper.copyMap(mapObj2,mapObj);
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 11:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to get the value from map:");
			
			Object value=mapper.getValueOfKey(mapObj,checkKey);
			read.print("The value of key "+checkKey+" is "+value);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 12:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("enter a Object Key to get the value from map:");
			
			Object value=mapper.getValueOfKey(mapObj,checkKey);
			read.print("The value of key "+checkKey+" is "+value);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 13:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to get the value from map:");
			
			checkValue=read.getString("Enter a default Value to be returned from Key:");
			Object value=mapper.getDefaultValue(mapObj,checkKey,checkValue);
			
			read.print("The value of key "+checkKey+" is "+value);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	case 14:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to remove from a map:");
			
			mapper.removeFromMap(mapObj,checkKey);
			
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 15:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to remove from a map:");
			checkValue=read.getString("Enter a Object Value to check match with key:");
			
			mapper.removeFromMap(mapObj,checkKey,checkValue);
			
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 16:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to replace its value from a map:");
			checkValue=read.getString("Enter a Object Value to be placed for the key:");
			
			mapper.changeKeyValues(mapObj,checkKey,checkValue);
			
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 17:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			checkKey=read.getString("Enter a Object Key to replace its value from a map:");
			checkValue=read.getString("Enter a Object Value to check match with key:");
			String newStr=read.getString("Enter the Object value to be placed for the key:");
			
			mapper.changeKeyValues(mapObj,checkKey,checkValue,newStr);
			
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 18:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			mapObj2=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			read.print(String.format("Enter %d String & values pairs:",num));
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj2,strKey,strValue);
			}
			
			mapper.copyMap(mapObj2,mapObj);
			
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 19:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("Iterating through map elements:");
			read.print("Key\tValue\n______________________");
			for (Map.Entry<Object,Object> entry : mapObj.entrySet())
			{  
				read.print(entry.getKey() + "\t" + entry.getValue());   
			}   
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
		}
		break;
	}
	
	case 20:
	{
		try{
			mapObj=mapper.createMap();
			num=read.getInt("Enter no of Object key:value pairs to be entered:");
			
			for(int iter=0;iter<num;iter++)
			{
				read.print("Enter the key:value pairs of pair "+(iter+1)+":-");
				strKey=read.getString("");
				strValue=read.getString("");
				
				mapper.addKeyValues(mapObj,strKey,strValue);
			}
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length);
			
			mapper.clearMap(mapObj);
			 
			read.print("HashMap:"+mapObj);
			
			length=mapper.sizeOfMap(mapObj);
			read.print("Size of the Map:"+length); 
		}
		catch(CustomException ex)
		{
			read.print(ex.toString());
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
}while(option!=21); //end of loop

}//end of main()

}//end of class
