package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	//create driver to deal with my page elements
	protected WebDriver driver;

	public static JavascriptExecutor jse;
	public Select select;
	public Actions action;

	//create constructor
	public PageBase(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public void ClearTxt (WebElement elem) 
	{
		elem.clear();
	}
}
