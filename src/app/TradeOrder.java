package app;


/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 * 
 * @author Abhinav Venigalla
 */
public class TradeOrder
{
	private Trader trader;
	private String symbol;
	private boolean buyOrder;
	private boolean marketOrder;
	private int numShares;
	private double price;

	/**
	 * Constructs a new TradeOrder for a given trader, stock symbol, a number of
	 * shares, and other parameters.
	 * 
	 * @param trader
	 * @param symbol
	 * @param buyOrder
	 * @param marketOrder
	 * @param numShares
	 * @param price
	 */
	public TradeOrder(Trader trader, String symbol, boolean buyOrder,
			boolean marketOrder, int numShares, double price)
	{
		this.trader = trader;
		this.symbol = symbol;
		this.buyOrder = buyOrder;
		this.marketOrder = marketOrder;
		this.numShares = numShares;
		this.price = price;
	}

	/**
	 * Returns the price per share for this trade order (used by a limit order).
	 * 
	 * @return price per share
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * Returns the number of shares to be traded in this trade order.
	 * 
	 * @return number of shares
	 */
	public int getShares()
	{
		return numShares;
	}

	/**
	 * Returns the stock symbol for this trade order.
	 * 
	 * @return name of stock symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}

	/**
	 * Returns the trader for this trade order.
	 * 
	 * @return trader for this order
	 */
	public Trader getTrader()
	{
		return trader;
	}

	/**
	 * Returns true if this is a buy order; otherwise returns false.
	 * 
	 * @return whether this is a buy order
	 */
	public boolean isBuy()
	{
		return buyOrder;
	}

	/**
	 * Returns true if this is a sell order; otherwise returns false.
	 * 
	 * @return whether this is a sell order
	 */
	public boolean isSell()
	{
		return !buyOrder;
	}

	/**
	 * Returns true if this is a market order; otherwise returns false.
	 * 
	 * @return whether this is a market order
	 */
	public boolean isMarket()
	{
		return marketOrder;
	}

	/**
	 * Returns true if this is a limit order; otherwise returns false.
	 * 
	 * @return whether this is a limit order
	 */
	public boolean isLimit()
	{
		return !marketOrder;
	}

	/**
	 * Subtracts a given number of shares from the total number of shares in
	 * this trade order.
	 * 
	 * @param shares
	 *            number of shares to subtract
	 */
	public void subtractShares(int shares)
	{
		numShares -= shares;
	}
}
