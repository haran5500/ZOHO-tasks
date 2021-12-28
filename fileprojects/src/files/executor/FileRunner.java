package files.executor;

import java.io.*;
import java.util.Properties;

import files.io.FileOperation;
import inout.Reader;
import user.exception.CustomException;

public class FileRunner {

	public static void main(String[] args) {
		
		FileOperation operate=new FileOperation();
		Reader read=new Reader();
		
		read.print("File Operations programs");
		read.print("Option:\n1. Create a file and write something\n2. Create a property and store as file\n3. Load the file data to properties\n4. Create a directory and storing previous files");
		int option=read.getInt("Enter the option:");
		
		String fileName,pathName,key,value,sentence;
		int num;
		
		switch(option)
		{
		case 1:
		{
			try {
			fileName=read.getString("Enter the filename for creation with extension:");
			operate.createNewFile(fileName);
			
			num=read.getInt("Enter no of sentences:");
			
			read.print("Enter "+num+" sentences:");
			for(int iter=0;iter<num;iter++)
			{
				sentence=read.getString("");
				operate.appendToFile(fileName, sentence);
				operate.appendToFile(fileName, "\n");
			}
			System.out.println("Written Successfully!");
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
			break;
		}
		case 2:
		{
			try {
			Properties propObj=operate.createProperty();
			
			num=read.getInt("Enter no of key:value pairs:-");
			
			read.print("Enter "+num+" key|value pairs:");
			for(int iter=0;iter<num;iter++)
			{
				key=read.getString("");
				value=read.getString("");
				operate.setPropertyKeyValue(propObj, key, value);
			}
			read.print("Properties load successfully!");
			
			fileName=read.getString("Enter the filename with extension for loading properties file:");
			operate.createNewFile(fileName);
			
			operate.writeInFile(propObj, fileName);
			
			read.print("Properties written to file successfully!");
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
			
			break;
		
		}
		
		case 3:
		{
			try {
			fileName=read.getString("Enter the filename to load its contents to a properties object:");
			
			Properties propObj=operate.createProperty();
			
			File fileObj=operate.createFileObject(fileName);
			
			operate.loadFromFile(propObj, fileObj);
			
			read.print("Property loaded from file!");
			
			System.out.println(propObj);
			
			}
			catch(CustomException ex)
			{
				System.out.println(ex);
			}
			
			break;
		}
		case 4:
		{
			try {
				//1
				pathName=read.getString("Enter the directory name for creation:");
				
				operate.createNewDir(pathName);
				
				read.print("Directory created successfully!");
				
				fileName=read.getString("Enter the filename for creation with extension:");
				
				operate.fileWithDirectory(pathName, fileName);
				
				String absolutePath=pathName+"/"+fileName;
				operate.createNewFile(absolutePath);
				
				num=read.getInt("Enter no of sentences:");
				
				read.print("Enter "+num+" sentences:");
				for(int iter=0;iter<num;iter++)
				{
					sentence=read.getString("");
					operate.appendToFile(absolutePath, sentence);
					operate.appendToFile(absolutePath, "\n");
				}
				System.out.println("Written Successfully!");
				
				//2
				Properties propObj=operate.createProperty();
				
				num=read.getInt("Enter no of key:value pairs:-");
				
				read.print("Enter "+num+" key|value pairs:");
				for(int iter=0;iter<num;iter++)
				{
					key=read.getString("");
					value=read.getString("");
					operate.setPropertyKeyValue(propObj, key, value);
				}
				read.print("Properties load successfully!");
				
				fileName=read.getString("Enter the filename with extension for loading properties file:");
				
				absolutePath=pathName+"/"+fileName;
				operate.createNewFile(absolutePath);
				
				operate.writeInFile(propObj, absolutePath);
				
				read.print("Properties written to file successfully!");	
				
				//3
				fileName=read.getString("Enter the filename to load its contents to a properties object:");
				
				absolutePath=pathName+"/"+fileName;
				
				File fileObj=operate.createFileObject(absolutePath);
				
				Properties propObj1=operate.createProperty();
				
				operate.loadFromFile(propObj1, fileObj);
				
				read.print("Property loaded from file!");
				
				System.out.println(propObj);
				
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
