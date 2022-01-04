package threads;

public class SleeperInterface implements Runnable{
	
	private long seconds;
	public SleeperInterface()
	{
		
	}
	
	public SleeperInterface(long seconds)
	{
		this.seconds=seconds;
	}
	
	public void run()
	{
		System.out.println("Going to Sleep:"+Thread.currentThread().getName());
		try {
		Thread.sleep(seconds);
		System.out.println("After Sleep:"+Thread.currentThread().getName());
		}
		catch (InterruptedException ex) {
		
		}
	}
}
