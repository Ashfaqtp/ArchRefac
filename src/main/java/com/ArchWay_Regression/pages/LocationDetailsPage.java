package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class LocationDetailsPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="AddPolicy";
	static int row=0,ActiveRow=0;
	static By tab_LocationDetails=By.xpath("//li[text()='Location Details']");
	static By dd_Occupancy=By.xpath("(//label[contains(text(),'Occupancy')]/following::select)[1]");
	static By dd_Construction=By.xpath("(//label[contains(text(),'Construction')]/following::select)[1]");
	static By txt_ProtectionClass = By.xpath("(//label[contains(text(),'Protection Class')]/following::input)[1]");	
	static By dd_Sprinkler=By.xpath("(//label[contains(text(),'Sprinkler')]/following::select)[1]");
	static By txt_FloorArea=By.xpath("(//label[contains(text(),'Floor Area')]/following::input)[1]");
	static By dd_Other = By.xpath("(//label[contains(text(),'Other')]/following::select)[1]");	
	static By txt_Cov1	= By.xpath("(//label[contains(text(),'Coverages')]/following::input)[1]");	      
	static By txt_Cov2 = By.xpath("(//label[contains(text(),'Coverages')]/following::input)[2]");
	
	
	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		ActiveRow=GetActiveRow(tc, Sheetname);
		click(tab_LocationDetails, "tab_LocationDetails", driver);
		String Occupancy=selectDd(dd_Occupancy,ActiveRow, "Occupancy",true, driver);
		selectDd(dd_Construction,ActiveRow, "Construction", driver);
		setText(txt_ProtectionClass,ActiveRow,"Protection Class",driver);    
		selectDd(dd_Sprinkler,ActiveRow, "Sprinkler", driver);
		setText(txt_FloorArea,ActiveRow,"Floor Area",driver);    
		selectDd(dd_Other,ActiveRow, "Other", driver);
		if(Occupancy!=""){
		setText(txt_Cov1,ActiveRow,"Cov1",driver);    	
		setText(txt_Cov2,ActiveRow,"Cov2",driver);    	
		} 
		SaveChanges();
	}

}
