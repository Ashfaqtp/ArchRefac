package com.ArchWay_Regression.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ArchWay_Regression.functions.GenericFunctions;

public class Billing extends GenericFunctions {
	
	public static void MasterBills(String tc, WebDriver driver) throws InterruptedException {
	By btn_MasterBilling=By.xpath("//a[text()='Master Bills']");
	By lbl_Date=By.xpath("//th[text()='Date Generated']");
	By lbl_From=By.xpath("//th[text()='From']");
	By lbl_To=By.xpath("//th[text()='To']");
	waitforSeconds(1);
	driver.findElement(btn_MasterBilling).click();
	waitforSeconds(3);
	isVisible(lbl_Date, "Date Generated", true);
	isVisible(lbl_From, "From", true);
	isVisible(lbl_To, "To", true);
	
}}
