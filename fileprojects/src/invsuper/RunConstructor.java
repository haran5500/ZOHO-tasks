package invsuper;

import inout.Reader;
import invoke.SuperClass;

public class RunConstructor
{
	
	public static void main(String[] args) {
		Reader read=new Reader();
		String inputStr=read.getString("Enter a string:");
		
		SuperClass run=new SuperClass(inputStr);
		
		System.out.println(run);
	}

}
