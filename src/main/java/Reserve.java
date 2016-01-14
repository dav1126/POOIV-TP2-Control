/**
 * Class used to manage the stock of the program
 * 
 */
public class Reserve extends Thread
{
	/**
	 * Reserve current stock
	 */
	private int stock;
	
	/**
	 * Initial stock when a reserve is created
	 */
	static final int STOCK_INITIAL = 500;
	
	/**
	 * Reserve constructor that sets the stock to STOCK_INITAL
	 */
	public Reserve()
	{
		this.stock = STOCK_INITIAL;
	}
	
	/**
	 * Get the reserve stock
	 * @return stock
	 */
	public int getStock()
	{
		return stock;
	}

//	/**
//	 * Set the reserve stock
//	 * @param stock
//	 */
//	public void setStock(int stock) Probablement pas necessaire a cause des methodes consume, produce
//	{
//		this.stock = stock;
//	}
	
	/**
	 * Subtract a consumed quantity from the stock.
	 * Synchronized to prevent 
	 * @param consumed quantity
	 */
	protected synchronized void consume(int qty)
	{
		this.stock -= qty;
	}
	
	/**
	 * Add a produced quantity to the stock
	 * @param produced quantity
	 */
	protected synchronized void produce(int qty)
	{
		this.stock += qty;
	}
}
