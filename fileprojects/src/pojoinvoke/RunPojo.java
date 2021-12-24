package pojoinvoke;

import inout.Reader;

public class RunPojo extends StudentPojo {

	public RunPojo(String name,int age)
	{
		super(name,age);
	}
	public static void main(String[] args) {
		Reader read=new Reader();
		String name=read.getString("Enter name:");
		int age=read.getInt("Enter age:");
		
		RunPojo run=new RunPojo(name,age);
		
		System.out.println(run);
	}

}
