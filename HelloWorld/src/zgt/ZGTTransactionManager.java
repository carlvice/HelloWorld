/** @author Aashish Jha */

package zgt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import include.TransactionManager;

/** This class implements abstract methods of TransactionManager class */

public class ZGTTransactionManager extends TransactionManager{

	File logFile;
	FileReader fileReader;
	BufferedReader bufferedReader;

	public ZGTTransactionManager(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath,
			String outputFilePath) {
		super(sharedObjectListSize, sharedObjectInitialValue, inputFilePath, outputFilePath);
		logFile=new File(inputFilePath);
		
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openLogFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		fileReader=new FileReader(logFile);
		bufferedReader=new BufferedReader(fileReader);
		
		
	}

	@Override
	public void closeLogFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOperation() throws FileNotFoundException{
		// TODO Auto-generated method stub
		
		openLogFile();
		
	}

	@Override
	public void endOperation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printTxManager() {
		// TODO Auto-generated method stub
		
	}

	
}
