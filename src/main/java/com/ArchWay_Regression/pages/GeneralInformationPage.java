package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ArchWay_Regression.functions.GenericFunctions;



public class GeneralInformationPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="General Information";
	static int row=0,ActiveRow=0;
	static By txt_Name=By.xpath("(//label[contains(text(),'Program name')]/following::input)[1]");
	static By txt_ArfNumber=By.xpath("(//label[contains(text(),'ARF Number')]/following::input)[1]"); 
	static By dd_ArfUnderwriter =By.xpath("(//label[contains(text(),'ARF Underwriter')]/following::input)[1]");
	static By dd_UnderwriterBranch =By.xpath("(//label[contains(text(),'Underwriter Branch')]/following::select)[1]");
	static By dd_ParentCompany = By.xpath("(//label[contains(text(),'Parent Company')]/following::input)[1]");
	static By dd_ClientContactUW = By.xpath("(//label[contains(text(),'Client Contact/UW')]/following::input)[1]");
	static By dd_PaperCompany=By.xpath("(//label[contains(text(),'Paper Company')]/following::input)[1]");
	static By txt_EffectiveDate=By.xpath("(//label[contains(text(),'Effective Date')]/following::input)[1]");
	static By txt_ExpirationDate=By.xpath("(//label[contains(text(),'Expiration Date')]/following::input)[1]");
	static By txt_BrokerCommission = By.xpath("//label[contains(text(),'Brokerage Commission') ]/following::input[1]");
	static By txt_CedingCommission = By.xpath("(//label[contains(text(),'Ceding Commission')]/following::input)[1]");
	static By dd_Broker=By.xpath("((//label[text()='Broker'])[2]/following::select)[1]");
	static By dd_MaximumEOPeriod = By.xpath("(//label[contains(text(),'Maximum E&O Period')]/following::select)[1]");
	static By txt_EOdays = By.xpath("(//label[contains(text(),'Please specify')]/following::input)[1]");
	static By dd_ProgramType = By.xpath("(//label[contains(text(),'Program Type')]/following::select)[1]");
	static By dd_BDXOccurrence = By.xpath("(//label[contains(text(),'BDX Occurrence')]/following::select)[1]");
	static By dd_DefaultCountry = By.xpath("(//label[contains(text(),'Default Country')]/following::select)[1]");
	static By dd_DefaultCurrency = By.xpath("(//label[contains(text(),'Default Currency')]/following::select)[1]");		
	static By ch_SPARequest = By.xpath("(//label[contains(text(),'SPA Request from scratch')]/preceding::label)[last()]");
	static By ch_SPAPricing = By.xpath("(//label[contains(text(),'SPA Pricing')]/preceding::label)[last()]");



	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {
		String fetchedValue=null;
		int Rowcount = getRowCount(Sheetname);
		for (row=1;row<Rowcount;row++)
		{
			String Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				break;
			}
		}
		
		setText(txt_Name,ActiveRow,"Program name",driver);
		setText(txt_ArfNumber,ActiveRow,"ARF Number",driver);
		waitforSeconds(2);
		selectNewDd(dd_ArfUnderwriter,ActiveRow, "ARF Underwriter", driver);
		waitforSeconds(3);
		
		selectDd(dd_UnderwriterBranch,ActiveRow, "Underwriter Branch", driver);

		selectNewDd(dd_ParentCompany,ActiveRow, "Parent Company", driver);

		selectNewDd(dd_ClientContactUW,ActiveRow, "Client Contact/UW", driver);
		selectNewDd(dd_PaperCompany,ActiveRow, "Paper Company", driver);
		fetchedValue=clickRd(ActiveRow, "Direct / Broker",true,driver);
		if (fetchedValue.equalsIgnoreCase("Broker")){
			isVisible(txt_BrokerCommission,"Brokerage Commission %",true);
			isVisible(dd_Broker,"Broker",true);
			setText(txt_BrokerCommission,ActiveRow,"Brokerage Commission %",driver);
			selectDd(dd_Broker,ActiveRow, "Broker", driver);
		}
		else if(fetchedValue.equalsIgnoreCase("Direct")) {
			isVisible(txt_BrokerCommission,"Brokerage Commission %",false);
			isVisible(dd_Broker,"Broker",false);
		}
		setText(txt_CedingCommission,ActiveRow,"Ceding Commission %",driver);
		setDate(txt_EffectiveDate,ActiveRow,"Effective Date",driver);
		setDate(txt_ExpirationDate,ActiveRow,"Expiration Date",driver);
		isSelected("Enable E&O" , "Yes" , true, driver);
		fetchedValue=clickRd(ActiveRow, "Enable E&O",true,driver);
		if (fetchedValue.equalsIgnoreCase("Yes")){
			isVisible(dd_MaximumEOPeriod,"Maximum E&O Period",true);
			fetchedValue=selectDd(dd_MaximumEOPeriod,ActiveRow, "Maximum E&O Period",true, driver);
			if(fetchedValue.equalsIgnoreCase("other")){
				isVisible(txt_EOdays,"Please specify (days)",true);
				setText(txt_EOdays, ActiveRow,"E&O days", driver);
			}
			else{
				isVisible(txt_EOdays,"Please specify (days)",false);
			}
		}
		else
			isVisible(dd_MaximumEOPeriod,"Maximum E&O Period",false);
		fetchedValue=selectDd(dd_ProgramType, ActiveRow, "Program Type", true, driver);
		if(fetchedValue.contains("BDX")){
			isVisible(dd_BDXOccurrence,"BDX Occurrence",true);
			selectDd(dd_BDXOccurrence, ActiveRow, "BDX Occurrence", driver);
		}
		else
			isVisible(dd_BDXOccurrence,"BDX Occurrence",false);
		validateDefault(dd_DefaultCountry, "Default Country", "USA", driver);
		validateDefault(dd_DefaultCurrency, "Default Currency", "US dollar", driver);
		isEnabled(dd_DefaultCurrency, "Default Currency", false, driver);
		fetchedValue=selectDd(dd_DefaultCountry, ActiveRow, "Default Country",true, driver);
		validateCurrency(fetchedValue,dd_DefaultCurrency,driver);
		isSelected("Allow other than default currency" , "No" , true, driver);
		fetchedValue=clickRd(ActiveRow, "Allow other than default currency",true,driver);
		if(fetchedValue.equalsIgnoreCase("Yes")){
			isEnabled(dd_DefaultCurrency, "Default Currency", true, driver);
			selectDd(dd_DefaultCurrency, ActiveRow, "Default Currency", driver);
		}
		isSelected("Layer Set Up Type" , "Single Layer" , true, driver);
		isSelected("Multi Location Coverage" , "Scheduled" , true, driver);
		clickRd(ActiveRow, "Layer Set Up Type", driver);
		clickRd(ActiveRow, "Multi Location Coverage", driver);
		clickcb(ActiveRow, "SPA Request from scratch", driver);
		clickcb(ActiveRow, "SPA Pricing", driver);
		
	}
	
}
