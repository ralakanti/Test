package amazon;

import java.io.IOException;

public class AmazonModules extends AmazonTestScripts {

	public static void launchAmazon() throws InterruptedException, IOException{
		
		String[][] recData =AmazonReusables.readExcel("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet1");
		
		String url = recData[1][1];
		
		driver.get(url);
		Thread.sleep(3000);
		Update_Report("Pass", "Application launch", url+" has been succesfully launched");
	}
}
