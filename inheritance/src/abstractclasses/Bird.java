package abstractclasses;

import inout.Reader;

public abstract class Bird {
	public abstract void fly();
	
	public void speak()
	{
		Reader.print("I can Speak!");
	}
	
}
