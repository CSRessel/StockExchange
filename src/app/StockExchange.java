package app;

/**
 * Stub class for StockExchange
 * @author Allen Zhou
 *
 */
public class StockExchange
{
	public StockExchange() {
	}
	
	public String getQuote(String symbol) {
		return "Getting a quote from stock: " + symbol;
	}
	
	public void placeOrder(TradeOrder order) {
		System.out.println("Placing an order for: " + order.getTrader().getName() + ", " + order.getSymbol());
	}
}
