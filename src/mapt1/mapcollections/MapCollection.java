package mapcollections;
import java.util.*;
import userexception.CustomException;

public class MapCollection
{

	//method to validate map
	public void validateMap(Map<Object,Object> mapObj)throws CustomException
	{
		if(mapObj==null)
		{
			throw new CustomException("Map Object cannot be NULL!");
		}
	}
	
	//#1 method to create a map
	public Map<Object,Object> createMap()
	{
		Map<Object,Object> mapObject=new HashMap<Object,Object>();
		return mapObject;
	}
	
	//#2 -7 method to insert key,value object pairs to a map
	public Object addKeyValues(Map<Object,Object> mapObject,Object key,Object value) throws CustomException
	{
		validateMap(mapObject);
		return mapObject.put(key,value);
	}
	
	//method to find the size of the map
	public int sizeOfMap(Map<Object,Object> mapObject)throws CustomException
	{
		validateMap(mapObject);
		int length=mapObject.size();
		return length;
	}
	
	//#8 method to check the key exists in a map
	public boolean checkKeyExists(Map<Object,Object> mapObject,Object key) throws CustomException
	{
		validateMap(mapObject);
		boolean result=mapObject.containsKey(key);
		return result;
	}
	
	//#9 method to check the value exists in a map
	public boolean checkValueExists(Map<Object,Object> mapObject,Object value) throws CustomException
	{
		validateMap(mapObject);
		boolean result=mapObject.containsValue(value);
		return result;
	}
	
	//#16 method to change values of a key
	public Map<Object,Object> changeKeyValues(Map<Object,Object> mapObject,Object key,Object value) throws CustomException
	{
		validateMap(mapObject);
		mapObject.replace(key,value);
		return mapObject;
	}

	//#17 method to change values of a key iff value matches
	public Map<Object,Object> changeKeyValues(Map<Object,Object> mapObject,Object key,Object oldValue,Object newValue) throws CustomException
	{
		validateMap(mapObject);
		mapObject.replace(key,oldValue,newValue);
		return mapObject;
	}
		
	//#11 #12 method to get the value of a key
	public Object getValueOfKey(Map<Object,Object> mapObject,Object key) throws CustomException
	{
		validateMap(mapObject);
		Object value=mapObject.get(key);
		return value;
	}
	
	//#13 method to get default values of a key
	public Object getDefaultValue(Map<Object,Object> mapObject,Object key,Object defValue) throws CustomException
	{
		validateMap(mapObject);
		Object value=mapObject.getOrDefault(key,defValue);
		return value;
	}
	
	//#14 method to remove key from the map
	public Object removeFromMap(Map<Object,Object> mapObject,Object key) throws Exception
	{
		validateMap(mapObject);
		return mapObject.remove(key);
	}
	
	//#15 method to remove the key if value matches in the map
	public boolean removeFromMap(Map<Object,Object> mapObject,Object key,Object value) throws CustomException
	{
		validateMap(mapObject);
		return mapObject.remove(key,value);
	}
	
	//#10 #18 method to copy map to another map
	public void copyMap(Map<Object,Object> srcMap,Map<Object,Object> destMap)throws CustomException
	{
		validateMap(srcMap);
		validateMap(destMap);
		destMap.putAll(srcMap);
	}
	
	//#20 method to clear a map
	public void clearMap(Map<Object,Object> srcMap)throws CustomException
	{
		validateMap(srcMap);
		srcMap.clear();
	}
}//end of class
