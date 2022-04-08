package threads;


public class SleeperThread extends Thread {

	private long seconds;
	public SleeperThread()
	{
		
	}
	
	public SleeperThread(long seconds)
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
