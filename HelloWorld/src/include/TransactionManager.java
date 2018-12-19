/***
 * @author Aashish Jha
 *
 */

package include;

import java.io.File;

public interface TransactionManager {

	/** 
	 * This method will open log file which will passed as argument 
	 * @param logFile
	 *  
	 * */
	public void openLog(File logFile);

	/**
	 * This method will start the transaction whose id and type are passed as
	 * arguments
	 * @param transaction
	 * 
	 * @return
	 */
	public int beginTx(Transaction transaction);

	/** This method will commit the transaction whose id is passed as arguments 
	 * 
	 * @param transaction
	 * 
	 * @return
	 * 
	 * */
	public int commitTx(Transaction transaction);
	

	/** This method will abort the transaction whose id is passed as arguments 
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * */
	public int abortTx(Transaction transaction);
	

	/**
	 * This method will perform the read operation for transaction whose id is
	 * passed as arguments on object whose number is passed as argument
	 * 
	 * @param transaction
	 * @param objectNumber
	 * 
	 * @return
	 */
	public int txRead(Transaction transaction, int objectNumber);

	/**
	 * This method will perform the write operation for transaction whose id is
	 * passed as arguments on object whose number is passed as argument
	 * 
	 * @param transaction
	 * @param objectNumber
	 * 
	 * @return
	 */
	public int txWrite(Transaction transaction, int objectNumber);

	// Don't know if this required or not
	/**
	 * Lorem Ipsum
	 * 
	 * @param thread
	 * 
	 * @return
	 * 
	 * */
	public int endTxManager (Thread thread);

}
