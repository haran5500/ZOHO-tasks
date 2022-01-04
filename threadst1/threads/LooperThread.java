package threads;

public class LooperThread extends Thread{

	private long seconds;
	boolean execute=true;
	int count;
	
	public LooperThread()
	{
		
	}
	
	public LooperThread(long seconds)
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
			System.out.println("Going to Sleep:"+Thread.currentThread().getName()+" counter "+count);
			try {
			Thread.sleep(seconds);
			System.out.println("After Sleep:"+Thread.currentThread().getName()+" counter "+count);
			}
			catch (InterruptedException ex) {
			
			}
			count++;
		}
	}
	
}
