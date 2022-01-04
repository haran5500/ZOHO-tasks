package runners;
import threads.LooperThread;
import threads.LooperRunnable;
import inout.Reader;

public class RunThread
{

long seconds,sleepSeconds;

private void loopingThread()
	{
		int num=Reader.getInt("Enter no of Threads to generate:");
		
		LooperThread[] thread=new LooperThread[num];
		
		LooperRunnable[] runnable=new LooperRunnable[num];
		
		Thread[] interThread=new Thread[num];
		
		seconds=Reader.getLong("Enter the seconds for sleep:");
		seconds*=1000;
		
		sleepSeconds=Reader.getLong("Enter the seconds for sleep between stopping threads:");
		sleepSeconds*=1000;
		
		for(int iter=0;iter<num;iter++)
		{
			thread[iter]=new LooperThread(seconds);
			thread[iter].setName("Extended Thread - "+(iter+1));
			
			thread[iter].start();
			
			runnable[iter]=new LooperRunnable(seconds);
			interThread[iter]=new Thread(runnable[iter]);

			interThread[iter].setName("Interface Thread - "+(iter+1));
			interThread[iter].start();
		}
		
		try{
		Reader.print("!!!!!! Main Thread Going to Sleep: "+Thread.currentThread().getName());
		
		Thread.sleep(120000);
		
		Reader.print("!!!!!! Main Thread After Sleep: "+Thread.currentThread().getName());
		
		}catch(InterruptedException ex){}
			
		for(int iter=0;iter<num;iter++)
		{
			thread[iter].setBool();
			Reader.print("\n----------------------------\n");
			Reader.print("Stopped Extended Thread - "+(iter+1));
			Reader.print("\n----------------------------\n");
			
			try{
			thread[iter].sleep(sleepSeconds);
			}catch(InterruptedException ex){}
			
			runnable[iter].setBool();
			
			try{
			interThread[iter].sleep(sleepSeconds);
			}catch(InterruptedException ex){}
			
			Reader.print("\n----------------------------\n");
			Reader.print("Stopped Interface Thread - "+(iter+1));
			Reader.print("\n----------------------------\n");
			
		}
		
	}

public static void main(String[] args)
{
	RunThread runner=new RunThread();
	
	Reader.print("Looping the Threads!");

	runner.loopingThread();
	
}

}
