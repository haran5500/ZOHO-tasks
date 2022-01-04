package threads;

public class ExtendedThread extends Thread{

	public void run()
	{
		System.out.println("Current Thread Name:"+Thread.currentThread().getName());
		
		System.out.println("Thread Priority:"+Thread.currentThread().getPriority());
		
		System.out.println("Thread State:"+Thread.currentThread().getState());
	}

}
