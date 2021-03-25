package com.ArchWay_Regression.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.ArchWay_Regression.BaseClass.BaseClass;
import com.ArchWay_Regression.functions.GenericFunctions;
import com.ArchWay_Regression.pages.ContractualInformationPage;
import com.ArchWay_Regression.pages.GeneralInformationPage;
import com.ArchWay_Regression.pages.CharacteristicsPage;
import com.ArchWay_Regression.pages.LayersPage;
import com.ArchWay_Regression.pages.ReinsurancePage;
import com.ArchWay_Regression.pages.ReportingPage;
import com.ArchWay_Regression.pages.UsersAndCommunicationPage;

public class AddProgram extends BaseClass{



	@Test(enabled=true)
	public void TCBundle1() throws InterruptedException{
		String tc="TC_Bundle1";	
		GenericFunctions.AddProgram();
		GeneralInformationPage.addDetails(tc, driver);
		ContractualInformationPage.addDetails(tc, driver);
		CharacteristicsPage.addDetails(tc, driver);
		LayersPage.addDetails(tc, driver);
		//GenericFunctions.GotoPrograms();
		//GenericFunctions.DraftToPendingApproval(tc, driver);
		/*GenericFunctions.PendingApprovaltoUWApproved(tc, driver);
		GenericFunctions.GotoPrograms();
		GenericFunctions.CreateTestPolicy(tc, driver);*/
		//GenericFunctions.GotoPrograms();
		//GenericFunctions.UWApprovedtoApproved(tc, driver);
	}
	@Test(enabled=false)
	public void TC2() throws InterruptedException, IOException{
		String tc="TC_Bundle1";	
		GenericFunctions.InitialSteps();
		
		GenericFunctions.ProgramsPage();
		
		
		GenericFunctions.DeleteFromDraft(tc,5, driver);
		
		//ReinsurancePage.validatePremium2(tc,driver);
		/*GenericFunctions.InitialSteps();
		
		GenericFunctions.ProgramsPage();
		GenericFunctions.CreateTestPolicy(tc, driver);*/
		//GenericFunctions.DeleteFromDraft(tc, driver);
		//GenericFunctions.DeclineFromUWApproved(tc, driver);
		//GenericFunctions.DeclineFromPendingApproval(tc, driver);
		//UsersAndCommunicationPage.addDetails(tc, driver);
		//ReportingPage.addDetails(tc, driver);
	}
}
