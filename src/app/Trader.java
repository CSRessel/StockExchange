package app;

/**
 * Stub class for Trader
 * @author Allen Zhou
 *
 */
public class Trader {
	private String name;

	public Trader(String name) {
		this.name = name;
	}
	
	public void receiveMessage(String msg) {
		System.out.println(name + "\n" + msg);
	}
}
