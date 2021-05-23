package tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.InverntoryPage;
import pages.UserLoginPage;

public class UserLoginTest extends TestBase
{
	String actualTitle , expectedTitle , error;
	UserLoginPage LogPage;
	InverntoryPage InvPage;
	static final String RightUName= "standard_user" ;
	static final String RightPass = "secret_sauce";

	@Test(priority = 1)
	public void SuccessLogin() 
	{
		LogPage = new UserLoginPage(driver);
		LogPage.userLogin(RightUName , RightPass);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		actualTitle = driver.getCurrentUrl();
		expectedTitle = "https://www.saucedemo.com/inventory.html";
		Assert.assertTrue(expectedTitle.equals(actualTitle));
	}

	@Test(dependsOnMethods = "SuccessLogin" , priority = 2)
	public void LoggedUserCanLogout() 
	{
		LogPage.UserLogout();
		LogPage.ClearTxt();
	}

	@Test (dependsOnMethods = "LoggedUserCanLogout"  , priority = 3)
	public void passwordShouldBeRequired() 
	{
		LogPage.userLogin("RightUName", "");
		error = LogPage.getError();
		Assert.assertEquals(error,"Epic sadface: Password is required","Error message is not correct");
		LogPage.ClearTxt();
	}

	@Test (dependsOnMethods = "LoggedUserCanLogout" ,priority = 4)
	public void lockedUser() 
	{
		LogPage.ClearTxt();
		LogPage.userLogin("locked_out_user", "secret_sauce");
		String error = LogPage.getError();
		assertEquals(error, "Epic sadface: Sorry, this user has been locked out.","Error message is not correct");
		LogPage.ClearTxt();
	}

	@Test ( priority = 5)
	public void userNameShouldBeRequired()
	{
		LogPage = new UserLoginPage(driver);
		LogPage.userLogin("", "secret_sauce");
		String error = LogPage.getError();
		assertEquals(error, "Epic sadface: Sorry, this user has been locked out.","Error message is not correct");
		LogPage.ClearTxt();

	}

	@Test (dependsOnMethods = "LoggedUserCanLogout"  , priority = 6)
	public void incorrectPassword() 
	{
		LogPage.userLogin("RightUName", "mmm");
		String error = LogPage.getError();
		assertEquals(error, "Epic sadface: Username and password do not match any user in this service","Error message is not correct");
		LogPage.ClearTxt();
	}

	@Test (dependsOnMethods = "LoggedUserCanLogout"  , priority = 7)
	public void LoginWithEmptyAcess () 
	{
		LogPage.userLogin("", "");
		String error = LogPage.getError();
		assertEquals(error, "Epic sadface: Username and password do not match any user in this service","Error message is not correct");
		LogPage.ClearTxt();
	}


}
