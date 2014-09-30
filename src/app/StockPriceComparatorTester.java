package app;

/**
 * Tester class for the Stock/PriceComparator subsystem
 * @author Allen Zhou
 *
 */
public class StockPriceComparatorTester {
	
	public static void main(String[] args) {
		Trader t1 = new Trader("Trader1");
		Trader t2 = new Trader("Trader2");
		PriceComparator asc = new PriceComparator(true);
		PriceComparator dsc = new PriceComparator(false);
		TradeOrder order1 = new TradeOrder(t1, "TEST1", true, true, 100, 0);
		TradeOrder order2 = new TradeOrder(t2, "TEST2", false, true, 100, 0);
		TradeOrder order3 = new TradeOrder(t1, "TEST1", false, false, 100, 0.1);
		TradeOrder order4 = new TradeOrder(t2, "TEST2", true, false, 100, 0.2);
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
		Stock test1 = new Stock("TEST1", "Stock1", 0.5);
		Stock test2 = new Stock("TEST2", "Stock2", 0.5);
		//Stock test3 = new Stock("TEST3", "Stock3", 0.5);
		System.out.println(test1.getQuote());
		test1.placeOrder(order3);
		test2.placeOrder(order4);
		System.out.println(test2.getQuote());
	}

}
