/**
 * 
 * @author Nivedita Gautam
 *
 */

package test;

import java.io.FileNotFoundException;

import zgt.ZGTTransactionManager;

/**
 * 
 * The entry point for the ZGT Transaction Manager Application It will create
 * objects and initialize the Transaction Manager, Hash Table, Transaction List,
 * Shared Objects etc.
 */
public class Main {

	public static void main(String[] args) {

		int numberOfSharedObjects = 10;
		int sharedObjectInitialValue = 0;
		String inputFile = "";

		// Start the TM
		ZGTTransactionManager zgtTransactionManager = new ZGTTransactionManager(numberOfSharedObjects,
				sharedObjectInitialValue, inputFile, "");
		
		try {
			
			zgtTransactionManager.startOperation();
		}
		catch (FileNotFoundException fnfe) {
			
			System.out.println("ERROR: Could not find the input file");
		}
	}
}
