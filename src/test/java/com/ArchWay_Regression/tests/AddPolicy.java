package com.ArchWay_Regression.tests;

import org.testng.annotations.Test;

import com.ArchWay_Regression.BaseClass.BaseClass;
import com.ArchWay_Regression.functions.GenericFunctions;
import com.ArchWay_Regression.pages.LocationDetailsPage;
import com.ArchWay_Regression.pages.LocationInformationPage;
import com.ArchWay_Regression.pages.PolicyOperations;
import com.ArchWay_Regression.pages.ReinsurancePage;

public class AddPolicy extends BaseClass{



	@Test(enabled=true)
	public void TCBundle1() throws InterruptedException{
		String tc="TC_Bundle1";	
		
		GenericFunctions.AddPolicy();
		Boolean Action=LocationInformationPage.addDetails(tc, driver);
		if(Action==true){
		LocationDetailsPage.addDetails(tc, driver); 		
		ReinsurancePage.addDetails(tc,"AddPolicy", driver); 
		}
		PolicyOperations.addDetails(tc, driver);
	}

	
}
