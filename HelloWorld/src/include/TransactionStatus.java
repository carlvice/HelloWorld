/**
 * 
 * @author Nivedita Gautam
 */

package include;

/**
 * This enum denotes the different states of the transaction life-cycle
 */
public enum TransactionStatus {

	/**
	 * This implies that the transaction is currently executing.
	 */
	ACTIVE,

	/**
	 * This state implies that the transaction has been initiated and has
	 * started its execution but is currently suspended. This state arises when a
	 * transaction requests a particular lock on an object and this lock cannot be
	 * granted due to a conflict. In this state, the transaction is blocked on a
	 * semaphore.
	 */
	WAIT,

	/**
	 * This state implies that the transaction has aborted. This arises when a
	 * failure occurs or due to deadlock resolution. In this state, all the
	 * locks held by the transaction are released.
	 */
	ABORT,

	/**
	 * This state denotes that the transaction has successfully completed all of its
	 * operations.
	 */
	END
}
