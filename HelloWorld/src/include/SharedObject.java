package include;

import java.util.concurrent.Semaphore;

public class SharedObject {

	/**
	 * value of the object
	 * 
	 */
	private int value;

	/**
	 * This mutex should be acquired before accessing the object, It is used to
	 * restrict the number of threads that can access this object (i.e. 1)
	 */
	private Semaphore semaphore = new Semaphore(1);

	/**
	 * Constructor for SharedObject object class, initializes the value and creates
	 * a mutex for the object
	 * 
	 * @param value value to be assigned to the object
	 */
	public SharedObject(int value) {

		this.value = value;

	} 

	/**
	 * @return the value of the object
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param number the number to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the mutex
	 */
	public Semaphore getSemaphore() {
		return semaphore;
	}

}
