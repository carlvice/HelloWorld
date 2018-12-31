/** @author Aashish Jha */

package zgt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import include.TransactionManager;

/** This class implements abstract methods of TransactionManager class */

public class ZGTTransactionManager extends TransactionManager{

	public ZGTTransactionManager(int sharedObjectListSize, int sharedObjectInitialValue, String inputFilePath,
			String outputFilePath) {
		super(sharedObjectListSize, sharedObjectInitialValue, inputFilePath, outputFilePath);
		
		
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openLogFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeLogFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOperation() throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		File logFile = new File(inputFilePath);;
		FileReader fileReader = new FileReader(logFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lines="";
		while((lines=bufferedReader.readLine()).contains("//"));
		TransactionManager.outputFilePath=lines.split(" ")[1];
		while((lines=bufferedReader.readLine())!=null)
		{
			
		}
		
		
		
		
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
