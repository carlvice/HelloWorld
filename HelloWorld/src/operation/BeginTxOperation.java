/**
 *  @author Aashish Jha
 */

package operation;

import customType.TransactionStatus;
import customType.TransactionType;
import customType.TxOperationType;
import entity.Transaction;
import entity.TxOperation;
import zgt.ZGTTransaction;

/**
 * This class is BeginTx Operation thread. This thread will perform the
 * operations required to begin a transaction.
 */
public class BeginTxOperation  extends TxOperation{
	
	private TransactionType txType;
	
	/**
	 * @return the txType
	 */
	public TransactionType getTxType() {
		return txType;
	}
	
	/**
	 * @param txType the txType to set
	 */
	
	public void setTxType(TransactionType txType) {
		this.txType = txType;
	}

	public BeginTxOperation(int ownerTx, TxOperationType txOpType, int txOpSeqNumber , TransactionType txType) {
		
		setownerTxId(ownerTx);
		setTxOpType(txOpType);
		setTxOpSeqNumber(txOpSeqNumber);
		setTxType(txType);
		
		setOperationTime((int)(Math.random()*1000+1000));
	}

	@Override
	public void run() {
		
		System.out.println("SUCCESS : Begin Thread running "+getownerTxId());
		try {
			Thread.sleep(getOperationTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPTION : BeginTxOpeartion-Sleep intruupted ");
		}
		System.out.println("SUCCESS : Begin Thread Ended "+getownerTxId());
	}
	
	
}