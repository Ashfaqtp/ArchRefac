package com.ArchWay_Regression.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public String testName;
	public FileInputStream fis = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	public static Properties prop;
	@BeforeSuite
	public void setUp() throws IOException {
		
		String projectPath = null;
		String filepath = null;

		projectPath = System.getProperty("user.dir");
		filepath = projectPath+"\\src\\main\\java\\com\\ArchWay_Regression\\BaseClass\\config.properties";
		prop = readPropertiesFile(filepath);
		//filepath = projectPath+"\\src\\main\\java\\com\\ArchWay_Regression\\BaseClass\\Confidential.properties";
		//prop2 = readPropertiesFile(filepath);		
		//htmlReporter = new ExtentHtmlReporter("\\Reports\\LastRunReport.html");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		/*DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);*/
		htmlReporter = new ExtentHtmlReporter(projectPath + "\\Reports\\Report_" + timeStamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		fis = new FileInputStream(projectPath + "\\ExcelDB.xlsx");
		workbook = new XSSFWorkbook(fis);
		fis.close();

	}


	@BeforeMethod
	public WebDriver initialisation(Method method)  {
		testName = method.getName();	
		logger = extent.createTest(testName);
		System.out.println(testName);

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Common Files\\Selenium\\FinalJars\\chromedriver.exe");
		driver = new ChromeDriver();
		/*System.setProperty("webdriver.ie.driver", "C:\\Selenium\\FinalJars\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		logger.log(Status.PASS, "Opened url");

		return driver;
	}
	public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }

	@AfterMethod
	public void EndOfTestcase(){	
		//driver.close();
	}
	@AfterSuite
	public void tearDown(){
		extent.flush();
	}
}

