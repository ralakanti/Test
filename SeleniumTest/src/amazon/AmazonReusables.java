package amazon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class AmazonReusables{
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	
	
	/* Name of the Method: readExcel
	 * Brief description: Start creating a report 
	 * Arguments: scriptName --> String , ReportsPath --> String 
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	
	
	public static String[][] readExcel(String xPath, String SheetName) throws IOException
	{

		
		File xlFile = new File(xPath);
		
		FileInputStream xlDoc = new FileInputStream(xlFile);
		
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		
		HSSFSheet sheet = wb.getSheet(SheetName);	
		
		int iRowCount = sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();
		
		String[][] xlData = new String[iRowCount][iColCount];
			
		for(int i=0; i<iRowCount; i++)
		{
			for(int j=0; j<iColCount; j++)
			{
				xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				
				
			}
		}
		return xlData;
	}

	/* Name of the Method: startReport
	 * Brief description: Start creating a report 
	 * Arguments: scriptName --> String , ReportsPath --> String 
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 
	}

	/* Name of the Method: enterText
	 * Brief description: Enter text value to text boxes 
	 * Arguments: obj --> webelement Object, textVal --> text to be entered, objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{


		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass", "enterText", textVal+ "  is entered in " + objName + " field");
		}else{
			Update_Report("Fail", "enterText", objName + " field is not displayed please check your application ");
		}

	}
	
	/* Name of the Method: verifyColorChange
	 * Brief description: Enter text value to text boxes 
	 * Arguments: ele --> webelement Object
	 * Created By: TechPirates 
	 * Creation Date: July 15 2016
	 * Last Modified: July 15 2016
	 * */
	public static void verifyColorChange(WebElement ele, WebDriver driver, String opName) throws IOException{
		
		if(ele.isDisplayed()){
			Update_Report("Pass", "Option presence", opName+" option is displayed.");
			String eleColor = ele.getCssValue("color");
			Actions builder = new Actions(driver);
			Action mouseOver = builder.moveToElement(ele).build();
			mouseOver.perform();    
			String newColor = ele.getCssValue("color");
	    
			if(eleColor.equals(newColor)){
	    	Update_Report("Fail", "Color Change", "There was no change in color.");
	  	 }
			else{
	    	Update_Report("Pass", "Color Change", "There was a change in color.");
			}
		}
		else{
			Update_Report("Fail", "Option presence", opName+" option is not displayed.");
		}
			
	}

	/* Name of the Method: clickButton
	 * Brief description: Click on Web Button 
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void clickBtn(WebElement obj, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			Update_Report("Pass", "clickButton", objName + " is clicked");
		}
		else{
			Update_Report("Fail", "clickButton",  objName + " Button is not displayed please check your application");

		}
	}
	/* Name of the Method: validateTextMessage
	 * Brief description: validating Text message
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void validateTextMessage(WebElement obj, String expectedMsg, String objname) throws IOException{


		if(obj.isDisplayed()){
			String actualMsg = obj.getText().trim();
			if(expectedMsg.equals(actualMsg)){

				Update_Report("Pass","validateTextMessage", "Actual Message " + actualMsg+" is matched with expectedErrMsg "+expectedMsg);
				System.out.println("Test Successful");
			}
			else{

				Update_Report("Fail","validateTextMessage", "Actual Message " + actualMsg+" is not matched with expectedErrMsg "+expectedMsg);
				System.out.println("Test Fail");
			}
		}
		else{
			Update_Report("Fail", "validateTextMessage",  objname + " Button is not displayed please check your application");
			System.out.println("Test Failed due to object not found");
		}



	}

	/* Name of the Method: chkBoxSelect
	 * Brief description: Select Check box
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void chkBoxSelect(WebElement obj, String objName ) throws IOException{
		if(obj.isDisplayed()){
			if(obj.isSelected()){
				Update_Report("Pass", "chkBoxSelect", objName + " is already selected");
			}
			else{
				obj.click();
				Update_Report("Pass", "chkBoxSelect", objName + " is selected");
			}
		}
		else{
			Update_Report("Fail", "chkBoxSelect", objName + " is not displayed Please check your application");
		}
	}

	/* Name of the Method: chkBoxDeSelect
	 * Brief description: De-Select Check box
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: June 29 2016
	 * Last Modified: June 29 2016
	 * */
	public static void chkBoxDeSelect(WebElement obj, String objName ) throws IOException{
		if(obj.isDisplayed()){
			if(obj.isSelected()){
				obj.click();
				Update_Report("Pass", "chkBoxDeSelect", objName + " is De-selected");
			}
			else{
				Update_Report("Pass", "chkBoxDeSelect", objName + " is already de-selected");
			}
		}
		else{
			Update_Report("Fail", "chkBoxDeSelect", objName + " is not displayed Please check your application");
		}
	}
	
	
	
	/* Name of the Method: validateTextInDropdn
	 * Brief description: validate drop down menu text //div -tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	
	
	public static void validateImgChkBoxes(WebDriver driver,String xpath, String[] chkBoxs) throws IOException{
		
		List<WebElement> chkboxes = driver.findElements(By.xpath(xpath));
		for(int i=0; i<chkboxes.size(); i++){
			
				Update_Report("Pass", "Check box status", chkBoxs[i] + " available");
				if(chkboxes.get(i).getAttribute("alt").contains(chkBoxs[i]) && chkboxes.get(i).isDisplayed() && chkboxes.get(i).getAttribute("src").contains("checkbox_unselected_enabled")){
					Update_Report("Pass", "Check box status", chkBoxs[i] + " is unselected");
				}
				else if(chkboxes.get(i).getAttribute("alt").contains(chkBoxs[i]) && chkboxes.get(i).getAttribute("src").contains("checkbox_selected_enabled")){
					Update_Report("Pass", "Check box status", chkBoxs[i] + " is Selected");
				}
				else if(chkboxes.get(i).getAttribute("alt").contains(chkBoxs[i]) && !chkboxes.get(i).isDisplayed()){
					Update_Report("Fail", "Check box status", chkBoxs[i] + " is not available");
				}
			
			
		}
	}
	

	
	/* Name of the Method: checkOptionValidity
	 * Brief description: validate drop down menu text //div -tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	public static void checkOptionValidity( WebElement ele, String objName) throws IOException{
		
		
		if(ele.getAttribute("class").equals("refinementNotAvailable")){
			Update_Report("Pass", "Check box validity", objName+" option is  deactivated");
		}
		else{
			Update_Report("Fail", "Check box validity", objName+" option is not available and activated");
		}
	}
	
	/* Name of the Method: validateTextInDropdn
	 * Brief description: validate drop down menu text //div -tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	public static void validateTextInDropdn(WebElement obj, String[] expMenuItemes, String objname) throws IOException{
		String[] actualStr = obj.getText().split("[\\r\\n]+");
		if(obj.isDisplayed()){
			for(int i = 0; i < expMenuItemes.length; i++){

				if(actualStr[i].equals(expMenuItemes[i])){
					Update_Report("Pass", "validateTextInDropdn",  "Expected menu item is: "  +expMenuItemes[i]+ " is matched to actual item: "+actualStr[i]);

				}
				else{
					Update_Report("Fail", "validateTextInDropdn",  "Expected menu item is: "  +expMenuItemes[i]+ " is not matched to actual item: "+actualStr[i]);

				}

			}

		}
		else{
			Update_Report("Fail", "validateTextInDropdn",  objname + " DropDown menu is not displayed please check your application");

		}
	}
	
	/* Name of the Method: selectULDropDown
	 * Brief description: select from ul drop down list
	 * Arguments: obj --> list<webelement> Object, expItemsList--> list of expected items, objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 12 2016
	 * Last Modified: July 12 2016
	 * */
	
	public static void selectULDropDown(WebElement tab, List<WebElement> dropDownList, String listItem, String objName) throws IOException, InterruptedException{
		
		tab.click();
		Thread.sleep(1000);
		for(int i=0; i<dropDownList.size(); i++){
			if(dropDownList.get(i).getText().equals(listItem)){
				dropDownList.get(i).click();
				Thread.sleep(2000);
				Update_Report("Pass", "selectULDropDown",  "Expected menu item is: "  +listItem+ " is matched to actual item: "+dropDownList.get(i).getText());
			}
			else{
				Update_Report("Fail", "selectULDropDown",  "Expected menu item is: "  +listItem+ " did not match to actual item: "+dropDownList.get(i).getText());
			}
		}
		
	}
	
	/* Name of the Method: vaidateULDropDown
	 * Brief description: validate drop down menu with ul tag
	 * Arguments: obj --> list<webelement> Object, expItemsList--> list of expected items, objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 12 2016
	 * Last Modified: July 12 2016
	 * */
	public static void validateULDropDown(List<WebElement> dropDownList, String[] expItemsList, String objName) throws IOException{
				
		
		for(int i =0; i<dropDownList.size(); i++){
			if(dropDownList.get(i).getText().trim().equals(expItemsList[i])){
				System.out.println("matches");
				Update_Report("Pass", "validateTextInDropdn",  "Expected menu item is: "  +expItemsList[i]+ " is matched to actual item: "+dropDownList.get(i).getText());
			}
			else{
				Update_Report("Fail", "validateTextInDropdn",  "Expected menu item is: "  +expItemsList[i]+ " is Not matched to actual item: "+dropDownList.get(i).getText());
			}
		}
		
	}
	
	/* Name of the Method: mouseOver
	 * Brief description: mouse over an element
	 * Arguments: WebDriver driver, WebElement element
	 * Created By: TechPirates 
	 * Creation Date: July 15 2016
	 * Last Modified: July 15 2016
	 * */
	
	public static void mouseOver(WebDriver driver, WebElement element) throws IOException{
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		
	}
	
	
	/* Name of the Method: elementPresence
	 * Brief description: select from ul drop down list
	 * Arguments: obj --> WebElement ele Object, eleName --> element Name
	 * Created By: TechPirates 
	 * Creation Date: July 12 2016
	 * Last Modified: July 12 2016
	 * */
	
	public static void elementPresence(WebElement ele, String eleName) throws IOException{
		
		if(ele.isDisplayed()){
			Update_Report("Pass", "Element presence",  eleName+ " is present");
		}
		else{
			Update_Report("Fail", "Element presence",  eleName+ " is NOT present");
		}
	}
	
	public static void dropDownPresence(WebElement dDown, String objName) throws IOException{
		if(dDown.getAttribute("style").contains("display: block")){
			Update_Report("Pass", "Dropdown presence",  objName+ " Drop down is present");
			
		}
		else{
			Update_Report("Fail", "Dropdown presence",  objName+ " Drop down is NOT present");
	
		}
	}
	
	
	/* Name of the Method: mouseOverandWait
	 * Brief description: mouse over an element and wait
	 * Arguments: obj -->WebDriver driver, WebElement mOverEle, WebElement ele
	 * Created By: TechPirates 
	 * Creation Date: July 12 2016
	 * Last Modified: July 12 2016
	 * */
	public static void mouseOverandWait(WebDriver driver, WebElement mOverEle, WebElement ele){
		Actions actions = new Actions(driver);
		actions.moveToElement(mOverEle).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	/* Name of the Method: selectULDropDown
	 * Brief description: select from ul drop down list
	 * Arguments: obj --> list<webelement> Object, expItemsList--> list of expected items, objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 12 2016
	 * Last Modified: July 12 2016
	 * */
	
	public static void selectULDropDown(List<WebElement> dropDownList, String listItem, String objName) throws IOException, InterruptedException{
		
		Thread.sleep(1000);
		for(int i=0; i<dropDownList.size(); i++){
			if(dropDownList.get(i).getText().equals(listItem)){
				dropDownList.get(i).click();
				Thread.sleep(2000);
				Update_Report("Pass", "selectULDropDown",  "Expected menu item is: "  +listItem+ " is matched to actual item: "+dropDownList.get(i).getText());
			}
			else{
				Update_Report("Fail", "selectULDropDown",  "Expected menu item is: "  +listItem+ " did not match to actual item: "+dropDownList.get(i).getText());
			}
		}
		
	}

	
	/* Name of the Method: validateSelectDropDown
	 * Brief description: validate drop down menu with select tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	public static void validateSelectDropDown(WebElement obj, String[] expectedItems, String objname) throws Exception{
		if(obj.isDisplayed()){
			Select options = new Select(obj);
			
			java.util.List<WebElement> elementOptions = options.getOptions();
			
			int iSize = elementOptions.size();
			
			for(int i =0; i<iSize; i++){
				if(elementOptions.get(i).getText().equals(expectedItems[i])){
					Update_Report("Pass", "validateTextInDropdn",  "Expected menu item is: "  +expectedItems[i]+ " is matched to actual item: "+elementOptions.get(i).getText());
				}
				else{
					Update_Report("Fail", "validateTextInDropdn",  "Expected menu item is: "  +expectedItems[i]+ " is Not matched to actual item: "+elementOptions.get(i).getText());
				}
			}
			
		}
		else{
			Update_Report("Fail", "validateTextInDropdn",  objname + " DropDown menu is not displayed please check your application");
		}
		
	}
	
	/* Name of the Method: validateSelectMenuOneItem
	 * Brief description: validate drop down menu Only one item with select tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	public static void validateSelectMenuOneItem(WebElement obj, String expectedItems, String objname) throws Exception{
		if(obj.isDisplayed()){
			Select options = new Select(obj);
			
			java.util.List<WebElement> elementOptions = options.getOptions();
			
			int iSize = elementOptions.size();
			
			for(int i =0; i<iSize; i++){
				if(elementOptions.get(i).getText().equals(expectedItems)){
					Update_Report("Pass", "validateTextInDropdn",  "Expected menu item is: "  +expectedItems+ " is matched to actual item: "+elementOptions.get(i).getText());
				}
			}
			
		}
		else{
			Update_Report("Fail", "validateTextInDropdn",  objname + " DropDown menu is not displayed please check your application");
		}
		
	}
	
	/* Name of the Method: selectFromDropDown
	 * Brief description: select from drop down menu Only one item with select tag
	 * Arguments: obj --> webelement Object,objName --> Object Name
	 * Created By: TechPirates 
	 * Creation Date: July 4 2016
	 * Last Modified: July 4 2016
	 * */
	public static void selectFromDropDown(WebElement obj, String selectItem, String objname) throws Exception{
		if(obj.isDisplayed()){
			Select options = new Select(obj);
			options.selectByVisibleText(selectItem);
			Update_Report("Pass", "selectFromDropDown",  "Menu item is: "  +selectItem+ " is Selected");
			
		}
		else{
			Update_Report("Fail", "selectFromDropDown",  objname + " DropDown menu is not displayed please check your application");
		}
		
	}
	

	public static void passFail(String result) throws IOException{
		String path = "C:/Users/raghu/Desktop/Amazon_TestSuite.xls";
		int gcolor = new HSSFColor.GREEN().getIndex();
		int rcolor = new HSSFColor.RED().getIndex();
		if(result.equals("pass")){
			writeExcel(path, "Sheet2", 1, 3, "Pass");
			fillColor(path, "Sheet2", 1, 3, gcolor);
		}
		
		else{
			writeExcel(path, "Sheet2", 1, 3, "Fail");
			fillColor(path, "Sheet2", 1, 3, rcolor);
		}
	}
	
	public static void writeExcel(String xPath, String SheetName, int iRow, int iCol, String value) throws IOException
	{
		File xlFile = new File(xPath);
		
		FileInputStream xlDoc = new FileInputStream(xlFile);
		
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		
		HSSFSheet sheet = wb.getSheet(SheetName);
		
		HSSFRow row= sheet.getRow(iRow);
		HSSFCell cell= row.getCell(iCol);
		
		cell.setCellValue(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
		
		
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(titleStyle);
		
		 FileOutputStream out = new FileOutputStream(new File(xPath));
		 wb.write(out);
		 out.flush();
		 out.close();
	}
	
	
	
	public static void fillColor(String xPath, String SheetName, int iRow, int iCol, int color) throws IOException
	{
		File xlFile = new File(xPath);
		
		FileInputStream xlDoc = new FileInputStream(xlFile);
		
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		
		HSSFSheet sheet = wb.getSheet(SheetName);
		
		HSSFRow row= sheet.getRow(iRow);
		HSSFCell cell= row.getCell(iCol);
		
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFillForegroundColor((short) color);
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(titleStyle);
		
		 FileOutputStream out = new FileOutputStream(new File(xPath));
		 wb.write(out);
		 out.flush();
		 out.close();
	}
	
	public static void updateExcel(int row, int col, String result) throws IOException{
		
		int color;
		if(result.equals("Pass")){
			color = new HSSFColor.GREEN().getIndex();
			writeExcel("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet2", row,col, "Pass");
			fillColor("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet2", row, col, color);
		}
		else if(result.equals("Fail")){
			
			color = new HSSFColor.RED().getIndex();
			writeExcel("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet2", row, col, "Fail");
			fillColor("C:/Users/raghu/Desktop/Amazon_TestSuite.xls", "Sheet2", row, col, color);
		}
		
	}
}




