package app;
//import lib.*;

/**
 * A price comparator for trade orders. 
 * @author Abhinav Venigalla
 *
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
  private boolean asc;
  
  /**
   * Constructs a price comparator that compares two orders in ascending order.
   */
  public PriceComparator()
  {
    asc=true;
  }

  /**
   * Constructs a price comparator that compares two orders in ascending or descending order.
   * @param asc if true, make an ascending comparator; otherwise make a descending comparator.
   */
  public PriceComparator(boolean asc)
  {
    this.asc=asc;
  }
  

  /**
   * Compares two trade orders.
   * @param order1 the first TradeOrder
   * @param order2 the second TradeOrder
   */
  public int compare(TradeOrder order1, TradeOrder order2)
  {
    if (order1.isMarket() && order2.isMarket()) {return 0;}
    if (order1.isMarket() && order2.isLimit()) {return -1;}
    if (order1.isLimit() && order2.isMarket()) {return 1;}
    double cents1 = 100*order1.getPrice();
    double cents2 = 100*order2.getPrice();

    if (asc) {return (int)(Math.round(cents1 - cents2));}
    else {return (int)(Math.round(cents2 - cents1));}
  }
  
  /*
   * Test method.
   */
  public static void main(String[] args) {
	  PriceComparator asc = new PriceComparator(true);
	  PriceComparator dsc = new PriceComparator(false);
	  TradeOrder order1 = new TradeOrder(new Trader("Tester1"), "TEST1", true, true, 100, 0);
	  TradeOrder order2 = new TradeOrder(new Trader("Tester2"), "TEST2", false, true, 100, 0);
	  TradeOrder order3 = new TradeOrder(new Trader("Tester3"), "TEST3", false, false, 100, 0.1);
	  TradeOrder order4 = new TradeOrder(new Trader("Tester4"), "TEST4", true, false, 100, 0.2);
	  System.out.println(asc.compare(order1, order2));
	  System.out.println(asc.compare(order2, order3));
	  System.out.println(asc.compare(order3, order4));
	  System.out.println(asc.compare(order1, order3));
	  System.out.println(asc.compare(order2, order4));
	  System.out.println(asc.compare(order1, order4));

	  System.out.println(dsc.compare(order1, order2));
	  System.out.println(dsc.compare(order2, order3));
	  System.out.println(dsc.compare(order3, order4));
	  System.out.println(dsc.compare(order1, order3));
	  System.out.println(dsc.compare(order2, order4));
	  System.out.println(dsc.compare(order1, order4));
	  
  }
  
}
