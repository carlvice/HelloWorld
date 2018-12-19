/**
 * @author Nivedita Gautam
 */

package entity;

import java.util.ArrayList;
import java.util.List;

import include.LockMode;
import include.TransactionStatus;
import include.TransactionType;

/**
 * The Transaction class is used to represent all transactions in the system.
 * Note - This class only implements the operations associated with locks, i.e.,
 * lock acquisition, lock release, etc.
 */
public abstract class Transaction {

	/** The transaction identifier */
	private long id;

	/** The current status of the transaction. */
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
	 * A pointer to the next transaction object, since all transaction objects are
	 * maintained as a linear list.
	 */
	public Transaction next;

	/**
	 * List of the operations executed by the transactions, will be used to ensure
	 * that the operation threads for a particular transaction are executed in order
	 * and do not result in an illegal schedule.
	 */
	private List<TxOperation> txOpList;

	/**
	 * A simple getter method
	 * 
	 * @return the transaction id
	 */
	public long getId() {
		return id;
	}

	/**
	 * A simple setter method
	 * 
	 * @param id the transaction id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the tx status
	 */
	public TransactionStatus getStatus() {
		return status;
	}

	/**
	 * A simple setter method
	 * 
	 * @param status the tx status to set
	 */
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	/**
	 * A simple setter method
	 * 
	 * @return the tx type
	 */
	public TransactionType getType() {
		return type;
	}

	/**
	 * A simple setter method
	 * 
	 * @param type the tx type to set
	 */
	public void setType(TransactionType type) {
		this.type = type;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the sharedObject
	 */
	public SharedObject getSharedObject() {
		return sharedObject;
	}

	/**
	 * A simple setter method
	 * 
	 * @param sharedObject the sharedObject to set for the tx
	 */
	public void setSharedObject(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the semaphoreNumber for the tx
	 */
	public int getSemaphoreNumber() {
		return semaphoreNumber;
	}

	/**
	 * A simple setter method
	 * 
	 * @param semaphoreNumber the semaphoreNumber to set
	 */
	public void setSemaphoreNumber(int semaphoreNumber) {
		this.semaphoreNumber = semaphoreNumber;
	}

	/**
	 * A simple getter method
	 *
	 * @return the lockMode
	 */
	public LockMode getLockMode() {
		return lockMode;
	}

	/**
	 * A simple setter method
	 * 
	 * @param lockMode the lockMode to set
	 */
	public void setLockMode(LockMode lockMode) {
		this.lockMode = lockMode;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the next
	 */
	public Transaction getNext() {
		return next;
	}

	/**
	 * A simple setter method
	 * 
	 * @param next the next to set
	 */
	public void setNext(Transaction next) {
		this.next = next;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the txOpList
	 */
	public List<TxOperation> getTxOpList() {
		return txOpList;
	}

	/**
	 * A simple setter method
	 * 
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
	 * This method removes the tx from the list if it does not holds any locks, else
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
	 * @return true if the lock can be acquired else returns false
	 */
	public abstract boolean setLock(LockMode lockMode, long objectNumer);

	/**
	 * This method frees all the locks held by the transaction. Any other
	 * transaction which is blocked because it wants to access the object is
	 * unblocked and allowed to proceed.
	 */
	public abstract void freeLocks();

	/**
	 * This method performs the actual read and write operations after the lock is
	 * acquired based on the lock mode.
	 * 
	 * @param lockMode
	 * @param objectNumber
	 */

	public abstract void performReadWrite(LockMode lockMode, long objectNumber);

}
