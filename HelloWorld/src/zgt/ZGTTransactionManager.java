package zgt;

import java.io.File;

import include.Transaction;
import include.TransactionManager;

public class ZGTTransactionManager extends TransactionManager{

	@Override
	public void openLog(File logFile) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public int beginTx(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int commitTx(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int abortTx(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int txRead(Transaction transaction, int objectNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int txWrite(Transaction transaction, int objectNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endTxManager(Thread thread) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
