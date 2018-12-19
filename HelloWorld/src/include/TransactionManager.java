/***
 * @author Aashish Jha
 *
 */

package include;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionManager {

	/**
	 * The first transaction object in the transaction table
	 */
	private static Transaction firstTx;

	/**
	 * The last transaction object in the transaction table
	 */
	private static Transaction lastTx;
	
	
	/**
	 * 
	 * 
	 */
	private static String inputFilePath;

	/**
	 * 
	 * 
	 */
	private static String outputFilePath;

	public static List<SharedObject> sharedObjectList;

	public TransactionManager(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath,
			String outputFilePath) {
		// TODO Auto-generated constructor stub

		TransactionManager.inputFilePath = inputFilePath;
		TransactionManager.outputFilePath = outputFilePath;

		TransactionManager.sharedObjectList = new ArrayList<>();

		for (int id = 1; id <= sharedObjectListSize; id++) {

			SharedObject sharedObject = new SharedObject(id, sharedObjectInitialValue);
			sharedObjectList.add(sharedObject);
		}
	}

	/**
	 * This method will open log file which will passed as argument
	 * 
	 * @param logFile
	 * 
	 */
	public abstract void openLogFile();

	/**
	 * This method will open log file which will passed as argument
	 * 
	 * @param logFile
	 * 
	 */
	public abstract void closeLogFile();

	/**
	 * This method will read the operations from the log file one by one and start a
	 * corresponding thread based on the type of operation. The operation thread
	 * will wait for previous operations of the same tx to finish before starting,
	 * this will prevent illegal schedules.
	 */
	public abstract void startOperation();

	public abstract void endOperation();

	/**
	 * Prints the current state of the transaction manager
	 */
	public abstract void printTxManager();

}
