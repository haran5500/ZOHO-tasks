package runnerclasses;
import abstractclasses.*;
import inout.Reader;

public class AbstractRunable {

	private void parrotInvoker()
	{
		ParrotMod parrot=new ParrotMod();
		parrot.fly();
		parrot.speak();
	}
	
	private void duckInvoker()
	{
		Duck duck=new Duck();
		duck.fly();
		duck.speak();
	}
	
	public static void main(String[] args) {
		
		AbstractRunable run=new AbstractRunable();
		
//		BirdAbstract birds=new BirdAbstract();
		
		Reader.print("Abstract Invoking!");
		Reader.print("Options:\n1. Invoke Parrot\n2. Invoke Duck");
		int option=Reader.getInt("Enter the option:");
		
		switch(option)
		{
		case 1:
		{
			run.parrotInvoker();
			break;
		}
		case 2:
		{
			run.duckInvoker();
			break;
		}
		default:
		{
			Reader.print("No such option!");
			break;
		}
		}
		
	}

}
