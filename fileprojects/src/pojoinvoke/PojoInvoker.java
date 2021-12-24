package pojoinvoke;

import inout.Reader;

public class PojoInvoker extends StudentPojo {

	StudentPojo student;
	
	public PojoInvoker()
	{
		student=new StudentPojo();
	}
	
	public static void main(String[] args) {
		PojoInvoker pojo=new PojoInvoker();
		Reader read=new Reader();
		String name=read.getString("Enter name:");
		pojo.student.setName(name);
		int Age=read.getInt("Enter age:");
		pojo.student.setAge(Age);
		
		String message=String.format("Student details:\nName:%s\nAge:%d",pojo.student.getName(),pojo.student.getAge());
		
		System.out.println(message);
	}

}
