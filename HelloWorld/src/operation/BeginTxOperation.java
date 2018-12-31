/**
 *  @author Aashish Jha
 */

package operation;

import entity.Transaction;
import entity.TxOperation;
import include.TxOperationType;

/**
 * This class is BeginTx Operation thread. This thread will perform the
 * operations required to begin a transaction.
 */
public class BeginTxOperation  extends TxOperation{

	public BeginTxOperation(Transaction ownerTx, TxOperationType txOpType, int txOpSeqNumber) {
		
		setOwnerTx(ownerTx);
		setTxOpType(txOpType);
		setTxOpSeqNumber(txOpSeqNumber);
		
		setOperationTime((int)(Math.random()*1000+1000));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}