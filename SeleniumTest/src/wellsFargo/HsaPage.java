package wellsFargo;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HsaPage {
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		//Step 1: Open the WebPage "http://www.WellsFargo.com" in Chrome browser
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.wellsfargo.com/");
		Thread.sleep(5000);
		
		//Step 2: verify you are on personal tab
		String homeTitle = driver.getTitle().trim();
		String expHomeTitle = "Wells Fargo - Personal & Business Banking - Student, Auto & Home Loans - Investing & Insurance";
		if(expHomeTitle.equals(homeTitle)){
			System.out.println("We are on personal tab");
		}
		else{
			System.out.println("This is not the personal tab");
		}
		
		//Step 3: There is  "About Wells Fargo". click on link and verify the page.
		WebElement about = driver.findElement(By.xpath(".//*[@id='headerTools']/nav/ul/li[2]/a"));
		about.click();
		Thread.sleep(5000);
		
		//Step 4: Confirm the title
		String aboutTitle = driver.getTitle().trim();
		String expAboutTitle = "About Wells Fargo";
		if(aboutTitle.equals(expAboutTitle)){
			System.out.println("We are on About Wells fargo page");
		}
		else{
			System.out.println("This is not the About Wells fargo page");
		}
		
		//Step 5: navigate back to the home page
		WebElement home = driver.findElement(By.xpath(".//*[@id='brand']/a/img"));
		home.click();
		Thread.sleep(5000);
		
		//Step 6: check you are on the Personal tab
		if(expHomeTitle.equals(homeTitle)){
			System.out.println("We are on personal tab");
		}
		else{
			System.out.println("This is not the personal tab");
		}
		
		//Step 7: click on insurance tab - and under that Homeowners link.
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.xpath(".//*[@id='insuranceTab']/a"));
		actions.moveToElement(mainMenu).build().perform();
		
		//Hover over homeowners and click
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='insurance']/div[1]/div[2]/ul/li[1]/a")));
		
		actions.moveToElement(element);
		actions.click().build().perform();
		Thread.sleep(3000);
		
		//Step 7:Enter zipcode
		WebElement zipCode = driver.findElement(By.xpath(".//*[@id='zipCode']"));
		zipCode.sendKeys("94538");
		System.out.println("ZipCode entered");
		
		//Click continue
		WebElement continu = driver.findElement(By.xpath(".//*[@id='c28lastFocusable']"));
		continu.click();
		System.out.println("Continue clicked");
		Thread.sleep(2000);
		
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File("C:/Users/raghu/workspace/SeleniumTest1/src/wellsFargo/screen.png"));
	}

}





