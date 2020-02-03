import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a worker of class 2.
 * 
 * @author damiano
 *
 */public class Worker2 extends Thread {

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
	 * 2 and its parent (a worker of class 1). 
	 */
	private Object signal;
	
	/**
	 * Upon creation, a worker of class 2 takes the shared objects.
	 * 
	 * @param lookAtMeRef
	 * @param lockRef
	 * @param signalRef
	 */
	public Worker2(LookAtMe lookAtMeRef, ReentrantLock lockRef, Object signalRef) {
		System.out.println("Worker2 created");
		lookAtMe = lookAtMeRef;
		lock = lockRef;
		signal = signalRef;
	}
	
	/**
	 * A worker of class 2 creates a new worker of class 1, and unblocks its
	 * parent (another worker of class 1), so that the latter can release the
	 * lock.
	 */
	public void run() {
		lookAtMe.plusPlus();
		Worker1 child = new Worker1(lookAtMe,lock);
		child.start();
		synchronized(signal) {
			signal.notify();
		}
		
	}
	
}
