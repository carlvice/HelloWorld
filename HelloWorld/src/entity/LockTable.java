/**
 * @author Nivedita Gautam
 * 
 */

package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import customType.LockMode;
import exception.RemoveLockTableEntryException;

/**
 * This interface defines the structure the Lock hash table and the methods that
 * perform operations on it.
 */
public final class LockTable {

	/**
	 * <p>
	 * The Lock Hash Table manages all the lock requests. The entries are stored in
	 * the form of Key, Value pairs as describe below -
	 * </p>
	 * 
	 * <ul>
	 * <li>Key: Integer - SharedObject which is locked,</li>
	 * <li>Value: HashMap ( Integer - Id of the transaction which has lock on the
	 * SharedObject, LockMode - The type of lock )</li>
	 * </ul>
	 */
	public final static HashMap<Integer, HashMap<Integer, LockMode>> LOCK_HASH_TABLE = new HashMap<>();

	/**
	 * This semaphore is used to restrict the number of threads that can access the
	 * lock hash table (i.e. 1). It should be acquired before accessing the lock
	 * Hash Table, and should be released when done.
	 */
	public final static Semaphore LOCK_TABLE_SEMAPHORE = new Semaphore(1);

	/**
	 * Checks if the object is present in the Hash Table; returns NULL if it's not
	 * 
	 * @param sharedObject The object to be searched
	 * 
	 * @return true if an entry is found else returns false
	 * 
	 */

	public static boolean findSharedObject(SharedObject sharedObject) {

		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		boolean found = LOCK_HASH_TABLE.containsKey(sharedObject.getId());

		LOCK_TABLE_SEMAPHORE.release();

		return found;
	}

	/**
	 * Checks if a transaction holds lock on the object
	 * 
	 * @param sharedObject The object to be searched
	 * @param transaction  The transaction to be searched
	 * 
	 * 
	 * @return the lock mode if an entry is found else returns null
	 * 
	 */
	public static LockMode findSharedObject(SharedObject sharedObject, Transaction transaction) {

		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HashMap<Integer, LockMode> valueHashMap = LOCK_HASH_TABLE.get(sharedObject.getId());

		LockMode lockMode = valueHashMap.get(transaction.getId());

		// release semaphore when done.
		LOCK_TABLE_SEMAPHORE.release();

		return lockMode;
	}

	/**
	 * Adds an entry to the Lock Hash Table. If used incorrectly, it will overwrite
	 * the previous entry for the shared object in the hash table.
	 * 
	 * @param transaction  The transaction object who wants to acquire the lock
	 * @param sharedObject The object on which transaction wants to acquire the lock
	 * @param lockMode     The lock mode requested by the transaction i.e. 'S' or
	 *                     'X'
	 * @throws InterruptedException
	 */
	public static void addEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode)
			throws InterruptedException {

		// acquire semaphore before accessing HT
		LOCK_TABLE_SEMAPHORE.acquire();

		HashMap<Integer, LockMode> valueHashMap = LOCK_HASH_TABLE.get(sharedObject.getId());

		valueHashMap.put(transaction.getId(), lockMode);
		LOCK_HASH_TABLE.put(sharedObject.getId(), valueHashMap);

		LOCK_TABLE_SEMAPHORE.release();
	}

	/**
	 * Removes an entry from the Lock Hash Table
	 * 
	 * @param transaction  The transaction object holding the lock
	 * @param sharedObject The object on which transaction holds the lock
	 * @param lockMode     The lock mode held by the transaction i.e. 'S' or 'X'
	 * 
	 * @return true if entry was found and removed, false if entry was not found
	 * 
	 * @throws RemoveLockTableEntryException
	 */
	public static void removeEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode)
			throws RemoveLockTableEntryException {

		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if HT has an entry for the object
		if (LOCK_HASH_TABLE.containsKey(sharedObject.getId())) {

			HashMap<Integer, LockMode> valueHashMap = LOCK_HASH_TABLE.get(sharedObject.getId());

			// if the tx has a lock
			if (valueHashMap.containsKey(transaction.getId())) {

				LockMode lockModeEntry = valueHashMap.get(transaction.getId());

				// if lockmode entry matches the lockmode to remove
				if (lockMode == lockModeEntry) {

					// remove the entry
					valueHashMap.remove(transaction.getId());

					// if the object has no other locks
					if (valueHashMap.size() == 0) {

						// remove its entry from the Hash Table
						LOCK_HASH_TABLE.remove(sharedObject.getId());
					}

					LOCK_TABLE_SEMAPHORE.release();

					System.out.println("REMOVE_ENTRY_SUCCESS: Entry was removed from HT.");

				} else {
					throw new RemoveLockTableEntryException("Entry not found, lock Mode did not match.");
				}
			} else {
				throw new RemoveLockTableEntryException("Entry not found, tx does not hold lock.");
			}
		} else {
			throw new RemoveLockTableEntryException(
					"Entry not found, no lock on shared obj: " + sharedObject.getId() + ".");
		}
	}

	/**
	 * Prints the hash table. Shows all the objects along with the lock mode and
	 * transaction ID. Useful for debugging.
	 * @throws InterruptedException 
	 */
	public static void printHashTable() throws InterruptedException {

		System.out.println("****************** Lock Hash Table ******************");
		System.out.println("Object ID\tTransaction ID\tLock Mode");

		// acquire semaphore before accessing HT
		LOCK_TABLE_SEMAPHORE.acquire();

		Iterator<Entry<Integer, HashMap<Integer, LockMode>>> i = LOCK_HASH_TABLE.entrySet().iterator();
		
		// iterate through the nested HT
		while (i.hasNext()) {

			Map.Entry<Integer, HashMap<Integer, LockMode>> HTEntry = i.next();

			Integer objId = HTEntry.getKey();
			HashMap<Integer, LockMode> valueHashMap = HTEntry.getValue();

			Iterator<Entry<Integer, LockMode>> j = valueHashMap.entrySet().iterator();

			while (j.hasNext()) {

				Map.Entry<Integer, LockMode> valueEntry = j.next();

				Integer txId = valueEntry.getKey();
				LockMode lockMode = valueEntry.getValue();

				System.out.println(objId + "\t" + txId + "\t" + lockMode);
			}
		}

		LOCK_TABLE_SEMAPHORE.release();
		System.out.println("****************** End ******************");
	}

}
