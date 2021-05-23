package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InverntoryPage extends PageBase
{

	public InverntoryPage(WebDriver driver)
	{
		super(driver);

	}
	
	public void addToCart(WebElement Item) 
	{	  
			Item.click();	
	}
	
	@FindBy(id = "shopping_cart_container")
	WebElement CartIcon;
	public void OpenCartPage() 
	{	  
		CartIcon.click();
	}
	
	
}
