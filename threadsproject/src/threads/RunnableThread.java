package threads;

public class RunnableThread implements Runnable{

	public void run()
	{
		System.out.println("Current Thread Name:"+Thread.currentThread().getName());
		
		System.out.println("Thread Priority:"+Thread.currentThread().getPriority());
		
		System.out.println("Thread State:"+Thread.currentThread().getState());
	}
	
}
