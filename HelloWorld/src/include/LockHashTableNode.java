/**
 * @author Nivedita Gautam
 * 
 */

package include;

/**
 * This class denotes the structure of an entry in the lock hash table.
 */
public class LockHashTableNode {

	/**
	 * <p>
	 * This denotes the mode in which the object is held. This project supports 2
	 * Lock Modes:
	 * </p>
	 * 
	 * <ol>
	 * <li>LT_SHARED</li>
	 * <li>LT_EXCLUSIVE</li>
	 * </ol>
	 * 
	 */
	public LockMode lockMode;

	/**
	 * Object number on which the lock is held
	 */
	public long objectNumber;

	/**
	 * The transaction holding the lock
	 */
	public Transaction transaction;

	/**
	 * Pointer to the next entry in the Lock Hash table
	 */
	public LockHashTableNode next;

}
