/**
 * 
 * @author Nivedita Gautam
 *
 */

package test;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		String inputFile = "Test.txt";

		// Start the TM
		ZGTTransactionManager zgtTransactionManager = new ZGTTransactionManager(numberOfSharedObjects,
				sharedObjectInitialValue, inputFile, "");
		
		// Start the transaction
		try {
			
			zgtTransactionManager.startOperation();
		}
		catch (FileNotFoundException fnfe) {
			
			System.out.println("MAIN_CLASS_ERROR: Could not find the input file");
		}
		catch (IOException ioe) {
			// TODO: handle exception
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
