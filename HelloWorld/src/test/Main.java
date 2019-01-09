/**
 * 
 * @author Nivedita Gautam
 *
 */

package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import entity.TransactionManager;

/**
 * 
 * The entry point for the ZGT Transaction Manager Application It will create
 * objects and initialize the Transaction Manager, Hash Table, Transaction List,
 * Shared Objects etc.
 */
public class Main {

	public static void main(String[] args) {

	
		// initialize the shared objects
		TransactionManager.initialize(Constants.NUMBER_OF_SHARED_OBJECTS, Constants.SHARED_OBJECTS_INITIAL_VALUE,
				Constants.INPUT_FILE);

		// read input file and Start the transactions
		try {

			TransactionManager.startOperation();
			
		} catch (FileNotFoundException fnfe) {

			System.out.println("MAIN_CLASS_ERROR: Could not find the input file");
			
		} catch (IOException ioe) {
			
			System.out.println("MAIN_CLASS_ERROR: Could not open the input file");
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
