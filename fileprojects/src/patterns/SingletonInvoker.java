package patterns;
//import patterns.SingletonClass;
import inout.Reader;

public class SingletonInvoker {

	public static void main(String[] args) {
		
		SingletonClass singleObj=SingletonClass.INSTANCE;
		
		Reader.print(singleObj.str);
		}

}
