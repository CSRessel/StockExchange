/**
 * Represents a brokerage. 
 * @author Abhinav Venigalla
 */
package app;
import java.util.*;
import lib.*;

public class Brokerage implements Login
{
  private StockExchange exchange;
  private TreeMap<String, Trader> users;
  private TreeSet<Trader> active;

  /**
   * Constructs new brokerage affiliated with a given stock exchange.
   * @param exchange the Stock Exchange
   */
  public Brokerage(StockExchange exchange)
  {
    this.exchange=exchange;
    users = new TreeMap<String, Trader>();
    active = new TreeSet<Trader>();
  }

  /**
   * Tries to register a new trader with a given screen name and password.
   * @param name user name
   * @param password user password
   */
  public int addUser(String name, String password)
  {
    if (name.length()<4 || name.length()>10) return -1;
    if (password.length()<2 || password.length()>10) return -2;
    if (users.containsKey(name)) return -3;
    users.put(name, new Trader(this, name, password));
    return 0;   
  }

  /**
   * Tries to login a trader with a given screen name and password.
   * @param name user name
   * @param password user password
   */
  public int login(String name, String password)
  {
    if (!users.containsKey(name)) return -1;
    if (!users.get(name).getPassword().equals(password)) return -2;
    Trader t = users.get(name);
    if (active.contains(t)) return -3;

    active.add(t);
    t.openWindow();
    if (!t.hasMessages()) t.receiveMessage("Welcome to SafeTrade!");
    return 0;
  }

  /**
   * Removes a specified trader from the set of logged-in traders.
   * @param trader Trader to be removed
   */
  public void logout(Trader trader)
  {
    active.remove(trader);
  }

  /**
   * Requests a quote for a given stock from the stock exachange and passes it along to the trader by calling trader's receiveMessage method
   * @param symbol symbol of stock
   * @param trader trader requesting the quote
   */
  public void getQuote(String symbol, Trader trader)
  {
    trader.receiveMessage(exchange.getQuote(symbol));
  }

  /**
   * Places an order at the stock exchange
   * @param order the order to be placed
   */
  public void placeOrder(TradeOrder order)
  {
    exchange.placeOrder(order);
  }
  
}
