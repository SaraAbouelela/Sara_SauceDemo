package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserLoginPage extends PageBase
{

	public UserLoginPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy (id = "user-name")
	WebElement userNametxt;
	
	@FindBy (id = "password")
	WebElement passwordtxt;
	
	@FindBy (id = "login-button")
	WebElement loginbtn;
	
	@FindBy(css = "[data-test=error]")
	WebElement ERROR_MESSAGE;
	
	@FindBy (className=("bm-burger-button"))
	WebElement Menu;
	
	@FindBy (id="logout_sidebar_link")
	WebElement Logoutbtn;
	
	public void userLogin( String fName , String Pass)
	{
		userNametxt.sendKeys(fName);
		passwordtxt.sendKeys(Pass);
		loginbtn.click();		
	}

    public void open() 
    {
        driver.get("https://www.saucedemo.com/");
    }
    
	public String getError() 
	{
	        return ERROR_MESSAGE.getText();
	}
	
	public void UserLogout() 
	{
		Menu.click();
		Logoutbtn.click();
	}
	
	public void ClearTxt() 
	{
		userNametxt.clear();
		passwordtxt.clear();
	}
	
}
