/***
 * @author Aashish Jha
 *
 */

package include;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import entity.*;

/**
 * This abstract class declares the attributes and functionalities of the
 * transaction manager. The TM maintains a linked list of the active
 * transactions.
 */
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
	 * Path of input file
	 */
	public static String inputFilePath;

	/**
	 * Path of output file
	 * 
	 */
	public static String outputFilePath;

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
	 * This method will open the log file.
	 */
	public abstract void openLogFile();

	/**
	 * This method will close the log file.
	 */
	public abstract void closeLogFile() throws FileNotFoundException;

	/**
	 * This method will read the operations from the log file one by one and start a
	 * corresponding thread based on the type of operation. The operation thread
	 * will wait for previous operations of the same tx to finish before starting,
	 * this will prevent illegal schedules.
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */

	public abstract void startOperation() throws FileNotFoundException, IOException;
	
	/**
	 * This method ensure that all the lines from log file are executed and will
	 * then call closeLogFile() method.
	 */

	public abstract void endOperation();

	/**
	 * Prints the current snapshot of the transaction manager
	 */
	public abstract void printTxManager();

}
