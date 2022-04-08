package files.executor;

import java.io.*;
import java.util.Properties;

import files.io.FileOperation;
import inout.Reader;
import user.exception.CustomException;

public class FileRunner {

	FileOperation operate=new FileOperation();
	String fileName,pathName,key,value,sentence,absolutePath;
	int num;
	
	private String getDirectory()throws CustomException
	{
		
			pathName=Reader.getString("Enter the directory name for creation:");
		
			operate.createNewDir(pathName);
		
			Reader.print("Directory created successfully!");	

			return pathName;
		
	}
	
	private void writeInFile()
	{
		 try {
				fileName=Reader.getString("Enter the filename for creation with extension:");
				
				fileOperation(fileName);
		 }
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
	}
	
	private void writeFileInDirectory(String pathName)
	{
		try {
			
		fileName=Reader.getString("Enter the filename for creation with extension:");
		
		operate.fileWithDirectory(pathName, fileName);
		
		absolutePath=pathName+"/"+fileName;
		
		fileOperation(absolutePath);
	 }
	catch(CustomException ex)
	{
		System.out.println(ex);
	}

	}
	
	private void fileOperation(String fileName)throws CustomException
	{
			operate.createNewFile(fileName);
			
			num=Reader.getInt("Enter no of sentences:");
			
			Reader.print("Enter "+num+" sentences:");
			for(int iter=0;iter<num;iter++)
			{
				sentence=Reader.getString("");
				operate.appendToFile(fileName, sentence);
				operate.appendToFile(fileName, "\n");
			}
			System.out.println("Written Successfully!");
			
	}
	
	private void storePropertyInFile()
	{
		try {
			Properties propObj=getProperties();
			
			Reader.print("Properties load successfully!");
			
			fileName=Reader.getString("Enter the filename with extension for loading properties file:");
			operate.createNewFile(fileName);
			
			operate.writeInFile(propObj, fileName);
			
			Reader.print("Properties written to file successfully!");
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
	}
	
	private void writePropertyonDirectory(String pathName)
	{
		try
		{
			
			Properties propObj=getProperties();
			
			Reader.print("Properties load successfully!");
			
			fileName=Reader.getString("Enter the filename with extension for loading properties file:");
			
			absolutePath=pathName+"/"+fileName;
			operate.createNewFile(absolutePath);
			
			operate.writeInFile(propObj, absolutePath);
			
			Reader.print("Properties written to file successfully!");	
		
		}
		catch(CustomException ex)
		{
			System.out.println(ex);
		}
	}
	
	private Properties getProperties()throws CustomException
	{
		Properties propObj=operate.createProperty();
		num=Reader.getInt("Enter no of key:value pairs:-");
		
		Reader.print("Enter "+num+" key|value pairs:");
		for(int iter=0;iter<num;iter++)
		{
			key=Reader.getString("");
			value=Reader.getString("");
			operate.setPropertyKeyValue(propObj, key, value);
		}
		return propObj;
	}
	
	private void loadDirFileToProperty(String pathName)
	{
		try
		{
			fileName=Reader.getString("Enter the filename to load its contents to a properties object:");
		
			absolutePath=pathName+"/"+fileName;
		
			File fileObj=operate.createFileObject(absolutePath);
		
			Properties propObj1=operate.createProperty();
		
			propObj1=getPropFromFile(propObj1,fileObj);
			
			Reader.print("Property loaded from file!");
		
			System.out.println(propObj1);
		}
		catch(CustomException ex)
		{
			System.out.println(ex);
		}
	}
	
	private void loadFileToProperty()
	{
		try {
			fileName=Reader.getString("Enter the filename to load its contents to a properties object:");
			
			Properties propObj=operate.createProperty();
			
			File fileObj=operate.createFileObject(fileName);
			
			propObj=getPropFromFile(propObj,fileObj);
		
			Reader.print("Property loaded from file!");
			
			System.out.println(propObj);
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
				
	}
	
	private Properties getPropFromFile(Properties propObj,File fileObj)throws CustomException
	{
		operate.loadFromFile(propObj, fileObj);
				
		return propObj;
	}
	
	public static void main(String[] args) {
		
		FileRunner run=new FileRunner();
		
		Reader.print("File Operations programs");
		Reader.print("Option:\n1. Create a file and write something\n2. Create a property and store as file\n3. Load the file data to properties\n4. Create a directory and storing previous files");
		int option=Reader.getInt("Enter the option:");
		
		switch(option)
		{
		case 1:
		{
			run.writeInFile();
			break;
		}
		case 2:
		{
		
			run.storePropertyInFile();
			break;
		
		}
		
		case 3:
		{
			
			run.loadFileToProperty();
			break;
		}
		case 4:
		{
			
			try {	
				String pathName=run.getDirectory();
				
				//1	
				run.writeFileInDirectory(pathName);

				//2
				run.writePropertyonDirectory(pathName);

				//3
				run.loadDirFileToProperty(pathName);
				
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}	
			
			

			break;
		}
	}
	}
}
