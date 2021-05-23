package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends PageBase
{

	public CheckoutInfoPage(WebDriver driver) 
	{
		super(driver);
	}
	
	PageBase PgBase= new PageBase(driver);

	@FindBy(id="first-name")
	WebElement FirstName;
	
	@FindBy(id="last-name")
	WebElement LastName;
	
	@FindBy(id="postal-code")
	WebElement PostalCode;
	
	@FindBy(css = "#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error > h3")
	WebElement ErrorMsg;
	
	@FindBy(id="continue")
	WebElement Contbtn;
	
	@FindBy(id="cancel")
	WebElement Cancelbtn;
	
	public void SetFirstName(String name) 
	{
		FirstName.clear();
		FirstName.sendKeys(name);
	}
	
	public void SetLastName(String name) 
	{
		LastName.clear();
		LastName.sendKeys(name);
	}
	
	public void SetPostalCode(String code) 
	{
		PostalCode.clear();
		PostalCode.sendKeys(code);
	}
	
	public String GetErrMsg() 
	{
		return ErrorMsg.getText();
	}
	
	public void ContinueToOverview() 
	{
		Contbtn.click();
	}
	
	public void ClearForm() 
	{
		PgBase.ClearTxt(FirstName);
		PgBase.ClearTxt(LastName);
		PgBase.ClearTxt(PostalCode);

	}
	
	public void CancelChkoutInfo() 
	{
		Cancelbtn.click();
	}
	
	
	

}
