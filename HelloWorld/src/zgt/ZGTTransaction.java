/**
 * 
 * @author Nivedita Gautam
 * 
 */

package zgt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import entity.SharedObject;
import entity.Transaction;
import include.LockMode;
import include.LockTable;
import include.TransactionManager;
import include.TransactionStatus;
import include.TransactionType;

/** This class implements abstract methods of Transaction class */

public class ZGTTransaction extends Transaction {

	public ZGTTransaction(int id, TransactionStatus status, TransactionType type) {
		super(id, status, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTx() {

		// this is the first tx
		if (TransactionManager.firstTx == null) {

			// set first and last tx as current tx since it's the only tx.
			TransactionManager.firstTx = this;
			TransactionManager.lastTx = this;

			// update pointers
			TransactionManager.firstTx.next = null;
			TransactionManager.lastTx.next = null;

		} // not first tx
		else {

			// add this tx to the list
			TransactionManager.lastTx.next = this;
			this.next = null;
			// set current tx as the last tx
			TransactionManager.lastTx = this;
		}
	}

	@Override
	public boolean removeTx() {

		// check if the tx holds any locks
		
		boolean holdsLock = false;

		Iterator<Entry<Integer, HashMap<Integer, LockMode>>> i = LockTable.LOCK_HASH_TABLE.entrySet().iterator();

		while (i.hasNext()) {

			Map.Entry<Integer, HashMap<Integer, LockMode>> HTEntry = i.next();

			Integer objId = HTEntry.getKey();
			HashMap<Integer, LockMode> valueHashMap = HTEntry.getValue();

			Iterator<Entry<Integer, LockMode>> j = valueHashMap.entrySet().iterator();

			while (j.hasNext()) {

				Map.Entry<Integer, LockMode> valueEntry = j.next();

				Integer txId = valueEntry.getKey();
				
				if (txId == this.getId()) {
					
					holdsLock = true;
					break;
				}
			}
		}
		
		// if the tx does not holds lock
		if (!holdsLock) {
			
			Transaction ptr = TransactionManager.firstTx;
			
			while (ptr.next != null) {
			
				
			}
			
			
			
			
		}

		return false;
	}

	@Override
	public boolean setLock(LockMode lockMode, long objectNumer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void freeLocks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void performReadWrite(LockMode lockMode, SharedObject sharedObject) {
		// TODO Auto-generated method stub

	}

}
