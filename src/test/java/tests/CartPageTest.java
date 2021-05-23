package tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.InverntoryPage;
import pages.UserLoginPage;

public class CartPageTest extends TestBase
{

	InverntoryPage invPage;
	UserLoginPage LogPage;
	CartPage cartPage;
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
		invPage = new InverntoryPage(driver);
		Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
		invPage.addToCart(Item);
		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
		
		Item = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
		invPage.addToCart(Item);
		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "2");
	
		System.out.println("Two Items are added to the cart");
	}
	
	@Test(priority = 3 )
	public void OpenCartPage() 
	{	
		invPage.OpenCartPage();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
		System.out.println("The cart Page opened successfully ");

	}
	
	@Test(priority = 4 )
	public void UpdateQty() 
	{	try {
		cartPage= new CartPage(driver);
		cartPage.UpdateQty();
		}
		catch (Exception e) 
		{
			System.out.println("The system doesn't allow user to update the items quantity in the cart");
		}
	}
	
	@Test(priority = 5 )
	public void CheckRemoveItems() 
	{	
		Item = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
		cartPage.RemoveItemsFromCart(Item);
		Assert.assertEquals(driver.findElement(By.cssSelector("#shopping_cart_container > a > span")).getText(), "1");
		
		Item = driver.findElement(By.id("remove-sauce-labs-onesie"));
		cartPage.RemoveItemsFromCart(Item);
		
		java.util.List<WebElement> ItemsRmvNum = driver.findElements(By.cssSelector("#cart_contents_container > div > div.cart_list > div.removed_cart_item"));

		Assert.assertEquals(ItemsRmvNum.size(),2);
		System.out.println("The two added items are removed successully");

	}
	
	@Test(priority = 6 )
	public void CheckContinueShopping() 
	{	
		cartPage.ContinueShopping();
		System.out.println("The system allow continue shopping from the cart page");
	}
	
	@Test(priority = 7 )
	public void ProceedToCheckoutFromCartPage() 
	{	// Add items 
		invPage = new InverntoryPage(driver);
		Item = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
		invPage.addToCart(Item);
		
		Item = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
		invPage.addToCart(Item);
		// Open Cart 
		invPage.OpenCartPage();

		// Click on Checkout
		cartPage.ProceedToCheckOut();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html" );
		
		System.out.println("The system allow Checkout from the cart page");
	}
	
}
