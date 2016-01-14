
public class Main_Test
{

	public static void main(String[] args)
	{
		Reserve reserve1 = new Reserve();
		ThrProd thrProd = new ThrProd(10, reserve1, 1000);
		ProdThreadPool pool = new ProdThreadPool(thrProd);
	}

}
