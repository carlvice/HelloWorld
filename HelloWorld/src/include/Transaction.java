/**
 * @author Nivedita Gautam
 */

package include;

/**
 * The Transaction class is used to represent all transactions in the system.
 * Note - This class only implements the operations associated with locks, i.e.,
 * lock acquisition, lock release, etc.
 */
public abstract class Transaction {

	/** The transaction identifier */
	public long id;

	/** The reference to the transaction's thread */
	public Thread thread;

	/**
	 * The current status of the transaction.
	 * 
	 */
	public TransactionStatus status;

	/** Type of the transaction */
	public TransactionType type;

	/**
	 * The object for which the transaction is currently waiting. This implies that
	 * the transaction is currently blocked.
	 */
	public long objectNumber;

	/** The semaphore number on which the transaction is queued */
	public int semaphoreNumber;

	/**
	 * The lock mode requested for the object on which the transaction is blocked
	 */
	public LockMode lockMode;

	/**
	 * This points to the next transaction object, since all transaction objects are
	 * maintained as a linear list.
	 */
	public Transaction next;

	/** Constructor to initialize the attributes of the Transaction */
	public Transaction(long id, TransactionStatus status, TransactionType type, Thread thread) {

		this.id = id;
		this.status = status;
		this.type = type;
		this.thread = thread;
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
