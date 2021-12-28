package files.io;

import java.io.*;
import java.util.Properties;

import user.exception.CustomException;
import validator.util.Validator;

public class FileOperation {

	//method to create a file object
	public File createFileObject(String fileName) throws CustomException
	{
		Validator.validateObject(fileName);
		File fileObj=new File(fileName);
		return fileObj;	
	}
	
	//method to create a file
	public boolean createNewFile(String fileName) throws CustomException
	{
		try {
		Validator.validateString(fileName);
		File fileObj=createFileObject(fileName);
		return fileObj.createNewFile();
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to create a directory
	public boolean createNewDir(String path)throws CustomException
	{
		
		Validator.validateString(path);
		File pathObj=createFileObject(path);
		return pathObj.mkdir();
	
	}
	
	//method to combine file with its directory
	public boolean fileWithDirectory(String directory,String fileName)throws CustomException
	{
		try {
		Validator.validateObject(directory);
		Validator.validateObject(fileName);
		File fileObj=new File(directory,fileName);
		
		return fileObj.createNewFile();
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to create a BufferedWriter object
	public BufferedWriter getBufferedWriter(String fileName,boolean value)throws CustomException
	{
		Validator.validateObject(fileName);
		FileWriter fileObj=getFileWriter(fileName,value);
		BufferedWriter bufferObj=new BufferedWriter(fileObj);
		return bufferObj;
	}
	
	//method to create a FileWriter object
	public FileWriter getFileWriter(String fileName,boolean value)throws CustomException
	{
		try {
		Validator.validateObject(fileName);
		FileWriter fileObj=new FileWriter(fileName,value);
		return fileObj;
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to write a String data into a file
	public void writeInFile(String fileName,String inpStr)throws CustomException
	{
		Validator.validateString(fileName);
		Validator.validateString(inpStr);
		try(BufferedWriter fileObj=getBufferedWriter(fileName,false))
		{
				writeInFile(fileObj,inpStr);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to write String Array data into a file
	public void writeInFile(String fileName,String[] inpStr)throws CustomException
	{
		Validator.validateString(fileName);
		Validator.validateObject(inpStr);

		try(BufferedWriter fileObj=getBufferedWriter(fileName,false))
		{
			writeInFile(fileObj,inpStr);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
		
	}
	
	//method to write the property into a file
	public void writeInFile(Properties propertyFile,String fileName)throws CustomException
	{
		Validator.validateObject(propertyFile);
		Validator.validateString(fileName);
		try(BufferedWriter fileObj=getBufferedWriter(fileName,false))
		{
				propertyFile.store(fileObj, "Properies file data!");
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to write string data into an file having a data's
	public void appendToFile(String fileName,String inpStr)throws CustomException
	{
		Validator.validateString(fileName);
		Validator.validateString(inpStr);
		try(BufferedWriter fileObj=getBufferedWriter(fileName,true))
		{	
			fileObj.write(inpStr);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to write String Array data into a file having data's
	public void appendToFile(String fileName,String[] inpStr)throws CustomException
	{
		Validator.validateString(fileName);
		Validator.validateObject(inpStr);
		try(BufferedWriter fileObj=getBufferedWriter(fileName,true))
		{
			writeInFile(fileObj,inpStr);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}

	//method to handle Writer objects with write
	public void writeInFile(BufferedWriter fileObj,String inpStr)throws CustomException
	{
		try {
		Validator.validateObject(fileObj);
		Validator.validateObject(inpStr);
			
		fileObj.write(inpStr);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to iterate array and write to file
	public void writeInFile(BufferedWriter fileObj,String[] inpStr)throws CustomException
	{
		try {
		Validator.validateObject(fileObj);
		Validator.validateObject(inpStr);
		int length=Validator.getArrayLength(inpStr);
		
		for(int iter=0;iter<length;iter++)
		{
			writeInFile(fileObj,inpStr[iter]);
			fileObj.newLine();
		}
		}
		
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	//method to create a property
	public Properties createProperty()throws CustomException
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
	public void loadFromFile(Properties propertyObj,File fileName)throws CustomException
	{
		Validator.validateObject(propertyObj);
		Validator.validateFile(fileName);
		try(BufferedReader fileRead=new BufferedReader(new FileReader(fileName)))
		{
			propertyObj.load(fileRead);
		}
		catch(IOException ex)
		{
			throw new CustomException(ex);
		}
	}
	
	
}//end of class
