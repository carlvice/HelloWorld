/**
 * @author Aashish Jha
 */

package include;

import java.io.File;


public class Globals {

	/**
	 * Pointer to the head of the Hash table
	 */
	public static LockHashTable lockHashTableHead;

	/**
	 * The first transaction object in the transaction table
	 */
	public static Transaction firstTx;

	/**
	 * The last transaction object in the transaction table
	 */
	public static Transaction lastTx;

	/**
	 * Every line of a transaction waits on that value of count to match
	 * conditionSet. conditionSet is initialized to 0 and decremented every time an
	 * operation of a transaction finishes.
	 */
	int conditionSet[] = new int[Constants.MAX_TRANSACTIONS];

	/**
	 * objectArray is array of objects present for transactions to acquire lock on
	 */
	Object objectArray = new Object[Constants.MAX_ITEMS];

	/** operationTime is array of operation times for each transaction. */
	int operationTime[] = new int[Constants.MAX_TRANSACTIONS];

	// Not yet know where and how to use these
	// Thread mutexPool[]=new Thread[Constants.MAX_TRANSACTIONS];
	// Thread conditionPool[]=new Thread[Constants.MAX_TRANSACTIONS];
	
	/** log file keep records of the transactions in the system */
	File logFile = new File("logFile");

	/**
	 * These are sequence numbers for opeartions of each transactions . Every line
	 * of a transaction is given a different number using this array.
	 */
	int sequenceNumbers[] = new int[Constants.MAX_TRANSACTIONS];

	// don't know what it will be used for
	// Thread threadId[]=new Thread[Constants.NTHREADS];
	
	
	
	
	// mutex for hT p0 , v0
	
	
	// mutex 
}
