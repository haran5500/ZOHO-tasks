package invsuper;

import inout.Reader;
import invoke.SuperClass;

public class RunConstructor
{
	
	public static void main(String[] args) {

		String inputStr=Reader.getString("Enter a string:");
		
		SuperClass run=new SuperClass(inputStr);
		
		System.out.println(run);
	}

}
