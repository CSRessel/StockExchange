package app;

import lib.*;
import java.util.*;

/**
 * Represents a trader. It can request quotes and place orders with the brokerage, as well as receive and store messages.
 * 
 * @author Julia Xia
 */
public class Trader implements Comparable<Trader>
{

	// Fields
	TraderWindow myWindow;
	Queue<String> mailbox = new LinkedList<String>();
	Brokerage broker;
	String username;
	String password;

	/**
	 * Constructs a new trader, affiliated with a given brokerage, with a given
	 * screen name and password.
	 * 
	 * @param brokerage
	 * @param name
	 * @param pswd
	 */
	public Trader(Brokerage brokerage, String name, String pswd)
	{
		broker = brokerage;
		username = name;
		password = pswd;
	}

	/**
	 * Returns the screen name for this trader.
	 */
	public String getName()
	{
		return username;
	}

	/**
	 * Returns the password for this trader.
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Compares this trader to another by comparing their screen names case
	 * blind.
	 */
	public int compareTo(Trader other)
	{
		return username.toLowerCase().compareTo(other.getName().toLowerCase());
	}

	/**
	 * Indicates whether some other trader is "equal to" this one, based on
	 * comparing their screen names case blind. This method will throw a
	 * ClassCastException if other is not an instance of Trader.
	 */
	public boolean equals(Object other)
	{
		return username.toLowerCase().equals(((Trader) other).getName().toLowerCase());
	}

	/**
	 * Creates a new TraderWindow for this trader and saves a reference to it in
	 * myWindow. Removes and displays all the messages, if any, from this
	 * trader's mailbox by calling myWindow.show(msg) for each message.
	 */
	public void openWindow()
	{
		myWindow = new TraderWindow(this);
		while (hasMessages()) {
			myWindow.showMessage(mailbox.remove());
		}
	}

	/**
	 * Returns true if this trader has any messages in its mailbox.
	 */
	public boolean hasMessages()
	{
		return !mailbox.isEmpty();
	}

	/**
	 * Adds msg to this trader's mailbox and displays all messages. If this
	 * trader is logged in (myWindow is not null) removes and shows all the
	 * messages in the mailbox by calling myWindow.showMessage(msg) for each msg
	 * in the mailbox.
	 * 
	 * @param msg
	 */
	public void receiveMessage(String msg)
	{
		mailbox.add(msg);
		if (myWindow != null)
		{
			while (hasMessages()) {
				myWindow.showMessage(mailbox.remove());
			}
		}
	}

	/**
	 * Requests a quote for a given stock symbol from the brokerage by calling
	 * brokerage's getQuote.
	 * 
	 * @param symbol
	 */
	public void getQuote(String symbol)
	{
		broker.getQuote(symbol, this);
	}

	/**
	 * Places a given order with the brokerage by calling brokerage's
	 * placeOrder.
	 * 
	 * @param order
	 */
	public void placeOrder(TradeOrder order)
	{
		broker.placeOrder(order);
	}

	/**
	 * Logs out this trader. Calls brokerage's logout for this trader. Sets
	 * myWindow to null (this method is called from a TraderWindow's window
	 * listener when the "close window" button is clicked).
	 */
	public void quit()
	{
		broker.logout(this);
		mailbox = null;
	}

}
