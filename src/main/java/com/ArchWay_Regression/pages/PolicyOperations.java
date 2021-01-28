package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class PolicyOperations extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="AddPolicy";
	static int row=0,ActiveRow=0;
	static By tab_Reinsurance=By.xpath("//li[text()='Reinsurance']");
	static By btn_Calculate=By.xpath("(//button[contains(text(),'alculate')])");
	
	
	
	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		ActiveRow=GetActiveRow(tc, Sheetname);
		waitforSeconds(3);
		
		/*driver.findElement(By.xpath("//a[text()='Policies']")).click();
		driver.findElement(By.xpath("//li[text()='All']")).click();*/
		String ProgramName=getCellData(Sheetname,"Program Name",ActiveRow);
		String InsuredName=getCellData(Sheetname,"Name Insured",ActiveRow);
		/*SearchProgramName(ProgramName);
		waitforSeconds(2);
		driver.findElement(By.xpath("(//p[contains(text(),'"+ProgramName+"')])[last()]")).click();
		driver.findElement(By.xpath("((//p[text()='"+InsuredName+"'])[1]/following::i)[2]")).click();*/
		int ActiveRow2=GetActiveRow(tc, "PolicyOperations");
		
		ClickOperation(ProgramName,InsuredName, "Copy",ActiveRow2);
		ClickOperation(ProgramName,InsuredName, "Delete",ActiveRow2);
		waitforSeconds(3);
		//SaveChanges();
	}

}
