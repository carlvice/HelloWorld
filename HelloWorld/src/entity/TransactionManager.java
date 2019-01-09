/**
 * @author Aashish Jha
 */

package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import customType.TransactionStatus;
import customType.TransactionType;
import customType.TxOperationType;
import operation.BeginTxOperation;
import operation.ReadTxOperation;
import zgt.ZGTTransaction;

/**
 * This abstract class declares the attributes and functionalities of the
 * transaction manager. The TM maintains a linked list of the active
 * transactions.
 */
public final class TransactionManager {

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

	public static void initialize(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath) {
		
		TransactionManager.inputFilePath = inputFilePath;

		TransactionManager.sharedObjectList = new ArrayList<>();

		for (int id = 1; id <= sharedObjectListSize; id++) {

			SharedObject sharedObject = new SharedObject(id, sharedObjectInitialValue);
			sharedObjectList.add(sharedObject);
		}
	}

	/**
	 * This method will read the operations from the log file one by one and start a
	 * corresponding thread based on the type of operation. The operation thread
	 * will wait for previous operations of the same tx to finish before starting,
	 * this will prevent illegal schedules.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static void startOperation() throws FileNotFoundException, IOException, InterruptedException {

		File logFile = new File(inputFilePath);

		FileReader fileReader = new FileReader(logFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String lines = "";

		while ((lines = bufferedReader.readLine()).contains("//")) {
			// skip the comments in the input log file
		}

		// read the output file name from the input log file
		TransactionManager.outputFilePath = lines.split("\\s+")[1];

		// read the operations in the input log file
		while ((lines = bufferedReader.readLine()) != null) {

			ZGTTransaction tx;
			SharedObject object;

			String linesArray[] = lines.split("\\s+");

			String opType = linesArray[0];

			// begin tx operation
			if (opType.equalsIgnoreCase("BeginTx")) {

				String txType = linesArray[2];

				// tx type is read-write
				if (txType.equalsIgnoreCase("W") || txType.equalsIgnoreCase("R")) {

					TransactionType transactionType;

					if (txType.equalsIgnoreCase("W")) {

						transactionType = TransactionType.WRITE;
					} else {
						transactionType = TransactionType.READ;
					}

					int txId = Integer.parseInt(linesArray[1]);

					// TODO: check if tx has already begun

					// create a new tx object based on parameters from log file
					tx = new ZGTTransaction(txId, TransactionStatus.ACTIVE, transactionType);

					// add to tx list in tx manager
					tx.addTx();

					BeginTxOperation beginOp = new BeginTxOperation(txId, TxOperationType.BEGIN, 1, transactionType);

					tx.getTxOpList().add(beginOp);

					beginOp.start();

				} else {

					System.out.println("START_OPEARTION_ERROR : Invalid Tx type in Begin operation");
				}

			} else if (opType.equalsIgnoreCase("Read")) {

				int txId = Integer.parseInt(linesArray[1]);

				tx = (ZGTTransaction) getTransactionById(txId);

				object = getSharedObjectById(Integer.parseInt(linesArray[2]));

				ReadTxOperation readOp = new ReadTxOperation(txId, TxOperationType.READ, 2, object);

				TxOperation lastOp = tx.getTxOpList().get(tx.getTxOpList().size() - 1);

				lastOp.join();

				tx.getTxOpList().add(readOp);
				readOp.start();
			}
		}

		bufferedReader.close();

	}

	/**
	 * Prints the current snapshot of the transaction manager
	 */
	public static void printTxManager() {

	}

	/**
	 * Return the object of Transaction from the tx manager from its Id, if it
	 * exists in tx list else returns null
	 * 
	 * @param txId the tx id to find
	 * 
	 * @return tx object based on the tx id
	 * 
	 */
	public static Transaction getTransactionById(int txId) {

		Transaction tx = TransactionManager.firstTx;
		while (tx != null && tx.getId() != txId) {
			tx = tx.next;
		}
		return tx;
	}

	/**
	 * Return the instance of SharedObject based on the Id, if it exists in
	 * SharedObjects list of the transaction manager else returns null
	 * 
	 * @param objId the obj id to find
	 * 
	 * @return SharedObject object based on ObjId
	 */
	public static SharedObject getSharedObjectById(int objId) {

		SharedObject object = sharedObjectList.get(objId - 1);
		return object;
	}

	/**
	 * 
	 * @param line text to print
	 */
	public static void printToOutputLogFile(String line) {

	}

}
