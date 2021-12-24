package invoke;

import inout.Reader;

public class RunConstructor extends SuperClass
{

	public RunConstructor(String inputStr)
	{
		super(inputStr);
	}
	
	public static void main(String[] args) {
		Reader read=new Reader();
		String inputStr=read.getString("Enter a string:");
		
		RunConstructor run=new RunConstructor(inputStr);
		
		System.out.println(run);
	}

}
