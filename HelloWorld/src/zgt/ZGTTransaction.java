package zgt;

import include.LockMode;
import include.Transaction;
import include.TransactionStatus;
import include.TransactionType;

public class ZGTTransaction extends Transaction{

	public ZGTTransaction(long id, TransactionStatus status, TransactionType type, Thread thread) {
		super(id, status, type, thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean setLock(LockMode lockMode, long objectNumer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void performReadWrite(LockMode lockMode, long objectNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void freeLocks() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeTx() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printTxManager() {
		// TODO Auto-generated method stub
		
	}
}
