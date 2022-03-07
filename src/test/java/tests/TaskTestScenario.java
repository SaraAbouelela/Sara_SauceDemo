package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.TaskPageScenario;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskTestScenario extends TestBase
{
	TaskPageScenario taskPageScenario;

	 @Test
	public void navigateToSearchURL() throws InterruptedException {
         String regex="([0-9]+[.][0-9]+)";
         Pattern pattern=Pattern.compile(regex);
         Matcher matcher;

		 driver.navigate().to("https://www.autohero.com/de/search/");
		 driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/button[2]")).click();
		 driver.findElement(By.className("label___2A7vZ")).click();
		 driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div[1]/div/div/div[1]/div/div[1]/div[1]/ul/li[7]/input")).click();
		 driver.findElement(By.cssSelector("input[value='Golf']")).click();
		 driver.findElement(By.id("basicFilter")).click();
		 WebElement kilometerDDL = driver.findElement(By.cssSelector("select[data-qa-selector='select-mileage-to']"));
		 kilometerDDL.click();
		 Select maxMileage = new Select(kilometerDDL);
		 maxMileage.selectByVisibleText("25.000 km");
		 driver.findElement(By.id("basicFilter")).click();


		 JavascriptExecutor JS;
		 JS = (JavascriptExecutor) driver;

		 Thread.sleep(5000);
		 List<WebElement> Items = driver
				 .findElements(By.cssSelector("li.specItem___2u4I4:nth-of-type(3)"));
//				 .findElements(By.cssSelector("h2.title___1TYYE.adTitle___VxmTf"));

		 for (WebElement i:Items
		 ) {
			  matcher=pattern.matcher(i.getText());
			 while(matcher.find())
			 {
				 System.out.println(matcher.group());
				 Assert.assertTrue(Float.parseFloat(matcher.group())<25);
			 }
		 }
         WebElement ItemScroll = Items.get(Items.size()-1);
		 JS.executeScript("arguments[0].scrollIntoView();", ItemScroll);
		 Thread.sleep(3000);
		 java.util.List<WebElement> Updated = driver
				 .findElements(By.cssSelector("li.specItem___2u4I4:nth-of-type(3)"));

//				 .findElements(By.cssSelector("h2.title___1TYYE.adTitle___VxmTf"));
		 for (WebElement i:Updated
		 ) {
			 matcher=pattern.matcher(i.getText());
			 while(matcher.find())
			 {
				 System.out.println(matcher.group());
				 Assert.assertTrue(Float.parseFloat(matcher.group())<25);
			 }
		 }
	 }

//	@Test(priority = 1)
//	public void SuccessLogin() throws InterruptedException
//	{
//		LogPage = new UserLoginPage(driver);
//		driver.navigate().refresh();
//
//		LogPage.userLogin("standard_user" , "secret_sauce");
//		Assert.assertTrue("https://www.saucedemo.com/inventory.html".equals(driver.getCurrentUrl()));
//	}
//
//	@Test(priority = 2 )
//	public void AddItemUpdateCartbadge()
//	{
//		invPage = new InverntoryPage(driver);
//		Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
//		invPage.addToCart(Item);
//		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
//
//		Item = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
//		invPage.addToCart(Item);
//		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "2");
//
//		System.out.println("Two Items are added to the cart");
//	}
//
//	@Test(priority = 3 )
//	public void OpenCartPage()
//	{
//		invPage.OpenCartPage();
//		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
//		System.out.println("The cart Page opened successfully ");
//
//	}
//
//	@Test(priority = 4 )
//	public void UpdateQty()
//	{	try {
//		cartPage= new CartPage(driver);
//		cartPage.UpdateQty();
//		}
//		catch (Exception e)
//		{
//			System.out.println("The system doesn't allow user to update the items quantity in the cart");
//		}
//	}
//
//	@Test(priority = 5 )
//	public void CheckRemoveItems()
//	{
//		Item = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
//		cartPage.RemoveItemsFromCart(Item);
//		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
//
//		Item = driver.findElement(By.id("remove-sauce-labs-onesie"));
//		cartPage.RemoveItemsFromCart(Item);
//
//		java.util.List<WebElement> ItemsRmvNum = driver.findElements(By.cssSelector("#cart_contents_container > div > div.cart_list > div.removed_cart_item"));
//
//		Assert.assertEquals(ItemsRmvNum.size(),2);
//		System.out.println("The two added items are removed successully");
//
//	}
//
//	@Test(priority = 6 )
//	public void CheckContinueShopping()
//	{
//		cartPage.ContinueShopping();
//		System.out.println("The system allow continue shopping from the cart page");
//	}
//
//	@Test(priority = 7 )
//	public void ProceedToCheckoutFromCartPage()
//	{	// Add items
//		invPage = new InverntoryPage(driver);
//		Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
//		invPage.addToCart(Item);
//
//		Item = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
//		invPage.addToCart(Item);
//		// Open Cart
//		invPage.OpenCartPage();
//
//		// Click on Checkout
//		cartPage.ProceedToCheckOut();
//		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html" );
//
//		System.out.println("The system allow Checkout from the cart page");
//	}
//
}
