/**
 * @author Nivedita Gautam
 */
package zgt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import entity.SharedObject;
import entity.Transaction;
import include.*;

/** This class implements the abstract methods of the LockTable class. */
public class ZGTLockTable implements LockTable {

	@Override
	public boolean findSharedObject(SharedObject sharedObject) {
		
		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean found = LOCK_HASH_TABLE.containsKey(sharedObject.getId());
		
		LOCK_TABLE_SEMAPHORE.release();
		
		return found;
	}

	@Override
	public LockMode findSharedObject(SharedObject sharedObject, Transaction transaction) {

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

	@Override
	public void addEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		
		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<Integer, LockMode> valueHashMap = LOCK_HASH_TABLE.get(sharedObject.getId());

		valueHashMap.put(transaction.getId(), lockMode);
		LOCK_HASH_TABLE.put(sharedObject.getId(), valueHashMap);
		
		LOCK_TABLE_SEMAPHORE.release();
	}

	@Override
	public boolean removeEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		
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
					return true;

				} else {

					System.out.println("REMOVE_ENTRY_ERROR: Entry not found, lock Mode did not match.");
					return false;
				}
			} else {

				System.out.println("REMOVE_ENTRY_ERROR: Entry not found, tx does not hold lock.");
				return false;
			}
		} else {

			System.out.println(
					"REMOVE_ENTRY_ERROR: Entry not found, no lock on shared obj: " + sharedObject.getId() + ".");
			return false;
		}
	}

	@Override
	public void printHashTable() {

		System.out.println("****************** Lock Hash Table ******************");
		System.out.println("Object ID\tTransaction ID\tLock Mode");
		
		try {
			// acquire semaphore before accessing HT
			LOCK_TABLE_SEMAPHORE.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Entry<Integer, HashMap<Integer, LockMode>>> i = LOCK_HASH_TABLE.entrySet().iterator();

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
