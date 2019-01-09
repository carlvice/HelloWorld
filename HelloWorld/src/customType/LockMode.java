/**
 * 
 * @author Nivedita Gautam
 */

package customType;

/** Denotes the possible lock modes that can be acquired by a transaction. */
public enum LockMode {

	/**
	 * This lock mode implies that the transaction wants to read the object. More
	 * than one transactions can have a shared lock on the same object at the same
	 * point in time. This lock is upgradable. This lock can be upgraded to an
	 * exclusive lock provided no other transaction holds a shared or exclusive lock
	 * on that object.
	 */
	SHARED,

	/**
	 * This lock mode implies that a transaction has both read and write access on
	 * an object. Only one transaction may hold an object in exclusive mode at a
	 * time.
	 */
	EXCLUSIVE
}
