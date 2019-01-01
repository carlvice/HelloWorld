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

public class ZGTTransactionManager extends TransactionManager{

	public ZGTTransactionManager(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath,
			String outputFilePath) {
		super(sharedObjectListSize, sharedObjectInitialValue, inputFilePath, outputFilePath);
		
		
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openLogFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeLogFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOperation() throws FileNotFoundException,IOException, InterruptedException {
		// TODO Auto-generated method stub
		File logFile = new File(inputFilePath);;
		FileReader fileReader = new FileReader(logFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lines="";
		while((lines=bufferedReader.readLine()).contains("//"));
		TransactionManager.outputFilePath=lines.split(" ")[1];
		while((lines=bufferedReader.readLine())!=null)
		{
			ZGTTransaction tx;
			SharedObject object;
			String linesArray[]=lines.split("\\s+");
			if(linesArray[0].equalsIgnoreCase("BeginTx"))
			{
				if(linesArray[2].equalsIgnoreCase("W"))
				{
					tx= new ZGTTransaction(Integer.parseInt(linesArray[1]),TransactionStatus.ACTIVE,TransactionType.WRITE);
					tx.addTx();
					BeginTxOperation beginOp=new BeginTxOperation(tx.getId(), TxOperationType.BEGIN, 1);
					tx.getTxOpList().add(beginOp);
					beginOp.start();
				}
				else if(linesArray[2].equalsIgnoreCase("R"))
				{
					tx = new ZGTTransaction(Integer.parseInt(linesArray[1]),TransactionStatus.ACTIVE,TransactionType.READ);
					tx.addTx();
					BeginTxOperation beginOp=new BeginTxOperation(tx.getId(), TxOperationType.BEGIN, 1);
					tx.getTxOpList().add(beginOp);
					beginOp.start();
				}
				else
				{
					System.out.println("START_OPEARTION_ERROR : Invalid Tx type");
				}
				
			}
			else if(linesArray[0].equalsIgnoreCase("Read"))
			{
				tx=(ZGTTransaction) getTxFromId(Integer.parseInt(linesArray[1]));
				object=getObjectFromId(Integer.parseInt(linesArray[2]));
				ReadTxOperation readOp=new ReadTxOperation(tx.getId(), TxOperationType.READ, 2, object);
				TxOperation lastOp=tx.getTxOpList().get(tx.getTxOpList().size()-1);
				lastOp.join();
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
	public Transaction getTxFromId(int txId) {
		// TODO Auto-generated method stub
		Transaction tx=TransactionManager.firstTx;
		while(tx!=null&&tx.getId()!=txId)
		{
			tx=tx.next;
		}
		return tx;
	}

	@Override
	public SharedObject getObjectFromId(int objId) {
		// TODO Auto-generated method stub
		SharedObject object=sharedObjectList.get(objId-1);
		return object;
	}

	
}
