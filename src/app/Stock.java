package app;

/**
 * Stub class for Stock
 * @author Allen Zhou
 *
 */

public class Stock
{	
	private String symbol;
	
	public Stock(String symbol, String name, double price) {
		this.symbol = symbol;
	}
	
	public String getQuote() {
		return "This is a quote from " + symbol + "!";
	}
	
	public void placeOrder(TradeOrder order) {
		System.out.println("Placed order: " + symbol + ", " + order.getTrader().getName());
	}
}