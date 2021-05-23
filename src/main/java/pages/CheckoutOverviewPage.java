package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends PageBase
{

	public CheckoutOverviewPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]")
	WebElement PaymentInfo;
	
	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]")
	WebElement ShippingInfo;
	
	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[5]")
	WebElement TotalCost;
	
	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div/text()[2]")
	String Item1Cost;
	
	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div/text()[2]")
	String Item2Cost;
	
	@FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")
	WebElement Tax;
	
	@FindBy(id = "cancel")
	WebElement Cancelbtn;
	
	@FindBy(id = "finish")
	WebElement Finishbtn;
	
	@FindBy(xpath = "//*[@id=\"checkout_complete_container\"]/h2")
	WebElement ThnkMsg;
	
	
	public String CheckPaymentInfo() 
	{
		return PaymentInfo.getText();
	}
	public String CheckShippingInfo() 
	{
		return ShippingInfo.getText();
	}
	
	public float CheckTotalCost() 
	{		
		return Float.parseFloat(Item1Cost.replaceAll("[^0-9]", ""))+Float.parseFloat(Item2Cost.replaceAll("[^0-9]", ""));
	}
	public String GetTotal() 
	{
		return TotalCost.getText();
	}
	
	public String CheckTax() 
	{
		return Tax.getText();
	}
	public void CancelPayment()
	{
		 Cancelbtn.click();
	}
	public void FinishCheckout() 
	{
		Finishbtn.click();
	}
	
	public String ThnkMsgCheck() 
	{
		return ThnkMsg.getText();
	}
	
	
}
