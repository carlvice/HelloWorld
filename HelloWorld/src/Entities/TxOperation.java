/**
 * 
 * @author Aashish Jha
 *
 */

package Entities;

import include.TxOperationType;

/**
 * This is transaction operation class. Each statement in the log file will be
 * converted to corresponding TxOperation object for the transaction.
 */

public abstract class TxOperation implements Runnable {

	/** The Tx operation sequence number will ensures that the schedule is legal. */
	private int txOpSeqNumber;
	
	/** The Tx owner is the transaction whose corresponding TxOperation object is created . */
	private Transaction ownerTx;

	/** Tx operation type specify the ype of transaction's operation (Begin,Read,Write, Abort and Commit)  */
	private TxOperationType txOpType;

	/** Shared object is the object on which transaction will be performing its operations on. */
	private SharedObject sharedObject;
	
	/** Operation time is the time spent by the transaction while performing its operations */ 
	private int operationTime;

}
