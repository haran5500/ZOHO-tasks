package pojoinvoke;

import inout.Reader;
import pojo.pack.StudentPojo;

public class RunPojo {

	public static void main(String[] args) {
		
		String name=Reader.getString("Enter name:");
		int age=Reader.getInt("Enter age:");
		
		StudentPojo run=new StudentPojo(name,age);
		
		System.out.println(run);
	}

}
