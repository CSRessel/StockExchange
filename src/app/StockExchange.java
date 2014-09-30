package app;

import java.util.*;

/**
 * Represents a stock exchange, which keeps a HashMap of listed stocks, keyed by
 * stock symbols. It has methods to list a new stock, request a quote for a
 * given stock symbol, and to place a specified trade order.
 * 
 * @author Emily Zhu
 */
public class StockExchange
{
	private HashMap<String, Stock> stocks;

	/**
	 * Constructs a new stock exchange object, with an empty list of stocks.
	 */
	public StockExchange()
	{
		stocks = new HashMap<String, Stock>();
	}

	/**
	 * Returns a quote for a given stock.
	 * 
	 * @param symbol
	 * @return a message that contains the quote of the stock.
	 */
	public String getQuote(String symbol)
	{
		return stocks.get(symbol).getQuote();
	}

	/**
	 * Adds a new stock with given parameters to the listed stocks.
	 * 
	 * @param symbol
	 * @param name
	 * @param price
	 */
	public void listStock(String symbol, String name, double price)
	{
		Stock s = new Stock(symbol, name, price);
		stocks.put(symbol, s);
	}

	/**
	 * Places a trade order by calling stock.placeOrder for the stock specified
	 * by the stock symbol in the trade order.
	 * 
	 * @param order
	 */
	public void placeOrder(TradeOrder order)
	{
		stocks.get(order.getSymbol()).placeOrder(order);
	}
}
