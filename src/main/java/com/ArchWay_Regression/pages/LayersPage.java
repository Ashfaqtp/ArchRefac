package com.ArchWay_Regression.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.ArchWay_Regression.functions.GenericFunctions;

public class LayersPage extends GenericFunctions{
	static String Sheetname="Layers";
	static String AdditionalSheetname="Layers-Additional";
	static String Testcase=null,Characteristics=null;
	static int row=0,ActiveRow=0,Add_ActiveRow=0,Rowcount=0,Add_Rowcount=0,Index=0;
	static By hdr_Layers=By.xpath("//li[text()='Layers']");
	static By LayerLimit=By.xpath("(//div[contains(text(),'L1')]/following::input)[1]");
	static By Share=By.xpath("(//div[contains(text(),'L1')]/following::input)[2]");
	static By ARFProgramLimit=By.xpath("(//div[contains(text(),'L1')]/following::input)[3]");
	static By AttachmentPoint=By.xpath("(//div[contains(text(),'L1')]/following::input)[4]");
	static By IRO=By.xpath("(//div[contains(text(),'L1')]/following::input)[5]");
	static By PricingApproach=By.xpath("(//div[contains(text(),'L1')]/following::select)[1]");
	static By Expand=By.xpath("//div[contains(text(),'L1')]");
	static By F_Visble=By.xpath("(//div[text()='Visible']/following::label)[1]");
	static By dd_HUExclusion=By.xpath("(//p[contains(text(),'Hurricane (HU)')]/following::select)[1]");
	static By dd_EQExclusion=By.xpath("(//p[contains(text(),'Earthquake (EQ)')]/following::select)[1]");
	static By dd_BExclusion=By.xpath("(//p[contains(text(),'Boiler (B)')]/following::select)[1]");
	static By dd_TExclusion=By.xpath("(//p[contains(text(),'Terrorism (T)')]/following::select)[1]");
	static By dd_FLExclusion=By.xpath("(//p[contains(text(),'Flood (FL)')]/following::select)[1]");
	
	static By dd_Terrorism=By.xpath("(//label[contains(text(),'Terrorism Type')]/following::select)[1]");
	static By dd_FFTT=By.xpath("(//label[contains(text(),'Fire Following Terrorism Type')]/following::select)[1]");
	static By txt_MinimumRiskPremium=By.xpath("(//span[contains(text(),'Minimum Risk Premium')]/following::input)[1]");
	static By dd_GUP=By.xpath("//select[contains(.,'Select oneNet onlyGross onlyBoth Net and Gross')]");
	static By Generate=By.xpath("//button[contains(.,'Generate')]");
	static By dd_ArfID = By.xpath("(//label[text()='ARF ID']/following::select)[1]");
	static By dd_Type = By.xpath("(//label[text()='Type']/following::select)[1]");
	static By Addnewcharacteristic=By.xpath("//button[text()='Add new layer characteristic']");
	static By btn_AddNewShortLabel=By.xpath("(//button[contains(text(),'ADD NEW SHORT LABEL')])[last()]");
	static By txt_SHORTLABEL=By.xpath("((//label[contains(text(),'SHORT LABEL')])[last()]/following::input)[1]");
	static By txt_State=By.xpath("((//p[contains(.,'State')])[last()]/following::input)[1]");
	static By txt_from=By.xpath("((//p[text()='Values'])[last()]/following::input)[2]");
	static By txt_to=By.xpath("((//p[text()='Values'])[last()]/following::input)[3]");
	static By AllCountries=By.xpath("(//button[text()='Include all counties'])[1]");
	static By OptionUpdate=By.xpath("(//p[text()='Unique Pricing Combinations']/following::i)[2]");
	static By Update=By.xpath("//p[text()='Unique Pricing Combinations']/following::button[text()='Update']");
	static By YesUpdate=By.xpath("//button[text()='Yes, update']");
	static By Regenerate=By.xpath("//p[text()='Unique Pricing Combinations']/following::button[text()='Regenerate']");
	static By YesRegenerate=By.xpath("//button[text()='Yes, regenerate']");
	//static By FilterButton=By.xpath("(//p[text()='Unique Pricing Combinations']/following::i[@class='fas fa-filter'])[1]");
	static By FilterButton=By.xpath("(//i[@class='fas fa-filter'])[1]");
	static By FilterButton2=By.xpath("(//i[@class='fas fa-filter'])[2]");
	
	static By Rate=By.xpath("(//th[text()='Rate/Stop rate']/following::input)[1]");
	static By Rateoption=By.xpath("(//th[text()='Rate/Stop rate']/following::i)[1]");
	static By CopytoAll=By.xpath("(//th[text()='Rate/Stop rate']/following::button[text()='Copy to all'])[1]");
	static By YesCopy=By.xpath("//button[text()='Yes, copy']");
	static By txt_PricingRegion=By.xpath("(//p[text()='PRICE COMBO'])[2]/following::td[contains(.,'Farm')][1]");
	static By PricingRegion=By.xpath("(//p[text()='PRICE COMBO'])[2]/following::td[contains(.,'Farm')][1]/preceding::span[1]");
	static By search=By.xpath("(//button[text()='Search'])[1]");
	static By search2=By.xpath("(//button[text()='Search'])[2]");
	static By FloodZoneExclusions = By.xpath("(//label[text()='Flood Zone Exclusions']/following::select)[1]");
	static By AddNewRule=By.xpath("//button[text()='Add new rule']");
	static By CharacteristicsPerils = By.xpath("(//label[text()='Characteristics & Perils']/following::div)[1]");
	static By CharacteristicsPerils_Occupancy = By.xpath("(//label[contains(text(),'Characteristics & Perils')]/following::*[contains(text(),'Occupancy')])[1]");
	static By Values = By.xpath("(//label[text()='Values']/following::div)[1]");
	static By Values_farm = By.xpath("(//label[contains(text(),'Values')]/following::div[contains(text(),'Farm')])[1]");
	static By Type_rules = By.xpath("(//label[text()='Type of rules']/following::select)[1]");
	static By Type_Modifiers = By.xpath("(//label[text()='Type of modifier']/following::select)[1]");
	static By Modifiervalue = By.xpath("(//label[text()='Modifier value']/following::input)[1]");
	static By FilterSelect = By.xpath("((//th[contains(text(),'CHARACTERISTICS')]/following::p[text()='farm'])[1]/preceding::span)[last()]");

	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {

		Rowcount = getRowCount(Sheetname);
		for (row=1;row<=Rowcount+1;row++)
		{
			String Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				break;
			}
		}
		waitforSeconds(5);
		click(hdr_Layers,"hdr_Layers",driver);
		setPeril("F", ActiveRow);
		setPeril("HU", ActiveRow);
		setPeril("EQ", ActiveRow);
		/*driver.findElement(hdr_Layers).sendKeys(Keys.CONTROL,"-");
		driver.findElement(hdr_Layers).sendKeys(Keys.CONTROL,"-");
		driver.findElement(hdr_Layers).sendKeys(Keys.CONTROL,"-");
		setPeril("FL", ActiveRow);*/
		setPeril("B", ActiveRow);
		setPeril("T", ActiveRow);
		setPeril("FL", ActiveRow);
		selectDd(FloodZoneExclusions, ActiveRow, "Flood Zone Exclusions", driver);
		setText(LayerLimit, ActiveRow, "LAYER LIMIT" , driver);
		setText(Share, ActiveRow, "SHARE %" , driver);
		setText(ARFProgramLimit, ActiveRow, "ARF PROGRAM LIMIT" , driver);
		setText(AttachmentPoint, ActiveRow, "ATTACHMENT POINT" , driver);
		setText(IRO, ActiveRow, "IRO" , driver);
		selectDd(PricingApproach, ActiveRow, "PRICING APPROACH", driver);
		ExpandLayer("L1");
		selectDd(dd_EQExclusion, ActiveRow, "EQ Exclusion", driver);
		waitforSeconds(2);
		selectDd(dd_HUExclusion, ActiveRow, "HU Exclusion", driver);
		selectDd(dd_BExclusion, ActiveRow, "B Exclusion", driver);
		selectDd(dd_TExclusion, ActiveRow, "T Exclusion", driver);
		selectDd(dd_FLExclusion, ActiveRow, "FL Exclusion", driver);
		selectDd(dd_Terrorism, ActiveRow, "Terrorism Type", driver);
		selectDd(dd_FFTT, ActiveRow, "Fire Following Terrorism Type", driver);
		setText(txt_MinimumRiskPremium, ActiveRow, "Minimum Risk Premium" , driver);
		SaveChangesWithWait();
		waitforSeconds(3);
		click(Generate, "Generate", driver);
		ShowItemsInTable(100);
		waitforSeconds(3);
		if(getCellData(Sheetname,"TIV Range",ActiveRow).equalsIgnoreCase("Yes")){
			AddTIV(tc);
			SaveChangesWithWait();
		}
		if(getCellData(Sheetname,"Pricing Region",ActiveRow).equalsIgnoreCase("Yes")){
			AddPricingRegion(tc);
			SaveChangesWithWait();
		}
		
		waitforSeconds(5);
		driver.findElement(OptionUpdate).click();
		waitforSeconds(3);
		/*driver.findElement(Update).click();
		waitforSeconds(1);
		driver.findElement(YesUpdate).click();
		waitforSeconds(3);
		driver.findElement(OptionUpdate).click();
		waitforSeconds(1);*/
		driver.findElement(Regenerate).click();
		waitforSeconds(1);
		driver.findElement(YesRegenerate).click();
		
		
		waitforSeconds(4);
		AddRatesWithFilter(tc);
		SaveChangesWithWait();
		AddNewRule(tc);
		SaveChangesWithWait();
	}

	public static void AddTIV(String tc) {
		int flag=0;
		Add_Rowcount = getRowCount(AdditionalSheetname);
		for (row=1;row<=Add_Rowcount+1;row++)
		{
			Testcase=getCellData(AdditionalSheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				Add_ActiveRow=row;
				Characteristics=getCellData(AdditionalSheetname,"Characteristics",row);
				if(Characteristics.equalsIgnoreCase("TIV Range")){
					Index=Integer.parseInt(getCellData(AdditionalSheetname,"Index",row));					
					flag=1;
					//int Values_no=0,Cov_no=0;
					if(Index==1){
						click(Addnewcharacteristic, "Add new layer characteristic", driver);
						selectDd(dd_ArfID, Add_ActiveRow, "Characteristics", driver);
					}
					else
						click(btn_AddNewShortLabel, "AddNewShortLabel", driver);
					setText(txt_SHORTLABEL, Add_ActiveRow, "Short Label", driver);
					setText(txt_from, Add_ActiveRow, "From", driver);
					setText(txt_to, Add_ActiveRow, "To", driver);
					
				}
			}

		}
		if (flag==1)
			saveButton();
		
	}
	public static void AddPricingRegion(String tc) throws InterruptedException {
		int flag=0;
		Add_Rowcount = getRowCount(AdditionalSheetname);
		for (row=1;row<=Add_Rowcount+1;row++)
		{
			Testcase=getCellData(AdditionalSheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				Add_ActiveRow=row;
				Characteristics=getCellData(AdditionalSheetname,"Characteristics",Add_ActiveRow);
				if(Characteristics.equalsIgnoreCase("Other - N/A")){
					Index=Integer.parseInt(getCellData(AdditionalSheetname,"Index",Add_ActiveRow));					
					flag=1;
					//int Values_no=0,Cov_no=0;
					if(Index==1){
						click(Addnewcharacteristic, "Add new layer characteristic", driver);
						selectDd(dd_ArfID, Add_ActiveRow, "Characteristics", driver);
					}
					else
						click(btn_AddNewShortLabel, "AddNewShortLabel", driver);
					setText(txt_SHORTLABEL, Add_ActiveRow, "Short Label", driver);
					String str = getCellData(AdditionalSheetname,"State",Add_ActiveRow);
					String[] array=str.split(",");
					for(int i=0;i<array.length;i++){
						driver.findElement(txt_State).sendKeys(array[i]);
						driver.findElement(txt_State).sendKeys(Keys.ENTER);
						waitforSeconds(2);
						click(AllCountries, "All Countries Included", driver);
						waitforSeconds(2);
						driver.findElement(txt_State).sendKeys(Keys.CONTROL,"a");
						driver.findElement(txt_State).sendKeys(Keys.BACK_SPACE);
					}
				}
			}

		}
		if (flag==1)
			saveButton();
		
	}
	public static void AddRatesWithFilter(String tc) throws InterruptedException {
		int flag=0;
		Add_Rowcount = getRowCount(AdditionalSheetname);
		driver.findElement(Rate).sendKeys("0.123");
		waitforSeconds(1);
		driver.findElement(Rateoption).click();
		waitforSeconds(1);
		driver.findElement(CopytoAll).click();
		waitforSeconds(1);
		driver.findElement(YesCopy).click();
		waitforSeconds(1);
		for (row=1;row<=Add_Rowcount+1;row++)
		{
			Testcase=getCellData(AdditionalSheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				Add_ActiveRow=row;
				Characteristics=getCellData(AdditionalSheetname,"Characteristics",Add_ActiveRow);
				if(Characteristics.equalsIgnoreCase("Rule")){
					
					waitforSeconds(3);
					driver.findElement(FilterButton).click();
					String FilterData=getCellData(AdditionalSheetname,"Filter",Add_ActiveRow);
					String[] array=FilterData.split(",");
					
					for(int i=0;i<array.length;i++){
						waitforSeconds(1);
						//if(array[i].length()>3 && array[i].substring(0, 4).equalsIgnoreCase("TIV_"))
						if(array[i].startsWith("TIV_"))
							driver.findElement(By.xpath("((//p[text()='TIV'])[1]/following::label)['"+array[i].substring(4)+"']")).click();
						else
							driver.findElement(By.xpath("(//p[text()='PRICE COMBO']/following::p[contains(.,'"+array[i]+"')]/preceding::label)[last()]")).click();
					}
					waitforSeconds(2);
					click(search, "Search", driver);
					waitforSeconds(3);
					driver.findElement(FilterButton).click();
					setText(Rate, Add_ActiveRow, "Rate", driver);
					waitforSeconds(1);
					driver.findElement(Rateoption).click();
					waitforSeconds(1);
					driver.findElement(CopytoAll).click();
					waitforSeconds(1);
					driver.findElement(YesCopy).click();
					waitforSeconds(3);
					driver.findElement(FilterButton).click();
					
					for(int i=0;i<array.length;i++){
						waitforSeconds(1);
						if(array[i].startsWith("TIV_"))
							driver.findElement(By.xpath("((//p[text()='TIV'])[1]/following::label)['"+array[i].substring(4)+"']")).click();
						else
							driver.findElement(By.xpath("(//p[text()='PRICE COMBO']/following::p[contains(.,'"+array[i]+"')]/preceding::label)[last()]")).click();
					}
					driver.findElement(FilterButton).click();
					
				}
			}

		}
		if (flag==1)
			saveButton();
		
	}

	public static void AddNewRule(String tc) throws InterruptedException {
		Add_Rowcount = getRowCount(AdditionalSheetname);
		for (row=1;row<=Add_Rowcount+1;row++)
		{
			Testcase=getCellData(AdditionalSheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				Add_ActiveRow=row;
				Characteristics=getCellData(AdditionalSheetname,"Characteristics",Add_ActiveRow);
				String TypeOfRules=getCellData(AdditionalSheetname,"Type of rules",Add_ActiveRow);
				if(Characteristics.equalsIgnoreCase("Rule") && TypeOfRules!=null){
					
					waitforSeconds(4);
					click(AddNewRule, "AddNewRule", driver);
					waitforSeconds(3);
					//*driver.findElement(FilterButton2).click();
					String FilterData=getCellData(AdditionalSheetname,"Filter",Add_ActiveRow);
					String[] array=FilterData.split(",");
					
					for(int i=0;i<array.length;i++){
						waitforSeconds(1);
						
						//if(array[i].length()>3 && array[i].substring(0, 4).equalsIgnoreCase("TIV_"))
						if(array[i].startsWith("TIV_"))
						{
							for (int row2=1;row2<=Add_Rowcount+1;row2++)
							{
								Testcase=getCellData(AdditionalSheetname,"TestCase",row2);
								if (Testcase.equalsIgnoreCase(tc)){
									
									Characteristics=getCellData(AdditionalSheetname,"Characteristics",row2);
									if(Characteristics.equalsIgnoreCase("TIV Range")){
										//Index=Integer.parseInt(getCellData(AdditionalSheetname,"Index",row));
										String Index2=getCellData(AdditionalSheetname,"Index",row2);
										if(Index2.equalsIgnoreCase(array[i].substring(4))){
											String Tiv=getCellData(AdditionalSheetname,"Short Label",row2);
											driver.findElement(By.xpath("((//th[contains(text(),'CHARACTERISTICS')]/following::p[text()='"+Tiv+"'])[1]/preceding::span)[last()]")).click();
											break;	
										}
									}
								}
							}
						}
							
						else
							driver.findElement(By.xpath("((//th[contains(text(),'CHARACTERISTICS')]/following::p[text()='"+array[i]+"'])[1]/preceding::span)[last()]")).click();
					
				}
					/*
					click(search2, "Search", driver);
					waitforSeconds(3);
					driver.findElement(FilterButton2).click();
					for(int j=1;j>0;j++){
					try{
						driver.findElement(By.xpath("(//p[text()='PRICE COMBO'])[2]/following::td[contains(.,'"+array[1]+"')]["+j+"]")).click();
						driver.findElement(By.xpath("(//p[text()='PRICE COMBO'])[2]/following::td[contains(.,'"+array[1]+"')]["+j+"]/preceding::span[1]")).click();
					}
					catch (Exception e)
					{
						break;
					}
					}
					*/
					waitforSeconds(1);
					click(CharacteristicsPerils, "CharacteristicsPerils", driver);
					click(CharacteristicsPerils_Occupancy, "CharacteristicsPerils_Occupancy", driver);
					waitforSeconds(1);
					click(Values, "Values", driver);
					click(Values_farm, "Values_farm", driver);
					
					waitforSeconds(1);
					selectDd(Type_rules, Add_ActiveRow, "Type of rules", driver);
					selectDd(Type_Modifiers, Add_ActiveRow, "Type of modifier", driver);
					setText(Modifiervalue, Add_ActiveRow, "Modifier value", driver);
					saveButton();
				}
				}
			}

		}
		
		
	

}
