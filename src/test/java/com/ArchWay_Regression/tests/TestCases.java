package com.ArchWay_Regression.tests;

import org.testng.annotations.Test;

import com.ArchWay_Regression.BaseClass.BaseClass;
import com.ArchWay_Regression.functions.GenericFunctions;
import com.ArchWay_Regression.pages.Billing;
import com.ArchWay_Regression.pages.LocationDetailsPage;
import com.ArchWay_Regression.pages.LocationInformationPage;
import com.ArchWay_Regression.pages.PolicyOperations;
import com.ArchWay_Regression.pages.ReinsurancePage;

public class TestCases extends BaseClass{


/* **********Finaps-1210*************** */
	@Test(enabled=true)
	public void TCBilling() throws InterruptedException{
		String tc="TC_Billing1";	
		
		GenericFunctions.InitialSteps();
		GenericFunctions.BillingPage();
		Billing.MasterBills(tc, driver);
		
	}
	/* **********Finaps-1378*************** */
	@Test(enabled=true)
	public void TCLayers() throws InterruptedException{
		String tc="TC_Billing1";	
		
		GenericFunctions.InitialSteps();
		GenericFunctions.BillingPage();
		Billing.MasterBills(tc, driver);
		
	}

	
}
