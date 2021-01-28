package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class UsersAndCommunicationPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="UserCommunication";
	static int row=0,ActiveRow=0;
	static By tab_Reinsurance=By.xpath("//li[text()='Reinsurance']");
	static By Expand_ClientUnderwriters=By.xpath("(//p[text()='Client Underwriters']/preceding::i)[last()]");
	static By Expand_ClientManagers=By.xpath("(//p[text()='Client Managers']/preceding::i)[last()]");
	static By Expand_ArchUnderwriters=By.xpath("(//p[text()='Arch Underwriters']/preceding::i)[last()]");
	static By Email_ClientUnderwriters=By.xpath("(//p[text()='Client Underwriters']/following::span)[1]");
	static By Email_ClientManagers=By.xpath("(//p[text()='Client Managers']/following::span)[1]");
	static By Email_ArchUnderwriters=By.xpath("(//p[text()='Client Underwriters']/following::span)[1]");
	static By SearchUser=By.xpath("(//div[text()='User']/following::input)[1]");
	static By SelectUser=By.xpath("(//div[text()='User']/following::span)[2]");
	static By CManager=By.xpath("//label[text()='Client Manager']");
	static By Arch=By.xpath("//label[text()='Arch']");
	
	
	
	
	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		ActiveRow=GetActiveRow(tc, Sheetname);
		waitforSeconds(3);
		GenericFunctions.NavigateToPage("Approved", driver);
		String ProgramName=SearchProgramNameWithTC(tc);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Edit Program')])[last()]")).click();
		waitforSeconds(2);
		NavigateToPage("Users & Communication", driver);
		
		String User=getCellData(Sheetname,"Client Underwriters",ActiveRow);
		if(!User.equalsIgnoreCase("")){
			clickButton("Add new user");
			waitforSeconds(2);	
			clickButton("Next");
			waitforSeconds(20);
			driver.findElement(SearchUser).sendKeys(User);
			driver.findElement(SearchUser).sendKeys(Keys.ENTER);
			waitforSeconds(4);
			click(SelectUser, User, driver);
			clickButton("Add");
		}
		String User2=getCellData(Sheetname,"Client Managers",ActiveRow);
		if(!User2.equalsIgnoreCase("")){
			clickButton("Add new user");
			waitforSeconds(2);	
			clickButton("Next");
			waitforSeconds(2);
			click(CManager, "CManager", driver);
			waitforSeconds(10);
			driver.findElement(SearchUser).sendKeys(User2);
			driver.findElement(SearchUser).sendKeys(Keys.ENTER);
			waitforSeconds(4);
			
			click(SelectUser, User2, driver);
			clickButton("Add");
		}
		String User3=getCellData(Sheetname,"Arch Underwriters",ActiveRow);
		if(!User3.equalsIgnoreCase("")){
			clickButton("Add new user");
			waitforSeconds(2);
			click(Arch, "Arch", driver);
			clickButton("Next");
			waitforSeconds(8);
			driver.findElement(SearchUser).sendKeys(User3);
			driver.findElement(SearchUser).sendKeys(Keys.ENTER);
			waitforSeconds(4);
			
			click(SelectUser, User3, driver);
			clickButton("Add");
		}
		
		
		
}}
