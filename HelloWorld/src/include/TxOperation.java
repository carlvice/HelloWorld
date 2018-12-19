package include;

public abstract class TxOperation implements Runnable{
	
	
	private int txOpSeqNumber;
	
	
	private Transaction ownerTx;
	
	
	private TxOperationType txOpType;
	
	
	private SharedObject sharedObject;
	
	
	private int operationTime;
	
}
 