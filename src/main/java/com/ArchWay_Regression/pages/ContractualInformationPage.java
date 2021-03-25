package com.ArchWay_Regression.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ArchWay_Regression.functions.GenericFunctions;



public class ContractualInformationPage extends GenericFunctions{
	//WebDriver driver;
	static String Sheetname="Contractual Information";
	static int row=0,ActiveRow=0;
	static By hdr_ContractualInformation=By.xpath("//li[text()='Contractual Information']");
	static By txt_EstARFPremiumGross=By.xpath("(//span[contains(text(),'Estimated ARF Premium Gross')]/following::input)[1]");
	static By txt_EstARFPremiumNet=By.xpath("(//span[contains(text(),'Estimated ARF Premium Net')]/following::input)[1]");
	static By txt_BoundTechnical=By.xpath("(//label[contains(text(),'Bound To Technical')]/following::input)[1]");
	static By dd_CyberExclusion = By.xpath("(//label[contains(text(),'Cyber Exclusion')]/following::input)[1]");	
	static By dd_SpecialTermination	= By.xpath("(//label[contains(text(),'Special Termination?')]/following::select)[1]");	      
	static By dd_VirusExclusion = By.xpath("(//label[contains(text(),'Virus Exclusion (Optional)')]/following::input)[1]");	
	static By dd_certificate	= By.xpath("(//label[contains(text(),'Certificate')]/following::select)[1]");	      
	static By dd_Terms = By.xpath("(//label[contains(text(),'Terms & Conditions (Optional)')]/following::select)[1]");	
	
	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		int Rowcount = getRowCount(Sheetname);
		for (row=1;row<Rowcount;row++)
		{
			String Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				break;
			}
		}
		click(hdr_ContractualInformation,"hdr_ContractualInformation",driver);
		setText(txt_EstARFPremiumGross,ActiveRow,"Est Premium Gross",driver);
		setText(txt_EstARFPremiumNet,ActiveRow,"Est Premium Net",driver);    	
		setText(txt_BoundTechnical,ActiveRow, "BoundTechnical", driver);
		isSelected("Risk Attachment Type" , "Risk attaching" , true, driver);
		isSelected("Cat basis" , "Per risk" , true, driver);
		isSelected("Continuous Contract" , "No" , true, driver);
		isSelected("Shared & Layered Exclusion" , "Yes" , true, driver);
		isSelected("Expect Stacking IR Same Client" , "No" , true, driver);
		clickRd(ActiveRow, "Risk Attachment Type", driver);
		clickRd(ActiveRow, "Cat basis", driver);
		clickRd(ActiveRow, "Continuous Contract", driver);
		clickRd(ActiveRow, "Shared & Layered Exclusion", driver);
		clickRd(ActiveRow, "Expect Stacking IR Same Client", driver);
		clickRd(ActiveRow, "Capped By Cession", driver);
		selectDd(dd_SpecialTermination, ActiveRow, "Special Termination?", driver);
		clickRd(ActiveRow, "Declaratory Judgement (DJ)", driver);
		clickRd(ActiveRow, "Loss Adjustment Expense (LAE)", driver);
		clickRd(ActiveRow, "Extra Contractual Obligations (ECO)", driver);
		clickRd(ActiveRow, "Losses in Excess of Policy Limit (LEPL)", driver);
		waitforSeconds(2);
		selectNewDd(dd_CyberExclusion, ActiveRow, "Cyber Exclusion", driver);
		selectNewDd(dd_VirusExclusion, ActiveRow, "Virus Exclusion", driver);
		selectDd(dd_certificate, ActiveRow, "Certificate", driver);
		selectDd(dd_Terms, ActiveRow, "Terms & Conditions", driver);
		

	}

}
