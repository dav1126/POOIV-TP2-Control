import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class used to create and manage a pool of ThrConsume thread
 */
public class ConsumeThreadPool
{
	/**
	 * Max thread number in the thread pool
	 */
	private static final int MAX_THREAD_NUMBER = 3;

	/**
	 * Executor made to manage a pool of thread and start them periodically
	 */
	private ScheduledExecutorService scheduledThreadPool;

	/**
	 * ProdConsumePool constructor that sets the maximum number of thread to
	 * MAX_THREAD_NUMBER and executes a threadConsume with a fixed delay
	 * @param qty
	 * @param reserve
	 * @param delay
	 */ 
	public ConsumeThreadPool(int qty, Reserve reserve, int delay)
	{
		ThrConsume threadConsume  = new ThrConsume(qty, reserve, delay);
		scheduledThreadPool = new ScheduledThreadPoolExecutor(MAX_THREAD_NUMBER);
		scheduledThreadPool.scheduleAtFixedRate(threadConsume, 0, threadConsume.getDelay(), TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Return the scheduledExecutorService
	 * @return 
	 */
	public ScheduledExecutorService getScheduledThreadPool()
	{
		return scheduledThreadPool;
	}
}
