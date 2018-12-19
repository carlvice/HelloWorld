/**
 * @author Nivedita Gautam
 *
 */

package Entities;

import java.util.concurrent.Semaphore;

public class SharedObject {
	
	/**
	 * A unique identifier for the shared object
	 */
	private int id;
	
	/**
	 * value of the object
	 * 
	 */
	private int value;

	/**
	 * This semaphore should be acquired before accessing the object, It is used to
	 * restrict the number of threads that can access this object (i.e. 1)
	 */
	private Semaphore semaphore = new Semaphore(1);

	/**
	 * Constructor for SharedObject object class, initializes the value and creates
	 * a semaphore for the object
	 * 
	 * @param value value to be assigned to the object
	 */
	public SharedObject(int id, int value) {

		this.id = id;
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
	 * @return the semaphore
	 */
	public Semaphore getSemaphore() {
		return this.semaphore;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

}
