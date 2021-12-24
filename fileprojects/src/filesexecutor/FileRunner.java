package filesexecutor;

import filesio.FileOperation;
import java.io.*;
import inout.Reader;
import userexception.CustomException;

public class FileRunner {

	public static void main(String[] args) {
		FileOperation operate=new FileOperation();
		Reader read=new Reader();
		
		try {
			
			String fileName=read.getString("Enter the file name to create:");
			operate.createNewFile(fileName);
		
			int num=read.getInt("Enter no of strings:");
			
			
			String[] strArray=new String[num];
			
			String message=String.format("Enter %d Strings:", num);
			strArray=read.getStringArray(message, num);
			
			operate.writeInFile(fileName, strArray);
			
			System.out.println("Written Successfully!");
		}
		catch(CustomException ex)
		{
			System.out.println(ex);
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
