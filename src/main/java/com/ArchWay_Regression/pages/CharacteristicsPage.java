package com.ArchWay_Regression.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ArchWay_Regression.functions.GenericFunctions;

public class CharacteristicsPage extends GenericFunctions{

	static String Sheetname="Characteristics";
	static String Testcase=null,Characteristics=null;
	static int row=0,ActiveRow=0,Rowcount=0,Sl_no=0;;
	static By hdr_Characteristics=By.xpath("//li[text()='Characteristics']");
	static By btn_Occupancy=By.xpath("(//td[contains(text(),'Occupancy')]/following::div[@class='pointer'])[1]");
	static By EditOccupancy=By.xpath("(//td[contains(text(),'Occupancy')]/following::button[contains(text(),'Edit')])[1]");
	static By btn_Sprinkler=By.xpath("(//td[contains(text(),'Sprinkler')]/following::div[@class='pointer'])[1]");
	static By EditSprinkler=By.xpath("(//td[contains(text(),'Sprinkler')]/following::button[contains(text(),'Edit')])[1]");
	static By txt_SHORTLABEL=By.xpath("((//label[contains(text(),'SHORT LABEL')])[last()]/following::input)[1]");
	static By txt_Occ_UIlabel=By.xpath("((//label[contains(text(),'SHORT LABEL')])[last()]/following::th[contains(text(),'UI Label')]/following::input)[2]");
	static By dd_Occ_ARFValue=By.xpath("((//label[contains(text(),'SHORT LABEL')])[last()]/following::th[contains(text(),'UI Label')]/following::input)[3]");
	static By btn_AddNewValue=By.xpath("(//button[contains(text(),'ADD NEW VALUE')])[last()]");
	static By btn_AddNewCov=By.xpath("(//button[contains(text(),'ADD NEW COVERAGE')])[last()]");
	static By btn_AddNewShortLabel=By.xpath("(//button[contains(text(),'ADD NEW SHORT LABEL')])[last()]");

	static By txt_Cov_UIlabel=By.xpath("(((//button[contains(text(),'ADD NEW COVERAGE')])[last()])/preceding::input)[last()]");
	static By dd_Cov_ARFValue=By.xpath("(((//button[contains(text(),'ADD NEW COVERAGE')])[last()])/preceding::select)[last()]");
	static By save=By.xpath("//button[text()='Save']");
	static By Addnewcharacteristic=By.xpath("//button[text()='Add new characteristic']");
	static By dd_ArfID = By.xpath("(//label[text()='ARF ID']/following::select)[1]");
	static By dd_Type = By.xpath("(//label[text()='Type']/following::select)[1]");
	static By txt_UILabel = By.xpath("(//label[text()='UI Label']/following::input)[1]");

	static By txt_ValueUILabel = By.xpath("(((//button[contains(text(),'ADD NEW VALUE')])[last()])/preceding::input)[last()-2]");
	static By txt_From = By.xpath("(((//button[contains(text(),'ADD NEW VALUE')])[last()])/preceding::input)[last()-1]");
	static By txt_To = By.xpath("(((//button[contains(text(),'ADD NEW VALUE')])[last()])/preceding::input)[last()]");

	static By txt_UI = By.xpath("(((//button[contains(text(),'ADD NEW VALUE')])[last()])/preceding::input)[last()-1]");
	static By txt_Arf = By.xpath("(((//button[contains(text(),'ADD NEW VALUE')])[last()])/preceding::input)[last()]");
	static By txt_Dependancy = By.xpath("(//label[contains(text(),'Dependencies (optional)')]/following::p)[1]");
	

	public static void addDetails(String tc, WebDriver driver) throws InterruptedException {


		Rowcount = getRowCount(Sheetname);
		click(hdr_Characteristics,"hdr_Characteristics",driver);		

		EditOccupancy(tc);
		floorArea(tc);
		AddOtherCharacterstics(tc);
		EditSprinkler(tc);
		SaveChangesWithWait();
		waitforSeconds(5);
		
	}
	public static void EditOccupancy(String tc) throws InterruptedException {
		int flag=0;
		for (row=1;row<Rowcount;row++)
		{
			Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				if(getCellData(Sheetname,"Characteristics",row).equalsIgnoreCase("Occupancy")){
					Sl_no=Integer.parseInt(getCellData(Sheetname,"Sl_no",row));					
					flag=1;
					int Values_no=0,Cov_no=0;
					if(Sl_no==1){
						click(btn_Occupancy, "btn_Occupancy", driver);
						waitforSeconds(2);
						click(EditOccupancy, "EditOccupancy", driver);
					}
					else
						click(btn_AddNewShortLabel, "AddNewShortLabel", driver);
					setText(txt_SHORTLABEL, ActiveRow, "ShortLabel", driver);

					Values_no=Integer.parseInt(getCellData(Sheetname,"No of Values",row));
					Cov_no=Integer.parseInt(getCellData(Sheetname,"No of Coverages",row));
					for(int i=1;i<=Values_no;i++){
						if(i!=1)
							click(btn_AddNewValue, "Add new value", driver);	
						setText(txt_Occ_UIlabel, ActiveRow, "UILabel"+i, driver);
						selectNewDd(dd_Occ_ARFValue, ActiveRow, "Occupancy_ARFValue"+i, driver);
					}
					for(int i=1;i<=Cov_no;i++){
						if(i!=1)
							click(btn_AddNewCov, "Add new value", driver);	

						setText(txt_Cov_UIlabel, ActiveRow, "Cov_UILabel"+i, driver);
						selectDd(dd_Cov_ARFValue, ActiveRow, "Cov_ARFValue"+i, driver);
					}}
			}
		}
		if (flag==1)
			saveButton();
	}
	public static void floorArea(String tc) {
		int flag=0;
		for (row=1;row<Rowcount;row++)
		{
			Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				Characteristics=getCellData(Sheetname,"Characteristics",row);
				if(Characteristics.equalsIgnoreCase("Floor Area")){
					Sl_no=Integer.parseInt(getCellData(Sheetname,"Sl_no",row));					
					flag=1;
					//int Values_no=0,Cov_no=0;
					if(Sl_no==1){
						click(Addnewcharacteristic, "Add new characteristic", driver);
						selectDd(dd_ArfID, ActiveRow, "Characteristics", driver);
						selectDd(dd_Type, ActiveRow, "Type", driver);
						setText(txt_UILabel, ActiveRow, "Characteristics", driver);

					}
					else
						click(btn_AddNewShortLabel, "AddNewShortLabel", driver);
					setText(txt_SHORTLABEL, ActiveRow, "ShortLabel", driver);

					/*Values_no=Integer.parseInt(getCellData(Sheetname,"No of Values",row));
					for(int i=1;i<=Values_no;i++){
						if(i!=1)
					//click(btn_AddNewValue, "Add new value", driver);*/

					setText(txt_ValueUILabel, ActiveRow, "Characteristics" , driver);
					setText(txt_From, ActiveRow, "From" , driver);
					setText(txt_To, ActiveRow, "To" , driver);
				}

			}
		}
		if (flag==1)
			saveButton();

	}

	public static void AddOtherCharacterstics(String tc) {
		int flag=0;
		for (row=1;row<Rowcount;row++)
		{
			Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				Characteristics=getCellData(Sheetname,"Characteristics",row);
				if(Characteristics.equalsIgnoreCase("Other - N/A")){
					Sl_no=Integer.parseInt(getCellData(Sheetname,"Sl_no",row));					
					flag=1;
					//int Values_no=0,Cov_no=0;
					if(Sl_no==1){
						click(Addnewcharacteristic, "Add new characteristic", driver);
						selectDd(dd_ArfID, ActiveRow, "Characteristics", driver);
						selectDd(dd_Type, ActiveRow, "Type", driver);


					}
					else
						click(btn_AddNewShortLabel, "AddNewShortLabel", driver);
					setText(txt_SHORTLABEL, ActiveRow, "ShortLabel", driver);

					int Values_no = Integer.parseInt(getCellData(Sheetname,"No of Values",row));
					for(int i=1;i<=Values_no;i++){
						if(i!=1)
							click(btn_AddNewValue, "Add new value", driver);

						setText(txt_UI, ActiveRow, "UILabel"+i , driver);
						setText(txt_Arf, ActiveRow, "Occupancy_ARFValue"+i , driver);
					}
				}

			}
		}
		if (flag==1)
			saveButton();

	}
	public static void EditSprinkler(String tc) {
		int flag=0;
		for (row=1;row<Rowcount;row++)
		{
			Testcase=getCellData(Sheetname,"TestCase",row);
			if (Testcase.equalsIgnoreCase(tc)){
				ActiveRow=row;
				Characteristics=getCellData(Sheetname,"Characteristics",row);
				if(Characteristics.equalsIgnoreCase("Sprinkler")){
					Sl_no=Integer.parseInt(getCellData(Sheetname,"Sl_no",row));					
					flag=1;

					click(btn_Sprinkler, "btn_Sprinkler", driver);
					waitforElement(EditSprinkler);
					click(EditSprinkler, "EditSprinkler", driver);
					waitforElement(txt_Dependancy);
					click(txt_Dependancy, "txt_Dependancy", driver);
					String str = getCellData(Sheetname,"Dependencies",row);
					String[] array=str.split(",");
					for(int i=0;i<array.length;i++){
						By dependancy=By.xpath("(//h1[contains(text(),'Edit characteristic')]/following::p[contains(text(),'"+array[i].toString()+"')])[1]");
						waitforElement(dependancy);
						click(dependancy, array[i].toString(), driver);

					}
				}

			}
		}
		if (flag==1)
			saveButton();

	}
}
