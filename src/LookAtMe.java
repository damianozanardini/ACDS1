/**
 * This class is used to created a shared object that contains an int counter.
 * 
 * @author damiano
 *
 */
public class LookAtMe {

	/**
	 * The internal value.
	 */
	private int value = 0;
	/**
	 * Whether it is necessary to print the value again (used to avoid multiple
	 * prints of the same values if the main thread is activated too often).
	 */
	private boolean print = true;
	
	/**
	 * This method increments the internal value.
	 */
	public synchronized void plusPlus() {
		System.out.println("++");
		value++;
		print = true;
	}
	
	/**
	 * This method prints the current value.
	 */
	public synchronized void ask() {
		if (print) System.out.println("The current value is " + value);
		print = false;
	}

}
