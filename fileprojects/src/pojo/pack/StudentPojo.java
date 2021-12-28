package pojo.pack;

public class StudentPojo {
	
	private String name;
	private int age;
	
	public StudentPojo()
	{
		System.out.println("Default Constructor!");
	}
	
	public StudentPojo(String name,int age)
	{
		setName(name);
		setAge(age);
	}
	
	public void setName(String inpName)
	{
		name=inpName;
	}
	
	public void setAge(int inpAge)
	{
		age=inpAge;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String toString()
	{
		String result=String.format("Name:%s and Age:%d",getName(),getAge());
		return result;
	}
}
