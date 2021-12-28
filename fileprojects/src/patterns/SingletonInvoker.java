package patterns;
//import patterns.SingletonClass;
import inout.Reader;

public class SingletonInvoker {

	public static void main(String[] args) {
		
		Reader read=new Reader();
		
		SingletonClass singleObj=SingletonClass.INSTANCE;
		
		read.print(singleObj.str);
		}

}
