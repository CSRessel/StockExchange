package app;

import java.text.NumberFormat;
import java.util.PriorityQueue;

/**
 * Stock represents a stock, and stores this Stock's symbol, company name, price
 * info, and orders
 * 
 * @author Clifford Ressel
 */
public class Stock
{
	private String symbol;
	private String companyName;

	private double startingPrice, lowPrice, highPrice, lastPrice;
	private int dayVolume;

	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;

	/**
	 * Constructor for Stock
	 * 
	 * @param symbol
	 *            short name for the Stock
	 * @param name
	 *            company name
	 * @param price
	 *            starting price
	 */
	public Stock(String symbol, String name, double price)
	{
		this.symbol = symbol;
		this.companyName = name;

		this.startingPrice = price;
		this.lowPrice = price;
		this.highPrice = price;
		this.lastPrice = price;

		this.dayVolume = 0;

		sellOrders = new PriorityQueue<TradeOrder>(1337, new PriceComparator(true));
		buyOrders = new PriorityQueue<TradeOrder>(1337, new PriceComparator(false));
	}

	/**
	 * Returns the starting price
	 * @return double 
	 * 				the startingPrice
	 */
	public double getStartingPrice() {
		return startingPrice;
	}
	
	/**
	 * Creates and returns a quote String for this Stock
	 * 
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
		} else
		{
			finalString += "none  Bid: ";
		}

		if (!buyOrders.isEmpty())
		{
			TradeOrder order = buyOrders.peek();

			finalString += order.getPrice();
			finalString += "size: ";
			finalString += order.getShares();
		} else
		{
			finalString += "none";
		}

		return finalString;
	}

	/**
	 * Places a trading order for this stock
	 * 
	 * @param order
	 *            a trading order to be placed
	 */
	public void placeOrder(TradeOrder order)
	{
		Trader t = order.getTrader();

		String msg = "New order:  ";

		if (order.isSell())
		{
			msg += "Sell ";
		} else
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
		} else
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
		} else
		{
			this.sellOrders.add(order);
		}

		executeOrders();
	}
	
	public void executeOrders()
	{
		while (!buyOrders.isEmpty() && !sellOrders.isEmpty())
		{		
			TradeOrder buy = buyOrders.peek();
			TradeOrder sell = sellOrders.peek();
			
			// if nothing will trade at given prices, just return
			if (buy.isLimit() && sell.isLimit() && buy.getPrice() < sell.getPrice())
				return;
			
			// find what price we are using
			double price;
			if (buy.isLimit() && sell.isLimit())		{ price = sell.getPrice(); }
			else if (buy.isLimit() && sell.isMarket())	{ price = buy.getPrice(); }
			else if (buy.isMarket() && sell.isLimit())	{ price = sell.getPrice(); }
			else										{ price = this.lastPrice; }
			
			// find lowest number of shares
			int shares;
			if (buy.getShares() >= sell.getShares())	{ shares = sell.getShares(); }
			else										{ shares = buy.getShares(); }
			
			// remove relevant shares
			sell.subtractShares(shares);
			buy.subtractShares(shares);
			
			// update this Stock's vars
			this.dayVolume += shares;
			if (price < this.lowPrice)	{ this.lowPrice = price; }
			if (price > this.highPrice)	{ this.highPrice = price; }
			this.lastPrice = price;
			
			// remove the orders if completely fulfilled
			if (sell.getShares() == 0)
				sellOrders.remove();
			if (buy.getShares() == 0)
				buyOrders.remove();
			
			// and finally let's send the message to the Traders
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String priceString = formatter.format(price);
			String totalPriceString = formatter.format((double)(price * shares));
			
			String msgSell = "You bought: " + shares + " at " + priceString + " amt " +  totalPriceString;
			String msgBuy = "You sold: " + shares + " at " + priceString + " amt " +  totalPriceString;
			
			sell.getTrader().receiveMessage(msgSell);
			buy.getTrader().receiveMessage(msgBuy);
		}
	}
}