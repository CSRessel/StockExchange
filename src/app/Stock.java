package app;

import lib.*;

import java.text.NumberFormat;
import java.util.PriorityQueue;

/**
 * @author Clifford Ressel
 * Stock represents a stock, and stores this Stock's symbol, company name, price info, and orders
 */
public class Stock
{
	private String symbol;
	private String companyName;
	private double startingPrice, lowPrice, highPrice, lastPrice;
	private int dayVolume;
	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;
	
	public Stock(String symbol, String name, double price)
	{
		this.symbol = symbol;
		this.companyName = name;
		
		this.startingPrice 	= price;
		this.lowPrice 		= price;
		this.highPrice 		= price;
		this.lastPrice 		= price;
		
		this.dayVolume = 0;
		
		sellOrders = new PriorityQueue<TradeOrder>(0, new PriceComparator(true));
		buyOrders = new PriorityQueue<TradeOrder>(0, new PriceComparator(false));
	}
	
	/**
	 * Creates and returns a quote String for this Stock
	 * @return the quote for this stock
	 */
	public String getQuote()
	{
		String finalString = "";
		
		finalString += this.companyName;
		finalString += " (";
		finalString += this.symbol;
		finalString += ")\nPrice: ";
		finalString += this.lastPrice;
		finalString += "  hi: ";
		finalString += this.highPrice;
		finalString += "  lo: ";
		finalString += this.lowPrice;
		finalString += "  vol: ";
		finalString += this.dayVolume;
		
		finalString += "\nAsk: ";
		if (!sellOrders.isEmpty())
		{
			TradeOrder order = sellOrders.peek();
			
			finalString += order.getPrice();
			finalString += " size: ";
			finalString += order.getShares();
			finalString += "  Bid: ";
		}
		else
		{
			finalString += "none  Bid: ";
		}
		
		if (!buyOrders.isEmpty())
		{
			TradeOrder order = buyOrders.peek();
			
			finalString += order.getPrice();
			finalString += "size: ";
			finalString += order.getShares();
		}
		else
		{
			finalString += "none";
		}
		
		return finalString;
	}
	
	/**
	 * Places a trading order for this stock
	 * @param order a trading order to be placed
	 */
	public void PlaceOrder(TradeOrder order)
	{
		Trader t = order.getTrader();
		
		String msg = "New order:  ";
		
		if (order.isSell())
		{
			msg += "Sell ";
		}
		else
		{
			msg += "Buy ";
		}
		
		msg += this.symbol;
		if (!"".equals(this.companyName))
		{
			msg += "(" + this.companyName + ")\n";
		}
		
		msg += order.getShares() + " shares ";
		
		if (order.isMarket())
		{
			msg += "at market";
		}
		else
		{
			double money = order.getPrice();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyString = formatter.format(money);
			msg += moneyString;
		}
		
		t.receiveMessage(msg);
		
		if (order.isBuy())
		{
			this.buyOrders.add(order);
		}
		else
		{
			this.sellOrders.add(order);
		}
		
		//TODO: call executeOrders ?
	}
	
	
}
