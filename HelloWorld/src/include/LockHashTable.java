/**
 * @author Nivedita Gautam
 * 
 */

package include;

public interface LockHashTable {

	/**
	 * Checks if the object is present in the Hash Table; returns NULL if it's not
	 * 
	 * @param objectNumber The object to be searched
	 * 
	 * @return 
	 * 
	 */
	public LockHashTableNode findObject(long objectNumber);

	/**
	 * Checks if a transaction holds lock on the object; returns NULL if it doesn't
	 * 
	 * @param objectNumber The object to be searched
	 * @param transaction  The transaction to be searched
	 * 
	 */
	public LockHashTableNode findObject(long objectNumber, Transaction transaction);

	/**
	 * Adds an entry for a lock to the Lock Hash Table
	 * 
	 * @param transaction  The transaction object who wants to acquire the lock
	 * @param objectNumber The object on which tx wants to acquire the lock
	 * @param lockMode     The lock mode requested by the transaction i.e. 'S' or
	 *                     'X'
	 */
	public boolean add(Transaction transaction, long objectNumber, LockMode lockMode);

	/**
	 * Removes an entry from the Lock Hash Table
	 * 
	 * @param transaction  The transaction object holding the lock
	 * @param objectNumber The object on which tx holds the lock
	 * @param lockMode     The lock mode held by the transaction i.e. 'S' or 'X'
	 */
	public boolean remove(Transaction transaction, long objectNumber, LockMode lockMode);

	/**
	 * Prints the hash table. Shows all the elements along with the lock mode etc.
	 * Useful for debugging.
	 */
	public void printHashTable();

}
