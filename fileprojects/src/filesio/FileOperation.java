package filesio;

import java.io.*;
import java.util.Properties;
import myutilities.Validator;
import userexception.CustomException;

public class FileOperation {

	//method to create a file
	public boolean createNewFile(String fileName) throws CustomException,IOException
	{
		Validator.validateString(fileName);
		File fileObj=new File(fileName);
		return fileObj.createNewFile();
	}
	
	//method to create a directory
	public boolean createNewDir(String path)throws CustomException,IOException
	{
		Validator.validateString(path);
		File pathObj=new File(path);
		return pathObj.mkdir();
	}
	//method to write a String data into a file
	public void writeInFile(String fileName,String inpStr)throws CustomException,IOException
	{
		Validator.validateString(fileName);
		Validator.validateString(inpStr);
		try(BufferedWriter fileObj=new BufferedWriter(new FileWriter(fileName)))
		{
				fileObj.write(inpStr);
		}
	}
		
	//method to write String Array data into a file
	public void writeInFile(String fileName,String[] inpStr)throws CustomException,IOException
	{
		Validator.validateString(fileName);
		Validator.validateObject(inpStr);
		try(BufferedWriter fileObj=new BufferedWriter(new FileWriter(fileName)))
		{
			int length=Validator.getArrayLength(inpStr);
			
			for(int iter=0;iter<length;iter++)
			{
				fileObj.write(inpStr[iter]);
				fileObj.newLine();
			}
		}
	}
	
	//method to write the property into a file
	public void writeInFile(Properties propertyFile,String fileName)throws CustomException,IOException
	{
		Validator.validateObject(propertyFile);
		Validator.validateString(fileName);
		try(BufferedWriter fileObj=new BufferedWriter(new FileWriter(fileName)))
		{
				propertyFile.store(fileObj, "Properies file data!");
		}
	}
	
	//method to write string data into an file having a data's
	public void appendToFile(String fileName,String inpStr)throws CustomException,IOException
	{
		Validator.validateString(fileName);
		Validator.validateString(inpStr);
		try(BufferedWriter fileObj=new BufferedWriter(new FileWriter(fileName,true)))
		{	
			fileObj.write(inpStr);
		}
	}
	
	//method to write String Array data into a file having data's
	public void appendToFile(String fileName,String[] inpStr)throws CustomException,IOException
	{
		Validator.validateString(fileName);
		Validator.validateObject(inpStr);
		try(BufferedWriter fileObj=new BufferedWriter(new FileWriter(fileName,true)))
		{
			int length=Validator.getArrayLength(inpStr);
			
			for(int iter=0;iter<length;iter++)
			{
				fileObj.write(inpStr[iter]);
				fileObj.newLine();
			}
		}
	}

	//method to create a property
	public Properties createProperty()throws IOException
	{
		Properties createdProperty=new Properties();
		return createdProperty;
	}
	
	//method to write the data's key:value to a property
	public Object setPropertyKeyValue(Properties inpProperty,String key,String value)throws CustomException
	{
		Validator.validateObject(inpProperty);
		Validator.validateString(key);
		Validator.validateString(value);
		return inpProperty.setProperty(key, value);
	}
	
	//method to load data from file to properties
	public void loadFromFile(Properties propertyObj,File fileName)throws CustomException,IOException
	{
		Validator.validateObject(propertyObj);
		Validator.validateFile(fileName);
		try(BufferedReader fileRead=new BufferedReader(new FileReader(fileName)))
		{
			propertyObj.load(fileRead);
		}
	}
	
	
}//end of class
