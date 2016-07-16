package amazon;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AmazonExeEngine {
	
	public static void main(String[] args) throws InterruptedException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String[][] recData = AmazonTestScripts.readExcel("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet2");
		String automateScript;
		String runNoRun;
		
		
		for(int i=1; i<recData.length; i++){
			
			runNoRun = recData[i][1];
			
			if(runNoRun.equals("Y")){
				automateScript = recData[i][2];
				Method ts = AmazonTestScripts.class.getMethod(automateScript);
				ts.invoke(ts);
			}
			
			
		
		}
		
	}

}
