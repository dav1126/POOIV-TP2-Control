import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.print.Units;



/**
 * Class used to create and manage a pool of ThrProd thread
 */
public class ProdThreadPool
{
	/**
	 * Max thread number in the thread pool
	 */
	private static final int MAX_THREAD_NUMBER = 6;

	/**
	 * Executor made to manage a pool of thread and start them periodically
	 */
	ScheduledExecutorService scheduledThreadPool;

	/**
	 * ProdThreadPool constructor that sets the maximum number of thread to
	 * MAX_THREAD_NUMBER and 
	 */
	public ProdThreadPool(ThrProd threadProd)
	{
		scheduledThreadPool = new ScheduledThreadPoolExecutor(MAX_THREAD_NUMBER);
		scheduledThreadPool.scheduleWithFixedDelay(threadProd, 0, threadProd.getDelay(), TimeUnit.MILLISECONDS);
	}

}
