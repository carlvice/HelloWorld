package exception;

@SuppressWarnings("serial")
public class RemoveLockTableEntryException extends Exception{
	
	public RemoveLockTableEntryException(String msg) {
		
		System.out.println("REMOVE_ENTRY_ERROR: "+msg);		
	}
}
