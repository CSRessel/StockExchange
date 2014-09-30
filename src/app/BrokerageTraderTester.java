package app;

/**
 * Tester class for the brokerage/trader subsystem.
 * @author Allen Zhou
 *
 */
public class BrokerageTraderTester {
	
	public static void main(String[] args) {
		StockExchange exch = new StockExchange();
		Brokerage brkr = new Brokerage(exch);
		brkr.addUser("Trader1", "pw1");
		brkr.addUser("Trader2", "pw2");
		brkr.login("Trader1", "pw1"); //Confirmed wrong password does not work
		brkr.login("Trader2", "pw2");
		//brkr.getQuote("TEST", trader1);
		//Messaging testing done from window
		//Logout functionality to be tested with final program
		
	}

}
