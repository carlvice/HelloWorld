/**
 * 
 * @author Aashish Jha
 *
 */

package entity;

import customType.TxOperationType;

/**
 * This class describes the transaction operation entity. Each statement
 * (operation) in the input file will be converted to a corresponding
 * TxOperation object.
 */

public abstract class TxOperation extends Thread {

	/** The Tx operation sequence number will ensures that the schedule is legal. */
	private int txOpSeqNumber;

	/** The owner is the transaction to whom this operation belongs to. */
	private int ownerTxId;

	/**
	 * Tx operation type specifies the type of operation (Begin, Read, Write, Abort
	 * and Commit)
	 * 
	 * @see TxOperationType
	 */
	private TxOperationType txOpType;

	/**
	 * Shared object is the object on which transaction will be performing its
	 * operations on.
	 */
	private SharedObject sharedObject;

	/**
	 * Operation time is the time spent by the transaction while performing its
	 * operations
	 */
	private int operationTime;

	/**
	 * A simple getter method
	 * 
	 * @return the txOpSeqNumber
	 */
	public int getTxOpSeqNumber() {
		return txOpSeqNumber;
	}

	/**
	 * A simple setter method
	 * 
	 * @param txOpSeqNumber the txOpSeqNumber to set
	 */
	public void setTxOpSeqNumber(int txOpSeqNumber) {
		this.txOpSeqNumber = txOpSeqNumber;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the ownerTx
	 */
	public int getownerTxId() {
		return ownerTxId;
	}

	/**
	 * A simple setter method
	 * 
	 * @param ownerTx the ownerTx to set
	 */
	public void setownerTxId(int ownerTxId) {
		this.ownerTxId = ownerTxId;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the txOpType
	 */
	public TxOperationType getTxOpType() {
		return txOpType;
	}

	/**
	 * A simple setter method
	 * 
	 * @param txOpType the txOpType to set
	 */
	public void setTxOpType(TxOperationType txOpType) {
		this.txOpType = txOpType;
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
	 * @param sharedObject the sharedObject to set
	 */
	public void setSharedObject(SharedObject sharedObject) {
		this.sharedObject = sharedObject;
	}

	/**
	 * A simple getter method
	 * 
	 * @return the operationTime
	 */
	public int getOperationTime() {
		return operationTime;
	}

	/**
	 * A simple setter method
	 * 
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(int operationTime) {
		this.operationTime = operationTime;
	}
	
	

}
