
/**
 * Class used to create the production of a reserve's resource
 *
 */
public class ThrProd extends Thread
{

	/**
	 * The quantity of resource produced
	 */
	private int qty;
	
	/**
	 * The reserve in which the production is made
	 */
	private Reserve reserve;
	
	/**
	 * Time used to create a delay after this thread
	 */
	private int delay;
	
	/**
	 * ThrProd constructor
	 * @param qty
	 * @param reserve
	 * @param delay
	 */
	public ThrProd(int qty, Reserve reserve, int delay)
	{
		this.qty = qty;
		this.reserve = reserve;
		this.delay = delay;
	}
	
	/**
	 * Running this ThrProd instance produces a qty of resource for it's 
	 * reserve and then puts the thread to sleep for it's delay time.
	 */
	@Override
	public void run()
	{
		this.reserve.consume(this.qty);
		//System.out.println(getReserve().getStock());
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
	 * Returns the quantity of resource produced
	 * @return quantity
	 */
	public int getQty()
	{
		return qty;
	}
	
	/**
	 * Sets the quantity of resource produced
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
