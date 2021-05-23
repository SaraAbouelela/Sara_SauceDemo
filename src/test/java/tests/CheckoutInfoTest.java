package tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutInfoPage;
import pages.InverntoryPage;
import pages.UserLoginPage;

public class CheckoutInfoTest extends TestBase
{
	UserLoginPage LogPage;
	InverntoryPage InvPage;
	CartPage cartPage;
	CheckoutInfoPage ChkOutInfo;
	 WebElement Item ; 

		@Test(priority = 1)
		public void SuccessLogin() 
		{		
			LogPage = new UserLoginPage(driver);
			LogPage.ClearTxt();
			LogPage.userLogin("standard_user" , "secret_sauce");
			Assert.assertTrue("https://www.saucedemo.com/inventory.html".equals(driver.getCurrentUrl()));
		}

		@Test(priority = 2 )
		public void AddItemUpdateCartbadge() 
		{	
			InvPage = new InverntoryPage(driver);
			Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
			InvPage.addToCart(Item);
			Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
			
			Item = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
			InvPage.addToCart(Item);
			Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "2");
		
			System.out.println("Two Items are added to the cart");
		}
		
		@Test(priority = 3 )
		public void OpenCartPage() 
		{	
			InvPage.OpenCartPage();
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
			System.out.println("The cart Page opened successfully ");

		}
	 
	@Test ( priority = 4)
	public void FirstNameISRequired()
	{
		// Click on Checkout
		cartPage = new CartPage(driver);
		cartPage.ProceedToCheckOut();
		
		ChkOutInfo = new CheckoutInfoPage(driver);
		//Leave FirstNAme Empty 
		ChkOutInfo.SetLastName("ahmed");
		ChkOutInfo.SetPostalCode("123");
		ChkOutInfo.ContinueToOverview();

		Assert.assertTrue(ChkOutInfo.GetErrMsg().equalsIgnoreCase("Error: First Name is required"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@Test ( priority = 5 , dependsOnMethods = "FirstNameISRequired")
	public void CheckCancelChkoutInfo()
	{
		ChkOutInfo = new CheckoutInfoPage(driver);
		ChkOutInfo.CancelChkoutInfo();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html"));
	}
	
	@Test ( priority = 6)
	public void LastNameISRequired()
	{
		cartPage = new CartPage(driver);
		ChkOutInfo = new CheckoutInfoPage(driver);
		cartPage.ProceedToCheckOut();
		ChkOutInfo.SetFirstName("mohamed");
		ChkOutInfo.SetPostalCode("123");
		ChkOutInfo.ContinueToOverview();
		
		Assert.assertTrue(ChkOutInfo.GetErrMsg().equalsIgnoreCase("Error: Last Name is required"));
	}
	
	
	@Test ( priority =7)
	public void PostalCodeISRequired()
	{
		cartPage = new CartPage(driver);
		ChkOutInfo = new CheckoutInfoPage(driver);
		ChkOutInfo.CancelChkoutInfo();
		cartPage.ProceedToCheckOut();
		ChkOutInfo.SetFirstName("mohamed");
		ChkOutInfo.SetLastName("123");
		ChkOutInfo.SetPostalCode("");
		ChkOutInfo.ContinueToOverview();
		
		Assert.assertTrue(ChkOutInfo.GetErrMsg().equalsIgnoreCase("Error: Postal Code is required"));
	}

	@Test ( priority = 8)
	public void ProceedToCheckoutOverviewPage()
	{
		ChkOutInfo = new CheckoutInfoPage(driver);
		ChkOutInfo.SetFirstName("mohamed");
		ChkOutInfo.SetLastName("123");
		ChkOutInfo.SetPostalCode("123");
		ChkOutInfo.ContinueToOverview();

		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html"));
	}
}
