package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.PageBase;
import utilities.Helper;

public class TestBase 
{

	public static WebDriver driver;
	PageBase PageBaseObj;


	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver (@Optional("chrome") String browserName) 
	{
		
		PageBaseObj = new PageBase(driver);
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.navigate().to("https://www.saucedemo.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void stopDriver () 
	{
		driver.quit();
	}

	
	//Take screenshot when test case fail and add it to screenshot folder
	@AfterMethod
	public void screenshotOnFailure (ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			System.out.println("Failed");
			System.out.println("Taking Screenshot...");
			Helper.captureScreenshot(driver, result.getName());
		}

	}
	
}
