package sourishBakshiFrameworkAuto.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sourishBakshiFrameworkAuto.Utils.Utils;

public class CheckOutPage extends Utils {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	By itemDropList = By.cssSelector(".ta-item");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".ta-item:last-of-type")
	WebElement selectChoicedCountry;
	
	@FindBy(xpath="//a[contains(@class,'submit')]")
	WebElement placeOrder;
	
	public void selectCountry(String country)
	{
		Actions act = new Actions(driver);
		act.sendKeys(selectCountry, country).build().perform();
		waitforElementToAppear(itemDropList);
		selectChoicedCountry.click();
		waitforElementToDisappear(selectChoicedCountry);
	}
	
	public ConfirmationPage placeOrder() 
	{
		scrollDown();
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	

}
