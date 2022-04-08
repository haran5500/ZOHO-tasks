package runners;

import inout.Reader;
import threads.ExtendedThread;
import threads.RunnableThread;
import threads.SleeperInterface;
import threads.SleeperThread;

public class ThreadRunner {

	long seconds;
	ExtendedThread extend=new ExtendedThread();
	
	RunnableThread runable=new RunnableThread();
	
	Thread thread1=new Thread(runable);
	
	private void showThreadProperties()
	{
		Reader.print("Current Thread Name:"+Thread.currentThread().getName());
		
		Reader.print("Thread Priority:"+Thread.currentThread().getPriority());
		
		Reader.print("Thread State:"+Thread.currentThread().getState());
		Reader.print("-------------------");

	}
	
	private void startThread()
	{
		extend.start();
	}
	
	private void startInterface()
	{
		thread1.start();
	}
	
	private void runThread()
	{
		Reader.print("Running Thread Class and its properties!");
		
		Reader.print("Before Starting:");
		
		showThreadProperties();
				
		startThread();
		
		Reader.print("After Starting:");
		
		showThreadProperties();

	}
	
	private void runInterface()
	{
		Reader.print("Running Runnable Interface and its properties!");
		
		Reader.print("Before Starting:");
		
		showThreadProperties();
		
		startInterface();
		
		Reader.print("After Starting:");
		
		showThreadProperties();
		
	}
	
	private void setThreadName()
	{
		String name=Reader.getString("Enter the Name for the Thread:");
		
		extend.setName(name);
		
		runThread();
		
	}
	
	private void setInterfaceName()
	{
		String name1=Reader.getString("Enter the name for the Runnable Interface:");
		
		thread1=new Thread(extend,name1);
		
		runInterface();
	}
	
	private void setSleeperThread()
	{
		seconds=Reader.getLong("Enter the seconds for sleep:");
		seconds*=1000;
		
		int num=Reader.getInt("Enter no of Threads to create:");
		
		for(int iter=0;iter<num;iter++)
		{
			SleeperThread threads=new SleeperThread(seconds);
			
		///	String name=Reader.getString("Enter the thread name:");
			threads.setName("ExtendedThread "+iter);
			threads.start();
		}
	
		seconds=Reader.getLong("Enter the seconds for making interface sleep:");
		seconds*=1000;
		
		int num2=Reader.getInt("Enter no of Runnable Threads to create:");
		
		for(int iter=0;iter<num2;iter++)
		{
			SleeperInterface threads=new SleeperInterface(seconds);
			Thread threadn=new Thread(threads);
			threadn.setName("RunnableThread "+iter);
			threadn.start();
		}
	}
	
	private void setDiffSleeperThread()
	{
	
		int num=Reader.getInt("Enter no of Threads to create:");
		
		for(int iter=0;iter<num;iter++)
		{
			seconds=Reader.getLong("Enter the seconds to make Thread "+iter+" to sleep:");
			seconds*=1000;
			SleeperThread threads=new SleeperThread(seconds);
			threads.setName("ExtendedThread "+iter);
			threads.start();
		}
	
		
		
		int num2=Reader.getInt("Enter no of Runnable Threads to create:");
		
		for(int iter=0;iter<num2;iter++)
		{
			seconds=Reader.getLong("Enter the seconds for making interface Thread "+iter+" to sleep:");
			seconds*=1000;
			SleeperInterface threads=new SleeperInterface(seconds);
			Thread threadn=new Thread(threads);
			threadn.setName("RunnableThread "+iter);
			threadn.start();
		}
	}
	
	public static void main(String[] args) {
		
		ThreadRunner runner=new ThreadRunner();
		
		Reader.print("Thread Running Interface!");
		Reader.print("Options:\n1. Running Thread\n2. Running Runnable Interface\n3. Setting names for the Threads\n4. Setting sleep for the Threads\n5. Setting Different sleeping intervals");
		
		int option=Reader.getInt("Enter your option:");
		
		switch(option)
		{
		
		case 1:
		{
			runner.runThread();
			break;
		}
		case 2:
		{
			runner.runInterface();
			break;
		}
		case 3:
		{
			runner.setThreadName();
			
			runner.setInterfaceName();
			break;
		}
		case 4:
		{
			runner.setSleeperThread();
			break;
		}case 5:
		{
			runner.setDiffSleeperThread();
			break;
		}
		default:
		{
			Reader.print("No such option!");
			break;
		}
		
		}
		
	}

}
