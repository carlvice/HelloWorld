/**
 *  @author Aashish Jha
 */

package operation;

import customType.TxOperationType;
import entity.SharedObject;
import entity.TxOperation;

/**
 * This class is ReadTx Operation thread. This thread will perform the read
 * operation for a transaction.
 */

public class ReadTxOperation extends TxOperation {

	 public ReadTxOperation(int ownerTx, TxOperationType txOpType, int txOpSeqNumber,SharedObject sharedObject) {
		// TODO Auto-generated constructor stub
		setownerTxId(ownerTx);
		setTxOpType(txOpType);
		setTxOpSeqNumber(txOpSeqNumber);
		setSharedObject(sharedObject);
		
		setOperationTime((int)(Math.random()*1000+1000));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("SUCCESS : Read Thread running "+getownerTxId());
		getSharedObject().setValue(getSharedObject().getValue()-1);
		try {
			Thread.sleep(getOperationTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION : ReadTxOpeartion-Sleep intruupted ");
		}
		System.out.println("SUCCESS : Read Thread Ended "+getownerTxId());
		
	}
}
