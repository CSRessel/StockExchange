package app;
import lib.*;

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
    if (order1.isMarket() && order2.isMarket()) return 0;
    if (order1.isMarket() && order2.isLimit()) return -1;
    if (order1.isLimit() && order2.isMarket()) return 1;
    if (asc) return (int)(100*(order1.getPrice()-order2.getPrice()) + .5);
    else return (int)(100*(order1.getPrice()-order2.getPrice()) + .5);
  }
}
