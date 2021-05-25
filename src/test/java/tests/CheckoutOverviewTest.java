package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.InverntoryPage;
import pages.UserLoginPage;

public class CheckoutOverviewTest extends TestBase
{
	UserLoginPage LogPage;
	InverntoryPage InvPage;
	CartPage cartPage;
	CheckoutInfoPage ChkOutInfo;
	CheckoutOverviewPage ChkOutOverview;
	String ExpTotal , ActTotal;
	 WebElement Item ; 
/* Uncomment in case of stand alone execution 
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
			// item 1
			Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
			InvPage.addToCart(Item);
			Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
			// item 2
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
		
		@Test(priority = 4 )
		public void ProceedToCheckoutInfo() 
		{	
			cartPage = new CartPage(driver);
			cartPage.ProceedToCheckOut();
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
			System.out.println("The checkout info Page opened successfully ");
		}
		
		
		@Test ( priority = 5)
		public void ProceedToCheckoutOverview()
		{ 	
			ChkOutInfo = new CheckoutInfoPage(driver);
			ChkOutInfo.SetFirstName("mohamed");
			ChkOutInfo.SetLastName("123");
			ChkOutInfo.SetPostalCode("123");
			
			ChkOutInfo.ContinueToOverview();
			
			Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html"));
		}
		*/
	 
		@Test ( priority = 6)
		public void CheckPaymentInfo()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			Assert.assertEquals(ChkOutOverview.CheckPaymentInfo(), "SauceCard #31337", "The Payment Information is wrongly displayed");
		}
		@Test ( priority = 7)
		public void CheckShippingInfo()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			Assert.assertEquals(ChkOutOverview.CheckShippingInfo(), "FREE PONY EXPRESS DELIVERY!", "The Payment Information is wrongly displayed");
		}
		
		@Test ( priority = 8 , enabled = false)
		public void CheckTotalCost()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			ExpTotal = "Item total: $"+ChkOutOverview.GetTotal();
			ActTotal = ChkOutOverview.CheckTotalCost()+"";
			Assert.assertEquals(ActTotal, ExpTotal);
		}
		
		@Test ( priority = 9)
		public void CheckTax()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			Assert.assertEquals(ChkOutOverview.CheckTax(), "Tax: $4.64");
		}
		
		@Test ( priority = 10)
		public void CheckCancelingCheckout()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			ChkOutOverview.CancelPayment();
			Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));
		}
		
		@Test ( priority = 11)
		public void CheckFinishCheckout()
		{
			ChkOutOverview = new CheckoutOverviewPage(driver);
			driver.navigate().back();
			ChkOutOverview.FinishCheckout();
			Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html"));
			Assert.assertEquals(ChkOutOverview.ThnkMsgCheck(),("THANK YOU FOR YOUR ORDER"));
		}
		

}
