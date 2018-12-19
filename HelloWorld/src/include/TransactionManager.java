/***
 * @author Aashish Jha
 *
 */

package include;

import java.io.File;

public abstract class TransactionManager {
	
	
	/**
	 * The first transaction object in the transaction table
	 */
	public static Transaction firstTx;

	/**
	 * The last transaction object in the transaction table
	 */
	public static Transaction lastTx;

	/** 
	 * This method will open log file which will passed as argument 
	 * @param logFile
	 *  
	 * */
	public abstract void openLog(File logFile);

	/**
	 * This method will start the transaction whose id and type are passed as
	 * arguments
	 * @param transaction
	 * 
	 * @return
	 */
	public abstract int beginTx(Transaction transaction);

	/** This method will commit the transaction whose id is passed as arguments 
	 * 
	 * @param transaction
	 * 
	 * @return
	 * 
	 * */
	public abstract int commitTx(Transaction transaction);
	

	/** This method will abort the transaction whose id is passed as arguments 
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * */
	public abstract int abortTx(Transaction transaction);
	

	/**
	 * This method will perform the read operation for transaction whose id is
	 * passed as arguments on object whose number is passed as argument
	 * 
	 * @param transaction
	 * @param objectNumber
	 * 
	 * @return
	 */
	public abstract int txRead(Transaction transaction, int objectNumber);

	/**
	 * This method will perform the write operation for transaction whose id is
	 * passed as arguments on object whose number is passed as argument
	 * 
	 * @param transaction
	 * @param objectNumber
	 * 
	 * @return
	 */
	public abstract int txWrite(Transaction transaction, int objectNumber);

	// Don't know if this required or not
	/**
	 * Lorem Ipsum
	 * 
	 * @param thread
	 * 
	 * @return
	 * 
	 * */
	public abstract int endTxManager (Thread thread);

}
