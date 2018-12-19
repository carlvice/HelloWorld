/**
 * 
 * @author Nivedita Gautam
 * 
 */

package zgt;

import Entities.Transaction;
import include.LockMode;
import include.TransactionStatus;
import include.TransactionType;

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
