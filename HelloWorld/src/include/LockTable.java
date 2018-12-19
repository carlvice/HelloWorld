/**
 * @author Nivedita Gautam
 * 
 */

package include;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import Entities.SharedObject;
import Entities.Transaction;

public interface LockTable {

	/**
	 * <p>
	 * The Lock Hash Table manages all the lock requests. The entries are stored in
	 * the form of Key, Value pairs as describe below -
	 * </p>
	 * 
	 * <ul>
	 * <li>Key: <Integer> - SharedObject which is locked,</li>
	 * <li>Value: HashMap < Integer - Id of the transaction which has lock on the
	 * SharedObject, LockMode - The type of lock ></li>
	 * </ul>
	 */
	public final static HashMap<Integer, HashMap<Integer, LockMode>> LOCK_HASH_TABLE = new HashMap<>();

	/**
	 * This mutex is used to restrict the number of threads that can access the lock
	 * hash table (i.e. 1). It should be acquired before accessing the lock Hash
	 * Table, and should be released when done.
	 */
	public final static Semaphore LOCK_TABLE_SEMAPHORE = new Semaphore(1);

	/**
	 * Checks if the object is present in the Hash Table; returns NULL if it's not
	 * 
	 * @param objectNumber The object to be searched
	 * 
	 * @return true if an entry is found else returns false
	 * 
	 */
	public boolean findSharedObject(SharedObject sharedObject);

	/**
	 * Checks if a transaction holds lock on the object
	 * 
	 * @param objectNumber The object to be searched
	 * @param transaction  The transaction to be searched
	 * 
	 * 
	 * @return true if an entry is found else returns false
	 * 
	 */
	public boolean findSharedObject(SharedObject sharedObject, Transaction transaction);

	/**
	 * Adds an entry to the Lock Hash Table
	 * 
	 * @param transaction  The transaction object who wants to acquire the lock
	 * @param objectNumber The object on which transaction wants to acquire the lock
	 * @param lockMode     The lock mode requested by the transaction i.e. 'S' or
	 *                     'X'
	 */
	public void addEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode);

	/**
	 * Removes an entry from the Lock Hash Table
	 * 
	 * @param transaction  The transaction object holding the lock
	 * @param objectNumber The object on which transaction holds the lock
	 * @param lockMode     The lock mode held by the transaction i.e. 'S' or 'X'
	 */
	public void removeEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode);

	/**
	 * Prints the hash table. Shows all the objects along with the lock mode and
	 * transaction ID. Useful for debugging.
	 */
	public void printHashTable();

}
