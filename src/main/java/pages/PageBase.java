package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	//create driver to deal with my page elements
	protected WebDriver driver;

	//create constructor
	public PageBase(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void navigateToURL (String URL)
	{
		driver.navigate().to(URL);
	}
}
