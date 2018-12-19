/**
 * @author Nivedita Gautam
 */

package include;

import java.util.ArrayList;
import java.util.List;

/**
 * The Transaction class is used to represent all transactions in the system.
 * Note - This class only implements the operations associated with locks, i.e.,
 * lock acquisition, lock release, etc.
 */
public abstract class Transaction {

	/** The transaction identifier */
	private long id;

	/**
	 * The current status of the transaction.
	 * 
	 */
	private TransactionStatus status;

	/** Type of the transaction */
	private TransactionType type;

	/**
	 * The object for which the transaction is currently waiting. This implies that
	 * the transaction is currently blocked.
	 */
	private SharedObject sharedObject;

	/**
	 * If the value is null, it means no other tx is waiting for the current tx.
	 * Else if the value is equal to the current tx ID, some other tx(s) is waiting
	 * on the current tx
	 */
	private int semaphoreNumber;

	/**
	 * The lock mode requested for the object on which the transaction is blocked
	 */
	private LockMode lockMode;

	/**
	 * This points to the next transaction object, since all transaction objects are
	 * maintained as a linear list.
	 */
	public Transaction next;
	
	
	private List <TxOperation> txOpList;

	/**
	 * @return the transaction id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the transaction id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tx status
	 */
	public TransactionStatus getStatus() {
		return status;
	}

	/**
	 * @param status the tx status to set
	 */
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	/**
	 * @return the tx type
	 */
	public TransactionType getType() {
		return type;
	}

	/**
	 * @param type the tx type to set
	 */
	public void setType(TransactionType type) {
		this.type = type;
	}

	/**
	 * @return the sharedObject
	 */
	public SharedObject getSharedObject() {
		return sharedObject;
	}

	/**
	 * @param sharedObject the sharedObject to set for the tx
	 */
	public void setSharedObject(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
	}

	/**
	 * @return the semaphoreNumber for the tx
	 */
	public int getSemaphoreNumber() {
		return semaphoreNumber;
	}

	/**
	 * @param semaphoreNumber the semaphoreNumber to set
	 */
	public void setSemaphoreNumber(int semaphoreNumber) {
		this.semaphoreNumber = semaphoreNumber;
	}

	/**
	 * @return the lockMode
	 */
	public LockMode getLockMode() {
		return lockMode;
	}

	/**
	 * @param lockMode the lockMode to set
	 */
	public void setLockMode(LockMode lockMode) {
		this.lockMode = lockMode;
	}

	/**
	 * @return the next
	 */
	public Transaction getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Transaction next) {
		this.next = next;
	}

	/**
	 * @return the txOpList
	 */
	public List<TxOperation> getTxOpList() {
		return txOpList;
	}

	/**
	 * @param txOpList the txOpList to set
	 */
	public void setTxOpList(List<TxOperation> txOpList) {
		this.txOpList = txOpList;
	}

	/** Constructor to initialize the attributes of the Transaction */
	public Transaction(long id, TransactionStatus status, TransactionType type) {

		this.id = id;
		this.status = status;
		this.type = type;
		this.txOpList = new ArrayList<>();
	}

	/**
	 * This method frees all locks held by the transaction. While traversing
	 * sequentially through the locks held by the transaction, any transaction which
	 * is blocked because it needs the just released object is unblocked and allowed
	 * to proceed.
	 */
	public abstract void freeLocks();

	/**
	 * This method removes the tx from the list IF it does not holds any locks, else
	 * it doesn't
	 * 
	 * @return true if the tx was removed successfully, else returns false, which
	 *         means that the tx holds some locks and cannot be removed from the
	 *         list
	 */
	public abstract boolean removeTx();

	/**
	 * This method attempts to acquire the specified lock on the given object for
	 * the current tx
	 * 
	 * @param lockMode    lock mode requested by the tx
	 * @param objectNumer object on which the lock is requested
	 * 
	 * @return true if lock is acquired else returns false
	 */
	public abstract boolean setLock(LockMode lockMode, long objectNumer);

	public abstract void performReadWrite(LockMode lockMode, long objectNumber);

	/** */
	public abstract void printTxManager();

}