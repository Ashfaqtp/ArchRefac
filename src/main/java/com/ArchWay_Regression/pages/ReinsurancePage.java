package com.ArchWay_Regression.pages;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class ReinsurancePage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="AddPolicy";
	static int row=0,ActiveRow=0;
	static By tab_Reinsurance=By.xpath("//li[text()='Reinsurance']");
	static By btn_Calculate=By.xpath("(//button[contains(text(),'alculate')])");
	
	
	
	public static void addDetails(String tc,String Sheetname, WebDriver driver) throws InterruptedException {

		ActiveRow=GetActiveRow(tc, Sheetname);
		waitforSeconds(3);
		click(tab_Reinsurance, "tab_LocationDetails", driver);
		waitforSeconds(2);
		click(btn_Calculate, "btn_Calculate", driver);
		waitforSeconds(3);
		String Action=getCellData(Sheetname,"Action",ActiveRow);
		if(!Action.equalsIgnoreCase("Draft")){
		if(Action.equalsIgnoreCase("Bind")){
		clickButton(Action);
		clickButton(Action);
		}
		else if(Action.equalsIgnoreCase("Decline")){
			clickButton(Action);
			String Reason=getCellData(Sheetname,"DeclineReason",ActiveRow);
			driver.findElement(By.xpath("//label[text()='"+Reason+"']")).click();
			if(Reason.equalsIgnoreCase("Other")){
				String OtherMessage=getCellData(Sheetname,"OtherMessage",ActiveRow);
				driver.findElement(By.xpath("//label[text()='Other']/following::textarea")).sendKeys(OtherMessage);
			}
			clickButton(Action);
			}
		else if(Action.equalsIgnoreCase("SPA")){
			clickButton("Start Special Acceptance process");
			clickButton("Yes, continue");
			clickButton("Submit for special acceptance");
			clickButton("Yes, continue");
			}
		}
		else{
			SaveChanges();
		}
		driver.findElement(By.xpath("//a[text()='Policies']")).click();
		driver.findElement(By.xpath("//li[text()='All']")).click();
		SearchProgramNameWithTC(tc);
		String ProgramName=getCellData(Sheetname,"Program Name",ActiveRow);
		String InsuredName=getCellData(Sheetname,"Name Insured",ActiveRow);
		waitforSeconds(1);
		driver.findElement(By.xpath("(//p[contains(text(),'"+ProgramName+"')])[last()]")).click();
		waitforSeconds(2);
		CheckStatus(Action,InsuredName);
		waitforSeconds(3);
		//SaveChanges();
	}
	public static void validatePremium(String tc, WebDriver driver) throws InterruptedException, IOException {
		int ActiveRow2=GetActiveRow(tc, Sheetname);
		String Rate=getCellData(Sheetname,"Rate",ActiveRow2);
		String TotalTIV=getCellData(Sheetname,"Total TIV",ActiveRow2);
		String AttachmentPoint=getCellData(Sheetname,"Attachment Point",ActiveRow2);
		
		FileInputStream fis = new FileInputStream("C:\\Users\\APoyil\\Desktop\\CalculationFinaps.xlsx");
		 
		 //load the input stream to a workbook object
		 //Use XSSF for (.xlsx) excel file and HSSF for (.xls) excel file
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 
		 //get the sheet from the workbook by index
		 XSSFSheet sheet = wb.getSheet("XsRate");
		 
		 //Count the total number of rows present in the sheet
		 int rowcount = sheet.getLastRowNum();
		 System.out.println(" Total number of rows present in the sheet : "+rowcount);
		 
		 //get column count present in the sheet
		 int colcount = sheet.getRow(1).getLastCellNum();
		 System.out.println(" Total number of columns present in the sheet : "+colcount);
		 ActiveRow2=ActiveRow2-1;
		 sheet.getRow(ActiveRow2).getCell(1).setCellValue(Double.parseDouble(Rate));
		 sheet.getRow(ActiveRow2).getCell(2).setCellValue(Double.parseDouble(TotalTIV));
		 sheet.getRow(ActiveRow2).getCell(3).setCellValue(Double.parseDouble(AttachmentPoint));
		 fis.close();
		 

			//Open an excel to write the data into workbook
			FileOutputStream fos = new FileOutputStream("C:\\Users\\APoyil\\Desktop\\CalculationFinaps.xlsx");
			
			//Write into workbook
			wb.write(fos);
			
			//close fileoutstream
			fos.close();
			/*FormulaEvaluator evaluator =wb.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAll();
			wb.setForceFormulaRecalculation(true);*/
			XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
	}
	public static void validatePremium2(String tc, WebDriver driver) throws InterruptedException, IOException {
		int ActiveRow2=GetActiveRow(tc, Sheetname);
		Double Rate=Double.parseDouble(getCellData(Sheetname,"Rate",ActiveRow2));
		Double TotalTIV=Double.parseDouble(getCellData(Sheetname,"Total TIV",ActiveRow2));
		Double AttachmentPoint=Double.parseDouble(getCellData(Sheetname,"Attachment Point",ActiveRow2));
		Double Layerlimit=TotalTIV-AttachmentPoint;
		Double Premium=Layerlimit*(Rate/100);
		System.out.println(Premium);
	}
}
