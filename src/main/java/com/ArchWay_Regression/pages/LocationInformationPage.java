package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class LocationInformationPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="AddPolicy";
	static int row=0,ActiveRow=0;
	static By txt_ProgramSearch=By.xpath("//input[@placeholder='Enter program name to search']");
	static By txt_ClickProgram=By.xpath("//td[text()='SherTest123']");
	static By txt_NameInsured=By.xpath("(//label[contains(text(),'Name Insured')]/following::input)[1]");
	static By txt_PolicyNumber=By.xpath("(//label[contains(text(),'Policy Number')]/following::input)[1]");
	static By dd_CedingCompany = By.xpath("(//label[contains(text(),'Ceding Company')]/following::select)[1]");	
	static By txt_EffectiveDate=By.xpath("(//label[contains(text(),'Effective Date')]/following::input)[1]");
	static By txt_ExpirationDate=By.xpath("(//label[contains(text(),'Expiration Date')]/following::input)[1]");
	static By txt_Streetaddress = By.xpath("(//label[contains(text(),'Street address')]/following::input)[1]");	
	static By txt_Zip	= By.xpath("(//label[contains(text(),'Zip Code')]/following::input)[1]");	      
	static By dd_Terms = By.xpath("(//label[contains(text(),'Terms & Conditions (Optional)')]/following::select)[1]");
	static By rd_Peril	= By.xpath("(//td[contains(text(),'Earthquake')]/following::span)[1]");	      
	static By btn_AddNewPolicy=By.xpath("//button[contains(text(),'Add new policy')]");
	
	
	public static Boolean addDetails(String tc, WebDriver driver) throws InterruptedException {
		ActiveRow=GetActiveRow(tc, Sheetname);
		
		String Action=getCellData(Sheetname, "Action", ActiveRow);
		if(Action.equalsIgnoreCase("Nil")){
			return false;
		}
		else{
		waitforElement(btn_AddNewPolicy);
		click(btn_AddNewPolicy,"btn_AddNewPolicy",driver);	
		waitforSeconds(3);
		setText(txt_ProgramSearch, ActiveRow, "Program Name", driver);
		driver.findElement(txt_ProgramSearch).sendKeys(Keys.ENTER);
		waitforSeconds(3);
		String Program=getCellData(Sheetname, "Program Name", ActiveRow);
		driver.findElement(By.xpath("//td[text()='"+Program+"']")).click();
		waitforSeconds(5);
		fillLocation(ActiveRow);
		return true;
		}

	}
	public static void fillLocation(int ActiveRow) {
		setText(txt_NameInsured,ActiveRow,"Name Insured",driver);
		setText(txt_PolicyNumber,ActiveRow,"Policy Number",driver);    	
		selectDd(dd_CedingCompany,ActiveRow, "Ceding Company", driver);
		setDate(txt_EffectiveDate,ActiveRow,"Effective Date",driver);
		setDate(txt_ExpirationDate,ActiveRow,"Expiration Date",driver);
		setText(txt_Zip,ActiveRow,"Zip Code",driver);    
		driver.findElement(txt_Zip).sendKeys(Keys.TAB);
		setText(txt_Streetaddress,ActiveRow,"Street Address",driver);    	
		
		setPolicyPeril("Fire", ActiveRow);	
		setPolicyPeril("Hurricane", ActiveRow);
		setPolicyPeril("Earthquake", ActiveRow);
		setPolicyPeril("Flood", ActiveRow);
		setPolicyPeril("Terrorism", ActiveRow);
		setPolicyPeril("Boiler", ActiveRow);
		
	}

}
