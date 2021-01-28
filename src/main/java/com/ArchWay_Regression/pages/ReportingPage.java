package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class ReportingPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="Reporting";
	static int row=0,ActiveRow=0;
	static By tab_Reports=By.xpath("//span[text()='Reports']");
	static By ExcludeAll=By.xpath("//span[text()='Exclude all']");
	static By IncludeAll=By.xpath("//span[text()='Include all']");
	static By EffDate_from=By.xpath("(//label[text()='Effective date from']/following::input)[1]");
	static By EffDate_to=By.xpath("(//label[text()='Effective date to']/following::input)[1]");
	static By ExpDate_from=By.xpath("(//label[text()='Expiration date from']/following::input)[1]");
	static By ExpDate_to=By.xpath("(//label[text()='Expiration date to']/following::input)[1]");
	static By InputDate_from=By.xpath("(//label[text()='Input date from']/following::input)[1]");
	static By InputDate_to=By.xpath("(//label[text()='Input date to']/following::input)[1]");
	static By PolicyStatus=By.xpath("(//label[text()='Policy status']/following::select)[1]");
	
	
	
	
	
	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		ActiveRow=GetActiveRow(tc, Sheetname);
		waitforSeconds(3);
		GenericFunctions.NavigateToPage("Approved", driver);
		String ProgramName=SearchProgramNameWithTC(tc);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::div[contains(@class,'pointer')])[last()]")).click();
		waitforSeconds(1);
		driver.findElement(By.xpath("(//td[text()='"+ProgramName+"']/following::button[contains(.,'Edit Program')])[last()]")).click();
		waitforSeconds(2);
		NavigateToPage("Reporting", driver);
		ActiveRow=GetActiveRow(tc, Sheetname);
		waitforSeconds(3);
		click(ExcludeAll, "ExcludeAll", driver);
		waitforSeconds(2);
		click(IncludeAll, "IncludeAll", driver);
		SaveChanges();
		
		click(tab_Reports, "tab_Reports", driver);
		
		setText(EffDate_from, ActiveRow, "Effective date from", driver);
		setText(EffDate_to, ActiveRow, "Effective date to", driver);
		setText(ExpDate_from, ActiveRow, "Expiration date from", driver);
		setText(ExpDate_to, ActiveRow, "Expiration date to", driver);
		setText(InputDate_from, ActiveRow, "Input date from", driver);
		setText(InputDate_to, ActiveRow, "Input date to", driver);
		selectDd(PolicyStatus, ActiveRow, "Policy status", driver);
		clickButton("Generate report");
		
		
}}
