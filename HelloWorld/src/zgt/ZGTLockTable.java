package zgt;

import Entities.SharedObject;
import Entities.Transaction;
import include.*;

/**
 * 
 * This class implements the abstract methods of the LockTable class
 *
 */
public class ZGTLockTable implements LockTable{

	@Override
	public boolean findSharedObject(SharedObject sharedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findSharedObject(SharedObject sharedObject, Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printHashTable() {
		// TODO Auto-generated method stub
		
	}
	
}
