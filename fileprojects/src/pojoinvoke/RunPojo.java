package pojoinvoke;

import inout.Reader;
import pojo.pack.StudentPojo;

public class RunPojo {

	public static void main(String[] args) {
		
		Reader read=new Reader();
		String name=read.getString("Enter name:");
		int age=read.getInt("Enter age:");
		
		StudentPojo run=new StudentPojo(name,age);
		
		System.out.println(run);
	}

}
