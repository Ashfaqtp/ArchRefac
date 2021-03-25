package com.ArchWay_Regression.functions;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ArchWay_Regression.BaseClass.BaseClass;
import com.ArchWay_Regression.pages.LocationDetailsPage;
import com.ArchWay_Regression.pages.LocationInformationPage;
import com.ArchWay_Regression.pages.ReinsurancePage;
import com.aventstack.extentreports.Status;




public class GenericFunctions extends BaseClass{
	public static String ActiveSheet=null;
	public static void click(By xpath,String element,WebDriver driver) {
		try{
			driver.findElement(xpath).click();
			logger.log(Status.PASS, element+" is clicked");
		}
		catch(Exception e)
		{
			logger.log(Status.FAIL, element+" click failed");
		}
	}
	public static void saveButton() {
		driver.findElement(By.xpath("//button[text()='Save']")).click();
	}
	public static void clickButton(String Name) {
		driver.findElement(By.xpath("(//button[text()='"+Name+"'])[last()]")).click();
	}
	public static void CheckStatus(String status,String Insured) {
		String statusExpected=status;
		if(status.equalsIgnoreCase("Bind"))
			statusExpected="Bound";
		else if(status.equalsIgnoreCase("SPA"))
			statusExpected="SPA Submitted";
		else if(status.equalsIgnoreCase("Decline"))
			statusExpected="Declined";
		else if(status.equalsIgnoreCase("Cancel"))
			statusExpected="Cancelled";
		String Actual=(driver.findElement(By.xpath("((//p[text()='"+Insured+"'])[1]/following::div)[5]")).getText());
		if (Actual.equalsIgnoreCase(statusExpected)){
			logger.log(Status.PASS, status+" status is displayed");
		}
		else{
			logger.log(Status.FAIL, status+" status is not displayed");
		}
	}
	public static void ClickOperation(String ProgramName,String Insured,String Operation, int ActiveRow) throws InterruptedException {
		ActiveSheet="PolicyOperations";
		
		String tc=getCellData(ActiveSheet,"TestCase",ActiveRow);
		String Action=getCellData(ActiveSheet,Operation,ActiveRow);
	if(Action.equalsIgnoreCase("Yes")){
		driver.findElement(By.xpath("//a[text()='Policies']")).click();
		if(Operation.equalsIgnoreCase("Delete"))
			driver.findElement(By.xpath("//li[text()='Draft']")).click();
		else
			driver.findElement(By.xpath("//li[text()='All']")).click();	
		
		SearchProgramName(ProgramName);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//p[contains(text(),'"+ProgramName+"')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("((//p[text()='"+Insured+"'])[1]/following::i)[2]")).click();
		waitforSeconds(1);
		try{  
		driver.findElement(By.xpath("((//p[text()='"+Insured+"'])[1]/following::button[text()='"+Operation+"'])[1]")).click();
		waitforSeconds(3);
		}
		catch (Exception e)
		{
			logger.log(Status.WARNING, Operation+" not displayed");
		}
		if(Operation.equalsIgnoreCase("Copy")){
			By txt_Zip	= By.xpath("(//label[contains(text(),'Zip Code')]/following::input)[1]");
			By txt_NameInsured=By.xpath("(//label[contains(text(),'Name Insured')]/following::input)[1]");
			
			setText(txt_NameInsured,ActiveRow,"New Name Insured",driver);
			setText(txt_Zip,ActiveRow,"Zip Code",driver);    
			driver.findElement(txt_Zip).sendKeys(Keys.TAB);
			waitforSeconds(2);
			SaveChanges();
			waitforSeconds(3);
			ReinsurancePage.addDetails(tc,ActiveSheet, driver);
		}
		else if(Operation.equalsIgnoreCase("Delete")){
			driver.findElement(By.xpath("//button[text()='Yes, delete policy']")).click();
		}
		}
	}
	public static void waitforElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));

	}
	
	public static void setText(By xpath,int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + "column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				if (driver.findElement(xpath).getText()!=null){
					driver.findElement(xpath).sendKeys(Keys.CONTROL,"a");
					driver.findElement(xpath).sendKeys(Keys.BACK_SPACE);
				}

				driver.findElement(xpath).sendKeys(text);
				logger.log(Status.PASS, text+" is entered in Text Box: "+column);
			}

			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in Text Box: "+column);
			}
		}}
	public static void setDate(By xpath,int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + " column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				if (driver.findElement(xpath).getText()!=null){
					driver.findElement(xpath).sendKeys(Keys.CONTROL,"a");
					driver.findElement(xpath).sendKeys(Keys.BACK_SPACE);
				}

				driver.findElement(xpath).sendKeys(text);
				driver.findElement(xpath).sendKeys(Keys.ENTER);
				logger.log(Status.PASS, text+" is entered for "+column);
			}

			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered for "+column);
			}
		}}
	public static void selectDd(By xpath,int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + "column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				Select drpCountry = new Select(driver.findElement(xpath));
				drpCountry.selectByVisibleText(text);
				logger.log(Status.PASS, text+" is selected for DropDown: "+column);
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in DropDown: "+column);
			}
		}}
	
	public static String selectDd(By xpath,int row,String column,Boolean fetch,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + "column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				Select drpCountry = new Select(driver.findElement(xpath));
				drpCountry.selectByVisibleText(text);
				logger.log(Status.PASS, text+" is selected for DropDown: "+column);
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in Text Box: "+column);
			}
		}
		return text;
		}


	public static void selectNewDd(By xpath,int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + " column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{	
				driver.findElement(xpath).click();
				driver.findElement(xpath).sendKeys(text);
				driver.findElement(By.xpath("//button[text()='"+text+"']")).click();
				logger.log(Status.PASS, column+" is clicked");
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in Text Box: "+column);
			}
		}}
	
	public static void clickRd(int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + "column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				driver.findElement(By.xpath("//label[contains(text(),'"+column+"')]/following::label[text()='"+text+"']")).click();
				logger.log(Status.PASS, "Radio button "+text+" is clicked for "+column);
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in Text Box: "+column);
			}
		}}
	public static String clickRd(int row,String column,Boolean fetch,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase(""))
		{
			logger.log(Status.INFO, column + "column value is empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
		{
			try{
				driver.findElement(By.xpath("//label[contains(text(),'"+column+"')]/following::label[text()='"+text+"']")).click();
				logger.log(Status.PASS, "Radio button "+text+" is clicked for "+column);
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, text+" is entered in Text Box: "+column);
			}
		}
		return text;
	}
	
	public static void clickcb(int row,String column,WebDriver driver) {
		String text=getCellData(ActiveSheet,column,row);
		if(text.equalsIgnoreCase("") || text.equalsIgnoreCase("No")) 
		{
			logger.log(Status.INFO, column + " column value is either No or empty");
		}
		else if(!text.equalsIgnoreCase("Invalid"))
			if(text.equalsIgnoreCase("Yes")){
			try{
				driver.findElement(By.xpath("(//label[contains(text(),'"+column+"')]/preceding::label)[last()]")).click();
				logger.log(Status.PASS, column+" :Check Box is clicked ");
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, column+" :Check Box is not clicked "+column);
			}
		
		}}
	
	public static void isVisible(By element,String text,boolean visibility){
		if (visibility==true)
		{
			try{
			if(driver.findElement(element).isDisplayed()){
				logger.log(Status.PASS, text+" is visible");
			}}
			catch(Exception e){
				logger.log(Status.FAIL, "Failed because "+text+" is not visible");
			}
			
		}
		/*else if(visibility==false)
		{
			try{
			if(driver.findElement(element).isDisplayed()){
				logger.log(Status.FAIL, "Failed because "+text+" is visible");
			}}
			catch(Exception e){
				logger.log(Status.PASS, text+" is not visible");
			}
			
		}*/
	
	}
	public static void isSelected(String column,String value,Boolean selected, WebDriver driver){
		if (selected==true){
			if(driver.findElement(By.xpath("((//label[contains(text(),'"+column+"')]/following::label[text()='"+value+"'])[1]/preceding::input)[last()]")).isSelected())
				logger.log(Status.PASS, value+" is selected by default for "+column);
			else
				logger.log(Status.FAIL, value+" is not selected by default for "+column);
		}
		else{
			if(driver.findElement(By.xpath("((//label[contains(text(),'"+column+"')]/following::label[text()='"+value+"'])[1]/preceding::input)[last()]")).isSelected()==false)
					logger.log(Status.PASS, value+" is not selected by default for "+column);
			else
				logger.log(Status.FAIL, value+" is selected by default for "+column);
		}
	}
	public static void isEnabled(By element,String name,Boolean enabled, WebDriver driver){
		if (enabled==true){
			if(driver.findElement(element).isEnabled())
				logger.log(Status.PASS, name+" is enabled");
			else
				logger.log(Status.FAIL, name+" is not enabled");
		}
		else{
			if(driver.findElement(element).isEnabled()==false)
				logger.log(Status.PASS, name+" is not enabled");
			else
				logger.log(Status.FAIL, name+" is enabled");
		}

	}
	public static void InitialSteps() {
		By txt_email=By.xpath("//input[@name='email']");
		By btn_login=By.xpath("//button[@name='submit']");
		By txt_password=By.xpath("//input[@name='passwd']");
		By btn_signin=By.xpath("//input[@type='submit']");
		
		driver.findElement(txt_email).sendKeys(prop.getProperty("email"));
		driver.findElement(btn_login).click();
		/*driver.findElement(txt_password).sendKeys(prop2.getProperty("password"));
		driver.findElement(btn_signin).click();*/
		}
	
	public static void AddProgram() throws InterruptedException {
		InitialSteps();
		
		ProgramsPage();
		waitforSeconds(10);
		By btn_AddNewProgram=By.xpath("//button[contains(text(),'Add new program')]");
		
		waitforElement(btn_AddNewProgram);
		click(btn_AddNewProgram,"btn_AddNewProgram",driver);
		
	}
	public static void ProgramsPage() throws InterruptedException {
		By btn_Programs=By.xpath("//a[text()='Programs']");
		
		waitforSeconds(1);
		driver.findElement(btn_Programs).click();
		waitforSeconds(2);
		}
	public static void BillingPage() throws InterruptedException {
		WebElement btn_Billing=driver.findElement(By.xpath("//a[text()='Billing']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(btn_Billing).perform();
		waitforSeconds(1);
		//driver.findElement(btn_Billing).click();
		//waitforSeconds(2);
		}
	public static void AddPolicy() throws InterruptedException {
		InitialSteps();
		By btn_Policies=By.xpath("//button[text()='Policies']");
		
		driver.findElement(btn_Policies).click();
		waitforSeconds(5);
		
	}
	
	
	public static void waitforSeconds(int seconds) throws InterruptedException {
		Thread.sleep(seconds*1000);
	}
	
	public static void validateDefault(By element,String name,String expected, WebDriver driver){
		Select select = new Select(driver.findElement(element));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
			if(defaultItem.equalsIgnoreCase(expected))
				logger.log(Status.PASS, expected+" is default for "+name);
			else
				logger.log(Status.FAIL, expected+" is not default for "+name);
		

	}

	public static Integer getRowCount(String sheetName)
	{	
		ActiveSheet=sheetName;
		int rowcount=0;
		sheet = workbook.getSheet(sheetName);
		rowcount = sheet.getLastRowNum();
		return rowcount;
	}
	public static String getCellData(String sheetName, String colName, int rowNum)
	{
		try
		{
			int col_Num = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for(int i = 0; i < row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				{
					col_Num = i;
					break;
				}
			}

			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(col_Num);
			//cell=Integer.toString(cell);
			Double val=null;
			if (cell==null){
				return "";
			}
			else{
				if (CellType.FORMULA.equals(cell.getCellType())){
					FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
			     // Evaluting cell
			      CellValue c=formulaEval.evaluate(cell);
			     //System.out.println(c.getNumberValue());
			     return c.getNumberValue()+"";
				}
				else{

				if (CellType.NUMERIC.equals(cell.getCellType())){
					if (DateUtil.isCellDateFormatted(cell)){
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");                  
						return dateFormat.format(cell.getDateCellValue())+"";
					}
					else{
						val=cell.getNumericCellValue();
						if (val%1==0)
							return (int)Math.round(val)+"";
						else
							return cell.getNumericCellValue()+"";
					}}
				else
					return cell.getStringCellValue();

			}  } 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.log(Status.FAIL, "Row "+rowNum+" or column "+colName+" does not exist  in Excel");
			return "Invalid";
		}
	}
	public static void validateCurrency(String country,By dd_DefaultCurrency,WebDriver driver){
		boolean flag=false;
		Select select = new Select(driver.findElement(dd_DefaultCurrency));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		switch (country) {
		case "France":
		case "Netherlands":
			if (defaultItem.equalsIgnoreCase("EURO")){
				flag=true;
				break;
			}
		case "USA":
			if (defaultItem.equalsIgnoreCase("US dollar")){
				flag=true;
				break;
			}
		case "United Kingdom":
			if (defaultItem.equalsIgnoreCase("Pound sterling")){
				flag=true;
				break;
			}
		case "Canada":
			if (defaultItem.equalsIgnoreCase("Canadian dollar")){
				flag=true;
				break;
			}
		}
		if (flag)
			logger.log(Status.PASS, "Default Currency for "+country+" is correct: "+defaultItem);
		else
			logger.log(Status.FAIL, "Default Currency for "+country+" is incorrect: "+defaultItem);

	}
	public static void setPeril(String Peril,int row) {
		int i=0;
		String Value=getCellData(ActiveSheet,Peril,row);
		if(Value!=""){
		for(i=1;i<=6;i++)
		{
			if(driver.findElement(By.xpath("(//div[text()='Perils']/following::div)["+i+"]")).getText().equalsIgnoreCase(Peril))
				break;
		}
		try{
			driver.findElement(By.xpath("(//div[text()='"+Value+"']/following::label)["+i+"]")).click();
			logger.log(Status.PASS, "Selected "+Value+" for Peril "+Peril);
		}
		catch(Exception e)
		{
			logger.log(Status.FAIL, "Not Selected "+Value+" for Peril "+Peril);
		}

	}
	}
	public static void setPolicyPeril(String Peril,int row) {
		
		String Value=getCellData(ActiveSheet,Peril,row);
		if(Value!=""){
			try{
			if(Value.startsWith("$")){
				
				driver.findElement(By.xpath("(//td[contains(text(),'"+Peril+"')]/following::input)[2]")).sendKeys(Value.substring(1));
				logger.log(Status.PASS, "Entered "+Value+" for Peril "+Peril);
			}
			else if(Value.startsWith("%")){
				
				driver.findElement(By.xpath("(//td[contains(text(),'"+Peril+"')]/following::input)[3]")).sendKeys(Value.substring(1));
				logger.log(Status.PASS, "Entered "+Value+" for Peril "+Peril);
			}
				 
			}
		catch(Exception e)
		{
			logger.log(Status.FAIL, "Not Entered "+Value+" for Peril "+Peril);
		}

	}
	}
	public static void ExpandLayer(String Layer) {
		try{
		driver.findElement(By.xpath("//div[text()='"+Layer+"']")).click();
		logger.log(Status.PASS, Layer+" Expanded/Shrinked");
		}
		catch(Exception e)
		{
			logger.log(Status.FAIL, Layer+" Expanded/Shrinked");
		}
	}
	public static void SaveChanges() {
		try{
			driver.findElement(By.xpath("//button[text()='Save changes']")).click();
			waitforSeconds(3);
			logger.log(Status.PASS, "Clicked on Save Changes button");
			
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, "Clicked on Save Changes button");
			}
	}
	public static void SaveChangesWithWait() {
		try{
			driver.findElement(By.xpath("//button[text()='Save changes']")).click();
			waitforSeconds(5);
			logger.log(Status.PASS, "Clicked on Save Changes button");
			
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, "Clicked on Save Changes button");
			}
	}
	public static void ShowItemsInTable(int num) {
		try{
			driver.findElement(By.xpath("(//span[text()=' Show: ']/following::button[@class='popup_button__eM6qw'])[1]")).click();
			waitforSeconds(1);
			driver.findElement(By.xpath("//div[contains(@class,'popup_content__1DJv5 popup_left')]/button[text()="+num+"]")).click();
			driver.findElement(By.xpath("//span[text()=' Show: ']")).click();
			waitforSeconds(2);
			logger.log(Status.PASS, "Showing "+num+ "rows in table");
			
			}
			catch(Exception e)
			{
				logger.log(Status.FAIL, "Showing "+num+ "rows in table");
			}
	}
	public static void GotoPrograms() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Programs']")).click();
		waitforSeconds(2);
	}
	public static int GetActiveRow(String tc,String Sheetname) throws InterruptedException {
		int ActiveRow=0;
		int Rowcount = getRowCount(Sheetname);
		for (int row=1;row<=Rowcount+1;row++)
		{
			String Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				break;
			}
		}
		return ActiveRow;
	}
	public static String SearchProgramNameWithTC(String tc) throws InterruptedException {
		int ActiveRow=GetActiveRow(tc,"General Information");
		String ProgramName=getCellData("General Information","Program name",ActiveRow);
		driver.findElement(By.xpath("//button[text()='Show Search']")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Program name') ]/following::input[1]")).sendKeys(ProgramName);
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		waitforSeconds(2);
		driver.findElement(By.xpath("//button[text()='Hide Search']")).click();
		
		return ProgramName;
	}
	public static String SearchProgramName(String ProgramName) throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Show Search']")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Program name') ]/following::input[1]")).sendKeys(ProgramName);
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		waitforSeconds(2);
		driver.findElement(By.xpath("//button[text()='Hide Search']")).click();
		
		return ProgramName;
	}
	
	public static void DraftToPendingApproval(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='Draft']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Submit for testing')])[last()]")).click();
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		waitforSeconds(3);
	}
	public static void PendingApprovaltoUWApproved(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='Pending Approval']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Approve')])[last()]")).click();
		driver.findElement(By.xpath("(//button[text()='Approve'])[2]")).click();
		waitforSeconds(3);
	
	}
	public static void UWApprovedtoApproved(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='UW Approved']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Finalize')])[last()]")).click();
		driver.findElement(By.xpath("(//button[text()='Finalize'])[last()]")).click();
		waitforSeconds(3);
	
	}
	public static void DeleteFromDraft(String tc,int count, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='Draft']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		
		waitforSeconds(3);
		for(int i=0;i<count;i++){
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Delete')])[last()]")).click();
		driver.findElement(By.xpath("//button[text()='Yes, delete program']")).click();
		waitforSeconds(3);
	}}
	public static void DeclineFromPendingApproval(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='Pending Approval']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Decline')])[last()]")).click();
		driver.findElement(By.xpath("(//button[text()='Decline'])[last()]")).click();
		waitforSeconds(3);
	}
	public static void DeclineFromUWApproved(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='UW Approved']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Decline')])[last()]")).click();
		driver.findElement(By.xpath("(//button[text()='Decline'])[last()]")).click();
		waitforSeconds(3);
	}
	public static void NavigateToPage(String Page, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='"+Page+"']")).click();		
		waitforSeconds(3);
	}
	public static void CreateTestPolicy(String tc, WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//li[text()='UW Approved']")).click();		
		String ProgramName=SearchProgramNameWithTC(tc);
		waitforSeconds(2);
		try{
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Create Test Policy')])[last()]")).click();
		waitforSeconds(3);
		int ActiveRow=0;
		String Sheetname="AddPolicy";
		ActiveRow=GetActiveRow(tc, Sheetname);
		LocationInformationPage.fillLocation(ActiveRow);
		LocationDetailsPage.addDetails(tc, driver);
		waitforSeconds(3);
		ReinsurancePage.addDetails(tc,Sheetname, driver);
		}
		catch (Exception e){
			
		}
	}
}
