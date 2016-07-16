package amazon;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonTestScripts extends AmazonReusables {

	static WebDriver driver;

	// completed
	public static void TestId001() throws InterruptedException, IOException {
		
		try{
			driver = new FirefoxDriver();
			startReport("TestId001", "D:/Raghu/Reports/");
			AmazonModules.launchAmazon();

			bw.close();
			
		}
		
		catch(Exception e){
			
		}
	}

	// completed
	public static void TestId002() throws InterruptedException, IOException {

		driver = new FirefoxDriver();
		startReport("TestId002", "D:/Raghu/Reports/");
		AmazonModules.launchAmazon();

		WebElement search = driver.findElement(By.xpath(".//*[@id='twotabsearchtextbox']"));
		enterText(search, "iphone", "Search");

		Thread.sleep(2000);

		List<WebElement> allDivs = driver.findElements(By.className("s-suggestion"));
		String[] expList = { "All Departments", "Electronics", "Cell Phones & Accessories" };

		for (int i = 0; i < 3; i++) {
			String divs = allDivs.get(i).getAttribute("data-store").trim();
			if (divs.equals(expList[i])) {
				Update_Report("Pass", "Drop down Item match", divs + " matches expected value " + expList[i]);
			} else {
				Update_Report("Fail", "Drop down Item match", divs + " does not match expected value " + expList[i]);
			}

		}

		bw.close();

	}

	// completed
	public static void TestId003() throws InterruptedException, IOException {

		driver = new FirefoxDriver();
		startReport("TestId003", "D:/Raghu/Reports/");
		AmazonModules.launchAmazon();

		WebElement search = driver.findElement(By.xpath(".//*[@id='twotabsearchtextbox']"));
		enterText(search, "iphone", "Search bar");

		Thread.sleep(2000);

		WebElement element = driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input"));
		mouseOver(driver, element);

		clickBtn(element, "Search");

		String resPage = "Amazon.com: iphone: Cell Phones & Accessories";
		String resPageTitle = driver.getTitle();

		if (resPageTitle.equals(resPage)) {
			Update_Report("Pass", "Title match", "Expected Title matches the actual title.");
		} else {
			Update_Report("Fail", "Title match", "Expected Title does not match the actual title.");
		}

		bw.close();
	}

	// completed
	public static void TestId004() throws InterruptedException, IOException {

		driver = new FirefoxDriver();
		startReport("TestId004", "D:/Raghu/Reports/");
		driver.get(
				"https://www.amazon.com/s/ref=sr_ex_n_0?rh=i%3Aaps%2Ck%3Aiphone&keywords=iphone&ie=UTF8&qid=1468620779");
		Thread.sleep(3000);
		// AmazonModules.launchAmazon();

		WebElement op1 = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li/ul[1]/li[1]/a/span"));
		verifyColorChange(op1, driver, "Cell Phones");

		WebElement op2 = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li/ul[1]/li[2]/a/span"));
		verifyColorChange(op2, driver, "Unlocked Cell Phones");

		WebElement op3 = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li/ul[1]/li[4]/a/span"));
		verifyColorChange(op3, driver, "Cell Phone Accessories");

		WebElement op4 = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li/ul[1]/li[5]/a/span"));
		verifyColorChange(op4, driver, "Cell Phone Cases");

		WebElement op5 = driver.findElement(By.xpath(".//*[@id='seeMoreRefinementValuesClosed1']/li/span/a/span[2]"));
		verifyColorChange(op5, driver, "+ See more");

		bw.close();

	}

	// Completed
	public static void TestId005() throws IOException {

		driver = new FirefoxDriver();
		startReport("TestId005", "D:/Raghu/Reports/");
		driver.get(
				"https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dcomputers&field-keywords=iphone&rh=n%3A541966%2Ck%3Aiphone");

		WebElement cComps = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li[7]/a/span[1]"));
		elementPresence(cComps, "Computer Components");
		verifyColorChange(cComps, driver, "Computer Components");

		bw.close();
	}
	//completed
	public static void TestId006() throws IOException, InterruptedException {

		driver = new FirefoxDriver();
		startReport("TestId006", "D:/Raghu/Reports/");
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dcomputers&field-keywords=iphone&rh=n%3A541966%2Ck%3Aiphone");
		Thread.sleep(2000);

		WebElement op1 = driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li[7]/a/span[1]"));
		verifyColorChange(op1, driver, "Computer Components");
		
		bw.close();
	}

	public static void TestId007() throws IOException, InterruptedException {

		driver = new FirefoxDriver();
		startReport("TestId007", "D:/Raghu/Reports/");
		driver.get("http://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=iphone");
		Thread.sleep(3000);

		
		String[] chkBoxItem = {"Free Shipping by Amazon","Apple","Hyperion EA","Rinbers","Buyee","Grantwood Technology","Energen","RCO","KINGLAKE","UNU","Cell Armor","Maxboost","8 GB","16 GB","32 GB","64 GB","AmazonGlobal Eligible","3.9 Inches & Under","4.0 to 4.4 Inches","4.5 to 4.9 Inches","5.0 to 5.4 Inches","5.5 Inches & Over","Samsung Galaxy S 4","Blackberry","iPhone 5C","iPhone 4/4S","HTC One","Nexus 4","Samsung Galaxy S III","Nexus 5","iPhone 5S","iPhone 5","iPhone 6/6S","iPhone 6/6S Plus","iPhone 5/5S/SE","iPhone 5C","iPhone 4/4S","Samsung Galaxy S 6","Samsung Galaxy S 6 Edge","Samsung Galaxy S 5","Samsung Galaxy S 4","Samsung Galaxy Note 4","LG G3","Frustration-Free Packaging","WuWuShop","FanShop123","TYD Designs","HyperNova","ChristieVera","Young-Stellar","KOnly","kevin lockhart","Phone caseS","GRECO GRECO"};
		validateImgChkBoxes(driver, ".//*[@id='refinements']/ul/li/a/img", chkBoxItem);
		
		
		WebElement deAc1 = driver.findElement(By.xpath(".//*[@id='ref_2488708011']/li[6]/span"));
		checkOptionValidity(deAc1, "Samsung Galaxy S 7");
		
		WebElement deAc2 = driver.findElement(By.xpath(".//*[@id='ref_2488708011']/li[7]/span"));
		checkOptionValidity(deAc2, "Samsung Galaxy S 7");
		
		bw.close();
	}
	
	
	
	//
	//completed
	public static void TestId008() throws IOException, InterruptedException {

		driver = new FirefoxDriver();
		startReport("TestId008", "D:/Raghu/Reports/");

		AmazonModules.launchAmazon();

		WebElement depts = driver.findElement(By.xpath(".//*[@id='nav-link-shopall']"));
		// WebElement deptsItem =
		// driver.findElement(By.xpath(".//*[@id='nav-flyout-shopAll']/div[2]/span[2]/span"));
		mouseOver(driver, depts);
		WebElement dropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-shopAll']"));
		mouseOver(driver, depts);
		// mouseOverandWait(driver, depts, deptsItem);
		dropDownPresence(dropDown, "Shop by your department");

		WebElement yourAmazon = driver.findElement(By.xpath(".//*[@id='nav-your-amazon']"));
		mouseOver(driver, yourAmazon);
		elementPresence(yourAmazon, "Your amazon link");

		WebElement todaysDeals = driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[2]"));
		mouseOver(driver, todaysDeals);
		elementPresence(todaysDeals, "Today's deals link");

		WebElement giftCards = driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[3]"));
		mouseOver(driver, giftCards);
		elementPresence(giftCards, "Gift Cards & Registry link");

		WebElement sell = driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[4]"));
		mouseOver(driver, sell);
		elementPresence(sell, "Sell link");

		WebElement help = driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[5]"));
		mouseOver(driver, help);
		elementPresence(help, "Help link");

		WebElement hello = driver.findElement(By.xpath(".//*[@id='nav-link-yourAccount']"));
		WebElement helloItem = driver.findElement(By.xpath(".//*[@id='nav_prefetch_yourorders']/span"));
		mouseOver(driver, hello);
		WebElement helloDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-yourAccount']"));
		mouseOver(driver, hello);
		// mouseOverandWait(driver, hello, helloItem);
		dropDownPresence(helloDropDown, "Hello Sign In");

		WebElement tryPrime = driver.findElement(By.xpath(".//*[@id='nav-link-prime']"));
		// WebElement tryPrimeItem =
		// driver.findElement(By.xpath(".//*[@id='nav-flyout-prime']/div[2]/div[2]"));
		mouseOver(driver, tryPrime);
		WebElement tryPrimeDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-prime']"));
		mouseOver(driver, tryPrime);
		// mouseOverandWait(driver, tryPrime, tryPrimeItem);
		dropDownPresence(tryPrimeDropDown, "Try Prime");

		WebElement lists = driver.findElement(By.xpath(".//*[@id='nav-link-wishlist']"));
		// WebElement listsItem =
		// driver.findElement(By.xpath(".//*[@id='nav-flyout-prime']/div[2]/div[2]/div[1]/p[1]/a"));
		// mouseOverandWait(driver, lists, listsItem);
		mouseOver(driver, lists);
		WebElement listsDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-wishlist']"));
		mouseOver(driver, lists);
		// mouseOverandWait(driver, lists, listsItem);
		dropDownPresence(listsDropDown, "List");

		WebElement cart = driver.findElement(By.xpath(".//*[@id='nav-your-amazon']"));
		mouseOver(driver, cart);
		elementPresence(cart, "Cart");

		bw.close();

	}

	// Complete

	public static void TestId009() throws IOException, InterruptedException {

		driver = new FirefoxDriver();
		startReport("TestId010", "D:/Raghu/Reports/");

		AmazonModules.launchAmazon();

		WebElement depts = driver.findElement(By.xpath(".//*[@id='nav-link-shopall']"));
		WebElement dropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-shopAll']"));
		mouseOver(driver, depts);
		dropDownPresence(dropDown, "Shop by your department");

		mouseOver(driver, depts);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(".//*[@id='nav-flyout-shopAll']/div[2]/span[7]/span")));

		String[] expDeptDDown = { "Amazon Video", "Digital & Prime Music", "Appstore for Android",
				"Amazon Photos & Drive", "Kindle E-readers & Books", "Fire Tablets", "Fire TV", "Echo & Alexa",
				"AmazonFresh", "Books & Audible", "Movies, Music & Games", "Electronics & Computers",
				"Home, Garden & Tools", "Beauty, Health & Grocery", "Toys, Kids & Baby", "Clothing, Shoes & Jewelry",
				"Sports & Outdoors", "Automotive & Industrial", "Handmade", "Home Services",
				"Credit & Payment Products" };
		List<WebElement> deptDropDown = driver.findElements(By.xpath(".//*[@id='nav-flyout-shopAll']/div[2]/span"));
		for (int i = 0; i < deptDropDown.size(); i++) {
			if (deptDropDown.get(i).getAttribute("aria-label").equals(expDeptDDown[i])) {
				Update_Report("Pass", "Departments drop down",
						deptDropDown.get(i).getAttribute("aria-label") + " matches " + expDeptDDown[i]);
			} else {
				Update_Report("Fail", "Departments drop down",
						deptDropDown.get(i).getAttribute("aria-label") + " does not matche " + expDeptDDown[i]);
			}

		}

		bw.close();
	}

	// complete
	public static void TestId010() throws IOException, InterruptedException {

		driver = new FirefoxDriver();
		startReport("TestId010", "D:/Raghu/Reports/");

		AmazonModules.launchAmazon();

		WebElement hello = driver.findElement(By.xpath(".//*[@id='nav-link-yourAccount']"));
		mouseOver(driver, hello);
		WebElement helloDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-yourAccount']"));
		mouseOver(driver, hello);
		dropDownPresence(helloDropDown, "Your account");

		mouseOver(driver, hello);
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='nav-flyout-shopAll']/div[2]/span[7]/span")));

		List<WebElement> dropDown = driver.findElements(By.xpath(".//*[@id='nav-flyout-yourAccount']/div[3]/a/span"));

		String[] expDDownItem = { "Your Account", "Your Orders", "Your Lists", "Your Recommendations",
				"Your Subscribe & Save Items", "Your Digital Subscriptions", "Your Service Requests",
				"Your Prime Membership", "Your Garage", "Register for a Business Account",
				"Your Amazon Credit Card Accounts", "Manage Your Content and Devices", "Your Video Subscriptions",
				"Your Prime Music", "Your Music Library", "Your Amazon Drive", "Your Prime Video",
				"Your Kindle Unlimited", "Your Watchlist", "Your Video Library", "Your Android Apps & Devices" };
		for (int i = 0; i < dropDown.size(); i++) {
			if (dropDown.get(i).getText().equals(expDDownItem[i])) {
				Update_Report("Pass", "Departments drop down",
						dropDown.get(i).getText() + " matches " + expDDownItem[i]);
			} else {
				Update_Report("Fail", "Departments drop down",
						dropDown.get(i).getText() + " does not match " + expDDownItem[i]);
			}

		}

		bw.close();
	}

	// Complete
	public static void TestId011() throws InterruptedException, IOException {

		driver = new FirefoxDriver();
		startReport("TestId011", "D:/Raghu/Reports/");

		AmazonModules.launchAmazon();

		WebElement tryPrime = driver.findElement(By.xpath(".//*[@id='nav-link-prime']"));
		WebElement tryPrimeDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-prime']"));
		mouseOver(driver, tryPrime);
		dropDownPresence(tryPrimeDropDown, "Your account");
		mouseOver(driver, tryPrime);

		List<WebElement> tryPrimeItems = driver.findElements(By.xpath(".//*[@id='nav-flyout-prime']/div/a"));

		for (int i = 0; i < tryPrimeItems.size(); i++) {
			System.out.println(tryPrimeItems.get(i).getText());
		}

		bw.close();
		
	}

	// Complete
	public static void TestId012() throws InterruptedException, IOException {

		driver = new FirefoxDriver();
		startReport("TestId012", "D:/Raghu/Reports/");

		AmazonModules.launchAmazon();

		WebElement list = driver.findElement(By.xpath(".//*[@id='nav-link-wishlist']"));
		mouseOver(driver, list);
		WebElement listDropDown = driver.findElement(By.xpath(".//*[@id='nav-flyout-wishlist']"));
		dropDownPresence(listDropDown, "List");
		mouseOver(driver, list);

		List<WebElement> listItems = driver.findElements(By.xpath(".//*[@id='nav-flyout-wishlist']/div[2]/a/span"));

		String[] expListItems = { "Create a List", "Find a List or Registry", "Find a Gift", "Save Items from the Web",
				"Install Amazon Assistant", "Wedding Registry", "Baby Registry", "Kids' Birthdays", "School Lists",
				"Friends & Family Gifting", "Pantry Lists", "Your Hearts (in Fashion)" };
		for (int i = 0; i < listItems.size(); i++) {
			if (listItems.get(i).getText().equals(expListItems[i])) {
				Update_Report("Pass", "List drop down", listItems.get(i).getText() + " matches " + expListItems[i]);
			} else {
				Update_Report("Fail", "List drop down",
						listItems.get(i).getText() + " does not match " + expListItems[i]);
			}

		}

		bw.close();

	}
}
