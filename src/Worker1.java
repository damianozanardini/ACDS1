import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a worker of class 1.
 * 
 * @author damiano
 *
 */
public class Worker1 extends Thread {

	/**
	 * Reference to the shared counter.
	 */
	private LookAtMe lookAtMe;
	/**
	 * Reference to the shared lock.
	 */
	private ReentrantLock lock;
	/**
	 * Reference to the signal object that is shared between a worker of class
	 * 1 and its direct child (a worker of class 2). 
	 */
	private Object signal;
	
	/**
	 * Upon creation, a worker of class 1 is passed the shared objects.
	 * 
	 * @param lookAtMeRef The reference to the shared counter.
	 * @param lockRef The reference to the shared lock.
	 */
	public Worker1(LookAtMe lookAtMeRef, ReentrantLock lockRef) {
		System.out.println("Worker1 created");
		lookAtMe = lookAtMeRef;
		lock = lockRef;
		signal = new Object();
	}
	
	/**
	 * Each worker of class 1 creates and run a worker of class 2
	 */
	public void run() {
		lock.lock();
		lookAtMe.plusPlus();
		Worker2 child = new Worker2(lookAtMe,lock,signal);
		child.start();
		synchronized(signal) {
			try {
				signal.wait();
			} catch(InterruptedException e) { }
		}
		lock.unlock();
	}
	
}
