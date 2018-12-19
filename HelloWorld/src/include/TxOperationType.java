package include;

public enum TxOperationType {

	/**
	 * It means that a new tx has just begun and an entry for that tx should be
	 * created in the Transaction list
	 * 
	 */
	BEGIN,

	/**
	 * When a tx performs read operation, it will request for a Shared lock, and if
	 * granted it will decrease the value of object by 1.
	 */

	READ,

	/**
	 * When a tx performs write operation, it will requests for an Exclusive lock,
	 * and if granted, it will increase the value of object by 1
	 */
	WRITE,

	/**
	 * A COMMIT statement ends a transaction and makes all changes visible to other
	 * users. In strict 2PL , all the locks acquired by the tx are released during
	 * this operation.
	 */

	COMMIT,

	/**
	 * The ABORT statement means that the tx had to end prematurely because of a
	 * problem or fault. In strict 2PL, the tx must release all its locks if it
	 * aborts.
	 * 
	 */

	ABORT

}
