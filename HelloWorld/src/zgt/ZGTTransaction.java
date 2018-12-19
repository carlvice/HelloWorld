/**
 * 
 * @author Nivedita Gautam
 * 
 */

package zgt;

import entity.Transaction;
import include.LockMode;
import include.TransactionStatus;
import include.TransactionType;

/** This class implements abstract methods of Transaction class */

public class ZGTTransaction extends Transaction {

	public ZGTTransaction(long id, TransactionStatus status, TransactionType type) {
		super(id, status, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void freeLocks() {
		// TODO Auto-generated method stub

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
	public boolean removeTx() {
		// TODO Auto-generated method stub
		return false;
	}

}
