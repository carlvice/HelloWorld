/**
 * 
 * @author Aashish Jha
 */
package include;

/** This enum contains the possible types of a transaction. */
public enum TransactionType {

	/**
	 * This denotes that the transaction will perform only read operations and hence
	 * will request only for Shared locks.
	 */
	READ,

	/**
	 * This denotes that the transaction can perform both read and write operations
	 * and hence can request for both Shared and Exclusive locks.
	 */
	WRITE
}
