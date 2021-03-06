package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageBase
{	

	public CartPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(className = "span.shopping_cart_badge")
	WebElement CartIcon;
	
	public void OpenCart () 
	{
		CartIcon.click();
	}
	
	
	public void RemoveItemsFromCart(WebElement Item) 
	{	  
			Item.click();	
	}
	
	
	@FindBy(css = "#cart_contents_container > div > div.cart_list > div:nth-child(3) > div.cart_quantity")
	WebElement ItemQty; 
	
	public void UpdateQty () 
	{
		ItemQty.clear();
		ItemQty.sendKeys("3");
	}
	
	@FindBy(id = "continue-shopping")
	WebElement ContShop; 
	
	public void ContinueShopping () 
	{
		ContShop.click();
	}
	
	
	@FindBy(id = "checkout")
	WebElement GotoCheckout; 
	public void ProceedToCheckOut () 
	{
		GotoCheckout.click();
	}
	
	
		
}
