
/**
 * Class used to create the consumption of a reserve's resource
 *
 */
/**
 * @author 0345162
 *
 */
public class ThrConsumme extends Thread
{
	/**
	 * The quantity of resource consumed
	 */
	private int qty;
	
	/**
	 * The reserve in which the consumption is made
	 */
	private Reserve reserve;
	
	/**
	 * Time used to create a delay after this thread, in milliseconds
	 */
	private int delay;
	
	/**
	 * ThrConsumme constructor
	 * @param qty
	 * @param reserve
	 * @param delay
	 */
	public ThrConsumme(int qty, Reserve reserve, int delay)
	{
		this.qty = qty;
		this.reserve = reserve;
		this.delay = delay;
	}
	
	/**
	 * Running this ThrConsumme instance consumes a qty of resource form it's 
	 * reserve and then puts the thread to sleep for it's delay time.
	 */
	@Override
	public void run()
	{
		this.reserve.consume(this.qty);
		try
		{
			Thread.sleep(this.delay);
		} catch (InterruptedException e)
		{
			System.out.println
				("Thread sleep error: Consumption thread is interrupted");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the quantity of resource consumed
	 * @return quantity
	 */
	public int getQty()
	{
		return qty;
	}

	/**
	 * Sets the quantity of resource consumed
	 * @param quantity
	 */
	public void setQty(int qty)
	{
		this.qty = qty;
	}

	/**
	 * Return the reserve of this instance
	 * @return reserve
	 */
	public Reserve getReserve()
	{
		return reserve;
	}
	
	/**
	 * Sets the reserve of this instance
	 * @param reserve
	 */
	public void setReserve(Reserve reserve)
	{
		this.reserve = reserve;
	}

	/**
	 * Returns the delay of this instance
	 * @return delay
	 */
	public int getDelay()
	{
		return delay;
	}

	/**
	 * Sets the delay of this instance
	 * @param delay
	 */
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
}
