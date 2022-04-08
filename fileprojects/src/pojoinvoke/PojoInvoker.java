package pojoinvoke;

import inout.Reader;
import pojo.pack.StudentPojo;

public class PojoInvoker extends StudentPojo {
	
	public static void main(String[] args) {
		
		StudentPojo student=new StudentPojo();
		
		String name=Reader.getString("Enter name:");
		student.setName(name);
		
		int Age=Reader.getInt("Enter age:");
		student.setAge(Age);
		
		String message=String.format("Student details:\nName:%s\nAge:%d",student.getName(),student.getAge());
		
		System.out.println(message);
	}

}
