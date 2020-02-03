import java.util.concurrent.locks.ReentrantLock;

public class Task1 {
	/**
	 * This object is shared by all threads: it contains a counter.
	 */
	public static LookAtMe lookAtMe;
	
	/**
	 * This lock is shared by all threads.
	 */
	public static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Main method: it creates and starts a worker of class 1.
	 * Afterwards, it tries to get the value of the shared counter.
	 * 
	 * @param args
	 */
	public static void main(String args []) {
		lookAtMe = new LookAtMe();
		
		Worker1 w = new Worker1(lookAtMe,lock);
		w.start();
		
		while(true) {
			lock.lock();
			lookAtMe.ask();
			lock.unlock();
		}
	}
}
