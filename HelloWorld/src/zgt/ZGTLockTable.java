package zgt;

import include.*;

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
	public boolean addEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEntry(Transaction transaction, SharedObject sharedObject, LockMode lockMode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printHashTable() {
		// TODO Auto-generated method stub
		
	}
}
