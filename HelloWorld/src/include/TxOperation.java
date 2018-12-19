/**
 * 
 * @author Aashish Jha
 *
 */

package include;

/**
 * This is transaction operation class. Each statement in the log file will be converted to corresponding operation for the transaction
 * 
 *
 */

public abstract class TxOperation implements Runnable {

	/**
	 * * The Tx operation sequence number will ensures that the schedule is legal.
	 */
	private int txOpSeqNumber;

	private Transaction ownerTx;

	private TxOperationType txOpType;

	private SharedObject sharedObject;

	private int operationTime;

}
