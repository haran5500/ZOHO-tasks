package threads;

public class LooperRunnable implements Runnable{

	private long seconds;
	boolean execute=true;
	int count;
	
	public LooperRunnable()
	{
		
	}
	
	public LooperRunnable(long seconds)
	{
		this.seconds=seconds;
	}
	
	public void setBool()
	{
		execute=false;
	}
	
	
	public void run()
	{
		count=0;
		while(execute)
		{
			System.out.println("From Runnable! Going to Sleep:"+Thread.currentThread().getName()+" counter "+count);
			try {
			Thread.sleep(seconds);
			System.out.println("From Runnable! After Sleep:"+Thread.currentThread().getName()+" counter "+count);
			}
			catch (InterruptedException ex) {
			
			}
			count++;
		}
	}
	
}
