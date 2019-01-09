/** @author Aashish Jha */

package zgt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import entity.SharedObject;
import entity.Transaction;
import entity.TxOperation;
import include.TransactionManager;
import include.TransactionStatus;
import include.TransactionType;
import include.TxOperationType;
import operation.BeginTxOperation;
import operation.ReadTxOperation;

/** This class implements abstract methods of TransactionManager class */

public class ZGTTransactionManager extends TransactionManager {

	public ZGTTransactionManager(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath,
			String outputFilePath) {

		super(sharedObjectListSize, sharedObjectInitialValue, inputFilePath, outputFilePath);
	}

	@Override
	public void startOperation() throws FileNotFoundException, IOException, InterruptedException {

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

			// begin tx operation
			if (linesArray[0].equalsIgnoreCase("BeginTx")) {

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

			} else if (linesArray[0].equalsIgnoreCase("Read")) {

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

	}

	@Override
	public void endOperation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printTxManager() {
		// TODO Auto-generated method stub

	}

	@Override
	public Transaction getTransactionById(int txId) {

		Transaction tx = TransactionManager.firstTx;
		while (tx != null && tx.getId() != txId) {
			tx = tx.next;
		}
		return tx;
	}

	@Override
	public SharedObject getSharedObjectById(int objId) {

		SharedObject object = sharedObjectList.get(objId - 1);
		return object;
	}

}
